package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MemoryAccidentRepository implements AccidentMem {

    private final AtomicInteger id = new AtomicInteger();

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public MemoryAccidentRepository() {

        save(new Accident(0, "Нарушение ПДД", new AccidentType(), Set.of(new Rule()),
                "Проезд на красный свет", "Екатеринбург,проспект Ленина 10"));
        save(new Accident(0, "ДТП двух автомобилей", new AccidentType(), Set.of(new Rule()),
                "Столкновение двух автомобилей", "Екатеринбург, улица Малышева 14"));
        save(new Accident(0, "Происшествие на дороге", new AccidentType(), Set.of(new Rule()),
                "Открытый люк", "Екатеринбург, улица Московская 123"));
    }

    @Override
    public Optional<Accident> save(Accident accident) {
        accident.setId(id.incrementAndGet());
        accidents.put(accident.getId(), accident);
        return Optional.of(accident);
    }

    @Override
    public boolean deleteById(int id) {
        return accidents.remove(id) != null;
    }

    @Override
    public boolean update(Accident accident) {
        return accidents.computeIfPresent(
                accident.getId(), (id, oldAccident) -> new Accident(
                        id, accident.getName(), accident.getType(), accident.getRules(), accident.getText(),
                        accident.getAddress())) != null;
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    @Override
    public Collection<Accident> findAll() {
        return accidents.values();
    }
}
