package lk.ijse.super_cargo.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.super_cargo.db.DBConnection;
import lk.ijse.super_cargo.dto.Item;
import lk.ijse.super_cargo.dto.Order;
import lk.ijse.super_cargo.dto.Supplier;
import lk.ijse.super_cargo.dto.SupplierOrderDetail;
import lk.ijse.super_cargo.dto.tm.OrderTm;
import lk.ijse.super_cargo.dto.tm.SupplierDetailTm;
import lk.ijse.super_cargo.model.*;
import lk.ijse.super_cargo.util.AlertController;
import lk.ijse.super_cargo.util.ButtonColourController;
import lk.ijse.super_cargo.util.ValidationController;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class SupplierItemDetailController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorpaneHome;

    @FXML
    private AnchorPane AddSUpplierLoad;

    @FXML
    private JFXComboBox<String> SupplierIdBox;

    @FXML
    private JFXComboBox<String> ItemCodeBox;

    @FXML
    private JFXComboBox paymentMethordBox;

    @FXML
    private Label ItemNameLabel;

    @FXML
    private Label lblQtyOnHands;

    @FXML
    private Label lblsupplyloadid;

    @FXML
    private Label lblsupplierloadid;

    @FXML
    private Label lbldate;

    @FXML
    private Label lblTime;

    @FXML
    private JFXTextField ItemContityText;

    @FXML
    private Button addToLoad;

    @FXML
    private TableView<SupplierDetailTm> tblSupplierItem;

    @FXML
    private TableColumn<?, ?> ItemCodeCol;

    @FXML
    private TableColumn<?, ?> ItemNameCol;

    @FXML
    private TableColumn<?, ?> QuantityOnHandCol;

    @FXML
    private TableColumn<?, ?> ActionCol;

    @FXML
    private Label SupplierNameLbl;

    @FXML
    private JFXTextField PriceText;

    @FXML
    private Button AddSupplierLoad;

    @FXML
    private TableColumn<?, ?> Quantity;

    @FXML
    private Hyperlink AddNewSupplierHyperLink;
    @FXML
    private TableColumn<?, ?> PaymentCol;



    private void LoadSupplierId(){
        try{
            ObservableList<String> supplierIds= FXCollections.observableArrayList();
            List<String> ids = SupplierModel.LoadSupplierIds();

            for(String id:ids) {
                supplierIds.add(id);

            }
            SupplierIdBox.setItems(supplierIds);

        }catch (SQLException throwables){
            AlertController.errormessage("Something went Wrong");
        }
    }

    private void LoadItemCode(){

        try{
            ObservableList<String>itemIds= FXCollections.observableArrayList();
            List<String> ids = ItemModel.LoadItemCodes();

            for(String id:ids) {
                itemIds.add(id);

            }
            ItemCodeBox.setItems(itemIds);

        }catch (SQLException throwables){
            AlertController.errormessage("Something went Wrong");
        }
    }

    @FXML
    void AddSupplierLoadClick(MouseEvent event) {

    }

    @FXML
    void AddSupplierLoadOnAction(ActionEvent event) {




        String supplierLoadId=lblsupplierloadid.getText();
        String supplierId=SupplierIdBox.getValue();
        String loadPayment=PriceText.getText();

        List<SupplierOrderDetail> supplierOrderList = new ArrayList<>();

        for(int i=0; i<tblSupplierItem.getItems().size();i++){
            SupplierDetailTm supplierOrderTm=obList.get(i);

//            Order order=new Order(
//                    orderTm.getItemCode(),
//                    orderTm.getQuantity()
//            );

            SupplierOrderDetail supplierOrderDetail=new SupplierOrderDetail(

                    supplierOrderTm.getItemcode(),

                    Double.parseDouble(  supplierOrderTm.getQty())

            );


            supplierOrderList.add(supplierOrderDetail);
        }
        boolean isSupplierOrders=false;

        try {
            isSupplierOrders = SupplierItemDetailModel.SupplierLoad(supplierLoadId, supplierId,loadPayment,supplierOrderList);

            if (isSupplierOrders) {

                AlertController.confirmmessage("Add Supplier Load");
                InputStream resource = this.getClass().getResourceAsStream("/report/SupplierLoad.jrxml");
                try {
                    JasperReport jasperReport = JasperCompileManager.compileReport(resource);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
                    JasperViewer.viewReport(jasperPrint, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                    AddSupplierLoad.setDisable(true);
                tblSupplierItem.getItems().clear();

                PriceText.setText("");
                ItemCodeBox.setValue(null);
                SupplierIdBox.setValue(null);
                paymentMethordBox.setValue(null);

                generateNextLoadId();
            } else {
                AlertController.errormessage("Not Added Supplier Load");
            }
        }catch (SQLException e){
            AlertController.errormessage("Sql Error");
        }

    }

    @FXML
    private ObservableList<SupplierDetailTm> obList = FXCollections.observableArrayList();

    private void setRemoveBtnOnAction(Button btnRemove) {

        btnRemove.setOnAction((e) -> {

            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to remove?", yes, no).showAndWait();
            if (result.orElse(no) == yes) {
                int index = tblSupplierItem.getSelectionModel().getSelectedIndex();
                obList.remove(index);
                if(tblSupplierItem.getItems().isEmpty()) {
                    AddSupplierLoad.setDisable(true);
                }

                tblSupplierItem.refresh();
            }
        });
    }

    @FXML
    void AddToLoadClick(ActionEvent event) {

        if(!SupplierIdBox.getSelectionModel().isEmpty()) {
            if (!ItemCodeBox.getSelectionModel().isEmpty()) {
                if (!paymentMethordBox.getSelectionModel().isEmpty()) {
                    if (ValidationController.salary(ItemContityText.getText())) {
                        if (ValidationController.salary(PriceText.getText())) {





                            String itemCode = ItemCodeBox.getValue();
                            String itamName = ItemNameLabel.getText();
                            String qty = ItemContityText.getText();
                            double payment= Double.parseDouble(PriceText.getText());
                            Button btnremove = new Button("Remove");
                            btnremove.setCursor(Cursor.HAND);

                            setRemoveBtnOnAction(btnremove);

                            if (!obList.isEmpty()) {

                                for (int i = 0; i < tblSupplierItem.getItems().size(); i++) {

                                    if (ItemCodeCol.getCellData(i).equals(itemCode)) {
                                        qty += (double) Quantity.getCellData(i);

                                        obList.get(i).setQty(qty);
                                        tblSupplierItem.refresh();
                                        return;
                                    }
                                }
                            }
                            SupplierDetailTm tm = new SupplierDetailTm(itemCode, itamName, qty,payment, btnremove);
                            obList.add(tm);
                            tblSupplierItem.setItems(obList);


                            AddSupplierLoad.setDisable(false);
                            ItemContityText.setText("");

                        }else{
                            AlertController.errormessage("Invalied  Payment");
                        }
                    }else{
                        AlertController.errormessage("Invalied  Contity");
                    }
                }else{
                    AlertController.errormessage("Please Select Payment Methord");

                }
            }else{
                AlertController.errormessage("Please Select Item Code");
            }
        }else{
            AlertController.errormessage("Please Select Supplier Id");
        }
    }

    @FXML
    void ItemCodeLodeOnAction(ActionEvent event) {
        String id= String.valueOf(ItemCodeBox.getValue());
        try {
            Item item = ItemModel.searchByItemCode(id);
            if (item != null) {
                ItemNameLabel.setText(item.getItemName());
                lblQtyOnHands.setText(String.valueOf(item.getWeight()));
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }

    }

    @FXML
    void PaymentMethordBoxOnAction(ActionEvent event) {

    }

    @FXML
    void SpplierCodeLodeOnAction(ActionEvent event) {
        String id= String.valueOf(SupplierIdBox.getValue());
        try {
            Supplier supplier = SupplierModel.searchBySupplierId(id);
            if (supplier != null) {
                SupplierNameLbl.setText(supplier.getSupplierName());
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    private void generateNextLoadId() throws SQLException {
        try {
            String id = SupplierItemDetailModel.getNextLoadId();
            lblsupplierloadid.setText(id);
        }catch (Exception e){
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR,"Exception").show();
        }
    }

    private void setCellValueFactory(){

        ItemCodeCol.setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        ItemNameCol.setCellValueFactory(new PropertyValueFactory<>("itemname"));
        Quantity.setCellValueFactory(new PropertyValueFactory<>("qty"));
        PaymentCol.setCellValueFactory(new PropertyValueFactory<>("payment"));
        ActionCol.setCellValueFactory(new PropertyValueFactory<>("removebtn"));

    }

    @FXML
    void initialize() throws SQLException {

        setCellValueFactory();
        LoadSupplierId();
        LoadItemCode();

        paymentMethordBox.getItems().addAll("Cash","Card");

        generateNextLoadId();

        assert AddSUpplierLoad != null : "fx:id=\"AddSUpplierLoad\" was not injected: check your FXML file 'SupplierItemDetail.fxml'.";
        assert SupplierIdBox != null : "fx:id=\"SupplierIdBox\" was not injected: check your FXML file 'SupplierItemDetail.fxml'.";
        assert ItemCodeBox != null : "fx:id=\"ItemCodeBox\" was not injected: check your FXML file 'SupplierItemDetail.fxml'.";
        assert paymentMethordBox != null : "fx:id=\"paymentMethordBox\" was not injected: check your FXML file 'SupplierItemDetail.fxml'.";
        assert ItemNameLabel != null : "fx:id=\"ItemNameLabel\" was not injected: check your FXML file 'SupplierItemDetail.fxml'.";
        assert lblQtyOnHands != null : "fx:id=\"lblQtyOnHands\" was not injected: check your FXML file 'SupplierItemDetail.fxml'.";
        assert lblsupplierloadid != null : "fx:id=\"lblsupplierloadid\" was not injected: check your FXML file 'SupplierItemDetail.fxml'.";
        assert lbldate != null : "fx:id=\"lbldate\" was not injected: check your FXML file 'SupplierItemDetail.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'SupplierItemDetail.fxml'.";
        assert ItemContityText != null : "fx:id=\"ItemContityText\" was not injected: check your FXML file 'SupplierItemDetail.fxml'.";
        assert addToLoad != null : "fx:id=\"addToLoad\" was not injected: check your FXML file 'SupplierItemDetail.fxml'.";
        assert tblSupplierItem != null : "fx:id=\"tblSupplierItem\" was not injected: check your FXML file 'SupplierItemDetail.fxml'.";
        assert ItemCodeCol != null : "fx:id=\"ItemCodeCol\" was not injected: check your FXML file 'SupplierItemDetail.fxml'.";
        assert ItemNameCol != null : "fx:id=\"ItemNameCol\" was not injected: check your FXML file 'SupplierItemDetail.fxml'.";
        assert QuantityOnHandCol != null : "fx:id=\"QuantityOnHandCol\" was not injected: check your FXML file 'SupplierItemDetail.fxml'.";
        assert ActionCol != null : "fx:id=\"ActionCol\" was not injected: check your FXML file 'SupplierItemDetail.fxml'.";
        assert SupplierNameLbl != null : "fx:id=\"SupplierNameLbl\" was not injected: check your FXML file 'SupplierItemDetail.fxml'.";
        assert PriceText != null : "fx:id=\"PriceText\" was not injected: check your FXML file 'SupplierItemDetail.fxml'.";
        assert AddSupplierLoad != null : "fx:id=\"AddSupplierLoad\" was not injected: check your FXML file 'SupplierItemDetail.fxml'.";

        DashBordController.Timenow(lblTime,lbldate);
    }

    public void AddNewSupplierClick(ActionEvent event) throws IOException {

        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/supplier.fxml"));
        AnchorpaneHome.getChildren().clear();
        AnchorpaneHome.getChildren().add(load);


    }
}
