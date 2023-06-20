package ru.job4j.accidents.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;

import java.util.List;
import java.util.Optional;

public interface RuleRepository extends CrudRepository<Rule, Integer> {

    @Query("SELECT DISTINCT r FROM Rule")
    List<Rule> findAll();

    @Query("FROM Rule r WHERE r.id = :id")
    Optional<Rule> findById(@Param("id") int id);
}
