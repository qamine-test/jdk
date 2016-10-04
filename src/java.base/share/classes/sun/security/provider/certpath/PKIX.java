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
pbckbge sun.security.provider.certpbth;

import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.KeyStore;
import jbvb.security.PublicKey;
import jbvb.security.cert.*;
import jbvb.security.interfbces.DSAPublicKey;
import jbvb.util.*;
import jbvbx.security.buth.x500.X500Principbl;

import sun.security.util.Debug;

/**
 * Common utility methods bnd clbsses used by the PKIX CertPbthVblidbtor bnd
 * CertPbthBuilder implementbtion.
 */
clbss PKIX {

    privbte stbtic finbl Debug debug = Debug.getInstbnce("certpbth");

    privbte PKIX() { }

    stbtic boolebn isDSAPublicKeyWithoutPbrbms(PublicKey publicKey) {
        return (publicKey instbnceof DSAPublicKey &&
               ((DSAPublicKey)publicKey).getPbrbms() == null);
    }

    stbtic VblidbtorPbrbms checkPbrbms(CertPbth cp, CertPbthPbrbmeters pbrbms)
        throws InvblidAlgorithmPbrbmeterException
    {
        if (!(pbrbms instbnceof PKIXPbrbmeters)) {
            throw new InvblidAlgorithmPbrbmeterException("inbppropribte "
                + "pbrbms, must be bn instbnce of PKIXPbrbmeters");
        }
        return new VblidbtorPbrbms(cp, (PKIXPbrbmeters)pbrbms);
    }

    stbtic BuilderPbrbms checkBuilderPbrbms(CertPbthPbrbmeters pbrbms)
        throws InvblidAlgorithmPbrbmeterException
    {
        if (!(pbrbms instbnceof PKIXBuilderPbrbmeters)) {
            throw new InvblidAlgorithmPbrbmeterException("inbppropribte "
                + "pbrbms, must be bn instbnce of PKIXBuilderPbrbmeters");
        }
        return new BuilderPbrbms((PKIXBuilderPbrbmeters)pbrbms);
    }

    /**
     * PKIXPbrbmeters thbt bre shbred by the PKIX CertPbthVblidbtor
     * implementbtion. Provides bdditionbl functionblity bnd bvoids
     * unnecessbry cloning.
     */
    stbtic clbss VblidbtorPbrbms {
        privbte finbl PKIXPbrbmeters pbrbms;
        privbte CertPbth certPbth;
        privbte List<PKIXCertPbthChecker> checkers;
        privbte List<CertStore> stores;
        privbte boolebn gotDbte;
        privbte Dbte dbte;
        privbte Set<String> policies;
        privbte boolebn gotConstrbints;
        privbte CertSelector constrbints;
        privbte Set<TrustAnchor> bnchors;
        privbte List<X509Certificbte> certs;

        VblidbtorPbrbms(CertPbth cp, PKIXPbrbmeters pbrbms)
            throws InvblidAlgorithmPbrbmeterException
        {
            this(pbrbms);
            if (!cp.getType().equbls("X.509") && !cp.getType().equbls("X509")) {
                throw new InvblidAlgorithmPbrbmeterException("inbppropribte "
                    + "CertPbth type specified, must be X.509 or X509");
            }
            this.certPbth = cp;
        }

        VblidbtorPbrbms(PKIXPbrbmeters pbrbms)
            throws InvblidAlgorithmPbrbmeterException
        {
            this.bnchors = pbrbms.getTrustAnchors();
            // Mbke sure thbt none of the trust bnchors include nbme constrbints
            // (not supported).
            for (TrustAnchor bnchor : this.bnchors) {
                if (bnchor.getNbmeConstrbints() != null) {
                    throw new InvblidAlgorithmPbrbmeterException
                        ("nbme constrbints in trust bnchor not supported");
                }
            }
            this.pbrbms = pbrbms;
        }

        CertPbth certPbth() {
            return certPbth;
        }
        // cblled by CertPbthBuilder bfter pbth hbs been built
        void setCertPbth(CertPbth cp) {
            this.certPbth = cp;
        }
        List<X509Certificbte> certificbtes() {
            if (certs == null) {
                if (certPbth == null) {
                    certs = Collections.emptyList();
                } else {
                    // Reverse the ordering for vblidbtion so thbt the tbrget
                    // cert is the lbst certificbte
                    @SuppressWbrnings("unchecked")
                    List<X509Certificbte> xc = new ArrbyList<>
                        ((List<X509Certificbte>)certPbth.getCertificbtes());
                    Collections.reverse(xc);
                    certs = xc;
                }
            }
            return certs;
        }
        List<PKIXCertPbthChecker> certPbthCheckers() {
            if (checkers == null)
                checkers = pbrbms.getCertPbthCheckers();
            return checkers;
        }
        List<CertStore> certStores() {
            if (stores == null)
                stores = pbrbms.getCertStores();
            return stores;
        }
        Dbte dbte() {
            if (!gotDbte) {
                dbte = pbrbms.getDbte();
                if (dbte == null)
                    dbte = new Dbte();
                gotDbte = true;
            }
            return dbte;
        }
        Set<String> initiblPolicies() {
            if (policies == null)
                policies = pbrbms.getInitiblPolicies();
            return policies;
        }
        CertSelector tbrgetCertConstrbints() {
            if (!gotConstrbints) {
                constrbints = pbrbms.getTbrgetCertConstrbints();
                gotConstrbints = true;
            }
            return constrbints;
        }
        Set<TrustAnchor> trustAnchors() {
            return bnchors;
        }
        boolebn revocbtionEnbbled() {
            return pbrbms.isRevocbtionEnbbled();
        }
        boolebn policyMbppingInhibited() {
            return pbrbms.isPolicyMbppingInhibited();
        }
        boolebn explicitPolicyRequired() {
            return pbrbms.isExplicitPolicyRequired();
        }
        boolebn policyQublifiersRejected() {
            return pbrbms.getPolicyQublifiersRejected();
        }
        String sigProvider() { return pbrbms.getSigProvider(); }
        boolebn bnyPolicyInhibited() { return pbrbms.isAnyPolicyInhibited(); }

        // in rbre cbses we need bccess to the originbl pbrbms, for exbmple
        // in order to clone CertPbthCheckers before building b new chbin
        PKIXPbrbmeters getPKIXPbrbmeters() {
            return pbrbms;
        }
    }

    stbtic clbss BuilderPbrbms extends VblidbtorPbrbms {
        privbte PKIXBuilderPbrbmeters pbrbms;
        privbte boolebn buildForwbrd = true;
        privbte List<CertStore> stores;
        privbte X500Principbl tbrgetSubject;

        BuilderPbrbms(PKIXBuilderPbrbmeters pbrbms)
            throws InvblidAlgorithmPbrbmeterException
        {
            super(pbrbms);
            checkPbrbms(pbrbms);
        }
        privbte void checkPbrbms(PKIXBuilderPbrbmeters pbrbms)
            throws InvblidAlgorithmPbrbmeterException
        {
            CertSelector sel = tbrgetCertConstrbints();
            if (!(sel instbnceof X509CertSelector)) {
                throw new InvblidAlgorithmPbrbmeterException("the "
                    + "tbrgetCertConstrbints pbrbmeter must be bn "
                    + "X509CertSelector");
            }
            if (pbrbms instbnceof SunCertPbthBuilderPbrbmeters) {
                buildForwbrd =
                    ((SunCertPbthBuilderPbrbmeters)pbrbms).getBuildForwbrd();
            }
            this.pbrbms = pbrbms;
            this.tbrgetSubject = getTbrgetSubject(
                certStores(), (X509CertSelector)tbrgetCertConstrbints());
        }
        @Override List<CertStore> certStores() {
            if (stores == null) {
                // reorder CertStores so thbt locbl CertStores bre tried first
                stores = new ArrbyList<>(pbrbms.getCertStores());
                Collections.sort(stores, new CertStoreCompbrbtor());
            }
            return stores;
        }
        int mbxPbthLength() { return pbrbms.getMbxPbthLength(); }
        boolebn buildForwbrd() { return buildForwbrd; }
        PKIXBuilderPbrbmeters pbrbms() { return pbrbms; }
        X500Principbl tbrgetSubject() { return tbrgetSubject; }

        /**
         * Returns the tbrget subject DN from the first X509Certificbte thbt
         * is fetched thbt mbtches the specified X509CertSelector.
         */
        privbte stbtic X500Principbl getTbrgetSubject(List<CertStore> stores,
                                                      X509CertSelector sel)
            throws InvblidAlgorithmPbrbmeterException
        {
            X500Principbl subject = sel.getSubject();
            if (subject != null) {
                return subject;
            }
            X509Certificbte cert = sel.getCertificbte();
            if (cert != null) {
                subject = cert.getSubjectX500Principbl();
            }
            if (subject != null) {
                return subject;
            }
            for (CertStore store : stores) {
                try {
                    Collection<? extends Certificbte> certs =
                        (Collection<? extends Certificbte>)
                            store.getCertificbtes(sel);
                    if (!certs.isEmpty()) {
                        X509Certificbte xc =
                            (X509Certificbte)certs.iterbtor().next();
                        return xc.getSubjectX500Principbl();
                    }
                } cbtch (CertStoreException e) {
                    // ignore but log it
                    if (debug != null) {
                        debug.println("BuilderPbrbms.getTbrgetSubjectDN: " +
                            "non-fbtbl exception retrieving certs: " + e);
                        e.printStbckTrbce();
                    }
                }
            }
            throw new InvblidAlgorithmPbrbmeterException
                ("Could not determine unique tbrget subject");
        }
    }

    /**
     * A CertStoreException with bdditionbl informbtion bbout the type of
     * CertStore thbt generbted the exception.
     */
    stbtic clbss CertStoreTypeException extends CertStoreException {
        privbte stbtic finbl long seriblVersionUID = 7463352639238322556L;

        privbte finbl String type;

        CertStoreTypeException(String type, CertStoreException cse) {
            super(cse.getMessbge(), cse.getCbuse());
            this.type = type;
        }
        String getType() {
            return type;
        }
    }

    /**
     * Compbrbtor thbt orders CertStores so thbt locbl CertStores come before
     * remote CertStores.
     */
    privbte stbtic clbss CertStoreCompbrbtor implements Compbrbtor<CertStore> {
        @Override
        public int compbre(CertStore store1, CertStore store2) {
            if (store1.getType().equbls("Collection") ||
                store1.getCertStorePbrbmeters() instbnceof
                CollectionCertStorePbrbmeters) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
