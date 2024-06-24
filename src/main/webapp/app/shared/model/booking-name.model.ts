import { type IClientVenueStats } from '@/shared/model/client-venue-stats.model';

export interface IBookingName {
  id?: number;
  name?: string | null;
  clientVenueStats?: IClientVenueStats | null;
}

export class BookingName implements IBookingName {
  constructor(
    public id?: number,
    public name?: string | null,
    public clientVenueStats?: IClientVenueStats | null,
  ) {}
}
