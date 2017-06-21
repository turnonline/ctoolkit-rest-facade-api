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
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * The extended {@link Request} to provide retrieval specific API to work with list of resources.
 *
 * @param <T> the list item resource type
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface ListRetrievalRequest<T>
        extends RetrievalRequest<List<T>>
{
    /**
     * Execute a remote call to find the list of resources of given type.
     *
     * @return the list of resources
     */
    @Override
    List<T> finish();

    /**
     * Execute a remote call to find filtered list of resources.
     *
     * @param start  the position of the first result, numbered from 0
     * @param length the maximum number of results to retrieve
     * @return the list of filtered resources, if none returns empty list
     * @throws IllegalArgumentException thrown for any negative numbers
     */
    List<T> finish( int start, int length );

    /**
     * Execute a remote call to find the list of resources of given type and additional filtering criteria.
     *
     * @param criteria the optional filtering criteria map
     * @return the list of resources matching filtering criteria, otherwise returns empty list
     */
    @Override
    List<T> finish( @Nullable Map<String, Object> criteria );

    /**
     * Execute a remote call to find the list of resources with specified locale.
     *
     * @param locale the language the client has configured to prefer in results if applicable
     * @return the resource as a result of the remote call
     */
    @Override
    List<T> finish( @Nullable Locale locale );

    /**
     * Execute a remote call to find the list of resources of given type and additional filtering criteria.
     * o
     *
     * @param criteria the optional filtering criteria map
     * @param locale   the language the client has configured to prefer in results if applicable
     * @return the list of resources matching filtering criteria, otherwise returns empty list
     */
    @Override
    List<T> finish( @Nullable Map<String, Object> criteria, @Nullable Locale locale );

    /**
     * Apply request specific credential and configuration to this call.
     * The underlying API must support (at least partially) this functionality
     * otherwise call to this method will be ignored and default value of target API will be used.
     *
     * @param credential the credential and configuration to be applied to this request
     * @return this request to chain calls
     */
    @Override
    ListRetrievalRequest<T> configWith( @Nonnull RequestCredential credential );

    /**
     * Config request with optional locale.
     *
     * @param locale the language the client has configured to prefer in results if applicable
     * @return this request to chain calls
     */
    @Override
    ListRetrievalRequest<T> forLang( @Nonnull Locale locale );

    /**
     * Set the position of the first result to retrieve.
     *
     * @param start the position of the first result, numbered from 0
     * @return this request to chain calls
     * @throws IllegalArgumentException thrown for any negative numbers
     */
    ListRetrievalRequest<T> start( int start );

    /**
     * Set the maximum number of results to retrieve.
     *
     * @param length the maximum number of results to retrieve
     * @return this request to chain calls
     * @throws IllegalArgumentException thrown for any negative numbers
     */
    ListRetrievalRequest<T> length( int length );

    /**
     * Order the result based on the given property name.
     * If not set, the underlying API a default value (if any) will be applied.
     * The underlying API must support sorting functionality, otherwise will be ignored.
     *
     * @param property the resource property name used to sort the result
     * @return this request to chain calls
     */
    ListRetrievalRequest<T> orderBy( @Nullable String property );

    /**
     * Sort the result either ascending or descending for given property by {@link #orderBy(String)}.
     * If not set, the underlying API a default value (if any) will be applied.
     * The underlying API must support sorting functionality, otherwise will be ignored.
     *
     * @param ascending true to sort the result ascending
     * @return this request to chain calls
     */
    ListRetrievalRequest<T> sortAscending( boolean ascending );
}
