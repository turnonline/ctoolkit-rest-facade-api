package org.ctoolkit.restapi.client.error;

/**
 * The server is currently unable to handle the request due to a temporary
 * overloading or maintenance of the server.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class ServiceUnavailableException
        extends HttpFailureException
{
    public ServiceUnavailableException()
    {
        super( 503 );
    }

    public ServiceUnavailableException( String message )
    {
        super( 503, message );
    }
}
