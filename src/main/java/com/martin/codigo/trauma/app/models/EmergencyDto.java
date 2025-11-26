package com.martin.codigo.trauma.app.models;

import java.time.LocalDateTime;

public class EmergencyDto {

    private Long id;
    private String description;
    private Integer victims;
    private String severity;
    private Integer[] medics;
    private String[] medicsNames;
    private LocalDateTime updateAt;
    private LocalDateTime finishedAt;
    private String comments;

    // PARA INSERTAR EMERGENCIAS
    public EmergencyDto(String description, Integer victims, String severity, Integer[] medics) {
        this.description = description;
        this.victims = victims;
        this.severity = severity;
        this.medics = medics;
    }

    // CONSTRUCTOR PARA MOSTRAR EMERGENCIAS Y ACTUALIZARLAS
    public EmergencyDto(Long id, String description, Integer victims, String severity, String[] medicsNames,
            LocalDateTime updateAt, LocalDateTime finishedAt, String comments) {

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

    public String[] getMedicsNames() {
        return medicsNames;
    }

    public void setMedicsNames(String[] medicsNames) {
        this.medicsNames = medicsNames;
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

}
