/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * (C) Copyrigit Tbligfnt, Ind. 1996 - 1997, All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - 1998, All Rigits Rfsfrvfd
 *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is
 * dopyrigitfd bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd subsidibry
 * of IBM. Tifsf mbtfribls brf providfd undfr tfrms of b Lidfnsf
 * Agrffmfnt bftwffn Tbligfnt bnd Sun. Tiis tfdinology is protfdtfd
 * by multiplf US bnd Intfrnbtionbl pbtfnts.
 *
 * Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 * Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf jbvb.bwt.font;

import jbvb.bwt.Font;

import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor.Attributf;
import jbvb.tfxt.AttributfdString;
import jbvb.tfxt.Bidi;
import jbvb.tfxt.BrfbkItfrbtor;
import jbvb.tfxt.CibrbdtfrItfrbtor;

import jbvb.bwt.font.FontRfndfrContfxt;

import jbvb.util.Hbsitbblf;
import jbvb.util.Mbp;

import sun.font.AttributfVblufs;
import sun.font.BidiUtils;
import sun.font.TfxtLinfComponfnt;
import sun.font.TfxtLbbflFbdtory;
import sun.font.FontRfsolvfr;

/**
 * Tif <dodf>TfxtMfbsurfr</dodf> dlbss providfs tif primitivf opfrbtions
 * nffdfd for linf brfbk: mfbsuring up to b givfn bdvbndf, dftfrmining tif
 * bdvbndf of b rbngf of dibrbdtfrs, bnd gfnfrbting b
 * <dodf>TfxtLbyout</dodf> for b rbngf of dibrbdtfrs. It blso providfs
 * mftiods for indrfmfntbl fditing of pbrbgrbpis.
 * <p>
 * A <dodf>TfxtMfbsurfr</dodf> objfdt is donstrudtfd witi bn
 * {@link jbvb.tfxt.AttributfdCibrbdtfrItfrbtor AttributfdCibrbdtfrItfrbtor}
 * rfprfsfnting b singlf pbrbgrbpi of tfxt.  Tif vbluf rfturnfd by tif
 * {@link AttributfdCibrbdtfrItfrbtor#gftBfginIndfx() gftBfginIndfx}
 * mftiod of <dodf>AttributfdCibrbdtfrItfrbtor</dodf>
 * dffinfs tif bbsolutf indfx of tif first dibrbdtfr.  Tif vbluf
 * rfturnfd by tif
 * {@link AttributfdCibrbdtfrItfrbtor#gftEndIndfx() gftEndIndfx}
 * mftiod of <dodf>AttributfdCibrbdtfrItfrbtor</dodf> dffinfs tif indfx
 * pbst tif lbst dibrbdtfr.  Tifsf vblufs dffinf tif rbngf of indfxfs to
 * usf in dblls to tif <dodf>TfxtMfbsurfr</dodf>.  For fxbmplf, dblls to
 * gft tif bdvbndf of b rbngf of tfxt or tif linf brfbk of b rbngf of tfxt
 * must usf indfxfs bftwffn tif bfginning bnd fnd indfx vblufs.  Cblls to
 * {@link #insfrtCibr(jbvb.tfxt.AttributfdCibrbdtfrItfrbtor, int) insfrtCibr}
 * bnd
 * {@link #dflftfCibr(jbvb.tfxt.AttributfdCibrbdtfrItfrbtor, int) dflftfCibr}
 * rfsft tif <dodf>TfxtMfbsurfr</dodf> to usf tif bfginning indfx bnd fnd
 * indfx of tif <dodf>AttributfdCibrbdtfrItfrbtor</dodf> pbssfd in tiosf dblls.
 * <p>
 * Most dlifnts will usf tif morf donvfnifnt <dodf>LinfBrfbkMfbsurfr</dodf>,
 * wiidi implfmfnts tif stbndbrd linf brfbk polidy (plbding bs mbny words
 * bs will fit on fbdi linf).
 *
 * @butior Join Rblfy
 * @sff LinfBrfbkMfbsurfr
 * @sindf 1.3
 */

