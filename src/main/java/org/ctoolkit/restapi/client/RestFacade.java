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
     * operator or based on the implementation a remote call may be executed to get a 'new' instance
     * initialized with default values and given parameters if any.
     * <p/>
     * Note: the remote call itself will be executed by request instance {@link PayloadRequest} with possibility
     * to provide optional parameters or locale.
     *
     * @param resource the type of resource to create
     * @return the fluent action for consequent configurations
     * @throws NotFoundException if not matching request URI has found (remote call)
     */
    <T> PayloadRequest<T> newInstance( @Nonnull Class<T> resource );

    /**
     * Create a new in memory resource instance of requested type with optional parameters and locale.
     *
     * @param resource   the type of resource to create
     * @param parameters the optional resource parameters
     * @param locale     the language the client has configured to prefer in results if applicable
     * @return the fluent action for consequent configurations
     * @throws NotFoundException if not matching request URI has found (remote call)
     */
    <T> PayloadRequest<T> newInstance( @Nonnull Class<T> resource,
                                       @Nullable Map<String, Object> parameters,
                                       @Nullable Locale locale );

    /**
     * Upload a media and related metadata represented by given resource.
     *
     * @param resource the resource that acts as a metadata to be inserted or updated
     * @return the fluent action
     * @throws NotFoundException if not matching request URI has found
     */
    <T> UploadMediaProvider<T> upload( @Nonnull T resource );

    /**
     * Download a media for given resource.
     *
     * @param resource the type of the resource to download
     * @return the fluent action, consequent call will download requested type
     * @throws NotFoundException if not matching request URI has found
     */
    <T> DownloadMediaProvider download( @Nonnull Class<T> resource );

    /**
     * Retrieve a resource instance of requested type and identifier.
     * <p/>
     * Note: the remote call itself will be executed by request instance {@link SingleRetrievalRequest}
     * with possibility to provide optional parameters or locale.
     *
     * @param resource   the type of resource to get
     * @param identifier the unique identifier of the resource
     * @return the fluent action, consequent call will return concrete resource instance for given type and identifier,
     * otherwise returns <tt>null</tt>
     */
    <T> SingleRetrievalRequest<T> get( @Nonnull Class<T> resource, @Nonnull Identifier identifier );

    /**
     * Same as {@link #get(Class, Identifier)} just identifier is being overloaded.
     *
     * @param resource   the type of resource to get
     * @param identifier the string type identifier of the resource
     * @return the fluent action, consequent call will return concrete resource instance for given type and identifier,
     * otherwise returns <tt>null</tt>
     */
    <T> SingleRetrievalRequest<T> get( @Nonnull Class<T> resource, @Nonnull String identifier );

    /**
     * Same as {@link #get(Class, Identifier)} just identifier is being overloaded.
     *
     * @param resource   the type of resource to get
     * @param identifier the long type identifier of the resource
     * @return the fluent action, consequent call will return concrete resource instance for given type and identifier,
     * otherwise returns <tt>null</tt>
     */
    <T> SingleRetrievalRequest<T> get( @Nonnull Class<T> resource, @Nonnull Long identifier );

    /**
     * Find the list of resource instance of given type and filtering criteria.
     * <p/>
     * Note: the remote call itself will be executed by request instance {@link RetrievalRequest} with possibility
     * to provide optional parameters or locale.
     *
     * @param resource the type of resource to find
     * @return the fluent action, consequent call will return list of resource instance matching filtering criteria,
     * otherwise returns empty list
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
     * @throws NotFoundException if not matching request URI has found
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
     * @throws NotFoundException if not matching request URI has found
     */
    <T> PayloadRequest<T> insert( @Nonnull T resource, @Nullable Identifier parent );

    /**
     * Update the given resource instance.
     * <p/>
     * Note: the remote call itself will be executed by request instance {@link PayloadRequest} with possibility
     * to provide optional parameters or locale.
     *
     * @param resource   the resource instance of concrete type
     * @param identifier the unique identifier of resource to update
     * @return the fluent action, consequent call will return updated instance
     * @throws NotFoundException if not matching request URI has found
     */
    <T> PayloadRequest<T> update( @Nonnull T resource, @Nonnull Identifier identifier );

    /**
     * Same as {@link #update(Object, Identifier)} just identifier is being overloaded.
     *
     * @param resource   the resource instance of concrete type
     * @param identifier the string type identifier of the resource
     * @return the fluent action, consequent call will return updated instance
     * @throws NotFoundException if not matching request URI has found
     */
    <T> PayloadRequest<T> update( @Nonnull T resource, @Nonnull String identifier );

    /**
     * Same as {@link #update(Object, Identifier)} just identifier is being overloaded.
     *
     * @param resource   the resource instance of concrete type
     * @param identifier the long type identifier of the resource
     * @return the fluent action, consequent call will return updated instance
     * @throws NotFoundException if not matching request URI has found
     */
    <T> PayloadRequest<T> update( @Nonnull T resource, @Nonnull Long identifier );

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
     * @param resource   the type of resource to remove
     * @param identifier the unique identifier of resource
     * @return the fluent action
     * @throws NotFoundException if not matching request URI has found
     */
    <T> SingleRequest<T> delete( @Nonnull Class<T> resource, @Nonnull Identifier identifier );

    /**
     * Same as {@link #delete(Class, Identifier)} just identifier is being overloaded.
     *
     * @param resource   the type of resource to remove
     * @param identifier the string type identifier of the resource
     * @return the fluent action
     * @throws NotFoundException if not matching request URI has found
     */
    <T> SingleRequest<T> delete( @Nonnull Class<T> resource, @Nonnull String identifier );

    /**
     * Same as {@link #delete(Class, Identifier)} just identifier is being overloaded.
     *
     * @param resource   the type of resource to remove
     * @param identifier the long type identifier of the resource
     * @return the fluent action
     * @throws NotFoundException if not matching request URI has found
     */
    <T> SingleRequest<T> delete( @Nonnull Class<T> resource, @Nonnull Long identifier );
}
