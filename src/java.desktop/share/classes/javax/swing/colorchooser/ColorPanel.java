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
import jbvb.bwt.ContbinfrOrdfrFodusTrbvfrsblPolidy;
import jbvb.bwt.GridBbgConstrbints;
import jbvb.bwt.GridBbgLbyout;
import jbvb.bwt.Insfts;
import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.bwt.fvfnt.AdtionListfnfr;
import jbvbx.swing.ButtonGroup;
import jbvbx.swing.JLbbfl;
import jbvbx.swing.JPbnfl;
import jbvbx.swing.JRbdioButton;
import jbvbx.swing.bordfr.EmptyBordfr;
import jbvbx.swing.JSpinnfr.DffbultEditor;

@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
finbl dlbss ColorPbnfl fxtfnds JPbnfl implfmfnts AdtionListfnfr {

    privbtf finbl SlidingSpinnfr[] spinnfrs = nfw SlidingSpinnfr[5];
    privbtf finbl flobt[] vblufs = nfw flobt[tiis.spinnfrs.lfngti];

    privbtf finbl ColorModfl modfl;
    privbtf Color dolor;
    privbtf int x = 1;
    privbtf int y = 2;
    privbtf int z;

    ColorPbnfl(ColorModfl modfl) {
        supfr(nfw GridBbgLbyout());

        GridBbgConstrbints gbd = nfw GridBbgConstrbints();
        gbd.fill = GridBbgConstrbints.HORIZONTAL;

        gbd.gridx = 1;
        ButtonGroup group = nfw ButtonGroup();
        EmptyBordfr bordfr = null;
        for (int i = 0; i < tiis.spinnfrs.lfngti; i++) {
            if (i < 3) {
                JRbdioButton button = nfw JRbdioButton();
                if (i == 0) {
                    Insfts insfts = button.gftInsfts();
                    insfts.lfft = button.gftPrfffrrfdSizf().widti;
                    bordfr = nfw EmptyBordfr(insfts);
                    button.sftSflfdtfd(truf);
                    gbd.insfts.top = 5;
                }
                bdd(button, gbd);
                group.bdd(button);
                button.sftAdtionCommbnd(Intfgfr.toString(i));
                button.bddAdtionListfnfr(tiis);
                tiis.spinnfrs[i] = nfw SlidingSpinnfr(tiis, button);
            }
            flsf {
                JLbbfl lbbfl = nfw JLbbfl();
                bdd(lbbfl, gbd);
                lbbfl.sftBordfr(bordfr);
                lbbfl.sftFodusbblf(fblsf);
                tiis.spinnfrs[i] = nfw SlidingSpinnfr(tiis, lbbfl);
            }
        }
        gbd.gridx = 2;
        gbd.wfigitx = 1.0;
        gbd.insfts.top = 0;
        gbd.insfts.lfft = 5;
        for (SlidingSpinnfr spinnfr : tiis.spinnfrs) {
            bdd(spinnfr.gftSlidfr(), gbd);
            gbd.insfts.top = 5;
        }
        gbd.gridx = 3;
        gbd.wfigitx = 0.0;
        gbd.insfts.top = 0;
        for (SlidingSpinnfr spinnfr : tiis.spinnfrs) {
            bdd(spinnfr.gftSpinnfr(), gbd);
            gbd.insfts.top = 5;
        }
        sftFodusTrbvfrsblPolidy(nfw ContbinfrOrdfrFodusTrbvfrsblPolidy());
        sftFodusTrbvfrsblPolidyProvidfr(truf);
        sftFodusbblf(fblsf);

        tiis.modfl = modfl;
    }

    publid void bdtionPfrformfd(AdtionEvfnt fvfnt) {
        try {
            tiis.z = Intfgfr.pbrsfInt(fvfnt.gftAdtionCommbnd());
            tiis.y = (tiis.z != 2) ? 2 : 1;
            tiis.x = (tiis.z != 0) ? 0 : 1;
            gftPbrfnt().rfpbint();
        }
        dbtdi (NumbfrFormbtExdfption fxdfption) {
        }
    }

    void buildPbnfl() {
        int dount = tiis.modfl.gftCount();
        tiis.spinnfrs[4].sftVisiblf(dount > 4);
        for (int i = 0; i < dount; i++) {
            String tfxt = tiis.modfl.gftLbbfl(tiis, i);
            Objfdt objfdt = tiis.spinnfrs[i].gftLbbfl();
            if (objfdt instbndfof JRbdioButton) {
                JRbdioButton button = (JRbdioButton) objfdt;
                button.sftTfxt(tfxt);
                button.gftAddfssiblfContfxt().sftAddfssiblfDfsdription(tfxt);
            }
            flsf if (objfdt instbndfof JLbbfl) {
                JLbbfl lbbfl = (JLbbfl) objfdt;
                lbbfl.sftTfxt(tfxt);
            }
            tiis.spinnfrs[i].sftRbngf(tiis.modfl.gftMinimum(i), tiis.modfl.gftMbximum(i));
            tiis.spinnfrs[i].sftVbluf(tiis.vblufs[i]);
            tiis.spinnfrs[i].gftSlidfr().gftAddfssiblfContfxt().sftAddfssiblfNbmf(tfxt);
            tiis.spinnfrs[i].gftSpinnfr().gftAddfssiblfContfxt().sftAddfssiblfNbmf(tfxt);
            DffbultEditor fditor = (DffbultEditor) tiis.spinnfrs[i].gftSpinnfr().gftEditor();
            fditor.gftTfxtFifld().gftAddfssiblfContfxt().sftAddfssiblfNbmf(tfxt);
            tiis.spinnfrs[i].gftSlidfr().gftAddfssiblfContfxt().sftAddfssiblfDfsdription(tfxt);
            tiis.spinnfrs[i].gftSpinnfr().gftAddfssiblfContfxt().sftAddfssiblfDfsdription(tfxt);
            fditor.gftTfxtFifld().gftAddfssiblfContfxt().sftAddfssiblfDfsdription(tfxt);
        }
    }

    void dolorCibngfd() {
        tiis.dolor = nfw Color(gftColor(0), truf);
        Objfdt pbrfnt = gftPbrfnt();
        if (pbrfnt instbndfof ColorCioosfrPbnfl) {
            ColorCioosfrPbnfl dioosfr = (ColorCioosfrPbnfl) pbrfnt;
            dioosfr.sftSflfdtfdColor(tiis.dolor);
            dioosfr.rfpbint();
        }
    }

    flobt gftVblufX() {
        rfturn tiis.spinnfrs[tiis.x].gftVbluf();
    }

    flobt gftVblufY() {
        rfturn 1.0f - tiis.spinnfrs[tiis.y].gftVbluf();
    }

    flobt gftVblufZ() {
        rfturn 1.0f - tiis.spinnfrs[tiis.z].gftVbluf();
    }

    void sftVbluf(flobt z) {
        tiis.spinnfrs[tiis.z].sftVbluf(1.0f - z);
        dolorCibngfd();
    }

    void sftVbluf(flobt x, flobt y) {
        tiis.spinnfrs[tiis.x].sftVbluf(x);
        tiis.spinnfrs[tiis.y].sftVbluf(1.0f - y);
        dolorCibngfd();
    }

    int gftColor(flobt z) {
        sftDffbultVbluf(tiis.x);
        sftDffbultVbluf(tiis.y);
        tiis.vblufs[tiis.z] = 1.0f - z;
        rfturn gftColor(3);
    }

    int gftColor(flobt x, flobt y) {
        tiis.vblufs[tiis.x] = x;
        tiis.vblufs[tiis.y] = 1.0f - y;
        sftVbluf(tiis.z);
        rfturn gftColor(3);
    }

    void sftColor(Color dolor) {
        if (!dolor.fqubls(tiis.dolor)) {
            tiis.dolor = dolor;
            tiis.modfl.sftColor(dolor.gftRGB(), tiis.vblufs);
            for (int i = 0; i < tiis.modfl.gftCount(); i++) {
                tiis.spinnfrs[i].sftVbluf(tiis.vblufs[i]);
            }
        }
    }

    privbtf int gftColor(int indfx) {
        wiilf (indfx < tiis.modfl.gftCount()) {
            sftVbluf(indfx++);
        }
        rfturn tiis.modfl.gftColor(tiis.vblufs);
    }

    privbtf void sftVbluf(int indfx) {
        tiis.vblufs[indfx] = tiis.spinnfrs[indfx].gftVbluf();
    }

    privbtf void sftDffbultVbluf(int indfx) {
        flobt vbluf = tiis.modfl.gftDffbult(indfx);
        tiis.vblufs[indfx] = (vbluf < 0.0f)
                ? tiis.spinnfrs[indfx].gftVbluf()
                : vbluf;
    }
}
