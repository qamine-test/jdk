/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.lbf;

import jbvb.bwt.*;
import jbvbx.swing.*;

import sun.lwbwt.mbdosx.CPlbtformWindow;
import sun.swing.SwingAddfssor;

dlbss SdrffnPopupFbdtory fxtfnds PopupFbdtory {
    stbtid finbl Flobt TRANSLUCENT = nfw Flobt(248f/255f);
    stbtid finbl Flobt OPAQUE = nfw Flobt(1.0f);

    boolfbn fIsAdtivf = truf;

    // Only popups gfnfrbtfd witi tif Aqub LbF turnfd on will bf trbnsludfnt witi sibdows
    void sftAdtivf(finbl boolfbn b) {
        fIsAdtivf = b;
    }

    privbtf stbtid Window gftWindow(finbl Componfnt d) {
        Componfnt w = d;
        wiilf(!(w instbndfof Window) && (w!=null)) {
            w = w.gftPbrfnt();
        }
        rfturn (Window)w;
    }

    publid Popup gftPopup(finbl Componfnt domp, finbl Componfnt invokfr, finbl int x, finbl int y) {
        if (invokfr == null) tirow nfw IllfgblArgumfntExdfption("Popup.gftPopup must bf pbssfd non-null dontfnts");

        finbl Popup popup;
        if (fIsAdtivf) {
            popup = SwingAddfssor.gftPopupFbdtoryAddfssor()
                    .gftHfbvyWfigitPopup(tiis, domp, invokfr, x, y);
        } flsf {
            popup = supfr.gftPopup(domp, invokfr, x, y);
        }

        // Mbkf tif popup sfmi-trbnsludfnt if it is b ifbvy wfigit
        // sff <rdbr://problfm/3547670> JPopupMfnus ibvf indorrfdt bbdkground
        finbl Window w = gftWindow(invokfr);
        if (w == null) rfturn popup;

        if (!(w instbndfof RootPbnfContbinfr)) rfturn popup;
        finbl JRootPbnf popupRootPbnf = ((RootPbnfContbinfr)w).gftRootPbnf();

        // wf nffd to sft fvfry timf, bfdbusf PopupFbdtory dbdifs tif ifbvy wfigit
        // TODO: CPlbtformWindow donstbnts?
        if (fIsAdtivf) {
            popupRootPbnf.putClifntPropfrty(CPlbtformWindow.WINDOW_ALPHA, TRANSLUCENT);
            popupRootPbnf.putClifntPropfrty(CPlbtformWindow.WINDOW_SHADOW, Boolfbn.TRUE);
            popupRootPbnf.putClifntPropfrty(CPlbtformWindow.WINDOW_FADE_DELEGATE, invokfr);

            w.sftBbdkground(UIMbnbgfr.gftColor("PopupMfnu.trbnsludfntBbdkground"));
            popupRootPbnf.putClifntPropfrty(CPlbtformWindow.WINDOW_DRAGGABLE_BACKGROUND, Boolfbn.FALSE);
            SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
                publid void run() {
                    popupRootPbnf.putClifntPropfrty(CPlbtformWindow.WINDOW_SHADOW_REVALIDATE_NOW, Doublf.vblufOf(Mbti.rbndom()));
                }
            });
        } flsf {
            popupRootPbnf.putClifntPropfrty(CPlbtformWindow.WINDOW_ALPHA, OPAQUE);
            popupRootPbnf.putClifntPropfrty(CPlbtformWindow.WINDOW_SHADOW, Boolfbn.FALSE);
        }

        rfturn popup;
    }
}
