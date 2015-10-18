package org.ctoolkit.restapi.client;

/**
 * The remote server unexpected error.
 * <p/>
 * Generally all HTTP 5xx status codes are converted into this exception.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class RemoteServerErrorException
        extends HttpFailureException
{
    public RemoteServerErrorException( int statusCode )
    {
        super( statusCode );
    }

    public RemoteServerErrorException( int statusCode, String message )
    {
        super( statusCode, message );
    }
}
