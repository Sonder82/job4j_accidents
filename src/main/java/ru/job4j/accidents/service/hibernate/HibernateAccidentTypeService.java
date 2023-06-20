package ru.job4j.accidents.service.hibernate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeRepository;
import ru.job4j.accidents.service.AccidentTypeService;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HibernateAccidentTypeService implements AccidentTypeService {

    private final AccidentTypeRepository typeRepository;

    @Override
    public AccidentType save(AccidentType type) {
        return typeRepository.save(type);
    }

    @Override
    public boolean deleteById(int id) {
        typeRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean update(AccidentType type) {
         typeRepository.save(type);
        return true;
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
