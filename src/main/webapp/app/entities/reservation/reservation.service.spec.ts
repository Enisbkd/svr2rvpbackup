/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import ReservationService from './reservation.service';
import { Reservation } from '@/shared/model/reservation.model';

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
  describe('Reservation Service', () => {
    let service: ReservationService;
    let elemDefault;

    beforeEach(() => {
      service = new ReservationService();
      elemDefault = new Reservation(
        123,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        false,
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
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        false,
        'AAAAAAA',
        'AAAAAAA',
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

      it('should create a Reservation', async () => {
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

      it('should not create a Reservation', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Reservation', async () => {
        const returnedFromService = Object.assign(
          {
            resvId: 'BBBBBB',
            created: 'BBBBBB',
            updated: 'BBBBBB',
            deleted: 'BBBBBB',
            venueGroupClientId: 'BBBBBB',
            venueGroupId: 'BBBBBB',
            venueId: 'BBBBBB',
            date: 'BBBBBB',
            duration: 1,
            checkNumbers: 'BBBBBB',
            shiftCategory: 'BBBBBB',
            shiftPersistentId: 'BBBBBB',
            maxGuests: 1,
            mfratioMale: 1,
            mfratioFemale: 1,
            status: 'BBBBBB',
            statusDisplay: 'BBBBBB',
            statusSimple: 'BBBBBB',
            accessPersistentId: 'BBBBBB',
            arrivedGuests: 1,
            isvip: true,
            bookedby: 'BBBBBB',
            clientReferenceCode: 'BBBBBB',
            lastname: 'BBBBBB',
            firstname: 'BBBBBB',
            email: 'BBBBBB',
            phoneNumber: 'BBBBBB',
            address: 'BBBBBB',
            address2: 'BBBBBB',
            city: 'BBBBBB',
            postalCode: 'BBBBBB',
            state: 'BBBBBB',
            country: 'BBBBBB',
            loyaltyId: 'BBBBBB',
            loyaltyRank: 1,
            loyaltyTier: 'BBBBBB',
            notes: 'BBBBBB',
            arrivalTime: 'BBBBBB',
            seatedTime: 'BBBBBB',
            leftTime: 'BBBBBB',
            clientRequests: 'BBBBBB',
            comps: 1,
            compsPriceType: 'BBBBBB',
            costOption: 1,
            policy: 'BBBBBB',
            minPrice: 1,
            prePayment: 1,
            onsitePayment: 1,
            totalPayment: 1,
            paidBy: 'BBBBBB',
            servedBy: 'BBBBBB',
            rating: 1,
            problems: 'BBBBBB',
            autoAssignments: 'BBBBBB',
            externalClientId: 'BBBBBB',
            externalId: 'BBBBBB',
            externalReferenceCode: 'BBBBBB',
            externalUserId: 'BBBBBB',
            modifyReservationLink: 'BBBBBB',
            referenceCode: 'BBBBBB',
            reservationSmsOptin: true,
            reservationType: 'BBBBBB',
            sendReminderEmail: true,
            sendreminderSms: true,
            sourceClientId: 'BBBBBB',
            userId: 'BBBBBB',
            userName: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Reservation', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Reservation', async () => {
        const patchObject = Object.assign(
          {
            updated: 'BBBBBB',
            deleted: 'BBBBBB',
            venueGroupId: 'BBBBBB',
            date: 'BBBBBB',
            duration: 1,
            shiftPersistentId: 'BBBBBB',
            maxGuests: 1,
            mfratioMale: 1,
            statusDisplay: 'BBBBBB',
            statusSimple: 'BBBBBB',
            accessPersistentId: 'BBBBBB',
            bookedby: 'BBBBBB',
            lastname: 'BBBBBB',
            firstname: 'BBBBBB',
            email: 'BBBBBB',
            address: 'BBBBBB',
            state: 'BBBBBB',
            loyaltyId: 'BBBBBB',
            arrivalTime: 'BBBBBB',
            seatedTime: 'BBBBBB',
            leftTime: 'BBBBBB',
            clientRequests: 'BBBBBB',
            comps: 1,
            onsitePayment: 1,
            totalPayment: 1,
            paidBy: 'BBBBBB',
            servedBy: 'BBBBBB',
            problems: 'BBBBBB',
            autoAssignments: 'BBBBBB',
            referenceCode: 'BBBBBB',
            reservationSmsOptin: true,
            reservationType: 'BBBBBB',
            sendReminderEmail: true,
          },
          new Reservation(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Reservation', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Reservation', async () => {
        const returnedFromService = Object.assign(
          {
            resvId: 'BBBBBB',
            created: 'BBBBBB',
            updated: 'BBBBBB',
            deleted: 'BBBBBB',
            venueGroupClientId: 'BBBBBB',
            venueGroupId: 'BBBBBB',
            venueId: 'BBBBBB',
            date: 'BBBBBB',
            duration: 1,
            checkNumbers: 'BBBBBB',
            shiftCategory: 'BBBBBB',
            shiftPersistentId: 'BBBBBB',
            maxGuests: 1,
            mfratioMale: 1,
            mfratioFemale: 1,
            status: 'BBBBBB',
            statusDisplay: 'BBBBBB',
            statusSimple: 'BBBBBB',
            accessPersistentId: 'BBBBBB',
            arrivedGuests: 1,
            isvip: true,
            bookedby: 'BBBBBB',
            clientReferenceCode: 'BBBBBB',
            lastname: 'BBBBBB',
            firstname: 'BBBBBB',
            email: 'BBBBBB',
            phoneNumber: 'BBBBBB',
            address: 'BBBBBB',
            address2: 'BBBBBB',
            city: 'BBBBBB',
            postalCode: 'BBBBBB',
            state: 'BBBBBB',
            country: 'BBBBBB',
            loyaltyId: 'BBBBBB',
            loyaltyRank: 1,
            loyaltyTier: 'BBBBBB',
            notes: 'BBBBBB',
            arrivalTime: 'BBBBBB',
            seatedTime: 'BBBBBB',
            leftTime: 'BBBBBB',
            clientRequests: 'BBBBBB',
            comps: 1,
            compsPriceType: 'BBBBBB',
            costOption: 1,
            policy: 'BBBBBB',
            minPrice: 1,
            prePayment: 1,
            onsitePayment: 1,
            totalPayment: 1,
            paidBy: 'BBBBBB',
            servedBy: 'BBBBBB',
            rating: 1,
            problems: 'BBBBBB',
            autoAssignments: 'BBBBBB',
            externalClientId: 'BBBBBB',
            externalId: 'BBBBBB',
            externalReferenceCode: 'BBBBBB',
            externalUserId: 'BBBBBB',
            modifyReservationLink: 'BBBBBB',
            referenceCode: 'BBBBBB',
            reservationSmsOptin: true,
            reservationType: 'BBBBBB',
            sendReminderEmail: true,
            sendreminderSms: true,
            sourceClientId: 'BBBBBB',
            userId: 'BBBBBB',
            userName: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Reservation', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Reservation', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Reservation', async () => {
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
