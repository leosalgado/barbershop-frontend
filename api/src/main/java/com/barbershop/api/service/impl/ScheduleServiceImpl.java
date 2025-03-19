package com.barbershop.api.service.impl;

import com.barbershop.api.domain.model.Schedule;
import com.barbershop.api.domain.repository.ScheduleRepository;
import com.barbershop.api.service.ScheduleService;
import com.barbershop.api.service.query.ScheduleQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository repository;
    private final ScheduleQueryService queryService;


    @Override
    public Schedule save(Schedule entity) {
        queryService.verifyIfScheduleExists(entity.getStartAt(), entity.getEndAt());
        return repository.save(entity);
    }

    @Override
    public void delete(long id) {
        queryService.findById(id);
        repository.deleteById(id);
    }
}
