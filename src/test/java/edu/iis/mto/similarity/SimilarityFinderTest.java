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

        Assert.assertThat(1.0 ,is(equalTo(similarityFinder.calculateJackardSimilarity(seq1,seq2))) );
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

        Assert.assertThat(1d, is(equalTo(similarityFinder.calculateJackardSimilarity(seq1,seq2))));
    }

    @Test
    public void jackardSimilarityWithDiffSeqShouldReturnZero(){
        int[] seq1 = {1,2,3};
        int[] seq2 = {4,5,6};

        similarityFinder = new SimilarityFinder((key, seq) -> SearchResult.builder().build());

        Assert.assertThat(0.0, is(equalTo(similarityFinder.calculateJackardSimilarity(seq1,seq2))));
    }
}
