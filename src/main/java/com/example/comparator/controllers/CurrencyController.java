package com.example.comparator.controllers;

import com.example.comparator.ApplicationProperties;
import com.example.comparator.models.Currency;
import com.example.comparator.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


@RestController
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;

    ApplicationProperties properties = new ApplicationProperties();

    @RequestMapping(value = "/getAllCurrencies", method = RequestMethod.GET)
    public ArrayList<Currency> getAllCurrencies(){
        return (ArrayList<Currency>)currencyService.getAllCurrencies();
    }

    void updateRates(CurrencyService currencyService) throws IOException, ParserConfigurationException, SAXException, ParseException {
        URL obj = new URL(properties.getProperty("cbr.url"));
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "windows-1251"));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource inputSource = new InputSource(new StringReader(response.toString()));
        inputSource.setEncoding("UTF-8");
        Document document = documentBuilder.parse(inputSource);
        Node root = document.getDocumentElement();
        NodeList currencies = root.getChildNodes();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        currencyService.updateCurrencyRate("RUB(Российский рубль)", 1.0,
                formatter.parse(formatter.format(date)));
        for(int i = 0; i < currencies.getLength(); i++){
            Node currency = currencies.item(i);
            NodeList currencyData = currency. getChildNodes();
            String name = currencyData.item(1).getTextContent() + "(" + currencyData.item(3).getTextContent()
                    + ")";
            double rate = Double.parseDouble(currencyData.item(4).getTextContent().replaceAll(",",
                    ".")) / Double.parseDouble(currencyData.item(2).getTextContent().replaceAll(",",
                    "."));
            currencyService.updateCurrencyRate(name, rate, formatter.parse(formatter.format(date)));
        }
    }
}
