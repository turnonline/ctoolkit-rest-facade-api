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

import java.util.Locale;
import java.util.Map;

/**
 * The top level request wrapper to provide possibility to isolate a remote call to standalone object.
 *
 * @param <T> the resource type
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface Request<T>
{
    /**
     * Returns the underlying request instance in order to configure an additional query parameters
     * or provide specific request configuration.
     *
     * @param type the underlying request related to concrete API implementation
     * @param <Q>  the class type of underlying request to be cast
     * @return the underlying request object
     * @throws ClassCastException
     */
    <Q> Q query( Class<Q> type );

    /**
     * Execute a remote call based on the underlying {@link #query(Class)} instance.
     *
     * @return the resource as a result of the remote call
     */
    T execute();

    /**
     * Execute a remote call with additional resource parameters.
     *
     * @param parameters the optional resource parameters
     * @return the resource as a result of the remote call
     */
    T execute( Map<String, Object> parameters );

    /**
     * Execute a remote call with specified locale.
     *
     * @param locale the language the client has configured to prefer in results if applicable
     * @return the resource as a result of the remote call
     */
    T execute( Locale locale );

    /**
     * Execute a remote call with additional resource parameters or locale.
     *
     * @param parameters the optional resource parameters
     * @param locale     the language the client has configured to prefer in results if applicable
     * @return the resource as a result of the remote call
     */
    T execute( Map<String, Object> parameters, Locale locale );

    /**
     * Apply request specific credential and configuration to this call.
     * The underlying API must support (at least partially) this functionality
     * otherwise call to this method will be ignored and default value of target API will be used.
     *
     * @param credential the credential and configuration to be applied to this request
     * @return this request to chain calls
     */
    Request<T> config( RequestCredential credential );
}
