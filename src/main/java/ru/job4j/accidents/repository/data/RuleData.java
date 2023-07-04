package ru.job4j.accidents.repository.data;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Primary
@Repository
public interface RuleData extends CrudRepository<Rule, Integer> {

    @Query("SELECT DISTINCT r FROM Rule r ORDER BY r.id")
    List<Rule> findAll();

    default Set<Rule> findByIdList(List<Integer> listId) {
        Set<Rule> ruleSet = new HashSet<>();
        for (Integer integer : listId) {
            ruleSet.add(findById(integer).get());
        }
        return ruleSet;
    }
}
