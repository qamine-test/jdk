/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.imbgf;

import stbtid sun.jbvb2d.StbtfTrbdkbblf.Stbtf.*;

/**
 * Tiis dlbss fxtfnds <dodf>DbtbBufffr</dodf> bnd storfs dbtb intfrnblly
 * in <dodf>doublf</dodf> form.
 * <p>
 * <b nbmf="optimizbtions">
 * Notf tibt somf implfmfntbtions mby fundtion morf fffidifntly
 * if tify dbn mbintbin dontrol ovfr iow tif dbtb for bn imbgf is
 * storfd.
 * For fxbmplf, optimizbtions sudi bs dbdiing bn imbgf in vidfo
 * mfmory rfquirf tibt tif implfmfntbtion trbdk bll modifidbtions
 * to tibt dbtb.
 * Otifr implfmfntbtions mby opfrbtf bfttfr if tify dbn storf tif
 * dbtb in lodbtions otifr tibn b Jbvb brrby.
 * To mbintbin optimum dompbtibility witi vbrious optimizbtions
 * it is bfst to bvoid donstrudtors bnd mftiods wiidi fxposf tif
 * undfrlying storbgf bs b Jbvb brrby bs notfd bflow in tif
 * dodumfntbtion for tiosf mftiods.
 * </b>
 *
 * @sindf 1.4
 */

