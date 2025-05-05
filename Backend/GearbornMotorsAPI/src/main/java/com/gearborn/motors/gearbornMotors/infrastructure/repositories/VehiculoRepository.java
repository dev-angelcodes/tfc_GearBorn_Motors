package com.gearborn.motors.gearbornMotors.infrastructure.repositories;

import com.gearborn.motors.gearbornMotors.domain.entities.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VehiculoRepository extends JpaRepository<VehiculoEntity, Integer> {

    Optional<VehiculoEntity> findByMatricula(String matricula);

    @Query("SELECT DISTINCT v.marca FROM VehiculoEntity v  WHERE v.estado = 'Disponible'")
    List<String> findMarcasDisponibles();

    @Query("SELECT DISTINCT v.modelo FROM VehiculoEntity v WHERE v.marca = ?1 AND v.estado = 'Disponible'")
    List<String> findModelosDisponiblesPorMarca(String marca);

    @Query("SELECT v FROM VehiculoEntity v WHERE v.estado = 'Disponible' AND v.marca = :marca " +
            "AND (:modelo IS NULL OR v.modelo = :modelo)")
    List<VehiculoEntity> findVehiculosFiltrados(@Param("marca") String marca,@Param("modelo") String modelo);
}
