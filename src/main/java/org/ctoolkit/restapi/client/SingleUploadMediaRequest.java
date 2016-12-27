/*
 * Copyright (c) 2016 Comvai, s.r.o. All Rights Reserved.
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
import javax.annotation.Nullable;

/**
 * The single upload media request as wrapper of media content and associated resource.
 * Gives a possibility to this resource to be either inserted or updated.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface SingleUploadMediaRequest<T>
{
    /**
     * Same as {@link ResourceFacade#insert(Object)}.
     *
     * @return the newly inserted instance of associated resource
     * @throws NotFoundException if not matching request URI has found
     */
    SingleRequest<T> insert();

    /**
     * Same as {@link ResourceFacade#insert(Object, Identifier)}.
     *
     * @param parent the resource parent identifier
     * @return the newly inserted instance of associated resource
     * @throws NotFoundException if not matching request URI has found
     */
    SingleRequest<T> insert( @Nullable Identifier parent );

    /**
     * Same as {@link ResourceFacade#update(Object, Identifier)}.
     *
     * @param identifier the unique identifier of associated resource to update
     * @return the updated instance of associated resource
     * @throws NotFoundException if not matching request URI has found
     */
    SingleRequest<T> update( @Nonnull Identifier identifier );
}
