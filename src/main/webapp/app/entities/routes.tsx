import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import ImageAsset from './image-asset';
import UserAccount from './user-account';
import GenerateList from './generate-list';
import GenerateItem from './generate-item';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="image-asset/*" element={<ImageAsset />} />
        <Route path="user-account/*" element={<UserAccount />} />
        <Route path="generate-list/*" element={<GenerateList />} />
        <Route path="generate-item/*" element={<GenerateItem />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
