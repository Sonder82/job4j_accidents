package ru.job4j.accidents.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * Вариант репозитория с JDBC
 * @Repository
 */
@AllArgsConstructor
public class AccidentTypeJdbcTemplateRepository implements AccidentTypeRepository {

    private static final String SQL_INSERT_INTO_ACCIDENT_TYPES = "INSERT INTO accident_types (name) values (?)";

    private static final String SQL_DELETE_FROM_ACCIDENT_TYPES = "DELETE FROM accident_types WHERE id = ?";

    private static final String SQL_UPDATE_ACCIDENT_TYPES = "UPDATE accident_types SET name = ?";

    private static final String SQL_SELECT_TYPES_BY_ID = "SELECT * FROM accident_types WHERE id = ?";

    private static final String SQL_SELECT_TYPES = "SELECT * FROM accident_types";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public AccidentType save(AccidentType type) {
        jdbcTemplate.update(SQL_INSERT_INTO_ACCIDENT_TYPES, type.getName());
        return type;
    }

    @Override
    public boolean deleteById(int id) {
        return jdbcTemplate.update(SQL_DELETE_FROM_ACCIDENT_TYPES, id) > 0;
    }

    @Override
    public boolean update(AccidentType type) {
        return jdbcTemplate.update(SQL_UPDATE_ACCIDENT_TYPES, type.getName()) > 0;
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        var types = jdbcTemplate.query(
                SQL_SELECT_TYPES_BY_ID, (rs, rowNum) -> new AccidentType(
                        rs.getInt("id"), rs.getString("name")), id);
        return types.stream().findFirst();
    }

    @Override
    public Collection<AccidentType> findAll() {
        return jdbcTemplate.query(
                SQL_SELECT_TYPES, (rs, rowNum) -> new AccidentType(
                        rs.getInt("id"), rs.getString("name")));
    }
}
