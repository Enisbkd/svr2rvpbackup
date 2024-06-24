<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="resTag">
        <h2 class="jh-entity-heading" data-cy="resTagDetailsHeading">
          <span v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.detail.title')"></span> {{ resTag.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.tag')"></span>
          </dt>
          <dd>
            <span>{{ resTag.tag }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.tagDisplay')"></span>
          </dt>
          <dd>
            <span>{{ resTag.tagDisplay }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.group')"></span>
          </dt>
          <dd>
            <span>{{ resTag.group }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.groupDisplay')"></span>
          </dt>
          <dd>
            <span>{{ resTag.groupDisplay }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.color')"></span>
          </dt>
          <dd>
            <span>{{ resTag.color }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.tagSearchQuery')"></span>
          </dt>
          <dd>
            <span>{{ resTag.tagSearchQuery }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.reservation')"></span>
          </dt>
          <dd>
            <div v-if="resTag.reservation">
              <router-link :to="{ name: 'ReservationView', params: { reservationId: resTag.reservation.id } }">{{
                resTag.reservation.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="resTag.id" :to="{ name: 'ResTagEdit', params: { resTagId: resTag.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./res-tag-details.component.ts"></script>
