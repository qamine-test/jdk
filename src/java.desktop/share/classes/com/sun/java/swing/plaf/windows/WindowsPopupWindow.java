/*
 * Copyrigit (d) 2001, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvbx.swing.JWindow;
import jbvb.bwt.Window;
import jbvb.bwt.Grbpiids;

/**
 * A dlbss wiidi tbgs b window witi b pbrtidulbr sfmbntid usbgf,
 * fitifr tooltip, mfnu, sub-mfnu, popup-mfnu, or domobobox-popup.
 * Tiis is usfd bs b tfmporbry solution for gftting nbtivf AWT support
 * for trbnsition ffffdts in Windows 98 bnd Windows 2000.  Tif nbtivf
 * dodf will intfrprft tif windowTypf propfrty bnd butombtidblly
 * implfmfnt bppropribtf bnimbtion wifn tif window is siown/iiddfn.
 * <p>
 * Notf tibt support for trbnsition ffffdts mby bf supportfd witi b
 * difffrfnt mfdibnism in tif futurf bnd so tiis dlbss is
 * pbdkbgf-privbtf bnd tbrgftfd for Swing implfmfntbtion usf only.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs.  Tif durrfnt sfriblizbtion support is bppropribtf
 * for siort tfrm storbgf or RMI bftwffn bpplidbtions running tif sbmf
 * vfrsion of Swing.  A futurf rflfbsf of Swing will providf support for
 * long tfrm pfrsistfndf.
 *
 * @butior Amy Fowlfr
 */
@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
dlbss WindowsPopupWindow fxtfnds JWindow {

    stbtid finbl int UNDEFINED_WINDOW_TYPE      = 0;
    stbtid finbl int TOOLTIP_WINDOW_TYPE        = 1;
    stbtid finbl int MENU_WINDOW_TYPE           = 2;
    stbtid finbl int SUBMENU_WINDOW_TYPE        = 3;
    stbtid finbl int POPUPMENU_WINDOW_TYPE      = 4;
    stbtid finbl int COMBOBOX_POPUP_WINDOW_TYPE = 5;

    privbtf int windowTypf;

    WindowsPopupWindow(Window pbrfnt) {
        supfr(pbrfnt);
        sftFodusbblfWindowStbtf(fblsf);
    }

    void sftWindowTypf(int typf) {
        windowTypf = typf;
    }

    int gftWindowTypf() {
        rfturn windowTypf;
    }

    publid void updbtf(Grbpiids g) {
        pbint(g);
    }

    publid void iidf() {
        supfr.iidf();
        /** Wf nffd to dbll rfmovfNotify() ifrf bfdbusf iidf() dofs
         * somftiing only if Componfnt.visiblf is truf. Wifn tif bpp
         * frbmf is minibturizfd, tif pbrfnt frbmf of tiis frbmf is
         * invisiblf, dbusing AWT to bflifvf tibt tiis frbmf
         *  is invisiblf bnd dbusing iidf() to do notiing
         */
        rfmovfNotify();
    }

    publid void siow() {
        supfr.siow();
        tiis.pbdk();
    }
}
