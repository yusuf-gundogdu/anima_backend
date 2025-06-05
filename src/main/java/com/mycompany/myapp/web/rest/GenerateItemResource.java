package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.GenerateItem;
import com.mycompany.myapp.repository.GenerateItemRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.GenerateItem}.
 */
@RestController
@RequestMapping("/api/generate-items")
@Transactional
public class GenerateItemResource {

    private static final Logger LOG = LoggerFactory.getLogger(GenerateItemResource.class);

    private static final String ENTITY_NAME = "generateItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GenerateItemRepository generateItemRepository;

    public GenerateItemResource(GenerateItemRepository generateItemRepository) {
        this.generateItemRepository = generateItemRepository;
    }

    /**
     * {@code POST  /generate-items} : Create a new generateItem.
     *
     * @param generateItem the generateItem to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new generateItem, or with status {@code 400 (Bad Request)} if the generateItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<GenerateItem> createGenerateItem(@RequestBody GenerateItem generateItem) throws URISyntaxException {
        LOG.debug("REST request to save GenerateItem : {}", generateItem);
        if (generateItem.getId() != null) {
            throw new BadRequestAlertException("A new generateItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        generateItem = generateItemRepository.save(generateItem);
        return ResponseEntity.created(new URI("/api/generate-items/" + generateItem.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, generateItem.getId().toString()))
            .body(generateItem);
    }

    /**
     * {@code PUT  /generate-items/:id} : Updates an existing generateItem.
     *
     * @param id the id of the generateItem to save.
     * @param generateItem the generateItem to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated generateItem,
     * or with status {@code 400 (Bad Request)} if the generateItem is not valid,
     * or with status {@code 500 (Internal Server Error)} if the generateItem couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<GenerateItem> updateGenerateItem(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody GenerateItem generateItem
    ) throws URISyntaxException {
        LOG.debug("REST request to update GenerateItem : {}, {}", id, generateItem);
        if (generateItem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, generateItem.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!generateItemRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        generateItem = generateItemRepository.save(generateItem);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, generateItem.getId().toString()))
            .body(generateItem);
    }

    /**
     * {@code PATCH  /generate-items/:id} : Partial updates given fields of an existing generateItem, field will ignore if it is null
     *
     * @param id the id of the generateItem to save.
     * @param generateItem the generateItem to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated generateItem,
     * or with status {@code 400 (Bad Request)} if the generateItem is not valid,
     * or with status {@code 404 (Not Found)} if the generateItem is not found,
     * or with status {@code 500 (Internal Server Error)} if the generateItem couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<GenerateItem> partialUpdateGenerateItem(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody GenerateItem generateItem
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update GenerateItem partially : {}, {}", id, generateItem);
        if (generateItem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, generateItem.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!generateItemRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<GenerateItem> result = generateItemRepository
            .findById(generateItem.getId())
            .map(existingGenerateItem -> {
                if (generateItem.getName() != null) {
                    existingGenerateItem.setName(generateItem.getName());
                }
                if (generateItem.getCredit() != null) {
                    existingGenerateItem.setCredit(generateItem.getCredit());
                }
                if (generateItem.getLevel() != null) {
                    existingGenerateItem.setLevel(generateItem.getLevel());
                }

                return existingGenerateItem;
            })
            .map(generateItemRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, generateItem.getId().toString())
        );
    }

    /**
     * {@code GET  /generate-items} : get all the generateItems.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of generateItems in body.
     */
    @GetMapping("")
    public List<GenerateItem> getAllGenerateItems() {
        LOG.debug("REST request to get all GenerateItems");
        return generateItemRepository.findAll();
    }

    /**
     * {@code GET  /generate-items/:id} : get the "id" generateItem.
     *
     * @param id the id of the generateItem to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the generateItem, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<GenerateItem> getGenerateItem(@PathVariable("id") Long id) {
        LOG.debug("REST request to get GenerateItem : {}", id);
        Optional<GenerateItem> generateItem = generateItemRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(generateItem);
    }

    /**
     * {@code DELETE  /generate-items/:id} : delete the "id" generateItem.
     *
     * @param id the id of the generateItem to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenerateItem(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete GenerateItem : {}", id);
        generateItemRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
