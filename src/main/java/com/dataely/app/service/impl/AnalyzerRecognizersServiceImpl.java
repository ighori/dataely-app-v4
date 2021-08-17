package com.dataely.app.service.impl;

import com.dataely.app.domain.AnalyzerRecognizers;
import com.dataely.app.repository.AnalyzerRecognizersRepository;
import com.dataely.app.service.AnalyzerRecognizersService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AnalyzerRecognizers}.
 */
@Service
@Transactional
public class AnalyzerRecognizersServiceImpl implements AnalyzerRecognizersService {

    private final Logger log = LoggerFactory.getLogger(AnalyzerRecognizersServiceImpl.class);

    private final AnalyzerRecognizersRepository analyzerRecognizersRepository;

    public AnalyzerRecognizersServiceImpl(AnalyzerRecognizersRepository analyzerRecognizersRepository) {
        this.analyzerRecognizersRepository = analyzerRecognizersRepository;
    }

    @Override
    public AnalyzerRecognizers save(AnalyzerRecognizers analyzerRecognizers) {
        log.debug("Request to save AnalyzerRecognizers : {}", analyzerRecognizers);
        return analyzerRecognizersRepository.save(analyzerRecognizers);
    }

    @Override
    public Optional<AnalyzerRecognizers> partialUpdate(AnalyzerRecognizers analyzerRecognizers) {
        log.debug("Request to partially update AnalyzerRecognizers : {}", analyzerRecognizers);

        return analyzerRecognizersRepository
            .findById(analyzerRecognizers.getId())
            .map(
                existingAnalyzerRecognizers -> {
                    if (analyzerRecognizers.getName() != null) {
                        existingAnalyzerRecognizers.setName(analyzerRecognizers.getName());
                    }
                    if (analyzerRecognizers.getDetail() != null) {
                        existingAnalyzerRecognizers.setDetail(analyzerRecognizers.getDetail());
                    }
                    if (analyzerRecognizers.getRecognizerName() != null) {
                        existingAnalyzerRecognizers.setRecognizerName(analyzerRecognizers.getRecognizerName());
                    }
                    if (analyzerRecognizers.getCreationDate() != null) {
                        existingAnalyzerRecognizers.setCreationDate(analyzerRecognizers.getCreationDate());
                    }
                    if (analyzerRecognizers.getLastUpdated() != null) {
                        existingAnalyzerRecognizers.setLastUpdated(analyzerRecognizers.getLastUpdated());
                    }

                    return existingAnalyzerRecognizers;
                }
            )
            .map(analyzerRecognizersRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AnalyzerRecognizers> findAll(Pageable pageable) {
        log.debug("Request to get all AnalyzerRecognizers");
        return analyzerRecognizersRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AnalyzerRecognizers> findOne(Long id) {
        log.debug("Request to get AnalyzerRecognizers : {}", id);
        return analyzerRecognizersRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AnalyzerRecognizers : {}", id);
        analyzerRecognizersRepository.deleteById(id);
    }
}
