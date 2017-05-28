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
 * Specific request for a patch operation.
 *
 * @param <U> the concrete type of the underlying request instance
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface PatchRequest<U>
{
    /**
     * Builds underlying request and returns a proxy object related to concrete API implementation.
     * It gives a possibility to configure an additional query parameters or provide specific request configuration.
     *
     * @return the underlying request instance
     */
    U get();

    /**
     * The type of the response expected as a result of the remote call execution.
     *
     * @param type the response type
     * @param <R>  the class type of underlying request to be cast
     * @return the request to chain calls with new response type defined
     */
    <R> Request<R> response( Class<R> type );

    /**
     * Configure the optional resource instance as a source of specific update.
     *
     * @param resource the resource instance to be set
     * @return this request to be chained
     */
    PatchRequest<U> resource( @Nonnull Object resource );

    /**
     * Configure the optional unique identifier of the resource.
     *
     * @param identifier the unique identifier of the resource to be set
     * @return this request to be chained
     */
    PatchRequest<U> identifier( @Nonnull Identifier identifier );
}
