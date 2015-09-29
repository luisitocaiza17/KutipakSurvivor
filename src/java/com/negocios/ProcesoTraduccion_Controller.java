/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negocios;

import com.datos.DAO.PalabrasDAO;
import com.motorTraduccion.ComposicionEstructural;
import com.motorTraduccion.Descompositor;
import com.motorTraduccion.IdentificadorEstructura;
import com.motorTraduccion.IdentificadorEstructurasBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import persistencia.tables.records.PalabrasRecord;

/**
 *
 * @author luisito
 */
@WebServlet(name = "ProcesoTraduccion_Controller", urlPatterns = {"/ProcesoTraduccion_Controller"})
public class ProcesoTraduccion_Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProcesoTraduccion_Controller</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProcesoTraduccion_Controller at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        JSONObject result = new JSONObject();
        String palabra = request.getParameter("palabra") == null ? "" : request.getParameter("palabra");
        String idioma = request.getParameter("idioma") == null ? "" : request.getParameter("idioma");
        
        palabra= palabra.toUpperCase();
        
        PalabrasDAO palabraProcesos= new PalabrasDAO();
        PalabrasRecord palabraObjeto = new PalabrasRecord();
	palabraObjeto.setNombrepalabra(palabra);
        palabraObjeto.setIdiomaid(Integer.parseInt(idioma));
        try{
            /***1).PROCESO DE DESCOMPOSICION DE PALABRAS***/
            Descompositor descompositor = new Descompositor();
            String oraciones[]= descompositor.descompositorOraciones(palabra);
            for (String a : oraciones)
            {
                String palabras[]= descompositor.descompositorPalabras(a);
                /***2) PROCESO DE IDENTIFICACION DE PALABRAS (ESTRUCTURA GRAMATICAL)***/
                IdentificadorEstructurasBD  identificador = new IdentificadorEstructurasBD();
                String[][] palabraTipos=identificador.PalabrasTipos(palabras,palabraObjeto.getIdiomaid());
                for(int i=0;i<palabraTipos.length;i++){
                    //Almacenamiento de estructura de la Oracion
                     System.out.println("significado: "+palabraTipos[i][0]+ " tipo palabra: "+palabraTipos[i][1]);
                }
                
                /*PROCESO DE ANALISIS ESTRUCTURAL, TRADUCCION Y RESOLUCION DE AMBIGUEDADES*/
                String [][] procesosETA=new String[palabraTipos.length][palabraTipos[0].length];
                
                 if(palabraTipos[0].length>0){
                    for(int i=0;i<palabraTipos.length;i++){
                    //Almacenamiento de estructura de la Oracion
                     int contadorNormal=0;
                    for(int ambiguedad=0; ambiguedad<palabraTipos[0].length;ambiguedad=ambiguedad+2){
                        
                        String significado=""+palabraTipos[i][ambiguedad];
                        String tipo=""+palabraTipos[i][ambiguedad+1];
                        if(tipo.equals("null")){
                            significado=palabraTipos[i][0];
                            tipo=palabraTipos[i][1];
                        }
                        procesosETA[i][contadorNormal]=significado;
                        contadorNormal++;
                        procesosETA[i][contadorNormal]=tipo;
                        contadorNormal++;
                        //System.out.println("significado2: "+significado+ " tipo palabra2: "+tipo);
                        
                     }                   
                  }
                }
                /*for(int i=0;i<ambiguedades.length;i++){
                    for(int j=0;j<ambiguedades[0].length;j=j+2){
                        //Almacenamiento de estructura de la Oracion
                        System.out.println("significado: "+ambiguedades[i][j]+ " tipo palabra: "+ambiguedades[i][j+1]);
                    }
                    
                }*/
                
                for(int i=0;i<procesosETA[0].length;i=i+2){
                    for(int j=0;j<procesosETA.length;j++){
                        System.out.println("palabra"+procesosETA[j][i]);
                    }
                }
                
                ComposicionEstructural compocion = new ComposicionEstructural();
                compocion.realizarComposicion(procesosETA);
            }
            
            
            /*JSONObject planJSONObject = new JSONObject();
            if(!palabraProcesos.ConsultarPalabrasExisteId(palabra).equals(""))
            {
                JSONArray JSONArrayPalabraSignificado = new JSONArray();
                List<PalabrasRecord> results = palabraProcesos.ConsultarPalabrasTraduccion(palabraObjeto);
                for(PalabrasRecord rs : results){
                    planJSONObject.put("traduccion", rs.getSignificado());
                    JSONArrayPalabraSignificado.add(planJSONObject);
                }
                result.put("ListadoSignificados",JSONArrayPalabraSignificado);
                result.put("success", Boolean.TRUE);
                response.setContentType("application/json; charset=ISO-8859-1"); 
                result.write(response.getWriter());
            }else{                
                result.put("success", Boolean.FALSE);
                result.put("mensaje", " PALABRA NO ENCONTRADA :( !!"); 
                response.setContentType("application/json; charset=ISO-8859-1"); 
                result.write(response.getWriter());
            }
            */
            
        }catch(Exception e){
            e.printStackTrace();
            result.put("success", Boolean.FALSE);
            result.put("mensaje", " PALABRA NO ENCONTRADA :( !!"); 
            response.setContentType("application/json; charset=ISO-8859-1"); 
            result.write(response.getWriter());
        }
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
