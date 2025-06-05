import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './image-asset.reducer';

export const ImageAssetUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const imageAssetEntity = useAppSelector(state => state.imageAsset.entity);
  const loading = useAppSelector(state => state.imageAsset.loading);
  const updating = useAppSelector(state => state.imageAsset.updating);
  const updateSuccess = useAppSelector(state => state.imageAsset.updateSuccess);

  const handleClose = () => {
    navigate('/image-asset');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
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
    if (values.fileSize !== undefined && typeof values.fileSize !== 'number') {
      values.fileSize = Number(values.fileSize);
    }

    const entity = {
      ...imageAssetEntity,
      ...values,
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
          ...imageAssetEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="backendApp.imageAsset.home.createOrEditLabel" data-cy="ImageAssetCreateUpdateHeading">
            Create or edit a Image Asset
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="image-asset-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="File Name" id="image-asset-fileName" name="fileName" data-cy="fileName" type="text" />
              <ValidatedField label="File Path" id="image-asset-filePath" name="filePath" data-cy="filePath" type="text" />
              <ValidatedField label="File Size" id="image-asset-fileSize" name="fileSize" data-cy="fileSize" type="text" />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/image-asset" replace color="info">
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

export default ImageAssetUpdate;
