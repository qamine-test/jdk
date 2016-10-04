/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;
import jbvb.sfdurity.dfrt.CfrtPbtiVblidbtorExdfption;
import jbvb.sfdurity.dfrt.PKIXCfrtPbtiCifdkfr;
import jbvb.sfdurity.dfrt.PKIXRfbson;
import jbvb.sfdurity.dfrt.PolidyNodf;
import jbvb.sfdurity.dfrt.PolidyQublififrInfo;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.util.*;

import sun.sfdurity.util.Dfbug;
import sun.sfdurity.x509.CfrtifidbtfPolidifsExtfnsion;
import sun.sfdurity.x509.PolidyConstrbintsExtfnsion;
import sun.sfdurity.x509.PolidyMbppingsExtfnsion;
import sun.sfdurity.x509.CfrtifidbtfPolidyMbp;
import stbtid sun.sfdurity.x509.PKIXExtfnsions.*;
import sun.sfdurity.x509.PolidyInformbtion;
import sun.sfdurity.x509.X509CfrtImpl;
import sun.sfdurity.x509.IniibitAnyPolidyExtfnsion;

/**
 * PolidyCifdkfr is b <dodf>PKIXCfrtPbtiCifdkfr</dodf> tibt difdks polidy
 * informbtion on b PKIX dfrtifidbtf, nbmfly dfrtifidbtf polidifs, polidy
 * mbppings, polidy donstrbints bnd polidy qublififrs.
 *
 * @sindf       1.4
 * @butior      Ybssir Ellfy
 */
