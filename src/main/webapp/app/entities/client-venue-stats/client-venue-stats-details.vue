<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="clientVenueStats">
        <h2 class="jh-entity-heading" data-cy="clientVenueStatsDetailsHeading">
          <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.detail.title')"></span> {{ clientVenueStats.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalSpendLocalperCover')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.totalSpendLocalperCover }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.lastVisitDate')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.lastVisitDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalCancellations')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.totalCancellations }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalCovers')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.totalCovers }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.avgRating')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.avgRating }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalSpendperCover')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.totalSpendperCover }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalSpend')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.totalSpend }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalNoShows')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.totalNoShows }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.numRatings')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.numRatings }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalSpendPerVisit')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.totalSpendPerVisit }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalSpendLocal')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.totalSpendLocal }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalSpendLocalPerVisit')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.totalSpendLocalPerVisit }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalVisits')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.totalVisits }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.grossTotal')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.grossTotal }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalOrderCount')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.totalOrderCount }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalOrderCancellations')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.totalOrderCancellations }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalOrderSpend')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.totalOrderSpend }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.grossOrderTotal')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.grossOrderTotal }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalOrderSpendLocal')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.totalOrderSpendLocal }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.lastOrderDate')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.lastOrderDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalSpendperOrder')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.totalSpendperOrder }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalSpendLocalperOrder')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.totalSpendLocalperOrder }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.venueId')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.venueId }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.venueMarketingOptin')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.venueMarketingOptin }}</span>
          </dd>
          <dt>
            <span v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.venueMarketingOptints')"></span>
          </dt>
          <dd>
            <span>{{ clientVenueStats.venueMarketingOptints }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="clientVenueStats.id"
          :to="{ name: 'ClientVenueStatsEdit', params: { clientVenueStatsId: clientVenueStats.id } }"
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

<script lang="ts" src="./client-venue-stats-details.component.ts"></script>
