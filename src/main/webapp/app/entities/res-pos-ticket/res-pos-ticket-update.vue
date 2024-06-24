<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="sevenRoomsToReviewProApplicationApp.resPosTicket.home.createOrEditLabel"
          data-cy="ResPosTicketCreateUpdateHeading"
          v-text="t$('sevenRoomsToReviewProApplicationApp.resPosTicket.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="resPosTicket.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="resPosTicket.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resPosTicket.status')"
              for="res-pos-ticket-status"
            ></label>
            <input
              type="text"
              class="form-control"
              name="status"
              id="res-pos-ticket-status"
              data-cy="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resPosTicket.adminFee')"
              for="res-pos-ticket-adminFee"
            ></label>
            <input
              type="number"
              class="form-control"
              name="adminFee"
              id="res-pos-ticket-adminFee"
              data-cy="adminFee"
              :class="{ valid: !v$.adminFee.$invalid, invalid: v$.adminFee.$invalid }"
              v-model.number="v$.adminFee.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resPosTicket.code')"
              for="res-pos-ticket-code"
            ></label>
            <input
              type="number"
              class="form-control"
              name="code"
              id="res-pos-ticket-code"
              data-cy="code"
              :class="{ valid: !v$.code.$invalid, invalid: v$.code.$invalid }"
              v-model.number="v$.code.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resPosTicket.tableNo')"
              for="res-pos-ticket-tableNo"
            ></label>
            <input
              type="text"
              class="form-control"
              name="tableNo"
              id="res-pos-ticket-tableNo"
              data-cy="tableNo"
              :class="{ valid: !v$.tableNo.$invalid, invalid: v$.tableNo.$invalid }"
              v-model="v$.tableNo.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resPosTicket.tax')"
              for="res-pos-ticket-tax"
            ></label>
            <input
              type="number"
              class="form-control"
              name="tax"
              id="res-pos-ticket-tax"
              data-cy="tax"
              :class="{ valid: !v$.tax.$invalid, invalid: v$.tax.$invalid }"
              v-model.number="v$.tax.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resPosTicket.businessId')"
              for="res-pos-ticket-businessId"
            ></label>
            <input
              type="number"
              class="form-control"
              name="businessId"
              id="res-pos-ticket-businessId"
              data-cy="businessId"
              :class="{ valid: !v$.businessId.$invalid, invalid: v$.businessId.$invalid }"
              v-model.number="v$.businessId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resPosTicket.ticketId')"
              for="res-pos-ticket-ticketId"
            ></label>
            <input
              type="number"
              class="form-control"
              name="ticketId"
              id="res-pos-ticket-ticketId"
              data-cy="ticketId"
              :class="{ valid: !v$.ticketId.$invalid, invalid: v$.ticketId.$invalid }"
              v-model.number="v$.ticketId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resPosTicket.localPosticketId')"
              for="res-pos-ticket-localPosticketId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="localPosticketId"
              id="res-pos-ticket-localPosticketId"
              data-cy="localPosticketId"
              :class="{ valid: !v$.localPosticketId.$invalid, invalid: v$.localPosticketId.$invalid }"
              v-model="v$.localPosticketId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resPosTicket.employeeName')"
              for="res-pos-ticket-employeeName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="employeeName"
              id="res-pos-ticket-employeeName"
              data-cy="employeeName"
              :class="{ valid: !v$.employeeName.$invalid, invalid: v$.employeeName.$invalid }"
              v-model="v$.employeeName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resPosTicket.total')"
              for="res-pos-ticket-total"
            ></label>
            <input
              type="number"
              class="form-control"
              name="total"
              id="res-pos-ticket-total"
              data-cy="total"
              :class="{ valid: !v$.total.$invalid, invalid: v$.total.$invalid }"
              v-model.number="v$.total.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resPosTicket.subtotal')"
              for="res-pos-ticket-subtotal"
            ></label>
            <input
              type="number"
              class="form-control"
              name="subtotal"
              id="res-pos-ticket-subtotal"
              data-cy="subtotal"
              :class="{ valid: !v$.subtotal.$invalid, invalid: v$.subtotal.$invalid }"
              v-model.number="v$.subtotal.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resPosTicket.startTime')"
              for="res-pos-ticket-startTime"
            ></label>
            <input
              type="text"
              class="form-control"
              name="startTime"
              id="res-pos-ticket-startTime"
              data-cy="startTime"
              :class="{ valid: !v$.startTime.$invalid, invalid: v$.startTime.$invalid }"
              v-model="v$.startTime.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resPosTicket.serviceCharge')"
              for="res-pos-ticket-serviceCharge"
            ></label>
            <input
              type="number"
              class="form-control"
              name="serviceCharge"
              id="res-pos-ticket-serviceCharge"
              data-cy="serviceCharge"
              :class="{ valid: !v$.serviceCharge.$invalid, invalid: v$.serviceCharge.$invalid }"
              v-model.number="v$.serviceCharge.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resPosTicket.endtime')"
              for="res-pos-ticket-endtime"
            ></label>
            <input
              type="text"
              class="form-control"
              name="endtime"
              id="res-pos-ticket-endtime"
              data-cy="endtime"
              :class="{ valid: !v$.endtime.$invalid, invalid: v$.endtime.$invalid }"
              v-model="v$.endtime.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.resPosTicket.reservation')"
              for="res-pos-ticket-reservation"
            ></label>
            <select
              class="form-control"
              id="res-pos-ticket-reservation"
              data-cy="reservation"
              name="reservation"
              v-model="resPosTicket.reservation"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  resPosTicket.reservation && reservationOption.id === resPosTicket.reservation.id
                    ? resPosTicket.reservation
                    : reservationOption
                "
                v-for="reservationOption in reservations"
                :key="reservationOption.id"
              >
                {{ reservationOption.id }}
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
<script lang="ts" src="./res-pos-ticket-update.component.ts"></script>
