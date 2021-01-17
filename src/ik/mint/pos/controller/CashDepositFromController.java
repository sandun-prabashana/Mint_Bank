package ik.mint.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import ik.mint.pos.bo.BOFactry;
import ik.mint.pos.bo.custom.CashDepositBO;
import ik.mint.pos.dto.AcccountDetailDTO;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ik.mint.pos.controller.AddCustomerFormController.customerBO;

public class CashDepositFromController implements Initializable {
    public AnchorPane CashDeposit;
    public JFXTextField txtDate;
    public JFXTextField txtTime;
    public JFXTextField txtAccountnumber;
    public JFXTextField txtCustomerId;
    public JFXTextField txtName;
    public JFXTextField txtBalance;
    public JFXTextField txtDeposit;


    static CashDepositBO cashDepositBO= (CashDepositBO) BOFactry.getInstance().getBO(BOFactry.BOTypes.CASHDEPOSIT);
    public JFXButton btnDeposit;
    public JFXTextField txtRef;
    public JFXTextField txtAccountType;

    public void set(){
        try {
            ArrayList<CustomDTO> accountList = cashDepositBO.getAllAccount();

            for (CustomDTO cDTO : accountList) {
                System.out.println(cDTO.getAccountNumber());
            }


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AddCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void generateDateTime(){
        txtDate.setText(LocalDate.now().toString());


        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e->{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            txtTime.setText(LocalDateTime.now().format(formatter));
        }),new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    public void txtAccountnumberOnAction(ActionEvent actionEvent) {
        int x=0;
        Integer AccountNumber=Integer.parseInt(txtAccountnumber.getText());

        try {
            ArrayList<CustomDTO> accountList = cashDepositBO.getAllAccount();

            for (CustomDTO cDTO : accountList) {
                if(AccountNumber.equals(cDTO.getAccountNumber())){
                    x=1;
                }
            }
            if(x==1){
                try {
                    CustomDTO customDTO = cashDepositBO.getBalance(AccountNumber);
                    if (customDTO!=null) {
                        txtCustomerId.setText(customDTO.getCustomerId());
                        String name=customDTO.getFname();
                        String name1=customDTO.getLname();
                        String full=name+" "+name1;
                        txtName.setText(full);
                        txtAccountType.setText(customDTO.getAccountType());
                        txtBalance.setText(customDTO.getBalance()+"");
                        int ref=customDTO.getRef();
                        if(ref==0){
                            txtRef.setText(String.valueOf(1));
                        }
                        if(ref>0){
                            txtRef.setText(String.valueOf(ref+1));
                        }
                    }else{
                        new Alert(Alert.AlertType.WARNING, "Try Again!",
                                ButtonType.OK).show();
                    }

                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    new Alert(Alert.AlertType.WARNING, "Worng Account Number", ButtonType.OK).show();
                } catch (NumberFormatException ex) {
                    new Alert(Alert.AlertType.WARNING, "All Fields should be completed", ButtonType.OK).show();
                } catch (NullPointerException ex) {
                    new Alert(Alert.AlertType.WARNING, "Worng Account Number", ButtonType.OK).show();
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

        generateDateTime();
    }

    public void btnDepositOnAction(ActionEvent actionEvent) {
        String Date=txtDate.getText();
        Integer AccountNo=Integer.parseInt(txtAccountnumber.getText());
        String Detail="Cash Deposit";
        Double Withdraw=0.00;
        Double Deposit=Double.parseDouble(txtDeposit.getText());
        Double Balance=Double.parseDouble(txtBalance.getText());
        int ref=Integer.parseInt(txtRef.getText());

        try {
            AcccountDetailDTO acccountDetailDTO = new AcccountDetailDTO(ref,Date,AccountNo,Detail,Withdraw,Deposit,Balance);

                boolean isAdd = cashDepositBO.addAccountDetail(acccountDetailDTO);
                if (isAdd) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Cash Deposit Sussecfull", ButtonType.OK).show();
                    txtRef.setText(null);
                    txtBalance.setText(null);
                    txtName.setText(null);
                    txtDeposit.setText(null);
                    txtCustomerId.setText(null);
                } else {
                    new Alert(Alert.AlertType.WARNING, "Cash Deposit  Fail", ButtonType.OK).show();
                }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.WARNING, "Cash Deposit Fail", ButtonType.OK).show();
        } catch (NumberFormatException ex) {
            new Alert(Alert.AlertType.WARNING, "All Fields should be completed", ButtonType.OK).show();
        } catch (NullPointerException ex) {
            new Alert(Alert.AlertType.WARNING, "Cash Deposit Fail", ButtonType.OK).show();
        }
    }

    public void txtDepositOnAction(ActionEvent actionEvent) {
        Double w=Double.parseDouble(txtDeposit.getText());
        Double b=Double.parseDouble(txtBalance.getText());

        Double Balance=w+b;
        txtBalance.setText(String.valueOf(Balance));
    }
}

