package com.opencsv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser {

    public int findNumberOfRecord(String indianStatesInformationFile) throws CSVStateException {

        CSVStates csvStates = new CSVStates();
        int counter = openCSVBuilder(csvStates, indianStatesInformationFile);
        return counter;
    }

    public int findNumberOfCensusRecord(String indianStatesCensusInformationFile) throws CSVStateException {

        CSVStateCensus csvStateCensus = new CSVStateCensus();
        int counter = openCSVBuilder(csvStateCensus, indianStatesCensusInformationFile);
        return counter;
    }

    public int openCSVBuilder(Object className, String fileName) throws CSVStateException {
        int count = 0;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            CsvToBean<Object> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Object.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<Object> csvUserIterator = csvToBean.iterator();
            while (csvUserIterator.hasNext()) {
                Object csvUser = csvUserIterator.next();
                count++;
            }
        } catch (NoSuchFileException e){
            throw new CSVStateException(CSVStateException.ExceptionType.NO_SUCH_FILE ,"File not exist");
        }catch (IOException e) {
            e.printStackTrace();
        }catch (RuntimeException e){
            throw new CSVStateException(CSVStateException.ExceptionType.DELIMETER_EXCEPTION , "File delimeter Issue Or Hearder Issue");
        }
        return count;
    }
}