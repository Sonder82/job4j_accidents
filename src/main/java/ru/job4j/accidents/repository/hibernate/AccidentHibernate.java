package ru.job4j.accidents.repository.hibernate;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMem;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentHibernate implements AccidentMem {

    private static final Logger LOG = LoggerFactory.getLogger(AccidentHibernate.class.getName());

    private final CrudRepository crudRepository;

    @Override
    public Optional<Accident> save(Accident accident) {
        Optional<Accident> rsl = Optional.empty();
        try {
            crudRepository.run(session -> session.persist(accident));
            rsl = Optional.of(accident);
        } catch (Exception e) {
            LOG.error("Error message: " + e.getMessage(), e);
        }
        return rsl;
    }

    @Override
    public boolean deleteById(int id) {
        boolean rsl = false;
        try {
            crudRepository.run(
                    "DELETE Accident WHERE id = :fId",
                    Map.of("fId", id)
            );
            rsl = true;
        } catch (Exception e) {
            LOG.error("Error message: " + e.getMessage(), e);
        }
        return rsl;
    }

    @Override
    public boolean update(Accident accident) {
        boolean rsl = false;
        try {
            crudRepository.run(session -> session.merge(accident));
            rsl = true;
        } catch (Exception e) {
            LOG.error("Error message: " + e.getMessage(), e);
        }
        return rsl;
    }

    @Override
    public Optional<Accident> findById(int id) {
        return crudRepository.optional(
                "FROM Accident a JOIN FETCH a.rules WHERE a.id = :fId", Accident.class,
                Map.of("fId", id)
        );
    }

    @Override
    public Collection<Accident> findAll() {
        return crudRepository.query(
                "SELECT DISTINCT a FROM Accident a JOIN FETCH a.rules", Accident.class);
    }
}
