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
        <title>Visitas</title>
        <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">
        <link href="../css/bootstrap/bootstrap.css" rel="stylesheet">
        <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="../css/Kendo/kendo.common.min.css" />
        <link rel="stylesheet" href="../css/Kendo/kendo.default.min.css">
        <link href="../css/bootstrap/styles.css" rel="stylesheet">
        
        <script src="../js/jquery.min.js"></script>
        <script src="../js/bootstrap/bootstrap.js"></script>
        <script src="../js/bootstrap/bootstrap.min.js"></script>
        
        <script src="../js/Kendo/kendo.all.min.js"></script>
        <script src="../js/Kendo/kendo.web.min.js"></script>
        <script src="../js/Kendo/jszip.min.js"></script>
        <script src="../js/Kendo/bootstrap-datepicker.js"></script>
        
        <!-- Para el Datepicker-->
        <link href="../css/Kendo/datepicker.css" rel="stylesheet">
        
        
        <script>
            
            var tipoConsulta="";
            var dataP;
            
            var entradaList1 = "";
            var salidaList1 = "";
            var entradaList2 = "";
            var salidaList2 = "";
            
            $(document).ready(function () {  
                
                cargarGrafico('','');
                                
             });
            
            //Metodo validacion de fechas buscador
            $(function() {
                    var startDate = new Date();
                    var endDate = new Date();

                    $('#dp1')
                                    .datepicker()
                                    .on(
                                                    'changeDate',
                                                    function(ev) {
                                                            if (ev.date.valueOf() > endDate.valueOf()) {
                                                                    alert("La Fecha Inicial no puede ser mayor que la Fecha Actual");
                                                                    return false;
                                                            } else {
                                                                    startDate = new Date(ev.date);
                                                                    $('#startDate').text($('#dp1').data('date'));
                                                            }
                                                            $('#dp1').datepicker('hide');
                                                    });
                    $('#dp2')
                                    .datepicker()
                                    .on(
                                                    'changeDate',
                                                    function(ev) {
                                                            if (ev.date.valueOf() < startDate.valueOf()) {
                                                                    alert('La Fecha Final no puede ser menor que la Fecha Inicial');
                                                                    return false;
                                                            } else {

                                                                    endDate = new Date(ev.date);
                                                                    $('#endDate').text($('#dp2').data('date'));
                                                            }
                                                            $('#dp2').datepicker('hide');
                                                    });
            });    
    
            function limpiar(){
                $("#idioma").data("kendoMultiSelect").value(null);
                $("#nombre").val("");
                $("#entrada1").data("kendoMultiSelect").value(null);
                $("#entrada2").data("kendoMultiSelect").value(null);
                $("#salida1").data("kendoMultiSelect").value(null);
                $("#salida2").data("kendoMultiSelect").value(null);
                
            }    
                
            function abreCambio(){
                 var agente = window.open("cambioContrasenia.jsp","ventana1","directories=no,width=500,height=500,resizable=no,scrollbars=yes,top=0,left=260,status=1");
                 agente.opener = self;
             }
             
            function cerrarSesion(){
                $.ajax({
                    url : '../Salir',      				
                    type : 'POST',
                    datatype : 'json',
                    success : function(data) {   
                        if (data.success === true){
                                window.location= "../index.jsp";
                        }
                    }
                });	                
             }
                        
            function buscar(){
                var fechaInicio = $("#dp1").val();
                var fechaFin = $("#dp2").val();                
                cargarGrafico(fechaInicio,fechaFin);
            }
            
            function cargarGrafico(fechaInicio,fechaFin ){
                $("#chart").kendoChart({
                    dataSource: {
                        type: "json",
                        serverPaging: true,
                        serverSorting: true,
                        serverFiltering: true,
                        pageSize: 20,
                        transport:{
                                read: {
                                        url: "../analisiVistas_Controller",
                                        data: {
                                                 "tipoConsulta" : "encontrarTodos",
                                                 "dp1":fechaInicio,
                                                 "dp2":fechaFin
                                            }
                                    }

                        },
                        schema: {
                                data: "Data"
                        }
                    },
                    valueAxis: {
                        line: {
                            visible: true
                        }
                    },
                    categoryAxis: {
                        categories: ["Visitantes de la Aplicación"],
                        majorGridLines: {
                            visible: false
                        }
                    },
                    seriesDefaults: {
                        type: "column"
                    },
                    series: [{
                        
                            field: "value",
                            name: "visitas"
                        
                    }],
                       
                    tooltip: {
                        visible: true,
                        format: "{0:N0}"
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
                    <a href="estructura.jsp" class="list-group-item">Estructura</a>
                    <a href="analisiVisitas.jsp" class="list-group-item active">Visitas</a>
                </div>
            </div>
            <!-- Content Column -->
            <div class="col-md-9">
                <div class="row" id="formIncial">
                    <div class="col-md-2"></div>
                    <div class="col-md-8">
                        <div class="row crud-nav-bar">
                        <div class="well">
                        <h2>Analisis de Visitas realizadas diarimente</h2>
                        <p>Se muestra el analisis de las visitas realizadas entre fechas .</p><!--<button id="cargar" type="button" class="btn btn-danger" onclick="cargarTabla();">Cargar</button>-->
                        <br>
                        <table class="table">
                            <tr>
                                <th style="width: 150px">Fecha Creaci&oacute;n Desde:</th>
                                <th style="width: 250px"><input type="text" class="form-control" data-date-format="dd-mm-yyyy" id="dp1"></th>
                            </tr>    
                            <tr>    
                                <th style="width: 150px">Fecha Creaci&oacute;n Hasta:</th>
                                <th style="width: 250px"><input type="text" class="form-control" data-date-format="dd-mm-yyyy" id="dp2" ></th>
                            </tr>
                            <tr>
                                <th >
                                    
                                </th>
                                <th >                                
                                    <button class="btn btn-primary" id="ConsultaBtn" onclick="buscar();">
                                            &nbsp; Consulta
                                    </button>
				</th>		
                            </tr>
                        </table>
                        </div>
                        </div>
                    </div>
                    <div class="col-md-2">
                        
                    </div>
                </div>
                <!-- Table -->
                <br>            
                   
                <br> 
                <div class="row">
                        <div class="col-lg-12">
                                <div class="table-responsive">
                                       <div id="chart"></div>
                                </div>
                        </div>
                </div>
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
