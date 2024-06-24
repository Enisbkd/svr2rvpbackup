<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="customField">
        <h2 class="jh-entity-heading" data-cy="customFieldDetailsHeading">
          <span v-text="t$('sevenRoomsToReviewProApplicationApp.customField.detail.title')"></span> {{ customField.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.customField.systemName')"></span>
          </dt>
          <dd>
            <span>{{ customField.systemName }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.customField.displayOrder')"></span>
          </dt>
          <dd>
            <span>{{ customField.displayOrder }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.customField.name')"></span>
          </dt>
          <dd>
            <span>{{ customField.name }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.customField.value')"></span>
          </dt>
          <dd>
            <span>{{ customField.value }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.customField.client')"></span>
          </dt>
          <dd>
            <div v-if="customField.client">
              <router-link :to="{ name: 'ClientView', params: { clientId: customField.client.id } }">{{
                customField.client.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="customField.id"
          :to="{ name: 'CustomFieldEdit', params: { customFieldId: customField.id } }"
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

<script lang="ts" src="./custom-field-details.component.ts"></script>
