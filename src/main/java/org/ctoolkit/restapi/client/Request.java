package org.ctoolkit.restapi.client;

import java.util.Locale;
import java.util.Map;

/**
 * The top level request wrapper to provide possibility to isolate a remote call to standalone object.
 *
 * @param <T> the resource type
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface Request<T>
{
    /**
     * Returns the underlying request instance in order to configure an additional query parameters
     * or provide specific request configuration.
     *
     * @param type the underlying request related to concrete API implementation
     * @param <Q>  the class type of underlying request to be cast
     * @return the underlying request object
     * @throws ClassCastException
     */
    <Q> Q query( Class<Q> type );

    /**
     * Execute a remote call based on the underlying {@link #query(Class)} instance.
     *
     * @return the resource as a result of the remote call
     */
    T execute();

    /**
     * Execute a remote call with additional resource parameters.
     *
     * @param parameters the optional resource parameters
     * @return the resource as a result of the remote call
     */
    T execute( Map<String, Object> parameters );

    /**
     * Execute a remote call with specified locale.
     *
     * @param locale the language the client has configured to prefer in results if applicable
     * @return the resource as a result of the remote call
     */
    T execute( Locale locale );

    /**
     * Execute a remote call with additional resource parameters or locale.
     *
     * @param parameters the optional resource parameters
     * @param locale     the language the client has configured to prefer in results if applicable
     * @return the resource as a result of the remote call
     */
    T execute( Map<String, Object> parameters, Locale locale );
}
