package com.opencsv;

import com.sun.tools.jdeprscan.CSV;
import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyserTest {

    private static final String INDIAN_STATES_INFORMATION_FILE = "/home/admin142/Desktop/IndianStateCensusProblem/StateCode (1).csv";
    private static final Object INDIAN_STATES_INFORMATION_FILE1 = "/home/admin142/Desktop/IndianStateCensusProblem/StateCode (2).csv" ;

    private static final String INDIAN_STATES_CENSUS_INFORMATION_FILE = "/home/admin142/Desktop/IndianStateCensusProblem/StateCensusData.csv";
    private static final String INDIAN_STATES_CENSUS_INFORMATION_FILE2 = "/home/admin142/Desktop/IndianStateCensusProblem/StateCensusData2.csv";


    @Test
    public void givenIndianStateInformation_whenProperAnalyse_shouldMatchRecords() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        int numberOfRecord = 0;
        try {
            numberOfRecord = stateCensusAnalyser.findNumberOfRecord(INDIAN_STATES_INFORMATION_FILE);
        } catch (CSVStateException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(37,numberOfRecord);
    }

    @Test
    public void givenIndianStateInformation_whenImproperFile_shouldHandleException()  {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        int numberOfRecord = 0;
        try {
            numberOfRecord = stateCensusAnalyser.findNumberOfRecord((String) INDIAN_STATES_INFORMATION_FILE1);
            Assert.assertEquals(37,numberOfRecord);
        } catch (CSVStateException e) {
            Assert.assertEquals(CSVStateException.ExceptionType.NO_SUCH_FILE  ,e.type);
        }
    }

    @Test
    public void givenIndianStateInformation_whenNotSupportedFileType_shouldHandleException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            int numberOfRecord = stateCensusAnalyser.findNumberOfRecord(INDIAN_STATES_INFORMATION_FILE);
            Assert.assertEquals(37,numberOfRecord);
        } catch (CSVStateException e) {
            Assert.assertEquals(CSVStateException.ExceptionType.NO_SUCH_FILE ,e.type);
        }
    }

    @Test
    public void givenIndianStateInformation_whenDelimeterImproper_shouldHandleException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            int numberOfRecord = stateCensusAnalyser.findNumberOfRecord(INDIAN_STATES_INFORMATION_FILE);
            Assert.assertEquals(37,numberOfRecord);
        } catch (CSVStateException e) {
            Assert.assertEquals(CSVStateException.ExceptionType.DELIMETER_EXCEPTION ,e.type);
        }
    }

    @Test
    public void givenIndianStateInformation_whenHeaderImproper_shouldHandleException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            int numberOfRecord = stateCensusAnalyser.findNumberOfRecord(INDIAN_STATES_INFORMATION_FILE);
            Assert.assertEquals(37,numberOfRecord);
        } catch (CSVStateException e) {
            Assert.assertEquals(CSVStateException.ExceptionType.DELIMETER_EXCEPTION ,e.type);
        }
    }

    @Test
    public void givenIndianStateCensusInformation_whenProperAnalyse_shouldMatchRecords() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        int numberOfRecord = 0;
        try {
            numberOfRecord = stateCensusAnalyser.findNumberOfCensusRecord(INDIAN_STATES_CENSUS_INFORMATION_FILE);
            Assert.assertEquals(29,numberOfRecord);
        } catch (CSVStateException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusInformation_whenImproperFile_shouldHandleException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        int numberOfCensusRecord = 0;
        try {
            numberOfCensusRecord = stateCensusAnalyser.findNumberOfCensusRecord(INDIAN_STATES_CENSUS_INFORMATION_FILE2);
            Assert.assertEquals(29,numberOfCensusRecord);
        } catch (CSVStateException e) {
            Assert.assertEquals(CSVStateException.ExceptionType.NO_SUCH_FILE  ,e.type);
        }
    }
}
