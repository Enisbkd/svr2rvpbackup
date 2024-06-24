import { type IReservation } from '@/shared/model/reservation.model';

export interface IResTable {
  id?: number;
  tableNumber?: string | null;
  reservation?: IReservation | null;
}

export class ResTable implements IResTable {
  constructor(
    public id?: number,
    public tableNumber?: string | null,
    public reservation?: IReservation | null,
  ) {}
}
