/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import ResPosTicketService from './res-pos-ticket.service';
import { ResPosTicket } from '@/shared/model/res-pos-ticket.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('ResPosTicket Service', () => {
    let service: ResPosTicketService;
    let elemDefault;

    beforeEach(() => {
      service = new ResPosTicketService();
      elemDefault = new ResPosTicket(123, 'AAAAAAA', 0, 0, 'AAAAAAA', 0, 0, 0, 'AAAAAAA', 'AAAAAAA', 0, 0, 'AAAAAAA', 0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a ResPosTicket', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a ResPosTicket', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a ResPosTicket', async () => {
        const returnedFromService = Object.assign(
          {
            status: 'BBBBBB',
            adminFee: 1,
            code: 1,
            tableNo: 'BBBBBB',
            tax: 1,
            businessId: 1,
            ticketId: 1,
            localPosticketId: 'BBBBBB',
            employeeName: 'BBBBBB',
            total: 1,
            subtotal: 1,
            startTime: 'BBBBBB',
            serviceCharge: 1,
            endtime: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a ResPosTicket', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a ResPosTicket', async () => {
        const patchObject = Object.assign(
          {
            status: 'BBBBBB',
            tax: 1,
            businessId: 1,
            ticketId: 1,
            subtotal: 1,
          },
          new ResPosTicket(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a ResPosTicket', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of ResPosTicket', async () => {
        const returnedFromService = Object.assign(
          {
            status: 'BBBBBB',
            adminFee: 1,
            code: 1,
            tableNo: 'BBBBBB',
            tax: 1,
            businessId: 1,
            ticketId: 1,
            localPosticketId: 'BBBBBB',
            employeeName: 'BBBBBB',
            total: 1,
            subtotal: 1,
            startTime: 'BBBBBB',
            serviceCharge: 1,
            endtime: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of ResPosTicket', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a ResPosTicket', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a ResPosTicket', async () => {
        axiosStub.delete.rejects(error);

        return service
          .delete(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
