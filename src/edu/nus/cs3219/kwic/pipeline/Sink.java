package edu.nus.cs3219.kwic.pipeline;

import java.util.Collection;
import java.util.Iterator;

public abstract class Sink<T> implements Iterable<T> {

    @Override
    public Iterator<T> iterator() {
        return collection.iterator();
    }

    private Collection<T> collection = null;
    private Pipe<T> incoming;
}
