/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negocios;

import com.datos.DAO.PalabrasDAO;
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
            JSONObject planJSONObject = new JSONObject();
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
