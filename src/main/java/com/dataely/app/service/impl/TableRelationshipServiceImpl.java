package com.dataely.app.service.impl;

import com.dataely.app.domain.TableRelationship;
import com.dataely.app.repository.TableRelationshipRepository;
import com.dataely.app.service.TableRelationshipService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TableRelationship}.
 */
@Service
@Transactional
public class TableRelationshipServiceImpl implements TableRelationshipService {

    private final Logger log = LoggerFactory.getLogger(TableRelationshipServiceImpl.class);

    private final TableRelationshipRepository tableRelationshipRepository;

    public TableRelationshipServiceImpl(TableRelationshipRepository tableRelationshipRepository) {
        this.tableRelationshipRepository = tableRelationshipRepository;
    }

    @Override
    public TableRelationship save(TableRelationship tableRelationship) {
        log.debug("Request to save TableRelationship : {}", tableRelationship);
        return tableRelationshipRepository.save(tableRelationship);
    }

    @Override
    public Optional<TableRelationship> partialUpdate(TableRelationship tableRelationship) {
        log.debug("Request to partially update TableRelationship : {}", tableRelationship);

        return tableRelationshipRepository
            .findById(tableRelationship.getId())
            .map(
                existingTableRelationship -> {
                    if (tableRelationship.getSource() != null) {
                        existingTableRelationship.setSource(tableRelationship.getSource());
                    }
                    if (tableRelationship.getTarget() != null) {
                        existingTableRelationship.setTarget(tableRelationship.getTarget());
                    }
                    if (tableRelationship.getSourceKey() != null) {
                        existingTableRelationship.setSourceKey(tableRelationship.getSourceKey());
                    }
                    if (tableRelationship.getTargetKey() != null) {
                        existingTableRelationship.setTargetKey(tableRelationship.getTargetKey());
                    }
                    if (tableRelationship.getCreationDate() != null) {
                        existingTableRelationship.setCreationDate(tableRelationship.getCreationDate());
                    }
                    if (tableRelationship.getLastUpdated() != null) {
                        existingTableRelationship.setLastUpdated(tableRelationship.getLastUpdated());
                    }

                    return existingTableRelationship;
                }
            )
            .map(tableRelationshipRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TableRelationship> findAll(Pageable pageable) {
        log.debug("Request to get all TableRelationships");
        return tableRelationshipRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TableRelationship> findOne(Long id) {
        log.debug("Request to get TableRelationship : {}", id);
        return tableRelationshipRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TableRelationship : {}", id);
        tableRelationshipRepository.deleteById(id);
    }
}
