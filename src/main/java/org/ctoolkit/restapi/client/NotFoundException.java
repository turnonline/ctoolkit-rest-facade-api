package org.ctoolkit.restapi.client;

/**
 * The exception thrown once the server has not found anything matching the Request-URI.
 * Note, the retrieval operations (get, list) will return <tt>null</tt> instead of NotFoundException.
 *
 * @author <a href="mailto:aurel.medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class NotFoundException
        extends HttpFailureException
{
    public NotFoundException()
    {
        super( 404 );
    }

    public NotFoundException( String message )
    {
        super( 404, message );
    }
}
