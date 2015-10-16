package org.ctoolkit.restapi.client.error;

/**
 * The server encountered an unexpected condition which prevented it from fulfilling the request.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class InternalServerErrorException
        extends HttpFailureException
{
    public InternalServerErrorException()
    {
        super( 500 );
    }

    public InternalServerErrorException( String message )
    {
        super( 500, message );
    }
}
