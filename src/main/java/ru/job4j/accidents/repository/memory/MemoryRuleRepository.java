package ru.job4j.accidents.repository.memory;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleMem;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MemoryRuleRepository implements RuleMem {

    private final AtomicInteger id = new AtomicInteger();

    private final Map<Integer, Rule> accidentRules = new ConcurrentHashMap<>();

    public MemoryRuleRepository() {

        save(new Rule(0, "Cтатья. 1"));
        save(new Rule(0, "Cтатья. 2"));
        save(new Rule(0, "Cтатья. 3"));
    }

    @Override
    public Rule save(Rule rule) {
        rule.setId(id.incrementAndGet());
        accidentRules.put(rule.getId(), rule);
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return accidentRules.remove(id) != null;
    }

    @Override
    public boolean update(Rule rule) {
        return accidentRules.computeIfPresent(
                rule.getId(), (id, oldAccidentType) -> new Rule(id, rule.getName())) != null;
    }

    @Override
    public Optional<Rule> findById(int id) {
        return Optional.of(accidentRules.get(id));
    }

    @Override
    public Set<Rule> findByIdList(List<Integer> listId) {
        Set<Rule> ruleSet = new HashSet<>();
        for (Integer integer : listId) {
            ruleSet.add(findById(integer).get());
        }
        return ruleSet;
    }

    @Override
    public Collection<Rule> findAll() {
        return accidentRules.values();
    }
}
