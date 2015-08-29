package edu.nus.cs3219.kwic.pipeline;

public abstract class Pipe<T> {
    public void setProducer(Producer<T> producer) {
        this.producer = producer;
    }

    public void setConsumer(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    public abstract boolean hasMore();
    public abstract void next();

    protected Producer<T> producer = null;
    protected Consumer<T> consumer = null;
}
