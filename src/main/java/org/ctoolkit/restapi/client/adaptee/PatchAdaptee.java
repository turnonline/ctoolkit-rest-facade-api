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

package org.ctoolkit.restapi.client.adaptee;

import org.ctoolkit.restapi.client.Identifier;

import java.io.IOException;

/**
 * The adaptee interface to provide implementation for REST PATCH operation.
 *
 * @param <R> the concrete type of the underlying request instance
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface PatchAdaptee<R>
{
    /**
     * Prepare request instance as a proxy object to execute a remote call for PATCH operation.
     *
     * @param resource   the resource instance
     * @param identifier the unique identifier of the resource
     * @return the new request instance
     * @throws IOException may be thrown during request initialization
     */
    R preparePatch( Object resource, Identifier identifier )
            throws IOException;
}
