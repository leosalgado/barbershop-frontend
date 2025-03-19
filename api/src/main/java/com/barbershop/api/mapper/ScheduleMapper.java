package com.barbershop.api.mapper;

import com.barbershop.api.controller.request.SaveScheduleRequest;
import com.barbershop.api.controller.response.ClientScheduleAppointmentResponse;
import com.barbershop.api.controller.response.SaveScheduleResponse;
import com.barbershop.api.controller.response.ScheduleAppointmentMonthResponse;
import com.barbershop.api.domain.model.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ScheduleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client.id", source = "clientId")
    Schedule toEntity(final SaveScheduleRequest request);

    @Mapping(target = "clientId", source = "client.id")
    SaveScheduleResponse toSaveResponse(final Schedule entity);

    @Mapping(target = "scheduledAppointments", expression = "java(toClientMonthResponse(entities))")
    ScheduleAppointmentMonthResponse toMonthResponse(final int year, final int month, final List<Schedule> entities);

    List<ClientScheduleAppointmentResponse> toClientMonthResponse(final List<Schedule> entities);

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "clientName", source = "client.name")
    @Mapping(target = "day", expression = "java(entity.getStartAt().getDayOfMonth())")
    ClientScheduleAppointmentResponse toClientMonthResponse(final Schedule entity);
}
