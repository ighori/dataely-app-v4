package com.dataely.app.service.impl;

import com.dataely.app.domain.TableColumn;
import com.dataely.app.repository.TableColumnRepository;
import com.dataely.app.service.TableColumnService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TableColumn}.
 */
@Service
@Transactional
public class TableColumnServiceImpl implements TableColumnService {

    private final Logger log = LoggerFactory.getLogger(TableColumnServiceImpl.class);

    private final TableColumnRepository tableColumnRepository;

    public TableColumnServiceImpl(TableColumnRepository tableColumnRepository) {
        this.tableColumnRepository = tableColumnRepository;
    }

    @Override
    public TableColumn save(TableColumn tableColumn) {
        log.debug("Request to save TableColumn : {}", tableColumn);
        return tableColumnRepository.save(tableColumn);
    }

    @Override
    public Optional<TableColumn> partialUpdate(TableColumn tableColumn) {
        log.debug("Request to partially update TableColumn : {}", tableColumn);

        return tableColumnRepository
            .findById(tableColumn.getId())
            .map(
                existingTableColumn -> {
                    if (tableColumn.getColumnName() != null) {
                        existingTableColumn.setColumnName(tableColumn.getColumnName());
                    }
                    if (tableColumn.getColumnType() != null) {
                        existingTableColumn.setColumnType(tableColumn.getColumnType());
                    }
                    if (tableColumn.getColumnSize() != null) {
                        existingTableColumn.setColumnSize(tableColumn.getColumnSize());
                    }
                    if (tableColumn.getIsNullable() != null) {
                        existingTableColumn.setIsNullable(tableColumn.getIsNullable());
                    }
                    if (tableColumn.getIsPrimaryKey() != null) {
                        existingTableColumn.setIsPrimaryKey(tableColumn.getIsPrimaryKey());
                    }
                    if (tableColumn.getIsForeignKey() != null) {
                        existingTableColumn.setIsForeignKey(tableColumn.getIsForeignKey());
                    }
                    if (tableColumn.getIsIndexed() != null) {
                        existingTableColumn.setIsIndexed(tableColumn.getIsIndexed());
                    }
                    if (tableColumn.getCreationDate() != null) {
                        existingTableColumn.setCreationDate(tableColumn.getCreationDate());
                    }
                    if (tableColumn.getLastUpdated() != null) {
                        existingTableColumn.setLastUpdated(tableColumn.getLastUpdated());
                    }

                    return existingTableColumn;
                }
            )
            .map(tableColumnRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TableColumn> findAll(Pageable pageable) {
        log.debug("Request to get all TableColumns");
        return tableColumnRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TableColumn> findOne(Long id) {
        log.debug("Request to get TableColumn : {}", id);
        return tableColumnRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TableColumn : {}", id);
        tableColumnRepository.deleteById(id);
    }
}
