/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negocios;

import Utils.Utilitarios;
import com.datos.DAO.ContadorDAO;
import com.datos.DAO.PalabrasDAO;
import com.datos.DAO.SugerenciasDAO;
import com.motorTraduccion.Descompositor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import persistencia.tables.Contador;
import persistencia.tables.records.ContadorRecord;
import persistencia.tables.records.PalabrasRecord;
import persistencia.tables.records.PantallapalabrasRecord;
import persistencia.tables.records.SugerenciasRecord;

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
        String proceso = request.getParameter("proceso") == null ? "" : request.getParameter("proceso");
        
       
        try{
            if(proceso.equals("normal")){
                palabra= palabra.toUpperCase();
                PalabrasDAO palabraProcesos= new PalabrasDAO();
                PalabrasRecord palabraObjeto = new PalabrasRecord();
                palabraObjeto.setNombrepalabra(palabra);
                palabraObjeto.setIdiomaid(Integer.parseInt(idioma));
                /***1).PROCESO DE DESCOMPOSICION DE PALABRAS***/
                Descompositor descompositor = new Descompositor();
                String traduccionCompleta= descompositor.descompositorOraciones(palabra,Integer.parseInt(idioma));
                result.put("success", Boolean.TRUE);
                result.put("mensaje", " PROCESO CORRECTO "); 
                result.put("traduccion", traduccionCompleta); 
                response.setContentType("application/json; charset=ISO-8859-1"); 
                result.write(response.getWriter());
            }
            if(proceso.equals("busquedaPalabras")){
                 palabra= palabra.toUpperCase();
                PalabrasDAO palabraProcesos= new PalabrasDAO();
                PalabrasRecord palabraObjeto = new PalabrasRecord();
                palabraObjeto.setNombrepalabra(palabra);
                palabraObjeto.setIdiomaid(Integer.parseInt(idioma));
                Descompositor descompositor = new Descompositor();
                ArrayList<String> traduccionCompleta= descompositor.descompositorOraciones3(palabra,Integer.parseInt(idioma));
                JSONArray JSONArrayTipos = new JSONArray();
                JSONArray JSONTraducciones = new JSONArray();
                for(String palabraIndividual:traduccionCompleta){
                        JSONArrayTipos.add(palabraIndividual);
                 }
                result.put("listadoPalabras", JSONArrayTipos);
                result.put("success", Boolean.TRUE);
                result.put("mensaje", " PROCESO CORRECTO "); 
                result.put("traduccion", traduccionCompleta); 
                response.setContentType("application/json; charset=ISO-8859-1"); 
                result.write(response.getWriter());
            }
            if(proceso.equals("contador")){
                ContadorDAO contadorDAO = new ContadorDAO();
                int contadorActual= contadorDAO.consultar();
                SugerenciasDAO sugerenciasDAO = new SugerenciasDAO();
                int contadorActualSugerencias= sugerenciasDAO.ContadorSugerencias();
                int contadorTiemposExitosos=sugerenciasDAO.ContadorTiemposExitosos();
                int contadorComentariosBuenos=sugerenciasDAO.ContadorConsultasExitosas();
                Date fecha = new Date();
                Timestamp fechaActual = new Timestamp(fecha.getTime());
                ContadorRecord nuevoContador= new ContadorRecord();
                nuevoContador.setValor(contadorActual++);
                nuevoContador.setFecha(fechaActual);
                contadorDAO.InsertarNumero(nuevoContador);
                //sugerencias completas
                ArrayList<SugerenciasRecord> sugerenciasRecord = new ArrayList<SugerenciasRecord>();
                sugerenciasRecord=sugerenciasDAO.ConsultaSugerencias();
                JSONObject planJSONObject = new JSONObject();
                JSONArray JSONArrayTipos = new JSONArray();
                JSONArray JSONTraducciones = new JSONArray();
                for(SugerenciasRecord rs:sugerenciasRecord){
                    planJSONObject.put("comentario", rs.getComentario());
                    JSONArrayTipos.add(planJSONObject);
                }
                result.put("comentariosRegitrados", JSONArrayTipos);
                result.put("success", Boolean.TRUE);
                result.put("contador", contadorActual);
                result.put("contadorSugerencias", contadorActualSugerencias);
                result.put("rapidas", contadorTiemposExitosos);
                 result.put("comentariosBuenos", contadorComentariosBuenos);
                response.setContentType("application/json; charset=ISO-8859-1"); 
                result.write(response.getWriter());
            }
            if(proceso.equals("comentarios")){
                SugerenciasDAO sugerenciasDAO = new SugerenciasDAO();
                int contadorActual= sugerenciasDAO.ContadorSugerencias();
                SugerenciasRecord nuevaSugerencia= new SugerenciasRecord();
                String tiempo = request.getParameter("tiempo") == null ? "" : request.getParameter("tiempo");
                String sirvio = request.getParameter("sirvio") == null ? "" : request.getParameter("sirvio");
                String comentario = request.getParameter("comentario") == null ? "" : request.getParameter("comentario");
                
                boolean tiemporespuesta=false;
                if(tiempo.equals("SI"))
                    tiemporespuesta=true;
                boolean sirviorespuesta=false;
                if(sirvio.equals("SI"))
                    sirviorespuesta=true; 
                Date fecha = new Date();
                Timestamp fechaActual = new Timestamp(fecha.getTime());
                nuevaSugerencia.setFecha(fechaActual);
                nuevaSugerencia.setSirvio(tiemporespuesta);
                nuevaSugerencia.setTiempo(sirviorespuesta);
                nuevaSugerencia.setComentario(comentario);
                int contadorTiemposExitosos=sugerenciasDAO.ContadorTiemposExitosos();
                int contadorComentariosBuenos=sugerenciasDAO.ContadorConsultasExitosas();
                sugerenciasDAO.GrabarSugerencia(nuevaSugerencia);
                
                
                //sugerencias completas
                ArrayList<SugerenciasRecord> sugerenciasRecord = new ArrayList<SugerenciasRecord>();
                sugerenciasRecord=sugerenciasDAO.ConsultaSugerencias();
                JSONObject planJSONObject = new JSONObject();
                JSONArray JSONArrayTipos = new JSONArray();
                JSONArray JSONTraducciones = new JSONArray();
                for(SugerenciasRecord rs:sugerenciasRecord){
                    planJSONObject.put("comentario", rs.getComentario());
                    JSONArrayTipos.add(planJSONObject);
                }
                result.put("comentariosRegitrados", JSONArrayTipos);
                result.put("success", Boolean.TRUE);
                result.put("contadorSugerencias", contadorActual);
                result.put("rapidas", contadorTiemposExitosos);
                result.put("comentariosBuenos", contadorComentariosBuenos);
                response.setContentType("application/json; charset=ISO-8859-1"); 
                result.write(response.getWriter());
            }
            if(proceso.equals("correo")){
                String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
                String email = request.getParameter("email") == null ? "" : request.getParameter("email");
                String Asunto = request.getParameter("Asunto") == null ? "" : request.getParameter("Asunto");
                String mensaje = request.getParameter("message") == null ? "" : request.getParameter("message");
                
                
                
                Utilitarios utils = new  Utilitarios();
                Utilitarios.envioMail("luis.caiza@smartwork.com.ec", Asunto+ " "+nombre,email+"  "+ mensaje);
                        
                result.put("success", Boolean.TRUE);
                response.setContentType("application/json; charset=ISO-8859-1"); 
                result.write(response.getWriter());
            }
            
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