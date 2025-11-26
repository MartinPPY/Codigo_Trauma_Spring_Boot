package com.martin.codigo.trauma.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.martin.codigo.trauma.app.entities.User;
import com.martin.codigo.trauma.app.models.UserDto;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("SELECT u from User u WHERE u.email = ?1")
    Optional<User> findEmail(String email);

    // Query para traer medicos disponibles y no disponibles
    @Query("SELECT new com.martin.codigo.trauma.app.models.UserDto(u.id,u.name,u.lastname,u.phone,u.email,u.availability) FROM User u WHERE u.role.id = 2 ORDER BY u.name,u.lastname")
    List<UserDto> findAllMedics();

    // Query para traer solo medicos y filtrar por disponibilidad
    @Query("SELECT new com.martin.codigo.trauma.app.models.UserDto(u.id,u.name,u.lastname,u.phone,u.email,u.availability) FROM User u WHERE u.role.id = 2 AND u.availability = ?1 ORDER BY u.name,u.lastname")
    List<UserDto> findAllAvailabilityMedics(Boolean availability);

}
