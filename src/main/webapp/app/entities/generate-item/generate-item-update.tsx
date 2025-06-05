import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getImageAssets } from 'app/entities/image-asset/image-asset.reducer';
import { getEntities as getGenerateLists } from 'app/entities/generate-list/generate-list.reducer';
import { createEntity, getEntity, reset, updateEntity } from './generate-item.reducer';

export const GenerateItemUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const imageAssets = useAppSelector(state => state.imageAsset.entities);
  const generateLists = useAppSelector(state => state.generateList.entities);
  const generateItemEntity = useAppSelector(state => state.generateItem.entity);
  const loading = useAppSelector(state => state.generateItem.loading);
  const updating = useAppSelector(state => state.generateItem.updating);
  const updateSuccess = useAppSelector(state => state.generateItem.updateSuccess);

  const handleClose = () => {
    navigate('/generate-item');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getImageAssets({}));
    dispatch(getGenerateLists({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }
    if (values.credit !== undefined && typeof values.credit !== 'number') {
      values.credit = Number(values.credit);
    }
    if (values.level !== undefined && typeof values.level !== 'number') {
      values.level = Number(values.level);
    }

    const entity = {
      ...generateItemEntity,
      ...values,
      image: imageAssets.find(it => it.id.toString() === values.image?.toString()),
      generateList: generateLists.find(it => it.id.toString() === values.generateList?.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...generateItemEntity,
          image: generateItemEntity?.image?.id,
          generateList: generateItemEntity?.generateList?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="backendApp.generateItem.home.createOrEditLabel" data-cy="GenerateItemCreateUpdateHeading">
            Create or edit a Generate Item
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField name="id" required readOnly id="generate-item-id" label="Id" validate={{ required: true }} />
              ) : null}
              <ValidatedField label="Name" id="generate-item-name" name="name" data-cy="name" type="text" />
              <ValidatedField label="Credit" id="generate-item-credit" name="credit" data-cy="credit" type="text" />
              <ValidatedField label="Level" id="generate-item-level" name="level" data-cy="level" type="text" />
              <ValidatedField id="generate-item-image" name="image" data-cy="image" label="Image" type="select">
                <option value="" key="0" />
                {imageAssets
                  ? imageAssets.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="generate-item-generateList"
                name="generateList"
                data-cy="generateList"
                label="Generate List"
                type="select"
              >
                <option value="" key="0" />
                {generateLists
                  ? generateLists.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/generate-item" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default GenerateItemUpdate;
