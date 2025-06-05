import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './generate-item.reducer';

export const GenerateItemDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const generateItemEntity = useAppSelector(state => state.generateItem.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="generateItemDetailsHeading">Generate Item</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">Id</span>
          </dt>
          <dd>{generateItemEntity.id}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{generateItemEntity.name}</dd>
          <dt>
            <span id="credit">Credit</span>
          </dt>
          <dd>{generateItemEntity.credit}</dd>
          <dt>
            <span id="level">Level</span>
          </dt>
          <dd>{generateItemEntity.level}</dd>
          <dt>Image</dt>
          <dd>{generateItemEntity.image ? generateItemEntity.image.id : ''}</dd>
          <dt>Generate List</dt>
          <dd>{generateItemEntity.generateList ? generateItemEntity.generateList.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/generate-item" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/generate-item/${generateItemEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default GenerateItemDetail;
