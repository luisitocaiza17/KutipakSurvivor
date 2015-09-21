/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.motorTraduccion;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

/**
 * http://www.programcreek.com/2012/05/opennlp-tutorial/
 * @author luisito
 */
public class IdentificadorEstructura {
    
    /*PROCESO MEDIANTE EL CUAL CADA PALABRA ES IDENTIFICADA POR SU ESTRUCTURA GRAMACTICAL*/
    public void identificadorGenericoPalabras(String[] palabras){
        /*1) Proceso de identificacion de los pronombrespropios que no se podran traducir */
        //proceso de carga masiva de identificadores.
         ArrayList IndicesDespreciados = new ArrayList();  
         ArrayList nombreI = identificadorNombresI(palabras); 
         Iterator<String> it = nombreI.iterator();
         while (it.hasNext()) {
             IndicesDespreciados.add(it.next());             
        }
        it.remove();
        ArrayList nombreE = identificadorNombresI(palabras);
        it = nombreE.iterator();
        while (it.hasNext()) {
             IndicesDespreciados.add(it.next());             
        }
        it.remove();
        ArrayList UbicacionI = IdentificadorUbicacionesI(palabras);
        it = UbicacionI.iterator();
        while (it.hasNext()) {
             IndicesDespreciados.add(it.next());             
        }
        it.remove();
        ArrayList UbicacionE = IdentificadorUbicacionesE(palabras);
        it = UbicacionE.iterator();
        while (it.hasNext()) {
             IndicesDespreciados.add(it.next());             
        }
        it.remove();
        ArrayList Fecha = IdentificadorFechasI(palabras);
        it = Fecha.iterator();
        while (it.hasNext()) {
             IndicesDespreciados.add(it.next());             
        }
        it.remove();
        ArrayList Tiempo = IdentificadorTiempo(palabras);
        it = Tiempo.iterator();
        while (it.hasNext()) {
             IndicesDespreciados.add(it.next());             
        }
        it.remove();
        ArrayList Moneda = IdentificadorMonetario(palabras);
        it = Moneda.iterator();
        while (it.hasNext()) {
             IndicesDespreciados.add(it.next());             
        }
        it.remove();
        ArrayList NombreEspecial = IdentificadorNombresEspecialesE(palabras);
        it = NombreEspecial.iterator();
        while (it.hasNext()) {
             IndicesDespreciados.add(it.next());             
        }
        it.remove();
        ArrayList OrganizacionesI = IdentificadorOrganizacionesI(palabras);
        it = OrganizacionesI.iterator();
        while (it.hasNext()) {
             IndicesDespreciados.add(it.next());             
        }
        it.remove();
        ArrayList OrganizacionesE = IdentificadorOrganizacionesE(palabras);
        it = OrganizacionesE.iterator();
        while (it.hasNext()) {
             IndicesDespreciados.add(it.next());             
        }
        it.remove();
        //Recorrido de los identificadores
        it = IndicesDespreciados.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
                      
        }
    }

    public  ArrayList identificadorNombresI(String[] tokens) {
        try {
            NameFinderME nameFinder
                    = new NameFinderME(
                            new TokenNameFinderModel(new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\en-ner-person.bin")));
            Span nameSpans[] = nameFinder.find(tokens);
            ArrayList IndicesDespreciados = new ArrayList();    
            for (int i = 0; i < nameSpans.length; i++) {
                IndicesDespreciados.add(nameSpans[i].getStart());
                System.out.println("Span: " + nameSpans[i].toString());
                System.out.println("Covered text is: " + tokens[nameSpans[i].getStart()] + " " + nameSpans[i].getStart());
            }
            return IndicesDespreciados;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    public  ArrayList identificadorNombresE(String[] tokens) {
        try {
            NameFinderME nameFinder
                    = new NameFinderME(
                            new TokenNameFinderModel(new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\es-ner-person.bin")));
            Span nameSpans[] = nameFinder.find(tokens);
            ArrayList IndicesDespreciados = new ArrayList();    
            for (int i = 0; i < nameSpans.length; i++) {
                IndicesDespreciados.add(nameSpans[i].getStart());
                System.out.println("Span: " + nameSpans[i].toString());
                System.out.println("Covered text is: " + tokens[nameSpans[i].getStart()] + " " + nameSpans[i].getStart());
            }
            return IndicesDespreciados;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public  ArrayList IdentificadorUbicacionesI(String[] tokens) {
        try {
            //String documentStr=new String("says to Buffalo.");
            NameFinderME nameFinder
                    = new NameFinderME(
                            new TokenNameFinderModel(new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\en-ner-location.bin")));

            //String tokens[] = tokenizer.tokenize(documentStr);
            Span nameSpans[] = nameFinder.find(tokens);
            ArrayList IndicesDespreciados = new ArrayList();      
            for (int i = 0; i < nameSpans.length; i++) {
                IndicesDespreciados.add(nameSpans[i].getStart());
                System.out.println("Span: " + nameSpans[i].toString());
                System.out.println("Covered text is: " + tokens[nameSpans[i].getStart()] + " " + tokens[nameSpans[i].getStart() + 1]);
            }
            return IndicesDespreciados;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    public  ArrayList IdentificadorUbicacionesE(String[] tokens) {
        try {
            //String documentStr=new String("says to Buffalo.");
            NameFinderME nameFinder
                    = new NameFinderME(
                            new TokenNameFinderModel(new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\es-ner-location")));

            //String tokens[] = tokenizer.tokenize(documentStr);
            Span nameSpans[] = nameFinder.find(tokens);
            ArrayList IndicesDespreciados = new ArrayList();      
            for (int i = 0; i < nameSpans.length; i++) {
                IndicesDespreciados.add(nameSpans[i].getStart());
                System.out.println("Span: " + nameSpans[i].toString());
                System.out.println("Covered text is: " + tokens[nameSpans[i].getStart()] + " " + tokens[nameSpans[i].getStart() + 1]);
            }
            return IndicesDespreciados;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    public  ArrayList IdentificadorFechasI(String[] tokens) {
        try {
            //String documentStr=new String("says to Buffalo.");
            NameFinderME nameFinder
                    = new NameFinderME(
                            new TokenNameFinderModel(new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\en-ner-date")));

            //String tokens[] = tokenizer.tokenize(documentStr);
            Span nameSpans[] = nameFinder.find(tokens);
            ArrayList IndicesDespreciados = new ArrayList();      
            for (int i = 0; i < nameSpans.length; i++) {
                IndicesDespreciados.add(nameSpans[i].getStart());
                System.out.println("Span: " + nameSpans[i].toString());
                System.out.println("Covered text is: " + tokens[nameSpans[i].getStart()] + " " + tokens[nameSpans[i].getStart() + 1]);
            }
            return IndicesDespreciados;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    public  ArrayList IdentificadorOrganizacionesE(String[] tokens) {
        try {
            //String documentStr=new String("says to Buffalo.");
            NameFinderME nameFinder
                    = new NameFinderME(
                            new TokenNameFinderModel(new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\es-ner-organization")));

            //String tokens[] = tokenizer.tokenize(documentStr);
            Span nameSpans[] = nameFinder.find(tokens);
            ArrayList IndicesDespreciados = new ArrayList();      
            for (int i = 0; i < nameSpans.length; i++) {
                IndicesDespreciados.add(nameSpans[i].getStart());
                System.out.println("Span: " + nameSpans[i].toString());
                System.out.println("Covered text is: " + tokens[nameSpans[i].getStart()] + " " + tokens[nameSpans[i].getStart() + 1]);
            }
            return IndicesDespreciados;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    public  ArrayList IdentificadorOrganizacionesI(String[] tokens) {
        try {
            //String documentStr=new String("says to Buffalo.");
            NameFinderME nameFinder
                    = new NameFinderME(
                            new TokenNameFinderModel(new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\en-ner-organization")));

            //String tokens[] = tokenizer.tokenize(documentStr);
            Span nameSpans[] = nameFinder.find(tokens);
            ArrayList IndicesDespreciados = new ArrayList();      
            for (int i = 0; i < nameSpans.length; i++) {
                IndicesDespreciados.add(nameSpans[i].getStart());
                System.out.println("Span: " + nameSpans[i].toString());
                System.out.println("Covered text is: " + tokens[nameSpans[i].getStart()] + " " + tokens[nameSpans[i].getStart() + 1]);
            }
            return IndicesDespreciados;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    public  ArrayList IdentificadorNombresEspecialesE(String[] tokens) {
        try {
            //String documentStr=new String("says to Buffalo.");
            NameFinderME nameFinder
                    = new NameFinderME(
                            new TokenNameFinderModel(new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\es-ner-misc")));

            //String tokens[] = tokenizer.tokenize(documentStr);
            Span nameSpans[] = nameFinder.find(tokens);
            ArrayList IndicesDespreciados = new ArrayList();      
            for (int i = 0; i < nameSpans.length; i++) {
                IndicesDespreciados.add(nameSpans[i].getStart());
                System.out.println("Span: " + nameSpans[i].toString());
                System.out.println("Covered text is: " + tokens[nameSpans[i].getStart()] + " " + tokens[nameSpans[i].getStart() + 1]);
            }
            return IndicesDespreciados;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    public  ArrayList IdentificadorMonetario(String[] tokens) {
        try {
            //String documentStr=new String("says to Buffalo.");
            NameFinderME nameFinder
                    = new NameFinderME(
                            new TokenNameFinderModel(new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\en-ner-money")));

            //String tokens[] = tokenizer.tokenize(documentStr);
            Span nameSpans[] = nameFinder.find(tokens);
            ArrayList IndicesDespreciados = new ArrayList();      
            for (int i = 0; i < nameSpans.length; i++) {
                IndicesDespreciados.add(nameSpans[i].getStart());
                System.out.println("Span: " + nameSpans[i].toString());
                System.out.println("Covered text is: " + tokens[nameSpans[i].getStart()] + " " + tokens[nameSpans[i].getStart() + 1]);
            }
            return IndicesDespreciados;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    public  ArrayList IdentificadorTiempo(String[] tokens) {
        try {
            //String documentStr=new String("says to Buffalo.");
            NameFinderME nameFinder
                    = new NameFinderME(
                            new TokenNameFinderModel(new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\en-ner-time")));

            //String tokens[] = tokenizer.tokenize(documentStr);
            Span nameSpans[] = nameFinder.find(tokens);
            ArrayList IndicesDespreciados = new ArrayList();      
            for (int i = 0; i < nameSpans.length; i++) {
                IndicesDespreciados.add(nameSpans[i].getStart());
                System.out.println("Span: " + nameSpans[i].toString());
                System.out.println("Covered text is: " + tokens[nameSpans[i].getStart()] + " " + tokens[nameSpans[i].getStart() + 1]);
            }
            return IndicesDespreciados;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
