package edu.nus.cs3219.kwic.pipeline.impl;

import edu.nus.cs3219.kwic.pipeline.*;

import java.util.Iterator;

public class NonBlockingPipeline<S> {

    private Producer<S> current;

    private NonBlockingPipeline(Producer<S> source) {
        current = source;
    }

    public static <S> NonBlockingPipeline<S> fromIterable(final Iterable<S> iterable) {
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

    public <I> NonBlockingPipeline<I> pipe(final Mapper<S, I> mapper) {
        Pipe<S> incoming = new NonBlockingPipe<>();
        incoming.setProducer(current);
        Pipe<I> outgoing = new NonBlockingPipe<>();
        Producer<I> filter = new Filter<S, I>(incoming, outgoing) {
            @Override
            public Mapper<S, I> getMapper() {
                return mapper;
            }
        };
        return new NonBlockingPipeline(filter);
    }

    public void flush(final Sink<S> sink) {
        while (current.hasMore()) {
            sink.consume(current.produce());
        }
    }
}
