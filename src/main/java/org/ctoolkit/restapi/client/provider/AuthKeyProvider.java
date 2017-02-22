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

package org.ctoolkit.restapi.client.provider;

import java.io.InputStream;

/**
 * The provider to provide implementation specific mechanism to retrieve authentication key for API authentication
 * (from file, encrypted datastore etc).
 * <p>
 * The implementation is optional and once configured will take a precedence.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface AuthKeyProvider
{
    /**
     * Provides an instance of the authentication key as a stream.
     *
     * @return the authentication key as a stream
     * @throws RuntimeException if the implementation encounters an error while providing an instance.
     */
    InputStream get();
}
