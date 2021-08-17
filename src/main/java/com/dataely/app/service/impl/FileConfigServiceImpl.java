package com.dataely.app.service.impl;

import com.dataely.app.domain.FileConfig;
import com.dataely.app.repository.FileConfigRepository;
import com.dataely.app.service.FileConfigService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FileConfig}.
 */
@Service
@Transactional
public class FileConfigServiceImpl implements FileConfigService {

    private final Logger log = LoggerFactory.getLogger(FileConfigServiceImpl.class);

    private final FileConfigRepository fileConfigRepository;

    public FileConfigServiceImpl(FileConfigRepository fileConfigRepository) {
        this.fileConfigRepository = fileConfigRepository;
    }

    @Override
    public FileConfig save(FileConfig fileConfig) {
        log.debug("Request to save FileConfig : {}", fileConfig);
        return fileConfigRepository.save(fileConfig);
    }

    @Override
    public Optional<FileConfig> partialUpdate(FileConfig fileConfig) {
        log.debug("Request to partially update FileConfig : {}", fileConfig);

        return fileConfigRepository
            .findById(fileConfig.getId())
            .map(
                existingFileConfig -> {
                    if (fileConfig.getName() != null) {
                        existingFileConfig.setName(fileConfig.getName());
                    }
                    if (fileConfig.getDetail() != null) {
                        existingFileConfig.setDetail(fileConfig.getDetail());
                    }
                    if (fileConfig.getColumnNameLineNumber() != null) {
                        existingFileConfig.setColumnNameLineNumber(fileConfig.getColumnNameLineNumber());
                    }
                    if (fileConfig.getEncoding() != null) {
                        existingFileConfig.setEncoding(fileConfig.getEncoding());
                    }
                    if (fileConfig.getSeparatorChar() != null) {
                        existingFileConfig.setSeparatorChar(fileConfig.getSeparatorChar());
                    }
                    if (fileConfig.getQuoteChar() != null) {
                        existingFileConfig.setQuoteChar(fileConfig.getQuoteChar());
                    }
                    if (fileConfig.getEscapeChar() != null) {
                        existingFileConfig.setEscapeChar(fileConfig.getEscapeChar());
                    }
                    if (fileConfig.getFixedValueWidth() != null) {
                        existingFileConfig.setFixedValueWidth(fileConfig.getFixedValueWidth());
                    }
                    if (fileConfig.getSkipEmptyLines() != null) {
                        existingFileConfig.setSkipEmptyLines(fileConfig.getSkipEmptyLines());
                    }
                    if (fileConfig.getSkipEmptyColumns() != null) {
                        existingFileConfig.setSkipEmptyColumns(fileConfig.getSkipEmptyColumns());
                    }
                    if (fileConfig.getFailOnInconsistentLineWidth() != null) {
                        existingFileConfig.setFailOnInconsistentLineWidth(fileConfig.getFailOnInconsistentLineWidth());
                    }
                    if (fileConfig.getSkipEbcdicHeader() != null) {
                        existingFileConfig.setSkipEbcdicHeader(fileConfig.getSkipEbcdicHeader());
                    }
                    if (fileConfig.getEolPresent() != null) {
                        existingFileConfig.setEolPresent(fileConfig.getEolPresent());
                    }
                    if (fileConfig.getFileType() != null) {
                        existingFileConfig.setFileType(fileConfig.getFileType());
                    }
                    if (fileConfig.getCreationDate() != null) {
                        existingFileConfig.setCreationDate(fileConfig.getCreationDate());
                    }
                    if (fileConfig.getLastUpdated() != null) {
                        existingFileConfig.setLastUpdated(fileConfig.getLastUpdated());
                    }

                    return existingFileConfig;
                }
            )
            .map(fileConfigRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FileConfig> findAll(Pageable pageable) {
        log.debug("Request to get all FileConfigs");
        return fileConfigRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FileConfig> findOne(Long id) {
        log.debug("Request to get FileConfig : {}", id);
        return fileConfigRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete FileConfig : {}", id);
        fileConfigRepository.deleteById(id);
    }
}
