package com.snypso.database.manager.DatabaseHandler;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;

public class Language {
    private String name;
    private String version;
    private int amountOfTranslations;
    private boolean selected;
    private Pane pane;
    private List<Translation> translations;
    Language(){
        selected = false;
        translations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    void setVersion(String version) {
        this.version = version;
    }

    public int getAmountOfTranslations() {
        return amountOfTranslations;
    }

    void setAmountOfTranslations(int amountOfTranslations) {
        this.amountOfTranslations = amountOfTranslations;
    }

    public void setSelected(boolean value) {
        this.selected = value;
        if (this.selected){
            Background background = new Background(new BackgroundFill(Paint.valueOf("#7696c9"), CornerRadii.EMPTY, Insets.EMPTY));
            this.pane.setBackground(background);
        }else {
            this.pane.setBackground(null);
        }
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public boolean isSelected() {
        return selected;
    }

    public Pane getPane() {
        return pane;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }
    public void addTranslation(Translation translation){
        this.translations.add(translation);
    }
    public Translation fetchTranslation(int index){
        return this.translations.get(index);
    }
}
