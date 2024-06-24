import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ReservationService from './reservation.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IReservation, Reservation } from '@/shared/model/reservation.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ReservationUpdate',
  setup() {
    const reservationService = inject('reservationService', () => new ReservationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const reservation: Ref<IReservation> = ref(new Reservation());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveReservation = async reservationId => {
      try {
        const res = await reservationService().find(reservationId);
        reservation.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.reservationId) {
      retrieveReservation(route.params.reservationId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      resvId: {},
      created: {},
      updated: {},
      deleted: {},
      venueGroupClientId: {},
      venueGroupId: {},
      venueId: {},
      date: {},
      duration: {},
      checkNumbers: {},
      shiftCategory: {},
      shiftPersistentId: {},
      maxGuests: {},
      mfratioMale: {},
      mfratioFemale: {},
      status: {},
      statusDisplay: {},
      statusSimple: {},
      accessPersistentId: {},
      arrivedGuests: {},
      isvip: {},
      bookedby: {},
      clientReferenceCode: {},
      lastname: {},
      firstname: {},
      email: {},
      phoneNumber: {},
      address: {},
      address2: {},
      city: {},
      postalCode: {},
      state: {},
      country: {},
      loyaltyId: {},
      loyaltyRank: {},
      loyaltyTier: {},
      notes: {},
      arrivalTime: {},
      seatedTime: {},
      leftTime: {},
      clientRequests: {},
      comps: {},
      compsPriceType: {},
      costOption: {},
      policy: {},
      minPrice: {},
      prePayment: {},
      onsitePayment: {},
      totalPayment: {},
      paidBy: {},
      servedBy: {},
      rating: {},
      problems: {},
      autoAssignments: {},
      externalClientId: {},
      externalId: {},
      externalReferenceCode: {},
      externalUserId: {},
      modifyReservationLink: {},
      referenceCode: {},
      reservationSmsOptin: {},
      reservationType: {},
      sendReminderEmail: {},
      sendreminderSms: {},
      sourceClientId: {},
      userId: {},
      userName: {},
    };
    const v$ = useVuelidate(validationRules, reservation as any);
    v$.value.$validate();

    return {
      reservationService,
      alertService,
      reservation,
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
      if (this.reservation.id) {
        this.reservationService()
          .update(this.reservation)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('sevenRoomsToReviewProApplicationApp.reservation.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.reservationService()
          .create(this.reservation)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(
              this.t$('sevenRoomsToReviewProApplicationApp.reservation.created', { param: param.id }).toString(),
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
