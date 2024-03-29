package ru.job4j.accidents.service.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.jdbc.AccidentTypeJdbcTemplateRepository;
import ru.job4j.accidents.service.AccidentTypeService;

import java.util.Collection;
import java.util.Optional;

/**
 * Вариант сервиса с JDBC
 * @Service
 */
@AllArgsConstructor
public class JdbcAccidentTypeService implements AccidentTypeService {

    private final AccidentTypeJdbcTemplateRepository typeRepository;

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
