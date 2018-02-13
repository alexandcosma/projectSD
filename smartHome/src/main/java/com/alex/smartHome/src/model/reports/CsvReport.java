package com.alex.smartHome.src.model.reports;

import data.Logger;

import java.io.FileWriter;

/**
 * Created by cosma on 06.05.2017.
 */
public class CsvReport implements Report {
    private static String content;

    public void write(String place){
        getContent(place);
        try {
            FileWriter fileWriter = new FileWriter("reports/report.csv");
            fileWriter.append("date, change\n");
            String[] rs = content.split("\n");
            for(int i=0;i<rs.length;i++){
                if(rs[i].length()!=0) {
                    if (rs[i].length()>5)
                        fileWriter.append("\"" + rs[i] + "\",");
                    else
                        fileWriter.append("\"" + rs[i] + "\"\n");
                }
            }
            fileWriter.flush();
            fileWriter.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void getContent(String place) {
        content = (new Logger().getInfo(place));
    }

    /*public static void main(String args[]){
        writee("room2");
    }*/
}
