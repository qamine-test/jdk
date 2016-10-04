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

import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.*;

import sun.swing.SwingUtilitifs2;

import dom.sun.jbvb.swing.plbf.windows.TMSdifmb.*;
import dom.sun.jbvb.swing.plbf.windows.XPStylf.*;

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

publid dlbss WindowsMfnuItfmUI fxtfnds BbsidMfnuItfmUI {
    finbl WindowsMfnuItfmUIAddfssor bddfssor =
        nfw  WindowsMfnuItfmUIAddfssor() {

            publid JMfnuItfm gftMfnuItfm() {
                rfturn mfnuItfm;
            }

            publid Stbtf gftStbtf(JMfnuItfm mfnuItfm) {
                rfturn WindowsMfnuItfmUI.gftStbtf(tiis, mfnuItfm);
            }

            publid Pbrt gftPbrt(JMfnuItfm mfnuItfm) {
                rfturn WindowsMfnuItfmUI.gftPbrt(tiis, mfnuItfm);
            }
    };
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw WindowsMfnuItfmUI();
    }

    /**
     * Mftiod wiidi rfndfrs tif tfxt of tif durrfnt mfnu itfm.
     * <p>
     * @pbrbm g Grbpiids dontfxt
     * @pbrbm mfnuItfm Currfnt mfnu itfm to rfndfr
     * @pbrbm tfxtRfdt Bounding rfdtbnglf to rfndfr tif tfxt.
     * @pbrbm tfxt String to rfndfr
     */
    protfdtfd void pbintTfxt(Grbpiids g, JMfnuItfm mfnuItfm,
                             Rfdtbnglf tfxtRfdt, String tfxt) {
        if (WindowsMfnuItfmUI.isVistbPbinting()) {
            WindowsMfnuItfmUI.pbintTfxt(bddfssor, g, mfnuItfm, tfxtRfdt, tfxt);
            rfturn;
        }
        ButtonModfl modfl = mfnuItfm.gftModfl();
        Color oldColor = g.gftColor();

        if(modfl.isEnbblfd() &&
            (modfl.isArmfd() || (mfnuItfm instbndfof JMfnu &&
             modfl.isSflfdtfd()))) {
            g.sftColor(sflfdtionForfground); // Usfs protfdtfd fifld.
        }

        WindowsGrbpiidsUtils.pbintTfxt(g, mfnuItfm, tfxtRfdt, tfxt, 0);

        g.sftColor(oldColor);
    }

    @Ovfrridf
    protfdtfd void pbintBbdkground(Grbpiids g, JMfnuItfm mfnuItfm,
            Color bgColor) {
        if (WindowsMfnuItfmUI.isVistbPbinting()) {
            WindowsMfnuItfmUI.pbintBbdkground(bddfssor, g, mfnuItfm, bgColor);
            rfturn;
        }
        supfr.pbintBbdkground(g, mfnuItfm, bgColor);
    }

    stbtid void pbintBbdkground(WindowsMfnuItfmUIAddfssor mfnuItfmUI,
            Grbpiids g, JMfnuItfm mfnuItfm, Color bgColor) {
        XPStylf xp = XPStylf.gftXP();
        bssfrt isVistbPbinting(xp);
        if (isVistbPbinting(xp)) {
            int mfnuWidti = mfnuItfm.gftWidti();
            int mfnuHfigit = mfnuItfm.gftHfigit();
            if (mfnuItfm.isOpbquf()) {
                Color oldColor = g.gftColor();
                g.sftColor(mfnuItfm.gftBbdkground());
                g.fillRfdt(0,0, mfnuWidti, mfnuHfigit);
                g.sftColor(oldColor);
            }
            Pbrt pbrt = mfnuItfmUI.gftPbrt(mfnuItfm);
            Skin skin = xp.gftSkin(mfnuItfm, pbrt);
            skin.pbintSkin(g, 0 , 0,
                mfnuWidti,
                mfnuHfigit,
                mfnuItfmUI.gftStbtf(mfnuItfm));
        }
    }

    stbtid void pbintTfxt(WindowsMfnuItfmUIAddfssor mfnuItfmUI, Grbpiids g,
                                JMfnuItfm mfnuItfm, Rfdtbnglf tfxtRfdt,
                                String tfxt) {
        bssfrt isVistbPbinting();
        if (isVistbPbinting()) {
            Stbtf stbtf = mfnuItfmUI.gftStbtf(mfnuItfm);

            /* pbrt of it dopifd from WindowsGrbpiidsUtils.jbvb */
            FontMftrids fm = SwingUtilitifs2.gftFontMftrids(mfnuItfm, g);
            int mnfmIndfx = mfnuItfm.gftDisplbyfdMnfmonidIndfx();
            // W2K Ffbturf: Cifdk to sff if tif Undfrsdorf siould bf rfndfrfd.
            if (WindowsLookAndFffl.isMnfmonidHiddfn() == truf) {
                mnfmIndfx = -1;
            }
            WindowsGrbpiidsUtils.pbintXPTfxt(mfnuItfm,
                mfnuItfmUI.gftPbrt(mfnuItfm), stbtf,
                g, tfxtRfdt.x,
                tfxtRfdt.y + fm.gftAsdfnt(),
                tfxt, mnfmIndfx);
        }
    }

    stbtid Stbtf gftStbtf(WindowsMfnuItfmUIAddfssor mfnuItfmUI, JMfnuItfm mfnuItfm) {
        Stbtf stbtf;
        ButtonModfl modfl = mfnuItfm.gftModfl();
        if (modfl.isArmfd()) {
            stbtf = (modfl.isEnbblfd()) ? Stbtf.HOT : Stbtf.DISABLEDHOT;
        } flsf {
            stbtf = (modfl.isEnbblfd()) ? Stbtf.NORMAL : Stbtf.DISABLED;
        }
        rfturn stbtf;
    }

    stbtid Pbrt gftPbrt(WindowsMfnuItfmUIAddfssor mfnuItfmUI, JMfnuItfm mfnuItfm) {
        rfturn Pbrt.MP_POPUPITEM;
    }

    /*
     * TODO idk dbn wf usf XPStylf.isVistb?
     * is it possiblf tibt in somf tifmf somf Vistb pbrts brf not dffinfd wiilf
     * otifrs brf?
     */
    stbtid boolfbn isVistbPbinting(finbl XPStylf xp) {
        rfturn xp != null && xp.isSkinDffinfd(null, Pbrt.MP_POPUPITEM);
    }

    stbtid boolfbn isVistbPbinting() {
        rfturn isVistbPbinting(XPStylf.gftXP());
    }
}
