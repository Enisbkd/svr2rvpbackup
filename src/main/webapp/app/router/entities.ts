import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore
const Entities = () => import('@/entities/entities.vue');

const RvpProfile = () => import('@/entities/rvp-profile/rvp-profile.vue');
const RvpProfileUpdate = () => import('@/entities/rvp-profile/rvp-profile-update.vue');
const RvpProfileDetails = () => import('@/entities/rvp-profile/rvp-profile-details.vue');

const Client = () => import('@/entities/client/client.vue');
const ClientUpdate = () => import('@/entities/client/client-update.vue');
const ClientDetails = () => import('@/entities/client/client-details.vue');

const MemberGroup = () => import('@/entities/member-group/member-group.vue');
const MemberGroupUpdate = () => import('@/entities/member-group/member-group-update.vue');
const MemberGroupDetails = () => import('@/entities/member-group/member-group-details.vue');

const ClientTag = () => import('@/entities/client-tag/client-tag.vue');
const ClientTagUpdate = () => import('@/entities/client-tag/client-tag-update.vue');
const ClientTagDetails = () => import('@/entities/client-tag/client-tag-details.vue');

const CustomField = () => import('@/entities/custom-field/custom-field.vue');
const CustomFieldUpdate = () => import('@/entities/custom-field/custom-field-update.vue');
const CustomFieldDetails = () => import('@/entities/custom-field/custom-field-details.vue');

const ClientVenueStats = () => import('@/entities/client-venue-stats/client-venue-stats.vue');
const ClientVenueStatsUpdate = () => import('@/entities/client-venue-stats/client-venue-stats-update.vue');
const ClientVenueStatsDetails = () => import('@/entities/client-venue-stats/client-venue-stats-details.vue');

const BookingName = () => import('@/entities/booking-name/booking-name.vue');
const BookingNameUpdate = () => import('@/entities/booking-name/booking-name-update.vue');
const BookingNameDetails = () => import('@/entities/booking-name/booking-name-details.vue');

const ClientPhoto = () => import('@/entities/client-photo/client-photo.vue');
const ClientPhotoUpdate = () => import('@/entities/client-photo/client-photo-update.vue');
const ClientPhotoDetails = () => import('@/entities/client-photo/client-photo-details.vue');

const Reservation = () => import('@/entities/reservation/reservation.vue');
const ReservationUpdate = () => import('@/entities/reservation/reservation-update.vue');
const ReservationDetails = () => import('@/entities/reservation/reservation-details.vue');

const ResCustomField = () => import('@/entities/res-custom-field/res-custom-field.vue');
const ResCustomFieldUpdate = () => import('@/entities/res-custom-field/res-custom-field-update.vue');
const ResCustomFieldDetails = () => import('@/entities/res-custom-field/res-custom-field-details.vue');

const ResPosTicket = () => import('@/entities/res-pos-ticket/res-pos-ticket.vue');
const ResPosTicketUpdate = () => import('@/entities/res-pos-ticket/res-pos-ticket-update.vue');
const ResPosTicketDetails = () => import('@/entities/res-pos-ticket/res-pos-ticket-details.vue');

const ResPosticketsItem = () => import('@/entities/res-postickets-item/res-postickets-item.vue');
const ResPosticketsItemUpdate = () => import('@/entities/res-postickets-item/res-postickets-item-update.vue');
const ResPosticketsItemDetails = () => import('@/entities/res-postickets-item/res-postickets-item-details.vue');

const ResTag = () => import('@/entities/res-tag/res-tag.vue');
const ResTagUpdate = () => import('@/entities/res-tag/res-tag-update.vue');
const ResTagDetails = () => import('@/entities/res-tag/res-tag-details.vue');

const ResTable = () => import('@/entities/res-table/res-table.vue');
const ResTableUpdate = () => import('@/entities/res-table/res-table-update.vue');
const ResTableDetails = () => import('@/entities/res-table/res-table-details.vue');

const Venue = () => import('@/entities/venue/venue.vue');
const VenueUpdate = () => import('@/entities/venue/venue-update.vue');
const VenueDetails = () => import('@/entities/venue/venue-details.vue');

// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default {
  path: '/',
  component: Entities,
  children: [
    {
      path: 'rvp-profile',
      name: 'RvpProfile',
      component: RvpProfile,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'rvp-profile/new',
      name: 'RvpProfileCreate',
      component: RvpProfileUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'rvp-profile/:rvpProfileId/edit',
      name: 'RvpProfileEdit',
      component: RvpProfileUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'rvp-profile/:rvpProfileId/view',
      name: 'RvpProfileView',
      component: RvpProfileDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client',
      name: 'Client',
      component: Client,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/new',
      name: 'ClientCreate',
      component: ClientUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/:clientId/edit',
      name: 'ClientEdit',
      component: ClientUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/:clientId/view',
      name: 'ClientView',
      component: ClientDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'member-group',
      name: 'MemberGroup',
      component: MemberGroup,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'member-group/new',
      name: 'MemberGroupCreate',
      component: MemberGroupUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'member-group/:memberGroupId/edit',
      name: 'MemberGroupEdit',
      component: MemberGroupUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'member-group/:memberGroupId/view',
      name: 'MemberGroupView',
      component: MemberGroupDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client-tag',
      name: 'ClientTag',
      component: ClientTag,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client-tag/new',
      name: 'ClientTagCreate',
      component: ClientTagUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client-tag/:clientTagId/edit',
      name: 'ClientTagEdit',
      component: ClientTagUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client-tag/:clientTagId/view',
      name: 'ClientTagView',
      component: ClientTagDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'custom-field',
      name: 'CustomField',
      component: CustomField,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'custom-field/new',
      name: 'CustomFieldCreate',
      component: CustomFieldUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'custom-field/:customFieldId/edit',
      name: 'CustomFieldEdit',
      component: CustomFieldUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'custom-field/:customFieldId/view',
      name: 'CustomFieldView',
      component: CustomFieldDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client-venue-stats',
      name: 'ClientVenueStats',
      component: ClientVenueStats,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client-venue-stats/new',
      name: 'ClientVenueStatsCreate',
      component: ClientVenueStatsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client-venue-stats/:clientVenueStatsId/edit',
      name: 'ClientVenueStatsEdit',
      component: ClientVenueStatsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client-venue-stats/:clientVenueStatsId/view',
      name: 'ClientVenueStatsView',
      component: ClientVenueStatsDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'booking-name',
      name: 'BookingName',
      component: BookingName,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'booking-name/new',
      name: 'BookingNameCreate',
      component: BookingNameUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'booking-name/:bookingNameId/edit',
      name: 'BookingNameEdit',
      component: BookingNameUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'booking-name/:bookingNameId/view',
      name: 'BookingNameView',
      component: BookingNameDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client-photo',
      name: 'ClientPhoto',
      component: ClientPhoto,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client-photo/new',
      name: 'ClientPhotoCreate',
      component: ClientPhotoUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client-photo/:clientPhotoId/edit',
      name: 'ClientPhotoEdit',
      component: ClientPhotoUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client-photo/:clientPhotoId/view',
      name: 'ClientPhotoView',
      component: ClientPhotoDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'reservation',
      name: 'Reservation',
      component: Reservation,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'reservation/new',
      name: 'ReservationCreate',
      component: ReservationUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'reservation/:reservationId/edit',
      name: 'ReservationEdit',
      component: ReservationUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'reservation/:reservationId/view',
      name: 'ReservationView',
      component: ReservationDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'res-custom-field',
      name: 'ResCustomField',
      component: ResCustomField,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'res-custom-field/new',
      name: 'ResCustomFieldCreate',
      component: ResCustomFieldUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'res-custom-field/:resCustomFieldId/edit',
      name: 'ResCustomFieldEdit',
      component: ResCustomFieldUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'res-custom-field/:resCustomFieldId/view',
      name: 'ResCustomFieldView',
      component: ResCustomFieldDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'res-pos-ticket',
      name: 'ResPosTicket',
      component: ResPosTicket,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'res-pos-ticket/new',
      name: 'ResPosTicketCreate',
      component: ResPosTicketUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'res-pos-ticket/:resPosTicketId/edit',
      name: 'ResPosTicketEdit',
      component: ResPosTicketUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'res-pos-ticket/:resPosTicketId/view',
      name: 'ResPosTicketView',
      component: ResPosTicketDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'res-postickets-item',
      name: 'ResPosticketsItem',
      component: ResPosticketsItem,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'res-postickets-item/new',
      name: 'ResPosticketsItemCreate',
      component: ResPosticketsItemUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'res-postickets-item/:resPosticketsItemId/edit',
      name: 'ResPosticketsItemEdit',
      component: ResPosticketsItemUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'res-postickets-item/:resPosticketsItemId/view',
      name: 'ResPosticketsItemView',
      component: ResPosticketsItemDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'res-tag',
      name: 'ResTag',
      component: ResTag,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'res-tag/new',
      name: 'ResTagCreate',
      component: ResTagUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'res-tag/:resTagId/edit',
      name: 'ResTagEdit',
      component: ResTagUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'res-tag/:resTagId/view',
      name: 'ResTagView',
      component: ResTagDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'res-table',
      name: 'ResTable',
      component: ResTable,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'res-table/new',
      name: 'ResTableCreate',
      component: ResTableUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'res-table/:resTableId/edit',
      name: 'ResTableEdit',
      component: ResTableUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'res-table/:resTableId/view',
      name: 'ResTableView',
      component: ResTableDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'venue',
      name: 'Venue',
      component: Venue,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'venue/new',
      name: 'VenueCreate',
      component: VenueUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'venue/:venueId/edit',
      name: 'VenueEdit',
      component: VenueUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'venue/:venueId/view',
      name: 'VenueView',
      component: VenueDetails,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
