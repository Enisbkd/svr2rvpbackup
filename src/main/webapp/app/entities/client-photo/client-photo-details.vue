<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="clientPhoto">
        <h2 class="jh-entity-heading" data-cy="clientPhotoDetailsHeading">
          <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.detail.title')"></span> {{ clientPhoto.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.large')"></span>
          </dt>
          <dd>
            <span>{{ clientPhoto.large }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.largeHeight')"></span>
          </dt>
          <dd>
            <span>{{ clientPhoto.largeHeight }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.largeWidth')"></span>
          </dt>
          <dd>
            <span>{{ clientPhoto.largeWidth }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.medium')"></span>
          </dt>
          <dd>
            <span>{{ clientPhoto.medium }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.mediumHeight')"></span>
          </dt>
          <dd>
            <span>{{ clientPhoto.mediumHeight }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.mediumWidth')"></span>
          </dt>
          <dd>
            <span>{{ clientPhoto.mediumWidth }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.small')"></span>
          </dt>
          <dd>
            <span>{{ clientPhoto.small }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.smallHeight')"></span>
          </dt>
          <dd>
            <span>{{ clientPhoto.smallHeight }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.smallWidth')"></span>
          </dt>
          <dd>
            <span>{{ clientPhoto.smallWidth }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.raw')"></span>
          </dt>
          <dd>
            <span>{{ clientPhoto.raw }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.cropx')"></span>
          </dt>
          <dd>
            <span>{{ clientPhoto.cropx }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.cropy')"></span>
          </dt>
          <dd>
            <span>{{ clientPhoto.cropy }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.cropHeight')"></span>
          </dt>
          <dd>
            <span>{{ clientPhoto.cropHeight }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientPhoto.cropWidth')"></span>
          </dt>
          <dd>
            <span>{{ clientPhoto.cropWidth }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="clientPhoto.id"
          :to="{ name: 'ClientPhotoEdit', params: { clientPhotoId: clientPhoto.id } }"
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

<script lang="ts" src="./client-photo-details.component.ts"></script>
