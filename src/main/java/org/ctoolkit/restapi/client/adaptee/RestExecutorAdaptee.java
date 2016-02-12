package org.ctoolkit.restapi.client.adaptee;

/**
 * The adaptee interface to provide execute implementation for all REST like operations on top of concrete type.
 *
 * @param <M> the concrete type of the model object to work with
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface RestExecutorAdaptee<M>
        extends NewExecutorAdaptee<M>, GetExecutorAdaptee<M>, ListExecutorAdaptee<M>,
        InsertExecutorAdaptee<M>, UpdateExecutorAdaptee<M>, PatchExecutorAdaptee<M>,
        DeleteExecutorAdaptee<M>
{
}

