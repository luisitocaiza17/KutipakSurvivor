/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.motorTraduccion;

import com.datos.DAO.PalabrasDAO;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;
import persistencia.tables.records.PalabrasRecord;

/**
 * http://www.programcreek.com/2012/05/opennlp-tutorial/
 * @author luisito
 */
public class IdentificadorEstructura {
    
    /*PROCESO MEDIANTE EL CUAL CADA PALABRA ES IDENTIFICADA POR SU ESTRUCTURA GRAMACTICAL*/
    public void identificadorGenericoPalabras(String[] palabras, int idiomaId)throws Exception{
        /*1) Proceso de identificacion de los pronombrespropios que no se podran traducir */
        //proceso de carga masiva de identificadores.
         ArrayList IndicesDespreciados = new ArrayList();  
         ArrayList nombreI = identificadorNombresI(palabras); 
         Iterator<String> it = nombreI.iterator();
         while (it.hasNext()) {
             IndicesDespreciados.add(it.next().toString());             
        }
        //it.remove();
        ArrayList nombreE = identificadorNombresI(palabras);
        Iterator<String>it2 = nombreE.iterator();
        while (it2.hasNext()) {
             IndicesDespreciados.add(it2.next().toString());             
        }
        //it.remove();
        ArrayList UbicacionI = IdentificadorUbicacionesI(palabras);
        Iterator<String>it3 = UbicacionI.iterator();
        while (it3.hasNext()) {
             IndicesDespreciados.add(it3.next().toString());             
        }
        //it.remove();
        ArrayList UbicacionE = IdentificadorUbicacionesE(palabras);
        Iterator<String>it4 = UbicacionE.iterator();
        while (it4.hasNext()) {
             IndicesDespreciados.add(it4.next().toString());             
        }
        //it.remove();
        ArrayList Fecha = IdentificadorFechasI(palabras);
        Iterator<String>it5 = Fecha.iterator();
        while (it5.hasNext()) {
             IndicesDespreciados.add(it5.next().toString());             
        }
        //it.remove();
        ArrayList Tiempo = IdentificadorTiempo(palabras);
        Iterator<String>it6 = Tiempo.iterator();
        while (it6.hasNext()) {
             IndicesDespreciados.add(it6.next().toString());             
        }
        //it.remove();
        ArrayList Moneda = IdentificadorMonetario(palabras);
        Iterator<String>it7 = Moneda.iterator();
        while (it7.hasNext()) {
             IndicesDespreciados.add(it7.next().toString());             
        }
        //it.remove();
        ArrayList NombreEspecial = IdentificadorNombresEspecialesE(palabras);
        Iterator<String>it8 = NombreEspecial.iterator();
        while (it8.hasNext()) {
             IndicesDespreciados.add(it8.next().toString());             
        }
        //it.remove();
        ArrayList OrganizacionesI = IdentificadorOrganizacionesI(palabras);
        Iterator<String>it9 = OrganizacionesI.iterator();
        while (it9.hasNext()) {
             IndicesDespreciados.add(it9.next().toString());             
        }
        //it.remove();
        ArrayList OrganizacionesE = IdentificadorOrganizacionesE(palabras);
        Iterator<String>it10 = OrganizacionesE.iterator();
        while (it10.hasNext()) {
             IndicesDespreciados.add(it10.next().toString());             
        }
        //it.remove();
        //Recorrido de los identificadores
        Set<String> linkedHashSet = new LinkedHashSet<String>();
        linkedHashSet.addAll(IndicesDespreciados);
        IndicesDespreciados.clear();
        IndicesDespreciados.addAll(linkedHashSet);
        Iterator<String>it11 = IndicesDespreciados.iterator();
        while (it11.hasNext()) {
            System.out.println(it11.next());                  
        }
        /*Proceso de identificacion de los tipos de palabras*/
        String[][] palabraTipos=ProcesoTiposPalabras(palabras,IndicesDespreciados,idiomaId);
        for(int i=0;i<palabraTipos.length;i++){
            System.out.println("significado: "+palabraTipos[i][0]+ " tipo palabra: "+palabraTipos[i][1]);
        }
    }
    
    public String[][] ProcesoTiposPalabras(String[] palabras, ArrayList indices, int idiomaId ) throws Exception{
        String[][] palabraTipos = new String[palabras.length][palabras.length];
        for (int i=0; i<palabras.length;i++){
            PalabrasDAO palabraProcesos= new PalabrasDAO();
            PalabrasRecord palabraObjeto = new PalabrasRecord();
            if(!palabraProcesos.ConsultarPalabrasExisteId(palabras[i],idiomaId).equals(""))
            {
                palabraObjeto.setNombrepalabra(palabras[i]);
                palabraObjeto.setIdiomaid(2);
                List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccion(palabraObjeto);
                for(PalabrasRecord rs : results){
                    palabraTipos[i][0]=rs.getSignificado();
                    palabraTipos[i][1]=""+rs.getTipoid();
                }
            }
        }
        return palabraTipos;
    }

