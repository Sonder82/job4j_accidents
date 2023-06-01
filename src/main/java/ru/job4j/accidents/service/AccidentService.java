package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AccidentService {

    Optional<Accident> save(Accident accident, List<Integer> list);

    boolean deleteById(int id);

    boolean update(Accident accident, List<Integer> list);

    Optional<Accident> findById(int id);

    Collection<Accident> findAll();
}
