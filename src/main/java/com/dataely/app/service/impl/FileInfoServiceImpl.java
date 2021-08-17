package com.dataely.app.service.impl;

import com.dataely.app.domain.FileInfo;
import com.dataely.app.repository.FileInfoRepository;
import com.dataely.app.service.FileInfoService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FileInfo}.
 */
@Service
@Transactional
public class FileInfoServiceImpl implements FileInfoService {

    private final Logger log = LoggerFactory.getLogger(FileInfoServiceImpl.class);

    private final FileInfoRepository fileInfoRepository;

    public FileInfoServiceImpl(FileInfoRepository fileInfoRepository) {
        this.fileInfoRepository = fileInfoRepository;
    }

    @Override
    public FileInfo save(FileInfo fileInfo) {
        log.debug("Request to save FileInfo : {}", fileInfo);
        return fileInfoRepository.save(fileInfo);
    }

    @Override
    public Optional<FileInfo> partialUpdate(FileInfo fileInfo) {
        log.debug("Request to partially update FileInfo : {}", fileInfo);

        return fileInfoRepository
            .findById(fileInfo.getId())
            .map(
                existingFileInfo -> {
                    if (fileInfo.getName() != null) {
                        existingFileInfo.setName(fileInfo.getName());
                    }
                    if (fileInfo.getDetail() != null) {
                        existingFileInfo.setDetail(fileInfo.getDetail());
                    }
                    if (fileInfo.getFileType() != null) {
                        existingFileInfo.setFileType(fileInfo.getFileType());
                    }
                    if (fileInfo.getFilePath() != null) {
                        existingFileInfo.setFilePath(fileInfo.getFilePath());
                    }
                    if (fileInfo.getColumnNameLineNumber() != null) {
                        existingFileInfo.setColumnNameLineNumber(fileInfo.getColumnNameLineNumber());
                    }
                    if (fileInfo.getEncoding() != null) {
                        existingFileInfo.setEncoding(fileInfo.getEncoding());
                    }
                    if (fileInfo.getSeparatorChar() != null) {
                        existingFileInfo.setSeparatorChar(fileInfo.getSeparatorChar());
                    }
                    if (fileInfo.getQuoteChar() != null) {
                        existingFileInfo.setQuoteChar(fileInfo.getQuoteChar());
                    }
                    if (fileInfo.getEscapeChar() != null) {
                        existingFileInfo.setEscapeChar(fileInfo.getEscapeChar());
                    }
                    if (fileInfo.getFixedValueWidth() != null) {
                        existingFileInfo.setFixedValueWidth(fileInfo.getFixedValueWidth());
                    }
                    if (fileInfo.getSkipEmptyLines() != null) {
                        existingFileInfo.setSkipEmptyLines(fileInfo.getSkipEmptyLines());
                    }
                    if (fileInfo.getSkipEmptyColumns() != null) {
                        existingFileInfo.setSkipEmptyColumns(fileInfo.getSkipEmptyColumns());
                    }
                    if (fileInfo.getFailOnInconsistentLineWidth() != null) {
                        existingFileInfo.setFailOnInconsistentLineWidth(fileInfo.getFailOnInconsistentLineWidth());
                    }
                    if (fileInfo.getSkipEbcdicHeader() != null) {
                        existingFileInfo.setSkipEbcdicHeader(fileInfo.getSkipEbcdicHeader());
                    }
                    if (fileInfo.getEolPresent() != null) {
                        existingFileInfo.setEolPresent(fileInfo.getEolPresent());
                    }
                    if (fileInfo.getCreationDate() != null) {
                        existingFileInfo.setCreationDate(fileInfo.getCreationDate());
                    }
                    if (fileInfo.getLastUpdated() != null) {
                        existingFileInfo.setLastUpdated(fileInfo.getLastUpdated());
                    }

                    return existingFileInfo;
                }
            )
            .map(fileInfoRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FileInfo> findAll(Pageable pageable) {
        log.debug("Request to get all FileInfos");
        return fileInfoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FileInfo> findOne(Long id) {
        log.debug("Request to get FileInfo : {}", id);
        return fileInfoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete FileInfo : {}", id);
        fileInfoRepository.deleteById(id);
    }
}
