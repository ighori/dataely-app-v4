package com.dataely.app.service;

import com.dataely.app.domain.TableColumn;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link TableColumn}.
 */
public interface TableColumnService {
    /**
     * Save a tableColumn.
     *
     * @param tableColumn the entity to save.
     * @return the persisted entity.
     */
    TableColumn save(TableColumn tableColumn);

    /**
     * Partially updates a tableColumn.
     *
     * @param tableColumn the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TableColumn> partialUpdate(TableColumn tableColumn);

    /**
     * Get all the tableColumns.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TableColumn> findAll(Pageable pageable);

    /**
     * Get the "id" tableColumn.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TableColumn> findOne(Long id);

    /**
     * Delete the "id" tableColumn.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
