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

    @Query("SELECT DISTINCT a FROM Accident a JOIN FETCH a.rules")
    List<Accident> findAll();

    @Query("FROM Accident a JOIN FETCH a.rules WHERE a.id = :id")
    Optional<Accident> findById(@Param("id") int id);
}
