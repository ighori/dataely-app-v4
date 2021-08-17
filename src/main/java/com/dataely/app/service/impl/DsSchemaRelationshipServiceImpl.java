package com.dataely.app.service.impl;

import com.dataely.app.domain.DsSchemaRelationship;
import com.dataely.app.repository.DsSchemaRelationshipRepository;
import com.dataely.app.service.DsSchemaRelationshipService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DsSchemaRelationship}.
 */
@Service
@Transactional
public class DsSchemaRelationshipServiceImpl implements DsSchemaRelationshipService {

    private final Logger log = LoggerFactory.getLogger(DsSchemaRelationshipServiceImpl.class);

    private final DsSchemaRelationshipRepository dsSchemaRelationshipRepository;

    public DsSchemaRelationshipServiceImpl(DsSchemaRelationshipRepository dsSchemaRelationshipRepository) {
        this.dsSchemaRelationshipRepository = dsSchemaRelationshipRepository;
    }

    @Override
    public DsSchemaRelationship save(DsSchemaRelationship dsSchemaRelationship) {
        log.debug("Request to save DsSchemaRelationship : {}", dsSchemaRelationship);
        return dsSchemaRelationshipRepository.save(dsSchemaRelationship);
    }

    @Override
    public Optional<DsSchemaRelationship> partialUpdate(DsSchemaRelationship dsSchemaRelationship) {
        log.debug("Request to partially update DsSchemaRelationship : {}", dsSchemaRelationship);

        return dsSchemaRelationshipRepository
            .findById(dsSchemaRelationship.getId())
            .map(
                existingDsSchemaRelationship -> {
                    if (dsSchemaRelationship.getSource() != null) {
                        existingDsSchemaRelationship.setSource(dsSchemaRelationship.getSource());
                    }
                    if (dsSchemaRelationship.getTarget() != null) {
                        existingDsSchemaRelationship.setTarget(dsSchemaRelationship.getTarget());
                    }
                    if (dsSchemaRelationship.getCreationDate() != null) {
                        existingDsSchemaRelationship.setCreationDate(dsSchemaRelationship.getCreationDate());
                    }
                    if (dsSchemaRelationship.getLastUpdated() != null) {
                        existingDsSchemaRelationship.setLastUpdated(dsSchemaRelationship.getLastUpdated());
                    }

                    return existingDsSchemaRelationship;
                }
            )
            .map(dsSchemaRelationshipRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DsSchemaRelationship> findAll(Pageable pageable) {
        log.debug("Request to get all DsSchemaRelationships");
        return dsSchemaRelationshipRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DsSchemaRelationship> findOne(Long id) {
        log.debug("Request to get DsSchemaRelationship : {}", id);
        return dsSchemaRelationshipRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DsSchemaRelationship : {}", id);
        dsSchemaRelationshipRepository.deleteById(id);
    }
}
