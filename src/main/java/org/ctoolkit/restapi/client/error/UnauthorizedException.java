package org.ctoolkit.restapi.client.error;

/**
 * The request requires user authentication.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class UnauthorizedException
        extends HttpFailureException
{
    public UnauthorizedException()
    {
        super( 401 );
    }

    public UnauthorizedException( String message )
    {
        super( 401, message );
    }
}
