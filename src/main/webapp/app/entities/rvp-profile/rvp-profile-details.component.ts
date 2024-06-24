import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import RvpProfileService from './rvp-profile.service';
import { useDateFormat } from '@/shared/composables';
import { type IRvpProfile } from '@/shared/model/rvp-profile.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RvpProfileDetails',
  setup() {
    const dateFormat = useDateFormat();
    const rvpProfileService = inject('rvpProfileService', () => new RvpProfileService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const rvpProfile: Ref<IRvpProfile> = ref({});

    const retrieveRvpProfile = async rvpProfileId => {
      try {
        const res = await rvpProfileService().find(rvpProfileId);
        rvpProfile.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.rvpProfileId) {
      retrieveRvpProfile(route.params.rvpProfileId);
    }

    return {
      ...dateFormat,
      alertService,
      rvpProfile,

      previousState,
      t$: useI18n().t,
    };
  },
});
