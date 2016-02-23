/*
 * Copyright (c) 2016 Comvai, s.r.o. All Rights Reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

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
