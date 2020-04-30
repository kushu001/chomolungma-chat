package com.chomolungma.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Login extends Application {


    private Stage stage;
    public Scene createLoginScene(){
        GridPane gridPane = new GridPane();
        Label userName = new Label("用户名：");
        Label password = new Label("密码：");
        TextField userNameTextField = new TextField();
        PasswordField passwordTextField = new PasswordField();
        Button submit = new Button("登录");

        submit.setOnAction(event -> Platform.runLater(()->{
            ChatWindow chatWindow = new ChatWindow();
            chatWindow.initChatWindow(userNameTextField.getText());
            stage.close();
        }));
        gridPane.add(userName,0,0);
        gridPane.add(password,0,1);
        gridPane.add(userNameTextField,1,0,2,1);
        gridPane.add(passwordTextField,1,1,2,1);
        gridPane.add(submit,1,2,2,1);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(20);
        gridPane.setId("grid");

        Scene scene = new Scene(gridPane);

        //设置背景图样式
        scene.getStylesheets().addAll(ClassLoader.getSystemResource("css/login.css").toExternalForm());
        return scene;
    }


    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        primaryStage.setTitle("QQ 登录");
        primaryStage.setWidth(300);
        primaryStage.setHeight(600);
        primaryStage.setScene(createLoginScene());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
