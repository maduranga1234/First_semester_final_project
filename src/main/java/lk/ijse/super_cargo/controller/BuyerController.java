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
import java.util.regex.Pattern;

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
import lk.ijse.super_cargo.dto.Buyer;
import lk.ijse.super_cargo.dto.Employee;
import lk.ijse.super_cargo.dto.tm.BuyerTm;
import lk.ijse.super_cargo.dto.tm.EmployeeTm;
import lk.ijse.super_cargo.model.BuyerModel;
import lk.ijse.super_cargo.model.EmployeeModel;
import lk.ijse.super_cargo.model.UserModel;
import lk.ijse.super_cargo.util.AlertController;
import lk.ijse.super_cargo.util.ValidationController;

public class BuyerController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private JFXTextField UserNameBox;


    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorpaneHome;

    @FXML
    private JFXTextField BuyerIdText;

    @FXML
    private JFXTextField BuyerNameText;

    @FXML
    private JFXTextField CountryText;

    @FXML
    private JFXTextField EmailText;

    @FXML
    private JFXTextField NumberText;

    @FXML
    private JFXTextField UserNameText1;

    @FXML
    private JFXButton BuyerUpdateBtn;

    @FXML
    private JFXButton BuyerSaveBtn;

    @FXML
    private JFXButton BuyerDeleteBtn;

    @FXML
    private TableColumn<?, ?> BuyerIdCol;

    @FXML
    private TableColumn<?, ?> UserNameCol;

    @FXML
    private TableColumn<?, ?> BuyerNameCol;

    @FXML
    private TableColumn<?, ?> CountryCol;

    @FXML
    private TableColumn<?, ?> EmailCol;

    @FXML
    private TableColumn<?, ?> ContactNumberCol;

    @FXML
    private TableView<BuyerTm> BuyerTbl;

    @FXML
    private TextField searchText;

    @FXML
    private Button searchBtn;

    @FXML
    private JFXComboBox UserNameComboBox;



    @FXML
    void DeleteClick(ActionEvent event) throws SQLException {

        String buyerId=BuyerIdText.getText();
        boolean result = AlertController.okconfirmmessage("Are you sure you want to remove this Buyer?");
        if(result==true){

            try {
                boolean isDeleted = BuyerModel.delete(buyerId);
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


        getAll();

        BuyerIdText.setText("");
        UserNameComboBox.setValue("");
        BuyerNameText.setText("");
        CountryText.setText("");
        NumberText.setText("");
        UserNameBox.setText("");


    }

    @FXML

    void SaveClick(ActionEvent event) throws SQLException {

        System.out.println(UserNameComboBox.getValue());

        if (ValidationController.buyerIdCheck(BuyerIdText.getText())) {
            if (ValidationController.customerNameValidate(BuyerNameText.getText())) {
                if (ValidationController.customerNameValidate(CountryText.getText())) {
                    if (!UserNameComboBox.getSelectionModel().isEmpty()){
                        if (ValidationController.emailCheck(UserNameBox.getText())) {
                            if (ValidationController.BuyercontactCheck(NumberText.getText())) {
                                Buyer buyer = new Buyer();

                                buyer.setBuyerId(BuyerIdText.getText());
                                buyer.setUserName(String.valueOf(UserNameComboBox.getValue()));
                                buyer.setBuyerName(BuyerNameText.getText());
                                buyer.setCountry(CountryText.getText());
                                buyer.setContactNumber(NumberText.getText());
                                buyer.setEmail(UserNameBox.getText());


                                try {
                                    boolean isSaved = BuyerModel.Save(buyer);
                                    if (isSaved) {
                                        AlertController.confirmmessage("saved");
                                    }
                                } catch (SQLIntegrityConstraintViolationException throwables) {

                                    AlertController.errormessage("Duplicate Id");

                                } catch (Exception throwables) {

                                    AlertController.errormessage("Error");

                                }
                                getAll();


                                BuyerIdText.setText("");
                                UserNameComboBox.setValue("");
                                BuyerNameText.setText("");
                                CountryText.setText("");
                                NumberText.setText("");
                                UserNameBox.setText("");
                            } else {
                                AlertController.errormessage("Invalied Contact Number");

                            }

                        } else {
                            AlertController.errormessage("Invalied Email");
                        }
                }else{
                        AlertController.errormessage("Please Select User Name");
                    }

                }else{
                    AlertController.errormessage("Invalied Country");
                }
                } else {
                    AlertController.errormessage("Invalied Name");
                }
            } else {
                AlertController.errormessage("Invalied Id");

            }

        }



    @FXML
    void UpdateClick(ActionEvent event) throws SQLException {

        if (ValidationController.buyerIdCheck(BuyerIdText.getText())) {
            if (ValidationController.customerNameValidate(BuyerNameText.getText())) {
                if (ValidationController.customerNameValidate(CountryText.getText())) {
                    if (!UserNameComboBox.getSelectionModel().isEmpty()){
                        if (ValidationController.emailCheck(UserNameBox.getText())) {
                            if (ValidationController.BuyercontactCheck(NumberText.getText())) {
                    Buyer buyer = new Buyer();

                    buyer.setBuyerId(BuyerIdText.getText());
                    buyer.setUserName(String.valueOf(UserNameComboBox.getValue()));
                    buyer.setBuyerName(BuyerNameText.getText());
                    buyer.setCountry(CountryText.getText());
                    buyer.setContactNumber(NumberText.getText());
                    buyer.setEmail(UserNameBox.getText());

                    boolean result = AlertController.okconfirmmessage("Are you sure you want to Update this Buyer?");
                    if (result == true) {

                        try {
                            boolean isUpdates = BuyerModel.update(buyer);
                            if (isUpdates) {
                                AlertController.confirmmessage("Update Ok");

                            } else {

                                AlertController.errormessage("Error!!");
                            }
                        } catch (SQLException e) {
                            System.out.println(e);
                            AlertController.errormessage("Error");
                        }
                    }

                    setCellValueFactory();
                    getAll();

                    BuyerIdText.setText("");
                    UserNameComboBox.setValue("");
                    BuyerNameText.setText("");
                    CountryText.setText("");
                    NumberText.setText("");
                    UserNameBox.setText("");

                            } else {
                                AlertController.errormessage("Invalied Contact Number");
                            }

                        } else {
                            AlertController.errormessage("Invalied Email");
                        }
                    }else{
                        AlertController.errormessage("Please Select User Name");
                    }

                }else{
                    AlertController.errormessage("Invalied Country");
                }
            } else {
                AlertController.errormessage("Invalied Name");
            }
        } else {
            AlertController.errormessage("Invalied Id");

        }

    }

    public void BuyerIdSearchOnAction(ActionEvent event) {

        String buyerId=searchText.getText();

        try {
            Buyer buyer = BuyerModel.Search(buyerId);
            BuyerIdText.setText(buyer.getBuyerId());
            UserNameComboBox.setValue(buyer.getUserName());
            BuyerNameText.setText(buyer.getBuyerName());
            CountryText.setText(buyer.getCountry());
            NumberText.setText(buyer.getContactNumber());
            UserNameBox.setText(buyer.getEmail());

            ObservableList<BuyerTm> obList = FXCollections.observableArrayList();
        }catch (SQLException throwables){
            throwables.printStackTrace();
            AlertController.errormessage("Error");
        }



    }



    private void setCellValueFactory() {

        BuyerIdCol.setCellValueFactory(new PropertyValueFactory<>("buyerId"));
        UserNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        BuyerNameCol.setCellValueFactory(new PropertyValueFactory<>("buyerName"));
        CountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        ContactNumberCol.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        EmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

    }

    private void getAll() throws SQLException {
        try {

            ObservableList<BuyerTm> buyerData =BuyerModel.getAll();
            BuyerTbl.setItems(buyerData);

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert AnchorpaneHome != null : "fx:id=\"AnchorpaneHome\" was not injected: check your FXML file 'buyer.fxml'.";
        assert BuyerIdText != null : "fx:id=\"BuyerIdText\" was not injected: check your FXML file 'buyer.fxml'.";
        assert BuyerNameText != null : "fx:id=\"BuyerNameText\" was not injected: check your FXML file 'buyer.fxml'.";
        assert CountryText != null : "fx:id=\"CountryText\" was not injected: check your FXML file 'buyer.fxml'.";
        assert EmailText != null : "fx:id=\"EmailText\" was not injected: check your FXML file 'buyer.fxml'.";
        assert NumberText != null : "fx:id=\"NumberText\" was not injected: check your FXML file 'buyer.fxml'.";
        assert BuyerUpdateBtn != null : "fx:id=\"BuyerUpdateBtn\" was not injected: check your FXML file 'buyer.fxml'.";
        assert BuyerSaveBtn != null : "fx:id=\"BuyerSaveBtn\" was not injected: check your FXML file 'buyer.fxml'.";
        assert BuyerDeleteBtn != null : "fx:id=\"BuyerDeleteBtn\" was not injected: check your FXML file 'buyer.fxml'.";

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        LoadEmployeeId();
        try {
            getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


    public void BuyerOnMouseClick(MouseEvent mouseEvent) {

        TablePosition pos=BuyerTbl.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<BuyerTm,?>> columns=BuyerTbl.getColumns();

        BuyerIdText.setText(columns.get(0).getCellData(row).toString());
        UserNameComboBox.setValue(columns.get(1).getCellData(row).toString());
        BuyerNameText.setText(columns.get(2).getCellData(row).toString());
        CountryText.setText(columns.get(3).getCellData(row).toString());
        NumberText.setText(columns.get(4).getCellData(row).toString());
        UserNameBox.setText(columns.get(5).getCellData(row).toString());

    }

    @FXML
    void UserNameOnAction(ActionEvent event) {

    }

    public void searchTextOnKeyTyped(KeyEvent keyEvent) throws SQLException {

        String searchValue=searchText.getText().trim();
        ObservableList<BuyerTm>obList=BuyerModel.getAll();

        if (!searchValue.isEmpty()) {
            ObservableList<BuyerTm> filteredData = obList.filtered(new Predicate<BuyerTm>(){
                @Override
                public boolean test(BuyerTm buyerTm) {
                    return String.valueOf(buyerTm.getBuyerId()).toLowerCase().contains(searchValue.toLowerCase());        }
            });
            BuyerTbl.setItems(filteredData);} else {
            BuyerTbl.setItems(obList);
        }
    }

    private void LoadEmployeeId(){

        try{
            ObservableList<String>userNames= FXCollections.observableArrayList();
            List<String> names = UserModel.LoadUserNames();

            for(String name:names) {
                userNames.add(name);

            }
         UserNameComboBox.setItems(userNames);

        }catch (SQLException throwables){
            AlertController.errormessage("Something went Wrong");
        }
    }

}
