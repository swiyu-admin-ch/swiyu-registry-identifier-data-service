/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

package ch.admin.bj.swiyu.registry.identifier.data.domain.datastore;

import ch.admin.bj.swiyu.registry.identifier.data.common.exception.ResourceNotFoundException;
import ch.admin.bj.swiyu.registry.identifier.data.common.exception.ResourceNotReadyException;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DatastoreEntityDomainService {

    private final DatastoreEntityRepository datastoreEntityRepository;

    @Transactional(readOnly = true)
    public DatastoreEntity getDatastoreEntity(UUID id) {
        return this.datastoreEntityRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(id.toString(), DatastoreEntity.class)
            );
    }

    public static void validateCanShow(DatastoreEntity entry) throws ResourceNotReadyException {
        validateCanEdit(entry);
        if (
            !(entry.getStatus() != DatastoreStatus.SETUP && entry.getStatus() != DatastoreStatus.DEACTIVATED)
        ) throw new ResourceNotReadyException(entry.getId().toString(), DatastoreEntity.class);
    }

    private static void validateCanEdit(DatastoreEntity entry) throws ResourceNotReadyException {
        if (entry.getStatus() == DatastoreStatus.DISABLED) throw new ResourceNotReadyException(
            entry.getId().toString(),
            DatastoreEntity.class
        );
    }
}
