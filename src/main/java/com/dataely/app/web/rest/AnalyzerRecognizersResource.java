package com.dataely.app.web.rest;

import com.dataely.app.domain.AnalyzerRecognizers;
import com.dataely.app.repository.AnalyzerRecognizersRepository;
import com.dataely.app.service.AnalyzerRecognizersService;
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
 * REST controller for managing {@link com.dataely.app.domain.AnalyzerRecognizers}.
 */
@RestController
@RequestMapping("/api")
public class AnalyzerRecognizersResource {

    private final Logger log = LoggerFactory.getLogger(AnalyzerRecognizersResource.class);

    private static final String ENTITY_NAME = "dataelyAppAnalyzerRecognizers";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AnalyzerRecognizersService analyzerRecognizersService;

    private final AnalyzerRecognizersRepository analyzerRecognizersRepository;

    public AnalyzerRecognizersResource(
        AnalyzerRecognizersService analyzerRecognizersService,
        AnalyzerRecognizersRepository analyzerRecognizersRepository
    ) {
        this.analyzerRecognizersService = analyzerRecognizersService;
        this.analyzerRecognizersRepository = analyzerRecognizersRepository;
    }

    /**
     * {@code POST  /analyzer-recognizers} : Create a new analyzerRecognizers.
     *
     * @param analyzerRecognizers the analyzerRecognizers to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new analyzerRecognizers, or with status {@code 400 (Bad Request)} if the analyzerRecognizers has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/analyzer-recognizers")
    public ResponseEntity<AnalyzerRecognizers> createAnalyzerRecognizers(@Valid @RequestBody AnalyzerRecognizers analyzerRecognizers)
        throws URISyntaxException {
        log.debug("REST request to save AnalyzerRecognizers : {}", analyzerRecognizers);
        if (analyzerRecognizers.getId() != null) {
            throw new BadRequestAlertException("A new analyzerRecognizers cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AnalyzerRecognizers result = analyzerRecognizersService.save(analyzerRecognizers);
        return ResponseEntity
            .created(new URI("/api/analyzer-recognizers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /analyzer-recognizers/:id} : Updates an existing analyzerRecognizers.
     *
     * @param id the id of the analyzerRecognizers to save.
     * @param analyzerRecognizers the analyzerRecognizers to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated analyzerRecognizers,
     * or with status {@code 400 (Bad Request)} if the analyzerRecognizers is not valid,
     * or with status {@code 500 (Internal Server Error)} if the analyzerRecognizers couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/analyzer-recognizers/{id}")
    public ResponseEntity<AnalyzerRecognizers> updateAnalyzerRecognizers(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AnalyzerRecognizers analyzerRecognizers
    ) throws URISyntaxException {
        log.debug("REST request to update AnalyzerRecognizers : {}, {}", id, analyzerRecognizers);
        if (analyzerRecognizers.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, analyzerRecognizers.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!analyzerRecognizersRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AnalyzerRecognizers result = analyzerRecognizersService.save(analyzerRecognizers);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, analyzerRecognizers.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /analyzer-recognizers/:id} : Partial updates given fields of an existing analyzerRecognizers, field will ignore if it is null
     *
     * @param id the id of the analyzerRecognizers to save.
     * @param analyzerRecognizers the analyzerRecognizers to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated analyzerRecognizers,
     * or with status {@code 400 (Bad Request)} if the analyzerRecognizers is not valid,
     * or with status {@code 404 (Not Found)} if the analyzerRecognizers is not found,
     * or with status {@code 500 (Internal Server Error)} if the analyzerRecognizers couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/analyzer-recognizers/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<AnalyzerRecognizers> partialUpdateAnalyzerRecognizers(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AnalyzerRecognizers analyzerRecognizers
    ) throws URISyntaxException {
        log.debug("REST request to partial update AnalyzerRecognizers partially : {}, {}", id, analyzerRecognizers);
        if (analyzerRecognizers.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, analyzerRecognizers.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!analyzerRecognizersRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AnalyzerRecognizers> result = analyzerRecognizersService.partialUpdate(analyzerRecognizers);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, analyzerRecognizers.getId().toString())
        );
    }

    /**
     * {@code GET  /analyzer-recognizers} : get all the analyzerRecognizers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of analyzerRecognizers in body.
     */
    @GetMapping("/analyzer-recognizers")
    public ResponseEntity<List<AnalyzerRecognizers>> getAllAnalyzerRecognizers(Pageable pageable) {
        log.debug("REST request to get a page of AnalyzerRecognizers");
        Page<AnalyzerRecognizers> page = analyzerRecognizersService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /analyzer-recognizers/:id} : get the "id" analyzerRecognizers.
     *
     * @param id the id of the analyzerRecognizers to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the analyzerRecognizers, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/analyzer-recognizers/{id}")
    public ResponseEntity<AnalyzerRecognizers> getAnalyzerRecognizers(@PathVariable Long id) {
        log.debug("REST request to get AnalyzerRecognizers : {}", id);
        Optional<AnalyzerRecognizers> analyzerRecognizers = analyzerRecognizersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(analyzerRecognizers);
    }

    /**
     * {@code DELETE  /analyzer-recognizers/:id} : delete the "id" analyzerRecognizers.
     *
     * @param id the id of the analyzerRecognizers to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/analyzer-recognizers/{id}")
    public ResponseEntity<Void> deleteAnalyzerRecognizers(@PathVariable Long id) {
        log.debug("REST request to delete AnalyzerRecognizers : {}", id);
        analyzerRecognizersService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
