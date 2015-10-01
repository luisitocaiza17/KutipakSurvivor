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
    public String[][] realizarComposicion(String[][] traduccionTipos, int idioma) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
        
        String codigoEntrante="";
        for(int i=1;i<traduccionTipos[0].length;i=i+2){
            for(int j=0;j<traduccionTipos.length;j++){
                codigoEntrante=codigoEntrante+""+traduccionTipos[j][i];
            }
            codigoEntrante=codigoEntrante+"-";
        }
        System.out.println("codigoEntrante "+codigoEntrante);
        String[] codigosArray = codigoEntrante.split("-");
        
        for (int i = 0; i < codigosArray.length; i++) {
            String codigoSaliente=DecodificardorEstructural(codigosArray[i],idioma);
            
            if(codigoSaliente==null||codigoSaliente.equals(""))
                System.out.println(codigosArray[i]);
            else
                System.out.println(codigoSaliente);
        }
        return null;
    }
    
    public String  DecodificardorEstructural(String codigoEntrante, int idioma) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
        EstructuraDAO estructura = new EstructuraDAO();
        EstructuraRecord estructuraEntrante= new EstructuraRecord();
        estructuraEntrante.setFormula(codigoEntrante);
        estructuraEntrante.setIdiomaid(idioma);
        List<EstructuraRecord> resultado = estructura.ConsultarEstructuraEspecificaIdioma(estructuraEntrante);
        String codigoResultante="";
        for(EstructuraRecord rs :resultado){
           codigoResultante=rs.getFormulasalida();
           break;
        }
        return codigoResultante;
    }
}
