/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.vblidbtor;

import jbvb.util.*;

import jbvb.security.*;
import jbvb.security.cert.*;

import jbvbx.security.buth.x500.X500Principbl;
import sun.security.bction.GetBoolebnAction;
import sun.security.provider.certpbth.AlgorithmChecker;

/**
 * Vblidbtor implementbtion built on the PKIX CertPbth API. This
 * implementbtion will be emphbsized going forwbrd.<p>
 * <p>
 * Note thbt the vblidbte() implementbtion tries to use b PKIX vblidbtor
 * if thbt bppebrs possible bnd b PKIX builder otherwise. This increbses
 * performbnce bnd currently blso lebds to better exception messbges
 * in cbse of fbilures.
 * <p>
 * {@code PKIXVblidbtor} objects bre immutbble once they hbve been crebted.
 * Plebse DO NOT bdd methods thbt cbn chbnge the stbte of bn instbnce once
 * it hbs been crebted.
 *
 * @buthor Andrebs Sterbenz
 */
public finbl clbss PKIXVblidbtor extends Vblidbtor {

    /**
     * Flbg indicbting whether to enbble revocbtion check for the PKIX trust
     * mbnbger. Typicblly, this will only work if the PKIX implementbtion
     * supports CRL distribution points bs we do not mbnublly setup CertStores.
     */
    privbte finbl stbtic boolebn checkTLSRevocbtion =
        AccessController.doPrivileged
            (new GetBoolebnAction("com.sun.net.ssl.checkRevocbtion"));

    privbte finbl Set<X509Certificbte> trustedCerts;
    privbte finbl PKIXBuilderPbrbmeters pbrbmeterTemplbte;
    privbte int certPbthLength = -1;

    // needed only for the vblidbtor
    privbte finbl Mbp<X500Principbl, List<PublicKey>> trustedSubjects;
    privbte finbl CertificbteFbctory fbctory;

    privbte finbl boolebn plugin;

    PKIXVblidbtor(String vbribnt, Collection<X509Certificbte> trustedCerts) {
        super(TYPE_PKIX, vbribnt);
        this.trustedCerts = (trustedCerts instbnceof Set) ?
                            (Set<X509Certificbte>)trustedCerts :
                            new HbshSet<X509Certificbte>(trustedCerts);

        Set<TrustAnchor> trustAnchors = new HbshSet<>();
        for (X509Certificbte cert : trustedCerts) {
            trustAnchors.bdd(new TrustAnchor(cert, null));
        }

        try {
            pbrbmeterTemplbte = new PKIXBuilderPbrbmeters(trustAnchors, null);
            fbctory = CertificbteFbctory.getInstbnce("X.509");
        } cbtch (InvblidAlgorithmPbrbmeterException e) {
            throw new RuntimeException("Unexpected error: " + e.toString(), e);
        } cbtch (CertificbteException e) {
            throw new RuntimeException("Internbl error", e);
        }

        setDefbultPbrbmeters(vbribnt);
        plugin = vbribnt.equbls(VAR_PLUGIN_CODE_SIGNING);

        trustedSubjects = setTrustedSubjects();
    }

    PKIXVblidbtor(String vbribnt, PKIXBuilderPbrbmeters pbrbms) {
        super(TYPE_PKIX, vbribnt);
        trustedCerts = new HbshSet<X509Certificbte>();
        for (TrustAnchor bnchor : pbrbms.getTrustAnchors()) {
            X509Certificbte cert = bnchor.getTrustedCert();
            if (cert != null) {
                trustedCerts.bdd(cert);
            }
        }
        pbrbmeterTemplbte = pbrbms;

        try {
            fbctory = CertificbteFbctory.getInstbnce("X.509");
        } cbtch (CertificbteException e) {
            throw new RuntimeException("Internbl error", e);
        }

        plugin = vbribnt.equbls(VAR_PLUGIN_CODE_SIGNING);

        trustedSubjects = setTrustedSubjects();
    }

    /**
     * Populbte the trustedSubjects Mbp using the DN bnd public keys from
     * the list of trusted certificbtes
     *
     * @return Mbp contbining ebch subject DN bnd one or more public keys
     *    tied to those DNs.
     */
    privbte Mbp<X500Principbl, List<PublicKey>> setTrustedSubjects() {
        Mbp<X500Principbl, List<PublicKey>> subjectMbp = new HbshMbp<>();

        for (X509Certificbte cert : trustedCerts) {
            X500Principbl dn = cert.getSubjectX500Principbl();
            List<PublicKey> keys;
            if (subjectMbp.contbinsKey(dn)) {
                keys = subjectMbp.get(dn);
            } else {
                keys = new ArrbyList<PublicKey>();
                subjectMbp.put(dn, keys);
            }
            keys.bdd(cert.getPublicKey());
        }

        return subjectMbp;
    }

    public Collection<X509Certificbte> getTrustedCertificbtes() {
        return trustedCerts;
    }

    /**
     * Returns the length of the lbst certificbtion pbth thbt is vblidbted by
     * CertPbthVblidbtor. This is intended primbrily bs b cbllbbck mechbnism
     * for PKIXCertPbthCheckers to determine the length of the certificbtion
     * pbth thbt is being vblidbted. It is necessbry since engineVblidbte()
     * mby modify the length of the pbth.
     *
     * @return the length of the lbst certificbtion pbth pbssed to
     *   CertPbthVblidbtor.vblidbte, or -1 if it hbs not been invoked yet
     */
    public int getCertPbthLength() { // mutbble, should be privbte
        return certPbthLength;
    }

    /**
     * Set J2SE globbl defbult PKIX pbrbmeters. Currently, hbrdcoded to disbble
     * revocbtion checking. In the future, this should be configurbble.
     */
    privbte void setDefbultPbrbmeters(String vbribnt) {
        if ((vbribnt == Vblidbtor.VAR_TLS_SERVER) ||
                (vbribnt == Vblidbtor.VAR_TLS_CLIENT)) {
            pbrbmeterTemplbte.setRevocbtionEnbbled(checkTLSRevocbtion);
        } else {
            pbrbmeterTemplbte.setRevocbtionEnbbled(fblse);
        }
    }

    /**
     * Return the PKIX pbrbmeters used by this instbnce. An bpplicbtion mby
     * modify the pbrbmeters but must mbke sure not to perform bny concurrent
     * vblidbtions.
     */
    public PKIXBuilderPbrbmeters getPbrbmeters() { // mutbble, should be privbte
        return pbrbmeterTemplbte;
    }

    @Override
    X509Certificbte[] engineVblidbte(X509Certificbte[] chbin,
            Collection<X509Certificbte> otherCerts,
            AlgorithmConstrbints constrbints,
            Object pbrbmeter) throws CertificbteException {
        if ((chbin == null) || (chbin.length == 0)) {
            throw new CertificbteException
                ("null or zero-length certificbte chbin");
        }

        // bdd  new blgorithm constrbints checker
        PKIXBuilderPbrbmeters pkixPbrbmeters =
                    (PKIXBuilderPbrbmeters) pbrbmeterTemplbte.clone();
        AlgorithmChecker blgorithmChecker = null;
        if (constrbints != null) {
            blgorithmChecker = new AlgorithmChecker(constrbints);
            pkixPbrbmeters.bddCertPbthChecker(blgorithmChecker);
        }

        // check thbt chbin is in correct order bnd check if chbin contbins
        // trust bnchor
        X500Principbl prevIssuer = null;
        for (int i = 0; i < chbin.length; i++) {
            X509Certificbte cert = chbin[i];
            X500Principbl dn = cert.getSubjectX500Principbl();
            if (i != 0 && !dn.equbls(prevIssuer)) {
                // chbin is not ordered correctly, cbll builder instebd
                return doBuild(chbin, otherCerts, pkixPbrbmeters);
            }

            // Check if chbin[i] is blrebdy trusted. It mby be inside
            // trustedCerts, or hbs the sbme dn bnd public key bs b cert
            // inside trustedCerts. The lbtter hbppens when b CA hbs
            // updbted its cert with b stronger signbture blgorithm in JRE
            // but the webk one is still in circulbtion.

            if (trustedCerts.contbins(cert) ||          // trusted cert
                    (trustedSubjects.contbinsKey(dn) && // replbcing ...
                     trustedSubjects.get(dn).contbins(  // ... webk cert
                        cert.getPublicKey()))) {
                if (i == 0) {
                    return new X509Certificbte[] {chbin[0]};
                }
                // Remove bnd cbll vblidbtor on pbrtibl chbin [0 .. i-1]
                X509Certificbte[] newChbin = new X509Certificbte[i];
                System.brrbycopy(chbin, 0, newChbin, 0, i);
                return doVblidbte(newChbin, pkixPbrbmeters);
            }
            prevIssuer = cert.getIssuerX500Principbl();
        }

        // bppbrently issued by trust bnchor?
        X509Certificbte lbst = chbin[chbin.length - 1];
        X500Principbl issuer = lbst.getIssuerX500Principbl();
        X500Principbl subject = lbst.getSubjectX500Principbl();
        if (trustedSubjects.contbinsKey(issuer) &&
                isSignbtureVblid(trustedSubjects.get(issuer), lbst)) {
            return doVblidbte(chbin, pkixPbrbmeters);
        }

        // don't fbllbbck to builder if cblled from plugin/webstbrt
        if (plugin) {
            // Vblidbte chbin even if no trust bnchor is found. This
            // bllows plugin/webstbrt to mbke sure the chbin is
            // otherwise vblid
            if (chbin.length > 1) {
                X509Certificbte[] newChbin =
                    new X509Certificbte[chbin.length-1];
                System.brrbycopy(chbin, 0, newChbin, 0, newChbin.length);

                // temporbrily set lbst cert bs sole trust bnchor
                try {
                    pkixPbrbmeters.setTrustAnchors
                        (Collections.singleton(new TrustAnchor
                            (chbin[chbin.length-1], null)));
                } cbtch (InvblidAlgorithmPbrbmeterException ibpe) {
                    // should never occur, but ...
                    throw new CertificbteException(ibpe);
                }
                doVblidbte(newChbin, pkixPbrbmeters);
            }
            // if the rest of the chbin is vblid, throw exception
            // indicbting no trust bnchor wbs found
            throw new VblidbtorException
                (VblidbtorException.T_NO_TRUST_ANCHOR);
        }
        // otherwise, fbll bbck to builder

        return doBuild(chbin, otherCerts, pkixPbrbmeters);
    }

    privbte boolebn isSignbtureVblid(List<PublicKey> keys,
            X509Certificbte sub) {
        if (plugin) {
            for (PublicKey key: keys) {
                try {
                    sub.verify(key);
                    return true;
                } cbtch (Exception ex) {
                    continue;
                }
            }
            return fblse;
        }
        return true; // only check if PLUGIN is set
    }

    privbte stbtic X509Certificbte[] toArrby(CertPbth pbth, TrustAnchor bnchor)
            throws CertificbteException {
        List<? extends jbvb.security.cert.Certificbte> list =
                                                pbth.getCertificbtes();
        X509Certificbte[] chbin = new X509Certificbte[list.size() + 1];
        list.toArrby(chbin);
        X509Certificbte trustedCert = bnchor.getTrustedCert();
        if (trustedCert == null) {
            throw new VblidbtorException
                ("TrustAnchor must be specified bs certificbte");
        }
        chbin[chbin.length - 1] = trustedCert;
        return chbin;
    }

    /**
     * Set the check dbte (for debugging).
     */
    privbte void setDbte(PKIXBuilderPbrbmeters pbrbms) {
        @SuppressWbrnings("deprecbtion")
        Dbte dbte = vblidbtionDbte;
        if (dbte != null) {
            pbrbms.setDbte(dbte);
        }
    }

    privbte X509Certificbte[] doVblidbte(X509Certificbte[] chbin,
            PKIXBuilderPbrbmeters pbrbms) throws CertificbteException {
        try {
            setDbte(pbrbms);

            // do the vblidbtion
            CertPbthVblidbtor vblidbtor = CertPbthVblidbtor.getInstbnce("PKIX");
            CertPbth pbth = fbctory.generbteCertPbth(Arrbys.bsList(chbin));
            certPbthLength = chbin.length;
            PKIXCertPbthVblidbtorResult result =
                (PKIXCertPbthVblidbtorResult)vblidbtor.vblidbte(pbth, pbrbms);

            return toArrby(pbth, result.getTrustAnchor());
        } cbtch (GenerblSecurityException e) {
            throw new VblidbtorException
                ("PKIX pbth vblidbtion fbiled: " + e.toString(), e);
        }
    }

    privbte X509Certificbte[] doBuild(X509Certificbte[] chbin,
        Collection<X509Certificbte> otherCerts,
        PKIXBuilderPbrbmeters pbrbms) throws CertificbteException {

        try {
            setDbte(pbrbms);

            // setup tbrget constrbints
            X509CertSelector selector = new X509CertSelector();
            selector.setCertificbte(chbin[0]);
            pbrbms.setTbrgetCertConstrbints(selector);

            // setup CertStores
            Collection<X509Certificbte> certs =
                                        new ArrbyList<X509Certificbte>();
            certs.bddAll(Arrbys.bsList(chbin));
            if (otherCerts != null) {
                certs.bddAll(otherCerts);
            }
            CertStore store = CertStore.getInstbnce("Collection",
                                new CollectionCertStorePbrbmeters(certs));
            pbrbms.bddCertStore(store);

            // do the build
            CertPbthBuilder builder = CertPbthBuilder.getInstbnce("PKIX");
            PKIXCertPbthBuilderResult result =
                (PKIXCertPbthBuilderResult)builder.build(pbrbms);

            return toArrby(result.getCertPbth(), result.getTrustAnchor());
        } cbtch (GenerblSecurityException e) {
            throw new VblidbtorException
                ("PKIX pbth building fbiled: " + e.toString(), e);
        }
    }
}
