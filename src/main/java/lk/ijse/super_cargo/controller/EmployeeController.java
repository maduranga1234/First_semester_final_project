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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.super_cargo.dto.Employee;
import lk.ijse.super_cargo.dto.tm.EmployeeTm;
import lk.ijse.super_cargo.model.EmployeeModel;
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
    void DeleteClick(ActionEvent event) throws SQLException {

        String empId=EmpIdText.getText();

     try {
            boolean isDeleted = EmployeeModel.delete(empId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Delete successFully").show();

            } else {
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }
        }catch (SQLException throwables){
         throwables.printStackTrace();
         new Alert(Alert.AlertType.ERROR,"Error1").show();

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
                new Alert(Alert.AlertType.CONFIRMATION,"saved").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();

        }
        getAll();


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


        try {
            boolean isUpdates=EmployeeModel.update(employee);
            if(isUpdates){
                new Alert(Alert.AlertType.CONFIRMATION,"Comform").show();

            }
            else{

                new Alert(Alert.AlertType.ERROR,"Error").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"ErrorA");
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
           ObservableList<EmployeeTm> observableList = FXCollections.observableArrayList();
           List<Employee> allData = EmployeeModel.getAll();

           for (Employee employee : allData) {
               observableList.add(new EmployeeTm(
                       employee.getEmpId(),
                       employee.getEmpName(),
                       employee.getEmpNic(),
                       employee.getEmpDob(),
                       employee.getEmpAddress(),
                       employee.getEmpContact(),
                       employee.getEmpJob()
               ));
           }
           EmployeeTbl.setItems(observableList);

       }catch (SQLException throwables){
           throwables.printStackTrace();
       }
   }

    @FXML
    void empIdSeachOnAction(ActionEvent event) {

        String empId=EmpIdText.getText();

        try {
            Employee employee = EmployeeModel.Search(empId);
            EmpIdText.setText(employee.getEmpId());
            EmpNameText.setText(employee.getEmpName());
            EmpAddressText.setText(employee.getEmpAddress());
            EmpNicText.setText(employee.getEmpNic());
            EmpDobText.setText(employee.getEmpDob());
            EmpContactText.setText(employee.getEmpContact());
            EmpJopText.setText(employee.getEmpJob());

            String searchValue = EmpIdText.getText().trim();
            ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

            List<Employee> allData = EmployeeModel.getAll();

            for (Employee employe : allData) {
                obList.add(new EmployeeTm(
                        employe.getEmpId(),
                        employe.getEmpName(),
                        employe.getEmpNic(),
                        employe.getEmpDob(),
                        employe.getEmpAddress(),
                        employe.getEmpContact(),
                        employe.getEmpJob()
                ));
            }
            if (!searchValue.isEmpty()) {
                ObservableList<EmployeeTm> filteredData = obList.filtered(new Predicate<EmployeeTm>(){
                    @Override
                    public boolean test(EmployeeTm employeetm) {
                        return String.valueOf(employeetm.getEmpId()).toLowerCase().contains(searchValue.toLowerCase());        }
                });
                EmployeeTbl.setItems(filteredData);} else {
                EmployeeTbl.setItems(obList);
            }

        }catch (SQLException throwables){
            throwables.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error");
        }


    }

    @FXML
    void initialize() {
        assert AnchorpaneHome != null : "fx:id=\"AnchorpaneHome\" was not injected: check your FXML file 'employee.fxml'.";
        assert EmpUpdateBtn != null : "fx:id=\"EmpUpdateBtn\" was not injected: check your FXML file 'employee.fxml'.";
        assert EmpSaveBtn != null : "fx:id=\"EmpSaveBtn\" was not injected: check your FXML file 'employee.fxml'.";
        assert EmpDeleteBtn != null : "fx:id=\"EmpDeleteBtn\" was not injected: check your FXML file 'employee.fxml'.";
        assert EmpNameText != null : "fx:id=\"EmpNameText\" was not injected: check your FXML file 'employee.fxml'.";
        assert EmpAddressText != null : "fx:id=\"EmpAddressText\" was not injected: check your FXML file 'employee.fxml'.";
        assert EmpNicText != null : "fx:id=\"EmpNicText\" was not injected: check your FXML file 'employee.fxml'.";
        assert EmpDobText != null : "fx:id=\"EmpDobText\" was not injected: check your FXML file 'employee.fxml'.";
        assert EmpContactText != null : "fx:id=\"EmpContactText\" was not injected: check your FXML file 'employee.fxml'.";
        assert EmpJopText != null : "fx:id=\"EmpJopText\" was not injected: check your FXML file 'employee.fxml'.";
        assert EmpIdText != null : "fx:id=\"EmpIdText\" was not injected: check your FXML file 'employee.fxml'.";

    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setCellValueFactory();
        getAll();

    }
}
