package ru.job4j.accidents.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMem;
import ru.job4j.accidents.resultsetextractor.AccidentResultSetExtractor;

import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplateRepository implements AccidentMem {

    private static final String SQL_INSERT_INTO_ACCIDENTS = "INSERT INTO accidents (name,"
          +  " accident_type_id, description, address) values (?, ?, ?, ?)";

    private static final String SQL_DELETE_FROM_ACCIDENTS = "DELETE FROM accidents WHERE id = ?";

    private static final String SQL_UPDATE_ACCIDENTS = "UPDATE accidents SET name = ?,"
         +   " accident_type_id = ?, description = ?, address = ?";

    private static final String SQL_INSERT_INTO_ACCIDENT_RULES = "INSERT INTO accident_rules (accident_id,"
         +   " rule_id) values (?, ?)";

    private static final String SQL_SELECT_FROM_ACCIDENTS = "SELECT * FROM accidents AS ac"
           + " LEFT JOIN accident_types AS at ON ac.type_id = at.id"
           + " LEFT JOIN accident_rules AS ar ON ac.id = ar.accident_id"
           + " LEFT JOIN rules AS r ON ar.rule_id = r.id";

    private static final String SQL_SELECT_FROM_ACCIDENTS_BY_ID = "SELECT * FROM accidents AS ac"
           + " LEFT JOIN accident_types AS at ON ac.type_id = at.id"
           + " LEFT JOIN accident_rules AS ar ON ac.id = ar.accident_id"
           + " LEFT JOIN rules AS r ON ar.rule_id = r.id WHERE ac.id = ?";

    private static final String SQL_DELETE_FROM_ACCIDENT_RULES = "DELETE FROM accident_rules WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Accident> save(Accident accident) {
        Optional<Accident> rsl = Optional.empty();
        jdbcTemplate.update(SQL_INSERT_INTO_ACCIDENTS,
                accident.getName(),
                accident.getType().getId(),
                accident.getDescription(),
                accident.getAddress());
        rsl = Optional.of(accident);
        saveAccidentRules(accident);
        return rsl;
    }

    @Override
    public boolean deleteById(int id) {
        return jdbcTemplate.update(SQL_DELETE_FROM_ACCIDENTS, id) > 0;
    }

    @Override
    public boolean update(Accident accident) {
        deleteAccidentRules(accident);
        boolean rsl = jdbcTemplate.update(SQL_UPDATE_ACCIDENTS,
                accident.getName(), accident.getType(), accident.getDescription(), accident.getAddress()) > 0;
        saveAccidentRules(accident);
        return rsl;
    }

    @Override
    public Optional<Accident> findById(int id) {
        var accidents = jdbcTemplate.query(
                SQL_SELECT_FROM_ACCIDENTS_BY_ID, new AccidentResultSetExtractor(), id);
        return accidents != null ? accidents.stream().findFirst() : Optional.empty();
    }

    @Override
    public Collection<Accident> findAll() {
        return jdbcTemplate.query(SQL_SELECT_FROM_ACCIDENTS, new AccidentResultSetExtractor());
    }

    private void saveAccidentRules(Accident accident) {
        for (Rule rule : accident.getRules()) {
            jdbcTemplate.update(SQL_INSERT_INTO_ACCIDENT_RULES,
                    accident.getId(),
                    rule.getId());
        }
    }

    private void deleteAccidentRules(Accident accident) {
        jdbcTemplate.update(SQL_DELETE_FROM_ACCIDENT_RULES, accident.getId());
    }
}
