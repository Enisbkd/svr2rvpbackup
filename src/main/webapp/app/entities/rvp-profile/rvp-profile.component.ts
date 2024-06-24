import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import RvpProfileService from './rvp-profile.service';
import { type IRvpProfile } from '@/shared/model/rvp-profile.model';
import { useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RvpProfile',
  setup() {
    const { t: t$ } = useI18n();
    const dateFormat = useDateFormat();
    const rvpProfileService = inject('rvpProfileService', () => new RvpProfileService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const rvpProfiles: Ref<IRvpProfile[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveRvpProfiles = async () => {
      isFetching.value = true;
      try {
        const res = await rvpProfileService().retrieve();
        rvpProfiles.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveRvpProfiles();
    };

    onMounted(async () => {
      await retrieveRvpProfiles();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IRvpProfile) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeRvpProfile = async () => {
      try {
        await rvpProfileService().delete(removeId.value);
        const message = t$('sevenRoomsToReviewProApplicationApp.rvpProfile.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveRvpProfiles();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      rvpProfiles,
      handleSyncList,
      isFetching,
      retrieveRvpProfiles,
      clear,
      ...dateFormat,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeRvpProfile,
      t$,
    };
  },
});
