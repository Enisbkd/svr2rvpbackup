<template>
  <div>
    <h2 id="page-heading" data-cy="BookingNameHeading">
      <span v-text="t$('sevenRoomsToReviewProApplicationApp.bookingName.home.title')" id="booking-name-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('sevenRoomsToReviewProApplicationApp.bookingName.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'BookingNameCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-booking-name"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.bookingName.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && bookingNames && bookingNames.length === 0">
      <span v-text="t$('sevenRoomsToReviewProApplicationApp.bookingName.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="bookingNames && bookingNames.length > 0">
      <table class="table table-striped" aria-describedby="bookingNames">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('name')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.bookingName.name')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('clientVenueStats.id')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.bookingName.clientVenueStats')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'clientVenueStats.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="bookingName in bookingNames" :key="bookingName.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'BookingNameView', params: { bookingNameId: bookingName.id } }">{{ bookingName.id }}</router-link>
            </td>
            <td>{{ bookingName.name }}</td>
            <td>
              <div v-if="bookingName.clientVenueStats">
                <router-link :to="{ name: 'ClientVenueStatsView', params: { clientVenueStatsId: bookingName.clientVenueStats.id } }">{{
                  bookingName.clientVenueStats.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'BookingNameView', params: { bookingNameId: bookingName.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'BookingNameEdit', params: { bookingNameId: bookingName.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(bookingName)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <template #modal-title>
        <span
          id="sevenRoomsToReviewProApplicationApp.bookingName.delete.question"
          data-cy="bookingNameDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-bookingName-heading"
          v-text="t$('sevenRoomsToReviewProApplicationApp.bookingName.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-bookingName"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeBookingName()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="bookingNames && bookingNames.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./booking-name.component.ts"></script>
