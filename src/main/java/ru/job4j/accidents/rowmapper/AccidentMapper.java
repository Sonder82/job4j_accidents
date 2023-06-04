package ru.job4j.accidents.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class AccidentMapper implements RowMapper<Accident> {

    @Override
    public Accident mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Accident accident = new Accident();

        accident.setId(resultSet.getInt("id"));
        accident.setType(new AccidentType(resultSet.getInt("type_id"), resultSet.getString("type_name")));
        accident.setText(resultSet.getString("text"));
        accident.setAddress(resultSet.getString("address"));
        accident.setRules(Set.of(new Rule(
                resultSet.getInt("rule_id"), resultSet.getString("rule_name"))));
        return accident;
    }
}
