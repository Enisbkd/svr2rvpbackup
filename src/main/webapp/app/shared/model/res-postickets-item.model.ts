import { type IResPosTicket } from '@/shared/model/res-pos-ticket.model';

export interface IResPosticketsItem {
  id?: number;
  price?: number | null;
  name?: string | null;
  quantity?: number | null;
  resPosTicket?: IResPosTicket | null;
}

export class ResPosticketsItem implements IResPosticketsItem {
  constructor(
    public id?: number,
    public price?: number | null,
    public name?: string | null,
    public quantity?: number | null,
    public resPosTicket?: IResPosTicket | null,
  ) {}
}
