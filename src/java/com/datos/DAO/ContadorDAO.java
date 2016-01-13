/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datos.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
