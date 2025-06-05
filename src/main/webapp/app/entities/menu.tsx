import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/image-asset">
        Image Asset
      </MenuItem>
      <MenuItem icon="asterisk" to="/user-account">
        User Account
      </MenuItem>
      <MenuItem icon="asterisk" to="/generate-list">
        Generate List
      </MenuItem>
      <MenuItem icon="asterisk" to="/generate-item">
        Generate Item
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
