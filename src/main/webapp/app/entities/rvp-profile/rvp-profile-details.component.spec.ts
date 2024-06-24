/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RvpProfileDetails from './rvp-profile-details.vue';
import RvpProfileService from './rvp-profile.service';
import AlertService from '@/shared/alert/alert.service';

type RvpProfileDetailsComponentType = InstanceType<typeof RvpProfileDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const rvpProfileSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('RvpProfile Management Detail Component', () => {
    let rvpProfileServiceStub: SinonStubbedInstance<RvpProfileService>;
    let mountOptions: MountingOptions<RvpProfileDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      rvpProfileServiceStub = sinon.createStubInstance<RvpProfileService>(RvpProfileService);

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
          rvpProfileService: () => rvpProfileServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        rvpProfileServiceStub.find.resolves(rvpProfileSample);
        route = {
          params: {
            rvpProfileId: '' + 123,
          },
        };
        const wrapper = shallowMount(RvpProfileDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.rvpProfile).toMatchObject(rvpProfileSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        rvpProfileServiceStub.find.resolves(rvpProfileSample);
        const wrapper = shallowMount(RvpProfileDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
