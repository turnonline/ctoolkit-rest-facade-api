package org.ctoolkit.restapi.client.adaptee;

/**
 * The adaptee interface to provide execute implementation for all REST like operations on top of concrete type.
 *
 * @param <M> the concrete type of the model object to work with
 * @param <R> the concrete type of the request
 * @param <K> the concrete type of the resource identifier
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface RestExecutorAdaptee<M, R, K>
        extends NewExecutorAdaptee<M, R>, GetExecutorAdaptee<M, R, K>, ListExecutorAdaptee<M, R, K>,
        InsertExecutorAdaptee<M, R, K>, UpdateExecutorAdaptee<M, R, K>, PatchExecutorAdaptee<M, R, K>,
        DeleteExecutorAdaptee<R, K>
{
}

