import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import RvpProfileService from './rvp-profile.service';
import { useValidation, useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IRvpProfile, RvpProfile } from '@/shared/model/rvp-profile.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RvpProfileUpdate',
  setup() {
    const rvpProfileService = inject('rvpProfileService', () => new RvpProfileService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const rvpProfile: Ref<IRvpProfile> = ref(new RvpProfile());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveRvpProfile = async rvpProfileId => {
      try {
        const res = await rvpProfileService().find(rvpProfileId);
        res.checkin = new Date(res.checkin);
        res.checkout = new Date(res.checkout);
        rvpProfile.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.rvpProfileId) {
      retrieveRvpProfile(route.params.rvpProfileId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      pmsId: {},
      firstName: {},
      lastName: {},
      language: {},
      checkin: {},
      checkout: {},
      email: {},
    };
    const v$ = useVuelidate(validationRules, rvpProfile as any);
    v$.value.$validate();

    return {
      rvpProfileService,
      alertService,
      rvpProfile,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      ...useDateFormat({ entityRef: rvpProfile }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.rvpProfile.id) {
        this.rvpProfileService()
          .update(this.rvpProfile)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('sevenRoomsToReviewProApplicationApp.rvpProfile.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.rvpProfileService()
          .create(this.rvpProfile)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(
              this.t$('sevenRoomsToReviewProApplicationApp.rvpProfile.created', { param: param.id }).toString(),
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
