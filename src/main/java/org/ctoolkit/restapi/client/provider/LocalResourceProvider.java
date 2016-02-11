package org.ctoolkit.restapi.client.provider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * The interface that provides possibility to plug-in right before a remote resource retrieval (either get or list).
 * The implementation of concrete type is optional and will be used once binded in guice configuration.
 * <p/>
 * To bind a concrete implementation use this in your guice module: <pre>{@code
 *  bind( new TypeLiteral<LocalResourceProvider<MyResource>>(){} )
 *      .to( MyResourceProvider.class );
 * }</pre>
 *
 * @param <T> the type of the resource
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface LocalResourceProvider<T>
{
    /**
     * Retrieve a local resource to avoid a remote call.
     * Note: if this method returns <tt>null</tt> it will make call of {@link #persist(Object, Object, Map, Locale)}
     * once a remote resource call returns a non <tt>null</tt> value. Processed in the same thread.
     *
     * @param identifier       the unique identifier of the resource
     * @param parameters       the optional resource parameters
     * @param locale           the language the client has configured to prefer in results if applicable
     * @param lastModifiedDate the last modified date of the remote resource if any
     * @return the locally cached or stored resource if exist, otherwise returns <tt>null</tt>
     */
    T get( Object identifier,
           @Nullable Map<String, Object> parameters,
           @Nullable Locale locale,
           @Nullable Date lastModifiedDate );

    /**
     * Optionally, persist the resource instance as a result of the remote call.
     * This method will be called in case if {@link #get(Object, Map, Locale, Date)}
     * returns <tt>null</tt> and for a non null result of the remote call. Processed in the same thread.
     * <p/>
     * Note: prefer asynchronous implementation.
     *
     * @param instance   the resource instance to be either persisted or cached
     * @param identifier the unique identifier of the resource
     * @param parameters the optional resource parameters
     * @param locale     the language the client has configured to prefer in results if applicable
     */
    void persist( @Nonnull T instance,
                  @Nullable Object identifier,
                  @Nullable Map<String, Object> parameters,
                  @Nullable Locale locale );

    /**
     * Retrieve a local list of resource to avoid a remote call.
     * Note: if this method returns <tt>null</tt> it will make call of {@link #persistList(List, Map, Locale)}
     * once a remote resource call returns a non <tt>null</tt> value. Processed in the same thread.
     *
     * @param parameters       the optional resource parameters
     * @param locale           the language the client has configured to prefer in results if applicable
     * @param lastModifiedDate the last modified date of the remote resource if any
     * @return the locally cached or stored resource if exist, otherwise returns <tt>null</tt>
     */
    List<T> list( @Nullable Map<String, Object> parameters,
                  @Nullable Locale locale,
                  @Nullable Date lastModifiedDate );

    /**
     * Optionally, persist the resource instance as a result of the remote call.
     * This method will be called in case if {@link #list(Map, Locale, Date)}
     * returns <tt>null</tt> and for a non null result of the remote call. Processed in the same thread.
     * <p/>
     * Note: prefer asynchronous implementation.
     *
     * @param instance   the resource instance to be either persisted or cached
     * @param parameters the optional resource parameters
     * @param locale     the language the client has configured to prefer in results if applicable
     */
    void persistList( @Nonnull List<T> instance,
                      @Nullable Map<String, Object> parameters,
                      @Nullable Locale locale );
}
