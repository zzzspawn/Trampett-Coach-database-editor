package com.snypso.database.manager.DatabaseHandler;

public class Order {
    OrderType type;
    String name;
    String previousName;
    String original;
    String translation;
    String previousOriginal;
    String previousTranslation;
    public Order(){

    }

    public String getPreviousOriginal() {
        return previousOriginal;
    }

    public void setPreviousOriginal(String previousOriginal) {
        this.previousOriginal = previousOriginal;
    }

    public String getPreviousTranslation() {
        return previousTranslation;
    }

    public void setPreviousTranslation(String previousTranslation) {
        this.previousTranslation = previousTranslation;
    }

    public void setType(OrderType type){
        this.type = type;
    }

    public OrderType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String makeOrderText() {
        if (this.type == OrderType.ADD_LANGUAGE){
            return "Add Lang \"" + this.name + "\"";
        }else if (this.type == OrderType.MODIFY_LANGUAGE){
            return "Mod Lang \"" + this.previousName + "\"" + "\nTo: \"" + this.name + "\"";
        }else if (this.type == OrderType.DELETE_LANGUAGE){
            return "Del Lang \"" + this.name + "\"";
        }else if (this.type == OrderType.ADD_TRANSLATION){
            return "Add Translation to \"" + this.name + "\"" + "\nOr:\""+this.original + "\"\nTr: \"" + this.translation + "\"";
        }else if (this.type == OrderType.MODIFY_TRANSLATION){
            return "Mod Translation of \"" + this.name + "\"" +
                    "\n"+
                    "Original"+
                    "\n"+
                    "From: \""+this.getPreviousOriginal() +"\""+
                    "\n"+
                    "To: \"" + this.getOriginal() + "\""+
                    "\n"+
                    "Translated:"+
                    "\n"+
                    "From: \"" + this.getPreviousTranslation() + "\""+
                    "\n"+
                    "To: \"" + this.getTranslation() + "\"";
        }else {
            return "Order type not supported yet";
        }
    }

    public void setPreviousName(String name) {
        this.previousName = name;
    }

    public String stringify() {
        StringBuilder text = new StringBuilder();
        text.append("Type|");
        text.append(type.toString());
        text.append("|");
        text.append("Name|");
        if (name != null){
            text.append(name);
        }
        text.append("|");
        text.append("PreviousName|");
        if (previousName != null){
            text.append(previousName);
        }
        text.append("|");
        text.append("Original|");
        if (original != null){
            text.append(original);
        }
        text.append("|");
        text.append("Translation|");
        if (translation != null){
            text.append(translation);
        }else {
            text.append(" ");
        }
        text.append("|");
        text.append("PreviousTranslation|");
        if (previousTranslation != null){
            text.append(previousTranslation);
        }else {
            text.append(" ");
        }
        text.append("|");

        text.append("PreviousOriginal|");
        if (previousOriginal != null){
            text.append(previousOriginal);
        }else {
            text.append(" ");
        }
        text.append("|");

        return text.toString();
    }

    public void deStringify(String text){
        String[] valueArray = text.split("\\|");
        type = OrderType.valueOf(valueArray[1]);
        if (!valueArray[3].equals("")){
            this.name = valueArray[3];
        }
        if (!valueArray[5].equals("")){
            this.previousName = valueArray[5];
        }
        if (!valueArray[7].equals("")){
            this.original = valueArray[7];
        }
        if (!valueArray[9].equals("") && !valueArray[9].equals(" ")){
            this.translation = valueArray[9];
        }
        if (!valueArray[11].equals("") && !valueArray[11].equals(" ")){
            this.previousTranslation = valueArray[11];
        }
        if (!valueArray[13].equals("") && !valueArray[13].equals(" ")){
            this.previousOriginal = valueArray[13];
        }
    }
}