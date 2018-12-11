package com.snypso.database.manager.GUI;

import com.snypso.database.manager.DatabaseHandler.DatabaseHandler;
import com.snypso.database.manager.DatabaseHandler.Language;
import com.snypso.database.manager.DatabaseHandler.Order;
import com.snypso.database.manager.DatabaseHandler.OrderType;
import com.snypso.database.manager.LocalDataStorage.OrderHandler;
import com.snypso.database.manager.LocalDataStorage.User;
import com.snypso.database.manager.LocalDataStorage.UserHandler;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    Pane loginPane;
    Pane languagePane;
    Pane rulesetPane;
    Stage window;
    User user;
    List<Order> orderList;
    LanguageEditing languageEditingController;
    RulesetEditing rulesetEditingController;
    LoginWindow loginWindowController;
    DatabaseHandler databaseHandler;
    @Override
    public void start(Stage stage) throws Exception {
        databaseHandler = new DatabaseHandler();
        this.window = stage;
        window.setResizable(false);
        OrderHandler orderHandler = new OrderHandler();
        orderList = orderHandler.loadOrderList();
        UserHandler userHandler = new UserHandler();
        User currentUser = userHandler.getUserData();

        this.loginPane = new Pane();
        this.languagePane = new Pane();
        this.rulesetPane = new Pane();
        this.languageEditingController = new LanguageEditing(databaseHandler, this.window, orderList, languagePane, currentUser);
        this.rulesetEditingController = new RulesetEditing(databaseHandler, this.window, orderList, rulesetPane, currentUser);
        this.loginWindowController = new LoginWindow(databaseHandler, loginPane, this.languageEditingController, this.rulesetEditingController, this.window, userHandler, currentUser);
        this.loginWindowController.launchLoginWindow();

    }
}
