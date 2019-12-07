package com.opencsv;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;


public class StateCensusAnalyser<T extends Comparable<T>> {

    public List openCSVBuilder(String fileName) throws StateCensusAnalayserException {
        int count = 0;
        List<CSVStateCensus> csvStateCensusList = null;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVStateCensus.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            csvStateCensusList = csvToBean.parse();

        } catch (NoSuchFileException e) {
            throw new StateCensusAnalayserException(StateCensusAnalayserException.ExceptionType.NO_SUCH_FILE, "File not exist");
        } catch (IOException e) {
            throw new StateCensusAnalayserException(StateCensusAnalayserException.ExceptionType.INPUT_OUTPUT_ISSUE, "Input Output issue");
        } catch (RuntimeException e) {
            throw new StateCensusAnalayserException(StateCensusAnalayserException.ExceptionType.DELIMETER_EXCEPTION, "File delimeter Issue Or Hearder Issue");
        }
        return csvStateCensusList;
    }

    public List ascendingSort(List<CSVStateCensus> list, String methodname) throws StateCensusAnalayserException {
        try {
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = 0; j < list.size() - i - 1; j++) {
                    Class cls = list.get(j).getClass();
                    Method methodcall = cls.getDeclaredMethod("get" + methodname);
                    T value1 = (T) methodcall.invoke(list.get(j));
                    Class cls1 = list.get(j + 1).getClass();
                    Method methodcall1 = cls1.getDeclaredMethod("get" + methodname);
                    T value2 = (T) methodcall1.invoke(list.get(j + 1));
                    if (value1.compareTo(value2) > 0) {
                        CSVStateCensus tempObj = list.get(j);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, tempObj);
                    }
                }
            }
        } catch ( IllegalAccessException e) {
            throw new StateCensusAnalayserException(StateCensusAnalayserException.ExceptionType.NO_SUCH_METHOD);
        } catch (InvocationTargetException e) {
            throw new StateCensusAnalayserException(StateCensusAnalayserException.ExceptionType.INVOCATION_TARGET);
        } catch (NoSuchMethodException e) {
            throw new StateCensusAnalayserException(StateCensusAnalayserException.ExceptionType.ILLEGAL_ACCESS);
        }
        return list;
    }

    public List descendingSort(List<CSVStateCensus> list, String methodname) throws StateCensusAnalayserException {
        try {
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = 0; j < list.size() - i - 1; j++) {
                    Class cls = list.get(j).getClass();
                    Method methodcall = cls.getDeclaredMethod("get" + methodname);
                    T value1 = (T) methodcall.invoke(list.get(j));
                    Class cls1 = list.get(j + 1).getClass();
                    Method methodcall1 = cls1.getDeclaredMethod("get" + methodname);
                    T value2 = (T) methodcall1.invoke(list.get(j + 1));
                    if (value1.compareTo(value2) < 0) {
                        CSVStateCensus tempObj = list.get(j);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, tempObj);
                    }
                }
            }
        } catch ( IllegalAccessException e) {
            throw new StateCensusAnalayserException(StateCensusAnalayserException.ExceptionType.NO_SUCH_METHOD);
        } catch (InvocationTargetException e) {
            throw new StateCensusAnalayserException(StateCensusAnalayserException.ExceptionType.INVOCATION_TARGET);
        } catch (NoSuchMethodException e) {
            throw new StateCensusAnalayserException(StateCensusAnalayserException.ExceptionType.ILLEGAL_ACCESS);
        }
        return list;
    }

    private void writeToJsonFile(List<CSVStateCensus> list, String fileName) throws StateCensusAnalayserException {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            throw new StateCensusAnalayserException(StateCensusAnalayserException.ExceptionType.INPUT_OUTPUT_ISSUE, "Input Output issue");
        }
    }

    public List ascendingData(String csvFileName, String sortBy, String jsonFile) throws StateCensusAnalayserException {
        List dataList = openCSVBuilder(csvFileName);
        List ascendData = ascendingSort(dataList, sortBy);
        writeToJsonFile(ascendData, jsonFile);
        return ascendData;
    }

    public List descendingData(String csvFileName, String sortBy, String jsonFile) throws StateCensusAnalayserException {
        List dataList = openCSVBuilder(csvFileName);
        List ascendData = descendingSort(dataList, sortBy);
        writeToJsonFile(ascendData, jsonFile);
        return ascendData;
    }
}


