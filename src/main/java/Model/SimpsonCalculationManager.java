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
public class SimpsonCalculationManager {
    
    static public final double ACCEPTABLE_ERROR = 0.00001;
    
    /**
     *Calculates the integral using the Simpson method
     * 
     * @param integralInfo
     * @return
     */
    static public IntegralInfo simpsonIntegral(IntegralInfo integralInfo) {
        double p = calculateP(integralInfo);
        integralInfo.setNumberOfSegments(integralInfo.getNumberOfSegments() * 2.0);
        double p2 = calculateP(integralInfo);

        if((p - p2) < ACCEPTABLE_ERROR) {
            integralInfo.setIntegralResult(p2);
            return integralInfo;
        }else {
            return simpsonIntegral(integralInfo);
        }
    }
    
    /**
     *Calculates the p value
     * 
     * @param integralInfo
     * @return p value
     */
    static private double calculateP(IntegralInfo integralInfo) {
        
        double thirdOfWidth = integralInfo.getSegmentWidth() / 3.0;
        double tDistribution0Value = tDistributionFunction(integralInfo, 0.0);
        double tDistributionXValue = tDistributionFunction(integralInfo, integralInfo.getIntegralUpperLimit());
        double sumatories = tDistribution0Value + oddSumatory(integralInfo) + evenSumatory(integralInfo) + tDistributionXValue;

        return  thirdOfWidth * sumatories;
        
    }
    
    /**
     * Calculates the gamma function of a given value
     * 
     * @param value
     * @return Result of calculating gamma function to the given value
     */
    static private double gammaFunction(double value) {
        
        double gammaValue = 1.0;
        value--;
        
        do {
            gammaValue *= value;
            value--;
        }while(value > 0.0);
        
        if(value < 1 && value != 0) {
            gammaValue *= Math.sqrt(Math.PI);
        }
        
        return gammaValue;
    }
    
    /**
     * Calculates the tDistribution of a value
     * @param integralInfo Integral parameters
     * @param value to calculate the t distribution
     * @return Result of the t distribution
     */
    static private double tDistributionFunction(IntegralInfo integralInfo, double value) {
        
        double enumerator = gammaFunction((integralInfo.getDegreesOfFreedom() + 1) / 2);
        
        double dofTimesPiRoot = Math.sqrt(integralInfo.getDegreesOfFreedom() * Math.PI);
        double denominator = dofTimesPiRoot * gammaFunction(integralInfo.getDegreesOfFreedom() / 2);
        
        double division = enumerator / denominator;
        
        double base = 1 + (Math.pow(value, 2) / integralInfo.getDegreesOfFreedom());
        double exp = -((integralInfo.getDegreesOfFreedom() + 1) / 2);

        double power = Math.pow(base, exp);

        return division * power;
    }
    
    /**
     * Calculates the sumatory of the even numbers
     * 
     * @param integralInfo Integral parameters
     * @return sumatory result
     */
    static private double evenSumatory(IntegralInfo integralInfo) {
        
        double evenSumatory = 0.0;
        double limit = integralInfo.getNumberOfSegments() - 2;
        for(int i = 2; i <= limit; i += 2) {
            evenSumatory += 2 * tDistributionFunction(integralInfo, i * integralInfo.getSegmentWidth());
        }
        return evenSumatory;
    }
    
    /**
     * Calculates the sumatory of the odd numbers
     * @param integralInfo Integral parameters
     * @return Sumatory result
     */
    static private double oddSumatory(IntegralInfo integralInfo) {
        
        double oddSumatory = 0.0;
        double limit = integralInfo.getNumberOfSegments() - 1;
        for(int i = 1; i <= limit; i += 2) {
            oddSumatory += 4 * tDistributionFunction(integralInfo, i * integralInfo.getSegmentWidth());
        }
        return oddSumatory;
    }
}
