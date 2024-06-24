/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ResTableDetails from './res-table-details.vue';
import ResTableService from './res-table.service';
import AlertService from '@/shared/alert/alert.service';

type ResTableDetailsComponentType = InstanceType<typeof ResTableDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const resTableSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ResTable Management Detail Component', () => {
    let resTableServiceStub: SinonStubbedInstance<ResTableService>;
    let mountOptions: MountingOptions<ResTableDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      resTableServiceStub = sinon.createStubInstance<ResTableService>(ResTableService);

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
          resTableService: () => resTableServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        resTableServiceStub.find.resolves(resTableSample);
        route = {
          params: {
            resTableId: '' + 123,
          },
        };
        const wrapper = shallowMount(ResTableDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.resTable).toMatchObject(resTableSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        resTableServiceStub.find.resolves(resTableSample);
        const wrapper = shallowMount(ResTableDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
