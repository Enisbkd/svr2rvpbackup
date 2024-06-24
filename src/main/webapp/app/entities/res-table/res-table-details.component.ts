import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ResTableService from './res-table.service';
import { type IResTable } from '@/shared/model/res-table.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ResTableDetails',
  setup() {
    const resTableService = inject('resTableService', () => new ResTableService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const resTable: Ref<IResTable> = ref({});

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

    return {
      alertService,
      resTable,

      previousState,
      t$: useI18n().t,
    };
  },
});
