package org.ctoolkit.restapi.client.adaptee;

import org.ctoolkit.restapi.client.Identifier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

/**
 * The adaptee interface to provide execute implementation for REST GET operation on top of concrete type.
 *
 * @param <M> the concrete type of request's model object to work with
 * @param <R> the concrete type of the request
 * @param <K> the concrete type of the resource identifier
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface GetExecutorAdaptee<M, R, K>
{
    /**
     * Prepare request instance to represent a remote call as GET operation.
     *
     * @param identifier the unique identifier of the resource
     * @return the new request instance
     * @throws IOException may be thrown during request initialization
     */
    R prepareGet( @Nonnull Identifier<K> identifier )
            throws IOException;

    /**
     * Provide execute implementation of the get operation.
     *
     * @param request    the concrete request instance, see {@link #prepareGet(Identifier)}
     * @param parameters the optional parameter map
     * @param locale     the the optional language the client has configured to prefer in results if applicable
     * @return the resource instance
     * @throws IOException
     */
    M executeGet( @Nonnull R request, @Nullable Map<String, Object> parameters, @Nullable Locale locale )
            throws IOException;

}

