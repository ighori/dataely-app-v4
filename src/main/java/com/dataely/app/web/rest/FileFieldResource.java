package com.dataely.app.web.rest;

import com.dataely.app.domain.FileField;
import com.dataely.app.repository.FileFieldRepository;
import com.dataely.app.service.FileFieldService;
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
 * REST controller for managing {@link com.dataely.app.domain.FileField}.
 */
@RestController
@RequestMapping("/api")
public class FileFieldResource {

    private final Logger log = LoggerFactory.getLogger(FileFieldResource.class);

    private static final String ENTITY_NAME = "dataelyAppFileField";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FileFieldService fileFieldService;

    private final FileFieldRepository fileFieldRepository;

    public FileFieldResource(FileFieldService fileFieldService, FileFieldRepository fileFieldRepository) {
        this.fileFieldService = fileFieldService;
        this.fileFieldRepository = fileFieldRepository;
    }

    /**
     * {@code POST  /file-fields} : Create a new fileField.
     *
     * @param fileField the fileField to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fileField, or with status {@code 400 (Bad Request)} if the fileField has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/file-fields")
    public ResponseEntity<FileField> createFileField(@Valid @RequestBody FileField fileField) throws URISyntaxException {
        log.debug("REST request to save FileField : {}", fileField);
        if (fileField.getId() != null) {
            throw new BadRequestAlertException("A new fileField cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FileField result = fileFieldService.save(fileField);
        return ResponseEntity
            .created(new URI("/api/file-fields/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /file-fields/:id} : Updates an existing fileField.
     *
     * @param id the id of the fileField to save.
     * @param fileField the fileField to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fileField,
     * or with status {@code 400 (Bad Request)} if the fileField is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fileField couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/file-fields/{id}")
    public ResponseEntity<FileField> updateFileField(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FileField fileField
    ) throws URISyntaxException {
        log.debug("REST request to update FileField : {}, {}", id, fileField);
        if (fileField.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fileField.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fileFieldRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FileField result = fileFieldService.save(fileField);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fileField.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /file-fields/:id} : Partial updates given fields of an existing fileField, field will ignore if it is null
     *
     * @param id the id of the fileField to save.
     * @param fileField the fileField to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fileField,
     * or with status {@code 400 (Bad Request)} if the fileField is not valid,
     * or with status {@code 404 (Not Found)} if the fileField is not found,
     * or with status {@code 500 (Internal Server Error)} if the fileField couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/file-fields/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<FileField> partialUpdateFileField(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FileField fileField
    ) throws URISyntaxException {
        log.debug("REST request to partial update FileField partially : {}, {}", id, fileField);
        if (fileField.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fileField.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fileFieldRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FileField> result = fileFieldService.partialUpdate(fileField);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fileField.getId().toString())
        );
    }

    /**
     * {@code GET  /file-fields} : get all the fileFields.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fileFields in body.
     */
    @GetMapping("/file-fields")
    public ResponseEntity<List<FileField>> getAllFileFields(Pageable pageable) {
        log.debug("REST request to get a page of FileFields");
        Page<FileField> page = fileFieldService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /file-fields/:id} : get the "id" fileField.
     *
     * @param id the id of the fileField to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fileField, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/file-fields/{id}")
    public ResponseEntity<FileField> getFileField(@PathVariable Long id) {
        log.debug("REST request to get FileField : {}", id);
        Optional<FileField> fileField = fileFieldService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fileField);
    }

    /**
     * {@code DELETE  /file-fields/:id} : delete the "id" fileField.
     *
     * @param id the id of the fileField to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/file-fields/{id}")
    public ResponseEntity<Void> deleteFileField(@PathVariable Long id) {
        log.debug("REST request to delete FileField : {}", id);
        fileFieldService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
