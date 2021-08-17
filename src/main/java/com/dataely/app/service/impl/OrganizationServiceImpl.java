package com.dataely.app.service.impl;

import com.dataely.app.domain.Organization;
import com.dataely.app.repository.OrganizationRepository;
import com.dataely.app.service.OrganizationService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Organization}.
 */
@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

    private final Logger log = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    private final OrganizationRepository organizationRepository;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public Organization save(Organization organization) {
        log.debug("Request to save Organization : {}", organization);
        return organizationRepository.save(organization);
    }

    @Override
    public Optional<Organization> partialUpdate(Organization organization) {
        log.debug("Request to partially update Organization : {}", organization);

        return organizationRepository
            .findById(organization.getId())
            .map(
                existingOrganization -> {
                    if (organization.getName() != null) {
                        existingOrganization.setName(organization.getName());
                    }
                    if (organization.getDetail() != null) {
                        existingOrganization.setDetail(organization.getDetail());
                    }
                    if (organization.getCreationDate() != null) {
                        existingOrganization.setCreationDate(organization.getCreationDate());
                    }
                    if (organization.getLastUpdated() != null) {
                        existingOrganization.setLastUpdated(organization.getLastUpdated());
                    }

                    return existingOrganization;
                }
            )
            .map(organizationRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Organization> findAll(Pageable pageable) {
        log.debug("Request to get all Organizations");
        return organizationRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Organization> findOne(Long id) {
        log.debug("Request to get Organization : {}", id);
        return organizationRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Organization : {}", id);
        organizationRepository.deleteById(id);
    }
}
