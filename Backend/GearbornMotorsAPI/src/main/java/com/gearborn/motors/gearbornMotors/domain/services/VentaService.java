package com.gearborn.motors.gearbornMotors.domain.services;

import com.gearborn.motors.gearbornMotors.application.dtos.Venta.VentaRequestDto;
import com.gearborn.motors.gearbornMotors.application.mappers.VentaMapper;
import com.gearborn.motors.gearbornMotors.domain.entities.ClienteEntity;
import com.gearborn.motors.gearbornMotors.domain.entities.EmpleadoEntity;
import com.gearborn.motors.gearbornMotors.domain.entities.VehiculoEntity;
import com.gearborn.motors.gearbornMotors.domain.entities.VentaEntity;
import com.gearborn.motors.gearbornMotors.infrastructure.repositories.ClienteRepository;
import com.gearborn.motors.gearbornMotors.infrastructure.repositories.EmpleadoRepository;
import com.gearborn.motors.gearbornMotors.infrastructure.repositories.VehiculoRepository;
import com.gearborn.motors.gearbornMotors.infrastructure.repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VentaService {
    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;
    private final VehiculoRepository vehiculoRepository;
    private final EmpleadoRepository empleadoRepository;
    @Autowired
    public VentaService(VentaRepository ventaRepository, ClienteRepository clienteRepository, VehiculoRepository vehiculoRepository, EmpleadoRepository empleadoRepository) {
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.empleadoRepository = empleadoRepository;
    }


    public void save(VentaRequestDto dto) {
        ClienteEntity cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        EmpleadoEntity empleado = empleadoRepository.findById(dto.getIdEmpleado())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        VehiculoEntity vehiculo = vehiculoRepository.findById(dto.getIdVehiculo())
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        VentaEntity venta = VentaMapper.ventaRequestDtoToEntity(dto, cliente, empleado, vehiculo);
        ventaRepository.save(venta);
    }
}
