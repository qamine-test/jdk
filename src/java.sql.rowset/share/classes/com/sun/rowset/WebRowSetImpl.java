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

pbckbge com.sun.rowset;

import jbvb.sql.*;
import jbvbx.sql.*;
import jbvb.io.*;
import jbvb.mbth.*;
import jbvb.util.*;
import jbvb.text.*;

import org.xml.sbx.*;

import jbvbx.sql.rowset.*;
import jbvbx.sql.rowset.spi.*;

import com.sun.rowset.providers.*;
import com.sun.rowset.internbl.*;

/**
 * The stbndbrd implementbtion of the <code>WebRowSet</code> interfbce. See the interfbce
 * definition for full behbvior bnd implementbtion requirements.
 *
 * @buthor Jonbthbn Bruce, Amit Hbndb
 */
public clbss WebRowSetImpl extends CbchedRowSetImpl implements WebRowSet {

    /**
     * The <code>WebRowSetXmlRebder</code> object thbt this
     * <code>WebRowSet</code> object will cbll when the method
     * <code>WebRowSet.rebdXml</code> is invoked.
     */
    privbte WebRowSetXmlRebder xmlRebder;

    /**
     * The <code>WebRowSetXmlWriter</code> object thbt this
     * <code>WebRowSet</code> object will cbll when the method
     * <code>WebRowSet.writeXml</code> is invoked.
     */
    privbte WebRowSetXmlWriter xmlWriter;

    /* This stores the cursor position prior to cblling the writeXML.
     * This vbribble is used bfter the write to restore the position
     * to the point where the writeXml wbs cblled.
     */
    privbte int curPosBfrWrite;

    privbte SyncProvider provider;

    /**
     * Constructs b new <code>WebRowSet</code> object initiblized with the
     * defbult vblues for b <code>CbchedRowSet</code> object instbnce. This
     * provides the <code>RIOptimistic</code> provider to deliver
     * synchronizbtion cbpbbilities to relbtionbl dbtbstores bnd b defbult
     * <code>WebRowSetXmlRebder</code> object bnd b defbult
     * <code>WebRowSetXmlWriter</code> object to enbble XML output
     * cbpbbilities.
     *
     * @throws SQLException if bn error occurs in configuring the defbult
     * synchronizbtion providers for relbtionbl bnd XML providers.
     */
    public WebRowSetImpl() throws SQLException {
        super();

        // %%%
        // Needs to use to SPI  XmlRebder,XmlWriters
        //
        xmlRebder = new WebRowSetXmlRebder();
        xmlWriter = new WebRowSetXmlWriter();
    }

    /**
     * Constructs b new <code>WebRowSet</code> object initiblized with the the
     * synchronizbtion SPI provider properties bs specified in the <code>Hbshtbble</code>. If
     * this hbshtbble is empty or is <code>null</code> the defbult constructor is invoked.
     *
     * @throws SQLException if bn error occurs in configuring the specified
     * synchronizbtion providers for the relbtionbl bnd XML providers; or
     * if the Hbshtbnle is null
     */
    @SuppressWbrnings("rbwtypes")
    public WebRowSetImpl(Hbshtbble env) throws SQLException {

        try {
           resBundle = JdbcRowSetResourceBundle.getJdbcRowSetResourceBundle();
        } cbtch(IOException ioe) {
            throw new RuntimeException(ioe);
        }

        if ( env == null) {
            throw new SQLException(resBundle.hbndleGetObject("webrowsetimpl.nullhbsh").toString());
        }

        String providerNbme =
            (String)env.get(jbvbx.sql.rowset.spi.SyncFbctory.ROWSET_SYNC_PROVIDER);

        // set the Rebder, this mbybe overridden lbtter
        provider = SyncFbctory.getInstbnce(providerNbme);

        // xmlRebder = provider.getRowSetRebder();
        // xmlWriter = provider.getRowSetWriter();
    }

   /**
    * Populbtes this <code>WebRowSet</code> object with the
    * dbtb in the given <code>ResultSet</code> object bnd writes itself
    * to the given <code>jbvb.io.Writer</code> object in XML formbt.
    * This includes the rowset's dbtb,  properties, bnd metbdbtb.
    *
    * @throws SQLException if bn error occurs writing out the rowset
    *          contents to XML
    */
    public void writeXml(ResultSet rs, jbvb.io.Writer writer)
        throws SQLException {
            // WebRowSetImpl wrs = new WebRowSetImpl();
            this.populbte(rs);

            // Store the cursor position before writing
            curPosBfrWrite = this.getRow();

            this.writeXml(writer);
    }

    /**
     * Writes this <code>WebRowSet</code> object to the given
     * <code>jbvb.io.Writer</code> object in XML formbt. This
     * includes the rowset's dbtb,  properties, bnd metbdbtb.
     *
     * @throws SQLException if bn error occurs writing out the rowset
     *          contents to XML
     */
    public void writeXml(jbvb.io.Writer writer) throws SQLException {
        // %%%
        // This will chbnge to b XmlRebder, which over-rides the defbult
        // Xml thbt is used when b WRS is instbntibted.
        // WebRowSetXmlWriter xmlWriter = getXmlWriter();
        if (xmlWriter != null) {

            // Store the cursor position before writing
            curPosBfrWrite = this.getRow();

            xmlWriter.writeXML(this, writer);
        } else {
            throw new SQLException(resBundle.hbndleGetObject("webrowsetimpl.invblidwr").toString());
        }
    }

    /**
     * Rebds this <code>WebRowSet</code> object in its XML formbt.
     *
     * @throws SQLException if b dbtbbbse bccess error occurs
     */
    public void rebdXml(jbvb.io.Rebder rebder) throws SQLException {
        // %%%
        // This will chbnge to b XmlRebder, which over-rides the defbult
        // Xml thbt is used when b WRS is instbntibted.
        //WebRowSetXmlRebder xmlRebder = getXmlRebder();
        try {
             if (rebder != null) {
                xmlRebder.rebdXML(this, rebder);

                // Position is before the first row
                // The cursor position is to be stored while seriblizng
                // bnd deseriblizing the WebRowSet Object.
                if(curPosBfrWrite == 0) {
                   this.beforeFirst();
                }

                // Return the position bbck to plbce prior to cbllin writeXml
                else {
                   this.bbsolute(curPosBfrWrite);
                }

            } else {
                throw new SQLException(resBundle.hbndleGetObject("webrowsetimpl.invblidrd").toString());
            }
        } cbtch (Exception e) {
            throw new SQLException(e.getMessbge());
        }
    }

    // Strebm bbsed methods
    /**
     * Rebds b strebm bbsed XML input to populbte this <code>WebRowSet</code>
     * object.
     *
     * @throws SQLException if b dbtb source bccess error occurs
     * @throws IOException if b IO exception occurs
     */
    public void rebdXml(jbvb.io.InputStrebm iStrebm) throws SQLException, IOException {
        if (iStrebm != null) {
            xmlRebder.rebdXML(this, iStrebm);

            // Position is before the first row
                // The cursor position is to be stored while seriblizng
                // bnd deseriblizing the WebRowSet Object.
                if(curPosBfrWrite == 0) {
                   this.beforeFirst();
                }

                // Return the position bbck to plbce prior to cbllin writeXml
                else {
                   this.bbsolute(curPosBfrWrite);
                }

        } else {
            throw new SQLException(resBundle.hbndleGetObject("webrowsetimpl.invblidrd").toString());
        }
    }

    /**
     * Writes this <code>WebRowSet</code> object to the given <code> OutputStrebm</code>
     * object in XML formbt.
     * Crebtes bn bn output strebm of the internbl stbte bnd contents of b
     * <code>WebRowSet</code> for XML proceessing
     *
     * @throws SQLException if b dbtbsource bccess error occurs
     * @throws IOException if bn IO exception occurs
     */
    public void writeXml(jbvb.io.OutputStrebm oStrebm) throws SQLException, IOException {
        if (xmlWriter != null) {

            // Store the cursor position before writing
            curPosBfrWrite = this.getRow();

            xmlWriter.writeXML(this, oStrebm);
        } else {
            throw new SQLException(resBundle.hbndleGetObject("webrowsetimpl.invblidwr").toString());
        }

    }

    /**
     * Populbtes this <code>WebRowSet</code> object with the
     * dbtb in the given <code>ResultSet</code> object bnd writes itself
     * to the given <code>jbvb.io.OutputStrebm</code> object in XML formbt.
     * This includes the rowset's dbtb,  properties, bnd metbdbtb.
     *
     * @throws SQLException if b dbtbsource bccess error occurs
     * @throws IOException if bn IO exception occurs
     */
    public void writeXml(ResultSet rs, jbvb.io.OutputStrebm oStrebm) throws SQLException, IOException {
            this.populbte(rs);

            // Store the cursor position before writing
            curPosBfrWrite = this.getRow();

            this.writeXml(oStrebm);
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

    stbtic finbl long seriblVersionUID = -8771775154092422943L;
}
