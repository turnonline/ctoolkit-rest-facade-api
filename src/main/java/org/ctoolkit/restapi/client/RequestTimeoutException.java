package org.ctoolkit.restapi.client;

/**
 * The exception thrown once the client did not produce a request within the time that the server was prepared to wait.
 * The client MAY repeat the request without modifications at any later time.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class RequestTimeoutException
        extends HttpFailureException
{
    public RequestTimeoutException()
    {
        super( 408 );
    }

    public RequestTimeoutException( String message )
    {
        super( 408, message );
    }
}
