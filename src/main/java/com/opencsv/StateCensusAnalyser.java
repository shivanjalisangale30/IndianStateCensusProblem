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

    String sortByState = "/home/admin1/Desktop/IndianStateCensusProblem/SortByState.json";
    String sortByPopulation = "/home/admin1/Desktop/IndianStateCensusProblem/SortByPopulation.json";
    String sortByDensity = "/home/admin1/Desktop/IndianStateCensusProblem/SortedByPopulationDensity.json";
    String sortByArea = "/home/admin1/Desktop/IndianStateCensusProblem/SortByArea.json";

    public int openCSVBuilder(String fileName) throws CSVStateException, IllegalAccessException {
        int count = 0;
        List<CSVStateCensus> list = new ArrayList<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVStateCensus.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<CSVStateCensus> csvUserIterator = csvToBean.iterator();
            while (csvUserIterator.hasNext()) {
                CSVStateCensus csvUser = csvUserIterator.next();
                list.add(csvUser);
                count++;
            }

            ascendingSort(list, "getState", sortByState);
            descendingSort(list, "getPopulation", sortByPopulation);
            descendingSort(list,"getDensityPerSqKm",sortByDensity );
            descendingSort(list,"getAreaInSqKm",sortByArea );

        } catch (NoSuchFileException e) {
            throw new CSVStateException(CSVStateException.ExceptionType.NO_SUCH_FILE, "File not exist");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            throw new CSVStateException(CSVStateException.ExceptionType.DELIMETER_EXCEPTION, "File delimeter Issue Or Hearder Issue");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return count;
    }

    void ascendingSort(List<CSVStateCensus> list, String methodname, String fileName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                Class cls = list.get(j).getClass();
                Method methodcall = cls.getDeclaredMethod(methodname);
                T value1 = (T) methodcall.invoke(list.get(j));
                Class cls1 = list.get(j + 1).getClass();
                Method methodcall1 = cls1.getDeclaredMethod(methodname);
                T value2 = (T) methodcall1.invoke(list.get(j + 1));
                if (value1.compareTo(value2) > 0) {
                    CSVStateCensus tempObj = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, tempObj);
                }
            }
        }
        writeToJsonFile(list, fileName);
    }

    void descendingSort(List<CSVStateCensus> list, String methodname, String fileName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                Class cls = list.get(j).getClass();
                Method methodcall = cls.getDeclaredMethod(methodname);
                T value1 = (T) methodcall.invoke(list.get(j));
                Class cls1 = list.get(j + 1).getClass();
                Method methodcall1 = cls1.getDeclaredMethod(methodname);
                T value2 = (T) methodcall1.invoke(list.get(j + 1));
                if (value1.compareTo(value2) < 0) {
                    CSVStateCensus tempObj = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, tempObj);
                }
            }
        }
        writeToJsonFile(list, fileName);
    }
    public void writeToJsonFile(List<CSVStateCensus> list, String fileName) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


