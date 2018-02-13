package com.alex.smartHome.src.communication;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by cosma on 30.05.2017.
 */
public class WeatherServerConnection {
    private String url;
    private Document doc;


    public WeatherServerConnection(String url){
        this.url = url;
    }

    public String getTemperature() {
        readXml();
        return extractTemperatureValue();
    }

    private String extractTemperatureValue() {
        NodeList nList = doc.getElementsByTagName("temperature");
        if(nList.item(0).getNodeType() == Node.ELEMENT_NODE){
            Element el = (Element) nList.item(0);
            return el.getAttribute("value");
        }
        return "error";
    }

    private void readXml() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(url);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
