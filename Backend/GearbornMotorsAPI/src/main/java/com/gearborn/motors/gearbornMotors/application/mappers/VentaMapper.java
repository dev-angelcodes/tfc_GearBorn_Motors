package com.gearborn.motors.gearbornMotors.application.mappers;

import com.gearborn.motors.gearbornMotors.application.dtos.Venta.VentaRequestDto;
import com.gearborn.motors.gearbornMotors.domain.entities.ClienteEntity;
import com.gearborn.motors.gearbornMotors.domain.entities.EmpleadoEntity;
import com.gearborn.motors.gearbornMotors.domain.entities.VehiculoEntity;
import com.gearborn.motors.gearbornMotors.domain.entities.VentaEntity;

public class VentaMapper {

   public static VentaEntity ventaRequestDtoToEntity(VentaRequestDto ventaRequestDto,
                                                     ClienteEntity cliente,
                                                     EmpleadoEntity empleado,
                                                     VehiculoEntity vehiculo) {
       VentaEntity venta = new VentaEntity();
       venta.setFecha(ventaRequestDto.getFecha());
       venta.setImporte(ventaRequestDto.getImporte());
       venta.setGarantia(ventaRequestDto.getFecha().plusYears(2));
       venta.setCliente(cliente);
       venta.setEmpleado(empleado);
       venta.setVehiculo(vehiculo);
       return venta;
   }
}
