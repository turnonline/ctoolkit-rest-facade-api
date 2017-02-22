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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The request credential class with convenience methods to access credential and configuration.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class RequestCredential
        extends ApiCredential
{
    private static final String REQUEST_CREDENTIAL_PREFIX = "request-scope";

    private static final long serialVersionUID = 4050136829585508854L;

    public RequestCredential()
    {
        super( REQUEST_CREDENTIAL_PREFIX );
    }

    public RequestCredential( @Nonnull Map<String, String> properties )
    {
        super( properties, REQUEST_CREDENTIAL_PREFIX );
    }

    /**
     * Fill in the properties read from the given source that matches {@link #REQUEST_CREDENTIAL_PREFIX}
     * and one of the property defined by {@link ApiCredential}.
     *
     * @param source the source of properties to read from
     */
    public void fillInFrom( @Nullable Map<String, Object> source )
    {
        fillInFrom( source, false );
    }

    /**
     * Fill in the properties read from the given source that matches {@link #REQUEST_CREDENTIAL_PREFIX}
     * and one of the property defined by {@link ApiCredential}.
     *
     * @param source the source of properties to read from
     * @param remove true to remove the property from the source if matched
     * @return the map of removed properties
     */
    public Map<String, Object> fillInFrom( @Nullable Map<String, Object> source, boolean remove )
    {
        Map<String, Object> removedProps = new HashMap<>();
        if ( source == null )
        {
            // nothing to extract
            return removedProps;
        }

        Set<String> props = new HashSet<>();

        props.add( PROPERTY_PROJECT_ID );
        props.add( PROPERTY_CLIENT_ID );
        props.add( PROPERTY_DISABLE_GZIP_CONTENT );
        props.add( PROPERTY_SERVICE_ACCOUNT_EMAIL );
        props.add( PROPERTY_APPLICATION_NAME );
        props.add( PROPERTY_FILE_NAME );
        props.add( PROPERTY_FILE_NAME_JSON_STREAM );
        props.add( PROPERTY_API_KEY );
        props.add( PROPERTY_ENDPOINT_URL );
        props.add( PROPERTY_CREDENTIAL_ON );
        props.add( PROPERTY_NUMBER_OF_RETRIES );
        props.add( PROPERTY_READ_TIMEOUT );

        String fullProperty;
        for ( String property : props )
        {
            fullProperty = CREDENTIAL_ATTR + prefix + property;
            Object value = source.get( fullProperty );

            if ( value instanceof String )
            {
                this.setProperty( fullProperty, ( String ) value );
                if ( remove )
                {
                    Object removed = source.remove( fullProperty );
                    removedProps.put( fullProperty, removed );
                }
            }
        }
        return removedProps;
    }

    /**
     * Populates the given map with properties from this instance by
     * list of key (if key is string) and its corresponding value.
     *
     * @param properties the map to be populated
     * @return the same instance (populated) as input or a new map if {@code null} was given
     */
    public Map<String, Object> populate( @Nullable Map<String, Object> properties )
    {
        Map<String, Object> map;
        if ( properties == null )
        {
            map = new HashMap<>();
        }
        else
        {
            map = properties;
        }

        for ( Enumeration next = keys(); next.hasMoreElements(); )
        {
            Object key = next.nextElement();
            Object value;
            if ( key instanceof String )
            {
                value = get( key );
                map.put( ( String ) key, value );
            }
        }

        return map;
    }

    /**
     * Returns Google Cloud Project ID also known as applicationId (AppId).
     *
     * @return the project ID
     */
    public String getProjectId()
    {
        return getStringValue( PROPERTY_PROJECT_ID );
    }

    /**
     * Returns the Google API OAuth 2.0 Client ID Credential.
     *
     * @return the client ID
     */
    public String getClientId()
    {
        return getStringValue( PROPERTY_CLIENT_ID );
    }

    /**
     * Returns whether to disable GZip compression of HTTP content
     *
     * @return true to disable GZip compression. Otherwise HTTP content will be compressed.
     */
    public final boolean isDisableGZipContent()
    {
        String value = getStringValue( PROPERTY_DISABLE_GZIP_CONTENT );
        if ( value == null )
        {
            return false;
        }

        return Boolean.valueOf( value );
    }

    /**
     * Returns the service account ID (typically an e-mail address).
     *
     * @return the service email
     */
    public String getServiceAccountEmail()
    {
        return getStringValue( PROPERTY_SERVICE_ACCOUNT_EMAIL );
    }

    /**
     * Returns the name of the client application.
     *
     * @return the name of the client application.
     */
    public String getApplicationName()
    {
        return getStringValue( PROPERTY_APPLICATION_NAME );
    }

    /**
     * Returns the path name to the private key file.
     *
     * @return the relative path to file
     */
    public String getFileName()
    {
        return getStringValue( PROPERTY_FILE_NAME );
    }

    /**
     * Returns the API authentication key.
     *
     * @return the API key
     */
    public String getApiKey()
    {
        return getStringValue( PROPERTY_API_KEY );
    }

    /**
     * Returns the backend service API endpoint URL.
     *
     * @return the endpoint URL
     */
    public String getEndpointUrl()
    {
        return getStringValue( PROPERTY_ENDPOINT_URL );
    }

    /**
     * Returns the boolean identification whether current environment should use these credential
     * in order to authenticate client calls or use cloud native environment for authentication.
     *
     * @return the true if credential will be used to authenticate
     */
    public final boolean isCredentialOn()
    {
        String value = getStringValue( PROPERTY_CREDENTIAL_ON );
        if ( value == null )
        {
            return false;
        }

        return Boolean.valueOf( value );
    }

    /**
     * Returns the number of retries that will be allowed to execute before the request will be
     * terminated or {@code 0} to not retry requests.
     * <p>
     * The default value is {@code 1}.
     * </p>
     *
     * @return the number of retries
     */
    public int getNumberOfRetries()
    {
        String value = getProperty( CREDENTIAL_ATTR + prefix + PROPERTY_NUMBER_OF_RETRIES );
        if ( value == null )
        {
            value = DEFAULT_NUMBER_OF_RETRIES;
        }
        return Integer.valueOf( value );
    }

    /**
     * Returns the timeout in milliseconds to read data from an established connection or {@code 0} for
     * an infinite timeout.
     * <p>
     * By default it is 20000 (20 seconds).
     * </p>
     *
     * @return the timeout in milliseconds
     */
    public int getRequestReadTimeout()
    {
        String value = getProperty( CREDENTIAL_ATTR + prefix + PROPERTY_READ_TIMEOUT );
        if ( value == null )
        {
            value = DEFAULT_READ_TIMEOUT;
        }
        return Integer.valueOf( value );
    }
}
