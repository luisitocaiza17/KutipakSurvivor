/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.motorTraduccion;

import com.datos.DAO.EstructuraDAO;
import com.datos.DAO.EstructuraPalabrasDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import persistencia.tables.records.EstructuraRecord;

/**
 *
 * @author luisito
 */
public class ComposicionEstructural {
    public String realizarComposicion(String[][] traduccionTipos, int idioma) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
        String resultado=null;
        String codigoEntrante="";
        for(int i=1;i<traduccionTipos[0].length;i=i+2){
            for(int j=0;j<traduccionTipos.length;j++){
                codigoEntrante=codigoEntrante+""+traduccionTipos[j][i];
            }
        }
        System.out.println("codigoEntrante "+codigoEntrante);
        resultado=DecodificardorEstructural(traduccionTipos,idioma,codigoEntrante);
        return resultado;
    }
    
    public String DecodificardorEstructural(String[][] palabraEstructura, int idioma,String codigo) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
        String resultado=null;
        EstructuraDAO estructura = new EstructuraDAO();
        EstructuraRecord estructuraEntrante= new EstructuraRecord();
        estructuraEntrante.setFormula(codigo);
        estructuraEntrante.setIdiomaid(idioma);
        List<EstructuraRecord> result = estructura.ConsultarEstructuraEspecificaIdioma(estructuraEntrante);
        String codigoResultante=codigo;
        
        for(EstructuraRecord rs :result){
           codigoResultante=rs.getFormulasalida();
           break;
        }
        
        EstructuraDAO estructuraDAO = new EstructuraDAO();
        List<EstructuraRecord>  estructuraModificable= estructuraDAO.ConsultarEstruturaIdioma(idioma);
        
        for(EstructuraRecord rs: estructuraModificable){
            codigoResultante=codigoResultante.replace(rs.getFormula(),rs.getFormulasalida());
        }
        
        /*
        if(idioma==1){
            //español-kichwa
            codigoResultante=codigoResultante.replace("AZ","ZA");
            codigoResultante=codigoResultante.replace("D0", "0D");
            codigoResultante=codigoResultante.replace("M0", "0M");
            codigoResultante=codigoResultante.replace("M1", "1M");
        }else{
            //kichwa - español
            codigoResultante=codigoResultante.replace("VS", "SV");
            codigoResultante=codigoResultante.replace("BS", "SB");
            codigoResultante=codigoResultante.replace("ZS", "SZ");
            codigoResultante=codigoResultante.replace("ZA","AZ");
            codigoResultante=codigoResultante.replace("0M","M0");
            codigoResultante=codigoResultante.replace("ZM", "MZ");
        }*/
        //comfigurar la respues em codigo em el resultado de salida, com el . remplace
        return resultado=ArmadoEstructural(palabraEstructura, codigo,codigoResultante,idioma);
    }
    
    public String ArmadoEstructural(String[][] palabraEstructura,String codigoE,String codigoS,int idioma) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
        String resultado=null;
        /*
        String[] numerosComoArray = codigoE.split("||");
        String[][] codigoIndices= new String[numerosComoArray.length][2];
        for(int i=0; i<numerosComoArray.length;i++){
            codigoIndices[i][0]=numerosComoArray[i];
            codigoIndices[i][1]=""+i;
        }
        String[] numerosComoArray2 = codigoS.split("||");
        String[][] codigoIndices2= new String[numerosComoArray2.length][2];
        int contadorDesiguales=0;
        for(int i=0; i<numerosComoArray2.length;i++){
            codigoIndices2[i][0]=numerosComoArray2[i];
        }
        
        int numeroEmpiesa=0;
        for(int j=0; j<numerosComoArray2.length;j++){
            if(!codigoIndices[j][0].equals(codigoIndices2[j][0])){
                numeroEmpiesa=j;
                break;
            }
        }
        String noIguales="";
        for(int j=0; j<numerosComoArray2.length;j++){
            if(codigoIndices[j][0].equals(codigoIndices2[j][0])){
                codigoIndices2[j][1]=codigoIndices[j][1];
            }
            else{
                int contadorT=0;
                if(contadorT==0){
                    for(int i=numeroEmpiesa;i<numerosComoArray2.length;i++){
                        if(codigoIndices[j][0].equals(codigoIndices2[i][0])){
                        codigoIndices2[j][1]=codigoIndices[i][1];
                        contadorT++;
                        break;
                        }
                    }
                }
                contadorDesiguales++;
                noIguales=codigoIndices[j][1];
            }
            
            System.out.println("codigoEntrante "+codigoIndices[j][0]+" imdice "+codigoIndices[j][1]);
        }
        //proceso de cambio de indices distintos
        String[]noIgualesRecorrer= new String[contadorDesiguales];
        
        for(int j=0; j<numerosComoArray2.length;j++){
            System.out.println("codigoSaliete "+codigoIndices2[j][0]+" imdice "+codigoIndices2[j][1]);
        }*/
        
        String[] ArrayEntrante  = codigoE.split("||");
        String[] ArraySaliente  = codigoS.split("||");
        String[] ArrayIndices=orderIntIndex(ArrayEntrante,ArraySaliente);
        String[][] codigoIndices2= new String[ArraySaliente.length][2];
        for(int i=0; i<ArraySaliente.length;i++){
            codigoIndices2[i][0]=ArraySaliente[i];
            codigoIndices2[i][1]=ArrayIndices[i];        
        }
        
        
        ProcesadorResultados proceso = new ProcesadorResultados();
        resultado=proceso.ResultadoTraduccion(palabraEstructura,codigoIndices2,idioma);    
        return resultado;
    }
    
    public static String[] orderIntIndex(String[] disorderArray, String[] orderArray) {
	int lon = disorderArray.length;

	String[] index = new String[lon];
	Arrays.fill(index, "0");

	boolean[] esta = new boolean[lon];
	Arrays.fill(esta, false);

	for (int i = 0; i < orderArray.length; i++) {
		int in = 0;
		boolean stay = false;
		while (in < orderArray.length & !stay) {
			if ((disorderArray[in].equals(orderArray[i])) & !esta[in]) {
				esta[in] = true;
				index[i] = ""+in;
				stay = true;
			} else {
				in++;
			}
		}
	}
	return index;
}
}
