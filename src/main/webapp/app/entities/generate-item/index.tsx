import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import GenerateItem from './generate-item';
import GenerateItemDetail from './generate-item-detail';
import GenerateItemUpdate from './generate-item-update';
import GenerateItemDeleteDialog from './generate-item-delete-dialog';

const GenerateItemRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<GenerateItem />} />
    <Route path="new" element={<GenerateItemUpdate />} />
    <Route path=":id">
      <Route index element={<GenerateItemDetail />} />
      <Route path="edit" element={<GenerateItemUpdate />} />
      <Route path="delete" element={<GenerateItemDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default GenerateItemRoutes;
