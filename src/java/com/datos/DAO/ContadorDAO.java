/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datos.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import static persistencia.Tables.CONTADOR;
import persistencia.tables.Contador;
import persistencia.tables.Estructura;
import static persistencia.tables.Estructura.ESTRUCTURA;
import persistencia.tables.records.ContadorRecord;
import persistencia.tables.records.EstructuraRecord;

/**
 *
 * @author lcaiza
 */
public class ContadorDAO {
   public int consultar() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
        ConectarBD con = new ConectarBD();
        List<EstructuraRecord>listadoEstructuras= new ArrayList<EstructuraRecord>();
        Connection conexion= con.realiza_conexion();
        DSLContext create = DSL.using(conexion, SQLDialect.MYSQL);
        Result<Record> result = create.select().from(CONTADOR).fetch();
        int contador=0;
        for(Record r : result){
           contador++;
        }
        return contador;
   }
   
   public int consultar(String fecha1, String fecha2) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException{
        
       if(fecha1.equals(""))
           fecha1="01-01-1990";
       
       if(fecha2.equals(""))
           fecha2="01-01-2220";
       
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        Date parsedDate1 = dateFormat.parse(fecha1);
        Timestamp timestamp1 = new java.sql.Timestamp(parsedDate1.getTime());
        
        Date parsedDate2 = dateFormat.parse(fecha2);
        Timestamp timestamp2 = new java.sql.Timestamp(parsedDate2.getTime());
    
       ConectarBD con = new ConectarBD();
        List<EstructuraRecord>listadoEstructuras= new ArrayList<EstructuraRecord>();
        Connection conexion= con.realiza_conexion();
        DSLContext create = DSL.using(conexion, SQLDialect.MYSQL);
        Result<Record> result = create.select().from(CONTADOR).where(CONTADOR.FECHA.greaterOrEqual(timestamp1).and(CONTADOR.FECHA.lessOrEqual(timestamp2))).fetch();
        int contador=0;
        for(Record r : result){
           contador++;
        }
        return contador;
   }
   
   
   
   public boolean InsertarNumero(ContadorRecord contadorEntrante) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
        ConectarBD con = new ConectarBD();
        Connection conexion = con.realiza_conexion();
	DSLContext create = DSL.using(conexion, SQLDialect.MYSQL);
        create.insertInto(Contador.CONTADOR,Contador.CONTADOR.VALOR,Contador.CONTADOR.FECHA)
                .values(contadorEntrante.getValor(),contadorEntrante.getFecha()).execute();
        conexion.close();
        return true;
    }
}
