package com.dataely.app.service.impl;

import com.dataely.app.domain.AnalyzerEntities;
import com.dataely.app.repository.AnalyzerEntitiesRepository;
import com.dataely.app.service.AnalyzerEntitiesService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AnalyzerEntities}.
 */
@Service
@Transactional
public class AnalyzerEntitiesServiceImpl implements AnalyzerEntitiesService {

    private final Logger log = LoggerFactory.getLogger(AnalyzerEntitiesServiceImpl.class);

    private final AnalyzerEntitiesRepository analyzerEntitiesRepository;

    public AnalyzerEntitiesServiceImpl(AnalyzerEntitiesRepository analyzerEntitiesRepository) {
        this.analyzerEntitiesRepository = analyzerEntitiesRepository;
    }

    @Override
    public AnalyzerEntities save(AnalyzerEntities analyzerEntities) {
        log.debug("Request to save AnalyzerEntities : {}", analyzerEntities);
        return analyzerEntitiesRepository.save(analyzerEntities);
    }

    @Override
    public Optional<AnalyzerEntities> partialUpdate(AnalyzerEntities analyzerEntities) {
        log.debug("Request to partially update AnalyzerEntities : {}", analyzerEntities);

        return analyzerEntitiesRepository
            .findById(analyzerEntities.getId())
            .map(
                existingAnalyzerEntities -> {
                    if (analyzerEntities.getName() != null) {
                        existingAnalyzerEntities.setName(analyzerEntities.getName());
                    }
                    if (analyzerEntities.getDetail() != null) {
                        existingAnalyzerEntities.setDetail(analyzerEntities.getDetail());
                    }
                    if (analyzerEntities.getEntityName() != null) {
                        existingAnalyzerEntities.setEntityName(analyzerEntities.getEntityName());
                    }
                    if (analyzerEntities.getCreationDate() != null) {
                        existingAnalyzerEntities.setCreationDate(analyzerEntities.getCreationDate());
                    }
                    if (analyzerEntities.getLastUpdated() != null) {
                        existingAnalyzerEntities.setLastUpdated(analyzerEntities.getLastUpdated());
                    }

                    return existingAnalyzerEntities;
                }
            )
            .map(analyzerEntitiesRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AnalyzerEntities> findAll(Pageable pageable) {
        log.debug("Request to get all AnalyzerEntities");
        return analyzerEntitiesRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AnalyzerEntities> findOne(Long id) {
        log.debug("Request to get AnalyzerEntities : {}", id);
        return analyzerEntitiesRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AnalyzerEntities : {}", id);
        analyzerEntitiesRepository.deleteById(id);
    }
}
