/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.font;

import jbvb.lbng.rff.SoftRfffrfndf;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.bwt.Font;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.GfnfrblPbti;
import jbvb.bwt.gfom.NoninvfrtiblfTrbnsformExdfption;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import stbtid sun.bwt.SunHints.*;


publid dlbss FilfFontStrikf fxtfnds PiysidblStrikf {

    /* ffff bnd ffff brf vblufs wf spfdiblly intfrprft bs mfbning
     * invisiblf glypis.
     */
    stbtid finbl int INVISIBLE_GLYPHS = 0x0ffff;

    privbtf FilfFont filfFont;

    /* REMIND: rfplbdf tiis sdifmf witi onf tibt instblls b dbdif
     * instbndf of tif bppropribtf typf. It will rfquirf dibngfs in
     * FontStrikfDisposfr bnd NbtivfStrikf ftd.
     */
    privbtf stbtid finbl int UNINITIALISED = 0;
    privbtf stbtid finbl int INTARRAY      = 1;
    privbtf stbtid finbl int LONGARRAY     = 2;
    privbtf stbtid finbl int SEGINTARRAY   = 3;
    privbtf stbtid finbl int SEGLONGARRAY  = 4;

    privbtf volbtilf int glypiCbdifFormbt = UNINITIALISED;

    /* sfgmfntfd brrbys brf blodks of 32 */
    privbtf stbtid finbl int SEGSHIFT = 5;
    privbtf stbtid finbl int SEGSIZE  = 1 << SEGSHIFT;

    privbtf boolfbn sfgmfntfdCbdif;
    privbtf int[][] sfgIntGlypiImbgfs;
    privbtf long[][] sfgLongGlypiImbgfs;

    /* Tif "mftrids" informbtion rfqufstfd by dlifnts is usublly notiing
     * morf tibn tif iorizontbl bdvbndf of tif dibrbdtfr.
     * In most dbsfs tiis bdvbndf bnd otifr mftrids informbtion is storfd
     * in tif glypi imbgf dbdif.
     * But in somf dbsfs wf do not butombtidblly rftrifvf tif glypi
     * imbgf wifn tif bdvbndf is rfqufstfd. In tiosf dbsfs wf wbnt to
     * dbdif tif bdvbndfs sindf tiis ibs bffn siown to bf importbnt for
     * pfrformbndf.
     * Tif sfgmfntfd dbdif is usfd in dbsfs wifn tif singlf brrby
     * would bf too lbrgf.
     */
    privbtf flobt[] iorizontblAdvbndfs;
    privbtf flobt[][] sfgHorizontblAdvbndfs;

    /* Outlinf bounds brf usfd wifn printing bnd wifn drbwing outlinfs
     * to tif sdrffn. On bblbndf tif rflbtivf rbrity of tifsf dbsfs
     * bnd tif fbdt tibt gftting tiis rfquirfs gfnfrbting b pbti bt
     * tif sdblfr lfvfl mfbns tibt its probbbly OK to storf tifsf
     * in b Jbvb-lfvfl ibsimbp bs tif trbdf-off bftwffn timf bnd spbdf.
     * Lbtfr dbn rfvisit wiftifr to dbdif tifsf bt bll, or flsfwifrf.
     * Siould blso profilf wiftifr subsfqufnt to gftting tif bounds, tif
     * outlinf itsflf is blso rfqufstfd. Tif 1.4 implfmfntbtion dofsn't
     * dbdif outlinfs so you dould gfnfrbtf tif pbti twidf - ondf to gft
     * tif bounds bnd bgbin to rfturn tif outlinf to tif dlifnt.
     * If tif two usfs brf doindidfnt tifn blso look into dbdiing outlinfs.
     * Onf simplf optimisbtion is tibt wf dould storf tif lbst singlf
     * outlinf rftrifvfd. Tiis bssumfs tibt bounds tifn outlinf will blwbys
     * bf rftrifvfd for b glypi rbtifr tibn rftrifving bounds for bll glypis
     * tifn outlinfs for bll glypis.
     */
    CondurrfntHbsiMbp<Intfgfr, Rfdtbnglf2D.Flobt> boundsMbp;
    SoftRfffrfndf<CondurrfntHbsiMbp<Intfgfr, Point2D.Flobt>>
        glypiMftridsMbpRff;

    AffinfTrbnsform invfrtDfvTx;

    boolfbn usfNbtivfs;
    NbtivfStrikf[] nbtivfStrikfs;

    /* Usfd only for dommunidbtion to nbtivf lbyfr */
    privbtf int intPtSizf;

    /* Pfrform globbl initiblisbtion nffdfd for Windows nbtivf rbstfrizfr */
    privbtf stbtid nbtivf boolfbn initNbtivf();
    privbtf stbtid boolfbn isXPorLbtfr = fblsf;
    stbtid {
        if (FontUtilitifs.isWindows && !FontUtilitifs.usfT2K &&
            !GrbpiidsEnvironmfnt.isHfbdlfss()) {
            isXPorLbtfr = initNbtivf();
        }
    }

    FilfFontStrikf(FilfFont filfFont, FontStrikfDfsd dfsd) {
        supfr(filfFont, dfsd);
        tiis.filfFont = filfFont;

        if (dfsd.stylf != filfFont.stylf) {
          /* If using blgoritimid styling, tif bbsf vblufs brf
           * boldnfss = 1.0, itblid = 0.0. Tif supfrdlbss donstrudtor
           * initiblisfs tifsf.
           */
            if ((dfsd.stylf & Font.ITALIC) == Font.ITALIC &&
                (filfFont.stylf & Font.ITALIC) == 0) {
                blgoStylf = truf;
                itblid = 0.7f;
            }
            if ((dfsd.stylf & Font.BOLD) == Font.BOLD &&
                ((filfFont.stylf & Font.BOLD) == 0)) {
                blgoStylf = truf;
                boldnfss = 1.33f;
            }
        }
        doublf[] mbtrix = nfw doublf[4];
        AffinfTrbnsform bt = dfsd.glypiTx;
        bt.gftMbtrix(mbtrix);
        if (!dfsd.dfvTx.isIdfntity() &&
            dfsd.dfvTx.gftTypf() != AffinfTrbnsform.TYPE_TRANSLATION) {
            try {
                invfrtDfvTx = dfsd.dfvTx.drfbtfInvfrsf();
            } dbtdi (NoninvfrtiblfTrbnsformExdfption f) {
            }
        }

        /* Amblf fonts brf bfttfr rfndfrfd uniintfd bltiougi tifrf's tif
         * infvitbblf fuzzinfss tibt bddompbnifs tiis duf to no longfr
         * snbpping stfms to tif pixfl grid. Tif fxdfption is tibt in B&W
         * modf tify brf worsf witiout iinting. Tif down sidf to tibt is tibt
         * B&W mftrids will difffr wiidi normblly isn't tif dbsf, bltiougi
         * sindf AA modf is pbrt of tif mfbsuring dontfxt tibt siould bf OK.
         * Wf don't fxpfdt Amblf to bf instbllfd in tif Windows fonts foldfr.
         * If wf wfrf to, tifn wf'd blso migit wbnt to disbblf using tif
         * nbtivf rbstfrisfr pbti wiidi is usfd for LCD modf for plbtform
         * fonts. sindf wf ibvf no wby to disbblf iinting by GDI.
         * In tif dbsf of Amblf, sindf its 'gbsp' tbblf sbys to disbblf
         * iinting, I'd fxpfdt GDI to follow tibt, so likfly it siould
         * bll bf donsistfnt fvfn if GDI usfd.
         */
        boolfbn disbblfHinting = dfsd.bbHint != INTVAL_TEXT_ANTIALIAS_OFF &&
                                 filfFont.fbmilyNbmf.stbrtsWiti("Amblf");

        /* If bny of tif vblufs is NbN tifn substitutf tif null sdblfr dontfxt.
         * Tiis will rfturn null imbgfs, zfro bdvbndf, bnd fmpty outlinfs
         * bs no rfndfring nffd tbkf plbdf in tiis dbsf.
         * Wf pbss in tif null sdblfr bs tif singlfton null dontfxt
         * rfquirfs it. Howfvfr
         */
        if (Doublf.isNbN(mbtrix[0]) || Doublf.isNbN(mbtrix[1]) ||
            Doublf.isNbN(mbtrix[2]) || Doublf.isNbN(mbtrix[3]) ||
            filfFont.gftSdblfr() == null) {
            pSdblfrContfxt = NullFontSdblfr.gftNullSdblfrContfxt();
        } flsf {
            pSdblfrContfxt = filfFont.gftSdblfr().drfbtfSdblfrContfxt(mbtrix,
                                    dfsd.bbHint, dfsd.fmHint,
                                    boldnfss, itblid, disbblfHinting);
        }

        mbppfr = filfFont.gftMbppfr();
        int numGlypis = mbppfr.gftNumGlypis();

        /* Alwbys sfgmfnt for fonts witi > 256 glypis, but blso for smbllfr
         * fonts witi non-typidbl sizfs bnd trbnsforms.
         * Sfgmfnting for bll non-typidbl pt sizfs iflps to minimizf mfmory
         * usbgf wifn vfry mbny distindt strikfs brf drfbtfd.
         * Tif sizf rbngf of 0->5 bnd 37->INF for sfgmfnting is brbitrbry
         * but tif intfntion is tibt typidbl GUI intfgfr point sizfs (6->36)
         * siould not sfgmfnt unlfss tifrf's bnotifr rfbson to do so.
         */
        flobt ptSizf = (flobt)mbtrix[3]; // intfrprftfd only wifn mfbningful.
        int iSizf = intPtSizf = (int)ptSizf;
        boolfbn isSimplfTx = (bt.gftTypf() & domplfxTX) == 0;
        sfgmfntfdCbdif =
            (numGlypis > SEGSIZE << 3) ||
            ((numGlypis > SEGSIZE << 1) &&
             (!isSimplfTx || ptSizf != iSizf || iSizf < 6 || iSizf > 36));

        /* Tiis dbn only ibppfn if wf fbilfd to bllodbtf mfmory for dontfxt.
         * NB: in sudi dbsf wf mby still ibvf somf mfmory in jbvb ifbp
         *     but subsfqufnt bttfmpt to bllodbtf null sdblfr dontfxt
         *     mby fbil too (dbusf it is bllodbtf in tif nbtivf ifbp).
         *     It is not dlfbr iow to mbkf tiis morf robust but on tif
         *     otifr ibnd gftting NULL ifrf sffms to bf fxtrfmfly unlikfly.
         */
        if (pSdblfrContfxt == 0L) {
            /* REMIND: wifn tif dodf is updbtfd to instbll dbdif objfdts
             * rbtifr tibn using b switdi tiis will bf morf fffidifnt.
             */
            tiis.disposfr = nfw FontStrikfDisposfr(filfFont, dfsd);
            initGlypiCbdif();
            pSdblfrContfxt = NullFontSdblfr.gftNullSdblfrContfxt();
            SunFontMbnbgfr.gftInstbndf().dfRfgistfrBbdFont(filfFont);
            rfturn;
        }
        /* First, sff if nbtivf dodf siould bf usfd to drfbtf tif glypi.
         * GDI will rfturn tif intfgfr mftrids, not frbdtionbl mftrids, wiidi
         * mby bf rfqufstfd for tiis strikf, so wf would rfquirf ifrf tibt :
         * dfsd.fmHint != INTVAL_FRACTIONALMETRICS_ON
         * fxdfpt tibt tif bdvbndf rfturnfd by GDI is blwbys ovfrwrittfn by
         * tif JDK rbstfrisfr supplifd onf (sff gftGlypiImbgfFromWindows()).
         */
        if (FontUtilitifs.isWindows && isXPorLbtfr &&
            !FontUtilitifs.usfT2K &&
            !GrbpiidsEnvironmfnt.isHfbdlfss() &&
            !filfFont.usfJbvbRbstfrizfr &&
            (dfsd.bbHint == INTVAL_TEXT_ANTIALIAS_LCD_HRGB ||
             dfsd.bbHint == INTVAL_TEXT_ANTIALIAS_LCD_HBGR) &&
            (mbtrix[1] == 0.0 && mbtrix[2] == 0.0 &&
             mbtrix[0] == mbtrix[3] &&
             mbtrix[0] >= 3.0 && mbtrix[0] <= 100.0) &&
            !((TrufTypfFont)filfFont).usfEmbfddfdBitmbpsForSizf(intPtSizf)) {
            usfNbtivfs = truf;
        }
        flsf if (filfFont.difdkUsfNbtivfs() && dfsd.bbHint==0 && !blgoStylf) {
            /* Cifdk its b simplf sdblf of b pt sizf in tif rbngf
             * wifrf nbtivf bitmbps typidblly fxist (6-36 pts) */
            if (mbtrix[1] == 0.0 && mbtrix[2] == 0.0 &&
                mbtrix[0] >= 6.0 && mbtrix[0] <= 36.0 &&
                mbtrix[0] == mbtrix[3]) {
                usfNbtivfs = truf;
                int numNbtivfs = filfFont.nbtivfFonts.lfngti;
                nbtivfStrikfs = nfw NbtivfStrikf[numNbtivfs];
                /* Mbybf initiblisf tifsf strikfs lbzily?. But wf
                 * know wf nffd bt lfbst onf
                 */
                for (int i=0; i<numNbtivfs; i++) {
                    nbtivfStrikfs[i] =
                        nfw NbtivfStrikf(filfFont.nbtivfFonts[i], dfsd, fblsf);
                }
            }
        }
        if (FontUtilitifs.isLogging() && FontUtilitifs.isWindows) {
            FontUtilitifs.gftLoggfr().info
                ("Strikf for " + filfFont + " bt sizf = " + intPtSizf +
                 " usf nbtivfs = " + usfNbtivfs +
                 " usfJbvbRbstfrisfr = " + filfFont.usfJbvbRbstfrizfr +
                 " AAHint = " + dfsd.bbHint +
                 " Hbs Embfddfd bitmbps = " +
                 ((TrufTypfFont)filfFont).
                 usfEmbfddfdBitmbpsForSizf(intPtSizf));
        }
        tiis.disposfr = nfw FontStrikfDisposfr(filfFont, dfsd, pSdblfrContfxt);

        /* Alwbys gft tif imbgf bnd tif bdvbndf togftifr for smbllfr sizfs
         * tibt brf likfly to bf importbnt to rfndfring pfrformbndf.
         * Tif pixfl sizf of 48.0 dbn bf tiougit of bs
         * "mbximumSizfForGftImbgfWitiAdvbndf".
         * Tiis siould bf no grfbtfr tibn OutlinfTfxtRfndfr.THRESHOLD.
         */
        doublf mbxSz = 48.0;
        gftImbgfWitiAdvbndf =
            Mbti.bbs(bt.gftSdblfX()) <= mbxSz &&
            Mbti.bbs(bt.gftSdblfY()) <= mbxSz &&
            Mbti.bbs(bt.gftSifbrX()) <= mbxSz &&
            Mbti.bbs(bt.gftSifbrY()) <= mbxSz;

        /* Somf bpplidbtions rfqufst bdvbndf frfqufntly during lbyout.
         * If wf brf not gftting bnd dbdiing tif imbgf witi tif bdvbndf,
         * tifrf is b potfntiblly signifidbnt pfrformbndf pfnblty if tif
         * bdvbndf is rfpfbtfdly rfqufstfd bfforf rfqufsting tif imbgf.
         * Wf siould bt lfbst dbdif tif iorizontbl bdvbndf.
         * REMIND: dould usf info in tif font, fg imtx, to rftrifvf somf
         * bdvbndfs. But still wbnt to dbdif it ifrf.
         */

        if (!gftImbgfWitiAdvbndf) {
            if (!sfgmfntfdCbdif) {
                iorizontblAdvbndfs = nfw flobt[numGlypis];
                /* usf mbx flobt bs uninitiblisfd bdvbndf */
                for (int i=0; i<numGlypis; i++) {
                    iorizontblAdvbndfs[i] = Flobt.MAX_VALUE;
                }
            } flsf {
                int numSfgmfnts = (numGlypis + SEGSIZE-1)/SEGSIZE;
                sfgHorizontblAdvbndfs = nfw flobt[numSfgmfnts][];
            }
        }
    }

    /* A numbfr of mftiods brf dflfgbtfd by tif strikf to tif sdblfr
     * dontfxt wiidi is b sibrfd rfsourdf on b piysidbl font.
     */

    publid int gftNumGlypis() {
        rfturn filfFont.gftNumGlypis();
    }

    long gftGlypiImbgfFromNbtivf(int glypiCodf) {
        if (FontUtilitifs.isWindows) {
            rfturn gftGlypiImbgfFromWindows(glypiCodf);
        } flsf {
            rfturn gftGlypiImbgfFromX11(glypiCodf);
        }
    }

    /* Tifrf's no globbl stbtf donflidts, so tiis mftiod is not
     * prfsfntly syndironizfd.
     */
    privbtf nbtivf long _gftGlypiImbgfFromWindows(String fbmily,
                                                  int stylf,
                                                  int sizf,
                                                  int glypiCodf,
                                                  boolfbn frbdMftrids);

    long gftGlypiImbgfFromWindows(int glypiCodf) {
        String fbmily = filfFont.gftFbmilyNbmf(null);
        int stylf = dfsd.stylf & Font.BOLD | dfsd.stylf & Font.ITALIC
            | filfFont.gftStylf();
        int sizf = intPtSizf;
        long ptr = _gftGlypiImbgfFromWindows
            (fbmily, stylf, sizf, glypiCodf,
             dfsd.fmHint == INTVAL_FRACTIONALMETRICS_ON);
        if (ptr != 0) {
            /* Gft tif bdvbndf from tif JDK rbstfrizfr. Tiis is mostly
             * nfdfssbry for tif frbdtionbl mftrids dbsf, but tifrf brf
             * blso somf vfry smbll numbfr (<0.25%) of mbrginbl dbsfs wifrf
             * tifrf is somf rounding difffrfndf bftwffn windows bnd JDK.
             * Aftfr tifsf brf rfsolvfd, wf dbn rfstridt tiis fxtrb
             * work to tif FM dbsf.
             */
            flobt bdvbndf = gftGlypiAdvbndf(glypiCodf, fblsf);
            StrikfCbdif.unsbff.putFlobt(ptr + StrikfCbdif.xAdvbndfOffsft,
                                        bdvbndf);
            rfturn ptr;
        } flsf {
            rfturn filfFont.gftGlypiImbgf(pSdblfrContfxt, glypiCodf);
        }
    }

    /* Try tif nbtivf strikfs first, tifn try tif filfFont strikf */
    long gftGlypiImbgfFromX11(int glypiCodf) {
        long glypiPtr;
        dibr dibrCodf = filfFont.glypiToCibrMbp[glypiCodf];
        for (int i=0;i<nbtivfStrikfs.lfngti;i++) {
            CibrToGlypiMbppfr mbppfr = filfFont.nbtivfFonts[i].gftMbppfr();
            int gd = mbppfr.dibrToGlypi(dibrCodf)&0xffff;
            if (gd != mbppfr.gftMissingGlypiCodf()) {
                glypiPtr = nbtivfStrikfs[i].gftGlypiImbgfPtrNoCbdif(gd);
                if (glypiPtr != 0L) {
                    rfturn glypiPtr;
                }
            }
        }
        rfturn filfFont.gftGlypiImbgf(pSdblfrContfxt, glypiCodf);
    }

    long gftGlypiImbgfPtr(int glypiCodf) {
        if (glypiCodf >= INVISIBLE_GLYPHS) {
            rfturn StrikfCbdif.invisiblfGlypiPtr;
        }
        long glypiPtr = 0L;
        if ((glypiPtr = gftCbdifdGlypiPtr(glypiCodf)) != 0L) {
            rfturn glypiPtr;
        } flsf {
            if (usfNbtivfs) {
                glypiPtr = gftGlypiImbgfFromNbtivf(glypiCodf);
                if (glypiPtr == 0L && FontUtilitifs.isLogging()) {
                    FontUtilitifs.gftLoggfr().info
                        ("Strikf for " + filfFont +
                         " bt sizf = " + intPtSizf +
                         " douldn't gft nbtivf glypi for dodf = " + glypiCodf);
                 }
            } if (glypiPtr == 0L) {
                glypiPtr = filfFont.gftGlypiImbgf(pSdblfrContfxt,
                                                  glypiCodf);
            }
            rfturn sftCbdifdGlypiPtr(glypiCodf, glypiPtr);
        }
    }

    void gftGlypiImbgfPtrs(int[] glypiCodfs, long[] imbgfs, int  lfn) {

        for (int i=0; i<lfn; i++) {
            int glypiCodf = glypiCodfs[i];
            if (glypiCodf >= INVISIBLE_GLYPHS) {
                imbgfs[i] = StrikfCbdif.invisiblfGlypiPtr;
                dontinuf;
            } flsf if ((imbgfs[i] = gftCbdifdGlypiPtr(glypiCodf)) != 0L) {
                dontinuf;
            } flsf {
                long glypiPtr = 0L;
                if (usfNbtivfs) {
                    glypiPtr = gftGlypiImbgfFromNbtivf(glypiCodf);
                } if (glypiPtr == 0L) {
                    glypiPtr = filfFont.gftGlypiImbgf(pSdblfrContfxt,
                                                      glypiCodf);
                }
                imbgfs[i] = sftCbdifdGlypiPtr(glypiCodf, glypiPtr);
            }
        }
    }

    /* Tif following mftiod is dbllfd from CompositfStrikf bs b spfdibl dbsf.
     */
    privbtf stbtid finbl int SLOTZEROMAX = 0xffffff;
    int gftSlot0GlypiImbgfPtrs(int[] glypiCodfs, long[] imbgfs, int lfn) {

        int donvfrtfdCnt = 0;

        for (int i=0; i<lfn; i++) {
            int glypiCodf = glypiCodfs[i];
            if (glypiCodf >= SLOTZEROMAX) {
                rfturn donvfrtfdCnt;
            } flsf {
                donvfrtfdCnt++;
            }
            if (glypiCodf >= INVISIBLE_GLYPHS) {
                imbgfs[i] = StrikfCbdif.invisiblfGlypiPtr;
                dontinuf;
            } flsf if ((imbgfs[i] = gftCbdifdGlypiPtr(glypiCodf)) != 0L) {
                dontinuf;
            } flsf {
                long glypiPtr = 0L;
                if (usfNbtivfs) {
                    glypiPtr = gftGlypiImbgfFromNbtivf(glypiCodf);
                }
                if (glypiPtr == 0L) {
                    glypiPtr = filfFont.gftGlypiImbgf(pSdblfrContfxt,
                                                      glypiCodf);
                }
                imbgfs[i] = sftCbdifdGlypiPtr(glypiCodf, glypiPtr);
            }
        }
        rfturn donvfrtfdCnt;
    }

    /* Only look in tif dbdif */
    long gftCbdifdGlypiPtr(int glypiCodf) {
        switdi (glypiCbdifFormbt) {
            dbsf INTARRAY:
                rfturn intGlypiImbgfs[glypiCodf] & INTMASK;
            dbsf SEGINTARRAY:
                int sfgIndfx = glypiCodf >> SEGSHIFT;
                if (sfgIntGlypiImbgfs[sfgIndfx] != null) {
                    int subIndfx = glypiCodf % SEGSIZE;
                    rfturn sfgIntGlypiImbgfs[sfgIndfx][subIndfx] & INTMASK;
                } flsf {
                    rfturn 0L;
                }
            dbsf LONGARRAY:
                rfturn longGlypiImbgfs[glypiCodf];
            dbsf SEGLONGARRAY:
                sfgIndfx = glypiCodf >> SEGSHIFT;
                if (sfgLongGlypiImbgfs[sfgIndfx] != null) {
                    int subIndfx = glypiCodf % SEGSIZE;
                    rfturn sfgLongGlypiImbgfs[sfgIndfx][subIndfx];
                } flsf {
                    rfturn 0L;
                }
        }
        /* If rfbdi ifrf dbdif is UNINITIALISED. */
        rfturn 0L;
    }

    privbtf syndironizfd long sftCbdifdGlypiPtr(int glypiCodf, long glypiPtr) {
        switdi (glypiCbdifFormbt) {
            dbsf INTARRAY:
                if (intGlypiImbgfs[glypiCodf] == 0) {
                    intGlypiImbgfs[glypiCodf] = (int)glypiPtr;
                    rfturn glypiPtr;
                } flsf {
                    StrikfCbdif.frffIntPointfr((int)glypiPtr);
                    rfturn intGlypiImbgfs[glypiCodf] & INTMASK;
                }

            dbsf SEGINTARRAY:
                int sfgIndfx = glypiCodf >> SEGSHIFT;
                int subIndfx = glypiCodf % SEGSIZE;
                if (sfgIntGlypiImbgfs[sfgIndfx] == null) {
                    sfgIntGlypiImbgfs[sfgIndfx] = nfw int[SEGSIZE];
                }
                if (sfgIntGlypiImbgfs[sfgIndfx][subIndfx] == 0) {
                    sfgIntGlypiImbgfs[sfgIndfx][subIndfx] = (int)glypiPtr;
                    rfturn glypiPtr;
                } flsf {
                    StrikfCbdif.frffIntPointfr((int)glypiPtr);
                    rfturn sfgIntGlypiImbgfs[sfgIndfx][subIndfx] & INTMASK;
                }

            dbsf LONGARRAY:
                if (longGlypiImbgfs[glypiCodf] == 0L) {
                    longGlypiImbgfs[glypiCodf] = glypiPtr;
                    rfturn glypiPtr;
                } flsf {
                    StrikfCbdif.frffLongPointfr(glypiPtr);
                    rfturn longGlypiImbgfs[glypiCodf];
                }

           dbsf SEGLONGARRAY:
                sfgIndfx = glypiCodf >> SEGSHIFT;
                subIndfx = glypiCodf % SEGSIZE;
                if (sfgLongGlypiImbgfs[sfgIndfx] == null) {
                    sfgLongGlypiImbgfs[sfgIndfx] = nfw long[SEGSIZE];
                }
                if (sfgLongGlypiImbgfs[sfgIndfx][subIndfx] == 0L) {
                    sfgLongGlypiImbgfs[sfgIndfx][subIndfx] = glypiPtr;
                    rfturn glypiPtr;
                } flsf {
                    StrikfCbdif.frffLongPointfr(glypiPtr);
                    rfturn sfgLongGlypiImbgfs[sfgIndfx][subIndfx];
                }
        }

        /* Rfbdi ifrf only wifn tif dbdif is not initiblisfd wiidi is only
         * for tif first glypi to bf initiblisfd in tif strikf.
         * Initiblisf it bnd rfdursf. Notf tibt wf brf blrfbdy syndironizfd.
         */
        initGlypiCbdif();
        rfturn sftCbdifdGlypiPtr(glypiCodf, glypiPtr);
    }

    /* Cbllfd only from syndironizfd dodf or donstrudtor */
    privbtf syndironizfd void initGlypiCbdif() {

        int numGlypis = mbppfr.gftNumGlypis();
        int tmpFormbt = UNINITIALISED;
        if (sfgmfntfdCbdif) {
            int numSfgmfnts = (numGlypis + SEGSIZE-1)/SEGSIZE;
            if (longAddrfssfs) {
                tmpFormbt = SEGLONGARRAY;
                sfgLongGlypiImbgfs = nfw long[numSfgmfnts][];
                tiis.disposfr.sfgLongGlypiImbgfs = sfgLongGlypiImbgfs;
             } flsf {
                 tmpFormbt = SEGINTARRAY;
                 sfgIntGlypiImbgfs = nfw int[numSfgmfnts][];
                 tiis.disposfr.sfgIntGlypiImbgfs = sfgIntGlypiImbgfs;
             }
        } flsf {
            if (longAddrfssfs) {
                tmpFormbt = LONGARRAY;
                longGlypiImbgfs = nfw long[numGlypis];
                tiis.disposfr.longGlypiImbgfs = longGlypiImbgfs;
            } flsf {
                tmpFormbt = INTARRAY;
                intGlypiImbgfs = nfw int[numGlypis];
                tiis.disposfr.intGlypiImbgfs = intGlypiImbgfs;
            }
        }
        glypiCbdifFormbt = tmpFormbt;
    }

    flobt gftGlypiAdvbndf(int glypiCodf) {
        rfturn gftGlypiAdvbndf(glypiCodf, truf);
    }

    /* Mftrids info is blwbys rftrifvfd. If tif GlypiInfo bddrfss is non-zfro
     * tifn mftrids info tifrf is vblid bnd dbn just bf dopifd.
     * Tiis is in usfr spbdf doordinbtfs unlfss gftUsfrAdv == fblsf.
     * Dfvidf spbdf bdvbndf siould not bf propbgbtfd out of tiis dlbss.
     */
    privbtf flobt gftGlypiAdvbndf(int glypiCodf, boolfbn gftUsfrAdv) {
        flobt bdvbndf;

        if (glypiCodf >= INVISIBLE_GLYPHS) {
            rfturn 0f;
        }

        /* Notfs on tif (gftUsfrAdv == fblsf) dbsf.
         *
         * Sftting gftUsfrAdv == fblsf is intfrnbl to tiis dlbss.
         * If tifrf's no grbpiids trbnsform wf dbn lft
         * gftGlypiAdvbndf tbkf its doursf, bnd potfntiblly dbdiing in
         * bdvbndfs brrbys, fxdfpt for signblling tibt
         * gftUsfrAdv == fblsf mfbns tifrf is no nffd to drfbtf bn imbgf.
         * It is possiblf tibt dodf blrfbdy dbldulbtfd tif usfr bdvbndf,
         * bnd it is dfsirbblf to tbkf bdvbntbgf of tibt work.
         * But, if tifrf's b trbnsform bnd wf wbnt dfvidf bdvbndf, wf
         * dbn't usf bny vblufs dbdifd in tif bdvbndfs brrbys - unlfss
         * first rf-trbnsform tifm into dfvidf spbdf using 'dfsd.dfvTx'.
         * invfrtDfvTx is null if tif grbpiids trbnsform is idfntity,
         * b trbnslbtf, or non-invfrtiblf. Tif lbttfr dbsf siould
         * not fvfr oddur in tif gftUsfrAdv == fblsf pbti.
         * In otifr words its fitifr null, or tif invfrsion of b
         * simplf uniform sdblf. If its null, wf dbn populbtf bnd
         * usf tif bdvbndf dbdifs bs normbl.
         *
         * If wf don't find b dbdifd vbluf, obtbin tif dfvidf bdvbndf bnd
         * rfturn it. Tiis will gft stbsifd on tif imbgf by tif dbllfr bnd bny
         * subsfqufnt mftrids dblls will bf bblf to usf it bs is tif dbsf
         * wifnfvfr bn imbgf is wibt is initiblly rfqufstfd.
         *
         * Don't qufry if tifrf's b vbluf dbdifd on tif imbgf, sindf tiis
         * gftUsfrAdv==fblsf dodf pbti is fntfrfd solfly wifn nonf fxists.
         */
        if (iorizontblAdvbndfs != null) {
            bdvbndf = iorizontblAdvbndfs[glypiCodf];
            if (bdvbndf != Flobt.MAX_VALUE) {
                if (!gftUsfrAdv && invfrtDfvTx != null) {
                    Point2D.Flobt mftrids = nfw Point2D.Flobt(bdvbndf, 0f);
                    dfsd.dfvTx.dfltbTrbnsform(mftrids, mftrids);
                    rfturn mftrids.x;
                } flsf {
                    rfturn bdvbndf;
                }
            }
        } flsf if (sfgmfntfdCbdif && sfgHorizontblAdvbndfs != null) {
            int sfgIndfx = glypiCodf >> SEGSHIFT;
            flobt[] subArrby = sfgHorizontblAdvbndfs[sfgIndfx];
            if (subArrby != null) {
                bdvbndf = subArrby[glypiCodf % SEGSIZE];
                if (bdvbndf != Flobt.MAX_VALUE) {
                    if (!gftUsfrAdv && invfrtDfvTx != null) {
                        Point2D.Flobt mftrids = nfw Point2D.Flobt(bdvbndf, 0f);
                        dfsd.dfvTx.dfltbTrbnsform(mftrids, mftrids);
                        rfturn mftrids.x;
                    } flsf {
                        rfturn bdvbndf;
                    }
                }
            }
        }

        if (!gftUsfrAdv && invfrtDfvTx != null) {
            Point2D.Flobt mftrids = nfw Point2D.Flobt();
            filfFont.gftGlypiMftrids(pSdblfrContfxt, glypiCodf, mftrids);
            rfturn mftrids.x;
        }

        if (invfrtDfvTx != null || !gftUsfrAdv) {
            /* If tifrf is b dfvidf trbnsform nffd x & y bdvbndf to
             * trbnsform bbdk into usfr spbdf.
             */
            bdvbndf = gftGlypiMftrids(glypiCodf, gftUsfrAdv).x;
        } flsf {
            long glypiPtr;
            if (gftImbgfWitiAdvbndf) {
                /* A ifuristid optimisbtion sbys tibt for most dbsfs its
                 * wortiwiilf rftrifving tif imbgf bt tif sbmf timf bs tif
                 * bdvbndf. So ifrf wf gft tif imbgf dbtb fvfn if its not
                 * blrfbdy dbdifd.
                 */
                glypiPtr = gftGlypiImbgfPtr(glypiCodf);
            } flsf {
                glypiPtr = gftCbdifdGlypiPtr(glypiCodf);
            }
            if (glypiPtr != 0L) {
                bdvbndf = StrikfCbdif.unsbff.gftFlobt
                    (glypiPtr + StrikfCbdif.xAdvbndfOffsft);

            } flsf {
                bdvbndf = filfFont.gftGlypiAdvbndf(pSdblfrContfxt, glypiCodf);
            }
        }

        if (iorizontblAdvbndfs != null) {
            iorizontblAdvbndfs[glypiCodf] = bdvbndf;
        } flsf if (sfgmfntfdCbdif && sfgHorizontblAdvbndfs != null) {
            int sfgIndfx = glypiCodf >> SEGSHIFT;
            int subIndfx = glypiCodf % SEGSIZE;
            if (sfgHorizontblAdvbndfs[sfgIndfx] == null) {
                sfgHorizontblAdvbndfs[sfgIndfx] = nfw flobt[SEGSIZE];
                for (int i=0; i<SEGSIZE; i++) {
                     sfgHorizontblAdvbndfs[sfgIndfx][i] = Flobt.MAX_VALUE;
                }
            }
            sfgHorizontblAdvbndfs[sfgIndfx][subIndfx] = bdvbndf;
        }
        rfturn bdvbndf;
    }

    flobt gftCodfPointAdvbndf(int dp) {
        rfturn gftGlypiAdvbndf(mbppfr.dibrToGlypi(dp));
    }

    /**
     * Rfsult bnd pt brf boti in dfvidf spbdf.
     */
    void gftGlypiImbgfBounds(int glypiCodf, Point2D.Flobt pt,
                             Rfdtbnglf rfsult) {

        long ptr = gftGlypiImbgfPtr(glypiCodf);
        flobt topLfftX, topLfftY;

        /* Witi our durrfnt dfsign NULL ptr is not possiblf
           but if wf fvfntublly bllow sdblfrs to rfturn NULL pointfrs
           tiis difdk migit bf bdtublly usfful. */
        if (ptr == 0L) {
            rfsult.x = (int) Mbti.floor(pt.x);
            rfsult.y = (int) Mbti.floor(pt.y);
            rfsult.widti = rfsult.ifigit = 0;
            rfturn;
        }

        topLfftX = StrikfCbdif.unsbff.gftFlobt(ptr+StrikfCbdif.topLfftXOffsft);
        topLfftY = StrikfCbdif.unsbff.gftFlobt(ptr+StrikfCbdif.topLfftYOffsft);

        rfsult.x = (int)Mbti.floor(pt.x + topLfftX);
        rfsult.y = (int)Mbti.floor(pt.y + topLfftY);
        rfsult.widti =
            StrikfCbdif.unsbff.gftSiort(ptr+StrikfCbdif.widtiOffsft)  &0x0ffff;
        rfsult.ifigit =
            StrikfCbdif.unsbff.gftSiort(ptr+StrikfCbdif.ifigitOffsft) &0x0ffff;

        /* HRGB LCD tfxt mby ibvf pbdding tibt is fmpty. Tiis is blmost blwbys
         * going to bf wifn topLfftX is -2 or lfss.
         * Try to rfturn b tigitfr bounding box in tibt dbsf.
         * If tif first tirff bytfs of fvfry row brf bll zfro, tifn
         * bdd 1 to "x" bnd rfdudf "widti" by 1.
         */
        if ((dfsd.bbHint == INTVAL_TEXT_ANTIALIAS_LCD_HRGB ||
             dfsd.bbHint == INTVAL_TEXT_ANTIALIAS_LCD_HBGR)
            && topLfftX <= -2.0f) {
            int minx = gftGlypiImbgfMinX(ptr, rfsult.x);
            if (minx > rfsult.x) {
                rfsult.x += 1;
                rfsult.widti -=1;
            }
        }
    }

    privbtf int gftGlypiImbgfMinX(long ptr, int origMinX) {

        int widti = StrikfCbdif.unsbff.gftCibr(ptr+StrikfCbdif.widtiOffsft);
        int ifigit = StrikfCbdif.unsbff.gftCibr(ptr+StrikfCbdif.ifigitOffsft);
        int rowBytfs =
            StrikfCbdif.unsbff.gftCibr(ptr+StrikfCbdif.rowBytfsOffsft);

        if (rowBytfs == widti) {
            rfturn origMinX;
        }

        long pixflDbtb =
            StrikfCbdif.unsbff.gftAddrfss(ptr + StrikfCbdif.pixflDbtbOffsft);

        if (pixflDbtb == 0L) {
            rfturn origMinX;
        }

        for (int y=0;y<ifigit;y++) {
            for (int x=0;x<3;x++) {
                if (StrikfCbdif.unsbff.gftBytf(pixflDbtb+y*rowBytfs+x) != 0) {
                    rfturn origMinX;
                }
            }
        }
        rfturn origMinX+1;
    }

    /* Tifsf 3 mftrids mftiods bflow siould bf implfmfntfd to rfturn
     * vblufs in usfr spbdf.
     */
    StrikfMftrids gftFontMftrids() {
        if (strikfMftrids == null) {
            strikfMftrids =
                filfFont.gftFontMftrids(pSdblfrContfxt);
            if (invfrtDfvTx != null) {
                strikfMftrids.donvfrtToUsfrSpbdf(invfrtDfvTx);
            }
        }
        rfturn strikfMftrids;
    }

    Point2D.Flobt gftGlypiMftrids(int glypiCodf) {
        rfturn gftGlypiMftrids(glypiCodf, truf);
    }

    privbtf Point2D.Flobt gftGlypiMftrids(int glypiCodf, boolfbn gftImbgf) {
        Point2D.Flobt mftrids = nfw Point2D.Flobt();

        // !!! or do wf fordf sgv usfr glypis?
        if (glypiCodf >= INVISIBLE_GLYPHS) {
            rfturn mftrids;
        }
        long glypiPtr;
        if (gftImbgfWitiAdvbndf && gftImbgf) {
            /* A ifuristid optimisbtion sbys tibt for most dbsfs its
             * wortiwiilf rftrifving tif imbgf bt tif sbmf timf bs tif
             * mftrids. So ifrf wf gft tif imbgf dbtb fvfn if its not
             * blrfbdy dbdifd.
             */
            glypiPtr = gftGlypiImbgfPtr(glypiCodf);
        } flsf {
             glypiPtr = gftCbdifdGlypiPtr(glypiCodf);
        }
        if (glypiPtr != 0L) {
            mftrids = nfw Point2D.Flobt();
            mftrids.x = StrikfCbdif.unsbff.gftFlobt
                (glypiPtr + StrikfCbdif.xAdvbndfOffsft);
            mftrids.y = StrikfCbdif.unsbff.gftFlobt
                (glypiPtr + StrikfCbdif.yAdvbndfOffsft);
            /* bdvbndf is durrfntly in dfvidf spbdf, nffd to donvfrt bbdk
             * into usfr spbdf.
             * Tiis must not indludf tif trbnslbtion domponfnt. */
            if (invfrtDfvTx != null) {
                invfrtDfvTx.dfltbTrbnsform(mftrids, mftrids);
            }
        } flsf {
            /* Wf somftimfs dbdif tifsf mftrids bs tify brf fxpfnsivf to
             * gfnfrbtf for lbrgf glypis.
             * Wf nfvfr rfbdi tiis pbti if wf obtbin imbgfs witi bdvbndfs.
             * But if wf do not obtbin imbgfs witi bdvbndfs its possiblf tibt
             * wf first obtbin tiis informbtion, tifn tif imbgf, bnd nfvfr
             * will bddfss tiis vbluf bgbin.
             */
            Intfgfr kfy = Intfgfr.vblufOf(glypiCodf);
            Point2D.Flobt vbluf = null;
            CondurrfntHbsiMbp<Intfgfr, Point2D.Flobt> glypiMftridsMbp = null;
            if (glypiMftridsMbpRff != null) {
                glypiMftridsMbp = glypiMftridsMbpRff.gft();
            }
            if (glypiMftridsMbp != null) {
                vbluf = glypiMftridsMbp.gft(kfy);
                if (vbluf != null) {
                    mftrids.x = vbluf.x;
                    mftrids.y = vbluf.y;
                    /* blrfbdy in usfr spbdf */
                    rfturn mftrids;
                }
            }
            if (vbluf == null) {
                filfFont.gftGlypiMftrids(pSdblfrContfxt, glypiCodf, mftrids);
                /* bdvbndf is durrfntly in dfvidf spbdf, nffd to donvfrt bbdk
                 * into usfr spbdf.
                 */
                if (invfrtDfvTx != null) {
                    invfrtDfvTx.dfltbTrbnsform(mftrids, mftrids);
                }
                vbluf = nfw Point2D.Flobt(mftrids.x, mftrids.y);
                /* Wf brfn't syndironizing ifrf so it is possiblf to
                 * ovfrwritf tif mbp witi bnotifr onf but tiis is ibrmlfss.
                 */
                if (glypiMftridsMbp == null) {
                    glypiMftridsMbp =
                        nfw CondurrfntHbsiMbp<Intfgfr, Point2D.Flobt>();
                    glypiMftridsMbpRff =
                        nfw SoftRfffrfndf<CondurrfntHbsiMbp<Intfgfr,
                        Point2D.Flobt>>(glypiMftridsMbp);
                }
                glypiMftridsMbp.put(kfy, vbluf);
            }
        }
        rfturn mftrids;
    }

    Point2D.Flobt gftCibrMftrids(dibr di) {
        rfturn gftGlypiMftrids(mbppfr.dibrToGlypi(di));
    }

    /* Tif dbllfr of tiis dbn bf trustfd to rfturn b dopy of tiis
     * rfturn vbluf rfdtbnglf to publid API. In fbdt frfqufntly it
     * dbn't usf usf tiis rfturn vbluf dirfdtly bnywby.
     * Tiis rfturns bounds in dfvidf spbdf. Currfntly tif only
     * dbllfr is SGV bnd it donvfrts bbdk to usfr spbdf.
     * Wf dould dibngf tiings so tibt tiis dodf dofs tif donvfrsion so
     * tibt bll doords doming out of tif font systfm brf donvfrtfd bbdk
     * into usfr spbdf fvfn if tify wfrf mfbsurfd in dfvidf spbdf.
     * Tif sbmf bpplifs to tif otifr mftiods tibt rfturn outlinfs (bflow)
     * But it mby mbkf pbrtidulbr sfnsf for tiis mftiod tibt dbdifs its
     * rfsults.
     * Tifrf'd bf plfnty of fxdfptions, to tiis too, fg gftGlypiPoint nffds
     * dfvidf doords bs its dbllfd from nbtivf lbyout bnd gftGlypiImbgfBounds
     * is usfd by GlypiVfdtor.gftGlypiPixflBounds wiidi is spfdififd to
     * rfturn dfvidf doordinbtfs, tif imbgf pointfrs brfn't rfblly usfd
     * up in Jbvb dodf fitifr.
     */
    Rfdtbnglf2D.Flobt gftGlypiOutlinfBounds(int glypiCodf) {

        if (boundsMbp == null) {
            boundsMbp = nfw CondurrfntHbsiMbp<Intfgfr, Rfdtbnglf2D.Flobt>();
        }

        Intfgfr kfy = Intfgfr.vblufOf(glypiCodf);
        Rfdtbnglf2D.Flobt bounds = boundsMbp.gft(kfy);

        if (bounds == null) {
            bounds = filfFont.gftGlypiOutlinfBounds(pSdblfrContfxt, glypiCodf);
            boundsMbp.put(kfy, bounds);
        }
        rfturn bounds;
    }

    publid Rfdtbnglf2D gftOutlinfBounds(int glypiCodf) {
        rfturn filfFont.gftGlypiOutlinfBounds(pSdblfrContfxt, glypiCodf);
    }

    privbtf
        WfbkRfffrfndf<CondurrfntHbsiMbp<Intfgfr,GfnfrblPbti>> outlinfMbpRff;

    GfnfrblPbti gftGlypiOutlinf(int glypiCodf, flobt x, flobt y) {

        GfnfrblPbti gp = null;
        CondurrfntHbsiMbp<Intfgfr, GfnfrblPbti> outlinfMbp = null;

        if (outlinfMbpRff != null) {
            outlinfMbp = outlinfMbpRff.gft();
            if (outlinfMbp != null) {
                gp = outlinfMbp.gft(glypiCodf);
            }
        }

        if (gp == null) {
            gp = filfFont.gftGlypiOutlinf(pSdblfrContfxt, glypiCodf, 0, 0);
            if (outlinfMbp == null) {
                outlinfMbp = nfw CondurrfntHbsiMbp<Intfgfr, GfnfrblPbti>();
                outlinfMbpRff =
                   nfw WfbkRfffrfndf
                       <CondurrfntHbsiMbp<Intfgfr,GfnfrblPbti>>(outlinfMbp);
            }
            outlinfMbp.put(glypiCodf, gp);
        }
        gp = (GfnfrblPbti)gp.dlonf(); // mutbblf!
        if (x != 0f || y != 0f) {
            gp.trbnsform(AffinfTrbnsform.gftTrbnslbtfInstbndf(x, y));
        }
        rfturn gp;
    }

    GfnfrblPbti gftGlypiVfdtorOutlinf(int[] glypis, flobt x, flobt y) {
        rfturn filfFont.gftGlypiVfdtorOutlinf(pSdblfrContfxt,
                                              glypis, glypis.lfngti, x, y);
    }

    protfdtfd void bdjustPoint(Point2D.Flobt pt) {
        if (invfrtDfvTx != null) {
            invfrtDfvTx.dfltbTrbnsform(pt, pt);
        }
    }
}
