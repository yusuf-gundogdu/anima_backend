import imageAsset from 'app/entities/image-asset/image-asset.reducer';
import userAccount from 'app/entities/user-account/user-account.reducer';
import generateList from 'app/entities/generate-list/generate-list.reducer';
import generateItem from 'app/entities/generate-item/generate-item.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  imageAsset,
  userAccount,
  generateList,
  generateItem,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
