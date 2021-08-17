package com.dataely.app.service.impl;

import com.dataely.app.domain.DataSource;
import com.dataely.app.repository.DataSourceRepository;
import com.dataely.app.service.DataSourceService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DataSource}.
 */
@Service
@Transactional
public class DataSourceServiceImpl implements DataSourceService {

    private final Logger log = LoggerFactory.getLogger(DataSourceServiceImpl.class);

    private final DataSourceRepository dataSourceRepository;

    public DataSourceServiceImpl(DataSourceRepository dataSourceRepository) {
        this.dataSourceRepository = dataSourceRepository;
    }

    @Override
    public DataSource save(DataSource dataSource) {
        log.debug("Request to save DataSource : {}", dataSource);
        return dataSourceRepository.save(dataSource);
    }

    @Override
    public Optional<DataSource> partialUpdate(DataSource dataSource) {
        log.debug("Request to partially update DataSource : {}", dataSource);

        return dataSourceRepository
            .findById(dataSource.getId())
            .map(
                existingDataSource -> {
                    if (dataSource.getName() != null) {
                        existingDataSource.setName(dataSource.getName());
                    }
                    if (dataSource.getDetail() != null) {
                        existingDataSource.setDetail(dataSource.getDetail());
                    }
                    if (dataSource.getDatabaseName() != null) {
                        existingDataSource.setDatabaseName(dataSource.getDatabaseName());
                    }
                    if (dataSource.getInstanceName() != null) {
                        existingDataSource.setInstanceName(dataSource.getInstanceName());
                    }
                    if (dataSource.getSchemaName() != null) {
                        existingDataSource.setSchemaName(dataSource.getSchemaName());
                    }
                    if (dataSource.getHostname() != null) {
                        existingDataSource.setHostname(dataSource.getHostname());
                    }
                    if (dataSource.getPort() != null) {
                        existingDataSource.setPort(dataSource.getPort());
                    }
                    if (dataSource.getUsername() != null) {
                        existingDataSource.setUsername(dataSource.getUsername());
                    }
                    if (dataSource.getPassword() != null) {
                        existingDataSource.setPassword(dataSource.getPassword());
                    }
                    if (dataSource.getIcon() != null) {
                        existingDataSource.setIcon(dataSource.getIcon());
                    }
                    if (dataSource.getDbType() != null) {
                        existingDataSource.setDbType(dataSource.getDbType());
                    }
                    if (dataSource.getDbVersion() != null) {
                        existingDataSource.setDbVersion(dataSource.getDbVersion());
                    }
                    if (dataSource.getSchemaCount() != null) {
                        existingDataSource.setSchemaCount(dataSource.getSchemaCount());
                    }
                    if (dataSource.getDsType() != null) {
                        existingDataSource.setDsType(dataSource.getDsType());
                    }
                    if (dataSource.getDriverClassName() != null) {
                        existingDataSource.setDriverClassName(dataSource.getDriverClassName());
                    }
                    if (dataSource.getJdbcUrl() != null) {
                        existingDataSource.setJdbcUrl(dataSource.getJdbcUrl());
                    }
                    if (dataSource.getSid() != null) {
                        existingDataSource.setSid(dataSource.getSid());
                    }
                    if (dataSource.getCreationDate() != null) {
                        existingDataSource.setCreationDate(dataSource.getCreationDate());
                    }
                    if (dataSource.getLastUpdated() != null) {
                        existingDataSource.setLastUpdated(dataSource.getLastUpdated());
                    }

                    return existingDataSource;
                }
            )
            .map(dataSourceRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DataSource> findAll(Pageable pageable) {
        log.debug("Request to get all DataSources");
        return dataSourceRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DataSource> findOne(Long id) {
        log.debug("Request to get DataSource : {}", id);
        return dataSourceRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DataSource : {}", id);
        dataSourceRepository.deleteById(id);
    }
}
