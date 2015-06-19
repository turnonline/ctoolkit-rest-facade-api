package org.ctoolkit.restapi.client;

import java.io.Serializable;

/**
 * An interface that acts as an input resource that is a subset of the origin resource.
 * A marker to define its response type.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface Patch<T>
        extends Serializable
{
    /**
     * Returns the response class type.
     *
     * @return the response type
     */
    Class<T> type();
}
