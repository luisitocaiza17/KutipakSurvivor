/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negocios;

import com.datos.DAO.ConectarBD;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.json.JSONObject;

/**
 *
 * @author lcaiza
 */
@WebServlet(name = "Reporte_controller", urlPatterns = {"/Reporte_controller"})
public class Reporte_controller extends HttpServlet {

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
            out.println("<title>Servlet Reporte_controller</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Reporte_controller at " + request.getContextPath() + "</h1>");
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
        String palabra = request.getParameter("palabra") == null ? "" : request.getParameter("palabra");
        String idioma = request.getParameter("idioma") == null ? "" : request.getParameter("idioma");
        String proceso = request.getParameter("proceso") == null ? "" : request.getParameter("proceso");
        ConectarBD con = new ConectarBD();
        Connection conexion= null;
        try{
            
                String rutaPlantilla = Reporte_controller.class.getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		rutaPlantilla=rutaPlantilla.replace("Reporte_controller.class", "");
		String rutaRelativaReporte=".."+File.separator+"reportes"+File.separator+"Segundo.jasper";
		rutaPlantilla=rutaPlantilla+rutaRelativaReporte;
                System.out.println("Ruta: "+rutaPlantilla);
                conexion= con.realiza_conexion();
                byte[] data  = JasperRunManager.runReportToPdf(rutaPlantilla,null,conexion);
                response.setHeader("Content-Transfer-Encoding", "binary"); 
		response.setContentLength(data.length);
		response.setHeader("Content-Encoding", "none");
		response.setContentType("application/force-download");
		response.setHeader("Content-Disposition","attachment; filename=" + "KutipakDiccionario.pdf");//fileName);
		//result.write(response.getWriter());
		ServletOutputStream  o = response.getOutputStream();
		o.write(data); 
		o.flush(); 
		o.close(); 
		
                                    
        }catch(Exception e){
            e.printStackTrace();
        }               
            
       return;   
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
