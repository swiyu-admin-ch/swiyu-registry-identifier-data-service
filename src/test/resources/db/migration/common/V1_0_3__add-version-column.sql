/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

ALTER TABLE datastore_entity
    ADD COLUMN version int NOT NULL DEFAULT 0;

ALTER TABLE did_entity
    ADD COLUMN version int NOT NULL DEFAULT 0;
