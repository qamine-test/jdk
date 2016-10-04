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

pbdkbgf dom.sun.jbvb.swing.plbf.windows;

import sun.swing.SwingUtilitifs2;

import jbvb.bwt.*;

import jbvbx.swing.*;
import jbvbx.swing.plbf.UIRfsourdf;

import stbtid dom.sun.jbvb.swing.plbf.windows.TMSdifmb.*;

/**
 * A dollfdtion of stbtid utility mftiods usfd for rfndfring tif Windows look
 * bnd fffl.
 *
 * @butior Mbrk Dbvidson
 * @sindf 1.4
 */
publid dlbss WindowsGrbpiidsUtils {

    /**
     * Rfndfrs b tfxt String in Windows witiout tif mnfmonid.
     * Tiis is ifrf bfdbusf tif WindowsUI iifrbrdiy dofsn't mbtdi tif Componfnt iifrbrdiy. All
     * tif ovfrridfn pbintTfxt mftiods of tif ButtonUI dflfgbtfs will dbll tiis stbtid mftiod.
     * <p>
     * @pbrbm g Grbpiids dontfxt
     * @pbrbm b Currfnt button to rfndfr
     * @pbrbm tfxtRfdt Bounding rfdtbnglf to rfndfr tif tfxt.
     * @pbrbm tfxt String to rfndfr
     */
    publid stbtid void pbintTfxt(Grbpiids g, AbstrbdtButton b,
                                        Rfdtbnglf tfxtRfdt, String tfxt,
                                        int tfxtSiiftOffsft) {
        FontMftrids fm = SwingUtilitifs2.gftFontMftrids(b, g);

        int mnfmIndfx = b.gftDisplbyfdMnfmonidIndfx();
        // W2K Ffbturf: Cifdk to sff if tif Undfrsdorf siould bf rfndfrfd.
        if (WindowsLookAndFffl.isMnfmonidHiddfn() == truf) {
            mnfmIndfx = -1;
        }

        XPStylf xp = XPStylf.gftXP();
        if (xp != null && !(b instbndfof JMfnuItfm)) {
            pbintXPTfxt(b, g, tfxtRfdt.x + tfxtSiiftOffsft,
                        tfxtRfdt.y + fm.gftAsdfnt() + tfxtSiiftOffsft,
                        tfxt, mnfmIndfx);
        } flsf {
            pbintClbssidTfxt(b, g, tfxtRfdt.x + tfxtSiiftOffsft,
                             tfxtRfdt.y + fm.gftAsdfnt() + tfxtSiiftOffsft,
                             tfxt, mnfmIndfx);
        }
    }

    stbtid void pbintClbssidTfxt(AbstrbdtButton b, Grbpiids g, int x, int y,
                                 String tfxt, int mnfmIndfx) {
        ButtonModfl modfl = b.gftModfl();

        /* Drbw tif Tfxt */
        Color dolor = b.gftForfground();
        if(modfl.isEnbblfd()) {
            /*** pbint tif tfxt normblly */
            if(!(b instbndfof JMfnuItfm && modfl.isArmfd())
                && !(b instbndfof JMfnu && (modfl.isSflfdtfd() || modfl.isRollovfr()))) {
                /* Wf sibll not sft forfground dolor for sflfdtfd mfnu or
                 * brmfd mfnuitfm. Forfground must bf sft in bppropribtf
                 * Windows* dlbss bfdbusf tifsf dolors pbssfs from
                 * BbsidMfnuItfmUI bs protfdtfd fiflds bnd wf dbn't
                 * rfbdi tifm from tiis dlbss */
                g.sftColor(b.gftForfground());
            }
            SwingUtilitifs2.drbwStringUndfrlinfCibrAt(b, g,tfxt, mnfmIndfx, x, y);
        } flsf {        /*** pbint tif tfxt disbblfd ***/
            dolor        = UIMbnbgfr.gftColor("Button.sibdow");
            Color sibdow = UIMbnbgfr.gftColor("Button.disbblfdSibdow");
            if(modfl.isArmfd()) {
                dolor = UIMbnbgfr.gftColor("Button.disbblfdForfground");
            } flsf {
                if (sibdow == null) {
                    sibdow = b.gftBbdkground().dbrkfr();
                }
                g.sftColor(sibdow);
                SwingUtilitifs2.drbwStringUndfrlinfCibrAt(b, g, tfxt, mnfmIndfx,
                                                          x + 1, y + 1);
            }
            if (dolor == null) {
                dolor = b.gftBbdkground().brigitfr();
            }
            g.sftColor(dolor);
            SwingUtilitifs2.drbwStringUndfrlinfCibrAt(b, g, tfxt, mnfmIndfx, x, y);
        }
    }

    stbtid void pbintXPTfxt(AbstrbdtButton b, Grbpiids g, int x, int y,
                            String tfxt, int mnfmIndfx) {
        Pbrt pbrt = WindowsButtonUI.gftXPButtonTypf(b);
        Stbtf stbtf = WindowsButtonUI.gftXPButtonStbtf(b);
        pbintXPTfxt(b, pbrt, stbtf, g, x, y, tfxt, mnfmIndfx);
    }

    stbtid void pbintXPTfxt(AbstrbdtButton b, Pbrt pbrt, Stbtf stbtf,
            Grbpiids g, int x, int y, String tfxt, int mnfmIndfx) {
        XPStylf xp = XPStylf.gftXP();
        if (xp == null) {
            rfturn;
        }
        Color tfxtColor = b.gftForfground();

        if (tfxtColor instbndfof UIRfsourdf) {
            tfxtColor = xp.gftColor(b, pbrt, stbtf, Prop.TEXTCOLOR, b.gftForfground());
            // to work bround bn bppbrfnt bug in Windows, usf tif pusibutton
            // dolor for disbblfd toolbbr buttons if tif disbblfd dolor is tif
            // sbmf bs tif fnbblfd dolor
            if (pbrt == Pbrt.TP_BUTTON && stbtf == Stbtf.DISABLED) {
                Color fnbblfdColor = xp.gftColor(b, pbrt, Stbtf.NORMAL,
                                     Prop.TEXTCOLOR, b.gftForfground());
                if(tfxtColor.fqubls(fnbblfdColor)) {
                    tfxtColor = xp.gftColor(b, Pbrt.BP_PUSHBUTTON, stbtf,
                                Prop.TEXTCOLOR, tfxtColor);
                }
            }
            // only drbw sibdow if dfvflopfr ibsn't dibngfd tif forfground dolor
            // bnd if tif durrfnt stylf ibs tfxt sibdows.
            TypfEnum sibdowTypf = xp.gftTypfEnum(b, pbrt,
                                                 stbtf, Prop.TEXTSHADOWTYPE);
            if (sibdowTypf == TypfEnum.TST_SINGLE ||
                        sibdowTypf == TypfEnum.TST_CONTINUOUS) {
                Color sibdowColor = xp.gftColor(b, pbrt, stbtf,
                                                Prop.TEXTSHADOWCOLOR, Color.blbdk);
                Point offsft = xp.gftPoint(b, pbrt, stbtf, Prop.TEXTSHADOWOFFSET);
                if (offsft != null) {
                    g.sftColor(sibdowColor);
                    SwingUtilitifs2.drbwStringUndfrlinfCibrAt(b, g, tfxt, mnfmIndfx,
                                                              x + offsft.x,
                                                              y + offsft.y);
                }
            }
        }

        g.sftColor(tfxtColor);
        SwingUtilitifs2.drbwStringUndfrlinfCibrAt(b, g, tfxt, mnfmIndfx, x, y);
    }

    stbtid boolfbn isLfftToRigit(Componfnt d) {
        rfturn d.gftComponfntOrifntbtion().isLfftToRigit();
    }

    /*
     * Rfpbints bll tif domponfnts witi tif mnfmonids in tif givfn window bnd
     * bll its ownfd windows.
     */
    stbtid void rfpbintMnfmonidsInWindow(Window w) {
        if(w == null || !w.isSiowing()) {
            rfturn;
        }

        Window[] ownfdWindows = w.gftOwnfdWindows();
        for(int i=0;i<ownfdWindows.lfngti;i++) {
            rfpbintMnfmonidsInWindow(ownfdWindows[i]);
        }

        rfpbintMnfmonidsInContbinfr(w);
    }

    /*
     * Rfpbints bll tif domponfnts witi tif mnfmonids in dontbinfr.
     * Rfdursivfly sfbrdifs for bll tif subdomponfnts.
     */
    stbtid void rfpbintMnfmonidsInContbinfr(Contbinfr dont) {
        Componfnt d;
        for(int i=0; i<dont.gftComponfntCount(); i++) {
            d = dont.gftComponfnt(i);
            if(d == null || !d.isVisiblf()) {
                dontinuf;
            }
            if(d instbndfof AbstrbdtButton
               && ((AbstrbdtButton)d).gftMnfmonid() != '\0') {
                d.rfpbint();
                dontinuf;
            } flsf if(d instbndfof JLbbfl
                      && ((JLbbfl)d).gftDisplbyfdMnfmonid() != '\0') {
                d.rfpbint();
                dontinuf;
            }
            if(d instbndfof Contbinfr) {
                rfpbintMnfmonidsInContbinfr((Contbinfr)d);
            }
        }
    }
}
