import Controller.FinalController;
import Model.CalculationResult;
import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import java.net.URI;
import java.net.URISyntaxException;

import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

import com.heroku.sdk.jdbc.DatabaseUrl;
import java.util.Iterator;
import java.util.List;
import static spark.Spark.get;

/**
 *
 * @author 
 */
public class Main {

   /**
     * Main method, where the endpoints are defined
     * 
     * @param args
     */
  public static void main(String[] args) {

    port(Integer.valueOf(System.getenv("PORT")));
    staticFileLocation("/public");

    
    get("/FinalCalculations", (req, res) -> {
        final String FILE_NAME_1 = "result1.txt";
        final String FILE_NAME_2 = "result2.txt";
        final String FILE_NAME_3 = "result3.txt";
        final String FILE_NAME_4 = "result4.txt";
        final String[] FILE_NAMES = {FILE_NAME_1, FILE_NAME_2, FILE_NAME_3, FILE_NAME_4};
        
        List<CalculationResult> data = new ArrayList<>();
        FinalController controller = new FinalController();
        String answer = "<p>Workshop 7 </p>";
        answer += "<br/>";
        int i = 1;
        for(String fileName : FILE_NAMES) {
            data = controller.loadClassInfo(fileName);
            Iterator it = data.iterator();
            answer += "<table border='1' cellpadding='2' cellspacing='0'>"
                    + "<tr>"
                    + "<td>Test</td>"
                    + "<td>Parameter</td>"
                    + "<td>Expected Value</td>"
                    + "<td>Actual Value</td>"
                    + "</tr>"
                    + "<tr><td rowspan='11'>Test " + i++ +" </td></tr>";
            while(it.hasNext()){
                CalculationResult calculationResult = (CalculationResult) it.next();
                answer += "<tr><td>rxy</td><td>" +calculationResult.getExpR() + " </td><td>" + calculationResult.getCorrelationR() + "</td></tr>"
                        + "<tr><td>r2</td><td>" +calculationResult.getExpR2() + " </td><td>"+ calculationResult.getCorrelationSquareR() + "</td></tr>"
                        + "<tr><td>significance</td><td>" +calculationResult.getExpSignificance()+ " </td><td>"+calculationResult.getCorrelationSignificance() + "</td></tr>"
                        + "<tr><td>B0</td><td>" +calculationResult.getExpB0() + " </td><td>"+calculationResult.getRegressionB0() + "</td></tr>"
                        + "<tr><td>B1</td><td>" +calculationResult.getExpB1() + " </td><td>"+calculationResult.getRegressionB1() + "</td></tr>"
                        + "<tr><td>yk</td><td>" +calculationResult.getExpYk() + " </td><td>"+calculationResult.getYK() + "</td></tr>"
                        + "<tr><td>Range</td><td>" +calculationResult.getExpRange() + " </td><td>"+calculationResult.getRange() + "</td></tr>"
                        + "<tr><td>UPI</td><td>" +calculationResult.getExpUPI()+ " </td><td>"+calculationResult.getUPI() + "</td></tr>"
                        + "<tr><td>LPI</td><td>" +calculationResult.getExpLPI() + " </td><td>"+calculationResult.getLPI() + "</td></tr>";
            }
            answer += "</table>";
            answer += "<br/>";
            answer += "<br/>";
            
        }   
        return "<html><body>" + answer + "</body><html/>";
    });
    
    
    get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Hello World!");

            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());

    get("/db", (req, res) -> {
      Connection connection = null;
      Map<String, Object> attributes = new HashMap<>();
      try {
        connection = DatabaseUrl.extract().getConnection();

        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

        ArrayList<String> output = new ArrayList<String>();
        while (rs.next()) {
          output.add( "Read from DB: " + rs.getTimestamp("tick"));
        }

        attributes.put("results", output);
        return new ModelAndView(attributes, "db.ftl");
      } catch (Exception e) {
        attributes.put("message", "There was an error: " + e);
        return new ModelAndView(attributes, "error.ftl");
      } finally {
        if (connection != null) try{connection.close();} catch(SQLException e){}
      }
    }, new FreeMarkerEngine());

  }

}
