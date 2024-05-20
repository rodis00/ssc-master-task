export interface Error {
  status: string;
  code: number;
  message: {
    detail: string;
  };
}
