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
import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.Span;
/**
 *
 * @author luisito
 */
public class Descompositor {
    
    public String[] descompositorOraciones(String oraciones) throws FileNotFoundException, IOException{
        String paragraph = oraciones;
        InputStream is = new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\en-sent.bin");
	SentenceModel model = new SentenceModel(is);
	SentenceDetectorME sdetector = new SentenceDetectorME(model);
	String sentences[] = sdetector.sentDetect(paragraph);
	for (String a : sentences)
		System.out.println(a);
        is.close();
        return sentences;
    }
    
    public String[] descompositorPalabras2(String oraciones) throws FileNotFoundException, IOException{
        InputStream is = new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\en-token.bin");
	TokenizerModel model = new TokenizerModel(is);
	Tokenizer tokenizer = new TokenizerME(model);
	String tokens[] = tokenizer.tokenize(oraciones);
	for (String a : tokens)
		System.out.println(a);
	is.close();
        return tokens;
    }
    
    public void identificadorNombres(String[] palabras) throws FileNotFoundException, IOException{
        InputStream is = new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\en-ner-person.bin");
	TokenNameFinderModel model = new TokenNameFinderModel(is);
	is.close();
        NameFinderME nameFinder = new NameFinderME(model);
        
	//String []sentence = palabras;
        String []sentence = new String[]{
		    "Mike",
		    "Smith",
		    "is",
		    "a",
		    "good",
		    "person"
		    };
        Span nameSpans[] = nameFinder.find(sentence);
        double[] spanProbs = nameFinder.probs(nameSpans);
        
        for(Span s: nameSpans)
			System.out.println(s.toString());
        
        for( int i = 0; i<nameSpans.length; i++) {
		    		System.out.println("Span: "+nameSpans[i].toString());
		    		System.out.println("Probability is: "+spanProbs[i]);
		    	}	
        
    }
    
    public void identificadorNombres2(String[] palabras) throws FileNotFoundException, IOException{
        InputStream is = new FileInputStream("C:\\KutipakSurvivor\\librerias\\OpenNlp\\proceso\\en-ner-person.bin");
	TokenNameFinderModel model = new TokenNameFinderModel(is);
	is.close();
 
	NameFinderME nameFinder = new NameFinderME(model);
 
	String []sentence = new String[]{
		    "Mike",
		    "Smith",
		    "is",
		    "a",
		    "good",
		    "person"
		    };
 
		Span nameSpans[] = nameFinder.find(sentence);
 
		for(Span s: nameSpans)
			System.out.println(s.toString());	
    }
}
