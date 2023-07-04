package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

public interface AccidentTypeRepository {

    AccidentType save(AccidentType type);

    boolean deleteById(int id);

    boolean update(AccidentType type);

    Optional<AccidentType> findById(int id);

    Collection<AccidentType> findAll();
}
