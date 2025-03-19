package com.barbershop.api.service.query.impl;

import com.barbershop.api.domain.model.Client;
import com.barbershop.api.domain.repository.ClientRepository;
import com.barbershop.api.exception.NotFoundException;
import com.barbershop.api.exception.PhoneInUseException;
import com.barbershop.api.service.ClientService;
import com.barbershop.api.service.query.ClientQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ClientQueryServiceImpl implements ClientQueryService {

    private final ClientRepository repository;

    @Override
    public Client findById(long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("O cliente de id " + id + " nao foi encontrado."));
    }

    @Override
    public List<Client> list() {
        return repository.findAll();
    }

    @Override
    public void verifyPhone(String phone) {
        if(repository.existsByPhone(phone)){
            var message = "O telefone " + phone + " ja esta em uso";
            throw new PhoneInUseException(message);
        }
    }

    @Override
    public void verifyPhone(long id, String phone) {
        var optional = repository.findByPhone(phone);
        if(optional.isPresent() && !Objects.equals(optional.get().getPhone(), phone)){
            var message = "O telefone " + phone + " ja esta em uso";
            throw new PhoneInUseException(message);
        }
    }

    @Override
    public void verifyEmail(String email) {
        if(repository.existsByEmail(email)){
            var message = "O email " + email + " ja esta em uso";
            throw new PhoneInUseException(message);
        }
    }

    @Override
    public void verifyEmail(long id, String email) {
        var optional = repository.findByEmail(email);
        if(optional.isPresent() && !Objects.equals(optional.get().getPhone(), email)){
            var message = "O email " + email + " ja esta em uso";
            throw new PhoneInUseException(message);
        }
    }
}
