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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

/**
 * The adaptee interface to provide execution implementation of the GET operation to get default resource instance
 * of concrete model type.
 *
 * @param <M> the concrete type of request's model object to work with
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface NewExecutorAdaptee<M>
{
    /**
     * Prepare request instance as a proxy object to execute a remote call to GET default instance.
     * <p/>
     * Note: return null if remote call or action is not expected.
     *
     * @param type the string representation of the concrete type
     * @return the new request instance
     * @throws IOException may be thrown during request initialization
     */
    Object prepareNew( @Nullable String type )
            throws IOException;

    /**
     * Provide execute implementation of the remote call to get instance or create locally a specific one.
     *
     * @param request    the concrete request instance, see {@link #prepareNew(String)}
     * @param parameters the optional resource parameters
     * @param locale     the optional language the client has configured to prefer in results if applicable
     * @return the new resource instance
     * @throws IOException may be thrown during request execution
     */
    M executeNew( @Nonnull Object request, @Nullable Map<String, Object> parameters, @Nullable Locale locale )
            throws IOException;
}

