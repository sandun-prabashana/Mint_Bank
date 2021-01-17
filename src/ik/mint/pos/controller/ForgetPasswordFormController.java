package ik.mint.pos.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sun.misc.Launcher;

import java.beans.AppletInitializer;
import java.io.IOException;

public class ForgetPasswordFormController {
    public AnchorPane pane;

    public void closeBtn1(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void backBtn(MouseEvent mouseEvent) throws IOException {
        Stage window = (Stage) this.pane.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../views/LoginForm.fxml                                                             "))));
        window.show();
        

    }
}
