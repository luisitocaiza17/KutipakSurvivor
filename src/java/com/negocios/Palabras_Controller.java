/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negocios;

import com.datos.DAO.IdiomasDAO;
import com.datos.DAO.PalabrasDAO;
import com.datos.DAO.PantallaPalabrasDAO;
import com.datos.DAO.TiemposDAO;
import com.datos.DAO.TiposPalabrasDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import persistencia.tables.records.IdiomasRecord;
import persistencia.tables.records.PalabrasRecord;
import persistencia.tables.records.PantallapalabrasRecord;
import persistencia.tables.records.TiemposRecord;
import persistencia.tables.records.TipospalabrasRecord;
/**
 *
 * @author luisito
 */
@WebServlet(name = "Palabras_Controller", urlPatterns = {"/Palabras_Controller"})
public class Palabras_Controller extends HttpServlet {
   private Gson _gson;
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
            out.println("<title>Servlet Palabras_Controller</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Palabras_Controller at " + request.getContextPath() + "</h1>");
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
                  System.out.println("sie entra a palabras");
                JSONObject result = new JSONObject();
		try {
                    
                        JSONObject planJSONObject = new JSONObject();
                        String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String operacion = request.getParameter("operacion") == null ? "" : request.getParameter("operacion");
			String id = request.getParameter("id") == null ? "" : request.getParameter("id");
			
                        if(tipoConsulta.equals("busquedaEspecifica")){
                            String palabra = request.getParameter("palabra") == null ? "" : request.getParameter("palabra");
                            String idioma = request.getParameter("idioma") == null ? "" : request.getParameter("idioma");
                            String traduccion = request.getParameter("traduccion") == null ? "" : request.getParameter("traduccion");
			
                             PantallaPalabrasDAO TodasPalabras = new PantallaPalabrasDAO();
                             List<PantallapalabrasRecord> results = TodasPalabras.ConsultarPalabrasEspecifica2(palabra,idioma);
                             JSONArray JSONArrayTipos = new JSONArray();
                             JSONArray JSONTraducciones = new JSONArray(); 
                             for(PantallapalabrasRecord rs : results){
                                 planJSONObject.put("id", rs.getPalabraid());
                                 planJSONObject.put("idioma", rs.getIdioma());
                                 planJSONObject.put("palabra", rs.getPalabras());
                                 planJSONObject.put("traducion", rs.getSignificado());
                                 planJSONObject.put("tipo", rs.getTipo());
                                 planJSONObject.put("tiempo", rs.getTiempo());
                                 planJSONObject.put("tipoP", rs.getNombretipo());
                                 planJSONObject.put("tiempoP", rs.getNombretiempo());
                                 JSONArrayTipos.add(planJSONObject);
                                 JSONTraducciones.add(rs.getSignificado());
                             }
                             result.put("listadoPalabras", JSONArrayTipos);
                              result.put("success", Boolean.TRUE);
                        }
                        
                        //carga inicial de combos
                        if(tipoConsulta.equals("cargaCombosConsulta")){
                            JSONObject planJSONObjectTiempos = new JSONObject();
                             IdiomasDAO todosIdiomas = new IdiomasDAO();
                             List<IdiomasRecord> resultsIdiomas = todosIdiomas.ConsultarIdiomas();
                             JSONArray JSONArrayIdioma = new JSONArray();  
                             for(IdiomasRecord rs : resultsIdiomas){
                                 planJSONObjectTiempos.put("id", rs.getIdiomaid());
                                 planJSONObjectTiempos.put("idioma", rs.getNombre());
                                 JSONArrayIdioma.add(planJSONObjectTiempos);
                             }
                             result.put("listadoIdioma", JSONArrayIdioma);
                        }
                        
                        if(tipoConsulta.equals("TodosTipos")){
                             PantallaPalabrasDAO TodasPalabras = new PantallaPalabrasDAO();
                             List<PantallapalabrasRecord> results = TodasPalabras.ConsultarPalabras();
                             JSONArray JSONArrayTipos = new JSONArray();
                             JSONArray JSONTraducciones = new JSONArray(); 
                             for(PantallapalabrasRecord rs : results){
                                 planJSONObject.put("id", rs.getPalabraid());
                                 planJSONObject.put("idioma", rs.getIdioma());
                                 planJSONObject.put("palabra", rs.getPalabras());
                                 planJSONObject.put("traducion", rs.getSignificado());
                                 planJSONObject.put("tipo", rs.getTipo());
                                 planJSONObject.put("tiempo", rs.getTiempo());
                                 planJSONObject.put("tipoP", rs.getNombretipo());
                                 planJSONObject.put("tiempoP", rs.getNombretiempo());
                                 JSONArrayTipos.add(planJSONObject);
                                 JSONTraducciones.add(rs.getSignificado());
                             }
                             result.put("listadoPalabras", JSONArrayTipos);
                             result.put("listadoTraducciones",JSONTraducciones);
                             
                             JSONObject planJSONObjectTiempos = new JSONObject();
                             IdiomasDAO todosIdiomas = new IdiomasDAO();
                             List<IdiomasRecord> resultsIdiomas = todosIdiomas.ConsultarIdiomas();
                             JSONArray JSONArrayIdioma = new JSONArray();  
                             for(IdiomasRecord rs : resultsIdiomas){
                                 planJSONObjectTiempos.put("id", rs.getIdiomaid());
                                 planJSONObjectTiempos.put("idioma", rs.getNombre());
                                 
                                 JSONArrayIdioma.add(planJSONObjectTiempos);
                             }
                             result.put("listadoIdioma", JSONArrayIdioma);
                             
                             JSONObject planJSONObjectTiposP = new JSONObject();
                             TiposPalabrasDAO todosTipos = new TiposPalabrasDAO();
                             List<TipospalabrasRecord> resultsPalabras = todosTipos.ConsultarTiposPalabras();
                             JSONArray JSONArrayTiposP = new JSONArray();  
                             for(TipospalabrasRecord rs : resultsPalabras){
                                 planJSONObjectTiposP.put("id", rs.getTipoid());
                                 planJSONObjectTiposP.put("tipo", rs.getNombretipo());
                                 
                                 JSONArrayTiposP.add(planJSONObjectTiposP);
                             }
                             result.put("listadoTipos", JSONArrayTiposP);
                             
                             
                             JSONObject planJSONObjectTiempo = new JSONObject();
                             TiemposDAO todosTiempos = new TiemposDAO();
                             List<TiemposRecord> resultsTiempos = todosTiempos.ConsultarTiempos();
                             JSONArray JSONArrayTiempos= new JSONArray();  
                             for(TiemposRecord rs : resultsTiempos){
                                 planJSONObjectTiempo.put("id", rs.getTiemposid());
                                 planJSONObjectTiempo.put("tiempo",rs.getNombretiempo());
                                 
                                 JSONArrayTiempos.add(planJSONObjectTiempo);
                             }
                             result.put("listadoTiempos", JSONArrayTiempos);
                             
                             result.put("success", Boolean.TRUE);
                         }
                        /*LLAMADA PARA LA BUSQUEDA ESPECIFICA DE LAS PALABRAS QUE SE DESEAN BUSCAR*/
                        if(tipoConsulta.equals("Significados")){
                            String idioma = request.getParameter("idioma") == null ? "" : request.getParameter("idioma");
			    PantallaPalabrasDAO TodasPalabras = new PantallaPalabrasDAO();
                             List<PantallapalabrasRecord> results = TodasPalabras.ConsultarPalabrasEspecificaIdioma(idioma);
                             JSONArray JSONArrayTipos = new JSONArray();
                             JSONArray JSONTraducciones = new JSONArray(); 
                             //BUSCAMOS Y LAS ENVIAMOS A PANTALLA
                             for(PantallapalabrasRecord rs : results){
                                 planJSONObject.put("id", rs.getPalabraid());
                                 planJSONObject.put("idioma", rs.getIdioma());
                                 planJSONObject.put("palabra", rs.getPalabras());
                                 planJSONObject.put("traducion", rs.getSignificado());
                                 planJSONObject.put("tipo", rs.getTipo());
                                 planJSONObject.put("tiempo", rs.getTiempo());
                                 planJSONObject.put("tipoP", rs.getNombretipo());
                                 planJSONObject.put("tiempoP", rs.getNombretiempo());
                                 JSONTraducciones.add(rs.getSignificado());
                             }
                             
                             result.put("listadoTraducciones",JSONTraducciones);
                        }
                       
                        /*procesos con los datos*/
                        if(operacion.toUpperCase().equals("INSERTAR")&& id.equals("")){
                            
                            String palabra = request.getParameter("palabra") == null ? "" : request.getParameter("palabra");
			    String idioma = request.getParameter("idioma") == null ? "" : request.getParameter("idioma");
			    String tipo = request.getParameter("tipo") == null ? "" : request.getParameter("tipo");
			    String tiempo = request.getParameter("tiempo") == null ? "" : request.getParameter("tiempo");
			    String traducion = request.getParameter("traducion") == null ? "" : request.getParameter("traducion");
			    /*transforma los ids */
                            IdiomasDAO idiomabuqueda = new IdiomasDAO();
                            IdiomasRecord idiomaId = idiomabuqueda.ConsultarIdiomasEspecificosId(idioma);
                            TiposPalabrasDAO tiposbusqueda = new TiposPalabrasDAO();  
                            TipospalabrasRecord tipoId= tiposbusqueda.ConsultarPalabraEspecificosId(tipo);
                            TiemposDAO tiemposbusqueda = new TiemposDAO();
                            TiemposRecord tiemposId= tiemposbusqueda.ConsultarTiempoEspecificosId(tiempo);
                            
                            PalabrasDAO procesosPalabras = new PalabrasDAO();
                            PalabrasRecord palabraIngreso = new PalabrasRecord();
                            
                            palabraIngreso.setNombrepalabra(palabra);
                            palabraIngreso.setSignificado(traducion);
                            palabraIngreso.setIdiomaid(idiomaId.getIdiomaid());
                            palabraIngreso.setTiemposid(tiemposId.getTiemposid());
                            palabraIngreso.setTipoid(tipoId.getTipoid());
                            
                            List<PalabrasRecord> resultsPalabras = procesosPalabras.ConsultarPalabrasExiste(palabraIngreso);
                            int contador=0;
                            for(PalabrasRecord rs : resultsPalabras){
                                contador++;
                                break;
                            }
                            
                            
                            if(contador==0){
                               
                               procesosPalabras.GrabarPalabras(palabraIngreso);
                               result.put("success", Boolean.TRUE);
                               result.put("mensaje", "GUARDADO CORRECTO");
                            }else{
                               result.put("success", Boolean.FALSE);
                               result.put("mensaje", " PALABRA DUPLICADA, YA EXISTE!!"); 
                            }
                            
                        }
                        if(operacion.toUpperCase().equals("ACTUALIZAR") && !id.equals("")){
                            String palabra = request.getParameter("palabra") == null ? "" : request.getParameter("palabra");
			    String idioma = request.getParameter("idioma") == null ? "" : request.getParameter("idioma");
			    String tipo = request.getParameter("tipo") == null ? "" : request.getParameter("tipo");
			    String tiempo = request.getParameter("tiempo") == null ? "" : request.getParameter("tiempo");
			    String traducion = request.getParameter("traducion") == null ? "" : request.getParameter("traducion");
			    
                            /*transforma los ids */
                            IdiomasDAO idiomabuqueda = new IdiomasDAO();
                            IdiomasRecord idiomaId = idiomabuqueda.ConsultarIdiomasEspecificosId(idioma);
                            TiposPalabrasDAO tiposbusqueda = new TiposPalabrasDAO();  
                            TipospalabrasRecord tipoId= tiposbusqueda.ConsultarPalabraEspecificosId(tipo);
                            TiemposDAO tiemposbusqueda = new TiemposDAO();
                            TiemposRecord tiemposId= tiemposbusqueda.ConsultarTiempoEspecificosId(tiempo);
                            
                            PalabrasDAO procesosPalabras = new PalabrasDAO();
                            PalabrasRecord palabraIngreso = new PalabrasRecord();
                            
                            palabraIngreso.setNombrepalabra(palabra);
                            palabraIngreso.setSignificado(traducion);
                            palabraIngreso.setIdiomaid(idiomaId.getIdiomaid());
                            palabraIngreso.setTiemposid(tiemposId.getTiemposid());
                            palabraIngreso.setTipoid(tipoId.getTipoid());
                            try{
                                palabraIngreso.setPalabraid(Integer.parseInt(id));
                                 List<PalabrasRecord> resultsPalabras = procesosPalabras.ConsultarPalabrasExiste(palabraIngreso);
                            int contador=0;
                            for(PalabrasRecord rs : resultsPalabras){
                                contador++;
                                break;
                            }
                            if(contador==0){
                               procesosPalabras.ActualizarPalabras(palabraIngreso);
                               result.put("success", Boolean.TRUE);
                               result.put("mensaje", "ACTUALIZADO CORRECTO");
                            }else{
                               result.put("success", Boolean.FALSE);
                               result.put("mensaje", " PALABRA DUPLICADA, YA EXISTE!!"); 
                            }
                            }catch(Exception e){
                                e.printStackTrace();
                                result.put("success", Boolean.FALSE);
                                result.put("mensaje",e);
                            }
                        }
                        
                        if(operacion.toUpperCase().equals("ELIMINAR")){
                            PalabrasRecord palabra= new PalabrasRecord();
                            try{
                                 palabra.setPalabraid(Integer.parseInt(id));
                                PalabrasDAO PalabrasProcesos = new PalabrasDAO();
                                PalabrasProcesos.EliminarPalabras(palabra);
                                result.put("success", Boolean.TRUE);
                                result.put("mensaje", "ELIMINACION DE REGISTRO CORRECTA");
                            }catch(Exception e){
                                e.printStackTrace();
                                result.put("success", Boolean.FALSE);
                                result.put("mensaje",e);
                            }
                        }
                         response.setContentType("application/json; charset=ISO-8859-1"); 
                         result.write(response.getWriter());
                    } catch (Exception e) {
                        e.printStackTrace();
			result.put("success", Boolean.FALSE);
			result.put("mensaje",e);
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
