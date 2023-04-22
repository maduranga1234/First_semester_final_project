package lk.ijse.super_cargo.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.super_cargo.db.DBConnection;
import lk.ijse.super_cargo.dto.Buyer;
import lk.ijse.super_cargo.dto.Item;
import lk.ijse.super_cargo.dto.Order;
import lk.ijse.super_cargo.dto.tm.OrderTm;
import lk.ijse.super_cargo.model.BuyerModel;
import lk.ijse.super_cargo.model.ItemModel;
import lk.ijse.super_cargo.model.OrderModel;
import lk.ijse.super_cargo.util.AlertController;
import lk.ijse.super_cargo.util.ValidationController;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class OrdersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorpaneHome;

    @FXML
    private JFXComboBox<String> BuyerIdBox;

    @FXML
    private JFXComboBox<String> ItemCodeBox;

    @FXML
    private Label BuyerNameLabel;

    @FXML
    private Label ItemNameLabel;

    @FXML
    private Label UnitPriceLabel;

    @FXML
    private Label QtyOnHandsLabel;

    @FXML
    private Label OrderIdLabel;

    @FXML
    private Label OrderDateLabel;

    @FXML
    private Label OrderTimeLabel;

    @FXML
    private JFXTextField ItemContityText;

    @FXML
    private JFXTextField PaidAmountText;


    @FXML
    private Label BalanceLbl;


    @FXML
    private Label TotalPaymentLabel;

    @FXML
    private TableView<OrderTm> OrderTbl;

    @FXML
    private TableColumn<?, ?> ItemCodeCol;

    @FXML
    private TableColumn<?, ?> ItemNameCol;

    @FXML
    private TableColumn<?, ?> UnitPriceCol;

    @FXML
    private TableColumn<?, ?> QuantityCol;

    @FXML
    private TableColumn<?, ?> QuantityUnitPriceCol;

    @FXML
    private TableColumn<?, ?> ActionCol;

    @FXML
    private JFXComboBox PaymentMethordBox;

    @FXML
    private JFXButton PlaceOrderBtn;

    @FXML
    private JFXButton AddToCartBtn;

    @FXML
    void BuyerIdLoadOnAction(ActionEvent event) throws SQLException {
        String id= String.valueOf(BuyerIdBox.getValue());
        try {
            Buyer buyer = BuyerModel.searchByBuyerId(id);
            if (buyer != null) {
                BuyerNameLabel.setText(buyer.getBuyerName());
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }


    private void LoadBuyerId(){

   try{
        ObservableList<String>buyerIds= FXCollections.observableArrayList();
        List<String> ids = BuyerModel.LoadBuyerIds();

        for(String id:ids) {
            buyerIds.add(id);

          }
           BuyerIdBox.setItems(buyerIds);

        }catch (SQLException throwables){
            AlertController.errormessage("Something went Wrong");
        }
    }

    Item item;
    @FXML
    void ItemCodeLodeOnAction(ActionEvent event) {

        String id= String.valueOf(ItemCodeBox.getValue());
        try {
            item = ItemModel.searchByItemCode(id);
            if (item != null) {
                ItemNameLabel.setText(item.getItemName());
                UnitPriceLabel.setText(String.valueOf(item.getUnitPrice()));
                QtyOnHandsLabel.setText(String.valueOf(item.getWeight()));


                if (item.getWeight() > 0) {
                    QtyOnHandsLabel.setText(String.valueOf(item.getWeight()));
                } else {
                    QtyOnHandsLabel.setText("Out Of Stock");
                    AlertController.errormessage(item.getItemName() + " out of stock");
                }
            }




        }catch (SQLException throwables){
            throwables.printStackTrace();
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

    private void generateNextOrderId() throws SQLException {

        try {
            String id = OrderModel.getNextOrderId();
            OrderIdLabel.setText(id);
        }catch (Exception e){
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR,"Exception").show();
        }
    }

    private void setRemoveBtnOnAction(Button btnRemove) {

        btnRemove.setOnAction((e) -> {

            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to remove?", yes, no).showAndWait();
            if (result.orElse(no) == yes) {
                int index = OrderTbl.getSelectionModel().getSelectedIndex();
                obList.remove(index);

                if(OrderTbl.getItems().isEmpty()) {
                    PlaceOrderBtn.setDisable(true);
                    PaidAmountText.setDisable(true);
                }

                OrderTbl.refresh();
                calculateNetTotal();
            }
        });
    }




        @FXML
        private ObservableList<OrderTm> obList = FXCollections.observableArrayList();
    @FXML
    void AddToCartClick(ActionEvent event) {

        if (!BuyerIdBox.getSelectionModel().isEmpty()) {
            if (!ItemCodeBox.getSelectionModel().isEmpty()) {
                if (!PaymentMethordBox.getSelectionModel().isEmpty()) {
                    if (ValidationController.salary(ItemContityText.getText())) {
                       if(!QtyOnHandsLabel.getText().equals("Out Of Stock")) {


//                           TablePosition pos = OrderTbl.getSelectionModel().getSelectedCells().get(0);
//                           int row = pos.getRow();
//                           ObservableList<TableColumn<OrderTm, ?>> columns = OrderTbl.getColumns();
//
//                           columns.get(4).getCellData(row).toString();


                           String itemCode = ItemCodeBox.getValue();
                           String itamName = ItemNameLabel.getText();
                           String unitPrice = UnitPriceLabel.getText();
                           double quantiti = Double.parseDouble(ItemContityText.getText());
                           double total = Double.parseDouble(UnitPriceLabel.getText()) * Double.parseDouble(ItemContityText.getText());

                           Button btnremove = new Button("Remove");
                           btnremove.setCursor(Cursor.HAND);

                           if (quantiti > item.getWeight()) {
                               AlertController.errormessage(itamName + "Out of stock or not enough stock");
                           } else {
                               setRemoveBtnOnAction(btnremove);

                               if (!obList.isEmpty()) {

                                   for (int i = 0; i < OrderTbl.getItems().size(); i++) {

                                       if (ItemCodeCol.getCellData(i).equals(itemCode)) {
                                           quantiti += (double) QuantityCol.getCellData(i);
                                           total = quantiti * Double.parseDouble(unitPrice);

                                           obList.get(i).setQuantity(quantiti);
                                           obList.get(i).setQuntityUnitPrice(total);

                                           OrderTbl.refresh();
                                           calculateNetTotal();
                                           return;
                                       }
                                   }
                               }
                               OrderTm tm = new OrderTm(itemCode, itamName, unitPrice, quantiti, total, btnremove);
                               obList.add(tm);
                               OrderTbl.setItems(obList);
                               calculateNetTotal();

                               ItemContityText.setText("");
                               PlaceOrderBtn.setDisable(false);
                               PaidAmountText.setDisable(false);
                           }
                       }else{
                           AlertController.errormessage("Item "+ItemNameLabel.getText() + "  is out of stock");
                       }
                    }else{
                        AlertController.errormessage("invalied Quantity");
                    }
                }else{
                    AlertController.errormessage("Please Select Payment Methord");
                }
            }else{
                AlertController.errormessage("Please Select Item Code");
            }
        }else{
            AlertController.errormessage("Please Select Buyer Id");
        }


    }



    @FXML
    void PlaceOrderClick(ActionEvent event) throws SQLException {

        if (!PaidAmountText.getText().isEmpty()) {

            String orderId = OrderIdLabel.getText();
            String buyerId = BuyerIdBox.getValue();
            String orderPay = TotalPaymentLabel.getText();

            List<Order> orderList = new ArrayList<>();

            for (int i = 0; i < OrderTbl.getItems().size(); i++) {
                OrderTm orderTm = obList.get(i);

                Order order = new Order(
                        orderTm.getItemCode(),
                        orderTm.getQuantity()
                );
                orderList.add(order);
            }
            boolean isOrders = false;

            try {
                isOrders = OrderModel.placeOrder(orderId, buyerId, orderPay, orderList);

                if (isOrders) {

                    AlertController.confirmmessage("Order Placed");


                    String printcash = PaidAmountText.getText();
                    String balance = BalanceLbl.getText();

                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("param1", printcash);
                    parameters.put("param2", balance);

                    InputStream resource = this.getClass().getResourceAsStream("/report/PlaceOrder.jrxml");
                    try {
                        JasperReport jasperReport = JasperCompileManager.compileReport(resource);
                        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, DBConnection.getInstance().getConnection());
                        JasperViewer.viewReport(jasperPrint, false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    PlaceOrderBtn.setDisable(true);
                    PaidAmountText.setDisable(true);

                    BuyerIdBox.setValue(null);
                    ItemCodeBox.setValue(null);
                    PaymentMethordBox.setValue(null);
                    ItemContityText.setText("");
                    BuyerNameLabel.setText("");
                    ItemNameLabel.setText("");
                    UnitPriceLabel.setText("");
                    QtyOnHandsLabel.setText("");
                    OrderIdLabel.setText("");
                    OrderTimeLabel.setText("");
                    OrderDateLabel.setText("");
                    TotalPaymentLabel.setText("");
                    PaidAmountText.setText("");
                    BalanceLbl.setText("");

                    OrderTbl.getItems().clear();


                    generateNextOrderId();
                } else {
                    AlertController.errormessage("Order Not Placed");
                }
            } catch (SQLException e) {
                AlertController.errormessage("Sql Error");
            }
        }else{
            AlertController.errormessage("Please Select Paid Amount");

        }
    }

    private void calculateNetTotal(){
        double netTotal=0.0;

        for(int i =0; i<OrderTbl.getItems().size();i++){
            double total=(double) QuantityUnitPriceCol.getCellData(i);
            netTotal+=total;
        }
        TotalPaymentLabel.setText(String.valueOf(netTotal));
    }

    private void setCellValueFactory(){

        ItemCodeCol.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        ItemNameCol.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        UnitPriceCol.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        QuantityCol.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        QuantityUnitPriceCol.setCellValueFactory(new PropertyValueFactory<>("QuntityUnitPrice"));
        ActionCol.setCellValueFactory(new PropertyValueFactory<>("action"));

    }

    @FXML
    void PaidAmountCheck(KeyEvent event) {

        if(!PaidAmountText.getText().isEmpty()){
            double balance = Double.parseDouble(PaidAmountText.getText())-Double.parseDouble(TotalPaymentLabel.getText());
            if(balance>=0){
                BalanceLbl.setText(String.valueOf(balance));

            }else if(balance<0){
                double positbalance = Math.abs(balance);

       }
    }

    }

    @FXML
    void initialize() {
        assert AnchorpaneHome != null : "fx:id=\"AnchorpaneHome\" was not injected: check your FXML file 'orders.fxml'.";
        assert BuyerIdBox != null : "fx:id=\"BuyerIdBox\" was not injected: check your FXML file 'orders.fxml'.";
        assert ItemCodeBox != null : "fx:id=\"ItemCodeBox\" was not injected: check your FXML file 'orders.fxml'.";
        assert BuyerNameLabel != null : "fx:id=\"BuyerNameLabel\" was not injected: check your FXML file 'orders.fxml'.";
        assert ItemNameLabel != null : "fx:id=\"ItemNameLabel\" was not injected: check your FXML file 'orders.fxml'.";
        assert UnitPriceLabel != null : "fx:id=\"UnitPriceLabel\" was not injected: check your FXML file 'orders.fxml'.";
        assert QtyOnHandsLabel != null : "fx:id=\"QtyOnHandsLabel\" was not injected: check your FXML file 'orders.fxml'.";
        assert OrderIdLabel != null : "fx:id=\"OrderIdLabel\" was not injected: check your FXML file 'orders.fxml'.";
        assert OrderDateLabel != null : "fx:id=\"OrderDateLabel\" was not injected: check your FXML file 'orders.fxml'.";
        assert OrderTimeLabel != null : "fx:id=\"OrderTimeLabel\" was not injected: check your FXML file 'orders.fxml'.";
        assert ItemContityText != null : "fx:id=\"ItemContityText\" was not injected: check your FXML file 'orders.fxml'.";
        assert TotalPaymentLabel != null : "fx:id=\"TotalPaymentLabel\" was not injected: check your FXML file 'orders.fxml'.";
        assert OrderTbl != null : "fx:id=\"OrderTbl\" was not injected: check your FXML file 'orders.fxml'.";
        assert ItemCodeCol != null : "fx:id=\"ItemCodeCol\" was not injected: check your FXML file 'orders.fxml'.";
        assert ItemNameCol != null : "fx:id=\"ItemNameCol\" was not injected: check your FXML file 'orders.fxml'.";
        assert UnitPriceCol != null : "fx:id=\"UnitPriceCol\" was not injected: check your FXML file 'orders.fxml'.";
        assert QuantityCol != null : "fx:id=\"QuantityCol\" was not injected: check your FXML file 'orders.fxml'.";
        assert QuantityUnitPriceCol != null : "fx:id=\"QuantityUnitPriceCol\" was not injected: check your FXML file 'orders.fxml'.";
        assert ActionCol != null : "fx:id=\"ActionCol\" was not injected: check your FXML file 'orders.fxml'.";
        assert PaymentMethordBox != null : "fx:id=\"PaymentMethordBox\" was not injected: check your FXML file 'orders.fxml'.";
        assert PlaceOrderBtn != null : "fx:id=\"PlaceOrderBtn\" was not injected: check your FXML file 'orders.fxml'.";
        assert AddToCartBtn != null : "fx:id=\"AddToCartBtn\" was not injected: check your FXML file 'orders.fxml'.";

       setCellValueFactory();
      LoadBuyerId();
      LoadItemCode();
        try {
            generateNextOrderId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PaymentMethordBox.getItems().addAll("Cash", "Bank Deposit");
        DashBordController.Timenow(OrderTimeLabel,OrderDateLabel);
    }

    public void reportClick(ActionEvent event) {


    }
}
