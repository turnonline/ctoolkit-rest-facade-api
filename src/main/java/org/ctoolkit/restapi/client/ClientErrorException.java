package org.ctoolkit.restapi.client;

/**
 * The client error, the request that could not be understood by the server. The client SHOULD NOT repeat the request
 * without modifications. Ideally all client error exceptions should be catched during development.
 * <p/>
 * Generally most of HTTP 4xx status codes are converted into this exception.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class ClientErrorException
        extends HttpFailureException
{
    public ClientErrorException( int statusCode )
    {
        super( statusCode );
    }

    public ClientErrorException( int statusCode, String message )
    {
        super( statusCode, message );
    }

    public ClientErrorException( int statusCode, Throwable cause )
    {
        super( statusCode, cause );
    }
}
