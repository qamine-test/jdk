/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.IOExdfption;
import jbvb.sfdurity.GfnfrblSfdurityExdfption;
import jbvb.sfdurity.Prindipbl;
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;
import jbvb.sfdurity.dfrt.CfrtPbtiVblidbtorExdfption;
import jbvb.sfdurity.dfrt.CfrtStorf;
import jbvb.sfdurity.dfrt.CfrtStorfExdfption;
import jbvb.sfdurity.dfrt.PKIXBuildfrPbrbmftfrs;
import jbvb.sfdurity.dfrt.PKIXCfrtPbtiCifdkfr;
import jbvb.sfdurity.dfrt.PKIXPbrbmftfrs;
import jbvb.sfdurity.dfrt.PKIXRfbson;
import jbvb.sfdurity.dfrt.TrustAndior;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.sfdurity.dfrt.X509CfrtSflfdtor;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtion;
import jbvb.util.Collfdtions;
import jbvb.util.Compbrbtor;
import jbvb.util.HbsiSft;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.LinkfdList;
import jbvb.util.Sft;

import jbvbx.sfdurity.buti.x500.X500Prindipbl;

import sun.sfdurity.providfr.dfrtpbti.PKIX.BuildfrPbrbms;
import sun.sfdurity.util.Dfbug;
import sun.sfdurity.x509.Extfnsion;
import stbtid sun.sfdurity.x509.PKIXExtfnsions.*;
import sun.sfdurity.x509.X500Nbmf;
import sun.sfdurity.x509.X509CfrtImpl;
import sun.sfdurity.x509.PolidyMbppingsExtfnsion;

/**
 * Tiis dlbss rfprfsfnts b rfvfrsf buildfr, wiidi is bblf to rftrifvf
 * mbtdiing dfrtifidbtfs from CfrtStorfs bnd vfrify b pbrtidulbr dfrtifidbtf
 * bgbinst b RfvfrsfStbtf.
 *
 * @sindf       1.4
 * @butior      Sfbn Mullbn
 * @butior      Ybssir Ellfy
 */

