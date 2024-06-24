export interface IRvpProfile {
  id?: number;
  pmsId?: string | null;
  firstName?: string | null;
  lastName?: string | null;
  language?: string | null;
  checkin?: Date | null;
  checkout?: Date | null;
  email?: string | null;
}

export class RvpProfile implements IRvpProfile {
  constructor(
    public id?: number,
    public pmsId?: string | null,
    public firstName?: string | null,
    public lastName?: string | null,
    public language?: string | null,
    public checkin?: Date | null,
    public checkout?: Date | null,
    public email?: string | null,
  ) {}
}
