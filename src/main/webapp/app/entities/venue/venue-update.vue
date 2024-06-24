<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="sevenRoomsToReviewProApplicationApp.venue.home.createOrEditLabel"
          data-cy="VenueCreateUpdateHeading"
          v-text="t$('sevenRoomsToReviewProApplicationApp.venue.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="venue.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="venue.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToReviewProApplicationApp.venue.address')" for="venue-address"></label>
            <input
              type="text"
              class="form-control"
              name="address"
              id="venue-address"
              data-cy="address"
              :class="{ valid: !v$.address.$invalid, invalid: v$.address.$invalid }"
              v-model="v$.address.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.venue.blackLogo')"
              for="venue-blackLogo"
            ></label>
            <input
              type="text"
              class="form-control"
              name="blackLogo"
              id="venue-blackLogo"
              data-cy="blackLogo"
              :class="{ valid: !v$.blackLogo.$invalid, invalid: v$.blackLogo.$invalid }"
              v-model="v$.blackLogo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToReviewProApplicationApp.venue.country')" for="venue-country"></label>
            <input
              type="text"
              class="form-control"
              name="country"
              id="venue-country"
              data-cy="country"
              :class="{ valid: !v$.country.$invalid, invalid: v$.country.$invalid }"
              v-model="v$.country.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.venue.crossStreet')"
              for="venue-crossStreet"
            ></label>
            <input
              type="text"
              class="form-control"
              name="crossStreet"
              id="venue-crossStreet"
              data-cy="crossStreet"
              :class="{ valid: !v$.crossStreet.$invalid, invalid: v$.crossStreet.$invalid }"
              v-model="v$.crossStreet.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.venue.currencyCode')"
              for="venue-currencyCode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="currencyCode"
              id="venue-currencyCode"
              data-cy="currencyCode"
              :class="{ valid: !v$.currencyCode.$invalid, invalid: v$.currencyCode.$invalid }"
              v-model="v$.currencyCode.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.venue.externalVenueId')"
              for="venue-externalVenueId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="externalVenueId"
              id="venue-externalVenueId"
              data-cy="externalVenueId"
              :class="{ valid: !v$.externalVenueId.$invalid, invalid: v$.externalVenueId.$invalid }"
              v-model="v$.externalVenueId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.venue.fullDiningBackend')"
              for="venue-fullDiningBackend"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="fullDiningBackend"
              id="venue-fullDiningBackend"
              data-cy="fullDiningBackend"
              :class="{ valid: !v$.fullDiningBackend.$invalid, invalid: v$.fullDiningBackend.$invalid }"
              v-model="v$.fullDiningBackend.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.venue.gridEnabled')"
              for="venue-gridEnabled"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="gridEnabled"
              id="venue-gridEnabled"
              data-cy="gridEnabled"
              :class="{ valid: !v$.gridEnabled.$invalid, invalid: v$.gridEnabled.$invalid }"
              v-model="v$.gridEnabled.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToReviewProApplicationApp.venue.venueId')" for="venue-venueId"></label>
            <input
              type="text"
              class="form-control"
              name="venueId"
              id="venue-venueId"
              data-cy="venueId"
              :class="{ valid: !v$.venueId.$invalid, invalid: v$.venueId.$invalid }"
              v-model="v$.venueId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.venue.internalName')"
              for="venue-internalName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="internalName"
              id="venue-internalName"
              data-cy="internalName"
              :class="{ valid: !v$.internalName.$invalid, invalid: v$.internalName.$invalid }"
              v-model="v$.internalName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.venue.membershipEnabled')"
              for="venue-membershipEnabled"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="membershipEnabled"
              id="venue-membershipEnabled"
              data-cy="membershipEnabled"
              :class="{ valid: !v$.membershipEnabled.$invalid, invalid: v$.membershipEnabled.$invalid }"
              v-model="v$.membershipEnabled.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToReviewProApplicationApp.venue.name')" for="venue-name"></label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="venue-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.venue.neighborhood')"
              for="venue-neighborhood"
            ></label>
            <input
              type="text"
              class="form-control"
              name="neighborhood"
              id="venue-neighborhood"
              data-cy="neighborhood"
              :class="{ valid: !v$.neighborhood.$invalid, invalid: v$.neighborhood.$invalid }"
              v-model="v$.neighborhood.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.venue.phoneNumber')"
              for="venue-phoneNumber"
            ></label>
            <input
              type="text"
              class="form-control"
              name="phoneNumber"
              id="venue-phoneNumber"
              data-cy="phoneNumber"
              :class="{ valid: !v$.phoneNumber.$invalid, invalid: v$.phoneNumber.$invalid }"
              v-model="v$.phoneNumber.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToReviewProApplicationApp.venue.policy')" for="venue-policy"></label>
            <input
              type="text"
              class="form-control"
              name="policy"
              id="venue-policy"
              data-cy="policy"
              :class="{ valid: !v$.policy.$invalid, invalid: v$.policy.$invalid }"
              v-model="v$.policy.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.venue.postalCode')"
              for="venue-postalCode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="postalCode"
              id="venue-postalCode"
              data-cy="postalCode"
              :class="{ valid: !v$.postalCode.$invalid, invalid: v$.postalCode.$invalid }"
              v-model="v$.postalCode.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.venue.primaryColor')"
              for="venue-primaryColor"
            ></label>
            <input
              type="text"
              class="form-control"
              name="primaryColor"
              id="venue-primaryColor"
              data-cy="primaryColor"
              :class="{ valid: !v$.primaryColor.$invalid, invalid: v$.primaryColor.$invalid }"
              v-model="v$.primaryColor.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.venue.secondaryColor')"
              for="venue-secondaryColor"
            ></label>
            <input
              type="text"
              class="form-control"
              name="secondaryColor"
              id="venue-secondaryColor"
              data-cy="secondaryColor"
              :class="{ valid: !v$.secondaryColor.$invalid, invalid: v$.secondaryColor.$invalid }"
              v-model="v$.secondaryColor.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToReviewProApplicationApp.venue.state')" for="venue-state"></label>
            <input
              type="text"
              class="form-control"
              name="state"
              id="venue-state"
              data-cy="state"
              :class="{ valid: !v$.state.$invalid, invalid: v$.state.$invalid }"
              v-model="v$.state.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.venue.uniqueConfirmationPrefix')"
              for="venue-uniqueConfirmationPrefix"
            ></label>
            <input
              type="text"
              class="form-control"
              name="uniqueConfirmationPrefix"
              id="venue-uniqueConfirmationPrefix"
              data-cy="uniqueConfirmationPrefix"
              :class="{ valid: !v$.uniqueConfirmationPrefix.$invalid, invalid: v$.uniqueConfirmationPrefix.$invalid }"
              v-model="v$.uniqueConfirmationPrefix.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.venue.venueClass')"
              for="venue-venueClass"
            ></label>
            <input
              type="text"
              class="form-control"
              name="venueClass"
              id="venue-venueClass"
              data-cy="venueClass"
              :class="{ valid: !v$.venueClass.$invalid, invalid: v$.venueClass.$invalid }"
              v-model="v$.venueClass.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.venue.venueGroupId')"
              for="venue-venueGroupId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="venueGroupId"
              id="venue-venueGroupId"
              data-cy="venueGroupId"
              :class="{ valid: !v$.venueGroupId.$invalid, invalid: v$.venueGroupId.$invalid }"
              v-model="v$.venueGroupId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.venue.venueGroupName')"
              for="venue-venueGroupName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="venueGroupName"
              id="venue-venueGroupName"
              data-cy="venueGroupName"
              :class="{ valid: !v$.venueGroupName.$invalid, invalid: v$.venueGroupName.$invalid }"
              v-model="v$.venueGroupName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.venue.venueUrlKey')"
              for="venue-venueUrlKey"
            ></label>
            <input
              type="text"
              class="form-control"
              name="venueUrlKey"
              id="venue-venueUrlKey"
              data-cy="venueUrlKey"
              :class="{ valid: !v$.venueUrlKey.$invalid, invalid: v$.venueUrlKey.$invalid }"
              v-model="v$.venueUrlKey.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToReviewProApplicationApp.venue.website')" for="venue-website"></label>
            <input
              type="text"
              class="form-control"
              name="website"
              id="venue-website"
              data-cy="website"
              :class="{ valid: !v$.website.$invalid, invalid: v$.website.$invalid }"
              v-model="v$.website.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.venue.whiteLogo')"
              for="venue-whiteLogo"
            ></label>
            <input
              type="text"
              class="form-control"
              name="whiteLogo"
              id="venue-whiteLogo"
              data-cy="whiteLogo"
              :class="{ valid: !v$.whiteLogo.$invalid, invalid: v$.whiteLogo.$invalid }"
              v-model="v$.whiteLogo.$model"
            />
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
<script lang="ts" src="./venue-update.component.ts"></script>
