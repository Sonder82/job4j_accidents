package ru.job4j.accidents.service.data;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.data.RuleData;
import ru.job4j.accidents.service.RuleService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Primary
@Service
@AllArgsConstructor
public class DataRuleService implements RuleService {

    private final RuleData ruleRepository;

    @Override
    public Rule save(Rule rule) {
        return ruleRepository.save(rule);
    }

    @Override
    public boolean deleteById(int id) {
        ruleRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean update(Rule rule) {
        ruleRepository.save(rule);
        return true;
    }

    @Override
    public Optional<Rule> findById(int id) {
        return ruleRepository.findById(id);
    }

    @Override
    public Set<Rule> findByIdList(List<Integer> listId) {
        return ruleRepository.findByIdList(listId);
    }

    @Override
    public Collection<Rule> findAll() {
        return ruleRepository.findAll();
    }
}
