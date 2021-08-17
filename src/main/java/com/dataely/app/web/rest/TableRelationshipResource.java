package com.dataely.app.web.rest;

import com.dataely.app.domain.TableRelationship;
import com.dataely.app.repository.TableRelationshipRepository;
import com.dataely.app.service.TableRelationshipService;
import com.dataely.app.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.dataely.app.domain.TableRelationship}.
 */
@RestController
@RequestMapping("/api")
public class TableRelationshipResource {

    private final Logger log = LoggerFactory.getLogger(TableRelationshipResource.class);

    private static final String ENTITY_NAME = "dataelyAppTableRelationship";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TableRelationshipService tableRelationshipService;

    private final TableRelationshipRepository tableRelationshipRepository;

    public TableRelationshipResource(
        TableRelationshipService tableRelationshipService,
        TableRelationshipRepository tableRelationshipRepository
    ) {
        this.tableRelationshipService = tableRelationshipService;
        this.tableRelationshipRepository = tableRelationshipRepository;
    }

    /**
     * {@code POST  /table-relationships} : Create a new tableRelationship.
     *
     * @param tableRelationship the tableRelationship to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tableRelationship, or with status {@code 400 (Bad Request)} if the tableRelationship has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/table-relationships")
    public ResponseEntity<TableRelationship> createTableRelationship(@RequestBody TableRelationship tableRelationship)
        throws URISyntaxException {
        log.debug("REST request to save TableRelationship : {}", tableRelationship);
        if (tableRelationship.getId() != null) {
            throw new BadRequestAlertException("A new tableRelationship cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TableRelationship result = tableRelationshipService.save(tableRelationship);
        return ResponseEntity
            .created(new URI("/api/table-relationships/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /table-relationships/:id} : Updates an existing tableRelationship.
     *
     * @param id the id of the tableRelationship to save.
     * @param tableRelationship the tableRelationship to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tableRelationship,
     * or with status {@code 400 (Bad Request)} if the tableRelationship is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tableRelationship couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/table-relationships/{id}")
    public ResponseEntity<TableRelationship> updateTableRelationship(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TableRelationship tableRelationship
    ) throws URISyntaxException {
        log.debug("REST request to update TableRelationship : {}, {}", id, tableRelationship);
        if (tableRelationship.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tableRelationship.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tableRelationshipRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TableRelationship result = tableRelationshipService.save(tableRelationship);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tableRelationship.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /table-relationships/:id} : Partial updates given fields of an existing tableRelationship, field will ignore if it is null
     *
     * @param id the id of the tableRelationship to save.
     * @param tableRelationship the tableRelationship to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tableRelationship,
     * or with status {@code 400 (Bad Request)} if the tableRelationship is not valid,
     * or with status {@code 404 (Not Found)} if the tableRelationship is not found,
     * or with status {@code 500 (Internal Server Error)} if the tableRelationship couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/table-relationships/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TableRelationship> partialUpdateTableRelationship(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TableRelationship tableRelationship
    ) throws URISyntaxException {
        log.debug("REST request to partial update TableRelationship partially : {}, {}", id, tableRelationship);
        if (tableRelationship.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tableRelationship.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tableRelationshipRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TableRelationship> result = tableRelationshipService.partialUpdate(tableRelationship);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tableRelationship.getId().toString())
        );
    }

    /**
     * {@code GET  /table-relationships} : get all the tableRelationships.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tableRelationships in body.
     */
    @GetMapping("/table-relationships")
    public ResponseEntity<List<TableRelationship>> getAllTableRelationships(Pageable pageable) {
        log.debug("REST request to get a page of TableRelationships");
        Page<TableRelationship> page = tableRelationshipService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /table-relationships/:id} : get the "id" tableRelationship.
     *
     * @param id the id of the tableRelationship to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tableRelationship, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/table-relationships/{id}")
    public ResponseEntity<TableRelationship> getTableRelationship(@PathVariable Long id) {
        log.debug("REST request to get TableRelationship : {}", id);
        Optional<TableRelationship> tableRelationship = tableRelationshipService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tableRelationship);
    }

    /**
     * {@code DELETE  /table-relationships/:id} : delete the "id" tableRelationship.
     *
     * @param id the id of the tableRelationship to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/table-relationships/{id}")
    public ResponseEntity<Void> deleteTableRelationship(@PathVariable Long id) {
        log.debug("REST request to delete TableRelationship : {}", id);
        tableRelationshipService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
