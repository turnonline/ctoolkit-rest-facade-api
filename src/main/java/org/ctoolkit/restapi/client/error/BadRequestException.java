package org.ctoolkit.restapi.client.error;

/**
 * The request could not be understood by the server due to malformed syntax.
 * The client SHOULD NOT repeat the request without modifications.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class BadRequestException
        extends HttpFailureException
{
    public BadRequestException()
    {
        super( 400 );
    }

    public BadRequestException( String message )
    {
        super( 400, message );
    }
}
