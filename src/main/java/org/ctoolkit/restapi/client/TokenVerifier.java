package org.ctoolkit.restapi.client;

import java.security.SignatureException;

/**
 * The token verifier.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface TokenVerifier<T>
{
    /**
     * Verifies the given token and returns an identity instance for positive verification.
     *
     * @param token the token to be verified
     * @return the verified and parsed identity instance
     */
    T verifyAndGet( String token ) throws SignatureException;
}
