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
        <script>
            
            var tipoConsulta="";
            var dataP;
            
            var entradaList1 = "";
            var salidaList1 = "";
            var entradaList2 = "";
            var salidaList2 = "";
            
            $(document).ready(function () {  
                
                cargarGrafico();
                                
             });
            function enviarDatos(tipoProceso){
                var entrada1=$("#entrada1 option:selected").val();
                var entrada2=$("#entrada2 option:selected").val();
                var salida1=$("#salida1 option:selected").val();
                var salida2=$("#salida2 option:selected").val();
                var idioma=$("#idioma option:selected").val();
                var texto=$("#nombre").val();
                var id=$('#UsuarioId').val();
                $.ajax({
                    url: '../Estructura_Controller',
                    data: {
                        "tipoConsulta": tipoProceso,
                        "texto":texto,
                        "idioma":idioma,
                        "entrante":entrada1+""+entrada2,
                        "saliente":salida1+""+salida2,
                        "id":id
                    },
                    async: false,
                    type: 'POST',
                    datatype: 'json',
                    success: function (data) {
                        var exito= data.success;
			if(exito=="true" || exito ===true){
				$("#msgPopup").show();
                                cargarTabla();
			}else{
				alert("Se produjo un error al guardar la configuracion");
			}
                    }                    
                });
                
                
            }    
                
            function limpiar(){
                $("#idioma").data("kendoMultiSelect").value(null);
                $("#nombre").val("");
                $("#entrada1").data("kendoMultiSelect").value(null);
                $("#entrada2").data("kendoMultiSelect").value(null);
                $("#salida1").data("kendoMultiSelect").value(null);
                $("#salida2").data("kendoMultiSelect").value(null);
                
            }    
                
            function CargarInicial (){
                $.ajax({
                    url: '../Estructura_Controller',
                    data: {
                        "tipoConsulta": "CargaInicial"                        
                    },
                    async: false,
                    type: 'POST',
                    datatype: 'json',
                    success: function (data) {
                        /*Cargo el select agencias*/
                        entradaList1.dataSource.filter({});
                        entradaList1.setDataSource(data.entrada1);
                        
                        entradaList2.dataSource.filter({});
                        entradaList2.setDataSource(data.entrada2);
                        
                        /*Cargo el select agencias*/
                        salidaList1.dataSource.filter({});
                        salidaList1.setDataSource(data.salida1);
                        salidaList2.dataSource.filter({});
                        salidaList2.setDataSource(data.salida2);
                    }                    
                });
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
            
            function fnEventoClick(e) {
                e.preventDefault();
                
                var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
                //alert("Cotización id:"+dataItem.codigo);
                 $('#add').modal('show');
                actualizar(dataItem.id);
            }
            
            function eliminar(){
                var id=$('#UsuarioId').val();
                $.ajax({
                    url : '../Estructura_Controller',  
                    data: {
                        "tipoConsulta": "eliminar" ,
                        "id": id 
                    },
                    async: false,
                    type : 'POST',
                    datatype : 'json',
                    success : function(data) {   
                        if (data.success === true){
                                cargarTabla();
                                $('#add').modal('hide');
                                alert ("Se Borró sin problemas");
                                
                        }else{
                            alert ("Upss..Tuvimos un problema no se pudo eliminar el registro");
                        }
                        
                    }
                });
            }
            
            function actualizar(id){
                 $('#UsuarioId').val(id);
                 $('#elimina').show();
                 $("#msgPopup").hide();
                 $.ajax({
                    url : '../Estructura_Controller',  
                    data: {
                        "tipoConsulta": "CargaIndividual" ,
                        "id": id 
                    },
                    async: false,
                    type : 'POST',
                    datatype : 'json',
                    success : function(data) {   
                        $("#idioma").data("kendoMultiSelect").value(data.idioma);
                        $("#nombre").val(data.texto);
                        $("#entrada1").data("kendoMultiSelect").value(data.entrada1);
                        $("#entrada2").data("kendoMultiSelect").value(data.entrada2);
                        $("#salida1").data("kendoMultiSelect").value(data.salida1);
                        $("#salida2").data("kendoMultiSelect").value(data.salida2);
                    }
                });
            }
            
            
            function cargarGrafico(){
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
                                                 "tipoConsulta" : "encontrarTodos"
                                            }
                                    }

                        },
                        schema: {
                                data: "Data"
                        }
                    },
                    seriesDefaults: {
                        type: "column"
                    },
                    series: [{
                        
                            field: "value",
                            name: "value"
                        
                    }]
                });
            }
            
            
            
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
                    serverFiltering: true,
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
                        
                        { field: "idioma", title: "Idioma", width: "60px"},
                        { field: "nombreEstructura", title: "Nombre estructura.", width: "100px"},
                        { field: "estructuraEntrante", title: "Entrante" , width: "80px"},
                        { field: "estructuraSaliente", title: "Saliente", width: "80px"},
                        { command: { text: "Ver Detalle", click: fnEventoClick }, title: " Detalle ", width: "110px"}
                        ],
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
                    <a href="estructura.jsp" class="list-group-item">Estructura</a>
                    <a href="analisiVisitas.jsp" class="list-group-item active">Visitas</a>
                </div>
            </div>
            <!-- Content Column -->
            <div class="col-md-9">
                <div class="row" id="formIncial">
                    <div class="col-md-2"></div>
                    <div class="col-md-8">
                        <h2>Analisis de Visitas realizadas diarimente</h2>
                        <p>Se muestra el analisis de las visitas realizadas entre fechas .</p><!--<button id="cargar" type="button" class="btn btn-danger" onclick="cargarTabla();">Cargar</button>-->
                    </div>
                    <div class="col-md-2">
                        
                    </div>
                </div>
                <!-- Table -->
                <br>            
                    <div align="right">
                        <button id="nuevo" type="button" class="btn btn-default" onclick="redireccionaInsertar();">Nuevo</button>
                    </div>
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
