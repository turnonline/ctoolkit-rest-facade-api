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
import javax.annotation.Nullable;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

/**
 * The top level request wrapper to provide possibility to isolate a remote call to standalone object.
 *
 * @param <T> the resource type
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface Request<T>
{
    /**
     * Execute a remote call.
     *
     * @return the resource as a result of the remote call
     */
    T finish();

    /**
     * Execute a remote call with request specific credential and configuration.
     * <p>
     * Note: The underlying API must support (at least partially) this functionality
     * otherwise these properties will be ignored and default value of target API will be used.
     *
     * @param credential the credential and configuration to be applied to this request
     * @return the resource as a result of the remote call
     */
    T finish( @Nonnull RequestCredential credential );

    /**
     * Execute a remote call with additional resource (query) parameters.
     *
     * @param parameters the optional resource (query) parameters
     * @return the resource as a result of the remote call
     */
    T finish( @Nullable Map<String, Object> parameters );

    /**
     * Execute a remote call with specified locale.
     *
     * @param locale the language the client has configured to prefer in results if applicable
     * @return the resource as a result of the remote call
     */
    T finish( @Nullable Locale locale );

    /**
     * Execute a remote call with additional resource (query) parameters or locale.
     *
     * @param parameters the optional resource (query) parameters
     * @param locale     the language the client has configured to prefer in results if applicable
     * @return the resource as a result of the remote call
     */
    T finish( @Nullable Map<String, Object> parameters, @Nullable Locale locale );

    /**
     * Apply specific request configuration properties (query parameters) to this call.
     *
     * @param properties the request configuration properties to be applied to this request
     * @return this request to chain calls
     * @see #finish(Map)
     */
    Request<T> configWith( @Nonnull Properties properties );

    /**
     * Config request with optional locale.
     *
     * @param locale the language the client has configured to prefer in results if applicable
     * @return this request to chain calls
     */
    Request<T> forLang( @Nonnull Locale locale );

    /**
     * Add optional (query) parameter to the request.
     *
     * @param name  the name of the (query) parameter
     * @param value the value of the parameter
     * @return this request to chain calls
     */
    Request<T> add( @Nonnull String name, @Nonnull Object value );

    /**
     * Add optional (query) parameter to the request.
     *
     * @param name  the name of the (query) parameter
     * @param value the value of the parameter
     * @return this request to chain calls
     */
    Request<T> add( @Nonnull String name, @Nonnull String value );

    /**
     * Add optional header to the request.
     *
     * @param header the name of the header
     * @param value  the header value
     * @return this request to chain calls
     */
    Request<T> addHeader( @Nonnull String header, @Nonnull String value );

    /**
     * Authorize this request by given authorization token.
     * It will be set right before a remote call and will override an existing value.
     *
     * @param token the authorization header value
     * @return this request to chain calls
     */
    AuthRequest<T> authBy( @Nonnull String token );
}
