package com.sbm.mc.sevenroomstoreviewpro.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Venue.
 */
@Entity
@Table(name = "venue")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Venue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "black_logo")
    private String blackLogo;

    @Column(name = "country")
    private String country;

    @Column(name = "cross_street")
    private String crossStreet;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "external_venue_id")
    private String externalVenueId;

    @Column(name = "full_dining_backend")
    private Boolean fullDiningBackend;

    @Column(name = "grid_enabled")
    private Boolean gridEnabled;

    @Column(name = "venue_id")
    private String venueId;

    @Column(name = "internal_name")
    private String internalName;

    @Column(name = "membership_enabled")
    private Boolean membershipEnabled;

    @Column(name = "name")
    private String name;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "policy")
    private String policy;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "primary_color")
    private String primaryColor;

    @Column(name = "secondary_color")
    private String secondaryColor;

    @Column(name = "state")
    private String state;

    @Column(name = "unique_confirmation_prefix")
    private String uniqueConfirmationPrefix;

    @Column(name = "venue_class")
    private String venueClass;

    @Column(name = "venue_group_id")
    private String venueGroupId;

    @Column(name = "venue_group_name")
    private String venueGroupName;

    @Column(name = "venue_url_key")
    private String venueUrlKey;

    @Column(name = "website")
    private String website;

    @Column(name = "white_logo")
    private String whiteLogo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Venue id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return this.address;
    }

    public Venue address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBlackLogo() {
        return this.blackLogo;
    }

    public Venue blackLogo(String blackLogo) {
        this.setBlackLogo(blackLogo);
        return this;
    }

    public void setBlackLogo(String blackLogo) {
        this.blackLogo = blackLogo;
    }

    public String getCountry() {
        return this.country;
    }

    public Venue country(String country) {
        this.setCountry(country);
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCrossStreet() {
        return this.crossStreet;
    }

    public Venue crossStreet(String crossStreet) {
        this.setCrossStreet(crossStreet);
        return this;
    }

    public void setCrossStreet(String crossStreet) {
        this.crossStreet = crossStreet;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public Venue currencyCode(String currencyCode) {
        this.setCurrencyCode(currencyCode);
        return this;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getExternalVenueId() {
        return this.externalVenueId;
    }

    public Venue externalVenueId(String externalVenueId) {
        this.setExternalVenueId(externalVenueId);
        return this;
    }

    public void setExternalVenueId(String externalVenueId) {
        this.externalVenueId = externalVenueId;
    }

    public Boolean getFullDiningBackend() {
        return this.fullDiningBackend;
    }

    public Venue fullDiningBackend(Boolean fullDiningBackend) {
        this.setFullDiningBackend(fullDiningBackend);
        return this;
    }

    public void setFullDiningBackend(Boolean fullDiningBackend) {
        this.fullDiningBackend = fullDiningBackend;
    }

    public Boolean getGridEnabled() {
        return this.gridEnabled;
    }

    public Venue gridEnabled(Boolean gridEnabled) {
        this.setGridEnabled(gridEnabled);
        return this;
    }

    public void setGridEnabled(Boolean gridEnabled) {
        this.gridEnabled = gridEnabled;
    }

    public String getVenueId() {
        return this.venueId;
    }

    public Venue venueId(String venueId) {
        this.setVenueId(venueId);
        return this;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public String getInternalName() {
        return this.internalName;
    }

    public Venue internalName(String internalName) {
        this.setInternalName(internalName);
        return this;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    public Boolean getMembershipEnabled() {
        return this.membershipEnabled;
    }

    public Venue membershipEnabled(Boolean membershipEnabled) {
        this.setMembershipEnabled(membershipEnabled);
        return this;
    }

    public void setMembershipEnabled(Boolean membershipEnabled) {
        this.membershipEnabled = membershipEnabled;
    }

    public String getName() {
        return this.name;
    }

    public Venue name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNeighborhood() {
        return this.neighborhood;
    }

    public Venue neighborhood(String neighborhood) {
        this.setNeighborhood(neighborhood);
        return this;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Venue phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPolicy() {
        return this.policy;
    }

    public Venue policy(String policy) {
        this.setPolicy(policy);
        return this;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public Venue postalCode(String postalCode) {
        this.setPostalCode(postalCode);
        return this;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPrimaryColor() {
        return this.primaryColor;
    }

    public Venue primaryColor(String primaryColor) {
        this.setPrimaryColor(primaryColor);
        return this;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getSecondaryColor() {
        return this.secondaryColor;
    }

    public Venue secondaryColor(String secondaryColor) {
        this.setSecondaryColor(secondaryColor);
        return this;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public String getState() {
        return this.state;
    }

    public Venue state(String state) {
        this.setState(state);
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUniqueConfirmationPrefix() {
        return this.uniqueConfirmationPrefix;
    }

    public Venue uniqueConfirmationPrefix(String uniqueConfirmationPrefix) {
        this.setUniqueConfirmationPrefix(uniqueConfirmationPrefix);
        return this;
    }

    public void setUniqueConfirmationPrefix(String uniqueConfirmationPrefix) {
        this.uniqueConfirmationPrefix = uniqueConfirmationPrefix;
    }

    public String getVenueClass() {
        return this.venueClass;
    }

    public Venue venueClass(String venueClass) {
        this.setVenueClass(venueClass);
        return this;
    }

    public void setVenueClass(String venueClass) {
        this.venueClass = venueClass;
    }

    public String getVenueGroupId() {
        return this.venueGroupId;
    }

    public Venue venueGroupId(String venueGroupId) {
        this.setVenueGroupId(venueGroupId);
        return this;
    }

    public void setVenueGroupId(String venueGroupId) {
        this.venueGroupId = venueGroupId;
    }

    public String getVenueGroupName() {
        return this.venueGroupName;
    }

    public Venue venueGroupName(String venueGroupName) {
        this.setVenueGroupName(venueGroupName);
        return this;
    }

    public void setVenueGroupName(String venueGroupName) {
        this.venueGroupName = venueGroupName;
    }

    public String getVenueUrlKey() {
        return this.venueUrlKey;
    }

    public Venue venueUrlKey(String venueUrlKey) {
        this.setVenueUrlKey(venueUrlKey);
        return this;
    }

    public void setVenueUrlKey(String venueUrlKey) {
        this.venueUrlKey = venueUrlKey;
    }

    public String getWebsite() {
        return this.website;
    }

    public Venue website(String website) {
        this.setWebsite(website);
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getWhiteLogo() {
        return this.whiteLogo;
    }

    public Venue whiteLogo(String whiteLogo) {
        this.setWhiteLogo(whiteLogo);
        return this;
    }

    public void setWhiteLogo(String whiteLogo) {
        this.whiteLogo = whiteLogo;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Venue)) {
            return false;
        }
        return getId() != null && getId().equals(((Venue) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Venue{" +
            "id=" + getId() +
            ", address='" + getAddress() + "'" +
            ", blackLogo='" + getBlackLogo() + "'" +
            ", country='" + getCountry() + "'" +
            ", crossStreet='" + getCrossStreet() + "'" +
            ", currencyCode='" + getCurrencyCode() + "'" +
            ", externalVenueId='" + getExternalVenueId() + "'" +
            ", fullDiningBackend='" + getFullDiningBackend() + "'" +
            ", gridEnabled='" + getGridEnabled() + "'" +
            ", venueId='" + getVenueId() + "'" +
            ", internalName='" + getInternalName() + "'" +
            ", membershipEnabled='" + getMembershipEnabled() + "'" +
            ", name='" + getName() + "'" +
            ", neighborhood='" + getNeighborhood() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", policy='" + getPolicy() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", primaryColor='" + getPrimaryColor() + "'" +
            ", secondaryColor='" + getSecondaryColor() + "'" +
            ", state='" + getState() + "'" +
            ", uniqueConfirmationPrefix='" + getUniqueConfirmationPrefix() + "'" +
            ", venueClass='" + getVenueClass() + "'" +
            ", venueGroupId='" + getVenueGroupId() + "'" +
            ", venueGroupName='" + getVenueGroupName() + "'" +
            ", venueUrlKey='" + getVenueUrlKey() + "'" +
            ", website='" + getWebsite() + "'" +
            ", whiteLogo='" + getWhiteLogo() + "'" +
            "}";
    }
}
