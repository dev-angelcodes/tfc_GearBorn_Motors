package com.gearborn.motors.gearbornMotors.domain.services;

import com.gearborn.motors.gearbornMotors.application.dtos.Gastos.CompraGastoRequestDto;
import com.gearborn.motors.gearbornMotors.application.dtos.Gastos.GastoDto;
import com.gearborn.motors.gearbornMotors.application.dtos.Gastos.GastoFrontDto;
import com.gearborn.motors.gearbornMotors.application.dtos.Gastos.GastoVehiculoRequestDto;
import com.gearborn.motors.gearbornMotors.application.mappers.GastoMapper;
import com.gearborn.motors.gearbornMotors.domain.entities.EmpleadoEntity;
import com.gearborn.motors.gearbornMotors.domain.entities.GastoEntity;
import com.gearborn.motors.gearbornMotors.domain.entities.VehiculoEntity;
import com.gearborn.motors.gearbornMotors.infrastructure.repositories.EmpleadoRepository;
import com.gearborn.motors.gearbornMotors.infrastructure.repositories.GastoRepository;
import com.gearborn.motors.gearbornMotors.infrastructure.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GastoService {
    private final GastoRepository gastoRepository;
    private final EmpleadoRepository empleadoRepository;
    private final VehiculoRepository vehiculoRepository;

    @Autowired
    public GastoService(GastoRepository gastoRepository, EmpleadoRepository empleadoRepository, VehiculoRepository vehiculoRepository) {
        this.gastoRepository = gastoRepository;
        this.empleadoRepository = empleadoRepository;
        this.vehiculoRepository = vehiculoRepository;
    }


    public void save(CompraGastoRequestDto gasto) {
        GastoEntity gastoEntity = GastoMapper.CompraGastoRequestDtoToEntity(gasto);
        gastoRepository.save(gastoEntity);
    }

    public List<GastoFrontDto> getAll() {
        List<GastoEntity> gastosEntity = gastoRepository.findAll();

        List<GastoFrontDto> gastosDto = new ArrayList<>();

        for(GastoEntity gasto : gastosEntity) {
            GastoFrontDto dto = GastoMapper.gastoFrontDto(gasto);
            gastosDto.add(dto);
        }

        return  gastosDto;
    }

    public List<GastoDto> getAllByMatricula(String matricula){
        List<GastoEntity> gastosEntity = gastoRepository.findByVehiculoMatricula(matricula);

        List<GastoDto> gastosDto = new ArrayList<>();

        for(GastoEntity gasto : gastosEntity) {
            GastoDto dto = GastoMapper.toDto(gasto);
            gastosDto.add(dto);
        }
        return gastosDto;
    }

    public void saveByMatricula(GastoVehiculoRequestDto gasto) {
        EmpleadoEntity empleado = empleadoRepository.findById(gasto.getIdEmpleado())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + gasto.getIdEmpleado()));

        VehiculoEntity vehiculo = vehiculoRepository.findByMatricula(gasto.getMatricula())
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con matrícula: " + gasto.getMatricula()));

        GastoEntity gastoEntity = GastoMapper.gastoVehiculoRequestDtoToEntity(gasto);

        gastoEntity.setEmpleado(empleado);
        gastoEntity.setVehiculo(vehiculo);

        gastoRepository.save(gastoEntity);
    }

}
