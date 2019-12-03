package com.opencsv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StateCensusAnalyser {

    public int openCSVBuilder(Object className, String fileName) throws CSVStateException, IllegalAccessException {
        int count = 0;

        Class<? extends Object> c1 = className.getClass();
        Field[] fields1 = c1.getDeclaredFields();
        List<Object> list = new ArrayList<>();

        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            CsvToBean<Object> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(c1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<Object> csvUserIterator = csvToBean.iterator();
            while (csvUserIterator.hasNext()) {
                Object csvUser = csvUserIterator.next();
                count++;
            }

        } catch (NoSuchFileException e) {
            throw new CSVStateException(CSVStateException.ExceptionType.NO_SUCH_FILE, "File not exist");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            throw new CSVStateException(CSVStateException.ExceptionType.DELIMETER_EXCEPTION, "File delimeter Issue Or Hearder Issue");
        }
        return count;
    }
}

