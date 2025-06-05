package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.GenerateList;
import com.mycompany.myapp.repository.GenerateListRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.GenerateList}.
 */
@RestController
@RequestMapping("/api/generate-lists")
@Transactional
public class GenerateListResource {

    private static final Logger LOG = LoggerFactory.getLogger(GenerateListResource.class);

    private static final String ENTITY_NAME = "generateList";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GenerateListRepository generateListRepository;

    public GenerateListResource(GenerateListRepository generateListRepository) {
        this.generateListRepository = generateListRepository;
    }

    /**
     * {@code POST  /generate-lists} : Create a new generateList.
     *
     * @param generateList the generateList to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new generateList, or with status {@code 400 (Bad Request)} if the generateList has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<GenerateList> createGenerateList(@RequestBody GenerateList generateList) throws URISyntaxException {
        LOG.debug("REST request to save GenerateList : {}", generateList);
        if (generateList.getId() != null) {
            throw new BadRequestAlertException("A new generateList cannot already have an ID", ENTITY_NAME, "idexists");
        }
        generateList = generateListRepository.save(generateList);
        return ResponseEntity.created(new URI("/api/generate-lists/" + generateList.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, generateList.getId().toString()))
            .body(generateList);
    }

    /**
     * {@code PUT  /generate-lists/:id} : Updates an existing generateList.
     *
     * @param id the id of the generateList to save.
     * @param generateList the generateList to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated generateList,
     * or with status {@code 400 (Bad Request)} if the generateList is not valid,
     * or with status {@code 500 (Internal Server Error)} if the generateList couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<GenerateList> updateGenerateList(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody GenerateList generateList
    ) throws URISyntaxException {
        LOG.debug("REST request to update GenerateList : {}, {}", id, generateList);
        if (generateList.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, generateList.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!generateListRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        generateList = generateListRepository.save(generateList);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, generateList.getId().toString()))
            .body(generateList);
    }

    /**
     * {@code PATCH  /generate-lists/:id} : Partial updates given fields of an existing generateList, field will ignore if it is null
     *
     * @param id the id of the generateList to save.
     * @param generateList the generateList to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated generateList,
     * or with status {@code 400 (Bad Request)} if the generateList is not valid,
     * or with status {@code 404 (Not Found)} if the generateList is not found,
     * or with status {@code 500 (Internal Server Error)} if the generateList couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<GenerateList> partialUpdateGenerateList(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody GenerateList generateList
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update GenerateList partially : {}, {}", id, generateList);
        if (generateList.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, generateList.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!generateListRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<GenerateList> result = generateListRepository
            .findById(generateList.getId())
            .map(existingGenerateList -> {
                if (generateList.getTitle() != null) {
                    existingGenerateList.setTitle(generateList.getTitle());
                }

                return existingGenerateList;
            })
            .map(generateListRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, generateList.getId().toString())
        );
    }

    /**
     * {@code GET  /generate-lists} : get all the generateLists.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of generateLists in body.
     */
    @GetMapping("")
    public List<GenerateList> getAllGenerateLists() {
        LOG.debug("REST request to get all GenerateLists");
        return generateListRepository.findAll();
    }

    /**
     * {@code GET  /generate-lists/:id} : get the "id" generateList.
     *
     * @param id the id of the generateList to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the generateList, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<GenerateList> getGenerateList(@PathVariable("id") Long id) {
        LOG.debug("REST request to get GenerateList : {}", id);
        Optional<GenerateList> generateList = generateListRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(generateList);
    }

    /**
     * {@code DELETE  /generate-lists/:id} : delete the "id" generateList.
     *
     * @param id the id of the generateList to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenerateList(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete GenerateList : {}", id);
        generateListRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
