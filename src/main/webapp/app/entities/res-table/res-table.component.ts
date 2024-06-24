import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ResTableService from './res-table.service';
import { type IResTable } from '@/shared/model/res-table.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ResTable',
  setup() {
    const { t: t$ } = useI18n();
    const resTableService = inject('resTableService', () => new ResTableService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const resTables: Ref<IResTable[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveResTables = async () => {
      isFetching.value = true;
      try {
        const res = await resTableService().retrieve();
        resTables.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveResTables();
    };

    onMounted(async () => {
      await retrieveResTables();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IResTable) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeResTable = async () => {
      try {
        await resTableService().delete(removeId.value);
        const message = t$('sevenRoomsToReviewProApplicationApp.resTable.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveResTables();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      resTables,
      handleSyncList,
      isFetching,
      retrieveResTables,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeResTable,
      t$,
    };
  },
});
