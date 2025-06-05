export interface IImageAsset {
  id?: number;
  fileName?: string | null;
  filePath?: string | null;
  fileSize?: number | null;
}

export const defaultValue: Readonly<IImageAsset> = {};
