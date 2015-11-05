package org.dfernandez.algorithm;


import org.dfernandez.exception.InternalException;

/**
 * The <code>Search</code> interface should be implemented by any
 * class whose instances are intended to executed a search algorithm.
 */
public interface Search {

    /**
     * Search algorithm
     *
     * @return <code>true</code>  get out of a dark maze; <code>false</code> otherwise
     * @throws InternalException
     */
    public abstract boolean search() throws InternalException;


}