package com.snypso.database.manager.GUI;

import com.snypso.database.manager.DatabaseHandler.*;
import com.snypso.database.manager.LocalDataStorage.User;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;

class LanguageEditingModifyTranslation {

    private User user;
    private Pane mainPane;
    private List<Order> orderList;
    private Pane inputPane;
    private LanguageEditing languageEditing;
    private DatabaseHandler databaseHandler;
    private Language selectedLanguage;
    private Translation selectedTranslation;
    LanguageEditingModifyTranslation(DatabaseHandler databaseHandler, User user, Pane mainPane, List<Order> orderList, LanguageEditing languageEditing){
        this.user = user;
        this.mainPane = mainPane;
        this.orderList = orderList;
        this.languageEditing = languageEditing;
        this.databaseHandler = databaseHandler;
    }

    void display() {
        Label mainTitle = new Label("Modify Translation");
        mainTitle.relocate(10,10);
        mainPane.getChildren().add(mainTitle);


        List<Language> languages = databaseHandler.getAllLanguages(user);

        databaseHandler.addAllTranslations(languages, user);

        Label labelChoicebox = new Label("Pick Language");
        labelChoicebox.relocate(100,75);
        mainPane.getChildren().add(labelChoicebox);

        ChoiceBox<String> cb = new ChoiceBox<>();
        for (Language language : languages){
            cb.getItems().add(language.getName());
        }

        cb.getSelectionModel()
                .selectedItemProperty()
                .addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    selectedLanguage = findLanguage(languages, newValue);
                    displayTranslations(selectedLanguage);
                });


        cb.relocate(100,100);
        mainPane.getChildren().add(cb);


        Button confirmButton = new Button();
        confirmButton.setText("Confirm Order");
        confirmButton.relocate(900, 650);
        mainPane.getChildren().add(confirmButton);

        LanguageEditing editor = this.languageEditing;
        confirmButton.setOnMouseClicked((EventHandler<Event>) event -> {
            Order order = new Order();
            String original = originalTextField.getText();
            String translated = translationTextField.getText();
            if (!original.equals("") && !translated.equals("")){
                order.setType(OrderType.MODIFY_TRANSLATION);
                order.setName(selectedLanguage.getName());
                order.setOriginal(original);
                order.setTranslation(translated);
                order.setPreviousOriginal(selectedTranslation.getEnglish());
                order.setPreviousTranslation(selectedTranslation.getTranslation());
                orderList.add(order);
                addInputFields();
                editor.updateOrders();
            }else {
                //Toast/prompt asking to fill out field
                System.out.println("Please Fill in empty field");
            }

        });

        scrollPaneLabel = new Label("Existing Translations");
        scrollPaneLabel.relocate(230,75);
        mainPane.getChildren().add(scrollPaneLabel);

        scrollPane = new ScrollPane();
        scrollPane.relocate(230, 100);
        scrollPane.setMinSize(500,500);
        scrollPane.setMaxSize(500,500);

        scrollContent = new VBox();
        scrollContent.setSpacing(5);
        scrollPane.setContent(scrollContent);

        mainPane.getChildren().add(scrollPane);
        scrollPane.setVisible(false);
        scrollPaneLabel.setVisible(false);

        inputPane = new Pane();
        mainPane.getChildren().add(inputPane);
        inputPane.relocate(780,100);
        inputPane.setVisible(false);
        addInputFields();
    }
    private TextField originalTextField;
    private TextField translationTextField;
    private void addInputFields(){
        inputPane.getChildren().clear();

        Label originalLabel = new Label("Original");

        Label translationLabel = new Label("Translation");

        originalTextField = new TextField();
        originalTextField.setPromptText("Original Text");

        translationTextField = new TextField();
        translationTextField.setPromptText("Translated Text");

        inputPane.getChildren().add(originalLabel);
        inputPane.getChildren().add(translationLabel);
        inputPane.getChildren().add(originalTextField);
        inputPane.getChildren().add(translationTextField);

        originalLabel.relocate(0,0);
        originalTextField.relocate(0, 30);
        translationLabel.relocate(0, 80);
        translationTextField.relocate(0,110);

        originalTextField.setMinWidth(400);
        translationTextField.setMinWidth(400);
    }


    private VBox scrollContent;
    private ScrollPane scrollPane;
    private Label scrollPaneLabel;

    private void displayTranslations(Language language){
        //in add we just make existing translations visible
        inputPane.setVisible(false);
        scrollContent.getChildren().clear();
        if (language != null){
            scrollPane.setVisible(true);
            scrollPaneLabel.setVisible(true);
            List<Translation> translations = language.getTranslations();
            if (translations != null && translations.size() > 0){
                //display scrollable list of translations
                for (Translation translation : translations) {
                    Label label = new Label();
                    label.setText("Original: \"" + translation.getEnglish() + "\", Translated: \"" + translation.getTranslation() + "\"");
                    label.minWidth(498);
                    label.minHeight(20);
                    scrollContent.getChildren().add(label);
                    translation.addLabel(label);
                    label.setOnMouseClicked((EventHandler<Event>) event -> {
                        for (Translation trans : language.getTranslations()) {
                            trans.setSelected(false);
                        }
                        selectedTranslation = translation;
                        translation.setSelected(true);
                        displaySelectedTranslation(translation);
                    });
                }
            }else {
                if (translations == null){
                    System.out.println("Translations was null");
                }else {
                    System.out.println("Translations size was less than 1");
                }
            }
        }else {
            //something went wrong
            System.out.println("This shouldn't happen");
        }
    }

    private void displaySelectedTranslation(Translation translation) {
        originalTextField.setText(translation.getEnglish());
        translationTextField.setText(translation.getTranslation());
        inputPane.setVisible(true);
    }

    private Language findLanguage(List<Language> languages, String name){
        for (Language language : languages){
            if (language.getName().equals(name)){
                return language;
            }
        }
        return null;
    }
}
