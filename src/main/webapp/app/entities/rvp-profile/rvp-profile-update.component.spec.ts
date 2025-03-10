/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import dayjs from 'dayjs';
import RvpProfileUpdate from './rvp-profile-update.vue';
import RvpProfileService from './rvp-profile.service';
import { DATE_TIME_LONG_FORMAT } from '@/shared/composables/date-format';
import AlertService from '@/shared/alert/alert.service';

type RvpProfileUpdateComponentType = InstanceType<typeof RvpProfileUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const rvpProfileSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<RvpProfileUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('RvpProfile Management Update Component', () => {
    let comp: RvpProfileUpdateComponentType;
    let rvpProfileServiceStub: SinonStubbedInstance<RvpProfileService>;

    beforeEach(() => {
      route = {};
      rvpProfileServiceStub = sinon.createStubInstance<RvpProfileService>(RvpProfileService);
      rvpProfileServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          rvpProfileService: () => rvpProfileServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('load', () => {
      beforeEach(() => {
        const wrapper = shallowMount(RvpProfileUpdate, { global: mountOptions });
        comp = wrapper.vm;
      });
      it('Should convert date from string', () => {
        // GIVEN
        const date = new Date('2019-10-15T11:42:02Z');

        // WHEN
        const convertedDate = comp.convertDateTimeFromServer(date);

        // THEN
        expect(convertedDate).toEqual(dayjs(date).format(DATE_TIME_LONG_FORMAT));
      });

      it('Should not convert date if date is not present', () => {
        expect(comp.convertDateTimeFromServer(null)).toBeNull();
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(RvpProfileUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.rvpProfile = rvpProfileSample;
        rvpProfileServiceStub.update.resolves(rvpProfileSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(rvpProfileServiceStub.update.calledWith(rvpProfileSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        rvpProfileServiceStub.create.resolves(entity);
        const wrapper = shallowMount(RvpProfileUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.rvpProfile = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(rvpProfileServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        rvpProfileServiceStub.find.resolves(rvpProfileSample);
        rvpProfileServiceStub.retrieve.resolves([rvpProfileSample]);

        // WHEN
        route = {
          params: {
            rvpProfileId: '' + rvpProfileSample.id,
          },
        };
        const wrapper = shallowMount(RvpProfileUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.rvpProfile).toMatchObject(rvpProfileSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        rvpProfileServiceStub.find.resolves(rvpProfileSample);
        const wrapper = shallowMount(RvpProfileUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
