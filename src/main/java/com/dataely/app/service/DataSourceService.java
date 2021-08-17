package com.dataely.app.service;

import com.dataely.app.domain.DataSource;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link DataSource}.
 */
public interface DataSourceService {
    /**
     * Save a dataSource.
     *
     * @param dataSource the entity to save.
     * @return the persisted entity.
     */
    DataSource save(DataSource dataSource);

    /**
     * Partially updates a dataSource.
     *
     * @param dataSource the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DataSource> partialUpdate(DataSource dataSource);

    /**
     * Get all the dataSources.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DataSource> findAll(Pageable pageable);

    /**
     * Get the "id" dataSource.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DataSource> findOne(Long id);

    /**
     * Delete the "id" dataSource.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
