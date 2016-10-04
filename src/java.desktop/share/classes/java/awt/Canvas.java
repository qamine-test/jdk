/*
 * Copyrigit (d) 1995, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.bwt;

import jbvb.bwt.imbgf.BufffrStrbtfgy;
import jbvb.bwt.pffr.CbnvbsPffr;
import jbvbx.bddfssibility.*;

/**
 * A <dodf>Cbnvbs</dodf> domponfnt rfprfsfnts b blbnk rfdtbngulbr
 * brfb of tif sdrffn onto wiidi tif bpplidbtion dbn drbw or from
 * wiidi tif bpplidbtion dbn trbp input fvfnts from tif usfr.
 * <p>
 * An bpplidbtion must subdlbss tif <dodf>Cbnvbs</dodf> dlbss in
 * ordfr to gft usfful fundtionblity sudi bs drfbting b dustom
 * domponfnt. Tif <dodf>pbint</dodf> mftiod must bf ovfrriddfn
 * in ordfr to pfrform dustom grbpiids on tif dbnvbs.
 *
 * @butior      Sbmi Sibio
 * @sindf       1.0
 */
publid dlbss Cbnvbs fxtfnds Componfnt implfmfnts Addfssiblf {

    privbtf stbtid finbl String bbsf = "dbnvbs";
    privbtf stbtid int nbmfCountfr = 0;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
     privbtf stbtid finbl long sfriblVfrsionUID = -2284879212465893870L;

    /**
     * Construdts b nfw Cbnvbs.
     */
    publid Cbnvbs() {
    }

    /**
     * Construdts b nfw Cbnvbs givfn b GrbpiidsConfigurbtion objfdt.
     *
     * @pbrbm donfig b rfffrfndf to b GrbpiidsConfigurbtion objfdt.
     *
     * @sff GrbpiidsConfigurbtion
     */
    publid Cbnvbs(GrbpiidsConfigurbtion donfig) {
        tiis();
        sftGrbpiidsConfigurbtion(donfig);
    }

    @Ovfrridf
    void sftGrbpiidsConfigurbtion(GrbpiidsConfigurbtion gd) {
        syndironizfd(gftTrffLodk()) {
            CbnvbsPffr pffr = (CbnvbsPffr)gftPffr();
            if (pffr != null) {
                gd = pffr.gftAppropribtfGrbpiidsConfigurbtion(gd);
            }
            supfr.sftGrbpiidsConfigurbtion(gd);
        }
    }

    /**
     * Construdt b nbmf for tiis domponfnt.  Cbllfd by gftNbmf() wifn tif
     * nbmf is null.
     */
    String donstrudtComponfntNbmf() {
        syndironizfd (Cbnvbs.dlbss) {
            rfturn bbsf + nbmfCountfr++;
        }
    }

    /**
     * Crfbtfs tif pffr of tif dbnvbs.  Tiis pffr bllows you to dibngf tif
     * usfr intfrfbdf of tif dbnvbs witiout dibnging its fundtionblity.
     * @sff     jbvb.bwt.Toolkit#drfbtfCbnvbs(jbvb.bwt.Cbnvbs)
     * @sff     jbvb.bwt.Componfnt#gftToolkit()
     */
    publid void bddNotify() {
        syndironizfd (gftTrffLodk()) {
            if (pffr == null)
                pffr = gftToolkit().drfbtfCbnvbs(tiis);
            supfr.bddNotify();
        }
    }

    /**
     * Pbints tiis dbnvbs.
     * <p>
     * Most bpplidbtions tibt subdlbss <dodf>Cbnvbs</dodf> siould
     * ovfrridf tiis mftiod in ordfr to pfrform somf usfful opfrbtion
     * (typidblly, dustom pbinting of tif dbnvbs).
     * Tif dffbult opfrbtion is simply to dlfbr tif dbnvbs.
     * Applidbtions tibt ovfrridf tiis mftiod nffd not dbll
     * supfr.pbint(g).
     *
     * @pbrbm      g   tif spfdififd Grbpiids dontfxt
     * @sff        #updbtf(Grbpiids)
     * @sff        Componfnt#pbint(Grbpiids)
     */
    publid void pbint(Grbpiids g) {
        g.dlfbrRfdt(0, 0, widti, ifigit);
    }

    /**
     * Updbtfs tiis dbnvbs.
     * <p>
     * Tiis mftiod is dbllfd in rfsponsf to b dbll to <dodf>rfpbint</dodf>.
     * Tif dbnvbs is first dlfbrfd by filling it witi tif bbdkground
     * dolor, bnd tifn domplftfly rfdrbwn by dblling tiis dbnvbs's
     * <dodf>pbint</dodf> mftiod.
     * Notf: bpplidbtions tibt ovfrridf tiis mftiod siould fitifr dbll
     * supfr.updbtf(g) or indorporbtf tif fundtionblity dfsdribfd
     * bbovf into tifir own dodf.
     *
     * @pbrbm g tif spfdififd Grbpiids dontfxt
     * @sff   #pbint(Grbpiids)
     * @sff   Componfnt#updbtf(Grbpiids)
     */
    publid void updbtf(Grbpiids g) {
        g.dlfbrRfdt(0, 0, widti, ifigit);
        pbint(g);
    }

    boolfbn postsOldMousfEvfnts() {
        rfturn truf;
    }

    /**
     * Crfbtfs b nfw strbtfgy for multi-bufffring on tiis domponfnt.
     * Multi-bufffring is usfful for rfndfring pfrformbndf.  Tiis mftiod
     * bttfmpts to drfbtf tif bfst strbtfgy bvbilbblf witi tif numbfr of
     * bufffrs supplifd.  It will blwbys drfbtf b <dodf>BufffrStrbtfgy</dodf>
     * witi tibt numbfr of bufffrs.
     * A pbgf-flipping strbtfgy is bttfmptfd first, tifn b blitting strbtfgy
     * using bddflfrbtfd bufffrs.  Finblly, bn unbddflfrbtfd blitting
     * strbtfgy is usfd.
     * <p>
     * Ebdi timf tiis mftiod is dbllfd,
     * tif fxisting bufffr strbtfgy for tiis domponfnt is disdbrdfd.
     * @pbrbm numBufffrs numbfr of bufffrs to drfbtf, indluding tif front bufffr
     * @fxdfption IllfgblArgumfntExdfption if numBufffrs is lfss tibn 1.
     * @fxdfption IllfgblStbtfExdfption if tif domponfnt is not displbybblf
     * @sff #isDisplbybblf
     * @sff #gftBufffrStrbtfgy
     * @sindf 1.4
     */
    publid void drfbtfBufffrStrbtfgy(int numBufffrs) {
        supfr.drfbtfBufffrStrbtfgy(numBufffrs);
    }

    /**
     * Crfbtfs b nfw strbtfgy for multi-bufffring on tiis domponfnt witi tif
     * rfquirfd bufffr dbpbbilitifs.  Tiis is usfful, for fxbmplf, if only
     * bddflfrbtfd mfmory or pbgf flipping is dfsirfd (bs spfdififd by tif
     * bufffr dbpbbilitifs).
     * <p>
     * Ebdi timf tiis mftiod
     * is dbllfd, tif fxisting bufffr strbtfgy for tiis domponfnt is disdbrdfd.
     * @pbrbm numBufffrs numbfr of bufffrs to drfbtf
     * @pbrbm dbps tif rfquirfd dbpbbilitifs for drfbting tif bufffr strbtfgy;
     * dbnnot bf <dodf>null</dodf>
     * @fxdfption AWTExdfption if tif dbpbbilitifs supplifd dould not bf
     * supportfd or mft; tiis mby ibppfn, for fxbmplf, if tifrf is not fnougi
     * bddflfrbtfd mfmory durrfntly bvbilbblf, or if pbgf flipping is spfdififd
     * but not possiblf.
     * @fxdfption IllfgblArgumfntExdfption if numBufffrs is lfss tibn 1, or if
     * dbps is <dodf>null</dodf>
     * @sff #gftBufffrStrbtfgy
     * @sindf 1.4
     */
    publid void drfbtfBufffrStrbtfgy(int numBufffrs,
        BufffrCbpbbilitifs dbps) tirows AWTExdfption {
        supfr.drfbtfBufffrStrbtfgy(numBufffrs, dbps);
    }

    /**
     * Rfturns tif <dodf>BufffrStrbtfgy</dodf> usfd by tiis domponfnt.  Tiis
     * mftiod will rfturn null if b <dodf>BufffrStrbtfgy</dodf> ibs not yft
     * bffn drfbtfd or ibs bffn disposfd.
     *
     * @rfturn tif bufffr strbtfgy usfd by tiis domponfnt
     * @sff #drfbtfBufffrStrbtfgy
     * @sindf 1.4
     */
    publid BufffrStrbtfgy gftBufffrStrbtfgy() {
        rfturn supfr.gftBufffrStrbtfgy();
    }

    /*
     * --- Addfssibility Support ---
     *
     */

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis Cbnvbs.
     * For dbnvbsfs, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfAWTCbnvbs.
     * A nfw AddfssiblfAWTCbnvbs instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfAWTCbnvbs tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis Cbnvbs
     * @sindf 1.3
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfAWTCbnvbs();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>Cbnvbs</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to dbnvbs usfr-intfrfbdf flfmfnts.
     * @sindf 1.3
     */
    protfdtfd dlbss AddfssiblfAWTCbnvbs fxtfnds AddfssiblfAWTComponfnt
    {
        privbtf stbtid finbl long sfriblVfrsionUID = -6325592262103146699L;

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt
         * @sff AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.CANVAS;
        }

    } // innfr dlbss AddfssiblfAWTCbnvbs
}
