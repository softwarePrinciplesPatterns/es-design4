package edu.nus.cs3219.kwic;

import java.util.List;

public interface Kwic {
    List<String> compute(List<String> sentences, List<String> ignoredWords);
}
