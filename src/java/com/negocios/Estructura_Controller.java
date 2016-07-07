/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negocios;

import com.datos.DAO.EstructuraDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import persistencia.tables.records.EstructuraRecord;


/**
 *
 * @author Administrator
 */
@WebServlet(name = "Estructura_Controller", urlPatterns = {"/Estructura_Controller"})
public class Estructura_Controller extends HttpServlet {

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
            out.println("<title>Servlet Estructura_Controller</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Estructura_Controller at " + request.getContextPath() + "</h1>");
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
        doPost(request, response);
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
        String tipoConsulta = request.getParameter("tipoConsulta") == null ? "": request.getParameter("tipoConsulta");
        // get the take and skip parameters
        int skip = request.getParameter("skip") == null ? 0 : Integer.parseInt(request.getParameter("skip"));
        int take = request.getParameter("take") == null ? 20 : Integer.parseInt(request.getParameter("take"));
        if (tipoConsulta.equalsIgnoreCase("encontrarTodos")) {
            try {
                //preparamos la lista y preparamos el contador
                EstructuraDAO estrucutraDAO = new EstructuraDAO();
                List<EstructuraRecord> estructuraRecord = new ArrayList<EstructuraRecord>();
                long contador = 0;
                estructuraRecord=estrucutraDAO.ConsultarEstrutura(skip, take);
                contador=estrucutraDAO.ConsultarEstruturaNumero(skip, take);
                DataSourceResult pg = new DataSourceResult();
                pg.setTotal((int)contador);
                pg.setData(estructuraRecord);
		Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy HH:mm:ss").create();		
                response.setContentType("application/json; charset=ISO-8859-1");
                response.getWriter().print(gson.toJson(pg));
                return;	
            } catch (SQLException ex) {
                Logger.getLogger(Estructura_Controller.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Estructura_Controller.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            } catch (InstantiationException ex) {
                Logger.getLogger(Estructura_Controller.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Estructura_Controller.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
            result.put("success", Boolean.TRUE);
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
