<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="resTable">
        <h2 class="jh-entity-heading" data-cy="resTableDetailsHeading">
          <span v-text="t$('sevenRoomsToReviewProApplicationApp.resTable.detail.title')"></span> {{ resTable.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.resTable.tableNumber')"></span>
          </dt>
          <dd>
            <span>{{ resTable.tableNumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.resTable.reservation')"></span>
          </dt>
          <dd>
            <div v-if="resTable.reservation">
              <router-link :to="{ name: 'ReservationView', params: { reservationId: resTable.reservation.id } }">{{
                resTable.reservation.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="resTable.id" :to="{ name: 'ResTableEdit', params: { resTableId: resTable.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./res-table-details.component.ts"></script>
