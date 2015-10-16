package org.ctoolkit.restapi.client.error;

/**
 * The server understood the request, but is refusing to fulfill it.
 * Authorization will not help and the request SHOULD NOT be repeated.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class ForbiddenException
        extends HttpFailureException
{
    public ForbiddenException()
    {
        super( 403 );
    }

    public ForbiddenException( String message )
    {
        super( 403, message );
    }
}