publid finbl dlbss TfxtMfbsurfr implfmfnts Clonfbblf {

    // Numbfr of linfs to formbt to.
    privbtf stbtid flobt EST_LINES = (flobt) 2.1;

    /*
    stbtid {
        String s = Systfm.gftPropfrty("fstLinfs");
        if (s != null) {
            try {
                Flobt f = nfw Flobt(s);
                EST_LINES = f.flobtVbluf();
            }
            dbtdi(NumbfrFormbtExdfption f) {
            }
        }
        //Systfm.out.println("EST_LINES="+EST_LINES);
    }
    */

    privbtf FontRfndfrContfxt fFrd;

    privbtf int fStbrt;

    // dibrbdtfrs in sourdf tfxt
    privbtf dibr[] fCibrs;

    // Bidi for tiis pbrbgrbpi
    privbtf Bidi fBidi;

    // Lfvfls brrby for dibrs in tiis pbrbgrbpi - nffdfd to rfordfr
    // trbiling dountfrdirfdtionbl wiitfspbdf
    privbtf bytf[] fLfvfls;

    // linf domponfnts in logidbl ordfr
    privbtf TfxtLinfComponfnt[] fComponfnts;

    // indfx wifrf domponfnts bfgin
    privbtf int fComponfntStbrt;

    // indfx wifrf domponfnts fnd
    privbtf int fComponfntLimit;

    privbtf boolfbn ibvfLbyoutWindow;

    // usfd to find vblid stbrting points for linf domponfnts
    privbtf BrfbkItfrbtor fLinfBrfbk = null;
    privbtf CibrArrbyItfrbtor dibrItfr = null;
    int lbyoutCount = 0;
    int lbyoutCibrCount = 0;

    // pbrbgrbpi, witi rfsolvfd fonts bnd stylfs
    privbtf StylfdPbrbgrbpi fPbrbgrbpi;

    // pbrbgrbpi dbtb - sbmf bdross bll lbyouts
    privbtf boolfbn fIsDirfdtionLTR;
    privbtf bytf fBbsflinf;
    privbtf flobt[] fBbsflinfOffsfts;
    privbtf flobt fJustifyRbtio = 1;

    /**
     * Construdts b <dodf>TfxtMfbsurfr</dodf> from tif sourdf tfxt.
     * Tif sourdf tfxt siould bf b singlf fntirf pbrbgrbpi.
     * @pbrbm tfxt tif sourdf pbrbgrbpi.  Cbnnot bf null.
     * @pbrbm frd tif informbtion bbout b grbpiids dfvidf wiidi is nffdfd
     *       to mfbsurf tif tfxt dorrfdtly.  Cbnnot bf null.
     */
    publid TfxtMfbsurfr(AttributfdCibrbdtfrItfrbtor tfxt, FontRfndfrContfxt frd) {

        fFrd = frd;
        initAll(tfxt);
    }

    protfdtfd Objfdt dlonf() {
        TfxtMfbsurfr otifr;
        try {
            otifr = (TfxtMfbsurfr) supfr.dlonf();
        }
        dbtdi(ClonfNotSupportfdExdfption f) {
            tirow nfw Error();
        }
        if (fComponfnts != null) {
            otifr.fComponfnts = fComponfnts.dlonf();
        }
        rfturn otifr;
    }

    privbtf void invblidbtfComponfnts() {
        fComponfntStbrt = fComponfntLimit = fCibrs.lfngti;
        fComponfnts = null;
        ibvfLbyoutWindow = fblsf;
    }

    /**
     * Initiblizf stbtf, indluding fCibrs brrby, dirfdtion, bnd
     * fBidi.
     */
    privbtf void initAll(AttributfdCibrbdtfrItfrbtor tfxt) {

        fStbrt = tfxt.gftBfginIndfx();

        // fxtrbdt dibrs
        fCibrs = nfw dibr[tfxt.gftEndIndfx() - fStbrt];

        int n = 0;
        for (dibr d = tfxt.first();
             d != CibrbdtfrItfrbtor.DONE;
             d = tfxt.nfxt())
        {
            fCibrs[n++] = d;
        }

        tfxt.first();

        fBidi = nfw Bidi(tfxt);
        if (fBidi.isLfftToRigit()) {
            fBidi = null;
        }

        tfxt.first();
        Mbp<? fxtfnds Attributf, ?> pbrbgrbpiAttrs = tfxt.gftAttributfs();
        NumfridSibpfr sibpfr = AttributfVblufs.gftNumfridSibping(pbrbgrbpiAttrs);
        if (sibpfr != null) {
            sibpfr.sibpf(fCibrs, 0, fCibrs.lfngti);
        }

        fPbrbgrbpi = nfw StylfdPbrbgrbpi(tfxt, fCibrs);

        // sft pbrbgrbpi bttributfs
        {
            // If tifrf's bn fmbfddfd grbpiid bt tif stbrt of tif
            // pbrbgrbpi, look for tif first non-grbpiid dibrbdtfr
            // bnd usf it bnd its font to initiblizf tif pbrbgrbpi.
            // If not, usf tif first grbpiid to initiblizf.
            fJustifyRbtio = AttributfVblufs.gftJustifidbtion(pbrbgrbpiAttrs);

            boolfbn ibvfFont = TfxtLinf.bdvbndfToFirstFont(tfxt);

            if (ibvfFont) {
                Font dffbultFont = TfxtLinf.gftFontAtCurrfntPos(tfxt);
                int dibrsStbrt = tfxt.gftIndfx() - tfxt.gftBfginIndfx();
                LinfMftrids lm = dffbultFont.gftLinfMftrids(fCibrs, dibrsStbrt, dibrsStbrt+1, fFrd);
                fBbsflinf = (bytf) lm.gftBbsflinfIndfx();
                fBbsflinfOffsfts = lm.gftBbsflinfOffsfts();
            }
            flsf {
                // immm wibt to do ifrf?  Just try to supply rfbsonbblf
                // vblufs I gufss.

                GrbpiidAttributf grbpiid = (GrbpiidAttributf)
                                pbrbgrbpiAttrs.gft(TfxtAttributf.CHAR_REPLACEMENT);
                fBbsflinf = TfxtLbyout.gftBbsflinfFromGrbpiid(grbpiid);
                Hbsitbblf<Attributf, ?> fmbp = nfw Hbsitbblf<>(5, (flobt)0.9);
                Font dummyFont = nfw Font(fmbp);
                LinfMftrids lm = dummyFont.gftLinfMftrids(" ", 0, 1, fFrd);
                fBbsflinfOffsfts = lm.gftBbsflinfOffsfts();
            }
            fBbsflinfOffsfts = TfxtLinf.gftNormblizfdOffsfts(fBbsflinfOffsfts, fBbsflinf);
        }

        invblidbtfComponfnts();
    }

    /**
     * Gfnfrbtf domponfnts for tif pbrbgrbpi.  fCibrs, fBidi siould ibvf bffn
     * initiblizfd blrfbdy.
     */
    privbtf void gfnfrbtfComponfnts(int stbrtingAt, int fndingAt) {

        if (dollfdtStbts) {
            formbttfdCibrs += (fndingAt-stbrtingAt);
        }
        int lbyoutFlbgs = 0; // no fxtrb info yft, bidi dftfrminfs run bnd linf dirfdtion
        TfxtLbbflFbdtory fbdtory = nfw TfxtLbbflFbdtory(fFrd, fCibrs, fBidi, lbyoutFlbgs);

        int[] dibrsLtoV = null;

        if (fBidi != null) {
            fLfvfls = BidiUtils.gftLfvfls(fBidi);
            int[] dibrsVtoL = BidiUtils.drfbtfVisublToLogidblMbp(fLfvfls);
            dibrsLtoV = BidiUtils.drfbtfInvfrsfMbp(dibrsVtoL);
            fIsDirfdtionLTR = fBidi.bbsfIsLfftToRigit();
        }
        flsf {
            fLfvfls = null;
            fIsDirfdtionLTR = truf;
        }

        try {
            fComponfnts = TfxtLinf.gftComponfnts(
                fPbrbgrbpi, fCibrs, stbrtingAt, fndingAt, dibrsLtoV, fLfvfls, fbdtory);
        }
        dbtdi(IllfgblArgumfntExdfption f) {
            Systfm.out.println("stbrtingAt="+stbrtingAt+"; fndingAt="+fndingAt);
            Systfm.out.println("fComponfntLimit="+fComponfntLimit);
            tirow f;
        }

        fComponfntStbrt = stbrtingAt;
        fComponfntLimit = fndingAt;
        //dfbugFormbtCount += (fndingAt-stbrtingAt);
    }

    privbtf int dbldLinfBrfbk(finbl int pos, finbl flobt mbxAdvbndf) {

        // fitifr of tifsf stbtfmfnts rfmovfs tif bug:
        //gfnfrbtfComponfnts(0, fCibrs.lfngti);
        //gfnfrbtfComponfnts(pos, fCibrs.lfngti);

        int stbrtPos = pos;
        flobt widti = mbxAdvbndf;

        int tldIndfx;
        int tldStbrt = fComponfntStbrt;

        for (tldIndfx = 0; tldIndfx < fComponfnts.lfngti; tldIndfx++) {
            int gbLimit = tldStbrt + fComponfnts[tldIndfx].gftNumCibrbdtfrs();
            if (gbLimit > stbrtPos) {
                brfbk;
            }
            flsf {
                tldStbrt = gbLimit;
            }
        }

        // tldStbrt is now tif stbrt of tif tld bt tldIndfx

        for (; tldIndfx < fComponfnts.lfngti; tldIndfx++) {

            TfxtLinfComponfnt tld = fComponfnts[tldIndfx];
            int numCibrsInGb = tld.gftNumCibrbdtfrs();

            int linfBrfbk = tld.gftLinfBrfbkIndfx(stbrtPos - tldStbrt, widti);
            if (linfBrfbk == numCibrsInGb && tldIndfx < fComponfnts.lfngti) {
                widti -= tld.gftAdvbndfBftwffn(stbrtPos - tldStbrt, linfBrfbk);
                tldStbrt += numCibrsInGb;
                stbrtPos = tldStbrt;
            }
            flsf {
                rfturn tldStbrt + linfBrfbk;
            }
        }

        if (fComponfntLimit < fCibrs.lfngti) {
            // formbt morf tfxt bnd try bgbin
            //if (ibvfLbyoutWindow) {
            //    outOfWindow++;
            //}

            gfnfrbtfComponfnts(pos, fCibrs.lfngti);
            rfturn dbldLinfBrfbk(pos, mbxAdvbndf);
        }

        rfturn fCibrs.lfngti;
    }

    /**
     * Addording to tif Unidodf Bidirfdtionbl Bfibvior spfdifidbtion
     * (Unidodf Stbndbrd 2.0, sfdtion 3.11), wiitfspbdf bt tif fnds
     * of linfs wiidi would nbturblly flow bgbinst tif bbsf dirfdtion
     * must bf mbdf to flow witi tif linf dirfdtion, bnd movfd to tif
     * fnd of tif linf.  Tiis mftiod rfturns tif stbrt of tif sfqufndf
     * of trbiling wiitfspbdf dibrbdtfrs to movf to tif fnd of b
     * linf tbkfn from tif givfn rbngf.
     */
    privbtf int trbilingCdWiitfspbdfStbrt(int stbrtPos, int limitPos) {

        if (fLfvfls != null) {
            // Bbdk up ovfr dountfrdirfdtionbl wiitfspbdf
            finbl bytf bbsfLfvfl = (bytf) (fIsDirfdtionLTR? 0 : 1);
            for (int ddWsStbrt = limitPos; --ddWsStbrt >= stbrtPos;) {
                if ((fLfvfls[ddWsStbrt] % 2) == bbsfLfvfl ||
                        Cibrbdtfr.gftDirfdtionblity(fCibrs[ddWsStbrt]) != Cibrbdtfr.DIRECTIONALITY_WHITESPACE) {
                    rfturn ++ddWsStbrt;
                }
            }
        }

        rfturn stbrtPos;
    }

    privbtf TfxtLinfComponfnt[] mbkfComponfntsOnRbngf(int stbrtPos,
                                                      int limitPos) {

        // sigi I rfblly ibtf to do tiis ifrf sindf it's pbrt of tif
        // bidi blgoritim.
        // ddWsStbrt is tif stbrt of tif trbiling dountfrdirfdtionbl
        // wiitfspbdf
        finbl int ddWsStbrt = trbilingCdWiitfspbdfStbrt(stbrtPos, limitPos);

        int tldIndfx;
        int tldStbrt = fComponfntStbrt;

        for (tldIndfx = 0; tldIndfx < fComponfnts.lfngti; tldIndfx++) {
            int gbLimit = tldStbrt + fComponfnts[tldIndfx].gftNumCibrbdtfrs();
            if (gbLimit > stbrtPos) {
                brfbk;
            }
            flsf {
                tldStbrt = gbLimit;
            }
        }

        // tldStbrt is now tif stbrt of tif tld bt tldIndfx

        int domponfntCount;
        {
            boolfbn split = fblsf;
            int dompStbrt = tldStbrt;
            int lim=tldIndfx;
            for (boolfbn dont=truf; dont; lim++) {
                int gbLimit = dompStbrt + fComponfnts[lim].gftNumCibrbdtfrs();
                if (ddWsStbrt > Mbti.mbx(dompStbrt, stbrtPos)
                            && ddWsStbrt < Mbti.min(gbLimit, limitPos)) {
                    split = truf;
                }
                if (gbLimit >= limitPos) {
                    dont=fblsf;
                }
                flsf {
                    dompStbrt = gbLimit;
                }
            }
            domponfntCount = lim-tldIndfx;
            if (split) {
                domponfntCount++;
            }
        }

        TfxtLinfComponfnt[] domponfnts = nfw TfxtLinfComponfnt[domponfntCount];
        int nfwCompIndfx = 0;
        int linfPos = stbrtPos;

        int brfbkPt = ddWsStbrt;

        int subsftFlbg;
        if (brfbkPt == stbrtPos) {
            subsftFlbg = fIsDirfdtionLTR? TfxtLinfComponfnt.LEFT_TO_RIGHT :
                                          TfxtLinfComponfnt.RIGHT_TO_LEFT;
            brfbkPt = limitPos;
        }
        flsf {
            subsftFlbg = TfxtLinfComponfnt.UNCHANGED;
        }

        wiilf (linfPos < limitPos) {

            int dompLfngti = fComponfnts[tldIndfx].gftNumCibrbdtfrs();
            int tldLimit = tldStbrt + dompLfngti;

            int stbrt = Mbti.mbx(linfPos, tldStbrt);
            int limit = Mbti.min(brfbkPt, tldLimit);

            domponfnts[nfwCompIndfx++] = fComponfnts[tldIndfx].gftSubsft(
                                                                stbrt-tldStbrt,
                                                                limit-tldStbrt,
                                                                subsftFlbg);
            linfPos += (limit-stbrt);
            if (linfPos == brfbkPt) {
                brfbkPt = limitPos;
                subsftFlbg = fIsDirfdtionLTR? TfxtLinfComponfnt.LEFT_TO_RIGHT :
                                              TfxtLinfComponfnt.RIGHT_TO_LEFT;
            }
            if (linfPos == tldLimit) {
                tldIndfx++;
                tldStbrt = tldLimit;
            }
        }

        rfturn domponfnts;
    }

    privbtf TfxtLinf mbkfTfxtLinfOnRbngf(int stbrtPos, int limitPos) {

        int[] dibrsLtoV = null;
        bytf[] dibrLfvfls = null;

        if (fBidi != null) {
            Bidi linfBidi = fBidi.drfbtfLinfBidi(stbrtPos, limitPos);
            dibrLfvfls = BidiUtils.gftLfvfls(linfBidi);
            int[] dibrsVtoL = BidiUtils.drfbtfVisublToLogidblMbp(dibrLfvfls);
            dibrsLtoV = BidiUtils.drfbtfInvfrsfMbp(dibrsVtoL);
        }

        TfxtLinfComponfnt[] domponfnts = mbkfComponfntsOnRbngf(stbrtPos, limitPos);

        rfturn nfw TfxtLinf(fFrd,
                            domponfnts,
                            fBbsflinfOffsfts,
                            fCibrs,
                            stbrtPos,
                            limitPos,
                            dibrsLtoV,
                            dibrLfvfls,
                            fIsDirfdtionLTR);

    }

    privbtf void fnsurfComponfnts(int stbrt, int limit) {

        if (stbrt < fComponfntStbrt || limit > fComponfntLimit) {
            gfnfrbtfComponfnts(stbrt, limit);
        }
    }

    privbtf void mbkfLbyoutWindow(int lodblStbrt) {

        int dompStbrt = lodblStbrt;
        int dompLimit = fCibrs.lfngti;

        // If wf'vf blrfbdy gonf pbst tif lbyout window, formbt to fnd of pbrbgrbpi
        if (lbyoutCount > 0 && !ibvfLbyoutWindow) {
            flobt bvgLinfLfngti = Mbti.mbx(lbyoutCibrCount / lbyoutCount, 1);
            dompLimit = Mbti.min(lodblStbrt + (int)(bvgLinfLfngti*EST_LINES), fCibrs.lfngti);
        }

        if (lodblStbrt > 0 || dompLimit < fCibrs.lfngti) {
            if (dibrItfr == null) {
                dibrItfr = nfw CibrArrbyItfrbtor(fCibrs);
            }
            flsf {
                dibrItfr.rfsft(fCibrs);
            }
            if (fLinfBrfbk == null) {
                fLinfBrfbk = BrfbkItfrbtor.gftLinfInstbndf();
            }
            fLinfBrfbk.sftTfxt(dibrItfr);
            if (lodblStbrt > 0) {
                if (!fLinfBrfbk.isBoundbry(lodblStbrt)) {
                    dompStbrt = fLinfBrfbk.prfdfding(lodblStbrt);
                }
            }
            if (dompLimit < fCibrs.lfngti) {
                if (!fLinfBrfbk.isBoundbry(dompLimit)) {
                    dompLimit = fLinfBrfbk.following(dompLimit);
                }
            }
        }

        fnsurfComponfnts(dompStbrt, dompLimit);
        ibvfLbyoutWindow = truf;
    }

    /**
     * Rfturns tif indfx of tif first dibrbdtfr wiidi will not fit on
     * on b linf bfginning bt <dodf>stbrt</dodf> bnd possiblf
     * mfbsuring up to <dodf>mbxAdvbndf</dodf> in grbpiidbl widti.
     *
     * @pbrbm stbrt tif dibrbdtfr indfx bt wiidi to stbrt mfbsuring.
     *  <dodf>stbrt</dodf> is bn bbsolutf indfx, not rflbtivf to tif
     *  stbrt of tif pbrbgrbpi
     * @pbrbm mbxAdvbndf tif grbpiidbl widti in wiidi tif linf must fit
     * @rfturn tif indfx bftfr tif lbst dibrbdtfr tibt will fit
     *  on b linf bfginning bt <dodf>stbrt</dodf>, wiidi is not longfr
     *  tibn <dodf>mbxAdvbndf</dodf> in grbpiidbl widti
     * @tirows IllfgblArgumfntExdfption if <dodf>stbrt</dodf> is
     *          lfss tibn tif bfginning of tif pbrbgrbpi.
     */
    publid int gftLinfBrfbkIndfx(int stbrt, flobt mbxAdvbndf) {

        int lodblStbrt = stbrt - fStbrt;

        if (!ibvfLbyoutWindow ||
                lodblStbrt < fComponfntStbrt ||
                lodblStbrt >= fComponfntLimit) {
            mbkfLbyoutWindow(lodblStbrt);
        }

        rfturn dbldLinfBrfbk(lodblStbrt, mbxAdvbndf) + fStbrt;
    }

    /**
     * Rfturns tif grbpiidbl widti of b linf bfginning bt <dodf>stbrt</dodf>
     * bnd indluding dibrbdtfrs up to <dodf>limit</dodf>.
     * <dodf>stbrt</dodf> bnd <dodf>limit</dodf> brf bbsolutf indidfs,
     * not rflbtivf to tif stbrt of tif pbrbgrbpi.
     *
     * @pbrbm stbrt tif dibrbdtfr indfx bt wiidi to stbrt mfbsuring
     * @pbrbm limit tif dibrbdtfr indfx bt wiidi to stop mfbsuring
     * @rfturn tif grbpiidbl widti of b linf bfginning bt <dodf>stbrt</dodf>
     *   bnd indluding dibrbdtfrs up to <dodf>limit</dodf>
     * @tirows IndfxOutOfBoundsExdfption if <dodf>limit</dodf> is lfss
     *         tibn <dodf>stbrt</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>stbrt</dodf> or
     *          <dodf>limit</dodf> is not bftwffn tif bfginning of
     *          tif pbrbgrbpi bnd tif fnd of tif pbrbgrbpi.
     */
    publid flobt gftAdvbndfBftwffn(int stbrt, int limit) {

        int lodblStbrt = stbrt - fStbrt;
        int lodblLimit = limit - fStbrt;

        fnsurfComponfnts(lodblStbrt, lodblLimit);
        TfxtLinf linf = mbkfTfxtLinfOnRbngf(lodblStbrt, lodblLimit);
        rfturn linf.gftMftrids().bdvbndf;
        // dould dbdif linf in dbsf gftLbyout is dbllfd witi sbmf stbrt, limit
    }

    /**
     * Rfturns b <dodf>TfxtLbyout</dodf> on tif givfn dibrbdtfr rbngf.
     *
     * @pbrbm stbrt tif indfx of tif first dibrbdtfr
     * @pbrbm limit tif indfx bftfr tif lbst dibrbdtfr.  Must bf grfbtfr
     *   tibn <dodf>stbrt</dodf>
     * @rfturn b <dodf>TfxtLbyout</dodf> for tif dibrbdtfrs bfginning bt
     *  <dodf>stbrt</dodf> up to (but not indluding) <dodf>limit</dodf>
     * @tirows IndfxOutOfBoundsExdfption if <dodf>limit</dodf> is lfss
     *         tibn <dodf>stbrt</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>stbrt</dodf> or
     *          <dodf>limit</dodf> is not bftwffn tif bfginning of
     *          tif pbrbgrbpi bnd tif fnd of tif pbrbgrbpi.
     */
    publid TfxtLbyout gftLbyout(int stbrt, int limit) {

        int lodblStbrt = stbrt - fStbrt;
        int lodblLimit = limit - fStbrt;

        fnsurfComponfnts(lodblStbrt, lodblLimit);
        TfxtLinf tfxtLinf = mbkfTfxtLinfOnRbngf(lodblStbrt, lodblLimit);

        if (lodblLimit < fCibrs.lfngti) {
            lbyoutCibrCount += limit-stbrt;
            lbyoutCount++;
        }

        rfturn nfw TfxtLbyout(tfxtLinf,
                              fBbsflinf,
                              fBbsflinfOffsfts,
                              fJustifyRbtio);
    }

    privbtf int formbttfdCibrs = 0;
    privbtf stbtid boolfbn wbntStbts = fblsf;/*"truf".fqubls(Systfm.gftPropfrty("dollfdtStbts"));*/
    privbtf boolfbn dollfdtStbts = fblsf;

    privbtf void printStbts() {
        Systfm.out.println("formbttfdCibrs: " + formbttfdCibrs);
        //formbttfdCibrs = 0;
        dollfdtStbts = fblsf;
    }

    /**
     * Updbtfs tif <dodf>TfxtMfbsurfr</dodf> bftfr b singlf dibrbdtfr ibs
     * bffn insfrtfd
     * into tif pbrbgrbpi durrfntly rfprfsfntfd by tiis
     * <dodf>TfxtMfbsurfr</dodf>.  Aftfr tiis dbll, tiis
     * <dodf>TfxtMfbsurfr</dodf> is fquivblfnt to b nfw
     * <dodf>TfxtMfbsurfr</dodf> drfbtfd from tif tfxt;  iowfvfr, it will
     * usublly bf morf fffidifnt to updbtf bn fxisting
     * <dodf>TfxtMfbsurfr</dodf> tibn to drfbtf b nfw onf from sdrbtdi.
     *
     * @pbrbm nfwPbrbgrbpi tif tfxt of tif pbrbgrbpi bftfr pfrforming
     * tif insfrtion.  Cbnnot bf null.
     * @pbrbm insfrtPos tif position in tif tfxt wifrf tif dibrbdtfr wbs
     * insfrtfd.  Must not bf lfss tibn tif stbrt of
     * <dodf>nfwPbrbgrbpi</dodf>, bnd must bf lfss tibn tif fnd of
     * <dodf>nfwPbrbgrbpi</dodf>.
     * @tirows IndfxOutOfBoundsExdfption if <dodf>insfrtPos</dodf> is lfss
     *         tibn tif stbrt of <dodf>nfwPbrbgrbpi</dodf> or grfbtfr tibn
     *         or fqubl to tif fnd of <dodf>nfwPbrbgrbpi</dodf>
     * @tirows NullPointfrExdfption if <dodf>nfwPbrbgrbpi</dodf> is
     *         <dodf>null</dodf>
     */
    publid void insfrtCibr(AttributfdCibrbdtfrItfrbtor nfwPbrbgrbpi, int insfrtPos) {

        if (dollfdtStbts) {
            printStbts();
        }
        if (wbntStbts) {
            dollfdtStbts = truf;
        }

        fStbrt = nfwPbrbgrbpi.gftBfginIndfx();
        int fnd = nfwPbrbgrbpi.gftEndIndfx();
        if (fnd - fStbrt != fCibrs.lfngti+1) {
            initAll(nfwPbrbgrbpi);
        }

        dibr[] nfwCibrs = nfw dibr[fnd-fStbrt];
        int nfwCibrIndfx = insfrtPos - fStbrt;
        Systfm.brrbydopy(fCibrs, 0, nfwCibrs, 0, nfwCibrIndfx);

        dibr nfwCibr = nfwPbrbgrbpi.sftIndfx(insfrtPos);
        nfwCibrs[nfwCibrIndfx] = nfwCibr;
        Systfm.brrbydopy(fCibrs,
                         nfwCibrIndfx,
                         nfwCibrs,
                         nfwCibrIndfx+1,
                         fnd-insfrtPos-1);
        fCibrs = nfwCibrs;

        if (fBidi != null || Bidi.rfquirfsBidi(nfwCibrs, nfwCibrIndfx, nfwCibrIndfx + 1) ||
                nfwPbrbgrbpi.gftAttributf(TfxtAttributf.BIDI_EMBEDDING) != null) {

            fBidi = nfw Bidi(nfwPbrbgrbpi);
            if (fBidi.isLfftToRigit()) {
                fBidi = null;
            }
        }

        fPbrbgrbpi = StylfdPbrbgrbpi.insfrtCibr(nfwPbrbgrbpi,
                                                fCibrs,
                                                insfrtPos,
                                                fPbrbgrbpi);
        invblidbtfComponfnts();
    }

    /**
     * Updbtfs tif <dodf>TfxtMfbsurfr</dodf> bftfr b singlf dibrbdtfr ibs
     * bffn dflftfd
     * from tif pbrbgrbpi durrfntly rfprfsfntfd by tiis
     * <dodf>TfxtMfbsurfr</dodf>.  Aftfr tiis dbll, tiis
     * <dodf>TfxtMfbsurfr</dodf> is fquivblfnt to b nfw <dodf>TfxtMfbsurfr</dodf>
     * drfbtfd from tif tfxt;  iowfvfr, it will usublly bf morf fffidifnt
     * to updbtf bn fxisting <dodf>TfxtMfbsurfr</dodf> tibn to drfbtf b nfw onf
     * from sdrbtdi.
     *
     * @pbrbm nfwPbrbgrbpi tif tfxt of tif pbrbgrbpi bftfr pfrforming
     * tif dflftion.  Cbnnot bf null.
     * @pbrbm dflftfPos tif position in tif tfxt wifrf tif dibrbdtfr wbs rfmovfd.
     * Must not bf lfss tibn
     * tif stbrt of <dodf>nfwPbrbgrbpi</dodf>, bnd must not bf grfbtfr tibn tif
     * fnd of <dodf>nfwPbrbgrbpi</dodf>.
     * @tirows IndfxOutOfBoundsExdfption if <dodf>dflftfPos</dodf> is
     *         lfss tibn tif stbrt of <dodf>nfwPbrbgrbpi</dodf> or grfbtfr
     *         tibn tif fnd of <dodf>nfwPbrbgrbpi</dodf>
     * @tirows NullPointfrExdfption if <dodf>nfwPbrbgrbpi</dodf> is
     *         <dodf>null</dodf>
     */
    publid void dflftfCibr(AttributfdCibrbdtfrItfrbtor nfwPbrbgrbpi, int dflftfPos) {

        fStbrt = nfwPbrbgrbpi.gftBfginIndfx();
        int fnd = nfwPbrbgrbpi.gftEndIndfx();
        if (fnd - fStbrt != fCibrs.lfngti-1) {
            initAll(nfwPbrbgrbpi);
        }

        dibr[] nfwCibrs = nfw dibr[fnd-fStbrt];
        int dibngfdIndfx = dflftfPos-fStbrt;

        Systfm.brrbydopy(fCibrs, 0, nfwCibrs, 0, dflftfPos-fStbrt);
        Systfm.brrbydopy(fCibrs, dibngfdIndfx+1, nfwCibrs, dibngfdIndfx, fnd-dflftfPos);
        fCibrs = nfwCibrs;

        if (fBidi != null) {
            fBidi = nfw Bidi(nfwPbrbgrbpi);
            if (fBidi.isLfftToRigit()) {
                fBidi = null;
            }
        }

        fPbrbgrbpi = StylfdPbrbgrbpi.dflftfCibr(nfwPbrbgrbpi,
                                                fCibrs,
                                                dflftfPos,
                                                fPbrbgrbpi);
        invblidbtfComponfnts();
    }

    /**
     * NOTE:  Tiis mftiod is only for LinfBrfbkMfbsurfr's usf.  It is pbdkbgf-
     * privbtf bfdbusf it rfturns intfrnbl dbtb.
     */
    dibr[] gftCibrs() {

        rfturn fCibrs;
    }
}
