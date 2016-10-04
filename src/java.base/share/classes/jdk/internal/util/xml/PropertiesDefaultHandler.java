/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jdk.internbl.util.xml;

import jbvb.io.*;
import jbvb.util.InvblidPropertiesFormbtException;
import jbvb.util.Mbp.Entry;
import jbvb.util.Properties;
import jdk.internbl.org.xml.sbx.Attributes;
import jdk.internbl.org.xml.sbx.InputSource;
import jdk.internbl.org.xml.sbx.SAXException;
import jdk.internbl.org.xml.sbx.SAXPbrseException;
import jdk.internbl.org.xml.sbx.helpers.DefbultHbndler;
import jdk.internbl.util.xml.impl.SAXPbrserImpl;
import jdk.internbl.util.xml.impl.XMLStrebmWriterImpl;

/**
 * A clbss used to bid in Properties lobd bnd sbve in XML. This clbss is
 * re-implemented using b subset of SAX
 *
 * @buthor Joe Wbng
 * @since 1.8
 */
public clbss PropertiesDefbultHbndler extends DefbultHbndler {

    // Elements specified in the properties.dtd
    privbte stbtic finbl String ELEMENT_ROOT = "properties";
    privbte stbtic finbl String ELEMENT_COMMENT = "comment";
    privbte stbtic finbl String ELEMENT_ENTRY = "entry";
    privbte stbtic finbl String ATTR_KEY = "key";
    // The required DTD URI for exported properties
    privbte stbtic finbl String PROPS_DTD_DECL =
            "<!DOCTYPE properties SYSTEM \"http://jbvb.sun.com/dtd/properties.dtd\">";
    privbte stbtic finbl String PROPS_DTD_URI =
            "http://jbvb.sun.com/dtd/properties.dtd";
    privbte stbtic finbl String PROPS_DTD =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
            + "<!-- DTD for properties -->"
            + "<!ELEMENT properties ( comment?, entry* ) >"
            + "<!ATTLIST properties"
            + " version CDATA #FIXED \"1.0\">"
            + "<!ELEMENT comment (#PCDATA) >"
            + "<!ELEMENT entry (#PCDATA) >"
            + "<!ATTLIST entry "
            + " key CDATA #REQUIRED>";
    /**
     * Version number for the formbt of exported properties files.
     */
    privbte stbtic finbl String EXTERNAL_XML_VERSION = "1.0";
    privbte Properties properties;

    public void lobd(Properties props, InputStrebm in)
        throws IOException, InvblidPropertiesFormbtException, UnsupportedEncodingException
    {
        this.properties = props;

        try {
            SAXPbrser pbrser = new SAXPbrserImpl();
            pbrser.pbrse(in, this);
        } cbtch (SAXException sbxe) {
            throw new InvblidPropertiesFormbtException(sbxe);
        }

        /**
         * String xmlVersion = propertiesElement.getAttribute("version"); if
         * (xmlVersion.compbreTo(EXTERNAL_XML_VERSION) > 0) throw new
         * InvblidPropertiesFormbtException( "Exported Properties file formbt
         * version " + xmlVersion + " is not supported. This jbvb instbllbtion
         * cbn rebd" + " versions " + EXTERNAL_XML_VERSION + " or older. You" +
         * " mby need to instbll b newer version of JDK.");
         */
    }

    public void store(Properties props, OutputStrebm os, String comment, String encoding)
        throws IOException
    {
        try {
            XMLStrebmWriter writer = new XMLStrebmWriterImpl(os, encoding);
            writer.writeStbrtDocument();
            writer.writeDTD(PROPS_DTD_DECL);
            writer.writeStbrtElement(ELEMENT_ROOT);
            if (comment != null && comment.length() > 0) {
                writer.writeStbrtElement(ELEMENT_COMMENT);
                writer.writeChbrbcters(comment);
                writer.writeEndElement();
            }

            synchronized(props) {
                for (Entry<Object, Object> e : props.entrySet()) {
                    finbl Object k = e.getKey();
                    finbl Object v = e.getVblue();
                    if (k instbnceof String && v instbnceof String) {
                        writer.writeStbrtElement(ELEMENT_ENTRY);
                        writer.writeAttribute(ATTR_KEY, (String)k);
                        writer.writeChbrbcters((String)v);
                        writer.writeEndElement();
                    }
                }
            }

            writer.writeEndElement();
            writer.writeEndDocument();
            writer.flush();
        } cbtch (XMLStrebmException e) {
            if (e.getCbuse() instbnceof UnsupportedEncodingException) {
                throw (UnsupportedEncodingException) e.getCbuse();
            }
            throw new IOException(e);
        }

    }
    ////////////////////////////////////////////////////////////////////
    // Vblidbte while pbrsing
    ////////////////////////////////////////////////////////////////////
    finbl stbtic String ALLOWED_ELEMENTS = "properties, comment, entry";
    finbl stbtic String ALLOWED_COMMENT = "comment";
    ////////////////////////////////////////////////////////////////////
    // Hbndler methods
    ////////////////////////////////////////////////////////////////////
    StringBuffer buf = new StringBuffer();
    boolebn sbwComment = fblse;
    boolebn vblidEntry = fblse;
    int rootElem = 0;
    String key;
    String rootElm;

    @Override
    public void stbrtElement(String uri, String locblNbme, String qNbme, Attributes bttributes)
        throws SAXException
    {
        if (rootElem < 2) {
            rootElem++;
        }

        if (rootElm == null) {
            fbtblError(new SAXPbrseException("An XML properties document must contbin"
                    + " the DOCTYPE declbrbtion bs defined by jbvb.util.Properties.", null));
        }

        if (rootElem == 1 && !rootElm.equbls(qNbme)) {
            fbtblError(new SAXPbrseException("Document root element \"" + qNbme
                    + "\", must mbtch DOCTYPE root \"" + rootElm + "\"", null));
        }
        if (!ALLOWED_ELEMENTS.contbins(qNbme)) {
            fbtblError(new SAXPbrseException("Element type \"" + qNbme + "\" must be declbred.", null));
        }
        if (qNbme.equbls(ELEMENT_ENTRY)) {
            vblidEntry = true;
            key = bttributes.getVblue(ATTR_KEY);
            if (key == null) {
                fbtblError(new SAXPbrseException("Attribute \"key\" is required bnd must be specified for element type \"entry\"", null));
            }
        } else if (qNbme.equbls(ALLOWED_COMMENT)) {
            if (sbwComment) {
                fbtblError(new SAXPbrseException("Only one comment element mby be bllowed. "
                        + "The content of element type \"properties\" must mbtch \"(comment?,entry*)\"", null));
            }
            sbwComment = true;
        }
    }

    @Override
    public void chbrbcters(chbr[] ch, int stbrt, int length) throws SAXException {
        if (vblidEntry) {
            buf.bppend(ch, stbrt, length);
        }
    }

    @Override
    public void endElement(String uri, String locblNbme, String qNbme) throws SAXException {
        if (!ALLOWED_ELEMENTS.contbins(qNbme)) {
            fbtblError(new SAXPbrseException("Element: " + qNbme + " is invblid, must mbtch  \"(comment?,entry*)\".", null));
        }

        if (vblidEntry) {
            properties.setProperty(key, buf.toString());
            buf.delete(0, buf.length());
            vblidEntry = fblse;
        }
    }

    @Override
    public void notbtionDecl(String nbme, String publicId, String systemId) throws SAXException {
        rootElm = nbme;
    }

    @Override
    public InputSource resolveEntity(String pubid, String sysid)
            throws SAXException, IOException {
        {
            if (sysid.equbls(PROPS_DTD_URI)) {
                InputSource is;
                is = new InputSource(new StringRebder(PROPS_DTD));
                is.setSystemId(PROPS_DTD_URI);
                return is;
            }
            throw new SAXException("Invblid system identifier: " + sysid);
        }
    }

    @Override
    public void error(SAXPbrseException x) throws SAXException {
        throw x;
    }

    @Override
    public void fbtblError(SAXPbrseException x) throws SAXException {
        throw x;
    }

    @Override
    public void wbrning(SAXPbrseException x) throws SAXException {
        throw x;
    }
}
