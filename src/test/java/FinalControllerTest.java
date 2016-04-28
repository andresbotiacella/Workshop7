import java.util.ArrayList;
import Controller.FinalController;
import Model.FinalModel;
import Model.CalculationResult;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class containing the unit tests for the Controller
 *
 * @author Andy
 */
public class FinalControllerTest {
    
    /**
     *Empty Constructor
     */
    public FinalControllerTest() {
    }
   
    /**
     * Test for loadClassInfo method, from class FinalController.
     */
    @Test
    public void testLoadIntegrationData() {
        System.out.println("Testing loadClassInfo Method:");
        String fileName = "result1.txt";
        FinalController testController = new FinalController();
        List<CalculationResult> dataList = testController.loadClassInfo(fileName);
        assertTrue("Data List must have values.", dataList.size() > 0);
    }

}
