package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.ImageAsset;
import com.mycompany.myapp.repository.ImageAssetRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.ImageAsset}.
 */
@RestController
@RequestMapping("/api/image-assets")
@Transactional
public class ImageAssetResource {

    private static final Logger LOG = LoggerFactory.getLogger(ImageAssetResource.class);

    private static final String ENTITY_NAME = "imageAsset";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ImageAssetRepository imageAssetRepository;

    public ImageAssetResource(ImageAssetRepository imageAssetRepository) {
        this.imageAssetRepository = imageAssetRepository;
    }

    /**
     * {@code POST  /image-assets} : Create a new imageAsset.
     *
     * @param imageAsset the imageAsset to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new imageAsset, or with status {@code 400 (Bad Request)} if the imageAsset has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ImageAsset> createImageAsset(@RequestBody ImageAsset imageAsset) throws URISyntaxException {
        LOG.debug("REST request to save ImageAsset : {}", imageAsset);
        if (imageAsset.getId() != null) {
            throw new BadRequestAlertException("A new imageAsset cannot already have an ID", ENTITY_NAME, "idexists");
        }
        imageAsset = imageAssetRepository.save(imageAsset);
        return ResponseEntity.created(new URI("/api/image-assets/" + imageAsset.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, imageAsset.getId().toString()))
            .body(imageAsset);
    }

    /**
     * {@code PUT  /image-assets/:id} : Updates an existing imageAsset.
     *
     * @param id the id of the imageAsset to save.
     * @param imageAsset the imageAsset to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated imageAsset,
     * or with status {@code 400 (Bad Request)} if the imageAsset is not valid,
     * or with status {@code 500 (Internal Server Error)} if the imageAsset couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ImageAsset> updateImageAsset(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ImageAsset imageAsset
    ) throws URISyntaxException {
        LOG.debug("REST request to update ImageAsset : {}, {}", id, imageAsset);
        if (imageAsset.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, imageAsset.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!imageAssetRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        imageAsset = imageAssetRepository.save(imageAsset);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, imageAsset.getId().toString()))
            .body(imageAsset);
    }

    /**
     * {@code PATCH  /image-assets/:id} : Partial updates given fields of an existing imageAsset, field will ignore if it is null
     *
     * @param id the id of the imageAsset to save.
     * @param imageAsset the imageAsset to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated imageAsset,
     * or with status {@code 400 (Bad Request)} if the imageAsset is not valid,
     * or with status {@code 404 (Not Found)} if the imageAsset is not found,
     * or with status {@code 500 (Internal Server Error)} if the imageAsset couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ImageAsset> partialUpdateImageAsset(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ImageAsset imageAsset
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update ImageAsset partially : {}, {}", id, imageAsset);
        if (imageAsset.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, imageAsset.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!imageAssetRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ImageAsset> result = imageAssetRepository
            .findById(imageAsset.getId())
            .map(existingImageAsset -> {
                if (imageAsset.getFileName() != null) {
                    existingImageAsset.setFileName(imageAsset.getFileName());
                }
                if (imageAsset.getFilePath() != null) {
                    existingImageAsset.setFilePath(imageAsset.getFilePath());
                }
                if (imageAsset.getFileSize() != null) {
                    existingImageAsset.setFileSize(imageAsset.getFileSize());
                }

                return existingImageAsset;
            })
            .map(imageAssetRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, imageAsset.getId().toString())
        );
    }

    /**
     * {@code GET  /image-assets} : get all the imageAssets.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of imageAssets in body.
     */
    @GetMapping("")
    public List<ImageAsset> getAllImageAssets(@RequestParam(name = "filter", required = false) String filter) {
        if ("generateitem-is-null".equals(filter)) {
            LOG.debug("REST request to get all ImageAssets where generateItem is null");
            return StreamSupport.stream(imageAssetRepository.findAll().spliterator(), false)
                .filter(imageAsset -> imageAsset.getGenerateItem() == null)
                .toList();
        }
        LOG.debug("REST request to get all ImageAssets");
        return imageAssetRepository.findAll();
    }

    /**
     * {@code GET  /image-assets/:id} : get the "id" imageAsset.
     *
     * @param id the id of the imageAsset to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the imageAsset, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ImageAsset> getImageAsset(@PathVariable("id") Long id) {
        LOG.debug("REST request to get ImageAsset : {}", id);
        Optional<ImageAsset> imageAsset = imageAssetRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(imageAsset);
    }

    /**
     * {@code DELETE  /image-assets/:id} : delete the "id" imageAsset.
     *
     * @param id the id of the imageAsset to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImageAsset(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete ImageAsset : {}", id);
        imageAssetRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
