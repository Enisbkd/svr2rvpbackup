import { type IReservation } from '@/shared/model/reservation.model';

export interface IResTag {
  id?: number;
  tag?: string | null;
  tagDisplay?: string | null;
  group?: string | null;
  groupDisplay?: string | null;
  color?: string | null;
  tagSearchQuery?: string | null;
  reservation?: IReservation | null;
}

export class ResTag implements IResTag {
  constructor(
    public id?: number,
    public tag?: string | null,
    public tagDisplay?: string | null,
    public group?: string | null,
    public groupDisplay?: string | null,
    public color?: string | null,
    public tagSearchQuery?: string | null,
    public reservation?: IReservation | null,
  ) {}
}
