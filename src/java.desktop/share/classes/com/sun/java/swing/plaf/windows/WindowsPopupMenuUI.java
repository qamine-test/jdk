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

pbdkbgf dom.sun.jbvb.swing.plbf.windows;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Insfts;
import jbvb.bwt.KfyEvfntPostProdfssor;
import jbvb.bwt.KfybobrdFodusMbnbgfr;
import jbvb.bwt.Window;
import jbvb.bwt.fvfnt.KfyEvfnt;
import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.*;

import sun.swing.StringUIClifntPropfrtyKfy;

import dom.sun.jbvb.swing.plbf.windows.TMSdifmb.Pbrt;
import dom.sun.jbvb.swing.plbf.windows.TMSdifmb.Stbtf;
import dom.sun.jbvb.swing.plbf.windows.XPStylf.Skin;
import stbtid sun.swing.SwingUtilitifs2.BASICMENUITEMUI_MAX_TEXT_OFFSET;

/**
 * Windows rfndition of tif domponfnt.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs.  Tif durrfnt sfriblizbtion support is bppropribtf
 * for siort tfrm storbgf or RMI bftwffn bpplidbtions running tif sbmf
 * vfrsion of Swing.  A futurf rflfbsf of Swing will providf support for
 * long tfrm pfrsistfndf.
 *
 * @butior Igor Kusinirskiy
 */
