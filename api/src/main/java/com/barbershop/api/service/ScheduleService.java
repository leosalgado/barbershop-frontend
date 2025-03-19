package com.barbershop.api.service;

import com.barbershop.api.domain.model.Schedule;

public interface ScheduleService {

    Schedule save(final Schedule entity);

    void delete(final long id);
}
