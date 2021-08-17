package com.dataely.app.service.impl;

import com.dataely.app.domain.RelatedTableColumn;
import com.dataely.app.repository.RelatedTableColumnRepository;
import com.dataely.app.service.RelatedTableColumnService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RelatedTableColumn}.
 */
@Service
@Transactional
public class RelatedTableColumnServiceImpl implements RelatedTableColumnService {

    private final Logger log = LoggerFactory.getLogger(RelatedTableColumnServiceImpl.class);

    private final RelatedTableColumnRepository relatedTableColumnRepository;

    public RelatedTableColumnServiceImpl(RelatedTableColumnRepository relatedTableColumnRepository) {
        this.relatedTableColumnRepository = relatedTableColumnRepository;
    }

    @Override
    public RelatedTableColumn save(RelatedTableColumn relatedTableColumn) {
        log.debug("Request to save RelatedTableColumn : {}", relatedTableColumn);
        return relatedTableColumnRepository.save(relatedTableColumn);
    }

    @Override
    public Optional<RelatedTableColumn> partialUpdate(RelatedTableColumn relatedTableColumn) {
        log.debug("Request to partially update RelatedTableColumn : {}", relatedTableColumn);

        return relatedTableColumnRepository
            .findById(relatedTableColumn.getId())
            .map(
                existingRelatedTableColumn -> {
                    if (relatedTableColumn.getColumnName() != null) {
                        existingRelatedTableColumn.setColumnName(relatedTableColumn.getColumnName());
                    }
                    if (relatedTableColumn.getColumnType() != null) {
                        existingRelatedTableColumn.setColumnType(relatedTableColumn.getColumnType());
                    }
                    if (relatedTableColumn.getCreationDate() != null) {
                        existingRelatedTableColumn.setCreationDate(relatedTableColumn.getCreationDate());
                    }
                    if (relatedTableColumn.getLastUpdated() != null) {
                        existingRelatedTableColumn.setLastUpdated(relatedTableColumn.getLastUpdated());
                    }

                    return existingRelatedTableColumn;
                }
            )
            .map(relatedTableColumnRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RelatedTableColumn> findAll(Pageable pageable) {
        log.debug("Request to get all RelatedTableColumns");
        return relatedTableColumnRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RelatedTableColumn> findOne(Long id) {
        log.debug("Request to get RelatedTableColumn : {}", id);
        return relatedTableColumnRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RelatedTableColumn : {}", id);
        relatedTableColumnRepository.deleteById(id);
    }
}
