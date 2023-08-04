package ru.job4j.accidents.service.data;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Authority;
import ru.job4j.accidents.repository.data.AuthorityRepository;
import ru.job4j.accidents.service.AuthorityService;

@Primary
@Service
@AllArgsConstructor
public class DataAuthorityService implements AuthorityService {

    private final AuthorityRepository repository;

    @Override
    public Authority findByAuthority(String authority) {
        return repository.findByAuthority(authority);
    }
}
