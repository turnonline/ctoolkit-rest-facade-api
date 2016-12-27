package org.ctoolkit.restapi.client.adaptee;

import org.ctoolkit.restapi.client.Identifier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.net.URL;
import java.util.Locale;
import java.util.Map;

/**
 * The adaptee interface to provide download configuration as additional functionality to REST GET.
 *
 * @param <M> the concrete type of request's model object to work with
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface DownloadExecutorAdaptee<M>
        extends GetExecutorAdaptee<M>
{
    /**
     * Returns the full URL to download requested resource content.
     *
     * @param identifier the unique identifier of the resource
     * @param type       the content type or {@code null} to expect default
     * @param parameters the optional resource parameters
     * @param locale     the the optional language the client has configured to prefer in results if applicable
     * @return the full download path
     */
    URL prepareDownloadUrl( @Nonnull Identifier identifier,
                            @Nullable String type,
                            @Nullable Map<String, Object> parameters,
                            @Nullable Locale locale );

    /**
     * Returns the API prefix in order to take a specific configuration.
     * If returns {@code null} default configuration will be used.
     *
     * @return the API prefix
     */
    String getApiPrefix();
}
