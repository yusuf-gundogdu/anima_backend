import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import GenerateList from './generate-list';
import GenerateListDetail from './generate-list-detail';
import GenerateListUpdate from './generate-list-update';
import GenerateListDeleteDialog from './generate-list-delete-dialog';

const GenerateListRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<GenerateList />} />
    <Route path="new" element={<GenerateListUpdate />} />
    <Route path=":id">
      <Route index element={<GenerateListDetail />} />
      <Route path="edit" element={<GenerateListUpdate />} />
      <Route path="delete" element={<GenerateListDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default GenerateListRoutes;
