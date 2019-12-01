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
        int count = 0;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(indianStatesInformationFile));
            CsvToBean<CSVStates> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVStates.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<CSVStates> csvUserIterator = csvToBean.iterator();
            while (csvUserIterator.hasNext()) {
                CSVStates csvUser = csvUserIterator.next();
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

    public int findNumberOfCensusRecord(String indianStatesCensusInformationFile) {
        int count = 0;
        try {
             Reader reader = Files.newBufferedReader(Paths.get(indianStatesCensusInformationFile));
             CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVStateCensus.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<CSVStateCensus> csvUserIterator = csvToBean.iterator();
            while (csvUserIterator.hasNext()) {
                CSVStateCensus csvUser = csvUserIterator.next();
                count++;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return count;

    }
}