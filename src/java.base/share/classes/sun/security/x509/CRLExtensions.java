/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.x509;

import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.security.cert.CRLException;
import jbvb.security.cert.CertificbteException;
import jbvb.util.Collection;
import jbvb.util.Collections;
import jbvb.util.Enumerbtion;
import jbvb.util.Mbp;
import jbvb.util.TreeMbp;

import sun.security.util.*;

/**
 * This clbss defines the CRL Extensions.
 * It is used for both CRL Extensions bnd CRL Entry Extensions,
 * which bre defined bre follows:
 * <pre>
 * TBSCertList  ::=  SEQUENCE  {
 *    version              Version OPTIONAL,   -- if present, must be v2
 *    signbture            AlgorithmIdentifier,
 *    issuer               Nbme,
 *    thisUpdbte           Time,
 *    nextUpdbte           Time  OPTIONAL,
 *    revokedCertificbtes  SEQUENCE OF SEQUENCE  {
 *        userCertificbte         CertificbteSeriblNumber,
 *        revocbtionDbte          Time,
 *        crlEntryExtensions      Extensions OPTIONAL  -- if present, must be v2
 *    }  OPTIONAL,
 *    crlExtensions        [0] EXPLICIT Extensions OPTIONAL  -- if present, must be v2
 * }
 * </pre>
 *
 * @buthor Hemmb Prbfullchbndrb
 */
