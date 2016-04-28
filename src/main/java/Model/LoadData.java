/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author 
 */
public class LoadData {
    
    /**
     *Load data from .txt file into a List of ClassInfo objects
     * 
     * @param fileName
     * @return classInfo list with the information read from the file
     */
    public static List<CalculationResult> loadDataFromFile(String fileName) {
        
        List<CalculationResult> dataList = new ArrayList<>();
        File archive = new File(fileName);
        try {
            String ruta = System.getProperty("user.dir") + "/"+fileName;
            BufferedReader reader = new BufferedReader(new FileReader(ruta));
            String line;
            while((line = reader.readLine()) != null) {
                StringTokenizer stringTokenized = new StringTokenizer(line, "\n");
                if(stringTokenized.hasMoreTokens()) {
                    
                    String[] stringsInLine = stringTokenized.nextToken().split(",");
                    CalculationResult result = new CalculationResult();
                    result.setExpR(Double.parseDouble(stringsInLine[0]));
                    result.setExpR2(Double.parseDouble(stringsInLine[1]));
                    result.setExpSignificance(Double.parseDouble(stringsInLine[2]));
                    result.setExpB0(Double.parseDouble(stringsInLine[3]));
                    result.setExpB1(Double.parseDouble(stringsInLine[4]));
                    result.setExpYk(Double.parseDouble(stringsInLine[5]));
                    result.setExpRange(Double.parseDouble(stringsInLine[6]));
                    result.setExpUPI(Double.parseDouble(stringsInLine[7]));
                    result.setExpLPI(Double.parseDouble(stringsInLine[8]));
                    result.setXk(Double.parseDouble(stringsInLine[9]));
                    ruta = System.getProperty("user.dir") + "/"+LoadData.getValueFileForResult(fileName);
                    result.setValues(LoadData.values(ruta));
                    dataList.add(result);
                       
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoadData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoadData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataList;
    }
    
    private static List<ValuePair> values(String fileName) {
        
        List<ValuePair> valuePairs = new ArrayList<>();
        File archive = new File(fileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while((line = reader.readLine()) != null) {
                StringTokenizer stringTokenized = new StringTokenizer(line, "\n");
                if(stringTokenized.hasMoreTokens()) {
                    String[] stringsInLine = stringTokenized.nextToken().split(",");
                    ValuePair valuePair = new ValuePair();
                    valuePair.setX(Double.parseDouble(stringsInLine[0]));
                    valuePair.setY(Double.parseDouble(stringsInLine[1]));
                    valuePairs.add(valuePair);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoadData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoadData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valuePairs;   
    }
    
    private static String getValueFileForResult(String resultFileName) {
        
        switch (resultFileName) {
            case "result1.txt":
                System.out.println("Estoy en el 1");
                return "valueset1.txt";
            case "result2.txt":
                System.out.println("Estoy en el 2");
                return "valueset2.txt";
            case "result3.txt":
                System.out.println("Estoy en el 3");
                return "valueset3.txt";
            default:
                System.out.println("Estoy en el 4");
                return "valueset4.txt";
        }
    }
}
