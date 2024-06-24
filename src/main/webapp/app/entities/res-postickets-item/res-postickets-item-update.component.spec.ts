/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ResPosticketsItemUpdate from './res-postickets-item-update.vue';
import ResPosticketsItemService from './res-postickets-item.service';
import AlertService from '@/shared/alert/alert.service';

import ResPosTicketService from '@/entities/res-pos-ticket/res-pos-ticket.service';

type ResPosticketsItemUpdateComponentType = InstanceType<typeof ResPosticketsItemUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const resPosticketsItemSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ResPosticketsItemUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ResPosticketsItem Management Update Component', () => {
    let comp: ResPosticketsItemUpdateComponentType;
    let resPosticketsItemServiceStub: SinonStubbedInstance<ResPosticketsItemService>;

    beforeEach(() => {
      route = {};
      resPosticketsItemServiceStub = sinon.createStubInstance<ResPosticketsItemService>(ResPosticketsItemService);
      resPosticketsItemServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          resPosticketsItemService: () => resPosticketsItemServiceStub,
          resPosTicketService: () =>
            sinon.createStubInstance<ResPosTicketService>(ResPosTicketService, {
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
        const wrapper = shallowMount(ResPosticketsItemUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.resPosticketsItem = resPosticketsItemSample;
        resPosticketsItemServiceStub.update.resolves(resPosticketsItemSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(resPosticketsItemServiceStub.update.calledWith(resPosticketsItemSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        resPosticketsItemServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ResPosticketsItemUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.resPosticketsItem = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(resPosticketsItemServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        resPosticketsItemServiceStub.find.resolves(resPosticketsItemSample);
        resPosticketsItemServiceStub.retrieve.resolves([resPosticketsItemSample]);

        // WHEN
        route = {
          params: {
            resPosticketsItemId: '' + resPosticketsItemSample.id,
          },
        };
        const wrapper = shallowMount(ResPosticketsItemUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.resPosticketsItem).toMatchObject(resPosticketsItemSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        resPosticketsItemServiceStub.find.resolves(resPosticketsItemSample);
        const wrapper = shallowMount(ResPosticketsItemUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
