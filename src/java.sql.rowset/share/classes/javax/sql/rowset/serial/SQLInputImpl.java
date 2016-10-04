/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.sql.rowset.seribl;

import jbvb.sql.*;
import jbvb.util.Arrbys;
import jbvb.util.Mbp;
import sun.reflect.misc.ReflectUtil;

/**
 * An input strebm used for custom mbpping user-defined types (UDTs).
 * An <code>SQLInputImpl</code> object is bn input strebm thbt contbins b
 * strebm of vblues thbt bre the bttributes of b UDT.
 * <p>
 * This clbss is used by the driver behind the scenes when the method
 * <code>getObject</code> is cblled on bn SQL structured or distinct type
 * thbt hbs b custom mbpping; b progrbmmer never invokes
 * <code>SQLInputImpl</code> methods directly. They bre provided here bs b
 * convenience for those who write <code>RowSet</code> implementbtions.
 * <P>
 * The <code>SQLInputImpl</code> clbss provides b set of
 * rebder methods bnblogous to the <code>ResultSet</code> getter
 * methods.  These methods mbke it possible to rebd the vblues in bn
 * <code>SQLInputImpl</code> object.
 * <P>
 * The method <code>wbsNull</code> is used to determine whether the
 * the lbst vblue rebd wbs SQL <code>NULL</code>.
 * <P>When the method <code>getObject</code> is cblled with bn
 * object of b clbss implementing the interfbce <code>SQLDbtb</code>,
 * the JDBC driver cblls the method <code>SQLDbtb.getSQLType</code>
 * to determine the SQL type of the UDT being custom mbpped. The driver
 * crebtes bn instbnce of <code>SQLInputImpl</code>, populbting it with the
 * bttributes of the UDT.  The driver then pbsses the input
 * strebm to the method <code>SQLDbtb.rebdSQL</code>, which in turn
 * cblls the <code>SQLInputImpl</code> rebder methods
 * to rebd the bttributes from the input strebm.
 * @since 1.5
 * @see jbvb.sql.SQLDbtb
 */
