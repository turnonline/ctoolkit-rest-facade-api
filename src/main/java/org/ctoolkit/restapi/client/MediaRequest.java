package org.ctoolkit.restapi.client;

import java.io.File;
import java.io.InputStream;

/**
 * The interface to wrap a concrete resource and media content into single media request instance.
 *
 * @author <a href="mailto:aurel.medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public interface MediaRequest<T>
{
    /**
     * Creates a media request with media content to be uploaded.
     * It generates repeatable input streams based on the contents of a file.
     *
     * @param file the file to read content from
     * @param type the content type or {@code null} for none
     * @return the upload media request instance with associated resource and media content
     */
    UploadMediaRequest<T> upload( File file, String type );

    /**
     * Creates a media request with media content to be uploaded, content taken from given input stream.
     * This should only be used for streams that can not be re-opened and retried.
     *
     * @param stream the input stream to read content from
     * @param type   the content type or {@code null} for none
     * @return the upload media request instance with associated resource and media content
     */
    UploadMediaRequest<T> upload( InputStream stream, String type );

    /**
     * Creates a media request with media content to be uploaded.
     * It generates repeatable input streams based on the contents of byte array.
     *
     * @param array the byte array content
     * @param type  the content type or {@code null} for none
     * @return the upload media request instance with associated resource and media content
     */
    UploadMediaRequest<T> upload( byte[] array, String type );

    /**
     * Creates a media request with media content to be uploaded.
     * It generates repeatable input streams based on the contents of byte array.
     *
     * @param array  the byte array content
     * @param offset starting offset into the byte array
     * @param length of bytes to read from byte array
     * @param type   the content type or {@code null} for none
     * @return the upload media request instance with associated resource and media content
     */
    UploadMediaRequest<T> upload( byte[] array, int offset, int length, String type );

}
