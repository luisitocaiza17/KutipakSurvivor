<%-- 
    Document   : tiempos
    Created on : Aug 20, 2015, 1:27:59 PM
    Author     : luisito
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estructura</title>
        <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">
        <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="../css/Kendo/kendo.common.min.css" />
        <link rel="stylesheet" href="../css/Kendo/kendo.default.min.css">
        <link href="../css/bootstrap/styles.css" rel="stylesheet">
        <script src="../js/jquery.min.js"></script>
        <script src="../js/Kendo/kendo.all.min.js"></script>
        <script src="../js/Kendo/kendo.web.min.js"></script>
        <script src="../js/Kendo/jszip.min.js"></script>
        <script>
            
            var tipoConsulta="";
            var dataP;
            $(document).ready(function () {              
               cargarTabla();
            });
                  
            function cargarTabla(){
                <%--Creamos la tabla tipo kendo--%>
                if ($('#grid').data().kendoGrid){
			$('#grid').data().kendoGrid.destroy();
			$('#grid').empty();
		}
                $("#grid").kendoGrid({
			toolbar: ["excel"],
                excel: {
                    fileName: "Estructuras.xlsx",
                    filterable: true,
                    allPages: true
                },
                            dataSource: {
                    type: "json",
                    serverPaging: true,
                    serverSorting: true,
                    pageSize: 20,
                    transport:{
                            read: {
                                    url: "../Estructura_Controller",
                                    data: {
                                             "tipoConsulta" : "encontrarTodos"
                                        }
                                }

                    },
                    schema: {
                            data: "Data",
                            total: "Total",
                    }
                },
                columns: [
                        { field: "ESTRUCTURAID", title: "Id Cotización", width: "60px"},
                        { field: "IDIOMAID", title: "Id Agricola.", width: "60px"},
                        { field: "NOMBREESTRUCTURA", title: "Periodo" , width: "160px"},
                        { field: "FORMULA", title: "Fecha Elavoración",format: "{0:yyyy/MM/dd}", width: "100px",headerAttributes: { style: "white-space: normal"}, hidden: true, exportar: true},
                        { field: "FORMULASALIDA", title: "Identificación Cliente", width: "90px",headerAttributes: { style: "white-space: normal"}}],
                    height: 500,
                    selectable: true,
                    resizable: true,
                    pageable: {
                        info: true,
                        numeric: false,
                        previousNext: false
                    },
                    scrollable: {
                        virtual: true
                    },
                });	

                    var exportFlag=false;
                    $("#grid").data("kendoGrid").bind("excelExport", function (e) {
                            var columns = e.sender.columns;
                            if (!exportFlag) {
                        jQuery.each(columns, function (index) {
                            if (this.exportar) {
                                    e.sender.showColumn(this.field);
                            }
                        });

                        //e.sender.showColumn("AgenteId");
                        e.preventDefault();
                        exportFlag = true;
                        setTimeout(function () {
                            e.sender.saveAsExcel();
                        }, 1000);
                    } else {
                            jQuery.each(columns, function (index) {
                            if (this.exportar) {
                                    e.sender.hideColumn(this.field);
                            }
                        });
                        exportFlag = false;
                    }
                    });
                
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
                
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a onClick="abreCambio();" >Cambio de Contraseña</a>
                    </li>
                    <li>
                        <a onClick="cerrarSesion();" href="../index.html">Cerrar Seción</a>
                    </li>
                    
                </ul>
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
            <div class="col-lg-12">
                <h2 class="page-header">Administración de KUTIPAK UTC
                    <small>'Mejorando cada día'</small>
                </h2>
                <ol class="breadcrumb">
                    <li><a href="administradorPrincipal.jsp">Inicio</a>
                    </li>
                    <li class="active">
                        <a href="administradorPrincipal.jsp">Estructura</a>
                    </li>
                    
                </ol>
            </div>
        </div>
        <!-- /.row -->
        <!-- Content Row -->
        <div class="row">
            
            <!-- Sidebar Column -->
            <div class="col-md-3">
                <div class="list-group">
                    <a href="administradorPrincipal.jsp" class="list-group-item ">Tipos Palabras</a>
                    <a href="palabras.jsp" class="list-group-item ">Palabras</a> 
                    <a href="estructura.jsp" class="list-group-item active">Estructura</a>
                </div>
            </div>
            <!-- Content Column -->
            <div class="col-md-9">
                <div class="row" id="formIncial">
                    <div class="col-md-2"></div>
                    <div class="col-md-8">
                        <h2>Estructura</h2>
                        <p>La estructura permite configurar el orden de la compasicion gramatical.</p>
                    </div>
                    <div class="col-md-2"></div>
                </div>
            </div>
            <br>            
            <div align="right">
                <button id="nuevo" type="button" class="btn btn-default" onclick="redireccionaInsertar();">Nuevo</button>
            </div>
            <br> 
            <!-- Table -->
            <div class="row">
                    <div class="col-lg-12">
                            <div class="table-responsive">
                                    <div id="grid"></div>
                            </div>
                    </div>
            </div>
            <div>    
                <div id="TablaTodos" class="row">
                    <div class="col-md-4"></div>
                    <div class="col-md-6">
                        <div id="grid"></div>
                    </div>
                    <div class="col-md-2"></div>
                    
                </div>
                <div id="Contenidos" class="row">
                    <div align="left">
                            <button id="regresar" type="button" class="btn btn-default" onclick="javascript:location.reload();">Regresar</button>
                        </div>
                    <div align="right">
                            <button id="eliminar" type="button" class="btn btn-danger" onclick="eliminarRegistro();">Eliminar</button>
                    </div>
                    <div class="col-md-2"></div>
                    <div class="col-md-7">
                         <br>
                        <form class="form-horizontal">
                                <div class="form-group">
                                  <label for="palabra" class="col-sm-6 control-label">Palabra</label>
                                  <div class="col-sm-6">
                                    <input type="text" class="form-control" id="palabra" placeholder="auto,casa" style="text-transform:uppercase;" onkeyup="javascript:this.value=this.value.toUpperCase();" >
                                  </div>
                                </div>
                                <div class="form-group">
                                  <label for="idioma" class="col-sm-6 control-label">Idioma</label>
                                  <div class="col-sm-6">
                                    <select class="form-control required" id="idioma"onchange="carga(this)"  > 
                                        
                                    </select>
                                  </div>
                                </div>
                            
                                <div class="form-group">
                                  <label for="tipo" class="col-sm-6 control-label">Tipo</label>
                                  <div class="col-sm-6">
                                    <select class="form-control required" id="tipo"> 
                                       
                                    </select>
                                  </div>
                                </div>
                            
                                <div class="form-group">
                                  <label for="eltiempo" class="col-sm-6 control-label">Tiempo</label>
                                  <div class="col-sm-6">
                                    <select class="form-control required" id="eltiempo"> 
                                        
                                    </select>
                                  </div>
                                </div>
                                
                                <div class="form-group">
                                  <label for="traducion" class="col-sm-6 control-label">Traducción</label>
                                  <div class="col-sm-6">
                                     
                                        <input id="traducion" class="form-control required" style="width: 100%;" style="text-transform:uppercase;" onkeyup="javascript:this.value=this.value.toUpperCase();" />
                                    </div>
                                  </div>
                                </div>
                                
                                 <br>
                                <div class="form-group">
                                  <div class="col-sm-offset-6 col-sm-10">
                                    <input type="hidden" class="form-control" id="idPalabra">
                                    <button type="button" id="guardar" class="btn btn-primary">Guardar</button>
                                  </div>
                                </div>
                                 
                              </form>
                         
                    </div>
                    <div class="col-md-3"></div>
                    
                </div>   


        </div>
        <!-- /.row -->

        <hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Your Website 2015</p>
                </div>
            </div>
        </footer>

    </div>
    <!-- /.container -->
    </body>
      <script>
          function carga(a) {
            var idIdioma = (a.value || a.options[a.selectedIndex].value);  //crossbrowser solution =)
            
            if(idIdioma==="1"){
                var idioma="ESPAÑOL";
                
            }else{
                var idioma="KITCHWA";
                
            }
            var Significados="Significados";
            cargaTraduccion(idioma,Significados);
            
        }
        
        
        
        
       </script>
               
</html>
