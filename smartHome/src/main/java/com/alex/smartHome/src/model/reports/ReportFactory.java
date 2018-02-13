package com.alex.smartHome.src.model.reports;

/**
 * Created by cosma on 06.05.2017.
 */
public class ReportFactory {
    public Report getReport(String type){

        if(type == null)
            return null;

        if(type.equalsIgnoreCase("PDF"))
            return new PdfReport();

        if(type.equalsIgnoreCase("CSV"))
            return new CsvReport();

        return null;
    }
}
