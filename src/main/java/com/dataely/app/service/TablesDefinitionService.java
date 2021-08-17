package com.dataely.app.service;

import com.dataely.app.domain.TablesDefinition;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link TablesDefinition}.
 */
public interface TablesDefinitionService {
    /**
     * Save a tablesDefinition.
     *
     * @param tablesDefinition the entity to save.
     * @return the persisted entity.
     */
    TablesDefinition save(TablesDefinition tablesDefinition);

    /**
     * Partially updates a tablesDefinition.
     *
     * @param tablesDefinition the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TablesDefinition> partialUpdate(TablesDefinition tablesDefinition);

    /**
     * Get all the tablesDefinitions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TablesDefinition> findAll(Pageable pageable);

    /**
     * Get the "id" tablesDefinition.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TablesDefinition> findOne(Long id);

    /**
     * Delete the "id" tablesDefinition.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
