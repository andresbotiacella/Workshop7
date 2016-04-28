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
public class ValuePair {
    
    private double x;
    private double y;

    /**
     * Empty constructor
     */
    public ValuePair() {
        
    }
    
    /**
     * Constructor that receives two values
     * @param x
     * @param y
     */
    public ValuePair(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * X attribute getter
     * @return X value
     */
    public double getX() {
        return x;
    }

    /**
     * X attribute setter
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Y attribute getter
     * @return Y value
     */
    public double getY() {
        return y;
    }

    /**
     *Y attribute setter
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }
    
}
