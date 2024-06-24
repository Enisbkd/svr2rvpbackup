<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="sevenRoomsToReviewProApplicationApp.resPosticketsItem.home.createOrEditLabel"
          data-cy="ResPosticketsItemCreateUpdateHeading"
          v-text="t$('sevenRoomsToReviewProApplicationApp.resPosticketsItem.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="resPosticketsItem.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="resPosticketsItem.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resPosticketsItem.price')"
              for="res-postickets-item-price"
            ></label>
            <input
              type="number"
              class="form-control"
              name="price"
              id="res-postickets-item-price"
              data-cy="price"
              :class="{ valid: !v$.price.$invalid, invalid: v$.price.$invalid }"
              v-model.number="v$.price.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resPosticketsItem.name')"
              for="res-postickets-item-name"
            ></label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="res-postickets-item-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resPosticketsItem.quantity')"
              for="res-postickets-item-quantity"
            ></label>
            <input
              type="number"
              class="form-control"
              name="quantity"
              id="res-postickets-item-quantity"
              data-cy="quantity"
              :class="{ valid: !v$.quantity.$invalid, invalid: v$.quantity.$invalid }"
              v-model.number="v$.quantity.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resPosticketsItem.resPosTicket')"
              for="res-postickets-item-resPosTicket"
            ></label>
            <select
              class="form-control"
              id="res-postickets-item-resPosTicket"
              data-cy="resPosTicket"
              name="resPosTicket"
              v-model="resPosticketsItem.resPosTicket"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  resPosticketsItem.resPosTicket && resPosTicketOption.id === resPosticketsItem.resPosTicket.id
                    ? resPosticketsItem.resPosTicket
                    : resPosTicketOption
                "
                v-for="resPosTicketOption in resPosTickets"
                :key="resPosTicketOption.id"
              >
                {{ resPosTicketOption.id }}
              </option>
            </select>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.cancel')"></span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="v$.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.save')"></span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./res-postickets-item-update.component.ts"></script>
