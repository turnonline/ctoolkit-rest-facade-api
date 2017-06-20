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

/**
 * Unified client facade API to perform REST operations of various APIs through a single interface.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface RestFacade
{
    /**
     * Create a new in memory resource instance of requested type.
     * <p/>
     * Note: Based on the concrete implementation of the resource it may result in a remote call
     * in order to get a more specific resource instance. Either created locally with 'new'
     * operator or based on the implementation a remote call might be executed to get a 'new' instance
     * initialized with default values and given parameters if any.
     * <p/>
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
     * <p/>
     * Note: the remote call itself will be executed by request instance {@link SingleRetrievalRequest}
     * with possibility to provide optional parameters or locale.
     *
     * @param resource the type of resource to get
     * @return the fluent action, consequent call will return concrete resource instance for given type and identifier,
     * otherwise returns <tt>null</tt>
     * @throws HttpFailureException a runtime exception wrapping all REST (status code) related exceptions
     */
    <T> SingleRetrievalIdentification<T> get( @Nonnull Class<T> resource );

    /**
     * Find the list of resource instance of given type and filtering criteria.
     * <p/>
     * Note: the remote call itself will be executed by request instance {@link RetrievalRequest} with possibility
     * to provide optional parameters or locale.
     *
     * @param resource the type of resource to find
     * @return the fluent action, consequent call will return list of resource instance matching filtering criteria,
     * otherwise returns empty list
     * @throws HttpFailureException a runtime exception wrapping all REST (status code) related exceptions
     */
    <T> RetrievalRequest<T> list( @Nonnull Class<T> resource );

    /**
     * Find the list of resource instance of given type and filtering criteria.
     * <p/>
     * Note: the remote call itself will be executed by request instance {@link RetrievalRequest} with possibility
     * to provide optional parameters or locale.
     *
     * @param resource the type of resource to find
     * @param parent   the unique identifier of the parent resource as an owner of given resource if any
     * @return the fluent action, consequent call will return list of resource instance matching filtering criteria,
     * otherwise returns empty list
     * @throws HttpFailureException a runtime exception wrapping all REST (status code) related exceptions
     */
    <T> RetrievalRequest<T> list( @Nonnull Class<T> resource, @Nullable Identifier parent );

    /**
     * Insert a resource instance.
     * <p/>
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
     * <p/>
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
     * <p/>
     * Note: the remote call itself will be executed by request instance {@link PayloadRequest} with possibility
     * to provide optional parameters or locale.
     *
     * @param resource the resource instance of concrete type
     * @return the fluent action, consequent call will return updated instance
     * @throws HttpFailureException a runtime exception wrapping all REST (status code) related exceptions
     */
    <T> UpdateIdentification<T> update( @Nonnull T resource );

    /**
     * The operation to get underlying request instance related to concrete API implementation to work with.
     *
     * @param request the concrete class type of the underlying request
     * @return the fluent action
     */
    <U> UnderlyingRequest<U> underlying( @Nonnull Class<U> request );

    /**
     * Remove the resource type for given identifier.
     * <p/>
     * Note: the remote call itself will be executed by request instance {@link SingleRequest} with possibility
     * to provide optional parameters or locale.
     *
     * @param resource the type of resource to remove
     * @return the fluent action
     * @throws HttpFailureException if not matching request URI has found
     */
    <T> DeleteIdentification<T> delete( @Nonnull Class<T> resource );
}
