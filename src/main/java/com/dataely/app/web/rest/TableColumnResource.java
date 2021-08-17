package com.dataely.app.web.rest;

import com.dataely.app.domain.TableColumn;
import com.dataely.app.repository.TableColumnRepository;
import com.dataely.app.service.TableColumnService;
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
 * REST controller for managing {@link com.dataely.app.domain.TableColumn}.
 */
@RestController
@RequestMapping("/api")
public class TableColumnResource {

    private final Logger log = LoggerFactory.getLogger(TableColumnResource.class);

    private static final String ENTITY_NAME = "dataelyAppTableColumn";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TableColumnService tableColumnService;

    private final TableColumnRepository tableColumnRepository;

    public TableColumnResource(TableColumnService tableColumnService, TableColumnRepository tableColumnRepository) {
        this.tableColumnService = tableColumnService;
        this.tableColumnRepository = tableColumnRepository;
    }

    /**
     * {@code POST  /table-columns} : Create a new tableColumn.
     *
     * @param tableColumn the tableColumn to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tableColumn, or with status {@code 400 (Bad Request)} if the tableColumn has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/table-columns")
    public ResponseEntity<TableColumn> createTableColumn(@Valid @RequestBody TableColumn tableColumn) throws URISyntaxException {
        log.debug("REST request to save TableColumn : {}", tableColumn);
        if (tableColumn.getId() != null) {
            throw new BadRequestAlertException("A new tableColumn cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TableColumn result = tableColumnService.save(tableColumn);
        return ResponseEntity
            .created(new URI("/api/table-columns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /table-columns/:id} : Updates an existing tableColumn.
     *
     * @param id the id of the tableColumn to save.
     * @param tableColumn the tableColumn to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tableColumn,
     * or with status {@code 400 (Bad Request)} if the tableColumn is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tableColumn couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/table-columns/{id}")
    public ResponseEntity<TableColumn> updateTableColumn(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TableColumn tableColumn
    ) throws URISyntaxException {
        log.debug("REST request to update TableColumn : {}, {}", id, tableColumn);
        if (tableColumn.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tableColumn.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tableColumnRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TableColumn result = tableColumnService.save(tableColumn);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tableColumn.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /table-columns/:id} : Partial updates given fields of an existing tableColumn, field will ignore if it is null
     *
     * @param id the id of the tableColumn to save.
     * @param tableColumn the tableColumn to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tableColumn,
     * or with status {@code 400 (Bad Request)} if the tableColumn is not valid,
     * or with status {@code 404 (Not Found)} if the tableColumn is not found,
     * or with status {@code 500 (Internal Server Error)} if the tableColumn couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/table-columns/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TableColumn> partialUpdateTableColumn(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TableColumn tableColumn
    ) throws URISyntaxException {
        log.debug("REST request to partial update TableColumn partially : {}, {}", id, tableColumn);
        if (tableColumn.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tableColumn.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tableColumnRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TableColumn> result = tableColumnService.partialUpdate(tableColumn);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tableColumn.getId().toString())
        );
    }

    /**
     * {@code GET  /table-columns} : get all the tableColumns.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tableColumns in body.
     */
    @GetMapping("/table-columns")
    public ResponseEntity<List<TableColumn>> getAllTableColumns(Pageable pageable) {
        log.debug("REST request to get a page of TableColumns");
        Page<TableColumn> page = tableColumnService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /table-columns/:id} : get the "id" tableColumn.
     *
     * @param id the id of the tableColumn to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tableColumn, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/table-columns/{id}")
    public ResponseEntity<TableColumn> getTableColumn(@PathVariable Long id) {
        log.debug("REST request to get TableColumn : {}", id);
        Optional<TableColumn> tableColumn = tableColumnService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tableColumn);
    }

    /**
     * {@code DELETE  /table-columns/:id} : delete the "id" tableColumn.
     *
     * @param id the id of the tableColumn to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/table-columns/{id}")
    public ResponseEntity<Void> deleteTableColumn(@PathVariable Long id) {
        log.debug("REST request to delete TableColumn : {}", id);
        tableColumnService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
