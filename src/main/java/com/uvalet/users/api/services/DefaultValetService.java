package com.uvalet.users.api.services;

import com.uvalet.users.api.data.repositories.ValetRepository;
import com.uvalet.users.api.domains.entities.Valet;
import com.uvalet.users.api.domains.valueobjects.ValetProfile;
import com.uvalet.users.api.exceptions.UserNotFoundException;
import com.uvalet.users.api.services.specs.ValetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultValetService implements ValetService {

    private final ValetRepository repository;

    @Override
    public List<ValetProfile> getValets() {
        return repository.findAll().stream()
                .map(Valet::mapToDtoOrDomain)
                .collect(Collectors.toList());
    }

    @Override
    public ValetProfile register(ValetProfile dto) {
        dto.setId(repository.save(dto.mapToDtoOrDomain()).getId());
        return dto;
    }

    @Override
    public ValetProfile getUserById(String id) {
        return repository.findById(id).orElseThrow(UserNotFoundException::new).mapToDtoOrDomain();
    }

    @Override
    public ValetProfile update(ValetProfile dto) {
        repository.save(dto.mapToDtoOrDomain());
        return dto;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

}
