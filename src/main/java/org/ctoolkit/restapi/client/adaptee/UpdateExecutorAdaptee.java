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
import java.util.Locale;
import java.util.Map;

/**
 * The adaptee interface to provide execution implementation of the PUT operation for concrete model type.
 *
 * @param <M> the concrete type of the input model object
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface UpdateExecutorAdaptee<M>
{
    /**
     * Prepare request instance as a proxy object to execute a remote call for UPDATE operation.
     *
     * @param resource   the resource instance
     * @param identifier the unique identifier of the resource
     * @param provider   the optional media to be uploaded
     * @return the new request instance
     * @throws IOException may be thrown during request initialization
     */
    Object prepareUpdate( @Nonnull M resource, @Nonnull Identifier identifier, @Nullable MediaProvider provider )
            throws IOException;

    /**
     * Provide execute implementation for the update operation.
     * The response type is either default one (same as the input model) or might be configured via
     * {@link org.ctoolkit.restapi.client.PayloadRequest#answerBy(Class)}. It's API specific and implementation must
     * reflect such expectation.
     *
     * @param request    the concrete request instance, see {@link #prepareUpdate(Object, Identifier, MediaProvider)}
     * @param parameters the optional map of parameters, configuration, and credential
     * @param locale     the optional language the client has configured to be associated with this resource
     * @return the response instance as result of the remote operation
     * @throws IOException might be thrown during remote call execution
     */
    Object executeUpdate( @Nonnull Object request, @Nullable Map<String, Object> parameters, @Nullable Locale locale )
            throws IOException;
}

