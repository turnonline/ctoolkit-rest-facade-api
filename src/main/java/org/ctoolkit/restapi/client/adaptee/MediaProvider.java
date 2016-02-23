package org.ctoolkit.restapi.client.adaptee;

/**
 * The media provider, a media to be uploaded by adaptee.
 *
 * @param <S> the concrete type of media content
 * @author <a href="mailto:aurel.medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public interface MediaProvider<S>
{
    /**
     * Provides an instance of media content. Must never return {@code null}.
     *
     * @return the instance of media content
     */
    S media();
}
