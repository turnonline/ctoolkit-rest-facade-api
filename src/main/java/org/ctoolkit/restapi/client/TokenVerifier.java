package org.ctoolkit.restapi.client;

/**
 * The token verifier. In case that whatever goes wrong it throws {@link UnauthorizedException}.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface TokenVerifier<T>
{
    /**
     * Verifies the given token and for positive verification returns an identity instance.
     *
     * @param token the token to be verified
     * @return the verified and parsed identity instance
     */
    T verifyAndGet( String token ) throws UnauthorizedException;
}
