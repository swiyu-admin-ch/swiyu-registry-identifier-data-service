/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

package ch.admin.bj.swiyu.registry.identifier.data.common.exception;

import java.text.MessageFormat;

public class ResourceNotFoundException extends RuntimeException {

    private static final MessageFormat ERR_MESSAGE = new MessageFormat(
        "Resource with id ''{0}'' in object ''{1}'' not found"
    );

    public ResourceNotFoundException(String id, Class<?> clazz) {
        super(ERR_MESSAGE.format(new String[] { id, clazz.getSimpleName() }));
    }
}