    public  ArrayList identificadorNombresI(String[] tokens) {
        try {
            NameFinderME nameFinder
                    = new NameFinderME(
                            new TokenNameFinderModel(new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\en-ner-person.bin")));
            Span nameSpans[] = nameFinder.find(tokens);
            ArrayList IndicesDespreciados = new ArrayList();    
            for (int i = 0; i < nameSpans.length; i++) {
                IndicesDespreciados.add(""+nameSpans[i].getStart());
                System.out.println("Span: " + nameSpans[i].toString());
                //System.out.println("Covered text is: " + tokens[nameSpans[i].getStart()] + " " + nameSpans[i].getStart());
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
                IndicesDespreciados.add(""+nameSpans[i].getStart());
                System.out.println("Span: " + nameSpans[i].toString());
                //System.out.println("Covered text is: " + tokens[nameSpans[i].getStart()] + " " + nameSpans[i].getStart());
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
                IndicesDespreciados.add(""+nameSpans[i].getStart());
                System.out.println("Span: " + nameSpans[i].toString());
                //.out.println("Covered text is: " + tokens[nameSpans[i].getStart()] + " " + tokens[nameSpans[i].getStart() + 1]);
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
                            new TokenNameFinderModel(new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\es-ner-location.bin")));

            //String tokens[] = tokenizer.tokenize(documentStr);
            Span nameSpans[] = nameFinder.find(tokens);
            ArrayList IndicesDespreciados = new ArrayList();      
            for (int i = 0; i < nameSpans.length; i++) {
                IndicesDespreciados.add(""+nameSpans[i].getStart());
                System.out.println("Span: " + nameSpans[i].toString());
                //System.out.println("Covered text is: " + tokens[nameSpans[i].getStart()] + " " + tokens[nameSpans[i].getStart() + 1]);
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
                            new TokenNameFinderModel(new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\en-ner-date.bin")));
            //String tokens[] = tokenizer.tokenize(documentStr);
            Span nameSpans[] = nameFinder.find(tokens);
            ArrayList IndicesDespreciados = new ArrayList();      
            for (int i = 0; i < nameSpans.length; i++) {
                IndicesDespreciados.add(""+nameSpans[i].getStart());
                System.out.println("Span: " + nameSpans[i].toString());
                //System.out.println("Covered text is: " + tokens[nameSpans[i].getStart()] + " " + tokens[nameSpans[i].getStart() + 1]);
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
                            new TokenNameFinderModel(new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\es-ner-organization.bin")));
            //String tokens[] = tokenizer.tokenize(documentStr);
            Span nameSpans[] = nameFinder.find(tokens);
            ArrayList IndicesDespreciados = new ArrayList();      
            for (int i = 0; i < nameSpans.length; i++) {
                IndicesDespreciados.add(""+nameSpans[i].getStart());
                System.out.println("Span: " + nameSpans[i].toString());
                //System.out.println("Covered text is: " + tokens[nameSpans[i].getStart()] + " " + tokens[nameSpans[i].getStart() + 1]);
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
                            new TokenNameFinderModel(new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\en-ner-organization.bin")));
            //String tokens[] = tokenizer.tokenize(documentStr);
            Span nameSpans[] = nameFinder.find(tokens);
            ArrayList IndicesDespreciados = new ArrayList();      
            for (int i = 0; i < nameSpans.length; i++) {
                IndicesDespreciados.add(""+nameSpans[i].getStart());
                System.out.println("Span: " + nameSpans[i].toString());
                //System.out.println("Covered text is: " + tokens[nameSpans[i].getStart()] + " " + tokens[nameSpans[i].getStart() + 1]);
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
                            new TokenNameFinderModel(new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\es-ner-misc.bin")));
            //String tokens[] = tokenizer.tokenize(documentStr);
            Span nameSpans[] = nameFinder.find(tokens);
            ArrayList IndicesDespreciados = new ArrayList();      
            for (int i = 0; i < nameSpans.length; i++) {
                IndicesDespreciados.add(""+nameSpans[i].getStart());
                System.out.println("Span: " + nameSpans[i].toString());
                //System.out.println("Covered text is: " + tokens[nameSpans[i].getStart()] + " " + tokens[nameSpans[i].getStart() + 1]);
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
                            new TokenNameFinderModel(new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\en-ner-money.bin")));
            //String tokens[] = tokenizer.tokenize(documentStr);
            Span nameSpans[] = nameFinder.find(tokens);
            ArrayList IndicesDespreciados = new ArrayList();      
            for (int i = 0; i < nameSpans.length; i++) {
                IndicesDespreciados.add(""+nameSpans[i].getStart());
                System.out.println("Span: " + nameSpans[i].toString());
                //System.out.println("Covered text is: " + tokens[nameSpans[i].getStart()] + " " + tokens[nameSpans[i].getStart() + 1]);
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
                            new TokenNameFinderModel(new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\en-ner-time.bin")));
            //String tokens[] = tokenizer.tokenize(documentStr);
            Span nameSpans[] = nameFinder.find(tokens);
            ArrayList IndicesDespreciados = new ArrayList();      
            for (int i = 0; i < nameSpans.length; i++) {
                IndicesDespreciados.add(""+nameSpans[i].getStart());
                System.out.println("Span: " + nameSpans[i].toString());
                //System.out.println("Covered text is: " + tokens[nameSpans[i].getStart()] + " " + tokens[nameSpans[i].getStart() + 1]);
            }
            return IndicesDespreciados;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
