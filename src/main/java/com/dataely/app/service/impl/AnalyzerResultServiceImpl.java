package com.dataely.app.service.impl;

import com.dataely.app.domain.AnalyzerResult;
import com.dataely.app.repository.AnalyzerResultRepository;
import com.dataely.app.service.AnalyzerResultService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AnalyzerResult}.
 */
@Service
@Transactional
public class AnalyzerResultServiceImpl implements AnalyzerResultService {

    private final Logger log = LoggerFactory.getLogger(AnalyzerResultServiceImpl.class);

    private final AnalyzerResultRepository analyzerResultRepository;

    public AnalyzerResultServiceImpl(AnalyzerResultRepository analyzerResultRepository) {
        this.analyzerResultRepository = analyzerResultRepository;
    }

    @Override
    public AnalyzerResult save(AnalyzerResult analyzerResult) {
        log.debug("Request to save AnalyzerResult : {}", analyzerResult);
        return analyzerResultRepository.save(analyzerResult);
    }

    @Override
    public Optional<AnalyzerResult> partialUpdate(AnalyzerResult analyzerResult) {
        log.debug("Request to partially update AnalyzerResult : {}", analyzerResult);

        return analyzerResultRepository
            .findById(analyzerResult.getId())
            .map(
                existingAnalyzerResult -> {
                    if (analyzerResult.getName() != null) {
                        existingAnalyzerResult.setName(analyzerResult.getName());
                    }
                    if (analyzerResult.getDetail() != null) {
                        existingAnalyzerResult.setDetail(analyzerResult.getDetail());
                    }
                    if (analyzerResult.getObjectId() != null) {
                        existingAnalyzerResult.setObjectId(analyzerResult.getObjectId());
                    }
                    if (analyzerResult.getObjectType() != null) {
                        existingAnalyzerResult.setObjectType(analyzerResult.getObjectType());
                    }
                    if (analyzerResult.getFieldId() != null) {
                        existingAnalyzerResult.setFieldId(analyzerResult.getFieldId());
                    }
                    if (analyzerResult.getFieldType() != null) {
                        existingAnalyzerResult.setFieldType(analyzerResult.getFieldType());
                    }
                    if (analyzerResult.getEntityType() != null) {
                        existingAnalyzerResult.setEntityType(analyzerResult.getEntityType());
                    }
                    if (analyzerResult.getStart() != null) {
                        existingAnalyzerResult.setStart(analyzerResult.getStart());
                    }
                    if (analyzerResult.getEnd() != null) {
                        existingAnalyzerResult.setEnd(analyzerResult.getEnd());
                    }
                    if (analyzerResult.getScore() != null) {
                        existingAnalyzerResult.setScore(analyzerResult.getScore());
                    }
                    if (analyzerResult.getRecognizer() != null) {
                        existingAnalyzerResult.setRecognizer(analyzerResult.getRecognizer());
                    }
                    if (analyzerResult.getPatternName() != null) {
                        existingAnalyzerResult.setPatternName(analyzerResult.getPatternName());
                    }
                    if (analyzerResult.getPatternExpr() != null) {
                        existingAnalyzerResult.setPatternExpr(analyzerResult.getPatternExpr());
                    }
                    if (analyzerResult.getOriginalScore() != null) {
                        existingAnalyzerResult.setOriginalScore(analyzerResult.getOriginalScore());
                    }
                    if (analyzerResult.getTextualExplanation() != null) {
                        existingAnalyzerResult.setTextualExplanation(analyzerResult.getTextualExplanation());
                    }
                    if (analyzerResult.getSupportiveContextWord() != null) {
                        existingAnalyzerResult.setSupportiveContextWord(analyzerResult.getSupportiveContextWord());
                    }
                    if (analyzerResult.getValidationResult() != null) {
                        existingAnalyzerResult.setValidationResult(analyzerResult.getValidationResult());
                    }
                    if (analyzerResult.getCreationDate() != null) {
                        existingAnalyzerResult.setCreationDate(analyzerResult.getCreationDate());
                    }
                    if (analyzerResult.getLastUpdated() != null) {
                        existingAnalyzerResult.setLastUpdated(analyzerResult.getLastUpdated());
                    }

                    return existingAnalyzerResult;
                }
            )
            .map(analyzerResultRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AnalyzerResult> findAll(Pageable pageable) {
        log.debug("Request to get all AnalyzerResults");
        return analyzerResultRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AnalyzerResult> findOne(Long id) {
        log.debug("Request to get AnalyzerResult : {}", id);
        return analyzerResultRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AnalyzerResult : {}", id);
        analyzerResultRepository.deleteById(id);
    }
}
