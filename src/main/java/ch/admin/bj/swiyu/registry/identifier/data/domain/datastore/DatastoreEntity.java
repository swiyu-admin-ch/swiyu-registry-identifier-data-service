/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

package ch.admin.bj.swiyu.registry.identifier.data.domain.datastore;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.Getter;

@Entity
@Getter
@Table(name = "datastore_entity")
public class DatastoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private DatastoreStatus status;
}
