/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negocios;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author luisito
 */
@WebServlet(name = "CargaExcel_Controller", urlPatterns = {"/CargaExcel_Controller"})
public class CargaExcel_Controller extends HttpServlet {
    private boolean isMultipart;
    private String filePath="C:\\kutipak\\";
    private int maxFileSize = 50 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file ;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public void init( ){
      // Get the file location where it would be stored.
      //filePath = getServletContext().getInitParameter("file-upload"); C:\kutipak
       filePath = "C:\\kutipak\\";
   }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
        
         isMultipart = ServletFileUpload.isMultipartContent(request);
      response.setContentType("text/html");
      java.io.PrintWriter out = response.getWriter( );
      if( !isMultipart ){
         out.println("<html>");
         out.println("<head>");
         out.println("<title>Servlet upload</title>");  
         out.println("</head>");
         out.println("<body>");
         out.println("<p>No file uploaded</p>"); 
         out.println("</body>");
         out.println("</html>");
         return;
      }
      DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
      // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File(filePath));

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );

      try{ 
          
            FileItemIterator iter = upload.getItemIterator(request);
            FileItemStream item;
            InputStream stream;
             while (iter.hasNext()) {
                 
                 item = iter.next();
                        stream = item.openStream();
                        Iterator<Row> filas;    
                        if (item.getName().endsWith(".xls")) {
                            HSSFWorkbook workbook = new HSSFWorkbook(stream);
                            HSSFSheet sheet = workbook.getSheetAt(0);
                            filas = sheet.iterator();
                            
                        } else {
                            if (item.getName().endsWith(".xlsx")) {
                                XSSFWorkbook workbook = new XSSFWorkbook(stream);
                                XSSFSheet sheet = workbook.getSheetAt(0);
                                filas = sheet.iterator();
                              
                            } else {
                                out.println("<html>");
                                out.println("<head>");
                                out.println("<title>Servlet Upload</title>");
                                out.println("</head>");
                                out.println("<body>");
                                out.println("<p>La extensión del archivo no es correcta</p>");
                                out.println("</body>");
                                out.println("</html>");
                                return;
                            }
                        }
                       while (filas.hasNext()) {
                           Row row = filas.next();
                           String Marca = "" + row.getCell(0);
                           System.out.println("Marca:"+Marca);
                       }
                          
                        
             }
          
      
   }catch(Exception ex) {
       System.out.println(ex);
   }
        
        
        
   }
    
}
