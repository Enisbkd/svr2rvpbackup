<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="rvpProfile">
        <h2 class="jh-entity-heading" data-cy="rvpProfileDetailsHeading">
          <span v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.detail.title')"></span> {{ rvpProfile.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.pmsId')"></span>
          </dt>
          <dd>
            <span>{{ rvpProfile.pmsId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.firstName')"></span>
          </dt>
          <dd>
            <span>{{ rvpProfile.firstName }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.lastName')"></span>
          </dt>
          <dd>
            <span>{{ rvpProfile.lastName }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.language')"></span>
          </dt>
          <dd>
            <span>{{ rvpProfile.language }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.checkin')"></span>
          </dt>
          <dd>
            <span v-if="rvpProfile.checkin">{{ formatDateLong(rvpProfile.checkin) }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.checkout')"></span>
          </dt>
          <dd>
            <span v-if="rvpProfile.checkout">{{ formatDateLong(rvpProfile.checkout) }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.email')"></span>
          </dt>
          <dd>
            <span>{{ rvpProfile.email }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="rvpProfile.id"
          :to="{ name: 'RvpProfileEdit', params: { rvpProfileId: rvpProfile.id } }"
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

<script lang="ts" src="./rvp-profile-details.component.ts"></script>
