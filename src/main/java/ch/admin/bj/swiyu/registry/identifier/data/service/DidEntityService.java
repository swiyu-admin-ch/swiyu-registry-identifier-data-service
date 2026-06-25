/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

package ch.admin.bj.swiyu.registry.identifier.data.service;

import static ch.admin.bj.swiyu.registry.identifier.data.domain.datastore.DatastoreEntityDomainService.validateCanShow;

import ch.admin.bj.swiyu.registry.identifier.data.common.exception.ResourceNotFoundException;
import ch.admin.bj.swiyu.registry.identifier.data.domain.DidEntity;
import ch.admin.bj.swiyu.registry.identifier.data.domain.DidEntityRepository;
import ch.admin.bj.swiyu.registry.identifier.data.domain.DidType;
import ch.admin.bj.swiyu.registry.identifier.data.domain.datastore.DatastoreEntity;
import ch.admin.bj.swiyu.registry.identifier.data.domain.datastore.DatastoreEntityDomainService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DidEntityService {

    private final DidEntityRepository didEntityRepository;
    private final DatastoreEntityDomainService datastoreEntityService;

    @Transactional(readOnly = true)
    public String getDidWeb(UUID id) {
        return getDidEntity(id, DidType.DID_WEB).getContent();
    }

    @Transactional(readOnly = true)
    public String getDidTdw(UUID id) {
        return getDidEntity(id, DidType.DID_TDW).getContent();
    }

    private DidEntity getDidEntity(UUID id, DidType fileType) {
        var entity = this.datastoreEntityService.getDatastoreEntity(id);
        validateCanShow(entity);
        return this.didEntityRepository.findByBase_IdAndFileType(id, fileType).orElseThrow(() ->
                new ResourceNotFoundException(id.toString(), DatastoreEntity.class)
            );
    }
}
