package com.gearborn.motors.gearbornMotors.infrastructure.repositories;

import com.gearborn.motors.gearbornMotors.domain.entities.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VehiculoRepository extends JpaRepository<VehiculoEntity, Integer> {

    Optional<VehiculoEntity> findByMatricula(String matricula);

    @Query("SELECT DISTINCT v.marca FROM VehiculoEntity v")
    Optional<List<String>> findMarcas();
}
