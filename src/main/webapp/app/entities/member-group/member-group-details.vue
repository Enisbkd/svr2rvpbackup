<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="memberGroup">
        <h2 class="jh-entity-heading" data-cy="memberGroupDetailsHeading">
          <span v-text="t$('sevenRoomsToReviewProApplicationApp.memberGroup.detail.title')"></span> {{ memberGroup.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.memberGroup.client')"></span>
          </dt>
          <dd>
            <div v-if="memberGroup.client">
              <router-link :to="{ name: 'ClientView', params: { clientId: memberGroup.client.id } }">{{
                memberGroup.client.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="memberGroup.id"
          :to="{ name: 'MemberGroupEdit', params: { memberGroupId: memberGroup.id } }"
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

<script lang="ts" src="./member-group-details.component.ts"></script>
