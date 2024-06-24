import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ClientPhotoService from './client-photo.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IClientPhoto, ClientPhoto } from '@/shared/model/client-photo.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ClientPhotoUpdate',
  setup() {
    const clientPhotoService = inject('clientPhotoService', () => new ClientPhotoService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const clientPhoto: Ref<IClientPhoto> = ref(new ClientPhoto());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveClientPhoto = async clientPhotoId => {
      try {
        const res = await clientPhotoService().find(clientPhotoId);
        clientPhoto.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.clientPhotoId) {
      retrieveClientPhoto(route.params.clientPhotoId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      large: {},
      largeHeight: {},
      largeWidth: {},
      medium: {},
      mediumHeight: {},
      mediumWidth: {},
      small: {},
      smallHeight: {},
      smallWidth: {},
      raw: {},
      cropx: {},
      cropy: {},
      cropHeight: {},
      cropWidth: {},
    };
    const v$ = useVuelidate(validationRules, clientPhoto as any);
    v$.value.$validate();

    return {
      clientPhotoService,
      alertService,
      clientPhoto,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.clientPhoto.id) {
        this.clientPhotoService()
          .update(this.clientPhoto)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('sevenRoomsToReviewProApplicationApp.clientPhoto.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.clientPhotoService()
          .create(this.clientPhoto)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(
              this.t$('sevenRoomsToReviewProApplicationApp.clientPhoto.created', { param: param.id }).toString(),
            );
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
