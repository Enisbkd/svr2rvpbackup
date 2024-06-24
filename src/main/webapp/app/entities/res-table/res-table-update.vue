<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="sevenRoomsToReviewProApplicationApp.resTable.home.createOrEditLabel"
          data-cy="ResTableCreateUpdateHeading"
          v-text="t$('sevenRoomsToReviewProApplicationApp.resTable.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="resTable.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="resTable.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resTable.tableNumber')"
              for="res-table-tableNumber"
            ></label>
            <input
              type="text"
              class="form-control"
              name="tableNumber"
              id="res-table-tableNumber"
              data-cy="tableNumber"
              :class="{ valid: !v$.tableNumber.$invalid, invalid: v$.tableNumber.$invalid }"
              v-model="v$.tableNumber.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resTable.reservation')"
              for="res-table-reservation"
            ></label>
            <select class="form-control" id="res-table-reservation" data-cy="reservation" name="reservation" v-model="resTable.reservation">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  resTable.reservation && reservationOption.id === resTable.reservation.id ? resTable.reservation : reservationOption
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
<script lang="ts" src="./res-table-update.component.ts"></script>
