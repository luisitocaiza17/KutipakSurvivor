/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negocios;


import com.datos.DAO.EstructuraDAO;
import com.datos.DAO.TiposPalabrasDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.negocios.model.EstructuraPalabras;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import persistencia.tables.records.EstructuraRecord;
import persistencia.tables.records.TipospalabrasRecord;



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
        
        //cargamos cada uno de los combos y los enviamos como objetos de JSON
        if (tipoConsulta.equalsIgnoreCase("crear")) {
            try {
                String texto = request.getParameter("texto") == null ? "": request.getParameter("texto");
                String idioma = request.getParameter("idioma") == null ? "": request.getParameter("idioma");
                String entrante = request.getParameter("entrante") == null ? "": request.getParameter("entrante");
                String saliente = request.getParameter("saliente") == null ? "": request.getParameter("saliente");
                
                //Guardamos el Objeto
                EstructuraRecord objeto = new EstructuraRecord();
                objeto.setIdiomaid(Integer.parseInt(idioma));
                objeto.setNombreestructura(texto);
                objeto.setFormula(entrante);
                objeto.setFormulasalida(saliente);
                EstructuraDAO estructuraDAO = new EstructuraDAO();
                estructuraDAO.GrabarEstructura(objeto);
                 result.put("success", Boolean.TRUE);
                response.setContentType("application/json; charset=ISO-8859-1");
                result.write(response.getWriter());
            } catch (Exception ex) {
                ex.printStackTrace();
                result.put("success", Boolean.FALSE);
                response.setContentType("application/json; charset=ISO-8859-1");
                result.write(response.getWriter());
            } 
        }
        
        //cargamos cada uno de los combos y los enviamos como objetos de JSON
        if (tipoConsulta.equalsIgnoreCase("editar")) {
            try {
                String texto = request.getParameter("texto") == null ? "": request.getParameter("texto");
                String idioma = request.getParameter("idioma") == null ? "": request.getParameter("idioma");
                String entrante = request.getParameter("entrante") == null ? "": request.getParameter("entrante");
                String saliente = request.getParameter("saliente") == null ? "": request.getParameter("saliente");
                String id = request.getParameter("id") == null ? "": request.getParameter("id");
                
                //Guardamos el Objeto
                EstructuraRecord objeto = new EstructuraRecord();
                objeto.setIdiomaid(Integer.parseInt(idioma));
                objeto.setNombreestructura(texto);
                objeto.setFormula(entrante);
                objeto.setFormulasalida(saliente);
                objeto.setEstructuraid(Integer.parseInt(id));
                EstructuraDAO estructuraDAO = new EstructuraDAO();
                estructuraDAO.ActualizarEstructura(objeto);
                 result.put("success", Boolean.TRUE);
                response.setContentType("application/json; charset=ISO-8859-1");
                result.write(response.getWriter());
            } catch (Exception ex) {
                ex.printStackTrace();
                result.put("success", Boolean.FALSE);
                response.setContentType("application/json; charset=ISO-8859-1");
                result.write(response.getWriter());
            } 
        }
        
        //Eliminamos el registro
        if (tipoConsulta.equalsIgnoreCase("eliminar")) {
            try {
                String id = request.getParameter("id") == null ? "": request.getParameter("id");
                //Guardamos el Objeto
                EstructuraRecord objeto = new EstructuraRecord();
                objeto.setEstructuraid(Integer.parseInt(id));
                EstructuraDAO estructuraDAO = new EstructuraDAO();
                estructuraDAO.EliminarEstructura(objeto);
                 result.put("success", Boolean.TRUE);
                response.setContentType("application/json; charset=ISO-8859-1");
                result.write(response.getWriter());
            } catch (Exception ex) {
                ex.printStackTrace();
                result.put("success", Boolean.FALSE);
                response.setContentType("application/json; charset=ISO-8859-1");
                result.write(response.getWriter());
            } 
        }
        
        if (tipoConsulta.equalsIgnoreCase("CargaIndividual")) {
            String id = request.getParameter("id") == null ? "": request.getParameter("id");
            EstructuraRecord objeto = new EstructuraRecord();
            objeto.setEstructuraid(Integer.parseInt(id));
            objeto.setEstructuraid(Integer.parseInt(id));
            EstructuraDAO estructuraDAO = new EstructuraDAO();
            try{
                objeto=estructuraDAO.ConsultarEstructuraEspecificaId(objeto);
                
                result.put("texto", objeto.getNombreestructura());
                result.put("idioma", objeto.getIdiomaid());
                
                //Separamos las cadenas Entrada
                String entrada=objeto.getFormula();
                char[] cadema =entrada.toCharArray();
                
                String E1=""+cadema[0];
                String E2=""+cadema[1];
                
                 //Separamos las cadenas SALIDAS
                String salida=objeto.getFormulasalida();
                char[] cademaSalida =salida.toCharArray();
                
                String S1=""+cademaSalida[0];
                String S2=""+cademaSalida[1];
                
                
                result.put("entrada1", E1);
                result.put("entrada2", E2);
                result.put("salida1", S1);
                result.put("salida2", S2);
                response.setContentType("application/json; charset=ISO-8859-1");
                result.write(response.getWriter());
                
            }
            catch(Exception ex){
                ex.printStackTrace();
                result.put("success", Boolean.FALSE);
                response.setContentType("application/json; charset=ISO-8859-1");
                result.write(response.getWriter());
            }
        }
        
        if (tipoConsulta.equalsIgnoreCase("CargaInicial")) {
            TiposPalabrasDAO tiposPalabrasDAO = new TiposPalabrasDAO();
            List<TipospalabrasRecord> tipospalabrasRecord = new ArrayList<TipospalabrasRecord>();
            //Obejtos de Entrada
            JSONObject palabrasObjetoEntrada1 = new JSONObject();
            JSONArray  palabrasArrayEntrada1 = new JSONArray();
            JSONObject palabrasObjetoEntrada2 = new JSONObject();
            JSONArray  palabrasArrayEntrada2 = new JSONArray();
            
            //Objetos de Salida
            JSONObject palabrasObjetoSalida1 = new JSONObject();
            JSONArray  palabrasArraySalida1 = new JSONArray();
            JSONObject palabrasObjetoSalida2 = new JSONObject();
            JSONArray  palabrasArraySalida2 = new JSONArray();
            
            try{
                //cargamos los Tipos de Palabras
                tipospalabrasRecord=tiposPalabrasDAO.ConsultarTiposPalabras();
                for(TipospalabrasRecord rs:tipospalabrasRecord){
                    palabrasObjetoEntrada1.put("nombre", rs.getNombretipo());
                    palabrasObjetoEntrada1.put("codigo", rs.getCodigoktpak());
                    palabrasArrayEntrada1.add(palabrasObjetoEntrada1);
                    palabrasObjetoEntrada2.put("nombre", rs.getNombretipo());
                    palabrasObjetoEntrada2.put("codigo", rs.getCodigoktpak());
                    palabrasArrayEntrada2.add(palabrasObjetoEntrada2);
                    palabrasObjetoSalida1.put("nombre", rs.getNombretipo());
                    palabrasObjetoSalida1.put("codigo", rs.getCodigoktpak());
                    palabrasArraySalida1.add(palabrasObjetoEntrada1);
                    palabrasObjetoSalida2.put("nombre", rs.getNombretipo());
                    palabrasObjetoSalida2.put("codigo", rs.getCodigoktpak());
                    palabrasArraySalida2.add(palabrasObjetoEntrada2);
                    
                }
                result.put("entrada1", palabrasArrayEntrada1);
                result.put("entrada2", palabrasArrayEntrada2);
                result.put("salida1", palabrasArraySalida1);
                result.put("salida2", palabrasArraySalida2);
                result.put("success", Boolean.TRUE);
                response.setContentType("application/json; charset=ISO-8859-1");
                result.write(response.getWriter());
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }
        
        if (tipoConsulta.equalsIgnoreCase("encontrarTodos")) {
            try {
                //preparamos la lista y preparamos el contador
                EstructuraDAO estrucutraDAO = new EstructuraDAO();
                List<EstructuraRecord> estructuraRecord = new ArrayList<EstructuraRecord>();
                long contador = 0;
                estructuraRecord=estrucutraDAO.ConsultarEstrutura(skip,take);
                
                //cargamos los datos del cotizador y la adaptamos al objeto
                List<EstructuraPalabras>  Listado = new ArrayList<EstructuraPalabras>();
               
                for( EstructuraRecord rs:estructuraRecord){
                    EstructuraPalabras palabras = new EstructuraPalabras();
                    palabras.setId(rs.getEstructuraid());
                    if(rs.getIdiomaid()==1)
                        palabras.setIdioma("ESPAÑOL");
                    else
                        palabras.setIdioma("KICHWA");
                    palabras.setNombreEstructura(rs.getNombreestructura());
                    palabras.setEstructuraEntrante(rs.getFormula());
                    palabras.setEstructuraSaliente(rs.getFormulasalida());
                    Listado.add(palabras);
                }
                
                contador=estrucutraDAO.ConsultarEstruturaNumero(skip, take);
                DataSourceResult pg = new DataSourceResult();
                pg.setTotal((int)contador);
                pg.setData(Listado);
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
