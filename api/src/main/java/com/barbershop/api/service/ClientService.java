package com.barbershop.api.service;

import com.barbershop.api.domain.model.Client;

public interface ClientService {

    Client save(final Client entity);

    Client update(final Client entity);

    void delete(final long id);
}
