package lk.ijse.super_cargo.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
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
import lk.ijse.super_cargo.dto.Matirial;
import lk.ijse.super_cargo.dto.tm.ItemTm;
import lk.ijse.super_cargo.dto.tm.MatirialTm;
import lk.ijse.super_cargo.model.ItemModel;
import lk.ijse.super_cargo.model.MatirialModel;
import lk.ijse.super_cargo.util.AlertController;

public class MatirialController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorpaneHome;

    @FXML
    private JFXTextField MatirealIdText;

    @FXML
    private JFXTextField MatirialNameText;

    @FXML
    private JFXTextField MatirialWeighText;

    @FXML
    private JFXTextField MatirialPriceText;
    @FXML
    private TableView<MatirialTm> MatirialTbl;




    @FXML
    private JFXComboBox MatirialQualityBox;

    @FXML
    private JFXButton MatirialUpdateBtn;

    @FXML
    private JFXButton MatirialSaveBtn;

    @FXML
    private JFXButton MatirialDeleteBtn;

    @FXML
    private TextField searchText;


    @FXML
    private Button searchBtn;


    @FXML
    private TableColumn<?, ?> MatirialIdCol;

    @FXML
    private TableColumn<?, ?> MatirialNameCol;

    @FXML
    private TableColumn<?, ?> MatirialWeightCol;

    @FXML
    private TableColumn<?, ?> MatirialPriceCol;

    @FXML
    private TableColumn<?, ?> MatirialQualityCol;

    @FXML
    void MatirialBoxOnAction(ActionEvent event) {

    }

    @FXML
    void MatirialDeleteClick(ActionEvent event) throws SQLException {

        String matirialId=MatirealIdText.getText();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to remove this matirial");
        if(result==true){

            try {
                boolean isDeleted = MatirialModel.delete(matirialId);
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

        MatirealIdText.setText("");
        MatirialNameText.setText("");
        MatirialWeighText.setText("");
        MatirialPriceText.setText("");
        MatirialQualityBox.setValue("");



    }



    @FXML
    void MatirialSaveClick(ActionEvent event) throws SQLException {

        Matirial matirial=new Matirial();


        matirial.setMatirialId(MatirealIdText.getText());
        matirial.setMatirialName(MatirialNameText.getText());
        matirial.setWeight(Double.parseDouble(MatirialWeighText.getText()));
        matirial.setPrice(Double.parseDouble(MatirialPriceText.getText()));
        matirial.setQuality(String.valueOf(MatirialQualityBox.getValue()));

        try {
            boolean isSave = MatirialModel.Save(matirial);
            if (isSave) {
                AlertController.confirmmessage("Save successFully");
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
            AlertController.errormessage("Somethink went wrong");
        }
        getAll();

        MatirealIdText.setText("");
        MatirialNameText.setText("");
        MatirialWeighText.setText("");
        MatirialPriceText.setText("");
        MatirialQualityBox.setValue("");
    }



    @FXML
    void MatirialUpdateClick(ActionEvent event) throws SQLException {

        Matirial matirial=new Matirial();



        matirial.setMatirialId(MatirealIdText.getText());
        matirial.setMatirialName(MatirialNameText.getText());
        matirial.setWeight(Double.parseDouble(MatirialWeighText.getText()));
        matirial.setPrice(Double.parseDouble(MatirialPriceText.getText()));
        matirial.setQuality(String.valueOf(MatirialQualityBox.getValue()));


        boolean result = AlertController.okconfirmmessage("Are you sure you want to remove this matirial?");
        if(result==true){


            try
            {
                boolean isUpdates=MatirialModel.Update(matirial);
                if(isUpdates){
                    AlertController.confirmmessage("Update successFully");


                }
                else{

                    AlertController.errormessage("Somethink went wrong");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                AlertController.errormessage("Somethink went wrong");
            }


        }


        setCellValueFactory();
        getAll();



        MatirealIdText.setText("");
        MatirialNameText.setText("");
        MatirialWeighText.setText("");
        MatirialPriceText.setText("");
        MatirialQualityBox.setValue("");
        searchText.setText("");


    }




    public void SearchClick(ActionEvent event) {

        String matirialId=searchText.getText();

        try{
            Matirial matirial= MatirialModel.Search(matirialId);

            MatirealIdText.setText(matirial.getMatirialId());
            MatirialNameText.setText(matirial.getMatirialName());
            MatirialWeighText.setText(String.valueOf(matirial.getWeight()));
            MatirialPriceText.setText(String.valueOf(matirial.getPrice()));
            MatirialQualityBox.setValue(matirial.getQuality());


            String searchValue=MatirealIdText.getText().trim();
            ObservableList<MatirialTm> obList= FXCollections.observableArrayList();

        }catch (SQLException throwables){
            throwables.printStackTrace();
            AlertController.errormessage("Somethink went wrong");
        }



    }



    private void setCellValueFactory(){

        MatirialIdCol.setCellValueFactory(new PropertyValueFactory<>("matirialId"));
        MatirialNameCol.setCellValueFactory(new PropertyValueFactory<>("matirialName"));
        MatirialWeightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        MatirialPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        MatirialQualityCol.setCellValueFactory(new PropertyValueFactory<>("quality"));


    }



    private void getAll() throws SQLException {

        try {
            ObservableList<MatirialTm> matirialData=MatirialModel.getAll();
           MatirialTbl .setItems(matirialData);


        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

//    @FXML
//    void initialize() throws SQLException {
//        assert AnchorpaneHome != null : "fx:id=\"AnchorpaneHome\" was not injected: check your FXML file 'matirial.fxml'.";
//        assert MatirealIdText != null : "fx:id=\"MatirealIdText\" was not injected: check your FXML file 'matirial.fxml'.";
//        assert MatirialNameText != null : "fx:id=\"MatirialNameText\" was not injected: check your FXML file 'matirial.fxml'.";
//        assert MatirialWeighText != null : "fx:id=\"MatirialNameText1\" was not injected: check your FXML file 'matirial.fxml'.";
//        assert MatirialPriceText != null : "fx:id=\"MatirialNameText2\" was not injected: check your FXML file 'matirial.fxml'.";
//        assert MatirialQualityBox != null : "fx:id=\"MatirialQualityBox\" was not injected: check your FXML file 'matirial.fxml'.";
//        assert MatirialUpdateBtn != null : "fx:id=\"MatirialUpdateBtn\" was not injected: check your FXML file 'matirial.fxml'.";
//        assert MatirialSaveBtn != null : "fx:id=\"MatirialSaveBtn\" was not injected: check your FXML file 'matirial.fxml'.";
//        assert MatirialDeleteBtn != null : "fx:id=\"MatirialDeleteBtn\" was not injected: check your FXML file 'matirial.fxml'.";
//
//
//
//
//    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MatirialQualityBox.getItems().addAll("Good", "Normal","Bad");

        try {
            getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        setCellValueFactory();

    }

    public void MatirialIdSearchclick(KeyEvent keyEvent) throws SQLException {

        String searchValue=searchText.getText().trim();
        ObservableList<MatirialTm>obList= MatirialModel.getAll();

        if (!searchValue.isEmpty()) {
            ObservableList<MatirialTm> filteredData = obList.filtered(new Predicate<MatirialTm>(){
                @Override
                public boolean test(MatirialTm matirialTm) {

                    return String.valueOf(matirialTm.getMatirialId()).toLowerCase().contains(searchValue.toLowerCase());        }
            });

            MatirialTbl.setItems(filteredData);} else {
            MatirialTbl.setItems(obList);
        }
    }

    public void MatirialOnMouseClicked(MouseEvent mouseEvent) {


        TablePosition pos=MatirialTbl.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<MatirialTm,?>> columns=MatirialTbl.getColumns();


        MatirealIdText.setText(columns.get(0).getCellData(row).toString());
        MatirialNameText.setText(columns.get(1).getCellData(row).toString());
        MatirialWeighText.setText(columns.get(2).getCellData(row).toString());
        MatirialPriceText.setText(columns.get(3).getCellData(row).toString());
        MatirialQualityBox.setValue(columns.get(4).getCellData(row).toString());

    }
}