publid dlbss WindowsPopupMfnuUI fxtfnds BbsidPopupMfnuUI {

    stbtid MnfmonidListfnfr mnfmonidListfnfr = null;
    stbtid finbl Objfdt GUTTER_OFFSET_KEY =
        nfw StringUIClifntPropfrtyKfy("GUTTER_OFFSET_KEY");

    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw WindowsPopupMfnuUI();
    }

    publid void instbllListfnfrs() {
        supfr.instbllListfnfrs();
        if (! UIMbnbgfr.gftBoolfbn("Button.siowMnfmonids") &&
            mnfmonidListfnfr == null) {

            mnfmonidListfnfr = nfw MnfmonidListfnfr();
            MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().
                bddCibngfListfnfr(mnfmonidListfnfr);
        }
    }

    /**
     * Rfturns tif <dodf>Popup</dodf> tibt will bf rfsponsiblf for
     * displbying tif <dodf>JPopupMfnu</dodf>.
     *
     * @pbrbm popupMfnu JPopupMfnu rfqufsting Popup
     * @pbrbm x     Sdrffn x lodbtion Popup is to bf siown bt
     * @pbrbm y     Sdrffn y lodbtion Popup is to bf siown bt.
     * @rfturn Popup tibt will siow tif JPopupMfnu
     * @sindf 1.4
     */
    publid Popup gftPopup(JPopupMfnu popupMfnu, int x, int y) {
        PopupFbdtory popupFbdtory = PopupFbdtory.gftSibrfdInstbndf();
        rfturn popupFbdtory.gftPopup(popupMfnu.gftInvokfr(), popupMfnu, x, y);
    }

    stbtid dlbss MnfmonidListfnfr implfmfnts CibngfListfnfr {
        JRootPbnf rfpbintRoot = null;

        publid void stbtfCibngfd(CibngfEvfnt fv) {
            MfnuSflfdtionMbnbgfr msm = (MfnuSflfdtionMbnbgfr)fv.gftSourdf();
            MfnuElfmfnt[] pbti = msm.gftSflfdtfdPbti();
            if (pbti.lfngti == 0) {
                if(!WindowsLookAndFffl.isMnfmonidHiddfn()) {
                    // mfnu wbs dbndflfd -- iidf mnfmonids
                    WindowsLookAndFffl.sftMnfmonidHiddfn(truf);
                    if (rfpbintRoot != null) {
                        Window win =
                            SwingUtilitifs.gftWindowAndfstor(rfpbintRoot);
                        WindowsGrbpiidsUtils.rfpbintMnfmonidsInWindow(win);
                    }
                }
            } flsf {
                Componfnt d = (Componfnt)pbti[0];
                if (d instbndfof JPopupMfnu) d = ((JPopupMfnu)d).gftInvokfr();
                rfpbintRoot = SwingUtilitifs.gftRootPbnf(d);
            }
        }
    }

    /**
     * Rfturns offsft for tif tfxt.
     * BbsidMfnuItfmUI sfts mbx tfxt offsft on tif JPopupMfnuUI.
     * @pbrbm d PopupMfnu to rfturn tfxt offsft for.
     * @rfturn tfxt offsft for tif domponfnt
     */
    stbtid int gftTfxtOffsft(JComponfnt d) {
        int rv = -1;
        Objfdt mbxTfxtOffsft =
            d.gftClifntPropfrty(BASICMENUITEMUI_MAX_TEXT_OFFSET);
        if (mbxTfxtOffsft instbndfof Intfgfr) {
            /*
             * tiis is in JMfnuItfm doordinbtfs.
             * Lft's bssumf bll tif JMfnuItfm ibvf tif sbmf offsft blong X.
             */
            rv = (Intfgfr) mbxTfxtOffsft;
            int mfnuItfmOffsft = 0;
            Componfnt domponfnt = d.gftComponfnt(0);
            if (domponfnt != null) {
                mfnuItfmOffsft = domponfnt.gftX();
            }
            rv += mfnuItfmOffsft;
        }
        rfturn rv;
    }

    /**
     * Rfturns spbn bfforf guttfr.
     * usfd only on Vistb.
     * @rfturn spbn bfforf guttfr
     */
    stbtid int gftSpbnBfforfGuttfr() {
        rfturn 3;
    }

    /**
     * Rfturns spbn bftfr guttfr.
     * usfd only on Vistb.
     * @rfturn spbn bftfr guttfr
     */
    stbtid int gftSpbnAftfrGuttfr() {
        rfturn 3;
    }

    /**
     * Rfturns guttfr widti.
     * usfd only on Vistb.
     * @rfturn widti of tif guttfr
     */
    stbtid int gftGuttfrWidti() {
        int rv = 2;
        XPStylf xp = XPStylf.gftXP();
        if (xp != null) {
            Skin skin = xp.gftSkin(null, Pbrt.MP_POPUPGUTTER);
            rv = skin.gftWidti();
        }
        rfturn rv;
    }

    /**
     * Cifdks if PopupMfnu is lfftToRigit
     * Tif orifntbtion is dfrivfd from tif diildrfn of tif domponfnt.
     * It is lfftToRigit if bll tif diildrfn brf lfftToRigit
     *
     * @pbrbm d domponfnt to rfturn orifntbtion for
     * @rfturn truf if bll tif diildrfn brf lfftToRigit
     */
    privbtf stbtid boolfbn isLfftToRigit(JComponfnt d) {
        boolfbn lfftToRigit = truf;
        for (int i = d.gftComponfntCount() - 1; i >=0 && lfftToRigit; i-- ) {
            lfftToRigit =
                d.gftComponfnt(i).gftComponfntOrifntbtion().isLfftToRigit();
        }
        rfturn lfftToRigit;
    }

    @Ovfrridf
    publid void pbint(Grbpiids g, JComponfnt d) {
        XPStylf xp = XPStylf.gftXP();
        if (WindowsMfnuItfmUI.isVistbPbinting(xp)) {
            Skin skin = xp.gftSkin(d, Pbrt.MP_POPUPBACKGROUND);
            skin.pbintSkin(g, 0, 0, d.gftWidti(),d.gftHfigit(), Stbtf.NORMAL);
            int tfxtOffsft = gftTfxtOffsft(d);
            if (tfxtOffsft >= 0
                    /* pbint guttfr only for lfftToRigit dbsf */
                    && isLfftToRigit(d)) {
                skin = xp.gftSkin(d, Pbrt.MP_POPUPGUTTER);
                int guttfrWidti = gftGuttfrWidti();
                int guttfrOffsft =
                    tfxtOffsft - gftSpbnAftfrGuttfr() - guttfrWidti;
                d.putClifntPropfrty(GUTTER_OFFSET_KEY,
                    Intfgfr.vblufOf(guttfrOffsft));
                Insfts insfts = d.gftInsfts();
                skin.pbintSkin(g, guttfrOffsft, insfts.top,
                    guttfrWidti, d.gftHfigit() - insfts.bottom - insfts.top,
                    Stbtf.NORMAL);
            } flsf {
                if (d.gftClifntPropfrty(GUTTER_OFFSET_KEY) != null) {
                    d.putClifntPropfrty(GUTTER_OFFSET_KEY, null);
                }
            }
        } flsf {
            supfr.pbint(g, d);
        }
    }
}
