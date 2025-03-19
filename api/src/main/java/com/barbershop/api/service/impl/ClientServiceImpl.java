package com.barbershop.api.service.impl;

import com.barbershop.api.domain.model.Client;
import com.barbershop.api.domain.repository.ClientRepository;
import com.barbershop.api.service.ClientService;
import com.barbershop.api.service.query.ClientQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;
    private final ClientQueryService queryService;

    @Override
    public Client save(Client entity) {
        queryService.verifyEmail(entity.getEmail());
        queryService.verifyPhone(entity.getPhone());

        return repository.save(entity);
    }

    @Override
    public Client update(Client entity) {
        queryService.verifyEmail(entity.getId(), entity.getEmail());
        queryService.verifyPhone(entity.getId(), entity.getPhone());

        var stored = queryService.findById(entity.getId());
        stored.setName(entity.getName());
        stored.setEmail(entity.getEmail());
        stored.setPhone(entity.getPhone());

        return repository.save(stored);
    }

    @Override
    public void delete(long id) {
        queryService.findById(id);
        repository.deleteById(id);
    }
}
