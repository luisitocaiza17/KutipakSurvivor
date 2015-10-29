/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.motorTraduccion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;              

/**
 *
 * @author luisito
 */
public class Descompositor {

    public String descompositorOraciones(String oraciones,int idioma) throws FileNotFoundException, IOException, Exception {
        String paragraph = oraciones;
        InputStream is = new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\en-sent.bin");
        SentenceModel model = new SentenceModel(is);
        SentenceDetectorME sdetector = new SentenceDetectorME(model);
        String sentences[] = sdetector.sentDetect(paragraph);
        String resultado="";
        for (String a : sentences) {
            /***1).PROCESO DE DESCOMPOSICION DE PALABRAS***/
            a=a+" ";
            if(idioma==2){
                String palabraBuscar="KA ";
                boolean existePlural=a.contains(palabraBuscar);
                if(!existePlural){
                   System.out.println("no es plural");  
                   //resultado=resultado+descompositorPalabras(a,idioma)+" ";
                }
                else{
                    System.out.println("Es plural");
                    String[] cadenasPlural = a.split("KA "); 
                    a="";
                    for (int i = 0; i < cadenasPlural.length; i++) {
                        if(i<cadenasPlural.length-1 || i==0){
                            a=a+cadenasPlural[i]+" KA ";
                        } 
                        else
                            a=a+cadenasPlural[i];
                        System.out.println("palabra : "+a); 
                    }
                    //resultado=resultado+descompositorPalabras(a,idioma)+" ";
                }
                palabraBuscar="MANTA ";
                boolean existeDesde=a.contains(palabraBuscar);
                if(!existeDesde){
                   System.out.println("No es desde");  
                   //resultado=resultado+descompositorPalabras(a,idioma)+" ";
                }else{
                    System.out.println("Si es desde ");
                    String[] cadenasPlural = a.split("MANTA "); 
                    a="";
                    for (int i = 0; i < cadenasPlural.length; i++) {
                        if(i<cadenasPlural.length-1 || i==0){
                            a=a+cadenasPlural[i]+" MANTA ";
                        } 
                        else
                            a=a+cadenasPlural[i];
                        System.out.println("palabra : "+a); 
                    }
                }
                
                resultado=resultado+descompositorPalabras(a,idioma)+" ";
            }else
                resultado=resultado+descompositorPalabras(a,idioma)+" ";
        }
        is.close();
        return resultado;
    }

    public String descompositorPalabras(String oraciones, int idioma) throws FileNotFoundException, IOException, Exception {
        InputStream is = new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\en-token.bin");
        TokenizerModel model = new TokenizerModel(is);
        Tokenizer tokenizer = new TokenizerME(model);
        String tokens[] = tokenizer.tokenize(oraciones);
        String resultado=null;
        /*2) PROCESO DE IDENTIFICACION DE PALABRAS (ESTRUCTURA GRAMATICAL)***/
        IdentificadorEstructurasBD  identificador = new IdentificadorEstructurasBD();
        resultado=identificador.PalabrasTipos(tokens,idioma);
        is.close();
        return resultado;
    }

}
