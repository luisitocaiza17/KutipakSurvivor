/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.motorTraduccion;
/**
 * @author luisito
 */

public class ProcesadorResultados {
    public String ResultadoTraduccion(String[][] palabraTraducida,String[][] estructuraSaliente,int idioma){
        String cadena="";
        for(int i=0; i<estructuraSaliente.length;i++){
            int indice= Integer.parseInt( estructuraSaliente[i][1]);
            if(idioma ==1) {
                if(i<(estructuraSaliente.length-1)){
                    String agrupacion=estructuraSaliente[i][0]+estructuraSaliente[i+1][0];
                    if(agrupacion.equals("12")){
                        cadena=cadena+palabraTraducida[indice][0];
                        continue;
                    }
                }
            }
            cadena=cadena+palabraTraducida[indice][0]+" ";
            System.out.println("cadena de salida: "+cadena);
        }
        return cadena;
    }
}
