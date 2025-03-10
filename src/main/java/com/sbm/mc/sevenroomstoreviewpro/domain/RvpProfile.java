package com.sbm.mc.sevenroomstoreviewpro.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RvpProfile.
 */
@Entity
@Table(name = "rvp_profile")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RvpProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "pms_id")
    private String pmsId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "language")
    private String language;

    @Column(name = "checkin")
    private LocalDateTime checkin;

    @Column(name = "checkout")
    private LocalDate checkout;

    @Column(name = "email")
    private String email;

    private String email2;

    private String Civilite;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public RvpProfile id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPmsId() {
        return this.pmsId;
    }

    public RvpProfile pmsId(String pmsId) {
        this.setPmsId(pmsId);
        return this;
    }

    public void setPmsId(String pmsId) {
        this.pmsId = pmsId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public RvpProfile firstName(String firstName) {
        this.setFirstName(firstName);
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public RvpProfile lastName(String lastName) {
        this.setLastName(lastName);
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLanguage() {
        return this.language;
    }

    public RvpProfile language(String language) {
        this.setLanguage(language);
        return this;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LocalDateTime getCheckin() {
        return this.checkin;
    }

    public RvpProfile checkin(LocalDateTime checkin) {
        this.setCheckin(checkin);
        return this;
    }

    public void setCheckin(LocalDateTime checkin) {
        this.checkin = checkin;
    }

    public LocalDate getCheckout() {
        return this.checkout;
    }

    public RvpProfile checkout(LocalDate checkout) {
        this.setCheckout(checkout);
        return this;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    public String getEmail() {
        return this.email;
    }

    public RvpProfile email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getCivilite() {
        return Civilite;
    }

    public void setCivilite(String civilite) {
        Civilite = civilite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RvpProfile)) {
            return false;
        }
        return getId() != null && getId().equals(((RvpProfile) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return (
            "RvpProfile{" +
            "id=" +
            id +
            ", pmsId='" +
            pmsId +
            '\'' +
            ", firstName='" +
            firstName +
            '\'' +
            ", lastName='" +
            lastName +
            '\'' +
            ", language='" +
            language +
            '\'' +
            ", checkin=" +
            checkin +
            ", checkout=" +
            checkout +
            ", email='" +
            email +
            '\'' +
            ", email2='" +
            email2 +
            '\'' +
            ", Civilite='" +
            Civilite +
            '\'' +
            '}'
        );
    }
}
