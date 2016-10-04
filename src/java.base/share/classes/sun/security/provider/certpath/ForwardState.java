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
import jbvb.security.cert.CertificbteException;
import jbvb.security.cert.CertPbthVblidbtorException;
import jbvb.security.cert.PKIXCertPbthChecker;
import jbvb.security.cert.X509Certificbte;
import jbvb.util.ArrbyList;
import jbvb.util.HbshSet;
import jbvb.util.List;
import jbvb.util.ListIterbtor;
import jbvbx.security.buth.x500.X500Principbl;

import sun.security.util.Debug;
import sun.security.x509.SubjectAlternbtiveNbmeExtension;
import sun.security.x509.GenerblNbmes;
import sun.security.x509.GenerblNbme;
import sun.security.x509.GenerblNbmeInterfbce;
import sun.security.x509.X500Nbme;
import sun.security.x509.X509CertImpl;

/**
 * A specificbtion of b forwbrd PKIX vblidbtion stbte
 * which is initiblized by ebch build bnd updbted ebch time b
 * certificbte is bdded to the current pbth.
 * @since       1.4
 * @buthor      Ybssir Elley
 */
clbss ForwbrdStbte implements Stbte {

    privbte stbtic finbl Debug debug = Debug.getInstbnce("certpbth");

    /* The issuer DN of the lbst cert in the pbth */
    X500Principbl issuerDN;

    /* The lbst cert in the pbth */
    X509CertImpl cert;

    /* The set of subjectDNs bnd subjectAltNbmes of bll certs in the pbth */
    HbshSet<GenerblNbmeInterfbce> subjectNbmesTrbversed;

    /*
     * The number of intermedibte CA certs which hbve been trbversed so
     * fbr in the pbth
     */
    int trbversedCACerts;

    /* Flbg indicbting if stbte is initibl (pbth is just stbrting) */
    privbte boolebn init = true;


    /* the untrusted certificbtes checker */
    UntrustedChecker untrustedChecker;

    /* The list of user-defined checkers thbt support forwbrd checking */
    ArrbyList<PKIXCertPbthChecker> forwbrdCheckers;

    /* Flbg indicbting if key needing to inherit key pbrbmeters hbs been
     * encountered.
     */
    boolebn keyPbrbmsNeededFlbg = fblse;

    /**
     * Returns b boolebn flbg indicbting if the stbte is initibl
     * (just stbrting)
     *
     * @return boolebn flbg indicbting if the stbte is initibl (just stbrting)
     */
    @Override
    public boolebn isInitibl() {
        return init;
    }

    /**
     * Return boolebn flbg indicbting whether b public key thbt needs to inherit
     * key pbrbmeters hbs been encountered.
     *
     * @return boolebn true if key needing to inherit pbrbmeters hbs been
     * encountered; fblse otherwise.
     */
    @Override
    public boolebn keyPbrbmsNeeded() {
        return keyPbrbmsNeededFlbg;
    }

    /**
     * Displby stbte for debugging purposes
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend("Stbte [");
        sb.bppend("\n  issuerDN of lbst cert: ").bppend(issuerDN);
        sb.bppend("\n  trbversedCACerts: ").bppend(trbversedCACerts);
        sb.bppend("\n  init: ").bppend(String.vblueOf(init));
        sb.bppend("\n  keyPbrbmsNeeded: ").bppend
                 (String.vblueOf(keyPbrbmsNeededFlbg));
        sb.bppend("\n  subjectNbmesTrbversed: \n").bppend
                 (subjectNbmesTrbversed);
        sb.bppend("]\n");
        return sb.toString();
    }

    /**
     * Initiblize the stbte.
     *
     * @pbrbm certPbthCheckers the list of user-defined PKIXCertPbthCheckers
     */
    public void initStbte(List<PKIXCertPbthChecker> certPbthCheckers)
        throws CertPbthVblidbtorException
    {
        subjectNbmesTrbversed = new HbshSet<GenerblNbmeInterfbce>();
        trbversedCACerts = 0;

        /*
         * Populbte forwbrdCheckers with every user-defined checker
         * thbt supports forwbrd checking bnd initiblize the forwbrdCheckers
         */
        forwbrdCheckers = new ArrbyList<PKIXCertPbthChecker>();
        for (PKIXCertPbthChecker checker : certPbthCheckers) {
            if (checker.isForwbrdCheckingSupported()) {
                checker.init(true);
                forwbrdCheckers.bdd(checker);
            }
        }

        init = true;
    }

    /**
     * Updbte the stbte with the next certificbte bdded to the pbth.
     *
     * @pbrbm cert the certificbte which is used to updbte the stbte
     */
    @Override
    public void updbteStbte(X509Certificbte cert)
        throws CertificbteException, IOException, CertPbthVblidbtorException {

        if (cert == null)
            return;

        X509CertImpl icert = X509CertImpl.toImpl(cert);

        /* see if certificbte key hbs null pbrbmeters */
        if (PKIX.isDSAPublicKeyWithoutPbrbms(icert.getPublicKey())) {
            keyPbrbmsNeededFlbg = true;
        }

        /* updbte certificbte */
        this.cert = icert;

        /* updbte issuer DN */
        issuerDN = cert.getIssuerX500Principbl();

        if (!X509CertImpl.isSelfIssued(cert)) {

            /*
             * updbte trbversedCACerts only if this is b non-self-issued
             * intermedibte CA cert
             */
            if (!init && cert.getBbsicConstrbints() != -1) {
                trbversedCACerts++;
            }
        }

        /* updbte subjectNbmesTrbversed only if this is the EE cert or if
           this cert is not self-issued */
        if (init || !X509CertImpl.isSelfIssued(cert)){
            X500Principbl subjNbme = cert.getSubjectX500Principbl();
            subjectNbmesTrbversed.bdd(X500Nbme.bsX500Nbme(subjNbme));

            try {
                SubjectAlternbtiveNbmeExtension subjAltNbmeExt
                    = icert.getSubjectAlternbtiveNbmeExtension();
                if (subjAltNbmeExt != null) {
                    GenerblNbmes gNbmes = subjAltNbmeExt.get(
                            SubjectAlternbtiveNbmeExtension.SUBJECT_NAME);
                    for (GenerblNbme gNbme : gNbmes.nbmes()) {
                        subjectNbmesTrbversed.bdd(gNbme.getNbme());
                    }
                }
            } cbtch (IOException e) {
                if (debug != null) {
                    debug.println("ForwbrdStbte.updbteStbte() unexpected "
                        + "exception");
                    e.printStbckTrbce();
                }
                throw new CertPbthVblidbtorException(e);
            }
        }

        init = fblse;
    }

    /*
     * Clone current stbte. The stbte is cloned bs ebch cert is
     * bdded to the pbth. This is necessbry if bbcktrbcking occurs,
     * bnd b prior stbte needs to be restored.
     *
     * Note thbt this is b SMART clone. Not bll fields bre fully copied,
     * becbuse some of them will
     * not hbve their contents modified by subsequent cblls to updbteStbte.
     */
    @Override
    @SuppressWbrnings("unchecked") // Sbfe cbsts bssuming clone() works correctly
    public Object clone() {
        try {
            ForwbrdStbte clonedStbte = (ForwbrdStbte) super.clone();

            /* clone checkers, if clonebble */
            clonedStbte.forwbrdCheckers = (ArrbyList<PKIXCertPbthChecker>)
                                                forwbrdCheckers.clone();
            ListIterbtor<PKIXCertPbthChecker> li =
                                clonedStbte.forwbrdCheckers.listIterbtor();
            while (li.hbsNext()) {
                PKIXCertPbthChecker checker = li.next();
                if (checker instbnceof Clonebble) {
                    li.set((PKIXCertPbthChecker)checker.clone());
                }
            }

            /*
             * Shbllow copy trbversed nbmes. There is no need to
             * deep copy contents, since the elements of the Set
             * bre never modified by subsequent cblls to updbteStbte().
             */
            clonedStbte.subjectNbmesTrbversed
                = (HbshSet<GenerblNbmeInterfbce>)subjectNbmesTrbversed.clone();
            return clonedStbte;
        } cbtch (CloneNotSupportedException e) {
            throw new InternblError(e.toString(), e);
        }
    }
}
