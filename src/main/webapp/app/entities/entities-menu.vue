<template>
  <div>
    <b-dropdown-item to="/rvp-profile">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.rvpProfile')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/client">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.client')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/member-group">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.memberGroup')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/client-tag">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.clientTag')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/custom-field">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.customField')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/client-venue-stats">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.clientVenueStats')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/booking-name">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.bookingName')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/client-photo">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.clientPhoto')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/reservation">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.reservation')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/res-custom-field">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.resCustomField')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/res-pos-ticket">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.resPosTicket')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/res-postickets-item">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.resPosticketsItem')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/res-tag">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.resTag')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/res-table">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.resTable')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/venue">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.venue')"></span>
    </b-dropdown-item>
    <!-- jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here -->
  </div>
</template>

<script lang="ts" src="./entities-menu.component.ts"></script>
