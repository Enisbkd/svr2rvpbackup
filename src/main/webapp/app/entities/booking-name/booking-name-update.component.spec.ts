/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import BookingNameUpdate from './booking-name-update.vue';
import BookingNameService from './booking-name.service';
import AlertService from '@/shared/alert/alert.service';

import ClientVenueStatsService from '@/entities/client-venue-stats/client-venue-stats.service';

type BookingNameUpdateComponentType = InstanceType<typeof BookingNameUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const bookingNameSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<BookingNameUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('BookingName Management Update Component', () => {
    let comp: BookingNameUpdateComponentType;
    let bookingNameServiceStub: SinonStubbedInstance<BookingNameService>;

    beforeEach(() => {
      route = {};
      bookingNameServiceStub = sinon.createStubInstance<BookingNameService>(BookingNameService);
      bookingNameServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'b-input-group': true,
          'b-input-group-prepend': true,
          'b-form-datepicker': true,
          'b-form-input': true,
        },
        provide: {
          alertService,
          bookingNameService: () => bookingNameServiceStub,
          clientVenueStatsService: () =>
            sinon.createStubInstance<ClientVenueStatsService>(ClientVenueStatsService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(BookingNameUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.bookingName = bookingNameSample;
        bookingNameServiceStub.update.resolves(bookingNameSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(bookingNameServiceStub.update.calledWith(bookingNameSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        bookingNameServiceStub.create.resolves(entity);
        const wrapper = shallowMount(BookingNameUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.bookingName = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(bookingNameServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        bookingNameServiceStub.find.resolves(bookingNameSample);
        bookingNameServiceStub.retrieve.resolves([bookingNameSample]);

        // WHEN
        route = {
          params: {
            bookingNameId: '' + bookingNameSample.id,
          },
        };
        const wrapper = shallowMount(BookingNameUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.bookingName).toMatchObject(bookingNameSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        bookingNameServiceStub.find.resolves(bookingNameSample);
        const wrapper = shallowMount(BookingNameUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
