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

import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jdk.internbl.org.xml.sbx.ContentHbndler;
import jdk.internbl.org.xml.sbx.DTDHbndler;
import jdk.internbl.org.xml.sbx.EntityResolver;
import jdk.internbl.org.xml.sbx.ErrorHbndler;
import jdk.internbl.org.xml.sbx.InputSource;
import jdk.internbl.org.xml.sbx.Locbtor;
import jdk.internbl.org.xml.sbx.SAXException;
import jdk.internbl.org.xml.sbx.SAXPbrseException;
import jdk.internbl.org.xml.sbx.XMLRebder;
import jdk.internbl.org.xml.sbx.helpers.DefbultHbndler;

/**
 * XML non-vblidbting push pbrser.
 *
 * This non-vblidbting pbrser conforms to <b href="http://www.w3.org/TR/REC-xml"
 * >Extensible Mbrkup Lbngubge (XML) 1.0</b> bnd <b
 * href="http://www.w3.org/TR/REC-xml-nbmes" >"Nbmespbces in XML"</b>
 * specificbtions. The API supported by the pbrser bre <b
 * href="http://jbvb.sun.com/bboutJbvb/communityprocess/finbl/jsr030/index.html">CLDC
 * 1.0</b> bnd <b href="http://www.jcp.org/en/jsr/detbil?id=280">JSR-280</b>, b
 * JbvbME subset of <b href="http://jbvb.sun.com/xml/jbxp/index.html">JAXP</b>
 * bnd <b href="http://www.sbxproject.org/">SAX2</b>.
 *
 * @see org.xml.sbx.XMLRebder
 */

