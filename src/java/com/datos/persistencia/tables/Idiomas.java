/**
 * This class is generated by jOOQ
 */
package persistencia.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;

import persistencia.Keys;
import persistencia.Kutipak;
import persistencia.tables.records.IdiomasRecord;


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
public class Idiomas extends TableImpl<IdiomasRecord> {

	private static final long serialVersionUID = -127058840;

	/**
	 * The reference instance of <code>kutipak.idiomas</code>
	 */
	public static final Idiomas IDIOMAS = new Idiomas();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<IdiomasRecord> getRecordType() {
		return IdiomasRecord.class;
	}

	/**
	 * The column <code>kutipak.idiomas.IDIOMAID</code>.
	 */
	public final TableField<IdiomasRecord, Integer> IDIOMAID = createField("IDIOMAID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>kutipak.idiomas.NOMBRE</code>.
	 */
	public final TableField<IdiomasRecord, String> NOMBRE = createField("NOMBRE", org.jooq.impl.SQLDataType.VARCHAR.length(20), this, "");

	/**
	 * Create a <code>kutipak.idiomas</code> table reference
	 */
	public Idiomas() {
		this("idiomas", null);
	}

	/**
	 * Create an aliased <code>kutipak.idiomas</code> table reference
	 */
	public Idiomas(String alias) {
		this(alias, IDIOMAS);
	}

	private Idiomas(String alias, Table<IdiomasRecord> aliased) {
		this(alias, aliased, null);
	}

	private Idiomas(String alias, Table<IdiomasRecord> aliased, Field<?>[] parameters) {
		super(alias, Kutipak.KUTIPAK, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<IdiomasRecord, Integer> getIdentity() {
		return Keys.IDENTITY_IDIOMAS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<IdiomasRecord> getPrimaryKey() {
		return Keys.KEY_IDIOMAS_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<IdiomasRecord>> getKeys() {
		return Arrays.<UniqueKey<IdiomasRecord>>asList(Keys.KEY_IDIOMAS_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Idiomas as(String alias) {
		return new Idiomas(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Idiomas rename(String name) {
		return new Idiomas(name, null);
	}
}
