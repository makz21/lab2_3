package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

public class SequenceSearcherMock implements SequenceSearcher {

    @Override public SearchResult search(int key, int[] seq) {
        if (seq == null || seq.length < 1) {
            throw new IllegalArgumentException("Sequence cannot be epmty");
        }

        for (int i = 0; i < seq.length; i++) {
            if (key == seq[i]) {
                return SearchResult.builder().withFound(true).withPosition(i).build();
            }
        }
        return SearchResult.builder().withFound(false).build();
    }
}

