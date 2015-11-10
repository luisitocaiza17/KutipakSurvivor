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
                //ESTAR ANDO
                palabraBuscar="KUNA ";
                existeDesde=a.contains(palabraBuscar);
                if(!existeDesde){
                   System.out.println("No es ANDO");  
                   //resultado=resultado+descompositorPalabras(a,idioma)+" ";
                }else{
                    System.out.println("Si es ANDO ");
                    String[] cadenasPlural = a.split("KUNA "); 
                    a="";
                    for (int i = 0; i < cadenasPlural.length; i++) {
                        if(i<cadenasPlural.length-1 || i==0){
                            a=a+cadenasPlural[i]+"NA KU ";
                        } 
                        else
                            a=a+cadenasPlural[i];
                        System.out.println("palabra : "+a); 
                    }
                }
                palabraBuscar="PI ";
                boolean existePi=a.contains(palabraBuscar);
                if(!existePi){
                   System.out.println("No es PI");  
                   //resultado=resultado+descompositorPalabras(a,idioma)+" ";
                }else{
                    System.out.println("Si es PI ");
                    String[] cadenasPlural = a.split("PI "); 
                    a="";
                    for (int i = 0; i < cadenasPlural.length; i++) {
                        if(i<cadenasPlural.length-1 || i==0){
                            a=a+cadenasPlural[i]+" PI ";
                        } 
                        else
                            a=a+cadenasPlural[i];
                        System.out.println("palabra : "+a); 
                    }
                }
                palabraBuscar="PAK ";
                boolean existePak=a.contains(palabraBuscar);
                if(!existePak){
                   System.out.println("No es Pak");  
                   //resultado=resultado+descompositorPalabras(a,idioma)+" ";
                }else{
                    System.out.println("Si es Pak ");
                    String[] cadenasPlural = a.split("PAK "); 
                    a="";
                    for (int i = 0; i < cadenasPlural.length; i++) {
                        if(i<cadenasPlural.length-1 || i==0){
                            a=a+cadenasPlural[i]+" PAK ";
                        } 
                        else
                            a=a+cadenasPlural[i];
                        System.out.println("palabra : "+a); 
                    }
                }
                palabraBuscar="YUK ";
                boolean existeYuk=a.contains(palabraBuscar);
                if(!existeYuk){
                   System.out.println("No es yuk");  
                   //resultado=resultado+descompositorPalabras(a,idioma)+" ";
                }else{
                    System.out.println("Si es yuk ");
                    String[] cadenasPlural = a.split("YUK "); 
                    a="";
                    for (int i = 0; i < cadenasPlural.length; i++) {
                        if(i<cadenasPlural.length-1 || i==0){
                            a=a+cadenasPlural[i]+" YUK ";
                        } 
                        else
                            a=a+cadenasPlural[i];
                        System.out.println("palabra : "+a); 
                    }
                }
                resultado=resultado+descompositorPalabras(a,idioma)+" ";
            }else{
                //idioma español
                String palabraBuscar="ANDO ";
                String palabraBuscar2="ENDO ";
                
                boolean existeANDO=a.contains(palabraBuscar);
                boolean existeENDO=a.contains(palabraBuscar2);
                if(!existeANDO){
                   System.out.println("No es ANDO");  
                   //resultado=resultado+descompositorPalabras(a,idioma)+" ";
                }else{
                    System.out.println("Si es ANDO ");
                    String[] cadenasPlural = a.split("ANDO "); 
                    a="";
                    for (int i = 0; i < cadenasPlural.length; i++) {
                        if(i<cadenasPlural.length-1 || i==0){
                            a=a+cadenasPlural[i]+"ANDO ANDO ";
                        } 
                        else
                            a=a+cadenasPlural[i];
                        System.out.println("palabra : "+a); 
                    }
                }
                 if(!existeENDO){
                   System.out.println("No es ENDO");  
                   //resultado=resultado+descompositorPalabras(a,idioma)+" ";
                }else{
                    System.out.println("Si es ENDO ");
                    String[] cadenasPlural = a.split("ENDO "); 
                    a="";
                    for (int i = 0; i < cadenasPlural.length; i++) {
                        if(i<cadenasPlural.length-1 || i==0){
                            a=a+cadenasPlural[i]+"ENDO ENDO ";
                        } 
                        else
                            a=a+cadenasPlural[i];
                        System.out.println("palabra : "+a); 
                    }
                }
                System.out.println("oraciom salida : "+a); 
                resultado=resultado+descompositorPalabras(a,idioma)+" ";
            }    
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
