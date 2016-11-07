package com.jeansandtshirt.validitychecker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by samir on 2016-11-07.
 */
public class ValidityChecksTest {

    ValidityChecks validityChecks = new ValidityChecks();

    @Test
    public void testCorrectPNum() throws Exception {
        List<Integer> pNumberList = new ArrayList<Integer>(Arrays.asList(7,8,0,2,0,2,2,3,8,9));

        assertEquals(true, validityChecks.correctPNum(pNumberList));
    }

    @Test
    public void testIsNull() throws Exception {
        assertEquals(true,validityChecks.isNull(new CandidateData(null, null, null)));
    }
}