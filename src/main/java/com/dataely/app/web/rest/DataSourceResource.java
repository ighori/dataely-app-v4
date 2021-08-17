package com.dataely.app.web.rest;

import com.dataely.app.domain.DataSource;
import com.dataely.app.repository.DataSourceRepository;
import com.dataely.app.service.DataSourceService;
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
 * REST controller for managing {@link com.dataely.app.domain.DataSource}.
 */
@RestController
@RequestMapping("/api")
public class DataSourceResource {

    private final Logger log = LoggerFactory.getLogger(DataSourceResource.class);

    private static final String ENTITY_NAME = "dataelyAppDataSource";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DataSourceService dataSourceService;

    private final DataSourceRepository dataSourceRepository;

    public DataSourceResource(DataSourceService dataSourceService, DataSourceRepository dataSourceRepository) {
        this.dataSourceService = dataSourceService;
        this.dataSourceRepository = dataSourceRepository;
    }

    /**
     * {@code POST  /data-sources} : Create a new dataSource.
     *
     * @param dataSource the dataSource to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dataSource, or with status {@code 400 (Bad Request)} if the dataSource has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/data-sources")
    public ResponseEntity<DataSource> createDataSource(@Valid @RequestBody DataSource dataSource) throws URISyntaxException {
        log.debug("REST request to save DataSource : {}", dataSource);
        if (dataSource.getId() != null) {
            throw new BadRequestAlertException("A new dataSource cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DataSource result = dataSourceService.save(dataSource);
        return ResponseEntity
            .created(new URI("/api/data-sources/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /data-sources/:id} : Updates an existing dataSource.
     *
     * @param id the id of the dataSource to save.
     * @param dataSource the dataSource to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dataSource,
     * or with status {@code 400 (Bad Request)} if the dataSource is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dataSource couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/data-sources/{id}")
    public ResponseEntity<DataSource> updateDataSource(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DataSource dataSource
    ) throws URISyntaxException {
        log.debug("REST request to update DataSource : {}, {}", id, dataSource);
        if (dataSource.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dataSource.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dataSourceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DataSource result = dataSourceService.save(dataSource);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, dataSource.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /data-sources/:id} : Partial updates given fields of an existing dataSource, field will ignore if it is null
     *
     * @param id the id of the dataSource to save.
     * @param dataSource the dataSource to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dataSource,
     * or with status {@code 400 (Bad Request)} if the dataSource is not valid,
     * or with status {@code 404 (Not Found)} if the dataSource is not found,
     * or with status {@code 500 (Internal Server Error)} if the dataSource couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/data-sources/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<DataSource> partialUpdateDataSource(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DataSource dataSource
    ) throws URISyntaxException {
        log.debug("REST request to partial update DataSource partially : {}, {}", id, dataSource);
        if (dataSource.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dataSource.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dataSourceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DataSource> result = dataSourceService.partialUpdate(dataSource);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, dataSource.getId().toString())
        );
    }

    /**
     * {@code GET  /data-sources} : get all the dataSources.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dataSources in body.
     */
    @GetMapping("/data-sources")
    public ResponseEntity<List<DataSource>> getAllDataSources(Pageable pageable) {
        log.debug("REST request to get a page of DataSources");
        Page<DataSource> page = dataSourceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /data-sources/:id} : get the "id" dataSource.
     *
     * @param id the id of the dataSource to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dataSource, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/data-sources/{id}")
    public ResponseEntity<DataSource> getDataSource(@PathVariable Long id) {
        log.debug("REST request to get DataSource : {}", id);
        Optional<DataSource> dataSource = dataSourceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dataSource);
    }

    /**
     * {@code DELETE  /data-sources/:id} : delete the "id" dataSource.
     *
     * @param id the id of the dataSource to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/data-sources/{id}")
    public ResponseEntity<Void> deleteDataSource(@PathVariable Long id) {
        log.debug("REST request to delete DataSource : {}", id);
        dataSourceService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
