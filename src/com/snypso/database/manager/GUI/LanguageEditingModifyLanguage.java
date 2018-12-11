package com.snypso.database.manager.GUI;

import com.snypso.database.manager.DatabaseHandler.DatabaseHandler;
import com.snypso.database.manager.DatabaseHandler.Language;
import com.snypso.database.manager.DatabaseHandler.Order;
import com.snypso.database.manager.DatabaseHandler.OrderType;
import com.snypso.database.manager.LocalDataStorage.User;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;

public class LanguageEditingModifyLanguage {

    User user;
    Pane editModePane;
    Pane informationDisplayLanguageModifyPane;
    List<Order> orderList;
    LanguageEditing languageEditing;
    DatabaseHandler databaseHandler;
    public LanguageEditingModifyLanguage(DatabaseHandler databaseHandler, User user, Pane editModePane, List<Order> orderList, LanguageEditing languageEditing){
        this.user = user;
        this.editModePane = editModePane;
        this.informationDisplayLanguageModifyPane = new Pane();
        this.orderList = orderList;
        this.languageEditing = languageEditing;
        this.databaseHandler = databaseHandler;
    }

    void display(){
        Label titleLabel = new Label();
        titleLabel.setText("Modify Language");
        titleLabel.relocate(100, 10);
        editModePane.getChildren().add(titleLabel);

        Label languageTitleLabel = new Label();
        languageTitleLabel.setText("Existing Languages");
        languageTitleLabel.relocate(100, 50);
        editModePane.getChildren().add(languageTitleLabel);

        ScrollPane existingLanguagesScrollPane = new ScrollPane();
        VBox existingLanguagesContainer = new VBox();
        existingLanguagesScrollPane.setContent(existingLanguagesContainer);
        existingLanguagesScrollPane.relocate(100,80);
        existingLanguagesScrollPane.setMinSize(500, 300);
        existingLanguagesScrollPane.setMaxSize(500, 300);

        editModePane.getChildren().add(existingLanguagesScrollPane);

        addExistingLanguagesMod(existingLanguagesContainer);


        informationDisplayLanguageModifyPane = new Pane();
        informationDisplayLanguageModifyPane.relocate(700, 80);
        editModePane.getChildren().add(informationDisplayLanguageModifyPane);

    }
    private void addExistingLanguagesMod(VBox languageContainer){

        List<Language> languages = databaseHandler.getAllLanguages(this.user);
        for (Language language : languages) {
            Pane pane = new Pane();
            pane.setMinWidth(498);
            Label label = new Label();
            label.setText(language.getName());
            pane.getChildren().add(label);
            language.setPane(pane);
            pane.setOnMouseClicked((EventHandler<Event>) event -> {
                for (Language lang : languages) {
                    lang.setSelected(false);
                }
                language.setSelected(true);
                displaySelectedLanguageMod(language);
            });
            languageContainer.getChildren().add(pane);
        }
    }
    private void displaySelectedLanguageMod(Language language){
        Order order = new Order();
        order.setPreviousName(language.getName());
        informationDisplayLanguageModifyPane.getChildren().clear();

        Label languageNameTitleLabel = new Label();
        languageNameTitleLabel.setText("Name");
        languageNameTitleLabel.relocate(0,0);

        informationDisplayLanguageModifyPane.getChildren().add(languageNameTitleLabel);

        TextField textField = new TextField();
        textField.setText(language.getName());
        textField.relocate(0,40);

        informationDisplayLanguageModifyPane.getChildren().add(textField);

        Label versionLabelTitle = new Label();
        versionLabelTitle.setText("Version");
        versionLabelTitle.relocate(0,80);

        informationDisplayLanguageModifyPane.getChildren().add(versionLabelTitle);

        Label versionLabel = new Label();
        versionLabel.setText(language.getVersion());
        versionLabel.relocate(0,120);

        informationDisplayLanguageModifyPane.getChildren().add(versionLabel);

        Label amountLabelTitle = new Label();
        amountLabelTitle.setText("Amount of Translations");
        amountLabelTitle.relocate(0,160);

        informationDisplayLanguageModifyPane.getChildren().add(amountLabelTitle);

        Label amountLabel = new Label();
        amountLabel.setText(Integer.toString(language.getAmountOfTranslations()));
        amountLabel.relocate(0,200);

        informationDisplayLanguageModifyPane.getChildren().add(amountLabel);


        Button confirmButton = new Button();
        confirmButton.setText("Confirm Order");
        confirmButton.relocate(1000, 380);
        editModePane.getChildren().add(confirmButton);
        LanguageEditing editor = this.languageEditing;
        confirmButton.setOnMouseClicked((EventHandler<Event>) event -> {
            order.setName(textField.getText());
            order.setType(OrderType.MODIFY_LANGUAGE);
            orderList.add(order);
            displaySelectedLanguageMod(language);
            editor.updateOrders();
        });
    }
}
