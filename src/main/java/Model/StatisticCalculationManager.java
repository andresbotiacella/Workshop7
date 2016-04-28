/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 
 */
public class StatisticCalculationManager {
    
    /**
     * Calculates the mean of a set of data
     * @param dataList
     * @return mean of the data
     */
    public static double calculateMean(List<Double> dataList) {
        
        double sum = 0.0;
        for(double element : dataList) {
            sum += element;
        }
        double listSize = (double)dataList.size();
        double mean = sum / listSize;
        return mean;
        
    }
    
    /**
     * Calculates regression B1 for a set of data
     * @param values
     * @return
     */
    public static double calculateRegressionB1(List<ValuePair> values) {
        
        double sumOfValuesProduct = calculateSumOfValuesProduct(values);
        
        List<Double> xElements = new ArrayList<Double>();
        List<Double> yElements = new ArrayList<Double>();
        for(ValuePair value : values) {
            xElements.add(value.getX());
            yElements.add(value.getY());
        }
        double xAverage = calculateMean(xElements);
        double yAverage = calculateMean(yElements);

        double sumOfXSquarevalues = calculateSumOfSquareValues(xElements);

        double numerator = sumOfValuesProduct - ((double) values.size() * xAverage * yAverage);
        double enumerator = sumOfXSquarevalues - ((double) values.size() * Math.pow(xAverage, 2));
        double result = numerator / enumerator;
        
        return result;
        
    }
    
    /**
     * Calculate correlation coefficient for a set of data
     * @param values
     * @return
     */
    public static double calculateCorrelationCoefficient(List<ValuePair> values) {
        
        double sumOfValuesProduct = calculateSumOfValuesProduct(values);
        double size = (double) values.size();
        
        sumOfValuesProduct = size * sumOfValuesProduct;
        List<Double> xElements = new ArrayList<Double>();
        List<Double> yElements = new ArrayList<Double>();
        for(ValuePair value : values) {
            xElements.add(value.getX());
            yElements.add(value.getY());
        }
        
        double xSum = calculateSumOfValues(xElements);
        double ySum = calculateSumOfValues(yElements);
        double productOfSums = xSum * ySum;
        double numerator = sumOfValuesProduct - productOfSums;
        
        double sumOfXSquarevalues = calculateSumOfSquareValues(xElements);
        double sumOfYSquarevalues = calculateSumOfSquareValues(yElements);
        
        double enumeratorPart1 = (size * sumOfXSquarevalues) - Math.pow(xSum, 2);
        double enumeratorPart2 = (size * sumOfYSquarevalues) - Math.pow(ySum, 2);
        double enumerator = Math.sqrt(enumeratorPart1 * enumeratorPart2);
        
        double result = numerator / enumerator;
        return result;
        
    }
    
    /**
     * Calculate standardDeviation
     * @param values
     * @return Standard Deviation
     */
    public static double standardDeviation(List<ValuePair> values) {
        
        double standardDeviation;
        double sumatory = 0.0;
        double size = (double) values.size();
        double firstTerm = 1 / (size - 2);

        List<Double> xElements = new ArrayList<>();
        List<Double> yElements = new ArrayList<>();
        
        for(ValuePair value : values) {
            xElements.add(value.getX());
            yElements.add(value.getY());
        }
        double B1 = calculateRegressionB1(values);  
        double B0 = calculateMean(yElements) - (calculateMean(xElements) * B1);
              
        sumatory = values.stream().map((value) -> value.getY() - B0 - (B1 * value.getX())).map((base) -> Math.pow(base, 2)).reduce(sumatory, (accumulator, _item) -> accumulator + _item);
        standardDeviation = Math.sqrt(firstTerm * sumatory);
        return standardDeviation;
    }
    
    /**
     * Sumatory of values product
     * @param values
     * @return 
     */
    private static double calculateSumOfValuesProduct(List<ValuePair> values) {
        double sumOfValuesProduct = 0.0;
        for(ValuePair value : values) {
            sumOfValuesProduct += value.getX() * value.getY();
        }
        return sumOfValuesProduct;
    }
    
    /**
     * Sumatory of square values
     * @param values
     * @return
     */
    private static double calculateSumOfSquareValues(List<Double> values) {
        double sumOfSquareValues = 0.0;
        for(double value : values) {
            sumOfSquareValues += Math.pow(value, 2);
        }
        return sumOfSquareValues;
    }
    
    /**
     * Sumatory of values
     * @param values
     * @return
     */
    private static double calculateSumOfValues(List<Double> values) {
        double sumOfValues = 0.0;
        for(double value : values) {
            sumOfValues += value;
        }
        return sumOfValues;
    }
}