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
                if(i<estructuraSaliente.length-1){
                    String valorEstructura=estructuraSaliente[i][0]+estructuraSaliente[i+1][0];
                    System.out.println("Estrucutura: "+valorEstructura);
                    if(valorEstructura.equals("12")||valorEstructura.equals("0M")||valorEstructura.equals("61")
                            ||valorEstructura.equals("1M")){
                        
                        if(valorEstructura.equals("12")||valorEstructura.equals("0M")||valorEstructura.equals("1M")){
                            cadena=cadena+palabraTraducida[indice][0];
                        }
                        //amdo emdo
                        if(valorEstructura.equals("61")){
                            cadena=cadena+palabraTraducida[indice][0];
                        }
                    }
                    else{
                        cadena=cadena+palabraTraducida[indice][0]+" ";
                    }
                }else{
                    cadena=cadena+palabraTraducida[indice][0]+" ";
                }
                
            }else{
                //el imdice se lo realiza para atras por ello se comprueba que sea mas que uho
                if( i>=1){
                    String valorEstructura=estructuraSaliente[i-1][0]+estructuraSaliente[i][0];
                     if(valorEstructura.equals("S6")||valorEstructura.equals("S1")||valorEstructura.equals("S4")){
                         cadena=cadena+palabraTraducida[indice][0]+"ANDO ";
                     }else{
                        cadena=cadena+palabraTraducida[indice][0]+" ";
                    }
                }else{
                    cadena=cadena+palabraTraducida[indice][0]+" ";
                }
               
            }
            
            System.out.println("cadena de salida: "+cadena);
        }
        return cadena;
    }
}