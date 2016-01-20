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
import static persistencia.tables.Pantallapalabras.PANTALLAPALABRAS;

import persistencia.tables.records.PantallapalabrasRecord;

/**
 *
 * @author luisito
 */
public class PantallaPalabrasDAO {
    /*Metodo que trae el listado de todos los tiempos*/
    public List<PantallapalabrasRecord> ConsultarPalabras() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
        ConectarBD con = new ConectarBD();
        List<PantallapalabrasRecord> listadoPalabras= new ArrayList<PantallapalabrasRecord>();
        Connection conexion= con.realiza_conexion();
        DSLContext create = DSL.using(conexion, SQLDialect.MYSQL);
        Result<Record> result = create.select().from(PANTALLAPALABRAS)
                .orderBy(PANTALLAPALABRAS.IDIOMA,PANTALLAPALABRAS.TIEMPO,PANTALLAPALABRAS.TIPO) .fetch();
        for(Record r : result){
            PantallapalabrasRecord palabras = new PantallapalabrasRecord();
             palabras.setIdioma(r.getValue(PANTALLAPALABRAS.IDIOMA));
             palabras.setNombretiempo(r.getValue(PANTALLAPALABRAS.NOMBRETIEMPO));
             palabras.setNombretipo(r.getValue(PANTALLAPALABRAS.NOMBRETIPO));
             palabras.setPalabraid(r.getValue(PANTALLAPALABRAS.PALABRAID));
             palabras.setPalabras(r.getValue(PANTALLAPALABRAS.PALABRAS));
             palabras.setSignificado(r.getValue(PANTALLAPALABRAS.SIGNIFICADO));
             palabras.setTiempo(r.getValue(PANTALLAPALABRAS.TIEMPO));
             palabras.setTipo(r.getValue(PANTALLAPALABRAS.TIPO));
             listadoPalabras.add(palabras);
        }
        conexion.close();
        return listadoPalabras;
    }
    
     public List<PantallapalabrasRecord> ConsultarPalabrasEspecifica2(String palabra,String idioma) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
        ConectarBD con = new ConectarBD();
        List<PantallapalabrasRecord> listadoPalabras= new ArrayList<PantallapalabrasRecord>();
        Connection conexion= con.realiza_conexion();
        DSLContext create = DSL.using(conexion, SQLDialect.MYSQL);
        Result<Record> result = create.select().from(PANTALLAPALABRAS).where(PANTALLAPALABRAS.PALABRAS.equal(palabra)).and (PANTALLAPALABRAS.IDIOMA.equal(idioma)).fetch();
        for(Record r : result){
            PantallapalabrasRecord palabras = new PantallapalabrasRecord();
            palabras.setIdioma(r.getValue(PANTALLAPALABRAS.IDIOMA));
             palabras.setNombretiempo(r.getValue(PANTALLAPALABRAS.NOMBRETIEMPO));
             palabras.setNombretipo(r.getValue(PANTALLAPALABRAS.NOMBRETIPO));
             palabras.setPalabraid(r.getValue(PANTALLAPALABRAS.PALABRAID));
             palabras.setPalabras(r.getValue(PANTALLAPALABRAS.PALABRAS));
             palabras.setSignificado(r.getValue(PANTALLAPALABRAS.SIGNIFICADO));
             palabras.setTiempo(r.getValue(PANTALLAPALABRAS.TIEMPO));
             palabras.setTipo(r.getValue(PANTALLAPALABRAS.TIPO));
            listadoPalabras.add(palabras);
        }
        conexion.close();
        return listadoPalabras;
    }
    
    public List<PantallapalabrasRecord> ConsultarPalabrasEspecifica(String palabra) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
        ConectarBD con = new ConectarBD();
        List<PantallapalabrasRecord> listadoPalabras= new ArrayList<PantallapalabrasRecord>();
        Connection conexion= con.realiza_conexion();
        DSLContext create = DSL.using(conexion, SQLDialect.MYSQL);
        Result<Record> result = create.select().from(PANTALLAPALABRAS).where(PANTALLAPALABRAS.PALABRAS.equal(palabra)).fetch();
        for(Record r : result){
            PantallapalabrasRecord palabras = new PantallapalabrasRecord();
            palabras.setIdioma(r.getValue(PANTALLAPALABRAS.IDIOMA));
             palabras.setNombretiempo(r.getValue(PANTALLAPALABRAS.NOMBRETIEMPO));
             palabras.setNombretipo(r.getValue(PANTALLAPALABRAS.NOMBRETIPO));
             palabras.setPalabraid(r.getValue(PANTALLAPALABRAS.PALABRAID));
             palabras.setPalabras(r.getValue(PANTALLAPALABRAS.PALABRAS));
             palabras.setSignificado(r.getValue(PANTALLAPALABRAS.SIGNIFICADO));
             palabras.setTiempo(r.getValue(PANTALLAPALABRAS.TIEMPO));
             palabras.setTipo(r.getValue(PANTALLAPALABRAS.TIPO));
            listadoPalabras.add(palabras);
        }
        conexion.close();
        return listadoPalabras;
    }
    
    public List<PantallapalabrasRecord> ConsultarPalabrasEspecificaIdioma(String idioma) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
        ConectarBD con = new ConectarBD();
        List<PantallapalabrasRecord> listadoPalabras= new ArrayList<PantallapalabrasRecord>();
        Connection conexion= con.realiza_conexion();
        DSLContext create = DSL.using(conexion, SQLDialect.MYSQL);
        Result<Record> result = create.select().from(PANTALLAPALABRAS).where(PANTALLAPALABRAS.IDIOMA.equal(idioma)).fetch();
        for(Record r : result){
            PantallapalabrasRecord palabras = new PantallapalabrasRecord();
            palabras.setIdioma(r.getValue(PANTALLAPALABRAS.IDIOMA));
             palabras.setNombretiempo(r.getValue(PANTALLAPALABRAS.NOMBRETIEMPO));
             palabras.setNombretipo(r.getValue(PANTALLAPALABRAS.NOMBRETIPO));
             palabras.setPalabraid(r.getValue(PANTALLAPALABRAS.PALABRAID));
             palabras.setPalabras(r.getValue(PANTALLAPALABRAS.PALABRAS));
             palabras.setSignificado(r.getValue(PANTALLAPALABRAS.SIGNIFICADO));
             palabras.setTiempo(r.getValue(PANTALLAPALABRAS.TIEMPO));
             palabras.setTipo(r.getValue(PANTALLAPALABRAS.TIPO));
            listadoPalabras.add(palabras);
        }
        conexion.close();
        return listadoPalabras;
    }
    
    public boolean ConsultarPalabrasExistente(PantallapalabrasRecord palabra) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
        ConectarBD con = new ConectarBD();
        List<PantallapalabrasRecord> listadoPalabras= new ArrayList<PantallapalabrasRecord>();
        Connection conexion= con.realiza_conexion();
        DSLContext create = DSL.using(conexion, SQLDialect.MYSQL);
        Result<Record> result = create.select().from(PANTALLAPALABRAS).where(PANTALLAPALABRAS.IDIOMA.equal(palabra.getIdioma()).
                and(PANTALLAPALABRAS.TIEMPO.equal(palabra.getTiempo()).and(PANTALLAPALABRAS.TIPO.equal(palabra.getTipo())
                        .and(PANTALLAPALABRAS.SIGNIFICADO.equal(palabra.getSignificado())))))
                .orderBy(PANTALLAPALABRAS.IDIOMA,PANTALLAPALABRAS.TIEMPO,PANTALLAPALABRAS.TIPO).fetch();
        boolean existe=false;
        for(Record r : result){
            existe=true;
            break;
        }
        conexion.close();
        return existe;
    }
}
