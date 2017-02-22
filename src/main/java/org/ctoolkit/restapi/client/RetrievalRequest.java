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

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * The extended {@link Request} to provide retrieval specific API to work with list of resources.
 *
 * @param <T> the list item resource type
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface RetrievalRequest<T>
        extends Request<List<T>>
{
    /**
     * Execute a remote call to find the list of resource instance of given type.
     *
     * @return the list of resource instance
     */
    List<T> execute();

    /**
     * Execute a remote call to find filtered list of resource instance.
     *
     * @param start  the position of the first result, numbered from 0
     * @param length the maximum number of results to retrieve
     * @return the list of filtered resource instance, if none returns empty list
     * @throws IllegalArgumentException thrown for any negative numbers
     */
    List<T> execute( int start, int length );

    /**
     * Execute a remote call to find the list of resource instance of given type and additional filtering criteria.
     *
     * @param criteria the optional filtering criteria map
     * @return the list of resource instance matching filtering criteria, otherwise returns empty list
     */
    List<T> execute( Map<String, Object> criteria );

    /**
     * Execute a remote call to find the list of resource instance of given type and additional filtering criteria.
     *
     * @param criteria the optional filtering criteria map
     * @param locale   the language the client has configured to prefer in results if applicable
     * @return the list of resource instance matching filtering criteria, otherwise returns empty list
     */
    List<T> execute( Map<String, Object> criteria, Locale locale );

    /**
     * Apply request specific credential and configuration to this call.
     * The underlying API must support (at least partially) this functionality
     * otherwise call to this method will be ignored and default value of target API will be used.
     *
     * @param credential the credential and configuration to be applied to this request
     * @return this request to chain calls
     */
    RetrievalRequest<T> config( RequestCredential credential );

    /**
     * Set the position of the first result to retrieve.
     *
     * @param start the position of the first result, numbered from 0
     * @return this request to chain calls
     * @throws IllegalArgumentException thrown for any negative numbers
     */
    RetrievalRequest<T> start( int start );

    /**
     * Set the maximum number of results to retrieve.
     *
     * @param length the maximum number of results to retrieve
     * @return this request to chain calls
     * @throws IllegalArgumentException thrown for any negative numbers
     */
    RetrievalRequest<T> length( int length );
}
