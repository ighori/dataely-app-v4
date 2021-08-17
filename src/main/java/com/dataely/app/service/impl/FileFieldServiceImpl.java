package com.dataely.app.service.impl;

import com.dataely.app.domain.FileField;
import com.dataely.app.repository.FileFieldRepository;
import com.dataely.app.service.FileFieldService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FileField}.
 */
@Service
@Transactional
public class FileFieldServiceImpl implements FileFieldService {

    private final Logger log = LoggerFactory.getLogger(FileFieldServiceImpl.class);

    private final FileFieldRepository fileFieldRepository;

    public FileFieldServiceImpl(FileFieldRepository fileFieldRepository) {
        this.fileFieldRepository = fileFieldRepository;
    }

    @Override
    public FileField save(FileField fileField) {
        log.debug("Request to save FileField : {}", fileField);
        return fileFieldRepository.save(fileField);
    }

    @Override
    public Optional<FileField> partialUpdate(FileField fileField) {
        log.debug("Request to partially update FileField : {}", fileField);

        return fileFieldRepository
            .findById(fileField.getId())
            .map(
                existingFileField -> {
                    if (fileField.getFieldName() != null) {
                        existingFileField.setFieldName(fileField.getFieldName());
                    }
                    if (fileField.getFieldType() != null) {
                        existingFileField.setFieldType(fileField.getFieldType());
                    }
                    if (fileField.getFieldSize() != null) {
                        existingFileField.setFieldSize(fileField.getFieldSize());
                    }
                    if (fileField.getCreationDate() != null) {
                        existingFileField.setCreationDate(fileField.getCreationDate());
                    }
                    if (fileField.getLastUpdated() != null) {
                        existingFileField.setLastUpdated(fileField.getLastUpdated());
                    }

                    return existingFileField;
                }
            )
            .map(fileFieldRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FileField> findAll(Pageable pageable) {
        log.debug("Request to get all FileFields");
        return fileFieldRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FileField> findOne(Long id) {
        log.debug("Request to get FileField : {}", id);
        return fileFieldRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete FileField : {}", id);
        fileFieldRepository.deleteById(id);
    }
}
