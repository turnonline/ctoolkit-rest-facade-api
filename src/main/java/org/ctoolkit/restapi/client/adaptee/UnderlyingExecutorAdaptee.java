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
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

/**
 * The adaptee interface to provide execute implementation for underlying API.
 *
 * @param <M> the concrete type of request's model object to work with
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface UnderlyingExecutorAdaptee<M>
{
    /**
     * Prepare request instance as a proxy object to execute a remote call.
     *
     * @param resource   the resource instance, implementation can decide whether instance is mandatory or not
     * @param identifier the unique identifier of the resource,
     *                   implementation can decide whether instance is mandatory or not
     * @param parameters the optional map of parameters or configuration
     * @return the new request instance
     * @throws IOException may be thrown during request initialization
     */
    Object prepareUnderlying( @Nullable Object resource,
                              @Nullable Identifier identifier,
                              @Nullable Map<String, Object> parameters )
            throws IOException;

    /**
     * Provide execute implementation.
     *
     * @param request    the concrete request instance, see {@link #prepareUnderlying(Object, Identifier, Map)}
     * @param parameters the optional map of parameters, configuration, and credential
     * @param locale     the optional language the client has configured to be associated with this resource
     * @return the resource instance as the result of the implementation
     * @throws IOException
     */
    M executeUnderlying( @Nonnull Object request, @Nullable Map<String, Object> parameters, @Nullable Locale locale )
            throws IOException;
}

