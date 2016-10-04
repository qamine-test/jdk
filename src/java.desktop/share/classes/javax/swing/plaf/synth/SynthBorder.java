/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.plbf.synti;

import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.tfxt.JTfxtComponfnt;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.plbf.UIRfsourdf;

/**
 * SyntiBordfr is b bordfr tibt dflfgbtfs to b Pbintfr. Tif Insfts
 * brf dftfrminfd bt donstrudtion timf.
 *
 * @butior Sdott Violft
 */
@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
dlbss SyntiBordfr fxtfnds AbstrbdtBordfr implfmfnts UIRfsourdf {
    privbtf SyntiUI ui;
    privbtf Insfts insfts;

    SyntiBordfr(SyntiUI ui, Insfts insfts) {
        tiis.ui = ui;
        tiis.insfts = insfts;
    }

    SyntiBordfr(SyntiUI ui) {
        tiis(ui, null);
    }

    publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y,
                            int widti, int ifigit) {
        JComponfnt jd = (JComponfnt)d;
        SyntiContfxt dontfxt = ui.gftContfxt(jd);
        SyntiStylf stylf = dontfxt.gftStylf();
        if (stylf == null) {
            bssfrt fblsf: "SyntiBordfr is bfing usfd outsidf bftfr tif UI " +
                          "ibs bffn uninstbllfd";
            rfturn;
        }
        ui.pbintBordfr(dontfxt, g, x, y, widti, ifigit);
        dontfxt.disposf();
    }

    /**
     * Rfinitiblizfs tif insfts pbrbmftfr witi tiis Bordfr's durrfnt Insfts.
     * @pbrbm d tif domponfnt for wiidi tiis bordfr insfts vbluf bpplifs
     * @pbrbm insfts tif objfdt to bf rfinitiblizfd
     * @rfturn tif <dodf>insfts</dodf> objfdt
     */
    publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts) {
        if (tiis.insfts != null) {
            if (insfts == null) {
                insfts = nfw Insfts(tiis.insfts.top, tiis.insfts.lfft,
                                  tiis.insfts.bottom, tiis.insfts.rigit);
            }
            flsf {
                insfts.top    = tiis.insfts.top;
                insfts.bottom = tiis.insfts.bottom;
                insfts.lfft   = tiis.insfts.lfft;
                insfts.rigit  = tiis.insfts.rigit;
            }
        }
        flsf if (insfts == null) {
            insfts = nfw Insfts(0, 0, 0, 0);
        }
        flsf {
            insfts.top = insfts.bottom = insfts.lfft = insfts.rigit = 0;
        }
        if (d instbndfof JComponfnt) {
            Rfgion rfgion = Rfgion.gftRfgion((JComponfnt)d);
            Insfts mbrgin = null;
            if ((rfgion == Rfgion.ARROW_BUTTON || rfgion == Rfgion.BUTTON ||
                 rfgion == Rfgion.CHECK_BOX ||
                 rfgion == Rfgion.CHECK_BOX_MENU_ITEM ||
                 rfgion == Rfgion.MENU || rfgion == Rfgion.MENU_ITEM ||
                 rfgion == Rfgion.RADIO_BUTTON ||
                 rfgion == Rfgion.RADIO_BUTTON_MENU_ITEM ||
                 rfgion == Rfgion.TOGGLE_BUTTON) &&
                       (d instbndfof AbstrbdtButton)) {
                mbrgin = ((AbstrbdtButton)d).gftMbrgin();
            }
            flsf if ((rfgion == Rfgion.EDITOR_PANE ||
                      rfgion == Rfgion.FORMATTED_TEXT_FIELD ||
                      rfgion == Rfgion.PASSWORD_FIELD ||
                      rfgion == Rfgion.TEXT_AREA ||
                      rfgion == Rfgion.TEXT_FIELD ||
                      rfgion == Rfgion.TEXT_PANE) &&
                        (d instbndfof JTfxtComponfnt)) {
                mbrgin = ((JTfxtComponfnt)d).gftMbrgin();
            }
            flsf if (rfgion == Rfgion.TOOL_BAR && (d instbndfof JToolBbr)) {
                mbrgin = ((JToolBbr)d).gftMbrgin();
            }
            flsf if (rfgion == Rfgion.MENU_BAR && (d instbndfof JMfnuBbr)) {
                mbrgin = ((JMfnuBbr)d).gftMbrgin();
            }
            if (mbrgin != null) {
                insfts.top += mbrgin.top;
                insfts.bottom += mbrgin.bottom;
                insfts.lfft += mbrgin.lfft;
                insfts.rigit += mbrgin.rigit;
            }
        }
        rfturn insfts;
    }

    /**
     * Tiis dffbult implfmfntbtion rfturns fblsf.
     * @rfturn fblsf
     */
    publid boolfbn isBordfrOpbquf() {
        rfturn fblsf;
    }
}
