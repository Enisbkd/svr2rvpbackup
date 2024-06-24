import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ResTableService from './res-table.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ReservationService from '@/entities/reservation/reservation.service';
import { type IReservation } from '@/shared/model/reservation.model';
import { type IResTable, ResTable } from '@/shared/model/res-table.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ResTableUpdate',
  setup() {
    const resTableService = inject('resTableService', () => new ResTableService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const resTable: Ref<IResTable> = ref(new ResTable());

    const reservationService = inject('reservationService', () => new ReservationService());

    const reservations: Ref<IReservation[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveResTable = async resTableId => {
      try {
        const res = await resTableService().find(resTableId);
        resTable.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.resTableId) {
      retrieveResTable(route.params.resTableId);
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
      tableNumber: {},
      reservation: {},
    };
    const v$ = useVuelidate(validationRules, resTable as any);
    v$.value.$validate();

    return {
      resTableService,
      alertService,
      resTable,
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
      if (this.resTable.id) {
        this.resTableService()
          .update(this.resTable)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('sevenRoomsToReviewProApplicationApp.resTable.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.resTableService()
          .create(this.resTable)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('sevenRoomsToReviewProApplicationApp.resTable.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
