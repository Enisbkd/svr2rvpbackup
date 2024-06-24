export interface IClientPhoto {
  id?: number;
  large?: string | null;
  largeHeight?: number | null;
  largeWidth?: number | null;
  medium?: string | null;
  mediumHeight?: number | null;
  mediumWidth?: number | null;
  small?: string | null;
  smallHeight?: number | null;
  smallWidth?: number | null;
  raw?: string | null;
  cropx?: number | null;
  cropy?: number | null;
  cropHeight?: number | null;
  cropWidth?: number | null;
}

export class ClientPhoto implements IClientPhoto {
  constructor(
    public id?: number,
    public large?: string | null,
    public largeHeight?: number | null,
    public largeWidth?: number | null,
    public medium?: string | null,
    public mediumHeight?: number | null,
    public mediumWidth?: number | null,
    public small?: string | null,
    public smallHeight?: number | null,
    public smallWidth?: number | null,
    public raw?: string | null,
    public cropx?: number | null,
    public cropy?: number | null,
    public cropHeight?: number | null,
    public cropWidth?: number | null,
  ) {}
}
