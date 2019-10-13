/*
 * Copyright (c) 2019 Comvai, s.r.o. All Rights Reserved.
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

import org.ctoolkit.restapi.client.AuthRequest;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * The abstraction to provide 'Authorization' token via client custom implementation.
 *
 * @param <O> type of the entity (user) on behalf of whom token will be issued
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface TokenProvider<O>
{
    /**
     * Prepares token to be added as a 'Authorization' header right before the remote call.
     *
     * @param scheme the authorization scheme or {@code null} if no scheme to be prepended to the token
     * @param of     the on behalf of user
     * @return the 'Authorization' token or {@code null} to be ignored
     */
    String token( @Nullable AuthRequest.AuthScheme scheme, @Nullable O of );

    /**
     * Prepares headers to be added in to client request right before the remote call.
     *
     * @param of the on behalf of user
     * @return the headers or {@code null} if nothing to be added to the request
     */
    default Map<String, String> headers( @Nullable O of )
    {
        return null;
    }
}
