package com.martin.codigo.trauma.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.martin.codigo.trauma.app.entities.UserEmergency;
import com.martin.codigo.trauma.app.models.EmergencyDto;

public interface UserEmergencyRepository extends CrudRepository<UserEmergency, Long> {

    @Query("SELECT e FROM UserEmergency e")
    List<EmergencyDto> findAllEmergencies();

}
