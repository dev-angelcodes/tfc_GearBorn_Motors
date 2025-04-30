package com.gearborn.motors.gearbornMotors.application.mappers;

import com.gearborn.motors.gearbornMotors.application.dtos.Gastos.CompraGastoRequestDto;
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

        //realacion con vehiculo
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setId(gasto.getIdVehiculo());
        gastoEntity.setVehiculo(vehiculo);

        return gastoEntity;
    }
}
