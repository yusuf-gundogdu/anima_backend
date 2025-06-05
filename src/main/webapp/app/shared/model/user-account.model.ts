import { Platform } from 'app/shared/model/enumerations/platform.model';

export interface IUserAccount {
  id?: number;
  timestamp?: number | null;
  udid?: string | null;
  platform?: keyof typeof Platform | null;
  credit?: number | null;
  level?: number | null;
}

export const defaultValue: Readonly<IUserAccount> = {};
