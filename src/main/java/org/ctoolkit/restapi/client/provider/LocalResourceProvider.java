/*
 * Copyright (c) 2017 Comvai, s.r.o. All Rights Reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package org.ctoolkit.restapi.client.provider;

import org.ctoolkit.restapi.client.Identifier;

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
     * Note: if this method returns <tt>null</tt> the {@link #persist(Object, Identifier, Map, Locale, Long)}
     * will be called once a remote call returns a non <tt>null</tt> value. Processed in the same thread.
     *
     * @param identifier the unique identifier of the resource
     * @param parameters the optional resource parameters
     * @param locale     the language the client has configured to prefer in results if applicable
     * @return the locally cached or stored resource if exist, otherwise returns <tt>null</tt>
     */
    T get( @Nonnull Identifier identifier,
           @Nullable Map<String, Object> parameters,
           @Nullable Locale locale );

    /**
     * Optionally, persist the resource instance as a result of the remote call.
     * This method will be called in case if {@link #get(Identifier, Map, Locale)}
     * returns <tt>null</tt> and for a non null result of the remote call. Processed in the same thread.
     * <p/>
     * Note: prefer asynchronous implementation.
     *
     * @param instance   the resource instance to be either persisted or cached
     * @param identifier the unique identifier of the resource
     * @param parameters the optional resource parameters
     * @param locale     the language the client has configured to prefer in results if applicable
     * @param lastFor    the time in milliseconds how long to keep the resource cached, <tt>null</tt> for undefined
     */
    void persist( @Nonnull T instance,
                  @Nonnull Identifier identifier,
                  @Nullable Map<String, Object> parameters,
                  @Nullable Locale locale,
                  @Nullable Long lastFor );

    /**
     * Retrieve a local list of resource to avoid a remote call.
     * Note: if this method returns <tt>null</tt> the {@link #persistList(List, Map, Locale, Long)}
     * will be called once a remote call returns a non <tt>null</tt> value. Processed in the same thread.
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
     * @param list       the list of resources to be either persisted or cached
     * @param parameters the optional resource parameters
     * @param locale     the language the client has configured to prefer in results if applicable
     * @param lastFor    the time in milliseconds how long to keep the resource cached, <tt>null</tt> for undefined
     */
    void persistList( @Nonnull List<T> list,
                      @Nullable Map<String, Object> parameters,
                      @Nullable Locale locale,
                      @Nullable Long lastFor );
}
