<template>
  <div>
    <h2 id="page-heading" data-cy="VenueHeading">
      <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.home.title')" id="venue-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'VenueCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-venue"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && venues && venues.length === 0">
      <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="venues && venues.length > 0">
      <table class="table table-striped" aria-describedby="venues">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('address')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.address')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'address'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('blackLogo')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.blackLogo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'blackLogo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('country')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.country')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'country'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('crossStreet')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.crossStreet')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'crossStreet'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('currencyCode')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.currencyCode')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'currencyCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('externalVenueId')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.externalVenueId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'externalVenueId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('fullDiningBackend')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.fullDiningBackend')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'fullDiningBackend'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('gridEnabled')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.gridEnabled')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'gridEnabled'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('venueId')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.venueId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'venueId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('internalName')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.internalName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'internalName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('membershipEnabled')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.membershipEnabled')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'membershipEnabled'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('name')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.name')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('neighborhood')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.neighborhood')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'neighborhood'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('phoneNumber')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.phoneNumber')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'phoneNumber'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('policy')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.policy')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'policy'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('postalCode')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.postalCode')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'postalCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('primaryColor')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.primaryColor')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'primaryColor'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('secondaryColor')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.secondaryColor')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'secondaryColor'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('state')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.state')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'state'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('uniqueConfirmationPrefix')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.uniqueConfirmationPrefix')"></span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'uniqueConfirmationPrefix'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('venueClass')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.venueClass')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'venueClass'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('venueGroupId')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.venueGroupId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'venueGroupId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('venueGroupName')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.venueGroupName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'venueGroupName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('venueUrlKey')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.venueUrlKey')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'venueUrlKey'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('website')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.website')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'website'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('whiteLogo')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.venue.whiteLogo')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'whiteLogo'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="venue in venues" :key="venue.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'VenueView', params: { venueId: venue.id } }">{{ venue.id }}</router-link>
            </td>
            <td>{{ venue.address }}</td>
            <td>{{ venue.blackLogo }}</td>
            <td>{{ venue.country }}</td>
            <td>{{ venue.crossStreet }}</td>
            <td>{{ venue.currencyCode }}</td>
            <td>{{ venue.externalVenueId }}</td>
            <td>{{ venue.fullDiningBackend }}</td>
            <td>{{ venue.gridEnabled }}</td>
            <td>{{ venue.venueId }}</td>
            <td>{{ venue.internalName }}</td>
            <td>{{ venue.membershipEnabled }}</td>
            <td>{{ venue.name }}</td>
            <td>{{ venue.neighborhood }}</td>
            <td>{{ venue.phoneNumber }}</td>
            <td>{{ venue.policy }}</td>
            <td>{{ venue.postalCode }}</td>
            <td>{{ venue.primaryColor }}</td>
            <td>{{ venue.secondaryColor }}</td>
            <td>{{ venue.state }}</td>
            <td>{{ venue.uniqueConfirmationPrefix }}</td>
            <td>{{ venue.venueClass }}</td>
            <td>{{ venue.venueGroupId }}</td>
            <td>{{ venue.venueGroupName }}</td>
            <td>{{ venue.venueUrlKey }}</td>
            <td>{{ venue.website }}</td>
            <td>{{ venue.whiteLogo }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'VenueView', params: { venueId: venue.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'VenueEdit', params: { venueId: venue.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(venue)"
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
          id="sevenRoomsToReviewProApplicationApp.venue.delete.question"
          data-cy="venueDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-venue-heading" v-text="t$('sevenRoomsToReviewProApplicationApp.venue.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-venue"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeVenue()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="venues && venues.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./venue.component.ts"></script>
