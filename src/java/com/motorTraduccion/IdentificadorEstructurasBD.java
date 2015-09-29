/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.motorTraduccion;

import com.datos.DAO.PalabrasDAO;
import java.sql.SQLException;
import java.util.List;
import persistencia.tables.records.PalabrasRecord;

/**
 *
 * @author luisito
 */
public class IdentificadorEstructurasBD {
    
    /*Proceso de identificacion de tipo de palabras*/
    public String[][] PalabrasTipos(String[] palabras,int idiomaId) throws Exception{
        String[][] palabraTipos = new String[palabras.length][palabras.length];
        int ambiguedad = 0;
        for (int i=0; i<palabras.length;i++){
            PalabrasDAO palabraProcesos= new PalabrasDAO();
            PalabrasRecord palabraObjeto = new PalabrasRecord();
            if(!palabraProcesos.ConsultarPalabrasExisteId(palabras[i]).equals(""))
            {
                palabraObjeto.setNombrepalabra(palabras[i]);
                palabraObjeto.setIdiomaid(idiomaId);
                List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccion(palabraObjeto);
                /*Proceso de resolucion de ambiguedades*/
                ambiguedad = 0;
                for(PalabrasRecord rs : results){
                    palabraTipos[i][ambiguedad]=rs.getSignificado();
                     ambiguedad++;
                    palabraTipos[i][ambiguedad]=""+rs.getTipoid();
                     ambiguedad++;
                }
            }
            else{
                    palabraTipos[i][0]=palabras[i];
                    palabraTipos[i][1]="1";
            }
        }
        String[][] palabraEncontradas = new String[palabraTipos.length][ambiguedad];
        for(int i=0;i<palabraTipos.length;i++){
            for(int j=0;j<ambiguedad;j++){
                palabraEncontradas[i][j]=palabraTipos[i][j];
            }
        }
        
        
        return palabraEncontradas;
    } 
    
}
