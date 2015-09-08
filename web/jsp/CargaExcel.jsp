<%-- 
    Document   : cambioContraseña
    Created on : Aug 19, 2015, 2:40:12 PM
    Author     : luisito
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carga Excel</title>
        <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">
        <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="../css/Kendo/kendo.common.min.css" />
        <link rel="stylesheet" href="../css/Kendo/kendo.default.min.css">
        
        <link href="../css/bootstrap/styles.css" rel="stylesheet">
        <script src="../js/jquery.min.js"></script>
        <script src="../js/Kendo/kendo.all.min.js"></script>
        
        <script>
            function comprobar(){
		if(document.formulario.carga.value===""){
			alert("Debe seleccionar un archivo excel");
                        return false;
		}
	}
            
        </script>
    </head>
    <body>
          
    <%// Permitimos el acceso si la session existe		
         if(session.getAttribute("usuario") == null){
             response.sendRedirect("../index.html");
         }
     %>
             <!-- Navigation -->
     <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
         <div class="container">
             <!-- Brand and toggle get grouped for better mobile display -->
             <div class="navbar-header">
                 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                     <span class="sr-only">Toggle navigation</span>
                     <span class="icon-bar"></span>
                     <span class="icon-bar"></span>
                     <span class="icon-bar"></span>
                 </button>
                 <a class="navbar-brand" href="index.html">KUTIPAK UTC</a>
             </div>
             <!-- Collect the nav links, forms, and other content for toggling -->
             <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                 
             </div>
             <!-- /.navbar-collapse -->
         </div>
         <!-- /.container -->
     </nav>
             <br>
             <br>
             <!-- Page Content -->
     <div class="container">

         <!-- Page Heading/Breadcrumbs -->
         
          <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                         <br />
                          <br />
                        <div class="bg-info">
                            <br />
                            <h4 align="center" >CARGA MASIVA DESDE EXCEL</h4>
                            <br />
                        </div>
                         <br />
                        <p> Usted puede cargar las palabras de forma rapida desde un archivo de excel, puede contactarse con
                            el administrador para obtener el formato, carguelo y verifiquelo en la pantalla de palabras</p>
                
                        
                         <br />
                        <form class="form-horizontal" name="formulario" method="POST" ACTION="../CargaExcel_Controller" onsubmit="return comprobar();" enctype="multipart/form-data" >
                            <div class="form-group">
                              <label for="carga" class="col-sm-2 control-label">Carga de Archivo: </label>
                              <br />
                              <div class="col-sm-4">
                                  <br />
                                <input type="file"  name="carga" id="carga">
                                <br />
                                <p class="help-block">Elija el archivo de Excel</p>
                              </div>
                            </div>
                            
                            
                            <div class="form-group">
                              <div class="col-sm-offset-2 col-sm-4">
                                <button type="submit" class="btn btn-primary">.CARGAR.</button>
                                <button onclick="window.close();" href="" class="btn btn-info">REGRESAR</button>
                              </div>
                            </div>
                          </form>
                    </div>
                    <div class="col-md-3"></div>

          </div>
     </div>
     
     </body>
</html>
