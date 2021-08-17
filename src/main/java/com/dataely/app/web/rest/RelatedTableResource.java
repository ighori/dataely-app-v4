package com.dataely.app.web.rest;

import com.dataely.app.domain.RelatedTable;
import com.dataely.app.repository.RelatedTableRepository;
import com.dataely.app.service.RelatedTableService;
import com.dataely.app.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
 * REST controller for managing {@link com.dataely.app.domain.RelatedTable}.
 */
@RestController
@RequestMapping("/api")
public class RelatedTableResource {

    private final Logger log = LoggerFactory.getLogger(RelatedTableResource.class);

    private static final String ENTITY_NAME = "dataelyAppRelatedTable";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RelatedTableService relatedTableService;

    private final RelatedTableRepository relatedTableRepository;

    public RelatedTableResource(RelatedTableService relatedTableService, RelatedTableRepository relatedTableRepository) {
        this.relatedTableService = relatedTableService;
        this.relatedTableRepository = relatedTableRepository;
    }

    /**
     * {@code POST  /related-tables} : Create a new relatedTable.
     *
     * @param relatedTable the relatedTable to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new relatedTable, or with status {@code 400 (Bad Request)} if the relatedTable has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/related-tables")
    public ResponseEntity<RelatedTable> createRelatedTable(@Valid @RequestBody RelatedTable relatedTable) throws URISyntaxException {
        log.debug("REST request to save RelatedTable : {}", relatedTable);
        if (relatedTable.getId() != null) {
            throw new BadRequestAlertException("A new relatedTable cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RelatedTable result = relatedTableService.save(relatedTable);
        return ResponseEntity
            .created(new URI("/api/related-tables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /related-tables/:id} : Updates an existing relatedTable.
     *
     * @param id the id of the relatedTable to save.
     * @param relatedTable the relatedTable to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated relatedTable,
     * or with status {@code 400 (Bad Request)} if the relatedTable is not valid,
     * or with status {@code 500 (Internal Server Error)} if the relatedTable couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/related-tables/{id}")
    public ResponseEntity<RelatedTable> updateRelatedTable(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody RelatedTable relatedTable
    ) throws URISyntaxException {
        log.debug("REST request to update RelatedTable : {}, {}", id, relatedTable);
        if (relatedTable.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, relatedTable.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!relatedTableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RelatedTable result = relatedTableService.save(relatedTable);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, relatedTable.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /related-tables/:id} : Partial updates given fields of an existing relatedTable, field will ignore if it is null
     *
     * @param id the id of the relatedTable to save.
     * @param relatedTable the relatedTable to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated relatedTable,
     * or with status {@code 400 (Bad Request)} if the relatedTable is not valid,
     * or with status {@code 404 (Not Found)} if the relatedTable is not found,
     * or with status {@code 500 (Internal Server Error)} if the relatedTable couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/related-tables/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<RelatedTable> partialUpdateRelatedTable(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody RelatedTable relatedTable
    ) throws URISyntaxException {
        log.debug("REST request to partial update RelatedTable partially : {}, {}", id, relatedTable);
        if (relatedTable.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, relatedTable.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!relatedTableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RelatedTable> result = relatedTableService.partialUpdate(relatedTable);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, relatedTable.getId().toString())
        );
    }

    /**
     * {@code GET  /related-tables} : get all the relatedTables.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of relatedTables in body.
     */
    @GetMapping("/related-tables")
    public ResponseEntity<List<RelatedTable>> getAllRelatedTables(Pageable pageable) {
        log.debug("REST request to get a page of RelatedTables");
        Page<RelatedTable> page = relatedTableService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /related-tables/:id} : get the "id" relatedTable.
     *
     * @param id the id of the relatedTable to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the relatedTable, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/related-tables/{id}")
    public ResponseEntity<RelatedTable> getRelatedTable(@PathVariable Long id) {
        log.debug("REST request to get RelatedTable : {}", id);
        Optional<RelatedTable> relatedTable = relatedTableService.findOne(id);
        return ResponseUtil.wrapOrNotFound(relatedTable);
    }

    /**
     * {@code DELETE  /related-tables/:id} : delete the "id" relatedTable.
     *
     * @param id the id of the relatedTable to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/related-tables/{id}")
    public ResponseEntity<Void> deleteRelatedTable(@PathVariable Long id) {
        log.debug("REST request to delete RelatedTable : {}", id);
        relatedTableService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
