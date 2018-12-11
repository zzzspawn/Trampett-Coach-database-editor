package com.snypso.database.manager.LocalDataStorage;

import java.io.*;

public class UserHandler {

    public User getUserData(){

        File file = new File("userdata/userdata.txt");
        if (file.exists() && !file.isDirectory()){
            User user = new User();
            try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            while (line != null){

                if (line.contains("User|")){
                    String[] userArray = line.split("\\|");
                    user.setUsername(userArray[1]);
                }else if (line.contains("Pass|")){
                    String[] userArray = line.split("\\|");
                    user.setPassword(userArray[1]);
                }

                line = bufferedReader.readLine();
            }


            }catch (Exception e){
                e.printStackTrace();
            }

            return user;
        }else {
            return new User();
        }
    }

    public void saveUserData(User user){
        File file = new File("userdata/userdata.txt");

        try {
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        if (user.getUsername() != null) {
            bufferedWriter.write("User|" + user.getUsername());
            bufferedWriter.newLine();
        }

        if (user.getPassword() != null) {
            bufferedWriter.write("Pass|" + user.getPassword());
            bufferedWriter.newLine();
        }

        bufferedWriter.flush();
        bufferedWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
