package org.ctoolkit.restapi.client.adaptee;

import org.ctoolkit.restapi.client.Identifier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Locale;

/**
 * The adaptee interface to provide execute implementation for REST DELETE operation on top of concrete type.
 *
 * @param <M> the concrete type of request's model object to work with
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface DeleteExecutorAdaptee<M>
{
    /**
     * Prepare request instance to represent a remote call as DELETE operation.
     *
     * @param identifier the unique identifier of the resource
     * @return the new request instance
     * @throws IOException may be thrown during request initialization
     */
    Object prepareDelete( @Nonnull Identifier identifier )
            throws IOException;

    /**
     * Provide execute implementation of the delete operation.
     *
     * @param request the concrete request instance, see {@link #prepareDelete(Identifier)}
     * @param locale  the optional language to identify language specific resource if applicable
     * @throws IOException
     */
    void executeDelete( @Nonnull Object request, @Nullable Locale locale )
            throws IOException;
}

