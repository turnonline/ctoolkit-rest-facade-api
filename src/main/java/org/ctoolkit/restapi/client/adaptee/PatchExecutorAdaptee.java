package org.ctoolkit.restapi.client.adaptee;

import org.ctoolkit.restapi.client.Identifier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

/**
 * The adaptee interface to provide execute implementation for REST PATCH (PUT) operation on top of concrete type.
 *
 * @param <M> the concrete type of request's model object to work with
 * @param <R> the concrete type of the request
 * @param <K> the concrete type of the resource identifier
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface PatchExecutorAdaptee<M, R, K>
{
    /**
     * Prepare request instance to represent a remote call as PATCH operation.
     *
     * @param resource   the resource instance
     * @param identifier the unique identifier of the resource
     * @param alias      the patch alias
     * @return the new request instance
     * @throws IOException may be thrown during request initialization
     */
    R preparePatch( @Nonnull Object resource, @Nonnull Identifier<K> identifier, @Nonnull String alias )
            throws IOException;

    /**
     * Provide execute implementation of the patch operation.
     *
     * @param request    the concrete request instance, see {@link #preparePatch(Object, Identifier, String)}
     * @param parameters the optional parameter map
     * @param locale     the optional language the client has configured to be associated with this resource
     * @return the resource instance as the result of the implementation
     * @throws IOException
     */
    M executePatch( @Nonnull R request, @Nullable Map<String, Object> parameters, @Nullable Locale locale )
            throws IOException;
}

