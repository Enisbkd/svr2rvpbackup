<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="sevenRoomsToReviewProApplicationApp.rvpProfile.home.createOrEditLabel"
          data-cy="RvpProfileCreateUpdateHeading"
          v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="rvpProfile.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="rvpProfile.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.pmsId')"
              for="rvp-profile-pmsId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="pmsId"
              id="rvp-profile-pmsId"
              data-cy="pmsId"
              :class="{ valid: !v$.pmsId.$invalid, invalid: v$.pmsId.$invalid }"
              v-model="v$.pmsId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.firstName')"
              for="rvp-profile-firstName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="firstName"
              id="rvp-profile-firstName"
              data-cy="firstName"
              :class="{ valid: !v$.firstName.$invalid, invalid: v$.firstName.$invalid }"
              v-model="v$.firstName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.lastName')"
              for="rvp-profile-lastName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="lastName"
              id="rvp-profile-lastName"
              data-cy="lastName"
              :class="{ valid: !v$.lastName.$invalid, invalid: v$.lastName.$invalid }"
              v-model="v$.lastName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.language')"
              for="rvp-profile-language"
            ></label>
            <input
              type="text"
              class="form-control"
              name="language"
              id="rvp-profile-language"
              data-cy="language"
              :class="{ valid: !v$.language.$invalid, invalid: v$.language.$invalid }"
              v-model="v$.language.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.checkin')"
              for="rvp-profile-checkin"
            ></label>
            <div class="d-flex">
              <input
                id="rvp-profile-checkin"
                data-cy="checkin"
                type="datetime-local"
                class="form-control"
                name="checkin"
                :class="{ valid: !v$.checkin.$invalid, invalid: v$.checkin.$invalid }"
                :value="convertDateTimeFromServer(v$.checkin.$model)"
                @change="updateZonedDateTimeField('checkin', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.checkout')"
              for="rvp-profile-checkout"
            ></label>
            <div class="d-flex">
              <input
                id="rvp-profile-checkout"
                data-cy="checkout"
                type="datetime-local"
                class="form-control"
                name="checkout"
                :class="{ valid: !v$.checkout.$invalid, invalid: v$.checkout.$invalid }"
                :value="convertDateTimeFromServer(v$.checkout.$model)"
                @change="updateZonedDateTimeField('checkout', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.email')"
              for="rvp-profile-email"
            ></label>
            <input
              type="text"
              class="form-control"
              name="email"
              id="rvp-profile-email"
              data-cy="email"
              :class="{ valid: !v$.email.$invalid, invalid: v$.email.$invalid }"
              v-model="v$.email.$model"
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
<script lang="ts" src="./rvp-profile-update.component.ts"></script>
