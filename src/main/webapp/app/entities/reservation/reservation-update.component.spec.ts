/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ReservationUpdate from './reservation-update.vue';
import ReservationService from './reservation.service';
import AlertService from '@/shared/alert/alert.service';

type ReservationUpdateComponentType = InstanceType<typeof ReservationUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const reservationSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ReservationUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Reservation Management Update Component', () => {
    let comp: ReservationUpdateComponentType;
    let reservationServiceStub: SinonStubbedInstance<ReservationService>;

    beforeEach(() => {
      route = {};
      reservationServiceStub = sinon.createStubInstance<ReservationService>(ReservationService);
      reservationServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          reservationService: () => reservationServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(ReservationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.reservation = reservationSample;
        reservationServiceStub.update.resolves(reservationSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(reservationServiceStub.update.calledWith(reservationSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        reservationServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ReservationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.reservation = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(reservationServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        reservationServiceStub.find.resolves(reservationSample);
        reservationServiceStub.retrieve.resolves([reservationSample]);

        // WHEN
        route = {
          params: {
            reservationId: '' + reservationSample.id,
          },
        };
        const wrapper = shallowMount(ReservationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.reservation).toMatchObject(reservationSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        reservationServiceStub.find.resolves(reservationSample);
        const wrapper = shallowMount(ReservationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
