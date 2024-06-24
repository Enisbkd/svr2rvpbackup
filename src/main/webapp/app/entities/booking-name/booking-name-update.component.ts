import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import BookingNameService from './booking-name.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ClientVenueStatsService from '@/entities/client-venue-stats/client-venue-stats.service';
import { type IClientVenueStats } from '@/shared/model/client-venue-stats.model';
import { type IBookingName, BookingName } from '@/shared/model/booking-name.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'BookingNameUpdate',
  setup() {
    const bookingNameService = inject('bookingNameService', () => new BookingNameService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const bookingName: Ref<IBookingName> = ref(new BookingName());

    const clientVenueStatsService = inject('clientVenueStatsService', () => new ClientVenueStatsService());

    const clientVenueStats: Ref<IClientVenueStats[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveBookingName = async bookingNameId => {
      try {
        const res = await bookingNameService().find(bookingNameId);
        bookingName.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.bookingNameId) {
      retrieveBookingName(route.params.bookingNameId);
    }

    const initRelationships = () => {
      clientVenueStatsService()
        .retrieve()
        .then(res => {
          clientVenueStats.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      name: {},
      clientVenueStats: {},
    };
    const v$ = useVuelidate(validationRules, bookingName as any);
    v$.value.$validate();

    return {
      bookingNameService,
      alertService,
      bookingName,
      previousState,
      isSaving,
      currentLanguage,
      clientVenueStats,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.bookingName.id) {
        this.bookingNameService()
          .update(this.bookingName)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('sevenRoomsToReviewProApplicationApp.bookingName.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.bookingNameService()
          .create(this.bookingName)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(
              this.t$('sevenRoomsToReviewProApplicationApp.bookingName.created', { param: param.id }).toString(),
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
