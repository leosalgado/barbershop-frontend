package com.barbershop.api.mapper;

import com.barbershop.api.controller.request.SaveClientRequest;
import com.barbershop.api.controller.request.UpdateClientRequest;
import com.barbershop.api.controller.response.ClientDetailResponse;
import com.barbershop.api.controller.response.ListClientResponse;
import com.barbershop.api.controller.response.SaveClientResponse;
import com.barbershop.api.controller.response.UpdateClientResponse;
import com.barbershop.api.domain.model.Client;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ClientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    Client toEntity(final SaveClientRequest request);

    SaveClientResponse toSaveResponse(final Client entity);

    @Mapping(target = "schedules", ignore = true)
    Client toEntity(final long id, final UpdateClientRequest request);

    UpdateClientResponse toUpdateResponse(final Client entity);

    ClientDetailResponse toDetailResponse(final Client entity);

    List<ListClientResponse> toListResponse(final List<Client> entities);
}
