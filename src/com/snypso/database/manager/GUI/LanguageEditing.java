package com.snypso.database.manager.GUI;

import com.snypso.database.manager.DatabaseHandler.DatabaseHandler;
import com.snypso.database.manager.DatabaseHandler.Order;
import com.snypso.database.manager.DatabaseHandler.OrderType;
import com.snypso.database.manager.LocalDataStorage.OrderHandler;
import com.snypso.database.manager.LocalDataStorage.User;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

class LanguageEditing {
    private Stage window;
    private List<Order> orderList;
    private Pane languagePane;
    private Pane addModePane;
    private Pane editModePane;
    private Pane deleteModePane;
    private Pane addTranslationPane;
    private Pane modifyTranslationPane;
    private Pane deleteTranslationPane;
    private User user;
    private VBox orderListContainer;
    private DatabaseHandler databaseHandler;
    LanguageEditing(DatabaseHandler databaseHandler, Stage window, List<Order> orderList, Pane languagePane, User user){
        this.window = window;
        this.orderList = orderList;
        this.languagePane = languagePane;
        this.user = user;
        this.databaseHandler = databaseHandler;
    }
    void launchLanguageEditor(){
        this.languagePane.getChildren().clear();
        Background background = new Background(new BackgroundFill(Paint.valueOf("#4c90ff"), CornerRadii.EMPTY, Insets.EMPTY));
        languagePane.setBackground(background);

        //System.out.println("\033[31;1;4mHello\033[0m");
        Background modifierBackground = new Background(new BackgroundFill(Paint.valueOf("#7caeff"), CornerRadii.EMPTY, Insets.EMPTY));
        Pane modifiersPane = new Pane();
        modifiersPane.setBackground(modifierBackground);
        modifiersPane.setMinSize(300, 280);
        modifiersPane.relocate(0,0);

        Pane imagePane = new Pane();
        Background imageBackground = new Background(new BackgroundFill(Paint.valueOf("#b2cfff"), CornerRadii.EMPTY, Insets.EMPTY));
        imagePane.setBackground(imageBackground);
        imagePane.setMinWidth(300);
        imagePane.relocate(0,0);
        File imageFile = new File("graphics/editLogo.png");
        Path path = imageFile.toPath();
        Image image = new Image(path.toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.relocate(0,20);
        imagePane.getChildren().add(imageView);
        modifiersPane.getChildren().add(imagePane);

        editModePane = new Pane();
        addToLanguagePane(editModePane);

        deleteModePane = new Pane();
        addToLanguagePane(deleteModePane);

        addModePane = new Pane();
        addToLanguagePane(addModePane);

        LanguageEditingAddLanguage languageEditingAddLanguage = new LanguageEditingAddLanguage(databaseHandler, addModePane,orderList,this);
        LanguageEditingModifyLanguage languageEditingModifyLanguage = new LanguageEditingModifyLanguage(databaseHandler, user,editModePane,orderList,this);
        LanguageEditingDeleteLanguage languageEditingDeleteLanguage = new LanguageEditingDeleteLanguage(databaseHandler, user,editModePane,orderList,this);


        addTranslationPane = new Pane();
        addToLanguagePane(addTranslationPane);

        modifyTranslationPane = new Pane();
        addToLanguagePane(modifyTranslationPane);

        deleteTranslationPane = new Pane();
        addToLanguagePane(deleteTranslationPane);


        LanguageEditingAddTranslation    languageEditingAddTranslation    = new LanguageEditingAddTranslation   (databaseHandler, user, addTranslationPane, orderList,this);
        LanguageEditingModifyTranslation languageEditingModifyTranslation = new LanguageEditingModifyTranslation(databaseHandler, user, modifyTranslationPane, orderList,this);
        LanguageEditingDeleteTranslation languageEditingDeleteTranslation = new LanguageEditingDeleteTranslation(databaseHandler, user, deleteTranslationPane, orderList,this);

        Button addLanguageButton = new Button();
        addLanguageButton.setText("Add Language");
        addLanguageButton.relocate(20,160);
        addLanguageButton.setOnMouseClicked((EventHandler<Event>) event -> {
            clearAllViews();
            languageEditingAddLanguage.display();
        });

        Button modifyLanguageButton = new Button();
        modifyLanguageButton.setText("Modify Language");
        modifyLanguageButton.relocate(20,200);
        modifyLanguageButton.setOnMouseClicked((EventHandler<Event>) event -> {
            clearAllViews();
            languageEditingModifyLanguage.display();
        });


        Button deleteLanguageButton = new Button();
        deleteLanguageButton.setText("Delete Language");
        deleteLanguageButton.relocate(20,240);
        deleteLanguageButton.setOnMouseClicked((EventHandler<Event>) event -> {
            clearAllViews();
            languageEditingDeleteLanguage.display();
        });


        Button addTranslationButton = new Button();
        addTranslationButton.setText("Add Translation");
        addTranslationButton.relocate(150,160);

        addTranslationButton.setOnMouseClicked((EventHandler<Event>) event -> {
            clearAllViews();
            languageEditingAddTranslation.display();
        });

        Button modifyTranslationButton = new Button();
        modifyTranslationButton.setText("Modify Translation");
        modifyTranslationButton.relocate(150,200);

        modifyTranslationButton.setOnMouseClicked((EventHandler<Event>) event -> {
            clearAllViews();
            languageEditingModifyTranslation.display();
        });

        Button deleteTranslationButton = new Button();
        deleteTranslationButton.setText("Delete Translation");
        deleteTranslationButton.relocate(150,240);

        deleteTranslationButton.setOnMouseClicked((EventHandler<Event>) event -> {
            clearAllViews();
            languageEditingDeleteTranslation.display();
        });


        modifiersPane.getChildren().add(addTranslationButton);
        modifiersPane.getChildren().add(modifyTranslationButton);
        modifiersPane.getChildren().add(deleteTranslationButton);
        modifiersPane.getChildren().add(addLanguageButton);
        modifiersPane.getChildren().add(modifyLanguageButton);
        modifiersPane.getChildren().add(deleteLanguageButton);

        languagePane.getChildren().add(modifiersPane);


        Pane modifyingOrdersPane = new Pane();
        modifyingOrdersPane.relocate(0, 280);
        modifyingOrdersPane.setMinSize(300, 580);
        Background modifyingOrdersBackground = new Background(new BackgroundFill(Paint.valueOf("#39537f"), CornerRadii.EMPTY, Insets.EMPTY));
        modifyingOrdersPane.setBackground(modifyingOrdersBackground);

        Label ordersLabel = new Label();
        ordersLabel.setText("Queued Orders");
        ordersLabel.relocate(100,15);
        ordersLabel.setTextFill(Paint.valueOf("#f4f4f4"));
        modifyingOrdersPane.getChildren().add(ordersLabel);


        ScrollPane queuedOrderScrollPane = new ScrollPane();
        queuedOrderScrollPane.setMinSize(300, 540);
        queuedOrderScrollPane.setMaxSize(300, 540);
        queuedOrderScrollPane.relocate(0,40);
        orderListContainer = new VBox(10);
        queuedOrderScrollPane.setContent(orderListContainer);

        modifyingOrdersPane.getChildren().add(queuedOrderScrollPane);

        languagePane.getChildren().add(modifyingOrdersPane);

        Pane verificationOrdersPane = new Pane();
        verificationOrdersPane.relocate(0, 860);
        verificationOrdersPane.setMinSize(300, 40);
        Background verificationOrdersBackground = new Background(new BackgroundFill(Paint.valueOf("#6187c6"), CornerRadii.EMPTY, Insets.EMPTY));
        verificationOrdersPane.setBackground(verificationOrdersBackground);

        Button verifyOrdersButton = new Button();
        verifyOrdersButton.relocate(100, 8);
        verifyOrdersButton.setText("Execute Orders");
        verificationOrdersPane.getChildren().add(verifyOrdersButton);

        languagePane.getChildren().add(verificationOrdersPane);

        updateOrders();

        Scene scene = new Scene(this.languagePane, 1700, 900);
        this.window.setScene(scene);
        this.window.setTitle("Språk Redigering");
    }
    void updateOrders(){
        orderListContainer.getChildren().clear();
        for (int i = 0; i < orderList.size(); i++){
            Order order = orderList.get(i);
            Pane pane = new Pane();
            Label label = new Label();
            label.setText(order.makeOrderText());
            Button button = new Button();
            button.setText("Remove");

            final int location = i;
            button.setOnMouseClicked((EventHandler<Event>) event -> {
                System.out.println("Removing Order");
                System.out.println(orderList.get(location).getName());
                orderList.remove(location);

                System.out.println("Removed Order, length:");
                System.out.println(orderList.size());
                updateOrders();
            });

            pane.getChildren().add(label);
            label.relocate(0,0);
            pane.getChildren().add(button);
            button.relocate(240, 7);
            pane.setMinHeight(120);
            pane.setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

            orderListContainer.getChildren().add(pane);

        }
        OrderHandler orderHandler = new OrderHandler();

        orderHandler.saveOrderList(orderList);

    }
    void clearAllViews(){
        clearAddView();
        clearEditView();
        clearDeleteView();
    }
    private void clearAddView(){
        addModePane.getChildren().clear();
        addTranslationPane.getChildren().clear();
    }
    private void clearEditView(){
        editModePane.getChildren().clear();
        modifyTranslationPane.getChildren().clear();
    }
    private void clearDeleteView(){
        deleteModePane.getChildren().clear();
        deleteTranslationPane.getChildren().clear();
    }
    private void addToLanguagePane(Pane pane){
        pane.relocate(300, 0);
        languagePane.getChildren().add(pane);
    }

}

