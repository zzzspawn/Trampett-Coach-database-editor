package com.snypso.database.manager.GUI;

import com.snypso.database.manager.DatabaseHandler.DatabaseHandler;
import com.snypso.database.manager.DatabaseHandler.Order;
import com.snypso.database.manager.LocalDataStorage.User;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class RulesetEditing {
    private Stage window;
    private List<Order> orderList;
    private Pane rulesetPane;
    private User user;
    DatabaseHandler databaseHandler;
    RulesetEditing(DatabaseHandler databaseHandler, Stage window, List<Order> orderList, Pane rulesetPane, User user){
        this.window = window;
        this.orderList = orderList;
        this.rulesetPane = rulesetPane;
        this.user = user;
        this.databaseHandler = databaseHandler;
    }

    void launchRulesetEditor(){
        this.rulesetPane.getChildren().clear();


        Scene scene = new Scene(this.rulesetPane, 1000, 500);
        this.window.setScene(scene);
        this.window.setTitle("Reglement Redigering");
    }
}
