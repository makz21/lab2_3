package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SimilarityFinderTest {
    private SimilarityFinder similarityFinder;

    @Test
    public void jackardSimilarityWithEmptySeq(){
        int[] seq1 = {};
        int[] seq2 = {};
        similarityFinder = new SimilarityFinder(((key, seq) -> SearchResult.builder().build()));

        Assert.assertThat(similarityFinder.calculateJackardSimilarity(seq1,seq2), is(equalTo(1.0)) );
    }

    @Test
    public void jackardSimilarityWithTheSameSeqShouldReturnOne(){
        int[] seq1 = {1,2,3};
        int[] seq2 = {1,2,3};

        similarityFinder = new SimilarityFinder((key, seq) -> {
            if (key == seq[0] || key == seq[1] || key == seq[2])
                return SearchResult.builder().withFound(true).build();
            return SearchResult.builder().withFound(false).build();
        });

        Assert.assertThat(similarityFinder.calculateJackardSimilarity(seq1,seq2), is(equalTo(1d)));
    }

    @Test
    public void jackardSimilarityWithDiffSeqShouldReturnZero(){
        int[] seq1 = {1,2,3};
        int[] seq2 = {4,5,6};

        similarityFinder = new SimilarityFinder((key, seq) -> SearchResult.builder().build());

        Assert.assertThat(similarityFinder.calculateJackardSimilarity(seq1,seq2), is(equalTo(0.0)));
    }

    @Test
    public void jackardSimilaritySeqDiffSizesWithHalfCommonElement() {
        int[] seq1 = {1,2,3};
        int[] seq2 = {1,2,3,77,88,99};

        similarityFinder = new SimilarityFinder((key, seq) -> {
            if(key == seq[0] || key == seq[1] || key == seq[2])
                return SearchResult.builder().withFound(true).build();

            return SearchResult.builder().build();
        });

        Assert.assertThat(similarityFinder.calculateJackardSimilarity(seq1,seq2), is(equalTo(0.5 )));
    }
    @Test
    public void jackardSimilarityCountAllCallsOfSearchMethod() {
        SequenceSearcherDubler searcherDubler = new SequenceSearcherDubler();

        int[] seq1 = {1, 2, 3, 4, 5};
        int[] seq2 = {11, 22, 33, 44, 55};

        similarityFinder = new SimilarityFinder(searcherDubler);
        similarityFinder.calculateJackardSimilarity(seq1, seq2);
        Assert.assertThat(seq1.length, is(equalTo(searcherDubler.getCounter())));
    }

    class SequenceSearcherDubler implements edu.iis.mto.search.SequenceSearcher {
        private int counter;

        @Override
        public SearchResult search(int key, int[] seq) {
            counter++;
            return SearchResult.builder().withFound(false).build();
        }

        int getCounter() {
            return counter;
        }
    }
}
