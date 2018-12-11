package com.snypso.database.manager.DatabaseHandler;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;

public class Translation {
    private String languageName;
    private String english;
    private String translation;
    private boolean selected;
    private Label label;
    public Translation(){
        selected = false;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if (this.selected){
            Background background = new Background(new BackgroundFill(Paint.valueOf("#7696c9"), CornerRadii.EMPTY, Insets.EMPTY));
            this.label.setBackground(background);
        }else {
            this.label.setBackground(null);
        }
    }
    public boolean getSelected(){
        return this.selected;
    }

    public void addLabel(Label label) {
        this.label = label;
    }
}
