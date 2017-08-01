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

package org.ctoolkit.restapi.client.adaptee;

import org.ctoolkit.restapi.client.Identifier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.net.URL;
import java.util.Locale;
import java.util.Map;

/**
 * The adaptee interface to provide download configuration as an additional functionality to GET operation.
 *
 * @param <M> the concrete type of the model object to be downloaded, it's here because of injection binding
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface DownloadExecutorAdaptee<M>
{
    /**
     * Returns the full URL to download requested resource content.
     *
     * @param identifier the unique (root) identifier of the resource
     * @param type       the requested content type to be downloaded (already part of the request header)
     *                   or {@code null} to expect default
     * @param parameters the optional resource parameters
     * @param locale     the optional language the client has configured to prefer in results if applicable
     *                   (already part of the request header if any)
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
