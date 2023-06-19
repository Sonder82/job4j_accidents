package ru.job4j.accidents.service.hibernate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentMem;
import ru.job4j.accidents.repository.AccidentTypeMem;
import ru.job4j.accidents.repository.RuleMem;
import ru.job4j.accidents.service.AccidentService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class HibernateAccidentService implements AccidentService {

    private final AccidentMem accidentRepository;

    private final AccidentTypeMem typeRepository;

    private final RuleMem ruleRepository;

    @Override
    public Optional<Accident> save(Accident accident, List<Integer> list) {
        Optional<Accident> result = Optional.empty();
        var type = typeRepository.findById(accident.getType().getId());
        if (type.isPresent()) {
            accident.setType(type.get());
            Set<Rule> rulesID = ruleRepository.findByIdList(list);
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
    public boolean update(Accident accident, List<Integer> list) {
        boolean result = false;
        var type = typeRepository.findById(accident.getType().getId());
        if (type.isPresent()) {
            accident.setType(type.get());
            Set<Rule> rulesID = ruleRepository.findByIdList(list);
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
