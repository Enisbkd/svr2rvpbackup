<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="sevenRoomsToReviewProApplicationApp.bookingName.home.createOrEditLabel"
          data-cy="BookingNameCreateUpdateHeading"
          v-text="t$('sevenRoomsToReviewProApplicationApp.bookingName.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="bookingName.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="bookingName.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.bookingName.name')"
              for="booking-name-name"
            ></label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="booking-name-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.bookingName.clientVenueStats')"
              for="booking-name-clientVenueStats"
            ></label>
            <select
              class="form-control"
              id="booking-name-clientVenueStats"
              data-cy="clientVenueStats"
              name="clientVenueStats"
              v-model="bookingName.clientVenueStats"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  bookingName.clientVenueStats && clientVenueStatsOption.id === bookingName.clientVenueStats.id
                    ? bookingName.clientVenueStats
                    : clientVenueStatsOption
                "
                v-for="clientVenueStatsOption in clientVenueStats"
                :key="clientVenueStatsOption.id"
              >
                {{ clientVenueStatsOption.id }}
              </option>
            </select>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.cancel')"></span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="v$.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.save')"></span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./booking-name-update.component.ts"></script>
