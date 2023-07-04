package ru.job4j.accidents.service.data;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.data.AccidentData;
import ru.job4j.accidents.repository.data.AccidentTypeData;
import ru.job4j.accidents.repository.data.RuleData;
import ru.job4j.accidents.service.AccidentService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Primary
@Service
@AllArgsConstructor
public class DataAccidentService implements AccidentService {

    private final AccidentData accidentRepository;

    private final AccidentTypeData typeRepository;

    private final RuleData ruleMemory;

    @Override
    public Optional<Accident> save(Accident accident, List<Integer> list) {
        Optional<Accident> result = Optional.empty();
        var type = typeRepository.findById(accident.getType().getId());
        if (type.isPresent()) {
            accident.setType(type.get());
            Set<Rule> rulesID = ruleMemory.findByIdList(list);
            accident.setRules(rulesID);
            accidentRepository.save(accident);
            result = Optional.of(accident);
        }
        return result;
    }

    @Override
    public boolean deleteById(int id) {
        accidentRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean update(Accident accident, List<Integer> list) {
        boolean result = false;
        var type = typeRepository.findById(accident.getType().getId());
        if (type.isPresent()) {
            accident.setType(type.get());
            Set<Rule> rulesID = ruleMemory.findByIdList(list);
            accident.setRules(rulesID);
            accidentRepository.save(accident);
            result = true;
        }
        return result;
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }

    @Override
    public Collection<Accident> findAll() {
        return accidentRepository.findAll();
    }
}
