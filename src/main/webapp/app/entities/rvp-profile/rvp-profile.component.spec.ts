/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import RvpProfile from './rvp-profile.vue';
import RvpProfileService from './rvp-profile.service';
import AlertService from '@/shared/alert/alert.service';

type RvpProfileComponentType = InstanceType<typeof RvpProfile>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('RvpProfile Management Component', () => {
    let rvpProfileServiceStub: SinonStubbedInstance<RvpProfileService>;
    let mountOptions: MountingOptions<RvpProfileComponentType>['global'];

    beforeEach(() => {
      rvpProfileServiceStub = sinon.createStubInstance<RvpProfileService>(RvpProfileService);
      rvpProfileServiceStub.retrieve.resolves({ headers: {} });

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          bModal: bModalStub as any,
          'font-awesome-icon': true,
          'b-badge': true,
          'b-button': true,
          'router-link': true,
        },
        directives: {
          'b-modal': {},
        },
        provide: {
          alertService,
          rvpProfileService: () => rvpProfileServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        rvpProfileServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(RvpProfile, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(rvpProfileServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.rvpProfiles[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: RvpProfileComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(RvpProfile, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        rvpProfileServiceStub.retrieve.reset();
        rvpProfileServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        rvpProfileServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeRvpProfile();
        await comp.$nextTick(); // clear components

        // THEN
        expect(rvpProfileServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(rvpProfileServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
