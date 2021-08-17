package com.dataely.app.service;

import com.dataely.app.domain.RelatedTableColumn;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link RelatedTableColumn}.
 */
public interface RelatedTableColumnService {
    /**
     * Save a relatedTableColumn.
     *
     * @param relatedTableColumn the entity to save.
     * @return the persisted entity.
     */
    RelatedTableColumn save(RelatedTableColumn relatedTableColumn);

    /**
     * Partially updates a relatedTableColumn.
     *
     * @param relatedTableColumn the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RelatedTableColumn> partialUpdate(RelatedTableColumn relatedTableColumn);

    /**
     * Get all the relatedTableColumns.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RelatedTableColumn> findAll(Pageable pageable);

    /**
     * Get the "id" relatedTableColumn.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RelatedTableColumn> findOne(Long id);

    /**
     * Delete the "id" relatedTableColumn.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
