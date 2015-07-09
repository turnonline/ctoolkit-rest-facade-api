package org.ctoolkit.restapi.client;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Uniform client facade API to perform common operations on top of REST to serve a resources
 * of various REST APIs through single interface.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface ResourceClient
{
    /**
     * Create a new in memory resource instance of requested type.
     * Based on the concrete implementation of the resource it may result in a remote call
     * in order to get a more specific resource instance.
     *
     * @param resource the type of resource to create
     * @return the new resource instance for given type
     */
    <T> T newInstance( @Nonnull Class<T> resource );

    /**
     * Create a new in memory resource instance of requested type
     * incl. optional argument locale to override a default value.
     *
     * @param resource the type of resource to create
     * @param locale   the language the client has configured to prefer in results if applicable
     * @return the new resource instance for given type
     */
    <T> T newInstance( @Nonnull Class<T> resource, @Nullable Locale locale );

    /**
     * Create a new in memory resource instance of requested type with optional parameters.
     *
     * @param resource   the type of resource to create
     * @param parameters the optional resource parameters
     * @return the new resource instance for given type
     */
    <T> T newInstance( @Nonnull Class<T> resource, @Nonnull Map<String, Object> parameters );


    /**
     * Create a new in memory resource instance of requested type with optional parameters.
     * <p/>
     * Note: Either created locally with 'new' operator or based on the implementation a remote call may be executed
     * to get a 'new' instance initialized with default values and given parameters if any.
     *
     * @param resource   the type of resource to create
     * @param parameters the optional resource parameters
     * @param locale     the language the client has configured to prefer in results if applicable
     * @return the new resource instance for given type
     */
    <T> T newInstance( @Nonnull Class<T> resource, @Nonnull Map<String, Object> parameters, @Nullable Locale locale );

    /**
     * Retrieve a resource instance of requested type and identifier.
     *
     * @param resource   the type of resource to get
     * @param identifier the unique identifier of the resource
     * @return the concrete resource instance for given type and identifier
     */
    <T> T get( @Nonnull Class<T> resource, @Nonnull Object identifier );

    /**
     * Same as {@link #get(Class, Object)} plus optional argument locale to override a default value.
     *
     * @param resource   the type of resource to get
     * @param identifier the unique identifier of the resource
     * @param locale     the language the client has configured to prefer in results if applicable
     * @return the concrete resource instance for given type and identifier
     */
    <T> T get( @Nonnull Class<T> resource, @Nonnull Object identifier, @Nullable Locale locale );

    /**
     * Retrieve a resource instance of requested type.
     *
     * @param resource   the type of resource to get
     * @param parameters the optional parameter map
     * @return the concrete resource instance for given type
     */
    <T> T get( @Nonnull Class<T> resource, @Nonnull Map<String, Object> parameters );

    /**
     * Retrieve a resource instance of requested type.
     *
     * @param resource   the type of resource to get
     * @param parameters the optional parameter map
     * @param locale     the language the client has configured to prefer in result if applicable
     * @return the concrete resource instance for given type
     */
    <T> T get( @Nonnull Class<T> resource,
               @Nonnull Map<String, Object> parameters,
               @Nullable Locale locale );

    /**
     * Retrieve a resource instance of requested type.
     *
     * @param resource   the type of resource to get
     * @param identifier the unique identifier of the resource
     * @param parameters the optional parameter map
     * @return the concrete resource instance for given type
     */
    <T> T get( @Nonnull Class<T> resource,
               @Nonnull Object identifier,
               @Nonnull Map<String, Object> parameters );

    /**
     * Retrieve a resource instance of requested type. At least one of the parameter to identify resource
     * must be provided, either identifier or parameters.
     *
     * @param resource   the type of resource to get
     * @param identifier the unique identifier of the resource
     * @param parameters the optional parameter map
     * @param locale     the language the client has configured to prefer in result if applicable
     * @return the concrete resource instance for given type
     */
    <T> T get( @Nonnull Class<T> resource,
               @Nullable Object identifier,
               @Nullable Map<String, Object> parameters,
               @Nullable Locale locale );

    /**
     * Find the list of resource instance of given type and filtering criteria.
     *
     * @param resource the type of resource to find
     * @param criteria the optional filtering criteria map
     * @return the list of resource instance matching filtering criteria
     */
    <T> List<T> list( @Nonnull Class<T> resource, @Nonnull Map<String, Object> criteria );

    /**
     * Same as {@link #list(Class, java.util.Map)} plus optional argument locale to override a default value.
     *
     * @param resource the type of resource to find
     * @param criteria the optional filtering criteria map
     * @param locale   the language the client has configured to prefer in results if applicable
     * @return the list of resource instance matching filtering criteria
     */
    <T> List<T> list( @Nonnull Class<T> resource,
                      @Nonnull Map<String, Object> criteria,
                      @Nullable Locale locale );

    /**
     * Insert a resource instance.
     *
     * @param resource the resource instance of concrete type to insert
     * @return the newly inserted instance of given resource
     */
    <T> T insert( @Nonnull T resource );

    /**
     * Insert a resource instance.
     *
     * @param resource the resource instance of concrete type to insert
     * @param parent   the resource parent identifier
     * @return the newly inserted instance of given resource
     */
    <T> T insert( @Nonnull T resource, @Nonnull Object parent );

    /**
     * Update the given resource instance.
     *
     * @param resource   the resource instance of concrete type
     * @param identifier the unique identifier of resource to update
     * @return the updated instance of given resource
     */
    <T> T update( @Nonnull T resource, @Nonnull Object identifier );

    /**
     * The partial update to send updated data only for the specific fields to be changed,
     * represented by different (simpler) resource.
     *
     * @param resource   the resource instance that is subset of the origin resource
     * @param identifier the unique identifier of resource to update
     * @param <T>        the type of the response
     * @return the response that describes the result of the update
     */
    <T> T patch( @Nonnull Patch<T> resource, @Nonnull Object identifier );

    /**
     * Remove the resource type for given identifier.
     *
     * @param resource   the type of resource to remove
     * @param identifier the unique identifier of resource
     */
    <T> void delete( @Nonnull Class<T> resource, @Nonnull Object identifier );
}
