package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Optional;

public interface AccidentService {

    Optional<Accident> save(Accident accident);

    boolean deleteById(int id);

    boolean update(Accident accident);

    Optional<Accident> findById(int id);

    Collection<Accident> findAll();
}
