package com.martin.codigo.trauma.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.martin.codigo.trauma.app.entities.Emergency;

public interface EmergencyRepository extends CrudRepository<Emergency, Long> {

}
