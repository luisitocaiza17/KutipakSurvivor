/**
 * This class is generated by jOOQ
 */
package persistencia;


import javax.annotation.Generated;

import persistencia.tables.Contador;
import persistencia.tables.Estructura;
import persistencia.tables.Estructurapalabras;
import persistencia.tables.Idiomas;
import persistencia.tables.Palabras;
import persistencia.tables.Palabrassubfijosprefijos;
import persistencia.tables.Pantallapalabras;
import persistencia.tables.Personas;
import persistencia.tables.Sugerencias;
import persistencia.tables.Tiempos;
import persistencia.tables.Tipospalabras;
import persistencia.tables.Usuario;


/**
 * Convenience access to all tables in kutipak
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

	/**
	 * The table kutipak.contador
	 */
	public static final Contador CONTADOR = persistencia.tables.Contador.CONTADOR;

	/**
	 * The table kutipak.estructura
	 */
	public static final Estructura ESTRUCTURA = persistencia.tables.Estructura.ESTRUCTURA;

	/**
	 * The table kutipak.estructurapalabras
	 */
	public static final Estructurapalabras ESTRUCTURAPALABRAS = persistencia.tables.Estructurapalabras.ESTRUCTURAPALABRAS;

	/**
	 * The table kutipak.idiomas
	 */
	public static final Idiomas IDIOMAS = persistencia.tables.Idiomas.IDIOMAS;

	/**
	 * The table kutipak.palabras
	 */
	public static final Palabras PALABRAS = persistencia.tables.Palabras.PALABRAS;

	/**
	 * The table kutipak.palabrassubfijosprefijos
	 */
	public static final Palabrassubfijosprefijos PALABRASSUBFIJOSPREFIJOS = persistencia.tables.Palabrassubfijosprefijos.PALABRASSUBFIJOSPREFIJOS;

	/**
	 * VIEW
	 */
	public static final Pantallapalabras PANTALLAPALABRAS = persistencia.tables.Pantallapalabras.PANTALLAPALABRAS;

	/**
	 * The table kutipak.personas
	 */
	public static final Personas PERSONAS = persistencia.tables.Personas.PERSONAS;

	/**
	 * The table kutipak.sugerencias
	 */
	public static final Sugerencias SUGERENCIAS = persistencia.tables.Sugerencias.SUGERENCIAS;

	/**
	 * The table kutipak.tiempos
	 */
	public static final Tiempos TIEMPOS = persistencia.tables.Tiempos.TIEMPOS;

	/**
	 * The table kutipak.tipospalabras
	 */
	public static final Tipospalabras TIPOSPALABRAS = persistencia.tables.Tipospalabras.TIPOSPALABRAS;

	/**
	 * The table kutipak.usuario
	 */
	public static final Usuario USUARIO = persistencia.tables.Usuario.USUARIO;
}
