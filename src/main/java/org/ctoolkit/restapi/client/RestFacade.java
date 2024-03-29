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
import java.util.Collection;

/**
 * Fluent facade API to allow perform REST operations of various APIs through a single interface.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface RestFacade
{
    /**
     * ISO-8601 (RFC-3339), date format.
     * Recommended messaging (Publish/Subscribe)
     * <p>
     * <strong>It includes:</strong>
     * <ul>
     * <li>2018-12-28T20:00:30.999Z - Millisecond fractions, UTC/Zulu time, has no offset</li>
     * <li>2018-12-28T21:00:30.999+01:00 - Millisecond fractions, CET time</li>
     * </ul>
     */
    String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    /**
     * Create a new in memory resource instance of requested type.
     * <p>
     * Note: Based on the concrete implementation of the resource it may result in a remote call
     * in order to get a more specific resource instance. Either created locally with 'new'
     * operator or based on the implementation a remote call might be executed to get a 'new' instance
     * initialized with default values and given parameters if any.
     * <p>
     * Note: the remote call itself will be executed by request instance {@link PayloadRequest} with possibility
     * to provide optional parameters or locale.
     *
     * @param resource the type of resource to create
     * @return the fluent action for consequent configurations
     * @throws HttpFailureException a runtime exception wrapping all REST (status code) related exceptions
     */
    <T> PayloadRequest<T> newInstance( @Nonnull Class<T> resource );

    /**
     * Upload a media and related metadata represented by given resource.
     *
     * @param resource the resource that acts as a metadata to be inserted or updated
     * @return the fluent action, consequent call will let you provide and configure a media to be uploaded
     * @throws HttpFailureException a runtime exception wrapping all REST (status code) related exceptions
     */
    <T> UploadMediaProvider<T> upload( @Nonnull T resource );

    /**
     * Download a media for given resource.
     *
     * @param resource the type of the resource to download
     * @return the fluent action, consequent call will download requested type
     * @throws HttpFailureException a runtime exception wrapping all REST (status code) related exceptions
     */
    <T> DownloadMediaProvider download( @Nonnull Class<T> resource );

    /**
     * Retrieve a resource instance of requested type and identifier.
     * <p>
     * Note: the remote call itself will be executed by request instance {@link RetrievalRequest}
     * with possibility to provide optional parameters or locale.
     *
     * @param resource the type of resource to get
     * @return the fluent action, consequent call will return concrete resource instance for given type and identifier,
     * otherwise returns <code>null</code>
     * @throws HttpFailureException a runtime exception wrapping all REST (status code) related exceptions
     */
    <T> SingleRetrievalIdentification<T> get( @Nonnull Class<T> resource );

    /**
     * Find the list of resource instance of given type and filtering criteria.
     * <p>
     * Note: the remote call itself will be executed by request instance {@link ListRetrievalRequest} with possibility
     * to provide optional parameters or locale.
     *
     * @param resource the type of resource to find
     * @return the fluent action, consequent call will return list of resource instance matching filtering criteria,
     * otherwise returns empty list
     * @throws HttpFailureException a runtime exception wrapping all REST (status code) related exceptions
     */
    <T> ListRetrievalRequest<T> list( @Nonnull Class<T> resource );

    /**
     * Find the list of resource instance of given type and filtering criteria.
     * <p>
     * Note: the remote call itself will be executed by request instance {@link ListRetrievalRequest} with possibility
     * to provide optional parameters or locale.
     *
     * @param resource the type of resource to find
     * @param parent   the unique identifier of the parent resource as an owner of given resource if any
     * @return the fluent action, consequent call will return list of resource instance matching filtering criteria,
     * otherwise returns empty list
     * @throws HttpFailureException a runtime exception wrapping all REST (status code) related exceptions
     */
    <T> ListRetrievalRequest<T> list( @Nonnull Class<T> resource, @Nullable Identifier parent );

    /**
     * Insert a resource instance.
     * <p>
     * Note: the remote call itself will be executed by request instance {@link PayloadRequest} with possibility
     * to provide optional parameters or locale.
     *
     * @param resource the resource instance of concrete type to insert
     * @return the fluent action, consequent call will return newly inserted instance
     * @throws HttpFailureException a runtime exception wrapping all REST (status code) related exceptions
     */
    <T> PayloadRequest<T> insert( @Nonnull T resource );

    /**
     * Insert a resource instance.
     * <p>
     * Note: the remote call itself will be executed by request instance {@link PayloadRequest} with possibility
     * to provide optional parameters or locale.
     *
     * @param resource the resource instance of concrete type to insert
     * @param parent   the resource parent identifier
     * @return the fluent action, consequent call will return newly inserted instance
     * @throws HttpFailureException a runtime exception wrapping all REST (status code) related exceptions
     */
    <T> PayloadRequest<T> insert( @Nonnull T resource, @Nullable Identifier parent );

    /**
     * Update the given resource instance.
     * <p>
     * Note: the remote call itself will be executed by request instance {@link PayloadRequest} with possibility
     * to provide optional parameters or locale.
     *
     * @param resource the resource instance of concrete type
     * @return the fluent action, consequent call will return updated instance
     * @throws HttpFailureException a runtime exception wrapping all REST (status code) related exceptions
     */
    <T> UpdateIdentification<T> update( @Nonnull T resource );

    /**
     * Remove the resource type for given identifier.
     * <p>
     * Note: the remote call itself will be executed by request instance {@link SimpleRequest} with possibility
     * to provide optional parameters or locale.
     *
     * @param resource the type of resource to remove
     * @return the fluent action
     * @throws HttpFailureException if not matching request URI has found
     */
    <T> DeleteIdentification<T> delete( @Nonnull Class<T> resource );

    /**
     * Get already initialized underlying API specific client instance to work with.
     *
     * @param type the concrete class type of the underlying client
     * @return the underlying client instance
     * @throws NotFoundException if unknown client type has been requested
     */
    <C> C client( @Nonnull Class<C> type );

    /**
     * Same as {@link #impersonate(Collection, String, String)}
     * where scopes parameter will be the same as for default configuration.
     *
     * @param userEmail the email address of the user to impersonate
     * @param api       the short name of an API that has been installed with this facade
     * @throws IllegalArgumentException if requested API is not initialized or impersonate is not supported
     */
    void impersonate( @Nonnull String userEmail, @Nonnull String api );

    /**
     * Sets the email address of the user and API scopes the application is trying to impersonate in the service
     * account flow for current thread (only) of the specified API.
     * <p>
     * It enables a service account (with domain-wide delegation enabled) to impersonate a managed user,
     * ie. act on behalf of an identity, via domain-wide delegation of authority (for example G-Suite).
     * An administrator of a target domain must configure service account's Client ID
     * to allow access to desired domain.
     *
     * @param scopes    the scopes for use with API
     * @param userEmail the email address of the user to impersonate
     * @param api       the short name of an API that has been installed with this facade
     * @throws IllegalArgumentException if requested API is not initialized or impersonate is not supported
     */
    void impersonate( @Nonnull Collection<String> scopes, @Nonnull String userEmail, @Nonnull String api );
}
