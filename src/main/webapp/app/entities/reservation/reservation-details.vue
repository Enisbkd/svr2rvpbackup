<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="reservation">
        <h2 class="jh-entity-heading" data-cy="reservationDetailsHeading">
          <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.detail.title')"></span> {{ reservation.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.resvId')"></span>
          </dt>
          <dd>
            <span>{{ reservation.resvId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.created')"></span>
          </dt>
          <dd>
            <span>{{ reservation.created }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.updated')"></span>
          </dt>
          <dd>
            <span>{{ reservation.updated }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.deleted')"></span>
          </dt>
          <dd>
            <span>{{ reservation.deleted }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.venueGroupClientId')"></span>
          </dt>
          <dd>
            <span>{{ reservation.venueGroupClientId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.venueGroupId')"></span>
          </dt>
          <dd>
            <span>{{ reservation.venueGroupId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.venueId')"></span>
          </dt>
          <dd>
            <span>{{ reservation.venueId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.date')"></span>
          </dt>
          <dd>
            <span>{{ reservation.date }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.duration')"></span>
          </dt>
          <dd>
            <span>{{ reservation.duration }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.checkNumbers')"></span>
          </dt>
          <dd>
            <span>{{ reservation.checkNumbers }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.shiftCategory')"></span>
          </dt>
          <dd>
            <span>{{ reservation.shiftCategory }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.shiftPersistentId')"></span>
          </dt>
          <dd>
            <span>{{ reservation.shiftPersistentId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.maxGuests')"></span>
          </dt>
          <dd>
            <span>{{ reservation.maxGuests }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.mfratioMale')"></span>
          </dt>
          <dd>
            <span>{{ reservation.mfratioMale }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.mfratioFemale')"></span>
          </dt>
          <dd>
            <span>{{ reservation.mfratioFemale }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.status')"></span>
          </dt>
          <dd>
            <span>{{ reservation.status }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.statusDisplay')"></span>
          </dt>
          <dd>
            <span>{{ reservation.statusDisplay }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.statusSimple')"></span>
          </dt>
          <dd>
            <span>{{ reservation.statusSimple }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.accessPersistentId')"></span>
          </dt>
          <dd>
            <span>{{ reservation.accessPersistentId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.arrivedGuests')"></span>
          </dt>
          <dd>
            <span>{{ reservation.arrivedGuests }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.isvip')"></span>
          </dt>
          <dd>
            <span>{{ reservation.isvip }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.bookedby')"></span>
          </dt>
          <dd>
            <span>{{ reservation.bookedby }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.clientReferenceCode')"></span>
          </dt>
          <dd>
            <span>{{ reservation.clientReferenceCode }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.lastname')"></span>
          </dt>
          <dd>
            <span>{{ reservation.lastname }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.firstname')"></span>
          </dt>
          <dd>
            <span>{{ reservation.firstname }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.email')"></span>
          </dt>
          <dd>
            <span>{{ reservation.email }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.phoneNumber')"></span>
          </dt>
          <dd>
            <span>{{ reservation.phoneNumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.address')"></span>
          </dt>
          <dd>
            <span>{{ reservation.address }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.address2')"></span>
          </dt>
          <dd>
            <span>{{ reservation.address2 }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.city')"></span>
          </dt>
          <dd>
            <span>{{ reservation.city }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.postalCode')"></span>
          </dt>
          <dd>
            <span>{{ reservation.postalCode }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.state')"></span>
          </dt>
          <dd>
            <span>{{ reservation.state }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.country')"></span>
          </dt>
          <dd>
            <span>{{ reservation.country }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.loyaltyId')"></span>
          </dt>
          <dd>
            <span>{{ reservation.loyaltyId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.loyaltyRank')"></span>
          </dt>
          <dd>
            <span>{{ reservation.loyaltyRank }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.loyaltyTier')"></span>
          </dt>
          <dd>
            <span>{{ reservation.loyaltyTier }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.notes')"></span>
          </dt>
          <dd>
            <span>{{ reservation.notes }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.arrivalTime')"></span>
          </dt>
          <dd>
            <span>{{ reservation.arrivalTime }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.seatedTime')"></span>
          </dt>
          <dd>
            <span>{{ reservation.seatedTime }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.leftTime')"></span>
          </dt>
          <dd>
            <span>{{ reservation.leftTime }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.clientRequests')"></span>
          </dt>
          <dd>
            <span>{{ reservation.clientRequests }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.comps')"></span>
          </dt>
          <dd>
            <span>{{ reservation.comps }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.compsPriceType')"></span>
          </dt>
          <dd>
            <span>{{ reservation.compsPriceType }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.costOption')"></span>
          </dt>
          <dd>
            <span>{{ reservation.costOption }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.policy')"></span>
          </dt>
          <dd>
            <span>{{ reservation.policy }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.minPrice')"></span>
          </dt>
          <dd>
            <span>{{ reservation.minPrice }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.prePayment')"></span>
          </dt>
          <dd>
            <span>{{ reservation.prePayment }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.onsitePayment')"></span>
          </dt>
          <dd>
            <span>{{ reservation.onsitePayment }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.totalPayment')"></span>
          </dt>
          <dd>
            <span>{{ reservation.totalPayment }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.paidBy')"></span>
          </dt>
          <dd>
            <span>{{ reservation.paidBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.servedBy')"></span>
          </dt>
          <dd>
            <span>{{ reservation.servedBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.rating')"></span>
          </dt>
          <dd>
            <span>{{ reservation.rating }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.problems')"></span>
          </dt>
          <dd>
            <span>{{ reservation.problems }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.autoAssignments')"></span>
          </dt>
          <dd>
            <span>{{ reservation.autoAssignments }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.externalClientId')"></span>
          </dt>
          <dd>
            <span>{{ reservation.externalClientId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.externalId')"></span>
          </dt>
          <dd>
            <span>{{ reservation.externalId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.externalReferenceCode')"></span>
          </dt>
          <dd>
            <span>{{ reservation.externalReferenceCode }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.externalUserId')"></span>
          </dt>
          <dd>
            <span>{{ reservation.externalUserId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.modifyReservationLink')"></span>
          </dt>
          <dd>
            <span>{{ reservation.modifyReservationLink }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.referenceCode')"></span>
          </dt>
          <dd>
            <span>{{ reservation.referenceCode }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.reservationSmsOptin')"></span>
          </dt>
          <dd>
            <span>{{ reservation.reservationSmsOptin }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.reservationType')"></span>
          </dt>
          <dd>
            <span>{{ reservation.reservationType }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.sendReminderEmail')"></span>
          </dt>
          <dd>
            <span>{{ reservation.sendReminderEmail }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.sendreminderSms')"></span>
          </dt>
          <dd>
            <span>{{ reservation.sendreminderSms }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.sourceClientId')"></span>
          </dt>
          <dd>
            <span>{{ reservation.sourceClientId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.userId')"></span>
          </dt>
          <dd>
            <span>{{ reservation.userId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.reservation.userName')"></span>
          </dt>
          <dd>
            <span>{{ reservation.userName }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="reservation.id"
          :to="{ name: 'ReservationEdit', params: { reservationId: reservation.id } }"
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

<script lang="ts" src="./reservation-details.component.ts"></script>
