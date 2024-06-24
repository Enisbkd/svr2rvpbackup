/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import ResCustomField from './res-custom-field.vue';
import ResCustomFieldService from './res-custom-field.service';
import AlertService from '@/shared/alert/alert.service';

type ResCustomFieldComponentType = InstanceType<typeof ResCustomField>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('ResCustomField Management Component', () => {
    let resCustomFieldServiceStub: SinonStubbedInstance<ResCustomFieldService>;
    let mountOptions: MountingOptions<ResCustomFieldComponentType>['global'];

    beforeEach(() => {
      resCustomFieldServiceStub = sinon.createStubInstance<ResCustomFieldService>(ResCustomFieldService);
      resCustomFieldServiceStub.retrieve.resolves({ headers: {} });

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          jhiItemCount: true,
          bPagination: true,
          bModal: bModalStub as any,
          'font-awesome-icon': true,
          'b-badge': true,
          'jhi-sort-indicator': true,
          'b-button': true,
          'router-link': true,
        },
        directives: {
          'b-modal': {},
        },
        provide: {
          alertService,
          resCustomFieldService: () => resCustomFieldServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        resCustomFieldServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(ResCustomField, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(resCustomFieldServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.resCustomFields[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for an id', async () => {
        // WHEN
        const wrapper = shallowMount(ResCustomField, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(resCustomFieldServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['id,asc'],
        });
      });
    });
    describe('Handles', () => {
      let comp: ResCustomFieldComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(ResCustomField, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        resCustomFieldServiceStub.retrieve.reset();
        resCustomFieldServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('should load a page', async () => {
        // GIVEN
        resCustomFieldServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.page = 2;
        await comp.$nextTick();

        // THEN
        expect(resCustomFieldServiceStub.retrieve.called).toBeTruthy();
        expect(comp.resCustomFields[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should not load a page if the page is the same as the previous page', () => {
        // WHEN
        comp.page = 1;

        // THEN
        expect(resCustomFieldServiceStub.retrieve.called).toBeFalsy();
      });

      it('should re-initialize the page', async () => {
        // GIVEN
        comp.page = 2;
        await comp.$nextTick();
        resCustomFieldServiceStub.retrieve.reset();
        resCustomFieldServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.clear();
        await comp.$nextTick();

        // THEN
        expect(comp.page).toEqual(1);
        expect(resCustomFieldServiceStub.retrieve.callCount).toEqual(1);
        expect(comp.resCustomFields[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for a non-id attribute', async () => {
        // WHEN
        comp.propOrder = 'name';
        await comp.$nextTick();

        // THEN
        expect(resCustomFieldServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['name,asc', 'id'],
        });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        resCustomFieldServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeResCustomField();
        await comp.$nextTick(); // clear components

        // THEN
        expect(resCustomFieldServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(resCustomFieldServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
