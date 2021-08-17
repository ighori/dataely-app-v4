package com.dataely.app.service.impl;

import com.dataely.app.domain.AnalyzerJob;
import com.dataely.app.repository.AnalyzerJobRepository;
import com.dataely.app.service.AnalyzerJobService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AnalyzerJob}.
 */
@Service
@Transactional
public class AnalyzerJobServiceImpl implements AnalyzerJobService {

    private final Logger log = LoggerFactory.getLogger(AnalyzerJobServiceImpl.class);

    private final AnalyzerJobRepository analyzerJobRepository;

    public AnalyzerJobServiceImpl(AnalyzerJobRepository analyzerJobRepository) {
        this.analyzerJobRepository = analyzerJobRepository;
    }

    @Override
    public AnalyzerJob save(AnalyzerJob analyzerJob) {
        log.debug("Request to save AnalyzerJob : {}", analyzerJob);
        return analyzerJobRepository.save(analyzerJob);
    }

    @Override
    public Optional<AnalyzerJob> partialUpdate(AnalyzerJob analyzerJob) {
        log.debug("Request to partially update AnalyzerJob : {}", analyzerJob);

        return analyzerJobRepository
            .findById(analyzerJob.getId())
            .map(
                existingAnalyzerJob -> {
                    if (analyzerJob.getName() != null) {
                        existingAnalyzerJob.setName(analyzerJob.getName());
                    }
                    if (analyzerJob.getDetail() != null) {
                        existingAnalyzerJob.setDetail(analyzerJob.getDetail());
                    }
                    if (analyzerJob.getEndTime() != null) {
                        existingAnalyzerJob.setEndTime(analyzerJob.getEndTime());
                    }
                    if (analyzerJob.getStartTime() != null) {
                        existingAnalyzerJob.setStartTime(analyzerJob.getStartTime());
                    }
                    if (analyzerJob.getStatus() != null) {
                        existingAnalyzerJob.setStatus(analyzerJob.getStatus());
                    }
                    if (analyzerJob.getPreviousRunTime() != null) {
                        existingAnalyzerJob.setPreviousRunTime(analyzerJob.getPreviousRunTime());
                    }
                    if (analyzerJob.getCreationDate() != null) {
                        existingAnalyzerJob.setCreationDate(analyzerJob.getCreationDate());
                    }
                    if (analyzerJob.getLastUpdated() != null) {
                        existingAnalyzerJob.setLastUpdated(analyzerJob.getLastUpdated());
                    }

                    return existingAnalyzerJob;
                }
            )
            .map(analyzerJobRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AnalyzerJob> findAll(Pageable pageable) {
        log.debug("Request to get all AnalyzerJobs");
        return analyzerJobRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AnalyzerJob> findOne(Long id) {
        log.debug("Request to get AnalyzerJob : {}", id);
        return analyzerJobRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AnalyzerJob : {}", id);
        analyzerJobRepository.deleteById(id);
    }
}
