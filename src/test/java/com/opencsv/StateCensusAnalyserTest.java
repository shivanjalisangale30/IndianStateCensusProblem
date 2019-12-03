package com.opencsv;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class StateCensusAnalyserTest {

    private static final String INDIAN_STATES_CENSUS_INFORMATION_FILE = "/home/admin1/Desktop/IndianStateCensusProblem/StateCensusData.csv";
    private static final String INDIAN_STATES_CENSUS_INFORMATION_FILE1 = "/home/admin1/Desktop/IndianStateCensusProblem/StateCensusData2.csv";

    CSVStateCensus csvStateCensus = new CSVStateCensus();

    @Test
    public void givenIndianStateCensusInformation_whenProperAnalyse_shouldMatchRecords() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        int numberOfRecord ;
        try {
            numberOfRecord = stateCensusAnalyser.openCSVBuilder(csvStateCensus,INDIAN_STATES_CENSUS_INFORMATION_FILE);
            Assert.assertEquals(29,numberOfRecord);
        } catch (CSVStateException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusInformation_whenImproperFile_shouldHandleException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        int numberOfRecord ;
        try {
            numberOfRecord = stateCensusAnalyser.openCSVBuilder(csvStateCensus, INDIAN_STATES_CENSUS_INFORMATION_FILE1);
            Assert.assertEquals(29,numberOfRecord);
        } catch (CSVStateException e) {
            Assert.assertEquals(CSVStateException.ExceptionType.NO_SUCH_FILE  ,e.type);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusInformation_whenNotSupportedFileType_shouldHandleException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            stateCensusAnalyser.openCSVBuilder(csvStateCensus, INDIAN_STATES_CENSUS_INFORMATION_FILE);
        } catch (CSVStateException e) {
            Assert.assertEquals(CSVStateException.ExceptionType.NO_SUCH_FILE ,e.type);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusInformation_whenDelimeterImproper_shouldHandleException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            int numberOfRecord = stateCensusAnalyser.openCSVBuilder(csvStateCensus,INDIAN_STATES_CENSUS_INFORMATION_FILE);
            Assert.assertEquals(29,numberOfRecord);
        } catch (CSVStateException e) {
            Assert.assertEquals(CSVStateException.ExceptionType.DELIMETER_EXCEPTION,e.type);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusInformation_whenHeaderImproper_shouldHandleException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            int numberOfRecord = stateCensusAnalyser.openCSVBuilder(csvStateCensus,INDIAN_STATES_CENSUS_INFORMATION_FILE);
            Assert.assertEquals(29,numberOfRecord);
        } catch (CSVStateException e) {
            Assert.assertEquals(CSVStateException.ExceptionType.DELIMETER_EXCEPTION,e.type);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
