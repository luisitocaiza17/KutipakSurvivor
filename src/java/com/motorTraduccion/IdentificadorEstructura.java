/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.motorTraduccion;

import java.io.FileInputStream;
import java.util.ArrayList;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

/**
 *
 * @author luisito
 */
public class IdentificadorEstructura {
    
    /*PROCESO MEDIANTE EL CUAL CADA PALABRA ES IDENTIFICADA POR SU ESTRUCTURA GRAMACTICAL*/
    public void identificadorGenericoPalabras(String[] palabras){
        /*1) Proceso de identificacion de los pronombrespropios que no se podran traducir */
        
    }

    public static ArrayList identificadorNombres(String[] tokens) {
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

    public static ArrayList IdentificadorUbicaciones(String[] tokens) {
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
}
