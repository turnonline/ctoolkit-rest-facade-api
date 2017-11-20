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

package org.ctoolkit.restapi.client;

/**
 * The client error, the request that could not be understood by the server. The client SHOULD NOT repeat the request
 * without modifications. Ideally all client error exceptions should be caught during development.
 * <p>
 * Generally most of HTTP 4xx status codes are converted into this exception.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class ClientErrorException
        extends HttpFailureException
{
    /**
     * The default client error exception, HTTP 400 status code, with no error message.
     */
    public ClientErrorException()
    {
        super( 400 );
    }

    /**
     * The client error exception with HTTP 400 status code and customized error message.
     *
     * @param message the error message
     */
    public ClientErrorException( String message )
    {
        super( 400, message );
    }

    /**
     * The client error exception with defined HTTP 4xx status code.
     *
     * @param statusCode the HTTP 4xx status code
     */
    public ClientErrorException( int statusCode )
    {
        super( statusCode );
    }

    /**
     * The client error exception with defined HTTP 4xx status code and customized error message.
     *
     * @param statusCode the HTTP 4xx status code
     * @param message    the error message
     */
    public ClientErrorException( int statusCode, String message )
    {
        super( statusCode, message );
    }
}
