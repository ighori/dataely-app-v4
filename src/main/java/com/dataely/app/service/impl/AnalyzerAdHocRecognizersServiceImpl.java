package com.dataely.app.service.impl;

import com.dataely.app.domain.AnalyzerAdHocRecognizers;
import com.dataely.app.repository.AnalyzerAdHocRecognizersRepository;
import com.dataely.app.service.AnalyzerAdHocRecognizersService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AnalyzerAdHocRecognizers}.
 */
@Service
@Transactional
public class AnalyzerAdHocRecognizersServiceImpl implements AnalyzerAdHocRecognizersService {

    private final Logger log = LoggerFactory.getLogger(AnalyzerAdHocRecognizersServiceImpl.class);

    private final AnalyzerAdHocRecognizersRepository analyzerAdHocRecognizersRepository;

    public AnalyzerAdHocRecognizersServiceImpl(AnalyzerAdHocRecognizersRepository analyzerAdHocRecognizersRepository) {
        this.analyzerAdHocRecognizersRepository = analyzerAdHocRecognizersRepository;
    }

    @Override
    public AnalyzerAdHocRecognizers save(AnalyzerAdHocRecognizers analyzerAdHocRecognizers) {
        log.debug("Request to save AnalyzerAdHocRecognizers : {}", analyzerAdHocRecognizers);
        return analyzerAdHocRecognizersRepository.save(analyzerAdHocRecognizers);
    }

    @Override
    public Optional<AnalyzerAdHocRecognizers> partialUpdate(AnalyzerAdHocRecognizers analyzerAdHocRecognizers) {
        log.debug("Request to partially update AnalyzerAdHocRecognizers : {}", analyzerAdHocRecognizers);

        return analyzerAdHocRecognizersRepository
            .findById(analyzerAdHocRecognizers.getId())
            .map(
                existingAnalyzerAdHocRecognizers -> {
                    if (analyzerAdHocRecognizers.getName() != null) {
                        existingAnalyzerAdHocRecognizers.setName(analyzerAdHocRecognizers.getName());
                    }
                    if (analyzerAdHocRecognizers.getDetail() != null) {
                        existingAnalyzerAdHocRecognizers.setDetail(analyzerAdHocRecognizers.getDetail());
                    }
                    if (analyzerAdHocRecognizers.getRecognizerName() != null) {
                        existingAnalyzerAdHocRecognizers.setRecognizerName(analyzerAdHocRecognizers.getRecognizerName());
                    }
                    if (analyzerAdHocRecognizers.getSupportedLang() != null) {
                        existingAnalyzerAdHocRecognizers.setSupportedLang(analyzerAdHocRecognizers.getSupportedLang());
                    }
                    if (analyzerAdHocRecognizers.getPatternName() != null) {
                        existingAnalyzerAdHocRecognizers.setPatternName(analyzerAdHocRecognizers.getPatternName());
                    }
                    if (analyzerAdHocRecognizers.getRegex() != null) {
                        existingAnalyzerAdHocRecognizers.setRegex(analyzerAdHocRecognizers.getRegex());
                    }
                    if (analyzerAdHocRecognizers.getScore() != null) {
                        existingAnalyzerAdHocRecognizers.setScore(analyzerAdHocRecognizers.getScore());
                    }
                    if (analyzerAdHocRecognizers.getContext() != null) {
                        existingAnalyzerAdHocRecognizers.setContext(analyzerAdHocRecognizers.getContext());
                    }
                    if (analyzerAdHocRecognizers.getDenyList() != null) {
                        existingAnalyzerAdHocRecognizers.setDenyList(analyzerAdHocRecognizers.getDenyList());
                    }
                    if (analyzerAdHocRecognizers.getSupportedEntity() != null) {
                        existingAnalyzerAdHocRecognizers.setSupportedEntity(analyzerAdHocRecognizers.getSupportedEntity());
                    }
                    if (analyzerAdHocRecognizers.getCreationDate() != null) {
                        existingAnalyzerAdHocRecognizers.setCreationDate(analyzerAdHocRecognizers.getCreationDate());
                    }
                    if (analyzerAdHocRecognizers.getLastUpdated() != null) {
                        existingAnalyzerAdHocRecognizers.setLastUpdated(analyzerAdHocRecognizers.getLastUpdated());
                    }

                    return existingAnalyzerAdHocRecognizers;
                }
            )
            .map(analyzerAdHocRecognizersRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AnalyzerAdHocRecognizers> findAll(Pageable pageable) {
        log.debug("Request to get all AnalyzerAdHocRecognizers");
        return analyzerAdHocRecognizersRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AnalyzerAdHocRecognizers> findOne(Long id) {
        log.debug("Request to get AnalyzerAdHocRecognizers : {}", id);
        return analyzerAdHocRecognizersRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AnalyzerAdHocRecognizers : {}", id);
        analyzerAdHocRecognizersRepository.deleteById(id);
    }
}
