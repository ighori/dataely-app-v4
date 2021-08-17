package com.dataely.app.web.rest;

import com.dataely.app.domain.DsSchema;
import com.dataely.app.repository.DsSchemaRepository;
import com.dataely.app.service.DsSchemaService;
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
 * REST controller for managing {@link com.dataely.app.domain.DsSchema}.
 */
@RestController
@RequestMapping("/api")
public class DsSchemaResource {

    private final Logger log = LoggerFactory.getLogger(DsSchemaResource.class);

    private static final String ENTITY_NAME = "dataelyAppDsSchema";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DsSchemaService dsSchemaService;

    private final DsSchemaRepository dsSchemaRepository;

    public DsSchemaResource(DsSchemaService dsSchemaService, DsSchemaRepository dsSchemaRepository) {
        this.dsSchemaService = dsSchemaService;
        this.dsSchemaRepository = dsSchemaRepository;
    }

    /**
     * {@code POST  /ds-schemas} : Create a new dsSchema.
     *
     * @param dsSchema the dsSchema to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dsSchema, or with status {@code 400 (Bad Request)} if the dsSchema has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ds-schemas")
    public ResponseEntity<DsSchema> createDsSchema(@Valid @RequestBody DsSchema dsSchema) throws URISyntaxException {
        log.debug("REST request to save DsSchema : {}", dsSchema);
        if (dsSchema.getId() != null) {
            throw new BadRequestAlertException("A new dsSchema cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DsSchema result = dsSchemaService.save(dsSchema);
        return ResponseEntity
            .created(new URI("/api/ds-schemas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ds-schemas/:id} : Updates an existing dsSchema.
     *
     * @param id the id of the dsSchema to save.
     * @param dsSchema the dsSchema to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dsSchema,
     * or with status {@code 400 (Bad Request)} if the dsSchema is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dsSchema couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ds-schemas/{id}")
    public ResponseEntity<DsSchema> updateDsSchema(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DsSchema dsSchema
    ) throws URISyntaxException {
        log.debug("REST request to update DsSchema : {}, {}", id, dsSchema);
        if (dsSchema.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dsSchema.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dsSchemaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DsSchema result = dsSchemaService.save(dsSchema);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, dsSchema.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /ds-schemas/:id} : Partial updates given fields of an existing dsSchema, field will ignore if it is null
     *
     * @param id the id of the dsSchema to save.
     * @param dsSchema the dsSchema to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dsSchema,
     * or with status {@code 400 (Bad Request)} if the dsSchema is not valid,
     * or with status {@code 404 (Not Found)} if the dsSchema is not found,
     * or with status {@code 500 (Internal Server Error)} if the dsSchema couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ds-schemas/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<DsSchema> partialUpdateDsSchema(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DsSchema dsSchema
    ) throws URISyntaxException {
        log.debug("REST request to partial update DsSchema partially : {}, {}", id, dsSchema);
        if (dsSchema.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dsSchema.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dsSchemaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DsSchema> result = dsSchemaService.partialUpdate(dsSchema);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, dsSchema.getId().toString())
        );
    }

    /**
     * {@code GET  /ds-schemas} : get all the dsSchemas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dsSchemas in body.
     */
    @GetMapping("/ds-schemas")
    public ResponseEntity<List<DsSchema>> getAllDsSchemas(Pageable pageable) {
        log.debug("REST request to get a page of DsSchemas");
        Page<DsSchema> page = dsSchemaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ds-schemas/:id} : get the "id" dsSchema.
     *
     * @param id the id of the dsSchema to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dsSchema, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ds-schemas/{id}")
    public ResponseEntity<DsSchema> getDsSchema(@PathVariable Long id) {
        log.debug("REST request to get DsSchema : {}", id);
        Optional<DsSchema> dsSchema = dsSchemaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dsSchema);
    }

    /**
     * {@code DELETE  /ds-schemas/:id} : delete the "id" dsSchema.
     *
     * @param id the id of the dsSchema to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ds-schemas/{id}")
    public ResponseEntity<Void> deleteDsSchema(@PathVariable Long id) {
        log.debug("REST request to delete DsSchema : {}", id);
        dsSchemaService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
