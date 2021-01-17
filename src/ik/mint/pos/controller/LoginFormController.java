package ik.mint.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import ik.mint.pos.bo.BOFactry;
import ik.mint.pos.bo.custom.LoginBO;
import ik.mint.pos.dto.CustomDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class LoginFormController {
    public Pane contentArea;
    public JFXButton btnLogin;
    public JFXTextField txtUserId;
    public JFXPasswordField txtPassword;
    public AnchorPane root;

    static LoginBO loginBo= (LoginBO) BOFactry.getInstance().getBO(BOFactry.BOTypes.LOGIN);

    public void forgetBtn(MouseEvent mouseEvent) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("../views/ForgetPawordForm.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);

    }

    public void closeBtn(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        String userName=txtUserId.getText();
        String Password=txtPassword.getText();
        try {
            if (userName.length()>0 && Password.length()>0){
            CustomDTO customDTO = loginBo.searchPassword(userName);
            if (customDTO!=null) {
                String pw=customDTO.getPassword();
                    if (Password.equals(pw)){
                        Stage window = (Stage) this.root.getScene().getWindow();
                        window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../views/AddCustomerForm.fxml"))));
                        window.show();
                    }else{
                        new Alert(Alert.AlertType.WARNING, "Try Again!",
                                ButtonType.OK).show();
                    }

            }
            }else{
                new Alert(Alert.AlertType.WARNING, "User Id Or Password Empty!",
                        ButtonType.OK).show();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
       } catch  (IOException e) {
            e.printStackTrace();
        }


    }


    }

