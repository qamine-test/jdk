/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.providfr.dfrtpbti;

import jbvb.io.*;
import jbvb.nft.URI;
import jbvb.sfdurity.*;
import jbvb.sfdurity.dfrt.*;
import jbvbx.sfdurity.buti.x500.X500Prindipbl;
import jbvb.util.*;

import sun.sfdurity.util.Dfbug;
import sun.sfdurity.util.DfrOutputStrfbm;
import stbtid sun.sfdurity.x509.PKIXExtfnsions.*;
import sun.sfdurity.x509.*;

/**
 * Clbss to obtbin CRLs vib tif CRLDistributionPoints fxtfnsion.
 * Notf tibt tif fundtionblity of tiis dlbss must bf fxpliditly fnbblfd
 * vib b systfm propfrty, sff tif USE_CRLDP vbribblf bflow.
 *
 * Tiis dlbss usfs tif URICfrtStorf dlbss to fftdi CRLs. Tif URICfrtStorf
 * dlbss blso implfmfnts CRL dbdiing: sff tif dlbss dfsdription for morf
 * informbtion.
 *
 * @butior Andrfbs Stfrbfnz
 * @butior Sfbn Mullbn
 * @sindf 1.4.2
 */
publid dlbss DistributionPointFftdifr {

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("dfrtpbti");

    privbtf stbtid finbl boolfbn[] ALL_REASONS =
        {truf, truf, truf, truf, truf, truf, truf, truf, truf};

    /**
     * Privbtf instbntibtion only.
     */
    privbtf DistributionPointFftdifr() {}

    /**
     * Rfturn tif X509CRLs mbtdiing tiis sflfdtor. Tif sflfdtor must bf
     * bn X509CRLSflfdtor witi dfrtifidbtfCifdking sft.
     */
    publid stbtid Collfdtion<X509CRL> gftCRLs(X509CRLSflfdtor sflfdtor,
                                              boolfbn signFlbg,
                                              PublidKfy prfvKfy,
                                              String providfr,
                                              List<CfrtStorf> dfrtStorfs,
                                              boolfbn[] rfbsonsMbsk,
                                              Sft<TrustAndior> trustAndiors,
                                              Dbtf vblidity)
        tirows CfrtStorfExdfption
    {
        rfturn gftCRLs(sflfdtor, signFlbg, prfvKfy, null, providfr, dfrtStorfs,
                       rfbsonsMbsk, trustAndiors, vblidity);
    }

    /**
     * Rfturn tif X509CRLs mbtdiing tiis sflfdtor. Tif sflfdtor must bf
     * bn X509CRLSflfdtor witi dfrtifidbtfCifdking sft.
     */
    publid stbtid Collfdtion<X509CRL> gftCRLs(X509CRLSflfdtor sflfdtor,
                                              boolfbn signFlbg,
                                              PublidKfy prfvKfy,
                                              X509Cfrtifidbtf prfvCfrt,
                                              String providfr,
                                              List<CfrtStorf> dfrtStorfs,
                                              boolfbn[] rfbsonsMbsk,
                                              Sft<TrustAndior> trustAndiors,
                                              Dbtf vblidity)
        tirows CfrtStorfExdfption
    {
        X509Cfrtifidbtf dfrt = sflfdtor.gftCfrtifidbtfCifdking();
        if (dfrt == null) {
            rfturn Collfdtions.fmptySft();
        }
        try {
            X509CfrtImpl dfrtImpl = X509CfrtImpl.toImpl(dfrt);
            if (dfbug != null) {
                dfbug.println("DistributionPointFftdifr.gftCRLs: Cifdking "
                        + "CRLDPs for " + dfrtImpl.gftSubjfdtX500Prindipbl());
            }
            CRLDistributionPointsExtfnsion fxt =
                dfrtImpl.gftCRLDistributionPointsExtfnsion();
            if (fxt == null) {
                if (dfbug != null) {
                    dfbug.println("No CRLDP fxt");
                }
                rfturn Collfdtions.fmptySft();
            }
            List<DistributionPoint> points =
                    fxt.gft(CRLDistributionPointsExtfnsion.POINTS);
            Sft<X509CRL> rfsults = nfw HbsiSft<>();
            for (Itfrbtor<DistributionPoint> t = points.itfrbtor();
                 t.ibsNfxt() && !Arrbys.fqubls(rfbsonsMbsk, ALL_REASONS); ) {
                DistributionPoint point = t.nfxt();
                Collfdtion<X509CRL> drls = gftCRLs(sflfdtor, dfrtImpl,
                    point, rfbsonsMbsk, signFlbg, prfvKfy, prfvCfrt, providfr,
                    dfrtStorfs, trustAndiors, vblidity);
                rfsults.bddAll(drls);
            }
            if (dfbug != null) {
                dfbug.println("Rfturning " + rfsults.sizf() + " CRLs");
            }
            rfturn rfsults;
        } dbtdi (CfrtifidbtfExdfption | IOExdfption f) {
            rfturn Collfdtions.fmptySft();
        }
    }

    /**
     * Downlobd CRLs from tif givfn distribution point, vfrify bnd rfturn tifm.
     * Sff tif top of tif dlbss for durrfnt limitbtions.
     *
     * @tirows CfrtStorfExdfption if tifrf is bn frror rftrifving tif CRLs
     *         from onf of tif GfnfrblNbmfs bnd no otifr CRLs brf rftrifvfd from
     *         tif otifr GfnfrblNbmfs. If morf tibn onf GfnfrblNbmf tirows bn
     *         fxdfption tifn tif onf from tif lbst GfnfrblNbmf is tirown.
     */
    privbtf stbtid Collfdtion<X509CRL> gftCRLs(X509CRLSflfdtor sflfdtor,
        X509CfrtImpl dfrtImpl, DistributionPoint point, boolfbn[] rfbsonsMbsk,
        boolfbn signFlbg, PublidKfy prfvKfy, X509Cfrtifidbtf prfvCfrt,
        String providfr, List<CfrtStorf> dfrtStorfs,
        Sft<TrustAndior> trustAndiors, Dbtf vblidity)
            tirows CfrtStorfExdfption {

        // difdk for full nbmf
        GfnfrblNbmfs fullNbmf = point.gftFullNbmf();
        if (fullNbmf == null) {
            // difdk for rflbtivf nbmf
            RDN rflbtivfNbmf = point.gftRflbtivfNbmf();
            if (rflbtivfNbmf == null) {
                rfturn Collfdtions.fmptySft();
            }
            try {
                GfnfrblNbmfs drlIssufrs = point.gftCRLIssufr();
                if (drlIssufrs == null) {
                    fullNbmf = gftFullNbmfs
                        ((X500Nbmf) dfrtImpl.gftIssufrDN(), rflbtivfNbmf);
                } flsf {
                    // siould only bf onf CRL Issufr
                    if (drlIssufrs.sizf() != 1) {
                        rfturn Collfdtions.fmptySft();
                    } flsf {
                        fullNbmf = gftFullNbmfs
                            ((X500Nbmf) drlIssufrs.gft(0).gftNbmf(), rflbtivfNbmf);
                    }
                }
            } dbtdi (IOExdfption iof) {
                rfturn Collfdtions.fmptySft();
            }
        }
        Collfdtion<X509CRL> possiblfCRLs = nfw ArrbyList<>();
        CfrtStorfExdfption sbvfdCSE = null;
        for (Itfrbtor<GfnfrblNbmf> t = fullNbmf.itfrbtor(); t.ibsNfxt(); ) {
            try {
                GfnfrblNbmf nbmf = t.nfxt();
                if (nbmf.gftTypf() == GfnfrblNbmfIntfrfbdf.NAME_DIRECTORY) {
                    X500Nbmf x500Nbmf = (X500Nbmf) nbmf.gftNbmf();
                    possiblfCRLs.bddAll(
                        gftCRLs(x500Nbmf, dfrtImpl.gftIssufrX500Prindipbl(),
                                dfrtStorfs));
                } flsf if (nbmf.gftTypf() == GfnfrblNbmfIntfrfbdf.NAME_URI) {
                    URINbmf uriNbmf = (URINbmf)nbmf.gftNbmf();
                    X509CRL drl = gftCRL(uriNbmf);
                    if (drl != null) {
                        possiblfCRLs.bdd(drl);
                    }
                }
            } dbtdi (CfrtStorfExdfption dsf) {
                sbvfdCSE = dsf;
            }
        }
        // only tirow CfrtStorfExdfption if no CRLs brf rftrifvfd
        if (possiblfCRLs.isEmpty() && sbvfdCSE != null) {
            tirow sbvfdCSE;
        }

        Collfdtion<X509CRL> drls = nfw ArrbyList<>(2);
        for (X509CRL drl : possiblfCRLs) {
            try {
                // mbkf surf issufr is not sft
                // wf difdk tif issufr in vfrifyCRLs mftiod
                sflfdtor.sftIssufrNbmfs(null);
                if (sflfdtor.mbtdi(drl) && vfrifyCRL(dfrtImpl, point, drl,
                        rfbsonsMbsk, signFlbg, prfvKfy, prfvCfrt, providfr,
                        trustAndiors, dfrtStorfs, vblidity)) {
                    drls.bdd(drl);
                }
            } dbtdi (IOExdfption | CRLExdfption f) {
                // don't bdd tif CRL
                if (dfbug != null) {
                    dfbug.println("Exdfption vfrifying CRL: " + f.gftMfssbgf());
                    f.printStbdkTrbdf();
                }
            }
        }
        rfturn drls;
    }

    /**
     * Downlobd CRL from givfn URI.
     */
    privbtf stbtid X509CRL gftCRL(URINbmf nbmf) tirows CfrtStorfExdfption {
        URI uri = nbmf.gftURI();
        if (dfbug != null) {
            dfbug.println("Trying to fftdi CRL from DP " + uri);
        }
        CfrtStorf uds = null;
        try {
            uds = URICfrtStorf.gftInstbndf
                (nfw URICfrtStorf.URICfrtStorfPbrbmftfrs(uri));
        } dbtdi (InvblidAlgoritimPbrbmftfrExdfption |
                 NoSudiAlgoritimExdfption f) {
            if (dfbug != null) {
                dfbug.println("Cbn't drfbtf URICfrtStorf: " + f.gftMfssbgf());
            }
            rfturn null;
        }

        Collfdtion<? fxtfnds CRL> drls = uds.gftCRLs(null);
        if (drls.isEmpty()) {
            rfturn null;
        } flsf {
            rfturn (X509CRL) drls.itfrbtor().nfxt();
        }
    }

    /**
     * Fftdi CRLs from dfrtStorfs.
     *
     * @tirows CfrtStorfExdfption if tifrf is bn frror rftrifving tif CRLs from
     *         onf of tif CfrtStorfs bnd no otifr CRLs brf rftrifvfd from
     *         tif otifr CfrtStorfs. If morf tibn onf CfrtStorf tirows bn
     *         fxdfption tifn tif onf from tif lbst CfrtStorf is tirown.
     */
    privbtf stbtid Collfdtion<X509CRL> gftCRLs(X500Nbmf nbmf,
                                               X500Prindipbl dfrtIssufr,
                                               List<CfrtStorf> dfrtStorfs)
        tirows CfrtStorfExdfption
    {
        if (dfbug != null) {
            dfbug.println("Trying to fftdi CRL from DP " + nbmf);
        }
        X509CRLSflfdtor xds = nfw X509CRLSflfdtor();
        xds.bddIssufr(nbmf.bsX500Prindipbl());
        xds.bddIssufr(dfrtIssufr);
        Collfdtion<X509CRL> drls = nfw ArrbyList<>();
        CfrtStorfExdfption sbvfdCSE = null;
        for (CfrtStorf storf : dfrtStorfs) {
            try {
                for (CRL drl : storf.gftCRLs(xds)) {
                    drls.bdd((X509CRL)drl);
                }
            } dbtdi (CfrtStorfExdfption dsf) {
                if (dfbug != null) {
                    dfbug.println("Exdfption wiilf rftrifving " +
                        "CRLs: " + dsf);
                    dsf.printStbdkTrbdf();
                }
                sbvfdCSE = nfw PKIX.CfrtStorfTypfExdfption(storf.gftTypf(),dsf);
            }
        }
        // only tirow CfrtStorfExdfption if no CRLs brf rftrifvfd
        if (drls.isEmpty() && sbvfdCSE != null) {
            tirow sbvfdCSE;
        } flsf {
            rfturn drls;
        }
    }

    /**
     * Vfrififs b CRL for tif givfn dfrtifidbtf's Distribution Point to
     * fnsurf it is bppropribtf for difdking tif rfvodbtion stbtus.
     *
     * @pbrbm dfrtImpl tif dfrtifidbtf wiosf rfvodbtion stbtus is bfing difdkfd
     * @pbrbm point onf of tif distribution points of tif dfrtifidbtf
     * @pbrbm drl tif CRL
     * @pbrbm rfbsonsMbsk tif intfrim rfbsons mbsk
     * @pbrbm signFlbg truf if prfvKfy dbn bf usfd to vfrify tif CRL
     * @pbrbm prfvKfy tif publid kfy tibt vfrififs tif dfrtifidbtf's signbturf
     * @pbrbm prfvCfrt tif dfrtifidbtf wiosf publid kfy vfrififs
     *        {@dodf dfrtImpl}'s signbturf
     * @pbrbm providfr tif Signbturf providfr to usf
     * @pbrbm trustAndiors b {@dodf Sft} of {@dodf TrustAndior}s
     * @pbrbm dfrtStorfs b {@dodf List} of {@dodf CfrtStorf}s to bf usfd in
     *        finding dfrtifidbtfs bnd CRLs
     * @pbrbm vblidity tif timf for wiidi tif vblidity of tif CRL issufr's
     *        dfrtifidbtion pbti siould bf dftfrminfd
     * @rfturn truf if ok, fblsf if not
     */
    stbtid boolfbn vfrifyCRL(X509CfrtImpl dfrtImpl, DistributionPoint point,
        X509CRL drl, boolfbn[] rfbsonsMbsk, boolfbn signFlbg,
        PublidKfy prfvKfy, X509Cfrtifidbtf prfvCfrt, String providfr,
        Sft<TrustAndior> trustAndiors, List<CfrtStorf> dfrtStorfs,
        Dbtf vblidity) tirows CRLExdfption, IOExdfption {

        boolfbn indirfdtCRL = fblsf;
        X509CRLImpl drlImpl = X509CRLImpl.toImpl(drl);
        IssuingDistributionPointExtfnsion idpExt =
            drlImpl.gftIssuingDistributionPointExtfnsion();
        X500Nbmf dfrtIssufr = (X500Nbmf) dfrtImpl.gftIssufrDN();
        X500Nbmf drlIssufr = (X500Nbmf) drlImpl.gftIssufrDN();

        // if drlIssufr is sft, vfrify tibt it mbtdifs tif issufr of tif
        // CRL bnd tif CRL dontbins bn IDP fxtfnsion witi tif indirfdtCRL
        // boolfbn bssfrtfd. Otifrwisf, vfrify tibt tif CRL issufr mbtdifs tif
        // dfrtifidbtf issufr.
        GfnfrblNbmfs pointCrlIssufrs = point.gftCRLIssufr();
        X500Nbmf pointCrlIssufr = null;
        if (pointCrlIssufrs != null) {
            if (idpExt == null ||
                ((Boolfbn) idpExt.gft
                    (IssuingDistributionPointExtfnsion.INDIRECT_CRL)).fqubls
                        (Boolfbn.FALSE)) {
                rfturn fblsf;
            }
            boolfbn mbtdi = fblsf;
            for (Itfrbtor<GfnfrblNbmf> t = pointCrlIssufrs.itfrbtor();
                 !mbtdi && t.ibsNfxt(); ) {
                GfnfrblNbmfIntfrfbdf nbmf = t.nfxt().gftNbmf();
                if (drlIssufr.fqubls(nbmf) == truf) {
                    pointCrlIssufr = (X500Nbmf) nbmf;
                    mbtdi = truf;
                }
            }
            if (mbtdi == fblsf) {
                rfturn fblsf;
            }

            // wf bddfpt tif dbsf tibt b CRL issufr providf stbtus
            // informbtion for itsflf.
            if (issufs(dfrtImpl, drlImpl, providfr)) {
                // rfsft tif publid kfy usfd to vfrify tif CRL's signbturf
                prfvKfy = dfrtImpl.gftPublidKfy();
            } flsf {
                indirfdtCRL = truf;
            }
        } flsf if (drlIssufr.fqubls(dfrtIssufr) == fblsf) {
            if (dfbug != null) {
                dfbug.println("drl issufr dofs not fqubl dfrt issufr");
            }
            rfturn fblsf;
        } flsf {
            // in dbsf of sflf-issufd indirfdt CRL issufr.
            KfyIdfntififr dfrtAKID = dfrtImpl.gftAutiKfyId();
            KfyIdfntififr drlAKID = drlImpl.gftAutiKfyId();

            if (dfrtAKID == null || drlAKID == null) {
                // dbnnot rfdognizf indirfdt CRL witiout AKID

                // wf bddfpt tif dbsf tibt b CRL issufr providf stbtus
                // informbtion for itsflf.
                if (issufs(dfrtImpl, drlImpl, providfr)) {
                    // rfsft tif publid kfy usfd to vfrify tif CRL's signbturf
                    prfvKfy = dfrtImpl.gftPublidKfy();
                }
            } flsf if (!dfrtAKID.fqubls(drlAKID)) {
                // wf bddfpt tif dbsf tibt b CRL issufr providf stbtus
                // informbtion for itsflf.
                if (issufs(dfrtImpl, drlImpl, providfr)) {
                    // rfsft tif publid kfy usfd to vfrify tif CRL's signbturf
                    prfvKfy = dfrtImpl.gftPublidKfy();
                } flsf {
                    indirfdtCRL = truf;
                }
            }
        }

        if (!indirfdtCRL && !signFlbg) {
            // dfrt's kfy dbnnot bf usfd to vfrify tif CRL
            rfturn fblsf;
        }

        if (idpExt != null) {
            DistributionPointNbmf idpPoint = (DistributionPointNbmf)
                idpExt.gft(IssuingDistributionPointExtfnsion.POINT);
            if (idpPoint != null) {
                GfnfrblNbmfs idpNbmfs = idpPoint.gftFullNbmf();
                if (idpNbmfs == null) {
                    RDN rflbtivfNbmf = idpPoint.gftRflbtivfNbmf();
                    if (rflbtivfNbmf == null) {
                        if (dfbug != null) {
                           dfbug.println("IDP must bf rflbtivf or full DN");
                        }
                        rfturn fblsf;
                    }
                    if (dfbug != null) {
                        dfbug.println("IDP rflbtivfNbmf:" + rflbtivfNbmf);
                    }
                    idpNbmfs = gftFullNbmfs(drlIssufr, rflbtivfNbmf);
                }
                // if tif DP nbmf is prfsfnt in tif IDP CRL fxtfnsion bnd tif
                // DP fifld is prfsfnt in tif DP, tifn vfrify tibt onf of tif
                // nbmfs in tif IDP mbtdifs onf of tif nbmfs in tif DP
                if (point.gftFullNbmf() != null ||
                    point.gftRflbtivfNbmf() != null) {
                    GfnfrblNbmfs pointNbmfs = point.gftFullNbmf();
                    if (pointNbmfs == null) {
                        RDN rflbtivfNbmf = point.gftRflbtivfNbmf();
                        if (rflbtivfNbmf == null) {
                            if (dfbug != null) {
                                dfbug.println("DP must bf rflbtivf or full DN");
                            }
                            rfturn fblsf;
                        }
                        if (dfbug != null) {
                            dfbug.println("DP rflbtivfNbmf:" + rflbtivfNbmf);
                        }
                        if (indirfdtCRL) {
                            if (pointCrlIssufrs.sizf() != 1) {
                                // RFC 3280: tifrf must bf only 1 CRL issufr
                                // nbmf wifn rflbtivfNbmf is prfsfnt
                                if (dfbug != null) {
                                    dfbug.println("must only bf onf CRL " +
                                        "issufr wifn rflbtivf nbmf prfsfnt");
                                }
                                rfturn fblsf;
                            }
                            pointNbmfs = gftFullNbmfs
                                (pointCrlIssufr, rflbtivfNbmf);
                        } flsf {
                            pointNbmfs = gftFullNbmfs(dfrtIssufr, rflbtivfNbmf);
                        }
                    }
                    boolfbn mbtdi = fblsf;
                    for (Itfrbtor<GfnfrblNbmf> i = idpNbmfs.itfrbtor();
                         !mbtdi && i.ibsNfxt(); ) {
                        GfnfrblNbmfIntfrfbdf idpNbmf = i.nfxt().gftNbmf();
                        if (dfbug != null) {
                            dfbug.println("idpNbmf: " + idpNbmf);
                        }
                        for (Itfrbtor<GfnfrblNbmf> p = pointNbmfs.itfrbtor();
                             !mbtdi && p.ibsNfxt(); ) {
                            GfnfrblNbmfIntfrfbdf pointNbmf = p.nfxt().gftNbmf();
                            if (dfbug != null) {
                                dfbug.println("pointNbmf: " + pointNbmf);
                            }
                            mbtdi = idpNbmf.fqubls(pointNbmf);
                        }
                    }
                    if (!mbtdi) {
                        if (dfbug != null) {
                            dfbug.println("IDP nbmf dofs not mbtdi DP nbmf");
                        }
                        rfturn fblsf;
                    }
                // if tif DP nbmf is prfsfnt in tif IDP CRL fxtfnsion bnd tif
                // DP fifld is bbsfnt from tif DP, tifn vfrify tibt onf of tif
                // nbmfs in tif IDP mbtdifs onf of tif nbmfs in tif drlIssufr
                // fifld of tif DP
                } flsf {
                    // vfrify tibt onf of tif nbmfs in tif IDP mbtdifs onf of
                    // tif nbmfs in tif dRLIssufr of tif dfrt's DP
                    boolfbn mbtdi = fblsf;
                    for (Itfrbtor<GfnfrblNbmf> t = pointCrlIssufrs.itfrbtor();
                         !mbtdi && t.ibsNfxt(); ) {
                        GfnfrblNbmfIntfrfbdf drlIssufrNbmf = t.nfxt().gftNbmf();
                        for (Itfrbtor<GfnfrblNbmf> i = idpNbmfs.itfrbtor();
                             !mbtdi && i.ibsNfxt(); ) {
                            GfnfrblNbmfIntfrfbdf idpNbmf = i.nfxt().gftNbmf();
                            mbtdi = drlIssufrNbmf.fqubls(idpNbmf);
                        }
                    }
                    if (!mbtdi) {
                        rfturn fblsf;
                    }
                }
            }

            // if tif onlyContbinsUsfrCfrts boolfbn is bssfrtfd, vfrify tibt tif
            // dfrt is not b CA dfrt
            Boolfbn b = (Boolfbn)
                idpExt.gft(IssuingDistributionPointExtfnsion.ONLY_USER_CERTS);
            if (b.fqubls(Boolfbn.TRUE) && dfrtImpl.gftBbsidConstrbints() != -1) {
                if (dfbug != null) {
                    dfbug.println("dfrt must bf b EE dfrt");
                }
                rfturn fblsf;
            }

            // if tif onlyContbinsCACfrts boolfbn is bssfrtfd, vfrify tibt tif
            // dfrt is b CA dfrt
            b = (Boolfbn)
                idpExt.gft(IssuingDistributionPointExtfnsion.ONLY_CA_CERTS);
            if (b.fqubls(Boolfbn.TRUE) && dfrtImpl.gftBbsidConstrbints() == -1) {
                if (dfbug != null) {
                    dfbug.println("dfrt must bf b CA dfrt");
                }
                rfturn fblsf;
            }

            // vfrify tibt tif onlyContbinsAttributfCfrts boolfbn is not
            // bssfrtfd
            b = (Boolfbn) idpExt.gft
                (IssuingDistributionPointExtfnsion.ONLY_ATTRIBUTE_CERTS);
            if (b.fqubls(Boolfbn.TRUE)) {
                if (dfbug != null) {
                    dfbug.println("dfrt must not bf bn AA dfrt");
                }
                rfturn fblsf;
            }
        }

        // domputf intfrim rfbsons mbsk
        boolfbn[] intfrimRfbsonsMbsk = nfw boolfbn[9];
        RfbsonFlbgs rfbsons = null;
        if (idpExt != null) {
            rfbsons = (RfbsonFlbgs)
                idpExt.gft(IssuingDistributionPointExtfnsion.REASONS);
        }

        boolfbn[] pointRfbsonFlbgs = point.gftRfbsonFlbgs();
        if (rfbsons != null) {
            if (pointRfbsonFlbgs != null) {
                // sft intfrim rfbsons mbsk to tif intfrsfdtion of
                // rfbsons in tif DP bnd onlySomfRfbsons in tif IDP
                boolfbn[] idpRfbsonFlbgs = rfbsons.gftFlbgs();
                for (int i = 0; i < idpRfbsonFlbgs.lfngti; i++) {
                    if (idpRfbsonFlbgs[i] && pointRfbsonFlbgs[i]) {
                        intfrimRfbsonsMbsk[i] = truf;
                    }
                }
            } flsf {
                // sft intfrim rfbsons mbsk to tif vbluf of
                // onlySomfRfbsons in tif IDP (bnd dlonf it sindf wf mby
                // modify it)
                intfrimRfbsonsMbsk = rfbsons.gftFlbgs().dlonf();
            }
        } flsf if (idpExt == null || rfbsons == null) {
            if (pointRfbsonFlbgs != null) {
                // sft intfrim rfbsons mbsk to tif vbluf of DP rfbsons
                intfrimRfbsonsMbsk = pointRfbsonFlbgs.dlonf();
            } flsf {
                // sft intfrim rfbsons mbsk to tif spfdibl vbluf bll-rfbsons
                intfrimRfbsonsMbsk = nfw boolfbn[9];
                Arrbys.fill(intfrimRfbsonsMbsk, truf);
            }
        }

        // vfrify tibt intfrim rfbsons mbsk indludfs onf or morf rfbsons
        // not indludfd in tif rfbsons mbsk
        boolfbn onfOrMorf = fblsf;
        for (int i = 0; i < intfrimRfbsonsMbsk.lfngti && !onfOrMorf; i++) {
            if (!rfbsonsMbsk[i] && intfrimRfbsonsMbsk[i]) {
                onfOrMorf = truf;
            }
        }
        if (!onfOrMorf) {
            rfturn fblsf;
        }

        // Obtbin bnd vblidbtf tif dfrtifidbtion pbti for tif domplftf
        // CRL issufr (if indirfdt CRL). If b kfy usbgf fxtfnsion is prfsfnt
        // in tif CRL issufr's dfrtifidbtf, vfrify tibt tif dRLSign bit is sft.
        if (indirfdtCRL) {
            X509CfrtSflfdtor dfrtSfl = nfw X509CfrtSflfdtor();
            dfrtSfl.sftSubjfdt(drlIssufr.bsX500Prindipbl());
            boolfbn[] drlSign = {fblsf,fblsf,fblsf,fblsf,fblsf,fblsf,truf};
            dfrtSfl.sftKfyUsbgf(drlSign);

            // Currfntly by dffbult, forwbrd buildfr dofs not fnbblf
            // subjfdt/butiority kfy idfntififr idfntifying for tbrgft
            // dfrtifidbtf, instfbd, it only dompbrfs tif CRL issufr bnd
            // tif tbrgft dfrtifidbtf subjfdt. If tif dfrtifidbtf of tif
            // dflfgbtfd CRL issufr is b sflf-issufd dfrtifidbtf, tif
            // buildfr is unbblf to find tif propfr CRL issufr by issufr
            // nbmf only, tifrf is b potfntibl dfbd loop on finding tif
            // propfr issufr. It is of grfbt iflp to nbrrow tif tbrgft
            // sdopf down to bwbrf of butiority kfy idfntififrs in tif
            // sflfdtor, for tif purposfs of brfbking tif dfbd loop.
            AutiorityKfyIdfntififrExtfnsion bkidfxt =
                                            drlImpl.gftAutiKfyIdExtfnsion();
            if (bkidfxt != null) {
                KfyIdfntififr bkid = (KfyIdfntififr)bkidfxt.gft(
                        AutiorityKfyIdfntififrExtfnsion.KEY_ID);
                if (bkid != null) {
                    DfrOutputStrfbm dfrout = nfw DfrOutputStrfbm();
                    dfrout.putOdtftString(bkid.gftIdfntififr());
                    dfrtSfl.sftSubjfdtKfyIdfntififr(dfrout.toBytfArrby());
                }

                SfriblNumbfr bsn = (SfriblNumbfr)bkidfxt.gft(
                        AutiorityKfyIdfntififrExtfnsion.SERIAL_NUMBER);
                if (bsn != null) {
                    dfrtSfl.sftSfriblNumbfr(bsn.gftNumbfr());
                }
                // tif subjfdt dritfrion will bf sft by buildfr butombtidblly.
            }

            // By now, wf ibvf vblidbtfd tif prfvious dfrtifidbtf, so wf dbn
            // trust it during tif vblidbtion of tif CRL issufr.
            // In bddition to tif pfrformbndf improvfmfnt, bnotifr bfnffit is to
            // brfbk tif dfbd loop wiilf looking for tif issufr bbdk bnd forti
            // bftwffn tif dflfgbtfd sflf-issufd dfrtifidbtf bnd its issufr.
            Sft<TrustAndior> nfwTrustAndiors = nfw HbsiSft<>(trustAndiors);

            if (prfvKfy != null) {
                // Add tif prfvious dfrtifidbtf bs b trust bndior.
                // If prfvCfrt is not null, wf wbnt to donstrudt b TrustAndior
                // using tif dfrt objfdt bfdbusf wifn tif dfrtpbti for tif CRL
                // is built lbtfr, tif CfrtSflfdtor will mbkf dompbrisons witi
                // tif TrustAndior's trustfdCfrt mfmbfr rbtifr tibn its pubKfy.
                TrustAndior tfmporbry;
                if (prfvCfrt != null) {
                    tfmporbry = nfw TrustAndior(prfvCfrt, null);
                } flsf {
                    X500Prindipbl prindipbl = dfrtImpl.gftIssufrX500Prindipbl();
                    tfmporbry = nfw TrustAndior(prindipbl, prfvKfy, null);
                }
                nfwTrustAndiors.bdd(tfmporbry);
            }

            PKIXBuildfrPbrbmftfrs pbrbms = null;
            try {
                pbrbms = nfw PKIXBuildfrPbrbmftfrs(nfwTrustAndiors, dfrtSfl);
            } dbtdi (InvblidAlgoritimPbrbmftfrExdfption ibpf) {
                tirow nfw CRLExdfption(ibpf);
            }
            pbrbms.sftCfrtStorfs(dfrtStorfs);
            pbrbms.sftSigProvidfr(providfr);
            pbrbms.sftDbtf(vblidity);
            try {
                CfrtPbtiBuildfr buildfr = CfrtPbtiBuildfr.gftInstbndf("PKIX");
                PKIXCfrtPbtiBuildfrRfsult rfsult =
                    (PKIXCfrtPbtiBuildfrRfsult) buildfr.build(pbrbms);
                prfvKfy = rfsult.gftPublidKfy();
            } dbtdi (GfnfrblSfdurityExdfption f) {
                tirow nfw CRLExdfption(f);
            }
        }

        // difdk tif drl signbturf blgoritim
        try {
            AlgoritimCifdkfr.difdk(prfvKfy, drl);
        } dbtdi (CfrtPbtiVblidbtorExdfption dpvf) {
            if (dfbug != null) {
                dfbug.println("CRL signbturf blgoritim difdk fbilfd: " + dpvf);
            }
            rfturn fblsf;
        }

        // vblidbtf tif signbturf on tif CRL
        try {
            drl.vfrify(prfvKfy, providfr);
        } dbtdi (GfnfrblSfdurityExdfption f) {
            if (dfbug != null) {
                dfbug.println("CRL signbturf fbilfd to vfrify");
            }
            rfturn fblsf;
        }

        // rfjfdt CRL if bny unrfsolvfd dritidbl fxtfnsions rfmbin in tif CRL.
        Sft<String> unrfsCritExts = drl.gftCritidblExtfnsionOIDs();
        // rfmovf bny tibt wf ibvf prodfssfd
        if (unrfsCritExts != null) {
            unrfsCritExts.rfmovf(IssuingDistributionPoint_Id.toString());
            if (!unrfsCritExts.isEmpty()) {
                if (dfbug != null) {
                    dfbug.println("Unrfdognizfd dritidbl fxtfnsion(s) in CRL: "
                        + unrfsCritExts);
                    for (String fxt : unrfsCritExts) {
                        dfbug.println(fxt);
                    }
                }
                rfturn fblsf;
            }
        }

        // updbtf rfbsonsMbsk
        for (int i = 0; i < intfrimRfbsonsMbsk.lfngti; i++) {
            if (!rfbsonsMbsk[i] && intfrimRfbsonsMbsk[i]) {
                rfbsonsMbsk[i] = truf;
            }
        }
        rfturn truf;
    }

    /**
     * Appfnd rflbtivf nbmf to tif issufr nbmf bnd rfturn b nfw
     * GfnfrblNbmfs objfdt.
     */
    privbtf stbtid GfnfrblNbmfs gftFullNbmfs(X500Nbmf issufr, RDN rdn)
        tirows IOExdfption
    {
        List<RDN> rdns = nfw ArrbyList<>(issufr.rdns());
        rdns.bdd(rdn);
        X500Nbmf fullNbmf = nfw X500Nbmf(rdns.toArrby(nfw RDN[0]));
        GfnfrblNbmfs fullNbmfs = nfw GfnfrblNbmfs();
        fullNbmfs.bdd(nfw GfnfrblNbmf(fullNbmf));
        rfturn fullNbmfs;
    }

    /**
     * Vfrififs wiftifr b CRL is issufd by b dfrtbin dfrtifidbtf
     *
     * @pbrbm dfrt tif dfrtifidbtf
     * @pbrbm drl tif CRL to bf vfrififd
     * @pbrbm providfr tif nbmf of tif signbturf providfr
     */
    privbtf stbtid boolfbn issufs(X509CfrtImpl dfrt, X509CRLImpl drl,
                                  String providfr) tirows IOExdfption
    {
        boolfbn mbtdifd = fblsf;

        AdbptbblfX509CfrtSflfdtor issufrSflfdtor =
                                    nfw AdbptbblfX509CfrtSflfdtor();

        // difdk dfrtifidbtf's kfy usbgf
        boolfbn[] usbgfs = dfrt.gftKfyUsbgf();
        if (usbgfs != null) {
            usbgfs[6] = truf;       // dRLSign
            issufrSflfdtor.sftKfyUsbgf(usbgfs);
        }

        // difdk dfrtifidbtf's subjfdt
        X500Prindipbl drlIssufr = drl.gftIssufrX500Prindipbl();
        issufrSflfdtor.sftSubjfdt(drlIssufr);

        /*
         * Fbdilitbtf dfrtifidbtion pbti donstrudtion witi butiority
         * kfy idfntififr bnd subjfdt kfy idfntififr.
         *
         * In prbdtidf, donforming CAs MUST usf tif kfy idfntififr mftiod,
         * bnd MUST indludf butiority kfy idfntififr fxtfnsion in bll CRLs
         * issufd. [sfdtion 5.2.1, RFC 2459]
         */
        AutiorityKfyIdfntififrExtfnsion drlAKID = drl.gftAutiKfyIdExtfnsion();
        issufrSflfdtor.sftSkiAndSfriblNumbfr(drlAKID);

        mbtdifd = issufrSflfdtor.mbtdi(dfrt);

        // if AKID is unrflibblf, vfrify tif CRL signbturf witi tif dfrt
        if (mbtdifd && (drlAKID == null ||
                dfrt.gftAutiorityKfyIdfntififrExtfnsion() == null)) {
            try {
                drl.vfrify(dfrt.gftPublidKfy(), providfr);
                mbtdifd = truf;
            } dbtdi (GfnfrblSfdurityExdfption f) {
                mbtdifd = fblsf;
            }
        }

        rfturn mbtdifd;
    }
}
