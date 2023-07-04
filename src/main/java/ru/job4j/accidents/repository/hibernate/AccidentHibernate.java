package ru.job4j.accidents.repository.hibernate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentHibernate implements AccidentRepository {

    private final CrudRepository crudRepository;

    @Override
    public Optional<Accident> save(Accident accident) {
        crudRepository.run(session -> session.persist(accident));
        return Optional.of(accident);
    }

    @Override
    public boolean deleteById(int id) {
        return crudRepository.booleanQuery(
                    "DELETE Accident WHERE id = :fId",
                    Map.of("fId", id)
            );
    }

    @Override
    public boolean update(Accident accident) {
            crudRepository.run(session -> session.merge(accident));
        return true;
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
