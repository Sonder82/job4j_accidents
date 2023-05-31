package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeMem;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleAccidentTypeService implements AccidentTypeService {

    private final AccidentTypeMem typeRepository;

    @Override
    public AccidentType save(AccidentType type) {
        return typeRepository.save(type);
    }

    @Override
    public boolean deleteById(int id) {
        return typeRepository.deleteById(id);
    }

    @Override
    public boolean update(AccidentType type) {
        return typeRepository.update(type);
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return typeRepository.findById(id);
    }

    @Override
    public Collection<AccidentType> findAll() {
        return typeRepository.findAll();
    }
}
