package ru.job4j.accidents.repository.memory;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MemoryAccidentTypeRepository implements AccidentTypeRepository {

    private final AtomicInteger id = new AtomicInteger();

    private final Map<Integer, AccidentType> accidentTypes = new ConcurrentHashMap<>();

    public MemoryAccidentTypeRepository() {

        save(new AccidentType(0, "Один автомобиль"));
        save(new AccidentType(0, "Два автомобиля"));
        save(new AccidentType(0, "Автомобиль и человек"));
        save(new AccidentType(0, "Автомобиль и велосипед"));
    }

    @Override
    public AccidentType save(AccidentType type) {
        type.setId(id.incrementAndGet());
        accidentTypes.put(type.getId(), type);
        return type;
    }

    @Override
    public boolean deleteById(int id) {
        return accidentTypes.remove(id) != null;
    }

    @Override
    public boolean update(AccidentType type) {
        return accidentTypes.computeIfPresent(
                type.getId(), (id, oldAccidentType) -> new AccidentType(id, type.getName())) != null;
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(accidentTypes.get(id));
    }

    @Override
    public Collection<AccidentType> findAll() {
        return accidentTypes.values();
    }
}
