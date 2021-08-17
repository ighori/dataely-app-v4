package com.dataely.app.service;

import com.dataely.app.domain.RelatedTable;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link RelatedTable}.
 */
public interface RelatedTableService {
    /**
     * Save a relatedTable.
     *
     * @param relatedTable the entity to save.
     * @return the persisted entity.
     */
    RelatedTable save(RelatedTable relatedTable);

    /**
     * Partially updates a relatedTable.
     *
     * @param relatedTable the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RelatedTable> partialUpdate(RelatedTable relatedTable);

    /**
     * Get all the relatedTables.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RelatedTable> findAll(Pageable pageable);

    /**
     * Get the "id" relatedTable.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RelatedTable> findOne(Long id);

    /**
     * Delete the "id" relatedTable.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
