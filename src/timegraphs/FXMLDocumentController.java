/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timegraphs;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 *
 * @author priyal
 */
public class FXMLDocumentController implements Initializable {

    private final String URL = "jdbc:sqlite:timelog.db";
    private Connection connection;

    @FXML
    private Text DailyBreakdownTitle;

    @FXML
    private BarChart<String, Double> DailyBreakdown;

    @FXML
    private Button DailyBreakdownRefresh;
    @FXML
    private Text Category;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DailyBreakDownRefresh();

    }

    private void DailyBreakDownRefresh() {
        String query = "select task_title, total_duration FROM timelog ORDER BY total_duration";
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        try {
            //connect to database 
            Connection connection = DriverManager.getConnection("jdbc:sqlite:timelog.db");

            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Duration");
            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Task");

            //execute query and store in result set 
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            DailyBreakdown.getData().add(series);

        } catch (Exception e) {

        }

    }

    //method to connect to database 
    private void initializeDatabaseConnection() throws SQLException, ClassNotFoundException {
        connection = DriverManager.getConnection(URL);
    }

}
