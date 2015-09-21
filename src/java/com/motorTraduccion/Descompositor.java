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

    public String[] descompositorOraciones(String oraciones) throws FileNotFoundException, IOException {
        String paragraph = oraciones;
        InputStream is = new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\en-sent.bin");
        SentenceModel model = new SentenceModel(is);
        SentenceDetectorME sdetector = new SentenceDetectorME(model);
        String sentences[] = sdetector.sentDetect(paragraph);
        for (String a : sentences) {
            System.out.println(a);
        }
        is.close();
        return sentences;
    }

    public String[] descompositorPalabras(String oraciones) throws FileNotFoundException, IOException {
        InputStream is = new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\en-token.bin");
        TokenizerModel model = new TokenizerModel(is);
        Tokenizer tokenizer = new TokenizerME(model);
        String tokens[] = tokenizer.tokenize(oraciones);
        for (String a : tokens) {
            System.out.println(a);
        }
        is.close();
        return tokens;
    }

}
