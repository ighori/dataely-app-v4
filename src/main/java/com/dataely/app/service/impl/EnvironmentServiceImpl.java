package com.dataely.app.service.impl;

import com.dataely.app.domain.Environment;
import com.dataely.app.repository.EnvironmentRepository;
import com.dataely.app.service.EnvironmentService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Environment}.
 */
@Service
@Transactional
public class EnvironmentServiceImpl implements EnvironmentService {

    private final Logger log = LoggerFactory.getLogger(EnvironmentServiceImpl.class);

    private final EnvironmentRepository environmentRepository;

    public EnvironmentServiceImpl(EnvironmentRepository environmentRepository) {
        this.environmentRepository = environmentRepository;
    }

    @Override
    public Environment save(Environment environment) {
        log.debug("Request to save Environment : {}", environment);
        return environmentRepository.save(environment);
    }

    @Override
    public Optional<Environment> partialUpdate(Environment environment) {
        log.debug("Request to partially update Environment : {}", environment);

        return environmentRepository
            .findById(environment.getId())
            .map(
                existingEnvironment -> {
                    if (environment.getName() != null) {
                        existingEnvironment.setName(environment.getName());
                    }
                    if (environment.getDetail() != null) {
                        existingEnvironment.setDetail(environment.getDetail());
                    }
                    if (environment.getType() != null) {
                        existingEnvironment.setType(environment.getType());
                    }
                    if (environment.getPurpose() != null) {
                        existingEnvironment.setPurpose(environment.getPurpose());
                    }
                    if (environment.getCreationDate() != null) {
                        existingEnvironment.setCreationDate(environment.getCreationDate());
                    }
                    if (environment.getLastUpdated() != null) {
                        existingEnvironment.setLastUpdated(environment.getLastUpdated());
                    }

                    return existingEnvironment;
                }
            )
            .map(environmentRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Environment> findAll(Pageable pageable) {
        log.debug("Request to get all Environments");
        return environmentRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Environment> findOne(Long id) {
        log.debug("Request to get Environment : {}", id);
        return environmentRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Environment : {}", id);
        environmentRepository.deleteById(id);
    }
}
