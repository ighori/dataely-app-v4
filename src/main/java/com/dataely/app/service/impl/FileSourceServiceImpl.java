package com.dataely.app.service.impl;

import com.dataely.app.domain.FileSource;
import com.dataely.app.repository.FileSourceRepository;
import com.dataely.app.service.FileSourceService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FileSource}.
 */
@Service
@Transactional
public class FileSourceServiceImpl implements FileSourceService {

    private final Logger log = LoggerFactory.getLogger(FileSourceServiceImpl.class);

    private final FileSourceRepository fileSourceRepository;

    public FileSourceServiceImpl(FileSourceRepository fileSourceRepository) {
        this.fileSourceRepository = fileSourceRepository;
    }

    @Override
    public FileSource save(FileSource fileSource) {
        log.debug("Request to save FileSource : {}", fileSource);
        return fileSourceRepository.save(fileSource);
    }

    @Override
    public Optional<FileSource> partialUpdate(FileSource fileSource) {
        log.debug("Request to partially update FileSource : {}", fileSource);

        return fileSourceRepository
            .findById(fileSource.getId())
            .map(
                existingFileSource -> {
                    if (fileSource.getName() != null) {
                        existingFileSource.setName(fileSource.getName());
                    }
                    if (fileSource.getDetail() != null) {
                        existingFileSource.setDetail(fileSource.getDetail());
                    }
                    if (fileSource.getHostname() != null) {
                        existingFileSource.setHostname(fileSource.getHostname());
                    }
                    if (fileSource.getPort() != null) {
                        existingFileSource.setPort(fileSource.getPort());
                    }
                    if (fileSource.getUsername() != null) {
                        existingFileSource.setUsername(fileSource.getUsername());
                    }
                    if (fileSource.getPassword() != null) {
                        existingFileSource.setPassword(fileSource.getPassword());
                    }
                    if (fileSource.getIcon() != null) {
                        existingFileSource.setIcon(fileSource.getIcon());
                    }
                    if (fileSource.getConnectionType() != null) {
                        existingFileSource.setConnectionType(fileSource.getConnectionType());
                    }
                    if (fileSource.getType() != null) {
                        existingFileSource.setType(fileSource.getType());
                    }
                    if (fileSource.getRegion() != null) {
                        existingFileSource.setRegion(fileSource.getRegion());
                    }
                    if (fileSource.getIgnoreDottedFiles() != null) {
                        existingFileSource.setIgnoreDottedFiles(fileSource.getIgnoreDottedFiles());
                    }
                    if (fileSource.getRecurse() != null) {
                        existingFileSource.setRecurse(fileSource.getRecurse());
                    }
                    if (fileSource.getPathFilterRegex() != null) {
                        existingFileSource.setPathFilterRegex(fileSource.getPathFilterRegex());
                    }
                    if (fileSource.getRemotePath() != null) {
                        existingFileSource.setRemotePath(fileSource.getRemotePath());
                    }
                    if (fileSource.getCreationDate() != null) {
                        existingFileSource.setCreationDate(fileSource.getCreationDate());
                    }
                    if (fileSource.getLastUpdated() != null) {
                        existingFileSource.setLastUpdated(fileSource.getLastUpdated());
                    }

                    return existingFileSource;
                }
            )
            .map(fileSourceRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FileSource> findAll(Pageable pageable) {
        log.debug("Request to get all FileSources");
        return fileSourceRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FileSource> findOne(Long id) {
        log.debug("Request to get FileSource : {}", id);
        return fileSourceRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete FileSource : {}", id);
        fileSourceRepository.deleteById(id);
    }
}
