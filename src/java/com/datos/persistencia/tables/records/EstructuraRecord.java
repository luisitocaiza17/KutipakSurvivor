/**
 * This class is generated by jOOQ
 */
package persistencia.tables.records;


import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;

import persistencia.tables.Estructura;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class EstructuraRecord extends UpdatableRecordImpl<EstructuraRecord> implements Record5<Integer, Integer, String, String, String> {

	private static final long serialVersionUID = 1940507777;

	/**
	 * Setter for <code>kutipak.estructura.ESTRUCTURAID</code>.
	 */
	public void setEstructuraid(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>kutipak.estructura.ESTRUCTURAID</code>.
	 */
	public Integer getEstructuraid() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>kutipak.estructura.IDIOMAID</code>.
	 */
	public void setIdiomaid(Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>kutipak.estructura.IDIOMAID</code>.
	 */
	public Integer getIdiomaid() {
		return (Integer) getValue(1);
	}

	/**
	 * Setter for <code>kutipak.estructura.NOMBREESTRUCTURA</code>.
	 */
	public void setNombreestructura(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>kutipak.estructura.NOMBREESTRUCTURA</code>.
	 */
	public String getNombreestructura() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>kutipak.estructura.FORMULA</code>.
	 */
	public void setFormula(String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>kutipak.estructura.FORMULA</code>.
	 */
	public String getFormula() {
		return (String) getValue(3);
	}

	/**
	 * Setter for <code>kutipak.estructura.FORMULASALIDA</code>.
	 */
	public void setFormulasalida(String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>kutipak.estructura.FORMULASALIDA</code>.
	 */
	public String getFormulasalida() {
		return (String) getValue(4);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<Integer> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record5 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row5<Integer, Integer, String, String, String> fieldsRow() {
		return (Row5) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row5<Integer, Integer, String, String, String> valuesRow() {
		return (Row5) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return Estructura.ESTRUCTURA.ESTRUCTURAID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field2() {
		return Estructura.ESTRUCTURA.IDIOMAID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return Estructura.ESTRUCTURA.NOMBREESTRUCTURA;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field4() {
		return Estructura.ESTRUCTURA.FORMULA;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field5() {
		return Estructura.ESTRUCTURA.FORMULASALIDA;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value1() {
		return getEstructuraid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value2() {
		return getIdiomaid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getNombreestructura();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value4() {
		return getFormula();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value5() {
		return getFormulasalida();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EstructuraRecord value1(Integer value) {
		setEstructuraid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EstructuraRecord value2(Integer value) {
		setIdiomaid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EstructuraRecord value3(String value) {
		setNombreestructura(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EstructuraRecord value4(String value) {
		setFormula(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EstructuraRecord value5(String value) {
		setFormulasalida(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EstructuraRecord values(Integer value1, Integer value2, String value3, String value4, String value5) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached EstructuraRecord
	 */
	public EstructuraRecord() {
		super(Estructura.ESTRUCTURA);
	}

	/**
	 * Create a detached, initialised EstructuraRecord
	 */
	public EstructuraRecord(Integer estructuraid, Integer idiomaid, String nombreestructura, String formula, String formulasalida) {
		super(Estructura.ESTRUCTURA);

		setValue(0, estructuraid);
		setValue(1, idiomaid);
		setValue(2, nombreestructura);
		setValue(3, formula);
		setValue(4, formulasalida);
	}
}
