export interface IVenue {
  id?: number;
  address?: string | null;
  blackLogo?: string | null;
  country?: string | null;
  crossStreet?: string | null;
  currencyCode?: string | null;
  externalVenueId?: string | null;
  fullDiningBackend?: boolean | null;
  gridEnabled?: boolean | null;
  venueId?: string | null;
  internalName?: string | null;
  membershipEnabled?: boolean | null;
  name?: string | null;
  neighborhood?: string | null;
  phoneNumber?: string | null;
  policy?: string | null;
  postalCode?: string | null;
  primaryColor?: string | null;
  secondaryColor?: string | null;
  state?: string | null;
  uniqueConfirmationPrefix?: string | null;
  venueClass?: string | null;
  venueGroupId?: string | null;
  venueGroupName?: string | null;
  venueUrlKey?: string | null;
  website?: string | null;
  whiteLogo?: string | null;
}

export class Venue implements IVenue {
  constructor(
    public id?: number,
    public address?: string | null,
    public blackLogo?: string | null,
    public country?: string | null,
    public crossStreet?: string | null,
    public currencyCode?: string | null,
    public externalVenueId?: string | null,
    public fullDiningBackend?: boolean | null,
    public gridEnabled?: boolean | null,
    public venueId?: string | null,
    public internalName?: string | null,
    public membershipEnabled?: boolean | null,
    public name?: string | null,
    public neighborhood?: string | null,
    public phoneNumber?: string | null,
    public policy?: string | null,
    public postalCode?: string | null,
    public primaryColor?: string | null,
    public secondaryColor?: string | null,
    public state?: string | null,
    public uniqueConfirmationPrefix?: string | null,
    public venueClass?: string | null,
    public venueGroupId?: string | null,
    public venueGroupName?: string | null,
    public venueUrlKey?: string | null,
    public website?: string | null,
    public whiteLogo?: string | null,
  ) {
    this.fullDiningBackend = this.fullDiningBackend ?? false;
    this.gridEnabled = this.gridEnabled ?? false;
    this.membershipEnabled = this.membershipEnabled ?? false;
  }
}
