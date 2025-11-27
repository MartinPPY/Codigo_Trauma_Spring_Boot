package com.martin.codigo.trauma.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.martin.codigo.trauma.app.entities.Emergency;

public interface EmergencyRepository extends CrudRepository<Emergency, Long> {


    @Query("SELECT e FROM Emergency e ORDER BY e.createdAt ASC")
    List<Emergency> findAllByOrderByCreation();

}
