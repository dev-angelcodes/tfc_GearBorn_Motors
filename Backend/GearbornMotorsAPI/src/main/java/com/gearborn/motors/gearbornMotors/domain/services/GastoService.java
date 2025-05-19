package com.gearborn.motors.gearbornMotors.domain.services;

import com.gearborn.motors.gearbornMotors.application.dtos.Gastos.CompraGastoRequestDto;
import com.gearborn.motors.gearbornMotors.application.dtos.Gastos.GastoDto;
import com.gearborn.motors.gearbornMotors.application.mappers.GastoMapper;
import com.gearborn.motors.gearbornMotors.domain.entities.GastoEntity;
import com.gearborn.motors.gearbornMotors.infrastructure.repositories.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GastoService {
    private final GastoRepository gastoRepository;

    @Autowired
    public GastoService(GastoRepository gastoRepository) {
        this.gastoRepository = gastoRepository;
    }


    public void save(CompraGastoRequestDto gasto) {
        GastoEntity gastoEntity = GastoMapper.CompraGastoRequestDtoToEntity(gasto);
        gastoRepository.save(gastoEntity);
    }

    public List<GastoDto> getAll() {
        List<GastoEntity> gastosEntity = gastoRepository.findAll();

        List<GastoDto> gastosDto = new ArrayList<>();

        for(GastoEntity gasto : gastosEntity) {
            GastoDto dto = GastoMapper.toDto(gasto);
            gastosDto.add(dto);
        }

        return  gastosDto;
    }
}
