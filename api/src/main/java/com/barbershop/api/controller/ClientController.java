package com.barbershop.api.controller;

import com.barbershop.api.controller.request.SaveClientRequest;
import com.barbershop.api.controller.request.UpdateClientRequest;
import com.barbershop.api.controller.response.ClientDetailResponse;
import com.barbershop.api.controller.response.ListClientResponse;
import com.barbershop.api.controller.response.SaveClientResponse;
import com.barbershop.api.controller.response.UpdateClientResponse;
import com.barbershop.api.mapper.ClientMapper;
import com.barbershop.api.service.ClientService;
import com.barbershop.api.service.query.ClientQueryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService service;
    private final ClientQueryService queryService;
    private final ClientMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    SaveClientResponse save(@RequestBody @Valid final SaveClientRequest request){
        var entity = mapper.toEntity(request);
        service.save(entity);
        return mapper.toSaveResponse(entity);
    }

    @PutMapping("{id}")
    UpdateClientResponse update(@PathVariable final long id, @RequestBody @Valid final UpdateClientRequest request){
        var entity = mapper.toEntity(id, request);
        service.update(entity);
        return mapper.toUpdateResponse(entity);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable final long id){
        service.delete(id);
    }

    @GetMapping("{id}")
    ClientDetailResponse findById(@PathVariable final long id){
        var entity = queryService.findById(id);
        return mapper.toDetailResponse(entity);
    }

    @GetMapping
    List<ListClientResponse> list(){
        var entities = queryService.list();
        return mapper.toListResponse(entities);
    }
}
