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
        <title>Palabras</title>
        <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">
        <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="../css/Kendo/kendo.common.min.css" />
        <link rel="stylesheet" href="../css/Kendo/kendo.default.min.css">
        <link href="../css/bootstrap/styles.css" rel="stylesheet">
        <script src="../js/jquery.min.js"></script>
        <script src="../js/Kendo/kendo.all.min.js"></script>
        <script>
            
            var tipoConsulta="";
            var dataP;
            $(document).ready(function () {              
               cargaCombos();
               
               $('#Contenidos').hide();
               $('#TablaTodos').hide();
               $('#nuevo').hide();
               $('#formIncial').show();
               $("#buscar").click(function () {
                    var palabra=$("#palabra1").val();
                    var idioma= $("#idioma1").val();
                    var traducion= $("#traducion1").val();
                    busquedaEspeficica(palabra,idioma,traducion,"busquedaEspecifica");
                    $('#TablaTodos').show();
               });
               $("#guardar").click(function () {
                        
                       var palabra=$("#palabra").val();
                       var idioma= $("#idioma").val();
                       var tipo=$("#tipo").val();
                       var tiempo= $("#eltiempo").val();
                       var traducion= $("#traducion").val();
                       var id= $("#idPalabra").val();
                       
                        if(palabra==="" || traducion==="" || tipo==="-1" || tiempo==="-1" || idioma==="-1"){
                            if(palabra===""){
                                alert("Por favor ingrese la Palabra");
                           }
                           if(traducion===""){
                                alert("Por favor ingrese el Significado");
                           } 
                           if(tipo==="-1"){
                                alert("Por favor ingrese el Tipo de palabra");
                           }
                           if(tiempo==="-1"){
                                alert("Por favor ingrese el Tiempo");
                           }
                           if(idioma==="-1"){
                                alert("Por favor ingrese el Idioma");
                           }
                        }else{
                            if(id===""){
                                var operacion="insertar";
                                Procesos(operacion,id,palabra,idioma,tipo,tiempo,traducion);
                                tipoConsulta="TodosTipos";
                                location.reload();
                                //cargaInicial(tipoConsulta);
                            }else{
                                var operacion="actualizar";
                                Procesos(operacion,id,palabra,idioma,tipo,tiempo,traducion);
                                tipoConsulta="TodosTipos";
                                location.reload();
                                //cargaInicial(tipoConsulta);
                            }
                            
                        }
                        
                   });
            });
                  
            function busquedaEspeficica(palabra, idioma, traduccion, proceso){
                $('#nuevo').show();
                $.ajax({
                    url: '../Palabras_Controller',
                    data: {
                        "tipoConsulta":proceso,
                        "palabra":palabra,
                        "idioma":idioma,
                        "traduccion":traduccion
                    },
                    async: false,
                    type: 'POST',
                    datatype: 'json',
                    success: function (data) {
                         var listadoPalabras = data.listadoPalabras;
                         var selogro=data.success;
                         if(selogro===true){
                                $("#dataTable").children().remove();
                                $.each(listadoPalabras, function (index) {
                                     $("#dataTable").append("<tr>" +
                                     "<td style='width: 10%'>" + listadoPalabras[index].idioma + "</td>" +        
                                     "<td style='width: 10%'><a onclick='redireccionaActualizar(&#39;"+listadoPalabras[index].id+"&#39;,&#39;"+listadoPalabras[index].idioma+"&#39;,&#39;"+listadoPalabras[index].palabra+"&#39;,&#39;"+listadoPalabras[index].traducion+"&#39;,&#39;"+listadoPalabras[index].tipoP+"&#39;,&#39;"+listadoPalabras[index].tiempoP+"&#39;);'>" + listadoPalabras[index].palabra + "</a></td>" +
                                     "<td style='width: 10%'>" + listadoPalabras[index].traducion + "</td>" +
                                     "<td style='width: 10%'>" + listadoPalabras[index].tipo + "</td>" +
                                     "<td style='width: 10%'>" + listadoPalabras[index].tiempo + "</td>" +
                                     "</tr>");
                                });
                            }
                          
                    }
                    
                });
                
            }
   
            function cargaCombos(){ 
                $("#palabra1").val("");
                $("#traducion1").val("");
                $('#nuevo').hide();
                $("#idioma").val("")
                    .find('option')
                    .remove()
                    .end();
                $.ajax({
                    url: '../Palabras_Controller',
                    data: {
                        "tipoConsulta": "cargaCombosConsulta"
                    },
                    async: false,
                    type: 'POST',
                    datatype: 'json',
                    success: function (data) {
                         $("#idioma1").append("<option selected  value='-1'>Seleccione el idioma</option>");
                            var listadoIdioma = data.listadoIdioma;
                            $.each(listadoIdioma, function (index) {
                                $("#idioma1").append("<option value='" + listadoIdioma[index].idioma + "'>" +  listadoIdioma[index].idioma + "</option>");
                        });
                    }
                });
            }   
    
            function cargaInicial(tipoConsulta){
                
                $('#TablaTodos').hide();
                $('#nuevo').show();
                $("#nemotecnico").val("");
                
                $("#idioma").val("")
                    .find('option')
                    .remove()
                    .end();
                $("#tipo").val("")
                    .find('option')
                    .remove()
                    .end();
                $("#eltiempo").val("")
                    .find('option')
                    .remove()
                    .end();
                 
                $("#palabra").val("");
                $("#traducion").val("")
                $.ajax({
                    url: '../Palabras_Controller',
                    data: {
                        "tipoConsulta": tipoConsulta
                    },
                    async: false,
                    type: 'POST',
                    datatype: 'json',
                    success: function (data) {
                         var listadoPalabras = data.listadoPalabras;
                         var selogro=data.success;
                         if(selogro===true){
                                $("#dataTable").children().remove();
                                
                                 $("#idioma").append("<option selected  value='-1'>Seleccione el idioma</option>");
                                    var listadoIdioma = data.listadoIdioma;
                                    $.each(listadoIdioma, function (index) {
                                        $("#idioma").append("<option value='" + listadoIdioma[index].idioma + "'>" +  listadoIdioma[index].idioma + "</option>");
                                    });
                                    
                                $("#tipo").append("<option selected  value='-1'>Seleccione el tipo de palabra</option>");
                                    var listadoTipos = data.listadoTipos;
                                    $.each(listadoTipos, function (index) {
                                        $("#tipo").append("<option value='" + listadoTipos[index].tipo + "'>" +  listadoTipos[index].tipo + "</option>");
                                    }); 
                                
                                
                                $("#eltiempo").append("<option selected  value='-1'>Seleccione el tiempo</option>");
                                    var listadoTiempos = data.listadoTiempos;
                                    $.each(listadoTiempos, function (index) {
                                        $("#eltiempo").append("<option value='" + listadoTiempos[index].tiempo + "'>" +  listadoTiempos[index].tiempo + "</option>");
                                    }); 
                                    
                                 $("#idioma1").append("<option selected  value='-1'>Seleccione el idioma</option>");
                                    var listadoIdioma = data.listadoIdioma;
                                    $.each(listadoIdioma, function (index) {
                                        $("#idioma1").append("<option value='" + listadoIdioma[index].idioma + "'>" +  listadoIdioma[index].idioma + "</option>");
                                    });
                                 
                             }else{
                                 alert ("no existe nada en la BD");
                             }
                    }
                });
                $("#gridInfo").kendoGrid({
                        height: 500,
                        filterable: true,
                        sortable: true,
                        scrollable: true,
                        pageable: {
                            input: true,
                            numeric: true
            
                        },
                        groupable: true,
                        navigatable: true
                       
                });
            }
            
            function redireccionaInsertar(){
                
                $('#formIncial').hide();
                $('#TablaTodos').hide();
                $('#Contenidos').show();
                $('#nuevo').hide();
                $("#guardar").text("GUARDAR");
                $('#eliminar').hide();
                cargaInicial("TodosTipos");                
            }
            
            function redireccionaActualizar(id,idioma, palabra, traducion, tipo, tiempo){
                $('#formIncial').hide();
                $('#TablaTodos').hide();
                $('#Contenidos').show();
                $('#eliminar').show();
                $('#nuevo').hide();
                cargaInicial("TodosTipos");  
                $('#nuevo').hide();
                if(id!==""){
                    $("#guardar").text("ACTUALIZAR");
                    $("#palabra").val(palabra);
                    $("#idioma").val(idioma);
                    $("#tipo").val(tipo);
                    $("#eltiempo").val(tiempo);
                    $("#traducion").val(traducion);
                    $("#idPalabra").val(id);
                }            
            }
            
            function eliminarRegistro(){
                
               var seguro = confirm("Esta seguro que desea borrar el tiempo?");
                if (seguro === true) {
                    var operacion="eliminar";
                    var idTipoPalabra=$("#idPalabra").val();
                    var nombrePalabra="";
                    var nemotecnico="";
                    Procesos(operacion,idTipoPalabra, nombrePalabra,nemotecnico);
                    var tipoConsulta="TodosTipos";
                    location.reload();
                } 
                
            }
            
            function Procesos(operacion,id,palabra,idioma,tipo,tiempo,traducion){
                
                $.ajax({
                    url: '../Palabras_Controller',
                    data: {
                        "operacion": operacion,
                        "id":id,
                        "palabra":palabra,
                        "idioma":idioma,
                        "tipo":tipo,
                        "tiempo":tiempo,
                        "traducion":traducion
                    },
                    async: false,
                    type: 'POST',
                    datatype: 'json',
                    success: function (data) {
                         var selogro=data.success;
                         var mensaje=data.mensaje;
                          if(selogro===true){
                                    alert (""+mensaje);
                                    
                              }else{
                                 alert ("PROCESO INCORRECTO ERROR:"+mensaje);
                             }
                          
                    }
                    
                });
                
            }
            
            function cargaTraduccion(idioma,tipoConsulta){
               
                $.ajax({
                    url: '../Palabras_Controller',
                    data: {
                        "tipoConsulta": tipoConsulta,
                        "idioma":idioma
                    },
                    async: false,
                    type: 'POST',
                    datatype: 'json',
                    success: function (data) {
                        
                        //toma el array y la combierte em JS    
                                var dataP = [];
                                var lista=data.listadoTraducciones;
                                $.each( lista, function( key, value ) {
                                    dataP.push( value );    
                                });
                                //Fim 
                                $("#traducion").kendoAutoComplete({
                                        dataSource: dataP,
                                        filter: "startswith",
                                        placeholder: "SIGNIFICADO..."
                                        
                                }); 
                                
                    }
                    
                });
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
         
         function abreCambio(){
             var agente = window.open("cambioContrasenia.jsp","ventana1","directories=no,width=500,height=500,resizable=no,scrollbars=yes,top=0,left=260,status=1");
             agente.opener = self;
        }
        
        function CargarExcel(){
             var agente = window.open("CargaExcel.jsp","ventana1","directories=no,width=500,height=500,resizable=no,scrollbars=yes,top=0,left=260,status=1");
             agente.opener = self;
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
                    <li class="active">Administración</li>
                    
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
                    <a href="palabras.jsp" class="list-group-item active">Palabras</a> 
                    <a href="estructura.jsp" class="list-group-item ">Estructura</a>
                </div>
            </div>
            <!-- Content Column -->
            <div class="col-md-9">
                <div class="row" id="formIncial">
                    <div class="col-md-2"></div>
                    <div class="col-md-8">
                    <h2>Palabras</h2>
                    <p>Todas las palabras que contamos en el traductor web.</p>
                    <br>
                    
                    <br>
                    <form class="form-horizontal">
                        <div class="form-group">
                          <label for="palabra1" class="col-sm-2 control-label">Palabra</label>
                          <div class="col-sm-6">
                            <input type="text" class="form-control" id="palabra1" placeholder="auto,casa" style="text-transform:uppercase;" onkeyup="javascript:this.value=this.value.toUpperCase();" >
                          </div>
                        </div>
                        <div class="form-group">
                          <label for="idioma1" class="col-sm-2 control-label">Idioma</label>
                          <div class="col-sm-6">
                            <select class="form-control required" id="idioma1"onchange="carga(this)"  > 

                            </select>
                          </div>
                        </div>
                        <div class="form-group">
                          <label for="traducion1" class="col-sm-2 control-label">Traducción</label>
                          <div class="col-sm-6">

                                <input id="traducion1" class="form-control required" style="width: 100%;" style="text-transform:uppercase;" onkeyup="javascript:this.value=this.value.toUpperCase();" />
                                <br>
                                <button type="button"  id="buscar" class="col-sm-6 btn btn-success" >BUSCAR</button>
                            </div>
                          </div>                                
                    </form>
                    </div>
                    <div class="col-md-2"></div>
                </div>
            </div>
            <br>
            <div align="right">
                            <button id="CargarExcel" type="button" class="btn btn-primary btn-xs" onclick="CargarExcel();">Cargar desde Excel</button>
            </div> 
            <div align="right">
                <button id="nuevo" type="button" class="btn btn-default" onclick="redireccionaInsertar();">Nuevo</button>
            </div>
            <br>            
            <div>    
                <div id="TablaTodos" class="row">
                    <div class="col-md-4"></div>
                    <div class="col-md-6">
                        <table class="table table-hover table-bordered" id="gridInfo">
                         <colgroup>
                                <col />
                                <col />
                                <col />
                                <col />
                                <col />
                        </colgroup>
                        <thead>
                            <tr>
                                <th >Idioma</th>
                                <th >Palabra</th>
                                <th >Traducción</th>
                                <th >Tipo</th>
                                <th >Tiempo</th>
                            </tr>
                        </thead>
                        <tbody id="dataTable">
                        </tbody>
                                
                    </table>
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
