package edu.nus.cs3219.kwic.pipeline;

public interface Mapper<S, T> {
    T apply(S input);
}

