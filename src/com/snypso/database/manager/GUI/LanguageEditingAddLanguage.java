package com.snypso.database.manager.GUI;

import com.snypso.database.manager.DatabaseHandler.DatabaseHandler;
import com.snypso.database.manager.DatabaseHandler.Order;
import com.snypso.database.manager.DatabaseHandler.OrderType;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.List;

class LanguageEditingAddLanguage {

    private Pane addModePane;
    private List<Order> orderList;
    private LanguageEditing languageEditing;
    private DatabaseHandler databaseHandler;
    LanguageEditingAddLanguage(DatabaseHandler databaseHandler, Pane addModePane, List<Order> orderList, LanguageEditing languageEditing){
        this.addModePane = addModePane;
        this.orderList = orderList;
        this.languageEditing = languageEditing;
        this.databaseHandler = databaseHandler;
    }


    void display(){
        Order order = new Order();

        Label titleLabel = new Label();
        titleLabel.setText("Add Language");
        titleLabel.relocate(100, 10);
        addModePane.getChildren().add(titleLabel);

        Label languageTitleLabel = new Label();
        languageTitleLabel.setText("Language name");
        languageTitleLabel.relocate(100, 50);
        addModePane.getChildren().add(languageTitleLabel);

        TextField languageTitleTextField = new TextField();
        languageTitleTextField.setPromptText("LanguageName");
        languageTitleTextField.relocate(100, 80);
        addModePane.getChildren().add(languageTitleTextField);

        Button confirmButton = new Button();
        confirmButton.setText("Confirm Order");
        confirmButton.relocate(100, 130);
        addModePane.getChildren().add(confirmButton);

        LanguageEditing editor = this.languageEditing;
        confirmButton.setOnMouseClicked((EventHandler<Event>) event -> {
            order.setName(languageTitleTextField.getText());
            order.setType(OrderType.ADD_LANGUAGE);
            orderList.add(order);
            editor.clearAllViews();
            editor.updateOrders();
        });

    }
}
