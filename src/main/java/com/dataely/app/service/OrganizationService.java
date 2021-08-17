package com.dataely.app.service;

import com.dataely.app.domain.Organization;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Organization}.
 */
public interface OrganizationService {
    /**
     * Save a organization.
     *
     * @param organization the entity to save.
     * @return the persisted entity.
     */
    Organization save(Organization organization);

    /**
     * Partially updates a organization.
     *
     * @param organization the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Organization> partialUpdate(Organization organization);

    /**
     * Get all the organizations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Organization> findAll(Pageable pageable);

    /**
     * Get the "id" organization.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Organization> findOne(Long id);

    /**
     * Delete the "id" organization.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
