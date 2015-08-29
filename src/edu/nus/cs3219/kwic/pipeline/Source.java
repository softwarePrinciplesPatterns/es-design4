package edu.nus.cs3219.kwic.pipeline;

public abstract class Source<T> implements Producer<T> {
    public Source(Pipe<T> outgoing) {
        this.outgoing = outgoing;
    }

    private Pipe<T> outgoing;
}
