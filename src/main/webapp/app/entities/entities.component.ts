import { defineComponent, provide } from 'vue';

import RvpProfileService from './rvp-profile/rvp-profile.service';
import ClientService from './client/client.service';
import MemberGroupService from './member-group/member-group.service';
import ClientTagService from './client-tag/client-tag.service';
import CustomFieldService from './custom-field/custom-field.service';
import ClientVenueStatsService from './client-venue-stats/client-venue-stats.service';
import BookingNameService from './booking-name/booking-name.service';
import ClientPhotoService from './client-photo/client-photo.service';
import ReservationService from './reservation/reservation.service';
import ResCustomFieldService from './res-custom-field/res-custom-field.service';
import ResPosTicketService from './res-pos-ticket/res-pos-ticket.service';
import ResPosticketsItemService from './res-postickets-item/res-postickets-item.service';
import ResTagService from './res-tag/res-tag.service';
import ResTableService from './res-table/res-table.service';
import VenueService from './venue/venue.service';
import UserService from '@/entities/user/user.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Entities',
  setup() {
    provide('userService', () => new UserService());
    provide('rvpProfileService', () => new RvpProfileService());
    provide('clientService', () => new ClientService());
    provide('memberGroupService', () => new MemberGroupService());
    provide('clientTagService', () => new ClientTagService());
    provide('customFieldService', () => new CustomFieldService());
    provide('clientVenueStatsService', () => new ClientVenueStatsService());
    provide('bookingNameService', () => new BookingNameService());
    provide('clientPhotoService', () => new ClientPhotoService());
    provide('reservationService', () => new ReservationService());
    provide('resCustomFieldService', () => new ResCustomFieldService());
    provide('resPosTicketService', () => new ResPosTicketService());
    provide('resPosticketsItemService', () => new ResPosticketsItemService());
    provide('resTagService', () => new ResTagService());
    provide('resTableService', () => new ResTableService());
    provide('venueService', () => new VenueService());
    // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
  },
});
