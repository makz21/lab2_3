package edu.iis.mto.similarity;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimilarityFinderTest {

    private SequenceSearcherMock sequenceSearcherMock = new SequenceSearcherMock();


    @Test
    public void calculateJackardSimilaritySameSeqShoultReturnOne() {
        int[] seq1 = {1,2,3,4};
        int[] seq2 = {1,2,3,4};
        SimilarityFinder similarityFinder = new SimilarityFinder(new SequenceSearcherMock());
        double similarity = similarityFinder.calculateJackardSimilarity(seq1,seq2);
        assertThat(similarity, is(1.0));
    }
}
