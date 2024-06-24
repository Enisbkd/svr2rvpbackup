/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import ClientVenueStatsService from './client-venue-stats.service';
import { ClientVenueStats } from '@/shared/model/client-venue-stats.model';

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
  describe('ClientVenueStats Service', () => {
    let service: ClientVenueStatsService;
    let elemDefault;

    beforeEach(() => {
      service = new ClientVenueStatsService();
      elemDefault = new ClientVenueStats(
        123,
        0,
        'AAAAAAA',
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        false,
        'AAAAAAA',
      );
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

      it('should create a ClientVenueStats', async () => {
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

      it('should not create a ClientVenueStats', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a ClientVenueStats', async () => {
        const returnedFromService = Object.assign(
          {
            totalSpendLocalperCover: 1,
            lastVisitDate: 'BBBBBB',
            totalCancellations: 1,
            totalCovers: 1,
            avgRating: 1,
            totalSpendperCover: 1,
            totalSpend: 1,
            totalNoShows: 1,
            numRatings: 1,
            totalSpendPerVisit: 1,
            totalSpendLocal: 1,
            totalSpendLocalPerVisit: 1,
            totalVisits: 1,
            grossTotal: 1,
            totalOrderCount: 1,
            totalOrderCancellations: 1,
            totalOrderSpend: 1,
            grossOrderTotal: 1,
            totalOrderSpendLocal: 1,
            lastOrderDate: 'BBBBBB',
            totalSpendperOrder: 1,
            totalSpendLocalperOrder: 1,
            venueId: 'BBBBBB',
            venueMarketingOptin: true,
            venueMarketingOptints: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a ClientVenueStats', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a ClientVenueStats', async () => {
        const patchObject = Object.assign(
          {
            totalSpendLocalperCover: 1,
            totalCancellations: 1,
            totalCovers: 1,
            avgRating: 1,
            totalSpend: 1,
            totalNoShows: 1,
            numRatings: 1,
            totalSpendPerVisit: 1,
            totalSpendLocalPerVisit: 1,
            grossTotal: 1,
            totalOrderCount: 1,
            totalOrderSpendLocal: 1,
            totalSpendLocalperOrder: 1,
            venueMarketingOptin: true,
            venueMarketingOptints: 'BBBBBB',
          },
          new ClientVenueStats(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a ClientVenueStats', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of ClientVenueStats', async () => {
        const returnedFromService = Object.assign(
          {
            totalSpendLocalperCover: 1,
            lastVisitDate: 'BBBBBB',
            totalCancellations: 1,
            totalCovers: 1,
            avgRating: 1,
            totalSpendperCover: 1,
            totalSpend: 1,
            totalNoShows: 1,
            numRatings: 1,
            totalSpendPerVisit: 1,
            totalSpendLocal: 1,
            totalSpendLocalPerVisit: 1,
            totalVisits: 1,
            grossTotal: 1,
            totalOrderCount: 1,
            totalOrderCancellations: 1,
            totalOrderSpend: 1,
            grossOrderTotal: 1,
            totalOrderSpendLocal: 1,
            lastOrderDate: 'BBBBBB',
            totalSpendperOrder: 1,
            totalSpendLocalperOrder: 1,
            venueId: 'BBBBBB',
            venueMarketingOptin: true,
            venueMarketingOptints: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of ClientVenueStats', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a ClientVenueStats', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a ClientVenueStats', async () => {
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
