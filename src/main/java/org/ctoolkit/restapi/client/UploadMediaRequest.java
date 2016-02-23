package org.ctoolkit.restapi.client;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * The upload media request as wrapper of media content and associated resource.
 * Gives a possibility to this resource to be either inserted or updated.
 *
 * @author <a href="mailto:aurel.medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public interface UploadMediaRequest<T>
{
    /**
     * Same as {@link ResourceFacade#insert(Object)}.
     *
     * @return the newly inserted instance of associated resource
     * @throws NotFoundException if not matching request URI has found
     */
    SingleRequest<T> insert();

    /**
     * Same as {@link ResourceFacade#insert(Object, Identifier)}.
     *
     * @param parent the resource parent identifier
     * @return the newly inserted instance of associated resource
     * @throws NotFoundException if not matching request URI has found
     */
    SingleRequest<T> insert( @Nullable Identifier parent );

    /**
     * Same as {@link ResourceFacade#update(Object, Identifier)}.
     *
     * @param identifier the unique identifier of associated resource to update
     * @return the updated instance of associated resource
     * @throws NotFoundException if not matching request URI has found
     */
    SingleRequest<T> update( @Nonnull Identifier identifier );
}
