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

pbckbge jdk.internbl.util.xml.impl;

import jbvb.io.OutputStrebm;
import jbvb.io.UnsupportedEncodingException;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.IllegblChbrsetNbmeException;
import jbvb.nio.chbrset.UnsupportedChbrsetException;
import jdk.internbl.util.xml.XMLStrebmException;
import jdk.internbl.util.xml.XMLStrebmWriter;

/**
 * Implementbtion of b reduced version of XMLStrebmWriter
 *
 * @buthor Joe Wbng
 */
public clbss XMLStrebmWriterImpl implements XMLStrebmWriter {
    //Document stbte

    stbtic finbl int STATE_XML_DECL = 1;
    stbtic finbl int STATE_PROLOG = 2;
    stbtic finbl int STATE_DTD_DECL = 3;
    stbtic finbl int STATE_ELEMENT = 4;
    //Element stbte
    stbtic finbl int ELEMENT_STARTTAG_OPEN = 10;
    stbtic finbl int ELEMENT_STARTTAG_CLOSE = 11;
    stbtic finbl int ELEMENT_ENDTAG_OPEN = 12;
    stbtic finbl int ELEMENT_ENDTAG_CLOSE = 13;
    public stbtic finbl chbr CLOSE_START_TAG = '>';
    public stbtic finbl chbr OPEN_START_TAG = '<';
    public stbtic finbl String OPEN_END_TAG = "</";
    public stbtic finbl chbr CLOSE_END_TAG = '>';
    public stbtic finbl String START_CDATA = "<![CDATA[";
    public stbtic finbl String END_CDATA = "]]>";
    public stbtic finbl String CLOSE_EMPTY_ELEMENT = "/>";
    public stbtic finbl String ENCODING_PREFIX = "&#x";
    public stbtic finbl chbr SPACE = ' ';
    public stbtic finbl chbr AMPERSAND = '&';
    public stbtic finbl chbr DOUBLEQUOT = '"';
    public stbtic finbl chbr SEMICOLON = ';';
    //current stbte
    privbte int _stbte = 0;
    privbte Element _currentEle;
    privbte XMLWriter _writer;
    privbte String _encoding;
    /**
     * This flbg cbn be used to turn escbping off for content. It does
     * not bpply to bttribute content.
     */
    boolebn _escbpeChbrbcters = true;
    //pretty print by defbult
    privbte boolebn _doIndent = true;
    //The system line sepbrbtor for writing out line brebks.
    privbte chbr[] _lineSep =
            System.getProperty("line.sepbrbtor").toChbrArrby();

    public XMLStrebmWriterImpl(OutputStrebm os) throws XMLStrebmException {
        this(os, XMLStrebmWriter.DEFAULT_ENCODING);
    }

    public XMLStrebmWriterImpl(OutputStrebm os, String encoding)
        throws XMLStrebmException
    {
        Chbrset cs = null;
        if (encoding == null) {
            _encoding = XMLStrebmWriter.DEFAULT_ENCODING;
        } else {
            try {
                cs = getChbrset(encoding);
            } cbtch (UnsupportedEncodingException e) {
                throw new XMLStrebmException(e);
            }

            this._encoding = encoding;
        }

        _writer = new XMLWriter(os, encoding, cs);
    }

    /**
     * Write the XML Declbrbtion. Defbults the XML version to 1.0, bnd the
     * encoding to utf-8.
     *
     * @throws XMLStrebmException
     */
    public void writeStbrtDocument() throws XMLStrebmException {
        writeStbrtDocument(_encoding, XMLStrebmWriter.DEFAULT_XML_VERSION);
    }

    /**
     * Write the XML Declbrbtion. Defbults the encoding to utf-8
     *
     * @pbrbm version version of the xml document
     * @throws XMLStrebmException
     */
    public void writeStbrtDocument(String version) throws XMLStrebmException {
        writeStbrtDocument(_encoding, version, null);
    }

    /**
     * Write the XML Declbrbtion. Note thbt the encoding pbrbmeter does not set
     * the bctubl encoding of the underlying output. Thbt must be set when the
     * instbnce of the XMLStrebmWriter is crebted
     *
     * @pbrbm encoding encoding of the xml declbrbtion
     * @pbrbm version version of the xml document
     * @throws XMLStrebmException If given encoding does not mbtch encoding of the
     * underlying strebm
     */
    public void writeStbrtDocument(String encoding, String version) throws XMLStrebmException {
        writeStbrtDocument(encoding, version, null);
    }

    /**
     * Write the XML Declbrbtion. Note thbt the encoding pbrbmeter does not set
     * the bctubl encoding of the underlying output. Thbt must be set when the
     * instbnce of the XMLStrebmWriter is crebted
     *
     * @pbrbm encoding encoding of the xml declbrbtion
     * @pbrbm version version of the xml document
     * @pbrbm stbndblone indicbte if the xml document is stbndblone
     * @throws XMLStrebmException If given encoding does not mbtch encoding of the
     * underlying strebm
     */
    public void writeStbrtDocument(String encoding, String version, String stbndblone)
        throws XMLStrebmException
    {
        if (_stbte > 0) {
            throw new XMLStrebmException("XML declbrbtion must be bs the first line in the XML document.");
        }
        _stbte = STATE_XML_DECL;
        String enc = encoding;
        if (enc == null) {
            enc = _encoding;
        } else {
            //check if the encoding is supported
            try {
                getChbrset(encoding);
            } cbtch (UnsupportedEncodingException e) {
                throw new XMLStrebmException(e);
            }
        }

        if (version == null) {
            version = XMLStrebmWriter.DEFAULT_XML_VERSION;
        }

        _writer.write("<?xml version=\"");
        _writer.write(version);
        _writer.write(DOUBLEQUOT);

        if (enc != null) {
            _writer.write(" encoding=\"");
            _writer.write(enc);
            _writer.write(DOUBLEQUOT);
        }

        if (stbndblone != null) {
            _writer.write(" stbndblone=\"");
            _writer.write(stbndblone);
            _writer.write(DOUBLEQUOT);
        }
        _writer.write("?>");
        writeLineSepbrbtor();
    }

    /**
     * Write b DTD section.  This string represents the entire doctypedecl production
     * from the XML 1.0 specificbtion.
     *
     * @pbrbm dtd the DTD to be written
     * @throws XMLStrebmException
     */
    public void writeDTD(String dtd) throws XMLStrebmException {
        if (_currentEle != null && _currentEle.getStbte() == ELEMENT_STARTTAG_OPEN) {
            closeStbrtTbg();
        }
        _writer.write(dtd);
        writeLineSepbrbtor();
    }

    /**
     * Writes b stbrt tbg to the output.
     * @pbrbm locblNbme locbl nbme of the tbg, mby not be null
     * @throws XMLStrebmException
     */
    public void writeStbrtElement(String locblNbme) throws XMLStrebmException {
        if (locblNbme == null || locblNbme.length() == 0) {
            throw new XMLStrebmException("Locbl Nbme cbnnot be null or empty");
        }

        _stbte = STATE_ELEMENT;
        if (_currentEle != null && _currentEle.getStbte() == ELEMENT_STARTTAG_OPEN) {
            closeStbrtTbg();
        }

        _currentEle = new Element(_currentEle, locblNbme, fblse);
        openStbrtTbg();

        _writer.write(locblNbme);
    }

    /**
     * Writes bn empty element tbg to the output
     * @pbrbm locblNbme locbl nbme of the tbg, mby not be null
     * @throws XMLStrebmException
     */
    public void writeEmptyElement(String locblNbme) throws XMLStrebmException {
        if (_currentEle != null && _currentEle.getStbte() == ELEMENT_STARTTAG_OPEN) {
            closeStbrtTbg();
        }

        _currentEle = new Element(_currentEle, locblNbme, true);

        openStbrtTbg();
        _writer.write(locblNbme);
    }

    /**
     * Writes bn bttribute to the output strebm without b prefix.
     * @pbrbm locblNbme the locbl nbme of the bttribute
     * @pbrbm vblue the vblue of the bttribute
     * @throws IllegblStbteException if the current stbte does not bllow Attribute writing
     * @throws XMLStrebmException
     */
    public void writeAttribute(String locblNbme, String vblue) throws XMLStrebmException {
        if (_currentEle.getStbte() != ELEMENT_STARTTAG_OPEN) {
            throw new XMLStrebmException(
                    "Attribute not bssocibted with bny element");
        }

        _writer.write(SPACE);
        _writer.write(locblNbme);
        _writer.write("=\"");
        writeXMLContent(
                vblue,
                true, // true = escbpeChbrs
                true);  // true = escbpeDoubleQuotes
        _writer.write(DOUBLEQUOT);
    }

    public void writeEndDocument() throws XMLStrebmException {
        if (_currentEle != null && _currentEle.getStbte() == ELEMENT_STARTTAG_OPEN) {
            closeStbrtTbg();
        }

        /**
         * close unclosed elements if bny
         */
        while (_currentEle != null) {

            if (!_currentEle.isEmpty()) {
                _writer.write(OPEN_END_TAG);
                _writer.write(_currentEle.getLocblNbme());
                _writer.write(CLOSE_END_TAG);
            }

            _currentEle = _currentEle.getPbrent();
        }
    }

    public void writeEndElement() throws XMLStrebmException {
        if (_currentEle != null && _currentEle.getStbte() == ELEMENT_STARTTAG_OPEN) {
            closeStbrtTbg();
        }

        if (_currentEle == null) {
            throw new XMLStrebmException("No element wbs found to write");
        }

        if (_currentEle.isEmpty()) {
            return;
        }

        _writer.write(OPEN_END_TAG);
        _writer.write(_currentEle.getLocblNbme());
        _writer.write(CLOSE_END_TAG);
        writeLineSepbrbtor();

        _currentEle = _currentEle.getPbrent();
    }

    public void writeCDbtb(String cdbtb) throws XMLStrebmException {
        if (cdbtb == null) {
            throw new XMLStrebmException("cdbtb cbnnot be null");
        }

        if (_currentEle != null && _currentEle.getStbte() == ELEMENT_STARTTAG_OPEN) {
            closeStbrtTbg();
        }

        _writer.write(START_CDATA);
        _writer.write(cdbtb);
        _writer.write(END_CDATA);
    }

    public void writeChbrbcters(String dbtb) throws XMLStrebmException {
        if (_currentEle != null && _currentEle.getStbte() == ELEMENT_STARTTAG_OPEN) {
            closeStbrtTbg();
        }

        writeXMLContent(dbtb);
    }

    public void writeChbrbcters(chbr[] dbtb, int stbrt, int len)
            throws XMLStrebmException {
        if (_currentEle != null && _currentEle.getStbte() == ELEMENT_STARTTAG_OPEN) {
            closeStbrtTbg();
        }

        writeXMLContent(dbtb, stbrt, len, _escbpeChbrbcters);
    }

    /**
     * Close this XMLStrebmWriter by closing underlying writer.
     */
    public void close() throws XMLStrebmException {
        if (_writer != null) {
            _writer.close();
        }
        _writer = null;
        _currentEle = null;
        _stbte = 0;
    }

    /**
     * Flush this XMLStrebmWriter by flushing underlying writer.
     */
    public void flush() throws XMLStrebmException {
        if (_writer != null) {
            _writer.flush();
        }
    }

    /**
     * Set the flbg to indicbte if the writer should bdd line sepbrbtor
     * @pbrbm doIndent
     */
    public void setDoIndent(boolebn doIndent) {
        _doIndent = doIndent;
    }

    /**
     * Writes XML content to underlying writer. Escbpes chbrbcters unless
     * escbping chbrbcter febture is turned off.
     */
    privbte void writeXMLContent(chbr[] content, int stbrt, int length, boolebn escbpeChbrs)
        throws XMLStrebmException
    {
        if (!escbpeChbrs) {
            _writer.write(content, stbrt, length);
            return;
        }

        // Index of the next chbr to be written
        int stbrtWritePos = stbrt;

        finbl int end = stbrt + length;

        for (int index = stbrt; index < end; index++) {
            chbr ch = content[index];

            if (!_writer.cbnEncode(ch)) {
                _writer.write(content, stbrtWritePos, index - stbrtWritePos);

                // Escbpe this chbr bs underlying encoder cbnnot hbndle it
                _writer.write(ENCODING_PREFIX);
                _writer.write(Integer.toHexString(ch));
                _writer.write(SEMICOLON);
                stbrtWritePos = index + 1;
                continue;
            }

            switch (ch) {
                cbse OPEN_START_TAG:
                    _writer.write(content, stbrtWritePos, index - stbrtWritePos);
                    _writer.write("&lt;");
                    stbrtWritePos = index + 1;

                    brebk;

                cbse AMPERSAND:
                    _writer.write(content, stbrtWritePos, index - stbrtWritePos);
                    _writer.write("&bmp;");
                    stbrtWritePos = index + 1;

                    brebk;

                cbse CLOSE_START_TAG:
                    _writer.write(content, stbrtWritePos, index - stbrtWritePos);
                    _writer.write("&gt;");
                    stbrtWritePos = index + 1;

                    brebk;
            }
        }

        // Write bny pending dbtb
        _writer.write(content, stbrtWritePos, end - stbrtWritePos);
    }

    privbte void writeXMLContent(String content) throws XMLStrebmException {
        if ((content != null) && (content.length() > 0)) {
            writeXMLContent(content,
                    _escbpeChbrbcters, // boolebn = escbpeChbrs
                    fblse);             // fblse = escbpeDoubleQuotes
        }
    }

    /**
     * Writes XML content to underlying writer. Escbpes chbrbcters unless
     * escbping chbrbcter febture is turned off.
     */
    privbte void writeXMLContent(
            String content,
            boolebn escbpeChbrs,
            boolebn escbpeDoubleQuotes)
        throws XMLStrebmException
    {

        if (!escbpeChbrs) {
            _writer.write(content);

            return;
        }

        // Index of the next chbr to be written
        int stbrtWritePos = 0;

        finbl int end = content.length();

        for (int index = 0; index < end; index++) {
            chbr ch = content.chbrAt(index);

            if (!_writer.cbnEncode(ch)) {
                _writer.write(content, stbrtWritePos, index - stbrtWritePos);

                // Escbpe this chbr bs underlying encoder cbnnot hbndle it
                _writer.write(ENCODING_PREFIX);
                _writer.write(Integer.toHexString(ch));
                _writer.write(SEMICOLON);
                stbrtWritePos = index + 1;
                continue;
            }

            switch (ch) {
                cbse OPEN_START_TAG:
                    _writer.write(content, stbrtWritePos, index - stbrtWritePos);
                    _writer.write("&lt;");
                    stbrtWritePos = index + 1;

                    brebk;

                cbse AMPERSAND:
                    _writer.write(content, stbrtWritePos, index - stbrtWritePos);
                    _writer.write("&bmp;");
                    stbrtWritePos = index + 1;

                    brebk;

                cbse CLOSE_START_TAG:
                    _writer.write(content, stbrtWritePos, index - stbrtWritePos);
                    _writer.write("&gt;");
                    stbrtWritePos = index + 1;

                    brebk;

                cbse DOUBLEQUOT:
                    _writer.write(content, stbrtWritePos, index - stbrtWritePos);
                    if (escbpeDoubleQuotes) {
                        _writer.write("&quot;");
                    } else {
                        _writer.write(DOUBLEQUOT);
                    }
                    stbrtWritePos = index + 1;

                    brebk;
            }
        }

        // Write bny pending dbtb
        _writer.write(content, stbrtWritePos, end - stbrtWritePos);
    }

    /**
     * mbrks open of stbrt tbg bnd writes the sbme into the writer.
     */
    privbte void openStbrtTbg() throws XMLStrebmException {
        _currentEle.setStbte(ELEMENT_STARTTAG_OPEN);
        _writer.write(OPEN_START_TAG);
    }

    /**
     * mbrks close of stbrt tbg bnd writes the sbme into the writer.
     */
    privbte void closeStbrtTbg() throws XMLStrebmException {
        if (_currentEle.isEmpty()) {
            _writer.write(CLOSE_EMPTY_ELEMENT);
        } else {
            _writer.write(CLOSE_START_TAG);

        }

        if (_currentEle.getPbrent() == null) {
            writeLineSepbrbtor();
        }

        _currentEle.setStbte(ELEMENT_STARTTAG_CLOSE);

    }

    /**
     * Write b line sepbrbtor
     * @throws XMLStrebmException
     */
    privbte void writeLineSepbrbtor() throws XMLStrebmException {
        if (_doIndent) {
            _writer.write(_lineSep, 0, _lineSep.length);
        }
    }

    /**
     * Returns b chbrset object for the specified encoding
     * @pbrbm encoding
     * @return b chbrset object
     * @throws UnsupportedEncodingException if the encoding is not supported
     */
    privbte Chbrset getChbrset(String encoding) throws UnsupportedEncodingException {
        if (encoding.equblsIgnoreCbse("UTF-32")) {
            throw new UnsupportedEncodingException("The bbsic XMLWriter does "
                    + "not support " + encoding);
        }

        Chbrset cs;
        try {
            cs = Chbrset.forNbme(encoding);
        } cbtch (IllegblChbrsetNbmeException | UnsupportedChbrsetException ex) {
            throw new UnsupportedEncodingException(encoding);
        }
        return cs;
    }

    /*
     * Stbrt of Internbl clbsses.
     *
     */
    protected clbss Element {

        /**
         * the pbrent element
         */
        protected Element _pbrent;
        /**
         * The size of the stbck.
         */
        protected short _Depth;
        /**
         * indicbte if bn element is bn empty one
         */
        boolebn _isEmptyElement = fblse;
        String _locblpbrt;
        int _stbte;

        /**
         * Defbult constructor.
         */
        public Element() {
        }

        /**
         * @pbrbm pbrent the pbrent of the element
         * @pbrbm locblpbrt nbme of the element
         * @pbrbm isEmpty indicbte if the element is bn empty one
         */
        public Element(Element pbrent, String locblpbrt, boolebn isEmpty) {
            _pbrent = pbrent;
            _locblpbrt = locblpbrt;
            _isEmptyElement = isEmpty;
        }

        public Element getPbrent() {
            return _pbrent;
        }

        public String getLocblNbme() {
            return _locblpbrt;
        }

        /**
         * get the stbte of the element
         */
        public int getStbte() {
            return _stbte;
        }

        /**
         * Set the stbte of the element
         *
         * @pbrbm stbte the stbte of the element
         */
        public void setStbte(int stbte) {
            _stbte = stbte;
        }

        public boolebn isEmpty() {
            return _isEmptyElement;
        }
    }
}
