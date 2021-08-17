package com.dataely.app.service.impl;

import com.dataely.app.domain.DsSchema;
import com.dataely.app.repository.DsSchemaRepository;
import com.dataely.app.service.DsSchemaService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DsSchema}.
 */
@Service
@Transactional
public class DsSchemaServiceImpl implements DsSchemaService {

    private final Logger log = LoggerFactory.getLogger(DsSchemaServiceImpl.class);

    private final DsSchemaRepository dsSchemaRepository;

    public DsSchemaServiceImpl(DsSchemaRepository dsSchemaRepository) {
        this.dsSchemaRepository = dsSchemaRepository;
    }

    @Override
    public DsSchema save(DsSchema dsSchema) {
        log.debug("Request to save DsSchema : {}", dsSchema);
        return dsSchemaRepository.save(dsSchema);
    }

    @Override
    public Optional<DsSchema> partialUpdate(DsSchema dsSchema) {
        log.debug("Request to partially update DsSchema : {}", dsSchema);

        return dsSchemaRepository
            .findById(dsSchema.getId())
            .map(
                existingDsSchema -> {
                    if (dsSchema.getName() != null) {
                        existingDsSchema.setName(dsSchema.getName());
                    }
                    if (dsSchema.getDetail() != null) {
                        existingDsSchema.setDetail(dsSchema.getDetail());
                    }
                    if (dsSchema.getTableCount() != null) {
                        existingDsSchema.setTableCount(dsSchema.getTableCount());
                    }
                    if (dsSchema.getTableRelCount() != null) {
                        existingDsSchema.setTableRelCount(dsSchema.getTableRelCount());
                    }
                    if (dsSchema.getCreationDate() != null) {
                        existingDsSchema.setCreationDate(dsSchema.getCreationDate());
                    }
                    if (dsSchema.getLastUpdated() != null) {
                        existingDsSchema.setLastUpdated(dsSchema.getLastUpdated());
                    }

                    return existingDsSchema;
                }
            )
            .map(dsSchemaRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DsSchema> findAll(Pageable pageable) {
        log.debug("Request to get all DsSchemas");
        return dsSchemaRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DsSchema> findOne(Long id) {
        log.debug("Request to get DsSchema : {}", id);
        return dsSchemaRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DsSchema : {}", id);
        dsSchemaRepository.deleteById(id);
    }
}
