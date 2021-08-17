package com.dataely.app.service.impl;

import com.dataely.app.domain.RelatedTable;
import com.dataely.app.repository.RelatedTableRepository;
import com.dataely.app.service.RelatedTableService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RelatedTable}.
 */
@Service
@Transactional
public class RelatedTableServiceImpl implements RelatedTableService {

    private final Logger log = LoggerFactory.getLogger(RelatedTableServiceImpl.class);

    private final RelatedTableRepository relatedTableRepository;

    public RelatedTableServiceImpl(RelatedTableRepository relatedTableRepository) {
        this.relatedTableRepository = relatedTableRepository;
    }

    @Override
    public RelatedTable save(RelatedTable relatedTable) {
        log.debug("Request to save RelatedTable : {}", relatedTable);
        return relatedTableRepository.save(relatedTable);
    }

    @Override
    public Optional<RelatedTable> partialUpdate(RelatedTable relatedTable) {
        log.debug("Request to partially update RelatedTable : {}", relatedTable);

        return relatedTableRepository
            .findById(relatedTable.getId())
            .map(
                existingRelatedTable -> {
                    if (relatedTable.getTableName() != null) {
                        existingRelatedTable.setTableName(relatedTable.getTableName());
                    }
                    if (relatedTable.getCreationDate() != null) {
                        existingRelatedTable.setCreationDate(relatedTable.getCreationDate());
                    }
                    if (relatedTable.getLastUpdated() != null) {
                        existingRelatedTable.setLastUpdated(relatedTable.getLastUpdated());
                    }

                    return existingRelatedTable;
                }
            )
            .map(relatedTableRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RelatedTable> findAll(Pageable pageable) {
        log.debug("Request to get all RelatedTables");
        return relatedTableRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RelatedTable> findOne(Long id) {
        log.debug("Request to get RelatedTable : {}", id);
        return relatedTableRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RelatedTable : {}", id);
        relatedTableRepository.deleteById(id);
    }
}
