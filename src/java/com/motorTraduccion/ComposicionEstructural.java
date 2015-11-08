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
        if(idioma==1){
            codigoResultante=codigoResultante.replace("21","12");
            codigoResultante=codigoResultante.replace("30", "03");
           
        }else{
            codigoResultante=codigoResultante.replace("6S", "S6");
            codigoResultante=codigoResultante.replace("4S", "S4");
            codigoResultante=codigoResultante.replace("1S", "S1");
            codigoResultante=codigoResultante.replace("12","21");
        }
        //comfigurar la respues em codigo em el resultado de salida, com el . remplace
        return resultado=ArmadoEstructural(palabraEstructura, codigo,codigoResultante,idioma);
    }
    
    public String ArmadoEstructural(String[][] palabraEstructura,String codigoE,String codigoS,int idioma) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
        String resultado=null;
        
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
        }
        ProcesadorResultados proceso = new ProcesadorResultados();
        resultado=proceso.ResultadoTraduccion(palabraEstructura,codigoIndices2,idioma);    
        return resultado;
    }
}
