package org.ctoolkit.restapi.client.adaptee;

import org.ctoolkit.restapi.client.Identifier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

/**
 * The adaptee interface to provide execute implementation for REST POST operation on top of concrete type.
 *
 * @param <M> the concrete type of request's model object to work with
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface InsertExecutorAdaptee<M>
{
    /**
     * Prepare request instance to represent a remote call as INSERT operation.
     *
     * @param resource  the resource instance
     * @param parentKey the unique identifier of the parent resource as an owner of the given resource
     * @return the new request instance
     * @throws IOException may be thrown during request initialization
     */
    Object prepareInsert( @Nonnull M resource, @Nullable Identifier parentKey )
            throws IOException;

    /**
     * Provide execute implementation of the insert operation.
     *
     * @param request    the concrete request instance, see {@link #prepareInsert(Object, Identifier)}
     * @param parameters the optional parameter map
     * @param locale     the the optional language the client has configured to be associated with this resource
     * @return the resource instance as the result of the implementation
     * @throws IOException
     */
    M executeInsert( @Nonnull Object request, @Nullable Map<String, Object> parameters, @Nullable Locale locale )
            throws IOException;
}

