/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

package ch.admin.bj.swiyu.registry.identifier.data.common.exception;

import java.text.MessageFormat;

public class ResourceNotReadyException extends RuntimeException {

    private static final MessageFormat ERR_MESSAGE = new MessageFormat(
        "Resource with id ''{0}'' is not ready for processing."
    );

    public ResourceNotReadyException(String id, Class<?> clazz) {
        super(ERR_MESSAGE.format(new String[] { id, clazz.getSimpleName() }));
    }
}
