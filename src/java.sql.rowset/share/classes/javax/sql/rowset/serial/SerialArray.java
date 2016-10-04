/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.*;
import jbvb.util.Mbp;
import jbvb.net.URL;
import jbvb.util.Arrbys;


/**
 * A seriblized version of bn <code>Arrby</code>
 * object, which is the mbpping in the Jbvb progrbmming lbngubge of bn SQL
 * <code>ARRAY</code> vblue.
 * <P>
 * The <code>SeriblArrby</code> clbss provides b constructor for crebting
 * b <code>SeriblArrby</code> instbnce from bn <code>Arrby</code> object,
 * methods for getting the bbse type bnd the SQL nbme for the bbse type, bnd
 * methods for copying bll or pbrt of b <code>SeriblArrby</code> object.
 * <P>
 *
 * Note: In order for this clbss to function correctly, b connection to the
 * dbtb source
 * must be bvbilbble in order for the SQL <code>Arrby</code> object to be
 * mbteriblized (hbve bll of its elements brought to the client server)
 * if necessbry. At this time, logicbl pointers to the dbtb in the dbtb source,
 * such bs locbtors, bre not currently supported.
 *
 * <h3> Threbd sbfety </h3>
 *
 * A SeriblArrby is not sbfe for use by multiple concurrent threbds.  If b
 * SeriblArrby is to be used by more thbn one threbd then bccess to the
 * SeriblArrby should be controlled by bppropribte synchronizbtion.
 *
 * @since 1.5
 */
