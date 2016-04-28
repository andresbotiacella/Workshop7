/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 *
 * @author 
 */
public class Search {
 
    /**
     * Search the x value for which the Simpson integral resolves to the given P
     * 
     * @param integralInfo
     * @return All the integral information with the x value updated
     */
    static public IntegralInfo xValue(IntegralInfo integralInfo) {
		
	boolean mustAdjustX = true;
	double d = 0.5;
	double initialPValue = valueFormatted(SimpsonCalculationManager.simpsonIntegral(integralInfo).getIntegralResult());
	double finalPValue = 0.0;
	double difference = Math.abs(initialPValue - integralInfo.getExpectedResult());

	if(difference < SimpsonCalculationManager.ACCEPTABLE_ERROR) {
            return integralInfo;
	}else {
            while (difference > SimpsonCalculationManager.ACCEPTABLE_ERROR) {

                difference  = finalPValue - integralInfo.getExpectedResult();

                    if(mustAdjustX && (difference < SimpsonCalculationManager.ACCEPTABLE_ERROR)) {
                        d = adjustDValue(d, integralInfo.getIntegralUpperLimit());
                    } else if (!mustAdjustX && (difference > SimpsonCalculationManager.ACCEPTABLE_ERROR)) {
                        d = adjustDValue(d, integralInfo.getIntegralUpperLimit());
                    }

                    mustAdjustX = finalPValue > integralInfo.getExpectedResult() ? true : false;
                        
                    integralInfo.setIntegralUpperLimit(adjustX(mustAdjustX, integralInfo.getIntegralUpperLimit(), d));
                    initialPValue = finalPValue;
                    finalPValue = valueFormatted(SimpsonCalculationManager.simpsonIntegral(integralInfo).getIntegralResult());
                    difference = Math.abs(finalPValue - integralInfo.getExpectedResult());
                    }
		}

		return integralInfo;
	}
    
    /**
     * 
     * @param mustAdjustX
     * @param integralUpperLimit
     * @param d
     * @return
     */
    static private double adjustX(boolean mustAdjustX, double integralUpperLimit, double d) {	
	return mustAdjustX ? (integralUpperLimit -= d) : (integralUpperLimit += d);    
    }
	
    /**
     *
     * @param d
     * @param integralUpperLimit
     * @return
     */
    static private double adjustDValue(double d, double integralUpperLimit) {
		
        if (integralUpperLimit != 1.0) {
            d /= 2;
        }
        return d;
    }
    
    /**
     *
     * @param d
     * @return
     */
    static private double valueFormatted(double d) {
        
        DecimalFormat decimalFormat = new DecimalFormat("#.####");
	DecimalFormatSymbols symbols = decimalFormat.getDecimalFormatSymbols();
	symbols.setDecimalSeparator('.');
	decimalFormat.setDecimalFormatSymbols(symbols);
        return Double.valueOf(decimalFormat.format(d));
    }
}
