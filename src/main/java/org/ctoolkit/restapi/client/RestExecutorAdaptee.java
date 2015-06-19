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
 * @param <T> the concrete type of the model object
 * @param <I> the concrete type of the resource identifier
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface RestExecutorAdaptee<T, I>
{
    /**
     * Note: returns null if there is no specific call to create a new default instance.
     */
    T executeNew( @Nullable Locale locale,
                  @Nullable String type,
                  @Nullable Map<String, Object> parameters )
            throws IOException;

    T executeGet( @Nullable I identifier, @Nullable Map<String, Object> parameters, @Nullable Locale locale )
            throws IOException;

    List<T> executeList( @Nullable Map<String, Object> criteria, @Nullable Locale locale )
            throws IOException;

    T executeInsert( @Nonnull T json, @Nullable Object parentKey )
            throws IOException;

    T executeUpdate( @Nonnull T json, @Nonnull I identifier )
            throws IOException;

    Object executePatch( @Nonnull Object json, @Nonnull Patch<?> origin, @Nonnull I identifier )
            throws IOException;

    void executeRemove( @Nonnull I identifier )
            throws IOException;
}

