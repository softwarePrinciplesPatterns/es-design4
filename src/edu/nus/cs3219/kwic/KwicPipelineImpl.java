package edu.nus.cs3219.kwic;

import edu.nus.cs3219.kwic.pipeline.Mapper;
import edu.nus.cs3219.kwic.pipeline.Sink;
import edu.nus.cs3219.kwic.pipeline.impl.NonBlockingPipeline;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Keyword In Context (KWIC) using Pipe and Filter
 *
 */
public class KwicPipelineImpl implements Kwic {
    public List<String> compute(List<String> sentences, List<String> ignoredWords) {
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        List<String> sentences = new ArrayList<String>() {{
            add("Hello World");
            add("Test foo");
        }};
        Mapper<String, String> uppercase = new Mapper<String, String>() {
            @Override
            public String apply(String input) {
                return input.toUpperCase();
            }
        };
        Sink<String> stringPinter = new Sink<String>() {
            @Override
            public void consume(String data) {
                System.out.println(data);
            }
        };
        NonBlockingPipeline.fromIterable(sentences).pipe(uppercase).flush(stringPinter);
    }

}
