package com.barbershop.api.controller;

import com.barbershop.api.controller.request.SaveScheduleRequest;
import com.barbershop.api.controller.response.SaveScheduleResponse;
import com.barbershop.api.controller.response.ScheduleAppointmentMonthResponse;
import com.barbershop.api.mapper.ScheduleMapper;
import com.barbershop.api.service.ScheduleService;
import com.barbershop.api.service.query.ScheduleQueryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.time.ZoneOffset;

@RestController
@RequestMapping("schedules")
@AllArgsConstructor
public class ScheduleController {

    private final ScheduleService service;
    private final ScheduleQueryService queryService;
    private final ScheduleMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    SaveScheduleResponse save(@RequestBody @Valid SaveScheduleRequest request){
        var entity = mapper.toEntity(request);
        service.save(entity);
        return mapper.toSaveResponse(entity);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable final long id){
        service.delete(id);
    }

    @GetMapping("{year}/{month}")
    ScheduleAppointmentMonthResponse listMonth(@PathVariable final int year, @PathVariable final int month){
        var yearMonth =  YearMonth.of(year, month);
        var startAt = yearMonth.atDay(1)
                .atTime(0, 0, 0, 0)
                .atOffset(ZoneOffset.UTC);
        var endAt = yearMonth.atEndOfMonth()
                .atTime(23 , 59, 59, 999_999_999)
                .atOffset(ZoneOffset.UTC);
        var entities = queryService.findInMonth(startAt, endAt);
        return mapper.toMonthResponse(year,month, entities);
    }


}
