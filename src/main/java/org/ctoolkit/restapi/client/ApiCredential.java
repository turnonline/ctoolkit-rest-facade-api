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
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * The API credential holder represents a map of credential used to authenticate client calls to APIs.
 * Default credential will be always used if any specific wouldn't be found.
 * Specific credential that are dedicated to a concrete API are optional unless default credential are defined.
 * <p>
 * Values are part of the binding thus available via injection like:
 * <pre>
 *   public class MyClass {
 *     &#064;Inject <b>@Named("credential.default.clientId")</b> String clientId;
 *     ...
 *   }</pre>
 * <b>Credential loaded via properties file</b>
 * <pre>
 * {@code
 *  ApiCredential credential = new ApiCredential();
 *  credential.load( "/identity.properties" );
 *  Names.bindProperties( binder(), credential );
 * }
 * </pre>
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class ApiCredential
        extends Properties
{
    public static final String DEFAULT_APP_NAME = "ctoolkit:facade-api";

    public static final String DEFAULT_NUMBER_OF_RETRIES = "1";

    public static final String DEFAULT_READ_TIMEOUT = "20000";

    public static final String DEFAULT_CREDENTIAL_PREFIX = "default";

    public static final String PROPERTY_PROJECT_ID = "projectId";

    public static final String PROPERTY_CLIENT_ID = "clientId";

    public static final String PROPERTY_CLIENT_SECRET = "clientSecret";

    public static final String PROPERTY_SCOPES = "scopes";

    public static final String PROPERTY_DISABLE_GZIP_CONTENT = "disableGZipContent";

    public static final String PROPERTY_SERVICE_ACCOUNT_EMAIL = "serviceAccountEmail";

    public static final String PROPERTY_APPLICATION_NAME = "appName";

    public static final String PROPERTY_FILE_NAME = "fileName";

    public static final String PROPERTY_FILE_NAME_JSON = "fileNameJson";

    public static final String PROPERTY_API_KEY = "apiKey";

    public static final String PROPERTY_ENDPOINT_URL = "endpointUrl";

    public static final String PROPERTY_CREDENTIAL_ON = "on";

    public static final String PROPERTY_NUMBER_OF_RETRIES = "numberOfRetries";

    public static final String PROPERTY_READ_TIMEOUT = "readTimeout";

    public static final String CREDENTIAL_ATTR = "credential.";

    private static final long serialVersionUID = -2258904700906913513L;

    protected final String prefix;

    /**
     * Creates default credential used to authenticate all calls unless specified another instance for a specific API.
     * Setting following properties will be configured as default credential.
     * <ul>
     * <li>{@link #setProjectId(String)}</li>
     * <li>{@link #setClientId(String)}</li>
     * <li>{@link #setClientSecret(String)}</li>
     * <li>{@link #setScopes(String)} (String)}</li>
     * <li>{@link #setDisableGZipContent(boolean)}</li>
     * <li>{@link #setServiceAccountEmail(String)}</li>
     * <li>{@link #setApplicationName(String)}</li>
     * <li>{@link #setFileName(String)}</li>
     * <li>{@link #setFileNameJson(String)} (String)}</li>
     * <li>{@link #setApiKey(String)}</li>
     * <li>{@link #setEndpointUrl(String)}</li>
     * <li>{@link #setCredentialOn(boolean)}</li>
     * <li>{@link #setNumberOfRetries(int)}</li>
     * <li>{@link #setRequestReadTimeout(int)}</li>
     * </ul>
     */
    public ApiCredential()
    {
        this( DEFAULT_CREDENTIAL_PREFIX );
    }

    /**
     * Creates specific credential used to authenticate calls to a specific API.
     * Setting following properties will be configured as specific credential - prefix based.
     * <ul>
     * <li>{@link #setProjectId(String)}</li>
     * <li>{@link #setClientId(String)}</li>
     * <li>{@link #setClientSecret(String)}</li>
     * <li>{@link #setScopes(String)} (String)}</li>
     * <li>{@link #setDisableGZipContent(boolean)}</li>
     * <li>{@link #setServiceAccountEmail(String)}</li>
     * <li>{@link #setApplicationName(String)}</li>
     * <li>{@link #setFileName(String)}</li>
     * <li>{@link #setFileNameJson(String)} (String)}</li>
     * <li>{@link #setApiKey(String)}</li>
     * <li>{@link #setEndpointUrl(String)}</li>
     * <li>{@link #setCredentialOn(boolean)}</li>
     * <li>{@link #setNumberOfRetries(int)}</li>
     * <li>{@link #setRequestReadTimeout(int)}</li>
     * </ul>
     *
     * @param prefix the prefix used to identify specific credential
     */
    public ApiCredential( String prefix )
    {
        if ( isNullOrEmpty( prefix ) )
        {
            throw new IllegalArgumentException( String.valueOf( "Prefix cannot be null or empty!" ) );
        }
        this.prefix = prefix + ".";
        setApplicationName( null );
    }

    /**
     * Creates credential map used to authenticate API calls.
     * It's client responsibility to make sure that map has at least a default credential configured.
     * <ul>
     * <li>credential.default.projectId</li>
     * <li>credential.default.clientId</li>
     * <li>credential.default.clientSecret</li>
     * <li>credential.default.scopes</li>
     * <li>credential.default.disableGZipContent</li>
     * <li>credential.default.serviceAccountEmail</li>
     * <li>credential.default.appName</li>
     * <li>credential.default.fileName</li>
     * <li>credential.default.fileNameJson</li>
     * <li>credential.default.apiKey</li>
     * <li>credential.default.endpointUrl</li>
     * <li>credential.default.on</li>
     * <li>credential.default.numberOfRetries</li>
     * <li>credential.default.readTimeout</li>
     * <li>credential.drive.projectId</li>
     * <li>credential.drive.clientId</li>
     * <li>..</li>
     * </ul>
     *
     * @param properties the map of credential and config properties
     * @param prefix     the base prefix of this credential instance
     */
    protected ApiCredential( @Nonnull Map<String, String> properties, String prefix )
    {
        this( prefix );
        for ( String next : properties.keySet() )
        {
            setProperty( next, properties.get( next ) );
        }
    }

    /**
     * Reads a property list (key and element pairs) from the properties file
     *
     * @param path the path of the desired properties file to be loaded
     * @throws RuntimeException         if an error occurred while reading from the properties file.
     * @throws IllegalArgumentException if the properties file has not been found at specified path.
     */
    public synchronized void load( String path )
    {
        if ( path == null )
        {
            throw new IllegalArgumentException( "The path to the properties file cannot be null." );
        }

        InputStream stream = ApiCredential.class.getResourceAsStream( path );
        if ( stream == null )
        {
            String msg = "The properties file at '" + path + "' has not been found.";
            throw new IllegalArgumentException( msg );
        }

        try
        {
            this.load( stream );
        }
        catch ( IOException e )
        {
            throw new RuntimeException( "Reading from the properties file '" + path + "' has failed.", e );
        }
    }

    /**
     * Sets the Google Cloud Project ID also known as applicationId (AppId).
     *
     * @param projectId the application ID
     * @return this instance to chain
     */
    public ApiCredential setProjectId( String projectId )
    {
        if ( !isNullOrEmpty( projectId ) )
        {
            setProperty( CREDENTIAL_ATTR + prefix + PROPERTY_PROJECT_ID, projectId );
        }
        return this;
    }

    /**
     * Sets the API OAuth 2.0 Client ID Credential.
     *
     * @param clientId the Client ID
     * @return this instance to chain
     */
    public ApiCredential setClientId( String clientId )
    {
        if ( !isNullOrEmpty( clientId ) )
        {
            setProperty( CREDENTIAL_ATTR + prefix + PROPERTY_CLIENT_ID, clientId );
        }
        return this;
    }

    /**
     * Sets the API client secret (signature).
     *
     * @param clientSecret the client secret
     * @return this instance to chain
     */
    public ApiCredential setClientSecret( String clientSecret )
    {
        if ( !isNullOrEmpty( clientSecret ) )
        {
            setProperty( CREDENTIAL_ATTR + prefix + PROPERTY_CLIENT_SECRET, clientSecret );
        }
        return this;
    }

    /**
     * Sets the API scopes, a comma separated values.
     *
     * @param scopes the API scopes
     * @return this instance to chain
     */
    public ApiCredential setScopes( String scopes )
    {
        if ( !isNullOrEmpty( scopes ) )
        {
            setProperty( CREDENTIAL_ATTR + prefix + PROPERTY_SCOPES, scopes );
        }
        return this;
    }

    /**
     * Sets whether to disable GZip compression of HTTP content.
     * <p>
     * By default it's {@code false} if not set.
     * </p>
     *
     * @param disable true to disable GZip compression. Otherwise HTTP content will be compressed.
     * @return this instance to chain
     */
    public ApiCredential setDisableGZipContent( boolean disable )
    {
        String valueOf = String.valueOf( disable );
        setProperty( CREDENTIAL_ATTR + prefix + PROPERTY_DISABLE_GZIP_CONTENT, valueOf );
        return this;
    }

    /**
     * Sets the service account ID (typically an e-mail address).
     *
     * @param serviceEmail the service email
     * @return this instance to chain
     */
    public ApiCredential setServiceAccountEmail( String serviceEmail )
    {
        if ( !isNullOrEmpty( serviceEmail ) )
        {
            setProperty( CREDENTIAL_ATTR + prefix + PROPERTY_SERVICE_ACCOUNT_EMAIL, serviceEmail );
        }
        return this;
    }

    /**
     * Sets the name of the client application. If the application name is {@code null} or blank,
     * the application will use default name {@link #DEFAULT_APP_NAME}.
     *
     * @param applicationName the application name to be used as caller name
     * @return this instance to chain
     */
    public ApiCredential setApplicationName( String applicationName )
    {
        if ( !isNullOrEmpty( applicationName ) )
        {
            setProperty( CREDENTIAL_ATTR + prefix + PROPERTY_APPLICATION_NAME, applicationName );
        }
        else
        {
            setProperty( CREDENTIAL_ATTR + prefix + PROPERTY_APPLICATION_NAME, DEFAULT_APP_NAME );
        }
        return this;
    }

    /**
     * Sets the path name to the private key file.
     * <p>
     * Use package relative path for example '/biz/turnonline/server/impl/PrivateKeyFile.p12'
     * or locate the private key file in resource package org.ctoolkit.restapi.client.googleapis
     * directory - 'PrivateKeyFile.p12'.
     *
     * @param fileName the relative path to file
     * @return this instance to chain
     */
    public ApiCredential setFileName( String fileName )
    {
        if ( !isNullOrEmpty( fileName ) )
        {
            setProperty( CREDENTIAL_ATTR + prefix + PROPERTY_FILE_NAME, fileName );
        }
        return this;
    }

    /**
     * Sets the path name to the json stream file which contains private key, service account email, client id and others.
     * <p>
     * Use package relative path for example '/biz/turnonline/server/impl/secret.json'
     * or locate the json stream file in resource package org.ctoolkit.restapi.client.googleapis
     * directory - 'secret.json'.
     *
     * @param fileName the relative path to file
     * @return this instance to chain
     */
    public ApiCredential setFileNameJson( String fileName )
    {
        if ( !isNullOrEmpty( fileName ) )
        {
            setProperty( CREDENTIAL_ATTR + prefix + PROPERTY_FILE_NAME_JSON, fileName );
        }
        return this;
    }

    /**
     * Sets the API Key to be used with any API that supports it.
     *
     * @param apiKey the API Key to be set
     * @return this instance to chain
     */
    public ApiCredential setApiKey( String apiKey )
    {
        if ( !isNullOrEmpty( apiKey ) )
        {
            setProperty( CREDENTIAL_ATTR + prefix + PROPERTY_API_KEY, apiKey );
        }
        return this;
    }

    /**
     * Sets the backend service API endpoint URL to be called.
     *
     * @param endpointUrl the endpoint URL to be set
     * @return this instance to chain
     */
    public ApiCredential setEndpointUrl( String endpointUrl )
    {
        if ( !isNullOrEmpty( endpointUrl ) )
        {
            setProperty( CREDENTIAL_ATTR + prefix + PROPERTY_ENDPOINT_URL, endpointUrl );
        }
        return this;
    }

    /**
     * Sets the boolean identification whether current environment should use these credential
     * in order to authenticate client calls or use cloud native environment for authentication.
     *
     * @param credentialOn true use these credential in order to authenticate calls
     * @return this instance to chain
     */
    public ApiCredential setCredentialOn( boolean credentialOn )
    {
        String valueOf = String.valueOf( credentialOn );
        setProperty( CREDENTIAL_ATTR + prefix + PROPERTY_CREDENTIAL_ON, valueOf );
        return this;
    }

    /**
     * Sets the number of retries that will be allowed to execute before the request will be
     * terminated or {@code 0} to not retry requests.
     * <p>
     * The default value is {@code 1}.
     * </p>
     *
     * @param numberOfRetries the number of retries
     * @return this instance to chain
     */
    public ApiCredential setNumberOfRetries( int numberOfRetries )
    {
        if ( numberOfRetries < 0 )
        {
            numberOfRetries = 1;
        }
        setProperty( CREDENTIAL_ATTR + prefix + PROPERTY_NUMBER_OF_RETRIES, String.valueOf( numberOfRetries ) );
        return this;
    }

    /**
     * Sets the timeout in milliseconds to read data from an established connection or {@code 0} for
     * an infinite timeout.
     * <p>
     * By default it is 20000 (20 seconds).
     * </p>
     *
     * @param readTimeout the request read timeout to be set
     * @return this instance to chain
     */
    public ApiCredential setRequestReadTimeout( int readTimeout )
    {
        if ( readTimeout < 0 )
        {
            readTimeout = 0;
        }
        setProperty( CREDENTIAL_ATTR + prefix + PROPERTY_READ_TIMEOUT, String.valueOf( readTimeout ) );
        return this;
    }

    /**
     * Searches for the property with the specified key in this property list.
     *
     * @param property the the property key
     * @return the property string value
     */
    protected final String getStringValue( String property )
    {
        if ( isNullOrEmpty( property ) )
        {
            throw new IllegalArgumentException( "The property is a mandatory input!" );
        }

        return getProperty( CREDENTIAL_ATTR + prefix + property );
    }

    private boolean isNullOrEmpty( String string )
    {
        return string == null || string.length() == 0;
    }
}
