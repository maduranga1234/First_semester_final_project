package lk.ijse.super_cargo.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.super_cargo.dto.Item;
import lk.ijse.super_cargo.dto.Salary;
import lk.ijse.super_cargo.dto.tm.ItemTm;
import lk.ijse.super_cargo.dto.tm.SalaryTm;
import lk.ijse.super_cargo.model.EmployeeModel;
import lk.ijse.super_cargo.model.ItemModel;
import lk.ijse.super_cargo.model.SalaryModel;
import lk.ijse.super_cargo.util.AlertController;
import lk.ijse.super_cargo.util.ValidationController;

public class SalaryController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorpaneHome;

    @FXML
    private JFXTextField EmployeeIdText;

    @FXML
    private JFXTextField SalaryIdText;

    @FXML
    private JFXTextField OverTimeText;

    @FXML
    private JFXTextField AmountText;


    @FXML
    private JFXButton SalaryUpdateBtn;

    @FXML
    private JFXButton SalarySaveBtn;

    @FXML
    private JFXButton SalaryDeleteBtn;

    @FXML
    private TableColumn<?, ?> SalaryIdCol;

    @FXML
    private TableColumn<?, ?> EmplooyeeIdCol;

    @FXML
    private TableColumn<?, ?> OverTimeCol;

    @FXML
    private TableColumn<?, ?> AmountCol;

    @FXML
    private TableColumn<?, ?> PaymentCol;

    @FXML
    private JFXComboBox PaymentMethordBox;

    @FXML
    private JFXComboBox EmployeeIdBox;

    @FXML
    private TableView<SalaryTm> SalaryTbl;

    @FXML
    private TextField searchText;

    @FXML
    private Button searchBtn;

    @FXML
    void DeleteClick(ActionEvent event) throws SQLException {

        String salaryId=SalaryIdText.getText();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to remove this Salary?");
        if(result==true){

            try {
                boolean isDeleted = SalaryModel.delete(salaryId);
                if (isDeleted) {
                    AlertController.confirmmessage("Delete successFully");

                } else {
                    AlertController.errormessage("Somethink went wrong");
                }
            }catch (SQLException throwables){
                throwables.printStackTrace();
                AlertController.errormessage("Somethink went wrong");

            }
        }
        setCellValueFactory();
        getAll();

        SalaryIdText.setText("");
        EmployeeIdBox.setValue("");
        OverTimeText.setText("");
        AmountText.setText("");
        PaymentMethordBox.setValue("");



    }



    @FXML
    void SaveClick(ActionEvent event) throws SQLException {

        if(ValidationController.salaryIdCheck(SalaryIdText.getText())) {
            if (!EmployeeIdBox.getSelectionModel().isEmpty()) {
                if (ValidationController.salary(OverTimeText.getText())) {
                    if (ValidationController.salary(AmountText.getText())) {
                        if (!PaymentMethordBox.getSelectionModel().isEmpty()) {

                            Salary salary = new Salary();

                            salary.setSalaryId(SalaryIdText.getText());
                            salary.setEmployeeId(String.valueOf(EmployeeIdBox.getValue()));
                            salary.setOt(Double.parseDouble(OverTimeText.getText()));
                            salary.setAmount(Double.parseDouble(AmountText.getText()));
                            salary.setPaymentMethord(String.valueOf(PaymentMethordBox.getValue()));


                            try {
                                boolean isSave = SalaryModel.Save(salary);
                                if (isSave) {
                                    AlertController.confirmmessage("Save successFully");

                                }

                            } catch (SQLIntegrityConstraintViolationException throwables) {

                                AlertController.errormessage("Duplicate Id");

                            } catch (Exception throwables) {

                                AlertController.errormessage("Error");

                            }


                            setCellValueFactory();
                            getAll();

                            SalaryIdText.setText("");
                            EmployeeIdBox.setValue("");
                            OverTimeText.setText("");
                            AmountText.setText("");
                            PaymentMethordBox.setValue("");
                        }else{
                            AlertController.errormessage("Please Select Payment Methord");
                        }
                    }else{
                        AlertController.errormessage("invalied Amount");
                    }
                }else{
                    AlertController.errormessage("invalied Over Time ");
                }
            }else{
                AlertController.errormessage("Please Select Employee Id");
            }
        }else{
            AlertController.errormessage("invalied Id");
        }
    }

    @FXML
    void UpdateClick(ActionEvent event) throws SQLException {

        if(ValidationController.salaryIdCheck(SalaryIdText.getText())) {
            if (!EmployeeIdBox.getSelectionModel().isEmpty()) {
                if (ValidationController.salary(OverTimeText.getText())) {
                    if (ValidationController.salary(AmountText.getText())) {
                        if (!PaymentMethordBox.getSelectionModel().isEmpty()) {
            Salary salary = new Salary();

            salary.setSalaryId(SalaryIdText.getText());
            salary.setEmployeeId(String.valueOf(EmployeeIdBox.getValue()));
            salary.setOt(Double.parseDouble(OverTimeText.getText()));
            salary.setAmount(Double.parseDouble(AmountText.getText()));
            salary.setPaymentMethord(String.valueOf(PaymentMethordBox.getValue()));

            boolean result = AlertController.okconfirmmessage("Are you sure you want to Update this Salary?");
            if (result == true) {

                try {
                    boolean isUpdates = SalaryModel.Update(salary);
                    if (isUpdates) {
                        AlertController.confirmmessage("Update successFully");


                    } else {

                        AlertController.errormessage("Somethink went wrong");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    AlertController.errormessage("Somethink went wrong");
                }
                setCellValueFactory();
                getAll();

                SalaryIdText.setText("");
                EmployeeIdBox.setValue("");
                OverTimeText.setText("");
                AmountText.setText("");
                PaymentMethordBox.setValue("");


            }
                        }else{
                            AlertController.errormessage("Please Select Payment Methord");
                        }
                    }else{
                        AlertController.errormessage("invalied Amount");
                    }
                }else{
                    AlertController.errormessage("invalied Over Time ");
                }
            }else{
                AlertController.errormessage("Please Select Employee Id");
            }
        }else{
            AlertController.errormessage("invalied Id");
        }

    }

    public void SalarySearchOnAction(ActionEvent event) {

        String salaryId=searchText.getText();

        try{
            Salary salary= SalaryModel.Search(salaryId);
            SalaryIdText.setText(salary.getSalaryId());
            EmployeeIdBox.setValue(salary.getEmployeeId());
            OverTimeText.setText(String.valueOf(salary.getOt()));
            AmountText.setText(String.valueOf(salary.getAmount()));
            PaymentMethordBox.setValue(salary.getPaymentMethord());

            String searchValue=SalaryIdText.getText().trim();
            ObservableList<SalaryTm> obList= FXCollections.observableArrayList();

        }catch (SQLException throwables){
            throwables.printStackTrace();
            AlertController.errormessage("Somethink went wrong");
        }
    }

    private void getAll() throws SQLException {

        try {
            ObservableList<SalaryTm> salaryData=SalaryModel.getAll();
           SalaryTbl .setItems(salaryData);


        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    private void setCellValueFactory(){

        SalaryIdCol.setCellValueFactory(new PropertyValueFactory<>("salaryId"));
        EmplooyeeIdCol.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        OverTimeCol.setCellValueFactory(new PropertyValueFactory<>("ot"));
        AmountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        PaymentCol.setCellValueFactory(new PropertyValueFactory<>("paymentMethord"));

    }
    @FXML
    void QualityBoxOnAction(ActionEvent event) { }

    @FXML
    void initialize() {
        assert AnchorpaneHome != null : "fx:id=\"AnchorpaneHome\" was not injected: check your FXML file 'salary.fxml'.";
        assert EmployeeIdText != null : "fx:id=\"EmployeeIdText\" was not injected: check your FXML file 'salary.fxml'.";
        assert SalaryIdText != null : "fx:id=\"EmployeeIdText1\" was not injected: check your FXML file 'salary.fxml'.";
        assert OverTimeText != null : "fx:id=\"EmployeeIdText2\" was not injected: check your FXML file 'salary.fxml'.";
        assert AmountText != null : "fx:id=\"EmployeeIdText3\" was not injected: check your FXML file 'salary.fxml'.";
        assert SalaryUpdateBtn != null : "fx:id=\"SalaryUpdateBtn\" was not injected: check your FXML file 'salary.fxml'.";
        assert SalarySaveBtn != null : "fx:id=\"SalarySaveBtn\" was not injected: check your FXML file 'salary.fxml'.";
        assert SalaryDeleteBtn != null : "fx:id=\"SalaryDeleteBtn\" was not injected: check your FXML file 'salary.fxml'.";
        assert SalaryIdCol != null : "fx:id=\"SalaryIdCol\" was not injected: check your FXML file 'salary.fxml'.";
        assert EmplooyeeIdCol != null : "fx:id=\"EmplooyeeIdCol\" was not injected: check your FXML file 'salary.fxml'.";
        assert OverTimeCol != null : "fx:id=\"OverTimeCol\" was not injected: check your FXML file 'salary.fxml'.";
        assert AmountCol != null : "fx:id=\"AmountCol\" was not injected: check your FXML file 'salary.fxml'.";
        assert PaymentCol != null : "fx:id=\"PaymentCol\" was not injected: check your FXML file 'salary.fxml'.";
        assert PaymentMethordBox != null : "fx:id=\"PaymentMethordBox\" was not injected: check your FXML file 'salary.fxml'.";


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        PaymentMethordBox.getItems().addAll("Cash", "Bank Deposit");
        LoadEmployeeId();

        setCellValueFactory();
        try {
            getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void searchTextOnKeyTyped(KeyEvent keyEvent) throws SQLException {

        String searchValue=searchText.getText().trim();
        ObservableList<SalaryTm>obList= SalaryModel.getAll();

        if (!searchValue.isEmpty()) {
            ObservableList<SalaryTm> filteredData = obList.filtered(new Predicate<SalaryTm>(){
                @Override
                public boolean test(SalaryTm salaryTm) {
                    return String.valueOf(salaryTm.getSalaryId()).toLowerCase().contains(searchValue.toLowerCase());        }
            });
            SalaryTbl.setItems(filteredData);} else {
            SalaryTbl.setItems(obList);
        }



    }


    public void SalaryOnMouseClick(MouseEvent mouseEvent) {

        TablePosition pos=SalaryTbl.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<SalaryTm,?>> columns=SalaryTbl.getColumns();

        SalaryIdText.setText(columns.get(0).getCellData(row).toString());
        EmployeeIdBox.setValue(columns.get(1).getCellData(row).toString());
        OverTimeText.setText(columns.get(2).getCellData(row).toString());
        AmountText.setText(columns.get(3).getCellData(row).toString());
        PaymentMethordBox.setValue(columns.get(4).getCellData(row).toString());

    }

    private void LoadEmployeeId(){

        try{
            ObservableList<String>employeeIds= FXCollections.observableArrayList();
            List<String> ids = EmployeeModel.LoadEmployeeIds();

            for(String id:ids) {
                employeeIds.add(id);

            }
            EmployeeIdBox.setItems(employeeIds);

        }catch (SQLException throwables){
            AlertController.errormessage("Something went Wrong");
        }
    }

    @FXML
    void EmployeeIdBoxAction(ActionEvent event) {


    }



}
