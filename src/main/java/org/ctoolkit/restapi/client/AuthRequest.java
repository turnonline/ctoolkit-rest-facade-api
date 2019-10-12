/*
 * Copyright (c) 2018 Comvai, s.r.o. All Rights Reserved.
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

package org.ctoolkit.restapi.client;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * The interface to provide possibility to configure request with authentication token.
 * Once token is being provided at the request level, it bypasses the underlying authorization mechanism.
 *
 * @param <T> the resource type
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface AuthRequest<T>
        extends Request<T>
{
    /**
     * Setup the authentication schema prefix for the token provided by {@link Request#authBy(String)}.
     *
     * @return the request to chain calls
     */
    Request<T> bearer();

    /**
     * Setup the authentication schema prefix for the token provided by {@link Request#authBy(String)}.
     *
     * @return the request to chain calls
     */
    Request<T> oauth();

    enum AuthScheme
    {
        BEARER( "Bearer" ),

        OAUTH( "OAuth" );

        private String value;

        AuthScheme( String value )
        {
            this.value = value;
        }

        public String getValue()
        {
            return value;
        }
    }

    /**
     * The abstraction to provide 'Authorization' token via client custom implementation.
     */
    interface TokenProvider
    {
        /**
         * Prepares token to be added as a 'Authorization' header right before the remote call.
         *
         * @param scheme the authorization scheme or {@code null} if no scheme to be prepended to the token
         * @return the 'Authorization' token
         */
        String token( @Nullable AuthRequest.AuthScheme scheme );

        /**
         * Prepares headers to be added in to client request right before the remote call.
         *
         * @return headers or {@code null} if nothing to be added
         */
        default Map<String, String> headers()
        {
            return null;
        }
    }
}
