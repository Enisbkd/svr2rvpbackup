import { type IReservation } from '@/shared/model/reservation.model';

export interface IResCustomField {
  id?: number;
  systemName?: string | null;
  displayOrder?: number | null;
  name?: string | null;
  value?: string | null;
  reservation?: IReservation | null;
}

export class ResCustomField implements IResCustomField {
  constructor(
    public id?: number,
    public systemName?: string | null,
    public displayOrder?: number | null,
    public name?: string | null,
    public value?: string | null,
    public reservation?: IReservation | null,
  ) {}
}