public clbss SQLInputImpl implements SQLInput {

    /**
     * <code>true</code> if the lbst vblue returned wbs <code>SQL NULL</code>;
     * <code>fblse</code> otherwise.
     */
    privbte boolebn lbstVblueWbsNull;

    /**
     * The current index into the brrby of SQL structured type bttributes
     * thbt will be rebd from this <code>SQLInputImpl</code> object bnd
     * mbpped to the fields of b clbss in the Jbvb progrbmming lbngubge.
     */
    privbte int idx;

    /**
     * The brrby of bttributes to be rebd from this strebm.  The order
     * of the bttributes is the sbme bs the order in which they were
     * listed in the SQL definition of the UDT.
     */
    privbte Object bttrib[];

    /**
     * The type mbp to use when the method <code>rebdObject</code>
     * is invoked. This is b <code>jbvb.util.Mbp</code> object in which
     * there mby be zero or more entries.  Ebch entry consists of the
     * fully qublified nbme of b UDT (the vblue to be mbpped) bnd the
     * <code>Clbss</code> object for b clbss thbt implements
     * <code>SQLDbtb</code> (the Jbvb clbss thbt defines how the UDT
     * will be mbpped).
     */
    privbte Mbp<String,Clbss<?>> mbp;


    /**
     * Crebtes bn <code>SQLInputImpl</code> object initiblized with the
     * given brrby of bttributes bnd the given type mbp. If bny of the
     * bttributes is b UDT whose nbme is in bn entry in the type mbp,
     * the bttribute will be mbpped bccording to the corresponding
     * <code>SQLDbtb</code> implementbtion.
     *
     * @pbrbm bttributes bn brrby of <code>Object</code> instbnces in which
     *        ebch element is bn bttribute of b UDT. The order of the
     *        bttributes in the brrby is the sbme order in which
     *        the bttributes were defined in the UDT definition.
     * @pbrbm mbp b <code>jbvb.util.Mbp</code> object contbining zero or more
     *        entries, with ebch entry consisting of 1) b <code>String</code>
     *        giving the fully
     *        qublified nbme of the UDT bnd 2) the <code>Clbss</code> object
     *        for the <code>SQLDbtb</code> implementbtion thbt defines how
     *        the UDT is to be mbpped
     * @throws SQLException if the <code>bttributes</code> or the <code>mbp</code>
     *        is b <code>null</code> vblue
     */

    public SQLInputImpl(Object[] bttributes, Mbp<String,Clbss<?>> mbp)
        throws SQLException
    {
        if ((bttributes == null) || (mbp == null)) {
            throw new SQLException("Cbnnot instbntibte b SQLInputImpl " +
            "object with null pbrbmeters");
        }
        // bssign our locbl reference to the bttribute strebm
        bttrib = Arrbys.copyOf(bttributes, bttributes.length);
        // init the index point before the hebd of the strebm
        idx = -1;
        // set the mbp
        this.mbp = mbp;
    }


    /**
     * Retrieves the next bttribute in this <code>SQLInputImpl</code> object
     * bs bn <code>Object</code> in the Jbvb progrbmming lbngubge.
     *
     * @return the next vblue in the input strebm
     *         bs bn <code>Object</code> in the Jbvb progrbmming lbngubge
     * @throws SQLException if the rebd position is locbted bt bn invblid
     *         position or if there bre no further vblues in the strebm
     */
    privbte Object getNextAttribute() throws SQLException {
        if (++idx >= bttrib.length) {
            throw new SQLException("SQLInputImpl exception: Invblid rebd " +
                                   "position");
        } else {
            lbstVblueWbsNull = bttrib[idx] == null;
            return bttrib[idx];
        }
    }


    //================================================================
    // Methods for rebding bttributes from the strebm of SQL dbtb.
    // These methods correspond to the column-bccessor methods of
    // jbvb.sql.ResultSet.
    //================================================================

    /**
     * Retrieves the next bttribute in this <code>SQLInputImpl</code> object bs
     * b <code>String</code> in the Jbvb progrbmming lbngubge.
     * <p>
     * This method does not perform type-sbfe checking to determine if the
     * returned type is the expected type; this responsibility is delegbted
     * to the UDT mbpping bs defined by b <code>SQLDbtb</code>
     * implementbtion.
     *
     * @return the next bttribute in this <code>SQLInputImpl</code> object;
     *     if the vblue is <code>SQL NULL</code>, return <code>null</code>
     * @throws SQLException if the rebd position is locbted bt bn invblid
     *     position or if there bre no further vblues in the strebm.
     */
    public String rebdString() throws SQLException {
        return  (String)getNextAttribute();
    }

    /**
     * Retrieves the next bttribute in this <code>SQLInputImpl</code> object bs
     * b <code>boolebn</code> in the Jbvb progrbmming lbngubge.
     * <p>
     * This method does not perform type-sbfe checking to determine if the
     * returned type is the expected type; this responsibility is delegbted
     * to the UDT mbpping bs defined by b <code>SQLDbtb</code>
     * implementbtion.
     *
     * @return the next bttribute in this <code>SQLInputImpl</code> object;
     *     if the vblue is <code>SQL NULL</code>, return <code>null</code>
     * @throws SQLException if the rebd position is locbted bt bn invblid
     *     position or if there bre no further vblues in the strebm.
     */
    public boolebn rebdBoolebn() throws SQLException {
        Boolebn bttrib = (Boolebn)getNextAttribute();
        return  (bttrib == null) ? fblse : bttrib.boolebnVblue();
    }

    /**
     * Retrieves the next bttribute in this <code>SQLInputImpl</code> object bs
     * b <code>byte</code> in the Jbvb progrbmming lbngubge.
     * <p>
     * This method does not perform type-sbfe checking to determine if the
     * returned type is the expected type; this responsibility is delegbted
     * to the UDT mbpping bs defined by b <code>SQLDbtb</code>
     * implementbtion.
     *
     * @return the next bttribute in this <code>SQLInputImpl</code> object;
     *     if the vblue is <code>SQL NULL</code>, return <code>null</code>
     * @throws SQLException if the rebd position is locbted bt bn invblid
     *     position or if there bre no further vblues in the strebm
     */
    public byte rebdByte() throws SQLException {
        Byte bttrib = (Byte)getNextAttribute();
        return  (bttrib == null) ? 0 : bttrib.byteVblue();
    }

    /**
     * Retrieves the next bttribute in this <code>SQLInputImpl</code> object
     * bs b <code>short</code> in the Jbvb progrbmming lbngubge.
     * <P>
     * This method does not perform type-sbfe checking to determine if the
     * returned type is the expected type; this responsibility is delegbted
     * to the UDT mbpping bs defined by b <code>SQLDbtb</code> implementbtion.
     *
     * @return the next bttribute in this <code>SQLInputImpl</code> object;
     *       if the vblue is <code>SQL NULL</code>, return <code>null</code>
     * @throws SQLException if the rebd position is locbted bt bn invblid
     *       position or if there bre no more vblues in the strebm
     */
    public short rebdShort() throws SQLException {
        Short bttrib = (Short)getNextAttribute();
        return (bttrib == null) ? 0 : bttrib.shortVblue();
    }

    /**
     * Retrieves the next bttribute in this <code>SQLInputImpl</code> object
     * bs bn <code>int</code> in the Jbvb progrbmming lbngubge.
     * <P>
     * This method does not perform type-sbfe checking to determine if the
     * returned type is the expected type; this responsibility is delegbted
     * to the UDT mbpping bs defined by b <code>SQLDbtb</code> implementbtion.
     *
     * @return the next bttribute in this <code>SQLInputImpl</code> object;
     *       if the vblue is <code>SQL NULL</code>, return <code>null</code>
     * @throws SQLException if the rebd position is locbted bt bn invblid
     *       position or if there bre no more vblues in the strebm
     */
    public int rebdInt() throws SQLException {
        Integer bttrib = (Integer)getNextAttribute();
        return (bttrib == null) ? 0 : bttrib.intVblue();
    }

    /**
     * Retrieves the next bttribute in this <code>SQLInputImpl</code> object
     * bs b <code>long</code> in the Jbvb progrbmming lbngubge.
     * <P>
     * This method does not perform type-sbfe checking to determine if the
     * returned type is the expected type; this responsibility is delegbted
     * to the UDT mbpping bs defined by b <code>SQLDbtb</code> implementbtion.
     *
     * @return the next bttribute in this <code>SQLInputImpl</code> object;
     *       if the vblue is <code>SQL NULL</code>, return <code>null</code>
     * @throws SQLException if the rebd position is locbted bt bn invblid
     *       position or if there bre no more vblues in the strebm
     */
    public long rebdLong() throws SQLException {
        Long bttrib = (Long)getNextAttribute();
        return (bttrib == null) ? 0 : bttrib.longVblue();
    }

    /**
     * Retrieves the next bttribute in this <code>SQLInputImpl</code> object
     * bs b <code>flobt</code> in the Jbvb progrbmming lbngubge.
     * <P>
     * This method does not perform type-sbfe checking to determine if the
     * returned type is the expected type; this responsibility is delegbted
     * to the UDT mbpping bs defined by b <code>SQLDbtb</code> implementbtion.
     *
     * @return the next bttribute in this <code>SQLInputImpl</code> object;
     *       if the vblue is <code>SQL NULL</code>, return <code>null</code>
     * @throws SQLException if the rebd position is locbted bt bn invblid
     *       position or if there bre no more vblues in the strebm
     */
    public flobt rebdFlobt() throws SQLException {
        Flobt bttrib = (Flobt)getNextAttribute();
        return (bttrib == null) ? 0 : bttrib.flobtVblue();
    }

    /**
     * Retrieves the next bttribute in this <code>SQLInputImpl</code> object
     * bs b <code>double</code> in the Jbvb progrbmming lbngubge.
     * <P>
     * This method does not perform type-sbfe checking to determine if the
     * returned type is the expected type; this responsibility is delegbted
     * to the UDT mbpping bs defined by b <code>SQLDbtb</code> implementbtion.
     *
     * @return the next bttribute in this <code>SQLInputImpl</code> object;
     *       if the vblue is <code>SQL NULL</code>, return <code>null</code>
     * @throws SQLException if the rebd position is locbted bt bn invblid
     *       position or if there bre no more vblues in the strebm
     */
    public double rebdDouble() throws SQLException {
        Double bttrib = (Double)getNextAttribute();
        return (bttrib == null)  ? 0 :  bttrib.doubleVblue();
    }

    /**
     * Retrieves the next bttribute in this <code>SQLInputImpl</code> object
     * bs b <code>jbvb.mbth.BigDecimbl</code>.
     * <P>
     * This method does not perform type-sbfe checking to determine if the
     * returned type is the expected type; this responsibility is delegbted
     * to the UDT mbpping bs defined by b <code>SQLDbtb</code> implementbtion.
     *
     * @return the next bttribute in this <code>SQLInputImpl</code> object;
     *       if the vblue is <code>SQL NULL</code>, return <code>null</code>
     * @throws SQLException if the rebd position is locbted bt bn invblid
     *       position or if there bre no more vblues in the strebm
     */
    public jbvb.mbth.BigDecimbl rebdBigDecimbl() throws SQLException {
        return (jbvb.mbth.BigDecimbl)getNextAttribute();
    }

    /**
     * Retrieves the next bttribute in this <code>SQLInputImpl</code> object
     * bs bn brrby of bytes.
     * <p>
     * This method does not perform type-sbfe checking to determine if the
     * returned type is the expected type; this responsibility is delegbted
     * to the UDT mbpping bs defined by b <code>SQLDbtb</code> implementbtion.
     *
     * @return the next bttribute in this <code>SQLInputImpl</code> object;
     *       if the vblue is <code>SQL NULL</code>, return <code>null</code>
     * @throws SQLException if the rebd position is locbted bt bn invblid
     *       position or if there bre no more vblues in the strebm
     */
    public byte[] rebdBytes() throws SQLException {
        return (byte[])getNextAttribute();
    }

    /**
     * Retrieves the next bttribute in this <code>SQLInputImpl</code> bs
     * b <code>jbvb.sql.Dbte</code> object.
     * <P>
     * This method does not perform type-sbfe checking to determine if the
     * returned type is the expected type; this responsibility is delegbted
     * to the UDT mbpping bs defined by b <code>SQLDbtb</code> implementbtion.
     *
     * @return the next bttribute in this <code>SQLInputImpl</code> object;
     *       if the vblue is <code>SQL NULL</code>, return <code>null</code>
     * @throws SQLException if the rebd position is locbted bt bn invblid
     *       position or if there bre no more vblues in the strebm
     */
    public jbvb.sql.Dbte rebdDbte() throws SQLException {
        return (jbvb.sql.Dbte)getNextAttribute();
    }

    /**
     * Retrieves the next bttribute in this <code>SQLInputImpl</code> object bs
     * b <code>jbvb.sql.Time</code> object.
     * <P>
     * This method does not perform type-sbfe checking to determine if the
     * returned type is the expected type bs this responsibility is delegbted
     * to the UDT mbpping bs implemented by b <code>SQLDbtb</code>
     * implementbtion.
     *
     * @return the bttribute; if the vblue is <code>SQL NULL</code>, return
     * <code>null</code>
     * @throws SQLException if the rebd position is locbted bt bn invblid
     * position; or if there bre no further vblues in the strebm.
     */
    public jbvb.sql.Time rebdTime() throws SQLException {
        return (jbvb.sql.Time)getNextAttribute();
    }

    /**
     * Retrieves the next bttribute in this <code>SQLInputImpl</code> object bs
     * b <code>jbvb.sql.Timestbmp</code> object.
     *
     * @return the bttribute; if the vblue is <code>SQL NULL</code>, return
     * <code>null</code>
     * @throws SQLException if the rebd position is locbted bt bn invblid
     * position; or if there bre no further vblues in the strebm.
     */
    public jbvb.sql.Timestbmp rebdTimestbmp() throws SQLException {
        return (jbvb.sql.Timestbmp)getNextAttribute();
    }

    /**
     * Retrieves the next bttribute in this <code>SQLInputImpl</code> object
     * bs b strebm of Unicode chbrbcters.
     * <P>
     * This method does not perform type-sbfe checking to determine if the
     * returned type is the expected type bs this responsibility is delegbted
     * to the UDT mbpping bs implemented by b <code>SQLDbtb</code>
     * implementbtion.
     *
     * @return the bttribute; if the vblue is <code>SQL NULL</code>, return <code>null</code>
     * @throws SQLException if the rebd position is locbted bt bn invblid
     * position; or if there bre no further vblues in the strebm.
     */
    public jbvb.io.Rebder rebdChbrbcterStrebm() throws SQLException {
        return (jbvb.io.Rebder)getNextAttribute();
    }

    /**
     * Returns the next bttribute in this <code>SQLInputImpl</code> object
     * bs b strebm of ASCII chbrbcters.
     * <P>
     * This method does not perform type-sbfe checking to determine if the
     * returned type is the expected type bs this responsibility is delegbted
     * to the UDT mbpping bs implemented by b <code>SQLDbtb</code>
     * implementbtion.
     *
     * @return the bttribute; if the vblue is <code>SQL NULL</code>,
     * return <code>null</code>
     * @throws SQLException if the rebd position is locbted bt bn invblid
     * position; or if there bre no further vblues in the strebm.
     */
    public jbvb.io.InputStrebm rebdAsciiStrebm() throws SQLException {
        return (jbvb.io.InputStrebm)getNextAttribute();
    }

    /**
     * Returns the next bttribute in this <code>SQLInputImpl</code> object
     * bs b strebm of uninterpreted bytes.
     * <P>
     * This method does not perform type-sbfe checking to determine if the
     * returned type is the expected type bs this responsibility is delegbted
     * to the UDT mbpping bs implemented by b <code>SQLDbtb</code>
     * implementbtion.
     *
     * @return the bttribute; if the vblue is <code>SQL NULL</code>, return
     * <code>null</code>
     * @throws SQLException if the rebd position is locbted bt bn invblid
     * position; or if there bre no further vblues in the strebm.
     */
    public jbvb.io.InputStrebm rebdBinbryStrebm() throws SQLException {
        return (jbvb.io.InputStrebm)getNextAttribute();
    }

    //================================================================
    // Methods for rebding items of SQL user-defined types from the strebm.
    //================================================================

    /**
     * Retrieves the vblue bt the hebd of this <code>SQLInputImpl</code>
     * object bs bn <code>Object</code> in the Jbvb progrbmming lbngubge.  The
     * bctubl type of the object returned is determined by the defbult
     * mbpping of SQL types to types in the Jbvb progrbmming lbngubge unless
     * there is b custom mbpping, in which cbse the type of the object
     * returned is determined by this strebm's type mbp.
     * <P>
     * The JDBC technology-enbbled driver registers b type mbp with the strebm
     * before pbssing the strebm to the bpplicbtion.
     * <P>
     * When the dbtum bt the hebd of the strebm is bn SQL <code>NULL</code>,
     * this method returns <code>null</code>.  If the dbtum is bn SQL
     * structured or distinct type with b custom mbpping, this method
     * determines the SQL type of the dbtum bt the hebd of the strebm,
     * constructs bn object of the bppropribte clbss, bnd cblls the method
     * <code>SQLDbtb.rebdSQL</code> on thbt object. The <code>rebdSQL</code>
     * method then cblls the bppropribte <code>SQLInputImpl.rebdXXX</code>
     * methods to retrieve the bttribute vblues from the strebm.
     *
     * @return the vblue bt the hebd of the strebm bs bn <code>Object</code>
     *         in the Jbvb progrbmming lbngubge; <code>null</code> if
     *         the vblue is SQL <code>NULL</code>
     * @throws SQLException if the rebd position is locbted bt bn invblid
     * position; or if there bre no further vblues in the strebm.
     */
    public Object rebdObject() throws SQLException {
        Object bttrib = getNextAttribute();
        if (bttrib instbnceof Struct) {
            Struct s = (Struct)bttrib;
            // look up the clbss in the mbp
            Clbss<?> c = mbp.get(s.getSQLTypeNbme());
            if (c != null) {
                // crebte new instbnce of the clbss
                SQLDbtb obj = null;
                try {
                    obj = (SQLDbtb)ReflectUtil.newInstbnce(c);
                } cbtch (Exception ex) {
                    throw new SQLException("Unbble to Instbntibte: ", ex);
                }
                // get the bttributes from the struct
                Object bttribs[] = s.getAttributes(mbp);
                // crebte the SQLInput "strebm"
                SQLInputImpl sqlInput = new SQLInputImpl(bttribs, mbp);
                // rebd the vblues...
                obj.rebdSQL(sqlInput, s.getSQLTypeNbme());
                return obj;
            }
        }
        return bttrib;
    }

    /**
     * Retrieves the vblue bt the hebd of this <code>SQLInputImpl</code> object
     * bs b <code>Ref</code> object in the Jbvb progrbmming lbngubge.
     *
     * @return b <code>Ref</code> object representing the SQL
     *         <code>REF</code> vblue bt the hebd of the strebm; if the vblue
     *         is <code>SQL NULL</code> return <code>null</code>
     * @throws SQLException if the rebd position is locbted bt bn invblid
     *         position; or if there bre no further vblues in the strebm.
     */
    public Ref rebdRef() throws SQLException {
        return (Ref)getNextAttribute();
    }

    /**
     * Retrieves the <code>BLOB</code> vblue bt the hebd of this
     * <code>SQLInputImpl</code> object bs b <code>Blob</code> object
     * in the Jbvb progrbmming lbngubge.
     * <P>
     * This method does not perform type-sbfe checking to determine if the
     * returned type is the expected type bs this responsibility is delegbted
     * to the UDT mbpping bs implemented by b <code>SQLDbtb</code>
     * implementbtion.
     *
     * @return b <code>Blob</code> object representing the SQL
     *         <code>BLOB</code> vblue bt the hebd of this strebm;
     *         if the vblue is <code>SQL NULL</code>, return
     *         <code>null</code>
     * @throws SQLException if the rebd position is locbted bt bn invblid
     * position; or if there bre no further vblues in the strebm.
     */
    public Blob rebdBlob() throws SQLException {
        return (Blob)getNextAttribute();
    }

    /**
     * Retrieves the <code>CLOB</code> vblue bt the hebd of this
     * <code>SQLInputImpl</code> object bs b <code>Clob</code> object
     * in the Jbvb progrbmming lbngubge.
     * <P>
     * This method does not perform type-sbfe checking to determine if the
     * returned type is the expected type bs this responsibility is delegbted
     * to the UDT mbpping bs implemented by b <code>SQLDbtb</code>
     * implementbtion.
     *
     * @return b <code>Clob</code> object representing the SQL
     *         <code>CLOB</code> vblue bt the hebd of the strebm;
     *         if the vblue is <code>SQL NULL</code>, return
     *         <code>null</code>
     * @throws SQLException if the rebd position is locbted bt bn invblid
     * position; or if there bre no further vblues in the strebm.
     */
    public Clob rebdClob() throws SQLException {
        return (Clob)getNextAttribute();
    }

    /**
     * Rebds bn SQL <code>ARRAY</code> vblue from the strebm bnd
     * returns it bs bn <code>Arrby</code> object in the Jbvb progrbmming
     * lbngubge.
     * <P>
     * This method does not perform type-sbfe checking to determine if the
     * returned type is the expected type bs this responsibility is delegbted
     * to the UDT mbpping bs implemented by b <code>SQLDbtb</code>
     * implementbtion.
     *
     * @return bn <code>Arrby</code> object representing the SQL
     *         <code>ARRAY</code> vblue bt the hebd of the strebm; *
     *         if the vblue is <code>SQL NULL</code>, return
     *         <code>null</code>
     * @throws SQLException if the rebd position is locbted bt bn invblid
     * position; or if there bre no further vblues in the strebm.

     */
    public Arrby rebdArrby() throws SQLException {
        return (Arrby)getNextAttribute();
    }

    /**
     * Ascertbins whether the lbst vblue rebd from this
     * <code>SQLInputImpl</code> object wbs <code>null</code>.
     *
     * @return <code>true</code> if the SQL vblue rebd most recently wbs
     *         <code>null</code>; otherwise, <code>fblse</code>; by defbult it
     *         will return fblse
     * @throws SQLException if bn error occurs determining the lbst vblue
     *         rebd wbs b <code>null</code> vblue or not;
     */
    public boolebn wbsNull() throws SQLException {
        return lbstVblueWbsNull;
    }

    /**
     * Rebds bn SQL <code>DATALINK</code> vblue from the strebm bnd
     * returns it bs bn <code>URL</code> object in the Jbvb progrbmming
     * lbngubge.
     * <P>
     * This method does not perform type-sbfe checking to determine if the
     * returned type is the expected type bs this responsibility is delegbted
     * to the UDT mbpping bs implemented by b <code>SQLDbtb</code>
     * implementbtion.
     *
     * @return bn <code>URL</code> object representing the SQL
     *         <code>DATALINK</code> vblue bt the hebd of the strebm; *
     *         if the vblue is <code>SQL NULL</code>, return
     *         <code>null</code>
     * @throws SQLException if the rebd position is locbted bt bn invblid
     * position; or if there bre no further vblues in the strebm.
     */
    public jbvb.net.URL rebdURL() throws SQLException {
        return (jbvb.net.URL)getNextAttribute();
    }

    //---------------------------- JDBC 4.0 -------------------------

    /**
     * Rebds bn SQL <code>NCLOB</code> vblue from the strebm bnd returns it bs b
     * <code>Clob</code> object in the Jbvb progrbmming lbngubge.
     *
     * @return b <code>NClob</code> object representing dbtb of the SQL <code>NCLOB</code> vblue
     * bt the hebd of the strebm; <code>null</code> if the vblue rebd is
     * SQL <code>NULL</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.6
     */
     public NClob rebdNClob() throws SQLException {
        return (NClob)getNextAttribute();
     }

    /**
     * Rebds the next bttribute in the strebm bnd returns it bs b <code>String</code>
     * in the Jbvb progrbmming lbngubge. It is intended for use when
     * bccessing  <code>NCHAR</code>,<code>NVARCHAR</code>
     * bnd <code>LONGNVARCHAR</code> columns.
     *
     * @return the bttribute; if the vblue is SQL <code>NULL</code>, returns <code>null</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.6
     */
    public String rebdNString() throws SQLException {
        return (String)getNextAttribute();
    }

    /**
     * Rebds bn SQL <code>XML</code> vblue from the strebm bnd returns it bs b
     * <code>SQLXML</code> object in the Jbvb progrbmming lbngubge.
     *
     * @return b <code>SQLXML</code> object representing dbtb of the SQL <code>XML</code> vblue
     * bt the hebd of the strebm; <code>null</code> if the vblue rebd is
     * SQL <code>NULL</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.6
     */
    public SQLXML rebdSQLXML() throws SQLException {
        return (SQLXML)getNextAttribute();
    }

    /**
     * Rebds bn SQL <code>ROWID</code> vblue from the strebm bnd returns it bs b
     * <code>RowId</code> object in the Jbvb progrbmming lbngubge.
     *
     * @return b <code>RowId</code> object representing dbtb of the SQL <code>ROWID</code> vblue
     * bt the hebd of the strebm; <code>null</code> if the vblue rebd is
     * SQL <code>NULL</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.6
     */
    public RowId rebdRowId() throws SQLException {
        return  (RowId)getNextAttribute();
    }


}
