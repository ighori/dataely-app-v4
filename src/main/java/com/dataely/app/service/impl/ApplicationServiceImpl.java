package com.dataely.app.service.impl;

import com.dataely.app.domain.Application;
import com.dataely.app.repository.ApplicationRepository;
import com.dataely.app.service.ApplicationService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Application}.
 */
@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

    private final Logger log = LoggerFactory.getLogger(ApplicationServiceImpl.class);

    private final ApplicationRepository applicationRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public Application save(Application application) {
        log.debug("Request to save Application : {}", application);
        return applicationRepository.save(application);
    }

    @Override
    public Optional<Application> partialUpdate(Application application) {
        log.debug("Request to partially update Application : {}", application);

        return applicationRepository
            .findById(application.getId())
            .map(
                existingApplication -> {
                    if (application.getName() != null) {
                        existingApplication.setName(application.getName());
                    }
                    if (application.getDetail() != null) {
                        existingApplication.setDetail(application.getDetail());
                    }
                    if (application.getVersion() != null) {
                        existingApplication.setVersion(application.getVersion());
                    }
                    if (application.getAppType() != null) {
                        existingApplication.setAppType(application.getAppType());
                    }
                    if (application.getCreationDate() != null) {
                        existingApplication.setCreationDate(application.getCreationDate());
                    }
                    if (application.getLastUpdated() != null) {
                        existingApplication.setLastUpdated(application.getLastUpdated());
                    }

                    return existingApplication;
                }
            )
            .map(applicationRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Application> findAll(Pageable pageable) {
        log.debug("Request to get all Applications");
        return applicationRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Application> findOne(Long id) {
        log.debug("Request to get Application : {}", id);
        return applicationRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Application : {}", id);
        applicationRepository.deleteById(id);
    }
}
