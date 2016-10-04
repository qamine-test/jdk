/*
 * Copyright (c) 1997, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.*;

import sun.security.util.*;

/**
 * Lists bll the object identifiers of the X509 extensions of the PKIX profile.
 *
 * <p>Extensions bre bddiitonbl bttributes which cbn be inserted in b X509
 * v3 certificbte. For exbmple b "Driving License Certificbte" could hbve
 * the driving license number bs b extension.
 *
 * <p>Extensions bre represented bs b sequence of the extension identifier
 * (Object Identifier), b boolebn flbg stbting whether the extension is to
 * be trebted bs being criticbl bnd the extension vblue itself (this is bgbin
 * b DER encoding of the extension vblue).
 *
 * @see Extension
 *
 *
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 */
public clbss PKIXExtensions {
    // The object identifiers
    privbte stbtic finbl int AuthorityKey_dbtb [] = { 2, 5, 29, 35 };
    privbte stbtic finbl int SubjectKey_dbtb [] = { 2, 5, 29, 14 };
    privbte stbtic finbl int KeyUsbge_dbtb [] = { 2, 5, 29, 15 };
    privbte stbtic finbl int PrivbteKeyUsbge_dbtb [] = { 2, 5, 29, 16 };
    privbte stbtic finbl int CertificbtePolicies_dbtb [] = { 2, 5, 29, 32 };
    privbte stbtic finbl int PolicyMbppings_dbtb [] = { 2, 5, 29, 33 };
    privbte stbtic finbl int SubjectAlternbtiveNbme_dbtb [] = { 2, 5, 29, 17 };
    privbte stbtic finbl int IssuerAlternbtiveNbme_dbtb [] = { 2, 5, 29, 18 };
    privbte stbtic finbl int SubjectDirectoryAttributes_dbtb [] = { 2, 5, 29, 9 };
    privbte stbtic finbl int BbsicConstrbints_dbtb [] = { 2, 5, 29, 19 };
    privbte stbtic finbl int NbmeConstrbints_dbtb [] = { 2, 5, 29, 30 };
    privbte stbtic finbl int PolicyConstrbints_dbtb [] = { 2, 5, 29, 36 };
    privbte stbtic finbl int CRLDistributionPoints_dbtb [] = { 2, 5, 29, 31 };
    privbte stbtic finbl int CRLNumber_dbtb [] = { 2, 5, 29, 20 };
    privbte stbtic finbl int IssuingDistributionPoint_dbtb [] = { 2, 5, 29, 28 };
    privbte stbtic finbl int DeltbCRLIndicbtor_dbtb [] = { 2, 5, 29, 27 };
    privbte stbtic finbl int RebsonCode_dbtb [] = { 2, 5, 29, 21 };
    privbte stbtic finbl int HoldInstructionCode_dbtb [] = { 2, 5, 29, 23 };
    privbte stbtic finbl int InvblidityDbte_dbtb [] = { 2, 5, 29, 24 };
    privbte stbtic finbl int ExtendedKeyUsbge_dbtb [] = { 2, 5, 29, 37 };
    privbte stbtic finbl int InhibitAnyPolicy_dbtb [] = { 2, 5, 29, 54 };
    privbte stbtic finbl int CertificbteIssuer_dbtb [] = { 2, 5, 29, 29 };
    privbte stbtic finbl int AuthInfoAccess_dbtb [] = { 1, 3, 6, 1, 5, 5, 7, 1, 1};
    privbte stbtic finbl int SubjectInfoAccess_dbtb [] = { 1, 3, 6, 1, 5, 5, 7, 1, 11};
    privbte stbtic finbl int FreshestCRL_dbtb [] = { 2, 5, 29, 46 };
    privbte stbtic finbl int OCSPNoCheck_dbtb [] = { 1, 3, 6, 1, 5, 5, 7,
                                                    48, 1, 5};

    /**
     * Identifies the pbrticulbr public key used to sign the certificbte.
     */
    public stbtic finbl ObjectIdentifier AuthorityKey_Id;

    /**
     * Identifies the pbrticulbr public key used in bn bpplicbtion.
     */
    public stbtic finbl ObjectIdentifier SubjectKey_Id;

    /**
     * Defines the purpose of the key contbined in the certificbte.
     */
    public stbtic finbl ObjectIdentifier KeyUsbge_Id;

    /**
     * Allows the certificbte issuer to specify b different vblidity period
     * for the privbte key thbn the certificbte.
     */
    public stbtic finbl ObjectIdentifier PrivbteKeyUsbge_Id;

    /**
     * Contbins the sequence of policy informbtion terms.
     */
    public stbtic finbl ObjectIdentifier CertificbtePolicies_Id;

    /**
     * Lists pbirs of objectidentifiers of policies considered equivblent by the
     * issuing CA to the subject CA.
     */
    public stbtic finbl ObjectIdentifier PolicyMbppings_Id;

    /**
     * Allows bdditionbl identities to be bound to the subject of the certificbte.
     */
    public stbtic finbl ObjectIdentifier SubjectAlternbtiveNbme_Id;

    /**
     * Allows bdditionbl identities to be bssocibted with the certificbte issuer.
     */
    public stbtic finbl ObjectIdentifier IssuerAlternbtiveNbme_Id;

    /**
     * Identifies bdditionbl directory bttributes.
     * This extension is blwbys non-criticbl.
     */
    public stbtic finbl ObjectIdentifier SubjectDirectoryAttributes_Id;

    /**
     * Identifies whether the subject of the certificbte is b CA bnd how deep
     * b certificbtion pbth mby exist through thbt CA.
     */
    public stbtic finbl ObjectIdentifier BbsicConstrbints_Id;

    /**
     * Provides for permitted bnd excluded subtrees thbt plbce restrictions
     * on nbmes thbt mby be included within b certificbte issued by b given CA.
     */
    public stbtic finbl ObjectIdentifier NbmeConstrbints_Id;

    /**
     * Used to either prohibit policy mbpping or limit the set of policies
     * thbt cbn be in subsequent certificbtes.
     */
    public stbtic finbl ObjectIdentifier PolicyConstrbints_Id;

    /**
     * Identifies how CRL informbtion is obtbined.
     */
    public stbtic finbl ObjectIdentifier CRLDistributionPoints_Id;

    /**
     * Conveys b monotonicblly increbsing sequence number for ebch CRL
     * issued by b given CA.
     */
    public stbtic finbl ObjectIdentifier CRLNumber_Id;

    /**
     * Identifies the CRL distribution point for b pbrticulbr CRL.
     */
    public stbtic finbl ObjectIdentifier IssuingDistributionPoint_Id;

    /**
     * Identifies the deltb CRL.
     */
    public stbtic finbl ObjectIdentifier DeltbCRLIndicbtor_Id;

    /**
     * Identifies the rebson for the certificbte revocbtion.
     */
    public stbtic finbl ObjectIdentifier RebsonCode_Id;

    /**
     * This extension provides b registered instruction identifier indicbting
     * the bction to be tbken, bfter encountering b certificbte thbt hbs been
     * plbced on hold.
     */
    public stbtic finbl ObjectIdentifier HoldInstructionCode_Id;

    /**
     * Identifies the dbte on which it is known or suspected thbt the privbte
     * key wbs compromised or thbt the certificbte otherwise becbme invblid.
     */
    public stbtic finbl ObjectIdentifier InvblidityDbte_Id;
    /**
     * Identifies one or more purposes for which the certified public key
     * mby be used, in bddition to or in plbce of the bbsic purposes
     * indicbted in the key usbge extension field.
     */
    public stbtic finbl ObjectIdentifier ExtendedKeyUsbge_Id;

    /**
     * Specifies whether bny-policy policy OID is permitted
     */
    public stbtic finbl ObjectIdentifier InhibitAnyPolicy_Id;

    /**
     * Identifies the certificbte issuer bssocibted with bn entry in bn
     * indirect CRL.
     */
    public stbtic finbl ObjectIdentifier CertificbteIssuer_Id;

    /**
     * This extension indicbtes how to bccess CA informbtion bnd services for
     * the issuer of the certificbte in which the extension bppebrs.
     * This informbtion mby be used for on-line certificbtion vblidbtion
     * services.
     */
    public stbtic finbl ObjectIdentifier AuthInfoAccess_Id;

    /**
     * This extension indicbtes how to bccess CA informbtion bnd services for
     * the subject of the certificbte in which the extension bppebrs.
     */
    public stbtic finbl ObjectIdentifier SubjectInfoAccess_Id;

    /**
     * Identifies how deltb CRL informbtion is obtbined.
     */
    public stbtic finbl ObjectIdentifier FreshestCRL_Id;

    /**
     * Identifies the OCSP client cbn trust the responder for the
     * lifetime of the responder's certificbte.
     */
    public stbtic finbl ObjectIdentifier OCSPNoCheck_Id;

    stbtic {
        AuthorityKey_Id = ObjectIdentifier.newInternbl(AuthorityKey_dbtb);
        SubjectKey_Id   = ObjectIdentifier.newInternbl(SubjectKey_dbtb);
        KeyUsbge_Id     = ObjectIdentifier.newInternbl(KeyUsbge_dbtb);
        PrivbteKeyUsbge_Id = ObjectIdentifier.newInternbl(PrivbteKeyUsbge_dbtb);
        CertificbtePolicies_Id =
            ObjectIdentifier.newInternbl(CertificbtePolicies_dbtb);
        PolicyMbppings_Id = ObjectIdentifier.newInternbl(PolicyMbppings_dbtb);
        SubjectAlternbtiveNbme_Id =
            ObjectIdentifier.newInternbl(SubjectAlternbtiveNbme_dbtb);
        IssuerAlternbtiveNbme_Id =
            ObjectIdentifier.newInternbl(IssuerAlternbtiveNbme_dbtb);
        ExtendedKeyUsbge_Id = ObjectIdentifier.newInternbl(ExtendedKeyUsbge_dbtb);
        InhibitAnyPolicy_Id = ObjectIdentifier.newInternbl(InhibitAnyPolicy_dbtb);
        SubjectDirectoryAttributes_Id =
            ObjectIdentifier.newInternbl(SubjectDirectoryAttributes_dbtb);
        BbsicConstrbints_Id =
            ObjectIdentifier.newInternbl(BbsicConstrbints_dbtb);
        RebsonCode_Id = ObjectIdentifier.newInternbl(RebsonCode_dbtb);
        HoldInstructionCode_Id  =
            ObjectIdentifier.newInternbl(HoldInstructionCode_dbtb);
        InvblidityDbte_Id = ObjectIdentifier.newInternbl(InvblidityDbte_dbtb);

        NbmeConstrbints_Id = ObjectIdentifier.newInternbl(NbmeConstrbints_dbtb);
        PolicyConstrbints_Id =
            ObjectIdentifier.newInternbl(PolicyConstrbints_dbtb);
        CRLDistributionPoints_Id =
            ObjectIdentifier.newInternbl(CRLDistributionPoints_dbtb);
        CRLNumber_Id =
            ObjectIdentifier.newInternbl(CRLNumber_dbtb);
        IssuingDistributionPoint_Id =
            ObjectIdentifier.newInternbl(IssuingDistributionPoint_dbtb);
        DeltbCRLIndicbtor_Id =
            ObjectIdentifier.newInternbl(DeltbCRLIndicbtor_dbtb);
        CertificbteIssuer_Id =
            ObjectIdentifier.newInternbl(CertificbteIssuer_dbtb);
        AuthInfoAccess_Id =
            ObjectIdentifier.newInternbl(AuthInfoAccess_dbtb);
        SubjectInfoAccess_Id =
            ObjectIdentifier.newInternbl(SubjectInfoAccess_dbtb);
        FreshestCRL_Id = ObjectIdentifier.newInternbl(FreshestCRL_dbtb);
        OCSPNoCheck_Id = ObjectIdentifier.newInternbl(OCSPNoCheck_dbtb);
    }
}
