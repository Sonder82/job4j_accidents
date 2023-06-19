package ru.job4j.accidents.repository.hibernate;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeMem;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentTypeHibernate implements AccidentTypeMem {

    private static final Logger LOG = LoggerFactory.getLogger(AccidentTypeHibernate.class.getName());

    private final CrudRepository crudRepository;

    @Override
    public AccidentType save(AccidentType type) {
        try {
            crudRepository.run(session -> session.persist(type));
        } catch (Exception e) {
            LOG.error("Error message: " + e.getMessage(), e);
        }
        return type;
    }

    @Override
    public boolean deleteById(int id) {
        boolean rsl = false;
        try {
            crudRepository.run(
                    "DELETE AccidentType WHERE id = :fId",
                    Map.of("fId", id)
            );
            rsl = true;
        } catch (Exception e) {
            LOG.error("Error message: " + e.getMessage(), e);
        }
        return rsl;
    }

    @Override
    public boolean update(AccidentType type) {
        boolean rsl = false;
        try {
            crudRepository.run(session -> session.merge(type));
            rsl = true;
        } catch (Exception e) {
            LOG.error("Error message: " + e.getMessage(), e);
        }
        return rsl;
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
