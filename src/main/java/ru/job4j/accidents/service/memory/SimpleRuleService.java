package ru.job4j.accidents.service.memory;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.memory.MemoryRuleRepository;
import ru.job4j.accidents.service.RuleService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Вариант сервиса с memory
 * @Service
 */
@AllArgsConstructor
public class SimpleRuleService implements RuleService {

    private final MemoryRuleRepository ruleRepository;

    @Override
    public Rule save(Rule rule) {
        return ruleRepository.save(rule);
    }

    @Override
    public boolean deleteById(int id) {
        return ruleRepository.deleteById(id);
    }

    @Override
    public boolean update(Rule rule) {
        return ruleRepository.update(rule);
    }

    @Override
    public Optional<Rule> findById(int id) {
        return ruleRepository.findById(id);
    }

    @Override
    public Set<Rule> findByIdList(List<Integer> list) {
        return ruleRepository.findByIdList(list);
    }

    @Override
    public Collection<Rule> findAll() {
        return ruleRepository.findAll();
    }
}
