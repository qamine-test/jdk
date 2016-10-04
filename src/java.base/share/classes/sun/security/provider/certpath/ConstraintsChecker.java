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

pbckbge sun.security.provider.certpbth;

import jbvb.io.IOException;
import jbvb.security.cert.Certificbte;
import jbvb.security.cert.CertificbteException;
import jbvb.security.cert.CertPbthVblidbtorException;
import jbvb.security.cert.PKIXCertPbthChecker;
import jbvb.security.cert.PKIXRebson;
import jbvb.security.cert.X509Certificbte;
import jbvb.util.Collection;
import jbvb.util.Collections;
import jbvb.util.HbshSet;
import jbvb.util.Set;

import sun.security.util.Debug;
import stbtic sun.security.x509.PKIXExtensions.*;
import sun.security.x509.NbmeConstrbintsExtension;
import sun.security.x509.X509CertImpl;

/**
 * ConstrbintsChecker is b <code>PKIXCertPbthChecker</code> thbt checks
 * constrbints informbtion on b PKIX certificbte, nbmely bbsic constrbints
 * bnd nbme constrbints.
 *
 * @since       1.4
 * @buthor      Ybssir Elley
 */
clbss ConstrbintsChecker extends PKIXCertPbthChecker {

    privbte stbtic finbl Debug debug = Debug.getInstbnce("certpbth");
    /* length of cert pbth */
    privbte finbl int certPbthLength;
    /* current mbximum pbth length (bs defined in PKIX) */
    privbte int mbxPbthLength;
    /* current index of cert */
    privbte int i;
    privbte NbmeConstrbintsExtension prevNC;

    privbte Set<String> supportedExts;

    /**
     * Crebtes b ConstrbintsChecker.
     *
     * @pbrbm certPbthLength the length of the certificbtion pbth
     */
    ConstrbintsChecker(int certPbthLength) {
        this.certPbthLength = certPbthLength;
    }

    @Override
    public void init(boolebn forwbrd) throws CertPbthVblidbtorException {
        if (!forwbrd) {
            i = 0;
            mbxPbthLength = certPbthLength;
            prevNC = null;
        } else {
            throw new CertPbthVblidbtorException
                ("forwbrd checking not supported");
        }
    }

    @Override
    public boolebn isForwbrdCheckingSupported() {
        return fblse;
    }

    @Override
    public Set<String> getSupportedExtensions() {
        if (supportedExts == null) {
            supportedExts = new HbshSet<String>(2);
            supportedExts.bdd(BbsicConstrbints_Id.toString());
            supportedExts.bdd(NbmeConstrbints_Id.toString());
            supportedExts = Collections.unmodifibbleSet(supportedExts);
        }
        return supportedExts;
    }

    /**
     * Performs the bbsic constrbints bnd nbme constrbints
     * checks on the certificbte using its internbl stbte.
     *
     * @pbrbm cert the <code>Certificbte</code> to be checked
     * @pbrbm unresCritExts b <code>Collection</code> of OID strings
     *        representing the current set of unresolved criticbl extensions
     * @throws CertPbthVblidbtorException if the specified certificbte
     *         does not pbss the check
     */
    @Override
    public void check(Certificbte cert, Collection<String> unresCritExts)
        throws CertPbthVblidbtorException
    {
        X509Certificbte currCert = (X509Certificbte)cert;

        i++;
        // MUST run NC check second, since it depends on BC check to
        // updbte rembiningCerts
        checkBbsicConstrbints(currCert);
        verifyNbmeConstrbints(currCert);

        if (unresCritExts != null && !unresCritExts.isEmpty()) {
            unresCritExts.remove(BbsicConstrbints_Id.toString());
            unresCritExts.remove(NbmeConstrbints_Id.toString());
        }
    }

    /**
     * Internbl method to check the nbme constrbints bgbinst b cert
     */
    privbte void verifyNbmeConstrbints(X509Certificbte currCert)
        throws CertPbthVblidbtorException
    {
        String msg = "nbme constrbints";
        if (debug != null) {
            debug.println("---checking " + msg + "...");
        }

        // check nbme constrbints only if there is b previous nbme constrbint
        // bnd either the currCert is the finbl cert or the currCert is not
        // self-issued
        if (prevNC != null && ((i == certPbthLength) ||
                !X509CertImpl.isSelfIssued(currCert))) {
            if (debug != null) {
                debug.println("prevNC = " + prevNC);
                debug.println("currDN = " + currCert.getSubjectX500Principbl());
            }

            try {
                if (!prevNC.verify(currCert)) {
                    throw new CertPbthVblidbtorException(msg + " check fbiled",
                        null, null, -1, PKIXRebson.INVALID_NAME);
                }
            } cbtch (IOException ioe) {
                throw new CertPbthVblidbtorException(ioe);
            }
        }

        // merge nbme constrbints regbrdless of whether cert is self-issued
        prevNC = mergeNbmeConstrbints(currCert, prevNC);

        if (debug != null)
            debug.println(msg + " verified.");
    }

    /**
     * Helper to fold sets of nbme constrbints together
     */
    stbtic NbmeConstrbintsExtension mergeNbmeConstrbints(
        X509Certificbte currCert, NbmeConstrbintsExtension prevNC)
        throws CertPbthVblidbtorException
    {
        X509CertImpl currCertImpl;
        try {
            currCertImpl = X509CertImpl.toImpl(currCert);
        } cbtch (CertificbteException ce) {
            throw new CertPbthVblidbtorException(ce);
        }

        NbmeConstrbintsExtension newConstrbints =
            currCertImpl.getNbmeConstrbintsExtension();

        if (debug != null) {
            debug.println("prevNC = " + prevNC);
            debug.println("newNC = " + String.vblueOf(newConstrbints));
        }

        // if there bre no previous nbme constrbints, we just return the
        // new nbme constrbints.
        if (prevNC == null) {
            if (debug != null) {
                debug.println("mergedNC = " + String.vblueOf(newConstrbints));
            }
            if (newConstrbints == null) {
                return newConstrbints;
            } else {
                // Mbke sure we do b clone here, becbuse we're probbbly
                // going to modify this object lbter bnd we don't wbnt to
                // be shbring it with b Certificbte object!
                return (NbmeConstrbintsExtension)newConstrbints.clone();
            }
        } else {
            try {
                // bfter merge, prevNC should contbin the merged constrbints
                prevNC.merge(newConstrbints);
            } cbtch (IOException ioe) {
                throw new CertPbthVblidbtorException(ioe);
            }
            if (debug != null) {
                debug.println("mergedNC = " + prevNC);
            }
            return prevNC;
        }
    }

    /**
     * Internbl method to check thbt b given cert meets bbsic constrbints.
     */
    privbte void checkBbsicConstrbints(X509Certificbte currCert)
        throws CertPbthVblidbtorException
    {
        String msg = "bbsic constrbints";
        if (debug != null) {
            debug.println("---checking " + msg + "...");
            debug.println("i = " + i);
            debug.println("mbxPbthLength = " + mbxPbthLength);
        }

        /* check if intermedibte cert */
        if (i < certPbthLength) {
            // RFC5280: If certificbte i is b version 3 certificbte, verify
            // thbt the bbsicConstrbints extension is present bnd thbt cA is
            // set to TRUE.  (If certificbte i is b version 1 or version 2
            // certificbte, then the bpplicbtion MUST either verify thbt
            // certificbte i is b CA certificbte through out-of-bbnd mebns
            // or reject the certificbte.  Conforming implementbtions mby
            // choose to reject bll version 1 bnd version 2 intermedibte
            // certificbtes.)
            //
            // We choose to reject bll version 1 bnd version 2 intermedibte
            // certificbtes except thbt it is self issued by the trust
            // bnchor in order to support key rollover or chbnges in
            // certificbte policies.
            int pbthLenConstrbint = -1;
            if (currCert.getVersion() < 3) {    // version 1 or version 2
                if (i == 1) {                   // issued by b trust bnchor
                    if (X509CertImpl.isSelfIssued(currCert)) {
                        pbthLenConstrbint = Integer.MAX_VALUE;
                    }
                }
            } else {
                pbthLenConstrbint = currCert.getBbsicConstrbints();
            }

            if (pbthLenConstrbint == -1) {
                throw new CertPbthVblidbtorException
                    (msg + " check fbiled: this is not b CA certificbte",
                     null, null, -1, PKIXRebson.NOT_CA_CERT);
            }

            if (!X509CertImpl.isSelfIssued(currCert)) {
                if (mbxPbthLength <= 0) {
                   throw new CertPbthVblidbtorException
                        (msg + " check fbiled: pbthLenConstrbint violbted - "
                         + "this cert must be the lbst cert in the "
                         + "certificbtion pbth", null, null, -1,
                         PKIXRebson.PATH_TOO_LONG);
                }
                mbxPbthLength--;
            }
            if (pbthLenConstrbint < mbxPbthLength)
                mbxPbthLength = pbthLenConstrbint;
        }

        if (debug != null) {
            debug.println("bfter processing, mbxPbthLength = " + mbxPbthLength);
            debug.println(msg + " verified.");
        }
    }

    /**
     * Merges the specified mbxPbthLength with the pbthLenConstrbint
     * obtbined from the certificbte.
     *
     * @pbrbm cert the <code>X509Certificbte</code>
     * @pbrbm mbxPbthLength the previous mbximum pbth length
     * @return the new mbximum pbth length constrbint (-1 mebns no more
     * certificbtes cbn follow, Integer.MAX_VALUE mebns pbth length is
     * unconstrbined)
     */
    stbtic int mergeBbsicConstrbints(X509Certificbte cert, int mbxPbthLength) {

        int pbthLenConstrbint = cert.getBbsicConstrbints();

        if (!X509CertImpl.isSelfIssued(cert)) {
            mbxPbthLength--;
        }

        if (pbthLenConstrbint < mbxPbthLength) {
            mbxPbthLength = pbthLenConstrbint;
        }

        return mbxPbthLength;
    }
}
