package com.gearborn.motors.gearbornMotors.domain.services;

import com.gearborn.motors.gearbornMotors.application.dtos.Vehiculo.VehiculoDto;
import com.gearborn.motors.gearbornMotors.application.mappers.VehiculoMapper;
import com.gearborn.motors.gearbornMotors.domain.entities.VehiculoEntity;
import com.gearborn.motors.gearbornMotors.infrastructure.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService {
    private final VehiculoRepository vehiculoRepository;

    @Autowired
    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }



    //Consulta para saber el numero total de vehiculos
    public int getTotalVehiculos() {
        return this.vehiculoRepository.findAll().size();
    }



    //Consulta para obtener una lista de todos los vehiculos
    public List<VehiculoDto> getAll(){
        //Obtenemos el listado de vehiculos de la base de datos
        List<VehiculoEntity> vehiculosE = vehiculoRepository.findAll();

        //Creamos un listado de vehiculos DTO, que usaremos apra guardar los vehiculos ya convertidos en DTO
        List<VehiculoDto> vehiculosDTO = new ArrayList<>();

        for(VehiculoEntity vehiculo : vehiculosE){
            VehiculoDto dto = VehiculoMapper.toDto(vehiculo);
            vehiculosDTO.add(dto);
        }

        return vehiculosDTO;    //Devolvemos el listado de vehiculos DTO
    }
}
