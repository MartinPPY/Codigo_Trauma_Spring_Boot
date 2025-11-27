package com.martin.codigo.trauma.app.models;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<String> medicNames;
    private String status;

    public EmergencyDto() {

    }

    // PARA INSERTAR EMERGENCIAS
    public EmergencyDto(String description, Integer victims, String severity, Integer[] medics) {
        this.description = description;
        this.victims = victims;
        this.severity = severity;
        this.medics = medics;
    }

    // CONSTRUCTOR PARA MOSTRAR EMERGENCIAS
    public EmergencyDto(Long id,String description, Integer victims, String severity, String stauts, String comments,
            LocalDateTime createAt, LocalDateTime updatedAt, LocalDateTime finishedAt, List<String> medics) {
        this.id = id;
        this.description = description;
        this.victims = victims;
        this.severity = severity;
        this.status = stauts;
        this.comments = comments;
        this.creation = createAt;
        this.updateAt = updatedAt;
        this.finishedAt = finishedAt;
        this.medicNames = medics;
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

    public List<String> getMedicNames() {
        return medicNames;
    }

    public void setMedicNames(List<String> medicNames) {
        this.medicNames = medicNames;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
