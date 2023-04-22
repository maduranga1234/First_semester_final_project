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
import lk.ijse.super_cargo.dto.Supplier;
import lk.ijse.super_cargo.dto.tm.EmployeeTm;
import lk.ijse.super_cargo.dto.tm.ItemTm;
import lk.ijse.super_cargo.dto.tm.SupplierTm;
import lk.ijse.super_cargo.model.EmployeeModel;
import lk.ijse.super_cargo.model.ItemModel;
import lk.ijse.super_cargo.model.SupplierModel;
import lk.ijse.super_cargo.util.AlertController;
import lk.ijse.super_cargo.util.ValidationController;
import lombok.SneakyThrows;

import javax.mail.FetchProfile;

public class ItemController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorpaneHome;

    @FXML
    private JFXTextField ItemIdText;

    @FXML
    private JFXTextField ItemNameText;

    @FXML
    private JFXTextField WeightText;

    @FXML
    private JFXTextField PriceText;

    @FXML
    private JFXComboBox QualityBox;


    @FXML
    private JFXButton ItemUpdateBtn;

    @FXML
    private JFXButton ItemSaveBtn;

    @FXML
    private JFXButton ItemDeleteBtn;

    @FXML
    private TableView<ItemTm> ItemTbl;

    @FXML
    private TableColumn<?, ?> ItemIdColm;

    @FXML
    private TableColumn<?, ?> Item6NameColm;

    @FXML
    private TableColumn<?, ?> WeightCol;

    @FXML
    private TableColumn<?, ?> PriceCol;

    @FXML
    private TableColumn<?, ?> QualityCol;

    @FXML
    private TextField searchText;

    @FXML
    private Button searchBtn;

    @FXML
    void ItemDeleteClick(ActionEvent event) throws SQLException {

        String itemId=ItemIdText.getText();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to remove this Item?");
        if(result==true){

            try {
                boolean isDeleted = ItemModel.delete(itemId);
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

        ItemIdText.setText("");
        ItemNameText.setText("");
        WeightText.setText("");
        PriceText.setText("");
        QualityBox.setValue("");
        searchText.setText("");

    }

    @FXML
    void ItemSaveClick(ActionEvent event) throws SQLException {
        System.out.println(QualityBox.getValue());

        if (ValidationController.itemIdCheck(ItemIdText.getText())) {
            if (ValidationController.customerNameValidate(ItemNameText.getText())) {
                if (ValidationController.salary(WeightText.getText())) {
                    if (ValidationController.salary(PriceText.getText())) {
                        if (!QualityBox.getSelectionModel().isEmpty()) {


                            Item item = new Item();

                            item.setItemId(ItemIdText.getText());
                            item.setItemName(ItemNameText.getText());
                            item.setWeight(Double.parseDouble(WeightText.getText()));
                            item.setUnitPrice(Double.parseDouble(PriceText.getText()));
                            item.setQuality(String.valueOf(QualityBox.getValue()));


                            try {
                                boolean isSave = ItemModel.Save(item);
                                if (isSave) {
                                    AlertController.confirmmessage("Save successFully");

                                }

                            } catch (SQLIntegrityConstraintViolationException throwables) {

                                AlertController.errormessage("Duplicate Id");

                            } catch (Exception throwables) {

                                AlertController.errormessage("Error");

                            }

                            getAll();

                            ItemIdText.setText("");
                            ItemNameText.setText("");
                            WeightText.setText("");
                            PriceText.setText("");
                            QualityBox.setValue("");

                        }else{
                            AlertController.errormessage("Please Select Quality");

                        }

                    }else{
                        AlertController.errormessage("invalied Price");
                    }
                }else{
                    AlertController.errormessage("invalied Weight");
                }
            }else{
                AlertController.errormessage("invalied Item Name");
            }
        } else {
            AlertController.errormessage("invalied Id");
        }
    }


    @FXML
    void ItemUpdateClick(ActionEvent event) throws SQLException {

        if (ValidationController.itemIdCheck(ItemIdText.getText())) {
            if (ValidationController.customerNameValidate(ItemNameText.getText())) {
                if (ValidationController.salary(WeightText.getText())) {
                    if (ValidationController.salary(PriceText.getText())) {
                        if (!QualityBox.getSelectionModel().isEmpty()) {


                            Item item = new Item();

            item.setItemId(ItemIdText.getText());
            item.setItemName(ItemNameText.getText());
            item.setWeight(Double.parseDouble(WeightText.getText()));
            item.setUnitPrice(Double.parseDouble(PriceText.getText()));
            item.setQuality(String.valueOf(QualityBox.getValue()));


            boolean result = AlertController.okconfirmmessage("Are you sure you want to Update  this Item?");
            if (result == true) {


                try {
                    boolean isUpdates = ItemModel.Update(item);
                    if (isUpdates) {
                        AlertController.confirmmessage("Update successFully");


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


            ItemIdText.setText("");
            ItemNameText.setText("");
            WeightText.setText("");
            PriceText.setText("");
            QualityBox.setValue("");
            searchText.setText("");

                        }else{
                            AlertController.errormessage("Please Select Quality");

                        }

                    }else{
                        AlertController.errormessage("invalied Price");
                    }
                }else{
                    AlertController.errormessage("invalied Weight");
                }
            }else{
                AlertController.errormessage("invalied Item Name");
            }
        } else {
            AlertController.errormessage("invalied Id");
        }
    }

    @FXML
    void SearchClick(ActionEvent event) {

        String itemId=searchText.getText();

        try{
            Item item= ItemModel.Search(itemId);
            ItemIdText.setText(item.getItemId());
            ItemNameText.setText(item.getItemName());
            WeightText.setText(String.valueOf(item.getWeight()));
            PriceText.setText(String.valueOf(item.getUnitPrice()));
            QualityBox.setValue(item.getQuality());

            String searchValue=ItemIdText.getText().trim();
            ObservableList<ItemTm> obList=FXCollections.observableArrayList();

        }catch (SQLException throwables){
            throwables.printStackTrace();
            AlertController.errormessage("Somethink went wrong");
        }

    }
    @FXML
    void QualityBoxOnAction(ActionEvent event) {

    }

    private void setCellValueFactory(){

        ItemIdColm.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        Item6NameColm.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        WeightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        PriceCol.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        QualityCol.setCellValueFactory(new PropertyValueFactory<>("quality"));

    }




    private void getAll() throws SQLException {

        try {
          ObservableList<ItemTm>itemData=ItemModel.getAll();
          ItemTbl.setItems(itemData);


        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }


//    @FXML
//    void initialize() {
//        assert AnchorpaneHome != null : "fx:id=\"AnchorpaneHome\" was not injected: check your FXML file 'item.fxml'.";
//        assert ItemIdText != null : "fx:id=\"ItemIdText\" was not injected: check your FXML file 'item.fxml'.";
//        assert ItemNameText != null : "fx:id=\"ItemNameText\" was not injected: check your FXML file 'item.fxml'.";
//        assert WeightText != null : "fx:id=\"WeightText\" was not injected: check your FXML file 'item.fxml'.";
//        assert PriceText != null : "fx:id=\"PriceText\" was not injected: check your FXML file 'item.fxml'.";
//        assert QualityBox != null : "fx:id=\"QualityBox\" was not injected: check your FXML file 'item.fxml'.";
//        assert ItemUpdateBtn != null : "fx:id=\"ItemUpdateBtn\" was not injected: check your FXML file 'item.fxml'.";
//        assert ItemSaveBtn != null : "fx:id=\"ItemSaveBtn\" was not injected: check your FXML file 'item.fxml'.";
//        assert ItemDeleteBtn != null : "fx:id=\"ItemDeleteBtn\" was not injected: check your FXML file 'item.fxml'.";
//        assert ItemTbl != null : "fx:id=\"ItemTbl\" was not injected: check your FXML file 'item.fxml'.";
//        assert ItemIdColm != null : "fx:id=\"ItemIdColm\" was not injected: check your FXML file 'item.fxml'.";
//        assert Item6NameColm != null : "fx:id=\"Item6NameColm\" was not injected: check your FXML file 'item.fxml'.";
//        assert WeightCol != null : "fx:id=\"WeightCol\" was not injected: check your FXML file 'item.fxml'.";
//        assert PriceCol != null : "fx:id=\"PriceCol\" was not injected: check your FXML file 'item.fxml'.";
//        assert QualityCol != null : "fx:id=\"QualityCol\" was not injected: check your FXML file 'item.fxml'.";
//        assert searchText != null : "fx:id=\"searchText\" was not injected: check your FXML file 'item.fxml'.";
//        assert searchBtn != null : "fx:id=\"searchBtn\" was not injected: check your FXML file 'item.fxml'.";
//        QualityBox.getItems().addAll("Good", "Normal","Not In Good Condition");
//    }



    public void initialize(URL url, ResourceBundle resourceBundle) {
       setCellValueFactory();
        try {
            getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        QualityBox.getItems().addAll("Good", "Normal","Bad");
    }

    public void ItemOnMouseClick(MouseEvent mouseEvent) {

        TablePosition pos=ItemTbl.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<ItemTm,?>> columns=ItemTbl.getColumns();


        ItemIdText.setText(columns.get(0).getCellData(row).toString());
        ItemNameText.setText(columns.get(1).getCellData(row).toString());
        WeightText.setText(columns.get(2).getCellData(row).toString());
        PriceText.setText(columns.get(3).getCellData(row).toString());
        QualityBox.setValue(columns.get(4).getCellData(row).toString());


    }

    public void ItemIdTextSearch(KeyEvent keyEvent) throws SQLException {

        String searchValue=searchText.getText().trim();
        ObservableList<ItemTm>obList= ItemModel.getAll();

        if (!searchValue.isEmpty()) {
            ObservableList<ItemTm> filteredData = obList.filtered(new Predicate<ItemTm>(){
                @Override
                public boolean test(ItemTm itemTm) {
                    return String.valueOf(itemTm.getItemId()).toLowerCase().contains(searchValue.toLowerCase());        }
            });
            ItemTbl.setItems(filteredData);} else {
            ItemTbl.setItems(obList);
        }
    }
}
