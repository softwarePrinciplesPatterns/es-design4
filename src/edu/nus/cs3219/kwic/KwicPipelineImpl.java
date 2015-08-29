package edu.nus.cs3219.kwic;

import edu.nus.cs3219.kwic.pipeline.Mapper;
import edu.nus.cs3219.kwic.pipeline.impl.NonBlockingPipeline;

import java.util.List;

/**
 * Implementation of Keyword In Context (KWIC) using Pipe and Filter
 *
 */
public class KwicPipelineImpl implements Kwic {
    public List<String> compute(List<String> sentences, List<String> ignoredWords) {
        Mapper<String, String> uppercase = new Mapper<String, String>() {
            @Override
            public String apply(String input) {
                return input.toUpperCase();
            }
        };
        NonBlockingPipeline<String, Object> kwicPipeline =
                NonBlockingPipeline.fromIterable(sentences).pipe(uppercase);
        kwicPipeline.run();
    }

}
