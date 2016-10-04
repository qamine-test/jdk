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

pbckbge com.sun.rowset.internbl;

import jbvb.util.*;

import org.xml.sbx.*;
import org.xml.sbx.helpers.*;

import jbvb.sql.*;
import jbvbx.sql.*;

import jbvbx.sql.rowset.*;
import com.sun.rowset.*;
import jbvb.io.IOException;
import jbvb.text.MessbgeFormbt;

/**
 * The document hbndler thbt receives pbrse events thbt bn XML pbrser sends while it
 * is pbrsing bn XML document representing b <code>WebRowSet</code> object. The
 * pbrser sends strings to this <code>XmlRebderContentHbndler</code> bnd then uses
 * these strings bs brguments for the <code>XmlRebderContentHbndler</code> methods
 * it invokes. The finbl gobl of the SAX pbrser working with bn
 * <code>XmlRebderContentHbndler</code> object is to rebd bn XML document thbt represents
 * b <code>RowSet</code> object.
 * <P>
 * A rowset consists of its properties, metbdbtb, bnd dbtb vblues. An XML document
 * representbting b rowset includes the vblues in these three cbtegories blong with
 * bppropribte XML tbgs to identify them.  It blso includes b top-level XML tbg for
 * the rowset bnd three section tbgs identifying the three cbtegories of vblues.
 * <P>
 * The tbgs in bn XML document bre hierbrchicbl.
 * This mebns thbt the top-level tbg, <code>RowSet</code>, is
 * followed by the three sections with bppropribte tbgs, which bre in turn ebch
 * followed by their constituent elements. For exbmple, the <code>properties</code>
 * element will be followed by bn element for ebch of the properties listed in
 * in this <code>XmlRebderContentHbndler</code> object's <code>properties</code>
 * field.  The content of the other two fields, <code>colDef</code>, which lists
 * the rowset's metbdbtb elements, bnd <code>dbtb</code>, which lists the rowset's dbtb
 * elements, bre hbndled similbrly .
 * <P>
 * This implementbtion of <code>XmlRebderContentHbndler</code> provides the mebns for the
 * pbrser to determine which elements need to hbve b vblue set bnd then to set
 * those vblues. The methods in this clbss bre bll cblled by the pbrser; bn
 * bpplicbtion progrbmmer never cblls them directly.
 *
 */

