package com.dataely.app.service;

import com.dataely.app.domain.Environment;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Environment}.
 */
public interface EnvironmentService {
    /**
     * Save a environment.
     *
     * @param environment the entity to save.
     * @return the persisted entity.
     */
    Environment save(Environment environment);

    /**
     * Partially updates a environment.
     *
     * @param environment the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Environment> partialUpdate(Environment environment);

    /**
     * Get all the environments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Environment> findAll(Pageable pageable);

    /**
     * Get the "id" environment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Environment> findOne(Long id);

    /**
     * Delete the "id" environment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