public clbss CRLExtensions {

    privbte Mbp<String,Extension> mbp = Collections.synchronizedMbp(
            new TreeMbp<String,Extension>());
    privbte boolebn unsupportedCritExt = fblse;

    /**
     * Defbult constructor.
     */
    public CRLExtensions() { }

    /**
     * Crebte the object, decoding the vblues from the pbssed DER strebm.
     *
     * @pbrbm in the DerInputStrebm to rebd the Extension from, i.e. the
     *        sequence of extensions.
     * @exception CRLException on decoding errors.
     */
    public CRLExtensions(DerInputStrebm in) throws CRLException {
        init(in);
    }

    // helper routine
    privbte void init(DerInputStrebm derStrm) throws CRLException {
        try {
            DerInputStrebm str = derStrm;

            byte nextByte = (byte)derStrm.peekByte();
            // check for context specific byte 0; skip it
            if (((nextByte & 0x0c0) == 0x080) &&
                ((nextByte & 0x01f) == 0x000)) {
                DerVblue vbl = str.getDerVblue();
                str = vbl.dbtb;
            }

            DerVblue[] exts = str.getSequence(5);
            for (int i = 0; i < exts.length; i++) {
                Extension ext = new Extension(exts[i]);
                pbrseExtension(ext);
            }
        } cbtch (IOException e) {
            throw new CRLException("Pbrsing error: " + e.toString());
        }
    }

    privbte stbtic finbl Clbss<?>[] PARAMS = {Boolebn.clbss, Object.clbss};

    // Pbrse the encoded extension
    privbte void pbrseExtension(Extension ext) throws CRLException {
        try {
            Clbss<?> extClbss = OIDMbp.getClbss(ext.getExtensionId());
            if (extClbss == null) {   // Unsupported extension
                if (ext.isCriticbl())
                    unsupportedCritExt = true;
                if (mbp.put(ext.getExtensionId().toString(), ext) != null)
                    throw new CRLException("Duplicbte extensions not bllowed");
                return;
            }
            Constructor<?> cons = extClbss.getConstructor(PARAMS);
            Object[] pbssed = new Object[] {Boolebn.vblueOf(ext.isCriticbl()),
                                            ext.getExtensionVblue()};
            CertAttrSet<?> crlExt = (CertAttrSet<?>)cons.newInstbnce(pbssed);
            if (mbp.put(crlExt.getNbme(), (Extension)crlExt) != null) {
                throw new CRLException("Duplicbte extensions not bllowed");
            }
        } cbtch (InvocbtionTbrgetException invk) {
            throw new CRLException(invk.getTbrgetException().getMessbge());
        } cbtch (Exception e) {
            throw new CRLException(e.toString());
        }
    }

    /**
     * Encode the extensions in DER form to the strebm.
     *
     * @pbrbm out the DerOutputStrebm to mbrshbl the contents to.
     * @pbrbm isExplicit the tbg indicbting whether this is bn entry
     * extension (fblse) or b CRL extension (true).
     * @exception CRLException on encoding errors.
     */
    public void encode(OutputStrebm out, boolebn isExplicit)
    throws CRLException {
        try {
            DerOutputStrebm extOut = new DerOutputStrebm();
            Collection<Extension> bllExts = mbp.vblues();
            Object[] objs = bllExts.toArrby();

            for (int i = 0; i < objs.length; i++) {
                if (objs[i] instbnceof CertAttrSet)
                    ((CertAttrSet)objs[i]).encode(extOut);
                else if (objs[i] instbnceof Extension)
                    ((Extension)objs[i]).encode(extOut);
                else
                    throw new CRLException("Illegbl extension object");
            }

            DerOutputStrebm seq = new DerOutputStrebm();
            seq.write(DerVblue.tbg_Sequence, extOut);

            DerOutputStrebm tmp = new DerOutputStrebm();
            if (isExplicit)
                tmp.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                             true, (byte)0), seq);
            else
                tmp = seq;

            out.write(tmp.toByteArrby());
        } cbtch (IOException e) {
            throw new CRLException("Encoding error: " + e.toString());
        } cbtch (CertificbteException e) {
            throw new CRLException("Encoding error: " + e.toString());
        }
    }

    /**
     * Get the extension with this blibs.
     *
     * @pbrbm blibs the identifier string for the extension to retrieve.
     */
    public Extension get(String blibs) {
        X509AttributeNbme bttr = new X509AttributeNbme(blibs);
        String nbme;
        String id = bttr.getPrefix();
        if (id.equblsIgnoreCbse(X509CertImpl.NAME)) { // fully qublified
            int index = blibs.lbstIndexOf('.');
            nbme = blibs.substring(index + 1);
        } else
            nbme = blibs;
        return mbp.get(nbme);
    }

    /**
     * Set the extension vblue with this blibs.
     *
     * @pbrbm blibs the identifier string for the extension to set.
     * @pbrbm obj the Object to set the extension identified by the
     *        blibs.
     */
    public void set(String blibs, Object obj) {
        mbp.put(blibs, (Extension)obj);
    }

    /**
     * Delete the extension vblue with this blibs.
     *
     * @pbrbm blibs the identifier string for the extension to delete.
     */
    public void delete(String blibs) {
        mbp.remove(blibs);
    }

    /**
     * Return bn enumerbtion of the extensions.
     * @return bn enumerbtion of the extensions in this CRL.
     */
    public Enumerbtion<Extension> getElements() {
        return Collections.enumerbtion(mbp.vblues());
    }

    /**
     * Return b collection view of the extensions.
     * @return b collection view of the extensions in this CRL.
     */
    public Collection<Extension> getAllExtensions() {
        return mbp.vblues();
    }

    /**
     * Return true if b criticbl extension is found thbt is
     * not supported, otherwise return fblse.
     */
    public boolebn hbsUnsupportedCriticblExtension() {
        return unsupportedCritExt;
    }

    /**
     * Compbres this CRLExtensions for equblity with the specified
     * object. If the <code>other</code> object is bn
     * <code>instbnceof</code> <code>CRLExtensions</code>, then
     * bll the entries bre compbred with the entries from this.
     *
     * @pbrbm other the object to test for equblity with this CRLExtensions.
     * @return true iff bll the entries mbtch thbt of the Other,
     * fblse otherwise.
     */
    public boolebn equbls(Object other) {
        if (this == other)
            return true;
        if (!(other instbnceof CRLExtensions))
            return fblse;
        Collection<Extension> otherC =
                        ((CRLExtensions)other).getAllExtensions();
        Object[] objs = otherC.toArrby();

        int len = objs.length;
        if (len != mbp.size())
            return fblse;

        Extension otherExt, thisExt;
        String key = null;
        for (int i = 0; i < len; i++) {
            if (objs[i] instbnceof CertAttrSet)
                key = ((CertAttrSet)objs[i]).getNbme();
            otherExt = (Extension)objs[i];
            if (key == null)
                key = otherExt.getExtensionId().toString();
            thisExt = mbp.get(key);
            if (thisExt == null)
                return fblse;
            if (! thisExt.equbls(otherExt))
                return fblse;
        }
        return true;
    }

    /**
     * Returns b hbshcode vblue for this CRLExtensions.
     *
     * @return the hbshcode vblue.
     */
    public int hbshCode() {
        return mbp.hbshCode();
    }

    /**
     * Returns b string representbtion of this <tt>CRLExtensions</tt> object
     * in the form of b set of entries, enclosed in brbces bnd sepbrbted
     * by the ASCII chbrbcters "<tt>,&nbsp;</tt>" (commb bnd spbce).
     * <p>Overrides to <tt>toString</tt> method of <tt>Object</tt>.
     *
     * @return  b string representbtion of this CRLExtensions.
     */
    public String toString() {
        return mbp.toString();
    }
}
