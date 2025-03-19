package com.barbershop.api.service.query.impl;

import com.barbershop.api.domain.model.Schedule;
import com.barbershop.api.domain.repository.ScheduleRepository;
import com.barbershop.api.exception.NotFoundException;
import com.barbershop.api.exception.ScheduleInUseException;
import com.barbershop.api.service.query.ScheduleQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class ScheduleQueryServiceImpl implements ScheduleQueryService {

    private final ScheduleRepository repository;

    @Override
    public Schedule findById(long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Agendamento nao encontrado")
        );
    }

    @Override
    public List<Schedule> findInMonth(OffsetDateTime startAt, OffsetDateTime endAt) {
        return repository.findByStartAtGreaterThanEqualAndEndAtLessThenEqualOrderByStartAtEndAt(startAt, endAt);
    }

    @Override
    public void verifyIfScheduleExists(OffsetDateTime startAt, OffsetDateTime endAt) {
        if (repository.existsStartAtAndEndAt(startAt, endAt)){
            var message = "Ja existe um cliente agendado no horario marcado";
            throw new ScheduleInUseException(message);
        }
    }
}
