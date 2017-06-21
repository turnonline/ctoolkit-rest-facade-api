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
 * The extended {@link Request} to provide single retrieval specific API.
 *
 * @param <T> the single item resource type
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface RetrievalRequest<T>
        extends Request<T>
{
    /**
     * Returns the underlying request instance in order to configure an additional query parameters
     * specific to the underlying API implementation.
     *
     * @param type the underlying request type to cast to
     * @return the underlying request instance
     * @throws ClassCastException if wrong type has been requested
     */
    <U> U underlying( Class<U> type );
}
