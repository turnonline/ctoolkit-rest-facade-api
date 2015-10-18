package org.ctoolkit.restapi.client;

/**
 * The base class as runtime exception for all HTTP status codes that represents a failure.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class HttpFailureException
        extends RuntimeException
{
    protected final int statusCode;

    public HttpFailureException( int statusCode )
    {
        this.statusCode = statusCode;
    }

    public HttpFailureException( int statusCode, String message )
    {
        super( message );
        this.statusCode = statusCode;
    }

    public int getStatusCode()
    {
        return statusCode;
    }
}
