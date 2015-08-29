package edu.nus.cs3219.kwic.pipeline;

public interface Consumer<T> {
    void consume(final T data);
}
