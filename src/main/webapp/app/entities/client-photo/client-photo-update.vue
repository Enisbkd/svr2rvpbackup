<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="sevenRoomsToReviewProApplicationApp.clientPhoto.home.createOrEditLabel"
          data-cy="ClientPhotoCreateUpdateHeading"
          v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="clientPhoto.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="clientPhoto.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.large')"
              for="client-photo-large"
            ></label>
            <input
              type="text"
              class="form-control"
              name="large"
              id="client-photo-large"
              data-cy="large"
              :class="{ valid: !v$.large.$invalid, invalid: v$.large.$invalid }"
              v-model="v$.large.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.largeHeight')"
              for="client-photo-largeHeight"
            ></label>
            <input
              type="number"
              class="form-control"
              name="largeHeight"
              id="client-photo-largeHeight"
              data-cy="largeHeight"
              :class="{ valid: !v$.largeHeight.$invalid, invalid: v$.largeHeight.$invalid }"
              v-model.number="v$.largeHeight.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.largeWidth')"
              for="client-photo-largeWidth"
            ></label>
            <input
              type="number"
              class="form-control"
              name="largeWidth"
              id="client-photo-largeWidth"
              data-cy="largeWidth"
              :class="{ valid: !v$.largeWidth.$invalid, invalid: v$.largeWidth.$invalid }"
              v-model.number="v$.largeWidth.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.medium')"
              for="client-photo-medium"
            ></label>
            <input
              type="text"
              class="form-control"
              name="medium"
              id="client-photo-medium"
              data-cy="medium"
              :class="{ valid: !v$.medium.$invalid, invalid: v$.medium.$invalid }"
              v-model="v$.medium.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.mediumHeight')"
              for="client-photo-mediumHeight"
            ></label>
            <input
              type="number"
              class="form-control"
              name="mediumHeight"
              id="client-photo-mediumHeight"
              data-cy="mediumHeight"
              :class="{ valid: !v$.mediumHeight.$invalid, invalid: v$.mediumHeight.$invalid }"
              v-model.number="v$.mediumHeight.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.mediumWidth')"
              for="client-photo-mediumWidth"
            ></label>
            <input
              type="number"
              class="form-control"
              name="mediumWidth"
              id="client-photo-mediumWidth"
              data-cy="mediumWidth"
              :class="{ valid: !v$.mediumWidth.$invalid, invalid: v$.mediumWidth.$invalid }"
              v-model.number="v$.mediumWidth.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.small')"
              for="client-photo-small"
            ></label>
            <input
              type="text"
              class="form-control"
              name="small"
              id="client-photo-small"
              data-cy="small"
              :class="{ valid: !v$.small.$invalid, invalid: v$.small.$invalid }"
              v-model="v$.small.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.smallHeight')"
              for="client-photo-smallHeight"
            ></label>
            <input
              type="number"
              class="form-control"
              name="smallHeight"
              id="client-photo-smallHeight"
              data-cy="smallHeight"
              :class="{ valid: !v$.smallHeight.$invalid, invalid: v$.smallHeight.$invalid }"
              v-model.number="v$.smallHeight.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.smallWidth')"
              for="client-photo-smallWidth"
            ></label>
            <input
              type="number"
              class="form-control"
              name="smallWidth"
              id="client-photo-smallWidth"
              data-cy="smallWidth"
              :class="{ valid: !v$.smallWidth.$invalid, invalid: v$.smallWidth.$invalid }"
              v-model.number="v$.smallWidth.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.raw')"
              for="client-photo-raw"
            ></label>
            <input
              type="text"
              class="form-control"
              name="raw"
              id="client-photo-raw"
              data-cy="raw"
              :class="{ valid: !v$.raw.$invalid, invalid: v$.raw.$invalid }"
              v-model="v$.raw.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.cropx')"
              for="client-photo-cropx"
            ></label>
            <input
              type="number"
              class="form-control"
              name="cropx"
              id="client-photo-cropx"
              data-cy="cropx"
              :class="{ valid: !v$.cropx.$invalid, invalid: v$.cropx.$invalid }"
              v-model.number="v$.cropx.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.cropy')"
              for="client-photo-cropy"
            ></label>
            <input
              type="number"
              class="form-control"
              name="cropy"
              id="client-photo-cropy"
              data-cy="cropy"
              :class="{ valid: !v$.cropy.$invalid, invalid: v$.cropy.$invalid }"
              v-model.number="v$.cropy.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.cropHeight')"
              for="client-photo-cropHeight"
            ></label>
            <input
              type="number"
              class="form-control"
              name="cropHeight"
              id="client-photo-cropHeight"
              data-cy="cropHeight"
              :class="{ valid: !v$.cropHeight.$invalid, invalid: v$.cropHeight.$invalid }"
              v-model.number="v$.cropHeight.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.cropWidth')"
              for="client-photo-cropWidth"
            ></label>
            <input
              type="number"
              class="form-control"
              name="cropWidth"
              id="client-photo-cropWidth"
              data-cy="cropWidth"
              :class="{ valid: !v$.cropWidth.$invalid, invalid: v$.cropWidth.$invalid }"
              v-model.number="v$.cropWidth.$model"
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
<script lang="ts" src="./client-photo-update.component.ts"></script>
