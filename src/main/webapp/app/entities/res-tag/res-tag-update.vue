<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="sevenRoomsToReviewProApplicationApp.resTag.home.createOrEditLabel"
          data-cy="ResTagCreateUpdateHeading"
          v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="resTag.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="resTag.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.tag')" for="res-tag-tag"></label>
            <input
              type="text"
              class="form-control"
              name="tag"
              id="res-tag-tag"
              data-cy="tag"
              :class="{ valid: !v$.tag.$invalid, invalid: v$.tag.$invalid }"
              v-model="v$.tag.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.tagDisplay')"
              for="res-tag-tagDisplay"
            ></label>
            <input
              type="text"
              class="form-control"
              name="tagDisplay"
              id="res-tag-tagDisplay"
              data-cy="tagDisplay"
              :class="{ valid: !v$.tagDisplay.$invalid, invalid: v$.tagDisplay.$invalid }"
              v-model="v$.tagDisplay.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.group')" for="res-tag-group"></label>
            <input
              type="text"
              class="form-control"
              name="group"
              id="res-tag-group"
              data-cy="group"
              :class="{ valid: !v$.group.$invalid, invalid: v$.group.$invalid }"
              v-model="v$.group.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.groupDisplay')"
              for="res-tag-groupDisplay"
            ></label>
            <input
              type="text"
              class="form-control"
              name="groupDisplay"
              id="res-tag-groupDisplay"
              data-cy="groupDisplay"
              :class="{ valid: !v$.groupDisplay.$invalid, invalid: v$.groupDisplay.$invalid }"
              v-model="v$.groupDisplay.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.color')" for="res-tag-color"></label>
            <input
              type="text"
              class="form-control"
              name="color"
              id="res-tag-color"
              data-cy="color"
              :class="{ valid: !v$.color.$invalid, invalid: v$.color.$invalid }"
              v-model="v$.color.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.tagSearchQuery')"
              for="res-tag-tagSearchQuery"
            ></label>
            <input
              type="text"
              class="form-control"
              name="tagSearchQuery"
              id="res-tag-tagSearchQuery"
              data-cy="tagSearchQuery"
              :class="{ valid: !v$.tagSearchQuery.$invalid, invalid: v$.tagSearchQuery.$invalid }"
              v-model="v$.tagSearchQuery.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.reservation')"
              for="res-tag-reservation"
            ></label>
            <select class="form-control" id="res-tag-reservation" data-cy="reservation" name="reservation" v-model="resTag.reservation">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="resTag.reservation && reservationOption.id === resTag.reservation.id ? resTag.reservation : reservationOption"
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
<script lang="ts" src="./res-tag-update.component.ts"></script>
