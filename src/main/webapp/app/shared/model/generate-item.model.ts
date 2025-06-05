import { IImageAsset } from 'app/shared/model/image-asset.model';
import { IGenerateList } from 'app/shared/model/generate-list.model';

export interface IGenerateItem {
  id?: number;
  name?: string | null;
  credit?: number | null;
  level?: number | null;
  image?: IImageAsset | null;
  generateList?: IGenerateList | null;
}

export const defaultValue: Readonly<IGenerateItem> = {};
