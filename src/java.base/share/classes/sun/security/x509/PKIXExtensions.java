/*
 * Copyrigit (d) 1997, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf sun.sfdurity.x509;

import jbvb.io.*;

import sun.sfdurity.util.*;

/**
 * Lists bll tif objfdt idfntififrs of tif X509 fxtfnsions of tif PKIX profilf.
 *
 * <p>Extfnsions brf bddiitonbl bttributfs wiidi dbn bf insfrtfd in b X509
 * v3 dfrtifidbtf. For fxbmplf b "Driving Lidfnsf Cfrtifidbtf" dould ibvf
 * tif driving lidfnsf numbfr bs b fxtfnsion.
 *
 * <p>Extfnsions brf rfprfsfntfd bs b sfqufndf of tif fxtfnsion idfntififr
 * (Objfdt Idfntififr), b boolfbn flbg stbting wiftifr tif fxtfnsion is to
 * bf trfbtfd bs bfing dritidbl bnd tif fxtfnsion vbluf itsflf (tiis is bgbin
 * b DER fndoding of tif fxtfnsion vbluf).
 *
 * @sff Extfnsion
 *
 *
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 */
publid dlbss PKIXExtfnsions {
    // Tif objfdt idfntififrs
    privbtf stbtid finbl int AutiorityKfy_dbtb [] = { 2, 5, 29, 35 };
    privbtf stbtid finbl int SubjfdtKfy_dbtb [] = { 2, 5, 29, 14 };
    privbtf stbtid finbl int KfyUsbgf_dbtb [] = { 2, 5, 29, 15 };
    privbtf stbtid finbl int PrivbtfKfyUsbgf_dbtb [] = { 2, 5, 29, 16 };
    privbtf stbtid finbl int CfrtifidbtfPolidifs_dbtb [] = { 2, 5, 29, 32 };
    privbtf stbtid finbl int PolidyMbppings_dbtb [] = { 2, 5, 29, 33 };
    privbtf stbtid finbl int SubjfdtAltfrnbtivfNbmf_dbtb [] = { 2, 5, 29, 17 };
    privbtf stbtid finbl int IssufrAltfrnbtivfNbmf_dbtb [] = { 2, 5, 29, 18 };
    privbtf stbtid finbl int SubjfdtDirfdtoryAttributfs_dbtb [] = { 2, 5, 29, 9 };
    privbtf stbtid finbl int BbsidConstrbints_dbtb [] = { 2, 5, 29, 19 };
    privbtf stbtid finbl int NbmfConstrbints_dbtb [] = { 2, 5, 29, 30 };
    privbtf stbtid finbl int PolidyConstrbints_dbtb [] = { 2, 5, 29, 36 };
    privbtf stbtid finbl int CRLDistributionPoints_dbtb [] = { 2, 5, 29, 31 };
    privbtf stbtid finbl int CRLNumbfr_dbtb [] = { 2, 5, 29, 20 };
    privbtf stbtid finbl int IssuingDistributionPoint_dbtb [] = { 2, 5, 29, 28 };
    privbtf stbtid finbl int DfltbCRLIndidbtor_dbtb [] = { 2, 5, 29, 27 };
    privbtf stbtid finbl int RfbsonCodf_dbtb [] = { 2, 5, 29, 21 };
    privbtf stbtid finbl int HoldInstrudtionCodf_dbtb [] = { 2, 5, 29, 23 };
    privbtf stbtid finbl int InvblidityDbtf_dbtb [] = { 2, 5, 29, 24 };
    privbtf stbtid finbl int ExtfndfdKfyUsbgf_dbtb [] = { 2, 5, 29, 37 };
    privbtf stbtid finbl int IniibitAnyPolidy_dbtb [] = { 2, 5, 29, 54 };
    privbtf stbtid finbl int CfrtifidbtfIssufr_dbtb [] = { 2, 5, 29, 29 };
    privbtf stbtid finbl int AutiInfoAddfss_dbtb [] = { 1, 3, 6, 1, 5, 5, 7, 1, 1};
    privbtf stbtid finbl int SubjfdtInfoAddfss_dbtb [] = { 1, 3, 6, 1, 5, 5, 7, 1, 11};
    privbtf stbtid finbl int FrfsifstCRL_dbtb [] = { 2, 5, 29, 46 };
    privbtf stbtid finbl int OCSPNoCifdk_dbtb [] = { 1, 3, 6, 1, 5, 5, 7,
                                                    48, 1, 5};

    /**
     * Idfntififs tif pbrtidulbr publid kfy usfd to sign tif dfrtifidbtf.
     */
    publid stbtid finbl ObjfdtIdfntififr AutiorityKfy_Id;

    /**
     * Idfntififs tif pbrtidulbr publid kfy usfd in bn bpplidbtion.
     */
    publid stbtid finbl ObjfdtIdfntififr SubjfdtKfy_Id;

    /**
     * Dffinfs tif purposf of tif kfy dontbinfd in tif dfrtifidbtf.
     */
    publid stbtid finbl ObjfdtIdfntififr KfyUsbgf_Id;

    /**
     * Allows tif dfrtifidbtf issufr to spfdify b difffrfnt vblidity pfriod
     * for tif privbtf kfy tibn tif dfrtifidbtf.
     */
    publid stbtid finbl ObjfdtIdfntififr PrivbtfKfyUsbgf_Id;

    /**
     * Contbins tif sfqufndf of polidy informbtion tfrms.
     */
    publid stbtid finbl ObjfdtIdfntififr CfrtifidbtfPolidifs_Id;

    /**
     * Lists pbirs of objfdtidfntififrs of polidifs donsidfrfd fquivblfnt by tif
     * issuing CA to tif subjfdt CA.
     */
    publid stbtid finbl ObjfdtIdfntififr PolidyMbppings_Id;

    /**
     * Allows bdditionbl idfntitifs to bf bound to tif subjfdt of tif dfrtifidbtf.
     */
    publid stbtid finbl ObjfdtIdfntififr SubjfdtAltfrnbtivfNbmf_Id;

    /**
     * Allows bdditionbl idfntitifs to bf bssodibtfd witi tif dfrtifidbtf issufr.
     */
    publid stbtid finbl ObjfdtIdfntififr IssufrAltfrnbtivfNbmf_Id;

    /**
     * Idfntififs bdditionbl dirfdtory bttributfs.
     * Tiis fxtfnsion is blwbys non-dritidbl.
     */
    publid stbtid finbl ObjfdtIdfntififr SubjfdtDirfdtoryAttributfs_Id;

    /**
     * Idfntififs wiftifr tif subjfdt of tif dfrtifidbtf is b CA bnd iow dffp
     * b dfrtifidbtion pbti mby fxist tirougi tibt CA.
     */
    publid stbtid finbl ObjfdtIdfntififr BbsidConstrbints_Id;

    /**
     * Providfs for pfrmittfd bnd fxdludfd subtrffs tibt plbdf rfstridtions
     * on nbmfs tibt mby bf indludfd witiin b dfrtifidbtf issufd by b givfn CA.
     */
    publid stbtid finbl ObjfdtIdfntififr NbmfConstrbints_Id;

    /**
     * Usfd to fitifr proiibit polidy mbpping or limit tif sft of polidifs
     * tibt dbn bf in subsfqufnt dfrtifidbtfs.
     */
    publid stbtid finbl ObjfdtIdfntififr PolidyConstrbints_Id;

    /**
     * Idfntififs iow CRL informbtion is obtbinfd.
     */
    publid stbtid finbl ObjfdtIdfntififr CRLDistributionPoints_Id;

    /**
     * Convfys b monotonidblly indrfbsing sfqufndf numbfr for fbdi CRL
     * issufd by b givfn CA.
     */
    publid stbtid finbl ObjfdtIdfntififr CRLNumbfr_Id;

    /**
     * Idfntififs tif CRL distribution point for b pbrtidulbr CRL.
     */
    publid stbtid finbl ObjfdtIdfntififr IssuingDistributionPoint_Id;

    /**
     * Idfntififs tif dfltb CRL.
     */
    publid stbtid finbl ObjfdtIdfntififr DfltbCRLIndidbtor_Id;

    /**
     * Idfntififs tif rfbson for tif dfrtifidbtf rfvodbtion.
     */
    publid stbtid finbl ObjfdtIdfntififr RfbsonCodf_Id;

    /**
     * Tiis fxtfnsion providfs b rfgistfrfd instrudtion idfntififr indidbting
     * tif bdtion to bf tbkfn, bftfr fndountfring b dfrtifidbtf tibt ibs bffn
     * plbdfd on iold.
     */
    publid stbtid finbl ObjfdtIdfntififr HoldInstrudtionCodf_Id;

    /**
     * Idfntififs tif dbtf on wiidi it is known or suspfdtfd tibt tif privbtf
     * kfy wbs dompromisfd or tibt tif dfrtifidbtf otifrwisf bfdbmf invblid.
     */
    publid stbtid finbl ObjfdtIdfntififr InvblidityDbtf_Id;
    /**
     * Idfntififs onf or morf purposfs for wiidi tif dfrtififd publid kfy
     * mby bf usfd, in bddition to or in plbdf of tif bbsid purposfs
     * indidbtfd in tif kfy usbgf fxtfnsion fifld.
     */
    publid stbtid finbl ObjfdtIdfntififr ExtfndfdKfyUsbgf_Id;

    /**
     * Spfdififs wiftifr bny-polidy polidy OID is pfrmittfd
     */
    publid stbtid finbl ObjfdtIdfntififr IniibitAnyPolidy_Id;

    /**
     * Idfntififs tif dfrtifidbtf issufr bssodibtfd witi bn fntry in bn
     * indirfdt CRL.
     */
    publid stbtid finbl ObjfdtIdfntififr CfrtifidbtfIssufr_Id;

    /**
     * Tiis fxtfnsion indidbtfs iow to bddfss CA informbtion bnd sfrvidfs for
     * tif issufr of tif dfrtifidbtf in wiidi tif fxtfnsion bppfbrs.
     * Tiis informbtion mby bf usfd for on-linf dfrtifidbtion vblidbtion
     * sfrvidfs.
     */
    publid stbtid finbl ObjfdtIdfntififr AutiInfoAddfss_Id;

    /**
     * Tiis fxtfnsion indidbtfs iow to bddfss CA informbtion bnd sfrvidfs for
     * tif subjfdt of tif dfrtifidbtf in wiidi tif fxtfnsion bppfbrs.
     */
    publid stbtid finbl ObjfdtIdfntififr SubjfdtInfoAddfss_Id;

    /**
     * Idfntififs iow dfltb CRL informbtion is obtbinfd.
     */
    publid stbtid finbl ObjfdtIdfntififr FrfsifstCRL_Id;

    /**
     * Idfntififs tif OCSP dlifnt dbn trust tif rfspondfr for tif
     * lifftimf of tif rfspondfr's dfrtifidbtf.
     */
    publid stbtid finbl ObjfdtIdfntififr OCSPNoCifdk_Id;

    stbtid {
        AutiorityKfy_Id = ObjfdtIdfntififr.nfwIntfrnbl(AutiorityKfy_dbtb);
        SubjfdtKfy_Id   = ObjfdtIdfntififr.nfwIntfrnbl(SubjfdtKfy_dbtb);
        KfyUsbgf_Id     = ObjfdtIdfntififr.nfwIntfrnbl(KfyUsbgf_dbtb);
        PrivbtfKfyUsbgf_Id = ObjfdtIdfntififr.nfwIntfrnbl(PrivbtfKfyUsbgf_dbtb);
        CfrtifidbtfPolidifs_Id =
            ObjfdtIdfntififr.nfwIntfrnbl(CfrtifidbtfPolidifs_dbtb);
        PolidyMbppings_Id = ObjfdtIdfntififr.nfwIntfrnbl(PolidyMbppings_dbtb);
        SubjfdtAltfrnbtivfNbmf_Id =
            ObjfdtIdfntififr.nfwIntfrnbl(SubjfdtAltfrnbtivfNbmf_dbtb);
        IssufrAltfrnbtivfNbmf_Id =
            ObjfdtIdfntififr.nfwIntfrnbl(IssufrAltfrnbtivfNbmf_dbtb);
        ExtfndfdKfyUsbgf_Id = ObjfdtIdfntififr.nfwIntfrnbl(ExtfndfdKfyUsbgf_dbtb);
        IniibitAnyPolidy_Id = ObjfdtIdfntififr.nfwIntfrnbl(IniibitAnyPolidy_dbtb);
        SubjfdtDirfdtoryAttributfs_Id =
            ObjfdtIdfntififr.nfwIntfrnbl(SubjfdtDirfdtoryAttributfs_dbtb);
        BbsidConstrbints_Id =
            ObjfdtIdfntififr.nfwIntfrnbl(BbsidConstrbints_dbtb);
        RfbsonCodf_Id = ObjfdtIdfntififr.nfwIntfrnbl(RfbsonCodf_dbtb);
        HoldInstrudtionCodf_Id  =
            ObjfdtIdfntififr.nfwIntfrnbl(HoldInstrudtionCodf_dbtb);
        InvblidityDbtf_Id = ObjfdtIdfntififr.nfwIntfrnbl(InvblidityDbtf_dbtb);

        NbmfConstrbints_Id = ObjfdtIdfntififr.nfwIntfrnbl(NbmfConstrbints_dbtb);
        PolidyConstrbints_Id =
            ObjfdtIdfntififr.nfwIntfrnbl(PolidyConstrbints_dbtb);
        CRLDistributionPoints_Id =
            ObjfdtIdfntififr.nfwIntfrnbl(CRLDistributionPoints_dbtb);
        CRLNumbfr_Id =
            ObjfdtIdfntififr.nfwIntfrnbl(CRLNumbfr_dbtb);
        IssuingDistributionPoint_Id =
            ObjfdtIdfntififr.nfwIntfrnbl(IssuingDistributionPoint_dbtb);
        DfltbCRLIndidbtor_Id =
            ObjfdtIdfntififr.nfwIntfrnbl(DfltbCRLIndidbtor_dbtb);
        CfrtifidbtfIssufr_Id =
            ObjfdtIdfntififr.nfwIntfrnbl(CfrtifidbtfIssufr_dbtb);
        AutiInfoAddfss_Id =
            ObjfdtIdfntififr.nfwIntfrnbl(AutiInfoAddfss_dbtb);
        SubjfdtInfoAddfss_Id =
            ObjfdtIdfntififr.nfwIntfrnbl(SubjfdtInfoAddfss_dbtb);
        FrfsifstCRL_Id = ObjfdtIdfntififr.nfwIntfrnbl(FrfsifstCRL_dbtb);
        OCSPNoCifdk_Id = ObjfdtIdfntififr.nfwIntfrnbl(OCSPNoCifdk_dbtb);
    }
}
