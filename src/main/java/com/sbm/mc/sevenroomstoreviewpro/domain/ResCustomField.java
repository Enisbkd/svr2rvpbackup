package com.sbm.mc.sevenroomstoreviewpro.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ResCustomField.
 */
@Entity
@Table(name = "res_custom_field")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ResCustomField implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "system_name")
    private String systemName;

    @Column(name = "display_order")
    private Integer displayOrder;

    @Column(name = "name")
    private String name;

    @Column(name = "jhi_value")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "resTags", "resPosTickets", "resCustomFields", "resTables" }, allowSetters = true)
    private Reservation reservation;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ResCustomField id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystemName() {
        return this.systemName;
    }

    public ResCustomField systemName(String systemName) {
        this.setSystemName(systemName);
        return this;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public Integer getDisplayOrder() {
        return this.displayOrder;
    }

    public ResCustomField displayOrder(Integer displayOrder) {
        this.setDisplayOrder(displayOrder);
        return this;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getName() {
        return this.name;
    }

    public ResCustomField name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public ResCustomField value(String value) {
        this.setValue(value);
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Reservation getReservation() {
        return this.reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public ResCustomField reservation(Reservation reservation) {
        this.setReservation(reservation);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResCustomField)) {
            return false;
        }
        return getId() != null && getId().equals(((ResCustomField) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ResCustomField{" +
            "id=" + getId() +
            ", systemName='" + getSystemName() + "'" +
            ", displayOrder=" + getDisplayOrder() +
            ", name='" + getName() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }
}