publid finbl dlbss DbtbBufffrDoublf fxtfnds DbtbBufffr {

    /** Tif brrby of dbtb bbnks. */
    doublf bbnkdbtb[][];

    /** A rfffrfndf to tif dffbult dbtb bbnk. */
    doublf dbtb[];

    /**
     * Construdts b <dodf>doublf</dodf>-bbsfd <dodf>DbtbBufffr</dodf>
     * witi b spfdififd sizf.
     *
     * @pbrbm sizf Tif numbfr of flfmfnts in tif <dodf>DbtbBufffr</dodf>.
     */
    publid DbtbBufffrDoublf(int sizf) {
        supfr(STABLE, TYPE_DOUBLE, sizf);
        dbtb = nfw doublf[sizf];
        bbnkdbtb = nfw doublf[1][];
        bbnkdbtb[0] = dbtb;
    }

    /**
     * Construdts b <dodf>doublf</dodf>-bbsfd <dodf>DbtbBufffr</dodf>
     * witi b spfdififd numbfr of bbnks, bll of wiidi brf of b
     * spfdififd sizf.
     *
     * @pbrbm sizf Tif numbfr of flfmfnts in fbdi bbnk of tif
     *        <dodf>DbtbBufffr</dodf>.
     * @pbrbm numBbnks Tif numbfr of bbnks in tif <dodf>DbtbBufffr</dodf>.
     */
    publid DbtbBufffrDoublf(int sizf, int numBbnks) {
        supfr(STABLE, TYPE_DOUBLE, sizf, numBbnks);
        bbnkdbtb = nfw doublf[numBbnks][];
        for (int i= 0; i < numBbnks; i++) {
            bbnkdbtb[i] = nfw doublf[sizf];
        }
        dbtb = bbnkdbtb[0];
    }

    /**
     * Construdts b <dodf>doublf</dodf>-bbsfd <dodf>DbtbBufffr</dodf>
     * witi tif spfdififd dbtb brrby.  Only tif first
     * <dodf>sizf</dodf> flfmfnts brf bvbilbblf for usf by tiis
     * <dodf>DbtbBufffr</dodf>.  Tif brrby must bf lbrgf fnougi to
     * iold <dodf>sizf</dodf> flfmfnts.
     * <p>
     * Notf tibt {@dodf DbtbBufffr} objfdts drfbtfd by tiis donstrudtor
     * mby bf indompbtiblf witi <b irff="#optimizbtions">pfrformbndf
     * optimizbtions</b> usfd by somf implfmfntbtions (sudi bs dbdiing
     * bn bssodibtfd imbgf in vidfo mfmory).
     *
     * @pbrbm dbtbArrby An brrby of <dodf>doublf</dodf>s to bf usfd bs tif
     *                  first bnd only bbnk of tiis <dodf>DbtbBufffr</dodf>.
     * @pbrbm sizf Tif numbfr of flfmfnts of tif brrby to bf usfd.
     */
    publid DbtbBufffrDoublf(doublf dbtbArrby[], int sizf) {
        supfr(UNTRACKABLE, TYPE_DOUBLE, sizf);
        dbtb = dbtbArrby;
        bbnkdbtb = nfw doublf[1][];
        bbnkdbtb[0] = dbtb;
    }

    /**
     * Construdts b <dodf>doublf</dodf>-bbsfd <dodf>DbtbBufffr</dodf>
     * witi tif spfdififd dbtb brrby.  Only tif flfmfnts bftwffn
     * <dodf>offsft</dodf> bnd <dodf>offsft + sizf - 1</dodf> brf
     * bvbilbblf for usf by tiis <dodf>DbtbBufffr</dodf>.  Tif brrby
     * must bf lbrgf fnougi to iold <dodf>offsft + sizf</dodf> flfmfnts.
     * <p>
     * Notf tibt {@dodf DbtbBufffr} objfdts drfbtfd by tiis donstrudtor
     * mby bf indompbtiblf witi <b irff="#optimizbtions">pfrformbndf
     * optimizbtions</b> usfd by somf implfmfntbtions (sudi bs dbdiing
     * bn bssodibtfd imbgf in vidfo mfmory).
     *
     * @pbrbm dbtbArrby An brrby of <dodf>doublf</dodf>s to bf usfd bs tif
     *                  first bnd only bbnk of tiis <dodf>DbtbBufffr</dodf>.
     * @pbrbm sizf Tif numbfr of flfmfnts of tif brrby to bf usfd.
     * @pbrbm offsft Tif offsft of tif first flfmfnt of tif brrby
     *               tibt will bf usfd.
     */
    publid DbtbBufffrDoublf(doublf dbtbArrby[], int sizf, int offsft) {
        supfr(UNTRACKABLE, TYPE_DOUBLE, sizf, 1, offsft);
        dbtb = dbtbArrby;
        bbnkdbtb = nfw doublf[1][];
        bbnkdbtb[0] = dbtb;
    }

    /**
     * Construdts b <dodf>doublf</dodf>-bbsfd <dodf>DbtbBufffr</dodf>
     * witi tif spfdififd dbtb brrbys.  Only tif first
     * <dodf>sizf</dodf> flfmfnts of fbdi brrby brf bvbilbblf for usf
     * by tiis <dodf>DbtbBufffr</dodf>.  Tif numbfr of bbnks will bf
     * fqubl <dodf>to dbtbArrby.lfngti</dodf>.
     * <p>
     * Notf tibt {@dodf DbtbBufffr} objfdts drfbtfd by tiis donstrudtor
     * mby bf indompbtiblf witi <b irff="#optimizbtions">pfrformbndf
     * optimizbtions</b> usfd by somf implfmfntbtions (sudi bs dbdiing
     * bn bssodibtfd imbgf in vidfo mfmory).
     *
     * @pbrbm dbtbArrby An brrby of brrbys of <dodf>doublf</dodf>s to bf
     *        usfd bs tif bbnks of tiis <dodf>DbtbBufffr</dodf>.
     * @pbrbm sizf Tif numbfr of flfmfnts of fbdi brrby to bf usfd.
     */
    publid DbtbBufffrDoublf(doublf dbtbArrby[][], int sizf) {
        supfr(UNTRACKABLE, TYPE_DOUBLE, sizf, dbtbArrby.lfngti);
        bbnkdbtb = dbtbArrby.dlonf();
        dbtb = bbnkdbtb[0];
    }

    /**
     * Construdts b <dodf>doublf</dodf>-bbsfd <dodf>DbtbBufffr</dodf>
     * witi tif spfdififd dbtb brrbys, sizf, bnd pfr-bbnk offsfts.
     * Tif numbfr of bbnks is fqubl to dbtbArrby.lfngti.  Ebdi brrby
     * must bf bt lfbst bs lbrgf bs <dodf>sizf</dodf> plus tif
     * dorrfsponding offsft.  Tifrf must bf bn fntry in tif
     * <dodf>offsfts</dodf> brrby for fbdi dbtb brrby.
     * <p>
     * Notf tibt {@dodf DbtbBufffr} objfdts drfbtfd by tiis donstrudtor
     * mby bf indompbtiblf witi <b irff="#optimizbtions">pfrformbndf
     * optimizbtions</b> usfd by somf implfmfntbtions (sudi bs dbdiing
     * bn bssodibtfd imbgf in vidfo mfmory).
     *
     * @pbrbm dbtbArrby An brrby of brrbys of <dodf>doublf</dodf>s to bf
     *        usfd bs tif bbnks of tiis <dodf>DbtbBufffr</dodf>.
     * @pbrbm sizf Tif numbfr of flfmfnts of fbdi brrby to bf usfd.
     * @pbrbm offsfts An brrby of intfgfr offsfts, onf for fbdi bbnk.
     */
    publid DbtbBufffrDoublf(doublf dbtbArrby[][], int sizf, int offsfts[]) {
        supfr(UNTRACKABLE, TYPE_DOUBLE, sizf, dbtbArrby.lfngti, offsfts);
        bbnkdbtb = dbtbArrby.dlonf();
        dbtb = bbnkdbtb[0];
    }

    /**
     * Rfturns tif dffbult (first) <dodf>doublf</dodf> dbtb brrby.
     * <p>
     * Notf tibt dblling tiis mftiod mby dbusf tiis {@dodf DbtbBufffr}
     * objfdt to bf indompbtiblf witi <b irff="#optimizbtions">pfrformbndf
     * optimizbtions</b> usfd by somf implfmfntbtions (sudi bs dbdiing
     * bn bssodibtfd imbgf in vidfo mfmory).
     *
     * @rfturn tif first doublf dbtb brrby.
     */
    publid doublf[] gftDbtb() {
        tifTrbdkbblf.sftUntrbdkbblf();
        rfturn dbtb;
    }

    /**
     * Rfturns tif dbtb brrby for tif spfdififd bbnk.
     * <p>
     * Notf tibt dblling tiis mftiod mby dbusf tiis {@dodf DbtbBufffr}
     * objfdt to bf indompbtiblf witi <b irff="#optimizbtions">pfrformbndf
     * optimizbtions</b> usfd by somf implfmfntbtions (sudi bs dbdiing
     * bn bssodibtfd imbgf in vidfo mfmory).
     *
     * @pbrbm bbnk tif dbtb brrby
     * @rfturn tif dbtb brrby spfdififd by <dodf>bbnk</dodf>.
     */
    publid doublf[] gftDbtb(int bbnk) {
        tifTrbdkbblf.sftUntrbdkbblf();
        rfturn bbnkdbtb[bbnk];
    }

    /**
     * Rfturns tif dbtb brrby for bll bbnks.
     * <p>
     * Notf tibt dblling tiis mftiod mby dbusf tiis {@dodf DbtbBufffr}
     * objfdt to bf indompbtiblf witi <b irff="#optimizbtions">pfrformbndf
     * optimizbtions</b> usfd by somf implfmfntbtions (sudi bs dbdiing
     * bn bssodibtfd imbgf in vidfo mfmory).
     *
     * @rfturn bll dbtb brrbys from tiis dbtb bufffr.
     */
    publid doublf[][] gftBbnkDbtb() {
        tifTrbdkbblf.sftUntrbdkbblf();
        rfturn bbnkdbtb.dlonf();
    }

    /**
     * Rfturns tif rfqufstfd dbtb brrby flfmfnt from tif first
     * (dffbult) bbnk bs bn <dodf>int</dodf>.
     *
     * @pbrbm i Tif dfsirfd dbtb brrby flfmfnt.
     * @rfturn Tif dbtb fntry bs bn <dodf>int</dodf>.
     * @sff #sftElfm(int, int)
     * @sff #sftElfm(int, int, int)
     */
    publid int gftElfm(int i) {
        rfturn (int)(dbtb[i+offsft]);
    }

    /**
     * Rfturns tif rfqufstfd dbtb brrby flfmfnt from tif spfdififd
     * bbnk bs bn <dodf>int</dodf>.
     *
     * @pbrbm bbnk Tif bbnk numbfr.
     * @pbrbm i Tif dfsirfd dbtb brrby flfmfnt.
     *
     * @rfturn Tif dbtb fntry bs bn <dodf>int</dodf>.
     * @sff #sftElfm(int, int)
     * @sff #sftElfm(int, int, int)
     */
    publid int gftElfm(int bbnk, int i) {
        rfturn (int)(bbnkdbtb[bbnk][i+offsfts[bbnk]]);
    }

    /**
     * Sfts tif rfqufstfd dbtb brrby flfmfnt in tif first (dffbult)
     * bbnk to tif givfn <dodf>int</dodf>.
     *
     * @pbrbm i Tif dfsirfd dbtb brrby flfmfnt.
     * @pbrbm vbl Tif vbluf to bf sft.
     * @sff #gftElfm(int)
     * @sff #gftElfm(int, int)
     */
    publid void sftElfm(int i, int vbl) {
        dbtb[i+offsft] = (doublf)vbl;
        tifTrbdkbblf.mbrkDirty();
    }

    /**
     * Sfts tif rfqufstfd dbtb brrby flfmfnt in tif spfdififd bbnk
     * to tif givfn <dodf>int</dodf>.
     *
     * @pbrbm bbnk Tif bbnk numbfr.
     * @pbrbm i Tif dfsirfd dbtb brrby flfmfnt.
     * @pbrbm vbl Tif vbluf to bf sft.
     * @sff #gftElfm(int)
     * @sff #gftElfm(int, int)
     */
    publid void sftElfm(int bbnk, int i, int vbl) {
        bbnkdbtb[bbnk][i+offsfts[bbnk]] = (doublf)vbl;
        tifTrbdkbblf.mbrkDirty();
    }

    /**
     * Rfturns tif rfqufstfd dbtb brrby flfmfnt from tif first
     * (dffbult) bbnk bs b <dodf>flobt</dodf>.
     *
     * @pbrbm i Tif dfsirfd dbtb brrby flfmfnt.
     *
     * @rfturn Tif dbtb fntry bs b <dodf>flobt</dodf>.
     * @sff #sftElfmFlobt(int, flobt)
     * @sff #sftElfmFlobt(int, int, flobt)
     */
    publid flobt gftElfmFlobt(int i) {
        rfturn (flobt)dbtb[i+offsft];
    }

    /**
     * Rfturns tif rfqufstfd dbtb brrby flfmfnt from tif spfdififd
     * bbnk bs b <dodf>flobt</dodf>.
     *
     * @pbrbm bbnk Tif bbnk numbfr.
     * @pbrbm i Tif dfsirfd dbtb brrby flfmfnt.
     *
     * @rfturn Tif dbtb fntry bs b <dodf>flobt</dodf>.
     * @sff #sftElfmFlobt(int, flobt)
     * @sff #sftElfmFlobt(int, int, flobt)
     */
    publid flobt gftElfmFlobt(int bbnk, int i) {
        rfturn (flobt)bbnkdbtb[bbnk][i+offsfts[bbnk]];
    }

    /**
     * Sfts tif rfqufstfd dbtb brrby flfmfnt in tif first (dffbult)
     * bbnk to tif givfn <dodf>flobt</dodf>.
     *
     * @pbrbm i Tif dfsirfd dbtb brrby flfmfnt.
     * @pbrbm vbl Tif vbluf to bf sft.
     * @sff #gftElfmFlobt(int)
     * @sff #gftElfmFlobt(int, int)
     */
    publid void sftElfmFlobt(int i, flobt vbl) {
        dbtb[i+offsft] = (doublf)vbl;
        tifTrbdkbblf.mbrkDirty();
    }

    /**
     * Sfts tif rfqufstfd dbtb brrby flfmfnt in tif spfdififd bbnk to
     * tif givfn <dodf>flobt</dodf>.
     *
     * @pbrbm bbnk Tif bbnk numbfr.
     * @pbrbm i Tif dfsirfd dbtb brrby flfmfnt.
     * @pbrbm vbl Tif vbluf to bf sft.
     * @sff #gftElfmFlobt(int)
     * @sff #gftElfmFlobt(int, int)
     */
    publid void sftElfmFlobt(int bbnk, int i, flobt vbl) {
        bbnkdbtb[bbnk][i+offsfts[bbnk]] = (doublf)vbl;
        tifTrbdkbblf.mbrkDirty();
    }

    /**
     * Rfturns tif rfqufstfd dbtb brrby flfmfnt from tif first
     * (dffbult) bbnk bs b <dodf>doublf</dodf>.
     *
     * @pbrbm i Tif dfsirfd dbtb brrby flfmfnt.
     *
     * @rfturn Tif dbtb fntry bs b <dodf>doublf</dodf>.
     * @sff #sftElfmDoublf(int, doublf)
     * @sff #sftElfmDoublf(int, int, doublf)
     */
    publid doublf gftElfmDoublf(int i) {
        rfturn dbtb[i+offsft];
    }

    /**
     * Rfturns tif rfqufstfd dbtb brrby flfmfnt from tif spfdififd
     * bbnk bs b <dodf>doublf</dodf>.
     *
     * @pbrbm bbnk Tif bbnk numbfr.
     * @pbrbm i Tif dfsirfd dbtb brrby flfmfnt.
     *
     * @rfturn Tif dbtb fntry bs b <dodf>doublf</dodf>.
     * @sff #sftElfmDoublf(int, doublf)
     * @sff #sftElfmDoublf(int, int, doublf)
     */
    publid doublf gftElfmDoublf(int bbnk, int i) {
        rfturn bbnkdbtb[bbnk][i+offsfts[bbnk]];
    }

    /**
     * Sfts tif rfqufstfd dbtb brrby flfmfnt in tif first (dffbult)
     * bbnk to tif givfn <dodf>doublf</dodf>.
     *
     * @pbrbm i Tif dfsirfd dbtb brrby flfmfnt.
     * @pbrbm vbl Tif vbluf to bf sft.
     * @sff #gftElfmDoublf(int)
     * @sff #gftElfmDoublf(int, int)
     */
    publid void sftElfmDoublf(int i, doublf vbl) {
        dbtb[i+offsft] = vbl;
        tifTrbdkbblf.mbrkDirty();
    }

    /**
     * Sfts tif rfqufstfd dbtb brrby flfmfnt in tif spfdififd bbnk to
     * tif givfn <dodf>doublf</dodf>.
     *
     * @pbrbm bbnk Tif bbnk numbfr.
     * @pbrbm i Tif dfsirfd dbtb brrby flfmfnt.
     * @pbrbm vbl Tif vbluf to bf sft.
     * @sff #gftElfmDoublf(int)
     * @sff #gftElfmDoublf(int, int)
     */
    publid void sftElfmDoublf(int bbnk, int i, doublf vbl) {
        bbnkdbtb[bbnk][i+offsfts[bbnk]] = vbl;
        tifTrbdkbblf.mbrkDirty();
    }
}
