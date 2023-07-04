package ru.job4j.accidents.service.memory;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentRepository;
import ru.job4j.accidents.repository.AccidentTypeRepository;
import ru.job4j.accidents.repository.RuleRepository;
import ru.job4j.accidents.repository.memory.MemoryAccidentRepository;
import ru.job4j.accidents.repository.memory.MemoryAccidentTypeRepository;
import ru.job4j.accidents.repository.memory.MemoryRuleRepository;
import ru.job4j.accidents.service.AccidentService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class SimpleAccidentService implements AccidentService {

    private final MemoryAccidentRepository accidentRepository;

    private final MemoryAccidentTypeRepository typeRepository;

    private final MemoryRuleRepository ruleRepository;

    @Override
    public Optional<Accident> save(Accident accident, List<Integer> ruleListId) {
        Optional<Accident> result = Optional.empty();
        var type = typeRepository.findById(accident.getType().getId());
        if (type.isPresent()) {
            accident.setType(type.get());
            Set<Rule> rulesID = ruleRepository.findByIdList(ruleListId);
            accident.setRules(rulesID);
            accidentRepository.save(accident);
            result = Optional.of(accident);
        }
        return result;
    }

    @Override
    public boolean deleteById(int id) {
        return accidentRepository.deleteById(id);
    }

    @Override
    public boolean update(Accident accident, List<Integer> ruleListId) {
        boolean result = false;
        var type = typeRepository.findById(accident.getType().getId());
        if (type.isPresent()) {
            accident.setType(type.get());
            Set<Rule> rulesID = ruleRepository.findByIdList(ruleListId);
            accident.setRules(rulesID);
            result = accidentRepository.update(accident);
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
