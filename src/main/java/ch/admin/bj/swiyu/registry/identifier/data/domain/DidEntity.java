/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

package ch.admin.bj.swiyu.registry.identifier.data.domain;

import ch.admin.bj.swiyu.registry.identifier.data.domain.datastore.DatastoreEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "did_entity")
public class DidEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "base_id", referencedColumnName = "id")
    private DatastoreEntity base;

    @Column(name = "file_type")
    @Enumerated(EnumType.STRING)
    private DidType fileType;

    @Column(name = "content")
    private String content;

    @Column(name = "read_uri")
    private String readUri;
}
