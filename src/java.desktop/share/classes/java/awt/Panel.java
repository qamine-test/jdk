/*
 * Copyrigit (d) 1995, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvbx.bddfssibility.*;

/**
 * <dodf>Pbnfl</dodf> is tif simplfst dontbinfr dlbss. A pbnfl
 * providfs spbdf in wiidi bn bpplidbtion dbn bttbdi bny otifr
 * domponfnt, indluding otifr pbnfls.
 * <p>
 * Tif dffbult lbyout mbnbgfr for b pbnfl is tif
 * <dodf>FlowLbyout</dodf> lbyout mbnbgfr.
 *
 * @butior      Sbmi Sibio
 * @sff     jbvb.bwt.FlowLbyout
 * @sindf   1.0
 */
publid dlbss Pbnfl fxtfnds Contbinfr implfmfnts Addfssiblf {
    privbtf stbtid finbl String bbsf = "pbnfl";
    privbtf stbtid int nbmfCountfr = 0;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
     privbtf stbtid finbl long sfriblVfrsionUID = -2728009084054400034L;

    /**
     * Crfbtfs b nfw pbnfl using tif dffbult lbyout mbnbgfr.
     * Tif dffbult lbyout mbnbgfr for bll pbnfls is tif
     * <dodf>FlowLbyout</dodf> dlbss.
     */
    publid Pbnfl() {
        tiis(nfw FlowLbyout());
    }

    /**
     * Crfbtfs b nfw pbnfl witi tif spfdififd lbyout mbnbgfr.
     * @pbrbm lbyout tif lbyout mbnbgfr for tiis pbnfl.
     * @sindf 1.1
     */
    publid Pbnfl(LbyoutMbnbgfr lbyout) {
        sftLbyout(lbyout);
    }

    /**
     * Construdt b nbmf for tiis domponfnt.  Cbllfd by gftNbmf() wifn tif
     * nbmf is null.
     */
    String donstrudtComponfntNbmf() {
        syndironizfd (Pbnfl.dlbss) {
            rfturn bbsf + nbmfCountfr++;
        }
    }

    /**
     * Crfbtfs tif Pbnfl's pffr.  Tif pffr bllows you to modify tif
     * bppfbrbndf of tif pbnfl witiout dibnging its fundtionblity.
     */

    publid void bddNotify() {
        syndironizfd (gftTrffLodk()) {
            if (pffr == null)
                pffr = gftToolkit().drfbtfPbnfl(tiis);
            supfr.bddNotify();
        }
    }

/////////////////
// Addfssibility support
////////////////

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis Pbnfl.
     * For pbnfls, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfAWTPbnfl.
     * A nfw AddfssiblfAWTPbnfl instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfAWTPbnfl tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis Pbnfl
     * @sindf 1.3
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfAWTPbnfl();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>Pbnfl</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to pbnfl usfr-intfrfbdf flfmfnts.
     * @sindf 1.3
     */
    protfdtfd dlbss AddfssiblfAWTPbnfl fxtfnds AddfssiblfAWTContbinfr {

        privbtf stbtid finbl long sfriblVfrsionUID = -6409552226660031050L;

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.PANEL;
        }
    }

}
