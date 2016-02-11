package org.ctoolkit.restapi.client;

/**
 * The unauthorized exception thrown once the request requires user authentication.
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

    public UnauthorizedException( Throwable cause )
    {
        super( 401, cause );
    }
}
