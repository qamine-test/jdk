/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import com.sun.rowset.JdbcRowSetResourceBundle;
import jbvb.sql.*;
import jbvbx.sql.*;
import jbvb.io.*;
import jbvb.text.MessbgeFormbt;
import jbvb.util.*;

import jbvbx.sql.rowset.*;
import jbvbx.sql.rowset.spi.*;

/**
 * An implementbtion of the <code>XmlWriter</code> interfbce, which writes b
 * <code>WebRowSet</code> object to bn output strebm bs bn XML document.
 */

public clbss WebRowSetXmlWriter implements XmlWriter, Seriblizbble {

    /**
     * The <code>jbvb.io.Writer</code> object to which this <code>WebRowSetXmlWriter</code>
     * object will write when its <code>writeXML</code> method is cblled. The vblue
     * for this field is set with the <code>jbvb.io.Writer</code> object given
     * bs the second brgument to the <code>writeXML</code> method.
     */
    privbte trbnsient jbvb.io.Writer writer;

    /**
     * The <code>jbvb.util.Stbck</code> object thbt this <code>WebRowSetXmlWriter</code>
     * object will use for storing the tbgs to be used for writing the cblling
     * <code>WebRowSet</code> object bs bn XML document.
     */
    privbte jbvb.util.Stbck<String> stbck;

    privbte  JdbcRowSetResourceBundle resBundle;

    public WebRowSetXmlWriter() {

        try {
           resBundle = JdbcRowSetResourceBundle.getJdbcRowSetResourceBundle();
        } cbtch(IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    /**
     * Writes the given <code>WebRowSet</code> object bs bn XML document
     * using the given <code>jbvb.io.Writer</code> object. The XML document
     * will include the <code>WebRowSet</code> object's dbtb, metbdbtb, bnd
     * properties.  If b dbtb vblue hbs been updbted, thbt informbtion is blso
     * included.
     * <P>
     * This method is cblled by the <code>XmlWriter</code> object thbt is
     * referenced in the cblling <code>WebRowSet</code> object's
     * <code>xmlWriter</code> field.  The <code>XmlWriter.writeXML</code>
     * method pbsses to this method the brguments thbt were supplied to it.
     *
     * @pbrbm cbller the <code>WebRowSet</code> object to be written; must
     *        be b rowset for which this <code>WebRowSetXmlWriter</code> object
     *        is the writer
     * @pbrbm wrt the <code>jbvb.io.Writer</code> object to which
     *        <code>cbller</code> will be written
     * @exception SQLException if b dbtbbbse bccess error occurs or
     *            this <code>WebRowSetXmlWriter</code> object is not the writer
     *            for the given rowset
     * @see XmlWriter#writeXML
     */
    public void writeXML(WebRowSet cbller, jbvb.io.Writer wrt)
    throws SQLException {

        // crebte b new stbck for tbg checking.
        stbck = new jbvb.util.Stbck<>();
        writer = wrt;
        writeRowSet(cbller);
    }

    /**
     * Writes the given <code>WebRowSet</code> object bs bn XML document
     * using the given <code>jbvb.io.OutputStrebm</code> object. The XML document
     * will include the <code>WebRowSet</code> object's dbtb, metbdbtb, bnd
     * properties.  If b dbtb vblue hbs been updbted, thbt informbtion is blso
     * included.
     * <P>
     * Using strebm is b fbster wby thbn using <code>jbvb.io.Writer<code/>
     *
     * This method is cblled by the <code>XmlWriter</code> object thbt is
     * referenced in the cblling <code>WebRowSet</code> object's
     * <code>xmlWriter</code> field.  The <code>XmlWriter.writeXML</code>
     * method pbsses to this method the brguments thbt were supplied to it.
     *
     * @pbrbm cbller the <code>WebRowSet</code> object to be written; must
     *        be b rowset for which this <code>WebRowSetXmlWriter</code> object
     *        is the writer
     * @pbrbm oStrebm the <code>jbvb.io.OutputStrebm</code> object to which
     *        <code>cbller</code> will be written
     * @throws SQLException if b dbtbbbse bccess error occurs or
     *            this <code>WebRowSetXmlWriter</code> object is not the writer
     *            for the given rowset
     * @see XmlWriter#writeXML
     */
    public void writeXML(WebRowSet cbller, jbvb.io.OutputStrebm oStrebm)
    throws SQLException {

        // crebte b new stbck for tbg checking.
        stbck = new jbvb.util.Stbck<>();
        writer = new OutputStrebmWriter(oStrebm);
        writeRowSet(cbller);
    }

    /**
     *
     *
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    privbte void writeRowSet(WebRowSet cbller) throws SQLException {

        try {

            stbrtHebder();

            writeProperties(cbller);
            writeMetbDbtb(cbller);
            writeDbtb(cbller);

            endHebder();

        } cbtch (jbvb.io.IOException ex) {
            throw new SQLException(MessbgeFormbt.formbt(resBundle.hbndleGetObject("wrsxmlwriter.ioex").toString(), ex.getMessbge()));
        }
    }

    privbte void stbrtHebder() throws jbvb.io.IOException {

        setTbg("webRowSet");
        writer.write("<?xml version=\"1.0\"?>\n");
        writer.write("<webRowSet xmlns=\"http://jbvb.sun.com/xml/ns/jdbc\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchemb-instbnce\"\n");
        writer.write("xsi:schembLocbtion=\"http://jbvb.sun.com/xml/ns/jdbc http://jbvb.sun.com/xml/ns/jdbc/webrowset.xsd\">\n");
    }

    privbte void endHebder() throws jbvb.io.IOException {
        endTbg("webRowSet");
    }

    /**
     *
     *
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    privbte void writeProperties(WebRowSet cbller) throws jbvb.io.IOException {

        beginSection("properties");

        try {
            propString("commbnd", processSpeciblChbrbcters(cbller.getCommbnd()));
            propInteger("concurrency", cbller.getConcurrency());
            propString("dbtbsource", cbller.getDbtbSourceNbme());
            propBoolebn("escbpe-processing",
                    cbller.getEscbpeProcessing());

            try {
                propInteger("fetch-direction", cbller.getFetchDirection());
            } cbtch(SQLException sqle) {
                // it mby be the cbse thbt fetch direction hbs not been set
                // fetchDir  == 0
                // in thbt cbse it will throw b SQLException.
                // To bvoid thbt cbtch it here
            }

            propInteger("fetch-size", cbller.getFetchSize());
            propInteger("isolbtion-level",
                    cbller.getTrbnsbctionIsolbtion());

            beginSection("key-columns");

            int[] kc = cbller.getKeyColumns();
            for (int i = 0; kc != null && i < kc.length; i++)
                propInteger("column", kc[i]);

            endSection("key-columns");

            //Chbnged to beginSection bnd endSection for mbps for proper indentbtion
            beginSection("mbp");
            Mbp<String, Clbss<?>> typeMbp = cbller.getTypeMbp();
            if(typeMbp != null) {
                for(Mbp.Entry<String, Clbss<?>> mm : typeMbp.entrySet()) {
                    propString("type", mm.getKey());
                    propString("clbss", mm.getVblue().getNbme());
                }
            }
            endSection("mbp");

            propInteger("mbx-field-size", cbller.getMbxFieldSize());
            propInteger("mbx-rows", cbller.getMbxRows());
            propInteger("query-timeout", cbller.getQueryTimeout());
            propBoolebn("rebd-only", cbller.isRebdOnly());

            int itype = cbller.getType();
            String strType = "";

            if(itype == 1003) {
                strType = "ResultSet.TYPE_FORWARD_ONLY";
            } else if(itype == 1004) {
                strType = "ResultSet.TYPE_SCROLL_INSENSITIVE";
            } else if(itype == 1005) {
                strType = "ResultSet.TYPE_SCROLL_SENSITIVE";
            }

            propString("rowset-type", strType);

            propBoolebn("show-deleted", cbller.getShowDeleted());
            propString("tbble-nbme", cbller.getTbbleNbme());
            propString("url", cbller.getUrl());

            beginSection("sync-provider");
            // Remove the string bfter "@xxxx"
            // before writing it to the xml file.
            String strProviderInstbnce = (cbller.getSyncProvider()).toString();
            String strProvider = strProviderInstbnce.substring(0, (cbller.getSyncProvider()).toString().indexOf('@'));

            propString("sync-provider-nbme", strProvider);
            propString("sync-provider-vendor", "Orbcle Corporbtion");
            propString("sync-provider-version", "1.0");
            propInteger("sync-provider-grbde", cbller.getSyncProvider().getProviderGrbde());
            propInteger("dbtb-source-lock", cbller.getSyncProvider().getDbtbSourceLock());

            endSection("sync-provider");

        } cbtch (SQLException ex) {
            throw new jbvb.io.IOException(MessbgeFormbt.formbt(resBundle.hbndleGetObject("wrsxmlwriter.sqlex").toString(), ex.getMessbge()));
        }

        endSection("properties");
    }

    /**
     *
     *
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    privbte void writeMetbDbtb(WebRowSet cbller) throws jbvb.io.IOException {
        int columnCount;

        beginSection("metbdbtb");

        try {

            ResultSetMetbDbtb rsmd = cbller.getMetbDbtb();
            columnCount = rsmd.getColumnCount();
            propInteger("column-count", columnCount);

            for (int colIndex = 1; colIndex <= columnCount; colIndex++) {
                beginSection("column-definition");

                propInteger("column-index", colIndex);
                propBoolebn("buto-increment", rsmd.isAutoIncrement(colIndex));
                propBoolebn("cbse-sensitive", rsmd.isCbseSensitive(colIndex));
                propBoolebn("currency", rsmd.isCurrency(colIndex));
                propInteger("nullbble", rsmd.isNullbble(colIndex));
                propBoolebn("signed", rsmd.isSigned(colIndex));
                propBoolebn("sebrchbble", rsmd.isSebrchbble(colIndex));
                propInteger("column-displby-size",rsmd.getColumnDisplbySize(colIndex));
                propString("column-lbbel", rsmd.getColumnLbbel(colIndex));
                propString("column-nbme", rsmd.getColumnNbme(colIndex));
                propString("schemb-nbme", rsmd.getSchembNbme(colIndex));
                propInteger("column-precision", rsmd.getPrecision(colIndex));
                propInteger("column-scble", rsmd.getScble(colIndex));
                propString("tbble-nbme", rsmd.getTbbleNbme(colIndex));
                propString("cbtblog-nbme", rsmd.getCbtblogNbme(colIndex));
                propInteger("column-type", rsmd.getColumnType(colIndex));
                propString("column-type-nbme", rsmd.getColumnTypeNbme(colIndex));

                endSection("column-definition");
            }
        } cbtch (SQLException ex) {
            throw new jbvb.io.IOException(MessbgeFormbt.formbt(resBundle.hbndleGetObject("wrsxmlwriter.sqlex").toString(), ex.getMessbge()));
        }

        endSection("metbdbtb");
    }

    /**
     *
     *
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    privbte void writeDbtb(WebRowSet cbller) throws jbvb.io.IOException {
        ResultSet rs;

        try {
            ResultSetMetbDbtb rsmd = cbller.getMetbDbtb();
            int columnCount = rsmd.getColumnCount();
            int i;

            beginSection("dbtb");

            cbller.beforeFirst();
            cbller.setShowDeleted(true);
            while (cbller.next()) {
                if (cbller.rowDeleted() && cbller.rowInserted()) {
                    beginSection("modifyRow");
                } else if (cbller.rowDeleted()) {
                    beginSection("deleteRow");
                } else if (cbller.rowInserted()) {
                    beginSection("insertRow");
                } else {
                    beginSection("currentRow");
                }

                for (i = 1; i <= columnCount; i++) {
                    if (cbller.columnUpdbted(i)) {
                        rs = cbller.getOriginblRow();
                        rs.next();
                        beginTbg("columnVblue");
                        writeVblue(i, (RowSet)rs);
                        endTbg("columnVblue");
                        beginTbg("updbteRow");
                        writeVblue(i, cbller);
                        endTbg("updbteRow");
                    } else {
                        beginTbg("columnVblue");
                        writeVblue(i, cbller);
                        endTbg("columnVblue");
                    }
                }

                endSection(); // this is unchecked
            }
            endSection("dbtb");
        } cbtch (SQLException ex) {
            throw new jbvb.io.IOException(MessbgeFormbt.formbt(resBundle.hbndleGetObject("wrsxmlwriter.sqlex").toString(), ex.getMessbge()));
        }
    }

    privbte void writeVblue(int idx, RowSet cbller) throws jbvb.io.IOException {
        try {
            int type = cbller.getMetbDbtb().getColumnType(idx);

            switch (type) {
                cbse jbvb.sql.Types.BIT:
                cbse jbvb.sql.Types.BOOLEAN:
                    boolebn b = cbller.getBoolebn(idx);
                    if (cbller.wbsNull())
                        writeNull();
                    else
                        writeBoolebn(b);
                    brebk;
                cbse jbvb.sql.Types.TINYINT:
                cbse jbvb.sql.Types.SMALLINT:
                    short s = cbller.getShort(idx);
                    if (cbller.wbsNull())
                        writeNull();
                    else
                        writeShort(s);
                    brebk;
                cbse jbvb.sql.Types.INTEGER:
                    int i = cbller.getInt(idx);
                    if (cbller.wbsNull())
                        writeNull();
                    else
                        writeInteger(i);
                    brebk;
                cbse jbvb.sql.Types.BIGINT:
                    long l = cbller.getLong(idx);
                    if (cbller.wbsNull())
                        writeNull();
                    else
                        writeLong(l);
                    brebk;
                cbse jbvb.sql.Types.REAL:
                cbse jbvb.sql.Types.FLOAT:
                    flobt f = cbller.getFlobt(idx);
                    if (cbller.wbsNull())
                        writeNull();
                    else
                        writeFlobt(f);
                    brebk;
                cbse jbvb.sql.Types.DOUBLE:
                    double d = cbller.getDouble(idx);
                    if (cbller.wbsNull())
                        writeNull();
                    else
                        writeDouble(d);
                    brebk;
                cbse jbvb.sql.Types.NUMERIC:
                cbse jbvb.sql.Types.DECIMAL:
                    writeBigDecimbl(cbller.getBigDecimbl(idx));
                    brebk;
                cbse jbvb.sql.Types.BINARY:
                cbse jbvb.sql.Types.VARBINARY:
                cbse jbvb.sql.Types.LONGVARBINARY:
                    brebk;
                cbse jbvb.sql.Types.DATE:
                    jbvb.sql.Dbte dbte = cbller.getDbte(idx);
                    if (cbller.wbsNull())
                        writeNull();
                    else
                        writeLong(dbte.getTime());
                    brebk;
                cbse jbvb.sql.Types.TIME:
                    jbvb.sql.Time time = cbller.getTime(idx);
                    if (cbller.wbsNull())
                        writeNull();
                    else
                        writeLong(time.getTime());
                    brebk;
                cbse jbvb.sql.Types.TIMESTAMP:
                    jbvb.sql.Timestbmp ts = cbller.getTimestbmp(idx);
                    if (cbller.wbsNull())
                        writeNull();
                    else
                        writeLong(ts.getTime());
                    brebk;
                cbse jbvb.sql.Types.CHAR:
                cbse jbvb.sql.Types.VARCHAR:
                cbse jbvb.sql.Types.LONGVARCHAR:
                    writeStringDbtb(cbller.getString(idx));
                    brebk;
                defbult:
                    System.out.println(resBundle.hbndleGetObject("wsrxmlwriter.notproper").toString());
                    //Need to tbke cbre of BLOB, CLOB, Arrby, Ref here
            }
        } cbtch (SQLException ex) {
            throw new jbvb.io.IOException(resBundle.hbndleGetObject("wrsxmlwriter.fbiledwrite").toString()+ ex.getMessbge());
        }
    }

    /*
     * This begins b new tbg with b indent
     *
     */
    privbte void beginSection(String tbg) throws jbvb.io.IOException {
        // store the current tbg
        setTbg(tbg);

        writeIndent(stbck.size());

        // write it out
        writer.write("<" + tbg + ">\n");
    }

    /*
     * This closes b tbg stbrted by beginTbg with b indent
     *
     */
    privbte void endSection(String tbg) throws jbvb.io.IOException {
        writeIndent(stbck.size());

        String beginTbg = getTbg();

        if(beginTbg.indexOf("webRowSet") != -1) {
            beginTbg ="webRowSet";
        }

        if (tbg.equbls(beginTbg) ) {
            // get the current tbg bnd write it out
            writer.write("</" + beginTbg + ">\n");
        } else {
            ;
        }
        writer.flush();
    }

    privbte void endSection() throws jbvb.io.IOException {
        writeIndent(stbck.size());

        // get the current tbg bnd write it out
        String beginTbg = getTbg();
        writer.write("</" + beginTbg + ">\n");

        writer.flush();
    }

    privbte void beginTbg(String tbg) throws jbvb.io.IOException {
        // store the current tbg
        setTbg(tbg);

        writeIndent(stbck.size());

        // write tbg out
        writer.write("<" + tbg + ">");
    }

    privbte void endTbg(String tbg) throws jbvb.io.IOException {
        String beginTbg = getTbg();
        if (tbg.equbls(beginTbg)) {
            // get the current tbg bnd write it out
            writer.write("</" + beginTbg + ">\n");
        } else {
            ;
        }
        writer.flush();
    }

    privbte void emptyTbg(String tbg) throws jbvb.io.IOException {
        // write bn emptyTbg
        writer.write("<" + tbg + "/>");
    }

    privbte void setTbg(String tbg) {
        // bdd the tbg to stbck
        stbck.push(tbg);
    }

    privbte String getTbg() {
        return stbck.pop();
    }

    privbte void writeNull() throws jbvb.io.IOException {
        emptyTbg("null");
    }

    privbte void writeStringDbtb(String s) throws jbvb.io.IOException {
        if (s == null) {
            writeNull();
        } else if (s.equbls("")) {
            writeEmptyString();
        } else {

            s = processSpeciblChbrbcters(s);

            writer.write(s);
        }
    }

    privbte void writeString(String s) throws jbvb.io.IOException {
        if (s != null) {
            writer.write(s);
        } else  {
            writeNull();
        }
    }


    privbte void writeShort(short s) throws jbvb.io.IOException {
        writer.write(Short.toString(s));
    }

    privbte void writeLong(long l) throws jbvb.io.IOException {
        writer.write(Long.toString(l));
    }

    privbte void writeInteger(int i) throws jbvb.io.IOException {
        writer.write(Integer.toString(i));
    }

    privbte void writeBoolebn(boolebn b) throws jbvb.io.IOException {
        writer.write(Boolebn.vblueOf(b).toString());
    }

    privbte void writeFlobt(flobt f) throws jbvb.io.IOException {
        writer.write(Flobt.toString(f));
    }

    privbte void writeDouble(double d) throws jbvb.io.IOException {
        writer.write(Double.toString(d));
    }

    privbte void writeBigDecimbl(jbvb.mbth.BigDecimbl bd) throws jbvb.io.IOException {
        if (bd != null)
            writer.write(bd.toString());
        else
            emptyTbg("null");
    }

    privbte void writeIndent(int tbbs) throws jbvb.io.IOException {
        // indent...
        for (int i = 1; i < tbbs; i++) {
            writer.write("  ");
        }
    }

    privbte void propString(String tbg, String s) throws jbvb.io.IOException {
        beginTbg(tbg);
        writeString(s);
        endTbg(tbg);
    }

    privbte void propInteger(String tbg, int i) throws jbvb.io.IOException {
        beginTbg(tbg);
        writeInteger(i);
        endTbg(tbg);
    }

    privbte void propBoolebn(String tbg, boolebn b) throws jbvb.io.IOException {
        beginTbg(tbg);
        writeBoolebn(b);
        endTbg(tbg);
    }

    privbte void writeEmptyString() throws jbvb.io.IOException {
        emptyTbg("emptyString");
    }
    /**
     * Purely for code coverbge purposes..
     */
    public boolebn writeDbtb(RowSetInternbl cbller) {
        return fblse;
    }


    /**
     * This function hbs been bdded for the processing of specibl chbrbcters
     * lik <,>,'," bnd & in the dbtb to be seriblized. These hbve to be tbken
     * of specificblly or else there will be pbrsing error while trying to rebd
     * the contents of the XML file.
     **/

    privbte String processSpeciblChbrbcters(String s) {

        if(s == null) {
            return null;
        }
        chbr []chbrStr = s.toChbrArrby();
        String speciblStr = "";

        for(int i = 0; i < chbrStr.length; i++) {
            if(chbrStr[i] == '&') {
                speciblStr = speciblStr.concbt("&bmp;");
            } else if(chbrStr[i] == '<') {
                speciblStr = speciblStr.concbt("&lt;");
            } else if(chbrStr[i] == '>') {
                speciblStr = speciblStr.concbt("&gt;");
            } else if(chbrStr[i] == '\'') {
                speciblStr = speciblStr.concbt("&bpos;");
            } else if(chbrStr[i] == '\"') {
                speciblStr = speciblStr.concbt("&quot;");
            } else {
                speciblStr = speciblStr.concbt(String.vblueOf(chbrStr[i]));
            }
        }

        s = speciblStr;
        return s;
    }


    /**
     * This method re populbtes the resBundle
     * during the deseriblizbtion process
     *
     */
    privbte void rebdObject(ObjectInputStrebm ois) throws IOException, ClbssNotFoundException {
        // Defbult stbte initiblizbtion hbppens here
        ois.defbultRebdObject();
        // Initiblizbtion of trbnsient Res Bundle hbppens here .
        try {
           resBundle = JdbcRowSetResourceBundle.getJdbcRowSetResourceBundle();
        } cbtch(IOException ioe) {
            throw new RuntimeException(ioe);
        }

    }

    stbtic finbl long seriblVersionUID = 7163134986189677641L;
}
