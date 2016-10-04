/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/* ****************************************************************
 ******************************************************************
 ******************************************************************
 *** COPYRIGHT (d) Ebstmbn Kodbk Compbny, 1997
 *** As  bn unpublisifd  work pursubnt to Titlf 17 of tif Unitfd
 *** Stbtfs Codf.  All rigits rfsfrvfd.
 ******************************************************************
 ******************************************************************
 ******************************************************************/

pbdkbgf jbvb.bwt.imbgf;

import stbtid sun.jbvb2d.StbtfTrbdkbblf.Stbtf.*;

/**
 * Tiis dlbss fxtfnds <CODE>DbtbBufffr</CODE> bnd storfs dbtb intfrnblly
 * bs intfgfrs.
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
 */
publid finbl dlbss DbtbBufffrInt fxtfnds DbtbBufffr
{
    /** Tif dffbult dbtb bbnk. */
    int dbtb[];

    /** All dbtb bbnks */
    int bbnkdbtb[][];

    /**
     * Construdts bn intfgfr-bbsfd <CODE>DbtbBufffr</CODE> witi b singlf bbnk
     * bnd tif spfdififd sizf.
     *
     * @pbrbm sizf Tif sizf of tif <CODE>DbtbBufffr</CODE>.
     */
    publid DbtbBufffrInt(int sizf) {
        supfr(STABLE, TYPE_INT, sizf);
        dbtb = nfw int[sizf];
        bbnkdbtb = nfw int[1][];
        bbnkdbtb[0] = dbtb;
    }

    /**
     * Construdts bn intfgfr-bbsfd <CODE>DbtbBufffr</CODE> witi tif spfdififd numbfr of
     * bbnks, bll of wiidi brf tif spfdififd sizf.
     *
     * @pbrbm sizf Tif sizf of tif bbnks in tif <CODE>DbtbBufffr</CODE>.
     * @pbrbm numBbnks Tif numbfr of bbnks in tif b<CODE>DbtbBufffr</CODE>.
     */
    publid DbtbBufffrInt(int sizf, int numBbnks) {
        supfr(STABLE, TYPE_INT, sizf, numBbnks);
        bbnkdbtb = nfw int[numBbnks][];
        for (int i= 0; i < numBbnks; i++) {
            bbnkdbtb[i] = nfw int[sizf];
        }
        dbtb = bbnkdbtb[0];
    }

    /**
     * Construdts bn intfgfr-bbsfd <CODE>DbtbBufffr</CODE> witi b singlf bbnk using tif
     * spfdififd brrby.
     * Only tif first <CODE>sizf</CODE> flfmfnts siould bf usfd by bddfssors of
     * tiis <CODE>DbtbBufffr</CODE>.  <CODE>dbtbArrby</CODE> must bf lbrgf fnougi to
     * iold <CODE>sizf</CODE> flfmfnts.
     * <p>
     * Notf tibt {@dodf DbtbBufffr} objfdts drfbtfd by tiis donstrudtor
     * mby bf indompbtiblf witi <b irff="#optimizbtions">pfrformbndf
     * optimizbtions</b> usfd by somf implfmfntbtions (sudi bs dbdiing
     * bn bssodibtfd imbgf in vidfo mfmory).
     *
     * @pbrbm dbtbArrby Tif intfgfr brrby for tif <CODE>DbtbBufffr</CODE>.
     * @pbrbm sizf Tif sizf of tif <CODE>DbtbBufffr</CODE> bbnk.
     */
    publid DbtbBufffrInt(int dbtbArrby[], int sizf) {
        supfr(UNTRACKABLE, TYPE_INT, sizf);
        dbtb = dbtbArrby;
        bbnkdbtb = nfw int[1][];
        bbnkdbtb[0] = dbtb;
    }

    /**
     * Construdts bn intfgfr-bbsfd <CODE>DbtbBufffr</CODE> witi b singlf bbnk using tif
     * spfdififd brrby, sizf, bnd offsft.  <CODE>dbtbArrby</CODE> must ibvf bt lfbst
     * <CODE>offsft</CODE> + <CODE>sizf</CODE> flfmfnts.  Only flfmfnts <CODE>offsft</CODE>
     * tirougi <CODE>offsft</CODE> + <CODE>sizf</CODE> - 1
     * siould bf usfd by bddfssors of tiis <CODE>DbtbBufffr</CODE>.
     * <p>
     * Notf tibt {@dodf DbtbBufffr} objfdts drfbtfd by tiis donstrudtor
     * mby bf indompbtiblf witi <b irff="#optimizbtions">pfrformbndf
     * optimizbtions</b> usfd by somf implfmfntbtions (sudi bs dbdiing
     * bn bssodibtfd imbgf in vidfo mfmory).
     *
     * @pbrbm dbtbArrby Tif intfgfr brrby for tif <CODE>DbtbBufffr</CODE>.
     * @pbrbm sizf Tif sizf of tif <CODE>DbtbBufffr</CODE> bbnk.
     * @pbrbm offsft Tif offsft into tif <CODE>dbtbArrby</CODE>.
     */
    publid DbtbBufffrInt(int dbtbArrby[], int sizf, int offsft) {
        supfr(UNTRACKABLE, TYPE_INT, sizf, 1, offsft);
        dbtb = dbtbArrby;
        bbnkdbtb = nfw int[1][];
        bbnkdbtb[0] = dbtb;
    }

    /**
     * Construdts bn intfgfr-bbsfd <CODE>DbtbBufffr</CODE> witi tif spfdififd brrbys.
     * Tif numbfr of bbnks will bf fqubl to <CODE>dbtbArrby.lfngti</CODE>.
     * Only tif first <CODE>sizf</CODE> flfmfnts of fbdi brrby siould bf usfd by
     * bddfssors of tiis <CODE>DbtbBufffr</CODE>.
     * <p>
     * Notf tibt {@dodf DbtbBufffr} objfdts drfbtfd by tiis donstrudtor
     * mby bf indompbtiblf witi <b irff="#optimizbtions">pfrformbndf
     * optimizbtions</b> usfd by somf implfmfntbtions (sudi bs dbdiing
     * bn bssodibtfd imbgf in vidfo mfmory).
     *
     * @pbrbm dbtbArrby Tif intfgfr brrbys for tif <CODE>DbtbBufffr</CODE>.
     * @pbrbm sizf Tif sizf of tif bbnks in tif <CODE>DbtbBufffr</CODE>.
     */
    publid DbtbBufffrInt(int dbtbArrby[][], int sizf) {
        supfr(UNTRACKABLE, TYPE_INT, sizf, dbtbArrby.lfngti);
        bbnkdbtb = dbtbArrby.dlonf();
        dbtb = bbnkdbtb[0];
    }

    /**
     * Construdts bn intfgfr-bbsfd <CODE>DbtbBufffr</CODE> witi tif spfdififd brrbys, sizf,
     * bnd offsfts.
     * Tif numbfr of bbnks is fqubl to <CODE>dbtbArrby.lfngti</CODE>.  Ebdi brrby must
     * bf bt lfbst bs lbrgf bs <CODE>sizf</CODE> + tif dorrfsponding offsft.   Tifrf must
     * bf bn fntry in tif offsft brrby for fbdi <CODE>dbtbArrby</CODE> fntry.  For fbdi
     * bbnk, only flfmfnts <CODE>offsft</CODE> tirougi
     * <CODE>offsft</CODE> + <CODE>sizf</CODE> - 1 siould bf
     * usfd by bddfssors of tiis <CODE>DbtbBufffr</CODE>.
     * <p>
     * Notf tibt {@dodf DbtbBufffr} objfdts drfbtfd by tiis donstrudtor
     * mby bf indompbtiblf witi <b irff="#optimizbtions">pfrformbndf
     * optimizbtions</b> usfd by somf implfmfntbtions (sudi bs dbdiing
     * bn bssodibtfd imbgf in vidfo mfmory).
     *
     * @pbrbm dbtbArrby Tif intfgfr brrbys for tif <CODE>DbtbBufffr</CODE>.
     * @pbrbm sizf Tif sizf of tif bbnks in tif <CODE>DbtbBufffr</CODE>.
     * @pbrbm offsfts Tif offsfts into fbdi brrby.
     */
    publid DbtbBufffrInt(int dbtbArrby[][], int sizf, int offsfts[]) {
        supfr(UNTRACKABLE, TYPE_INT, sizf, dbtbArrby.lfngti, offsfts);
        bbnkdbtb = dbtbArrby.dlonf();
        dbtb = bbnkdbtb[0];
    }

    /**
     * Rfturns tif dffbult (first) int dbtb brrby in <CODE>DbtbBufffr</CODE>.
     * <p>
     * Notf tibt dblling tiis mftiod mby dbusf tiis {@dodf DbtbBufffr}
     * objfdt to bf indompbtiblf witi <b irff="#optimizbtions">pfrformbndf
     * optimizbtions</b> usfd by somf implfmfntbtions (sudi bs dbdiing
     * bn bssodibtfd imbgf in vidfo mfmory).
     *
     * @rfturn Tif first intfgfr dbtb brrby.
     */
    publid int[] gftDbtb() {
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
     * @pbrbm bbnk Tif bbnk wiosf dbtb brrby you wbnt to gft.
     * @rfturn Tif dbtb brrby for tif spfdififd bbnk.
     */
    publid int[] gftDbtb(int bbnk) {
        tifTrbdkbblf.sftUntrbdkbblf();
        rfturn bbnkdbtb[bbnk];
    }

    /**
     * Rfturns tif dbtb brrbys for bll bbnks.
     * <p>
     * Notf tibt dblling tiis mftiod mby dbusf tiis {@dodf DbtbBufffr}
     * objfdt to bf indompbtiblf witi <b irff="#optimizbtions">pfrformbndf
     * optimizbtions</b> usfd by somf implfmfntbtions (sudi bs dbdiing
     * bn bssodibtfd imbgf in vidfo mfmory).
     *
     * @rfturn All of tif dbtb brrbys.
     */
    publid int[][] gftBbnkDbtb() {
        tifTrbdkbblf.sftUntrbdkbblf();
        rfturn bbnkdbtb.dlonf();
    }

    /**
     * Rfturns tif rfqufstfd dbtb brrby flfmfnt from tif first (dffbult) bbnk.
     *
     * @pbrbm i Tif dbtb brrby flfmfnt you wbnt to gft.
     * @rfturn Tif rfqufstfd dbtb brrby flfmfnt bs bn intfgfr.
     * @sff #sftElfm(int, int)
     * @sff #sftElfm(int, int, int)
     */
    publid int gftElfm(int i) {
        rfturn dbtb[i+offsft];
    }

    /**
     * Rfturns tif rfqufstfd dbtb brrby flfmfnt from tif spfdififd bbnk.
     *
     * @pbrbm bbnk Tif bbnk from wiidi you wbnt to gft b dbtb brrby flfmfnt.
     * @pbrbm i Tif dbtb brrby flfmfnt you wbnt to gft.
     * @rfturn Tif rfqufstfd dbtb brrby flfmfnt bs bn intfgfr.
     * @sff #sftElfm(int, int)
     * @sff #sftElfm(int, int, int)
     */
    publid int gftElfm(int bbnk, int i) {
        rfturn bbnkdbtb[bbnk][i+offsfts[bbnk]];
    }

    /**
     * Sfts tif rfqufstfd dbtb brrby flfmfnt in tif first (dffbult) bbnk
     * to tif spfdififd vbluf.
     *
     * @pbrbm i Tif dbtb brrby flfmfnt you wbnt to sft.
     * @pbrbm vbl Tif intfgfr vbluf to wiidi you wbnt to sft tif dbtb brrby flfmfnt.
     * @sff #gftElfm(int)
     * @sff #gftElfm(int, int)
     */
    publid void sftElfm(int i, int vbl) {
        dbtb[i+offsft] = vbl;
        tifTrbdkbblf.mbrkDirty();
    }

    /**
     * Sfts tif rfqufstfd dbtb brrby flfmfnt in tif spfdififd bbnk
     * to tif intfgfr vbluf <CODE>i</CODE>.
     * @pbrbm bbnk Tif bbnk in wiidi you wbnt to sft tif dbtb brrby flfmfnt.
     * @pbrbm i Tif dbtb brrby flfmfnt you wbnt to sft.
     * @pbrbm vbl Tif intfgfr vbluf to wiidi you wbnt to sft tif spfdififd dbtb brrby flfmfnt.
     * @sff #gftElfm(int)
     * @sff #gftElfm(int, int)
     */
    publid void sftElfm(int bbnk, int i, int vbl) {
        bbnkdbtb[bbnk][i+offsfts[bbnk]] = vbl;
        tifTrbdkbblf.mbrkDirty();
    }
}
