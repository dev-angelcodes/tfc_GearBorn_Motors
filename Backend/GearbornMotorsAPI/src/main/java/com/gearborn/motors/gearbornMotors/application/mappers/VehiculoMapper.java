package com.gearborn.motors.gearbornMotors.application.mappers;

import com.gearborn.motors.gearbornMotors.application.dtos.Vehiculo.VehiculoDto;
import com.gearborn.motors.gearbornMotors.domain.entities.VehiculoEntity;

public class VehiculoMapper {

    //Mapeamos de un VehiculoEntity a un VehiculoDto
    public static VehiculoDto toDto(VehiculoEntity vehiculo){
        VehiculoDto vehiculoDto = new VehiculoDto();
        vehiculoDto.setMatricula(vehiculo.getMatricula());
        vehiculoDto.setTipo(vehiculo.getTipo());
        vehiculoDto.setMarca(vehiculo.getMarca());
        vehiculoDto.setModelo(vehiculo.getModelo());
        vehiculoDto.setAnio(vehiculo.getAnio());
        vehiculoDto.setKm(vehiculo.getKm());
        vehiculoDto.setEstado(vehiculo.getEstado());
        vehiculoDto.setTipoCombustible(vehiculo.getTipoCombustible());
        vehiculoDto.setTipoCambio(vehiculo.getTipoCambio());
        vehiculoDto.setColor(vehiculo.getColor());
        vehiculoDto.setImg(vehiculo.getImg());

        return vehiculoDto;
    }


    //Mapeamos de un VehiculoDto a un VehiculoEntity
    public static VehiculoEntity toEntity(VehiculoDto vehiculo){
        VehiculoEntity vehiculoEntity = new VehiculoEntity();
        vehiculoEntity.setMatricula(vehiculo.getMatricula());
        vehiculoEntity.setTipo(vehiculo.getTipo());
        vehiculoEntity.setMarca(vehiculo.getMarca());
        vehiculoEntity.setModelo(vehiculo.getModelo());
        vehiculoEntity.setAnio(vehiculo.getAnio());
        vehiculoEntity.setKm(vehiculo.getKm());
        vehiculoEntity.setEstado(vehiculo.getEstado());
        vehiculoEntity.setTipoCombustible(vehiculo.getTipoCombustible());
        vehiculoEntity.setTipoCambio(vehiculo.getTipoCambio());
        vehiculoEntity.setColor(vehiculo.getColor());
        vehiculoEntity.setImg(vehiculo.getImg());

        return vehiculoEntity;
    }
}
