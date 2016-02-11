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
     * in order to get a more specific resource instance -> Either created locally with 'new'
     * operator or based on the implementation a remote call may be executed to get a 'new' instance
     * initialized with default values and given parameters if any.
     * <p/>
     * Note: the remote call itself will be executed by request instance {@link SingleRequest} with possibility
     * to provide optional parameters or locale.
     *
     * @param resource the type of resource to create
     * @return the new resource instance for given type
     * @throws NotFoundException if not matching request URI has found (remote call)
     */
    <T> SingleRequest<T> newInstance( @Nonnull Class<T> resource );

    /**
     * Create a new in memory resource instance of requested type with optional parameters and locale.
     *
     * @param resource   the type of resource to create
     * @param parameters the optional resource parameters
     * @param locale     the language the client has configured to prefer in results if applicable
     * @return the new resource instance for given type
     * @throws NotFoundException if not matching request URI has found (remote call)
     */
    <T> SingleRequest<T> newInstance( @Nonnull Class<T> resource,
                                      @Nullable Map<String, Object> parameters,
                                      @Nullable Locale locale );

    /**
     * Retrieve a resource instance of requested type and identifier.
     * <p/>
     * Note: the remote call itself will be executed by request instance {@link SingleRequest} with possibility
     * to provide optional parameters or locale.
     *
     * @param resource   the type of resource to get
     * @param identifier the unique identifier of the resource
     * @return the concrete resource instance for given type and identifier, otherwise returns <tt>null</tt>
     */
    <T> SingleRequest<T> get( @Nonnull Class<T> resource, @Nonnull Identifier identifier );

    /**
     * Find the list of resource instance of given type and filtering criteria.
     * <p/>
     * Note: the remote call itself will be executed by request instance {@link RetrievalRequest} with possibility
     * to provide optional parameters or locale.
     *
     * @param resource the type of resource to find
     * @return the list of resource instance matching filtering criteria, otherwise returns empty list
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
     * @return the list of resource instance matching filtering criteria, otherwise returns empty list
     */
    <T> RetrievalRequest<T> list( @Nonnull Class<T> resource, @Nullable Identifier parent );

    /**
     * Insert a resource instance.
     * <p/>
     * Note: the remote call itself will be executed by request instance {@link SingleRequest} with possibility
     * to provide optional parameters or locale.
     *
     * @param resource the resource instance of concrete type to insert
     * @return the newly inserted instance of given resource
     * @throws NotFoundException if not matching request URI has found
     */
    <T> SingleRequest<T> insert( @Nonnull T resource );

    /**
     * Insert a resource instance.
     * <p/>
     * Note: the remote call itself will be executed by request instance {@link SingleRequest} with possibility
     * to provide optional parameters or locale.
     *
     * @param resource the resource instance of concrete type to insert
     * @param parent   the resource parent identifier
     * @return the newly inserted instance of given resource
     * @throws NotFoundException if not matching request URI has found
     */
    <T> SingleRequest<T> insert( @Nonnull T resource, @Nullable Identifier parent );

    /**
     * Update the given resource instance.
     * <p/>
     * Note: the remote call itself will be executed by request instance {@link SingleRequest} with possibility
     * to provide optional parameters or locale.
     *
     * @param resource   the resource instance of concrete type
     * @param identifier the unique identifier of resource to update
     * @return the updated instance of given resource
     * @throws NotFoundException if not matching request URI has found
     */
    <T> SingleRequest<T> update( @Nonnull T resource, @Nonnull Identifier identifier );

    /**
     * The partial update to send updated data only for the specific fields to be changed,
     * represented by different (simpler) resource.
     * <p/>
     * Note: the remote call itself will be executed by request instance {@link SingleRequest} with possibility
     * to provide optional parameters or locale.
     *
     * @param resource   the resource instance that is subset of the origin resource
     * @param identifier the unique identifier of resource to update
     * @param <T>        the type of the response
     * @return the response that describes the result of the update
     * @throws NotFoundException if not matching request URI has found
     */
    <T> SingleRequest<T> patch( @Nonnull Patch<T> resource, @Nonnull Identifier identifier );

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
}