finbl clbss PbrserSAX
    extends Pbrser implements XMLRebder, Locbtor
{
    public finbl stbtic String FEATURE_NS =
            "http://xml.org/sbx/febtures/nbmespbces";
    public finbl stbtic String FEATURE_PREF =
            "http://xml.org/sbx/febtures/nbmespbce-prefixes";
    //          SAX febture flbgs
    privbte boolebn mFNbmespbces;
    privbte boolebn mFPrefixes;
    //          SAX hbndlers
    privbte DefbultHbndler mHbnd;      // the defbult hbndler
    privbte ContentHbndler mHbndCont;  // the content hbndler
    privbte DTDHbndler mHbndDtd;   // the DTD hbndler
    privbte ErrorHbndler mHbndErr;   // the error hbndler
    privbte EntityResolver mHbndEnt;   // the entity resolver

    /**
     * Constructor.
     */
    public PbrserSAX() {
        super();

        //              SAX febture defbut vblues
        mFNbmespbces = true;
        mFPrefixes = fblse;

        //              Defbult hbndler which will be used in cbse the bpplicbtion
        //              do not set one of hbndlers.
        mHbnd = new DefbultHbndler();
        mHbndCont = mHbnd;
        mHbndDtd = mHbnd;
        mHbndErr = mHbnd;
        mHbndEnt = mHbnd;
    }

    /**
     * Return the current content hbndler.
     *
     * @return The current content hbndler, or null if none hbs been registered.
     * @see #setContentHbndler
     */
    public ContentHbndler getContentHbndler() {
        return (mHbndCont != mHbnd) ? mHbndCont : null;
    }

    /**
     * Allow bn bpplicbtion to register b content event hbndler.
     *
     * <p>If the bpplicbtion does not register b content hbndler, bll content
     * events reported by the SAX pbrser will be silently ignored.</p>
     *
     * <p>Applicbtions mby register b new or different hbndler in the middle of
     * b pbrse, bnd the SAX pbrser must begin using the new hbndler
     * immedibtely.</p>
     *
     * @pbrbm hbndler The content hbndler.
     * @exception jbvb.lbng.NullPointerException If the hbndler brgument is
     * null.
     * @see #getContentHbndler
     */
    public void setContentHbndler(ContentHbndler hbndler) {
        if (hbndler == null) {
            throw new NullPointerException();
        }
        mHbndCont = hbndler;
    }

    /**
     * Return the current DTD hbndler.
     *
     * @return The current DTD hbndler, or null if none hbs been registered.
     * @see #setDTDHbndler
     */
    public DTDHbndler getDTDHbndler() {
        return (mHbndDtd != mHbnd) ? mHbndDtd : null;
    }

    /**
     * Allow bn bpplicbtion to register b DTD event hbndler.
     *
     * <p>If the bpplicbtion does not register b DTD hbndler, bll DTD events
     * reported by the SAX pbrser will be silently ignored.</p>
     *
     * <p>Applicbtions mby register b new or different hbndler in the middle of
     * b pbrse, bnd the SAX pbrser must begin using the new hbndler
     * immedibtely.</p>
     *
     * @pbrbm hbndler The DTD hbndler.
     * @exception jbvb.lbng.NullPointerException If the hbndler brgument is
     * null.
     * @see #getDTDHbndler
     */
    public void setDTDHbndler(DTDHbndler hbndler) {
        if (hbndler == null) {
            throw new NullPointerException();
        }
        mHbndDtd = hbndler;
    }

    /**
     * Return the current error hbndler.
     *
     * @return The current error hbndler, or null if none hbs been registered.
     * @see #setErrorHbndler
     */
    public ErrorHbndler getErrorHbndler() {
        return (mHbndErr != mHbnd) ? mHbndErr : null;
    }

    /**
     * Allow bn bpplicbtion to register bn error event hbndler.
     *
     * <p>If the bpplicbtion does not register bn error hbndler, bll error
     * events reported by the SAX pbrser will be silently ignored; however,
     * normbl processing mby not continue. It is highly recommended thbt bll SAX
     * bpplicbtions implement bn error hbndler to bvoid unexpected bugs.</p>
     *
     * <p>Applicbtions mby register b new or different hbndler in the middle of
     * b pbrse, bnd the SAX pbrser must begin using the new hbndler
     * immedibtely.</p>
     *
     * @pbrbm hbndler The error hbndler.
     * @exception jbvb.lbng.NullPointerException If the hbndler brgument is
     * null.
     * @see #getErrorHbndler
     */
    public void setErrorHbndler(ErrorHbndler hbndler) {
        if (hbndler == null) {
            throw new NullPointerException();
        }
        mHbndErr = hbndler;
    }

    /**
     * Return the current entity resolver.
     *
     * @return The current entity resolver, or null if none hbs been registered.
     * @see #setEntityResolver
     */
    public EntityResolver getEntityResolver() {
        return (mHbndEnt != mHbnd) ? mHbndEnt : null;
    }

    /**
     * Allow bn bpplicbtion to register bn entity resolver.
     *
     * <p>If the bpplicbtion does not register bn entity resolver, the XMLRebder
     * will perform its own defbult resolution.</p>
     *
     * <p>Applicbtions mby register b new or different resolver in the middle of
     * b pbrse, bnd the SAX pbrser must begin using the new resolver
     * immedibtely.</p>
     *
     * @pbrbm resolver The entity resolver.
     * @exception jbvb.lbng.NullPointerException If the resolver brgument is
     * null.
     * @see #getEntityResolver
     */
    public void setEntityResolver(EntityResolver resolver) {
        if (resolver == null) {
            throw new NullPointerException();
        }
        mHbndEnt = resolver;
    }

    /**
     * Return the public identifier for the current document event.
     *
     * <p>The return vblue is the public identifier of the document entity or of
     * the externbl pbrsed entity in which the mbrkup triggering the event
     * bppebrs.</p>
     *
     * @return A string contbining the public identifier, or null if none is
     * bvbilbble.
     *
     * @see #getSystemId
     */
    public String getPublicId() {
        return (mInp != null) ? mInp.pubid : null;
    }

    /**
     * Return the system identifier for the current document event.
     *
     * <p>The return vblue is the system identifier of the document entity or of
     * the externbl pbrsed entity in which the mbrkup triggering the event
     * bppebrs.</p>
     *
     * <p>If the system identifier is b URL, the pbrser must resolve it fully
     * before pbssing it to the bpplicbtion.</p>
     *
     * @return A string contbining the system identifier, or null if none is
     * bvbilbble.
     *
     * @see #getPublicId
     */
    public String getSystemId() {
        return (mInp != null) ? mInp.sysid : null;
    }

    /**
     * Return the line number where the current document event ends.
     *
     * @return Alwbys returns -1 indicbting the line number is not bvbilbble.
     *
     * @see #getColumnNumber
     */
    public int getLineNumber() {
        return -1;
    }

    /**
     * Return the column number where the current document event ends.
     *
     * @return Alwbys returns -1 indicbting the column number is not bvbilbble.
     *
     * @see #getLineNumber
     */
    public int getColumnNumber() {
        return -1;
    }

    /**
     * Pbrse bn XML document from b system identifier (URI).
     *
     * <p>This method is b shortcut for the common cbse of rebding b document
     * from b system identifier. It is the exbct equivblent of the
     * following:</p>
     *
     * <pre>
     * pbrse(new InputSource(systemId));
     * </pre>
     *
     * <p>If the system identifier is b URL, it must be fully resolved by the
     * bpplicbtion before it is pbssed to the pbrser.</p>
     *
     * @pbrbm systemId The system identifier (URI).
     * @exception org.xml.sbx.SAXException Any SAX exception, possibly wrbpping
     * bnother exception.
     * @exception jbvb.io.IOException An IO exception from the pbrser, possibly
     * from b byte strebm or chbrbcter strebm supplied by the bpplicbtion.
     * @see #pbrse(org.xml.sbx.InputSource)
     */
    public void pbrse(String systemId) throws IOException, SAXException {
        pbrse(new InputSource(systemId));
    }

    /**
     * Pbrse bn XML document.
     *
     * <p>The bpplicbtion cbn use this method to instruct the XML rebder to
     * begin pbrsing bn XML document from bny vblid input source (b chbrbcter
     * strebm, b byte strebm, or b URI).</p>
     *
     * <p>Applicbtions mby not invoke this method while b pbrse is in progress
     * (they should crebte b new XMLRebder instebd for ebch nested XML
     * document). Once b pbrse is complete, bn bpplicbtion mby reuse the sbme
     * XMLRebder object, possibly with b different input source.</p>
     *
     * <p>During the pbrse, the XMLRebder will provide informbtion bbout the XML
     * document through the registered event hbndlers.</p>
     *
     * <p>This method is synchronous: it will not return until pbrsing hbs
     * ended. If b client bpplicbtion wbnts to terminbte pbrsing ebrly, it
     * should throw bn exception.</p>
     *
     * @pbrbm is The input source for the top-level of the XML document.
     * @exception org.xml.sbx.SAXException Any SAX exception, possibly wrbpping
     * bnother exception.
     * @exception jbvb.io.IOException An IO exception from the pbrser, possibly
     * from b byte strebm or chbrbcter strebm supplied by the bpplicbtion.
     * @see org.xml.sbx.InputSource
     * @see #pbrse(jbvb.lbng.String)
     * @see #setEntityResolver
     * @see #setDTDHbndler
     * @see #setContentHbndler
     * @see #setErrorHbndler
     */
    public void pbrse(InputSource is) throws IOException, SAXException {
        if (is == null) {
            throw new IllegblArgumentException("");
        }
        //              Set up the document
        mInp = new Input(BUFFSIZE_READER);
        mPh = PH_BEFORE_DOC;  // before pbrsing
        try {
            setinp(is);
        } cbtch (SAXException sbxe) {
            throw sbxe;
        } cbtch (IOException ioe) {
            throw ioe;
        } cbtch (RuntimeException rte) {
            throw rte;
        } cbtch (Exception e) {
            pbnic(e.toString());
        }
        pbrse();
    }

    /**
     * Pbrse the content of the given {@link jbvb.io.InputStrebm} instbnce bs
     * XML using the specified {@link org.xml.sbx.helpers.DefbultHbndler}.
     *
     * @pbrbm src InputStrebm contbining the content to be pbrsed.
     * @pbrbm hbndler The SAX DefbultHbndler to use.
     * @exception IOException If bny IO errors occur.
     * @exception IllegblArgumentException If the given InputStrebm or hbndler
     * is null.
     * @exception SAXException If the underlying pbrser throws b SAXException
     * while pbrsing.
     * @see org.xml.sbx.helpers.DefbultHbndler
     */
    public void pbrse(InputStrebm src, DefbultHbndler hbndler)
            throws SAXException, IOException {
        if ((src == null) || (hbndler == null)) {
            throw new IllegblArgumentException("");
        }
        pbrse(new InputSource(src), hbndler);
    }

    /**
     * Pbrse the content given {@link org.xml.sbx.InputSource} bs XML using the
     * specified {@link org.xml.sbx.helpers.DefbultHbndler}.
     *
     * @pbrbm is The InputSource contbining the content to be pbrsed.
     * @pbrbm hbndler The SAX DefbultHbndler to use.
     * @exception IOException If bny IO errors occur.
     * @exception IllegblArgumentException If the InputSource or hbndler is
     * null.
     * @exception SAXException If the underlying pbrser throws b SAXException
     * while pbrsing.
     * @see org.xml.sbx.helpers.DefbultHbndler
     */
    public void pbrse(InputSource is, DefbultHbndler hbndler)
        throws SAXException, IOException
    {
        if ((is == null) || (hbndler == null)) {
            throw new IllegblArgumentException("");
        }
        //              Set up the hbndler
        mHbndCont = hbndler;
        mHbndDtd = hbndler;
        mHbndErr = hbndler;
        mHbndEnt = hbndler;
        //              Set up the document
        mInp = new Input(BUFFSIZE_READER);
        mPh = PH_BEFORE_DOC;  // before pbrsing
        try {
            setinp(is);
        } cbtch (SAXException | IOException | RuntimeException sbxe) {
            throw sbxe;
        } cbtch (Exception e) {
            pbnic(e.toString());
        }
        pbrse();
    }

    /**
     * Pbrse the XML document content using specified hbndlers bnd bn input
     * source.
     *
     * @exception IOException If bny IO errors occur.
     * @exception SAXException If the underlying pbrser throws b SAXException
     * while pbrsing.
     */
    @SuppressWbrnings("fbllthrough")
    privbte void pbrse() throws SAXException, IOException {
        init();
        try {
            mHbndCont.setDocumentLocbtor(this);
            mHbndCont.stbrtDocument();

            if (mPh != PH_MISC_DTD) {
                mPh = PH_MISC_DTD;  // misc before DTD
            }
            int evt = EV_NULL;
            //          XML document prolog
            do {
                wsskip();
                switch (evt = step()) {
                    cbse EV_ELM:
                    cbse EV_ELMS:
                        mPh = PH_DOCELM;
                        brebk;

                    cbse EV_COMM:
                    cbse EV_PI:
                        brebk;

                    cbse EV_DTD:
                        if (mPh >= PH_DTD_MISC) {
                            pbnic(FAULT);
                        }
                        mPh = PH_DTD_MISC;  // misc bfter DTD
                        brebk;

                    defbult:
                        pbnic(FAULT);
                }
            } while (mPh < PH_DOCELM);  // misc before DTD
            //          XML document stbrting with document's element
            do {
                switch (evt) {
                    cbse EV_ELM:
                    cbse EV_ELMS:
                        //              Report the element
                        if (mIsNSAwbre == true) {
                            mHbndCont.stbrtElement(
                                    mElm.vblue,
                                    mElm.nbme,
                                    "",
                                    mAttrs);
                        } else {
                            mHbndCont.stbrtElement(
                                    "",
                                    "",
                                    mElm.nbme,
                                    mAttrs);
                        }
                        if (evt == EV_ELMS) {
                            evt = step();
                            brebk;
                        }

                    cbse EV_ELME:
                        //              Report the end of element
                        if (mIsNSAwbre == true) {
                            mHbndCont.endElement(mElm.vblue, mElm.nbme, "");
                        } else {
                            mHbndCont.endElement("", "", mElm.nbme);
                        }
                        //              Restore the top of the prefix stbck
                        while (mPref.list == mElm) {
                            mHbndCont.endPrefixMbpping(mPref.nbme);
                            mPref = del(mPref);
                        }
                        //              Remove the top element tbg
                        mElm = del(mElm);
                        if (mElm == null) {
                            mPh = PH_DOCELM_MISC;
                        } else {
                            evt = step();
                        }
                        brebk;

                    cbse EV_TEXT:
                    cbse EV_WSPC:
                    cbse EV_CDAT:
                    cbse EV_COMM:
                    cbse EV_PI:
                    cbse EV_ENT:
                        evt = step();
                        brebk;

                    defbult:
                        pbnic(FAULT);
                }
            } while (mPh == PH_DOCELM);
            //          Misc bfter document's element
            do {
                if (wsskip() == EOS) {
                    brebk;
                }

                switch (step()) {
                    cbse EV_COMM:
                    cbse EV_PI:
                        brebk;

                    defbult:
                        pbnic(FAULT);
                }
            } while (mPh == PH_DOCELM_MISC);
            mPh = PH_AFTER_DOC;  // pbrsing is completed

        } cbtch (SAXException sbxe) {
            throw sbxe;
        } cbtch (IOException ioe) {
            throw ioe;
        } cbtch (RuntimeException rte) {
            throw rte;
        } cbtch (Exception e) {
            pbnic(e.toString());
        } finblly {
            mHbndCont.endDocument();
            clebnup();
        }
    }

    /**
     * Reports document type.
     *
     * @pbrbm nbme The nbme of the entity.
     * @pbrbm pubid The public identifier of the entity or <code>null</code>.
     * @pbrbm sysid The system identifier of the entity or <code>null</code>.
     */
    protected void docType(String nbme, String pubid, String sysid) throws SAXException {
        mHbndDtd.notbtionDecl(nbme, pubid, sysid);
    }

    /**
     * Reports b comment.
     *
     * @pbrbm text The comment text stbrting from first chbrcbter.
     * @pbrbm length The number of chbrbcters in comment.
     */
    protected void comm(chbr[] text, int length) {
    }

    /**
     * Reports b processing instruction.
     *
     * @pbrbm tbrget The processing instruction tbrget nbme.
     * @pbrbm body The processing instruction body text.
     */
    protected void pi(String tbrget, String body) throws SAXException {
        mHbndCont.processingInstruction(tbrget, body);
    }

    /**
     * Reports new nbmespbce prefix. The Nbmespbce prefix (
     * <code>mPref.nbme</code>) being declbred bnd the Nbmespbce URI (
     * <code>mPref.vblue</code>) the prefix is mbpped to. An empty string is
     * used for the defbult element nbmespbce, which hbs no prefix.
     */
    protected void newPrefix() throws SAXException {
        mHbndCont.stbrtPrefixMbpping(mPref.nbme, mPref.vblue);
    }

    /**
     * Reports skipped entity nbme.
     *
     * @pbrbm nbme The entity nbme.
     */
    protected void skippedEnt(String nbme) throws SAXException {
        mHbndCont.skippedEntity(nbme);
    }

    /**
     * Returns bn
     * <code>InputSource</code> for specified entity or
     * <code>null</code>.
     *
     * @pbrbm nbme The nbme of the entity.
     * @pbrbm pubid The public identifier of the entity.
     * @pbrbm sysid The system identifier of the entity.
     */
    protected InputSource resolveEnt(String nbme, String pubid, String sysid)
        throws SAXException, IOException
    {
        return mHbndEnt.resolveEntity(pubid, sysid);
    }

    /**
     * Reports notbtion declbrbtion.
     *
     * @pbrbm nbme The notbtion's nbme.
     * @pbrbm pubid The notbtion's public identifier, or null if none wbs given.
     * @pbrbm sysid The notbtion's system identifier, or null if none wbs given.
     */
    protected void notDecl(String nbme, String pubid, String sysid)
        throws SAXException
    {
        mHbndDtd.notbtionDecl(nbme, pubid, sysid);
    }

    /**
     * Reports unpbrsed entity nbme.
     *
     * @pbrbm nbme The unpbrsed entity's nbme.
     * @pbrbm pubid The entity's public identifier, or null if none wbs given.
     * @pbrbm sysid The entity's system identifier.
     * @pbrbm notbtion The nbme of the bssocibted notbtion.
     */
    protected void unpbrsedEntDecl(String nbme, String pubid, String sysid, String notbtion)
        throws SAXException
    {
        mHbndDtd.unpbrsedEntityDecl(nbme, pubid, sysid, notbtion);
    }

    /**
     * Notifies the hbndler bbout fbtbl pbrsing error.
     *
     * @pbrbm msg The problem description messbge.
     */
    protected void pbnic(String msg) throws SAXException {
        SAXPbrseException spe = new SAXPbrseException(msg, this);
        mHbndErr.fbtblError(spe);
        throw spe;  // [#1.2] fbtbl error definition
    }

    /**
     * Reports chbrbcters bnd empties the pbrser's buffer. This method is cblled
     * only if pbrser is going to return control to the mbin loop. This mebns
     * thbt this method mby use pbrser buffer to report white spbce without
     * copeing chbrbcters to temporbry buffer.
     */
    protected void bflbsh() throws SAXException {
        if (mBuffIdx >= 0) {
            //          Textubl dbtb hbs been rebd
            mHbndCont.chbrbcters(mBuff, 0, (mBuffIdx + 1));
            mBuffIdx = -1;
        }
    }

    /**
     * Reports white spbce chbrbcters bnd empties the pbrser's buffer. This
     * method is cblled only if pbrser is going to return control to the mbin
     * loop. This mebns thbt this method mby use pbrser buffer to report white
     * spbce without copeing chbrbcters to temporbry buffer.
     */
    protected void bflbsh_ws() throws SAXException {
        if (mBuffIdx >= 0) {
            // BUG: With bdditionbl info from DTD bnd xml:spbce bttr [#2.10]
            // the following cbll cbn be supported:
            // mHbndCont.ignorbbleWhitespbce(mBuff, 0, (mBuffIdx + 1));

            //          Textubl dbtb hbs been rebd
            mHbndCont.chbrbcters(mBuff, 0, (mBuffIdx + 1));
            mBuffIdx = -1;
        }
    }

    public boolebn getFebture(String nbme) {
        throw new UnsupportedOperbtionException("Not supported yet.");
    }

    public void setFebture(String nbme, boolebn vblue) {
        throw new UnsupportedOperbtionException("Not supported yet.");
    }

    public Object getProperty(String nbme) {
        throw new UnsupportedOperbtionException("Not supported yet.");
    }

    public void setProperty(String nbme, Object vblue) {
        throw new UnsupportedOperbtionException("Not supported yet.");
    }
}
