package com.snypso.database.manager.DatabaseHandler;

import com.snypso.database.manager.LocalDataStorage.User;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler {
    boolean isLatest;
    boolean translationsAdded;
    List<Language> languages;
    public DatabaseHandler(){
        isLatest = false;
    }


    public boolean testConnection(User user){

        boolean languages = testConnection(user, "trampettlanguages");
        boolean series = testConnection(user, "trampettrulesets");

        if (languages && series){
            return true;
        }else {
            return false;
        }

    }

    private boolean testConnection(User user, String database){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://snypso.com/unrelated/trampapp/php/edit.php"+"?username="+user.getUsername().toLowerCase()+"&password="+user.getPassword()+"&query=testconnection"+"&database="+database)
                .build();

        String result = "";
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            result = response.body().string();

        }catch (Exception e){
            e.printStackTrace();
        }

        if (result.equals("connection established")){
            return true;
        }else {
            return false;
        }
    }


    public List<Language> getAllLanguages(User user) {
        if (!isLatest) {
            List<Language> languageList = new ArrayList<>();
            List<JSONObject> objectList = runQuery("https://snypso.com/unrelated/trampapp/php/edit.php" + "?username=" + user.getUsername().toLowerCase() + "&password=" + user.getPassword() + "&query=allLanguages" + "&database=" + "trampettlanguages");

            for (int i = 0; i < objectList.size(); i++) {
                Language language = new Language();
                language.setName(objectList.get(i).getString("Name"));
                language.setVersion(objectList.get(i).getString("Version"));
                language.setAmountOfTranslations(getAmountOfTranslations(user, language.getName()));
                languageList.add(language);
            }

            this.languages = languageList;
            isLatest = true;
            return languageList;
        }else {
            return languages;
        }
    }

    private int getAmountOfTranslations(User user, String name){

        List<JSONObject> objectList = runQuery("https://snypso.com/unrelated/trampapp/php/edit.php"+"?username="+user.getUsername().toLowerCase()+"&password="+user.getPassword()+"&query=amountTrans"+"&database="+"trampettlanguages"+"&query2="+name);
        String numberText = null;
        for (JSONObject jsonObject : objectList) {
            numberText = jsonObject.getString("numberOfTranslations");
        }

        if (numberText != null){
            return Integer.parseInt(numberText);
        }else {
            return 0;
        }
    }

    public void addAllTranslations(List<Language> languages, User user) {
        if (!translationsAdded) {
            for (Language language : languages) {
                addTranslations(language, user);
            }
            translationsAdded = true;
        }
    }
    void addTranslations(Language language, User user){
        //Language duplicate = findSameLanguage(language);

        List<JSONObject> objectList = runQuery("https://snypso.com/unrelated/trampapp/php/edit.php"+"?username="+user.getUsername().toLowerCase()+"&password="+user.getPassword()+"&query=getTranslations"+"&database="+"trampettlanguages"+"&query2="+language.getName());

        for (JSONObject jsonObject : objectList) {
            Translation translation = new Translation();

            translation.setLanguageName(language.getName());
            translation.setEnglish(jsonObject.getString("English"));
            translation.setTranslation(jsonObject.getString("Translated"));
            language.addTranslation(translation);
        }
    }

    private Language findSameLanguage(Language language) {
        boolean found = false;
        Language ret = null;
        List<Language> languages1 = this.languages;
        for (int i = 0; i < languages1.size(); i++) {
            Language local = languages1.get(i);
            if (local.getName().equals(language.getName())) {
                found = true;
                ret = local;
            }
        }
        if (found){
            return ret;
        }else {
            this.languages.add(language);
            return null;
        }
    }


    private List<JSONObject> runQuery(String query){

        OkHttpClient client = new OkHttpClient();
        List<JSONObject> objectList = new ArrayList<>();

        Request request = new Request.Builder()
                .url(query)
                .build();

        String result;
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            result = response.body().string();

            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                objectList.add(jsonArray.getJSONObject(i));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return objectList;

    }

    public void runOrders(){
        isLatest = false;
        translationsAdded = false;
    }

}
