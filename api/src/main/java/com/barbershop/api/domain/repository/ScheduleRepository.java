package com.barbershop.api.domain.repository;

import com.barbershop.api.domain.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByStartAtGreaterThanEqualAndEndAtLessThanEqualOrderByStartAtAscEndAtAsc(
            final OffsetDateTime startAt,
            final OffsetDateTime endAt
    );

    boolean existsByStartAtAndEndAt(
            final OffsetDateTime startAt,
            final OffsetDateTime endAt
    );
}
