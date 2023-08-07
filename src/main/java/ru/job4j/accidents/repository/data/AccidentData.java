package ru.job4j.accidents.repository.data;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

@Primary
@Repository
public interface AccidentData extends CrudRepository<Accident, Integer> {

    @Query("SELECT DISTINCT a FROM Accident AS a JOIN FETCH a.type JOIN FETCH a.rules")
    List<Accident> findAll();

    @Query("SELECT a FROM Accident AS a JOIN FETCH a.type JOIN FETCH a.rules WHERE a.id = :fId")
    Optional<Accident> findById(@Param("fId") int id);
}
