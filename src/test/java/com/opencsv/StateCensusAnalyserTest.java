package com.opencsv;

import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyserTest {

    private static final String INDIAN_STATES_INFORMATION_FILE = "/home/admin142/Desktop/IndianStateCensusProblem/StateCode (1).csv";

    @Test
    public void givenIndianStateInformation_whenProperAnalyse_shouldMatchRecords() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        int numberOfRecord = stateCensusAnalyser.findNumberOfRecord(INDIAN_STATES_INFORMATION_FILE);
        Assert.assertEquals(37,numberOfRecord);
    }
}
