package com.gearborn.motors.gearbornMotors.infrastructure.repositories;

import com.gearborn.motors.gearbornMotors.domain.entities.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Integer> {

    Optional<EmpleadoEntity> findById(Integer integer);
}
