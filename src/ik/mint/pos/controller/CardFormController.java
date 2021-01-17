package ik.mint.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import ik.mint.pos.bo.BOFactry;
import ik.mint.pos.bo.custom.CardBO;
import ik.mint.pos.dto.AcccountDetailDTO;
import ik.mint.pos.dto.CardDTO;
import ik.mint.pos.dto.CustomDTO;
import ik.mint.pos.dto.CustomerDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CardFormController implements Initializable {
    public AnchorPane Card;
    public JFXTextField txtDate;
    public JFXTextField txtAccountnumber;
    public JFXTextField txtStatus;
    public JFXTextField txtPassword;
    public JFXButton btnCreateCard;
    public JFXTextField txtDateEx;


    static CardBO cardBO= (CardBO) BOFactry.getInstance().getBO(BOFactry.BOTypes.CARD);
    public JFXComboBox cmdCardType;
    public JFXTextField txtCardNumber;
    public JFXTextField txtCardNumberPass;
    public JFXTextField txtCurrentP;
    public JFXTextField txtPasswordP;
    public JFXButton btnSave;
    public JFXTextField txtCardNo;
    public JFXTextField txtCustId;
    public JFXButton btnDeactivate;
    public JFXComboBox cmdStatues;


    public void getCardType() {
        cmdCardType.getItems().clear();
        cmdCardType.getItems().add("Credit Card");
        cmdCardType.getItems().add("Debit Card");
    }
    public void getStatues() {
        cmdStatues.getItems().clear();
        cmdStatues.getItems().add("DEACTIVATE");
        cmdStatues.getItems().add("ACTIVATE");
    }

    public void generateDate(){
        txtDate.setText(LocalDate.now().toString());
        txtDateEx.setText((LocalDate.of(2030,12,12).toString()));
    }

    public void btnCreateCardOnAction(ActionEvent actionEvent) {
            Integer AccountNo=Integer.parseInt(txtAccountnumber.getText());
            String CardType=cmdCardType.getSelectionModel().getSelectedItem().toString();
            String AccountType="Saving Account";
            int x=0;
            int y=0;

        try {
            ArrayList<CustomDTO> accountList = cardBO.getAllAccount();

            for (CustomDTO cDTO : accountList) {
                if(AccountNo.equals(cDTO.getAccountNumber())){
                    x=1;
                }
            }
            if(x==1){

                try {
                    ArrayList<CustomDTO> cardList = cardBO.getCardDetail(AccountNo);

                    for (CustomDTO cDTO : cardList) {
                        if (CardType.equalsIgnoreCase(cDTO.getAccountType())) {
                            y = 1;
                        }
                    }
                    if(y==1){
                        new Alert(Alert.AlertType.WARNING, "This Account Already Have"+" "+CardType,
                                ButtonType.OK).show();
                    }else{
                        try {
                            CustomDTO customDTO = cardBO.searchAccountType(AccountNo);
                            if (customDTO!=null) {
                                System.out.println(customDTO.getAccountType());
                                String Type=customDTO.getAccountType();
                                if (AccountType.equals(Type)){
                                    try {
                                        int CardNumber=Integer.parseInt(txtCardNumber.getText());
                                        String DateOfIssue=txtDate.getText();
                                        String DateOfExprie=txtDateEx.getText();
                                        String Status=txtStatus.getText();
                                        int Password=Integer.parseInt(txtPassword.getText());
                                        CardDTO cardDTO = new CardDTO(AccountNo,CardNumber,CardType,DateOfIssue,DateOfExprie,Status,Password);

                                        boolean isAdd = cardBO.addCard(cardDTO);
                                        if (isAdd) {
                                            new Alert(Alert.AlertType.CONFIRMATION, "Card Create Sussecfull", ButtonType.OK).show();
                                            txtAccountnumber.setText(null);
                                            txtCardNumber.setText(null);
                                            txtPassword.setText(null);

                                        } else {
                                            new Alert(Alert.AlertType.WARNING, "Card Create  Fail", ButtonType.OK).show();
                                        }
                                    } catch (ClassNotFoundException ex) {
                                        ex.printStackTrace();
                                    } catch (SQLException ex) {
                                        new Alert(Alert.AlertType.WARNING, "Card Create Fail", ButtonType.OK).show();
                                    } catch (NumberFormatException ex) {
                                        new Alert(Alert.AlertType.WARNING, "All Fields should be completed", ButtonType.OK).show();
                                    } catch (NullPointerException ex) {
                                        new Alert(Alert.AlertType.WARNING, "Card Create Fail", ButtonType.OK).show();
                                    }
                                }else{
                                    new Alert(Alert.AlertType.WARNING, "Card Can Be Create Only For Saving Account", ButtonType.OK).show();
                                }

                            }

                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }


                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AddCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AddCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(AddCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                new Alert(Alert.AlertType.WARNING, "worng Account Number", ButtonType.OK).show();
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AddCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getCardType();
        generateDate();
        getStatues();
    }

    public void card(){
        Integer AccountNo=Integer.parseInt(txtAccountnumber.getText());
        try {
            ArrayList<CustomDTO> accountList = cardBO.getCardDetail(AccountNo);

//            for (CustomDTO cDTO : accountList) {
//                if (AccountType.equalsIgnoreCase(cDTO.getPassword())) {
//                    x = 1;
//                }
//            }
//            if(x==1){
//                new Alert(Alert.AlertType.WARNING, "This Customer Already Open This Account Type!",
//                        ButtonType.OK).show();
//            }


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AddCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        Integer CardNo=Integer.parseInt(txtCardNumberPass.getText());
        Integer NewPassword=Integer.parseInt(txtPasswordP.getText());
        try {

                CustomDTO customDTO = new CustomDTO(CardNo,NewPassword);

                boolean isUpdate = cardBO.Update(customDTO);
                if (isUpdate) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Password Change Sussecfull", ButtonType.OK).show();
                    txtCardNumberPass.setText(null);
                    txtCurrentP.setText(null);
                    txtPasswordP.setText(null);
                } else {
                    new Alert(Alert.AlertType.WARNING, "Password Change Fail", ButtonType.OK).show();
                }

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(CardFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            new Alert(Alert.AlertType.WARNING, "All Fields should be completed", ButtonType.OK).show();
        } catch (NullPointerException ex) {
            new Alert(Alert.AlertType.WARNING, "Password Change Fail", ButtonType.OK).show();
        }

    }

    public void txtCardNumberPassOnAction(ActionEvent actionEvent) {
        Integer CardNo=Integer.parseInt(txtCardNumberPass.getText());
        int x=0;
        try {
            ArrayList<CustomDTO> card = cardBO.getCardNo();

            for (CustomDTO cDTO : card) {
                if(CardNo.equals(cDTO.getAccountNumber())){
                    x=1;
                }
            }
            if(x==1){
                try {
                    CustomDTO customDTO = cardBO.searchPassword(CardNo);
                    if (customDTO!=null) {
                        int password=customDTO.getCardPassword();
                        txtCurrentP.setText(String.valueOf(password));

                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }else{
                txtCardNo.setText(null);
                new Alert(Alert.AlertType.WARNING, "worng Card Number", ButtonType.OK).show();
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AddCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void txtCardNoOnAction(ActionEvent actionEvent) {
        Integer CardNo=Integer.parseInt(txtCardNo.getText());
        int x=0;
        try {
            ArrayList<CustomDTO> card = cardBO.getCardNo();

            for (CustomDTO cDTO : card) {
                if(CardNo.equals(cDTO.getAccountNumber())){
                    x=1;
                }
            }
            if(x==1){
                try {
                    ArrayList<CustomDTO> custList = cardBO.getCustId(CardNo);
            for (CustomDTO cDTO : custList) {
                txtCustId.setText(cDTO.getAccountType());
            }



                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AddCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AddCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(AddCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                txtCardNo.setText(null);
                new Alert(Alert.AlertType.WARNING, "worng Card Number", ButtonType.OK).show();
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AddCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void btnDeactivateOnAction(ActionEvent actionEvent) {
        Integer CardNo=Integer.parseInt(txtCardNo.getText());
        String status=cmdStatues.getSelectionModel().getSelectedItem().toString();
        try {

            CustomDTO customDTO = new CustomDTO(CardNo,status);

            boolean isUpdate = cardBO.UpdateStatues(customDTO);
            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "Card Deactivated", ButtonType.OK).show();
                txtCustId.setText(null);
                txtCardNo.setText(null);
            } else {
                new Alert(Alert.AlertType.WARNING, "Card Deactivated Fail", ButtonType.OK).show();
            }

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(CardFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            new Alert(Alert.AlertType.WARNING, "All Fields should be completed", ButtonType.OK).show();
        } catch (NullPointerException ex) {
            new Alert(Alert.AlertType.WARNING, "Card Deactivated Fail", ButtonType.OK).show();
        }


    }
}
