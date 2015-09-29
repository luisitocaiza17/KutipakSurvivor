/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.motorTraduccion;

/**
 *
 * @author luisito
 */
public class ComposicionEstructural {
    public String[][] realizarComposicion(String[][] traduccionTipos){
        
        String codigoEntrante="";
        for(int i=1;i<traduccionTipos[0].length;i=i+2){
            for(int j=0;j<traduccionTipos.length;j++){
                codigoEntrante=codigoEntrante+""+traduccionTipos[j][i];
            }
            codigoEntrante=codigoEntrante+"||";
        }
        System.out.println("codigoEntrante"+codigoEntrante);
        
        return null;
        
        
    }
}
