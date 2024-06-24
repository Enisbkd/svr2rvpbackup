import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ResPosticketsItemService from './res-postickets-item.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ResPosTicketService from '@/entities/res-pos-ticket/res-pos-ticket.service';
import { type IResPosTicket } from '@/shared/model/res-pos-ticket.model';
import { type IResPosticketsItem, ResPosticketsItem } from '@/shared/model/res-postickets-item.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ResPosticketsItemUpdate',
  setup() {
    const resPosticketsItemService = inject('resPosticketsItemService', () => new ResPosticketsItemService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const resPosticketsItem: Ref<IResPosticketsItem> = ref(new ResPosticketsItem());

    const resPosTicketService = inject('resPosTicketService', () => new ResPosTicketService());

    const resPosTickets: Ref<IResPosTicket[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveResPosticketsItem = async resPosticketsItemId => {
      try {
        const res = await resPosticketsItemService().find(resPosticketsItemId);
        resPosticketsItem.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.resPosticketsItemId) {
      retrieveResPosticketsItem(route.params.resPosticketsItemId);
    }

    const initRelationships = () => {
      resPosTicketService()
        .retrieve()
        .then(res => {
          resPosTickets.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      price: {},
      name: {},
      quantity: {},
      resPosTicket: {},
    };
    const v$ = useVuelidate(validationRules, resPosticketsItem as any);
    v$.value.$validate();

    return {
      resPosticketsItemService,
      alertService,
      resPosticketsItem,
      previousState,
      isSaving,
      currentLanguage,
      resPosTickets,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.resPosticketsItem.id) {
        this.resPosticketsItemService()
          .update(this.resPosticketsItem)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('sevenRoomsToReviewProApplicationApp.resPosticketsItem.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.resPosticketsItemService()
          .create(this.resPosticketsItem)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(
              this.t$('sevenRoomsToReviewProApplicationApp.resPosticketsItem.created', { param: param.id }).toString(),
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
