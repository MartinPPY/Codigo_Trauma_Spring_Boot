package com.martin.codigo.trauma.app.models;

import java.time.LocalDateTime;

public class EmergencyDto {

    private Long id;
    private String description;
    private Integer victims;
    private String severity;
    private Integer[] medics;
    private LocalDateTime creation;
    private LocalDateTime updateAt;
    private LocalDateTime finishedAt;
    private String comments;

    public EmergencyDto(){
        
    }

    // PARA INSERTAR EMERGENCIAS
    public EmergencyDto(String description, Integer victims, String severity, Integer[] medics) {
        this.description = description;
        this.victims = victims;
        this.severity = severity;
        this.medics = medics;
    }

    // CONSTRUCTOR PARA MOSTRAR EMERGENCIAS Y ACTUALIZARLAS
    public EmergencyDto(Long id, String description, Integer victims, String severity,
            LocalDateTime updateAt, LocalDateTime finishedAt, String comments, LocalDateTime creation) {
        this.id = id;
        this.description = description;
        this.victims = victims;
        this.severity = severity;
        this.updateAt = updateAt;
        this.finishedAt = finishedAt;
        this.comments = comments;
        this.creation = creation;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVictims() {
        return victims;
    }

    public void setVictims(Integer victims) {
        this.victims = victims;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public Integer[] getMedics() {
        return medics;
    }

    public void setMedics(Integer[] medics) {
        this.medics = medics;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDateTime getCreation() {
        return creation;
    }

    public void setCreation(LocalDateTime creation) {
        this.creation = creation;
    }

    

}
