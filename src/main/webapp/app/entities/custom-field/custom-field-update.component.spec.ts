/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import CustomFieldUpdate from './custom-field-update.vue';
import CustomFieldService from './custom-field.service';
import AlertService from '@/shared/alert/alert.service';

import ClientService from '@/entities/client/client.service';

type CustomFieldUpdateComponentType = InstanceType<typeof CustomFieldUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const customFieldSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<CustomFieldUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('CustomField Management Update Component', () => {
    let comp: CustomFieldUpdateComponentType;
    let customFieldServiceStub: SinonStubbedInstance<CustomFieldService>;

    beforeEach(() => {
      route = {};
      customFieldServiceStub = sinon.createStubInstance<CustomFieldService>(CustomFieldService);
      customFieldServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          customFieldService: () => customFieldServiceStub,
          clientService: () =>
            sinon.createStubInstance<ClientService>(ClientService, {
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
        const wrapper = shallowMount(CustomFieldUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.customField = customFieldSample;
        customFieldServiceStub.update.resolves(customFieldSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(customFieldServiceStub.update.calledWith(customFieldSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        customFieldServiceStub.create.resolves(entity);
        const wrapper = shallowMount(CustomFieldUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.customField = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(customFieldServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        customFieldServiceStub.find.resolves(customFieldSample);
        customFieldServiceStub.retrieve.resolves([customFieldSample]);

        // WHEN
        route = {
          params: {
            customFieldId: '' + customFieldSample.id,
          },
        };
        const wrapper = shallowMount(CustomFieldUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.customField).toMatchObject(customFieldSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        customFieldServiceStub.find.resolves(customFieldSample);
        const wrapper = shallowMount(CustomFieldUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
