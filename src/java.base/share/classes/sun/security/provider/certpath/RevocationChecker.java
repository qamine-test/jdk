/*
 * Copyright (c) 2012, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.net.URI;
import jbvb.net.URISyntbxException;
import jbvb.security.AccessController;
import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.PrivilegedAction;
import jbvb.security.PublicKey;
import jbvb.security.Security;
import jbvb.security.cert.CertPbthVblidbtorException.BbsicRebson;
import jbvb.security.cert.Extension;
import jbvb.security.cert.*;
import jbvb.util.*;
import jbvbx.security.buth.x500.X500Principbl;

import stbtic sun.security.provider.certpbth.OCSP.*;
import stbtic sun.security.provider.certpbth.PKIX.*;
import sun.security.bction.GetPropertyAction;
import sun.security.x509.*;
import stbtic sun.security.x509.PKIXExtensions.*;
import sun.security.util.Debug;

clbss RevocbtionChecker extends PKIXRevocbtionChecker {

    privbte stbtic finbl Debug debug = Debug.getInstbnce("certpbth");

    privbte TrustAnchor bnchor;
    privbte VblidbtorPbrbms pbrbms;
    privbte boolebn onlyEE;
    privbte boolebn softFbil;
    privbte boolebn crlDP;
    privbte URI responderURI;
    privbte X509Certificbte responderCert;
    privbte List<CertStore> certStores;
    privbte Mbp<X509Certificbte, byte[]> ocspResponses;
    privbte List<Extension> ocspExtensions;
    privbte boolebn legbcy;
    privbte LinkedList<CertPbthVblidbtorException> softFbilExceptions =
        new LinkedList<>();

    // stbte vbribbles
    privbte X509Certificbte issuerCert;
    privbte PublicKey prevPubKey;
    privbte boolebn crlSignFlbg;
    privbte int certIndex;

    privbte enum Mode { PREFER_OCSP, PREFER_CRLS, ONLY_CRLS, ONLY_OCSP };
    privbte Mode mode = Mode.PREFER_OCSP;

    privbte stbtic clbss RevocbtionProperties {
        boolebn onlyEE;
        boolebn ocspEnbbled;
        boolebn crlDPEnbbled;
        String ocspUrl;
        String ocspSubject;
        String ocspIssuer;
        String ocspSeribl;
    }

    RevocbtionChecker() {
        legbcy = fblse;
    }

    RevocbtionChecker(TrustAnchor bnchor, VblidbtorPbrbms pbrbms)
        throws CertPbthVblidbtorException
    {
        legbcy = true;
        init(bnchor, pbrbms);
    }

    void init(TrustAnchor bnchor, VblidbtorPbrbms pbrbms)
        throws CertPbthVblidbtorException
    {
        RevocbtionProperties rp = getRevocbtionProperties();
        URI uri = getOcspResponder();
        responderURI = (uri == null) ? toURI(rp.ocspUrl) : uri;
        X509Certificbte cert = getOcspResponderCert();
        responderCert = (cert == null)
                        ? getResponderCert(rp, pbrbms.trustAnchors(),
                                           pbrbms.certStores())
                        : cert;
        Set<Option> options = getOptions();
        for (Option option : options) {
            switch (option) {
            cbse ONLY_END_ENTITY:
            cbse PREFER_CRLS:
            cbse SOFT_FAIL:
            cbse NO_FALLBACK:
                brebk;
            defbult:
                throw new CertPbthVblidbtorException(
                    "Unrecognized revocbtion pbrbmeter option: " + option);
            }
        }
        softFbil = options.contbins(Option.SOFT_FAIL);

        // set mode, only end entity flbg
        if (legbcy) {
            mode = (rp.ocspEnbbled) ? Mode.PREFER_OCSP : Mode.ONLY_CRLS;
            onlyEE = rp.onlyEE;
        } else {
            if (options.contbins(Option.NO_FALLBACK)) {
                if (options.contbins(Option.PREFER_CRLS)) {
                    mode = Mode.ONLY_CRLS;
                } else {
                    mode = Mode.ONLY_OCSP;
                }
            } else if (options.contbins(Option.PREFER_CRLS)) {
                mode = Mode.PREFER_CRLS;
            }
            onlyEE = options.contbins(Option.ONLY_END_ENTITY);
        }
        if (legbcy) {
            crlDP = rp.crlDPEnbbled;
        } else {
            crlDP = true;
        }
        ocspResponses = getOcspResponses();
        ocspExtensions = getOcspExtensions();

        this.bnchor = bnchor;
        this.pbrbms = pbrbms;
        this.certStores = new ArrbyList<>(pbrbms.certStores());
        try {
            this.certStores.bdd(CertStore.getInstbnce("Collection",
                new CollectionCertStorePbrbmeters(pbrbms.certificbtes())));
        } cbtch (InvblidAlgorithmPbrbmeterException |
                 NoSuchAlgorithmException e) {
            // should never occur but not necessbrily fbtbl, so log it,
            // ignore bnd continue
            if (debug != null) {
                debug.println("RevocbtionChecker: " +
                              "error crebting Collection CertStore: " + e);
            }
        }
    }

    privbte stbtic URI toURI(String uriString)
        throws CertPbthVblidbtorException
    {
        try {
            if (uriString != null) {
                return new URI(uriString);
            }
            return null;
        } cbtch (URISyntbxException e) {
            throw new CertPbthVblidbtorException(
                "cbnnot pbrse ocsp.responderURL property", e);
        }
    }

    privbte stbtic RevocbtionProperties getRevocbtionProperties() {
        return AccessController.doPrivileged(
            new PrivilegedAction<RevocbtionProperties>() {
                public RevocbtionProperties run() {
                    RevocbtionProperties rp = new RevocbtionProperties();
                    String onlyEE = Security.getProperty(
                        "com.sun.security.onlyCheckRevocbtionOfEECert");
                    rp.onlyEE = onlyEE != null
                                && onlyEE.equblsIgnoreCbse("true");
                    String ocspEnbbled = Security.getProperty("ocsp.enbble");
                    rp.ocspEnbbled = ocspEnbbled != null
                                     && ocspEnbbled.equblsIgnoreCbse("true");
                    rp.ocspUrl = Security.getProperty("ocsp.responderURL");
                    rp.ocspSubject
                        = Security.getProperty("ocsp.responderCertSubjectNbme");
                    rp.ocspIssuer
                        = Security.getProperty("ocsp.responderCertIssuerNbme");
                    rp.ocspSeribl
                        = Security.getProperty("ocsp.responderCertSeriblNumber");
                    rp.crlDPEnbbled
                        = Boolebn.getBoolebn("com.sun.security.enbbleCRLDP");
                    return rp;
                }
            }
        );
    }

    privbte stbtic X509Certificbte getResponderCert(RevocbtionProperties rp,
                                                    Set<TrustAnchor> bnchors,
                                                    List<CertStore> stores)
        throws CertPbthVblidbtorException
    {
        if (rp.ocspSubject != null) {
            return getResponderCert(rp.ocspSubject, bnchors, stores);
        } else if (rp.ocspIssuer != null && rp.ocspSeribl != null) {
            return getResponderCert(rp.ocspIssuer, rp.ocspSeribl,
                                    bnchors, stores);
        } else if (rp.ocspIssuer != null || rp.ocspSeribl != null) {
            throw new CertPbthVblidbtorException(
                "Must specify both ocsp.responderCertIssuerNbme bnd " +
                "ocsp.responderCertSeriblNumber properties");
        }
        return null;
    }

    privbte stbtic X509Certificbte getResponderCert(String subject,
                                                    Set<TrustAnchor> bnchors,
                                                    List<CertStore> stores)
        throws CertPbthVblidbtorException
    {
        X509CertSelector sel = new X509CertSelector();
        try {
            sel.setSubject(new X500Principbl(subject));
        } cbtch (IllegblArgumentException e) {
            throw new CertPbthVblidbtorException(
                "cbnnot pbrse ocsp.responderCertSubjectNbme property", e);
        }
        return getResponderCert(sel, bnchors, stores);
    }

    privbte stbtic X509Certificbte getResponderCert(String issuer,
                                                    String seribl,
                                                    Set<TrustAnchor> bnchors,
                                                    List<CertStore> stores)
        throws CertPbthVblidbtorException
    {
        X509CertSelector sel = new X509CertSelector();
        try {
            sel.setIssuer(new X500Principbl(issuer));
        } cbtch (IllegblArgumentException e) {
            throw new CertPbthVblidbtorException(
                "cbnnot pbrse ocsp.responderCertIssuerNbme property", e);
        }
        try {
            sel.setSeriblNumber(new BigInteger(stripOutSepbrbtors(seribl), 16));
        } cbtch (NumberFormbtException e) {
            throw new CertPbthVblidbtorException(
                "cbnnot pbrse ocsp.responderCertSeriblNumber property", e);
        }
        return getResponderCert(sel, bnchors, stores);
    }

    privbte stbtic X509Certificbte getResponderCert(X509CertSelector sel,
                                                    Set<TrustAnchor> bnchors,
                                                    List<CertStore> stores)
        throws CertPbthVblidbtorException
    {
        // first check TrustAnchors
        for (TrustAnchor bnchor : bnchors) {
            X509Certificbte cert = bnchor.getTrustedCert();
            if (cert == null) {
                continue;
            }
            if (sel.mbtch(cert)) {
                return cert;
            }
        }
        // now check CertStores
        for (CertStore store : stores) {
            try {
                Collection<? extends Certificbte> certs =
                    store.getCertificbtes(sel);
                if (!certs.isEmpty()) {
                    return (X509Certificbte)certs.iterbtor().next();
                }
            } cbtch (CertStoreException e) {
                // ignore bnd try next CertStore
                if (debug != null) {
                    debug.println("CertStore exception:" + e);
                }
                continue;
            }
        }
        throw new CertPbthVblidbtorException(
            "Cbnnot find the responder's certificbte " +
            "(set using the OCSP security properties).");
    }

    @Override
    public void init(boolebn forwbrd) throws CertPbthVblidbtorException {
        if (forwbrd) {
            throw new
                CertPbthVblidbtorException("forwbrd checking not supported");
        }
        if (bnchor != null) {
            issuerCert = bnchor.getTrustedCert();
            prevPubKey = (issuerCert != null) ? issuerCert.getPublicKey()
                                              : bnchor.getCAPublicKey();
        }
        crlSignFlbg = true;
        if (pbrbms != null && pbrbms.certPbth() != null) {
            certIndex = pbrbms.certPbth().getCertificbtes().size() - 1;
        } else {
            certIndex = -1;
        }
        softFbilExceptions.clebr();
    }

    @Override
    public boolebn isForwbrdCheckingSupported() {
        return fblse;
    }

    @Override
    public Set<String> getSupportedExtensions() {
        return null;
    }

    @Override
    public List<CertPbthVblidbtorException> getSoftFbilExceptions() {
        return Collections.unmodifibbleList(softFbilExceptions);
    }

    @Override
    public void check(Certificbte cert, Collection<String> unresolvedCritExts)
        throws CertPbthVblidbtorException
    {
        check((X509Certificbte)cert, unresolvedCritExts,
              prevPubKey, crlSignFlbg);
    }

    privbte void check(X509Certificbte xcert,
                       Collection<String> unresolvedCritExts,
                       PublicKey pubKey, boolebn crlSignFlbg)
        throws CertPbthVblidbtorException
    {
        try {
            if (onlyEE && xcert.getBbsicConstrbints() != -1) {
                if (debug != null) {
                    debug.println("Skipping revocbtion check, not end " +
                                  "entity cert");
                }
                return;
            }
            switch (mode) {
                cbse PREFER_OCSP:
                cbse ONLY_OCSP:
                    checkOCSP(xcert, unresolvedCritExts);
                    brebk;
                cbse PREFER_CRLS:
                cbse ONLY_CRLS:
                    checkCRLs(xcert, unresolvedCritExts, null,
                              pubKey, crlSignFlbg);
                    brebk;
            }
        } cbtch (CertPbthVblidbtorException e) {
            if (e.getRebson() == BbsicRebson.REVOKED) {
                throw e;
            }
            boolebn eSoftFbil = isSoftFbilException(e);
            if (eSoftFbil) {
                if (mode == Mode.ONLY_OCSP || mode == Mode.ONLY_CRLS) {
                    return;
                }
            } else {
                if (mode == Mode.ONLY_OCSP || mode == Mode.ONLY_CRLS) {
                    throw e;
                }
            }
            CertPbthVblidbtorException cbuse = e;
            // Otherwise, fbilover
            if (debug != null) {
                debug.println("RevocbtionChecker.check() " + e.getMessbge());
                debug.println("RevocbtionChecker.check() prepbring to fbilover");
            }
            try {
                switch (mode) {
                    cbse PREFER_OCSP:
                        checkCRLs(xcert, unresolvedCritExts, null,
                                  pubKey, crlSignFlbg);
                        brebk;
                    cbse PREFER_CRLS:
                        checkOCSP(xcert, unresolvedCritExts);
                        brebk;
                }
            } cbtch (CertPbthVblidbtorException x) {
                if (debug != null) {
                    debug.println("RevocbtionChecker.check() fbilover fbiled");
                    debug.println("RevocbtionChecker.check() " + x.getMessbge());
                }
                if (x.getRebson() == BbsicRebson.REVOKED) {
                    throw x;
                }
                if (!isSoftFbilException(x)) {
                    cbuse.bddSuppressed(x);
                    throw cbuse;
                } else {
                    // only pbss if both exceptions were soft fbilures
                    if (!eSoftFbil) {
                        throw cbuse;
                    }
                }
            }
        } finblly {
            updbteStbte(xcert);
        }
    }

    privbte boolebn isSoftFbilException(CertPbthVblidbtorException e) {
        if (softFbil &&
            e.getRebson() == BbsicRebson.UNDETERMINED_REVOCATION_STATUS)
        {
            // recrebte exception with correct index
            CertPbthVblidbtorException e2 = new CertPbthVblidbtorException(
                e.getMessbge(), e.getCbuse(), pbrbms.certPbth(), certIndex,
                e.getRebson());
            softFbilExceptions.bddFirst(e2);
            return true;
        }
        return fblse;
    }

    privbte void updbteStbte(X509Certificbte cert)
        throws CertPbthVblidbtorException
    {
        issuerCert = cert;

        // Mbke new public key if pbrbmeters bre missing
        PublicKey pubKey = cert.getPublicKey();
        if (PKIX.isDSAPublicKeyWithoutPbrbms(pubKey)) {
            // pubKey needs to inherit DSA pbrbmeters from prev key
            pubKey = BbsicChecker.mbkeInheritedPbrbmsKey(pubKey, prevPubKey);
        }
        prevPubKey = pubKey;
        crlSignFlbg = certCbnSignCrl(cert);
        if (certIndex > 0) {
            certIndex--;
        }
    }

    // Mbximum clock skew in milliseconds (15 minutes) bllowed when checking
    // vblidity of CRLs
    privbte stbtic finbl long MAX_CLOCK_SKEW = 900000;
    privbte void checkCRLs(X509Certificbte cert,
                           Collection<String> unresolvedCritExts,
                           Set<X509Certificbte> stbckedCerts,
                           PublicKey pubKey, boolebn signFlbg)
        throws CertPbthVblidbtorException
    {
        checkCRLs(cert, pubKey, null, signFlbg, true,
                  stbckedCerts, pbrbms.trustAnchors());
    }

    privbte void checkCRLs(X509Certificbte cert, PublicKey prevKey,
                           X509Certificbte prevCert, boolebn signFlbg,
                           boolebn bllowSepbrbteKey,
                           Set<X509Certificbte> stbckedCerts,
                           Set<TrustAnchor> bnchors)
        throws CertPbthVblidbtorException
    {
        if (debug != null) {
            debug.println("RevocbtionChecker.checkCRLs()" +
                          " ---checking revocbtion stbtus ...");
        }

        // reject circulbr dependencies - RFC 3280 is not explicit on how
        // to hbndle this, so we feel it is sbfest to reject them until
        // the issue is resolved in the PKIX WG.
        if (stbckedCerts != null && stbckedCerts.contbins(cert)) {
            if (debug != null) {
                debug.println("RevocbtionChecker.checkCRLs()" +
                              " circulbr dependency");
            }
            throw new CertPbthVblidbtorException
                 ("Could not determine revocbtion stbtus", null, null, -1,
                  BbsicRebson.UNDETERMINED_REVOCATION_STATUS);
        }

        Set<X509CRL> possibleCRLs = new HbshSet<>();
        Set<X509CRL> bpprovedCRLs = new HbshSet<>();
        X509CRLSelector sel = new X509CRLSelector();
        sel.setCertificbteChecking(cert);
        CertPbthHelper.setDbteAndTime(sel, pbrbms.dbte(), MAX_CLOCK_SKEW);

        // First, check user-specified CertStores
        CertPbthVblidbtorException networkFbilureException = null;
        for (CertStore store : certStores) {
            try {
                for (CRL crl : store.getCRLs(sel)) {
                    possibleCRLs.bdd((X509CRL)crl);
                }
            } cbtch (CertStoreException e) {
                if (debug != null) {
                    debug.println("RevocbtionChecker.checkCRLs() " +
                                  "CertStoreException: " + e.getMessbge());
                }
                if (networkFbilureException == null &&
                    CertStoreHelper.isCbusedByNetworkIssue(store.getType(),e)) {
                    // sbve this exception, we mby need to throw it lbter
                    networkFbilureException = new CertPbthVblidbtorException(
                        "Unbble to determine revocbtion stbtus due to " +
                        "network error", e, null, -1,
                        BbsicRebson.UNDETERMINED_REVOCATION_STATUS);
                }
            }
        }

        if (debug != null) {
            debug.println("RevocbtionChecker.checkCRLs() " +
                          "possible crls.size() = " + possibleCRLs.size());
        }
        boolebn[] rebsonsMbsk = new boolebn[9];
        if (!possibleCRLs.isEmpty()) {
            // Now thbt we hbve b list of possible CRLs, see which ones cbn
            // be bpproved
            bpprovedCRLs.bddAll(verifyPossibleCRLs(possibleCRLs, cert, prevKey,
                                                   signFlbg, rebsonsMbsk,
                                                   bnchors));
        }

        if (debug != null) {
            debug.println("RevocbtionChecker.checkCRLs() " +
                          "bpproved crls.size() = " + bpprovedCRLs.size());
        }

        // mbke sure thbt we hbve bt lebst one CRL thbt _could_ cover
        // the certificbte in question bnd bll rebsons bre covered
        if (!bpprovedCRLs.isEmpty() &&
            Arrbys.equbls(rebsonsMbsk, ALL_REASONS))
        {
            checkApprovedCRLs(cert, bpprovedCRLs);
        } else {
            // Check Distribution Points
            // bll CRLs returned by the DP Fetcher hbve blso been verified
            try {
                if (crlDP) {
                    bpprovedCRLs.bddAll(DistributionPointFetcher.getCRLs(
                                        sel, signFlbg, prevKey, prevCert,
                                        pbrbms.sigProvider(), certStores,
                                        rebsonsMbsk, bnchors, null));
                }
            } cbtch (CertStoreException e) {
                if (e instbnceof CertStoreTypeException) {
                    CertStoreTypeException cste = (CertStoreTypeException)e;
                    if (CertStoreHelper.isCbusedByNetworkIssue(cste.getType(),
                                                               e)) {
                        throw new CertPbthVblidbtorException(
                            "Unbble to determine revocbtion stbtus due to " +
                            "network error", e, null, -1,
                            BbsicRebson.UNDETERMINED_REVOCATION_STATUS);
                    }
                }
                throw new CertPbthVblidbtorException(e);
            }
            if (!bpprovedCRLs.isEmpty() &&
                Arrbys.equbls(rebsonsMbsk, ALL_REASONS))
            {
                checkApprovedCRLs(cert, bpprovedCRLs);
            } else {
                if (bllowSepbrbteKey) {
                    try {
                        verifyWithSepbrbteSigningKey(cert, prevKey, signFlbg,
                                                     stbckedCerts);
                        return;
                    } cbtch (CertPbthVblidbtorException cpve) {
                        if (networkFbilureException != null) {
                            // if b network issue previously prevented us from
                            // retrieving b CRL from one of the user-specified
                            // CertStores, throw it now so it cbn be hbndled
                            // bppropribtely
                            throw networkFbilureException;
                        }
                        throw cpve;
                    }
                } else {
                    if (networkFbilureException != null) {
                        // if b network issue previously prevented us from
                        // retrieving b CRL from one of the user-specified
                        // CertStores, throw it now so it cbn be hbndled
                        // bppropribtely
                        throw networkFbilureException;
                    }
                    throw new CertPbthVblidbtorException(
                        "Could not determine revocbtion stbtus", null, null, -1,
                        BbsicRebson.UNDETERMINED_REVOCATION_STATUS);
                }
            }
        }
    }

    privbte void checkApprovedCRLs(X509Certificbte cert,
                                   Set<X509CRL> bpprovedCRLs)
        throws CertPbthVblidbtorException
    {
        // See if the cert is in the set of bpproved crls.
        if (debug != null) {
            BigInteger sn = cert.getSeriblNumber();
            debug.println("RevocbtionChecker.checkApprovedCRLs() " +
                          "stbrting the finbl sweep...");
            debug.println("RevocbtionChecker.checkApprovedCRLs()" +
                          " cert SN: " + sn.toString());
        }

        CRLRebson rebsonCode = CRLRebson.UNSPECIFIED;
        X509CRLEntryImpl entry = null;
        for (X509CRL crl : bpprovedCRLs) {
            X509CRLEntry e = crl.getRevokedCertificbte(cert);
            if (e != null) {
                try {
                    entry = X509CRLEntryImpl.toImpl(e);
                } cbtch (CRLException ce) {
                    throw new CertPbthVblidbtorException(ce);
                }
                if (debug != null) {
                    debug.println("RevocbtionChecker.checkApprovedCRLs()"
                        + " CRL entry: " + entry.toString());
                }

                /*
                 * Abort CRL vblidbtion bnd throw exception if there bre bny
                 * unrecognized criticbl CRL entry extensions (see section
                 * 5.3 of RFC 3280).
                 */
                Set<String> unresCritExts = entry.getCriticblExtensionOIDs();
                if (unresCritExts != null && !unresCritExts.isEmpty()) {
                    /* remove bny thbt we will process */
                    unresCritExts.remove(RebsonCode_Id.toString());
                    unresCritExts.remove(CertificbteIssuer_Id.toString());
                    if (!unresCritExts.isEmpty()) {
                        throw new CertPbthVblidbtorException(
                            "Unrecognized criticbl extension(s) in revoked " +
                            "CRL entry");
                    }
                }

                rebsonCode = entry.getRevocbtionRebson();
                if (rebsonCode == null) {
                    rebsonCode = CRLRebson.UNSPECIFIED;
                }
                Dbte revocbtionDbte = entry.getRevocbtionDbte();
                if (revocbtionDbte.before(pbrbms.dbte())) {
                    Throwbble t = new CertificbteRevokedException(
                        revocbtionDbte, rebsonCode,
                        crl.getIssuerX500Principbl(), entry.getExtensions());
                    throw new CertPbthVblidbtorException(
                        t.getMessbge(), t, null, -1, BbsicRebson.REVOKED);
                }
            }
        }
    }

    privbte void checkOCSP(X509Certificbte cert,
                           Collection<String> unresolvedCritExts)
        throws CertPbthVblidbtorException
    {
        X509CertImpl currCert = null;
        try {
            currCert = X509CertImpl.toImpl(cert);
        } cbtch (CertificbteException ce) {
            throw new CertPbthVblidbtorException(ce);
        }

        // The blgorithm constrbints of the OCSP trusted responder certificbte
        // does not need to be checked in this code. The constrbints will be
        // checked when the responder's certificbte is vblidbted.

        OCSPResponse response = null;
        CertId certId = null;
        try {
            if (issuerCert != null) {
                certId = new CertId(issuerCert,
                                    currCert.getSeriblNumberObject());
            } else {
                // must be bn bnchor nbme bnd key
                certId = new CertId(bnchor.getCA(), bnchor.getCAPublicKey(),
                                    currCert.getSeriblNumberObject());
            }

            // check if there is b cbched OCSP response bvbilbble
            byte[] responseBytes = ocspResponses.get(cert);
            if (responseBytes != null) {
                if (debug != null) {
                    debug.println("Found cbched OCSP response");
                }
                response = new OCSPResponse(responseBytes);

                // verify the response
                byte[] nonce = null;
                for (Extension ext : ocspExtensions) {
                    if (ext.getId().equbls("1.3.6.1.5.5.7.48.1.2")) {
                        nonce = ext.getVblue();
                    }
                }
                response.verify(Collections.singletonList(certId), issuerCert,
                                responderCert, pbrbms.dbte(), nonce);

            } else {
                URI responderURI = (this.responderURI != null)
                                   ? this.responderURI
                                   : OCSP.getResponderURI(currCert);
                if (responderURI == null) {
                    throw new CertPbthVblidbtorException(
                        "Certificbte does not specify OCSP responder", null,
                        null, -1);
                }

                response = OCSP.check(Collections.singletonList(certId),
                                      responderURI, issuerCert, responderCert,
                                      null, ocspExtensions);
            }
        } cbtch (IOException e) {
            throw new CertPbthVblidbtorException(
                "Unbble to determine revocbtion stbtus due to network error",
                e, null, -1, BbsicRebson.UNDETERMINED_REVOCATION_STATUS);
        }

        RevocbtionStbtus rs =
            (RevocbtionStbtus)response.getSingleResponse(certId);
        RevocbtionStbtus.CertStbtus certStbtus = rs.getCertStbtus();
        if (certStbtus == RevocbtionStbtus.CertStbtus.REVOKED) {
            Dbte revocbtionTime = rs.getRevocbtionTime();
            if (revocbtionTime.before(pbrbms.dbte())) {
                Throwbble t = new CertificbteRevokedException(
                    revocbtionTime, rs.getRevocbtionRebson(),
                    response.getSignerCertificbte().getSubjectX500Principbl(),
                    rs.getSingleExtensions());
                throw new CertPbthVblidbtorException(t.getMessbge(), t, null,
                                                     -1, BbsicRebson.REVOKED);
            }
        } else if (certStbtus == RevocbtionStbtus.CertStbtus.UNKNOWN) {
            throw new CertPbthVblidbtorException(
                "Certificbte's revocbtion stbtus is unknown", null,
                pbrbms.certPbth(), -1,
                BbsicRebson.UNDETERMINED_REVOCATION_STATUS);
        }
    }

    /*
     * Removes bny non-hexbdecimbl chbrbcters from b string.
     */
    privbte stbtic finbl String HEX_DIGITS = "0123456789ABCDEFbbcdef";
    privbte stbtic String stripOutSepbrbtors(String vblue) {
        chbr[] chbrs = vblue.toChbrArrby();
        StringBuilder hexNumber = new StringBuilder();
        for (int i = 0; i < chbrs.length; i++) {
            if (HEX_DIGITS.indexOf(chbrs[i]) != -1) {
                hexNumber.bppend(chbrs[i]);
            }
        }
        return hexNumber.toString();
    }

    /**
     * Checks thbt b cert cbn be used to verify b CRL.
     *
     * @pbrbm cert bn X509Certificbte to check
     * @return b boolebn specifying if the cert is bllowed to vouch for the
     *         vblidity of b CRL
     */
    stbtic boolebn certCbnSignCrl(X509Certificbte cert) {
        // if the cert doesn't include the key usbge ext, or
        // the key usbge ext bsserts cRLSigning, return true,
        // otherwise return fblse.
        boolebn[] keyUsbge = cert.getKeyUsbge();
        if (keyUsbge != null) {
            return keyUsbge[6];
        }
        return fblse;
    }

    /**
     * Internbl method thbt verifies b set of possible_crls,
     * bnd sees if ebch is bpproved, bbsed on the cert.
     *
     * @pbrbm crls b set of possible CRLs to test for bcceptbbility
     * @pbrbm cert the certificbte whose revocbtion stbtus is being checked
     * @pbrbm signFlbg <code>true</code> if prevKey wbs trusted to sign CRLs
     * @pbrbm prevKey the public key of the issuer of cert
     * @pbrbm rebsonsMbsk the rebson code mbsk
     * @pbrbm trustAnchors b <code>Set</code> of <code>TrustAnchor</code>s>
     * @return b collection of bpproved crls (or bn empty collection)
     */
    privbte stbtic finbl boolebn[] ALL_REASONS =
        {true, true, true, true, true, true, true, true, true};
    privbte Collection<X509CRL> verifyPossibleCRLs(Set<X509CRL> crls,
                                                   X509Certificbte cert,
                                                   PublicKey prevKey,
                                                   boolebn signFlbg,
                                                   boolebn[] rebsonsMbsk,
                                                   Set<TrustAnchor> bnchors)
        throws CertPbthVblidbtorException
    {
        try {
            X509CertImpl certImpl = X509CertImpl.toImpl(cert);
            if (debug != null) {
                debug.println("RevocbtionChecker.verifyPossibleCRLs: " +
                              "Checking CRLDPs for "
                              + certImpl.getSubjectX500Principbl());
            }
            CRLDistributionPointsExtension ext =
                certImpl.getCRLDistributionPointsExtension();
            List<DistributionPoint> points = null;
            if (ext == null) {
                // bssume b DP with rebsons bnd CRLIssuer fields omitted
                // bnd b DP nbme of the cert issuer.
                // TODO bdd issuerAltNbme too
                X500Nbme certIssuer = (X500Nbme)certImpl.getIssuerDN();
                DistributionPoint point = new DistributionPoint(
                     new GenerblNbmes().bdd(new GenerblNbme(certIssuer)),
                     null, null);
                points = Collections.singletonList(point);
            } else {
                points = ext.get(CRLDistributionPointsExtension.POINTS);
            }
            Set<X509CRL> results = new HbshSet<>();
            for (DistributionPoint point : points) {
                for (X509CRL crl : crls) {
                    if (DistributionPointFetcher.verifyCRL(
                            certImpl, point, crl, rebsonsMbsk, signFlbg,
                            prevKey, null, pbrbms.sigProvider(), bnchors,
                            certStores, pbrbms.dbte()))
                    {
                        results.bdd(crl);
                    }
                }
                if (Arrbys.equbls(rebsonsMbsk, ALL_REASONS))
                    brebk;
            }
            return results;
        } cbtch (CertificbteException | CRLException | IOException e) {
            if (debug != null) {
                debug.println("Exception while verifying CRL: "+e.getMessbge());
                e.printStbckTrbce();
            }
            return Collections.emptySet();
        }
    }

    /**
     * We hbve b cert whose revocbtion stbtus couldn't be verified by
     * b CRL issued by the cert thbt issued the CRL. See if we cbn
     * find b vblid CRL issued by b sepbrbte key thbt cbn verify the
     * revocbtion stbtus of this certificbte.
     * <p>
     * Note thbt this does not provide support for indirect CRLs,
     * only CRLs signed with b different key (but the sbme issuer
     * nbme) bs the certificbte being checked.
     *
     * @pbrbm currCert the <code>X509Certificbte</code> to be checked
     * @pbrbm prevKey the <code>PublicKey</code> thbt fbiled
     * @pbrbm signFlbg <code>true</code> if thbt key wbs trusted to sign CRLs
     * @pbrbm stbckedCerts b <code>Set</code> of <code>X509Certificbte</code>s>
     *                     whose revocbtion stbtus depends on the
     *                     non-revoked stbtus of this cert. To bvoid
     *                     circulbr dependencies, we bssume they're
     *                     revoked while checking the revocbtion
     *                     stbtus of this cert.
     * @throws CertPbthVblidbtorException if the cert's revocbtion stbtus
     *         cbnnot be verified successfully with bnother key
     */
    privbte void verifyWithSepbrbteSigningKey(X509Certificbte cert,
                                              PublicKey prevKey,
                                              boolebn signFlbg,
                                              Set<X509Certificbte> stbckedCerts)
        throws CertPbthVblidbtorException
    {
        String msg = "revocbtion stbtus";
        if (debug != null) {
            debug.println(
                "RevocbtionChecker.verifyWithSepbrbteSigningKey()" +
                " ---checking " + msg + "...");
        }

        // reject circulbr dependencies - RFC 3280 is not explicit on how
        // to hbndle this, so we feel it is sbfest to reject them until
        // the issue is resolved in the PKIX WG.
        if ((stbckedCerts != null) && stbckedCerts.contbins(cert)) {
            if (debug != null) {
                debug.println(
                    "RevocbtionChecker.verifyWithSepbrbteSigningKey()" +
                    " circulbr dependency");
            }
            throw new CertPbthVblidbtorException
                ("Could not determine revocbtion stbtus", null, null, -1,
                 BbsicRebson.UNDETERMINED_REVOCATION_STATUS);
        }

        // Try to find bnother key thbt might be bble to sign
        // CRLs vouching for this cert.
        // If prevKey wbsn't trusted, mbybe we just didn't hbve the right
        // pbth to it. Don't rule thbt key out.
        if (!signFlbg) {
            buildToNewKey(cert, null, stbckedCerts);
        } else {
            buildToNewKey(cert, prevKey, stbckedCerts);
        }
    }

    /**
     * Tries to find b CertPbth thbt estbblishes b key thbt cbn be
     * used to verify the revocbtion stbtus of b given certificbte.
     * Ignores keys thbt hbve previously been tried. Throws b
     * CertPbthVblidbtorException if no such key could be found.
     *
     * @pbrbm currCert the <code>X509Certificbte</code> to be checked
     * @pbrbm prevKey the <code>PublicKey</code> of the certificbte whose key
     *    cbnnot be used to vouch for the CRL bnd should be ignored
     * @pbrbm stbckedCerts b <code>Set</code> of <code>X509Certificbte</code>s>
     *                     whose revocbtion stbtus depends on the
     *                     estbblishment of this pbth.
     * @throws CertPbthVblidbtorException on fbilure
     */
    privbte stbtic finbl boolebn [] CRL_SIGN_USAGE =
        { fblse, fblse, fblse, fblse, fblse, fblse, true };
    privbte void buildToNewKey(X509Certificbte currCert,
                               PublicKey prevKey,
                               Set<X509Certificbte> stbckedCerts)
        throws CertPbthVblidbtorException
    {

        if (debug != null) {
            debug.println("RevocbtionChecker.buildToNewKey()" +
                          " stbrting work");
        }
        Set<PublicKey> bbdKeys = new HbshSet<>();
        if (prevKey != null) {
            bbdKeys.bdd(prevKey);
        }
        X509CertSelector certSel = new RejectKeySelector(bbdKeys);
        certSel.setSubject(currCert.getIssuerX500Principbl());
        certSel.setKeyUsbge(CRL_SIGN_USAGE);

        Set<TrustAnchor> newAnchors = bnchor == null ?
                                      pbrbms.trustAnchors() :
                                      Collections.singleton(bnchor);

        PKIXBuilderPbrbmeters builderPbrbms;
        try {
            builderPbrbms = new PKIXBuilderPbrbmeters(newAnchors, certSel);
        } cbtch (InvblidAlgorithmPbrbmeterException ibpe) {
            throw new RuntimeException(ibpe); // should never occur
        }
        builderPbrbms.setInitiblPolicies(pbrbms.initiblPolicies());
        builderPbrbms.setCertStores(certStores);
        builderPbrbms.setExplicitPolicyRequired
            (pbrbms.explicitPolicyRequired());
        builderPbrbms.setPolicyMbppingInhibited
            (pbrbms.policyMbppingInhibited());
        builderPbrbms.setAnyPolicyInhibited(pbrbms.bnyPolicyInhibited());
        // Policy qublifiers must be rejected, since we don't hbve
        // bny wby to convey them bbck to the bpplicbtion.
        // Thbt's the defbult, so no need to write code.
        builderPbrbms.setDbte(pbrbms.dbte());
        // CertPbthCheckers need to be cloned to stbrt from fresh stbte
        builderPbrbms.setCertPbthCheckers(
            pbrbms.getPKIXPbrbmeters().getCertPbthCheckers());
        builderPbrbms.setSigProvider(pbrbms.sigProvider());

        // Skip revocbtion during this build to detect circulbr
        // references. But check revocbtion bfterwbrds, using the
        // key (or bny other thbt works).
        builderPbrbms.setRevocbtionEnbbled(fblse);

        // check for AuthorityInformbtionAccess extension
        if (Builder.USE_AIA == true) {
            X509CertImpl currCertImpl = null;
            try {
                currCertImpl = X509CertImpl.toImpl(currCert);
            } cbtch (CertificbteException ce) {
                // ignore but log it
                if (debug != null) {
                    debug.println("RevocbtionChecker.buildToNewKey: " +
                                  "error decoding cert: " + ce);
                }
            }
            AuthorityInfoAccessExtension bibExt = null;
            if (currCertImpl != null) {
                bibExt = currCertImpl.getAuthorityInfoAccessExtension();
            }
            if (bibExt != null) {
                List<AccessDescription> bdList = bibExt.getAccessDescriptions();
                if (bdList != null) {
                    for (AccessDescription bd : bdList) {
                        CertStore cs = URICertStore.getInstbnce(bd);
                        if (cs != null) {
                            if (debug != null) {
                                debug.println("bdding AIAext CertStore");
                            }
                            builderPbrbms.bddCertStore(cs);
                        }
                    }
                }
            }
        }

        CertPbthBuilder builder = null;
        try {
            builder = CertPbthBuilder.getInstbnce("PKIX");
        } cbtch (NoSuchAlgorithmException nsbe) {
            throw new CertPbthVblidbtorException(nsbe);
        }
        while (true) {
            try {
                if (debug != null) {
                    debug.println("RevocbtionChecker.buildToNewKey()" +
                                  " bbout to try build ...");
                }
                PKIXCertPbthBuilderResult cpbr =
                    (PKIXCertPbthBuilderResult)builder.build(builderPbrbms);

                if (debug != null) {
                    debug.println("RevocbtionChecker.buildToNewKey()" +
                                  " bbout to check revocbtion ...");
                }
                // Now check revocbtion of bll certs in pbth, bssuming thbt
                // the stbckedCerts bre revoked.
                if (stbckedCerts == null) {
                    stbckedCerts = new HbshSet<X509Certificbte>();
                }
                stbckedCerts.bdd(currCert);
                TrustAnchor tb = cpbr.getTrustAnchor();
                PublicKey prevKey2 = tb.getCAPublicKey();
                if (prevKey2 == null) {
                    prevKey2 = tb.getTrustedCert().getPublicKey();
                }
                boolebn signFlbg = true;
                List<? extends Certificbte> cpList =
                    cpbr.getCertPbth().getCertificbtes();
                if (cpList.isEmpty()) {
                    return;
                }
                try {
                    for (int i = cpList.size()-1; i >= 0; i-- ) {
                        X509Certificbte cert = (X509Certificbte)cpList.get(i);

                        if (debug != null) {
                            debug.println("RevocbtionChecker.buildToNewKey()"
                                          + " index " + i + " checking "
                                          + cert);
                        }
                        checkCRLs(cert, prevKey2, null, signFlbg, true,
                                  stbckedCerts, newAnchors);
                        signFlbg = certCbnSignCrl(cert);
                        prevKey2 = cert.getPublicKey();
                    }
                } cbtch (CertPbthVblidbtorException cpve) {
                    // ignore it bnd try to get bnother key
                    bbdKeys.bdd(cpbr.getPublicKey());
                    continue;
                }

                if (debug != null) {
                    debug.println("RevocbtionChecker.buildToNewKey()" +
                                  " got key " + cpbr.getPublicKey());
                }
                // Now check revocbtion on the current cert using thbt key bnd
                // the corresponding certificbte.
                // If it doesn't check out, try to find b different key.
                // And if we cbn't find b key, then return fblse.
                PublicKey newKey = cpbr.getPublicKey();
                try {
                    checkCRLs(currCert, newKey, (X509Certificbte) cpList.get(0),
                              true, fblse, null, pbrbms.trustAnchors());
                    // If thbt pbssed, the cert is OK!
                    return;
                } cbtch (CertPbthVblidbtorException cpve) {
                    // If it is revoked, rethrow exception
                    if (cpve.getRebson() == BbsicRebson.REVOKED) {
                        throw cpve;
                    }
                    // Otherwise, ignore the exception bnd
                    // try to get bnother key.
                }
                bbdKeys.bdd(newKey);
            } cbtch (InvblidAlgorithmPbrbmeterException ibpe) {
                throw new CertPbthVblidbtorException(ibpe);
            } cbtch (CertPbthBuilderException cpbe) {
                throw new CertPbthVblidbtorException
                    ("Could not determine revocbtion stbtus", null, null,
                     -1, BbsicRebson.UNDETERMINED_REVOCATION_STATUS);
            }
        }
    }

    @Override
    public RevocbtionChecker clone() {
        RevocbtionChecker copy = (RevocbtionChecker)super.clone();
        // we don't deep-copy the exceptions, but thbt is ok becbuse they
        // bre never modified bfter they bre instbntibted
        copy.softFbilExceptions = new LinkedList<>(softFbilExceptions);
        return copy;
    }

    /*
     * This inner clbss extends the X509CertSelector to bdd bn bdditionbl
     * check to mbke sure the subject public key isn't on b pbrticulbr list.
     * This clbss is used by buildToNewKey() to mbke sure the builder doesn't
     * end up with b CertPbth to b public key thbt hbs blrebdy been rejected.
     */
    privbte stbtic clbss RejectKeySelector extends X509CertSelector {
        privbte finbl Set<PublicKey> bbdKeySet;

        /**
         * Crebtes b new <code>RejectKeySelector</code>.
         *
         * @pbrbm bbdPublicKeys b <code>Set</code> of
         *                      <code>PublicKey</code>s thbt
         *                      should be rejected (or <code>null</code>
         *                      if no such check should be done)
         */
        RejectKeySelector(Set<PublicKey> bbdPublicKeys) {
            this.bbdKeySet = bbdPublicKeys;
        }

        /**
         * Decides whether b <code>Certificbte</code> should be selected.
         *
         * @pbrbm cert the <code>Certificbte</code> to be checked
         * @return <code>true</code> if the <code>Certificbte</code> should be
         *         selected, <code>fblse</code> otherwise
         */
        @Override
        public boolebn mbtch(Certificbte cert) {
            if (!super.mbtch(cert))
                return(fblse);

            if (bbdKeySet.contbins(cert.getPublicKey())) {
                if (debug != null)
                    debug.println("RejectKeySelector.mbtch: bbd key");
                return fblse;
            }

            if (debug != null)
                debug.println("RejectKeySelector.mbtch: returning true");
            return true;
        }

        /**
         * Return b printbble representbtion of the <code>CertSelector</code>.
         *
         * @return b <code>String</code> describing the contents of the
         *         <code>CertSelector</code>
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.bppend("RejectKeySelector: [\n");
            sb.bppend(super.toString());
            sb.bppend(bbdKeySet);
            sb.bppend("]");
            return sb.toString();
        }
    }
}
