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
               $("#msgPopup").hide();
               cargarTabla();
               $('#Contenidos').hide();
               
                $("#idioma").kendoMultiSelect({
                    dataTextField : "nombre",
                    dataValueField : "codigo",
                    animation : false,
                    maxSelectedItems : 1
                });
                
               $("#entrada1").kendoMultiSelect({
                    dataTextField : "nombre",
                    dataValueField : "codigo",
                    animation : false,
                    maxSelectedItems : 1
                });
	
               $("#salida1").kendoMultiSelect({
                    dataTextField : "nombre",
                    dataValueField : "codigo",
                    animation : false,
                    maxSelectedItems : 1
                });
               
               $("#entrada2").kendoMultiSelect({
                    dataTextField : "nombre",
                    dataValueField : "codigo",
                    animation : false,
                    maxSelectedItems : 1
                });
	
               $("#salida2").kendoMultiSelect({
                    dataTextField : "nombre",
                    dataValueField : "codigo",
                    animation : false,
                    maxSelectedItems : 1
                });
               
                entradaList1 = $("#entrada1").data(
                        "kendoMultiSelect");
                salidaList1 = $("#salida1").data(
                        "kendoMultiSelect");
               entradaList2 = $("#entrada2").data(
                        "kendoMultiSelect");
                salidaList2 = $("#salida2").data(
                        "kendoMultiSelect");
               CargarInicial ();
               
                $("#save-record").bind({click: function(){
                    
                     var validator = $("#formCrud").kendoValidator().data("kendoValidator"); 
                     $(".required").css("border", "1px solid #ccc");
                      if (validator.validate() === false) {     
                             $(".required").each(function(index) {
                                             var cadena = $(this).val();
                                             if (cadena.length <= 0) {
                                                     $(this).css("border", "1px solid red");
                                                     alert("Por favor ingrese el campo requerido");
                                                     $(this).focus();
                                                     return false;
                                             }		
                                     });
                        }else{
                                identificadorCot = $("#UsuarioId").val();
                                if(identificadorCot === "")
                                    tipoConsulta = "crear";
                                else
                                    tipoConsulta = "editar";
                                enviarDatos(tipoConsulta);
                   }
		}
               
               
                });
                
             });
            function enviarDatos(tipoProceso){
                var entrada1=$("#entrada1 option:selected").val();
                var entrada2=$("#entrada2 option:selected").val();
                var salida1=$("#salida1 option:selected").val();
                var salida2=$("#salida2 option:selected").val();
                var idioma=$("#idioma option:selected").val();
                var texto=$("#nombre").val();
                
                $.ajax({
                    url: '../Estructura_Controller',
                    data: {
                        "tipoConsulta": tipoProceso,
                        "texto":texto,
                        "idioma":idioma,
                        "entrante":entrada1+""+entrada2,
                        "saliente":salida1+""+salida2
                    },
                    async: false,
                    type: 'POST',
                    datatype: 'json',
                    success: function (data) {
                        var exito= data.success;
			if(exito=="true" || exito ===true){
				$("#msgPopup").show();
			}else{
				alert("Se produjo un error al guardar la configuracion");
			}
                    }                    
                });
                
                
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
            
            function redireccionaInsertar(){                
                   $('#add').modal('show');                    
            }
            
            function fnEventoClick(e) {
                e.preventDefault();
                var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
                //alert("Cotización id:"+dataItem.codigo);
                 $('#add').modal('show');
                actualizar(dataItem.id);
            }
            
            function actualizar(id){
                 $('UsuarioId').val(id);
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
                        <!--<button id="cargar" type="button" class="btn btn-danger" onclick="cargarTabla();">Cargar</button>-->
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
                                        <div id="grid"></div>
                                </div>
                        </div>
                </div>
            </div>
            
            <!-- Modal -->
		<div class="modal fade" id="add" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<form id="formCrud">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Kutipak traductor Online</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">La configuraci&oacute;n se guardo con &eacute;xito.</div>
							<div class="status"> </div>	
							<div class="form-group">
								<input type="hidden"class="form-control" id="UsuarioId">										
																
								<select id="idioma" name="idioma"
									data-placeholder="Seleccione una opción..." validationMessage="Campo requerido!!!" required>
                                                                     <option value="1">Español</option>
                                                                     <option value="2">Kichwa</option>
								</select>
								<br>
								<label>Nombre Estructura</label>
									<input type="text" class="form-control required" id="nombre" validationMessage="Campo requerido!!!" required>
								
								<label>Formula Entrada</label>
                                                                    <select id="entrada1" name="entrada1"
									data-placeholder="Seleccione una opción..." validationMessage="Campo requerido!!!" required>											
                                                                    </select>
                                                                    <select id="entrada2" name="entrada2"
									data-placeholder="Seleccione una opción..." validationMessage="Campo requerido!!!" required>											
                                                                    </select>
								<label>Formula Salida</label>
                                                                    <select id="salida1" name="salida1"
									data-placeholder="Seleccione una opción..." validationMessage="Campo requerido!!!" required>											
                                                                    </select>
                                                                    <select id="salida2" name="salida2"
									data-placeholder="Seleccione una opción..." validationMessage="Campo requerido!!!" required>											
                                                                    </select>
								 
								<br />																						
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" id="close-popup"
								data-dismiss="modal">Cerrar</button>
							<button type="button" class="btn btn-primary" id="save-record">Guardar Cambios</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
            
            
            
            
            
            
            <div>    
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
