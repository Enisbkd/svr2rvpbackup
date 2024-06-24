/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ResPosTicketUpdate from './res-pos-ticket-update.vue';
import ResPosTicketService from './res-pos-ticket.service';
import AlertService from '@/shared/alert/alert.service';

import ReservationService from '@/entities/reservation/reservation.service';

type ResPosTicketUpdateComponentType = InstanceType<typeof ResPosTicketUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const resPosTicketSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ResPosTicketUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ResPosTicket Management Update Component', () => {
    let comp: ResPosTicketUpdateComponentType;
    let resPosTicketServiceStub: SinonStubbedInstance<ResPosTicketService>;

    beforeEach(() => {
      route = {};
      resPosTicketServiceStub = sinon.createStubInstance<ResPosTicketService>(ResPosTicketService);
      resPosTicketServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          resPosTicketService: () => resPosTicketServiceStub,
          reservationService: () =>
            sinon.createStubInstance<ReservationService>(ReservationService, {
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
        const wrapper = shallowMount(ResPosTicketUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.resPosTicket = resPosTicketSample;
        resPosTicketServiceStub.update.resolves(resPosTicketSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(resPosTicketServiceStub.update.calledWith(resPosTicketSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        resPosTicketServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ResPosTicketUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.resPosTicket = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(resPosTicketServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        resPosTicketServiceStub.find.resolves(resPosTicketSample);
        resPosTicketServiceStub.retrieve.resolves([resPosTicketSample]);

        // WHEN
        route = {
          params: {
            resPosTicketId: '' + resPosTicketSample.id,
          },
        };
        const wrapper = shallowMount(ResPosTicketUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.resPosTicket).toMatchObject(resPosTicketSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        resPosTicketServiceStub.find.resolves(resPosTicketSample);
        const wrapper = shallowMount(ResPosTicketUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
