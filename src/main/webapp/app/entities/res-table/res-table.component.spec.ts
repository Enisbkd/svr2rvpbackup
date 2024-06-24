/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import ResTable from './res-table.vue';
import ResTableService from './res-table.service';
import AlertService from '@/shared/alert/alert.service';

type ResTableComponentType = InstanceType<typeof ResTable>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('ResTable Management Component', () => {
    let resTableServiceStub: SinonStubbedInstance<ResTableService>;
    let mountOptions: MountingOptions<ResTableComponentType>['global'];

    beforeEach(() => {
      resTableServiceStub = sinon.createStubInstance<ResTableService>(ResTableService);
      resTableServiceStub.retrieve.resolves({ headers: {} });

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
          resTableService: () => resTableServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        resTableServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(ResTable, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(resTableServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.resTables[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ResTableComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(ResTable, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        resTableServiceStub.retrieve.reset();
        resTableServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        resTableServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeResTable();
        await comp.$nextTick(); // clear components

        // THEN
        expect(resTableServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(resTableServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
