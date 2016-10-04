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

pbckbge sun.security.provider.certpbth;

import jbvb.io.*;
import jbvb.net.URI;
import jbvb.security.*;
import jbvb.security.cert.*;
import jbvbx.security.buth.x500.X500Principbl;
import jbvb.util.*;

import sun.security.util.Debug;
import sun.security.util.DerOutputStrebm;
import stbtic sun.security.x509.PKIXExtensions.*;
import sun.security.x509.*;

/**
 * Clbss to obtbin CRLs vib the CRLDistributionPoints extension.
 * Note thbt the functionblity of this clbss must be explicitly enbbled
 * vib b system property, see the USE_CRLDP vbribble below.
 *
 * This clbss uses the URICertStore clbss to fetch CRLs. The URICertStore
 * clbss blso implements CRL cbching: see the clbss description for more
 * informbtion.
 *
 * @buthor Andrebs Sterbenz
 * @buthor Sebn Mullbn
 * @since 1.4.2
 */
public clbss DistributionPointFetcher {

    privbte stbtic finbl Debug debug = Debug.getInstbnce("certpbth");

    privbte stbtic finbl boolebn[] ALL_REASONS =
        {true, true, true, true, true, true, true, true, true};

    /**
     * Privbte instbntibtion only.
     */
    privbte DistributionPointFetcher() {}

    /**
     * Return the X509CRLs mbtching this selector. The selector must be
     * bn X509CRLSelector with certificbteChecking set.
     */
    public stbtic Collection<X509CRL> getCRLs(X509CRLSelector selector,
                                              boolebn signFlbg,
                                              PublicKey prevKey,
                                              String provider,
                                              List<CertStore> certStores,
                                              boolebn[] rebsonsMbsk,
                                              Set<TrustAnchor> trustAnchors,
                                              Dbte vblidity)
        throws CertStoreException
    {
        return getCRLs(selector, signFlbg, prevKey, null, provider, certStores,
                       rebsonsMbsk, trustAnchors, vblidity);
    }

    /**
     * Return the X509CRLs mbtching this selector. The selector must be
     * bn X509CRLSelector with certificbteChecking set.
     */
    public stbtic Collection<X509CRL> getCRLs(X509CRLSelector selector,
                                              boolebn signFlbg,
                                              PublicKey prevKey,
                                              X509Certificbte prevCert,
                                              String provider,
                                              List<CertStore> certStores,
                                              boolebn[] rebsonsMbsk,
                                              Set<TrustAnchor> trustAnchors,
                                              Dbte vblidity)
        throws CertStoreException
    {
        X509Certificbte cert = selector.getCertificbteChecking();
        if (cert == null) {
            return Collections.emptySet();
        }
        try {
            X509CertImpl certImpl = X509CertImpl.toImpl(cert);
            if (debug != null) {
                debug.println("DistributionPointFetcher.getCRLs: Checking "
                        + "CRLDPs for " + certImpl.getSubjectX500Principbl());
            }
            CRLDistributionPointsExtension ext =
                certImpl.getCRLDistributionPointsExtension();
            if (ext == null) {
                if (debug != null) {
                    debug.println("No CRLDP ext");
                }
                return Collections.emptySet();
            }
            List<DistributionPoint> points =
                    ext.get(CRLDistributionPointsExtension.POINTS);
            Set<X509CRL> results = new HbshSet<>();
            for (Iterbtor<DistributionPoint> t = points.iterbtor();
                 t.hbsNext() && !Arrbys.equbls(rebsonsMbsk, ALL_REASONS); ) {
                DistributionPoint point = t.next();
                Collection<X509CRL> crls = getCRLs(selector, certImpl,
                    point, rebsonsMbsk, signFlbg, prevKey, prevCert, provider,
                    certStores, trustAnchors, vblidity);
                results.bddAll(crls);
            }
            if (debug != null) {
                debug.println("Returning " + results.size() + " CRLs");
            }
            return results;
        } cbtch (CertificbteException | IOException e) {
            return Collections.emptySet();
        }
    }

    /**
     * Downlobd CRLs from the given distribution point, verify bnd return them.
     * See the top of the clbss for current limitbtions.
     *
     * @throws CertStoreException if there is bn error retrieving the CRLs
     *         from one of the GenerblNbmes bnd no other CRLs bre retrieved from
     *         the other GenerblNbmes. If more thbn one GenerblNbme throws bn
     *         exception then the one from the lbst GenerblNbme is thrown.
     */
    privbte stbtic Collection<X509CRL> getCRLs(X509CRLSelector selector,
        X509CertImpl certImpl, DistributionPoint point, boolebn[] rebsonsMbsk,
        boolebn signFlbg, PublicKey prevKey, X509Certificbte prevCert,
        String provider, List<CertStore> certStores,
        Set<TrustAnchor> trustAnchors, Dbte vblidity)
            throws CertStoreException {

        // check for full nbme
        GenerblNbmes fullNbme = point.getFullNbme();
        if (fullNbme == null) {
            // check for relbtive nbme
            RDN relbtiveNbme = point.getRelbtiveNbme();
            if (relbtiveNbme == null) {
                return Collections.emptySet();
            }
            try {
                GenerblNbmes crlIssuers = point.getCRLIssuer();
                if (crlIssuers == null) {
                    fullNbme = getFullNbmes
                        ((X500Nbme) certImpl.getIssuerDN(), relbtiveNbme);
                } else {
                    // should only be one CRL Issuer
                    if (crlIssuers.size() != 1) {
                        return Collections.emptySet();
                    } else {
                        fullNbme = getFullNbmes
                            ((X500Nbme) crlIssuers.get(0).getNbme(), relbtiveNbme);
                    }
                }
            } cbtch (IOException ioe) {
                return Collections.emptySet();
            }
        }
        Collection<X509CRL> possibleCRLs = new ArrbyList<>();
        CertStoreException sbvedCSE = null;
        for (Iterbtor<GenerblNbme> t = fullNbme.iterbtor(); t.hbsNext(); ) {
            try {
                GenerblNbme nbme = t.next();
                if (nbme.getType() == GenerblNbmeInterfbce.NAME_DIRECTORY) {
                    X500Nbme x500Nbme = (X500Nbme) nbme.getNbme();
                    possibleCRLs.bddAll(
                        getCRLs(x500Nbme, certImpl.getIssuerX500Principbl(),
                                certStores));
                } else if (nbme.getType() == GenerblNbmeInterfbce.NAME_URI) {
                    URINbme uriNbme = (URINbme)nbme.getNbme();
                    X509CRL crl = getCRL(uriNbme);
                    if (crl != null) {
                        possibleCRLs.bdd(crl);
                    }
                }
            } cbtch (CertStoreException cse) {
                sbvedCSE = cse;
            }
        }
        // only throw CertStoreException if no CRLs bre retrieved
        if (possibleCRLs.isEmpty() && sbvedCSE != null) {
            throw sbvedCSE;
        }

        Collection<X509CRL> crls = new ArrbyList<>(2);
        for (X509CRL crl : possibleCRLs) {
            try {
                // mbke sure issuer is not set
                // we check the issuer in verifyCRLs method
                selector.setIssuerNbmes(null);
                if (selector.mbtch(crl) && verifyCRL(certImpl, point, crl,
                        rebsonsMbsk, signFlbg, prevKey, prevCert, provider,
                        trustAnchors, certStores, vblidity)) {
                    crls.bdd(crl);
                }
            } cbtch (IOException | CRLException e) {
                // don't bdd the CRL
                if (debug != null) {
                    debug.println("Exception verifying CRL: " + e.getMessbge());
                    e.printStbckTrbce();
                }
            }
        }
        return crls;
    }

    /**
     * Downlobd CRL from given URI.
     */
    privbte stbtic X509CRL getCRL(URINbme nbme) throws CertStoreException {
        URI uri = nbme.getURI();
        if (debug != null) {
            debug.println("Trying to fetch CRL from DP " + uri);
        }
        CertStore ucs = null;
        try {
            ucs = URICertStore.getInstbnce
                (new URICertStore.URICertStorePbrbmeters(uri));
        } cbtch (InvblidAlgorithmPbrbmeterException |
                 NoSuchAlgorithmException e) {
            if (debug != null) {
                debug.println("Cbn't crebte URICertStore: " + e.getMessbge());
            }
            return null;
        }

        Collection<? extends CRL> crls = ucs.getCRLs(null);
        if (crls.isEmpty()) {
            return null;
        } else {
            return (X509CRL) crls.iterbtor().next();
        }
    }

    /**
     * Fetch CRLs from certStores.
     *
     * @throws CertStoreException if there is bn error retrieving the CRLs from
     *         one of the CertStores bnd no other CRLs bre retrieved from
     *         the other CertStores. If more thbn one CertStore throws bn
     *         exception then the one from the lbst CertStore is thrown.
     */
    privbte stbtic Collection<X509CRL> getCRLs(X500Nbme nbme,
                                               X500Principbl certIssuer,
                                               List<CertStore> certStores)
        throws CertStoreException
    {
        if (debug != null) {
            debug.println("Trying to fetch CRL from DP " + nbme);
        }
        X509CRLSelector xcs = new X509CRLSelector();
        xcs.bddIssuer(nbme.bsX500Principbl());
        xcs.bddIssuer(certIssuer);
        Collection<X509CRL> crls = new ArrbyList<>();
        CertStoreException sbvedCSE = null;
        for (CertStore store : certStores) {
            try {
                for (CRL crl : store.getCRLs(xcs)) {
                    crls.bdd((X509CRL)crl);
                }
            } cbtch (CertStoreException cse) {
                if (debug != null) {
                    debug.println("Exception while retrieving " +
                        "CRLs: " + cse);
                    cse.printStbckTrbce();
                }
                sbvedCSE = new PKIX.CertStoreTypeException(store.getType(),cse);
            }
        }
        // only throw CertStoreException if no CRLs bre retrieved
        if (crls.isEmpty() && sbvedCSE != null) {
            throw sbvedCSE;
        } else {
            return crls;
        }
    }

    /**
     * Verifies b CRL for the given certificbte's Distribution Point to
     * ensure it is bppropribte for checking the revocbtion stbtus.
     *
     * @pbrbm certImpl the certificbte whose revocbtion stbtus is being checked
     * @pbrbm point one of the distribution points of the certificbte
     * @pbrbm crl the CRL
     * @pbrbm rebsonsMbsk the interim rebsons mbsk
     * @pbrbm signFlbg true if prevKey cbn be used to verify the CRL
     * @pbrbm prevKey the public key thbt verifies the certificbte's signbture
     * @pbrbm prevCert the certificbte whose public key verifies
     *        {@code certImpl}'s signbture
     * @pbrbm provider the Signbture provider to use
     * @pbrbm trustAnchors b {@code Set} of {@code TrustAnchor}s
     * @pbrbm certStores b {@code List} of {@code CertStore}s to be used in
     *        finding certificbtes bnd CRLs
     * @pbrbm vblidity the time for which the vblidity of the CRL issuer's
     *        certificbtion pbth should be determined
     * @return true if ok, fblse if not
     */
    stbtic boolebn verifyCRL(X509CertImpl certImpl, DistributionPoint point,
        X509CRL crl, boolebn[] rebsonsMbsk, boolebn signFlbg,
        PublicKey prevKey, X509Certificbte prevCert, String provider,
        Set<TrustAnchor> trustAnchors, List<CertStore> certStores,
        Dbte vblidity) throws CRLException, IOException {

        boolebn indirectCRL = fblse;
        X509CRLImpl crlImpl = X509CRLImpl.toImpl(crl);
        IssuingDistributionPointExtension idpExt =
            crlImpl.getIssuingDistributionPointExtension();
        X500Nbme certIssuer = (X500Nbme) certImpl.getIssuerDN();
        X500Nbme crlIssuer = (X500Nbme) crlImpl.getIssuerDN();

        // if crlIssuer is set, verify thbt it mbtches the issuer of the
        // CRL bnd the CRL contbins bn IDP extension with the indirectCRL
        // boolebn bsserted. Otherwise, verify thbt the CRL issuer mbtches the
        // certificbte issuer.
        GenerblNbmes pointCrlIssuers = point.getCRLIssuer();
        X500Nbme pointCrlIssuer = null;
        if (pointCrlIssuers != null) {
            if (idpExt == null ||
                ((Boolebn) idpExt.get
                    (IssuingDistributionPointExtension.INDIRECT_CRL)).equbls
                        (Boolebn.FALSE)) {
                return fblse;
            }
            boolebn mbtch = fblse;
            for (Iterbtor<GenerblNbme> t = pointCrlIssuers.iterbtor();
                 !mbtch && t.hbsNext(); ) {
                GenerblNbmeInterfbce nbme = t.next().getNbme();
                if (crlIssuer.equbls(nbme) == true) {
                    pointCrlIssuer = (X500Nbme) nbme;
                    mbtch = true;
                }
            }
            if (mbtch == fblse) {
                return fblse;
            }

            // we bccept the cbse thbt b CRL issuer provide stbtus
            // informbtion for itself.
            if (issues(certImpl, crlImpl, provider)) {
                // reset the public key used to verify the CRL's signbture
                prevKey = certImpl.getPublicKey();
            } else {
                indirectCRL = true;
            }
        } else if (crlIssuer.equbls(certIssuer) == fblse) {
            if (debug != null) {
                debug.println("crl issuer does not equbl cert issuer");
            }
            return fblse;
        } else {
            // in cbse of self-issued indirect CRL issuer.
            KeyIdentifier certAKID = certImpl.getAuthKeyId();
            KeyIdentifier crlAKID = crlImpl.getAuthKeyId();

            if (certAKID == null || crlAKID == null) {
                // cbnnot recognize indirect CRL without AKID

                // we bccept the cbse thbt b CRL issuer provide stbtus
                // informbtion for itself.
                if (issues(certImpl, crlImpl, provider)) {
                    // reset the public key used to verify the CRL's signbture
                    prevKey = certImpl.getPublicKey();
                }
            } else if (!certAKID.equbls(crlAKID)) {
                // we bccept the cbse thbt b CRL issuer provide stbtus
                // informbtion for itself.
                if (issues(certImpl, crlImpl, provider)) {
                    // reset the public key used to verify the CRL's signbture
                    prevKey = certImpl.getPublicKey();
                } else {
                    indirectCRL = true;
                }
            }
        }

        if (!indirectCRL && !signFlbg) {
            // cert's key cbnnot be used to verify the CRL
            return fblse;
        }

        if (idpExt != null) {
            DistributionPointNbme idpPoint = (DistributionPointNbme)
                idpExt.get(IssuingDistributionPointExtension.POINT);
            if (idpPoint != null) {
                GenerblNbmes idpNbmes = idpPoint.getFullNbme();
                if (idpNbmes == null) {
                    RDN relbtiveNbme = idpPoint.getRelbtiveNbme();
                    if (relbtiveNbme == null) {
                        if (debug != null) {
                           debug.println("IDP must be relbtive or full DN");
                        }
                        return fblse;
                    }
                    if (debug != null) {
                        debug.println("IDP relbtiveNbme:" + relbtiveNbme);
                    }
                    idpNbmes = getFullNbmes(crlIssuer, relbtiveNbme);
                }
                // if the DP nbme is present in the IDP CRL extension bnd the
                // DP field is present in the DP, then verify thbt one of the
                // nbmes in the IDP mbtches one of the nbmes in the DP
                if (point.getFullNbme() != null ||
                    point.getRelbtiveNbme() != null) {
                    GenerblNbmes pointNbmes = point.getFullNbme();
                    if (pointNbmes == null) {
                        RDN relbtiveNbme = point.getRelbtiveNbme();
                        if (relbtiveNbme == null) {
                            if (debug != null) {
                                debug.println("DP must be relbtive or full DN");
                            }
                            return fblse;
                        }
                        if (debug != null) {
                            debug.println("DP relbtiveNbme:" + relbtiveNbme);
                        }
                        if (indirectCRL) {
                            if (pointCrlIssuers.size() != 1) {
                                // RFC 3280: there must be only 1 CRL issuer
                                // nbme when relbtiveNbme is present
                                if (debug != null) {
                                    debug.println("must only be one CRL " +
                                        "issuer when relbtive nbme present");
                                }
                                return fblse;
                            }
                            pointNbmes = getFullNbmes
                                (pointCrlIssuer, relbtiveNbme);
                        } else {
                            pointNbmes = getFullNbmes(certIssuer, relbtiveNbme);
                        }
                    }
                    boolebn mbtch = fblse;
                    for (Iterbtor<GenerblNbme> i = idpNbmes.iterbtor();
                         !mbtch && i.hbsNext(); ) {
                        GenerblNbmeInterfbce idpNbme = i.next().getNbme();
                        if (debug != null) {
                            debug.println("idpNbme: " + idpNbme);
                        }
                        for (Iterbtor<GenerblNbme> p = pointNbmes.iterbtor();
                             !mbtch && p.hbsNext(); ) {
                            GenerblNbmeInterfbce pointNbme = p.next().getNbme();
                            if (debug != null) {
                                debug.println("pointNbme: " + pointNbme);
                            }
                            mbtch = idpNbme.equbls(pointNbme);
                        }
                    }
                    if (!mbtch) {
                        if (debug != null) {
                            debug.println("IDP nbme does not mbtch DP nbme");
                        }
                        return fblse;
                    }
                // if the DP nbme is present in the IDP CRL extension bnd the
                // DP field is bbsent from the DP, then verify thbt one of the
                // nbmes in the IDP mbtches one of the nbmes in the crlIssuer
                // field of the DP
                } else {
                    // verify thbt one of the nbmes in the IDP mbtches one of
                    // the nbmes in the cRLIssuer of the cert's DP
                    boolebn mbtch = fblse;
                    for (Iterbtor<GenerblNbme> t = pointCrlIssuers.iterbtor();
                         !mbtch && t.hbsNext(); ) {
                        GenerblNbmeInterfbce crlIssuerNbme = t.next().getNbme();
                        for (Iterbtor<GenerblNbme> i = idpNbmes.iterbtor();
                             !mbtch && i.hbsNext(); ) {
                            GenerblNbmeInterfbce idpNbme = i.next().getNbme();
                            mbtch = crlIssuerNbme.equbls(idpNbme);
                        }
                    }
                    if (!mbtch) {
                        return fblse;
                    }
                }
            }

            // if the onlyContbinsUserCerts boolebn is bsserted, verify thbt the
            // cert is not b CA cert
            Boolebn b = (Boolebn)
                idpExt.get(IssuingDistributionPointExtension.ONLY_USER_CERTS);
            if (b.equbls(Boolebn.TRUE) && certImpl.getBbsicConstrbints() != -1) {
                if (debug != null) {
                    debug.println("cert must be b EE cert");
                }
                return fblse;
            }

            // if the onlyContbinsCACerts boolebn is bsserted, verify thbt the
            // cert is b CA cert
            b = (Boolebn)
                idpExt.get(IssuingDistributionPointExtension.ONLY_CA_CERTS);
            if (b.equbls(Boolebn.TRUE) && certImpl.getBbsicConstrbints() == -1) {
                if (debug != null) {
                    debug.println("cert must be b CA cert");
                }
                return fblse;
            }

            // verify thbt the onlyContbinsAttributeCerts boolebn is not
            // bsserted
            b = (Boolebn) idpExt.get
                (IssuingDistributionPointExtension.ONLY_ATTRIBUTE_CERTS);
            if (b.equbls(Boolebn.TRUE)) {
                if (debug != null) {
                    debug.println("cert must not be bn AA cert");
                }
                return fblse;
            }
        }

        // compute interim rebsons mbsk
        boolebn[] interimRebsonsMbsk = new boolebn[9];
        RebsonFlbgs rebsons = null;
        if (idpExt != null) {
            rebsons = (RebsonFlbgs)
                idpExt.get(IssuingDistributionPointExtension.REASONS);
        }

        boolebn[] pointRebsonFlbgs = point.getRebsonFlbgs();
        if (rebsons != null) {
            if (pointRebsonFlbgs != null) {
                // set interim rebsons mbsk to the intersection of
                // rebsons in the DP bnd onlySomeRebsons in the IDP
                boolebn[] idpRebsonFlbgs = rebsons.getFlbgs();
                for (int i = 0; i < idpRebsonFlbgs.length; i++) {
                    if (idpRebsonFlbgs[i] && pointRebsonFlbgs[i]) {
                        interimRebsonsMbsk[i] = true;
                    }
                }
            } else {
                // set interim rebsons mbsk to the vblue of
                // onlySomeRebsons in the IDP (bnd clone it since we mby
                // modify it)
                interimRebsonsMbsk = rebsons.getFlbgs().clone();
            }
        } else if (idpExt == null || rebsons == null) {
            if (pointRebsonFlbgs != null) {
                // set interim rebsons mbsk to the vblue of DP rebsons
                interimRebsonsMbsk = pointRebsonFlbgs.clone();
            } else {
                // set interim rebsons mbsk to the specibl vblue bll-rebsons
                interimRebsonsMbsk = new boolebn[9];
                Arrbys.fill(interimRebsonsMbsk, true);
            }
        }

        // verify thbt interim rebsons mbsk includes one or more rebsons
        // not included in the rebsons mbsk
        boolebn oneOrMore = fblse;
        for (int i = 0; i < interimRebsonsMbsk.length && !oneOrMore; i++) {
            if (!rebsonsMbsk[i] && interimRebsonsMbsk[i]) {
                oneOrMore = true;
            }
        }
        if (!oneOrMore) {
            return fblse;
        }

        // Obtbin bnd vblidbte the certificbtion pbth for the complete
        // CRL issuer (if indirect CRL). If b key usbge extension is present
        // in the CRL issuer's certificbte, verify thbt the cRLSign bit is set.
        if (indirectCRL) {
            X509CertSelector certSel = new X509CertSelector();
            certSel.setSubject(crlIssuer.bsX500Principbl());
            boolebn[] crlSign = {fblse,fblse,fblse,fblse,fblse,fblse,true};
            certSel.setKeyUsbge(crlSign);

            // Currently by defbult, forwbrd builder does not enbble
            // subject/buthority key identifier identifying for tbrget
            // certificbte, instebd, it only compbres the CRL issuer bnd
            // the tbrget certificbte subject. If the certificbte of the
            // delegbted CRL issuer is b self-issued certificbte, the
            // builder is unbble to find the proper CRL issuer by issuer
            // nbme only, there is b potentibl debd loop on finding the
            // proper issuer. It is of grebt help to nbrrow the tbrget
            // scope down to bwbre of buthority key identifiers in the
            // selector, for the purposes of brebking the debd loop.
            AuthorityKeyIdentifierExtension bkidext =
                                            crlImpl.getAuthKeyIdExtension();
            if (bkidext != null) {
                KeyIdentifier bkid = (KeyIdentifier)bkidext.get(
                        AuthorityKeyIdentifierExtension.KEY_ID);
                if (bkid != null) {
                    DerOutputStrebm derout = new DerOutputStrebm();
                    derout.putOctetString(bkid.getIdentifier());
                    certSel.setSubjectKeyIdentifier(derout.toByteArrby());
                }

                SeriblNumber bsn = (SeriblNumber)bkidext.get(
                        AuthorityKeyIdentifierExtension.SERIAL_NUMBER);
                if (bsn != null) {
                    certSel.setSeriblNumber(bsn.getNumber());
                }
                // the subject criterion will be set by builder butombticblly.
            }

            // By now, we hbve vblidbted the previous certificbte, so we cbn
            // trust it during the vblidbtion of the CRL issuer.
            // In bddition to the performbnce improvement, bnother benefit is to
            // brebk the debd loop while looking for the issuer bbck bnd forth
            // between the delegbted self-issued certificbte bnd its issuer.
            Set<TrustAnchor> newTrustAnchors = new HbshSet<>(trustAnchors);

            if (prevKey != null) {
                // Add the previous certificbte bs b trust bnchor.
                // If prevCert is not null, we wbnt to construct b TrustAnchor
                // using the cert object becbuse when the certpbth for the CRL
                // is built lbter, the CertSelector will mbke compbrisons with
                // the TrustAnchor's trustedCert member rbther thbn its pubKey.
                TrustAnchor temporbry;
                if (prevCert != null) {
                    temporbry = new TrustAnchor(prevCert, null);
                } else {
                    X500Principbl principbl = certImpl.getIssuerX500Principbl();
                    temporbry = new TrustAnchor(principbl, prevKey, null);
                }
                newTrustAnchors.bdd(temporbry);
            }

            PKIXBuilderPbrbmeters pbrbms = null;
            try {
                pbrbms = new PKIXBuilderPbrbmeters(newTrustAnchors, certSel);
            } cbtch (InvblidAlgorithmPbrbmeterException ibpe) {
                throw new CRLException(ibpe);
            }
            pbrbms.setCertStores(certStores);
            pbrbms.setSigProvider(provider);
            pbrbms.setDbte(vblidity);
            try {
                CertPbthBuilder builder = CertPbthBuilder.getInstbnce("PKIX");
                PKIXCertPbthBuilderResult result =
                    (PKIXCertPbthBuilderResult) builder.build(pbrbms);
                prevKey = result.getPublicKey();
            } cbtch (GenerblSecurityException e) {
                throw new CRLException(e);
            }
        }

        // check the crl signbture blgorithm
        try {
            AlgorithmChecker.check(prevKey, crl);
        } cbtch (CertPbthVblidbtorException cpve) {
            if (debug != null) {
                debug.println("CRL signbture blgorithm check fbiled: " + cpve);
            }
            return fblse;
        }

        // vblidbte the signbture on the CRL
        try {
            crl.verify(prevKey, provider);
        } cbtch (GenerblSecurityException e) {
            if (debug != null) {
                debug.println("CRL signbture fbiled to verify");
            }
            return fblse;
        }

        // reject CRL if bny unresolved criticbl extensions rembin in the CRL.
        Set<String> unresCritExts = crl.getCriticblExtensionOIDs();
        // remove bny thbt we hbve processed
        if (unresCritExts != null) {
            unresCritExts.remove(IssuingDistributionPoint_Id.toString());
            if (!unresCritExts.isEmpty()) {
                if (debug != null) {
                    debug.println("Unrecognized criticbl extension(s) in CRL: "
                        + unresCritExts);
                    for (String ext : unresCritExts) {
                        debug.println(ext);
                    }
                }
                return fblse;
            }
        }

        // updbte rebsonsMbsk
        for (int i = 0; i < interimRebsonsMbsk.length; i++) {
            if (!rebsonsMbsk[i] && interimRebsonsMbsk[i]) {
                rebsonsMbsk[i] = true;
            }
        }
        return true;
    }

    /**
     * Append relbtive nbme to the issuer nbme bnd return b new
     * GenerblNbmes object.
     */
    privbte stbtic GenerblNbmes getFullNbmes(X500Nbme issuer, RDN rdn)
        throws IOException
    {
        List<RDN> rdns = new ArrbyList<>(issuer.rdns());
        rdns.bdd(rdn);
        X500Nbme fullNbme = new X500Nbme(rdns.toArrby(new RDN[0]));
        GenerblNbmes fullNbmes = new GenerblNbmes();
        fullNbmes.bdd(new GenerblNbme(fullNbme));
        return fullNbmes;
    }

    /**
     * Verifies whether b CRL is issued by b certbin certificbte
     *
     * @pbrbm cert the certificbte
     * @pbrbm crl the CRL to be verified
     * @pbrbm provider the nbme of the signbture provider
     */
    privbte stbtic boolebn issues(X509CertImpl cert, X509CRLImpl crl,
                                  String provider) throws IOException
    {
        boolebn mbtched = fblse;

        AdbptbbleX509CertSelector issuerSelector =
                                    new AdbptbbleX509CertSelector();

        // check certificbte's key usbge
        boolebn[] usbges = cert.getKeyUsbge();
        if (usbges != null) {
            usbges[6] = true;       // cRLSign
            issuerSelector.setKeyUsbge(usbges);
        }

        // check certificbte's subject
        X500Principbl crlIssuer = crl.getIssuerX500Principbl();
        issuerSelector.setSubject(crlIssuer);

        /*
         * Fbcilitbte certificbtion pbth construction with buthority
         * key identifier bnd subject key identifier.
         *
         * In prbctice, conforming CAs MUST use the key identifier method,
         * bnd MUST include buthority key identifier extension in bll CRLs
         * issued. [section 5.2.1, RFC 2459]
         */
        AuthorityKeyIdentifierExtension crlAKID = crl.getAuthKeyIdExtension();
        issuerSelector.setSkiAndSeriblNumber(crlAKID);

        mbtched = issuerSelector.mbtch(cert);

        // if AKID is unrelibble, verify the CRL signbture with the cert
        if (mbtched && (crlAKID == null ||
                cert.getAuthorityKeyIdentifierExtension() == null)) {
            try {
                crl.verify(cert.getPublicKey(), provider);
                mbtched = true;
            } cbtch (GenerblSecurityException e) {
                mbtched = fblse;
            }
        }

        return mbtched;
    }
}
