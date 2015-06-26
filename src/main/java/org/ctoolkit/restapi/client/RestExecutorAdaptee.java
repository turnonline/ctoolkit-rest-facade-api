package org.ctoolkit.restapi.client;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * The adaptee interface to provide execute implementation for REST like operations on top of concrete type.
 *
 * @param <T> the concrete type of the model object to work with
 * @param <I> the concrete type of the resource identifier
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface RestExecutorAdaptee<T, I>
{
    /**
     * Provide execute implementation of the remote call to get instance or create a specific one.
     * <p/>
     * Note: return null if there is no specific remote call or action to create a new default instance.
     *
     * @param locale     the language the client has configured to prefer in results if applicable
     * @param type       the string representation of the concrete type
     * @param parameters the optional resource parameters
     * @return the new resource instance
     * @throws IOException
     */
    T executeNew( @Nullable Locale locale, @Nullable String type, @Nullable Map<String, Object> parameters )
            throws IOException;

    /**
     * Provide execute implementation of the get operation.
     *
     * @param identifier the unique identifier of the resource
     * @param parameters the optional resource parameters
     * @param locale     the language the client has configured to prefer in results if applicable
     * @return the resource instance
     * @throws IOException
     */
    T executeGet( @Nullable I identifier, @Nullable Map<String, Object> parameters, @Nullable Locale locale )
            throws IOException;

    /**
     * Provide execute implementation of the list operation.
     *
     * @param criteria the optional filtering criteria
     * @param locale   the language the client has configured to prefer in results if applicable
     * @return the list of resource instance matching filtering criteria
     * @throws IOException
     */
    List<T> executeList( @Nullable Map<String, Object> criteria, @Nullable Locale locale )
            throws IOException;

    /**
     * Provide execute implementation of the insert operation.
     *
     * @param resource  the resource instance
     * @param parentKey the unique identifier of the parent resource as an owner of the given resource
     * @return the resource instance as the result of the implementation
     * @throws IOException
     */
    T executeInsert( @Nonnull T resource, @Nullable Object parentKey )
            throws IOException;

    /**
     * Provide execute implementation of the update operation.
     *
     * @param resource   the resource instance
     * @param identifier the unique identifier of the resource
     * @return the resource instance as the result of the implementation
     * @throws IOException
     */
    T executeUpdate( @Nonnull T resource, @Nonnull I identifier )
            throws IOException;

    /**
     * Provide execute implementation of the patch operation.
     *
     * @param resource   the resource instance
     * @param identifier the unique identifier of the resource
     * @param alias
     * @return the resource instance as the result of the implementation
     * @throws IOException
     */
    Object executePatch( @Nonnull Object resource, @Nonnull I identifier, @Nonnull String alias )
            throws IOException;

    /**
     * Provide execute implementation of the delete operation.
     *
     * @param identifier the unique identifier of the resource
     * @throws IOException
     */
    void executeDelete( @Nonnull I identifier )
            throws IOException;
}

