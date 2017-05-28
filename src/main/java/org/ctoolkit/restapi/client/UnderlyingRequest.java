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
import java.util.Map;

/**
 * Specific request to handle API specific proxy object.
 *
 * @param <U> the concrete type of the underlying request
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface UnderlyingRequest<U>
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
    UnderlyingRequest<U> resource( @Nonnull Object resource );

    /**
     * Configure the optional identifier of the resource.
     *
     * @param identifier the unique identifier of the resource to be set
     * @return this request to be chained
     */
    UnderlyingRequest<U> identifier( @Nonnull Identifier identifier );

    /**
     * Configure request with additional parameters.
     *
     * @param parameters the optional parameters for the request
     * @return this request to chain calls
     */
    UnderlyingRequest<U> parameters( Map<String, Object> parameters );
}
