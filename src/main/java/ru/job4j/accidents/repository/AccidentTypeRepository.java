package ru.job4j.accidents.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;
import java.util.Optional;

public interface AccidentTypeRepository extends CrudRepository<AccidentType, Integer> {

    @Query("SELECT DISTINCT a FROM Accident")
    List<AccidentType> findAll();

    @Query("FROM Accident a WHERE a.id = :id")
    Optional<AccidentType> findById(@Param("id") int id);
}
