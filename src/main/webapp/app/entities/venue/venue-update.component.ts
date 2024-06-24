import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import VenueService from './venue.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IVenue, Venue } from '@/shared/model/venue.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'VenueUpdate',
  setup() {
    const venueService = inject('venueService', () => new VenueService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const venue: Ref<IVenue> = ref(new Venue());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveVenue = async venueId => {
      try {
        const res = await venueService().find(venueId);
        venue.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.venueId) {
      retrieveVenue(route.params.venueId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      address: {},
      blackLogo: {},
      country: {},
      crossStreet: {},
      currencyCode: {},
      externalVenueId: {},
      fullDiningBackend: {},
      gridEnabled: {},
      venueId: {},
      internalName: {},
      membershipEnabled: {},
      name: {},
      neighborhood: {},
      phoneNumber: {},
      policy: {},
      postalCode: {},
      primaryColor: {},
      secondaryColor: {},
      state: {},
      uniqueConfirmationPrefix: {},
      venueClass: {},
      venueGroupId: {},
      venueGroupName: {},
      venueUrlKey: {},
      website: {},
      whiteLogo: {},
    };
    const v$ = useVuelidate(validationRules, venue as any);
    v$.value.$validate();

    return {
      venueService,
      alertService,
      venue,
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
      if (this.venue.id) {
        this.venueService()
          .update(this.venue)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('sevenRoomsToReviewProApplicationApp.venue.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.venueService()
          .create(this.venue)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('sevenRoomsToReviewProApplicationApp.venue.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
