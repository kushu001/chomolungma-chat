package com.chomolungma.client;


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ChatWindow {

    public  void initChatWindow(String name){

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
        ClientSocket clientSocket = new ClientSocket(messagesArea,name);
        clientSocket.connect();
        sendButton.setOnAction(new SendButtonAction(messageArea,clientSocket));

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
    private ClientSocket clientSocket;

    public SendButtonAction(TextArea messageArea,ClientSocket clientSocket){
        this.messageArea = messageArea;
        this.clientSocket = clientSocket;

    }

    @Override
    public void handle(Event event) {
        clientSocket.sendMessage(messageArea.getText());
        messageArea.setText("");
    }
}
