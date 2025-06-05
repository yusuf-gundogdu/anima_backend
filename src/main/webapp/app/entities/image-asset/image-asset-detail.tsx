import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './image-asset.reducer';

export const ImageAssetDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const imageAssetEntity = useAppSelector(state => state.imageAsset.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="imageAssetDetailsHeading">Image Asset</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{imageAssetEntity.id}</dd>
          <dt>
            <span id="fileName">File Name</span>
          </dt>
          <dd>{imageAssetEntity.fileName}</dd>
          <dt>
            <span id="filePath">File Path</span>
          </dt>
          <dd>{imageAssetEntity.filePath}</dd>
          <dt>
            <span id="fileSize">File Size</span>
          </dt>
          <dd>{imageAssetEntity.fileSize}</dd>
        </dl>
        <Button tag={Link} to="/image-asset" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/image-asset/${imageAssetEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default ImageAssetDetail;
