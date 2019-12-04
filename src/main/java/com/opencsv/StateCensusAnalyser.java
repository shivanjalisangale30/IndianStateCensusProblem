package com.opencsv;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.runner.manipulation.Sortable;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;

public class StateCensusAnalyser<T> {

    String sortByState = "/home/admin1/Desktop/IndianStateCensusProblem/SortedByState.json";
    String sortByPopulation = "/home/admin1/Desktop/IndianStateCensusProblem/SortedByPopulation.json";
    String sortByPopulationDensity = "/home/admin1/Desktop/IndianStateCensusProblem/SortedByPopulationDensity.json";
    String sortByArea = "/home/admin1/Desktop/IndianStateCensusProblem/SortedByArea.json";

    public int openCSVBuilder(String fileName) throws CSVStateException, IllegalAccessException {
        int count = 0;
        List<CSVStateCensus> list = new ArrayList<>();
        ArrayList<Object> state = new ArrayList<>();

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

           SortByState(list,sortByState);
            Sort(list,sortByArea);

        } catch (NoSuchFileException e) {
            throw new CSVStateException(CSVStateException.ExceptionType.NO_SUCH_FILE, "File not exist");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            throw new CSVStateException(CSVStateException.ExceptionType.DELIMETER_EXCEPTION, "File delimeter Issue Or Hearder Issue");
        }
        return count;
    }

    private void Sort(List<CSVStateCensus> list,String fileName) {
        for(int i=0;i<list.size()-1;i++){
            for(int j=0;j<list.size()-i-1;j++){
                if(list.get(j).getAreaInSqKm() < (list.get(j+1).getAreaInSqKm())){
                    CSVStateCensus tempObj=list.get(j);
                    list.set(j,list.get(j+1));
                    list.set(j+1,tempObj);
                }
            }
        }
        writeToJsonFile(list,fileName);
    }


    private void SortByState(List<CSVStateCensus> list, String fileName) {
        for(int i=0;i<list.size()-1;i++){
            for(int j=0;j<list.size()-i-1;j++){
                if(list.get(j).getState().compareTo(list.get(j+1).getState())>0){
                    CSVStateCensus tempObj=list.get(j);
                    list.set(j,list.get(j+1));
                    list.set(j+1,tempObj);
                }
            }
        }
        writeToJsonFile(list,fileName);
    }

    public  void writeToJsonFile(List<CSVStateCensus> list, String fileName){
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