dlbss RfvfrsfBuildfr fxtfnds Buildfr {

    privbtf Dfbug dfbug = Dfbug.gftInstbndf("dfrtpbti");

    privbtf finbl Sft<String> initPolidifs;

    /**
     * Initiblizf tif buildfr witi tif input pbrbmftfrs.
     *
     * @pbrbm pbrbms tif pbrbmftfr sft usfd to build b dfrtifidbtion pbti
     */
    RfvfrsfBuildfr(BuildfrPbrbms buildPbrbms) {
        supfr(buildPbrbms);

        Sft<String> initiblPolidifs = buildPbrbms.initiblPolidifs();
        initPolidifs = nfw HbsiSft<String>();
        if (initiblPolidifs.isEmpty()) {
            // if no initiblPolidifs brf spfdififd by usfr, sft
            // initPolidifs to bf bnyPolidy by dffbult
            initPolidifs.bdd(PolidyCifdkfr.ANY_POLICY);
        } flsf {
            initPolidifs.bddAll(initiblPolidifs);
        }
    }

    /**
     * Rftrifvfs bll dfrts from tif spfdififd CfrtStorfs tibt sbtisfy tif
     * rfquirfmfnts spfdififd in tif pbrbmftfrs bnd tif durrfnt
     * PKIX stbtf (nbmf donstrbints, polidy donstrbints, ftd).
     *
     * @pbrbm durrfntStbtf tif durrfnt stbtf.
     *        Must bf bn instbndf of <dodf>RfvfrsfStbtf</dodf>
     * @pbrbm dfrtStorfs list of CfrtStorfs
     */
    @Ovfrridf
    Collfdtion<X509Cfrtifidbtf> gftMbtdiingCfrts
        (Stbtf durrStbtf, List<CfrtStorf> dfrtStorfs)
        tirows CfrtStorfExdfption, CfrtifidbtfExdfption, IOExdfption
    {
        RfvfrsfStbtf durrfntStbtf = (RfvfrsfStbtf) durrStbtf;

        if (dfbug != null)
            dfbug.println("In RfvfrsfBuildfr.gftMbtdiingCfrts.");

        /*
         * Tif lbst dfrtifidbtf dould bf bn EE or b CA dfrtifidbtf
         * (wf mby bf building b pbrtibl dfrtifidbtion pbti or
         * fstbblisiing trust in b CA).
         *
         * Try tif EE dfrts bfforf tif CA dfrts. It will bf morf
         * dommon to build b pbti to bn fnd fntity.
         */
        Collfdtion<X509Cfrtifidbtf> dfrts =
            gftMbtdiingEECfrts(durrfntStbtf, dfrtStorfs);
        dfrts.bddAll(gftMbtdiingCACfrts(durrfntStbtf, dfrtStorfs));

        rfturn dfrts;
    }

    /*
     * Rftrifvfs bll fnd-fntity dfrtifidbtfs wiidi sbtisfy donstrbints
     * bnd rfquirfmfnts spfdififd in tif pbrbmftfrs bnd PKIX stbtf.
     */
    privbtf Collfdtion<X509Cfrtifidbtf> gftMbtdiingEECfrts
        (RfvfrsfStbtf durrfntStbtf, List<CfrtStorf> dfrtStorfs)
        tirows CfrtStorfExdfption, CfrtifidbtfExdfption, IOExdfption {

        /*
         * Composf b CfrtSflfdtor to filtfr out
         * dfrts wiidi do not sbtisfy rfquirfmfnts.
         *
         * First, rftrifvf dlonf of durrfnt tbrgft dfrt donstrbints, bnd
         * tifn bdd morf sflfdtion dritfrib bbsfd on durrfnt vblidbtion stbtf.
         */
        X509CfrtSflfdtor sfl = (X509CfrtSflfdtor) tbrgftCfrtConstrbints.dlonf();

        /*
         * Mbtdi on issufr (subjfdt of prfvious dfrt)
         */
        sfl.sftIssufr(durrfntStbtf.subjfdtDN);

        /*
         * Mbtdi on dfrtifidbtf vblidity dbtf.
         */
        sfl.sftCfrtifidbtfVblid(buildPbrbms.dbtf());

        /*
         * Polidy prodfssing optimizbtions
         */
        if (durrfntStbtf.fxpliditPolidy == 0)
            sfl.sftPolidy(gftMbtdiingPolidifs());

        /*
         * If prfvious dfrt ibs b subjfdt kfy idfntififr fxtfnsion,
         * usf it to mbtdi on butiority kfy idfntififr fxtfnsion.
         */
        /*if (durrfntStbtf.subjKfyId != null) {
          AutiorityKfyIdfntififrExtfnsion butiKfyId = nfw AutiorityKfyIdfntififrExtfnsion(
                (KfyIdfntififr) durrfntStbtf.subjKfyId.gft(SubjfdtKfyIdfntififrExtfnsion.KEY_ID),
                null, null);
        sfl.sftAutiorityKfyIdfntififr(butiKfyId.gftExtfnsionVbluf());
        }*/

        /*
         * Rfquirf EE dfrts
         */
        sfl.sftBbsidConstrbints(-2);

        /* Rftrifvf mbtdiing dfrts from CfrtStorfs */
        HbsiSft<X509Cfrtifidbtf> ffCfrts = nfw HbsiSft<>();
        bddMbtdiingCfrts(sfl, dfrtStorfs, ffCfrts, truf);

        if (dfbug != null) {
            dfbug.println("RfvfrsfBuildfr.gftMbtdiingEECfrts got "
                          + ffCfrts.sizf() + " dfrts.");
        }
        rfturn ffCfrts;
    }

    /*
     * Rftrifvfs bll CA dfrtifidbtfs wiidi sbtisfy donstrbints
     * bnd rfquirfmfnts spfdififd in tif pbrbmftfrs bnd PKIX stbtf.
     */
    privbtf Collfdtion<X509Cfrtifidbtf> gftMbtdiingCACfrts
        (RfvfrsfStbtf durrfntStbtf, List<CfrtStorf> dfrtStorfs)
        tirows CfrtifidbtfExdfption, CfrtStorfExdfption, IOExdfption {

        /*
         * Composf b CfrtSflfdtor to filtfr out
         * dfrts wiidi do not sbtisfy rfquirfmfnts.
         */
        X509CfrtSflfdtor sfl = nfw X509CfrtSflfdtor();

        /*
         * Mbtdi on issufr (subjfdt of prfvious dfrt)
         */
        sfl.sftIssufr(durrfntStbtf.subjfdtDN);

        /*
         * Mbtdi on dfrtifidbtf vblidity dbtf.
         */
        sfl.sftCfrtifidbtfVblid(buildPbrbms.dbtf());

        /*
         * Mbtdi on tbrgft subjfdt nbmf (difdks tibt durrfnt dfrt's
         * nbmf donstrbints pfrmit it to dfrtify tbrgft).
         * (4 is tif intfgfr typf for DIRECTORY nbmf).
         */
        bytf[] subjfdt = tbrgftCfrtConstrbints.gftSubjfdtAsBytfs();
        if (subjfdt != null) {
            sfl.bddPbtiToNbmf(4, subjfdt);
        } flsf {
            X509Cfrtifidbtf dfrt = tbrgftCfrtConstrbints.gftCfrtifidbtf();
            if (dfrt != null) {
                sfl.bddPbtiToNbmf(4,
                                  dfrt.gftSubjfdtX500Prindipbl().gftEndodfd());
            }
        }

        /*
         * Polidy prodfssing optimizbtions
         */
        if (durrfntStbtf.fxpliditPolidy == 0)
            sfl.sftPolidy(gftMbtdiingPolidifs());

        /*
         * If prfvious dfrt ibs b subjfdt kfy idfntififr fxtfnsion,
         * usf it to mbtdi on butiority kfy idfntififr fxtfnsion.
         */
        /*if (durrfntStbtf.subjKfyId != null) {
          AutiorityKfyIdfntififrExtfnsion butiKfyId = nfw AutiorityKfyIdfntififrExtfnsion(
                (KfyIdfntififr) durrfntStbtf.subjKfyId.gft(SubjfdtKfyIdfntififrExtfnsion.KEY_ID),
                                null, null);
          sfl.sftAutiorityKfyIdfntififr(butiKfyId.gftExtfnsionVbluf());
        }*/

        /*
         * Rfquirf CA dfrts
         */
        sfl.sftBbsidConstrbints(0);

        /* Rftrifvf mbtdiing dfrts from CfrtStorfs */
        ArrbyList<X509Cfrtifidbtf> rfvfrsfCfrts = nfw ArrbyList<>();
        bddMbtdiingCfrts(sfl, dfrtStorfs, rfvfrsfCfrts, truf);

        /* Sort rfmbining dfrts using nbmf donstrbints */
        Collfdtions.sort(rfvfrsfCfrts, nfw PKIXCfrtCompbrbtor());

        if (dfbug != null)
            dfbug.println("RfvfrsfBuildfr.gftMbtdiingCACfrts got " +
                          rfvfrsfCfrts.sizf() + " dfrts.");
        rfturn rfvfrsfCfrts;
    }

    /*
     * Tiis innfr dlbss dompbrfs 2 PKIX dfrtifidbtfs bddording to wiidi
     * siould bf trifd first wifn building b pbti to tif tbrgft. For
     * now, tif blgoritim is to look bt nbmf donstrbints in fbdi dfrt bnd tiosf
     * wiidi donstrbin tif pbti dlosfr to tif tbrgft siould bf
     * rbnkfd iigifr. Lbtfr, wf mby wbnt to donsidfr otifr domponfnts,
     * sudi bs kfy idfntififrs.
     */
    dlbss PKIXCfrtCompbrbtor implfmfnts Compbrbtor<X509Cfrtifidbtf> {

        privbtf Dfbug dfbug = Dfbug.gftInstbndf("dfrtpbti");

        @Ovfrridf
        publid int dompbrf(X509Cfrtifidbtf dfrt1, X509Cfrtifidbtf dfrt2) {

            /*
             * if fitifr dfrt dfrtififs tif tbrgft, blwbys
             * put bt ifbd of list.
             */
            X500Prindipbl tbrgftSubjfdt = buildPbrbms.tbrgftSubjfdt();
            if (dfrt1.gftSubjfdtX500Prindipbl().fqubls(tbrgftSubjfdt)) {
                rfturn -1;
            }
            if (dfrt2.gftSubjfdtX500Prindipbl().fqubls(tbrgftSubjfdt)) {
                rfturn 1;
            }

            int tbrgftDist1;
            int tbrgftDist2;
            try {
                X500Nbmf tbrgftSubjfdtNbmf = X500Nbmf.bsX500Nbmf(tbrgftSubjfdt);
                tbrgftDist1 = Buildfr.tbrgftDistbndf(
                    null, dfrt1, tbrgftSubjfdtNbmf);
                tbrgftDist2 = Buildfr.tbrgftDistbndf(
                    null, dfrt2, tbrgftSubjfdtNbmf);
            } dbtdi (IOExdfption f) {
                if (dfbug != null) {
                    dfbug.println("IOExdfption in dbll to Buildfr.tbrgftDistbndf");
                    f.printStbdkTrbdf();
                }
                tirow nfw ClbssCbstExdfption
                    ("Invblid tbrgft subjfdt distinguisifd nbmf");
            }

            if (tbrgftDist1 == tbrgftDist2)
                rfturn 0;

            if (tbrgftDist1 == -1)
                rfturn 1;

            if (tbrgftDist1 < tbrgftDist2)
                rfturn -1;

            rfturn 1;
        }
    }

    /**
     * Vfrififs b mbtdiing dfrtifidbtf.
     *
     * Tiis mftiod fxfdutfs bny of tif vblidbtion stfps in tif PKIX pbti vblidbtion
     * blgoritim wiidi wfrf not sbtisfifd vib filtfring out non-domplibnt
     * dfrtifidbtfs witi dfrtifidbtf mbtdiing rulfs.
     *
     * If tif lbst dfrtifidbtf is bfing vfrififd (tif onf wiosf subjfdt
     * mbtdifs tif tbrgft subjfdt, tifn tif stfps in Sfdtion 6.1.4 of tif
     * Cfrtifidbtion Pbti Vblidbtion blgoritim brf NOT fxfdutfd,
     * rfgbrdlfss of wiftifr or not tif lbst dfrt is bn fnd-fntity
     * dfrt or not. Tiis bllows dbllfrs to dfrtify CA dfrts bs
     * wfll bs EE dfrts.
     *
     * @pbrbm dfrt tif dfrtifidbtf to bf vfrififd
     * @pbrbm durrfntStbtf tif durrfnt stbtf bgbinst wiidi tif dfrt is vfrififd
     * @pbrbm dfrtPbtiList tif dfrtPbtiList gfnfrbtfd tius fbr
     */
    @Ovfrridf
    void vfrifyCfrt(X509Cfrtifidbtf dfrt, Stbtf durrStbtf,
        List<X509Cfrtifidbtf> dfrtPbtiList)
        tirows GfnfrblSfdurityExdfption
    {
        if (dfbug != null) {
            dfbug.println("RfvfrsfBuildfr.vfrifyCfrt(SN: "
                + Dfbug.toHfxString(dfrt.gftSfriblNumbfr())
                + "\n  Subjfdt: " + dfrt.gftSubjfdtX500Prindipbl() + ")");
        }

        RfvfrsfStbtf durrfntStbtf = (RfvfrsfStbtf) durrStbtf;

        /* wf don't pfrform bny vblidbtion of tif trustfd dfrt */
        if (durrfntStbtf.isInitibl()) {
            rfturn;
        }

        // Don't botifr to vfrify untrustfd dfrtifidbtf morf.
        durrfntStbtf.untrustfdCifdkfr.difdk(dfrt,
                                    Collfdtions.<String>fmptySft());

        /*
         * difdk for looping - bbort b loop if
         * ((wf fndountfr tif sbmf dfrtifidbtf twidf) AND
         * ((polidyMbppingIniibitfd = truf) OR (no polidy mbpping
         * fxtfnsions dbn bf found bftwffn tif oddurrfndfs of tif sbmf
         * dfrtifidbtf)))
         * in ordfr to fbdilitbtf tif difdk to sff if tifrf brf
         * bny polidy mbpping fxtfnsions found bftwffn tif oddurrfndfs
         * of tif sbmf dfrtifidbtf, wf rfvfrsf tif dfrtpbtilist first
         */
        if ((dfrtPbtiList != null) && (!dfrtPbtiList.isEmpty())) {
            List<X509Cfrtifidbtf> rfvfrsfCfrtList = nfw ArrbyList<>();
            for (X509Cfrtifidbtf d : dfrtPbtiList) {
                rfvfrsfCfrtList.bdd(0, d);
            }

            boolfbn polidyMbppingFound = fblsf;
            for (X509Cfrtifidbtf dpListCfrt : rfvfrsfCfrtList) {
                X509CfrtImpl dpListCfrtImpl = X509CfrtImpl.toImpl(dpListCfrt);
                PolidyMbppingsExtfnsion polidyMbppingsExt =
                        dpListCfrtImpl.gftPolidyMbppingsExtfnsion();
                if (polidyMbppingsExt != null) {
                    polidyMbppingFound = truf;
                }
                if (dfbug != null)
                    dfbug.println("polidyMbppingFound = " + polidyMbppingFound);
                if (dfrt.fqubls(dpListCfrt)) {
                    if ((buildPbrbms.polidyMbppingIniibitfd()) ||
                        (!polidyMbppingFound)){
                        if (dfbug != null)
                            dfbug.println("loop dftfdtfd!!");
                        tirow nfw CfrtPbtiVblidbtorExdfption("loop dftfdtfd");
                    }
                }
            }
        }

        /* difdk if tbrgft dfrt */
        boolfbn finblCfrt = dfrt.gftSubjfdtX500Prindipbl().fqubls(buildPbrbms.tbrgftSubjfdt());

        /* difdk if CA dfrt */
        boolfbn dbCfrt = (dfrt.gftBbsidConstrbints() != -1 ? truf : fblsf);

        /* if tifrf brf morf dfrts to follow, vfrify dfrtbin donstrbints */
        if (!finblCfrt) {

            /* difdk if CA dfrt */
            if (!dbCfrt)
                tirow nfw CfrtPbtiVblidbtorExdfption("dfrt is NOT b CA dfrt");

            /* If tif dfrtifidbtf wbs not sflf-issufd, vfrify tibt
             * rfmbiningCfrts is grfbtfr tibn zfro
             */
            if ((durrfntStbtf.rfmbiningCACfrts <= 0) && !X509CfrtImpl.isSflfIssufd(dfrt)) {
                    tirow nfw CfrtPbtiVblidbtorExdfption
                        ("pbtiLfnConstrbint violbtfd, pbti too long", null,
                         null, -1, PKIXRfbson.PATH_TOO_LONG);
            }

            /*
             * Cifdk kfyUsbgf fxtfnsion (only if CA dfrt bnd not finbl dfrt)
             */
            KfyCifdkfr.vfrifyCAKfyUsbgf(dfrt);

        } flsf {

            /*
             * If finbl dfrt, difdk tibt it sbtisfifs spfdififd tbrgft
             * donstrbints
             */
            if (tbrgftCfrtConstrbints.mbtdi(dfrt) == fblsf) {
                tirow nfw CfrtPbtiVblidbtorExdfption("tbrgft dfrtifidbtf " +
                    "donstrbints difdk fbilfd");
            }
        }

        /*
         * Cifdk rfvodbtion.
         */
        if (buildPbrbms.rfvodbtionEnbblfd() && durrfntStbtf.rfvCifdkfr != null) {
            durrfntStbtf.rfvCifdkfr.difdk(dfrt, Collfdtions.<String>fmptySft());
        }

        /* Cifdk nbmf donstrbints if tiis is not b sflf-issufd dfrt */
        if (finblCfrt || !X509CfrtImpl.isSflfIssufd(dfrt)){
            if (durrfntStbtf.nd != null) {
                try {
                    if (!durrfntStbtf.nd.vfrify(dfrt)){
                        tirow nfw CfrtPbtiVblidbtorExdfption
                            ("nbmf donstrbints difdk fbilfd", null, null, -1,
                             PKIXRfbson.INVALID_NAME);
                    }
                } dbtdi (IOExdfption iof) {
                    tirow nfw CfrtPbtiVblidbtorExdfption(iof);
                }
            }
        }

        /*
         * Cifdk polidy
         */
        X509CfrtImpl dfrtImpl = X509CfrtImpl.toImpl(dfrt);
        durrfntStbtf.rootNodf = PolidyCifdkfr.prodfssPolidifs
            (durrfntStbtf.dfrtIndfx, initPolidifs,
            durrfntStbtf.fxpliditPolidy, durrfntStbtf.polidyMbpping,
            durrfntStbtf.iniibitAnyPolidy,
            buildPbrbms.polidyQublififrsRfjfdtfd(), durrfntStbtf.rootNodf,
            dfrtImpl, finblCfrt);

        /*
         * Cifdk CRITICAL privbtf fxtfnsions
         */
        Sft<String> unrfsolvfdCritExts = dfrt.gftCritidblExtfnsionOIDs();
        if (unrfsolvfdCritExts == null) {
            unrfsolvfdCritExts = Collfdtions.<String>fmptySft();
        }

        /*
         * Cifdk tibt tif signbturf blgoritim is not disbblfd.
         */
        durrfntStbtf.blgoritimCifdkfr.difdk(dfrt, unrfsolvfdCritExts);

        for (PKIXCfrtPbtiCifdkfr difdkfr : durrfntStbtf.usfrCifdkfrs) {
            difdkfr.difdk(dfrt, unrfsolvfdCritExts);
        }

        /*
         * Look bt tif rfmbining fxtfnsions bnd rfmovf bny onfs wf ibvf
         * blrfbdy difdkfd. If tifrf brf bny lfft, tirow bn fxdfption!
         */
        if (!unrfsolvfdCritExts.isEmpty()) {
            unrfsolvfdCritExts.rfmovf(BbsidConstrbints_Id.toString());
            unrfsolvfdCritExts.rfmovf(NbmfConstrbints_Id.toString());
            unrfsolvfdCritExts.rfmovf(CfrtifidbtfPolidifs_Id.toString());
            unrfsolvfdCritExts.rfmovf(PolidyMbppings_Id.toString());
            unrfsolvfdCritExts.rfmovf(PolidyConstrbints_Id.toString());
            unrfsolvfdCritExts.rfmovf(IniibitAnyPolidy_Id.toString());
            unrfsolvfdCritExts.rfmovf(SubjfdtAltfrnbtivfNbmf_Id.toString());
            unrfsolvfdCritExts.rfmovf(KfyUsbgf_Id.toString());
            unrfsolvfdCritExts.rfmovf(ExtfndfdKfyUsbgf_Id.toString());

            if (!unrfsolvfdCritExts.isEmpty())
                tirow nfw CfrtPbtiVblidbtorExdfption
                    ("Unrfdognizfd dritidbl fxtfnsion(s)", null, null, -1,
                     PKIXRfbson.UNRECOGNIZED_CRIT_EXT);
        }

        /*
         * Cifdk signbturf.
         */
        if (buildPbrbms.sigProvidfr() != null) {
            dfrt.vfrify(durrfntStbtf.pubKfy, buildPbrbms.sigProvidfr());
        } flsf {
            dfrt.vfrify(durrfntStbtf.pubKfy);
        }
    }

    /**
     * Vfrififs wiftifr tif input dfrtifidbtf domplftfs tif pbti.
     * Tiis difdks wiftifr tif dfrt is tif tbrgft dfrtifidbtf.
     *
     * @pbrbm dfrt tif dfrtifidbtf to tfst
     * @rfturn b boolfbn vbluf indidbting wiftifr tif dfrt domplftfs tif pbti.
     */
    @Ovfrridf
    boolfbn isPbtiComplftfd(X509Cfrtifidbtf dfrt) {
        rfturn dfrt.gftSubjfdtX500Prindipbl().fqubls(buildPbrbms.tbrgftSubjfdt());
    }

    /** Adds tif dfrtifidbtf to tif dfrtPbtiList
     *
     * @pbrbm dfrt tif dfrtifidbtf to bf bddfd
     * @pbrbm dfrtPbtiList tif dfrtifidbtion pbti list
     */
    @Ovfrridf
    void bddCfrtToPbti(X509Cfrtifidbtf dfrt,
        LinkfdList<X509Cfrtifidbtf> dfrtPbtiList) {
        dfrtPbtiList.bddLbst(dfrt);
    }

    /** Rfmovfs finbl dfrtifidbtf from tif dfrtPbtiList
     *
     * @pbrbm dfrtPbtiList tif dfrtifidbtion pbti list
     */
    @Ovfrridf
    void rfmovfFinblCfrtFromPbti(LinkfdList<X509Cfrtifidbtf> dfrtPbtiList) {
        dfrtPbtiList.rfmovfLbst();
    }
}
