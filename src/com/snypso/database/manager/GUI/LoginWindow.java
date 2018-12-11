package com.snypso.database.manager.GUI;

import com.snypso.database.manager.DatabaseHandler.DatabaseHandler;
import com.snypso.database.manager.LocalDataStorage.User;
import com.snypso.database.manager.LocalDataStorage.UserHandler;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LoginWindow {
    LanguageEditing languageEditingController;
    RulesetEditing rulesetEditingController;
    Stage window;
    User user;
    UserHandler userHandler;
    Pane loginPane;
    DatabaseHandler databaseHandler;
    public LoginWindow(DatabaseHandler databaseHandler, Pane loginPane, LanguageEditing languageEditingController, RulesetEditing rulesetEditingController, Stage window, UserHandler userHandler, User user){
        this.languageEditingController = languageEditingController;
        this.rulesetEditingController = rulesetEditingController;
        this.window = window;
        this.user = user;
        this.userHandler = userHandler;
        this.loginPane = loginPane;
        this.databaseHandler = databaseHandler;
    }

    void launchLoginWindow(){
        List<Node> listOfComponents = new ArrayList<>();

        File imageFile = new File("graphics/editLogo.png");
        Path path = imageFile.toPath();
        Image image = new Image(path.toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.relocate(0,10);
        listOfComponents.add(imageView);

        Label usernameLabel = new Label();
        usernameLabel.setText("Username");
        usernameLabel.relocate(75, 130);
        listOfComponents.add(usernameLabel);

        TextField userName = new TextField();
        userName.setPromptText("Username");
        userName.relocate(75, 150);
        if (user.getUsername() != null){
            userName.setText(user.getUsername());
        }
        listOfComponents.add(userName);

        Label passwordLabel = new Label();
        passwordLabel.setText("Password");
        passwordLabel.relocate(75, 180);
        listOfComponents.add(passwordLabel);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.relocate(75, 200);
        if (user.getPassword() != null){
            passwordField.setText(user.getPassword());
        }
        listOfComponents.add(passwordField);

        Label checkboxLabel = new Label();
        checkboxLabel.setText("Store Credentials");
        checkboxLabel.relocate(100, 240);
        listOfComponents.add(checkboxLabel);

        CheckBox checkBox = new CheckBox();
        checkBox.setSelected(true);
        checkBox.relocate(75, 240);
        listOfComponents.add(checkBox);

        Label succeeded = new Label();
        succeeded.setTextFill(Color.GREEN);
        succeeded.setText("Connection established");
        succeeded.relocate(122, 300);
        succeeded.setVisible(false);
        listOfComponents.add(succeeded);

        Label failed = new Label();
        failed.setTextFill(Color.RED);
        failed.setText("Connection failed");
        failed.relocate(122, 300);
        failed.setVisible(false);
        listOfComponents.add(failed);

        Button testConnectionButton = new Button();
        testConnectionButton.setText("Test Connection");
        testConnectionButton.relocate(122, 275);
        listOfComponents.add(testConnectionButton);

        Label dbSelectorLabel = new Label();
        dbSelectorLabel.setText("Select Database");
        dbSelectorLabel.relocate(100, 330);
        dbSelectorLabel.setVisible(false);
        listOfComponents.add(dbSelectorLabel);

        Button selectLanguageButton = new Button();
        selectLanguageButton.setText("Language");
        selectLanguageButton.relocate(75, 350);
        selectLanguageButton.setVisible(false);
        listOfComponents.add(selectLanguageButton);

        Button selectRulesetsButton = new Button();
        selectRulesetsButton.setText("Reglement");
        selectRulesetsButton.relocate(150, 350);
        selectRulesetsButton.setVisible(false);
        listOfComponents.add(selectRulesetsButton);


        selectLanguageButton.setOnMouseClicked((EventHandler<Event>) event -> {
            languageEditingController.launchLanguageEditor();
        });

        selectRulesetsButton.setOnMouseClicked((EventHandler<Event>) event -> {
            rulesetEditingController.launchRulesetEditor();
        });

        testConnectionButton.setOnMouseClicked((EventHandler<Event>) event -> {

            User user = new User(userName.getText(), passwordField.getText());
            if (checkBox.isSelected()){
                userHandler.saveUserData(user);
            }

            boolean connected = databaseHandler.testConnection(user);

            if (connected){
                failed.setVisible(false);
                succeeded.setVisible(true);
                System.out.println("Connection Succeed");
                selectRulesetsButton.setVisible(true);
                selectLanguageButton.setVisible(true);
                dbSelectorLabel.setVisible(true);
                this.user = user;
            }else {
                succeeded.setVisible(false);
                failed.setVisible(true);
                System.out.println("Connection Failed");
            }
        });

        for (int i = 0; i < listOfComponents.size(); i++){
            addToPane(listOfComponents.get(i), this.loginPane);
        }

        Scene scene = new Scene(this.loginPane,300,500);
        window.setScene(scene);
        window.setTitle("Login");
        window.show();
        setWindowIcon(window);


        skipAhead(new User(userName.getText(), passwordField.getText()));
    }
    private void skipAhead(User user){
        this.user = user;
        languageEditingController.launchLanguageEditor();
    }



    private void addToPane(Node node, Pane pane){
        pane.getChildren().add(node);
    }

    private void setWindowIcon(Stage stage){
        File imageFile = new File("graphics/Icon64.png");
        Path path = imageFile.toPath();
        Image image = new Image(path.toUri().toString());
        stage.getIcons().add(image);
    }



}
