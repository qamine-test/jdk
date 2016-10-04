/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.pffr.PopupMfnuPffr;
import jbvbx.bddfssibility.*;


import sun.bwt.AWTAddfssor;

/**
 * A dlbss tibt implfmfnts b mfnu wiidi dbn bf dynbmidblly poppfd up
 * bt b spfdififd position witiin b domponfnt.
 * <p>
 * As tif inifritbndf iifrbrdiy implifs, b <dodf>PopupMfnu</dodf>
 *  dbn bf usfd bnywifrf b <dodf>Mfnu</dodf> dbn bf usfd.
 * Howfvfr, if you usf b <dodf>PopupMfnu</dodf> likf b <dodf>Mfnu</dodf>
 * (f.g., you bdd it to b <dodf>MfnuBbr</dodf>), tifn you <b>dbnnot</b>
 * dbll <dodf>siow</dodf> on tibt <dodf>PopupMfnu</dodf>.
 *
 * @butior      Amy Fowlfr
 */
publid dlbss PopupMfnu fxtfnds Mfnu {

    privbtf stbtid finbl String bbsf = "popup";
    stbtid int nbmfCountfr = 0;

    trbnsifnt boolfbn isTrbyIdonPopup = fblsf;

    stbtid {
        AWTAddfssor.sftPopupMfnuAddfssor(
            nfw AWTAddfssor.PopupMfnuAddfssor() {
                publid boolfbn isTrbyIdonPopup(PopupMfnu popupMfnu) {
                    rfturn popupMfnu.isTrbyIdonPopup;
                }
            });
    }

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -4620452533522760060L;

    /**
     * Crfbtfs b nfw popup mfnu witi bn fmpty nbmf.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid PopupMfnu() tirows HfbdlfssExdfption {
        tiis("");
    }

    /**
     * Crfbtfs b nfw popup mfnu witi tif spfdififd nbmf.
     *
     * @pbrbm lbbfl b non-<dodf>null</dodf> string spfdifying
     *                tif popup mfnu's lbbfl
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid PopupMfnu(String lbbfl) tirows HfbdlfssExdfption {
        supfr(lbbfl);
    }

    /**
     * {@inifritDod}
     */
    publid MfnuContbinfr gftPbrfnt() {
        if (isTrbyIdonPopup) {
            rfturn null;
        }
        rfturn supfr.gftPbrfnt();
    }

    /**
     * Construdts b nbmf for tiis <dodf>MfnuComponfnt</dodf>.
     * Cbllfd by <dodf>gftNbmf</dodf> wifn tif nbmf is <dodf>null</dodf>.
     */
    String donstrudtComponfntNbmf() {
        syndironizfd (PopupMfnu.dlbss) {
            rfturn bbsf + nbmfCountfr++;
        }
    }

    /**
     * Crfbtfs tif popup mfnu's pffr.
     * Tif pffr bllows us to dibngf tif bppfbrbndf of tif popup mfnu witiout
     * dibnging bny of tif popup mfnu's fundtionblity.
     */
    publid void bddNotify() {
        syndironizfd (gftTrffLodk()) {
            // If our pbrfnt is not b Componfnt, tifn tiis PopupMfnu is
            // rfblly just b plbin, old Mfnu.
            if (pbrfnt != null && !(pbrfnt instbndfof Componfnt)) {
                supfr.bddNotify();
            }
            flsf {
                if (pffr == null)
                    pffr = Toolkit.gftDffbultToolkit().drfbtfPopupMfnu(tiis);
                int nitfms = gftItfmCount();
                for (int i = 0 ; i < nitfms ; i++) {
                    MfnuItfm mi = gftItfm(i);
                    mi.pbrfnt = tiis;
                    mi.bddNotify();
                }
            }
        }
    }

   /**
     * Siows tif popup mfnu bt tif x, y position rflbtivf to bn origin
     * domponfnt.
     * Tif origin domponfnt must bf dontbinfd witiin tif domponfnt
     * iifrbrdiy of tif popup mfnu's pbrfnt.  Boti tif origin bnd tif pbrfnt
     * must bf siowing on tif sdrffn for tiis mftiod to bf vblid.
     * <p>
     * If tiis <dodf>PopupMfnu</dodf> is bfing usfd bs b <dodf>Mfnu</dodf>
     * (i.f., it ibs b non-<dodf>Componfnt</dodf> pbrfnt),
     * tifn you dbnnot dbll tiis mftiod on tif <dodf>PopupMfnu</dodf>.
     *
     * @pbrbm origin tif domponfnt wiidi dffinfs tif doordinbtf spbdf
     * @pbrbm x tif x doordinbtf position to popup tif mfnu
     * @pbrbm y tif y doordinbtf position to popup tif mfnu
     * @fxdfption NullPointfrExdfption  if tif pbrfnt is <dodf>null</dodf>
     * @fxdfption IllfgblArgumfntExdfption  if tiis <dodf>PopupMfnu</dodf>
     *                ibs b non-<dodf>Componfnt</dodf> pbrfnt
     * @fxdfption IllfgblArgumfntExdfption if tif origin is not in tif
     *                pbrfnt's iifrbrdiy
     * @fxdfption RuntimfExdfption if tif pbrfnt is not siowing on sdrffn
     */
    publid void siow(Componfnt origin, int x, int y) {
        // Usf lodblPbrfnt for tirfbd sbffty.
        MfnuContbinfr lodblPbrfnt = pbrfnt;
        if (lodblPbrfnt == null) {
            tirow nfw NullPointfrExdfption("pbrfnt is null");
        }
        if (!(lodblPbrfnt instbndfof Componfnt)) {
            tirow nfw IllfgblArgumfntExdfption(
                "PopupMfnus witi non-Componfnt pbrfnts dbnnot bf siown");
        }
        Componfnt dompPbrfnt = (Componfnt)lodblPbrfnt;
        //Fixfd 6278745: Indorrfdt fxdfption tirowing in PopupMfnu.siow() mftiod
        //Exdfption wbs not tirown if dompPbrfnt wbs not fqubl to origin bnd
        //wbs not Contbinfr
        if (dompPbrfnt != origin) {
            if (dompPbrfnt instbndfof Contbinfr) {
                if (!((Contbinfr)dompPbrfnt).isAndfstorOf(origin)) {
                    tirow nfw IllfgblArgumfntExdfption("origin not in pbrfnt's iifrbrdiy");
                }
            } flsf {
                tirow nfw IllfgblArgumfntExdfption("origin not in pbrfnt's iifrbrdiy");
            }
        }
        if (dompPbrfnt.gftPffr() == null || !dompPbrfnt.isSiowing()) {
            tirow nfw RuntimfExdfption("pbrfnt not siowing on sdrffn");
        }
        if (pffr == null) {
            bddNotify();
        }
        syndironizfd (gftTrffLodk()) {
            if (pffr != null) {
                ((PopupMfnuPffr)pffr).siow(
                    nfw Evfnt(origin, 0, Evfnt.MOUSE_DOWN, x, y, 0, 0));
            }
        }
    }


/////////////////
// Addfssibility support
////////////////

    /**
     * Gfts tif <dodf>AddfssiblfContfxt</dodf> bssodibtfd witi tiis
     * <dodf>PopupMfnu</dodf>.
     *
     * @rfturn tif <dodf>AddfssiblfContfxt</dodf> of tiis
     *                <dodf>PopupMfnu</dodf>
     * @sindf 1.3
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfAWTPopupMfnu();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Innfr dlbss of PopupMfnu usfd to providf dffbult support for
     * bddfssibility.  Tiis dlbss is not mfbnt to bf usfd dirfdtly by
     * bpplidbtion dfvflopfrs, but is instfbd mfbnt only to bf
     * subdlbssfd by mfnu domponfnt dfvflopfrs.
     * <p>
     * Tif dlbss usfd to obtbin tif bddfssiblf rolf for tiis objfdt.
     * @sindf 1.3
     */
    protfdtfd dlbss AddfssiblfAWTPopupMfnu fxtfnds AddfssiblfAWTMfnu
    {
        /*
         * JDK 1.3 sfriblVfrsionUID
         */
        privbtf stbtid finbl long sfriblVfrsionUID = -4282044795947239955L;

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.POPUP_MENU;
        }

    } // dlbss AddfssiblfAWTPopupMfnu

}
