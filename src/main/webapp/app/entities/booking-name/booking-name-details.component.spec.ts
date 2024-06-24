/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import BookingNameDetails from './booking-name-details.vue';
import BookingNameService from './booking-name.service';
import AlertService from '@/shared/alert/alert.service';

type BookingNameDetailsComponentType = InstanceType<typeof BookingNameDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const bookingNameSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('BookingName Management Detail Component', () => {
    let bookingNameServiceStub: SinonStubbedInstance<BookingNameService>;
    let mountOptions: MountingOptions<BookingNameDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      bookingNameServiceStub = sinon.createStubInstance<BookingNameService>(BookingNameService);

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'router-link': true,
        },
        provide: {
          alertService,
          bookingNameService: () => bookingNameServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        bookingNameServiceStub.find.resolves(bookingNameSample);
        route = {
          params: {
            bookingNameId: '' + 123,
          },
        };
        const wrapper = shallowMount(BookingNameDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.bookingName).toMatchObject(bookingNameSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        bookingNameServiceStub.find.resolves(bookingNameSample);
        const wrapper = shallowMount(BookingNameDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
