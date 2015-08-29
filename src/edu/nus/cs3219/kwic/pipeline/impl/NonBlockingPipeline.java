package edu.nus.cs3219.kwic.pipeline.impl;

import edu.nus.cs3219.kwic.pipeline.*;

import java.util.Iterator;

public class NonBlockingPipeline<S, T> {

    private Producer<?> current;

    private NonBlockingPipeline(Source<S> source) {
        current = source;
    }

    public static <S, T> NonBlockingPipeline<S, T> fromIterable(final Iterable<S> iterable) {
        Pipe<S> outgoing = new NonBlockingPipe<>();
        Source<S> source = new Source<S>(outgoing) {
            private Iterator<S> iterator = iterable.iterator();
            @Override
            public boolean hasMore() {
                return iterator.hasNext();
            }

            @Override
            public S produce() {
                return iterator.next();
            }
        };
        return new NonBlockingPipeline<>(source);
    }

    public <I> NonBlockingPipeline<S, T> pipe(Mapper<S, I> mapper) {
        Pipe<S> incoming = new NonBlockingPipe<>();
        Pipe<I> outgoing = new NonBlockingPipe<>();
        Producer<I> filter = new Filter<S, I>(incoming, outgoing) {
            @Override
            public Mapper<S, I> getMapper() {
                return null;
            }
        };
        current = filter;
        return this;
    }

    public void run() {
        while(current.hasMore()) {
            current.produce();
        }
    }
}
