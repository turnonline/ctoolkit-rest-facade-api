package org.ctoolkit.restapi.client.error;

/**
 * The base class for all HTTP status codes that represents a failure.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public abstract class HttpFailureException
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
