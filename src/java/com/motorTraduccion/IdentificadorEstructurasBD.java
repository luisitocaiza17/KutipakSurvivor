/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.motorTraduccion;

import com.datos.DAO.PalabrasDAO;
import com.datos.DAO.TiposPalabrasDAO;
import java.sql.SQLException;
import java.util.List;
import persistencia.tables.records.PalabrasRecord;

/**
 *
 * @author luisito
 */
public class IdentificadorEstructurasBD {

    /*Proceso de identificacion de tipo de palabras*/
    public String PalabrasTipos(String[] palabras, int idiomaId) throws Exception {
        String[][] palabraTipos = new String[palabras.length][2];
        String resultado = null;
        TiposPalabrasDAO tipos = new TiposPalabrasDAO();
        for (int i = 0; i < palabras.length; i++) {
            PalabrasDAO palabraProcesos = new PalabrasDAO();
            PalabrasRecord palabraObjeto = new PalabrasRecord();
            //primero verifica si la palabra existe
            if (!palabraProcesos.ConsultarPalabrasExisteId(palabras[i]).equals("")) {
                palabraObjeto.setNombrepalabra(palabras[i]);
                palabraObjeto.setIdiomaid(idiomaId);
                List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccion(palabraObjeto);
                /*Proceso de resolucion de ambiguedades*/
                int contador = 0;
                for (PalabrasRecord rs : results) {
                    palabraTipos[i][contador] = rs.getSignificado();
                    contador++;
                    String codigo = tipos.ConsultarTiposPalabrasId("" + rs.getTipoid());
                    palabraTipos[i][contador] = "" + codigo;
                    contador++;
                    break;
                }
            } else {
                if (idiomaId == 1) {
                    //IDIOMA ESPAÑOL
                    /*Proceso de plurales*/
                    String palabra = palabras[i];
                    String ultimaLetra = "" + palabra.substring((palabra.length() - 3), (palabra.length()));
                    if (ultimaLetra.equals("CES")) {
                        String sSubCadena = palabra.substring(0, (palabra.length() - 3))+"Z";
                        if (!palabraProcesos.ConsultarPalabrasExisteId(sSubCadena).equals("")) {
                            palabraObjeto.setNombrepalabra(sSubCadena);
                            palabraObjeto.setIdiomaid(idiomaId);
                            List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccion(palabraObjeto);
                            /*Proceso de resolucion de ambiguedades*/
                            int contador = 0;
                            for (PalabrasRecord rs : results) {
                                palabraTipos[i][contador] = rs.getSignificado() + "KUNA";
                                contador++;
                                String codigo = tipos.ConsultarTiposPalabrasId("" + rs.getTipoid());
                                palabraTipos[i][contador] = "" + codigo;
                                contador++;
                                break;
                            }
                        } else {
                            palabraTipos[i][0] = palabras[i];
                            palabraTipos[i][1] = "0";
                        }
                    } else {
                        palabra = palabras[i];
                        ultimaLetra = "" + palabra.substring((palabra.length() - 2), (palabra.length()));
                        if (ultimaLetra.equals("ES")) {
                            String sSubCadena = palabra.substring(0, (palabra.length() - 2));
                            if (!palabraProcesos.ConsultarPalabrasExisteId(sSubCadena).equals("")) {
                                palabraObjeto.setNombrepalabra(sSubCadena);
                                palabraObjeto.setIdiomaid(idiomaId);
                                List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccion(palabraObjeto);
                                /*Proceso de resolucion de ambiguedades*/
                                int contador = 0;
                                for (PalabrasRecord rs : results) {
                                    palabraTipos[i][contador] = rs.getSignificado() + "KUNA";
                                    contador++;
                                    String codigo = tipos.ConsultarTiposPalabrasId("" + rs.getTipoid());
                                    palabraTipos[i][contador] = "" + codigo;
                                    contador++;
                                    break;
                                }
                            } else {
                                palabraTipos[i][0] = palabras[i];
                                palabraTipos[i][1] = "0";
                            }
                        } else {
                            palabra = palabras[i];
                            ultimaLetra=""+palabra.charAt(palabra.length()-1);
                            if (ultimaLetra.equals("S")) {
                                String sSubCadena = palabra.substring(0,(palabra.length()-1));
                                if (!palabraProcesos.ConsultarPalabrasExisteId(sSubCadena).equals("")) {
                                    palabraObjeto.setNombrepalabra(sSubCadena);
                                    palabraObjeto.setIdiomaid(idiomaId);
                                    List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccion(palabraObjeto);
                                    /*Proceso de resolucion de ambiguedades*/
                                    int contador = 0;
                                    for (PalabrasRecord rs : results) {
                                        palabraTipos[i][contador] = rs.getSignificado() + "KUNA";
                                        contador++;
                                        String codigo = tipos.ConsultarTiposPalabrasId("" + rs.getTipoid());
                                        palabraTipos[i][contador] = "" + codigo;
                                        contador++;
                                        break;
                                    }
                                } else {
                                    palabraTipos[i][0] = palabras[i];
                                    palabraTipos[i][1] = "0";
                                }
                            }else{
                                palabraTipos[i][0] = palabras[i];
                                palabraTipos[i][1] = "0";
                            }
                        }
                    }
                } else {
                    //IDIOMA KIYWA
                    palabraTipos[i][0] = palabras[i];
                    palabraTipos[i][1] = "0";
                }

            }
        }
        //Proceso de verificacion estructural
        ComposicionEstructural comp = new ComposicionEstructural();
        resultado = comp.realizarComposicion(palabraTipos, idiomaId);

        return resultado;
    }

}
