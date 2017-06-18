/*
 * Copyright (c) 2017 Comvai, s.r.o. All Rights Reserved.
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

package org.ctoolkit.restapi.client.adaptee;

import javax.annotation.Nullable;

/**
 * The media provider of the content to be uploaded by adaptee.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface MediaProvider
{
    /**
     * Provides an instance of media content. Must never return {@code null}.
     *
     * @return the instance of media content
     */
    Object getMedia();

    /**
     * Sets the content type or {@code null} for none.
     *
     * @param type the content type
     */
    void setType( @Nullable String type );

    /**
     * Sets whether the target input stream should be closed at the end of 'write to' operation.
     * The default value is {@code true}.
     *
     * @param closeInputStream the boolean value to be set
     */
    void setCloseInputStream( boolean closeInputStream );
}
