package lk.ijse.super_cargo.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Filter;

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
import lk.ijse.super_cargo.dto.Employee;
import lk.ijse.super_cargo.dto.tm.EmployeeTm;
import lk.ijse.super_cargo.model.EmployeeModel;
import lk.ijse.super_cargo.util.AlertController;
import lombok.SneakyThrows;

public class EmployeeController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorpaneHome;

    @FXML
    private JFXButton EmpUpdateBtn;

    @FXML
    private JFXButton EmpSaveBtn;

    @FXML
    private JFXButton EmpDeleteBtn;

    @FXML
    private JFXTextField EmpNameText;

    @FXML
    private JFXTextField EmpAddressText;

    @FXML
    private JFXTextField EmpNicText;

    @FXML
    private JFXTextField EmpDobText;

    @FXML
    private JFXTextField EmpContactText;

    @FXML
    private JFXTextField EmpJopText;

    @FXML
    private JFXTextField EmpIdText;

    @FXML
    private TableView<EmployeeTm> EmployeeTbl;

    @FXML
    private TableColumn<?, ?> IdColum;

    @FXML
    private TableColumn<?, ?> NameColum;

    @FXML
    private TableColumn<?, ?> AddressColum;

    @FXML
    private TableColumn<?, ?> NicColum;

    @FXML
    private TableColumn<?, ?> DobColum;

    @FXML
    private TableColumn<?, ?> ContactColum;

    @FXML
    private TableColumn<?, ?> JobColum;


    @FXML
    private TextField searchText;

    @FXML
    private Button searchBtn;




    @FXML
    void DeleteClick(ActionEvent event) throws SQLException {

        String empId=EmpIdText.getText();
        boolean result = AlertController.okconfirmmessage("Are you sure you want to remove this employee?");
        if(result==true){

            try {
                boolean isDeleted = EmployeeModel.delete(empId);
                if (isDeleted) {
                    AlertController.confirmmessage("Delete Successful");

                } else {
                    AlertController.errormessage("Somethink went wrong");
                }
            }catch (SQLException throwables){
                throwables.printStackTrace();
                AlertController.errormessage("Somethink went wrong");

            }

        }


     //setCellValueFactory();
     getAll();

        EmpIdText.setText("");
        EmpNameText.setText("");
        EmpNicText.setText("");
        EmpDobText.setText("");
        EmpAddressText.setText("");
        EmpContactText.setText("");
        EmpJopText.setText("");
        searchText.setText("");


    }

    @FXML
    void SaveClick(ActionEvent event) throws SQLException {

        Employee employee=new Employee();

        employee.setEmpId(EmpIdText.getText());
        employee.setEmpName(EmpNameText.getText());
        employee.setEmpAddress(EmpAddressText.getText());
        employee.setEmpNic(EmpNicText.getText());
        employee.setEmpDob(EmpDobText.getText());
        employee.setEmpContact(EmpContactText.getText());
        employee.setEmpJob(EmpJopText.getText());

        try {
            boolean isSaved= EmployeeModel.Save(employee);
            if(isSaved){
                AlertController.confirmmessage("saved");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            AlertController.errormessage("Error");

        }
        getAll();


        EmpIdText.setText("");
        EmpNameText.setText("");
        EmpNicText.setText("");
        EmpDobText.setText("");
        EmpAddressText.setText("");
        EmpContactText.setText("");
        EmpJopText.setText("");
        searchText.setText("");

    }

    @FXML
    void UpdateClick(ActionEvent event) throws SQLException {

        Employee employee=new Employee();

        employee.setEmpId(EmpIdText.getText());
        employee.setEmpName(EmpNameText.getText());
        employee.setEmpNic(EmpNicText.getText());
        employee.setEmpDob(EmpDobText.getText());
        employee.setEmpAddress(EmpAddressText.getText());
        employee.setEmpContact(EmpContactText.getText());
        employee.setEmpJob(EmpJopText.getText());



        boolean result = AlertController.okconfirmmessage("Are you sure you want to remove this employee?");
        if(result==true){

            try {
                boolean isUpdates=EmployeeModel.update(employee);
                if(isUpdates){
                    AlertController.confirmmessage("Update Ok");

                }
                else{

                    AlertController.errormessage("Error");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                AlertController.errormessage("Error");
            }


        }





        setCellValueFactory();
        getAll();

        EmpIdText.setText("");
        EmpNameText.setText("");
        EmpNicText.setText("");
        EmpDobText.setText("");
        EmpAddressText.setText("");
        EmpContactText.setText("");
        EmpJopText.setText("");
        searchText.setText("");

    }



    private void setCellValueFactory(){

       IdColum.setCellValueFactory(new PropertyValueFactory<>("empId"));
        NameColum.setCellValueFactory(new PropertyValueFactory<>("empName"));
        AddressColum.setCellValueFactory(new PropertyValueFactory<>("empAddress"));
        NicColum.setCellValueFactory(new PropertyValueFactory<>("empNic"));
        DobColum.setCellValueFactory(new PropertyValueFactory<>("empDob"));
        ContactColum.setCellValueFactory(new PropertyValueFactory<>("empContact"));
        JobColum.setCellValueFactory(new PropertyValueFactory<>("empJob"));
        }


   private void getAll() throws SQLException {
       try {

            ObservableList<EmployeeTm> employeeData =EmployeeModel.getAll();
            EmployeeTbl.setItems(employeeData);

       }catch (SQLException throwables){
           throwables.printStackTrace();
       }
   }

    @FXML
    void empIdSeachOnAction(ActionEvent event) {

        String empId=searchText.getText();

        try {
            Employee employee = EmployeeModel.Search(empId);
            EmpIdText.setText(employee.getEmpId());
            EmpNameText.setText(employee.getEmpName());
            EmpAddressText.setText(employee.getEmpAddress());
            EmpNicText.setText(employee.getEmpNic());
            EmpDobText.setText(employee.getEmpDob());
            EmpContactText.setText(employee.getEmpContact());
            EmpJopText.setText(employee.getEmpJob());

//            String searchValue = EmpIdText.getText().trim();
            ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();
        }catch (SQLException throwables){
            throwables.printStackTrace();
            AlertController.errormessage("Error");
        }


    }

//    @FXML
//    void initialize() {
//        assert AnchorpaneHome != null : "fx:id=\"AnchorpaneHome\" was not injected: check your FXML file 'employee.fxml'.";
//        assert EmpUpdateBtn != null : "fx:id=\"EmpUpdateBtn\" was not injected: check your FXML file 'employee.fxml'.";
//        assert EmpSaveBtn != null : "fx:id=\"EmpSaveBtn\" was not injected: check your FXML file 'employee.fxml'.";
//        assert EmpDeleteBtn != null : "fx:id=\"EmpDeleteBtn\" was not injected: check your FXML file 'employee.fxml'.";
//        assert EmpNameText != null : "fx:id=\"EmpNameText\" was not injected: check your FXML file 'employee.fxml'.";
//        assert EmpAddressText != null : "fx:id=\"EmpAddressText\" was not injected: check your FXML file 'employee.fxml'.";
//        assert EmpNicText != null : "fx:id=\"EmpNicText\" was not injected: check your FXML file 'employee.fxml'.";
//        assert EmpDobText != null : "fx:id=\"EmpDobText\" was not injected: check your FXML file 'employee.fxml'.";
//        assert EmpContactText != null : "fx:id=\"EmpContactText\" was not injected: check your FXML file 'employee.fxml'.";
//        assert EmpJopText != null : "fx:id=\"EmpJopText\" was not injected: check your FXML file 'employee.fxml'.";
//        assert EmpIdText != null : "fx:id=\"EmpIdText\" was not injected: check your FXML file 'employee.fxml'.";
//
//
//
//
//    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        EmpIdText.setText(null);
        EmpNameText.setText(null);
        EmpNicText.setText(null);
        EmpDobText.setText(null);
        EmpAddressText.setText(null);
        EmpContactText.setText(null);
        EmpJopText.setText(null);
        searchText.setText(null);

        setCellValueFactory();
        getAll();

    }

    public void EmployeeOnMouseClick(MouseEvent mouseEvent) {

        TablePosition pos=EmployeeTbl.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<EmployeeTm,?>> columns=EmployeeTbl.getColumns();

        EmpIdText.setText(columns.get(0).getCellData(row).toString());
        EmpNameText.setText(columns.get(1).getCellData(row).toString());
        EmpNicText.setText(columns.get(2).getCellData(row).toString());
        EmpDobText.setText(columns.get(3).getCellData(row).toString());
        EmpAddressText.setText(columns.get(4).getCellData(row).toString());
        EmpContactText.setText(columns.get(5).getCellData(row).toString());
        EmpJopText.setText(columns.get(6).getCellData(row).toString());

    }

    public void searchTextOnKeyTyped(KeyEvent keyEvent) throws SQLException {
        String searchValue=searchText.getText().trim();
        ObservableList<EmployeeTm>obList=EmployeeModel.getAll();

        if (!searchValue.isEmpty()) {
            ObservableList<EmployeeTm> filteredData = obList.filtered(new Predicate<EmployeeTm>(){
                @Override
                public boolean test(EmployeeTm employeetm) {
                    return String.valueOf(employeetm.getEmpId()).toLowerCase().contains(searchValue.toLowerCase());        }
            });
            EmployeeTbl.setItems(filteredData);} else {
            EmployeeTbl.setItems(obList);
        }



    }
}
