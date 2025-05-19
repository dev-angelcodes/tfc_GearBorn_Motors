package com.gearborn.motors.gearbornMotors.infrastructure.repositories;

import com.gearborn.motors.gearbornMotors.domain.entities.GastoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GastoRepository extends JpaRepository<GastoEntity, Integer> {

    List<GastoEntity> findByVehiculoMatricula(String matricula);

}