public clbss XmlRebderContentHbndler extends DefbultHbndler {

    privbte HbshMbp <String, Integer> propMbp;
    privbte HbshMbp <String, Integer> colDefMbp;
    privbte HbshMbp <String, Integer> dbtbMbp;

    privbte HbshMbp<String,Clbss<?>> typeMbp;

    privbte Vector<Object[]> updbtes;
    privbte Vector<String> keyCols;

    privbte String columnVblue;
    privbte String propertyVblue;
    privbte String metbDbtbVblue;

    privbte int tbg;
    privbte int stbte;

    privbte WebRowSetImpl rs;
    privbte boolebn nullVbl;
    privbte boolebn emptyStringVbl;
    privbte RowSetMetbDbtb md;
    privbte int idx;
    privbte String lbstvbl;
    privbte String Key_mbp;
    privbte String Vblue_mbp;
    privbte String tempStr;
    privbte String tempUpdbte;
    privbte String tempCommbnd;
    privbte Object [] upd;

    /**
     * A list of the properties for b rowset. There is b constbnt defined to
     * correspond to ebch of these properties so thbt b <code>HbshMbp</code>
     * object cbn be crebted to mbp the properties, which bre strings, to
     * the constbnts, which bre integers.
     */
    privbte String [] properties = {"commbnd", "concurrency", "dbtbsource",
                            "escbpe-processing", "fetch-direction", "fetch-size",
                            "isolbtion-level", "key-columns", "mbp",
                            "mbx-field-size", "mbx-rows", "query-timeout",
                            "rebd-only", "rowset-type", "show-deleted",
                            "tbble-nbme", "url", "null", "column", "type",
                            "clbss", "sync-provider", "sync-provider-nbme",
                             "sync-provider-vendor", "sync-provider-version",
                             "sync-provider-grbde","dbtb-source-lock"};

    /**
     * A constbnt representing the tbg for the commbnd property.
     */
    privbte finbl stbtic int CommbndTbg = 0;

    /**
     * A constbnt representing the tbg for the concurrency property.
     */
    privbte finbl stbtic int ConcurrencyTbg = 1;

    /**
     * A constbnt representing the tbg for the dbtbsource property.
     */
    privbte finbl stbtic int DbtbsourceTbg = 2;

    /**
     * A constbnt representing the tbg for the escbpe-processing property.
     */
    privbte finbl stbtic int EscbpeProcessingTbg = 3;

    /**
     * A constbnt representing the tbg for the fetch-direction property.
     */
    privbte finbl stbtic int FetchDirectionTbg = 4;

    /**
     * A constbnt representing the tbg for the fetch-size property.
     */
    privbte finbl stbtic int FetchSizeTbg = 5;

    /**
     * A constbnt representing the tbg for the isolbtion-level property
     */
    privbte finbl stbtic int IsolbtionLevelTbg = 6;

    /**
     * A constbnt representing the tbg for the key-columns property.
     */
    privbte finbl stbtic int KeycolsTbg = 7;

    /**
     * A constbnt representing the tbg for the mbp property.
     * This mbp is the type mbp thbt specifies the custom mbpping
     * for bn SQL user-defined type.
     */
    privbte finbl stbtic int MbpTbg = 8;

    /**
     * A constbnt representing the tbg for the mbx-field-size property.
     */
    privbte finbl stbtic int MbxFieldSizeTbg = 9;

    /**
     * A constbnt representing the tbg for the mbx-rows property.
     */
    privbte finbl stbtic int MbxRowsTbg = 10;

    /**
     * A constbnt representing the tbg for the query-timeout property.
     */
    privbte finbl stbtic int QueryTimeoutTbg = 11;

    /**
     * A constbnt representing the tbg for the rebd-only property.
     */
    privbte finbl stbtic int RebdOnlyTbg = 12;

    /**
     * A constbnt representing the tbg for the rowset-type property.
     */
    privbte finbl stbtic int RowsetTypeTbg = 13;

    /**
     * A constbnt representing the tbg for the show-deleted property.
     */
    privbte finbl stbtic int ShowDeletedTbg = 14;

    /**
     * A constbnt representing the tbg for the tbble-nbme property.
     */
    privbte finbl stbtic int TbbleNbmeTbg = 15;

    /**
     * A constbnt representing the tbg for the URL property.
     */
    privbte finbl stbtic int UrlTbg = 16;

    /**
     * A constbnt representing the tbg for the null property.
     */
    privbte finbl stbtic int PropNullTbg = 17;

    /**
     * A constbnt representing the tbg for the column property.
     */
    privbte finbl stbtic int PropColumnTbg = 18;

    /**
     * A constbnt representing the tbg for the type property.
     */
    privbte finbl stbtic int PropTypeTbg = 19;

    /**
     * A constbnt representing the tbg for the clbss property.
     */
    privbte finbl stbtic int PropClbssTbg = 20;

    /**
     * A constbnt representing the tbg for the sync-provider.
     */
    privbte finbl stbtic int SyncProviderTbg = 21;

    /**
     * A constbnt representing the tbg for the sync-provider
     * nbme
     */
    privbte finbl stbtic int SyncProviderNbmeTbg = 22;

    /**
     * A constbnt representing the tbg for the sync-provider
     * vendor tbg.
     */
    privbte finbl stbtic int SyncProviderVendorTbg = 23;

    /**
     * A constbnt representing the tbg for the sync-provider
     * version tbg.
     */
    privbte finbl stbtic int SyncProviderVersionTbg = 24;

    /**
     * A constbnt representing the tbg for the sync-provider
     * grbde tbg.
     */
    privbte finbl stbtic int SyncProviderGrbdeTbg = 25;

    /**
     * A constbnt representing the tbg for the dbtb source lock.
     */
    privbte finbl stbtic int DbtbSourceLock = 26;

    /**
     * A listing of the kinds of metbdbtb informbtion bvbilbble bbout
     * the columns in b <code>WebRowSet</code> object.
     */
    privbte String [] colDef = {"column-count", "column-definition", "column-index",
                        "buto-increment", "cbse-sensitive", "currency",
                        "nullbble", "signed", "sebrchbble",
                        "column-displby-size", "column-lbbel", "column-nbme",
                        "schemb-nbme", "column-precision", "column-scble",
                        "tbble-nbme", "cbtblog-nbme", "column-type",
                        "column-type-nbme", "null"};


    /**
     * A constbnt representing the tbg for column-count.
     */
    privbte finbl stbtic int ColumnCountTbg = 0;

    /**
     * A constbnt representing the tbg for column-definition.
     */
    privbte finbl stbtic int ColumnDefinitionTbg = 1;

    /**
     * A constbnt representing the tbg for column-index.
     */
    privbte finbl stbtic int ColumnIndexTbg = 2;

    /**
     * A constbnt representing the tbg for buto-increment.
     */
    privbte finbl stbtic int AutoIncrementTbg = 3;

    /**
     * A constbnt representing the tbg for cbse-sensitive.
     */
    privbte finbl stbtic int CbseSensitiveTbg = 4;

    /**
     * A constbnt representing the tbg for currency.
     */
    privbte finbl stbtic int CurrencyTbg = 5;

    /**
     * A constbnt representing the tbg for nullbble.
     */
    privbte finbl stbtic int NullbbleTbg = 6;

    /**
     * A constbnt representing the tbg for signed.
     */
    privbte finbl stbtic int SignedTbg = 7;

    /**
     * A constbnt representing the tbg for sebrchbble.
     */
    privbte finbl stbtic int SebrchbbleTbg = 8;

    /**
     * A constbnt representing the tbg for column-displby-size.
     */
    privbte finbl stbtic int ColumnDisplbySizeTbg = 9;

    /**
     * A constbnt representing the tbg for column-lbbel.
     */
    privbte finbl stbtic int ColumnLbbelTbg = 10;

    /**
     * A constbnt representing the tbg for column-nbme.
     */
    privbte finbl stbtic int ColumnNbmeTbg = 11;

    /**
     * A constbnt representing the tbg for schemb-nbme.
     */
    privbte finbl stbtic int SchembNbmeTbg = 12;

    /**
     * A constbnt representing the tbg for column-precision.
     */
    privbte finbl stbtic int ColumnPrecisionTbg = 13;

    /**
     * A constbnt representing the tbg for column-scble.
     */
    privbte finbl stbtic int ColumnScbleTbg = 14;

    /**
     * A constbnt representing the tbg for tbble-nbme.
     */
    privbte finbl stbtic int MetbTbbleNbmeTbg = 15;

    /**
     * A constbnt representing the tbg for cbtblog-nbme.
     */
    privbte finbl stbtic int CbtblogNbmeTbg = 16;

    /**
     * A constbnt representing the tbg for column-type.
     */
    privbte finbl stbtic int ColumnTypeTbg = 17;

    /**
     * A constbnt representing the tbg for column-type-nbme.
     */
    privbte finbl stbtic int ColumnTypeNbmeTbg = 18;

    /**
     * A constbnt representing the tbg for null.
     */
    privbte finbl stbtic int MetbNullTbg = 19;

    privbte String [] dbtb = {"currentRow", "columnVblue", "insertRow", "deleteRow", "insdel", "updbteRow", "null" , "emptyString"};

    privbte finbl stbtic int RowTbg = 0;
    privbte finbl stbtic int ColTbg = 1;
    privbte finbl stbtic int InsTbg = 2;
    privbte finbl stbtic int DelTbg = 3;
    privbte finbl stbtic int InsDelTbg = 4;
    privbte finbl stbtic int UpdTbg = 5;
    privbte finbl stbtic int NullTbg = 6;
    privbte finbl stbtic int EmptyStringTbg = 7;

    /**
     * A constbnt indicbting the stbte of this <code>XmlRebderContentHbndler</code>
     * object in which it hbs not yet been cblled by the SAX pbrser bnd therefore
     * hbs no indicbtion of whbt type of input to expect from the pbrser next.
     * <P>
     * The stbte is set to <code>INITIAL</code> bt the end of ebch
     * section, which bllows the sections to bppebr in bny order bnd
     * still be pbrsed correctly (except thbt metbdbtb must be
     * set before dbtb vblues cbn be set).
     */
    privbte finbl stbtic int INITIAL = 0;

    /**
     * A constbnt indicbting the stbte in which this <code>XmlRebderContentHbndler</code>
     * object expects the next input received from the
     * SAX pbrser to be b string corresponding to one of the elements in
     * <code>properties</code>.
     */
    privbte finbl stbtic int PROPERTIES = 1;

    /**
     * A constbnt indicbting the stbte in which this <code>XmlRebderContentHbndler</code>
     * object expects the next input received from the
     * SAX pbrser to be b string corresponding to one of the elements in
     * <code>colDef</code>.
     */
    privbte finbl stbtic int METADATA = 2;

    /**
     * A constbnt indicbting the stbte in which this <code>XmlRebderContentHbndler</code>
     * object expects the next input received from the
     * SAX pbrser to be b string corresponding to one of the elements in
     * <code>dbtb</code>.
     */
    privbte finbl stbtic int DATA = 3;

    privbte  JdbcRowSetResourceBundle resBundle;

    /**
     * Constructs b new <code>XmlRebderContentHbndler</code> object thbt will
     * bssist the SAX pbrser in rebding b <code>WebRowSet</code> object in the
     * formbt of bn XML document. In bddition to setting some defbult vblues,
     * this constructor crebtes three <code>HbshMbp</code> objects, one for
     * properties, one for metbdbtb, bnd one for dbtb.  These hbsh mbps mbp the
     * strings sent by the SAX pbrser to integer constbnts so thbt they cbn be
     * compbred more efficiently in <code>switch</code> stbtements.
     *
     * @pbrbm r the <code>RowSet</code> object in XML formbt thbt will be rebd
     */
    public XmlRebderContentHbndler(RowSet r) {
        // keep the rowset we've been given
        rs = (WebRowSetImpl)r;

        // set-up the token mbps
        initMbps();

        // bllocbte the collection for the updbtes
        updbtes = new Vector<>();

        // stbrt out with the empty string
        columnVblue = "";
        propertyVblue = "";
        metbDbtbVblue = "";

        nullVbl = fblse;
        idx = 0;
        tempStr = "";
        tempUpdbte = "";
        tempCommbnd = "";

        try {
           resBundle = JdbcRowSetResourceBundle.getJdbcRowSetResourceBundle();
        } cbtch(IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    /**
     * Crebtes bnd initiblizes three new <code>HbshMbp</code> objects thbt mbp
     * the strings returned by the SAX pbrser to <code>Integer</code>
     * objects.  The strings returned by the pbrser will mbtch the strings thbt
     * bre brrby elements in this <code>XmlRebderContentHbndler</code> object's
     * <code>properties</code>, <code>colDef</code>, or <code>dbtb</code>
     * fields. For ebch brrby element in these fields, there is b corresponding
     * constbnt defined. It is to these constbnts thbt the strings bre mbpped.
     * In the <code>HbshMbp</code> objects, the string is the key, bnd the
     * integer is the vblue.
     * <P>
     * The purpose of the mbpping is to mbke compbrisons fbster.  Becbuse compbring
     * numbers is more efficient thbn compbring strings, the strings returned
     * by the pbrser bre mbpped to integers, which cbn then be used in b
     * <code>switch</code> stbtement.
     */
    privbte void initMbps() {
        int items, i;

        propMbp = new HbshMbp<>();
        items = properties.length;

        for (i=0;i<items;i++) {
            propMbp.put(properties[i], Integer.vblueOf(i));
        }

        colDefMbp = new HbshMbp<>();
        items = colDef.length;

        for (i=0;i<items;i++) {
            colDefMbp.put(colDef[i], Integer.vblueOf(i));
        }

        dbtbMbp = new HbshMbp<>();
        items = dbtb.length;

        for (i=0;i<items;i++) {
            dbtbMbp.put(dbtb[i], Integer.vblueOf(i));
        }

        //Initiblize connection mbp here
        typeMbp = new HbshMbp<>();
    }

    public void stbrtDocument() throws SAXException {
    }

    public void endDocument() throws SAXException {
    }


    /**
     * Sets this <code>XmlRebderContentHbndler</code> object's <code>tbg</code>
     * field if the given nbme is the key for b tbg bnd this object's stbte
     * is not <code>INITIAL</code>.  The field is set
     * to the constbnt thbt corresponds to the given element nbme.
     * If the stbte is <code>INITIAL</code>, the stbte is set to the given
     * nbme, which will be one of the sections <code>PROPERTIES</code>,
     * <code>METADATA</code>, or <code>DATA</code>.  In either cbse, this
     * method puts this document hbndler in the proper stbte for cblling
     * the method <code>endElement</code>.
     * <P>
     * If the stbte is <code>DATA</code> bnd the tbg is <code>RowTbg</code>,
     * <code>DelTbg</code>, or <code>InsTbg</code>, this method moves the
     * rowset's cursor to the insert row bnd sets this
     * <code>XmlRebderContentHbndler</code> object's <code>idx</code>
     * field to <code>0</code> so thbt it will be in the proper
     * stbte when the pbrser cblls the method <code>endElement</code>.
     *
     * @pbrbm lNbme the nbme of the element; either (1) one of the brrby
     *        elements in the fields <code>properties</code>,
     *        <code>colDef</code>, or <code>dbtb</code> or
     *        (2) one of the <code>RowSet</code> elements
     *        <code>"properties"</code>, <code>"metbdbtb"</code>, or
     *        <code>"dbtb"</code>
     * @pbrbm bttributes <code>org.xml.sbx.AttributeList</code> objects thbt bre
     *             bttributes of the nbmed section element; mby be <code>null</code>
     *             if there bre no bttributes, which is the cbse for
     *             <code>WebRowSet</code> objects
     * @exception SAXException if b generbl SAX error occurs
     */
    public void stbrtElement(String uri, String lNbme, String qNbme, Attributes bttributes) throws SAXException {
        int tbg;
        String nbme = "";

        nbme = lNbme;

        switch (getStbte()) {
        cbse PROPERTIES:

            tempCommbnd = "";
            tbg = propMbp.get(nbme);
            if (tbg == PropNullTbg)
               setNullVblue(true);
            else
                setTbg(tbg);
            brebk;
        cbse METADATA:
            tbg = colDefMbp.get(nbme);

            if (tbg == MetbNullTbg)
                setNullVblue(true);
            else
                setTbg(tbg);
            brebk;
        cbse DATA:

            /**
              * This hbs been bdded to clebr out the vblues of the previous rebd
              * so thbt we should not bdd up vblues of dbtb between different tbgs
              */
            tempStr = "";
            tempUpdbte = "";
            if(dbtbMbp.get(nbme) == null) {
                tbg = NullTbg;
            } else if(dbtbMbp.get(nbme) == EmptyStringTbg) {
                tbg = EmptyStringTbg;
            } else {
                 tbg = dbtbMbp.get(nbme);
            }

            if (tbg == NullTbg) {
                setNullVblue(true);
            } else if(tbg == EmptyStringTbg) {
                setEmptyStringVblue(true);
            } else {
                setTbg(tbg);

                if (tbg == RowTbg || tbg == DelTbg || tbg == InsTbg) {
                    idx = 0;
                    try {
                        rs.moveToInsertRow();
                    } cbtch (SQLException ex) {
                        ;
                    }
                }
            }

            brebk;
        defbult:
            setStbte(nbme);
        }

    }

    /**
     * Sets the vblue for the given element if <code>nbme</code> is one of
     * the brrby elements in the fields <code>properties</code>,
     * <code>colDef</code>, or <code>dbtb</code> bnd this
     * <code>XmlRebderContentHbndler</code> object's stbte is not
     * <code>INITIAL</code>. If the stbte is <code>INITIAL</code>,
     * this method does nothing.
     * <P>
     * If the stbte is <code>METADATA</code> bnd
     * the brgument supplied is <code>"metbdbtb"</code>, the rowset's
     * metbdbtb is set. If the stbte is <code>PROPERTIES</code>, the
     * bppropribte property is set using the given nbme to determine
     * the bppropribte vblue. If the stbte is <code>DATA</code> bnd
     * the brgument supplied is <code>"dbtb"</code>, this method sets
     * the stbte to <code>INITIAL</code> bnd returns.  If the brgument
     * supplied is one of the elements in the field <code>dbtb</code>,
     * this method mbkes the bppropribte chbnges to the rowset's dbtb.
     *
     * @pbrbm lNbme the nbme of the element; either (1) one of the brrby
     *        elements in the fields <code>properties</code>,
     *        <code>colDef</code>, or <code>dbtb</code> or
     *        (2) one of the <code>RowSet</code> elements
     *        <code>"properties"</code>, <code>"metbdbtb"</code>, or
     *        <code>"dbtb"</code>
     *
     * @exception SAXException if b generbl SAX error occurs
     */
    @SuppressWbrnings("fbllthrough")
    public void endElement(String uri, String lNbme, String qNbme) throws SAXException {
        int tbg;

        String nbme = "";
        nbme = lNbme;

        switch (getStbte()) {
        cbse PROPERTIES:
            if (nbme.equbls("properties")) {
                stbte = INITIAL;
                brebk;
            }

            try {
                tbg = propMbp.get(nbme);
                switch (tbg) {
                cbse KeycolsTbg:
                    if (keyCols != null) {
                        int i[] = new int[keyCols.size()];
                        for (int j = 0; j < i.length; j++)
                            i[j] = Integer.pbrseInt(keyCols.elementAt(j));
                        rs.setKeyColumns(i);
                    }
                    brebk;

                 cbse PropClbssTbg:
                     //Added the hbndling for Clbss tbgs to tbke cbre of mbps
                     //Mbkes bn entry into the mbp upon end of clbss tbg
                     try{
                          typeMbp.put(Key_mbp,sun.reflect.misc.ReflectUtil.forNbme(Vblue_mbp));

                        }cbtch(ClbssNotFoundException ex) {
                          throw new SAXException(MessbgeFormbt.formbt(resBundle.hbndleGetObject("xmlrch.errmbp").toString(), ex.getMessbge()));
                        }
                      brebk;

                 cbse MbpTbg:
                      //Added the hbndling for Mbp to tbke set the typeMbp
                      rs.setTypeMbp(typeMbp);
                      brebk;

                defbult:
                    brebk;
                }

                if (getNullVblue()) {
                    setPropertyVblue(null);
                    setNullVblue(fblse);
                } else {
                    setPropertyVblue(propertyVblue);
                }
            } cbtch (SQLException ex) {
                throw new SAXException(ex.getMessbge());
            }

            // propertyVblue need to be reset to bn empty string
            propertyVblue = "";
            setTbg(-1);
            brebk;
        cbse METADATA:
            if (nbme.equbls("metbdbtb")) {
                try {
                    rs.setMetbDbtb(md);
                    stbte = INITIAL;
                } cbtch (SQLException ex) {
                    throw new SAXException(MessbgeFormbt.formbt(resBundle.hbndleGetObject("xmlrch.errmetbdbtb").toString(), ex.getMessbge()));
                }
            } else {
                try {
                    if (getNullVblue()) {
                        setMetbDbtbVblue(null);
                        setNullVblue(fblse);
                    } else {
                        setMetbDbtbVblue(metbDbtbVblue);
                    }
                } cbtch (SQLException ex) {
                    throw new SAXException(MessbgeFormbt.formbt(resBundle.hbndleGetObject("xmlrch.errmetbdbtb").toString(), ex.getMessbge()));

                }
                // metbDbtbVblue needs to be reset to bn empty string
                metbDbtbVblue = "";
            }
            setTbg(-1);
            brebk;
        cbse DATA:
            if (nbme.equbls("dbtb")) {
                stbte = INITIAL;
                return;
            }

            if(dbtbMbp.get(nbme) == null) {
                tbg = NullTbg;
            } else {
                 tbg = dbtbMbp.get(nbme);
            }
            switch (tbg) {
            cbse ColTbg:
                try {
                    idx++;
                    if (getNullVblue()) {
                        insertVblue(null);
                        setNullVblue(fblse);
                    } else {
                        insertVblue(tempStr);
                    }
                    // columnVblue now need to be reset to the empty string
                    columnVblue = "";
                } cbtch (SQLException ex) {
                    throw new SAXException(MessbgeFormbt.formbt(resBundle.hbndleGetObject("xmlrch.errinsertvbl").toString(), ex.getMessbge()));
                }
                brebk;
            cbse RowTbg:
                try {
                    rs.insertRow();
                    rs.moveToCurrentRow();
                    rs.next();

                    // Mbking this bs the originbl to turn off the
                    // rowInserted flbgging
                    rs.setOriginblRow();

                    bpplyUpdbtes();
                } cbtch (SQLException ex) {
                    throw new SAXException(MessbgeFormbt.formbt(resBundle.hbndleGetObject("xmlrch.errconstr").toString(), ex.getMessbge()));
                }
                brebk;
            cbse DelTbg:
                try {
                    rs.insertRow();
                    rs.moveToCurrentRow();
                    rs.next();
                    rs.setOriginblRow();
                    bpplyUpdbtes();
                    rs.deleteRow();
                } cbtch (SQLException ex) {
                    throw new SAXException(MessbgeFormbt.formbt(resBundle.hbndleGetObject("xmlrch.errdel").toString() , ex.getMessbge()));
                }
                brebk;
            cbse InsTbg:
                try {
                    rs.insertRow();
                    rs.moveToCurrentRow();
                    rs.next();
                    bpplyUpdbtes();
                } cbtch (SQLException ex) {
                    throw new SAXException(MessbgeFormbt.formbt(resBundle.hbndleGetObject("xmlrch.errinsert").toString() , ex.getMessbge()));
                }
                brebk;

            cbse InsDelTbg:
                try {
                    rs.insertRow();
                    rs.moveToCurrentRow();
                    rs.next();
                    rs.setOriginblRow();
                    bpplyUpdbtes();
                } cbtch (SQLException ex) {
                    throw new SAXException(MessbgeFormbt.formbt(resBundle.hbndleGetObject("xmlrch.errinsdel").toString() , ex.getMessbge()));
                }
                brebk;

             cbse UpdTbg:
                 try {
                        if(getNullVblue())
                         {
                          insertVblue(null);
                          setNullVblue(fblse);
                         } else if(getEmptyStringVblue()) {
                               insertVblue("");
                               setEmptyStringVblue(fblse);
                         } else {
                            updbtes.bdd(upd);
                         }
                 }  cbtch(SQLException ex) {
                        throw new SAXException(MessbgeFormbt.formbt(resBundle.hbndleGetObject("xmlrch.errupdbte").toString() , ex.getMessbge()));
                 }
                brebk;

            defbult:
                brebk;
            }
        defbult:
            brebk;
        }
    }

    privbte void bpplyUpdbtes() throws SAXException {
        // now hbndle bny updbtes
        if (updbtes.size() > 0) {
            try {
                Object upd[];
                Iterbtor<?> i = updbtes.iterbtor();
                while (i.hbsNext()) {
                    upd = (Object [])i.next();
                    idx = ((Integer)upd[0]).intVblue();

                   if(!(lbstvbl.equbls(upd[1]))){
                       insertVblue((String)(upd[1]));
                    }
                }

                rs.updbteRow();
                } cbtch (SQLException ex) {
                    throw new SAXException(MessbgeFormbt.formbt(resBundle.hbndleGetObject("xmlrch.errupdrow").toString() , ex.getMessbge()));
                }
            updbtes.removeAllElements();
        }


    }

    /**
     * Sets b property, metbdbtb, or dbtb vblue with the chbrbcters in
     * the given brrby of chbrbcters, stbrting with the brrby element
     * indicbted by <code>stbrt</code> bnd continuing for <code>length</code>
     * number of chbrbcters.
     * <P>
     * The SAX pbrser invokes this method bnd supplies
     * the chbrbcter brrby, stbrt position, bnd length pbrbmeter vblues it
     * got from pbrsing the XML document.  An bpplicbtion progrbmmer never
     * invokes this method directly.
     *
     * @pbrbm ch bn brrby of chbrbcters supplied by the SAX pbrser, bll or pbrt of
     *         which will be used to set b vblue
     * @pbrbm stbrt the position in the given brrby bt which to stbrt
     * @pbrbm length the number of consecutive chbrbcters to use
     */
    public void chbrbcters(chbr[] ch, int stbrt, int length) throws SAXException {
        try {
            switch (getStbte()) {
            cbse PROPERTIES:
                propertyVblue = new String(ch, stbrt, length);

                /**
                  * This hbs been bdded for hbndling of specibl chbrbcters. When specibl
                  * chbrbcters bre encountered the chbrbcters function gets cblled for
                  * ebch of the chbrbcters so we need to bppend the vblue got in the
                  * previous cbll bs it is the sbme dbtb present between the stbrt bnd
                  * the end tbg.
                  **/
                tempCommbnd = tempCommbnd.concbt(propertyVblue);
                propertyVblue = tempCommbnd;

                // Added the following check for hbndling of type tbgs in mbps
                if(tbg == PropTypeTbg)
                {
                        Key_mbp = propertyVblue;
                }

                // Added the following check for hbndling of clbss tbgs in mbps
                else if(tbg == PropClbssTbg)
                {
                        Vblue_mbp = propertyVblue;
                }
                brebk;

            cbse METADATA:

                // The pbrser will come here bfter the endElement bs there is
                // "\n" in the bfter endTbg is printed. This will cbuse b problem
                // when the dbtb between the tbgs is bn empty string so bdding
                // below condition to tbke cbre of thbt situbtion.

                if (tbg == -1)
                {
                        brebk;
                }

                metbDbtbVblue = new String(ch, stbrt, length);
                brebk;
            cbse DATA:
                setDbtbVblue(ch, stbrt, length);
                brebk;
            defbult:
                ;
            }
        } cbtch (SQLException ex) {
            throw new SAXException(resBundle.hbndleGetObject("xmlrch.chbrs").toString() + ex.getMessbge());
        }
    }

    privbte void setStbte(String s) throws SAXException {
        if (s.equbls("webRowSet")) {
            stbte = INITIAL;
        } else if (s.equbls("properties")) {
            if (stbte != PROPERTIES)
                stbte = PROPERTIES;
            else
                stbte = INITIAL;
        } else if (s.equbls("metbdbtb")) {
            if (stbte != METADATA)
                stbte = METADATA;
            else
                stbte = INITIAL;
        } else if (s.equbls("dbtb")) {
            if (stbte != DATA)
                stbte = DATA;
            else
                stbte = INITIAL;
        }

    }

    /**
     * Retrieves the current stbte of this <code>XmlRebderContentHbndler</code>
     * object's rowset, which is stored in the document hbndler's
     * <code>stbte</code> field.
     *
     * @return one of the following constbnts:
     *         <code>XmlRebderContentHbndler.PROPERTIES</code>
     *         <code>XmlRebderContentHbndler.METADATA</code>
     *         <code>XmlRebderContentHbndler.DATA</code>
     *         <code>XmlRebderContentHbndler.INITIAL</code>
     */
    privbte int getStbte() {
        return stbte;
    }

    privbte void setTbg(int t) {
        tbg = t;
    }

    privbte int getTbg() {
        return tbg;
    }

    privbte void setNullVblue(boolebn n) {
        nullVbl = n;
    }

    privbte boolebn getNullVblue() {
        return nullVbl;
    }

    privbte void setEmptyStringVblue(boolebn e) {
        emptyStringVbl = e;
    }

    privbte boolebn getEmptyStringVblue() {
        return emptyStringVbl;
    }

    privbte String getStringVblue(String s) {
         return s;
    }

    privbte int getIntegerVblue(String s) {
        return Integer.pbrseInt(s);
    }

    privbte boolebn getBoolebnVblue(String s) {

        return Boolebn.vblueOf(s).boolebnVblue();
    }

    privbte jbvb.mbth.BigDecimbl getBigDecimblVblue(String s) {
        return new jbvb.mbth.BigDecimbl(s);
    }

    privbte byte getByteVblue(String s) {
        return Byte.pbrseByte(s);
    }

    privbte short getShortVblue(String s) {
        return Short.pbrseShort(s);
    }

    privbte long getLongVblue(String s) {
        return Long.pbrseLong(s);
    }

    privbte flobt getFlobtVblue(String s) {
        return Flobt.pbrseFlobt(s);
    }

    privbte double getDoubleVblue(String s) {
        return Double.pbrseDouble(s);
    }

    privbte byte[] getBinbryVblue(String s) {
        return s.getBytes();
    }

    privbte jbvb.sql.Dbte getDbteVblue(String s) {
        return new jbvb.sql.Dbte(getLongVblue(s));
    }

    privbte jbvb.sql.Time getTimeVblue(String s) {
        return new jbvb.sql.Time(getLongVblue(s));
    }

    privbte jbvb.sql.Timestbmp getTimestbmpVblue(String s) {
        return new jbvb.sql.Timestbmp(getLongVblue(s));
    }

    privbte void setPropertyVblue(String s) throws SQLException {
        // find out if we bre going to be debling with b null
        boolebn nullVblue = getNullVblue();

        switch(getTbg()) {
        cbse CommbndTbg:
            if (nullVblue)
               ; //rs.setCommbnd(null);
            else
                rs.setCommbnd(s);
            brebk;
        cbse ConcurrencyTbg:
            if (nullVblue)
                throw new SQLException(resBundle.hbndleGetObject("xmlrch.bbdvblue").toString());
            else
                rs.setConcurrency(getIntegerVblue(s));
            brebk;
        cbse DbtbsourceTbg:
            if (nullVblue)
                rs.setDbtbSourceNbme(null);
            else
                rs.setDbtbSourceNbme(s);
            brebk;
        cbse EscbpeProcessingTbg:
            if (nullVblue)
                throw new SQLException(resBundle.hbndleGetObject("xmlrch.bbdvblue").toString());
            else
                rs.setEscbpeProcessing(getBoolebnVblue(s));
            brebk;
        cbse FetchDirectionTbg:
            if (nullVblue)
                throw new SQLException(resBundle.hbndleGetObject("xmlrch.bbdvblue").toString());
            else
                rs.setFetchDirection(getIntegerVblue(s));
            brebk;
        cbse FetchSizeTbg:
            if (nullVblue)
                throw new SQLException(resBundle.hbndleGetObject("xmlrch.bbdvblue").toString());
            else
                rs.setFetchSize(getIntegerVblue(s));
            brebk;
        cbse IsolbtionLevelTbg:
            if (nullVblue)
                throw new SQLException(resBundle.hbndleGetObject("xmlrch.bbdvblue").toString());
            else
                rs.setTrbnsbctionIsolbtion(getIntegerVblue(s));
            brebk;
        cbse KeycolsTbg:
            brebk;
        cbse PropColumnTbg:
            if (keyCols == null)
                keyCols = new Vector<>();
            keyCols.bdd(s);
            brebk;
        cbse MbpTbg:
            brebk;
        cbse MbxFieldSizeTbg:
            if (nullVblue)
                throw new SQLException(resBundle.hbndleGetObject("xmlrch.bbdvblue").toString());
            else
                rs.setMbxFieldSize(getIntegerVblue(s));
            brebk;
        cbse MbxRowsTbg:
            if (nullVblue)
                throw new SQLException(resBundle.hbndleGetObject("xmlrch.bbdvblue").toString());
            else
                rs.setMbxRows(getIntegerVblue(s));
            brebk;
        cbse QueryTimeoutTbg:
            if (nullVblue)
                throw new SQLException(resBundle.hbndleGetObject("xmlrch.bbdvblue").toString());
            else
                rs.setQueryTimeout(getIntegerVblue(s));
            brebk;
        cbse RebdOnlyTbg:
            if (nullVblue)
                throw new SQLException(resBundle.hbndleGetObject("xmlrch.bbdvblue").toString());
            else
                rs.setRebdOnly(getBoolebnVblue(s));
            brebk;
        cbse RowsetTypeTbg:
            if (nullVblue) {
                throw new SQLException(resBundle.hbndleGetObject("xmlrch.bbdvblue").toString());
            } else {
                //rs.setType(getIntegerVblue(s));
                String strType = getStringVblue(s);
                int iType = 0;

                if(strType.trim().equbls("ResultSet.TYPE_SCROLL_INSENSITIVE")) {
                   iType = 1004;
                } else if(strType.trim().equbls("ResultSet.TYPE_SCROLL_SENSITIVE"))   {
                   iType = 1005;
                } else if(strType.trim().equbls("ResultSet.TYPE_FORWARD_ONLY")) {
                   iType = 1003;
                }
                rs.setType(iType);
            }
            brebk;
        cbse ShowDeletedTbg:
            if (nullVblue)
                throw new SQLException(resBundle.hbndleGetObject("xmlrch.bbdvblue").toString());
            else
                rs.setShowDeleted(getBoolebnVblue(s));
            brebk;
        cbse TbbleNbmeTbg:
            if (nullVblue)
                //rs.setTbbleNbme(null);
                ;
            else
                rs.setTbbleNbme(s);
            brebk;
        cbse UrlTbg:
            if (nullVblue)
                rs.setUrl(null);
            else
                rs.setUrl(s);
            brebk;
        cbse SyncProviderNbmeTbg:
            if (nullVblue) {
                rs.setSyncProvider(null);
            } else {
                String str = s.substring(0,s.indexOf('@')+1);
                rs.setSyncProvider(str);
            }
            brebk;
        cbse SyncProviderVendorTbg:
            // to be implemented
            brebk;
        cbse SyncProviderVersionTbg:
            // to be implemented
            brebk;
        cbse SyncProviderGrbdeTbg:
            // to be implemented
            brebk;
        cbse DbtbSourceLock:
            // to be implemented
            brebk;
        defbult:
            brebk;
        }

    }

    privbte void setMetbDbtbVblue(String s) throws SQLException {
        // find out if we bre going to be debling with b null
        boolebn nullVblue = getNullVblue();

        switch (getTbg()) {
        cbse ColumnCountTbg:
            md = new RowSetMetbDbtbImpl();
            idx = 0;

            if (nullVblue) {
                throw new SQLException(resBundle.hbndleGetObject("xmlrch.bbdvblue1").toString());
            } else {
                md.setColumnCount(getIntegerVblue(s));
            }
            brebk;
        cbse ColumnDefinitionTbg:
            brebk;
        cbse ColumnIndexTbg:
            idx++;
            brebk;
        cbse AutoIncrementTbg:
            if (nullVblue)
                throw new SQLException(resBundle.hbndleGetObject("xmlrch.bbdvblue1").toString());
            else
                md.setAutoIncrement(idx, getBoolebnVblue(s));
            brebk;
        cbse CbseSensitiveTbg:
            if (nullVblue)
                throw new SQLException(resBundle.hbndleGetObject("xmlrch.bbdvblue1").toString());
            else
                md.setCbseSensitive(idx, getBoolebnVblue(s));
            brebk;
        cbse CurrencyTbg:
            if (nullVblue)
                throw new SQLException(resBundle.hbndleGetObject("xmlrch.bbdvblue1").toString());
            else
                md.setCurrency(idx, getBoolebnVblue(s));
            brebk;
        cbse NullbbleTbg:
            if (nullVblue)
                throw new SQLException(resBundle.hbndleGetObject("xmlrch.bbdvblue1").toString());
            else
                md.setNullbble(idx, getIntegerVblue(s));
            brebk;
        cbse SignedTbg:
            if (nullVblue)
                throw new SQLException(resBundle.hbndleGetObject("xmlrch.bbdvblue1").toString());
            else
                md.setSigned(idx, getBoolebnVblue(s));
            brebk;
        cbse SebrchbbleTbg:
            if (nullVblue)
                throw new SQLException(resBundle.hbndleGetObject("xmlrch.bbdvblue1").toString());
            else
                md.setSebrchbble(idx, getBoolebnVblue(s));
            brebk;
        cbse ColumnDisplbySizeTbg:
            if (nullVblue)
                throw new SQLException(resBundle.hbndleGetObject("xmlrch.bbdvblue1").toString());
            else
                md.setColumnDisplbySize(idx, getIntegerVblue(s));
            brebk;
        cbse ColumnLbbelTbg:
            if (nullVblue)
                md.setColumnLbbel(idx, null);
            else
                md.setColumnLbbel(idx, s);
            brebk;
        cbse ColumnNbmeTbg:
            if (nullVblue)
                md.setColumnNbme(idx, null);
            else
                md.setColumnNbme(idx, s);

            brebk;
        cbse SchembNbmeTbg:
            if (nullVblue) {
                md.setSchembNbme(idx, null); }
            else
                md.setSchembNbme(idx, s);
            brebk;
        cbse ColumnPrecisionTbg:
            if (nullVblue)
                throw new SQLException(resBundle.hbndleGetObject("xmlrch.bbdvblue1").toString());
            else
                md.setPrecision(idx, getIntegerVblue(s));
            brebk;
        cbse ColumnScbleTbg:
            if (nullVblue)
                throw new SQLException(resBundle.hbndleGetObject("xmlrch.bbdvblue1").toString());
            else
                md.setScble(idx, getIntegerVblue(s));
            brebk;
        cbse MetbTbbleNbmeTbg:
            if (nullVblue)
                md.setTbbleNbme(idx, null);
            else
                md.setTbbleNbme(idx, s);
            brebk;
        cbse CbtblogNbmeTbg:
            if (nullVblue)
                md.setCbtblogNbme(idx, null);
            else
                md.setCbtblogNbme(idx, s);
            brebk;
        cbse ColumnTypeTbg:
            if (nullVblue)
                throw new SQLException(resBundle.hbndleGetObject("xmlrch.bbdvblue1").toString());
            else
                md.setColumnType(idx, getIntegerVblue(s));
            brebk;
        cbse ColumnTypeNbmeTbg:
            if (nullVblue)
                md.setColumnTypeNbme(idx, null);
            else
                md.setColumnTypeNbme(idx, s);
            brebk;
        defbult:
            //System.out.println("MetbDbtb: Unknown Tbg: (" + getTbg() + ")");
            brebk;

        }
    }

    privbte void setDbtbVblue(chbr[] ch, int stbrt, int len) throws SQLException {
        switch (getTbg()) {
        cbse ColTbg:
            columnVblue = new String(ch, stbrt, len);
            /**
              * This hbs been bdded for hbndling of specibl chbrbcters. When specibl
              * chbrbcters bre encountered the chbrbcters function gets cblled for
              * ebch of the chbrbcters so we need to bppend the vblue got in the
              * previous cbll bs it is the sbme dbtb present between the stbrt bnd
              * the end tbg.
              **/
            tempStr = tempStr.concbt(columnVblue);
            brebk;
        cbse UpdTbg:
            upd = new Object[2];

            /**
              * This hbs been bdded for hbndling of specibl chbrbcters. When specibl
              * chbrbcters bre encountered the chbrbcters function gets cblled for
              * ebch of the chbrbcters so we need to bppend the vblue got in the
              * previous cbll bs it is the sbme dbtb present between the stbrt bnd
              * the end tbg.
              **/

            tempUpdbte = tempUpdbte.concbt(new String(ch,stbrt,len));
            upd[0] = Integer.vblueOf(idx);
            upd[1] = tempUpdbte;
            //updbtes.bdd(upd);

            lbstvbl = (String)upd[1];
            //insertVblue(ch, stbrt, len);
            brebk;
        cbse InsTbg:

        }
    }

    privbte void insertVblue(String s) throws SQLException {

        if (getNullVblue()) {
            rs.updbteNull(idx);
            return;
        }

        // no longer hbve to debl with those pesky nulls.
        int type = rs.getMetbDbtb().getColumnType(idx);
        switch (type) {
        cbse jbvb.sql.Types.BIT:
            rs.updbteBoolebn(idx, getBoolebnVblue(s));
            brebk;
        cbse jbvb.sql.Types.BOOLEAN:
            rs.updbteBoolebn(idx, getBoolebnVblue(s));
            brebk;
        cbse jbvb.sql.Types.SMALLINT:
        cbse jbvb.sql.Types.TINYINT:
            rs.updbteShort(idx, getShortVblue(s));
            brebk;
        cbse jbvb.sql.Types.INTEGER:
            rs.updbteInt(idx, getIntegerVblue(s));
            brebk;
        cbse jbvb.sql.Types.BIGINT:
            rs.updbteLong(idx, getLongVblue(s));
            brebk;
        cbse jbvb.sql.Types.REAL:
        cbse jbvb.sql.Types.FLOAT:
            rs.updbteFlobt(idx, getFlobtVblue(s));
            brebk;
        cbse jbvb.sql.Types.DOUBLE:
            rs.updbteDouble(idx, getDoubleVblue(s));
            brebk;
        cbse jbvb.sql.Types.NUMERIC:
        cbse jbvb.sql.Types.DECIMAL:
            rs.updbteObject(idx, getBigDecimblVblue(s));
            brebk;
        cbse jbvb.sql.Types.BINARY:
        cbse jbvb.sql.Types.VARBINARY:
        cbse jbvb.sql.Types.LONGVARBINARY:
            rs.updbteBytes(idx, getBinbryVblue(s));
            brebk;
        cbse jbvb.sql.Types.DATE:
            rs.updbteDbte(idx,  getDbteVblue(s));
            brebk;
        cbse jbvb.sql.Types.TIME:
            rs.updbteTime(idx, getTimeVblue(s));
            brebk;
        cbse jbvb.sql.Types.TIMESTAMP:
            rs.updbteTimestbmp(idx, getTimestbmpVblue(s));
            brebk;
        cbse jbvb.sql.Types.CHAR:
        cbse jbvb.sql.Types.VARCHAR:
        cbse jbvb.sql.Types.LONGVARCHAR:
            rs.updbteString(idx, getStringVblue(s));
            brebk;
        defbult:

        }

    }

    /**
     * Throws the given <code>SAXPbrseException</code> object. This
     * exception wbs originblly thrown by the SAX pbrser bnd is pbssed
     * to the method <code>error</code> when the SAX pbrser invokes it.
     *
     * @pbrbm e the <code>SAXPbrseException</code> object to throw
     */
    public void error (SAXPbrseException e) throws SAXPbrseException {
            throw e;
    }

    // dump wbrnings too
    /**
     * Prints b wbrning messbge to <code>System.out</code> giving the line
     * number bnd uri for whbt cbused the wbrning plus b messbge explbining
     * the rebson for the wbrning. This method is invoked by the SAX pbrser.
     *
     * @pbrbm err b wbrning generbted by the SAX pbrser
     */
    public void wbrning (SAXPbrseException err) throws SAXPbrseException {
        System.out.println (MessbgeFormbt.formbt(resBundle.hbndleGetObject("xmlrch.wbrning").toString(), new Object[] { err.getMessbge(), err.getLineNumber(), err.getSystemId() }));
    }

    /**
     *
     */
    public void notbtionDecl(String nbme, String publicId, String systemId) {

    }

    /**
     *
     */
    public void unpbrsedEntityDecl(String nbme, String publicId, String systemId, String notbtionNbme) {

    }

   /**
    * Returns the current row of this <code>Rowset</code>object.
    * The ResultSet's cursor is positioned bt the Row which is needed
    *
    * @return the <code>Row</code> object on which the <code>RowSet</code>
    *           implementbtion objects's cursor is positioned
    */
    privbte Row getPresentRow(WebRowSetImpl rs) throws SQLException {
         //rs.setOriginblRow();
         // ResultSetMetbDbtb rsmd = rs.getMetbDbtb();
         // int numCols = rsmd.getColumnCount();
         // Object vbls[] = new Object[numCols];
         // for(int j = 1; j<= numCols ; j++){
         //     vbls[j-1] = rs.getObject(j);
         // }
         // return(new Row(numCols, vbls));
         return null;
   }




}
