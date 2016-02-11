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
}
