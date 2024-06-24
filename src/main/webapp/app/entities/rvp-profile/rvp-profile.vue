<template>
  <div>
    <h2 id="page-heading" data-cy="RvpProfileHeading">
      <span v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.home.title')" id="rvp-profile-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'RvpProfileCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-rvp-profile"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && rvpProfiles && rvpProfiles.length === 0">
      <span v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="rvpProfiles && rvpProfiles.length > 0">
      <table class="table table-striped" aria-describedby="rvpProfiles">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.pmsId')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.firstName')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.lastName')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.language')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.checkin')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.checkout')"></span></th>
            <th scope="row"><span v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.email')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="rvpProfile in rvpProfiles" :key="rvpProfile.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'RvpProfileView', params: { rvpProfileId: rvpProfile.id } }">{{ rvpProfile.id }}</router-link>
            </td>
            <td>{{ rvpProfile.pmsId }}</td>
            <td>{{ rvpProfile.firstName }}</td>
            <td>{{ rvpProfile.lastName }}</td>
            <td>{{ rvpProfile.language }}</td>
            <td>{{ formatDateShort(rvpProfile.checkin) || '' }}</td>
            <td>{{ formatDateShort(rvpProfile.checkout) || '' }}</td>
            <td>{{ rvpProfile.email }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'RvpProfileView', params: { rvpProfileId: rvpProfile.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'RvpProfileEdit', params: { rvpProfileId: rvpProfile.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(rvpProfile)"
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
          id="sevenRoomsToReviewProApplicationApp.rvpProfile.delete.question"
          data-cy="rvpProfileDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-rvpProfile-heading"
          v-text="t$('sevenRoomsToReviewProApplicationApp.rvpProfile.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-rvpProfile"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeRvpProfile()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./rvp-profile.component.ts"></script>
