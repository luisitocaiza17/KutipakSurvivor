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
        Result<Record> result = create.select().from(PANTALLAPALABRAS).fetch();
        for(Record r : result){
            PantallapalabrasRecord palabras = new PantallapalabrasRecord();
            palabras.setPalabraid(r.getValue(PANTALLAPALABRAS.PALABRAID));
            palabras.setIdioma(r.getValue(PANTALLAPALABRAS.IDIOMA));
            palabras.setPalabras(r.getValue(PANTALLAPALABRAS.PALABRAS));
            palabras.setTraduccion(r.getValue(PANTALLAPALABRAS.TRADUCCION));
            palabras.setTipo(r.getValue(PANTALLAPALABRAS.TIPO));
            palabras.setTiempo(r.getValue(PANTALLAPALABRAS.TIEMPO));
            palabras.setNombretiempo(r.getValue(PANTALLAPALABRAS.NOMBRETIEMPO));
            palabras.setNombretipo(r.getValue(PANTALLAPALABRAS.NOMBRETIPO));
            listadoPalabras.add(palabras);
        }
        conexion.close();
        return listadoPalabras;
    }
}
