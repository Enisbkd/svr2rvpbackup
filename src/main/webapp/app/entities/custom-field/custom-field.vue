<template>
  <div>
    <h2 id="page-heading" data-cy="CustomFieldHeading">
      <span v-text="t$('sevenRoomsToReviewProApplicationApp.customField.home.title')" id="custom-field-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('sevenRoomsToReviewProApplicationApp.customField.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'CustomFieldCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-custom-field"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.customField.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && customFields && customFields.length === 0">
      <span v-text="t$('sevenRoomsToReviewProApplicationApp.customField.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="customFields && customFields.length > 0">
      <table class="table table-striped" aria-describedby="customFields">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('systemName')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.customField.systemName')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'systemName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('displayOrder')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.customField.displayOrder')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'displayOrder'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('name')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.customField.name')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('value')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.customField.value')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'value'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('client.id')">
              <span v-text="t$('sevenRoomsToReviewProApplicationApp.customField.client')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'client.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="customField in customFields" :key="customField.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'CustomFieldView', params: { customFieldId: customField.id } }">{{ customField.id }}</router-link>
            </td>
            <td>{{ customField.systemName }}</td>
            <td>{{ customField.displayOrder }}</td>
            <td>{{ customField.name }}</td>
            <td>{{ customField.value }}</td>
            <td>
              <div v-if="customField.client">
                <router-link :to="{ name: 'ClientView', params: { clientId: customField.client.id } }">{{
                  customField.client.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'CustomFieldView', params: { customFieldId: customField.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'CustomFieldEdit', params: { customFieldId: customField.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(customField)"
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
          id="sevenRoomsToReviewProApplicationApp.customField.delete.question"
          data-cy="customFieldDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-customField-heading"
          v-text="t$('sevenRoomsToReviewProApplicationApp.customField.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-customField"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeCustomField()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="customFields && customFields.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./custom-field.component.ts"></script>
