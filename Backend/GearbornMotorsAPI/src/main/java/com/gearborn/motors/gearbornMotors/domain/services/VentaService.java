package com.gearborn.motors.gearbornMotors.domain.services;

import com.gearborn.motors.gearbornMotors.application.dtos.Venta.VentaDto;
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

import java.util.ArrayList;
import java.util.List;


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
        ClienteEntity cliente = clienteRepository.findByEmail(dto.getEmailCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con email: " + dto.getEmailCliente()));

        EmpleadoEntity empleado = empleadoRepository.findByEmail(dto.getEmailEmpleado())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con email: " + dto.getEmailEmpleado()));

        VehiculoEntity vehiculo = vehiculoRepository.findByMatricula(dto.getMatriculaVehiculo())
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con matrícula: " + dto.getMatriculaVehiculo()));
        cambiarEstadoVehiculo(vehiculo);

        VentaEntity venta = VentaMapper.ventaRequestDtoToEntity(dto, cliente, empleado, vehiculo);
        ventaRepository.save(venta);
    }

    public List<VentaDto> getAll(){
        List<VentaEntity> ventasEntity = ventaRepository.findAll();

        List<VentaDto> ventasDto = new ArrayList<>();

        for(VentaEntity venta : ventasEntity) {
            VentaDto dto = VentaMapper.toDto(venta);
            ventasDto.add(dto);
        }

        return ventasDto;
    }


    private void cambiarEstadoVehiculo(VehiculoEntity vehiculo) {
        if ("Vendido".equalsIgnoreCase(vehiculo.getEstado())) {
            throw new IllegalStateException("El vehículo ya fue vendido y no puede revenderse.");
        }
        vehiculo.setEstado("Vendido");
    }
}
