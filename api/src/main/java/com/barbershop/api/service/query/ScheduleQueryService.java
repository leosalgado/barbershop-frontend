package com.barbershop.api.service.query;

import com.barbershop.api.domain.model.Schedule;

import java.time.OffsetDateTime;
import java.util.List;

public interface ScheduleQueryService {

    Schedule findById(final long id);

    List<Schedule> findInMonth(final OffsetDateTime startAt, final OffsetDateTime endAt);

    void verifyIfScheduleExists(final OffsetDateTime startAt, final OffsetDateTime endAt);
}
