/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negocios;

import com.datos.DAO.ContadorDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.negocios.model.contador;
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
import persistencia.tables.records.ContadorRecord;

/**
 *
 * @author lcaiza
 */
@WebServlet(name = "analisiVistas_Controller", urlPatterns = {"/analisiVistas_Controller"})
public class analisiVistas_Controller extends HttpServlet {

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
            out.println("<title>Servlet analisiVistas_Controller</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet analisiVistas_Controller at " + request.getContextPath() + "</h1>");
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
       
        if(tipoConsulta.equalsIgnoreCase("encontrarTodos")){
            ContadorDAO contadorDAO = new ContadorDAO();
            ContadorRecord contadorRecord = new ContadorRecord();
            int total=0;
            try {
                total=contadorDAO.consultar();
            } catch (Exception ex) {
                ex.printStackTrace();
            } 
            
            List<contador> contadorList = new ArrayList <contador>();
            contador elContador = new contador();
            elContador.setValue(total);
            contadorList.add(elContador);
            
            contador elContador2 = new contador();
            elContador.setValue(total);
            
            contadorList.add(elContador2);
            
            DataSourceResult pg = new DataSourceResult();
            pg.setData(contadorList);
            Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy HH:mm:ss").create();		
            response.setContentType("application/json; charset=ISO-8859-1");
            response.getWriter().print(gson.toJson(pg));
            return;
            
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
