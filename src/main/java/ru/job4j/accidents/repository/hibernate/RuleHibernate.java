package ru.job4j.accidents.repository.hibernate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepository;

import java.util.*;

/**
 * Вариант репозитория с Hibernate
 * @Repository
 */
@AllArgsConstructor
public class RuleHibernate implements RuleRepository {

    private final CrudRepository crudRepository;

    @Override
    public Rule save(Rule rule) {
        crudRepository.run(session -> session.persist(rule));
        return rule;
    }

    @Override
    public boolean deleteById(int id) {
        return crudRepository.booleanQuery(
                "DELETE Rule WHERE id = :fId",
                Map.of("fId", id));
    }

    @Override
    public boolean update(Rule rule) {
        crudRepository.run(session -> session.merge(rule));
        return true;
    }

    @Override
    public Optional<Rule> findById(int id) {
        return crudRepository.optional(
                "FROM Rule r WHERE r.id = :fId", Rule.class,
                Map.of("fId", id)
        );
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
        return crudRepository.query(
                "SELECT DISTINCT a FROM Rule", Rule.class);
    }
}
