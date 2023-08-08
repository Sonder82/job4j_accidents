package ru.job4j.accidents.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepository;

import java.util.*;

//@Repository
@AllArgsConstructor
public class RuleJdbcTemplateRepository implements RuleRepository {

    private static final String SQL_INSERT_INTO_RULES = "INSERT INTO rules (name) values (?)";

    private static final String SQL_DELETE_FROM_RULES = "DELETE FROM rules WHERE id = ?";

    private static final String SQL_UPDATE_RULES = "UPDATE rules SET name = ?";

    private static final String SQL_SELECT_RULES_BY_ID = "SELECT * FROM rules WHERE id = ?";

    private static final String SQL_SELECT_RULES = "SELECT * FROM rules";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Rule save(Rule rule) {
        jdbcTemplate.update(SQL_INSERT_INTO_RULES, rule.getName());
        return rule;
    }

    @Override
    public boolean deleteById(int id) {
        return jdbcTemplate.update(SQL_DELETE_FROM_RULES, id) > 0;
    }

    @Override
    public boolean update(Rule rule) {
        return jdbcTemplate.update(SQL_UPDATE_RULES, rule.getName()) > 0;
    }

    @Override
    public Optional<Rule> findById(int id) {
        var rules = jdbcTemplate.query(
                SQL_SELECT_RULES_BY_ID, (rs, rowNum) -> new Rule(
                        rs.getInt("id"), rs.getString("name")), id);
        return rules.stream().findFirst();
    }

    @Override
    public Set<Rule> findByIdList(List<Integer> listId) {
        Set<Rule> ruleSet = new HashSet<>();
        for (Integer integer : listId) {
            ruleSet.add(findById(integer).get());
        }
        return ruleSet;
    }

    @Override
    public Collection<Rule> findAll() {
        return jdbcTemplate.query(
                SQL_SELECT_RULES, (rs, rowNum) -> new Rule(
                        rs.getInt("id"), rs.getString("name")));
    }
}
