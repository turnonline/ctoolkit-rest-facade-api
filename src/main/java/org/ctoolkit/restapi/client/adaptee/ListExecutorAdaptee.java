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

import org.ctoolkit.restapi.client.Identifier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * The adaptee interface to provide execution implementation of the GET operation to retrieve list of resources
 * of concrete model type.
 *
 * @param <M> the concrete type of the model object to be retrieved
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface ListExecutorAdaptee<M>
{
    /**
     * Prepare request instance as a proxy object to execute a remote call for LIST operation.
     *
     * @param parentKey the unique (root) identifier of the parent resource as an owner of this resource if any
     * @return the new request instance
     * @throws IOException may be thrown during request initialization
     */
    Object prepareList( @Nullable Identifier parentKey )
            throws IOException;

    /**
     * Provide execute implementation of the list operation.
     *
     * @param request    the concrete request instance, see {@link #prepareList(Identifier)}
     * @param parameters the optional map of parameters, configuration, and credential
     * @param locale     the optional language the client has configured to prefer in results if applicable
     * @param offset     the optional position (offset) of the first result, numbered from 0.
     * @param limit      the optional maximum number of results (limit) to retrieve. Only positive values.
     * @param orderBy    the resource property name used to sort the result if any
     * @param ascending  true to sort the result ascending
     * @return the list of resource instance matching filtering criteria, otherwise returns empty list
     * @throws IOException might be thrown during remote call execution
     */
    List<M> executeList( @Nonnull Object request,
                         @Nullable Map<String, Object> parameters,
                         @Nullable Locale locale,
                         @Nullable Integer offset,
                         @Nullable Integer limit,
                         @Nullable String orderBy,
                         @Nullable Boolean ascending )
            throws IOException;
}

