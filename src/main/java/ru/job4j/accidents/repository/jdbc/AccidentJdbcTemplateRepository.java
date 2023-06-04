package ru.job4j.accidents.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.rowmapper.AccidentMapper;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMem;

import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplateRepository implements AccidentMem {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Accident> save(Accident accident) {
        Optional<Accident> rsl = Optional.empty();
        jdbcTemplate.update("insert into accidents (name, accident_type_id, text, address) values (?, ?, ?, ?)",
                accident.getName(),
                accident.getType().getId(),
                accident.getText(),
                accident.getAddress());
        rsl = Optional.of(accident);
        return rsl;
    }

    @Override
    public boolean deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM Accident WHERE id = ?", id) > 0;
    }

    @Override
    public boolean update(Accident accident) {
        return jdbcTemplate.update("UPDATE Accident SET name = ?, accident_type_id = ?, text = ?, address = ?",
                accident.getName(), accident.getType(), accident.getText(), accident.getAddress()) > 0;
    }

    @Override
    public Optional<Accident> findById(int id) {
        return jdbcTemplate.query("SELECT * FROM Accident WHERE id = ?", new AccidentMapper(), id)
                .stream().findFirst();
    }

    @Override
    public Collection<Accident> findAll() {
        return jdbcTemplate.query("SELECT * FROM Accident", new AccidentMapper());
    }
}
