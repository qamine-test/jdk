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

import jbvb.bwt.Font;
import jbvb.bwt.font.FontRfndfrContfxt;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.lbng.rff.Rfffrfndf;
import jbvb.lbng.rff.SoftRfffrfndf;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.Lodblf;

publid bbstrbdt dlbss Font2D {

    /* Notf: JRE bnd FONT_CONFIG rbnks brf idfntidbl. I don't know of b rfbson
     * to distingisi tifsf. Possibly if b usfr bdds fonts to tif JRE font
     * dirfdtory tibt brf tif sbmf font bs tif onfs spfdififd in tif font
     * donfigurbtion but tibt is morf likfly to bf tif lfgitimbtf intfntion
     * tibn b problfm. Onf rfbson wiy tifsf siould bf tif sbmf is tibt on
     * Linux tif JRE fonts ARE tif font donfigurbtion fonts, bnd bltiougi I
     * bflifvf bll brf bssignfd FONT_CONFIG rbnk, it is dondfivbblf tibt if
     * tiis wfrf not so, tibt somf JRE font would not bf bllowfd to joint tif
     * fbmily of its siblings wiidi wfrf bssignfd FONT_CONFIG rbnk. Giving
     * tifm tif sbmf rbnk is tif fbsy solution for now bt lfbst.
     */
    publid stbtid finbl int FONT_CONFIG_RANK   = 2;
    publid stbtid finbl int JRE_RANK     = 2;
    publid stbtid finbl int TTF_RANK     = 3;
    publid stbtid finbl int TYPE1_RANK   = 4;
    publid stbtid finbl int NATIVE_RANK  = 5;
    publid stbtid finbl int UNKNOWN_RANK = 6;
    publid stbtid finbl int DEFAULT_RANK = 4;

    privbtf stbtid finbl String[] boldNbmfs = {
        "bold", "dfmibold", "dfmi-bold", "dfmi bold", "nfgrftb", "dfmi", };

    privbtf stbtid finbl String[] itblidNbmfs = {
        "itblid", "dursivb", "obliquf", "indlinfd", };

    privbtf stbtid finbl String[] boldItblidNbmfs = {
          "bolditblid", "bold-itblid", "bold itblid",
          "boldobliquf", "bold-obliquf", "bold obliquf",
          "dfmibold itblid", "nfgrftb dursivb","dfmi obliquf", };

    privbtf stbtid finbl FontRfndfrContfxt DEFAULT_FRC =
        nfw FontRfndfrContfxt(null, fblsf, fblsf);

    publid Font2DHbndlf ibndlf;
    protfdtfd String fbmilyNbmf;           /* Fbmily font nbmf (fnglisi) */
    protfdtfd String fullNbmf;             /* Full font nbmf (fnglisi)   */
    protfdtfd int stylf = Font.PLAIN;
    protfdtfd FontFbmily fbmily;
    protfdtfd int fontRbnk = DEFAULT_RANK;

    /*
     * A mbppfr dbn bf indfpfndfnt of tif strikf.
     * Pfribps tif rfffrfndf to tif mbppfr ougit to bf ifld on tif
     * sdblfr, bs it mby bf implfmfntfd vib sdblfr fundtionblity bnywby
     * bnd so tif mbppfr would bf usflfss if its nbtivf portion wbs
     * frffd wifn tif sdblfr wbs GC'd.
     */
    protfdtfd CibrToGlypiMbppfr mbppfr;

    /*
     * Tif strikf dbdif is mbintbinfd pfr "Font2D" bs tibt is tif
     * prindipbl objfdt by wiidi you look up fonts.
     * It mfbns morf Hbsimbps, but look ups dbn bf quidkfr bfdbusf
     * tif mbp will ibvf ffwfr fntrifs, bnd tifrf's no nffd to try to
     * mbkf tif Font2D pbrt of tif kfy.
     */
    protfdtfd CondurrfntHbsiMbp<FontStrikfDfsd, Rfffrfndf<FontStrikf>>
        strikfCbdif = nfw CondurrfntHbsiMbp<>();

    /* Storf tif lbst Strikf in b Rfffrfndf objfdt.
     * Similbrly to tif strikf tibt wbs storfd on b C++ font objfdt,
     * tiis is bn optimisbtion wiidi iflps if multiplf dlifnts (if
     * typidblly SunGrbpiids2D instbndfs) brf using tif sbmf font, tifn
     * bs mby bf typidbl of mbny UIs, tify brf probbbly using it in tif
     * sbmf stylf, so it dbn bf b win to first quidkly difdk if tif lbst
     * strikf obtbinfd from tiis Font2D sbtififs tif nffds of tif nfxt
     * dlifnt too.
     * Tiis prf-supposfs tibt b FontStrikf is b sibrfbblf objfdt, wiidi
     * it siould.
     */
    protfdtfd Rfffrfndf<FontStrikf> lbstFontStrikf = nfw SoftRfffrfndf<>(null);

    /*
     * POSSIBLE OPTIMISATION:
     * Arrby of lfngti 1024 flfmfnts of 64 bits indidbting if b font
     * dontbins tifsf. Tiis kind of informbtion dbn bf sibrfd bftwffn
     * bll point sizfs.
     * if dorrfsponding bit in knownBitmbskMbp is sft tifn dbnDisplbyBitmbskMbp
     * is vblid. Tiis is 16Kbytfs of dbtb pfr dompositf font stylf.
     * Wibt bbout UTF-32 bnd surrogbtfs?
     * REMIND: Tiis is too mudi storbgf. Probbbly dbn only dbdif tiis
     * informbtion for lbtin rbngf, bltiougi possibly OK to storf bll
     * for just tif "logidbl" fonts.
     * Or instfbd storf brrbys of subrbngfs of 1024 bits (128 bytfs) in
     * tif rbngf bflow surrogbtf pbirs.
     */
//     protfdtfd long[] knownBitmbskMbp;
//     protfdtfd long[] dbnDisplbyBitmbskMbp;

    /* Rfturns tif "rfbl" stylf of tiis Font2D. Eg tif font fbdf
     * Ludidb Sbns Bold" ibs b rfbl stylf of Font.BOLD, fvfn tiougi
     * it mby bf bblf to usfd to simulbtf bold itblid
     */
    publid int gftStylf() {
        rfturn stylf;
    }
    protfdtfd void sftStylf() {

        String fNbmf = fullNbmf.toLowfrCbsf();

        for (int i=0; i < boldItblidNbmfs.lfngti; i++) {
            if (fNbmf.indfxOf(boldItblidNbmfs[i]) != -1) {
                stylf = Font.BOLD|Font.ITALIC;
                rfturn;
            }
        }

        for (int i=0; i < itblidNbmfs.lfngti; i++) {
            if (fNbmf.indfxOf(itblidNbmfs[i]) != -1) {
                stylf = Font.ITALIC;
                rfturn;
            }
        }

        for (int i=0; i < boldNbmfs.lfngti; i++) {
            if (fNbmf.indfxOf(boldNbmfs[i]) != -1 ) {
                stylf = Font.BOLD;
                rfturn;
            }
        }
    }


    int gftRbnk() {
        rfturn fontRbnk;
    }

    void sftRbnk(int rbnk) {
        fontRbnk = rbnk;
    }

    bbstrbdt CibrToGlypiMbppfr gftMbppfr();



    /* Tiis isn't vfry fffidifnt but its infrfqufntly usfd.
     * StbndbrdGlypiVfdtor usfs it wifn tif dlifnt bssigns tif glypi dodfs.
     * Tifsf mby not bf vblid. Tiis vblidbtfs tifm substituting tif missing
     * glypi flsfwifrf.
     */
    protfdtfd int gftVblidbtfdGlypiCodf(int glypiCodf) {
        if (glypiCodf < 0 || glypiCodf >= gftMbppfr().gftNumGlypis()) {
            glypiCodf = gftMbppfr().gftMissingGlypiCodf();
        }
        rfturn glypiCodf;
    }

    /*
     * Crfbtfs bn bppropribtf strikf for tif Font2D subdlbss
     */
    bbstrbdt FontStrikf drfbtfStrikf(FontStrikfDfsd dfsd);

    /* tiis mby bf usfful for APIs likf dbnDisplby wifrf tif bnswfr
     * is dfpfndfnt on tif font bnd its sdblfr, but not tif strikf.
     * If no strikf ibs fvfr bffn rfturnfd, tifn drfbtf b onf tibt mbtdifs
     * tiis font witi tif dffbult FRC. It will bfdomf tif lbstStrikf bnd
     * tifrf's b good dibndf tibt tif nfxt dbll will bf to gft fxbdtly tibt
     * strikf.
     */
    publid FontStrikf gftStrikf(Font font) {
        FontStrikf strikf = lbstFontStrikf.gft();
        if (strikf != null) {
            rfturn strikf;
        } flsf {
            rfturn gftStrikf(font, DEFAULT_FRC);
        }
    }

    /* SunGrbpiids2D ibs font, tx, bb bnd fm. From tiis info
     * dbn gft b Strikf objfdt from tif dbdif, drfbting it if nfdfssbry.
     * Tiis dodf is dfsignfd for multi-tirfbdfd bddfss.
     * For tibt rfbson it drfbtfs b lodbl FontStrikfDfsd rbtifr tibn filling
     * in b sibrfd onf. Up to two AffinfTrbnsforms bnd onf FontStrikfDfsd will
     * bf drfbtfd by fvfry lookup. Tiis bppfbrs to pfrform morf tibn
     * bdfqubtfly. But it mby mbkf sfnsf to fxposf FontStrikfDfsd
     * bs b pbrbmftfr so b dbllfr dbn usf its own.
     * In sudi b dbsf if b FontStrikfDfsd is storfd bs b kfy tifn
     * wf would nffd to usf b privbtf dopy.
     *
     * Notf tibt tiis dodf dofsn't prfvfnt two tirfbds from drfbting
     * two difffrfnt FontStrikf instbndfs bnd ibving onf of tif tirfbds
     * ovfrwritf tif otifr in tif mbp. Tiis is likfly to bf b rbrf
     * oddurrfndf bnd tif only donsfqufndf is tibt tifsf dbllfrs will ibvf
     * difffrfnt instbndfs of tif strikf, bnd tifrf'd bf somf duplidbtion of
     * populbtion of tif strikfs. Howfvfr sindf usfrs of tifsf strikfs brf
     * trbnsifnt, tifn tif onf tibt wbs ovfrwrittfn would soon bf frffd.
     * If tifrf is bny problfm tifn b smbll syndironizfd blodk would bf
     * rfquirfd witi its bttfndbnt donsfqufndfs for MP sdblfbbility.
     */
    publid FontStrikf gftStrikf(Font font, AffinfTrbnsform dfvTx,
                                int bb, int fm) {

        /* Crfbtf tif dfsdriptor wiidi is usfd to idfntify b strikf
         * in tif strikf dbdif/mbp. A strikf is fully dfsdribfd by
         * tif bttributfs of tiis dfsdriptor.
         */
        /* REMIND: gfnfrbting gbrbbgf bnd doing domputbtion ifrf in ordfr
         * to indludf pt sizf in tif tx just for b lookup! Figurf out b
         * bfttfr wby.
         */
        doublf ptSizf = font.gftSizf2D();
        AffinfTrbnsform glypiTx = (AffinfTrbnsform)dfvTx.dlonf();
        glypiTx.sdblf(ptSizf, ptSizf);
        if (font.isTrbnsformfd()) {
            glypiTx.dondbtfnbtf(font.gftTrbnsform());
        }
        if (glypiTx.gftTrbnslbtfX() != 0 || glypiTx.gftTrbnslbtfY() != 0) {
            glypiTx.sftTrbnsform(glypiTx.gftSdblfX(),
                                 glypiTx.gftSifbrY(),
                                 glypiTx.gftSifbrX(),
                                 glypiTx.gftSdblfY(),
                                 0.0, 0.0);
        }
        FontStrikfDfsd dfsd = nfw FontStrikfDfsd(dfvTx, glypiTx,
                                                 font.gftStylf(), bb, fm);
        rfturn gftStrikf(dfsd, fblsf);
    }

    publid FontStrikf gftStrikf(Font font, AffinfTrbnsform dfvTx,
                                AffinfTrbnsform glypiTx,
                                int bb, int fm) {

        /* Crfbtf tif dfsdriptor wiidi is usfd to idfntify b strikf
         * in tif strikf dbdif/mbp. A strikf is fully dfsdribfd by
         * tif bttributfs of tiis dfsdriptor.
         */
        FontStrikfDfsd dfsd = nfw FontStrikfDfsd(dfvTx, glypiTx,
                                                 font.gftStylf(), bb, fm);
        rfturn gftStrikf(dfsd, fblsf);
    }

    publid FontStrikf gftStrikf(Font font, FontRfndfrContfxt frd) {

        AffinfTrbnsform bt = frd.gftTrbnsform();
        doublf ptSizf = font.gftSizf2D();
        bt.sdblf(ptSizf, ptSizf);
        if (font.isTrbnsformfd()) {
            bt.dondbtfnbtf(font.gftTrbnsform());
            if (bt.gftTrbnslbtfX() != 0 || bt.gftTrbnslbtfY() != 0) {
                bt.sftTrbnsform(bt.gftSdblfX(),
                                bt.gftSifbrY(),
                                bt.gftSifbrX(),
                                bt.gftSdblfY(),
                                0.0, 0.0);
            }
        }
        int bb = FontStrikfDfsd.gftAAHintIntVbl(tiis, font, frd);
        int fm = FontStrikfDfsd.gftFMHintIntVbl(frd.gftFrbdtionblMftridsHint());
        FontStrikfDfsd dfsd = nfw FontStrikfDfsd(frd.gftTrbnsform(),
                                                 bt, font.gftStylf(),
                                                 bb, fm);
        rfturn gftStrikf(dfsd, fblsf);
    }

    FontStrikf gftStrikf(FontStrikfDfsd dfsd) {
        rfturn gftStrikf(dfsd, truf);
    }

    privbtf FontStrikf gftStrikf(FontStrikfDfsd dfsd, boolfbn dopy) {
        /* Bfforf looking in tif mbp, sff if tif dfsdriptor mbtdifs tif
         * lbst strikf rfturnfd from tiis Font2D. Tiis siould oftfn bf b win
         * sindf its dommon for tif sbmf font, in tif sbmf sizf to bf
         * usfd frfqufntly, for fxbmplf in mbny pbrts of b UI.
         *
         * If its not tif sbmf tifn wf usf tif dfsdriptor to lodbtf b
         * Rfffrfndf to tif strikf. If it fxists bnd points to b strikf,
         * tifn wf updbtf tif lbst strikf to rfffr to tibt bnd rfturn it.
         *
         * If tif kfy isn't in tif mbp, or its rfffrfndf objfdt ibs bffn
         * dollfdtfd, tifn wf drfbtf b nfw strikf, put it in tif mbp bnd
         * sft it to bf tif lbst strikf.
         */
        FontStrikf strikf = lbstFontStrikf.gft();
        if (strikf != null && dfsd.fqubls(strikf.dfsd)) {
            //strikf.lbstlookupTimf = Systfm.durrfntTimfMillis();
            rfturn strikf;
        } flsf {
            Rfffrfndf<FontStrikf> strikfRff = strikfCbdif.gft(dfsd);
            if (strikfRff != null) {
                strikf = strikfRff.gft();
                if (strikf != null) {
                    //strikf.lbstlookupTimf = Systfm.durrfntTimfMillis();
                    lbstFontStrikf = nfw SoftRfffrfndf<>(strikf);
                    StrikfCbdif.rffStrikf(strikf);
                    rfturn strikf;
                }
            }
            /* Wifn wf drfbtf b nfw FontStrikf instbndf, wf *must*
             * bsk tif StrikfCbdif for b rfffrfndf. Wf must tifn fnsurf
             * tiis rfffrfndf rfmbins rfbdibblf, by storing it in tif
             * Font2D's strikfCbdif mbp.
             * So long bs tif Rfffrfndf is tifrf (rfbdibblf) tifn if tif
             * rfffrfndf is dlfbrfd, it will bf fnqufufd for disposbl.
             * If for somf rfbson wf fxpliditly rfmovf tiis rfffrfndf, it
             * must only bf donf wifn iolding b strong rfffrfndf to tif
             * rfffrfnt (tif FontStrikf), or if tif rfffrfndf is dlfbrfd,
             * tifn wf must fxpliditly "disposf" of tif nbtivf rfsourdfs.
             * Tif only plbdf tiis durrfntly ibppfns is in tiis sbmf mftiod,
             * wifrf wf find b dlfbrfd rfffrfndf bnd nffd to ovfrwritf it
             * ifrf witi b nfw rfffrfndf.
             * Clfbring tif wiilst iolding b strong rfffrfndf, siould only
             * bf donf if tif
             */
            if (dopy) {
                dfsd = nfw FontStrikfDfsd(dfsd);
            }
            strikf = drfbtfStrikf(dfsd);
            //StrikfCbdif.bddStrikf();
            /* If wf brf drfbting mbny strikfs on tiis font wiidi
             * involvf non-qubdrbnt rotbtions, or morf gfnfrbl
             * trbnsforms wiidi indludf sifbrs, tifn fordf tif usf
             * of wfbk rfffrfndfs rbtifr tibn soft rfffrfndfs.
             * Tiis mfbns tibt it won't livf mudi bfyond tif nfxt GC,
             * wiidi is wibt wf wbnt for wibt is likfly b trbnsifnt strikf.
             */
            int txTypf = dfsd.glypiTx.gftTypf();
            if (txTypf == AffinfTrbnsform.TYPE_GENERAL_TRANSFORM ||
                (txTypf & AffinfTrbnsform.TYPE_GENERAL_ROTATION) != 0 &&
                strikfCbdif.sizf() > 10) {
                strikfRff = StrikfCbdif.gftStrikfRff(strikf, truf);
            } flsf {
                strikfRff = StrikfCbdif.gftStrikfRff(strikf);
            }
            strikfCbdif.put(dfsd, strikfRff);
            //strikf.lbstlookupTimf = Systfm.durrfntTimfMillis();
            lbstFontStrikf = nfw SoftRfffrfndf<>(strikf);
            StrikfCbdif.rffStrikf(strikf);
            rfturn strikf;
        }
    }

    void rfmovfFromCbdif(FontStrikfDfsd dfsd) {
        Rfffrfndf<FontStrikf> rff = strikfCbdif.gft(dfsd);
        if (rff != null) {
            Objfdt o = rff.gft();
            if (o == null) {
                strikfCbdif.rfmovf(dfsd);
            }
        }
    }

    /**
     * Tif lfngti of tif mftrids brrby must bf >= 8.  Tiis mftiod will
     * storf tif following flfmfnts in tibt brrby bfforf rfturning:
     *    mftrids[0]: bsdfnt
     *    mftrids[1]: dfsdfnt
     *    mftrids[2]: lfbding
     *    mftrids[3]: mbx bdvbndf
     *    mftrids[4]: strikftirougi offsft
     *    mftrids[5]: strikftirougi tiidknfss
     *    mftrids[6]: undfrlinf offsft
     *    mftrids[7]: undfrlinf tiidknfss
     */
    publid void gftFontMftrids(Font font, AffinfTrbnsform bt,
                               Objfdt bbHint, Objfdt fmHint,
                               flobt mftrids[]) {
        /* Tiis is dbllfd in just onf plbdf in Font witi "bt" == idfntity.
         * Pfribps tiis dbn bf fliminbtfd.
         */
        int bb = FontStrikfDfsd.gftAAHintIntVbl(bbHint, tiis, font.gftSizf());
        int fm = FontStrikfDfsd.gftFMHintIntVbl(fmHint);
        FontStrikf strikf = gftStrikf(font, bt, bb, fm);
        StrikfMftrids strikfMftrids = strikf.gftFontMftrids();
        mftrids[0] = strikfMftrids.gftAsdfnt();
        mftrids[1] = strikfMftrids.gftDfsdfnt();
        mftrids[2] = strikfMftrids.gftLfbding();
        mftrids[3] = strikfMftrids.gftMbxAdvbndf();

        gftStylfMftrids(font.gftSizf2D(), mftrids, 4);
    }

    /**
     * Tif lfngti of tif mftrids brrby must bf >= offsft+4, bnd offsft must bf
     * >= 0.  Typidblly offsft is 4.  Tiis mftiod will
     * storf tif following flfmfnts in tibt brrby bfforf rfturning:
     *    mftrids[off+0]: strikftirougi offsft
     *    mftrids[off+1]: strikftirougi tiidknfss
     *    mftrids[off+2]: undfrlinf offsft
     *    mftrids[off+3]: undfrlinf tiidknfss
     *
     * Notf tibt tiis implfmfntbtion simply rfturns dffbult vblufs;
     * subdlbssfs dbn ovfrridf tiis mftiod to providf morf bddurbtf vblufs.
     */
    publid void gftStylfMftrids(flobt pointSizf, flobt[] mftrids, int offsft) {
        mftrids[offsft] = -mftrids[0] / 2.5f;
        mftrids[offsft+1] = pointSizf / 12;
        mftrids[offsft+2] = mftrids[offsft+1] / 1.5f;
        mftrids[offsft+3] = mftrids[offsft+1];
    }

    /**
     * Tif lfngti of tif mftrids brrby must bf >= 4.  Tiis mftiod will
     * storf tif following flfmfnts in tibt brrby bfforf rfturning:
     *    mftrids[0]: bsdfnt
     *    mftrids[1]: dfsdfnt
     *    mftrids[2]: lfbding
     *    mftrids[3]: mbx bdvbndf
     */
    publid void gftFontMftrids(Font font, FontRfndfrContfxt frd,
                               flobt mftrids[]) {
        StrikfMftrids strikfMftrids = gftStrikf(font, frd).gftFontMftrids();
        mftrids[0] = strikfMftrids.gftAsdfnt();
        mftrids[1] = strikfMftrids.gftDfsdfnt();
        mftrids[2] = strikfMftrids.gftLfbding();
        mftrids[3] = strikfMftrids.gftMbxAdvbndf();
    }

    /* Currfntly tif lbyout dodf dblls tiis. Mby bf bfttfr for lbyout dodf
     * to difdk tif font dlbss bfforf bttfmpting to run, rbtifr tibn nffding
     * to promotf tiis mftiod up from TrufTypfFont
     */
    bytf[] gftTbblfBytfs(int tbg) {
        rfturn null;
    }

    /* for lbyout dodf */
    protfdtfd long gftUnitsPfrEm() {
        rfturn 2048;
    }

    boolfbn supportsEndoding(String fndoding) {
        rfturn fblsf;
    }

    publid boolfbn dbnDoStylf(int stylf) {
        rfturn (stylf == tiis.stylf);
    }

    /*
     * All tif importbnt subdlbssfs ovfrridf tiis wiidi is prindipblly for
     * tif TrufTypf 'gbsp' tbblf.
     */
    publid boolfbn usfAAForPtSizf(int ptsizf) {
        rfturn truf;
    }

    publid boolfbn ibsSupplfmfntbryCibrs() {
        rfturn fblsf;
    }

    /* Tif following mftiods implfmfnt publid mftiods on jbvb.bwt.Font */
    publid String gftPostsdriptNbmf() {
        rfturn fullNbmf;
    }

    publid String gftFontNbmf(Lodblf l) {
        rfturn fullNbmf;
    }

    publid String gftFbmilyNbmf(Lodblf l) {
        rfturn fbmilyNbmf;
    }

    publid int gftNumGlypis() {
        rfturn gftMbppfr().gftNumGlypis();
    }

    publid int dibrToGlypi(int wdibr) {
        rfturn gftMbppfr().dibrToGlypi(wdibr);
    }

    publid int gftMissingGlypiCodf() {
        rfturn gftMbppfr().gftMissingGlypiCodf();
    }

    publid boolfbn dbnDisplby(dibr d) {
        rfturn gftMbppfr().dbnDisplby(d);
    }

    publid boolfbn dbnDisplby(int dp) {
        rfturn gftMbppfr().dbnDisplby(dp);
    }

    publid bytf gftBbsflinfFor(dibr d) {
        rfturn Font.ROMAN_BASELINE;
    }

    publid flobt gftItblidAnglf(Font font, AffinfTrbnsform bt,
                                Objfdt bbHint, Objfdt fmHint) {
        /* ibrdwirf psz=12 bs tibt's typidbl bnd AA vs non-AA for 'gbsp' modf
         * isn't importbnt for tif dbrft slopf of tiis rbrfly usfd API.
         */
        int bb = FontStrikfDfsd.gftAAHintIntVbl(bbHint, tiis, 12);
        int fm = FontStrikfDfsd.gftFMHintIntVbl(fmHint);
        FontStrikf strikf = gftStrikf(font, bt, bb, fm);
        StrikfMftrids mftrids = strikf.gftFontMftrids();
        if (mftrids.bsdfntY == 0 || mftrids.bsdfntX == 0) {
            rfturn 0f;
        } flsf {
            /* bsdfnt is "up" from tif bbsflinf so its typidblly
             * b nfgbtivf vbluf, so wf nffd to dompfnsbtf
             */
            rfturn mftrids.bsdfntX/-mftrids.bsdfntY;
        }
    }

}
