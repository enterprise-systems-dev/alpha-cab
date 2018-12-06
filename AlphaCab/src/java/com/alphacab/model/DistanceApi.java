package com.alphacab.model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.lang.model.element.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import jdk.internal.org.xml.sax.InputSource;
import jdk.internal.org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class DistanceApi {
    
    //addresses
    String originPostcode;
    String originStreetAddress;
    String destinationPostcode;
    String destinationStreetAddress;
    
    //url - latitude and longitude
    double a1;   //origin longitude
    double a2; //origin latitude
    double b1;   //destination longitude
    double b2; //destination latitude
    
    //maps
    String key = "AhVIJRdT1xmACmjPERaW9X4lpZzj_TuXKAwqtARN6F4SQmjFGlO49rtlnk5MMKMg"; //bing maps key
    
    //xml
    Document doc;
    
    //resultant distance
    double distance;
    
    public DistanceApi(String originPostcode, String originStreetAddress, String destinationPostcode, String destinationStreetAddress){
        this.originPostcode = originPostcode;
        this.originStreetAddress = originStreetAddress;
        this.destinationPostcode = destinationPostcode;
        this.destinationStreetAddress = destinationStreetAddress;
    }
    
    public double getDistance(){
        return this.distance;
    }
    
    public boolean calculateDistance(){
    try{
        System.out.println(originPostcode);
        System.out.println(originStreetAddress);
        System.out.println(destinationPostcode);
        System.out.println(destinationStreetAddress);
        
        //generate XML document for getting lat and lon from origin
        getXMLDoc("http://dev.virtualearth.net/REST/v1/Locations/UK/"+originPostcode+"?"+originStreetAddress+"&key="+key+"&output=xml");
        
        //get origin lat and lon
        NodeList nl = doc.getElementsByTagName("Point").item(0).getChildNodes();
        a1 = Double.valueOf(nl.item(0).getTextContent());
        a2 = Double.valueOf(nl.item(1).getTextContent());
        System.out.println("a1: " + a1);
        System.out.println("a2: " + a2);
        
        //generate XML document for getting lat and lon from destination
        getXMLDoc("http://dev.virtualearth.net/REST/v1/Locations/UK/"+destinationPostcode+"?"+destinationStreetAddress+"&key="+key+"&output=xml");
        
        //get destination lat and lon
        nl = doc.getElementsByTagName("Point").item(0).getChildNodes();
        b1 = Double.valueOf(nl.item(0).getTextContent());
        b2 = Double.valueOf(nl.item(1).getTextContent());
        System.out.println("b1: " + b1);
        System.out.println("b2: " + b2);
        
        //generate XML document for distance between origin and destination
        getXMLDoc("https://dev.virtualearth.net/REST/v1/Routes/DistanceMatrix?origins="+a1+","+a2+"&destinations="+b1+","+b2+"&distanceUnit=mi&travelMode=driving&key="+key+"&output=xml");
        
        //get distance value from XML document
        distance = Double.valueOf(doc.getElementsByTagName("TravelDistance").item(0).getTextContent());
        
        //System.out.println("in function: " + distance);
        
        return true;
        
    } catch (Exception e) {
        System.out.println(e);;
    }
        return false;
    }
    
    public boolean getXMLDoc(String urlString){
        try{
        
        //create http request for getting distance between addresses
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET"); //assumption for lat/long request
       
        //generate xml document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        doc = builder.parse(con.getInputStream());
        
        //debug
        //prettyPrint(doc);
        
        //return true if successful
        return true;
        
        //absolute unit of an error catching block
        } catch(MalformedURLException e){
            System.out.println(e);
        } catch(ProtocolException e){
            System.out.println(e);
        } catch(IOException e){
            System.out.println(e);
        } catch(ParserConfigurationException e){
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);;
        }
        return false;
    }
    
    public void prettyPrint(Document xml) throws Exception{
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        Writer out = new StringWriter();
        tf.transform(new DOMSource(xml), new StreamResult(out));
        System.out.println(out.toString());
    }
}
