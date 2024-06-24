<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="sevenRoomsToReviewProApplicationApp.clientTag.home.createOrEditLabel"
          data-cy="ClientTagCreateUpdateHeading"
          v-text="t$('sevenRoomsToReviewProApplicationApp.clientTag.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="clientTag.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="clientTag.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('sevenRoomsToReviewProApplicationApp.clientTag.tag')" for="client-tag-tag"></label>
            <input
              type="text"
              class="form-control"
              name="tag"
              id="client-tag-tag"
              data-cy="tag"
              :class="{ valid: !v$.tag.$invalid, invalid: v$.tag.$invalid }"
              v-model="v$.tag.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientTag.tagDisplay')"
              for="client-tag-tagDisplay"
            ></label>
            <input
              type="text"
              class="form-control"
              name="tagDisplay"
              id="client-tag-tagDisplay"
              data-cy="tagDisplay"
              :class="{ valid: !v$.tagDisplay.$invalid, invalid: v$.tagDisplay.$invalid }"
              v-model="v$.tagDisplay.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientTag.group')"
              for="client-tag-group"
            ></label>
            <input
              type="text"
              class="form-control"
              name="group"
              id="client-tag-group"
              data-cy="group"
              :class="{ valid: !v$.group.$invalid, invalid: v$.group.$invalid }"
              v-model="v$.group.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientTag.groupDisplay')"
              for="client-tag-groupDisplay"
            ></label>
            <input
              type="text"
              class="form-control"
              name="groupDisplay"
              id="client-tag-groupDisplay"
              data-cy="groupDisplay"
              :class="{ valid: !v$.groupDisplay.$invalid, invalid: v$.groupDisplay.$invalid }"
              v-model="v$.groupDisplay.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientTag.color')"
              for="client-tag-color"
            ></label>
            <input
              type="text"
              class="form-control"
              name="color"
              id="client-tag-color"
              data-cy="color"
              :class="{ valid: !v$.color.$invalid, invalid: v$.color.$invalid }"
              v-model="v$.color.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientTag.tagSearchQuery')"
              for="client-tag-tagSearchQuery"
            ></label>
            <input
              type="text"
              class="form-control"
              name="tagSearchQuery"
              id="client-tag-tagSearchQuery"
              data-cy="tagSearchQuery"
              :class="{ valid: !v$.tagSearchQuery.$invalid, invalid: v$.tagSearchQuery.$invalid }"
              v-model="v$.tagSearchQuery.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientTag.client')"
              for="client-tag-client"
            ></label>
            <select class="form-control" id="client-tag-client" data-cy="client" name="client" v-model="clientTag.client">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="clientTag.client && clientOption.id === clientTag.client.id ? clientTag.client : clientOption"
                v-for="clientOption in clients"
                :key="clientOption.id"
              >
                {{ clientOption.id }}
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
<script lang="ts" src="./client-tag-update.component.ts"></script>
