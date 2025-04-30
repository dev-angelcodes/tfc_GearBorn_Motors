package com.gearborn.motors.gearbornMotors.domain.services;

import com.gearborn.motors.gearbornMotors.application.dtos.Gastos.CompraGastoRequestDto;
import com.gearborn.motors.gearbornMotors.application.mappers.GastoMapper;
import com.gearborn.motors.gearbornMotors.domain.entities.GastoEntity;
import com.gearborn.motors.gearbornMotors.infrastructure.repositories.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
