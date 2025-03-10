<template>
  <div>
    <h2 id="page-heading" data-cy="ResTagHeading">
      <span v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.home.title')" id="res-tag-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ResTagCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-res-tag"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && resTags && resTags.length === 0">
      <span v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="resTags && resTags.length > 0">
      <table class="table table-striped" aria-describedby="resTags">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('tag')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.tag')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'tag'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('tagDisplay')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.tagDisplay')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'tagDisplay'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('group')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.group')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'group'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('groupDisplay')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.groupDisplay')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'groupDisplay'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('color')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.color')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'color'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('tagSearchQuery')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.tagSearchQuery')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'tagSearchQuery'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('reservation.id')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.reservation')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'reservation.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="resTag in resTags" :key="resTag.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ResTagView', params: { resTagId: resTag.id } }">{{ resTag.id }}</router-link>
            </td>
            <td>{{ resTag.tag }}</td>
            <td>{{ resTag.tagDisplay }}</td>
            <td>{{ resTag.group }}</td>
            <td>{{ resTag.groupDisplay }}</td>
            <td>{{ resTag.color }}</td>
            <td>{{ resTag.tagSearchQuery }}</td>
            <td>
              <div v-if="resTag.reservation">
                <router-link :to="{ name: 'ReservationView', params: { reservationId: resTag.reservation.id } }">{{
                  resTag.reservation.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ResTagView', params: { resTagId: resTag.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ResTagEdit', params: { resTagId: resTag.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(resTag)"
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
          id="sevenRoomsToReviewProApplicationApp.resTag.delete.question"
          data-cy="resTagDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-resTag-heading" v-text="t$('sevenRoomsToReviewProApplicationApp.resTag.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-resTag"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeResTag()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="resTags && resTags.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./res-tag.component.ts"></script>
