package edu.nus.cs3219.kwic.pipeline.impl;

import edu.nus.cs3219.kwic.pipeline.Pipe;

public class NonBlockingPipe<T> extends Pipe<T> {

    @Override
    public boolean hasMore() {
        return producer.hasMore();
    }

    @Override
    public void next() {
        if (hasMore()) {
            consumer.consume(producer.produce());
        }
    }
}
