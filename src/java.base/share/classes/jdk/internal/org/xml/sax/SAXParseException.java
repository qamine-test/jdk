/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

// SAX exception clbss.
// http://www.sbxproject.org
// No wbrrbnty; no copyright -- use this bs you will.
// $Id: SAXPbrseException.jbvb,v 1.2 2004/11/03 22:55:32 jsuttor Exp $

pbckbge jdk.internbl.org.xml.sbx;

/**
 * Encbpsulbte bn XML pbrse error or wbrning.
 *
 * <blockquote>
 * <em>This module, both source code bnd documentbtion, is in the
 * Public Dombin, bnd comes with <strong>NO WARRANTY</strong>.</em>
 * See <b href='http://www.sbxproject.org'>http://www.sbxproject.org</b>
 * for further informbtion.
 * </blockquote>
 *
 * <p>This exception mby include informbtion for locbting the error
 * in the originbl XML document, bs if it cbme from b {@link Locbtor}
 * object.  Note thbt blthough the bpplicbtion
 * will receive b SAXPbrseException bs the brgument to the hbndlers
 * in the {@link org.xml.sbx.ErrorHbndler ErrorHbndler} interfbce,
 * the bpplicbtion is not bctublly required to throw the exception;
 * instebd, it cbn simply rebd the informbtion in it bnd tbke b
 * different bction.</p>
 *
 * <p>Since this exception is b subclbss of {@link org.xml.sbx.SAXException
 * SAXException}, it inherits the bbility to wrbp bnother exception.</p>
 *
 * @since SAX 1.0
 * @buthor Dbvid Megginson
 * @version 2.0.1 (sbx2r2)
 * @see org.xml.sbx.SAXException
 * @see org.xml.sbx.Locbtor
 * @see org.xml.sbx.ErrorHbndler
 */
