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
public class CalculationResult {
    
    private double xk;
    private List<ValuePair> values;
    private double expR;
    private double expR2;
    private double expSignificance;
    private double expB0;
    private double expB1;
    private double expYk;
    private double expRange;
    private double expUPI;
    private double expLPI;
    
    /**
     * Empty constructor of the class
     */
    public CalculationResult() {
        
    }

    /**
     *xk attribute getter
     * 
     * @return xk value
     */
    public double getXk() {
        return xk;
    }

    /**
     * xk attribute setter
     * @param xk
     */
    public void setXk(double xk) {
        this.xk = xk;
    }

    /**
     *RegressionB1  attribute getter
     * @return RegressionB1 getter
     */
    public double getRegressionB1() {
        return StatisticCalculationManager.calculateRegressionB1(values);
    }

    /**
     *
     * @return
     */
    public double getExpR() {
        return expR;
    }

    /**
     *
     * @param expR
     */
    public void setExpR(double expR) {
        this.expR = expR;
    }

    /**
     *
     * @return
     */
    public double getExpR2() {
        return expR2;
    }

    /**
     *
     * @param expR2
     */
    public void setExpR2(double expR2) {
        this.expR2 = expR2;
    }

    /**
     *
     * @return
     */
    public double getExpSignificance() {
        return expSignificance;
    }

    /**
     *
     * @param expSignificance
     */
    public void setExpSignificance(double expSignificance) {
        this.expSignificance = expSignificance;
    }

    /**
     *
     * @return
     */
    public double getExpB0() {
        return expB0;
    }

    /**
     *
     * @param expB0
     */
    public void setExpB0(double expB0) {
        this.expB0 = expB0;
    }

    /**
     *
     * @return
     */
    public double getExpB1() {
        return expB1;
    }

    /**
     *
     * @param expB1
     */
    public void setExpB1(double expB1) {
        this.expB1 = expB1;
    }

    /**
     *
     * @return
     */
    public double getExpYk() {
        return expYk;
    }

    /**
     *
     * @param expYk
     */
    public void setExpYk(double expYk) {
        this.expYk = expYk;
    }

    /**
     *
     * @return
     */
    public double getExpRange() {
        return expRange;
    }

    /**
     *
     * @param expRange
     */
    public void setExpRange(double expRange) {
        this.expRange = expRange;
    }

    /**
     *
     * @return
     */
    public double getExpUPI() {
        return expUPI;
    }

    /**
     *
     * @param expUPI
     */
    public void setExpUPI(double expUPI) {
        this.expUPI = expUPI;
    }

    /**
     *
     * @return
     */
    public double getExpLPI() {
        return expLPI;
    }

    /**
     *
     * @param expLPI
     */
    public void setExpLPI(double expLPI) {
        this.expLPI = expLPI;
    }

    
    /**
     *X Average  attribute getter
     * @return X Average value
     */
    public double getXAverage() {
        
        List<Double> xElements = new ArrayList<>();
        
        for(ValuePair value : values) {
            xElements.add(value.getX());
        }
        return StatisticCalculationManager.calculateMean(xElements);
    }

    /**
     * Y Average attribute getter
     * @return Y Average value
     */
    public double getYAverage() {
        
        List<Double> yElements = new ArrayList<>();
        
        for(ValuePair value : values) {
            yElements.add(value.getY());
        }
        
        return StatisticCalculationManager.calculateMean(yElements);
    }
    
    /**
     * CorrelationR  attribute getter
     * @return CorrelationR value
     */
    public double getCorrelationR() {
        return StatisticCalculationManager.calculateCorrelationCoefficient(values);
    }
    
    /**
     * N  attribute getter
     * @return N value
     */
    public double getN() {
        return (double)values.size();
    }
    
    /**
     * N  attribute getter
     * @return N value
     */
    public List<ValuePair> getValues() {
        return values;
    }

    /**
     * N attribute setter
     * @param values
     */
    public void setValues(List<ValuePair> values) {
        this.values = values;
    }
    
    /**
     * CorrelationSquareR attribute getter
     * @return CorrelationSquareR value
     */
    public double getCorrelationSquareR() {
        return getCorrelationR() * getCorrelationR();
    }
    
    /**
     * RegressionB0 attribute getter
     * @return RegressionB0 value
     */
    public double getRegressionB0() {
        return this.getYAverage() - (this.getXAverage() * getRegressionB1());
    }
    
    /**
     * YK attribute getter
     * @return YK value
     */
    public double getYK() {
        return this.getRegressionB0() + (getRegressionB1() * xk);
    }
    
    /**
     *
     * @return
     */
    public double getCorrelationSignificance() {
        
        double correlationSignificance = 0.0;
        double integralUpperlimit = calculateIntegralUpperLimit();

        IntegralInfo integralInfo = new IntegralInfo(this.getN() - 2.0, 10.0, 0.0, integralUpperlimit, 0.0);
        integralInfo = SimpsonCalculationManager.simpsonIntegral(integralInfo);
        correlationSignificance = 1 - (2 * integralInfo.getIntegralResult());

        return correlationSignificance;
    }
    
    /**
     *
     * @return
     */
    public double getRange() {
        
        IntegralInfo integralInfo = new IntegralInfo(this.getN() - 2.0, 10.0, 0.0, 0.5, 0.35);
        
        integralInfo = Search.xValue(integralInfo);
        
        double xValue = integralInfo.getIntegralUpperLimit();
        double standardDeviation = StatisticCalculationManager.standardDeviation(this.values);
        return xValue * standardDeviation * rangeThirdTerm();
    }
    
    /**
     *
     * @return
     */
    public double getUPI() {
        return this.getYK() + this.getRange();
    }
    
    /**
     *
     * @return
     */
    public double getLPI() {
        return this.getYK() - this.getRange();
    }
    
    private double calculateIntegralUpperLimit() {
        
        double numerator = Math.abs(this.getCorrelationR()) * Math.sqrt(this.getN() - 2.0);
        double enumarator = Math.sqrt(1 - this.getCorrelationSquareR());
        
        return numerator / enumarator;
    }
    
    private double rangeThirdTerm() {
        
        double rangeThirdTerm = 1 + (1 / (double) values.size());
        double numerator = Math.pow((xk - this.getXAverage()), 2.0);
        double enumerator = 0.0;
        
        for(ValuePair value : values) {
            enumerator += Math.pow(value.getX() - this.getXAverage(), 2.0);
        }
        
        double fraction = numerator / enumerator;
        rangeThirdTerm += fraction; 
        return Math.sqrt(rangeThirdTerm);
    }
}
