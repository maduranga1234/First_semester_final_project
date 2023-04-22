package lk.ijse.super_cargo.controller;

import com.jfoenix.controls.JFXButton;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.super_cargo.db.DBConnection;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class ReportController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorpaneHome;

    @FXML
    private JFXButton buyerReportBtn;

    @FXML
    private JFXButton EmployeeBtn;

    @FXML
    void BuyerReportClick(ActionEvent event) {

        InputStream resource = this.getClass().getResourceAsStream("/report/BuyerReport.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @FXML
    void EmployeeBtnClick(ActionEvent event) {


        InputStream resource = this.getClass().getResourceAsStream("/report/Blank_A4_1.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @FXML
    void SupplierLoadBtnClick(ActionEvent event) {

        InputStream resource = this.getClass().getResourceAsStream("/report/SupplierLoad.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
    @FXML
    void OrderReportClick(ActionEvent event) {


        InputStream resource = this.getClass().getResourceAsStream("/report/Blank_A4_1.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    void initialize() {
        assert AnchorpaneHome != null : "fx:id=\"AnchorpaneHome\" was not injected: check your FXML file 'report.fxml'.";
        assert buyerReportBtn != null : "fx:id=\"buyerReportBtn\" was not injected: check your FXML file 'report.fxml'.";

    }

    public void salaryReportClick(ActionEvent event) {



    }

    public void salaryReportClicks(ActionEvent event) {
        InputStream resource = this.getClass().getResourceAsStream("/report/Salary.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
