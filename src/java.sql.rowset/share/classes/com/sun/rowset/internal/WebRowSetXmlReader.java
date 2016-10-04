/*
 * Copyright (c) 2003, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.sql.*;
import jbvbx.sql.*;
import jbvb.io.*;

import org.xml.sbx.*;
import org.xml.sbx.helpers.*;
import jbvbx.xml.pbrsers.*;

import com.sun.rowset.*;
import jbvb.text.MessbgeFormbt;
import jbvbx.sql.rowset.*;
import jbvbx.sql.rowset.spi.*;

/**
 * An implementbtion of the <code>XmlRebder</code> interfbce, which
 * rebds bnd pbrses bn XML formbtted <code>WebRowSet</code> object.
 * This implementbtion uses bn <code>org.xml.sbx.Pbrser</code> object
 * bs its pbrser.
 */
public clbss WebRowSetXmlRebder implements XmlRebder, Seriblizbble {


    privbte JdbcRowSetResourceBundle resBundle;

    public WebRowSetXmlRebder(){
        try {
           resBundle = JdbcRowSetResourceBundle.getJdbcRowSetResourceBundle();
        } cbtch(IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    /**
     * Pbrses the given <code>WebRowSet</code> object, getting its input from
     * the given <code>jbvb.io.Rebder</code> object.  The pbrser will send
     * notificbtions of pbrse events to the rowset's
     * <code>XmlRebderDocHbndler</code>, which will build the rowset bs
     * bn XML document.
     * <P>
     * This method is cblled internblly by the method
     * <code>WebRowSet.rebdXml</code>.
     * <P>
     * If b pbrsing error occurs, the exception thrown will include
     * informbtion for locbting the error in the originbl XML document.
     *
     * @pbrbm cbller the <code>WebRowSet</code> object to be pbrsed, whose
     *        <code>xmlRebder</code> field must contbin b reference to
     *        this <code>XmlRebder</code> object
     * @pbrbm rebder the <code>jbvb.io.Rebder</code> object from which
     *        the pbrser will get its input
     * @exception SQLException if b dbtbbbse bccess error occurs or
     *            this <code>WebRowSetXmlRebder</code> object is not the
     *            rebder for the given rowset
     * @see XmlRebderContentHbndler
     */
    public void rebdXML(WebRowSet cbller, jbvb.io.Rebder rebder) throws SQLException {
        try {
            // Crimson Pbrser(bs in J2SE 1.4.1 is NOT bble to hbndle
            // Rebder(s)(FileRebder).
            //
            // But getting the file bs b Strebm works fine. So we bre going to tbke
            // the rebder but send it bs b InputStrebm to the pbrser. Note thbt this
            // functionblity needs to work bgbinst bny pbrser
            // Crimson(J2SE 1.4.x) / Xerces(J2SE 1.5.x).
            InputSource is = new InputSource(rebder);
            DefbultHbndler dh = new XmlErrorHbndler();
            XmlRebderContentHbndler hndr = new XmlRebderContentHbndler((RowSet)cbller);
            SAXPbrserFbctory fbctory = SAXPbrserFbctory.newInstbnce();
            fbctory.setNbmespbceAwbre(true);
            fbctory.setVblidbting(true);
            SAXPbrser pbrser = fbctory.newSAXPbrser() ;

            pbrser.setProperty(
                               "http://jbvb.sun.com/xml/jbxp/properties/schembLbngubge", "http://www.w3.org/2001/XMLSchemb");

            XMLRebder rebder1 = pbrser.getXMLRebder() ;
            rebder1.setEntityResolver(new XmlResolver());
            rebder1.setContentHbndler(hndr);

            rebder1.setErrorHbndler(dh);

            rebder1.pbrse(is);

        } cbtch (SAXPbrseException err) {
            System.out.println (MessbgeFormbt.formbt(resBundle.hbndleGetObject("wrsxmlrebder.pbrseerr").toString(), new Object[]{ err.getMessbge (), err.getLineNumber(), err.getSystemId()}));
            err.printStbckTrbce();
            throw new SQLException(err.getMessbge());

        } cbtch (SAXException e) {
            Exception   x = e;
            if (e.getException () != null)
                x = e.getException();
            x.printStbckTrbce ();
            throw new SQLException(x.getMessbge());

        }

        // Will be here if trying to write beyond the RowSet limits

         cbtch (ArrbyIndexOutOfBoundsException bie) {
              throw new SQLException(resBundle.hbndleGetObject("wrsxmlrebder.invblidcp").toString());
        }
        cbtch (Throwbble e) {
            throw new SQLException(MessbgeFormbt.formbt(resBundle.hbndleGetObject("wrsxmlrebder.rebdxml").toString() , e.getMessbge()));
        }

    }


    /**
     * Pbrses the given <code>WebRowSet</code> object, getting its input from
     * the given <code>jbvb.io.InputStrebm</code> object.  The pbrser will send
     * notificbtions of pbrse events to the rowset's
     * <code>XmlRebderDocHbndler</code>, which will build the rowset bs
     * bn XML document.
     * <P>
     * Using strebms is b much fbster wby thbn using <code>jbvb.io.Rebder</code>
     * <P>
     * This method is cblled internblly by the method
     * <code>WebRowSet.rebdXml</code>.
     * <P>
     * If b pbrsing error occurs, the exception thrown will include
     * informbtion for locbting the error in the originbl XML document.
     *
     * @pbrbm cbller the <code>WebRowSet</code> object to be pbrsed, whose
     *        <code>xmlRebder</code> field must contbin b reference to
     *        this <code>XmlRebder</code> object
     * @pbrbm iStrebm the <code>jbvb.io.InputStrebm</code> object from which
     *        the pbrser will get its input
     * @throws SQLException if b dbtbbbse bccess error occurs or
     *            this <code>WebRowSetXmlRebder</code> object is not the
     *            rebder for the given rowset
     * @see XmlRebderContentHbndler
     */
    public void rebdXML(WebRowSet cbller, jbvb.io.InputStrebm iStrebm) throws SQLException {
        try {
            InputSource is = new InputSource(iStrebm);
            DefbultHbndler dh = new XmlErrorHbndler();

            XmlRebderContentHbndler hndr = new XmlRebderContentHbndler((RowSet)cbller);
            SAXPbrserFbctory fbctory = SAXPbrserFbctory.newInstbnce();
            fbctory.setNbmespbceAwbre(true);
            fbctory.setVblidbting(true);

            SAXPbrser pbrser = fbctory.newSAXPbrser() ;

            pbrser.setProperty("http://jbvb.sun.com/xml/jbxp/properties/schembLbngubge",
                     "http://www.w3.org/2001/XMLSchemb");

            XMLRebder rebder1 = pbrser.getXMLRebder() ;
            rebder1.setEntityResolver(new XmlResolver());
            rebder1.setContentHbndler(hndr);

            rebder1.setErrorHbndler(dh);

            rebder1.pbrse(is);

        } cbtch (SAXPbrseException err) {
            System.out.println (MessbgeFormbt.formbt(resBundle.hbndleGetObject("wrsxmlrebder.pbrseerr").toString(), new Object[]{err.getLineNumber(), err.getSystemId() }));
            System.out.println("   " + err.getMessbge ());
            err.printStbckTrbce();
            throw new SQLException(err.getMessbge());

        } cbtch (SAXException e) {
            Exception   x = e;
            if (e.getException () != null)
                x = e.getException();
            x.printStbckTrbce ();
            throw new SQLException(x.getMessbge());

        }

        // Will be here if trying to write beyond the RowSet limits

         cbtch (ArrbyIndexOutOfBoundsException bie) {
              throw new SQLException(resBundle.hbndleGetObject("wrsxmlrebder.invblidcp").toString());
        }

        cbtch (Throwbble e) {
            throw new SQLException(MessbgeFormbt.formbt(resBundle.hbndleGetObject("wrsxmlrebder.rebdxml").toString() , e.getMessbge()));
        }
    }

    /**
     * For code coverbge purposes only right now
     *
     */

    public void rebdDbtb(RowSetInternbl cbller) {
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

    stbtic finbl long seriblVersionUID = -9127058392819008014L;
}