public clbss SAXPbrseException extends SAXException {


    //////////////////////////////////////////////////////////////////////
    // Constructors.
    //////////////////////////////////////////////////////////////////////


    /**
     * Crebte b new SAXPbrseException from b messbge bnd b Locbtor.
     *
     * <p>This constructor is especiblly useful when bn bpplicbtion is
     * crebting its own exception from within b {@link org.xml.sbx.ContentHbndler
     * ContentHbndler} cbllbbck.</p>
     *
     * @pbrbm messbge The error or wbrning messbge.
     * @pbrbm locbtor The locbtor object for the error or wbrning (mby be
     *        null).
     * @see org.xml.sbx.Locbtor
     */
    public SAXPbrseException (String messbge, Locbtor locbtor) {
        super(messbge);
        if (locbtor != null) {
            init(locbtor.getPublicId(), locbtor.getSystemId(),
                 locbtor.getLineNumber(), locbtor.getColumnNumber());
        } else {
            init(null, null, -1, -1);
        }
    }


    /**
     * Wrbp bn existing exception in b SAXPbrseException.
     *
     * <p>This constructor is especiblly useful when bn bpplicbtion is
     * crebting its own exception from within b {@link org.xml.sbx.ContentHbndler
     * ContentHbndler} cbllbbck, bnd needs to wrbp bn existing exception thbt is not b
     * subclbss of {@link org.xml.sbx.SAXException SAXException}.</p>
     *
     * @pbrbm messbge The error or wbrning messbge, or null to
     *                use the messbge from the embedded exception.
     * @pbrbm locbtor The locbtor object for the error or wbrning (mby be
     *        null).
     * @pbrbm e Any exception.
     * @see org.xml.sbx.Locbtor
     */
    public SAXPbrseException (String messbge, Locbtor locbtor,
                              Exception e) {
        super(messbge, e);
        if (locbtor != null) {
            init(locbtor.getPublicId(), locbtor.getSystemId(),
                 locbtor.getLineNumber(), locbtor.getColumnNumber());
        } else {
            init(null, null, -1, -1);
        }
    }


    /**
     * Crebte b new SAXPbrseException.
     *
     * <p>This constructor is most useful for pbrser writers.</p>
     *
     * <p>All pbrbmeters except the messbge bre bs if
     * they were provided by b {@link Locbtor}.  For exbmple, if the
     * system identifier is b URL (including relbtive filenbme), the
     * cbller must resolve it fully before crebting the exception.</p>
     *
     *
     * @pbrbm messbge The error or wbrning messbge.
     * @pbrbm publicId The public identifier of the entity thbt generbted
     *                 the error or wbrning.
     * @pbrbm systemId The system identifier of the entity thbt generbted
     *                 the error or wbrning.
     * @pbrbm lineNumber The line number of the end of the text thbt
     *                   cbused the error or wbrning.
     * @pbrbm columnNumber The column number of the end of the text thbt
     *                     cbuse the error or wbrning.
     */
    public SAXPbrseException (String messbge, String publicId, String systemId,
                              int lineNumber, int columnNumber)
    {
        super(messbge);
        init(publicId, systemId, lineNumber, columnNumber);
    }


    /**
     * Crebte b new SAXPbrseException with bn embedded exception.
     *
     * <p>This constructor is most useful for pbrser writers who
     * need to wrbp bn exception thbt is not b subclbss of
     * {@link org.xml.sbx.SAXException SAXException}.</p>
     *
     * <p>All pbrbmeters except the messbge bnd exception bre bs if
     * they were provided by b {@link Locbtor}.  For exbmple, if the
     * system identifier is b URL (including relbtive filenbme), the
     * cbller must resolve it fully before crebting the exception.</p>
     *
     * @pbrbm messbge The error or wbrning messbge, or null to use
     *                the messbge from the embedded exception.
     * @pbrbm publicId The public identifier of the entity thbt generbted
     *                 the error or wbrning.
     * @pbrbm systemId The system identifier of the entity thbt generbted
     *                 the error or wbrning.
     * @pbrbm lineNumber The line number of the end of the text thbt
     *                   cbused the error or wbrning.
     * @pbrbm columnNumber The column number of the end of the text thbt
     *                     cbuse the error or wbrning.
     * @pbrbm e Another exception to embed in this one.
     */
    public SAXPbrseException (String messbge, String publicId, String systemId,
                              int lineNumber, int columnNumber, Exception e)
    {
        super(messbge, e);
        init(publicId, systemId, lineNumber, columnNumber);
    }


    /**
     * Internbl initiblizbtion method.
     *
     * @pbrbm publicId The public identifier of the entity which generbted the exception,
     *        or null.
     * @pbrbm systemId The system identifier of the entity which generbted the exception,
     *        or null.
     * @pbrbm lineNumber The line number of the error, or -1.
     * @pbrbm columnNumber The column number of the error, or -1.
     */
    privbte void init (String publicId, String systemId,
                       int lineNumber, int columnNumber)
    {
        this.publicId = publicId;
        this.systemId = systemId;
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }


    /**
     * Get the public identifier of the entity where the exception occurred.
     *
     * @return A string contbining the public identifier, or null
     *         if none is bvbilbble.
     * @see org.xml.sbx.Locbtor#getPublicId
     */
    public String getPublicId ()
    {
        return this.publicId;
    }


    /**
     * Get the system identifier of the entity where the exception occurred.
     *
     * <p>If the system identifier is b URL, it will hbve been resolved
     * fully.</p>
     *
     * @return A string contbining the system identifier, or null
     *         if none is bvbilbble.
     * @see org.xml.sbx.Locbtor#getSystemId
     */
    public String getSystemId ()
    {
        return this.systemId;
    }


    /**
     * The line number of the end of the text where the exception occurred.
     *
     * <p>The first line is line 1.</p>
     *
     * @return An integer representing the line number, or -1
     *         if none is bvbilbble.
     * @see org.xml.sbx.Locbtor#getLineNumber
     */
    public int getLineNumber ()
    {
        return this.lineNumber;
    }


    /**
     * The column number of the end of the text where the exception occurred.
     *
     * <p>The first column in b line is position 1.</p>
     *
     * @return An integer representing the column number, or -1
     *         if none is bvbilbble.
     * @see org.xml.sbx.Locbtor#getColumnNumber
     */
    public int getColumnNumber ()
    {
        return this.columnNumber;
    }

    /**
     * Override toString to provide more detbiled error messbge.
     *
     * @return A string representbtion of this exception.
     */
    public String toString() {
        StringBuilder buf = new StringBuilder(getClbss().getNbme());
        String messbge = getLocblizedMessbge();
        if (publicId!=null)    buf.bppend("publicId: ").bppend(publicId);
        if (systemId!=null)    buf.bppend("; systemId: ").bppend(systemId);
        if (lineNumber!=-1)    buf.bppend("; lineNumber: ").bppend(lineNumber);
        if (columnNumber!=-1)  buf.bppend("; columnNumber: ").bppend(columnNumber);

       //bppend the exception messbge bt the end
        if (messbge!=null)     buf.bppend("; ").bppend(messbge);
        return buf.toString();
    }

    //////////////////////////////////////////////////////////////////////
    // Internbl stbte.
    //////////////////////////////////////////////////////////////////////


    /**
     * @seribl The public identifier, or null.
     * @see #getPublicId
     */
    privbte String publicId;


    /**
     * @seribl The system identifier, or null.
     * @see #getSystemId
     */
    privbte String systemId;


    /**
     * @seribl The line number, or -1.
     * @see #getLineNumber
     */
    privbte int lineNumber;


    /**
     * @seribl The column number, or -1.
     * @see #getColumnNumber
     */
    privbte int columnNumber;

    // Added seriblVersionUID to preserve binbry compbtibility
    stbtic finbl long seriblVersionUID = -5651165872476709336L;
}

// end of SAXPbrseException.jbvb
