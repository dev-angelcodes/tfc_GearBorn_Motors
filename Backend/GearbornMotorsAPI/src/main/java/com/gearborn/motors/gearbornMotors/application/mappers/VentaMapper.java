package com.gearborn.motors.gearbornMotors.application.mappers;

import com.gearborn.motors.gearbornMotors.application.dtos.Venta.VentaDto;
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

    public static VentaDto toDto(VentaEntity venta) {

       VentaDto ventaDto = new VentaDto();
       ventaDto.setImporte(venta.getImporte());
       ventaDto.setFecha(venta.getFecha());
       ventaDto.setGarantia(venta.getGarantia());
       ventaDto.setEmpleado(venta.getEmpleado().getNombre() + ", " + venta.getEmpleado().getApellidos()
               + " -> " + venta.getEmpleado().getEmail());
       ventaDto.setCliente(venta.getCliente().getNombre() + ", " + venta.getCliente().getApellidos()
               + " -> " + venta.getCliente().getEmail() );
       ventaDto.setDescripcionVehiculo(venta.getVehiculo().getMarca() + " " + venta.getVehiculo().getModelo()
                 + " " + venta.getVehiculo().getAnio());
       return ventaDto;
    }
}
