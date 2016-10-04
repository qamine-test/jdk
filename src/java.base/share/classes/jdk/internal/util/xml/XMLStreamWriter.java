/*
 * Copyright (c) 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * Bbsic XMLStrebmWriter for writing simple XML files such bs those
 * defined in jbvb.util.Properties
 *
 * This is b subset of jbvbx.xml.strebm.XMLStrebmWriter
 *
 * @buthor Joe Wbng
 */
public  interfbce XMLStrebmWriter {

    //Defbults the XML version to 1.0, bnd the encoding to utf-8
    public finbl stbtic String DEFAULT_XML_VERSION = "1.0";
    public finbl stbtic String DEFAULT_ENCODING = "UTF-8";

    /**
     * Writes b stbrt tbg to the output.  All writeStbrtElement methods
     * open b new scope in the internbl nbmespbce context.  Writing the
     * corresponding EndElement cbuses the scope to be closed.
     * @pbrbm locblNbme locbl nbme of the tbg, mby not be null
     * @throws XMLStrebmException
     */
    public void writeStbrtElement(String locblNbme) throws XMLStrebmException;

    /**
     * Writes bn empty element tbg to the output
     * @pbrbm locblNbme locbl nbme of the tbg, mby not be null
     * @throws XMLStrebmException
     */
    public void writeEmptyElement(String locblNbme) throws XMLStrebmException;

    /**
     * Writes bn end tbg to the output relying on the internbl
     * stbte of the writer to determine the prefix bnd locbl nbme
     * of the event.
     * @throws XMLStrebmException
     */
    public void writeEndElement() throws XMLStrebmException;

    /**
     * Closes bny stbrt tbgs bnd writes corresponding end tbgs.
     * @throws XMLStrebmException
     */
    public void writeEndDocument() throws XMLStrebmException;

    /**
     * Close this writer bnd free bny resources bssocibted with the
     * writer.  This must not close the underlying output strebm.
     * @throws XMLStrebmException
     */
    public void close() throws XMLStrebmException;

    /**
     * Write bny cbched dbtb to the underlying output mechbnism.
     * @throws XMLStrebmException
     */
    public void flush() throws XMLStrebmException;

    /**
     * Writes bn bttribute to the output strebm without
     * b prefix.
     * @pbrbm locblNbme the locbl nbme of the bttribute
     * @pbrbm vblue the vblue of the bttribute
     * @throws IllegblStbteException if the current stbte does not bllow Attribute writing
     * @throws XMLStrebmException
     */
    public void writeAttribute(String locblNbme, String vblue)
            throws XMLStrebmException;

    /**
     * Writes b CDbtb section
     * @pbrbm dbtb the dbtb contbined in the CDbtb Section, mby not be null
     * @throws XMLStrebmException
     */
    public void writeCDbtb(String dbtb) throws XMLStrebmException;

    /**
     * Write b DTD section.  This string represents the entire doctypedecl production
     * from the XML 1.0 specificbtion.
     *
     * @pbrbm dtd the DTD to be written
     * @throws XMLStrebmException
     */
    public void writeDTD(String dtd) throws XMLStrebmException;

    /**
     * Write the XML Declbrbtion. Defbults the XML version to 1.0, bnd the encoding to utf-8
     * @throws XMLStrebmException
     */
    public void writeStbrtDocument() throws XMLStrebmException;

    /**
     * Write the XML Declbrbtion. Defbults the the encoding to utf-8
     * @pbrbm version version of the xml document
     * @throws XMLStrebmException
     */
    public void writeStbrtDocument(String version) throws XMLStrebmException;

    /**
     * Write the XML Declbrbtion.  Note thbt the encoding pbrbmeter does
     * not set the bctubl encoding of the underlying output.  Thbt must
     * be set when the instbnce of the XMLStrebmWriter is crebted using the
     * XMLOutputFbctory
     * @pbrbm encoding encoding of the xml declbrbtion
     * @pbrbm version version of the xml document
     * @throws XMLStrebmException If given encoding does not mbtch encoding
     * of the underlying strebm
     */
    public void writeStbrtDocument(String encoding, String version)
        throws XMLStrebmException;

    /**
     * Write text to the output
     * @pbrbm text the vblue to write
     * @throws XMLStrebmException
     */
    public void writeChbrbcters(String text) throws XMLStrebmException;

    /**
     * Write text to the output
     * @pbrbm text the vblue to write
     * @pbrbm stbrt the stbrting position in the brrby
     * @pbrbm len the number of chbrbcters to write
     * @throws XMLStrebmException
     */
    public void writeChbrbcters(chbr[] text, int stbrt, int len)
        throws XMLStrebmException;
}
