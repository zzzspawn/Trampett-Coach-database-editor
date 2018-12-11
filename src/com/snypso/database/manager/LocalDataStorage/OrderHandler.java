package com.snypso.database.manager.LocalDataStorage;

import com.snypso.database.manager.DatabaseHandler.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderHandler {

    public OrderHandler(){

    }

    public void saveOrderList(List<Order> orderList){

        if (orderList != null && orderList.size() > 0) {
            //add nullchecks for all values, and save accordingly
            File file = new File("userdata/orderStorage.txt");
            try {
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                for (Order order : orderList) {
                    bufferedWriter.write(order.stringify());
                    bufferedWriter.newLine();
                }
                bufferedWriter.flush();
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            File file = new File("userdata/orderStorage.txt");
            if (file.exists() && !file.isDirectory()){
                file.delete();
            }
        }
    }

    public List<Order> loadOrderList(){
        List<Order> orderList = new ArrayList<>();

        //do loading here, return empty list if no orders saved
        File file = new File("userdata/orderStorage.txt");
        if (file.exists() && !file.isDirectory()) {
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line = bufferedReader.readLine();
                String[] testArray = line.split("\\|");
                System.out.println(testArray.length);
                int i = 0;
                for (String a : testArray){
                    System.out.println("Line, number: " + i);
                    System.out.println("\""+a+"\"");
                    System.out.println("Line end");
                    i++;
                }

                while (line != null) {
                    Order order = new Order();
                    order.deStringify(line);
                    orderList.add(order);
                    line = bufferedReader.readLine();
                }

                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderList;
    }

}
