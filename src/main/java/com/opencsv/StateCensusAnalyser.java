package com.opencsv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser {
    public int findNumberOfRecord(String indianStatesInformationFile) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}