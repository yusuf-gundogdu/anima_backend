import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import ImageAsset from './image-asset';
import ImageAssetDetail from './image-asset-detail';
import ImageAssetUpdate from './image-asset-update';
import ImageAssetDeleteDialog from './image-asset-delete-dialog';

const ImageAssetRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<ImageAsset />} />
    <Route path="new" element={<ImageAssetUpdate />} />
    <Route path=":id">
      <Route index element={<ImageAssetDetail />} />
      <Route path="edit" element={<ImageAssetUpdate />} />
      <Route path="delete" element={<ImageAssetDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default ImageAssetRoutes;
