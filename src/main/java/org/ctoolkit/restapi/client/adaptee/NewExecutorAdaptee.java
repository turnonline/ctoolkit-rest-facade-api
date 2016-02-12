package org.ctoolkit.restapi.client.adaptee;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

/**
 * The adaptee interface to provide execute implementation for REST GET operation to get default resource instance
 * if any on top of concrete type.
 *
 * @param <M> the concrete type of request's model object to work with
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface NewExecutorAdaptee<M>
{
    /**
     * Prepare request instance to represent a remote call to GET default instance.
     * <p/>
     * Note: return null if remote call or action is not expected.
     *
     * @param type       the string representation of the concrete type
     * @param parameters the optional resource parameters
     * @param locale     the language the client has configured to prefer in results if applicable
     * @return the new request instance
     * @throws IOException may be thrown during request initialization
     */
    Object prepareNew( @Nullable String type, @Nullable Map<String, Object> parameters, @Nullable Locale locale )
            throws IOException;

    /**
     * Provide execute implementation of the remote call to get instance or create locally a specific one.
     *
     * @param request    the concrete request instance, see {@link #prepareNew(String, Map, Locale)}
     * @param parameters the optional resource parameters
     * @param locale     the the optional language the client has configured to prefer in results if applicable
     * @return the new resource instance
     * @throws IOException
     */
    M executeNew( @Nonnull Object request, @Nullable Map<String, Object> parameters, @Nullable Locale locale )
            throws IOException;
}

