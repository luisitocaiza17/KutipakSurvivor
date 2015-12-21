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
import static persistencia.Tables.PALABRASSUBFIJOSPREFIJOS;
import persistencia.tables.Palabrassubfijosprefijos;
import static persistencia.tables.Pantallapalabras.PANTALLAPALABRAS;
import persistencia.tables.records.PalabrassubfijosprefijosRecord;
import persistencia.tables.records.PantallapalabrasRecord;


/**
 *
 * @author luisito
 */
public class PalabrassubfijosprefijosDAO {
    public List<PalabrassubfijosprefijosRecord> ConsultarPrefijosSubfijos(int idioma) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
        ConectarBD con = new ConectarBD();
        List<PalabrassubfijosprefijosRecord> listadoPalabras= new ArrayList<PalabrassubfijosprefijosRecord>();
        Connection conexion= con.realiza_conexion();
        DSLContext create = DSL.using(conexion, SQLDialect.MYSQL);
        Result<Record> result = create.select().from(PALABRASSUBFIJOSPREFIJOS).where(PALABRASSUBFIJOSPREFIJOS.IDIOMA.equal(idioma)) .fetch();
         for(Record r : result){
             PalabrassubfijosprefijosRecord palabrassubfijosprefijosRecord= new PalabrassubfijosprefijosRecord();
             palabrassubfijosprefijosRecord.setId(r.getValue(PALABRASSUBFIJOSPREFIJOS.ID));
             palabrassubfijosprefijosRecord.setIdioma(r.getValue(PALABRASSUBFIJOSPREFIJOS.IDIOMA));
             palabrassubfijosprefijosRecord.setPalabra(r.getValue(PALABRASSUBFIJOSPREFIJOS.PALABRA));
             listadoPalabras.add(palabrassubfijosprefijosRecord);
         }
        conexion.close();
        return listadoPalabras;
      }  
}
