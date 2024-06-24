package com.sbm.mc.sevenroomstoreviewpro.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * Base abstract class for entities which will hold definitions for created,
 * last modified, created by,
 * last modified by attributes.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate" }, allowGetters = true)
public abstract class AbstractAuditingEntitySBM<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public abstract T getId();

    @Column(name = "tech_lineage", nullable = false, updatable = false)
    private String tech_lineage = "KAFKA_API_7R";

    @CreationTimestamp
    @Column(name = "tech_created_date", updatable = false)
    private Timestamp techCreatedDate;

    @UpdateTimestamp
    @Column(name = "tech_updated_date", length = 50)
    private Timestamp techUpdatedDate;

    @Column(name = "tech_mapping")
    private Instant techMapping;

    @Column(name = "tech_comment")
    private String techComment;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getTech_lineage() {
        return tech_lineage;
    }

    public void setTech_lineage(String tech_lineage) {
        this.tech_lineage = tech_lineage;
    }

    public Timestamp getTechCreatedDate() {
        return techCreatedDate;
    }

    public void setTechCreatedDate(Timestamp techCreatedDate) {
        this.techCreatedDate = techCreatedDate;
    }

    public Timestamp getTechUpdatedDate() {
        return techUpdatedDate;
    }

    public void setTechUpdatedDate(Timestamp techUpdatedDate) {
        this.techUpdatedDate = techUpdatedDate;
    }

    public Instant getTechMapping() {
        return techMapping;
    }

    public void setTechMapping(Instant techMapping) {
        this.techMapping = techMapping;
    }

    public String getTechComment() {
        return techComment;
    }

    public void setTechComment(String techComment) {
        this.techComment = techComment;
    }

    @Override
    public String toString() {
        return (
            "AbstractAuditingEntitySBM [tech_lineage=" +
            tech_lineage +
            ", techCreatedDate=" +
            techCreatedDate +
            ", techUpdatedDate=" +
            techUpdatedDate +
            ", techMapping=" +
            techMapping +
            ", techComment=" +
            techComment +
            "]"
        );
    }
}
