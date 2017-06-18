/*
 * Copyright (c) 2017 Comvai, s.r.o. All Rights Reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package org.ctoolkit.restapi.client;

import javax.annotation.Nonnull;

/**
 * The single fluent action where concrete resource type has been already provided.
 * {@link Identifier} is aimed to be configured.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface IdentificationAction<T>
{
    /**
     * Creates request with given identification of the resource.
     *
     * @param identifier the unique identifier of the resource
     * @return the fluent action, a request with concrete resource type and identification already configured
     */
    Request<T> identifiedBy( @Nonnull Identifier identifier );

    /**
     * Creates request with given identification of the resource.
     *
     * @param identifier the string type identifier of the resource
     * @return the fluent action, a request with concrete resource type and identification already configured
     */
    Request<T> identifiedBy( @Nonnull String identifier );

    /**
     * Creates request with given identification of the resource.
     *
     * @param identifier the long type identifier of the resource
     * @return the fluent action, a request with concrete resource type and identification already configured
     */
    Request<T> identifiedBy( @Nonnull Long identifier );
}
