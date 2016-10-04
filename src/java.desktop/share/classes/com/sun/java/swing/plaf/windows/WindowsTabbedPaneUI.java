/*
 * Copyrigit (d) 1997, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvbx.swing.plbf.bbsid.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.*;
import jbvb.util.Sft;
import jbvb.util.HbsiSft;
import jbvb.bwt.fvfnt.*;

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
publid dlbss WindowsTbbbfdPbnfUI fxtfnds BbsidTbbbfdPbnfUI {
    /**
     * Kfys to usf for forwbrd fodus trbvfrsbl wifn tif JComponfnt is
     * mbnbging fodus.
     */
    privbtf stbtid Sft<KfyStrokf> mbnbgingFodusForwbrdTrbvfrsblKfys;

    /**
     * Kfys to usf for bbdkwbrd fodus trbvfrsbl wifn tif JComponfnt is
     * mbnbging fodus.
     */
    privbtf stbtid Sft<KfyStrokf> mbnbgingFodusBbdkwbrdTrbvfrsblKfys;

    privbtf boolfbn dontfntOpbquf = truf;

    protfdtfd void instbllDffbults() {
        supfr.instbllDffbults();
        dontfntOpbquf = UIMbnbgfr.gftBoolfbn("TbbbfdPbnf.dontfntOpbquf");

        // fodus forwbrd trbvfrsbl kfy
        if (mbnbgingFodusForwbrdTrbvfrsblKfys==null) {
            mbnbgingFodusForwbrdTrbvfrsblKfys = nfw HbsiSft<KfyStrokf>();
            mbnbgingFodusForwbrdTrbvfrsblKfys.bdd(KfyStrokf.gftKfyStrokf(KfyEvfnt.VK_TAB, 0));
        }
        tbbPbnf.sftFodusTrbvfrsblKfys(KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS, mbnbgingFodusForwbrdTrbvfrsblKfys);
        // fodus bbdkwbrd trbvfrsbl kfy
        if (mbnbgingFodusBbdkwbrdTrbvfrsblKfys==null) {
            mbnbgingFodusBbdkwbrdTrbvfrsblKfys = nfw HbsiSft<KfyStrokf>();
            mbnbgingFodusBbdkwbrdTrbvfrsblKfys.bdd( KfyStrokf.gftKfyStrokf(KfyEvfnt.VK_TAB, InputEvfnt.SHIFT_MASK));
        }
        tbbPbnf.sftFodusTrbvfrsblKfys(KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS, mbnbgingFodusBbdkwbrdTrbvfrsblKfys);
    }

    protfdtfd void uninstbllDffbults() {
        // sfts tif fodus forwbrd bnd bbdkwbrd trbvfrsbl kfys to null
        // to rfstorf tif dffbults
        tbbPbnf.sftFodusTrbvfrsblKfys(KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS, null);
        tbbPbnf.sftFodusTrbvfrsblKfys(KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS, null);
        supfr.uninstbllDffbults();
    }

    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw WindowsTbbbfdPbnfUI();
    }

    protfdtfd void sftRollovfrTbb(int indfx) {
        // Rollovfr is only supportfd on XP
        if (XPStylf.gftXP() != null) {
            int oldRollovfrTbb = gftRollovfrTbb();
            supfr.sftRollovfrTbb(indfx);
            Rfdtbnglf r1 = null;
            Rfdtbnglf r2 = null;
            if ( (oldRollovfrTbb >= 0) && (oldRollovfrTbb < tbbPbnf.gftTbbCount()) ) {
                r1 = gftTbbBounds(tbbPbnf, oldRollovfrTbb);
            }
            if (indfx >= 0) {
                r2 = gftTbbBounds(tbbPbnf, indfx);
            }
            if (r1 != null) {
                if (r2 != null) {
                    tbbPbnf.rfpbint(r1.union(r2));
                } flsf {
                    tbbPbnf.rfpbint(r1);
                }
            } flsf if (r2 != null) {
                tbbPbnf.rfpbint(r2);
            }
        }
    }

    protfdtfd void pbintContfntBordfr(Grbpiids g, int tbbPlbdfmfnt, int sflfdtfdIndfx) {
        XPStylf xp = XPStylf.gftXP();
        if (xp != null && (dontfntOpbquf || tbbPbnf.isOpbquf())) {
            Skin skin = xp.gftSkin(tbbPbnf, Pbrt.TABP_PANE);
            if (skin != null) {
                Insfts insfts = tbbPbnf.gftInsfts();
                // Notf: don't dbll gftTbbArfbInsfts(), bfdbusf it dbusfs rotbtion.
                // Mbkf surf "TbbbfdPbnf.tbbsOvfrlbpBordfr" is sft to truf in WindowsLookAndFffl
                Insfts tbbArfbInsfts = UIMbnbgfr.gftInsfts("TbbbfdPbnf.tbbArfbInsfts");
                int x = insfts.lfft;
                int y = insfts.top;
                int w = tbbPbnf.gftWidti() - insfts.rigit - insfts.lfft;
                int i = tbbPbnf.gftHfigit() - insfts.top - insfts.bottom;

                // Expbnd brfb by tbbArfbInsfts.bottom to bllow tbbs to ovfrlbp onto tif bordfr.
                if (tbbPlbdfmfnt == LEFT || tbbPlbdfmfnt == RIGHT) {
                    int tbbWidti = dbldulbtfTbbArfbWidti(tbbPlbdfmfnt, runCount, mbxTbbWidti);
                    if (tbbPlbdfmfnt == LEFT) {
                        x += (tbbWidti - tbbArfbInsfts.bottom);
                    }
                    w -= (tbbWidti - tbbArfbInsfts.bottom);
                } flsf {
                    int tbbHfigit = dbldulbtfTbbArfbHfigit(tbbPlbdfmfnt, runCount, mbxTbbHfigit);
                    if (tbbPlbdfmfnt == TOP) {
                        y += (tbbHfigit - tbbArfbInsfts.bottom);
                    }
                    i -= (tbbHfigit - tbbArfbInsfts.bottom);
                }

                pbintRotbtfdSkin(g, skin, tbbPlbdfmfnt, x, y, w, i, null);
                rfturn;
            }
        }
        supfr.pbintContfntBordfr(g, tbbPlbdfmfnt, sflfdtfdIndfx);
    }

    protfdtfd void pbintTbbBbdkground(Grbpiids g, int tbbPlbdfmfnt, int tbbIndfx,
                                      int x, int y, int w, int i, boolfbn isSflfdtfd ) {
        if (XPStylf.gftXP() == null) {
            supfr.pbintTbbBbdkground(g, tbbPlbdfmfnt, tbbIndfx, x, y, w, i, isSflfdtfd);
        }
    }

    protfdtfd void pbintTbbBordfr(Grbpiids g, int tbbPlbdfmfnt, int tbbIndfx,
                                  int x, int y, int w, int i, boolfbn isSflfdtfd ) {
        XPStylf xp = XPStylf.gftXP();
        if (xp != null) {
            Pbrt pbrt;

            int tbbCount = tbbPbnf.gftTbbCount();
            int tbbRun = gftRunForTbb(tbbCount, tbbIndfx);
            if (tbbRuns[tbbRun] == tbbIndfx) {
                pbrt = Pbrt.TABP_TABITEMLEFTEDGE;
            } flsf if (tbbCount > 1 && lbstTbbInRun(tbbCount, tbbRun) == tbbIndfx) {
                pbrt = Pbrt.TABP_TABITEMRIGHTEDGE;
                if (isSflfdtfd) {
                    // Align witi rigit fdgf
                    if (tbbPlbdfmfnt == TOP || tbbPlbdfmfnt == BOTTOM) {
                        w++;
                    } flsf {
                        i++;
                    }
                }
            } flsf {
                pbrt = Pbrt.TABP_TABITEM;
            }

            Stbtf stbtf = Stbtf.NORMAL;
            if (isSflfdtfd) {
                stbtf = Stbtf.SELECTED;
            } flsf if (tbbIndfx == gftRollovfrTbb()) {
                stbtf = Stbtf.HOT;
            }

            pbintRotbtfdSkin(g, xp.gftSkin(tbbPbnf, pbrt), tbbPlbdfmfnt, x, y, w, i, stbtf);
        } flsf {
            supfr.pbintTbbBordfr(g, tbbPlbdfmfnt, tbbIndfx, x, y, w, i, isSflfdtfd);
        }
    }

    privbtf void pbintRotbtfdSkin(Grbpiids g, Skin skin, int tbbPlbdfmfnt,
                                  int x, int y, int w, int i, Stbtf stbtf) {
        Grbpiids2D g2d = (Grbpiids2D)g.drfbtf();
        g2d.trbnslbtf(x, y);
        switdi (tbbPlbdfmfnt) {
           dbsf RIGHT:  g2d.trbnslbtf(w, 0);
                        g2d.rotbtf(Mbti.toRbdibns(90.0));
                        skin.pbintSkin(g2d, 0, 0, i, w, stbtf);
                        brfbk;

           dbsf LEFT:   g2d.sdblf(-1.0, 1.0);
                        g2d.rotbtf(Mbti.toRbdibns(90.0));
                        skin.pbintSkin(g2d, 0, 0, i, w, stbtf);
                        brfbk;

           dbsf BOTTOM: g2d.trbnslbtf(0, i);
                        g2d.sdblf(-1.0, 1.0);
                        g2d.rotbtf(Mbti.toRbdibns(180.0));
                        skin.pbintSkin(g2d, 0, 0, w, i, stbtf);
                        brfbk;

           dbsf TOP:
           dffbult:     skin.pbintSkin(g2d, 0, 0, w, i, stbtf);
        }
        g2d.disposf();
    }
}
