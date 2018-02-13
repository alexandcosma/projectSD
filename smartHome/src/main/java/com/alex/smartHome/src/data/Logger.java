package com.alex.smartHome.src.data;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Date;

/**
 * Created by cosma on 06.05.2017.
 */
public class Logger {
    public void createXml(){
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.newDocument();

            Element logs = doc.createElement("logs");
            doc.appendChild(logs);

            Element tmpChg = doc.createElement("tmpChanges");
            logs.appendChild(tmpChg);

            Element statusChg = doc.createElement("statusChanges");
            logs.appendChild(statusChg);

            writeXml(doc);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void writeXml(Document doc){
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("logs.xml"));
            transformer.transform(source, result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addLog(Date date, String place, String rec, boolean tempChg){
        try {
            File inputFile = new File("logs.xml");

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(inputFile);

            if(tempChg) {
                NodeList temps = doc.getElementsByTagName("tmpChanges");
                Element event = doc.createElement("event");

                Attr a1 = doc.createAttribute("heatedPlace");
                a1.setValue(place);
                event.setAttributeNode(a1);

                Attr a2 = doc.createAttribute("date");
                a2.setValue(date.toString());
                event.setAttributeNode(a2);

                event.appendChild(doc.createTextNode(rec));
                temps.item(0).appendChild(event);
            }
            else{
                NodeList temps = doc.getElementsByTagName("statusChanges");
                Element event = doc.createElement("event");

                Attr a1 = doc.createAttribute("heatedPlace");
                a1.setValue(place);
                event.setAttributeNode(a1);

                Attr a2 = doc.createAttribute("date");
                a2.setValue(date.toString());
                event.setAttributeNode(a2);

                event.appendChild(doc.createTextNode(rec));
                temps.item(0).appendChild(event);
            }

            writeXml(doc);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getInfo(String place){
        String result = new String();
        try {
            File inputFile = new File("logs.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(inputFile);

            NodeList nList = doc.getElementsByTagName("tmpChanges");
            if(nList.item(0).getNodeType() == Node.ELEMENT_NODE){
                Element el = (Element) nList.item(0);
                NodeList nl = el.getElementsByTagName("event");
                for(int i=0;i<nl.getLength();i++){
                    if(nl.item(i).getAttributes().getNamedItem("heatedPlace").getNodeValue().equals(place)){
                        result = result.concat(nl.item(i).getAttributes().getNamedItem("date").getNodeValue() + "\n");
                        result = result.concat(nl.item(i).getTextContent() + "\n");
                    }
                }
                result = result.concat("\n");
            }

            nList = doc.getElementsByTagName("statusChanges");
            if(nList.item(0).getNodeType() == Node.ELEMENT_NODE){
                Element el = (Element) nList.item(0);
                NodeList nl = el.getElementsByTagName("event");
                for(int i=0;i<nl.getLength();i++){
                    if(nl.item(i).getAttributes().getNamedItem("heatedPlace").getNodeValue().equals(place)){
                        result = result.concat(nl.item(i).getAttributes().getNamedItem("date").getNodeValue() + "\n");
                        result = result.concat(nl.item(i).getTextContent() + "\n");
                    }
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /*public static void main(String args[]){
        //addLog(new Date(), "room2", "OFF", false);
        //addLog(new Date(), "room1", "ON", false);
        System.out.print(getInfo("room2"));
    }*/
}