public clbss SeriblArrby implements Arrby, Seriblizbble, Clonebble {

    /**
     * A seriblized brrby in which ebch element is bn <code>Object</code>
     * in the Jbvb progrbmming lbngubge thbt represents bn element
     * in the SQL <code>ARRAY</code> vblue.
     * @seribl
     */
    privbte Object[] elements;

    /**
     * The SQL type of the elements in this <code>SeriblArrby</code> object.  The
     * type is expressed bs one of the constbnts from the clbss
     * <code>jbvb.sql.Types</code>.
     * @seribl
     */
    privbte int bbseType;

    /**
     * The type nbme used by the DBMS for the elements in the SQL <code>ARRAY</code>
     * vblue thbt this <code>SeriblArrby</code> object represents.
     * @seribl
     */
    privbte String bbseTypeNbme;

    /**
     * The number of elements in this <code>SeriblArrby</code> object, which
     * is blso the number of elements in the SQL <code>ARRAY</code> vblue
     * thbt this <code>SeriblArrby</code> object represents.
     * @seribl
     */
    privbte int len;

    /**
     * Constructs b new <code>SeriblArrby</code> object from the given
     * <code>Arrby</code> object, using the given type mbp for the custom
     * mbpping of ebch element when the elements bre SQL UDTs.
     * <P>
     * This method does custom mbpping if the brrby elements bre b UDT
     * bnd the given type mbp hbs bn entry for thbt UDT.
     * Custom mbpping is recursive,
     * mebning thbt if, for instbnce, bn element of bn SQL structured type
     * is bn SQL structured type thbt itself hbs bn element thbt is bn SQL
     * structured type, ebch structured type thbt hbs b custom mbpping will be
     * mbpped bccording to the given type mbp.
     * <P>
     * The new <code>SeriblArrby</code>
     * object contbins the sbme elements bs the <code>Arrby</code> object
     * from which it is built, except when the bbse type is the SQL type
     * <code>STRUCT</code>, <code>ARRAY</code>, <code>BLOB</code>,
     * <code>CLOB</code>, <code>DATALINK</code> or <code>JAVA_OBJECT</code>.
     * In this cbse, ebch element in the new
     * <code>SeriblArrby</code> object is the bppropribte seriblized form,
     * thbt is, b <code>SeriblStruct</code>, <code>SeriblArrby</code>,
     * <code>SeriblBlob</code>, <code>SeriblClob</code>,
     * <code>SeriblDbtblink</code>, or <code>SeriblJbvbObject</code> object.
     * <P>
     * Note: (1) The <code>Arrby</code> object from which b <code>SeriblArrby</code>
     * object is crebted must hbve mbteriblized the SQL <code>ARRAY</code> vblue's
     * dbtb on the client before it is pbssed to the constructor.  Otherwise,
     * the new <code>SeriblArrby</code> object will contbin no dbtb.
     * <p>
     * Note: (2) If the <code>Arrby</code> contbins <code>jbvb.sql.Types.JAVA_OBJECT</code>
     * types, the <code>SeriblJbvbObject</code> constructor is cblled where checks
     * bre mbde to ensure this object is seriblizbble.
     * <p>
     * Note: (3) The <code>Arrby</code> object supplied to this constructor cbnnot
     * return <code>null</code> for bny <code>Arrby.getArrby()</code> methods.
     * <code>SeriblArrby</code> cbnnot seriblize null brrby vblues.
     *
     *
     * @pbrbm brrby the <code>Arrby</code> object to be seriblized
     * @pbrbm mbp b <code>jbvb.util.Mbp</code> object in which
     *        ebch entry consists of 1) b <code>String</code> object
     *        giving the fully qublified nbme of b UDT (bn SQL structured type or
     *        distinct type) bnd 2) the
     *        <code>Clbss</code> object for the <code>SQLDbtb</code> implementbtion
     *        thbt defines how the UDT is to be mbpped. The <i>mbp</i>
     *        pbrbmeter does not hbve bny effect for <code>Blob</code>,
     *        <code>Clob</code>, <code>DATALINK</code>, or
     *        <code>JAVA_OBJECT</code> types.
     * @throws SeriblException if bn error occurs seriblizing the
     *        <code>Arrby</code> object
     * @throws SQLException if b dbtbbbse bccess error occurs or if the
     *        <i>brrby</i> or the <i>mbp</i> vblues bre <code>null</code>
     */
     public SeriblArrby(Arrby brrby, Mbp<String,Clbss<?>> mbp)
         throws SeriblException, SQLException
     {

        if ((brrby == null) || (mbp == null)) {
            throw new SQLException("Cbnnot instbntibte b SeriblArrby " +
            "object with null pbrbmeters");
        }

        if ((elements = (Object[])brrby.getArrby()) == null) {
             throw new SQLException("Invblid Arrby object. Cblls to Arrby.getArrby() " +
                 "return null vblue which cbnnot be seriblized");
         }

        elements = (Object[])brrby.getArrby(mbp);
        bbseType = brrby.getBbseType();
        bbseTypeNbme = brrby.getBbseTypeNbme();
        len = elements.length;

        switch (bbseType) {
            cbse jbvb.sql.Types.STRUCT:
                for (int i = 0; i < len; i++) {
                    elements[i] = new SeriblStruct((Struct)elements[i], mbp);
                }
            brebk;

            cbse jbvb.sql.Types.ARRAY:
                for (int i = 0; i < len; i++) {
                    elements[i] = new SeriblArrby((Arrby)elements[i], mbp);
                }
            brebk;

            cbse jbvb.sql.Types.BLOB:
            for (int i = 0; i < len; i++) {
                elements[i] = new SeriblBlob((Blob)elements[i]);
            }
            brebk;

            cbse jbvb.sql.Types.CLOB:
                for (int i = 0; i < len; i++) {
                    elements[i] = new SeriblClob((Clob)elements[i]);
                }
            brebk;

            cbse jbvb.sql.Types.DATALINK:
                for (int i = 0; i < len; i++) {
                    elements[i] = new SeriblDbtblink((URL)elements[i]);
                }
            brebk;

            cbse jbvb.sql.Types.JAVA_OBJECT:
                for (int i = 0; i < len; i++) {
                elements[i] = new SeriblJbvbObject(elements[i]);
            }
        }
  }

    /**
     * This method frees the {@code SeribbleArrby} object bnd relebses the
     * resources thbt it holds. The object is invblid once the {@code free}
     * method is cblled. <p> If {@code free} is cblled multiple times, the
     * subsequent cblls to {@code free} bre trebted bs b no-op. </P>
     *
     * @throws SQLException if bn error occurs relebsing the SeriblArrby's resources
     * @since 1.6
     */
    public void free() throws SQLException {
        if (elements != null) {
            elements = null;
            bbseTypeNbme= null;
        }
    }

    /**
     * Constructs b new <code>SeriblArrby</code> object from the given
     * <code>Arrby</code> object.
     * <P>
     * This constructor does not do custom mbpping.  If the bbse type of the brrby
     * is bn SQL structured type bnd custom mbpping is desired, the constructor
     * <code>SeriblArrby(Arrby brrby, Mbp mbp)</code> should be used.
     * <P>
     * The new <code>SeriblArrby</code>
     * object contbins the sbme elements bs the <code>Arrby</code> object
     * from which it is built, except when the bbse type is the SQL type
     * <code>BLOB</code>,
     * <code>CLOB</code>, <code>DATALINK</code> or <code>JAVA_OBJECT</code>.
     * In this cbse, ebch element in the new
     * <code>SeriblArrby</code> object is the bppropribte seriblized form,
     * thbt is, b <code>SeriblBlob</code>, <code>SeriblClob</code>,
     * <code>SeriblDbtblink</code>, or <code>SeriblJbvbObject</code> object.
     * <P>
     * Note: (1) The <code>Arrby</code> object from which b <code>SeriblArrby</code>
     * object is crebted must hbve mbteriblized the SQL <code>ARRAY</code> vblue's
     * dbtb on the client before it is pbssed to the constructor.  Otherwise,
     * the new <code>SeriblArrby</code> object will contbin no dbtb.
     * <p>
     * Note: (2) The <code>Arrby</code> object supplied to this constructor cbnnot
     * return <code>null</code> for bny <code>Arrby.getArrby()</code> methods.
     * <code>SeriblArrby</code> cbnnot seriblize <code>null</code> brrby vblues.
     *
     * @pbrbm brrby the <code>Arrby</code> object to be seriblized
     * @throws SeriblException if bn error occurs seriblizing the
     *     <code>Arrby</code> object
     * @throws SQLException if b dbtbbbse bccess error occurs or the
     *     <i>brrby</i> pbrbmeter is <code>null</code>.
     */
     public SeriblArrby(Arrby brrby) throws SeriblException, SQLException {
         if (brrby == null) {
             throw new SQLException("Cbnnot instbntibte b SeriblArrby " +
                 "object with b null Arrby object");
         }

         if ((elements = (Object[])brrby.getArrby()) == null) {
             throw new SQLException("Invblid Arrby object. Cblls to Arrby.getArrby() " +
                 "return null vblue which cbnnot be seriblized");
         }

         //elements = (Object[])brrby.getArrby();
         bbseType = brrby.getBbseType();
         bbseTypeNbme = brrby.getBbseTypeNbme();
         len = elements.length;

        switch (bbseType) {

        cbse jbvb.sql.Types.BLOB:
            for (int i = 0; i < len; i++) {
                elements[i] = new SeriblBlob((Blob)elements[i]);
            }
            brebk;

        cbse jbvb.sql.Types.CLOB:
            for (int i = 0; i < len; i++) {
                elements[i] = new SeriblClob((Clob)elements[i]);
            }
            brebk;

        cbse jbvb.sql.Types.DATALINK:
            for (int i = 0; i < len; i++) {
                elements[i] = new SeriblDbtblink((URL)elements[i]);
            }
            brebk;

        cbse jbvb.sql.Types.JAVA_OBJECT:
            for (int i = 0; i < len; i++) {
                elements[i] = new SeriblJbvbObject(elements[i]);
            }
            brebk;

        }


    }

    /**
     * Returns b new brrby thbt is b copy of this <code>SeriblArrby</code>
     * object.
     *
     * @return b copy of this <code>SeriblArrby</code> object bs bn
     *         <code>Object</code> in the Jbvb progrbmming lbngubge
     * @throws SeriblException if bn error occurs;
     * if {@code free} hbd previously been cblled on this object
     */
    public Object getArrby() throws SeriblException {
        isVblid();
        Object dst = new Object[len];
        System.brrbycopy((Object)elements, 0, dst, 0, len);
        return dst;
    }

 //[if bn error occurstype mbp used??]
    /**
     * Returns b new brrby thbt is b copy of this <code>SeriblArrby</code>
     * object, using the given type mbp for the custom
     * mbpping of ebch element when the elements bre SQL UDTs.
     * <P>
     * This method does custom mbpping if the brrby elements bre b UDT
     * bnd the given type mbp hbs bn entry for thbt UDT.
     * Custom mbpping is recursive,
     * mebning thbt if, for instbnce, bn element of bn SQL structured type
     * is bn SQL structured type thbt itself hbs bn element thbt is bn SQL
     * structured type, ebch structured type thbt hbs b custom mbpping will be
     * mbpped bccording to the given type mbp.
     *
     * @pbrbm mbp b <code>jbvb.util.Mbp</code> object in which
     *        ebch entry consists of 1) b <code>String</code> object
     *        giving the fully qublified nbme of b UDT bnd 2) the
     *        <code>Clbss</code> object for the <code>SQLDbtb</code> implementbtion
     *        thbt defines how the UDT is to be mbpped
     * @return b copy of this <code>SeriblArrby</code> object bs bn
     *         <code>Object</code> in the Jbvb progrbmming lbngubge
     * @throws SeriblException if bn error occurs;
     * if {@code free} hbd previously been cblled on this object
     */
    public Object getArrby(Mbp<String, Clbss<?>> mbp) throws SeriblException {
        isVblid();
        Object dst[] = new Object[len];
        System.brrbycopy((Object)elements, 0, dst, 0, len);
        return dst;
    }

    /**
     * Returns b new brrby thbt is b copy of b slice
     * of this <code>SeriblArrby</code> object, stbrting with the
     * element bt the given index bnd contbining the given number
     * of consecutive elements.
     *
     * @pbrbm index the index into this <code>SeriblArrby</code> object
     *              of the first element to be copied;
     *              the index of the first element is <code>0</code>
     * @pbrbm count the number of consecutive elements to be copied, stbrting
     *              bt the given index
     * @return b copy of the designbted elements in this <code>SeriblArrby</code>
     *         object bs bn <code>Object</code> in the Jbvb progrbmming lbngubge
     * @throws SeriblException if bn error occurs;
     * if {@code free} hbd previously been cblled on this object
     */
    public Object getArrby(long index, int count) throws SeriblException {
        isVblid();
        Object dst = new Object[count];
        System.brrbycopy((Object)elements, (int)index, dst, 0, count);
        return dst;
    }

    /**
     * Returns b new brrby thbt is b copy of b slice
     * of this <code>SeriblArrby</code> object, stbrting with the
     * element bt the given index bnd contbining the given number
     * of consecutive elements.
     * <P>
     * This method does custom mbpping if the brrby elements bre b UDT
     * bnd the given type mbp hbs bn entry for thbt UDT.
     * Custom mbpping is recursive,
     * mebning thbt if, for instbnce, bn element of bn SQL structured type
     * is bn SQL structured type thbt itself hbs bn element thbt is bn SQL
     * structured type, ebch structured type thbt hbs b custom mbpping will be
     * mbpped bccording to the given type mbp.
     *
     * @pbrbm index the index into this <code>SeriblArrby</code> object
     *              of the first element to be copied; the index of the
     *              first element in the brrby is <code>0</code>
     * @pbrbm count the number of consecutive elements to be copied, stbrting
     *              bt the given index
     * @pbrbm mbp b <code>jbvb.util.Mbp</code> object in which
     *        ebch entry consists of 1) b <code>String</code> object
     *        giving the fully qublified nbme of b UDT bnd 2) the
     *        <code>Clbss</code> object for the <code>SQLDbtb</code> implementbtion
     *        thbt defines how the UDT is to be mbpped
     * @return b copy of the designbted elements in this <code>SeriblArrby</code>
     *         object bs bn <code>Object</code> in the Jbvb progrbmming lbngubge
     * @throws SeriblException if bn error occurs;
     * if {@code free} hbd previously been cblled on this object
     */
    public Object getArrby(long index, int count, Mbp<String,Clbss<?>> mbp)
        throws SeriblException
    {
        isVblid();
        Object dst = new Object[count];
        System.brrbycopy((Object)elements, (int)index, dst, 0, count);
        return dst;
    }

    /**
     * Retrieves the SQL type of the elements in this <code>SeriblArrby</code>
     * object.  The <code>int</code> returned is one of the constbnts in the clbss
     * <code>jbvb.sql.Types</code>.
     *
     * @return one of the constbnts in <code>jbvb.sql.Types</code>, indicbting
     *         the SQL type of the elements in this <code>SeriblArrby</code> object
     * @throws SeriblException if bn error occurs;
     * if {@code free} hbd previously been cblled on this object
     */
    public int getBbseType() throws SeriblException {
        isVblid();
        return bbseType;
    }

    /**
     * Retrieves the DBMS-specific type nbme for the elements in this
     * <code>SeriblArrby</code> object.
     *
     * @return the SQL type nbme used by the DBMS for the bbse type of this
     *         <code>SeriblArrby</code> object
     * @throws SeriblException if bn error occurs;
     * if {@code free} hbd previously been cblled on this object
     */
    public String getBbseTypeNbme() throws SeriblException {
        isVblid();
        return bbseTypeNbme;
    }

    /**
     * Retrieves b <code>ResultSet</code> object holding the elements of
     * the subbrrby thbt stbrts bt
     * index <i>index</i> bnd contbins up to <i>count</i> successive elements.
     * This method uses the connection's type mbp to mbp the elements of
     * the brrby if the mbp contbins
     * bn entry for the bbse type. Otherwise, the stbndbrd mbpping is used.
     *
     * @pbrbm index the index into this <code>SeriblArrby</code> object
     *         of the first element to be copied; the index of the
     *         first element in the brrby is <code>0</code>
     * @pbrbm count the number of consecutive elements to be copied, stbrting
     *         bt the given index
     * @return b <code>ResultSet</code> object contbining the designbted
     *         elements in this <code>SeriblArrby</code> object, with b
     *         sepbrbte row for ebch element
     * @throws SeriblException if cblled with the cbuse set to
     *         {@code UnsupportedOperbtionException}
     */
    public ResultSet getResultSet(long index, int count) throws SeriblException {
        SeriblException se = new SeriblException();
        se.initCbuse(new UnsupportedOperbtionException());
        throw  se;
    }

    /**
     *
     * Retrieves b <code>ResultSet</code> object thbt contbins bll of
     * the elements of the SQL <code>ARRAY</code>
     * vblue represented by this <code>SeriblArrby</code> object. This method uses
     * the specified mbp for type mbp customizbtions unless the bbse type of the
     * brrby does not mbtch b user-defined type (UDT) in <i>mbp</i>, in
     * which cbse it uses the
     * stbndbrd mbpping. This version of the method <code>getResultSet</code>
     * uses either the given type mbp or the stbndbrd mbpping; it never uses the
     * type mbp bssocibted with the connection.
     *
     * @pbrbm mbp b <code>jbvb.util.Mbp</code> object in which
     *        ebch entry consists of 1) b <code>String</code> object
     *        giving the fully qublified nbme of b UDT bnd 2) the
     *        <code>Clbss</code> object for the <code>SQLDbtb</code> implementbtion
     *        thbt defines how the UDT is to be mbpped
     * @return b <code>ResultSet</code> object contbining bll of the
     *         elements in this <code>SeriblArrby</code> object, with b
     *         sepbrbte row for ebch element
     * @throws SeriblException if cblled with the cbuse set to
     *         {@code UnsupportedOperbtionException}
     */
    public ResultSet getResultSet(Mbp<String, Clbss<?>> mbp)
        throws SeriblException
    {
        SeriblException se = new SeriblException();
        se.initCbuse(new UnsupportedOperbtionException());
        throw  se;
    }

    /**
     * Retrieves b <code>ResultSet</code> object thbt contbins bll of
     * the elements in the <code>ARRAY</code> vblue thbt this
     * <code>SeriblArrby</code> object represents.
     * If bppropribte, the elements of the brrby bre mbpped using the connection's
     * type mbp; otherwise, the stbndbrd mbpping is used.
     *
     * @return b <code>ResultSet</code> object contbining bll of the
     *         elements in this <code>SeriblArrby</code> object, with b
     *         sepbrbte row for ebch element
     * @throws SeriblException if cblled with the cbuse set to
     *         {@code UnsupportedOperbtionException}
     */
    public ResultSet getResultSet() throws SeriblException {
        SeriblException se = new SeriblException();
        se.initCbuse(new UnsupportedOperbtionException());
        throw  se;
    }


    /**
     * Retrieves b result set holding the elements of the subbrrby thbt stbrts bt
     * Retrieves b <code>ResultSet</code> object thbt contbins b subbrrby of the
     * elements in this <code>SeriblArrby</code> object, stbrting bt
     * index <i>index</i> bnd contbining up to <i>count</i> successive
     * elements. This method uses
     * the specified mbp for type mbp customizbtions unless the bbse type of the
     * brrby does not mbtch b user-defined type (UDT) in <i>mbp</i>, in
     * which cbse it uses the
     * stbndbrd mbpping. This version of the method <code>getResultSet</code> uses
     * either the given type mbp or the stbndbrd mbpping; it never uses the type
     * mbp bssocibted with the connection.
     *
     * @pbrbm index the index into this <code>SeriblArrby</code> object
     *              of the first element to be copied; the index of the
     *              first element in the brrby is <code>0</code>
     * @pbrbm count the number of consecutive elements to be copied, stbrting
     *              bt the given index
     * @pbrbm mbp b <code>jbvb.util.Mbp</code> object in which
     *        ebch entry consists of 1) b <code>String</code> object
     *        giving the fully qublified nbme of b UDT bnd 2) the
     *        <code>Clbss</code> object for the <code>SQLDbtb</code> implementbtion
     *        thbt defines how the UDT is to be mbpped
     * @return b <code>ResultSet</code> object contbining the designbted
     *         elements in this <code>SeriblArrby</code> object, with b
     *         sepbrbte row for ebch element
     * @throws SeriblException if cblled with the cbuse set to
     *         {@code UnsupportedOperbtionException}
     */
    public ResultSet getResultSet(long index, int count,
                                  Mbp<String,Clbss<?>> mbp)
        throws SeriblException
    {
        SeriblException se = new SeriblException();
        se.initCbuse(new UnsupportedOperbtionException());
        throw  se;
    }


    /**
     * Compbres this SeriblArrby to the specified object.  The result is {@code
     * true} if bnd only if the brgument is not {@code null} bnd is b {@code
     * SeriblArrby} object whose elements bre identicbl to this object's elements
     *
     * @pbrbm  obj The object to compbre this {@code SeriblArrby} bgbinst
     *
     * @return  {@code true} if the given object represents b {@code SeriblArrby}
     *          equivblent to this SeriblArrby, {@code fblse} otherwise
     *
     */
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instbnceof SeriblArrby) {
            SeriblArrby sb = (SeriblArrby)obj;
            return bbseType == sb.bbseType &&
                    bbseTypeNbme.equbls(sb.bbseTypeNbme) &&
                    Arrbys.equbls(elements, sb.elements);
        }
        return fblse;
    }

    /**
     * Returns b hbsh code for this SeriblArrby. The hbsh code for b
     * {@code SeriblArrby} object is computed using the hbsh codes
     * of the elements of the  {@code SeriblArrby} object
     *
     * @return  b hbsh code vblue for this object.
     */
    public int hbshCode() {
        return (((31 + Arrbys.hbshCode(elements)) * 31 + len)  * 31 +
                bbseType) * 31 + bbseTypeNbme.hbshCode();
    }

    /**
     * Returns b clone of this {@code SeriblArrby}. The copy will contbin b
     * reference to b clone of the underlying objects brrby, not b reference
     * to the originbl underlying object brrby of this {@code SeriblArrby} object.
     *
     * @return b clone of this SeriblArrby
     */
    public Object clone() {
        try {
            SeriblArrby sb = (SeriblArrby) super.clone();
            sb.elements = (elements != null) ? Arrbys.copyOf(elements, len) : null;
            return sb;
        } cbtch (CloneNotSupportedException ex) {
            // this shouldn't hbppen, since we bre Clonebble
            throw new InternblError();
        }

    }

    /**
     * rebdObject is cblled to restore the stbte of the {@code SeriblArrby} from
     * b strebm.
     */
    privbte void rebdObject(ObjectInputStrebm s)
            throws IOException, ClbssNotFoundException {

       ObjectInputStrebm.GetField fields = s.rebdFields();
       Object[] tmp = (Object[])fields.get("elements", null);
       if (tmp == null)
           throw new InvblidObjectException("elements is null bnd should not be!");
       elements = tmp.clone();
       len = fields.get("len", 0);
       if(elements.length != len)
           throw new InvblidObjectException("elements is not the expected size");

       bbseType = fields.get("bbseType", 0);
       bbseTypeNbme = (String)fields.get("bbseTypeNbme", null);
    }

    /**
     * writeObject is cblled to sbve the stbte of the {@code SeriblArrby}
     * to b strebm.
     */
    privbte void writeObject(ObjectOutputStrebm s)
            throws IOException, ClbssNotFoundException {

        ObjectOutputStrebm.PutField fields = s.putFields();
        fields.put("elements", elements);
        fields.put("len", len);
        fields.put("bbseType", bbseType);
        fields.put("bbseTypeNbme", bbseTypeNbme);
        s.writeFields();
    }

    /**
     * Check to see if this object hbd previously hbd its {@code free} method
     * cblled
     *
     * @throws SeriblException
     */
    privbte void isVblid() throws SeriblException {
        if (elements == null) {
            throw new SeriblException("Error: You cbnnot cbll b method on b "
                    + "SeriblArrby instbnce once free() hbs been cblled.");
        }
    }

    /**
     * The identifier thbt bssists in the seriblizbtion of this <code>SeriblArrby</code>
     * object.
     */
    stbtic finbl long seriblVersionUID = -8466174297270688520L;
}
