/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author 
 */
public class IntegralInfo {
    
    private double degreesOfFreedom;
    private double numberOfSegments;
    private double integralLowerLimit;
    private double integralUpperLimit;
    private double integralResult;
    private double expectedResult;
    private double expectedUpperLimit;

    /**
     *Empty constructor of the class
     */
    public IntegralInfo() {
    }
    
    /**
     *IntegralInfo constructor
     * 
     * @param degreesOfFreedom
     * @param numberOfSegments
     * @param integralLowerLimit
     * @param integralUpperLimit
     * @param expectedResult
     */
    public IntegralInfo(double degreesOfFreedom, double numberOfSegments, double integralLowerLimit, double integralUpperLimit, double expectedResult) {
        this.degreesOfFreedom = degreesOfFreedom;
        this.numberOfSegments = numberOfSegments;
        this.integralLowerLimit = integralLowerLimit;
        this.integralUpperLimit = integralUpperLimit;
        this.expectedResult = expectedResult;
    }
    
    /**
     * Segment width getter
     * 
     * @param numberOfSegments
     */
    public void setNumberOfSegments(double numberOfSegments) {
        this.numberOfSegments = numberOfSegments;
    }
    
    /**
     * Number of segments getter
     * 
     * @return number of segments
     */
    public double getNumberOfSegments() {
        return numberOfSegments;
    }

    /**
     * Segment width getter
     * 
     * @return segment width
     */
    public double getSegmentWidth() {
        return this.integralUpperLimit / this.getNumberOfSegments();
    }
    
    /**
     *Degrees of freedom getter
     * 
     * @return degrees of freedom
     */
    public double getDegreesOfFreedom() {
        return degreesOfFreedom;
    }

    /**
     *Degrees of freedom setter
     * 
     * @param degreesOfFreedom
     */
    public void setDegreesOfFreedom(double degreesOfFreedom) {
        this.degreesOfFreedom = degreesOfFreedom;
    }

    /**
     *Integral lower limit getter
     * 
     * @return integral lower limit
     */
    public double getIntegralLowerLimit() {
        return integralLowerLimit;
    }

    /**
     *Integral lower limit setter
     * 
     * @param integralLowerLimit
     */
    public void setIntegralLowerLimit(double integralLowerLimit) {
        this.integralLowerLimit = integralLowerLimit;
    }

    /**
     *Integral upper limit getter
     * 
     * @return integral upper limit
     */
    public double getIntegralUpperLimit() {
        return integralUpperLimit;
    }

    /**
     *Integral upper limit setter
     * 
     * @param integralUpperLimit
     */
    public void setIntegralUpperLimit(double integralUpperLimit) {
        this.integralUpperLimit = integralUpperLimit;
    }

    /**
     *Integral result getter
     * @return integral result
     */
    public double getIntegralResult() {
        return integralResult;
    }

    /**
     *Integral result setter
     * 
     * @param integralResult
     */
    public void setIntegralResult(double integralResult) {
        this.integralResult = integralResult;
    }

    /**
     *Expected result getter
     * 
     * @return expected result
     */
    public double getExpectedResult() {
        return expectedResult;
    }

    /**
     *Expected result setter
     * 
     * @param expectedResult
     */
    public void setExpectedResult(double expectedResult) {
        this.expectedResult = expectedResult;
    }

    /**
     *Expected upper limit setter
     * 
     * @param expectedUpperLimit
     */
    public void setExpectedUpperLimit(double expectedUpperLimit) {
        this.expectedUpperLimit = expectedUpperLimit;
    }

    /**
     * Expected upper limit getter
     * @return
     */
    public double getExpectedUpperLimit() {
        return expectedUpperLimit;
    }
}
