import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { Platform } from 'app/shared/model/enumerations/platform.model';
import { createEntity, getEntity, reset, updateEntity } from './user-account.reducer';

export const UserAccountUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const userAccountEntity = useAppSelector(state => state.userAccount.entity);
  const loading = useAppSelector(state => state.userAccount.loading);
  const updating = useAppSelector(state => state.userAccount.updating);
  const updateSuccess = useAppSelector(state => state.userAccount.updateSuccess);
  const platformValues = Object.keys(Platform);

  const handleClose = () => {
    navigate('/user-account');
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
    if (values.timestamp !== undefined && typeof values.timestamp !== 'number') {
      values.timestamp = Number(values.timestamp);
    }
    if (values.credit !== undefined && typeof values.credit !== 'number') {
      values.credit = Number(values.credit);
    }
    if (values.level !== undefined && typeof values.level !== 'number') {
      values.level = Number(values.level);
    }

    const entity = {
      ...userAccountEntity,
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
          platform: 'AND',
          ...userAccountEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="backendApp.userAccount.home.createOrEditLabel" data-cy="UserAccountCreateUpdateHeading">
            Create or edit a User Account
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="user-account-id" label="Id" validate={{ required: true }} /> : null}
              <ValidatedField label="Timestamp" id="user-account-timestamp" name="timestamp" data-cy="timestamp" type="text" />
              <ValidatedField label="Udid" id="user-account-udid" name="udid" data-cy="udid" type="text" />
              <ValidatedField label="Platform" id="user-account-platform" name="platform" data-cy="platform" type="select">
                {platformValues.map(platform => (
                  <option value={platform} key={platform}>
                    {platform}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField label="Credit" id="user-account-credit" name="credit" data-cy="credit" type="text" />
              <ValidatedField label="Level" id="user-account-level" name="level" data-cy="level" type="text" />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/user-account" replace color="info">
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

export default UserAccountUpdate;
