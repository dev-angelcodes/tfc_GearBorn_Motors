package com.gearborn.motors.gearbornMotors.application.mappers;

import com.gearborn.motors.gearbornMotors.application.dtos.Gastos.CompraGastoRequestDto;
import com.gearborn.motors.gearbornMotors.application.dtos.Gastos.GastoDto;
import com.gearborn.motors.gearbornMotors.application.dtos.Gastos.GastoFrontDto;
import com.gearborn.motors.gearbornMotors.domain.entities.EmpleadoEntity;
import com.gearborn.motors.gearbornMotors.domain.entities.GastoEntity;
import com.gearborn.motors.gearbornMotors.domain.entities.VehiculoEntity;

import java.time.LocalDate;

public class GastoMapper {

    //Mapeamos el objeto CompraGastoRequestDto a GastoEntity
    public static GastoEntity CompraGastoRequestDtoToEntity(CompraGastoRequestDto gasto) {
        GastoEntity gastoEntity = new GastoEntity();
        gastoEntity.setImporte(gasto.getImporte());
        gastoEntity.setFecha(LocalDate.now());
        gastoEntity.setCompra(true);
        gastoEntity.setNombreProv(gasto.getNombreProv());
        gastoEntity.setDescripcion(gasto.getDescripcion());

        //relacion con empleado
        EmpleadoEntity empleado = new EmpleadoEntity();
        empleado.setId(gasto.getIdEmpleado());
        gastoEntity.setEmpleado(empleado);

        //relación con vehiculo
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setId(gasto.getIdVehiculo());
        gastoEntity.setVehiculo(vehiculo);

        return gastoEntity;
    }


    public static GastoFrontDto gastoFrontDto(GastoEntity entity) {
        GastoFrontDto dto = new GastoFrontDto();
        dto.setImporte(entity.getImporte());
        dto.setFecha(entity.getFecha());
        dto.setNombreProv(entity.getNombreProv());
        dto.setVehiculo(entity.getVehiculo().getMarca() + ", "
                + entity.getVehiculo().getModelo() + ", " + entity.getVehiculo().getAnio());
        dto.setDescripcion(entity.getDescripcion());

        return dto;
    }

    public static GastoDto toDto(GastoEntity entity) {
        GastoDto dto = new GastoDto();
        dto.setImporte(entity.getImporte());
        dto.setFecha(entity.getFecha());
        dto.setCompra(entity.isCompra());
        dto.setNombreProv(entity.getNombreProv());
        dto.setDescripcion(entity.getDescripcion());

        return dto;
    }
}
