/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import RvpProfileService from './rvp-profile.service';
import { DATE_TIME_FORMAT } from '@/shared/composables/date-format';
import { RvpProfile } from '@/shared/model/rvp-profile.model';

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
  describe('RvpProfile Service', () => {
    let service: RvpProfileService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new RvpProfileService();
      currentDate = new Date();
      elemDefault = new RvpProfile(123, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            checkin: dayjs(currentDate).format(DATE_TIME_FORMAT),
            checkout: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault,
        );
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

      it('should create a RvpProfile', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            checkin: dayjs(currentDate).format(DATE_TIME_FORMAT),
            checkout: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            checkin: currentDate,
            checkout: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a RvpProfile', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a RvpProfile', async () => {
        const returnedFromService = Object.assign(
          {
            pmsId: 'BBBBBB',
            firstName: 'BBBBBB',
            lastName: 'BBBBBB',
            language: 'BBBBBB',
            checkin: dayjs(currentDate).format(DATE_TIME_FORMAT),
            checkout: dayjs(currentDate).format(DATE_TIME_FORMAT),
            email: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            checkin: currentDate,
            checkout: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a RvpProfile', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a RvpProfile', async () => {
        const patchObject = Object.assign(
          {
            firstName: 'BBBBBB',
            language: 'BBBBBB',
            checkin: dayjs(currentDate).format(DATE_TIME_FORMAT),
            checkout: dayjs(currentDate).format(DATE_TIME_FORMAT),
            email: 'BBBBBB',
          },
          new RvpProfile(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            checkin: currentDate,
            checkout: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a RvpProfile', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of RvpProfile', async () => {
        const returnedFromService = Object.assign(
          {
            pmsId: 'BBBBBB',
            firstName: 'BBBBBB',
            lastName: 'BBBBBB',
            language: 'BBBBBB',
            checkin: dayjs(currentDate).format(DATE_TIME_FORMAT),
            checkout: dayjs(currentDate).format(DATE_TIME_FORMAT),
            email: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            checkin: currentDate,
            checkout: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of RvpProfile', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a RvpProfile', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a RvpProfile', async () => {
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
