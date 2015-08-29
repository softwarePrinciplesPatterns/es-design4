package edu.nus.cs3219.kwic.pipeline;

public interface Producer<T> {
     boolean hasMore();
     T produce();
}
