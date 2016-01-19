/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.motorTraduccion;

import com.datos.DAO.PalabrasDAO;
import com.datos.DAO.TiposPalabrasDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import persistencia.tables.Tipospalabras;
import persistencia.tables.records.PalabrasRecord;
import persistencia.tables.records.TipospalabrasRecord;

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
            if (palabraProcesos.ConsultarPalabrasExisteId(palabras[i], idiomaId).getPalabraid() != null) {
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
                                
                    if (palabra.length() > 3) {
                        String ultimaLetra = "" + palabra.substring((palabra.length() - 3), (palabra.length()));
                        if (ultimaLetra.equals("CES")) {
                            String sSubCadena = palabra.substring(0, (palabra.length() - 3)) + "Z";
                            if (palabraProcesos.ConsultarPalabrasExisteId(sSubCadena, idiomaId).getPalabraid() != null) {
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
                                String palabraSimilar = palabraTipos[i][0] = palabras[i];
                                if (palabraSimilar.length() > 3) {
                                    bucle:
                                    for (int a = palabraSimilar.length(); a > (palabraSimilar.length()-1); a--) {
                                        PalabrasRecord palabraObjeto2 = new PalabrasRecord();
                                        palabraSimilar = palabraSimilar.substring(0, a);
                                        palabraObjeto2.setNombrepalabra(palabraSimilar);
                                        palabraObjeto2.setIdiomaid(idiomaId);
                                        int tamanio = palabraSimilar.length() + 3;
                                        List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccionSimilar(palabraObjeto2, tamanio);
                                        for (PalabrasRecord rs : results) {
                                            palabraTipos[i][0] = rs.getSignificado();
                                            String codigo = tipos.ConsultarTiposPalabrasId("" + rs.getTipoid());
                                            palabraTipos[i][1] = codigo;
                                            break bucle;
                                        }
                                        if (a == 3) {
                                            palabraTipos[i][0] = palabras[i];
                                            palabraTipos[i][1] = "0";
                                            break;
                                        }
                                    }
                                } else {
                                    palabraTipos[i][0] = palabras[i];
                                    palabraTipos[i][1] = "0";
                                }
                            }
                        } else {
                            palabra = palabras[i];
                            ultimaLetra = "" + palabra.substring((palabra.length() - 2), (palabra.length()));
                            String palabraExiste2=palabra.substring(0,(palabra.length() - 2));
                                
                            if (ultimaLetra.equals("ES")) {
                                String sSubCadena = palabra.substring(0, (palabra.length() - 2));
                                if (palabraProcesos.ConsultarPalabrasExisteId(palabraExiste2, idiomaId).getPalabraid() != null) {
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
                                    String palabraSimilar = palabraTipos[i][0] = palabras[i];
                                    if (palabraSimilar.length() > 3) {
                                        bucle:
                                        for (int a = palabraSimilar.length(); a > (palabraSimilar.length()-1); a--) {
                                            PalabrasRecord palabraObjeto2 = new PalabrasRecord();
                                            palabraSimilar = palabraSimilar.substring(0, a);
                                            palabraObjeto2.setNombrepalabra(palabraSimilar);
                                            palabraObjeto2.setIdiomaid(idiomaId);
                                            int tamanio = palabraSimilar.length() + 3;
                                            List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccionSimilar(palabraObjeto2, tamanio);
                                            for (PalabrasRecord rs : results) {
                                                palabraTipos[i][0] = rs.getSignificado();
                                                String codigo = tipos.ConsultarTiposPalabrasId("" + rs.getTipoid());
                                                palabraTipos[i][1] = codigo;
                                                break bucle;
                                            }
                                            if (a == 3) {
                                                palabraTipos[i][0] = palabras[i];
                                                palabraTipos[i][1] = "0";
                                                break;
                                            }
                                        }
                                    } else {
                                        palabraTipos[i][0] = palabras[i];
                                        palabraTipos[i][1] = "0";
                                    }
                                }
                            } else {
                                palabra = palabras[i];
                                ultimaLetra = "" + palabra.charAt(palabra.length() - 1);
                                String palabraExiste3=palabra.substring(0,(palabra.length() - 1));
                                
                                if (ultimaLetra.equals("S")) {
                                    String sSubCadena = palabra.substring(0, (palabra.length() - 1));
                                    if (palabraProcesos.ConsultarPalabrasExisteId(palabraExiste3, idiomaId).getPalabraid() != null) {
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
                                        String palabraSimilar = palabraTipos[i][0] = palabras[i];
                                        if (palabraSimilar.length() > 3) {
                                            bucle:
                                            for (int a = palabraSimilar.length(); a > (palabraSimilar.length()-1); a--) {
                                                PalabrasRecord palabraObjeto2 = new PalabrasRecord();
                                                palabraSimilar = palabraSimilar.substring(0, a);
                                                palabraObjeto2.setNombrepalabra(palabraSimilar);
                                                palabraObjeto2.setIdiomaid(idiomaId);
                                                int tamanio = palabraSimilar.length() + 3;
                                                List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccionSimilar(palabraObjeto2, tamanio);
                                                for (PalabrasRecord rs : results) {
                                                    palabraTipos[i][0] = rs.getSignificado();
                                                    String codigo = tipos.ConsultarTiposPalabrasId("" + rs.getTipoid());
                                                    palabraTipos[i][1] = codigo;
                                                    break bucle;
                                                }
                                                if (a == 3) {
                                                    palabraTipos[i][0] = palabras[i];
                                                    palabraTipos[i][1] = "0";
                                                    break;
                                                }
                                            }
                                        } else {
                                            palabraTipos[i][0] = palabras[i];
                                            palabraTipos[i][1] = "0";
                                        }
                                    }
                                } else {
                                    String palabraSimilar = palabraTipos[i][0] = palabras[i];
                                    if (palabraSimilar.length() > 3) {
                                        bucle:
                                        for (int a = palabraSimilar.length(); a > (palabraSimilar.length()-1); a--) {
                                            PalabrasRecord palabraObjeto2 = new PalabrasRecord();
                                            palabraSimilar = palabraSimilar.substring(0, a);
                                            palabraObjeto2.setNombrepalabra(palabraSimilar);
                                            palabraObjeto2.setIdiomaid(idiomaId);
                                            int tamanio = palabraSimilar.length() + 3;
                                            List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccionSimilar(palabraObjeto2, tamanio);
                                            for (PalabrasRecord rs : results) {
                                                palabraTipos[i][0] = rs.getSignificado();
                                                String codigo = tipos.ConsultarTiposPalabrasId("" + rs.getTipoid());
                                                palabraTipos[i][1] = codigo;
                                                break bucle;
                                            }
                                            if (a == 3) {
                                                palabraTipos[i][0] = palabras[i];
                                                palabraTipos[i][1] = "0";
                                                break;
                                            }
                                        }
                                    } else {
                                        palabraTipos[i][0] = palabras[i];
                                        palabraTipos[i][1] = "0";
                                    }
                                }

                            }
                        }
                    } else {
                        palabraTipos[i][0] = palabras[i];
                        palabraTipos[i][1] = "0";
                    }
                } else {
                    //IDIOMA KIYWA
                    String palabra = palabras[i] + " ";
                    String palabraBuscar = "KUNA ";
                    TiposPalabrasDAO tiposPalabrasDAO = new TiposPalabrasDAO();
                    TipospalabrasRecord tiposPalabras = new TipospalabrasRecord();
                    tiposPalabras = tiposPalabrasDAO.ConsultarPalabraEspecificosId("SUSTANTIVO");
                    boolean existePlural = false;
                    existePlural = palabra.contains(palabraBuscar);
                    if (!existePlural) {
                        String palabraSimilar = palabraTipos[i][0] = palabras[i];
                        if (palabraSimilar.length() > 3) {
                            bucle:
                            for (int a = palabraSimilar.length(); a > (palabraSimilar.length()-1); a--) {
                                PalabrasRecord palabraObjeto2 = new PalabrasRecord();
                                palabraSimilar = palabraSimilar.substring(0, a);
                                palabraObjeto2.setNombrepalabra(palabraSimilar);
                                palabraObjeto2.setIdiomaid(idiomaId);
                                int tamanio = palabraSimilar.length() + 3;
                                List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccionSimilar(palabraObjeto2, tamanio);
                                for (PalabrasRecord rs : results) {
                                    palabraTipos[i][0] = rs.getSignificado();
                                    String codigo = tipos.ConsultarTiposPalabrasId("" + rs.getTipoid());
                                    palabraTipos[i][1] = codigo;
                                    break bucle;
                                }
                                if (a == 3) {
                                    palabraTipos[i][0] = palabras[i];
                                    palabraTipos[i][1] = "0";
                                    break;
                                }
                            }
                        } else {
                            palabraTipos[i][0] = palabras[i];
                            palabraTipos[i][1] = "0";
                        };
                    } else {
                        String[] cadenasPlural = palabra.split("KUNA");
                        for (int h = 0; h < cadenasPlural.length; h++) {
                            palabra = cadenasPlural[0];
                            System.out.println("La palabra esecencial kitchwa es: " + palabra);
                            if (palabraProcesos.ConsultarPalabrasExisteId(palabra, idiomaId).getPalabraid() != null
                                    && palabraProcesos.ConsultarPalabrasExisteId(palabra, idiomaId).getTipoid().equals(tiposPalabras.getTipoid())) {
                                palabraObjeto.setNombrepalabra(palabra);
                                palabraObjeto.setIdiomaid(idiomaId);
                                List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccion(palabraObjeto);
                                /*Proceso de resolucion de ambiguedades*/
                                int contador = 0;
                                for (PalabrasRecord rs : results) {
                                    String significado = rs.getSignificado();
                                    String ultimas = significado.substring(significado.length() - 1, significado.length());
                                    if (significado.length() > 3) {
                                        if (ultimas.equals("A") || ultimas.equals("E") || ultimas.equals("O")) {
                                            significado = significado + "S";
                                        } else {
                                            if (ultimas.equals("Z")) {
                                                significado = significado.substring(0, significado.length() - 1) + "CES";
                                            } else {
                                                significado = significado + "ES";
                                            }
                                        }
                                        palabraTipos[i][contador] = significado;
                                        contador++;
                                        String codigo = tipos.ConsultarTiposPalabrasId("" + rs.getTipoid());
                                        palabraTipos[i][contador] = "" + codigo;
                                    } else {
                                        palabraTipos[i][contador] = rs.getSignificado() + "S";
                                        contador++;
                                        String codigo = tipos.ConsultarTiposPalabrasId("" + rs.getTipoid());
                                        palabraTipos[i][contador] = "" + codigo;
                                    }

                                    contador++;
                                    break;
                                }
                            } else {
                                palabra = cadenasPlural[0] + "NA";
                                if (palabraProcesos.ConsultarPalabrasExisteId(palabra, idiomaId).getPalabraid() != null) {
                                    palabraObjeto.setNombrepalabra(palabra);
                                    palabraObjeto.setIdiomaid(idiomaId);
                                    List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccion(palabraObjeto);
                                    /*Proceso de resolucion de ambiguedades*/
                                    int contador = 0;
                                    for (PalabrasRecord rs : results) {
                                        String significado = rs.getSignificado();
                                        palabraTipos[i][contador] = "ESTAR " + significado;
                                        contador++;
                                        String codigo = tipos.ConsultarTiposPalabrasId("" + rs.getTipoid());
                                        palabraTipos[i][contador] = "" + codigo;
                                        contador++;
                                        break;
                                    }

                                } else {
                                    String palabraSimilar = palabraTipos[i][0] = palabras[i];
                                    if (palabraSimilar.length() > 3) {
                                        bucle:
                                        for (int a = palabraSimilar.length(); a > (palabraSimilar.length()-1); a--) {
                                            PalabrasRecord palabraObjeto2 = new PalabrasRecord();
                                            palabraSimilar = palabraSimilar.substring(0, a);
                                            palabraObjeto2.setNombrepalabra(palabraSimilar);
                                            palabraObjeto2.setIdiomaid(idiomaId);
                                            int tamanio = palabraSimilar.length() + 3;
                                            List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccionSimilar(palabraObjeto2, tamanio);
                                            for (PalabrasRecord rs : results) {
                                                palabraTipos[i][0] = rs.getSignificado();
                                                String codigo = tipos.ConsultarTiposPalabrasId("" + rs.getTipoid());
                                                palabraTipos[i][1] = codigo;
                                                break bucle;
                                            }
                                            if (a == 3) {
                                                palabraTipos[i][0] = palabras[i];
                                                palabraTipos[i][1] = "0";
                                                break;
                                            }
                                        }
                                    } else {
                                        palabraTipos[i][0] = palabras[i];
                                        palabraTipos[i][1] = "0";
                                    }
                                }

                            }
                        }
                    }
                }

            }
        }
        //Proceso de verificacion estructural
        ComposicionEstructural comp = new ComposicionEstructural();
        resultado = comp.realizarComposicion(palabraTipos, idiomaId);

        return resultado;
    }
    
    /*Proceso de identificacion de tipo de palabras*/
    public ArrayList<String> PalabrasSignificado(String[] palabras, int idiomaId) throws Exception {
        String[][] palabraTipos = new String[palabras.length][2];
        ArrayList<String> resultado = new ArrayList<String>();
        TiposPalabrasDAO tipos = new TiposPalabrasDAO();
        for (int i = 0; i < palabras.length; i++) {
            PalabrasDAO palabraProcesos = new PalabrasDAO();
            PalabrasRecord palabraObjeto = new PalabrasRecord();
            //primero verifica si la palabra existe
            if (palabraProcesos.ConsultarPalabrasExisteId(palabras[i], idiomaId).getPalabraid() != null) {
                palabraObjeto.setNombrepalabra(palabras[i]);
                palabraObjeto.setIdiomaid(idiomaId);
                List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccion(palabraObjeto);
                /*Proceso de resolucion de ambiguedades*/
                int contador = 0;
                for (PalabrasRecord rs : results) {
                    resultado.add(rs.getSignificado());
                    contador++;
                }
            } else {
                if (idiomaId == 1) {
                    //IDIOMA ESPAÑOL
                    /*Proceso de plurales*/
                    String palabra = palabras[i];
                                
                    if (palabra.length() > 3) {
                        String ultimaLetra = "" + palabra.substring((palabra.length() - 3), (palabra.length()));
                        if (ultimaLetra.equals("CES")) {
                            String sSubCadena = palabra.substring(0, (palabra.length() - 3)) + "Z";
                            if (palabraProcesos.ConsultarPalabrasExisteId(sSubCadena, idiomaId).getPalabraid() != null) {
                                palabraObjeto.setNombrepalabra(sSubCadena);
                                palabraObjeto.setIdiomaid(idiomaId);
                                List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccion(palabraObjeto);
                                /*Proceso de resolucion de ambiguedades*/
                                int contador = 0;
                                for (PalabrasRecord rs : results) {
                                    resultado.add(rs.getSignificado() + "KUNA");
                                    contador++;
                                }
                            } else {
                                String palabraSimilar = palabraTipos[i][0] = palabras[i];
                                if (palabraSimilar.length() > 3) {
                                    bucle:
                                    for (int a = palabraSimilar.length(); a > (palabraSimilar.length()-1); a--) {
                                        PalabrasRecord palabraObjeto2 = new PalabrasRecord();
                                        palabraSimilar = palabraSimilar.substring(0, a);
                                        palabraObjeto2.setNombrepalabra(palabraSimilar);
                                        palabraObjeto2.setIdiomaid(idiomaId);
                                        int tamanio = palabraSimilar.length() + 3;
                                        List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccionSimilar(palabraObjeto2, tamanio);
                                        for (PalabrasRecord rs : results) {
                                            resultado.add(rs.getSignificado());
                                        }
                                        if (a == 3) {
                                            resultado.add(palabras[i]);
                                        }
                                    }
                                } else {
                                    resultado.add(palabras[i]);
                                }
                            }
                        } else {
                            palabra = palabras[i];
                            ultimaLetra = "" + palabra.substring((palabra.length() - 2), (palabra.length()));
                            String palabraExiste2=palabra.substring(0,(palabra.length() - 2));
                                
                            if (ultimaLetra.equals("ES")) {
                                String sSubCadena = palabra.substring(0, (palabra.length() - 2));
                                if (palabraProcesos.ConsultarPalabrasExisteId(palabraExiste2, idiomaId).getPalabraid() != null) {
                                    palabraObjeto.setNombrepalabra(sSubCadena);
                                    palabraObjeto.setIdiomaid(idiomaId);
                                    List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccion(palabraObjeto);
                                    /*Proceso de resolucion de ambiguedades*/
                                    int contador = 0;
                                    for (PalabrasRecord rs : results) {
                                       resultado.add(rs.getSignificado() + "KUNA");
                                        
                                    }
                                } else {
                                    String palabraSimilar = palabraTipos[i][0] = palabras[i];
                                    if (palabraSimilar.length() > 3) {
                                        bucle:
                                        for (int a = palabraSimilar.length(); a > (palabraSimilar.length()-1); a--) {
                                            PalabrasRecord palabraObjeto2 = new PalabrasRecord();
                                            palabraSimilar = palabraSimilar.substring(0, a);
                                            palabraObjeto2.setNombrepalabra(palabraSimilar);
                                            palabraObjeto2.setIdiomaid(idiomaId);
                                            int tamanio = palabraSimilar.length() + 3;
                                            List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccionSimilar(palabraObjeto2, tamanio);
                                            for (PalabrasRecord rs : results) {
                                                resultado.add( rs.getSignificado());
                                                
                                            }
                                            if (a == 3) {
                                               resultado.add( palabras[i]);
                                               
                                            }
                                        }
                                    } else {
                                       resultado.add( palabras[i]);
                                       
                                    }
                                }
                            } else {
                                palabra = palabras[i];
                                ultimaLetra = "" + palabra.charAt(palabra.length() - 1);
                                String palabraExiste3=palabra.substring(0,(palabra.length() - 1));
                                
                                if (ultimaLetra.equals("S")) {
                                    String sSubCadena = palabra.substring(0, (palabra.length() - 1));
                                    if (palabraProcesos.ConsultarPalabrasExisteId(palabraExiste3, idiomaId).getPalabraid() != null) {
                                        palabraObjeto.setNombrepalabra(sSubCadena);
                                        palabraObjeto.setIdiomaid(idiomaId);
                                        List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccion(palabraObjeto);
                                        /*Proceso de resolucion de ambiguedades*/
                                        int contador = 0;
                                        for (PalabrasRecord rs : results) {
                                            resultado.add( rs.getSignificado() + "KUNA");
                                            contador++;
                                           
                                        }
                                    } else {
                                        String palabraSimilar = palabraTipos[i][0] = palabras[i];
                                        if (palabraSimilar.length() > 3) {
                                            bucle:
                                            for (int a = palabraSimilar.length(); a > (palabraSimilar.length()-1); a--) {
                                                PalabrasRecord palabraObjeto2 = new PalabrasRecord();
                                                palabraSimilar = palabraSimilar.substring(0, a);
                                                palabraObjeto2.setNombrepalabra(palabraSimilar);
                                                palabraObjeto2.setIdiomaid(idiomaId);
                                                int tamanio = palabraSimilar.length() + 3;
                                                List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccionSimilar(palabraObjeto2, tamanio);
                                                for (PalabrasRecord rs : results) {
                                                    resultado.add( rs.getSignificado());
                                                   
                                                }
                                                if (a == 3) {
                                                   resultado.add( palabras[i]);
                                                    
                                                }
                                            }
                                        } else {
                                           resultado.add(palabras[i]);
                                           
                                        }
                                    }
                                } else {
                                    String palabraSimilar = palabraTipos[i][0] = palabras[i];
                                    if (palabraSimilar.length() > 3) {
                                        bucle:
                                        for (int a = palabraSimilar.length(); a > (palabraSimilar.length()-1); a--) {
                                            PalabrasRecord palabraObjeto2 = new PalabrasRecord();
                                            palabraSimilar = palabraSimilar.substring(0, a);
                                            palabraObjeto2.setNombrepalabra(palabraSimilar);
                                            palabraObjeto2.setIdiomaid(idiomaId);
                                            int tamanio = palabraSimilar.length() + 3;
                                            List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccionSimilar(palabraObjeto2, tamanio);
                                            for (PalabrasRecord rs : results) {
                                               resultado.add(rs.getSignificado());
                                                
                                            }
                                            if (a == 3) {
                                              resultado.add( palabras[i]);
                                               
                                            }
                                        }
                                    } else {
                                       resultado.add( palabras[i]);
                                      
                                    }
                                }

                            }
                        }
                    } else {
                        resultado.add(palabras[i]);
                        
                    }
                } else {
                    //IDIOMA KIYWA
                    String palabra = palabras[i] + " ";
                    String palabraBuscar = "KUNA ";
                    TiposPalabrasDAO tiposPalabrasDAO = new TiposPalabrasDAO();
                    TipospalabrasRecord tiposPalabras = new TipospalabrasRecord();
                    tiposPalabras = tiposPalabrasDAO.ConsultarPalabraEspecificosId("SUSTANTIVO");
                    boolean existePlural = false;
                    existePlural = palabra.contains(palabraBuscar);
                    if (!existePlural) {
                        String palabraSimilar = palabraTipos[i][0] = palabras[i];
                        if (palabraSimilar.length() > 3) {
                            bucle:
                            for (int a = palabraSimilar.length(); a > (palabraSimilar.length()-1); a--) {
                                PalabrasRecord palabraObjeto2 = new PalabrasRecord();
                                palabraSimilar = palabraSimilar.substring(0, a);
                                palabraObjeto2.setNombrepalabra(palabraSimilar);
                                palabraObjeto2.setIdiomaid(idiomaId);
                                int tamanio = palabraSimilar.length() + 3;
                                List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccionSimilar(palabraObjeto2, tamanio);
                                for (PalabrasRecord rs : results) {
                                    resultado.add( rs.getSignificado());
                                   
                                }
                                if (a == 3) {
                                   resultado.add( palabras[i]);
                                   
                                }
                            }
                        } else {
                           resultado.add( palabras[i]);
                          
                        };
                    } else {
                        String[] cadenasPlural = palabra.split("KUNA");
                        for (int h = 0; h < cadenasPlural.length; h++) {
                            palabra = cadenasPlural[0];
                            System.out.println("La palabra esecencial kitchwa es: " + palabra);
                            if (palabraProcesos.ConsultarPalabrasExisteId(palabra, idiomaId).getPalabraid() != null
                                    && palabraProcesos.ConsultarPalabrasExisteId(palabra, idiomaId).getTipoid().equals(tiposPalabras.getTipoid())) {
                                palabraObjeto.setNombrepalabra(palabra);
                                palabraObjeto.setIdiomaid(idiomaId);
                                List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccion(palabraObjeto);
                                /*Proceso de resolucion de ambiguedades*/
                                int contador = 0;
                                for (PalabrasRecord rs : results) {
                                    String significado = rs.getSignificado();
                                    String ultimas = significado.substring(significado.length() - 1, significado.length());
                                    if (significado.length() > 3) {
                                        if (ultimas.equals("A") || ultimas.equals("E") || ultimas.equals("O")) {
                                            significado = significado + "S";
                                        } else {
                                            if (ultimas.equals("Z")) {
                                                significado = significado.substring(0, significado.length() - 1) + "CES";
                                            } else {
                                                significado = significado + "ES";
                                            }
                                        }
                                        resultado.add( significado);
                                        
                                    } else {
                                        resultado.add(rs.getSignificado() + "S");
                                        
                                    }

                                    contador++;
                                    break;
                                }
                            } else {
                                palabra = cadenasPlural[0] + "NA";
                                if (palabraProcesos.ConsultarPalabrasExisteId(palabra, idiomaId).getPalabraid() != null) {
                                    palabraObjeto.setNombrepalabra(palabra);
                                    palabraObjeto.setIdiomaid(idiomaId);
                                    List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccion(palabraObjeto);
                                    /*Proceso de resolucion de ambiguedades*/
                                    int contador = 0;
                                    for (PalabrasRecord rs : results) {
                                        String significado = rs.getSignificado();
                                        resultado.add( "ESTAR " + significado);
                                        
                                    }

                                } else {
                                    String palabraSimilar = palabraTipos[i][0] = palabras[i];
                                    if (palabraSimilar.length() > 3) {
                                        bucle:
                                        for (int a = palabraSimilar.length(); a > (palabraSimilar.length()-1); a--) {
                                            PalabrasRecord palabraObjeto2 = new PalabrasRecord();
                                            palabraSimilar = palabraSimilar.substring(0, a);
                                            palabraObjeto2.setNombrepalabra(palabraSimilar);
                                            palabraObjeto2.setIdiomaid(idiomaId);
                                            int tamanio = palabraSimilar.length() + 3;
                                            List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccionSimilar(palabraObjeto2, tamanio);
                                            for (PalabrasRecord rs : results) {
                                                resultado.add( rs.getSignificado());
                                               
                                            }
                                            if (a == 3) {
                                               resultado.add( palabras[i]);
                                               
                                            }
                                        }
                                    } else {
                                        resultado.add( palabras[i]);
                                        
                                    }
                                }

                            }
                        }
                    }
                }

            }
        }
        return resultado;
    }
    
}
