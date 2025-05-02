package com.gearborn.motors.gearbornMotors.domain.services;

import com.gearborn.motors.gearbornMotors.application.dtos.Vehiculo.CompraVehiculoRequestDto;
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
        List<VehiculoEntity> vehiculosEntity = vehiculoRepository.findAll();

        List<VehiculoDto> vehiculosDTO = new ArrayList<>();

        for(VehiculoEntity vehiculo : vehiculosEntity){
            if(vehiculo.getEstado().equals("Disponible")){ //Filtramos los vehiculos que estan disponibles
                VehiculoDto dto = VehiculoMapper.toDto(vehiculo);
                vehiculosDTO.add(dto);
            }
        }
        return vehiculosDTO;    //Devolvemos el listado de vehiculos DTO
    }

    //Consulta para guaradar un vehiculo
    public void save (CompraVehiculoRequestDto compraVehiculoRequestDto) {
        VehiculoEntity vehiculo = VehiculoMapper.registerRequestToEntity(compraVehiculoRequestDto);
        if(vehiculo.getMatricula() != null && vehiculoRepository.findByMatricula(vehiculo.getMatricula()).isPresent()) {
            throw new RuntimeException("Ya existe un vehículo registrado con esa matricula");
        }else{
            vehiculoRepository.save(vehiculo);
        }
    }

    public Optional<Integer> getIdByMatricula(String matricula) {
        return vehiculoRepository.findByMatricula(matricula).map(VehiculoEntity::getId);
    }
}