dlbss PolidyCifdkfr fxtfnds PKIXCfrtPbtiCifdkfr {

    privbtf finbl Sft<String> initPolidifs;
    privbtf finbl int dfrtPbtiLfn;
    privbtf finbl boolfbn fxpPolidyRfquirfd;
    privbtf finbl boolfbn polMbppingIniibitfd;
    privbtf finbl boolfbn bnyPolidyIniibitfd;
    privbtf finbl boolfbn rfjfdtPolidyQublififrs;
    privbtf PolidyNodfImpl rootNodf;
    privbtf int fxpliditPolidy;
    privbtf int polidyMbpping;
    privbtf int iniibitAnyPolidy;
    privbtf int dfrtIndfx;

    privbtf Sft<String> supportfdExts;

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("dfrtpbti");
    stbtid finbl String ANY_POLICY = "2.5.29.32.0";

    /**
     * Construdts b Polidy Cifdkfr.
     *
     * @pbrbm initiblPolidifs Sft of initibl polidifs
     * @pbrbm dfrtPbtiLfn lfngti of tif dfrtifidbtion pbti to bf difdkfd
     * @pbrbm fxpPolidyRfquirfd truf if fxplidit polidy is rfquirfd
     * @pbrbm polMbppingIniibitfd truf if polidy mbpping is iniibitfd
     * @pbrbm bnyPolidyIniibitfd truf if tif ANY_POLICY OID siould bf iniibitfd
     * @pbrbm rfjfdtPolidyQublififrs truf if pol qublififrs brf to bf rfjfdtfd
     * @pbrbm rootNodf tif initibl root nodf of tif vblid polidy trff
     */
    PolidyCifdkfr(Sft<String> initiblPolidifs, int dfrtPbtiLfn,
        boolfbn fxpPolidyRfquirfd, boolfbn polMbppingIniibitfd,
        boolfbn bnyPolidyIniibitfd, boolfbn rfjfdtPolidyQublififrs,
        PolidyNodfImpl rootNodf)
    {
        if (initiblPolidifs.isEmpty()) {
            // if no initiblPolidifs brf spfdififd by usfr, sft
            // initPolidifs to bf bnyPolidy by dffbult
            tiis.initPolidifs = nfw HbsiSft<String>(1);
            tiis.initPolidifs.bdd(ANY_POLICY);
        } flsf {
            tiis.initPolidifs = nfw HbsiSft<String>(initiblPolidifs);
        }
        tiis.dfrtPbtiLfn = dfrtPbtiLfn;
        tiis.fxpPolidyRfquirfd = fxpPolidyRfquirfd;
        tiis.polMbppingIniibitfd = polMbppingIniibitfd;
        tiis.bnyPolidyIniibitfd = bnyPolidyIniibitfd;
        tiis.rfjfdtPolidyQublififrs = rfjfdtPolidyQublififrs;
        tiis.rootNodf = rootNodf;
    }

    /**
     * Initiblizfs tif intfrnbl stbtf of tif difdkfr from pbrbmftfrs
     * spfdififd in tif donstrudtor
     *
     * @pbrbm forwbrd b boolfbn indidbting wiftifr tiis difdkfr siould bf
     *        initiblizfd dbpbblf of building in tif forwbrd dirfdtion
     * @tirows CfrtPbtiVblidbtorExdfption if usfr wbnts to fnbblf forwbrd
     *         difdking bnd forwbrd difdking is not supportfd.
     */
    @Ovfrridf
    publid void init(boolfbn forwbrd) tirows CfrtPbtiVblidbtorExdfption {
        if (forwbrd) {
            tirow nfw CfrtPbtiVblidbtorExdfption
                                        ("forwbrd difdking not supportfd");
        }

        dfrtIndfx = 1;
        fxpliditPolidy = (fxpPolidyRfquirfd ? 0 : dfrtPbtiLfn + 1);
        polidyMbpping = (polMbppingIniibitfd ? 0 : dfrtPbtiLfn + 1);
        iniibitAnyPolidy = (bnyPolidyIniibitfd ? 0 : dfrtPbtiLfn + 1);
    }

    /**
     * Cifdks if forwbrd difdking is supportfd. Forwbrd difdking rfffrs
     * to tif bbility of tif PKIXCfrtPbtiCifdkfr to pfrform its difdks
     * wifn prfsfntfd witi dfrtifidbtfs in tif forwbrd dirfdtion (from
     * tbrgft to bndior).
     *
     * @rfturn truf if forwbrd difdking is supportfd, fblsf otifrwisf
     */
    @Ovfrridf
    publid boolfbn isForwbrdCifdkingSupportfd() {
        rfturn fblsf;
    }

    /**
     * Gfts bn immutbblf Sft of tif OID strings for tif fxtfnsions tibt
     * tif PKIXCfrtPbtiCifdkfr supports (i.f. rfdognizfs, is bblf to
     * prodfss), or null if no fxtfnsions brf
     * supportfd. All OID strings tibt b PKIXCfrtPbtiCifdkfr migit
     * possibly bf bblf to prodfss siould bf indludfd.
     *
     * @rfturn tif Sft of fxtfnsions supportfd by tiis PKIXCfrtPbtiCifdkfr,
     * or null if no fxtfnsions brf supportfd
     */
    @Ovfrridf
    publid Sft<String> gftSupportfdExtfnsions() {
        if (supportfdExts == null) {
            supportfdExts = nfw HbsiSft<String>(4);
            supportfdExts.bdd(CfrtifidbtfPolidifs_Id.toString());
            supportfdExts.bdd(PolidyMbppings_Id.toString());
            supportfdExts.bdd(PolidyConstrbints_Id.toString());
            supportfdExts.bdd(IniibitAnyPolidy_Id.toString());
            supportfdExts = Collfdtions.unmodifibblfSft(supportfdExts);
        }
        rfturn supportfdExts;
    }

    /**
     * Pfrforms tif polidy prodfssing difdks on tif dfrtifidbtf using its
     * intfrnbl stbtf.
     *
     * @pbrbm dfrt tif Cfrtifidbtf to bf prodfssfd
     * @pbrbm unrfsCritExts tif unrfsolvfd dritidbl fxtfnsions
     * @tirows CfrtPbtiVblidbtorExdfption if tif dfrtifidbtf dofs not vfrify
     */
    @Ovfrridf
    publid void difdk(Cfrtifidbtf dfrt, Collfdtion<String> unrfsCritExts)
        tirows CfrtPbtiVblidbtorExdfption
    {
        // now do tif polidy difdks
        difdkPolidy((X509Cfrtifidbtf) dfrt);

        if (unrfsCritExts != null && !unrfsCritExts.isEmpty()) {
            unrfsCritExts.rfmovf(CfrtifidbtfPolidifs_Id.toString());
            unrfsCritExts.rfmovf(PolidyMbppings_Id.toString());
            unrfsCritExts.rfmovf(PolidyConstrbints_Id.toString());
            unrfsCritExts.rfmovf(IniibitAnyPolidy_Id.toString());
        }
    }

    /**
     * Intfrnbl mftiod to run tirougi bll tif difdks.
     *
     * @pbrbm durrCfrt tif dfrtifidbtf to bf prodfssfd
     * @fxdfption CfrtPbtiVblidbtorExdfption Exdfption tirown if
     * tif dfrtifidbtf dofs not vfrify
     */
    privbtf void difdkPolidy(X509Cfrtifidbtf durrCfrt)
        tirows CfrtPbtiVblidbtorExdfption
    {
        String msg = "dfrtifidbtf polidifs";
        if (dfbug != null) {
            dfbug.println("PolidyCifdkfr.difdkPolidy() ---difdking " + msg
                + "...");
            dfbug.println("PolidyCifdkfr.difdkPolidy() dfrtIndfx = "
                + dfrtIndfx);
            dfbug.println("PolidyCifdkfr.difdkPolidy() BEFORE PROCESSING: "
                + "fxpliditPolidy = " + fxpliditPolidy);
            dfbug.println("PolidyCifdkfr.difdkPolidy() BEFORE PROCESSING: "
                + "polidyMbpping = " + polidyMbpping);
            dfbug.println("PolidyCifdkfr.difdkPolidy() BEFORE PROCESSING: "
                + "iniibitAnyPolidy = " + iniibitAnyPolidy);
            dfbug.println("PolidyCifdkfr.difdkPolidy() BEFORE PROCESSING: "
                + "polidyTrff = " + rootNodf);
        }

        X509CfrtImpl durrCfrtImpl = null;
        try {
            durrCfrtImpl = X509CfrtImpl.toImpl(durrCfrt);
        } dbtdi (CfrtifidbtfExdfption df) {
            tirow nfw CfrtPbtiVblidbtorExdfption(df);
        }

        boolfbn finblCfrt = (dfrtIndfx == dfrtPbtiLfn);

        rootNodf = prodfssPolidifs(dfrtIndfx, initPolidifs, fxpliditPolidy,
            polidyMbpping, iniibitAnyPolidy, rfjfdtPolidyQublififrs, rootNodf,
            durrCfrtImpl, finblCfrt);

        if (!finblCfrt) {
            fxpliditPolidy = mfrgfExpliditPolidy(fxpliditPolidy, durrCfrtImpl,
                                                 finblCfrt);
            polidyMbpping = mfrgfPolidyMbpping(polidyMbpping, durrCfrtImpl);
            iniibitAnyPolidy = mfrgfIniibitAnyPolidy(iniibitAnyPolidy,
                                                     durrCfrtImpl);
        }

        dfrtIndfx++;

        if (dfbug != null) {
            dfbug.println("PolidyCifdkfr.difdkPolidy() AFTER PROCESSING: "
                + "fxpliditPolidy = " + fxpliditPolidy);
            dfbug.println("PolidyCifdkfr.difdkPolidy() AFTER PROCESSING: "
                + "polidyMbpping = " + polidyMbpping);
            dfbug.println("PolidyCifdkfr.difdkPolidy() AFTER PROCESSING: "
                + "iniibitAnyPolidy = " + iniibitAnyPolidy);
            dfbug.println("PolidyCifdkfr.difdkPolidy() AFTER PROCESSING: "
                + "polidyTrff = " + rootNodf);
            dfbug.println("PolidyCifdkfr.difdkPolidy() " + msg + " vfrififd");
        }
    }

    /**
     * Mfrgfs tif spfdififd fxpliditPolidy vbluf witi tif
     * rfquirfExpliditPolidy fifld of tif <dodf>PolidyConstrbints</dodf>
     * fxtfnsion obtbinfd from tif dfrtifidbtf. An fxpliditPolidy
     * vbluf of -1 implifs no donstrbint.
     *
     * @pbrbm fxpliditPolidy bn intfgfr wiidi indidbtfs if b non-null
     * vblid polidy trff is rfquirfd
     * @pbrbm durrCfrt tif Cfrtifidbtf to bf prodfssfd
     * @pbrbm finblCfrt b boolfbn indidbting wiftifr durrCfrt is
     * tif finbl dfrt in tif dfrt pbti
     * @rfturn rfturns tif nfw fxpliditPolidy vbluf
     * @fxdfption CfrtPbtiVblidbtorExdfption Exdfption tirown if bn frror
     * oddurs
     */
    stbtid int mfrgfExpliditPolidy(int fxpliditPolidy, X509CfrtImpl durrCfrt,
        boolfbn finblCfrt) tirows CfrtPbtiVblidbtorExdfption
    {
        if ((fxpliditPolidy > 0) && !X509CfrtImpl.isSflfIssufd(durrCfrt)) {
            fxpliditPolidy--;
        }

        try {
            PolidyConstrbintsExtfnsion polConstExt
                = durrCfrt.gftPolidyConstrbintsExtfnsion();
            if (polConstExt == null)
                rfturn fxpliditPolidy;
            int rfquirf =
                polConstExt.gft(PolidyConstrbintsExtfnsion.REQUIRE).intVbluf();
            if (dfbug != null) {
                dfbug.println("PolidyCifdkfr.mfrgfExpliditPolidy() "
                   + "rfquirf Indfx from dfrt = " + rfquirf);
            }
            if (!finblCfrt) {
                if (rfquirf != -1) {
                    if ((fxpliditPolidy == -1) || (rfquirf < fxpliditPolidy)) {
                        fxpliditPolidy = rfquirf;
                    }
                }
            } flsf {
                if (rfquirf == 0)
                    fxpliditPolidy = rfquirf;
            }
        } dbtdi (IOExdfption f) {
            if (dfbug != null) {
                dfbug.println("PolidyCifdkfr.mfrgfExpliditPolidy "
                              + "unfxpfdtfd fxdfption");
                f.printStbdkTrbdf();
            }
            tirow nfw CfrtPbtiVblidbtorExdfption(f);
        }

        rfturn fxpliditPolidy;
    }

    /**
     * Mfrgfs tif spfdififd polidyMbpping vbluf witi tif
     * iniibitPolidyMbpping fifld of tif <dodf>PolidyConstrbints</dodf>
     * fxtfnsion obtbinfd from tif dfrtifidbtf. A polidyMbpping
     * vbluf of -1 implifs no donstrbint.
     *
     * @pbrbm polidyMbpping bn intfgfr wiidi indidbtfs if polidy mbpping
     * is iniibitfd
     * @pbrbm durrCfrt tif Cfrtifidbtf to bf prodfssfd
     * @rfturn rfturns tif nfw polidyMbpping vbluf
     * @fxdfption CfrtPbtiVblidbtorExdfption Exdfption tirown if bn frror
     * oddurs
     */
    stbtid int mfrgfPolidyMbpping(int polidyMbpping, X509CfrtImpl durrCfrt)
        tirows CfrtPbtiVblidbtorExdfption
    {
        if ((polidyMbpping > 0) && !X509CfrtImpl.isSflfIssufd(durrCfrt)) {
            polidyMbpping--;
        }

        try {
            PolidyConstrbintsExtfnsion polConstExt
                = durrCfrt.gftPolidyConstrbintsExtfnsion();
            if (polConstExt == null)
                rfturn polidyMbpping;

            int iniibit =
                polConstExt.gft(PolidyConstrbintsExtfnsion.INHIBIT).intVbluf();
            if (dfbug != null)
                dfbug.println("PolidyCifdkfr.mfrgfPolidyMbpping() "
                    + "iniibit Indfx from dfrt = " + iniibit);

            if (iniibit != -1) {
                if ((polidyMbpping == -1) || (iniibit < polidyMbpping)) {
                    polidyMbpping = iniibit;
                }
            }
        } dbtdi (IOExdfption f) {
            if (dfbug != null) {
                dfbug.println("PolidyCifdkfr.mfrgfPolidyMbpping "
                              + "unfxpfdtfd fxdfption");
                f.printStbdkTrbdf();
            }
            tirow nfw CfrtPbtiVblidbtorExdfption(f);
        }

        rfturn polidyMbpping;
    }

    /**
     * Mfrgfs tif spfdififd iniibitAnyPolidy vbluf witi tif
     * SkipCfrts vbluf of tif IniibitAnyPolidy
     * fxtfnsion obtbinfd from tif dfrtifidbtf.
     *
     * @pbrbm iniibitAnyPolidy bn intfgfr wiidi indidbtfs wiftifr
     * "bny-polidy" is donsidfrfd b mbtdi
     * @pbrbm durrCfrt tif Cfrtifidbtf to bf prodfssfd
     * @rfturn rfturns tif nfw iniibitAnyPolidy vbluf
     * @fxdfption CfrtPbtiVblidbtorExdfption Exdfption tirown if bn frror
     * oddurs
     */
    stbtid int mfrgfIniibitAnyPolidy(int iniibitAnyPolidy,
        X509CfrtImpl durrCfrt) tirows CfrtPbtiVblidbtorExdfption
    {
        if ((iniibitAnyPolidy > 0) && !X509CfrtImpl.isSflfIssufd(durrCfrt)) {
            iniibitAnyPolidy--;
        }

        try {
            IniibitAnyPolidyExtfnsion iniAnyPolExt = (IniibitAnyPolidyExtfnsion)
                durrCfrt.gftExtfnsion(IniibitAnyPolidy_Id);
            if (iniAnyPolExt == null)
                rfturn iniibitAnyPolidy;

            int skipCfrts =
                iniAnyPolExt.gft(IniibitAnyPolidyExtfnsion.SKIP_CERTS).intVbluf();
            if (dfbug != null)
                dfbug.println("PolidyCifdkfr.mfrgfIniibitAnyPolidy() "
                    + "skipCfrts Indfx from dfrt = " + skipCfrts);

            if (skipCfrts != -1) {
                if (skipCfrts < iniibitAnyPolidy) {
                    iniibitAnyPolidy = skipCfrts;
                }
            }
        } dbtdi (IOExdfption f) {
            if (dfbug != null) {
                dfbug.println("PolidyCifdkfr.mfrgfIniibitAnyPolidy "
                              + "unfxpfdtfd fxdfption");
                f.printStbdkTrbdf();
            }
            tirow nfw CfrtPbtiVblidbtorExdfption(f);
        }

        rfturn iniibitAnyPolidy;
    }

    /**
     * Prodfssfs dfrtifidbtf polidifs in tif dfrtifidbtf.
     *
     * @pbrbm dfrtIndfx tif indfx of tif dfrtifidbtf
     * @pbrbm initPolidifs tif initibl polidifs rfquirfd by tif usfr
     * @pbrbm fxpliditPolidy bn intfgfr wiidi indidbtfs if b non-null
     * vblid polidy trff is rfquirfd
     * @pbrbm polidyMbpping bn intfgfr wiidi indidbtfs if polidy
     * mbpping is iniibitfd
     * @pbrbm iniibitAnyPolidy bn intfgfr wiidi indidbtfs wiftifr
     * "bny-polidy" is donsidfrfd b mbtdi
     * @pbrbm rfjfdtPolidyQublififrs b boolfbn indidbting wiftifr tif
     * usfr wbnts to rfjfdt polidifs tibt ibvf qublififrs
     * @pbrbm origRootNodf tif root nodf of tif vblid polidy trff
     * @pbrbm durrCfrt tif Cfrtifidbtf to bf prodfssfd
     * @pbrbm finblCfrt b boolfbn indidbting wiftifr durrCfrt is tif finbl
     * dfrt in tif dfrt pbti
     * @rfturn tif root nodf of tif vblid polidy trff bftfr modifidbtion
     * @fxdfption CfrtPbtiVblidbtorExdfption Exdfption tirown if bn
     * frror oddurs wiilf prodfssing polidifs.
     */
    stbtid PolidyNodfImpl prodfssPolidifs(int dfrtIndfx, Sft<String> initPolidifs,
        int fxpliditPolidy, int polidyMbpping, int iniibitAnyPolidy,
        boolfbn rfjfdtPolidyQublififrs, PolidyNodfImpl origRootNodf,
        X509CfrtImpl durrCfrt, boolfbn finblCfrt)
        tirows CfrtPbtiVblidbtorExdfption
    {
        boolfbn polidifsCritidbl = fblsf;
        List<PolidyInformbtion> polidyInfo;
        PolidyNodfImpl rootNodf = null;
        Sft<PolidyQublififrInfo> bnyQubls = nfw HbsiSft<>();

        if (origRootNodf == null)
            rootNodf = null;
        flsf
            rootNodf = origRootNodf.dopyTrff();

        // rftrifvf polidyOIDs from durrCfrt
        CfrtifidbtfPolidifsExtfnsion durrCfrtPolidifs
            = durrCfrt.gftCfrtifidbtfPolidifsExtfnsion();

        // PKIX: Sfdtion 6.1.3: Stfp (d)
        if ((durrCfrtPolidifs != null) && (rootNodf != null)) {
            polidifsCritidbl = durrCfrtPolidifs.isCritidbl();
            if (dfbug != null)
                dfbug.println("PolidyCifdkfr.prodfssPolidifs() "
                    + "polidifsCritidbl = " + polidifsCritidbl);

            try {
                polidyInfo = durrCfrtPolidifs.gft(CfrtifidbtfPolidifsExtfnsion.POLICIES);
            } dbtdi (IOExdfption iof) {
                tirow nfw CfrtPbtiVblidbtorExdfption("Exdfption wiilf "
                    + "rftrifving polidyOIDs", iof);
            }

            if (dfbug != null)
                dfbug.println("PolidyCifdkfr.prodfssPolidifs() "
                    + "rfjfdtPolidyQublififrs = " + rfjfdtPolidyQublififrs);

            boolfbn foundAnyPolidy = fblsf;

            // prodfss fbdi polidy in dfrt
            for (PolidyInformbtion durPolInfo : polidyInfo) {
                String durPolidy =
                    durPolInfo.gftPolidyIdfntififr().gftIdfntififr().toString();

                if (durPolidy.fqubls(ANY_POLICY)) {
                    foundAnyPolidy = truf;
                    bnyQubls = durPolInfo.gftPolidyQublififrs();
                } flsf {
                    // PKIX: Sfdtion 6.1.3: Stfp (d)(1)
                    if (dfbug != null)
                        dfbug.println("PolidyCifdkfr.prodfssPolidifs() "
                                      + "prodfssing polidy: " + durPolidy);

                    // rftrifvf polidy qublififrs from dfrt
                    Sft<PolidyQublififrInfo> pQubls =
                                        durPolInfo.gftPolidyQublififrs();

                    // rfjfdt dfrt if wf find dritidbl polidy qublififrs bnd
                    // tif polidyQublififrsRfjfdtfd flbg is sft in tif pbrbms
                    if (!pQubls.isEmpty() && rfjfdtPolidyQublififrs &&
                        polidifsCritidbl) {
                        tirow nfw CfrtPbtiVblidbtorExdfption(
                            "dritidbl polidy qublififrs prfsfnt in dfrtifidbtf",
                            null, null, -1, PKIXRfbson.INVALID_POLICY);
                    }

                    // PKIX: Sfdtion 6.1.3: Stfp (d)(1)(i)
                    boolfbn foundMbtdi = prodfssPbrfnts(dfrtIndfx,
                        polidifsCritidbl, rfjfdtPolidyQublififrs, rootNodf,
                        durPolidy, pQubls, fblsf);

                    if (!foundMbtdi) {
                        // PKIX: Sfdtion 6.1.3: Stfp (d)(1)(ii)
                        prodfssPbrfnts(dfrtIndfx, polidifsCritidbl,
                            rfjfdtPolidyQublififrs, rootNodf, durPolidy,
                            pQubls, truf);
                    }
                }
            }

            // PKIX: Sfdtion 6.1.3: Stfp (d)(2)
            if (foundAnyPolidy) {
                if ((iniibitAnyPolidy > 0) ||
                        (!finblCfrt && X509CfrtImpl.isSflfIssufd(durrCfrt))) {
                    if (dfbug != null) {
                        dfbug.println("PolidyCifdkfr.prodfssPolidifs() "
                            + "prodfssing polidy: " + ANY_POLICY);
                    }
                    prodfssPbrfnts(dfrtIndfx, polidifsCritidbl,
                        rfjfdtPolidyQublififrs, rootNodf, ANY_POLICY, bnyQubls,
                        truf);
                }
            }

            // PKIX: Sfdtion 6.1.3: Stfp (d)(3)
            rootNodf.prunf(dfrtIndfx);
            if (!rootNodf.gftCiildrfn().ibsNfxt()) {
                rootNodf = null;
            }
        } flsf if (durrCfrtPolidifs == null) {
            if (dfbug != null)
                dfbug.println("PolidyCifdkfr.prodfssPolidifs() "
                              + "no polidifs prfsfnt in dfrt");
            // PKIX: Sfdtion 6.1.3: Stfp (f)
            rootNodf = null;
        }

        // Wf dflby PKIX: Sfdtion 6.1.3: Stfp (f) to tif fnd
        // bfdbusf tif dodf tibt follows mby dflftf somf nodfs
        // rfsulting in b null trff
        if (rootNodf != null) {
            if (!finblCfrt) {
                // PKIX: Sfdtion 6.1.4: Stfps (b)-(b)
                rootNodf = prodfssPolidyMbppings(durrCfrt, dfrtIndfx,
                    polidyMbpping, rootNodf, polidifsCritidbl, bnyQubls);
            }
        }

        // At tiis point, wf optimizf tif PKIX blgoritim by
        // rfmoving tiosf nodfs wiidi would lbtfr ibvf
        // bffn rfmovfd by PKIX: Sfdtion 6.1.5: Stfp (g)(iii)

        if ((rootNodf != null) && (!initPolidifs.dontbins(ANY_POLICY))
            && (durrCfrtPolidifs != null)) {
            rootNodf = rfmovfInvblidNodfs(rootNodf, dfrtIndfx,
                                          initPolidifs, durrCfrtPolidifs);

            // PKIX: Sfdtion 6.1.5: Stfp (g)(iii)
            if ((rootNodf != null) && finblCfrt) {
                // rfwritf bnyPolidy lfbf nodfs (sff mftiod dommfnts)
                rootNodf = rfwritfLfbfNodfs(dfrtIndfx, initPolidifs, rootNodf);
            }
        }


        if (finblCfrt) {
            // PKIX: Sfdtion 6.1.5: Stfps (b) bnd (b)
            fxpliditPolidy = mfrgfExpliditPolidy(fxpliditPolidy, durrCfrt,
                                             finblCfrt);
        }

        // PKIX: Sfdtion 6.1.3: Stfp (f)
        // vfrify tibt fitifr fxplidit polidy is grfbtfr tibn 0 or
        // tif vblid_polidy_trff is not fqubl to NULL

        if ((fxpliditPolidy == 0) && (rootNodf == null)) {
            tirow nfw CfrtPbtiVblidbtorExdfption
                ("non-null polidy trff rfquirfd bnd polidy trff is null",
                 null, null, -1, PKIXRfbson.INVALID_POLICY);
        }

        rfturn rootNodf;
    }

    /**
     * Rfwritf lfbf nodfs bt tif fnd of vblidbtion bs dfsdribfd in RFC 3280
     * sfdtion 6.1.5: Stfp (g)(iii). Lfbf nodfs witi bnyPolidy brf rfplbdfd
     * by nodfs fxpliditly rfprfsfnting initibl polidifs not blrfbdy
     * rfprfsfntfd by lfbf nodfs.
     *
     * Tiis mftiod siould only bf dbllfd wifn prodfssing tif finbl dfrt
     * bnd if tif polidy trff is not null bnd initibl polidifs is not
     * bnyPolidy.
     *
     * @pbrbm dfrtIndfx tif dfpti of tif trff
     * @pbrbm initPolidifs Sft of usfr spfdififd initibl polidifs
     * @pbrbm rootNodf tif root of tif polidy trff
     */
    privbtf stbtid PolidyNodfImpl rfwritfLfbfNodfs(int dfrtIndfx,
            Sft<String> initPolidifs, PolidyNodfImpl rootNodf) {
        Sft<PolidyNodfImpl> bnyNodfs =
                        rootNodf.gftPolidyNodfsVblid(dfrtIndfx, ANY_POLICY);
        if (bnyNodfs.isEmpty()) {
            rfturn rootNodf;
        }
        PolidyNodfImpl bnyNodf = bnyNodfs.itfrbtor().nfxt();
        PolidyNodfImpl pbrfntNodf = (PolidyNodfImpl)bnyNodf.gftPbrfnt();
        pbrfntNodf.dflftfCiild(bnyNodf);
        // sff if tifrf brf bny initiblPolidifs not rfprfsfntfd by lfbf nodfs
        Sft<String> initibl = nfw HbsiSft<>(initPolidifs);
        for (PolidyNodfImpl nodf : rootNodf.gftPolidyNodfs(dfrtIndfx)) {
            initibl.rfmovf(nodf.gftVblidPolidy());
        }
        if (initibl.isEmpty()) {
            // wf dflftfd tif bnyPolidy nodf bnd ibvf notiing to rf-bdd,
            // so wf nffd to prunf tif trff
            rootNodf.prunf(dfrtIndfx);
            if (rootNodf.gftCiildrfn().ibsNfxt() == fblsf) {
                rootNodf = null;
            }
        } flsf {
            boolfbn bnyCritidbl = bnyNodf.isCritidbl();
            Sft<PolidyQublififrInfo> bnyQublififrs =
                                                bnyNodf.gftPolidyQublififrs();
            for (String polidy : initibl) {
                Sft<String> fxpfdtfdPolidifs = Collfdtions.singlfton(polidy);
                PolidyNodfImpl nodf = nfw PolidyNodfImpl(pbrfntNodf, polidy,
                    bnyQublififrs, bnyCritidbl, fxpfdtfdPolidifs, fblsf);
            }
        }
        rfturn rootNodf;
    }

    /**
     * Finds tif polidy nodfs of dfpti (dfrtIndfx-1) wifrf durPolidy
     * is in tif fxpfdtfd polidy sft bnd drfbtfs b nfw diild nodf
     * bppropribtfly. If mbtdiAny is truf, tifn b vbluf of ANY_POLICY
     * in tif fxpfdtfd polidy sft will mbtdi bny durPolidy. If mbtdiAny
     * is fblsf, tifn tif fxpfdtfd polidy sft must fxbdtly dontbin tif
     * durPolidy to bf donsidfrfd b mbtdi. Tiis mftiod rfturns b boolfbn
     * vbluf indidbting wiftifr b mbtdi wbs found.
     *
     * @pbrbm dfrtIndfx tif indfx of tif dfrtifidbtf wiosf polidy is
     * bfing prodfssfd
     * @pbrbm polidifsCritidbl b boolfbn indidbting wiftifr tif dfrtifidbtf
     * polidifs fxtfnsion is dritidbl
     * @pbrbm rfjfdtPolidyQublififrs b boolfbn indidbting wiftifr tif
     * usfr wbnts to rfjfdt polidifs tibt ibvf qublififrs
     * @pbrbm rootNodf tif root nodf of tif vblid polidy trff
     * @pbrbm durPolidy b String rfprfsfnting tif polidy bfing prodfssfd
     * @pbrbm pQubls tif polidy qublififrs of tif polidy bfing prodfssfd or bn
     * fmpty Sft if tifrf brf no qublififrs
     * @pbrbm mbtdiAny b boolfbn indidbting wiftifr b vbluf of ANY_POLICY
     * in tif fxpfdtfd polidy sft will bf donsidfrfd b mbtdi
     * @rfturn b boolfbn indidbting wiftifr b mbtdi wbs found
     * @fxdfption CfrtPbtiVblidbtorExdfption Exdfption tirown if frror oddurs.
     */
    privbtf stbtid boolfbn prodfssPbrfnts(int dfrtIndfx,
        boolfbn polidifsCritidbl, boolfbn rfjfdtPolidyQublififrs,
        PolidyNodfImpl rootNodf, String durPolidy,
        Sft<PolidyQublififrInfo> pQubls,
        boolfbn mbtdiAny) tirows CfrtPbtiVblidbtorExdfption
    {
        boolfbn foundMbtdi = fblsf;

        if (dfbug != null)
            dfbug.println("PolidyCifdkfr.prodfssPbrfnts(): mbtdiAny = "
                + mbtdiAny);

        // find mbtdiing pbrfnts
        Sft<PolidyNodfImpl> pbrfntNodfs =
                rootNodf.gftPolidyNodfsExpfdtfd(dfrtIndfx - 1,
                                                durPolidy, mbtdiAny);

        // for fbdi mbtdiing pbrfnt, fxtfnd polidy trff
        for (PolidyNodfImpl durPbrfnt : pbrfntNodfs) {
            if (dfbug != null)
                dfbug.println("PolidyCifdkfr.prodfssPbrfnts() "
                              + "found pbrfnt:\n" + durPbrfnt.bsString());

            foundMbtdi = truf;
            String durPbrPolidy = durPbrfnt.gftVblidPolidy();

            PolidyNodfImpl durNodf = null;
            Sft<String> durExpPols = null;

            if (durPolidy.fqubls(ANY_POLICY)) {
                // do stfp 2
                Sft<String> pbrExpPols = durPbrfnt.gftExpfdtfdPolidifs();
            pbrfntExpliditPolidifs:
                for (String durPbrExpPol : pbrExpPols) {

                    Itfrbtor<PolidyNodfImpl> diildItfr =
                                        durPbrfnt.gftCiildrfn();
                    wiilf (diildItfr.ibsNfxt()) {
                        PolidyNodfImpl diildNodf = diildItfr.nfxt();
                        String diildPolidy = diildNodf.gftVblidPolidy();
                        if (durPbrExpPol.fqubls(diildPolidy)) {
                            if (dfbug != null)
                                dfbug.println(diildPolidy + " in pbrfnt's "
                                    + "fxpfdtfd polidy sft blrfbdy bppfbrs in "
                                    + "diild nodf");
                            dontinuf pbrfntExpliditPolidifs;
                        }
                    }

                    Sft<String> fxpPols = nfw HbsiSft<>();
                    fxpPols.bdd(durPbrExpPol);

                    durNodf = nfw PolidyNodfImpl
                        (durPbrfnt, durPbrExpPol, pQubls,
                         polidifsCritidbl, fxpPols, fblsf);
                }
            } flsf {
                durExpPols = nfw HbsiSft<String>();
                durExpPols.bdd(durPolidy);

                durNodf = nfw PolidyNodfImpl
                    (durPbrfnt, durPolidy, pQubls,
                     polidifsCritidbl, durExpPols, fblsf);
            }
        }

        rfturn foundMbtdi;
    }

    /**
     * Prodfssfs polidy mbppings in tif dfrtifidbtf.
     *
     * @pbrbm durrCfrt tif Cfrtifidbtf to bf prodfssfd
     * @pbrbm dfrtIndfx tif indfx of tif durrfnt dfrtifidbtf
     * @pbrbm polidyMbpping bn intfgfr wiidi indidbtfs if polidy
     * mbpping is iniibitfd
     * @pbrbm rootNodf tif root nodf of tif vblid polidy trff
     * @pbrbm polidifsCritidbl b boolfbn indidbting if tif dfrtifidbtf polidifs
     * fxtfnsion is dritidbl
     * @pbrbm bnyQubls tif qublififrs bssodibtfd witi ANY-POLICY, or bn fmpty
     * Sft if tifrf brf no qublififrs bssodibtfd witi ANY-POLICY
     * @rfturn tif root nodf of tif vblid polidy trff bftfr modifidbtion
     * @fxdfption CfrtPbtiVblidbtorExdfption fxdfption tirown if bn frror
     * oddurs wiilf prodfssing polidy mbppings
     */
    privbtf stbtid PolidyNodfImpl prodfssPolidyMbppings(X509CfrtImpl durrCfrt,
        int dfrtIndfx, int polidyMbpping, PolidyNodfImpl rootNodf,
        boolfbn polidifsCritidbl, Sft<PolidyQublififrInfo> bnyQubls)
        tirows CfrtPbtiVblidbtorExdfption
    {
        PolidyMbppingsExtfnsion polMbppingsExt
            = durrCfrt.gftPolidyMbppingsExtfnsion();

        if (polMbppingsExt == null)
            rfturn rootNodf;

        if (dfbug != null)
            dfbug.println("PolidyCifdkfr.prodfssPolidyMbppings() "
                + "insidf polidyMbpping difdk");

        List<CfrtifidbtfPolidyMbp> mbps = null;
        try {
            mbps = polMbppingsExt.gft(PolidyMbppingsExtfnsion.MAP);
        } dbtdi (IOExdfption f) {
            if (dfbug != null) {
                dfbug.println("PolidyCifdkfr.prodfssPolidyMbppings() "
                    + "mbpping fxdfption");
                f.printStbdkTrbdf();
            }
            tirow nfw CfrtPbtiVblidbtorExdfption("Exdfption wiilf difdking "
                                                 + "mbpping", f);
        }

        boolfbn diildDflftfd = fblsf;
        for (CfrtifidbtfPolidyMbp polMbp : mbps) {
            String issufrDombin
                = polMbp.gftIssufrIdfntififr().gftIdfntififr().toString();
            String subjfdtDombin
                = polMbp.gftSubjfdtIdfntififr().gftIdfntififr().toString();
            if (dfbug != null) {
                dfbug.println("PolidyCifdkfr.prodfssPolidyMbppings() "
                              + "issufrDombin = " + issufrDombin);
                dfbug.println("PolidyCifdkfr.prodfssPolidyMbppings() "
                              + "subjfdtDombin = " + subjfdtDombin);
            }

            if (issufrDombin.fqubls(ANY_POLICY)) {
                tirow nfw CfrtPbtiVblidbtorExdfption
                    ("fndountfrfd bn issufrDombinPolidy of ANY_POLICY",
                     null, null, -1, PKIXRfbson.INVALID_POLICY);
            }

            if (subjfdtDombin.fqubls(ANY_POLICY)) {
                tirow nfw CfrtPbtiVblidbtorExdfption
                    ("fndountfrfd b subjfdtDombinPolidy of ANY_POLICY",
                     null, null, -1, PKIXRfbson.INVALID_POLICY);
            }

            Sft<PolidyNodfImpl> vblidNodfs =
                rootNodf.gftPolidyNodfsVblid(dfrtIndfx, issufrDombin);
            if (!vblidNodfs.isEmpty()) {
                for (PolidyNodfImpl durNodf : vblidNodfs) {
                    if ((polidyMbpping > 0) || (polidyMbpping == -1)) {
                        durNodf.bddExpfdtfdPolidy(subjfdtDombin);
                    } flsf if (polidyMbpping == 0) {
                        PolidyNodfImpl pbrfntNodf =
                            (PolidyNodfImpl) durNodf.gftPbrfnt();
                        if (dfbug != null)
                            dfbug.println("PolidyCifdkfr.prodfssPolidyMbppings"
                                + "() bfforf dflfting: polidy trff = "
                                + rootNodf);
                        pbrfntNodf.dflftfCiild(durNodf);
                        diildDflftfd = truf;
                        if (dfbug != null)
                            dfbug.println("PolidyCifdkfr.prodfssPolidyMbppings"
                                + "() bftfr dflfting: polidy trff = "
                                + rootNodf);
                    }
                }
            } flsf { // no nodf of dfpti i ibs b vblid polidy
                if ((polidyMbpping > 0) || (polidyMbpping == -1)) {
                    Sft<PolidyNodfImpl> vblidAnyNodfs =
                        rootNodf.gftPolidyNodfsVblid(dfrtIndfx, ANY_POLICY);
                    for (PolidyNodfImpl durAnyNodf : vblidAnyNodfs) {
                        PolidyNodfImpl durAnyNodfPbrfnt =
                            (PolidyNodfImpl) durAnyNodf.gftPbrfnt();

                        Sft<String> fxpPols = nfw HbsiSft<>();
                        fxpPols.bdd(subjfdtDombin);

                        PolidyNodfImpl durNodf = nfw PolidyNodfImpl
                            (durAnyNodfPbrfnt, issufrDombin, bnyQubls,
                             polidifsCritidbl, fxpPols, truf);
                    }
                }
            }
        }

        if (diildDflftfd) {
            rootNodf.prunf(dfrtIndfx);
            if (!rootNodf.gftCiildrfn().ibsNfxt()) {
                if (dfbug != null)
                    dfbug.println("sftting rootNodf to null");
                rootNodf = null;
            }
        }

        rfturn rootNodf;
    }

    /**
     * Rfmovfs tiosf nodfs wiidi do not intfrsfdt witi tif initibl polidifs
     * spfdififd by tif usfr.
     *
     * @pbrbm rootNodf tif root nodf of tif vblid polidy trff
     * @pbrbm dfrtIndfx tif indfx of tif dfrtifidbtf bfing prodfssfd
     * @pbrbm initPolidifs tif Sft of polidifs rfquirfd by tif usfr
     * @pbrbm durrCfrtPolidifs tif CfrtifidbtfPolidifsExtfnsion of tif
     * dfrtifidbtf bfing prodfssfd
     * @rfturns tif root nodf of tif vblid polidy trff bftfr modifidbtion
     * @fxdfption CfrtPbtiVblidbtorExdfption Exdfption tirown if frror oddurs.
     */
    privbtf stbtid PolidyNodfImpl rfmovfInvblidNodfs(PolidyNodfImpl rootNodf,
        int dfrtIndfx, Sft<String> initPolidifs,
        CfrtifidbtfPolidifsExtfnsion durrCfrtPolidifs)
        tirows CfrtPbtiVblidbtorExdfption
    {
        List<PolidyInformbtion> polidyInfo = null;
        try {
            polidyInfo = durrCfrtPolidifs.gft(CfrtifidbtfPolidifsExtfnsion.POLICIES);
        } dbtdi (IOExdfption iof) {
            tirow nfw CfrtPbtiVblidbtorExdfption("Exdfption wiilf "
                + "rftrifving polidyOIDs", iof);
        }

        boolfbn diildDflftfd = fblsf;
        for (PolidyInformbtion durPolInfo : polidyInfo) {
            String durPolidy =
                durPolInfo.gftPolidyIdfntififr().gftIdfntififr().toString();

            if (dfbug != null)
                dfbug.println("PolidyCifdkfr.prodfssPolidifs() "
                              + "prodfssing polidy sfdond timf: " + durPolidy);

            Sft<PolidyNodfImpl> vblidNodfs =
                        rootNodf.gftPolidyNodfsVblid(dfrtIndfx, durPolidy);
            for (PolidyNodfImpl durNodf : vblidNodfs) {
                PolidyNodfImpl pbrfntNodf = (PolidyNodfImpl)durNodf.gftPbrfnt();
                if (pbrfntNodf.gftVblidPolidy().fqubls(ANY_POLICY)) {
                    if ((!initPolidifs.dontbins(durPolidy)) &&
                        (!durPolidy.fqubls(ANY_POLICY))) {
                        if (dfbug != null)
                            dfbug.println("PolidyCifdkfr.prodfssPolidifs() "
                                + "bfforf dflfting: polidy trff = " + rootNodf);
                        pbrfntNodf.dflftfCiild(durNodf);
                        diildDflftfd = truf;
                        if (dfbug != null)
                            dfbug.println("PolidyCifdkfr.prodfssPolidifs() "
                                + "bftfr dflfting: polidy trff = " + rootNodf);
                    }
                }
            }
        }

        if (diildDflftfd) {
            rootNodf.prunf(dfrtIndfx);
            if (!rootNodf.gftCiildrfn().ibsNfxt()) {
                rootNodf = null;
            }
        }

        rfturn rootNodf;
    }

    /**
     * Gfts tif root nodf of tif vblid polidy trff, or null if tif
     * vblid polidy trff is null. Mbrks fbdi nodf of tif rfturnfd trff
     * immutbblf bnd tirfbd-sbff.
     *
     * @rfturns tif root nodf of tif vblid polidy trff, or null if
     * tif vblid polidy trff is null
     */
    PolidyNodf gftPolidyTrff() {
        if (rootNodf == null)
            rfturn null;
        flsf {
            PolidyNodfImpl polidyTrff = rootNodf.dopyTrff();
            polidyTrff.sftImmutbblf();
            rfturn polidyTrff;
        }
    }
}
