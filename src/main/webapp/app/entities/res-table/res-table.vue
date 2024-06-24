<template>
  <div>
    <h2 id="page-heading" data-cy="ResTableHeading">
      <span v-text="t$('sevenRoomsToReviewProApplicationApp.resTable.home.title')" id="res-table-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('sevenRoomsToReviewProApplicationApp.resTable.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ResTableCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-res-table"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.resTable.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && resTables && resTables.length === 0">
      <span v-text="t$('sevenRoomsToReviewProApplicationApp.resTable.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="resTables && resTables.length > 0">
      <table class="table table-striped" aria-describedby="resTables">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToReviewProApplicationApp.resTable.tableNumber')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToReviewProApplicationApp.resTable.reservation')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="resTable in resTables" :key="resTable.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ResTableView', params: { resTableId: resTable.id } }">{{ resTable.id }}</router-link>
            </td>
            <td>{{ resTable.tableNumber }}</td>
            <td>
              <div v-if="resTable.reservation">
                <router-link :to="{ name: 'ReservationView', params: { reservationId: resTable.reservation.id } }">{{
                  resTable.reservation.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ResTableView', params: { resTableId: resTable.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ResTableEdit', params: { resTableId: resTable.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(resTable)"
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
          id="sevenRoomsToReviewProApplicationApp.resTable.delete.question"
          data-cy="resTableDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-resTable-heading"
          v-text="t$('sevenRoomsToReviewProApplicationApp.resTable.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-resTable"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeResTable()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./res-table.component.ts"></script>
