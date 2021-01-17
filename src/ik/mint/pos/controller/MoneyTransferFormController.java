package ik.mint.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import ik.mint.pos.bo.BOFactry;
import ik.mint.pos.bo.custom.TransferBO;
import ik.mint.pos.dto.AcccountDetailDTO;
import ik.mint.pos.dto.AccountDTO;
import ik.mint.pos.dto.CustomDTO;
import ik.mint.pos.dto.TransferDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MoneyTransferFormController implements Initializable {
    public AnchorPane CashDeposit;
    public JFXTextField txtDate;
    public JFXTextField txtTime;
    public JFXTextField txtAccountnumber;
    public JFXTextField txtCustomerId;
    public JFXTextField txtName;
    public JFXTextField txtBalance;
    public JFXTextField txtTransfer;
    public JFXButton btnTransfer;
    public JFXTextField txtTranferId;
    public JFXTextField txtAccountType;
    public JFXTextField txtTransfernumber;
    public JFXTextField txtCustomerIdT;
    public JFXTextField txtNameT;
    public JFXTextField txtAccountTypeT;

    static TransferBO transferBO= (TransferBO) BOFactry.getInstance().getBO(BOFactry.BOTypes.TRANSFER);

    Integer refAccount;
    Integer refTransfer;
    Double TransferBalance;


    public void generateDateTime(){
        txtDate.setText(LocalDate.now().toString());


        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e->{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            txtTime.setText(LocalDateTime.now().format(formatter));
        }),new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    public void setCustomerId(){
        try {
            int Count = transferBO.getRegCount();
            if (Count==0){
                txtTranferId.setText("T001");
            }
            if (Count>0 && Count<=8){
                txtTranferId.setText("T00"+(Count+1));
            }
            if (Count>=9 && Count<=98){
                txtTranferId.setText("T0"+(Count+1));
            }
            if (Count>=99){
                txtTranferId.setText("T"+(Count+1));
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


        public void txtAccountnumberOnAction(ActionEvent actionEvent) {
        int x=0;
        Integer AccountNumber=Integer.parseInt(txtAccountnumber.getText());

        try {
            ArrayList<CustomDTO> accountList = transferBO.getAllAccount();

            for (CustomDTO cDTO : accountList) {
                if(AccountNumber.equals(cDTO.getAccountNumber())){
                    x=1;
                }
            }
            if(x==1){
                try {
                    CustomDTO customDTO = transferBO.getBalance(AccountNumber);
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
                            refAccount=1;
                        }
                        if(ref>0){
                            refAccount=ref+1;
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

    public void txtTranferOnAction(ActionEvent actionEvent) {
        Double Withdraw=Double.parseDouble(txtTransfer.getText());
        Double Balance=Double.parseDouble(txtBalance.getText());
        Double Money=Balance-Withdraw;
        if(Withdraw <= Balance){
            txtBalance.setText(String.valueOf(Money));
        }else{
            new Alert(Alert.AlertType.WARNING, "This Account Hasn't Enough Balance!",
                    ButtonType.OK).show();
        }
    }

    public void btnTransferOnAction(ActionEvent actionEvent) {
        Integer frist=Integer.parseInt(String.valueOf(refAccount));
        Integer second=Integer.parseInt(String.valueOf(refTransfer));
        System.out.println(refAccount);
        System.out.println(refTransfer);
        String CustomerId=txtCustomerId.getText();
        String Date=txtDate.getText();
        Integer AccountNumber=Integer.parseInt(txtAccountnumber.getText());
        String TransferId=txtTranferId.getText();
        Integer TransferAccountNumber=Integer.parseInt(txtTransfernumber.getText());
       try {
            if (CustomerId.length() > 0 ) {

                ArrayList<AcccountDetailDTO> accountDetail=new ArrayList();

                String Detail="Money Transfer";
                Double Withdraw=Double.parseDouble(txtTransfer.getText());
                Double Deposit=0.00;
                Double Balance=Double.parseDouble(txtBalance.getText());

                accountDetail.add(new AcccountDetailDTO(frist,Date,AccountNumber,Detail,Withdraw,Deposit,Balance));


                TransferDTO transferDTO = new TransferDTO(TransferId,AccountNumber,Withdraw,TransferAccountNumber,Date,accountDetail);

                boolean isAdded = transferBO.addTransfer(transferDTO);
                if (isAdded) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Money Transfer Sussecfull", ButtonType.OK).show();

                    try {
                        Double WithdrawTransfer=0.00;
                        Double DepositTransfer=Double.parseDouble(txtTransfer.getText());
                        Double newBalane=Double.parseDouble(String.valueOf(TransferBalance));
                        Double amount=newBalane+DepositTransfer;
                        AcccountDetailDTO acccountDetailDTO = new AcccountDetailDTO(second,Date,TransferAccountNumber,Detail,WithdrawTransfer,DepositTransfer,amount);

                        boolean isAdd = transferBO.addAccountDetail(acccountDetailDTO);
                        if (isAdd) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Account Detail Update", ButtonType.OK).show();
                            setCustomerId();
                            txtBalance.setText(null);
                            txtName.setText(null);
                            txtNameT.setText(null);
                            txtTransfer.setText(null);
                            txtCustomerId.setText(null);
                            txtCustomerIdT.setText(null);
                            txtAccountType.setText(null);
                            txtAccountTypeT.setText(null);
                            txtAccountnumber.setText(null);
                            txtTransfernumber.setText(null);
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Account Detail Update  Fail", ButtonType.OK).show();
                        }
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (SQLException ex) {
                        new Alert(Alert.AlertType.WARNING, "Account Detail Update Fail", ButtonType.OK).show();
                    } catch (NumberFormatException ex) {
                        new Alert(Alert.AlertType.WARNING, "All Fields should be completed", ButtonType.OK).show();
                    } catch (NullPointerException ex) {
                        new Alert(Alert.AlertType.WARNING, "Account Detail Update Fail", ButtonType.OK).show();
                    }
                    txtCustomerId.setText(null);

                } else {
                    new Alert(Alert.AlertType.WARNING, "Money Transfer Fail", ButtonType.OK).show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Filed Can't Be Empty!",
                        ButtonType.OK).show();
            }

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (NumberFormatException ex) {
            new Alert(Alert.AlertType.WARNING, "All Fields should be completed", ButtonType.OK).show();
        } catch (NullPointerException ex) {
            new Alert(Alert.AlertType.WARNING, "Money Transfer Fail", ButtonType.OK).show();
        } catch (SQLException ex) {
           new Alert(Alert.AlertType.WARNING, "Money Transfer Fail", ButtonType.OK).show();
        }
        System.out.println(TransferBalance);
    }

    public void txtTransfernumberOnAction(ActionEvent actionEvent) {
        int x=0;
        Integer AccountNumber=Integer.parseInt(txtTransfernumber.getText());

        try {
            ArrayList<CustomDTO> accountList = transferBO.getAllAccount();

            for (CustomDTO cDTO : accountList) {
                if(AccountNumber.equals(cDTO.getAccountNumber())){
                    x=1;
                }
            }
            if(x==1){
                try {
                    CustomDTO customDTO = transferBO.getBalance(AccountNumber);
                    if (customDTO!=null) {
                        txtCustomerIdT.setText(customDTO.getCustomerId());
                        String name=customDTO.getFname();
                        String name1=customDTO.getLname();
                        String full=name+" "+name1;
                        txtNameT.setText(full);
                        txtAccountTypeT.setText(customDTO.getAccountType());
                        TransferBalance=customDTO.getBalance();
                        int ref=customDTO.getRef();
                        if(ref==0){
                            refTransfer=1;
                        }
                        if(ref>0){
                            refTransfer=ref+1;
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
        setCustomerId();
    }
}
