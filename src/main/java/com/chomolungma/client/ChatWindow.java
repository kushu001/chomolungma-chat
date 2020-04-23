package com.chomolungma.client;


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ChatWindow {

    public static void initChatWindow(){
        Stage stage = new Stage();
        GridPane gridPane = new GridPane();
        TextArea messagesArea = new TextArea();
        messagesArea.setPrefSize(300,400);


        TextArea messageArea = new TextArea();
        messageArea.setPrefSize(250,100);

        TextArea personArea = new TextArea();
        personArea.setPrefSize(150,500);
        Button sendButton = new Button();
        sendButton.setPrefSize(50,100);
        sendButton.setText("发送");
        sendButton.setOnAction(new SendButtonAction(messagesArea,messageArea));

        gridPane.setPrefSize(450,500);

        gridPane.add(messagesArea,0,0,2,1);
        gridPane.add(messageArea,0,1);
        gridPane.add(personArea,2,0,1,2);
        gridPane.add(sendButton,1,1);
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.setTitle("聊天窗口");
        stage.setWidth(450);
        stage.setHeight(500);
        stage.show();
    }
}


class SendButtonAction implements EventHandler {

    private TextArea messageArea;
    private TextArea messagesArea;

    public SendButtonAction(TextArea messagesArea,TextArea messageArea){
        this.messageArea = messageArea;
        this.messagesArea = messagesArea;
    }

    @Override
    public void handle(Event event) {
        String messages =  messagesArea.getText()+"\n"+ messageArea.getText();
        messageArea.setText(null);
        messagesArea.setText(messages);

    }
}
