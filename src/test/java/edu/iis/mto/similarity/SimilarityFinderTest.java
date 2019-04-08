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

}
