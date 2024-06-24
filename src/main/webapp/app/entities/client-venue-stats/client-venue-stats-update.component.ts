import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ClientVenueStatsService from './client-venue-stats.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IClientVenueStats, ClientVenueStats } from '@/shared/model/client-venue-stats.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ClientVenueStatsUpdate',
  setup() {
    const clientVenueStatsService = inject('clientVenueStatsService', () => new ClientVenueStatsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const clientVenueStats: Ref<IClientVenueStats> = ref(new ClientVenueStats());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveClientVenueStats = async clientVenueStatsId => {
      try {
        const res = await clientVenueStatsService().find(clientVenueStatsId);
        clientVenueStats.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.clientVenueStatsId) {
      retrieveClientVenueStats(route.params.clientVenueStatsId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      totalSpendLocalperCover: {},
      lastVisitDate: {},
      totalCancellations: {},
      totalCovers: {},
      avgRating: {},
      totalSpendperCover: {},
      totalSpend: {},
      totalNoShows: {},
      numRatings: {},
      totalSpendPerVisit: {},
      totalSpendLocal: {},
      totalSpendLocalPerVisit: {},
      totalVisits: {},
      grossTotal: {},
      totalOrderCount: {},
      totalOrderCancellations: {},
      totalOrderSpend: {},
      grossOrderTotal: {},
      totalOrderSpendLocal: {},
      lastOrderDate: {},
      totalSpendperOrder: {},
      totalSpendLocalperOrder: {},
      venueId: {},
      venueMarketingOptin: {},
      venueMarketingOptints: {},
    };
    const v$ = useVuelidate(validationRules, clientVenueStats as any);
    v$.value.$validate();

    return {
      clientVenueStatsService,
      alertService,
      clientVenueStats,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.clientVenueStats.id) {
        this.clientVenueStatsService()
          .update(this.clientVenueStats)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.clientVenueStatsService()
          .create(this.clientVenueStats)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(
              this.t$('sevenRoomsToReviewProApplicationApp.clientVenueStats.created', { param: param.id }).toString(),
            );
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
