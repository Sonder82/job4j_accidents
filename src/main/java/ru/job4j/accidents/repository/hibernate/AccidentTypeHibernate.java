package ru.job4j.accidents.repository.hibernate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeMem;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentTypeHibernate implements AccidentTypeMem {

    private final CrudRepository crudRepository;

    @Override
    public AccidentType save(AccidentType type) {
        crudRepository.run(session -> session.persist(type));
        return type;
    }

    @Override
    public boolean deleteById(int id) {
        return crudRepository.booleanQuery(
                "DELETE AccidentType WHERE id = :fId",
                Map.of("fId", id)
        );
    }

    @Override
    public boolean update(AccidentType type) {
        crudRepository.run(session -> session.merge(type));
        return true;
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return crudRepository.optional(
                "FROM AccidentType a WHERE a.id = :fId", AccidentType.class,
                Map.of("fId", id)
        );
    }

    @Override
    public Collection<AccidentType> findAll() {
        return crudRepository.query(
                "SELECT DISTINCT a FROM AccidentType", AccidentType.class);
    }
}
