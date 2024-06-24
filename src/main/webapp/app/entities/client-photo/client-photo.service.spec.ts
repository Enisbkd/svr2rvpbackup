/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import ClientPhotoService from './client-photo.service';
import { ClientPhoto } from '@/shared/model/client-photo.model';

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
  describe('ClientPhoto Service', () => {
    let service: ClientPhotoService;
    let elemDefault;

    beforeEach(() => {
      service = new ClientPhotoService();
      elemDefault = new ClientPhoto(123, 'AAAAAAA', 0, 0, 'AAAAAAA', 0, 0, 'AAAAAAA', 0, 0, 'AAAAAAA', 0, 0, 0, 0);
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

      it('should create a ClientPhoto', async () => {
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

      it('should not create a ClientPhoto', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a ClientPhoto', async () => {
        const returnedFromService = Object.assign(
          {
            large: 'BBBBBB',
            largeHeight: 1,
            largeWidth: 1,
            medium: 'BBBBBB',
            mediumHeight: 1,
            mediumWidth: 1,
            small: 'BBBBBB',
            smallHeight: 1,
            smallWidth: 1,
            raw: 'BBBBBB',
            cropx: 1,
            cropy: 1,
            cropHeight: 1,
            cropWidth: 1,
          },
          elemDefault,
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a ClientPhoto', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a ClientPhoto', async () => {
        const patchObject = Object.assign(
          {
            large: 'BBBBBB',
            largeHeight: 1,
            largeWidth: 1,
            medium: 'BBBBBB',
            smallWidth: 1,
            raw: 'BBBBBB',
            cropx: 1,
            cropy: 1,
          },
          new ClientPhoto(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a ClientPhoto', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of ClientPhoto', async () => {
        const returnedFromService = Object.assign(
          {
            large: 'BBBBBB',
            largeHeight: 1,
            largeWidth: 1,
            medium: 'BBBBBB',
            mediumHeight: 1,
            mediumWidth: 1,
            small: 'BBBBBB',
            smallHeight: 1,
            smallWidth: 1,
            raw: 'BBBBBB',
            cropx: 1,
            cropy: 1,
            cropHeight: 1,
            cropWidth: 1,
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of ClientPhoto', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a ClientPhoto', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a ClientPhoto', async () => {
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
