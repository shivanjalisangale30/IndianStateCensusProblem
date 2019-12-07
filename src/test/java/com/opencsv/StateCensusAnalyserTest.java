package com.opencsv;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class StateCensusAnalyserTest {

    String INDIAN_STATES_CENSUS_INFORMATION_FILE = "/home/admin1/Desktop/IndianStateCensusProblem/StateCensusData.csv";
    String IMPROPER_FILE = "/home/admin1/Desktop/IndianStateCensusProblem/StateCensusData2.csv";

    String sortData = "/home/admin1/Desktop/IndianStateCensusProblem/SortData.json";

    @Test
    public void givenIndianStateCensusInformationFile_whenProperAnalyse_shouldMatchRecords() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            List<CSVStateCensus> numberOfRecord = stateCensusAnalyser.openCSVBuilder(INDIAN_STATES_CENSUS_INFORMATION_FILE);
            Assert.assertEquals(29, numberOfRecord.size());
        } catch (StateCensusAnalayserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusInformationFile_whenImproperFile_shouldHandlNoSuchFileeException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            List<CSVStateCensus> numberOfRecord = stateCensusAnalyser.openCSVBuilder(IMPROPER_FILE);
            Assert.assertEquals(29, numberOfRecord);
        } catch (StateCensusAnalayserException e) {
            Assert.assertEquals(StateCensusAnalayserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenIndianStateCensusInformationFile_whenNotSupportedFileType_shouldHandlNoSuchFileeException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            stateCensusAnalyser.openCSVBuilder(INDIAN_STATES_CENSUS_INFORMATION_FILE);
        } catch (StateCensusAnalayserException e) {
            Assert.assertEquals(StateCensusAnalayserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenIndianStateCensusInformationFile_whenDelimeterImproper_shouldHandleDelimeterException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            List<CSVStateCensus> numberOfRecord = stateCensusAnalyser.openCSVBuilder(INDIAN_STATES_CENSUS_INFORMATION_FILE);
            Assert.assertEquals(29, numberOfRecord.size());
        } catch (StateCensusAnalayserException e) {
            Assert.assertEquals(StateCensusAnalayserException.ExceptionType.DELIMETER_EXCEPTION, e.type);
        }
    }

    @Test
    public void givenIndianStateCensusInformationFile_whenHeaderImproper_shouldHandleDelimeterException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            stateCensusAnalyser.openCSVBuilder(INDIAN_STATES_CENSUS_INFORMATION_FILE);
        } catch (StateCensusAnalayserException e) {
            Assert.assertEquals(StateCensusAnalayserException.ExceptionType.DELIMETER_EXCEPTION, e.type);
        }
    }

    @Test
    public void givenIndianStateCensusInformationFile_whenSortInAscendingOrder_shouldGiveStateInAlphabeticOrder() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            List<CSVStateCensus> data = stateCensusAnalyser.ascendingData(INDIAN_STATES_CENSUS_INFORMATION_FILE, "State", sortData);
            Assert.assertEquals("Andhra Pradesh", data.get(0).getState());
        } catch (StateCensusAnalayserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusInformationFile_whenSortInDesendingOrder_shouldGiveMostPopulousState() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            List<CSVStateCensus> data = stateCensusAnalyser.descendingData(INDIAN_STATES_CENSUS_INFORMATION_FILE, "Population", sortData);
            Assert.assertEquals("Uttar Pradesh", data.get(0).getState());
        } catch (StateCensusAnalayserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusInformationFile_whenSortInDesendingOrder_shouldGiveMostPopulationDensityState() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            List<CSVStateCensus> data = stateCensusAnalyser.descendingData(INDIAN_STATES_CENSUS_INFORMATION_FILE, "DensityPerSqKm", sortData);
            Assert.assertEquals("Bihar", data.get(0).getState());
        } catch (StateCensusAnalayserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusInformationFile_whenSortInDesendingOrder_shouldGiveMostLargestStateByArea() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            List<CSVStateCensus> data = stateCensusAnalyser.descendingData(INDIAN_STATES_CENSUS_INFORMATION_FILE, "AreaInSqKm", sortData);
            Assert.assertEquals("Rajasthan", data.get(0).getState());
        } catch (StateCensusAnalayserException e) {
            e.printStackTrace();
        }
    }
}
