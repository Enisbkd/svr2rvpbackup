<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="client">
        <h2 class="jh-entity-heading" data-cy="clientDetailsHeading">
          <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.detail.title')"></span> {{ client.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.clientId')"></span>
          </dt>
          <dd>
            <span>{{ client.clientId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.createdDate')"></span>
          </dt>
          <dd>
            <span>{{ client.createdDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.updatedDate')"></span>
          </dt>
          <dd>
            <span>{{ client.updatedDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.deletedDate')"></span>
          </dt>
          <dd>
            <span>{{ client.deletedDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.lastname')"></span>
          </dt>
          <dd>
            <span>{{ client.lastname }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.firstname')"></span>
          </dt>
          <dd>
            <span>{{ client.firstname }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.gender')"></span>
          </dt>
          <dd>
            <span>{{ client.gender }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.salutation')"></span>
          </dt>
          <dd>
            <span>{{ client.salutation }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.title')"></span>
          </dt>
          <dd>
            <span>{{ client.title }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.birthdayDay')"></span>
          </dt>
          <dd>
            <span>{{ client.birthdayDay }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.birthdayMonth')"></span>
          </dt>
          <dd>
            <span>{{ client.birthdayMonth }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.birthdayAltMonth')"></span>
          </dt>
          <dd>
            <span>{{ client.birthdayAltMonth }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.anniversaryDay')"></span>
          </dt>
          <dd>
            <span>{{ client.anniversaryDay }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.anniversaryMonth')"></span>
          </dt>
          <dd>
            <span>{{ client.anniversaryMonth }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.company')"></span>
          </dt>
          <dd>
            <span>{{ client.company }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.email')"></span>
          </dt>
          <dd>
            <span>{{ client.email }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.emailAlt')"></span>
          </dt>
          <dd>
            <span>{{ client.emailAlt }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.phoneNumber')"></span>
          </dt>
          <dd>
            <span>{{ client.phoneNumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.phoneNumberlocale')"></span>
          </dt>
          <dd>
            <span>{{ client.phoneNumberlocale }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.phoneNumberalt')"></span>
          </dt>
          <dd>
            <span>{{ client.phoneNumberalt }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.phoneNumberaltlocale')"></span>
          </dt>
          <dd>
            <span>{{ client.phoneNumberaltlocale }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.address')"></span>
          </dt>
          <dd>
            <span>{{ client.address }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.address2')"></span>
          </dt>
          <dd>
            <span>{{ client.address2 }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.city')"></span>
          </dt>
          <dd>
            <span>{{ client.city }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.postalCode')"></span>
          </dt>
          <dd>
            <span>{{ client.postalCode }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.state')"></span>
          </dt>
          <dd>
            <span>{{ client.state }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.country')"></span>
          </dt>
          <dd>
            <span>{{ client.country }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.isContactPrivate')"></span>
          </dt>
          <dd>
            <span>{{ client.isContactPrivate }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.isOnetimeGuest')"></span>
          </dt>
          <dd>
            <span>{{ client.isOnetimeGuest }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.status')"></span>
          </dt>
          <dd>
            <span>{{ client.status }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.loyaltyId')"></span>
          </dt>
          <dd>
            <span>{{ client.loyaltyId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.loyaltyRank')"></span>
          </dt>
          <dd>
            <span>{{ client.loyaltyRank }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.loyaltyTier')"></span>
          </dt>
          <dd>
            <span>{{ client.loyaltyTier }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.marketingOptin')"></span>
          </dt>
          <dd>
            <span>{{ client.marketingOptin }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.marketingOptints')"></span>
          </dt>
          <dd>
            <span>{{ client.marketingOptints }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.marketingOptOutts')"></span>
          </dt>
          <dd>
            <span>{{ client.marketingOptOutts }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.hasBillingProfile')"></span>
          </dt>
          <dd>
            <span>{{ client.hasBillingProfile }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.notes')"></span>
          </dt>
          <dd>
            <span>{{ client.notes }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.privateNotes')"></span>
          </dt>
          <dd>
            <span>{{ client.privateNotes }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.tags')"></span>
          </dt>
          <dd>
            <span>{{ client.tags }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.totalVisits')"></span>
          </dt>
          <dd>
            <span>{{ client.totalVisits }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.totalCovers')"></span>
          </dt>
          <dd>
            <span>{{ client.totalCovers }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.totalCancellations')"></span>
          </dt>
          <dd>
            <span>{{ client.totalCancellations }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.totalNoShows')"></span>
          </dt>
          <dd>
            <span>{{ client.totalNoShows }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.totalSpend')"></span>
          </dt>
          <dd>
            <span>{{ client.totalSpend }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.totalSpendPerCover')"></span>
          </dt>
          <dd>
            <span>{{ client.totalSpendPerCover }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.totalspendPerVisit')"></span>
          </dt>
          <dd>
            <span>{{ client.totalspendPerVisit }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.avgRating')"></span>
          </dt>
          <dd>
            <span>{{ client.avgRating }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.referenceCode')"></span>
          </dt>
          <dd>
            <span>{{ client.referenceCode }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.externalUserId')"></span>
          </dt>
          <dd>
            <span>{{ client.externalUserId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.venueGroupId')"></span>
          </dt>
          <dd>
            <span>{{ client.venueGroupId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.birthdayAltDay')"></span>
          </dt>
          <dd>
            <span>{{ client.birthdayAltDay }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.userId')"></span>
          </dt>
          <dd>
            <span>{{ client.userId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.userName')"></span>
          </dt>
          <dd>
            <span>{{ client.userName }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.totalOrderCount')"></span>
          </dt>
          <dd>
            <span>{{ client.totalOrderCount }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.preferredLanguageCode')"></span>
          </dt>
          <dd>
            <span>{{ client.preferredLanguageCode }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.clientPhoto')"></span>
          </dt>
          <dd>
            <div v-if="client.clientPhoto">
              <router-link :to="{ name: 'ClientPhotoView', params: { clientPhotoId: client.clientPhoto.id } }">{{
                client.clientPhoto.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.client.clientVenueStats')"></span>
          </dt>
          <dd>
            <div v-if="client.clientVenueStats">
              <router-link :to="{ name: 'ClientVenueStatsView', params: { clientVenueStatsId: client.clientVenueStats.id } }">{{
                client.clientVenueStats.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="client.id" :to="{ name: 'ClientEdit', params: { clientId: client.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./client-details.component.ts"></script>
