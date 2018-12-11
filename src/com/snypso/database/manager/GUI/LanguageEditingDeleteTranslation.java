package com.snypso.database.manager.GUI;

import com.snypso.database.manager.DatabaseHandler.DatabaseHandler;
import com.snypso.database.manager.DatabaseHandler.Order;
import com.snypso.database.manager.DatabaseHandler.OrderType;
import com.snypso.database.manager.LocalDataStorage.User;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.List;

public class LanguageEditingDeleteTranslation {

    User user;
    Pane mainPane;
    List<Order> orderList;
    LanguageEditing languageEditing;
    DatabaseHandler databaseHandler;
    LanguageEditingDeleteTranslation(DatabaseHandler databaseHandler, User user, Pane mainPane, List<Order> orderList, LanguageEditing languageEditing){
        this.user = user;
        this.mainPane = mainPane;
        this.orderList = orderList;
        this.languageEditing = languageEditing;
        this.databaseHandler = databaseHandler;
    }

    public void display() {
        Label label = new Label("DELETING WORKED");
        mainPane.getChildren().add(label);


        Order order = new Order();


        Button confirmButton = new Button();
        confirmButton.setText("Confirm Order");
        confirmButton.relocate(100, 130);
        mainPane.getChildren().add(confirmButton);

        LanguageEditing editor = this.languageEditing;
        confirmButton.setOnMouseClicked((EventHandler<Event>) event -> {
            order.setName("GET TEXT HERE");
            order.setType(OrderType.ADD_TRANSLATION);
            orderList.add(order);
            editor.clearAllViews();
            editor.updateOrders();
        });
    }
}
