package com.dataely.app.web.rest;

import com.dataely.app.domain.RelatedTableColumn;
import com.dataely.app.repository.RelatedTableColumnRepository;
import com.dataely.app.service.RelatedTableColumnService;
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
 * REST controller for managing {@link com.dataely.app.domain.RelatedTableColumn}.
 */
@RestController
@RequestMapping("/api")
public class RelatedTableColumnResource {

    private final Logger log = LoggerFactory.getLogger(RelatedTableColumnResource.class);

    private static final String ENTITY_NAME = "dataelyAppRelatedTableColumn";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RelatedTableColumnService relatedTableColumnService;

    private final RelatedTableColumnRepository relatedTableColumnRepository;

    public RelatedTableColumnResource(
        RelatedTableColumnService relatedTableColumnService,
        RelatedTableColumnRepository relatedTableColumnRepository
    ) {
        this.relatedTableColumnService = relatedTableColumnService;
        this.relatedTableColumnRepository = relatedTableColumnRepository;
    }

    /**
     * {@code POST  /related-table-columns} : Create a new relatedTableColumn.
     *
     * @param relatedTableColumn the relatedTableColumn to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new relatedTableColumn, or with status {@code 400 (Bad Request)} if the relatedTableColumn has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/related-table-columns")
    public ResponseEntity<RelatedTableColumn> createRelatedTableColumn(@Valid @RequestBody RelatedTableColumn relatedTableColumn)
        throws URISyntaxException {
        log.debug("REST request to save RelatedTableColumn : {}", relatedTableColumn);
        if (relatedTableColumn.getId() != null) {
            throw new BadRequestAlertException("A new relatedTableColumn cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RelatedTableColumn result = relatedTableColumnService.save(relatedTableColumn);
        return ResponseEntity
            .created(new URI("/api/related-table-columns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /related-table-columns/:id} : Updates an existing relatedTableColumn.
     *
     * @param id the id of the relatedTableColumn to save.
     * @param relatedTableColumn the relatedTableColumn to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated relatedTableColumn,
     * or with status {@code 400 (Bad Request)} if the relatedTableColumn is not valid,
     * or with status {@code 500 (Internal Server Error)} if the relatedTableColumn couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/related-table-columns/{id}")
    public ResponseEntity<RelatedTableColumn> updateRelatedTableColumn(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody RelatedTableColumn relatedTableColumn
    ) throws URISyntaxException {
        log.debug("REST request to update RelatedTableColumn : {}, {}", id, relatedTableColumn);
        if (relatedTableColumn.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, relatedTableColumn.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!relatedTableColumnRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RelatedTableColumn result = relatedTableColumnService.save(relatedTableColumn);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, relatedTableColumn.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /related-table-columns/:id} : Partial updates given fields of an existing relatedTableColumn, field will ignore if it is null
     *
     * @param id the id of the relatedTableColumn to save.
     * @param relatedTableColumn the relatedTableColumn to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated relatedTableColumn,
     * or with status {@code 400 (Bad Request)} if the relatedTableColumn is not valid,
     * or with status {@code 404 (Not Found)} if the relatedTableColumn is not found,
     * or with status {@code 500 (Internal Server Error)} if the relatedTableColumn couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/related-table-columns/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<RelatedTableColumn> partialUpdateRelatedTableColumn(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody RelatedTableColumn relatedTableColumn
    ) throws URISyntaxException {
        log.debug("REST request to partial update RelatedTableColumn partially : {}, {}", id, relatedTableColumn);
        if (relatedTableColumn.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, relatedTableColumn.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!relatedTableColumnRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RelatedTableColumn> result = relatedTableColumnService.partialUpdate(relatedTableColumn);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, relatedTableColumn.getId().toString())
        );
    }

    /**
     * {@code GET  /related-table-columns} : get all the relatedTableColumns.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of relatedTableColumns in body.
     */
    @GetMapping("/related-table-columns")
    public ResponseEntity<List<RelatedTableColumn>> getAllRelatedTableColumns(Pageable pageable) {
        log.debug("REST request to get a page of RelatedTableColumns");
        Page<RelatedTableColumn> page = relatedTableColumnService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /related-table-columns/:id} : get the "id" relatedTableColumn.
     *
     * @param id the id of the relatedTableColumn to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the relatedTableColumn, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/related-table-columns/{id}")
    public ResponseEntity<RelatedTableColumn> getRelatedTableColumn(@PathVariable Long id) {
        log.debug("REST request to get RelatedTableColumn : {}", id);
        Optional<RelatedTableColumn> relatedTableColumn = relatedTableColumnService.findOne(id);
        return ResponseUtil.wrapOrNotFound(relatedTableColumn);
    }

    /**
     * {@code DELETE  /related-table-columns/:id} : delete the "id" relatedTableColumn.
     *
     * @param id the id of the relatedTableColumn to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/related-table-columns/{id}")
    public ResponseEntity<Void> deleteRelatedTableColumn(@PathVariable Long id) {
        log.debug("REST request to delete RelatedTableColumn : {}", id);
        relatedTableColumnService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
