package ru.job4j.accidents.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "accidents")
public class Accident {

    @EqualsAndHashCode.Include
    private int id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accident_type_id")
    private AccidentType type;

    @ManyToMany
    @JoinTable(
            name = "accident_rules", joinColumns = {
            @JoinColumn(name = "accident_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "rule_id", nullable = false, updatable = false)}
    )
    private Set<Rule> rules = new HashSet<>();

    private String description;

    private String address;

}
