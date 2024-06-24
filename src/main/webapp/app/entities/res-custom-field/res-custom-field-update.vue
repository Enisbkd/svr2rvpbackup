<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="sevenRoomsToReviewProApplicationApp.resCustomField.home.createOrEditLabel"
          data-cy="ResCustomFieldCreateUpdateHeading"
          v-text="t$('sevenRoomsToReviewProApplicationApp.resCustomField.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="resCustomField.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="resCustomField.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resCustomField.systemName')"
              for="res-custom-field-systemName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="systemName"
              id="res-custom-field-systemName"
              data-cy="systemName"
              :class="{ valid: !v$.systemName.$invalid, invalid: v$.systemName.$invalid }"
              v-model="v$.systemName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resCustomField.displayOrder')"
              for="res-custom-field-displayOrder"
            ></label>
            <input
              type="number"
              class="form-control"
              name="displayOrder"
              id="res-custom-field-displayOrder"
              data-cy="displayOrder"
              :class="{ valid: !v$.displayOrder.$invalid, invalid: v$.displayOrder.$invalid }"
              v-model.number="v$.displayOrder.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resCustomField.name')"
              for="res-custom-field-name"
            ></label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="res-custom-field-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resCustomField.value')"
              for="res-custom-field-value"
            ></label>
            <input
              type="text"
              class="form-control"
              name="value"
              id="res-custom-field-value"
              data-cy="value"
              :class="{ valid: !v$.value.$invalid, invalid: v$.value.$invalid }"
              v-model="v$.value.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resCustomField.reservation')"
              for="res-custom-field-reservation"
            ></label>
            <select
              class="form-control"
              id="res-custom-field-reservation"
              data-cy="reservation"
              name="reservation"
              v-model="resCustomField.reservation"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  resCustomField.reservation && reservationOption.id === resCustomField.reservation.id
                    ? resCustomField.reservation
                    : reservationOption
                "
                v-for="reservationOption in reservations"
                :key="reservationOption.id"
              >
                {{ reservationOption.id }}
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
<script lang="ts" src="./res-custom-field-update.component.ts"></script>
