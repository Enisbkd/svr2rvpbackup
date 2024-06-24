<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="venue">
        <h2 class="jh-entity-heading" data-cy="venueDetailsHeading">
          <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.detail.title')"></span> {{ venue.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.address')"></span>
          </dt>
          <dd>
            <span>{{ venue.address }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.blackLogo')"></span>
          </dt>
          <dd>
            <span>{{ venue.blackLogo }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.country')"></span>
          </dt>
          <dd>
            <span>{{ venue.country }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.crossStreet')"></span>
          </dt>
          <dd>
            <span>{{ venue.crossStreet }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.currencyCode')"></span>
          </dt>
          <dd>
            <span>{{ venue.currencyCode }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.externalVenueId')"></span>
          </dt>
          <dd>
            <span>{{ venue.externalVenueId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.fullDiningBackend')"></span>
          </dt>
          <dd>
            <span>{{ venue.fullDiningBackend }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.gridEnabled')"></span>
          </dt>
          <dd>
            <span>{{ venue.gridEnabled }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.venueId')"></span>
          </dt>
          <dd>
            <span>{{ venue.venueId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.internalName')"></span>
          </dt>
          <dd>
            <span>{{ venue.internalName }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.membershipEnabled')"></span>
          </dt>
          <dd>
            <span>{{ venue.membershipEnabled }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.name')"></span>
          </dt>
          <dd>
            <span>{{ venue.name }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.neighborhood')"></span>
          </dt>
          <dd>
            <span>{{ venue.neighborhood }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.phoneNumber')"></span>
          </dt>
          <dd>
            <span>{{ venue.phoneNumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.policy')"></span>
          </dt>
          <dd>
            <span>{{ venue.policy }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.postalCode')"></span>
          </dt>
          <dd>
            <span>{{ venue.postalCode }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.primaryColor')"></span>
          </dt>
          <dd>
            <span>{{ venue.primaryColor }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.secondaryColor')"></span>
          </dt>
          <dd>
            <span>{{ venue.secondaryColor }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.state')"></span>
          </dt>
          <dd>
            <span>{{ venue.state }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.uniqueConfirmationPrefix')"></span>
          </dt>
          <dd>
            <span>{{ venue.uniqueConfirmationPrefix }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.venueClass')"></span>
          </dt>
          <dd>
            <span>{{ venue.venueClass }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.venueGroupId')"></span>
          </dt>
          <dd>
            <span>{{ venue.venueGroupId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.venueGroupName')"></span>
          </dt>
          <dd>
            <span>{{ venue.venueGroupName }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.venueUrlKey')"></span>
          </dt>
          <dd>
            <span>{{ venue.venueUrlKey }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.website')"></span>
          </dt>
          <dd>
            <span>{{ venue.website }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.whiteLogo')"></span>
          </dt>
          <dd>
            <span>{{ venue.whiteLogo }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="venue.id" :to="{ name: 'VenueEdit', params: { venueId: venue.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./venue-details.component.ts"></script>
