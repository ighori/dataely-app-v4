package com.dataely.app.web.rest;

import com.dataely.app.domain.AnalyzerAdHocRecognizers;
import com.dataely.app.repository.AnalyzerAdHocRecognizersRepository;
import com.dataely.app.service.AnalyzerAdHocRecognizersService;
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
 * REST controller for managing {@link com.dataely.app.domain.AnalyzerAdHocRecognizers}.
 */
@RestController
@RequestMapping("/api")
public class AnalyzerAdHocRecognizersResource {

    private final Logger log = LoggerFactory.getLogger(AnalyzerAdHocRecognizersResource.class);

    private static final String ENTITY_NAME = "dataelyAppAnalyzerAdHocRecognizers";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AnalyzerAdHocRecognizersService analyzerAdHocRecognizersService;

    private final AnalyzerAdHocRecognizersRepository analyzerAdHocRecognizersRepository;

    public AnalyzerAdHocRecognizersResource(
        AnalyzerAdHocRecognizersService analyzerAdHocRecognizersService,
        AnalyzerAdHocRecognizersRepository analyzerAdHocRecognizersRepository
    ) {
        this.analyzerAdHocRecognizersService = analyzerAdHocRecognizersService;
        this.analyzerAdHocRecognizersRepository = analyzerAdHocRecognizersRepository;
    }

    /**
     * {@code POST  /analyzer-ad-hoc-recognizers} : Create a new analyzerAdHocRecognizers.
     *
     * @param analyzerAdHocRecognizers the analyzerAdHocRecognizers to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new analyzerAdHocRecognizers, or with status {@code 400 (Bad Request)} if the analyzerAdHocRecognizers has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/analyzer-ad-hoc-recognizers")
    public ResponseEntity<AnalyzerAdHocRecognizers> createAnalyzerAdHocRecognizers(
        @Valid @RequestBody AnalyzerAdHocRecognizers analyzerAdHocRecognizers
    ) throws URISyntaxException {
        log.debug("REST request to save AnalyzerAdHocRecognizers : {}", analyzerAdHocRecognizers);
        if (analyzerAdHocRecognizers.getId() != null) {
            throw new BadRequestAlertException("A new analyzerAdHocRecognizers cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AnalyzerAdHocRecognizers result = analyzerAdHocRecognizersService.save(analyzerAdHocRecognizers);
        return ResponseEntity
            .created(new URI("/api/analyzer-ad-hoc-recognizers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /analyzer-ad-hoc-recognizers/:id} : Updates an existing analyzerAdHocRecognizers.
     *
     * @param id the id of the analyzerAdHocRecognizers to save.
     * @param analyzerAdHocRecognizers the analyzerAdHocRecognizers to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated analyzerAdHocRecognizers,
     * or with status {@code 400 (Bad Request)} if the analyzerAdHocRecognizers is not valid,
     * or with status {@code 500 (Internal Server Error)} if the analyzerAdHocRecognizers couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/analyzer-ad-hoc-recognizers/{id}")
    public ResponseEntity<AnalyzerAdHocRecognizers> updateAnalyzerAdHocRecognizers(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AnalyzerAdHocRecognizers analyzerAdHocRecognizers
    ) throws URISyntaxException {
        log.debug("REST request to update AnalyzerAdHocRecognizers : {}, {}", id, analyzerAdHocRecognizers);
        if (analyzerAdHocRecognizers.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, analyzerAdHocRecognizers.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!analyzerAdHocRecognizersRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AnalyzerAdHocRecognizers result = analyzerAdHocRecognizersService.save(analyzerAdHocRecognizers);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, analyzerAdHocRecognizers.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /analyzer-ad-hoc-recognizers/:id} : Partial updates given fields of an existing analyzerAdHocRecognizers, field will ignore if it is null
     *
     * @param id the id of the analyzerAdHocRecognizers to save.
     * @param analyzerAdHocRecognizers the analyzerAdHocRecognizers to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated analyzerAdHocRecognizers,
     * or with status {@code 400 (Bad Request)} if the analyzerAdHocRecognizers is not valid,
     * or with status {@code 404 (Not Found)} if the analyzerAdHocRecognizers is not found,
     * or with status {@code 500 (Internal Server Error)} if the analyzerAdHocRecognizers couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/analyzer-ad-hoc-recognizers/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<AnalyzerAdHocRecognizers> partialUpdateAnalyzerAdHocRecognizers(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AnalyzerAdHocRecognizers analyzerAdHocRecognizers
    ) throws URISyntaxException {
        log.debug("REST request to partial update AnalyzerAdHocRecognizers partially : {}, {}", id, analyzerAdHocRecognizers);
        if (analyzerAdHocRecognizers.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, analyzerAdHocRecognizers.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!analyzerAdHocRecognizersRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AnalyzerAdHocRecognizers> result = analyzerAdHocRecognizersService.partialUpdate(analyzerAdHocRecognizers);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, analyzerAdHocRecognizers.getId().toString())
        );
    }

    /**
     * {@code GET  /analyzer-ad-hoc-recognizers} : get all the analyzerAdHocRecognizers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of analyzerAdHocRecognizers in body.
     */
    @GetMapping("/analyzer-ad-hoc-recognizers")
    public ResponseEntity<List<AnalyzerAdHocRecognizers>> getAllAnalyzerAdHocRecognizers(Pageable pageable) {
        log.debug("REST request to get a page of AnalyzerAdHocRecognizers");
        Page<AnalyzerAdHocRecognizers> page = analyzerAdHocRecognizersService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /analyzer-ad-hoc-recognizers/:id} : get the "id" analyzerAdHocRecognizers.
     *
     * @param id the id of the analyzerAdHocRecognizers to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the analyzerAdHocRecognizers, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/analyzer-ad-hoc-recognizers/{id}")
    public ResponseEntity<AnalyzerAdHocRecognizers> getAnalyzerAdHocRecognizers(@PathVariable Long id) {
        log.debug("REST request to get AnalyzerAdHocRecognizers : {}", id);
        Optional<AnalyzerAdHocRecognizers> analyzerAdHocRecognizers = analyzerAdHocRecognizersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(analyzerAdHocRecognizers);
    }

    /**
     * {@code DELETE  /analyzer-ad-hoc-recognizers/:id} : delete the "id" analyzerAdHocRecognizers.
     *
     * @param id the id of the analyzerAdHocRecognizers to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/analyzer-ad-hoc-recognizers/{id}")
    public ResponseEntity<Void> deleteAnalyzerAdHocRecognizers(@PathVariable Long id) {
        log.debug("REST request to delete AnalyzerAdHocRecognizers : {}", id);
        analyzerAdHocRecognizersService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
