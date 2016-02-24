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

/**
 * Specific request of the patch operation.
 *
 * @param <R> the concrete type of the underlying request instance
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface PatchRequest<R>
{
    /**
     * Build the underlying request instance as a proxy object to execute a remote operation.
     *
     * @return the underlying request instance
     */
    R build();

    /**
     * Configure resource instance as a source of partial update.
     *
     * @param resource the resource instance to be set
     * @return this request to be chained
     */
    PatchRequest<R> resource( Object resource );

    /**
     * Configure the unique identifier of the resource.
     *
     * @param identifier the unique identifier of the resource to be set
     * @return this request to be chained
     */
    PatchRequest<R> identifier( Identifier identifier );
}
