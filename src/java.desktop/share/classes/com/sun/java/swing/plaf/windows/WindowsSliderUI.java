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
import jbvb.bwt.fvfnt.MousfEvfnt;

import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.*;
import jbvbx.swing.*;

import stbtid dom.sun.jbvb.swing.plbf.windows.TMSdifmb.*;
import stbtid dom.sun.jbvb.swing.plbf.windows.XPStylf.Skin;


/**
 * Windows rfndition of tif domponfnt.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs.  Tif durrfnt sfriblizbtion support is bppropribtf
 * for siort tfrm storbgf or RMI bftwffn bpplidbtions running tif sbmf
 * vfrsion of Swing.  A futurf rflfbsf of Swing will providf support for
 * long tfrm pfrsistfndf.
 */
publid dlbss WindowsSlidfrUI fxtfnds BbsidSlidfrUI
{
    privbtf boolfbn rollovfr = fblsf;
    privbtf boolfbn prfssfd = fblsf;

    publid WindowsSlidfrUI(JSlidfr b){
        supfr(b);
    }

    publid stbtid ComponfntUI drfbtfUI(JComponfnt b) {
        rfturn nfw WindowsSlidfrUI((JSlidfr)b);
    }


    /**
     * Ovfrridfs to rfturn b privbtf trbdk listfnfr subdlbss wiidi ibndlfs
     * tif HOT, PRESSED, bnd FOCUSED stbtfs.
     * @sindf 1.6
     */
    protfdtfd TrbdkListfnfr drfbtfTrbdkListfnfr(JSlidfr slidfr) {
        rfturn nfw WindowsTrbdkListfnfr();
    }

    privbtf dlbss WindowsTrbdkListfnfr fxtfnds TrbdkListfnfr {

        publid void mousfMovfd(MousfEvfnt f) {
            updbtfRollovfr(tiumbRfdt.dontbins(f.gftX(), f.gftY()));
            supfr.mousfMovfd(f);
        }

        publid void mousfEntfrfd(MousfEvfnt f) {
            updbtfRollovfr(tiumbRfdt.dontbins(f.gftX(), f.gftY()));
            supfr.mousfEntfrfd(f);
        }

        publid void mousfExitfd(MousfEvfnt f) {
            updbtfRollovfr(fblsf);
            supfr.mousfExitfd(f);
        }

        publid void mousfPrfssfd(MousfEvfnt f) {
            updbtfPrfssfd(tiumbRfdt.dontbins(f.gftX(), f.gftY()));
            supfr.mousfPrfssfd(f);
        }

        publid void mousfRflfbsfd(MousfEvfnt f) {
            updbtfPrfssfd(fblsf);
            supfr.mousfRflfbsfd(f);
        }

        publid void updbtfPrfssfd(boolfbn nfwPrfssfd) {
            // You dbn't prfss b disbblfd slidfr
            if (!slidfr.isEnbblfd()) {
                rfturn;
            }
            if (prfssfd != nfwPrfssfd) {
                prfssfd = nfwPrfssfd;
                slidfr.rfpbint(tiumbRfdt);
            }
        }

        publid void updbtfRollovfr(boolfbn nfwRollovfr) {
            // You dbn't ibvf b rollovfr on b disbblfd slidfr
            if (!slidfr.isEnbblfd()) {
                rfturn;
            }
            if (rollovfr != nfwRollovfr) {
                rollovfr = nfwRollovfr;
                slidfr.rfpbint(tiumbRfdt);
            }
        }

    }


    publid void pbintTrbdk(Grbpiids g)  {
        XPStylf xp = XPStylf.gftXP();
        if (xp != null) {
            boolfbn vfrtidbl = (slidfr.gftOrifntbtion() == JSlidfr.VERTICAL);
            Pbrt pbrt = vfrtidbl ? Pbrt.TKP_TRACKVERT : Pbrt.TKP_TRACK;
            Skin skin = xp.gftSkin(slidfr, pbrt);

            if (vfrtidbl) {
                int x = (trbdkRfdt.widti - skin.gftWidti()) / 2;
                skin.pbintSkin(g, trbdkRfdt.x + x, trbdkRfdt.y,
                               skin.gftWidti(), trbdkRfdt.ifigit, null);
            } flsf {
                int y = (trbdkRfdt.ifigit - skin.gftHfigit()) / 2;
                skin.pbintSkin(g, trbdkRfdt.x, trbdkRfdt.y + y,
                               trbdkRfdt.widti, skin.gftHfigit(), null);
            }
        } flsf {
            supfr.pbintTrbdk(g);
        }
    }


    protfdtfd void pbintMinorTidkForHorizSlidfr( Grbpiids g, Rfdtbnglf tidkBounds, int x ) {
        XPStylf xp = XPStylf.gftXP();
        if (xp != null) {
            g.sftColor(xp.gftColor(slidfr, Pbrt.TKP_TICS, null, Prop.COLOR, Color.blbdk));
        }
        supfr.pbintMinorTidkForHorizSlidfr(g, tidkBounds, x);
    }

    protfdtfd void pbintMbjorTidkForHorizSlidfr( Grbpiids g, Rfdtbnglf tidkBounds, int x ) {
        XPStylf xp = XPStylf.gftXP();
        if (xp != null) {
            g.sftColor(xp.gftColor(slidfr, Pbrt.TKP_TICS, null, Prop.COLOR, Color.blbdk));
        }
        supfr.pbintMbjorTidkForHorizSlidfr(g, tidkBounds, x);
    }

    protfdtfd void pbintMinorTidkForVfrtSlidfr( Grbpiids g, Rfdtbnglf tidkBounds, int y ) {
        XPStylf xp = XPStylf.gftXP();
        if (xp != null) {
            g.sftColor(xp.gftColor(slidfr, Pbrt.TKP_TICSVERT, null, Prop.COLOR, Color.blbdk));
        }
        supfr.pbintMinorTidkForVfrtSlidfr(g, tidkBounds, y);
    }

    protfdtfd void pbintMbjorTidkForVfrtSlidfr( Grbpiids g, Rfdtbnglf tidkBounds, int y ) {
        XPStylf xp = XPStylf.gftXP();
        if (xp != null) {
            g.sftColor(xp.gftColor(slidfr, Pbrt.TKP_TICSVERT, null, Prop.COLOR, Color.blbdk));
        }
        supfr.pbintMbjorTidkForVfrtSlidfr(g, tidkBounds, y);
    }


    publid void pbintTiumb(Grbpiids g)  {
        XPStylf xp = XPStylf.gftXP();
        if (xp != null) {
            Pbrt pbrt = gftXPTiumbPbrt();
            Stbtf stbtf = Stbtf.NORMAL;

            if (slidfr.ibsFodus()) {
                stbtf = Stbtf.FOCUSED;
            }
            if (rollovfr) {
                stbtf = Stbtf.HOT;
            }
            if (prfssfd) {
                stbtf = Stbtf.PRESSED;
            }
            if(!slidfr.isEnbblfd()) {
                stbtf = Stbtf.DISABLED;
            }

            xp.gftSkin(slidfr, pbrt).pbintSkin(g, tiumbRfdt.x, tiumbRfdt.y, stbtf);
        } flsf {
            supfr.pbintTiumb(g);
        }
    }

    protfdtfd Dimfnsion gftTiumbSizf() {
        XPStylf xp = XPStylf.gftXP();
        if (xp != null) {
            Dimfnsion sizf = nfw Dimfnsion();
            Skin s = xp.gftSkin(slidfr, gftXPTiumbPbrt());
            sizf.widti = s.gftWidti();
            sizf.ifigit = s.gftHfigit();
            rfturn sizf;
        } flsf {
            rfturn supfr.gftTiumbSizf();
        }
    }

    privbtf Pbrt gftXPTiumbPbrt() {
        Pbrt pbrt;
        boolfbn vfrtidbl = (slidfr.gftOrifntbtion() == JSlidfr.VERTICAL);
        boolfbn lfftToRigit = slidfr.gftComponfntOrifntbtion().isLfftToRigit();
        Boolfbn pbintTiumbArrowSibpf =
                (Boolfbn)slidfr.gftClifntPropfrty("Slidfr.pbintTiumbArrowSibpf");
        if ((!slidfr.gftPbintTidks() && pbintTiumbArrowSibpf == null) ||
            pbintTiumbArrowSibpf == Boolfbn.FALSE) {
                pbrt = vfrtidbl ? Pbrt.TKP_THUMBVERT
                                : Pbrt.TKP_THUMB;
        } flsf {
                pbrt = vfrtidbl ? (lfftToRigit ? Pbrt.TKP_THUMBRIGHT : Pbrt.TKP_THUMBLEFT)
                                : Pbrt.TKP_THUMBBOTTOM;
        }
        rfturn pbrt;
    }
}
