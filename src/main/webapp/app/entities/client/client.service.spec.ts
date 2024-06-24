/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import ClientService from './client.service';
import { Client } from '@/shared/model/client.model';

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
  describe('Client Service', () => {
    let service: ClientService;
    let elemDefault;

    beforeEach(() => {
      service = new ClientService();
      elemDefault = new Client(
        123,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        false,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
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

      it('should create a Client', async () => {
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

      it('should not create a Client', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Client', async () => {
        const returnedFromService = Object.assign(
          {
            clientId: 'BBBBBB',
            createdDate: 'BBBBBB',
            updatedDate: 'BBBBBB',
            deletedDate: 'BBBBBB',
            lastname: 'BBBBBB',
            firstname: 'BBBBBB',
            gender: 'BBBBBB',
            salutation: 'BBBBBB',
            title: 'BBBBBB',
            birthdayDay: 1,
            birthdayMonth: 1,
            birthdayAltMonth: 1,
            anniversaryDay: 1,
            anniversaryMonth: 1,
            company: 'BBBBBB',
            email: 'BBBBBB',
            emailAlt: 'BBBBBB',
            phoneNumber: 'BBBBBB',
            phoneNumberlocale: 'BBBBBB',
            phoneNumberalt: 'BBBBBB',
            phoneNumberaltlocale: 'BBBBBB',
            address: 'BBBBBB',
            address2: 'BBBBBB',
            city: 'BBBBBB',
            postalCode: 'BBBBBB',
            state: 'BBBBBB',
            country: 'BBBBBB',
            isContactPrivate: true,
            isOnetimeGuest: true,
            status: 'BBBBBB',
            loyaltyId: 'BBBBBB',
            loyaltyRank: 1,
            loyaltyTier: 'BBBBBB',
            marketingOptin: true,
            marketingOptints: 'BBBBBB',
            marketingOptOutts: 'BBBBBB',
            hasBillingProfile: true,
            notes: 'BBBBBB',
            privateNotes: 'BBBBBB',
            tags: 'BBBBBB',
            totalVisits: 1,
            totalCovers: 1,
            totalCancellations: 1,
            totalNoShows: 1,
            totalSpend: 1,
            totalSpendPerCover: 1,
            totalspendPerVisit: 1,
            avgRating: 1,
            referenceCode: 'BBBBBB',
            externalUserId: 'BBBBBB',
            venueGroupId: 'BBBBBB',
            birthdayAltDay: 1,
            userId: 'BBBBBB',
            userName: 'BBBBBB',
            totalOrderCount: 1,
            preferredLanguageCode: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Client', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Client', async () => {
        const patchObject = Object.assign(
          {
            createdDate: 'BBBBBB',
            deletedDate: 'BBBBBB',
            gender: 'BBBBBB',
            salutation: 'BBBBBB',
            title: 'BBBBBB',
            birthdayDay: 1,
            birthdayMonth: 1,
            birthdayAltMonth: 1,
            anniversaryDay: 1,
            anniversaryMonth: 1,
            company: 'BBBBBB',
            email: 'BBBBBB',
            phoneNumber: 'BBBBBB',
            phoneNumberlocale: 'BBBBBB',
            phoneNumberalt: 'BBBBBB',
            address: 'BBBBBB',
            city: 'BBBBBB',
            postalCode: 'BBBBBB',
            state: 'BBBBBB',
            isContactPrivate: true,
            isOnetimeGuest: true,
            loyaltyRank: 1,
            loyaltyTier: 'BBBBBB',
            marketingOptin: true,
            marketingOptOutts: 'BBBBBB',
            hasBillingProfile: true,
            privateNotes: 'BBBBBB',
            totalVisits: 1,
            totalNoShows: 1,
            referenceCode: 'BBBBBB',
            birthdayAltDay: 1,
            userName: 'BBBBBB',
            preferredLanguageCode: 'BBBBBB',
          },
          new Client(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Client', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Client', async () => {
        const returnedFromService = Object.assign(
          {
            clientId: 'BBBBBB',
            createdDate: 'BBBBBB',
            updatedDate: 'BBBBBB',
            deletedDate: 'BBBBBB',
            lastname: 'BBBBBB',
            firstname: 'BBBBBB',
            gender: 'BBBBBB',
            salutation: 'BBBBBB',
            title: 'BBBBBB',
            birthdayDay: 1,
            birthdayMonth: 1,
            birthdayAltMonth: 1,
            anniversaryDay: 1,
            anniversaryMonth: 1,
            company: 'BBBBBB',
            email: 'BBBBBB',
            emailAlt: 'BBBBBB',
            phoneNumber: 'BBBBBB',
            phoneNumberlocale: 'BBBBBB',
            phoneNumberalt: 'BBBBBB',
            phoneNumberaltlocale: 'BBBBBB',
            address: 'BBBBBB',
            address2: 'BBBBBB',
            city: 'BBBBBB',
            postalCode: 'BBBBBB',
            state: 'BBBBBB',
            country: 'BBBBBB',
            isContactPrivate: true,
            isOnetimeGuest: true,
            status: 'BBBBBB',
            loyaltyId: 'BBBBBB',
            loyaltyRank: 1,
            loyaltyTier: 'BBBBBB',
            marketingOptin: true,
            marketingOptints: 'BBBBBB',
            marketingOptOutts: 'BBBBBB',
            hasBillingProfile: true,
            notes: 'BBBBBB',
            privateNotes: 'BBBBBB',
            tags: 'BBBBBB',
            totalVisits: 1,
            totalCovers: 1,
            totalCancellations: 1,
            totalNoShows: 1,
            totalSpend: 1,
            totalSpendPerCover: 1,
            totalspendPerVisit: 1,
            avgRating: 1,
            referenceCode: 'BBBBBB',
            externalUserId: 'BBBBBB',
            venueGroupId: 'BBBBBB',
            birthdayAltDay: 1,
            userId: 'BBBBBB',
            userName: 'BBBBBB',
            totalOrderCount: 1,
            preferredLanguageCode: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Client', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Client', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Client', async () => {
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
