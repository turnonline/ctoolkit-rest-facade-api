package org.ctoolkit.restapi.client.error;

/**
 * The request could not be completed due to a conflict with the current state of the resource.
 * This code is only allowed in situations where it is expected that the user might be able
 * to resolve the conflict and resubmit the request. The response body SHOULD include enough
 * information for the user to recognize the source of the conflict.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class ConflictException
        extends HttpFailureException
{
    public ConflictException()
    {
        super( 409 );
    }

    public ConflictException( String message )
    {
        super( 409, message );
    }
}
