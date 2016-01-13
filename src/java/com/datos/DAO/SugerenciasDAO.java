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
import static persistencia.Tables.SUGERENCIAS;
import persistencia.tables.Estructura;
import static persistencia.tables.Estructura.ESTRUCTURA;
import persistencia.tables.Sugerencias;
import persistencia.tables.records.EstructuraRecord;
import persistencia.tables.records.SugerenciasRecord;

/**
 *
 * @author lcaiza
 */
public class SugerenciasDAO {
    public ArrayList<SugerenciasRecord> ConsultaSugerencias () throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
        ConectarBD con = new ConectarBD();
        ArrayList<SugerenciasRecord>Sugerencias= new ArrayList<SugerenciasRecord>();
        Connection conexion= con.realiza_conexion();
        DSLContext create = DSL.using(conexion, SQLDialect.MYSQL);
        Result<Record> result = create.select().from(SUGERENCIAS).orderBy(SUGERENCIAS.SUGERENCIASID.desc()).limit(10).fetch();
         for(Record r : result){
             SugerenciasRecord lasSugerencias = new SugerenciasRecord();
             lasSugerencias.setSugerenciasid(r.getValue(SUGERENCIAS.SUGERENCIASID));
             lasSugerencias.setTiempo(r.getValue(SUGERENCIAS.TIEMPO));
             lasSugerencias.setSirvio(r.getValue(SUGERENCIAS.SIRVIO));
             lasSugerencias.setComentario(r.getValue(SUGERENCIAS.COMENTARIO));
             Sugerencias.add(lasSugerencias);
         }
         conexion.close();
         return Sugerencias;
    }
    
    public int ContadorTiemposExitosos () throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
        ConectarBD con = new ConectarBD();
        List<SugerenciasRecord>Sugerencias= new ArrayList<SugerenciasRecord>();
        Connection conexion= con.realiza_conexion();
        DSLContext create = DSL.using(conexion, SQLDialect.MYSQL);
        Result<Record> result = create.select().from(SUGERENCIAS).fetch();
        int contador=0;
        for(Record r : result){
            if(r.getValue(SUGERENCIAS.TIEMPO))
                contador++;             
         }
         conexion.close();
         return contador;
    }
    
    public int ContadorConsultasExitosas () throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
        ConectarBD con = new ConectarBD();
        List<SugerenciasRecord>Sugerencias= new ArrayList<SugerenciasRecord>();
        Connection conexion= con.realiza_conexion();
        DSLContext create = DSL.using(conexion, SQLDialect.MYSQL);
        Result<Record> result = create.select().from(SUGERENCIAS).fetch();
        int contador=0;
        for(Record r : result){
            if(r.getValue(SUGERENCIAS.SIRVIO))
                contador++;             
         }
         conexion.close();
         return contador;
    }
    
    public int ContadorSugerencias () throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
        ConectarBD con = new ConectarBD();
        List<SugerenciasRecord>Sugerencias= new ArrayList<SugerenciasRecord>();
        Connection conexion= con.realiza_conexion();
        DSLContext create = DSL.using(conexion, SQLDialect.MYSQL);
        Result<Record> result = create.select().from(SUGERENCIAS).fetch();
        int contador=0;
        for(Record r : result){
            contador++;
             
         }
         conexion.close();
         return contador;
    }
    
    
    public boolean GrabarSugerencia(SugerenciasRecord sugerencia) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
        ConectarBD con = new ConectarBD();
        Connection conexion = con.realiza_conexion();
	DSLContext create = DSL.using(conexion, SQLDialect.MYSQL);
        create.insertInto(Sugerencias.SUGERENCIAS,Sugerencias.SUGERENCIAS.SIRVIO,Sugerencias.SUGERENCIAS.TIEMPO,Sugerencias.SUGERENCIAS.COMENTARIO,Sugerencias.SUGERENCIAS.FECHA)
                .values(sugerencia.getSirvio(),sugerencia.getTiempo(),sugerencia.getComentario(),sugerencia.getFecha()).execute();
        conexion.close();
        return true;
    }
    
    
}
