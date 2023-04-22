package lk.ijse.super_cargo.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.super_cargo.dto.Employee;
import lk.ijse.super_cargo.dto.Supplier;
import lk.ijse.super_cargo.dto.tm.EmployeeTm;
import lk.ijse.super_cargo.dto.tm.ItemTm;
import lk.ijse.super_cargo.dto.tm.SupplierTm;
import lk.ijse.super_cargo.model.EmployeeModel;
import lk.ijse.super_cargo.model.SupplierModel;
import lk.ijse.super_cargo.util.AlertController;
import lk.ijse.super_cargo.util.ButtonColourController;
import lk.ijse.super_cargo.util.ValidationController;
import lombok.SneakyThrows;

public class SupplierController  implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorpaneHome;

    @FXML
    private JFXTextField SupIdText;

    @FXML
    private JFXTextField SupNameText;

    @FXML
    private JFXTextField SupAddressText1;

    @FXML
    private JFXTextField SuoContactText2;

    @FXML
    private JFXTextField SupEmailText3;

    @FXML
    private JFXButton SupUpdateBtn;

    @FXML
    private JFXButton SupSaveBtn;

    @FXML
    private JFXButton SupDeleteBtn;

    @FXML
    private TextField searchText;

    @FXML
    private Button searchBtn;

    @FXML
    private TableView<SupplierTm> SupplierTbl;

    @FXML
    private TableColumn<?, ?> SupIdColm;

    @FXML
    private TableColumn<?, ?> SupNameColm;

    @FXML
    private TableColumn<?, ?> SupAddressCol;

    @FXML
    private TableColumn<?, ?> SupEmailCol;

    @FXML
    private TableColumn<?, ?> SupContactCol;

    @FXML
    void SupDeleteClick(ActionEvent event) throws SQLException {

        String supplierId=SupIdText.getText();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to remove this Supplier?");
        if(result==true){

            try {
                boolean isDeleted = SupplierModel.delete(supplierId);
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


        setCellValueFactory();
        getAll();

        SupIdText.setText("");
        SupNameText.setText("");
        SupAddressText1.setText("");
        SuoContactText2.setText("");
        SupEmailText3.setText("");
        searchText.setText("");

    }

    @FXML
    void SupSaveClick(ActionEvent event) throws SQLException {

        if (ValidationController.supplierIdCheck(SupIdText.getText())) {
            if (ValidationController.customerNameValidate(SupNameText.getText())) {
                if (ValidationController.customerNameValidate(SupAddressText1.getText())) {
                    if (ValidationController.emailCheck(SupEmailText3.getText())) {
                        if (ValidationController.BuyercontactCheck(SuoContactText2.getText())) {

                            Supplier supplier = new Supplier();

                            supplier.setSupplierId(SupIdText.getText());
                            supplier.setSupplierName(SupNameText.getText());
                            supplier.setAddress(SupAddressText1.getText());
                            supplier.setContact(SuoContactText2.getText());
                            supplier.setEmail(SupEmailText3.getText());

                            try {
                                boolean isSave = SupplierModel.Save(supplier);
                                if (isSave) {
                                    AlertController.confirmmessage("Save Successful");
                                }

                            } catch (SQLIntegrityConstraintViolationException throwables) {

                                AlertController.errormessage("Duplicate Id");

                            } catch (Exception throwables) {

                                AlertController.errormessage("Error");

                            }
                            getAll();

                            SupIdText.setText("");
                            SupNameText.setText("");
                            SupAddressText1.setText("");
                            SuoContactText2.setText("");
                            SupEmailText3.setText("");
                            searchText.setText("");

                        }else{
                            AlertController.errormessage("Invalied Contact Number");
                        }
                    }else{
                        AlertController.errormessage("Invalied Email");
                    }
                }else{
                    AlertController.errormessage("Invalied Address");
                }
            }else{
                AlertController.errormessage("Invalied Supplier Name");
            }
        }else{
            AlertController.errormessage("Invalied Id");
        }

    }

    @FXML
    void SupUpdateClick(ActionEvent event) throws SQLException {


        if (ValidationController.supplierIdCheck(SupIdText.getText())) {
            if (ValidationController.customerNameValidate(SupNameText.getText())) {
                if (ValidationController.customerNameValidate(SupAddressText1.getText())) {
                    if (ValidationController.emailCheck(SupEmailText3.getText())) {
                        if (ValidationController.BuyercontactCheck(SuoContactText2.getText())) {

                            Supplier supplier = new Supplier();


                supplier.setSupplierId(SupIdText.getText());
                supplier.setSupplierName(SupNameText.getText());
                supplier.setAddress(SupAddressText1.getText());
                supplier.setContact(SuoContactText2.getText());
                supplier.setEmail(SupEmailText3.getText());


                boolean result = AlertController.okconfirmmessage("Are you sure you want to Update this Supplier?");
                if (result == true) {

                    try {
                        boolean isUpdates = SupplierModel.Update(supplier);
                        if (isUpdates) {
                            AlertController.confirmmessage("Update Successful");

                        } else {

                            AlertController.errormessage("Somethink went wrong");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        AlertController.errormessage("Somethink went wrong");
                    }

                }


                setCellValueFactory();
                getAll();
                SupIdText.setText("");
                SupNameText.setText("");
                SupAddressText1.setText("");
                SuoContactText2.setText("");
                SupEmailText3.setText("");

                        }else{
                            AlertController.errormessage("Invalied Contact Number");
                        }
                    }else{
                        AlertController.errormessage("Invalied Email");
                    }
                }else{
                    AlertController.errormessage("Invalied Address");
                }
            }else{
                AlertController.errormessage("Invalied Supplier Name");
            }
        }else{
            AlertController.errormessage("Invalied Id");
        }


    }

    @FXML
    void SearchClick(ActionEvent event) throws SQLException {

        String supplierId=searchText.getText();

        try{
        Supplier supplier=SupplierModel.Search(supplierId);
        SupIdText.setText(supplier.getSupplierId());
        SupNameText.setText(supplier.getSupplierName());
        SupAddressText1.setText(supplier.getAddress());
        SuoContactText2.setText(supplier.getContact());
        SupEmailText3.setText(supplier.getEmail());

        String searchValue=SupIdText.getText().trim();
        ObservableList<SupplierTm> obList=FXCollections.observableArrayList();




    }catch (SQLException throwables){
        throwables.printStackTrace();
            AlertController.errormessage("Somethink went wrong");
    }


}


    private void setCellValueFactory(){

      //  IdColum.setCellValueFactory(new PropertyValueFactory<>("empId"));

        SupIdColm.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        SupNameColm.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        SupAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        SupContactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        SupEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

    }



    private void getAll() throws SQLException {

        try {
            ObservableList<SupplierTm>supplierData=SupplierModel.getAll();
            SupplierTbl.setItems(supplierData);


        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert AnchorpaneHome != null : "fx:id=\"AnchorpaneHome\" was not injected: check your FXML file 'supplier.fxml'.";
        assert SupIdText != null : "fx:id=\"SupIdText\" was not injected: check your FXML file 'supplier.fxml'.";
        assert SupNameText != null : "fx:id=\"SupNameText\" was not injected: check your FXML file 'supplier.fxml'.";
        assert SupAddressText1 != null : "fx:id=\"SupAddressText1\" was not injected: check your FXML file 'supplier.fxml'.";
        assert SuoContactText2 != null : "fx:id=\"SuoContactText2\" was not injected: check your FXML file 'supplier.fxml'.";
        assert SupEmailText3 != null : "fx:id=\"SupEmailText3\" was not injected: check your FXML file 'supplier.fxml'.";
        assert SupUpdateBtn != null : "fx:id=\"SupUpdateBtn\" was not injected: check your FXML file 'supplier.fxml'.";
        assert SupSaveBtn != null : "fx:id=\"SupSaveBtn\" was not injected: check your FXML file 'supplier.fxml'.";
        assert SupDeleteBtn != null : "fx:id=\"SupDeleteBtn\" was not injected: check your FXML file 'supplier.fxml'.";

    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
    }

    public void OnMouseClicked(MouseEvent mouseEvent) {

        TablePosition pos=SupplierTbl.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<SupplierTm,?>> columns=SupplierTbl.getColumns();



        SupIdText.setText(columns.get(0).getCellData(row).toString());
        SupNameText.setText(columns.get(1).getCellData(row).toString());
        SupAddressText1.setText(columns.get(2).getCellData(row).toString());
        SuoContactText2.setText(columns.get(3).getCellData(row).toString());
        SupEmailText3.setText(columns.get(4).getCellData(row).toString());


    }

    public void supplierIdTextSearch(KeyEvent keyEvent) throws SQLException {

        String searchValue=searchText.getText().trim();
        ObservableList<SupplierTm>obList=SupplierModel.getAll();

        if (!searchValue.isEmpty()) {
            ObservableList<SupplierTm> filteredData = obList.filtered(new Predicate<SupplierTm>(){
                @Override
                public boolean test(SupplierTm suppliertm) {
                    return String.valueOf(suppliertm.getSupplierId()).toLowerCase().contains(searchValue.toLowerCase());        }
            });
            SupplierTbl.setItems(filteredData);} else {
            SupplierTbl.setItems(obList);
        }


    }

    public void BackBtnClick(ActionEvent event) throws IOException {

        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/SupplierItemDetail.fxml"));
        AnchorpaneHome.getChildren().clear();
        AnchorpaneHome.getChildren().add(load);


    }
}
