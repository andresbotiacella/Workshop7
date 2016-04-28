/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CalculationResult;
import Model.IntegralInfo;
import Model.LoadData;
//import Model.Representation;
import Model.Search;
import java.util.List;

/**
 *
 * @author 
 */
public class Controller {
    
    /**
     * Load data from file into list
     * 
     * @param fileName File to read the data from
     * @return List of values to be integrated
     */
    public List<CalculationResult> loadClassInfo(String fileName) {
        return LoadData.loadDataFromFile(fileName);
    }
}
