<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="bookingName">
        <h2 class="jh-entity-heading" data-cy="bookingNameDetailsHeading">
          <span v-text="t$('sevenRoomsToReviewProApplicationApp.bookingName.detail.title')"></span> {{ bookingName.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.bookingName.name')"></span>
          </dt>
          <dd>
            <span>{{ bookingName.name }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.bookingName.clientVenueStats')"></span>
          </dt>
          <dd>
            <div v-if="bookingName.clientVenueStats">
              <router-link :to="{ name: 'ClientVenueStatsView', params: { clientVenueStatsId: bookingName.clientVenueStats.id } }">{{
                bookingName.clientVenueStats.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="bookingName.id"
          :to="{ name: 'BookingNameEdit', params: { bookingNameId: bookingName.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./booking-name-details.component.ts"></script>
