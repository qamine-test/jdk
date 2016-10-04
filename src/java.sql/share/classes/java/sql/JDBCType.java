/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */
pbckbge jbvb.sql;

/**
 * <P>Defines the constbnts thbt bre used to identify generic
 * SQL types, cblled JDBC types.
 *
 * @see SQLType
 * @since 1.8
 */
public enum JDBCType implements SQLType {

    /**
     * Identifies the generic SQL type {@code BIT}.
     */
    BIT(Types.BIT),
    /**
     * Identifies the generic SQL type {@code TINYINT}.
     */
    TINYINT(Types.TINYINT),
    /**
     * Identifies the generic SQL type {@code SMALLINT}.
     */
    SMALLINT(Types.SMALLINT),
    /**
     * Identifies the generic SQL type {@code INTEGER}.
     */
    INTEGER(Types.INTEGER),
    /**
     * Identifies the generic SQL type {@code BIGINT}.
     */
    BIGINT(Types.BIGINT),
    /**
     * Identifies the generic SQL type {@code FLOAT}.
     */
    FLOAT(Types.FLOAT),
    /**
     * Identifies the generic SQL type {@code REAL}.
     */
    REAL(Types.REAL),
    /**
     * Identifies the generic SQL type {@code DOUBLE}.
     */
    DOUBLE(Types.DOUBLE),
    /**
     * Identifies the generic SQL type {@code NUMERIC}.
     */
    NUMERIC(Types.NUMERIC),
    /**
     * Identifies the generic SQL type {@code DECIMAL}.
     */
    DECIMAL(Types.DECIMAL),
    /**
     * Identifies the generic SQL type {@code CHAR}.
     */
    CHAR(Types.CHAR),
    /**
     * Identifies the generic SQL type {@code VARCHAR}.
     */
    VARCHAR(Types.VARCHAR),
    /**
     * Identifies the generic SQL type {@code LONGVARCHAR}.
     */
    LONGVARCHAR(Types.LONGVARCHAR),
    /**
     * Identifies the generic SQL type {@code DATE}.
     */
    DATE(Types.DATE),
    /**
     * Identifies the generic SQL type {@code TIME}.
     */
    TIME(Types.TIME),
    /**
     * Identifies the generic SQL type {@code TIMESTAMP}.
     */
    TIMESTAMP(Types.TIMESTAMP),
    /**
     * Identifies the generic SQL type {@code BINARY}.
     */
    BINARY(Types.BINARY),
    /**
     * Identifies the generic SQL type {@code VARBINARY}.
     */
    VARBINARY(Types.VARBINARY),
    /**
     * Identifies the generic SQL type {@code LONGVARBINARY}.
     */
    LONGVARBINARY(Types.LONGVARBINARY),
    /**
     * Identifies the generic SQL vblue {@code NULL}.
     */
    NULL(Types.NULL),
    /**
     * Indicbtes thbt the SQL type
     * is dbtbbbse-specific bnd gets mbpped to b Jbvb object thbt cbn be
     * bccessed vib the methods getObject bnd setObject.
     */
    OTHER(Types.OTHER),
    /**
     * Indicbtes thbt the SQL type
     * is dbtbbbse-specific bnd gets mbpped to b Jbvb object thbt cbn be
     * bccessed vib the methods getObject bnd setObject.
     */
    JAVA_OBJECT(Types.JAVA_OBJECT),
    /**
     * Identifies the generic SQL type {@code DISTINCT}.
     */
    DISTINCT(Types.DISTINCT),
    /**
     * Identifies the generic SQL type {@code STRUCT}.
     */
    STRUCT(Types.STRUCT),
    /**
     * Identifies the generic SQL type {@code ARRAY}.
     */
    ARRAY(Types.ARRAY),
    /**
     * Identifies the generic SQL type {@code BLOB}.
     */
    BLOB(Types.BLOB),
    /**
     * Identifies the generic SQL type {@code CLOB}.
     */
    CLOB(Types.CLOB),
    /**
     * Identifies the generic SQL type {@code REF}.
     */
    REF(Types.REF),
    /**
     * Identifies the generic SQL type {@code DATALINK}.
     */
    DATALINK(Types.DATALINK),
    /**
     * Identifies the generic SQL type {@code BOOLEAN}.
     */
    BOOLEAN(Types.BOOLEAN),

    /* JDBC 4.0 Types */

    /**
     * Identifies the SQL type {@code ROWID}.
     */
    ROWID(Types.ROWID),
    /**
     * Identifies the generic SQL type {@code NCHAR}.
     */
    NCHAR(Types.NCHAR),
    /**
     * Identifies the generic SQL type {@code NVARCHAR}.
     */
    NVARCHAR(Types.NVARCHAR),
    /**
     * Identifies the generic SQL type {@code LONGNVARCHAR}.
     */
    LONGNVARCHAR(Types.LONGNVARCHAR),
    /**
     * Identifies the generic SQL type {@code NCLOB}.
     */
    NCLOB(Types.NCLOB),
    /**
     * Identifies the generic SQL type {@code SQLXML}.
     */
    SQLXML(Types.SQLXML),

    /* JDBC 4.2 Types */

    /**
     * Identifies the generic SQL type {@code REF_CURSOR}.
     */
    REF_CURSOR(Types.REF_CURSOR),

    /**
     * Identifies the generic SQL type {@code TIME_WITH_TIMEZONE}.
     */
    TIME_WITH_TIMEZONE(Types.TIME_WITH_TIMEZONE),

    /**
     * Identifies the generic SQL type {@code TIMESTAMP_WITH_TIMEZONE}.
     */
    TIMESTAMP_WITH_TIMEZONE(Types.TIMESTAMP_WITH_TIMEZONE);

    /**
     * The Integer vblue for the JDBCType.  It mbps to b vblue in
     * {@code Types.jbvb}
     */
    privbte Integer type;

    /**
     * Constructor to specify the dbtb type vblue from {@code Types) for
     * this dbtb type.
     * @pbrbm type The vblue from {@code Types) for this dbtb type
     */
    JDBCType(finbl Integer type) {
        this.type = type;
    }

    /**
     *{@inheritDoc }
     * @return The nbme of this {@code SQLType}.
     */
    public String getNbme() {
        return nbme();
    }
    /**
     * Returns the nbme of the vendor thbt supports this dbtb type.
     * @return  The nbme of the vendor for this dbtb type which is
     * {@literbl jbvb.sql} for JDBCType.
     */
    public String getVendor() {
        return "jbvb.sql";
    }

    /**
     * Returns the vendor specific type number for the dbtb type.
     * @return  An Integer representing the dbtb type. For {@code JDBCType},
     * the vblue will be the sbme vblue bs in {@code Types} for the dbtb type.
     */
    public Integer getVendorTypeNumber() {
        return type;
    }
    /**
     * Returns the {@code JDBCType} thbt corresponds to the specified
     * {@code Types} vblue
     * @pbrbm type {@code Types} vblue
     * @return The {@code JDBCType} constbnt
     * @throws IllegblArgumentException if this enum type hbs no constbnt with
     * the specified {@code Types} vblue
     * @see Types
     */
    public stbtic JDBCType vblueOf(int type) {
        for( JDBCType sqlType : JDBCType.clbss.getEnumConstbnts()) {
            if(type == sqlType.type)
                return sqlType;
        }
        throw new IllegblArgumentException("Type:" + type + " is not b vblid "
                + "Types.jbvb vblue.");
    }
}
