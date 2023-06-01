package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RuleService {

    Rule save(Rule rule);

    boolean deleteById(int id);

    boolean update(Rule rule);

    Optional<Rule> findById(int id);

    Set<Rule> findByIdList(List<Integer> listId);

    Collection<Rule> findAll();
}
