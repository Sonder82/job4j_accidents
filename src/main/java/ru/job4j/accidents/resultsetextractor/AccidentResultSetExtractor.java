package ru.job4j.accidents.resultsetextractor;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AccidentResultSetExtractor implements ResultSetExtractor<List<Accident>> {

    @Override
    public List<Accident> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, Accident> accidents = new HashMap<>();
        while (rs.next()) {
            Accident accident = new Accident();
            accident.setId(rs.getInt("accident_id"));
            accident.setType(new AccidentType(rs.getInt("type_id"), rs.getString("type_name")));
            accident.setRules(new HashSet<>());
            accident.setDescription(rs.getString("accident_description"));
            accident.setAddress(rs.getString("accident_address"));
            accidents.putIfAbsent(accident.getId(), accident);
            accidents.get(accident.getId()).getRules().add(new Rule(
                    rs.getInt("rule_id"),
                    rs.getString("rule_name")
            ));

        }
        return new ArrayList<>(accidents.values());
    }
}
