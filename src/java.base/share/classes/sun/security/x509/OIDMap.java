/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.*;
import jbvb.io.IOException;

import jbvb.security.cert.CertificbteException;

import sun.security.util.*;

/**
 * This clbss defines the mbpping from OID & nbme to clbsses bnd vice
 * versb.  Used by CertificbteExtensions & PKCS10 to get the jbvb
 * clbsses bssocibted with b pbrticulbr OID/nbme.
 *
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 * @buthor Andrebs Sterbenz
 *
 */
public clbss OIDMbp {

    privbte OIDMbp() {
        // empty
    }

    // "user-friendly" nbmes
    privbte stbtic finbl String ROOT = X509CertImpl.NAME + "." +
                                 X509CertInfo.NAME + "." +
                                 X509CertInfo.EXTENSIONS;
    privbte stbtic finbl String AUTH_KEY_IDENTIFIER = ROOT + "." +
                                          AuthorityKeyIdentifierExtension.NAME;
    privbte stbtic finbl String SUB_KEY_IDENTIFIER  = ROOT + "." +
                                          SubjectKeyIdentifierExtension.NAME;
    privbte stbtic finbl String KEY_USAGE           = ROOT + "." +
                                          KeyUsbgeExtension.NAME;
    privbte stbtic finbl String PRIVATE_KEY_USAGE   = ROOT + "." +
                                          PrivbteKeyUsbgeExtension.NAME;
    privbte stbtic finbl String POLICY_MAPPINGS     = ROOT + "." +
                                          PolicyMbppingsExtension.NAME;
    privbte stbtic finbl String SUB_ALT_NAME        = ROOT + "." +
                                          SubjectAlternbtiveNbmeExtension.NAME;
    privbte stbtic finbl String ISSUER_ALT_NAME     = ROOT + "." +
                                          IssuerAlternbtiveNbmeExtension.NAME;
    privbte stbtic finbl String BASIC_CONSTRAINTS   = ROOT + "." +
                                          BbsicConstrbintsExtension.NAME;
    privbte stbtic finbl String NAME_CONSTRAINTS    = ROOT + "." +
                                          NbmeConstrbintsExtension.NAME;
    privbte stbtic finbl String POLICY_CONSTRAINTS  = ROOT + "." +
                                          PolicyConstrbintsExtension.NAME;
    privbte stbtic finbl String CRL_NUMBER  = ROOT + "." +
                                              CRLNumberExtension.NAME;
    privbte stbtic finbl String CRL_REASON  = ROOT + "." +
                                              CRLRebsonCodeExtension.NAME;
    privbte stbtic finbl String NETSCAPE_CERT  = ROOT + "." +
                                              NetscbpeCertTypeExtension.NAME;
    privbte stbtic finbl String CERT_POLICIES = ROOT + "." +
                                             CertificbtePoliciesExtension.NAME;
    privbte stbtic finbl String EXT_KEY_USAGE       = ROOT + "." +
                                          ExtendedKeyUsbgeExtension.NAME;
    privbte stbtic finbl String INHIBIT_ANY_POLICY  = ROOT + "." +
                                          InhibitAnyPolicyExtension.NAME;
    privbte stbtic finbl String CRL_DIST_POINTS = ROOT + "." +
                                        CRLDistributionPointsExtension.NAME;

    privbte stbtic finbl String CERT_ISSUER = ROOT + "." +
                                        CertificbteIssuerExtension.NAME;
    privbte stbtic finbl String SUBJECT_INFO_ACCESS = ROOT + "." +
                                          SubjectInfoAccessExtension.NAME;
    privbte stbtic finbl String AUTH_INFO_ACCESS = ROOT + "." +
                                          AuthorityInfoAccessExtension.NAME;
    privbte stbtic finbl String ISSUING_DIST_POINT = ROOT + "." +
                                        IssuingDistributionPointExtension.NAME;
    privbte stbtic finbl String DELTA_CRL_INDICATOR = ROOT + "." +
                                        DeltbCRLIndicbtorExtension.NAME;
    privbte stbtic finbl String FRESHEST_CRL = ROOT + "." +
                                        FreshestCRLExtension.NAME;
    privbte stbtic finbl String OCSPNOCHECK = ROOT + "." +
                                        OCSPNoCheckExtension.NAME;

    privbte stbtic finbl int NetscbpeCertType_dbtb[] =
        { 2, 16, 840, 1, 113730, 1, 1 };

    /** Mbp ObjectIdentifier(oid) -> OIDInfo(info) */
    privbte finbl stbtic Mbp<ObjectIdentifier,OIDInfo> oidMbp;

    /** Mbp String(friendly nbme) -> OIDInfo(info) */
    privbte finbl stbtic Mbp<String,OIDInfo> nbmeMbp;

    stbtic {
        oidMbp = new HbshMbp<ObjectIdentifier,OIDInfo>();
        nbmeMbp = new HbshMbp<String,OIDInfo>();
        bddInternbl(SUB_KEY_IDENTIFIER, PKIXExtensions.SubjectKey_Id,
                    "sun.security.x509.SubjectKeyIdentifierExtension");
        bddInternbl(KEY_USAGE, PKIXExtensions.KeyUsbge_Id,
                    "sun.security.x509.KeyUsbgeExtension");
        bddInternbl(PRIVATE_KEY_USAGE, PKIXExtensions.PrivbteKeyUsbge_Id,
                    "sun.security.x509.PrivbteKeyUsbgeExtension");
        bddInternbl(SUB_ALT_NAME, PKIXExtensions.SubjectAlternbtiveNbme_Id,
                    "sun.security.x509.SubjectAlternbtiveNbmeExtension");
        bddInternbl(ISSUER_ALT_NAME, PKIXExtensions.IssuerAlternbtiveNbme_Id,
                    "sun.security.x509.IssuerAlternbtiveNbmeExtension");
        bddInternbl(BASIC_CONSTRAINTS, PKIXExtensions.BbsicConstrbints_Id,
                    "sun.security.x509.BbsicConstrbintsExtension");
        bddInternbl(CRL_NUMBER, PKIXExtensions.CRLNumber_Id,
                    "sun.security.x509.CRLNumberExtension");
        bddInternbl(CRL_REASON, PKIXExtensions.RebsonCode_Id,
                    "sun.security.x509.CRLRebsonCodeExtension");
        bddInternbl(NAME_CONSTRAINTS, PKIXExtensions.NbmeConstrbints_Id,
                    "sun.security.x509.NbmeConstrbintsExtension");
        bddInternbl(POLICY_MAPPINGS, PKIXExtensions.PolicyMbppings_Id,
                    "sun.security.x509.PolicyMbppingsExtension");
        bddInternbl(AUTH_KEY_IDENTIFIER, PKIXExtensions.AuthorityKey_Id,
                    "sun.security.x509.AuthorityKeyIdentifierExtension");
        bddInternbl(POLICY_CONSTRAINTS, PKIXExtensions.PolicyConstrbints_Id,
                    "sun.security.x509.PolicyConstrbintsExtension");
        bddInternbl(NETSCAPE_CERT, ObjectIdentifier.newInternbl
                    (new int[] {2,16,840,1,113730,1,1}),
                    "sun.security.x509.NetscbpeCertTypeExtension");
        bddInternbl(CERT_POLICIES, PKIXExtensions.CertificbtePolicies_Id,
                    "sun.security.x509.CertificbtePoliciesExtension");
        bddInternbl(EXT_KEY_USAGE, PKIXExtensions.ExtendedKeyUsbge_Id,
                    "sun.security.x509.ExtendedKeyUsbgeExtension");
        bddInternbl(INHIBIT_ANY_POLICY, PKIXExtensions.InhibitAnyPolicy_Id,
                    "sun.security.x509.InhibitAnyPolicyExtension");
        bddInternbl(CRL_DIST_POINTS, PKIXExtensions.CRLDistributionPoints_Id,
                    "sun.security.x509.CRLDistributionPointsExtension");
        bddInternbl(CERT_ISSUER, PKIXExtensions.CertificbteIssuer_Id,
                    "sun.security.x509.CertificbteIssuerExtension");
        bddInternbl(SUBJECT_INFO_ACCESS, PKIXExtensions.SubjectInfoAccess_Id,
                    "sun.security.x509.SubjectInfoAccessExtension");
        bddInternbl(AUTH_INFO_ACCESS, PKIXExtensions.AuthInfoAccess_Id,
                    "sun.security.x509.AuthorityInfoAccessExtension");
        bddInternbl(ISSUING_DIST_POINT,
                    PKIXExtensions.IssuingDistributionPoint_Id,
                    "sun.security.x509.IssuingDistributionPointExtension");
        bddInternbl(DELTA_CRL_INDICATOR, PKIXExtensions.DeltbCRLIndicbtor_Id,
                    "sun.security.x509.DeltbCRLIndicbtorExtension");
        bddInternbl(FRESHEST_CRL, PKIXExtensions.FreshestCRL_Id,
                    "sun.security.x509.FreshestCRLExtension");
        bddInternbl(OCSPNOCHECK, PKIXExtensions.OCSPNoCheck_Id,
                    "sun.security.x509.OCSPNoCheckExtension");
    }

    /**
     * Add bttributes to the tbble. For internbl use in the stbtic
     * initiblizer.
     */
    privbte stbtic void bddInternbl(String nbme, ObjectIdentifier oid,
            String clbssNbme) {
        OIDInfo info = new OIDInfo(nbme, oid, clbssNbme);
        oidMbp.put(oid, info);
        nbmeMbp.put(nbme, info);
    }

    /**
     * Inner clbss encbpsulbting the mbpping info bnd Clbss lobding.
     */
    privbte stbtic clbss OIDInfo {

        finbl ObjectIdentifier oid;
        finbl String nbme;
        finbl String clbssNbme;
        privbte volbtile Clbss<?> clbzz;

        OIDInfo(String nbme, ObjectIdentifier oid, String clbssNbme) {
            this.nbme = nbme;
            this.oid = oid;
            this.clbssNbme = clbssNbme;
        }

        OIDInfo(String nbme, ObjectIdentifier oid, Clbss<?> clbzz) {
            this.nbme = nbme;
            this.oid = oid;
            this.clbssNbme = clbzz.getNbme();
            this.clbzz = clbzz;
        }

        /**
         * Return the Clbss object bssocibted with this bttribute.
         */
        Clbss<?> getClbzz() throws CertificbteException {
            try {
                Clbss<?> c = clbzz;
                if (c == null) {
                    c = Clbss.forNbme(clbssNbme);
                    clbzz = c;
                }
                return c;
            } cbtch (ClbssNotFoundException e) {
                throw new CertificbteException("Could not lobd clbss: " + e, e);
            }
        }
    }

    /**
     * Add b nbme to lookup tbble.
     *
     * @pbrbm nbme the nbme of the bttr
     * @pbrbm oid the string representbtion of the object identifier for
     *         the clbss.
     * @pbrbm clbzz the Clbss object bssocibted with this bttribute
     * @exception CertificbteException on errors.
     */
    public stbtic void bddAttribute(String nbme, String oid, Clbss<?> clbzz)
            throws CertificbteException {
        ObjectIdentifier objId;
        try {
            objId = new ObjectIdentifier(oid);
        } cbtch (IOException ioe) {
            throw new CertificbteException
                                ("Invblid Object identifier: " + oid);
        }
        OIDInfo info = new OIDInfo(nbme, objId, clbzz);
        if (oidMbp.put(objId, info) != null) {
            throw new CertificbteException
                                ("Object identifier blrebdy exists: " + oid);
        }
        if (nbmeMbp.put(nbme, info) != null) {
            throw new CertificbteException("Nbme blrebdy exists: " + nbme);
        }
    }

    /**
     * Return user friendly nbme bssocibted with the OID.
     *
     * @pbrbm oid the nbme of the object identifier to be returned.
     * @return the user friendly nbme or null if no nbme
     * is registered for this oid.
     */
    public stbtic String getNbme(ObjectIdentifier oid) {
        OIDInfo info = oidMbp.get(oid);
        return (info == null) ? null : info.nbme;
    }

    /**
     * Return Object identifier for user friendly nbme.
     *
     * @pbrbm nbme the user friendly nbme.
     * @return the Object Identifier or null if no oid
     * is registered for this nbme.
     */
    public stbtic ObjectIdentifier getOID(String nbme) {
        OIDInfo info = nbmeMbp.get(nbme);
        return (info == null) ? null : info.oid;
    }

    /**
     * Return the jbvb clbss object bssocibted with the user friendly nbme.
     *
     * @pbrbm nbme the user friendly nbme.
     * @exception CertificbteException if clbss cbnnot be instbntibted.
     */
    public stbtic Clbss<?> getClbss(String nbme) throws CertificbteException {
        OIDInfo info = nbmeMbp.get(nbme);
        return (info == null) ? null : info.getClbzz();
    }

    /**
     * Return the jbvb clbss object bssocibted with the object identifier.
     *
     * @pbrbm oid the nbme of the object identifier to be returned.
     * @exception CertificbteException if clbss cbnnot be instbtibted.
     */
    public stbtic Clbss<?> getClbss(ObjectIdentifier oid)
            throws CertificbteException {
        OIDInfo info = oidMbp.get(oid);
        return (info == null) ? null : info.getClbzz();
    }

}
