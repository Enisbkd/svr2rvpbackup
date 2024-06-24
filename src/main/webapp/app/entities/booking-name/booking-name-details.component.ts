import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import BookingNameService from './booking-name.service';
import { type IBookingName } from '@/shared/model/booking-name.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'BookingNameDetails',
  setup() {
    const bookingNameService = inject('bookingNameService', () => new BookingNameService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const bookingName: Ref<IBookingName> = ref({});

    const retrieveBookingName = async bookingNameId => {
      try {
        const res = await bookingNameService().find(bookingNameId);
        bookingName.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.bookingNameId) {
      retrieveBookingName(route.params.bookingNameId);
    }

    return {
      alertService,
      bookingName,

      previousState,
      t$: useI18n().t,
    };
  },
});
