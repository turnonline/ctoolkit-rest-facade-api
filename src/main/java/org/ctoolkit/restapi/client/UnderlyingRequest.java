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
 * Specific request to handle API specific proxy (request) object.
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
     * The type of the answer expected as a result of the remote call execution.
     *
     * @param type the response type
     * @return the request to chain calls with newly defined response type
     */
    <R> Request<R> answerBy( @Nonnull Class<R> type );

    /**
     * Configure the resource instance as a source of specific update.
     *
     * @param resource the resource instance to be set
     * @return this request to be chained
     */
    <R> Request<R> withPayload( @Nonnull R resource );

    /**
     * Configure the optional identifier of the resource.
     *
     * @param identifier the unique identifier of the resource to be set
     * @return this request to be chained
     */
    UnderlyingRequest<U> identifiedBy( @Nonnull Identifier identifier );

    /**
     * Configure request with additional parameters.
     *
     * @param parameters the optional parameters for the request
     * @return this request to chain calls
     */
    UnderlyingRequest<U> addParams( @Nonnull Map<String, Object> parameters );

    /**
     * Add parameter to the request.
     *
     * @param name  the name of the parameter
     * @param value the value of the parameter
     * @return this request to chain calls
     */
    UnderlyingRequest<U> add( @Nonnull String name, @Nonnull Object value );

    /**
     * Add parameter to the request.
     *
     * @param name  the name of the parameter
     * @param value the value of the parameter
     * @return this request to chain calls
     */
    UnderlyingRequest<U> add( @Nonnull String name, @Nonnull String value );
}
