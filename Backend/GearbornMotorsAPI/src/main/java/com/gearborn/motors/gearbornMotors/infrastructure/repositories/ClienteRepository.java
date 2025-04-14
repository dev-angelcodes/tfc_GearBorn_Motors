package com.gearborn.motors.gearbornMotors.infrastructure.repositories;

import com.gearborn.motors.gearbornMotors.domain.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository  extends JpaRepository<ClienteEntity, Integer> {

    //Obtener un cliente por el email que se introduzca
    Optional<ClienteEntity> findByEmail(String email);
}
