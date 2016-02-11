package org.ctoolkit.restapi.client.adaptee;

import org.ctoolkit.restapi.client.Identifier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * The adaptee interface to provide execute implementation for REST GET operation to retrieve list of resources
 * on top of concrete type.
 *
 * @param <M> the concrete type of request's model object to work with
 * @param <R> the concrete type of the request
 * @param <K> the concrete type of the resource identifier
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface ListExecutorAdaptee<M, R, K>
{
    /**
     * Prepare request instance to represent a remote call as list operation.
     *
     * @param parentKey the unique identifier of the parent resource as an owner of this resource if any
     * @return the new request instance
     * @throws IOException may be thrown during request initialization
     */
    R prepareList( @Nullable Identifier<K> parentKey )
            throws IOException;

    /**
     * Provide execute implementation of the list operation.
     *
     * @param request  the concrete request instance, see {@link #prepareList(Identifier)}
     * @param criteria the optional filtering criteria map
     * @param locale   the the optional language the client has configured to prefer in results if applicable
     * @return the list of resource instance matching filtering criteria, otherwise returns empty list
     * @throws IOException
     */
    List<M> executeList( @Nonnull Object request, @Nullable Map<String, Object> criteria, @Nullable Locale locale )
            throws IOException;
}

