import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './user-account.reducer';

export const UserAccountDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const userAccountEntity = useAppSelector(state => state.userAccount.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="userAccountDetailsHeading">User Account</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">Id</span>
          </dt>
          <dd>{userAccountEntity.id}</dd>
          <dt>
            <span id="timestamp">Timestamp</span>
          </dt>
          <dd>{userAccountEntity.timestamp}</dd>
          <dt>
            <span id="udid">Udid</span>
          </dt>
          <dd>{userAccountEntity.udid}</dd>
          <dt>
            <span id="platform">Platform</span>
          </dt>
          <dd>{userAccountEntity.platform}</dd>
          <dt>
            <span id="credit">Credit</span>
          </dt>
          <dd>{userAccountEntity.credit}</dd>
          <dt>
            <span id="level">Level</span>
          </dt>
          <dd>{userAccountEntity.level}</dd>
        </dl>
        <Button tag={Link} to="/user-account" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/user-account/${userAccountEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default UserAccountDetail;
