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

    public String[] descompositorOraciones(String oraciones,int idioma) throws FileNotFoundException, IOException, Exception {
        String paragraph = oraciones;
        InputStream is = new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\en-sent.bin");
        SentenceModel model = new SentenceModel(is);
        SentenceDetectorME sdetector = new SentenceDetectorME(model);
        String sentences[] = sdetector.sentDetect(paragraph);
        String resultado[]=null;
        for (String a : sentences) {
            /***1).PROCESO DE DESCOMPOSICION DE PALABRAS***/
            resultado=descompositorPalabras(a,idioma);
        }
        is.close();
        return resultado;
    }

    public String[] descompositorPalabras(String oraciones, int idioma) throws FileNotFoundException, IOException, Exception {
        InputStream is = new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\en-token.bin");
        TokenizerModel model = new TokenizerModel(is);
        Tokenizer tokenizer = new TokenizerME(model);
        String tokens[] = tokenizer.tokenize(oraciones);
        String resultado[]=null;
        /*2) PROCESO DE IDENTIFICACION DE PALABRAS (ESTRUCTURA GRAMATICAL)***/
        IdentificadorEstructurasBD  identificador = new IdentificadorEstructurasBD();
        resultado=identificador.PalabrasTipos(tokens,idioma);
        is.close();
        return resultado;
    }

}
