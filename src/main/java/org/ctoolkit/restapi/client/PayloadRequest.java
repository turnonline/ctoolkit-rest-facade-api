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

package org.ctoolkit.restapi.client;

/**
 * The extended {@link Request} to provide specific API for operation with requests having payload.
 *
 * @param <T> the resource type
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface PayloadRequest<T>
        extends Request<T>
{
    /**
     * The type of the response expected as a result of the remote call execution.
     *
     * @param type the response type different from the default one
     * @param <R>  the class type of underlying request to be cast
     * @return the request to chain calls with new response type defined
     */
    <R> Request<R> response( Class<R> type );
}
