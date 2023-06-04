package ru.job4j.accidents.service.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMem;
import ru.job4j.accidents.service.AccidentService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JdbcAccidentService implements AccidentService {

    private final AccidentMem repository;

    @Override
    public Optional<Accident> save(Accident accident, List<Integer> list) {
        return repository.save(accident);
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public boolean update(Accident accident, List<Integer> list) {
        return false;
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<Accident> findAll() {
        return null;
    }
}
