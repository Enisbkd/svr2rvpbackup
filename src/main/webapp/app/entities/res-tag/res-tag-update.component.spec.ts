/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ResTagUpdate from './res-tag-update.vue';
import ResTagService from './res-tag.service';
import AlertService from '@/shared/alert/alert.service';

import ReservationService from '@/entities/reservation/reservation.service';

type ResTagUpdateComponentType = InstanceType<typeof ResTagUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const resTagSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ResTagUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ResTag Management Update Component', () => {
    let comp: ResTagUpdateComponentType;
    let resTagServiceStub: SinonStubbedInstance<ResTagService>;

    beforeEach(() => {
      route = {};
      resTagServiceStub = sinon.createStubInstance<ResTagService>(ResTagService);
      resTagServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          resTagService: () => resTagServiceStub,
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
        const wrapper = shallowMount(ResTagUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.resTag = resTagSample;
        resTagServiceStub.update.resolves(resTagSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(resTagServiceStub.update.calledWith(resTagSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        resTagServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ResTagUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.resTag = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(resTagServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        resTagServiceStub.find.resolves(resTagSample);
        resTagServiceStub.retrieve.resolves([resTagSample]);

        // WHEN
        route = {
          params: {
            resTagId: '' + resTagSample.id,
          },
        };
        const wrapper = shallowMount(ResTagUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.resTag).toMatchObject(resTagSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        resTagServiceStub.find.resolves(resTagSample);
        const wrapper = shallowMount(ResTagUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
