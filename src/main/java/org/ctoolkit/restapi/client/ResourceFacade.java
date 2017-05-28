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
 * Uniform client facade API to perform common operations on top of REST to serve a resources
 * of various REST APIs through single interface.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface ResourceFacade
{
    /**
     * Create a new in memory resource instance of requested type.
     * <p/>
     * Note: Based on the concrete implementation of the resource it may result in a remote call
     * in order to get a more specific resource instance. Either created locally with 'new'
     * operator or based on the implementation a remote call may be executed to get a 'new' instance
     * initialized with default values and given parameters if any.
     * <p/>
     * Note: the remote call itself will be executed by request instance {@link SingleRequest} with possibility
     * to provide optional parameters or locale.
     *
     * @param resource the type of resource to create
     * @return the consequent call will return new resource instance for given type
     * @throws NotFoundException if not matching request URI has found (remote call)
     */
    <T> SingleRequest<T> newInstance( @Nonnull Class<T> resource );

    /**
     * Create a new in memory resource instance of requested type with optional parameters and locale.
     *
     * @param resource   the type of resource to create
     * @param parameters the optional resource parameters
     * @param locale     the language the client has configured to prefer in results if applicable
     * @return the consequent call will return new resource instance for given type
     * @throws NotFoundException if not matching request URI has found (remote call)
     */
    <T> SingleRequest<T> newInstance( @Nonnull Class<T> resource,
                                      @Nullable Map<String, Object> parameters,
                                      @Nullable Locale locale );

    /**
     * Upload a media and related metadata represented by given resource.
     *
     * @param resource the resource instance of concrete type to insert or update
     * @return the consequent call will return newly inserted or updated instance of given resource
     * @throws NotFoundException if not matching request URI has found
     */
    <T> UploadMediaRequestProvider<T> media( @Nonnull T resource );

    /**
     * Download a media for given resource.
     *
     * @param resource the type of resource to download as a media
     * @return the consequent call will return concrete resource instance for given type and identifier,
     * @throws NotFoundException if not matching request URI has found
     */
    <T> DownloadMediaRequestProvider media( @Nonnull Class<T> resource );

    /**
     * Retrieve a resource instance of requested type and identifier.
     * <p/>
     * Note: the remote call itself will be executed by request instance {@link SingleRetrievalRequest}
     * with possibility to provide optional parameters or locale.
     *
     * @param resource   the type of resource to get
     * @param identifier the unique identifier of the resource
     * @return the consequent call will return concrete resource instance for given type and identifier,
     * otherwise returns <tt>null</tt>
     */
    <T> SingleRetrievalRequest<T> get( @Nonnull Class<T> resource, @Nonnull Identifier identifier );

    /**
     * Same as {@link #get(Class, Identifier)} just identifier is being overloaded.
     *
     * @param resource   the type of resource to get
     * @param identifier the string type identifier of the resource
     * @return the consequent call will return concrete resource instance for given type and identifier,
     * otherwise returns <tt>null</tt>
     */
    <T> SingleRetrievalRequest<T> get( @Nonnull Class<T> resource, @Nonnull String identifier );

    /**
     * Same as {@link #get(Class, Identifier)} just identifier is being overloaded.
     *
     * @param resource   the type of resource to get
     * @param identifier the long type identifier of the resource
     * @return the consequent call will return concrete resource instance for given type and identifier,
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
     * @return the consequent call will return list of resource instance matching filtering criteria,
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
     * @return the consequent call will return list of resource instance matching filtering criteria,
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
     * @return the consequent call will return newly inserted instance of given resource
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
     * @return the consequent call will return newly inserted instance of given resource
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
     * @return the consequent call will return updated instance of given resource
     * @throws NotFoundException if not matching request URI has found
     */
    <T> PayloadRequest<T> update( @Nonnull T resource, @Nonnull Identifier identifier );

    /**
     * Same as {@link #update(Object, Identifier)} just identifier is being overloaded.
     *
     * @param resource   the resource instance of concrete type
     * @param identifier the string type identifier of the resource
     * @return the consequent call will return updated instance of given resource
     * @throws NotFoundException if not matching request URI has found
     */
    <T> PayloadRequest<T> update( @Nonnull T resource, @Nonnull String identifier );

    /**
     * Same as {@link #update(Object, Identifier)} just identifier is being overloaded.
     *
     * @param resource   the resource instance of concrete type
     * @param identifier the long type identifier of the resource
     * @return the consequent call will return updated instance of given resource
     * @throws NotFoundException if not matching request URI has found
     */
    <T> PayloadRequest<T> update( @Nonnull T resource, @Nonnull Long identifier );

    /**
     * The partial update to send updated data only for the specific fields to be changed,
     * represented by different (simpler) resource.
     * <p/>
     * Note: the remote call itself will be executed by request instance {@link PayloadRequest} with possibility
     * to provide optional parameters or locale.
     *
     * @param resource   the resource instance that is subset of the origin resource
     * @param identifier the unique identifier of resource to update
     * @param <T>        the type of the response
     * @return the consequent call will return response that describes the result of the partial update
     * @throws NotFoundException if not matching request URI has found
     */
    @Deprecated
    <T> PayloadRequest<T> patch( @Nonnull Patch<T> resource, @Nonnull Identifier identifier );

    /**
     * Specific patch operation to give underlying request instance
     * related to concrete API implementation to work with.
     *
     * @param request the concrete class type of the underlying request
     * @return the consequent call will return response that describes the result of the specific update
     */
    <R> PatchRequest<R> underlying( @Nonnull Class<R> request );

    /**
     * Remove the resource type for given identifier.
     * <p/>
     * Note: the remote call itself will be executed by request instance {@link SingleRequest} with possibility
     * to provide optional parameters or locale.
     *
     * @param resource   the type of resource to remove
     * @param identifier the unique identifier of resource
     * @throws NotFoundException if not matching request URI has found
     */
    <T> SingleRequest<T> delete( @Nonnull Class<T> resource, @Nonnull Identifier identifier );

    /**
     * Same as {@link #delete(Class, Identifier)} just identifier is being overloaded.
     *
     * @param resource   the type of resource to remove
     * @param identifier the string type identifier of the resource
     * @throws NotFoundException if not matching request URI has found
     */
    <T> SingleRequest<T> delete( @Nonnull Class<T> resource, @Nonnull String identifier );

    /**
     * Same as {@link #delete(Class, Identifier)} just identifier is being overloaded.
     *
     * @param resource   the type of resource to remove
     * @param identifier the long type identifier of the resource
     * @throws NotFoundException if not matching request URI has found
     */
    <T> SingleRequest<T> delete( @Nonnull Class<T> resource, @Nonnull Long identifier );
}
