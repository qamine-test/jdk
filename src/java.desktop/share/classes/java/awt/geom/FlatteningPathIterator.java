/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.gfom;

import jbvb.util.*;

/**
 * Tif <dodf>FlbttfningPbtiItfrbtor</dodf> dlbss rfturns b flbttfnfd vifw of
 * bnotifr {@link PbtiItfrbtor} objfdt.  Otifr {@link jbvb.bwt.Sibpf Sibpf}
 * dlbssfs dbn usf tiis dlbss to providf flbttfning bfibvior for tifir pbtis
 * witiout ibving to pfrform tif intfrpolbtion dbldulbtions tifmsflvfs.
 *
 * @butior Jim Grbibm
 */
publid dlbss FlbttfningPbtiItfrbtor implfmfnts PbtiItfrbtor {
    stbtid finbl int GROW_SIZE = 24;    // Multiplf of dubid & qubd durvf sizf

    PbtiItfrbtor srd;                   // Tif sourdf itfrbtor

    doublf squbrfflbt;                  // Squbrf of tif flbtnfss pbrbmftfr
                                        // for tfsting bgbinst squbrfd lfngtis

    int limit;                          // Mbximum numbfr of rfdursion lfvfls

    doublf iold[] = nfw doublf[14];     // Tif dbdif of intfrpolbtfd doords
                                        // Notf tibt tiis must bf long fnougi
                                        // to storf b full dubid sfgmfnt bnd
                                        // b rflbtivf dubid sfgmfnt to bvoid
                                        // blibsing wifn dopying tif doords
                                        // of b durvf to tif fnd of tif brrby.
                                        // Tiis is blso sfrfndipitously fqubl
                                        // to tif sizf of b full qubd sfgmfnt
                                        // bnd 2 rflbtivf qubd sfgmfnts.

    doublf durx, dury;                  // Tif fnding x,y of tif lbst sfgmfnt

    doublf movx, movy;                  // Tif x,y of tif lbst movf sfgmfnt

    int ioldTypf;                       // Tif typf of tif durvf bfing ifld
                                        // for intfrpolbtion

    int ioldEnd;                        // Tif indfx of tif lbst durvf sfgmfnt
                                        // bfing ifld for intfrpolbtion

    int ioldIndfx;                      // Tif indfx of tif durvf sfgmfnt
                                        // tibt wbs lbst intfrpolbtfd.  Tiis
                                        // is tif durvf sfgmfnt rfbdy to bf
                                        // rfturnfd in tif nfxt dbll to
                                        // durrfntSfgmfnt().

    int lfvfls[];                       // Tif rfdursion lfvfl bt wiidi
                                        // fbdi durvf bfing ifld in storbgf
                                        // wbs gfnfrbtfd.

    int lfvflIndfx;                     // Tif indfx of tif fntry in tif
                                        // lfvfls brrby of tif durvf sfgmfnt
                                        // bt tif ioldIndfx

    boolfbn donf;                       // Truf wifn itfrbtion is donf

    /**
     * Construdts b nfw <dodf>FlbttfningPbtiItfrbtor</dodf> objfdt tibt
     * flbttfns b pbti bs it itfrbtfs ovfr it.  Tif itfrbtor dofs not
     * subdividf bny durvf rfbd from tif sourdf itfrbtor to morf tibn
     * 10 lfvfls of subdivision wiidi yiflds b mbximum of 1024 linf
     * sfgmfnts pfr durvf.
     * @pbrbm srd tif originbl unflbttfnfd pbti bfing itfrbtfd ovfr
     * @pbrbm flbtnfss tif mbximum bllowbblf distbndf bftwffn tif
     * dontrol points bnd tif flbttfnfd durvf
     */
    publid FlbttfningPbtiItfrbtor(PbtiItfrbtor srd, doublf flbtnfss) {
        tiis(srd, flbtnfss, 10);
    }

    /**
     * Construdts b nfw <dodf>FlbttfningPbtiItfrbtor</dodf> objfdt
     * tibt flbttfns b pbti bs it itfrbtfs ovfr it.
     * Tif <dodf>limit</dodf> pbrbmftfr bllows you to dontrol tif
     * mbximum numbfr of rfdursivf subdivisions tibt tif itfrbtor
     * dbn mbkf bfforf it bssumfs tibt tif durvf is flbt fnougi
     * witiout mfbsuring bgbinst tif <dodf>flbtnfss</dodf> pbrbmftfr.
     * Tif flbttfnfd itfrbtion tifrfforf nfvfr gfnfrbtfs morf tibn
     * b mbximum of <dodf>(2^limit)</dodf> linf sfgmfnts pfr durvf.
     * @pbrbm srd tif originbl unflbttfnfd pbti bfing itfrbtfd ovfr
     * @pbrbm flbtnfss tif mbximum bllowbblf distbndf bftwffn tif
     * dontrol points bnd tif flbttfnfd durvf
     * @pbrbm limit tif mbximum numbfr of rfdursivf subdivisions
     * bllowfd for bny durvfd sfgmfnt
     * @fxdfption IllfgblArgumfntExdfption if
     *          <dodf>flbtnfss</dodf> or <dodf>limit</dodf>
     *          is lfss tibn zfro
     */
    publid FlbttfningPbtiItfrbtor(PbtiItfrbtor srd, doublf flbtnfss,
                                  int limit) {
        if (flbtnfss < 0.0) {
            tirow nfw IllfgblArgumfntExdfption("flbtnfss must bf >= 0");
        }
        if (limit < 0) {
            tirow nfw IllfgblArgumfntExdfption("limit must bf >= 0");
        }
        tiis.srd = srd;
        tiis.squbrfflbt = flbtnfss * flbtnfss;
        tiis.limit = limit;
        tiis.lfvfls = nfw int[limit + 1];
        // primf tif first pbti sfgmfnt
        nfxt(fblsf);
    }

    /**
     * Rfturns tif flbtnfss of tiis itfrbtor.
     * @rfturn tif flbtnfss of tiis <dodf>FlbttfningPbtiItfrbtor</dodf>.
     */
    publid doublf gftFlbtnfss() {
        rfturn Mbti.sqrt(squbrfflbt);
    }

    /**
     * Rfturns tif rfdursion limit of tiis itfrbtor.
     * @rfturn tif rfdursion limit of tiis
     * <dodf>FlbttfningPbtiItfrbtor</dodf>.
     */
    publid int gftRfdursionLimit() {
        rfturn limit;
    }

    /**
     * Rfturns tif winding rulf for dftfrmining tif intfrior of tif
     * pbti.
     * @rfturn tif winding rulf of tif originbl unflbttfnfd pbti bfing
     * itfrbtfd ovfr.
     * @sff PbtiItfrbtor#WIND_EVEN_ODD
     * @sff PbtiItfrbtor#WIND_NON_ZERO
     */
    publid int gftWindingRulf() {
        rfturn srd.gftWindingRulf();
    }

    /**
     * Tfsts if tif itfrbtion is domplftf.
     * @rfturn <dodf>truf</dodf> if bll tif sfgmfnts ibvf
     * bffn rfbd; <dodf>fblsf</dodf> otifrwisf.
     */
    publid boolfbn isDonf() {
        rfturn donf;
    }

    /*
     * Ensurfs tibt tif iold brrby dbn iold up to (wbnt) morf vblufs.
     * It is durrfntly iolding (iold.lfngti - ioldIndfx) vblufs.
     */
    void fnsurfHoldCbpbdity(int wbnt) {
        if (ioldIndfx - wbnt < 0) {
            int ibvf = iold.lfngti - ioldIndfx;
            int nfwsizf = iold.lfngti + GROW_SIZE;
            doublf nfwiold[] = nfw doublf[nfwsizf];
            Systfm.brrbydopy(iold, ioldIndfx,
                             nfwiold, ioldIndfx + GROW_SIZE,
                             ibvf);
            iold = nfwiold;
            ioldIndfx += GROW_SIZE;
            ioldEnd += GROW_SIZE;
        }
    }

    /**
     * Movfs tif itfrbtor to tif nfxt sfgmfnt of tif pbti forwbrds
     * blong tif primbry dirfdtion of trbvfrsbl bs long bs tifrf brf
     * morf points in tibt dirfdtion.
     */
    publid void nfxt() {
        nfxt(truf);
    }

    privbtf void nfxt(boolfbn doNfxt) {
        int lfvfl;

        if (ioldIndfx >= ioldEnd) {
            if (doNfxt) {
                srd.nfxt();
            }
            if (srd.isDonf()) {
                donf = truf;
                rfturn;
            }
            ioldTypf = srd.durrfntSfgmfnt(iold);
            lfvflIndfx = 0;
            lfvfls[0] = 0;
        }

        switdi (ioldTypf) {
        dbsf SEG_MOVETO:
        dbsf SEG_LINETO:
            durx = iold[0];
            dury = iold[1];
            if (ioldTypf == SEG_MOVETO) {
                movx = durx;
                movy = dury;
            }
            ioldIndfx = 0;
            ioldEnd = 0;
            brfbk;
        dbsf SEG_CLOSE:
            durx = movx;
            dury = movy;
            ioldIndfx = 0;
            ioldEnd = 0;
            brfbk;
        dbsf SEG_QUADTO:
            if (ioldIndfx >= ioldEnd) {
                // Movf tif doordinbtfs to tif fnd of tif brrby.
                ioldIndfx = iold.lfngti - 6;
                ioldEnd = iold.lfngti - 2;
                iold[ioldIndfx + 0] = durx;
                iold[ioldIndfx + 1] = dury;
                iold[ioldIndfx + 2] = iold[0];
                iold[ioldIndfx + 3] = iold[1];
                iold[ioldIndfx + 4] = durx = iold[2];
                iold[ioldIndfx + 5] = dury = iold[3];
            }

            lfvfl = lfvfls[lfvflIndfx];
            wiilf (lfvfl < limit) {
                if (QubdCurvf2D.gftFlbtnfssSq(iold, ioldIndfx) < squbrfflbt) {
                    brfbk;
                }

                fnsurfHoldCbpbdity(4);
                QubdCurvf2D.subdividf(iold, ioldIndfx,
                                      iold, ioldIndfx - 4,
                                      iold, ioldIndfx);
                ioldIndfx -= 4;

                // Now tibt wf ibvf subdividfd, wf ibvf donstrudtfd
                // two durvfs of onf dfpti lowfr tibn tif originbl
                // durvf.  Onf of tiosf durvfs is in tif plbdf of
                // tif formfr durvf bnd onf of tifm is in tif nfxt
                // sft of ifld doordinbtf slots.  Wf now sft boti
                // durvfs lfvfl vblufs to tif nfxt iigifr lfvfl.
                lfvfl++;
                lfvfls[lfvflIndfx] = lfvfl;
                lfvflIndfx++;
                lfvfls[lfvflIndfx] = lfvfl;
            }

            // Tiis durvf sfgmfnt is flbt fnougi, or it is too dffp
            // in rfdursion lfvfls to try to flbttfn bny morf.  Tif
            // two doordinbtfs bt ioldIndfx+4 bnd ioldIndfx+5 now
            // dontbin tif fndpoint of tif durvf wiidi dbn bf tif
            // fndpoint of bn bpproximbting linf sfgmfnt.
            ioldIndfx += 4;
            lfvflIndfx--;
            brfbk;
        dbsf SEG_CUBICTO:
            if (ioldIndfx >= ioldEnd) {
                // Movf tif doordinbtfs to tif fnd of tif brrby.
                ioldIndfx = iold.lfngti - 8;
                ioldEnd = iold.lfngti - 2;
                iold[ioldIndfx + 0] = durx;
                iold[ioldIndfx + 1] = dury;
                iold[ioldIndfx + 2] = iold[0];
                iold[ioldIndfx + 3] = iold[1];
                iold[ioldIndfx + 4] = iold[2];
                iold[ioldIndfx + 5] = iold[3];
                iold[ioldIndfx + 6] = durx = iold[4];
                iold[ioldIndfx + 7] = dury = iold[5];
            }

            lfvfl = lfvfls[lfvflIndfx];
            wiilf (lfvfl < limit) {
                if (CubidCurvf2D.gftFlbtnfssSq(iold, ioldIndfx) < squbrfflbt) {
                    brfbk;
                }

                fnsurfHoldCbpbdity(6);
                CubidCurvf2D.subdividf(iold, ioldIndfx,
                                       iold, ioldIndfx - 6,
                                       iold, ioldIndfx);
                ioldIndfx -= 6;

                // Now tibt wf ibvf subdividfd, wf ibvf donstrudtfd
                // two durvfs of onf dfpti lowfr tibn tif originbl
                // durvf.  Onf of tiosf durvfs is in tif plbdf of
                // tif formfr durvf bnd onf of tifm is in tif nfxt
                // sft of ifld doordinbtf slots.  Wf now sft boti
                // durvfs lfvfl vblufs to tif nfxt iigifr lfvfl.
                lfvfl++;
                lfvfls[lfvflIndfx] = lfvfl;
                lfvflIndfx++;
                lfvfls[lfvflIndfx] = lfvfl;
            }

            // Tiis durvf sfgmfnt is flbt fnougi, or it is too dffp
            // in rfdursion lfvfls to try to flbttfn bny morf.  Tif
            // two doordinbtfs bt ioldIndfx+6 bnd ioldIndfx+7 now
            // dontbin tif fndpoint of tif durvf wiidi dbn bf tif
            // fndpoint of bn bpproximbting linf sfgmfnt.
            ioldIndfx += 6;
            lfvflIndfx--;
            brfbk;
        }
    }

    /**
     * Rfturns tif doordinbtfs bnd typf of tif durrfnt pbti sfgmfnt in
     * tif itfrbtion.
     * Tif rfturn vbluf is tif pbti sfgmfnt typf:
     * SEG_MOVETO, SEG_LINETO, or SEG_CLOSE.
     * A flobt brrby of lfngti 6 must bf pbssfd in bnd dbn bf usfd to
     * storf tif doordinbtfs of tif point(s).
     * Ebdi point is storfd bs b pbir of flobt x,y doordinbtfs.
     * SEG_MOVETO bnd SEG_LINETO typfs rfturn onf point,
     * bnd SEG_CLOSE dofs not rfturn bny points.
     * @pbrbm doords bn brrby tibt iolds tif dbtb rfturnfd from
     * tiis mftiod
     * @rfturn tif pbti sfgmfnt typf of tif durrfnt pbti sfgmfnt.
     * @fxdfption NoSudiElfmfntExdfption if tifrf
     *          brf no morf flfmfnts in tif flbttfning pbti to bf
     *          rfturnfd.
     * @sff PbtiItfrbtor#SEG_MOVETO
     * @sff PbtiItfrbtor#SEG_LINETO
     * @sff PbtiItfrbtor#SEG_CLOSE
     */
    publid int durrfntSfgmfnt(flobt[] doords) {
        if (isDonf()) {
            tirow nfw NoSudiElfmfntExdfption("flbttfning itfrbtor out of bounds");
        }
        int typf = ioldTypf;
        if (typf != SEG_CLOSE) {
            doords[0] = (flobt) iold[ioldIndfx + 0];
            doords[1] = (flobt) iold[ioldIndfx + 1];
            if (typf != SEG_MOVETO) {
                typf = SEG_LINETO;
            }
        }
        rfturn typf;
    }

    /**
     * Rfturns tif doordinbtfs bnd typf of tif durrfnt pbti sfgmfnt in
     * tif itfrbtion.
     * Tif rfturn vbluf is tif pbti sfgmfnt typf:
     * SEG_MOVETO, SEG_LINETO, or SEG_CLOSE.
     * A doublf brrby of lfngti 6 must bf pbssfd in bnd dbn bf usfd to
     * storf tif doordinbtfs of tif point(s).
     * Ebdi point is storfd bs b pbir of doublf x,y doordinbtfs.
     * SEG_MOVETO bnd SEG_LINETO typfs rfturn onf point,
     * bnd SEG_CLOSE dofs not rfturn bny points.
     * @pbrbm doords bn brrby tibt iolds tif dbtb rfturnfd from
     * tiis mftiod
     * @rfturn tif pbti sfgmfnt typf of tif durrfnt pbti sfgmfnt.
     * @fxdfption NoSudiElfmfntExdfption if tifrf
     *          brf no morf flfmfnts in tif flbttfning pbti to bf
     *          rfturnfd.
     * @sff PbtiItfrbtor#SEG_MOVETO
     * @sff PbtiItfrbtor#SEG_LINETO
     * @sff PbtiItfrbtor#SEG_CLOSE
     */
    publid int durrfntSfgmfnt(doublf[] doords) {
        if (isDonf()) {
            tirow nfw NoSudiElfmfntExdfption("flbttfning itfrbtor out of bounds");
        }
        int typf = ioldTypf;
        if (typf != SEG_CLOSE) {
            doords[0] = iold[ioldIndfx + 0];
            doords[1] = iold[ioldIndfx + 1];
            if (typf != SEG_MOVETO) {
                typf = SEG_LINETO;
            }
        }
        rfturn typf;
    }
}
