package com.dataely.app.service.impl;

import com.dataely.app.domain.TablesDefinition;
import com.dataely.app.repository.TablesDefinitionRepository;
import com.dataely.app.service.TablesDefinitionService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TablesDefinition}.
 */
@Service
@Transactional
public class TablesDefinitionServiceImpl implements TablesDefinitionService {

    private final Logger log = LoggerFactory.getLogger(TablesDefinitionServiceImpl.class);

    private final TablesDefinitionRepository tablesDefinitionRepository;

    public TablesDefinitionServiceImpl(TablesDefinitionRepository tablesDefinitionRepository) {
        this.tablesDefinitionRepository = tablesDefinitionRepository;
    }

    @Override
    public TablesDefinition save(TablesDefinition tablesDefinition) {
        log.debug("Request to save TablesDefinition : {}", tablesDefinition);
        return tablesDefinitionRepository.save(tablesDefinition);
    }

    @Override
    public Optional<TablesDefinition> partialUpdate(TablesDefinition tablesDefinition) {
        log.debug("Request to partially update TablesDefinition : {}", tablesDefinition);

        return tablesDefinitionRepository
            .findById(tablesDefinition.getId())
            .map(
                existingTablesDefinition -> {
                    if (tablesDefinition.getTableName() != null) {
                        existingTablesDefinition.setTableName(tablesDefinition.getTableName());
                    }
                    if (tablesDefinition.getValue() != null) {
                        existingTablesDefinition.setValue(tablesDefinition.getValue());
                    }
                    if (tablesDefinition.getSymbolSize() != null) {
                        existingTablesDefinition.setSymbolSize(tablesDefinition.getSymbolSize());
                    }
                    if (tablesDefinition.getCategory() != null) {
                        existingTablesDefinition.setCategory(tablesDefinition.getCategory());
                    }
                    if (tablesDefinition.getColCnt() != null) {
                        existingTablesDefinition.setColCnt(tablesDefinition.getColCnt());
                    }
                    if (tablesDefinition.getColCntNbr() != null) {
                        existingTablesDefinition.setColCntNbr(tablesDefinition.getColCntNbr());
                    }
                    if (tablesDefinition.getColCntTB() != null) {
                        existingTablesDefinition.setColCntTB(tablesDefinition.getColCntTB());
                    }
                    if (tablesDefinition.getColCntSTR() != null) {
                        existingTablesDefinition.setColCntSTR(tablesDefinition.getColCntSTR());
                    }
                    if (tablesDefinition.getColCntBL() != null) {
                        existingTablesDefinition.setColCntBL(tablesDefinition.getColCntBL());
                    }
                    if (tablesDefinition.getColCntPK() != null) {
                        existingTablesDefinition.setColCntPK(tablesDefinition.getColCntPK());
                    }
                    if (tablesDefinition.getColCntFK() != null) {
                        existingTablesDefinition.setColCntFK(tablesDefinition.getColCntFK());
                    }
                    if (tablesDefinition.getColCntIX() != null) {
                        existingTablesDefinition.setColCntIX(tablesDefinition.getColCntIX());
                    }
                    if (tablesDefinition.getCreationDate() != null) {
                        existingTablesDefinition.setCreationDate(tablesDefinition.getCreationDate());
                    }
                    if (tablesDefinition.getLastUpdated() != null) {
                        existingTablesDefinition.setLastUpdated(tablesDefinition.getLastUpdated());
                    }

                    return existingTablesDefinition;
                }
            )
            .map(tablesDefinitionRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TablesDefinition> findAll(Pageable pageable) {
        log.debug("Request to get all TablesDefinitions");
        return tablesDefinitionRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TablesDefinition> findOne(Long id) {
        log.debug("Request to get TablesDefinition : {}", id);
        return tablesDefinitionRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TablesDefinition : {}", id);
        tablesDefinitionRepository.deleteById(id);
    }
}
