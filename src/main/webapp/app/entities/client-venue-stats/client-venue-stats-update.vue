<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="sevenRoomsToReviewProApplicationApp.clientVenueStats.home.createOrEditLabel"
          data-cy="ClientVenueStatsCreateUpdateHeading"
          v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="clientVenueStats.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="clientVenueStats.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalSpendLocalperCover')"
              for="client-venue-stats-totalSpendLocalperCover"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalSpendLocalperCover"
              id="client-venue-stats-totalSpendLocalperCover"
              data-cy="totalSpendLocalperCover"
              :class="{ valid: !v$.totalSpendLocalperCover.$invalid, invalid: v$.totalSpendLocalperCover.$invalid }"
              v-model.number="v$.totalSpendLocalperCover.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.lastVisitDate')"
              for="client-venue-stats-lastVisitDate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="lastVisitDate"
              id="client-venue-stats-lastVisitDate"
              data-cy="lastVisitDate"
              :class="{ valid: !v$.lastVisitDate.$invalid, invalid: v$.lastVisitDate.$invalid }"
              v-model="v$.lastVisitDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalCancellations')"
              for="client-venue-stats-totalCancellations"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalCancellations"
              id="client-venue-stats-totalCancellations"
              data-cy="totalCancellations"
              :class="{ valid: !v$.totalCancellations.$invalid, invalid: v$.totalCancellations.$invalid }"
              v-model.number="v$.totalCancellations.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalCovers')"
              for="client-venue-stats-totalCovers"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalCovers"
              id="client-venue-stats-totalCovers"
              data-cy="totalCovers"
              :class="{ valid: !v$.totalCovers.$invalid, invalid: v$.totalCovers.$invalid }"
              v-model.number="v$.totalCovers.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.avgRating')"
              for="client-venue-stats-avgRating"
            ></label>
            <input
              type="number"
              class="form-control"
              name="avgRating"
              id="client-venue-stats-avgRating"
              data-cy="avgRating"
              :class="{ valid: !v$.avgRating.$invalid, invalid: v$.avgRating.$invalid }"
              v-model.number="v$.avgRating.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalSpendperCover')"
              for="client-venue-stats-totalSpendperCover"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalSpendperCover"
              id="client-venue-stats-totalSpendperCover"
              data-cy="totalSpendperCover"
              :class="{ valid: !v$.totalSpendperCover.$invalid, invalid: v$.totalSpendperCover.$invalid }"
              v-model.number="v$.totalSpendperCover.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalSpend')"
              for="client-venue-stats-totalSpend"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalSpend"
              id="client-venue-stats-totalSpend"
              data-cy="totalSpend"
              :class="{ valid: !v$.totalSpend.$invalid, invalid: v$.totalSpend.$invalid }"
              v-model.number="v$.totalSpend.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalNoShows')"
              for="client-venue-stats-totalNoShows"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalNoShows"
              id="client-venue-stats-totalNoShows"
              data-cy="totalNoShows"
              :class="{ valid: !v$.totalNoShows.$invalid, invalid: v$.totalNoShows.$invalid }"
              v-model.number="v$.totalNoShows.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.numRatings')"
              for="client-venue-stats-numRatings"
            ></label>
            <input
              type="number"
              class="form-control"
              name="numRatings"
              id="client-venue-stats-numRatings"
              data-cy="numRatings"
              :class="{ valid: !v$.numRatings.$invalid, invalid: v$.numRatings.$invalid }"
              v-model.number="v$.numRatings.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalSpendPerVisit')"
              for="client-venue-stats-totalSpendPerVisit"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalSpendPerVisit"
              id="client-venue-stats-totalSpendPerVisit"
              data-cy="totalSpendPerVisit"
              :class="{ valid: !v$.totalSpendPerVisit.$invalid, invalid: v$.totalSpendPerVisit.$invalid }"
              v-model.number="v$.totalSpendPerVisit.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalSpendLocal')"
              for="client-venue-stats-totalSpendLocal"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalSpendLocal"
              id="client-venue-stats-totalSpendLocal"
              data-cy="totalSpendLocal"
              :class="{ valid: !v$.totalSpendLocal.$invalid, invalid: v$.totalSpendLocal.$invalid }"
              v-model.number="v$.totalSpendLocal.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalSpendLocalPerVisit')"
              for="client-venue-stats-totalSpendLocalPerVisit"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalSpendLocalPerVisit"
              id="client-venue-stats-totalSpendLocalPerVisit"
              data-cy="totalSpendLocalPerVisit"
              :class="{ valid: !v$.totalSpendLocalPerVisit.$invalid, invalid: v$.totalSpendLocalPerVisit.$invalid }"
              v-model.number="v$.totalSpendLocalPerVisit.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalVisits')"
              for="client-venue-stats-totalVisits"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalVisits"
              id="client-venue-stats-totalVisits"
              data-cy="totalVisits"
              :class="{ valid: !v$.totalVisits.$invalid, invalid: v$.totalVisits.$invalid }"
              v-model.number="v$.totalVisits.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.grossTotal')"
              for="client-venue-stats-grossTotal"
            ></label>
            <input
              type="number"
              class="form-control"
              name="grossTotal"
              id="client-venue-stats-grossTotal"
              data-cy="grossTotal"
              :class="{ valid: !v$.grossTotal.$invalid, invalid: v$.grossTotal.$invalid }"
              v-model.number="v$.grossTotal.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalOrderCount')"
              for="client-venue-stats-totalOrderCount"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalOrderCount"
              id="client-venue-stats-totalOrderCount"
              data-cy="totalOrderCount"
              :class="{ valid: !v$.totalOrderCount.$invalid, invalid: v$.totalOrderCount.$invalid }"
              v-model.number="v$.totalOrderCount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalOrderCancellations')"
              for="client-venue-stats-totalOrderCancellations"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalOrderCancellations"
              id="client-venue-stats-totalOrderCancellations"
              data-cy="totalOrderCancellations"
              :class="{ valid: !v$.totalOrderCancellations.$invalid, invalid: v$.totalOrderCancellations.$invalid }"
              v-model.number="v$.totalOrderCancellations.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalOrderSpend')"
              for="client-venue-stats-totalOrderSpend"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalOrderSpend"
              id="client-venue-stats-totalOrderSpend"
              data-cy="totalOrderSpend"
              :class="{ valid: !v$.totalOrderSpend.$invalid, invalid: v$.totalOrderSpend.$invalid }"
              v-model.number="v$.totalOrderSpend.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.grossOrderTotal')"
              for="client-venue-stats-grossOrderTotal"
            ></label>
            <input
              type="number"
              class="form-control"
              name="grossOrderTotal"
              id="client-venue-stats-grossOrderTotal"
              data-cy="grossOrderTotal"
              :class="{ valid: !v$.grossOrderTotal.$invalid, invalid: v$.grossOrderTotal.$invalid }"
              v-model.number="v$.grossOrderTotal.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalOrderSpendLocal')"
              for="client-venue-stats-totalOrderSpendLocal"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalOrderSpendLocal"
              id="client-venue-stats-totalOrderSpendLocal"
              data-cy="totalOrderSpendLocal"
              :class="{ valid: !v$.totalOrderSpendLocal.$invalid, invalid: v$.totalOrderSpendLocal.$invalid }"
              v-model.number="v$.totalOrderSpendLocal.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.lastOrderDate')"
              for="client-venue-stats-lastOrderDate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="lastOrderDate"
              id="client-venue-stats-lastOrderDate"
              data-cy="lastOrderDate"
              :class="{ valid: !v$.lastOrderDate.$invalid, invalid: v$.lastOrderDate.$invalid }"
              v-model="v$.lastOrderDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalSpendperOrder')"
              for="client-venue-stats-totalSpendperOrder"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalSpendperOrder"
              id="client-venue-stats-totalSpendperOrder"
              data-cy="totalSpendperOrder"
              :class="{ valid: !v$.totalSpendperOrder.$invalid, invalid: v$.totalSpendperOrder.$invalid }"
              v-model.number="v$.totalSpendperOrder.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.totalSpendLocalperOrder')"
              for="client-venue-stats-totalSpendLocalperOrder"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalSpendLocalperOrder"
              id="client-venue-stats-totalSpendLocalperOrder"
              data-cy="totalSpendLocalperOrder"
              :class="{ valid: !v$.totalSpendLocalperOrder.$invalid, invalid: v$.totalSpendLocalperOrder.$invalid }"
              v-model.number="v$.totalSpendLocalperOrder.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.venueId')"
              for="client-venue-stats-venueId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="venueId"
              id="client-venue-stats-venueId"
              data-cy="venueId"
              :class="{ valid: !v$.venueId.$invalid, invalid: v$.venueId.$invalid }"
              v-model="v$.venueId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.venueMarketingOptin')"
              for="client-venue-stats-venueMarketingOptin"
            ></label>
            <input
              type="checkbox"
              class="form-check"
              name="venueMarketingOptin"
              id="client-venue-stats-venueMarketingOptin"
              data-cy="venueMarketingOptin"
              :class="{ valid: !v$.venueMarketingOptin.$invalid, invalid: v$.venueMarketingOptin.$invalid }"
              v-model="v$.venueMarketingOptin.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.venueMarketingOptints')"
              for="client-venue-stats-venueMarketingOptints"
            ></label>
            <input
              type="text"
              class="form-control"
              name="venueMarketingOptints"
              id="client-venue-stats-venueMarketingOptints"
              data-cy="venueMarketingOptints"
              :class="{ valid: !v$.venueMarketingOptints.$invalid, invalid: v$.venueMarketingOptints.$invalid }"
              v-model="v$.venueMarketingOptints.$model"
            />
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
<script lang="ts" src="./client-venue-stats-update.component.ts"></script>
