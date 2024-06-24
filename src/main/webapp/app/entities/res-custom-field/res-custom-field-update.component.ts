import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ResCustomFieldService from './res-custom-field.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ReservationService from '@/entities/reservation/reservation.service';
import { type IReservation } from '@/shared/model/reservation.model';
import { type IResCustomField, ResCustomField } from '@/shared/model/res-custom-field.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ResCustomFieldUpdate',
  setup() {
    const resCustomFieldService = inject('resCustomFieldService', () => new ResCustomFieldService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const resCustomField: Ref<IResCustomField> = ref(new ResCustomField());

    const reservationService = inject('reservationService', () => new ReservationService());

    const reservations: Ref<IReservation[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveResCustomField = async resCustomFieldId => {
      try {
        const res = await resCustomFieldService().find(resCustomFieldId);
        resCustomField.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.resCustomFieldId) {
      retrieveResCustomField(route.params.resCustomFieldId);
    }

    const initRelationships = () => {
      reservationService()
        .retrieve()
        .then(res => {
          reservations.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      systemName: {},
      displayOrder: {},
      name: {},
      value: {},
      reservation: {},
    };
    const v$ = useVuelidate(validationRules, resCustomField as any);
    v$.value.$validate();

    return {
      resCustomFieldService,
      alertService,
      resCustomField,
      previousState,
      isSaving,
      currentLanguage,
      reservations,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.resCustomField.id) {
        this.resCustomFieldService()
          .update(this.resCustomField)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('sevenRoomsToReviewProApplicationApp.resCustomField.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.resCustomFieldService()
          .create(this.resCustomField)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(
              this.t$('sevenRoomsToReviewProApplicationApp.resCustomField.created', { param: param.id }).toString(),
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
