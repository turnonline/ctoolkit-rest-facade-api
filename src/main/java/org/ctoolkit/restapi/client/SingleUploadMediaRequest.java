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
 * The upload media request as a wrapper of data and associated (metadata) resource.
 * Gives a possibility to this resource to be either inserted or updated.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface SingleUploadMediaRequest<T>
{
    /**
     * Same as {@link RestFacade#insert(Object)}.
     *
     * @return the fluent action, the consequent call will return the newly inserted instance of associated resource
     * @throws NotFoundException if not matching request URI has found
     */
    PayloadRequest<T> insert();

    /**
     * Sets the resource identifier.
     * <ul>
     * <li>In case of the consequent {@link #insert()} operation it acts as a parent resource identifier</li>
     * <li>In case of the consequent {@link #update()} operation it acts as a resource identifier</li>
     * </ul>
     *
     * @param identifier the unique identifier of the (parent) resource
     * @return the fluent action, the consequent call will return concrete resource instance for given type and identifier
     * @throws NotFoundException if not matching request URI has found
     */
    SingleUploadMediaRequest<T> identifiedBy( @Nonnull Identifier identifier );

    /**
     * Sets the resource identifier.
     * <ul>
     * <li>In case of the consequent {@link #insert()} operation it acts as a parent resource identifier</li>
     * <li>In case of the consequent {@link #update()} operation it acts as a resource identifier</li>
     * </ul>
     *
     * @param identifier the unique identifier of the (parent) resource
     * @return the fluent action, the consequent call will return concrete resource instance for given type and identifier
     * @throws NotFoundException if not matching request URI has found
     */
    SingleUploadMediaRequest<T> identifiedBy( @Nonnull String identifier );

    /**
     * Sets the resource identifier.
     * <ul>
     * <li>In case of the consequent {@link #insert()} operation it acts as a parent resource identifier</li>
     * <li>In case of the consequent {@link #update()} operation it acts as a resource identifier</li>
     * </ul>
     *
     * @param identifier the unique identifier of the (parent) resource
     * @return the fluent action, the consequent call will return concrete resource instance for given type and identifier
     * @throws NotFoundException if not matching request URI has found
     */
    SingleUploadMediaRequest<T> identifiedBy( @Nonnull Long identifier );

    /**
     * Same as {@link RestFacade#update(Object, Identifier)}.
     *
     * @return the fluent action, the consequent call will return updated instance of associated resource
     * @throws NotFoundException if not matching request URI has found
     */
    PayloadRequest<T> update();
}
