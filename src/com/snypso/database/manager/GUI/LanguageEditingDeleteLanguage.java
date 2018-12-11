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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;

public class LanguageEditingDeleteLanguage {
    User user;
    Pane deleteModePane;
    Pane informationDisplayLanguageDeletePane;
    List<Order> orderList;
    LanguageEditing languageEditing;
    DatabaseHandler databaseHandler;
    public LanguageEditingDeleteLanguage(DatabaseHandler databaseHandler, User user, Pane deleteModePane, List<Order> orderList, LanguageEditing languageEditing){
        this.user = user;
        this.deleteModePane = deleteModePane;
        this.informationDisplayLanguageDeletePane = new Pane();
        this.orderList = orderList;
        this.languageEditing = languageEditing;
        this.databaseHandler = databaseHandler;
    }

    void display(){
        Label titleLabel = new Label();
        titleLabel.setText("Delete Language");
        titleLabel.relocate(100, 10);
        deleteModePane.getChildren().add(titleLabel);

        Label languageTitleLabel = new Label();
        languageTitleLabel.setText("Existing Languages");
        languageTitleLabel.relocate(100, 50);
        deleteModePane.getChildren().add(languageTitleLabel);

        ScrollPane existingLanguagesScrollPane = new ScrollPane();
        VBox existingLanguagesContainer = new VBox();
        existingLanguagesScrollPane.setContent(existingLanguagesContainer);
        existingLanguagesScrollPane.relocate(100,80);
        existingLanguagesScrollPane.setMinSize(500, 300);
        existingLanguagesScrollPane.setMaxSize(500, 300);

        deleteModePane.getChildren().add(existingLanguagesScrollPane);

        informationDisplayLanguageDeletePane = new Pane();
        informationDisplayLanguageDeletePane.relocate(700, 80);
        deleteModePane.getChildren().add(informationDisplayLanguageDeletePane);

        addExistingLanguagesDelete(existingLanguagesContainer);
    }
    private void addExistingLanguagesDelete(VBox languageContainer){

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
                displaySelectedLanguageForDeletion(language);
            });
            languageContainer.getChildren().add(pane);
        }
    }
    private void displaySelectedLanguageForDeletion(Language language){
        Order order = new Order();
        informationDisplayLanguageDeletePane.getChildren().clear();

        Label languageNameTitleLabel = new Label();
        languageNameTitleLabel.setText("Language To delete");
        languageNameTitleLabel.relocate(0,0);

        informationDisplayLanguageDeletePane.getChildren().add(languageNameTitleLabel);

        Label nameLabel = new Label();
        nameLabel.setText(language.getName());
        nameLabel.relocate(0,40);

        informationDisplayLanguageDeletePane.getChildren().add(nameLabel);

        Label versionLabelTitle = new Label();
        versionLabelTitle.setText("Version");
        versionLabelTitle.relocate(0,80);

        informationDisplayLanguageDeletePane.getChildren().add(versionLabelTitle);

        Label versionLabel = new Label();
        versionLabel.setText(language.getVersion());
        versionLabel.relocate(0,120);

        informationDisplayLanguageDeletePane.getChildren().add(versionLabel);

        Label amountLabelTitle = new Label();
        amountLabelTitle.setText("Amount of Translations");
        amountLabelTitle.relocate(0,160);

        informationDisplayLanguageDeletePane.getChildren().add(amountLabelTitle);

        Label amountLabel = new Label();
        amountLabel.setText(Integer.toString(language.getAmountOfTranslations()));
        amountLabel.relocate(0,200);

        informationDisplayLanguageDeletePane.getChildren().add(amountLabel);


        Button confirmButton = new Button();
        confirmButton.setText("Confirm Order");
        confirmButton.relocate(100, 380);
        informationDisplayLanguageDeletePane.getChildren().add(confirmButton);
        LanguageEditing editor = this.languageEditing;

        confirmButton.setOnMouseClicked((EventHandler<Event>) event -> {
            order.setName(language.getName());
            order.setType(OrderType.DELETE_LANGUAGE);
            orderList.add(order);
            editor.updateOrders();
        });
    }
}
