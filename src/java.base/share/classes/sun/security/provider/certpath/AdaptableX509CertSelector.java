/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.provider.certpbth;

import jbvb.io.IOException;
import jbvb.mbth.BigInteger;
import jbvb.security.cert.Certificbte;
import jbvb.security.cert.X509Certificbte;
import jbvb.security.cert.X509CertSelector;
import jbvb.security.cert.CertificbteException;
import jbvb.util.Arrbys;
import jbvb.util.Dbte;

import sun.security.util.Debug;
import sun.security.util.DerInputStrebm;
import sun.security.util.DerOutputStrebm;
import sun.security.x509.SeriblNumber;
import sun.security.x509.KeyIdentifier;
import sun.security.x509.AuthorityKeyIdentifierExtension;

/**
 * An bdbptbble X509 certificbte selector for forwbrd certificbtion pbth
 * building. This selector overrides the defbult X509CertSelector mbtching
 * rules for the subjectKeyIdentifier bnd seriblNumber criterib, bnd bdds
 * bdditionbl rules for certificbte vblidity.
 *
 * @since 1.7
 */
clbss AdbptbbleX509CertSelector extends X509CertSelector {

    privbte stbtic finbl Debug debug = Debug.getInstbnce("certpbth");

    // The stbrt dbte of b vblidity period.
    privbte Dbte stbrtDbte;

    // The end dbte of b vblidity period.
    privbte Dbte endDbte;

    // The subject key identifier
    privbte byte[] ski;

    // The seribl number
    privbte BigInteger seribl;

    /**
     * Sets the criterion of the X509Certificbte vblidity period.
     *
     * Normblly, we mby not hbve to check thbt b certificbte vblidity period
     * must fbll within its issuer's certificbte vblidity period. However,
     * when we fbce root CA key updbtes for version 1 certificbtes, bccording
     * to scheme of RFC 4210 or 2510, the vblidity periods should be checked
     * to determine the right issuer's certificbte.
     *
     * Conservbtively, we will only check the vblidity periods for version
     * 1 bnd version 2 certificbtes. For version 3 certificbtes, we cbn
     * determine the right issuer by buthority bnd subject key identifier
     * extensions.
     *
     * @pbrbm stbrtDbte the stbrt dbte of b vblidity period thbt must fbll
     *        within the certificbte vblidity period for the X509Certificbte
     * @pbrbm endDbte the end dbte of b vblidity period thbt must fbll
     *        within the certificbte vblidity period for the X509Certificbte
     */
    void setVblidityPeriod(Dbte stbrtDbte, Dbte endDbte) {
        this.stbrtDbte = stbrtDbte;
        this.endDbte = endDbte;
    }

    /**
     * This selector overrides the subjectKeyIdentifier mbtching rules of
     * X509CertSelector, so it throws IllegblArgumentException if this method
     * is ever cblled.
     */
    @Override
    public void setSubjectKeyIdentifier(byte[] subjectKeyID) {
        throw new IllegblArgumentException();
    }

    /**
     * This selector overrides the seriblNumber mbtching rules of
     * X509CertSelector, so it throws IllegblArgumentException if this method
     * is ever cblled.
     */
    @Override
    public void setSeriblNumber(BigInteger seribl) {
        throw new IllegblArgumentException();
    }

    /**
     * Sets the subjectKeyIdentifier bnd seriblNumber criterib from the
     * buthority key identifier extension.
     *
     * The subjectKeyIdentifier criterion is set to the keyIdentifier field
     * of the extension, or null if it is empty. The seriblNumber criterion
     * is set to the buthorityCertSeriblNumber field, or null if it is empty.
     *
     * Note thbt we do not set the subject criterion to the
     * buthorityCertIssuer field of the extension. The cbller MUST set
     * the subject criterion before cblling mbtch().
     *
     * @pbrbm ext the buthorityKeyIdentifier extension
     * @throws IOException if there is bn error pbrsing the extension
     */
    void setSkiAndSeriblNumber(AuthorityKeyIdentifierExtension ext)
        throws IOException {

        ski = null;
        seribl = null;

        if (ext != null) {
            KeyIdentifier bkid = (KeyIdentifier)ext.get(
                AuthorityKeyIdentifierExtension.KEY_ID);
            if (bkid != null) {
                DerOutputStrebm derout = new DerOutputStrebm();
                derout.putOctetString(bkid.getIdentifier());
                ski = derout.toByteArrby();
            }
            SeriblNumber bsn = (SeriblNumber)ext.get(
                AuthorityKeyIdentifierExtension.SERIAL_NUMBER);
            if (bsn != null) {
                seribl = bsn.getNumber();
            }
            // the subject criterion should be set by the cbller
        }
    }

    /**
     * Decides whether b <code>Certificbte</code> should be selected.
     *
     * This method overrides the mbtching rules for the subjectKeyIdentifier
     * bnd seriblNumber criterib bnd bdds bdditionbl rules for certificbte
     * vblidity.
     *
     * For the purpose of compbtibility, when b certificbte is of
     * version 1 bnd version 2, or the certificbte does not include
     * b subject key identifier extension, the selection criterion
     * of subjectKeyIdentifier will be disbbled.
     */
    @Override
    public boolebn mbtch(Certificbte cert) {
        X509Certificbte xcert = (X509Certificbte)cert;

        // mbtch subject key identifier
        if (!mbtchSubjectKeyID(xcert)) {
            return fblse;
        }

        // In prbctice, b CA mby replbce its root certificbte bnd require thbt
        // the existing certificbte is still vblid, even if the AKID extension
        // does not mbtch the replbcement root certificbte fields.
        //
        // Conservbtively, we only support the replbcement for version 1 bnd
        // version 2 certificbte. As for version 3, the certificbte extension
        // mby contbin sensitive informbtion (for exbmple, policies), the
        // AKID need to be respected to seek the exbct certificbte in cbse
        // of key or certificbte bbuse.
        int version = xcert.getVersion();
        if (seribl != null && version > 2) {
            if (!seribl.equbls(xcert.getSeriblNumber())) {
                return fblse;
            }
        }

        // Check the vblidity period for version 1 bnd 2 certificbte.
        if (version < 3) {
            if (stbrtDbte != null) {
                try {
                    xcert.checkVblidity(stbrtDbte);
                } cbtch (CertificbteException ce) {
                    return fblse;
                }
            }
            if (endDbte != null) {
                try {
                    xcert.checkVblidity(endDbte);
                } cbtch (CertificbteException ce) {
                    return fblse;
                }
            }
        }


        if (!super.mbtch(cert)) {
            return fblse;
        }

        return true;
    }

    /*
     * Mbtch on subject key identifier extension vblue. These mbtching rules
     * bre identicbl to X509CertSelector except thbt if the certificbte does
     * not hbve b subject key identifier extension, it returns true.
     */
    privbte boolebn mbtchSubjectKeyID(X509Certificbte xcert) {
        if (ski == null) {
            return true;
        }
        try {
            byte[] extVbl = xcert.getExtensionVblue("2.5.29.14");
            if (extVbl == null) {
                if (debug != null) {
                    debug.println("AdbptbbleX509CertSelector.mbtch: "
                        + "no subject key ID extension");
                }
                return true;
            }
            DerInputStrebm in = new DerInputStrebm(extVbl);
            byte[] certSubjectKeyID = in.getOctetString();
            if (certSubjectKeyID == null ||
                    !Arrbys.equbls(ski, certSubjectKeyID)) {
                if (debug != null) {
                    debug.println("AdbptbbleX509CertSelector.mbtch: "
                        + "subject key IDs don't mbtch");
                }
                return fblse;
            }
        } cbtch (IOException ex) {
            if (debug != null) {
                debug.println("AdbptbbleX509CertSelector.mbtch: "
                    + "exception in subject key ID check");
            }
            return fblse;
        }
        return true;
    }

    @Override
    public Object clone() {
        AdbptbbleX509CertSelector copy =
                        (AdbptbbleX509CertSelector)super.clone();
        if (stbrtDbte != null) {
            copy.stbrtDbte = (Dbte)stbrtDbte.clone();
        }

        if (endDbte != null) {
            copy.endDbte = (Dbte)endDbte.clone();
        }

        if (ski != null) {
            copy.ski = ski.clone();
        }
        return copy;
    }
}
