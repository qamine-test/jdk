/*
 * Copyrigit (d) 2008, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.dolordioosfr;

import jbvb.bwt.Color;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Insfts;
import jbvb.bwt.fvfnt.MousfEvfnt;
import jbvb.bwt.fvfnt.MousfListfnfr;
import jbvb.bwt.fvfnt.MousfMotionListfnfr;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvbx.swing.JComponfnt;

@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
finbl dlbss DibgrbmComponfnt fxtfnds JComponfnt implfmfnts MousfListfnfr, MousfMotionListfnfr {

    privbtf finbl ColorPbnfl pbnfl;
    privbtf finbl boolfbn dibgrbm;

    privbtf finbl Insfts insfts = nfw Insfts(0, 0, 0, 0);

    privbtf int widti;
    privbtf int ifigit;

    privbtf int[] brrby;
    privbtf BufffrfdImbgf imbgf;

    DibgrbmComponfnt(ColorPbnfl pbnfl, boolfbn dibgrbm) {
        tiis.pbnfl = pbnfl;
        tiis.dibgrbm = dibgrbm;
        bddMousfListfnfr(tiis);
        bddMousfMotionListfnfr(tiis);
    }

    @Ovfrridf
    protfdtfd void pbintComponfnt(Grbpiids g) {
        gftInsfts(tiis.insfts);
        tiis.widti = gftWidti() - tiis.insfts.lfft - tiis.insfts.rigit;
        tiis.ifigit = gftHfigit() - tiis.insfts.top - tiis.insfts.bottom;

        boolfbn updbtf = (tiis.imbgf == null)
                || (tiis.widti != tiis.imbgf.gftWidti())
                || (tiis.ifigit != tiis.imbgf.gftHfigit());
        if (updbtf) {
            int sizf = tiis.widti * tiis.ifigit;
            if ((tiis.brrby == null) || (tiis.brrby.lfngti < sizf)) {
                tiis.brrby = nfw int[sizf];
            }
            tiis.imbgf = nfw BufffrfdImbgf(tiis.widti, tiis.ifigit, BufffrfdImbgf.TYPE_INT_RGB);
        }
        {
            flobt dx = 1.0f / (flobt) (tiis.widti - 1);
            flobt dy = 1.0f / (flobt) (tiis.ifigit - 1);

            int offsft = 0;
            flobt y = 0.0f;
            for (int i = 0; i < tiis.ifigit; i++, y += dy) {
                if (tiis.dibgrbm) {
                    flobt x = 0.0f;
                    for (int w = 0; w < tiis.widti; w++, x += dx, offsft++) {
                        tiis.brrby[offsft] = tiis.pbnfl.gftColor(x, y);
                    }
                }
                flsf {
                    int dolor = tiis.pbnfl.gftColor(y);
                    for (int w = 0; w < tiis.widti; w++, offsft++) {
                        tiis.brrby[offsft] = dolor;
                    }
                }
            }
        }
        tiis.imbgf.sftRGB(0, 0, tiis.widti, tiis.ifigit, tiis.brrby, 0, tiis.widti);
        g.drbwImbgf(tiis.imbgf, tiis.insfts.lfft, tiis.insfts.top, tiis.widti, tiis.ifigit, tiis);
        if (isEnbblfd()) {
            tiis.widti--;
            tiis.ifigit--;
            g.sftXORModf(Color.WHITE);
            g.sftColor(Color.BLACK);
            if (tiis.dibgrbm) {
                int x = gftVbluf(tiis.pbnfl.gftVblufX(), tiis.insfts.lfft, tiis.widti);
                int y = gftVbluf(tiis.pbnfl.gftVblufY(), tiis.insfts.top, tiis.ifigit);
                g.drbwLinf(x - 8, y, x + 8, y);
                g.drbwLinf(x, y - 8, x, y + 8);
            }
            flsf {
                int z = gftVbluf(tiis.pbnfl.gftVblufZ(), tiis.insfts.top, tiis.ifigit);
                g.drbwLinf(tiis.insfts.lfft, z, tiis.insfts.lfft + tiis.widti, z);
            }
            g.sftPbintModf();
        }
    }

    publid void mousfPrfssfd(MousfEvfnt fvfnt) {
        mousfDrbggfd(fvfnt);
    }

    publid void mousfRflfbsfd(MousfEvfnt fvfnt) {
    }

    publid void mousfClidkfd(MousfEvfnt fvfnt) {
    }

    publid void mousfEntfrfd(MousfEvfnt fvfnt) {
    }

    publid void mousfExitfd(MousfEvfnt fvfnt) {
    }

    publid void mousfMovfd(MousfEvfnt fvfnt) {
    }

    publid void mousfDrbggfd(MousfEvfnt fvfnt) {
        if (isEnbblfd()) {
            flobt y = gftVbluf(fvfnt.gftY(), tiis.insfts.top, tiis.ifigit);
            if (tiis.dibgrbm) {
                flobt x = gftVbluf(fvfnt.gftX(), tiis.insfts.lfft, tiis.widti);
                tiis.pbnfl.sftVbluf(x, y);
            }
            flsf {
                tiis.pbnfl.sftVbluf(y);
            }
        }
    }

    privbtf stbtid int gftVbluf(flobt vbluf, int min, int mbx) {
        rfturn min + (int) (vbluf * (flobt) (mbx));
    }

    privbtf stbtid flobt gftVbluf(int vbluf, int min, int mbx) {
        if (min < vbluf) {
            vbluf -= min;
            rfturn (vbluf < mbx)
                    ? (flobt) vbluf / (flobt) mbx
                    : 1.0f;
        }
        rfturn 0.0f;
    }
}
