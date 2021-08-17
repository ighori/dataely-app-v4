package com.dataely.app.web.rest;

import com.dataely.app.domain.FileConfig;
import com.dataely.app.repository.FileConfigRepository;
import com.dataely.app.service.FileConfigService;
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
 * REST controller for managing {@link com.dataely.app.domain.FileConfig}.
 */
@RestController
@RequestMapping("/api")
public class FileConfigResource {

    private final Logger log = LoggerFactory.getLogger(FileConfigResource.class);

    private static final String ENTITY_NAME = "dataelyAppFileConfig";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FileConfigService fileConfigService;

    private final FileConfigRepository fileConfigRepository;

    public FileConfigResource(FileConfigService fileConfigService, FileConfigRepository fileConfigRepository) {
        this.fileConfigService = fileConfigService;
        this.fileConfigRepository = fileConfigRepository;
    }

    /**
     * {@code POST  /file-configs} : Create a new fileConfig.
     *
     * @param fileConfig the fileConfig to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fileConfig, or with status {@code 400 (Bad Request)} if the fileConfig has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/file-configs")
    public ResponseEntity<FileConfig> createFileConfig(@Valid @RequestBody FileConfig fileConfig) throws URISyntaxException {
        log.debug("REST request to save FileConfig : {}", fileConfig);
        if (fileConfig.getId() != null) {
            throw new BadRequestAlertException("A new fileConfig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FileConfig result = fileConfigService.save(fileConfig);
        return ResponseEntity
            .created(new URI("/api/file-configs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /file-configs/:id} : Updates an existing fileConfig.
     *
     * @param id the id of the fileConfig to save.
     * @param fileConfig the fileConfig to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fileConfig,
     * or with status {@code 400 (Bad Request)} if the fileConfig is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fileConfig couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/file-configs/{id}")
    public ResponseEntity<FileConfig> updateFileConfig(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FileConfig fileConfig
    ) throws URISyntaxException {
        log.debug("REST request to update FileConfig : {}, {}", id, fileConfig);
        if (fileConfig.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fileConfig.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fileConfigRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FileConfig result = fileConfigService.save(fileConfig);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fileConfig.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /file-configs/:id} : Partial updates given fields of an existing fileConfig, field will ignore if it is null
     *
     * @param id the id of the fileConfig to save.
     * @param fileConfig the fileConfig to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fileConfig,
     * or with status {@code 400 (Bad Request)} if the fileConfig is not valid,
     * or with status {@code 404 (Not Found)} if the fileConfig is not found,
     * or with status {@code 500 (Internal Server Error)} if the fileConfig couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/file-configs/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<FileConfig> partialUpdateFileConfig(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FileConfig fileConfig
    ) throws URISyntaxException {
        log.debug("REST request to partial update FileConfig partially : {}, {}", id, fileConfig);
        if (fileConfig.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fileConfig.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fileConfigRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FileConfig> result = fileConfigService.partialUpdate(fileConfig);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fileConfig.getId().toString())
        );
    }

    /**
     * {@code GET  /file-configs} : get all the fileConfigs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fileConfigs in body.
     */
    @GetMapping("/file-configs")
    public ResponseEntity<List<FileConfig>> getAllFileConfigs(Pageable pageable) {
        log.debug("REST request to get a page of FileConfigs");
        Page<FileConfig> page = fileConfigService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /file-configs/:id} : get the "id" fileConfig.
     *
     * @param id the id of the fileConfig to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fileConfig, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/file-configs/{id}")
    public ResponseEntity<FileConfig> getFileConfig(@PathVariable Long id) {
        log.debug("REST request to get FileConfig : {}", id);
        Optional<FileConfig> fileConfig = fileConfigService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fileConfig);
    }

    /**
     * {@code DELETE  /file-configs/:id} : delete the "id" fileConfig.
     *
     * @param id the id of the fileConfig to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/file-configs/{id}")
    public ResponseEntity<Void> deleteFileConfig(@PathVariable Long id) {
        log.debug("REST request to delete FileConfig : {}", id);
        fileConfigService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
