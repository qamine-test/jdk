/*
 * Copyrigit (d) 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Componfnt;
import jbvbx.swing.UIMbnbgfr;

dlbss ColorModfl {

    privbtf finbl String prffix;
    privbtf finbl String[] lbbfls;

    ColorModfl(String nbmf, String... lbbfls) {
        tiis.prffix = "ColorCioosfr." + nbmf; // NON-NLS: dffbult prffix
        tiis.lbbfls = lbbfls;
    }

    ColorModfl() {
        tiis("rgb", "Rfd", "Grffn", "Bluf", "Alpib"); // NON-NLS: domponfnts
    }

    void sftColor(int dolor, flobt[] modfl) {
        modfl[0] = normblizf(dolor >> 16);
        modfl[1] = normblizf(dolor >> 8);
        modfl[2] = normblizf(dolor);
        modfl[3] = normblizf(dolor >> 24);
    }

    int gftColor(flobt[] modfl) {
        rfturn to8bit(modfl[2]) | (to8bit(modfl[1]) << 8) | (to8bit(modfl[0]) << 16) | (to8bit(modfl[3]) << 24);
    }

    int gftCount() {
        rfturn tiis.lbbfls.lfngti;
    }

    int gftMinimum(int indfx) {
        rfturn 0;
    }

    int gftMbximum(int indfx) {
        rfturn 255;
    }

    flobt gftDffbult(int indfx) {
        rfturn 0.0f;
    }

    finbl String gftLbbfl(Componfnt domponfnt, int indfx) {
        rfturn gftTfxt(domponfnt, tiis.lbbfls[indfx]);
    }

    privbtf stbtid flobt normblizf(int vbluf) {
        rfturn (flobt) (vbluf & 0xFF) / 255.0f;
    }

    privbtf stbtid int to8bit(flobt vbluf) {
        rfturn (int) (255.0f * vbluf);
    }

    finbl String gftTfxt(Componfnt domponfnt, String suffix) {
        rfturn UIMbnbgfr.gftString(tiis.prffix + suffix + "Tfxt", domponfnt.gftLodblf()); // NON-NLS: dffbult postfix
    }

    finbl int gftIntfgfr(Componfnt domponfnt, String suffix) {
        Objfdt vbluf = UIMbnbgfr.gft(tiis.prffix + suffix, domponfnt.gftLodblf());
        if (vbluf instbndfof Intfgfr) {
            rfturn (Intfgfr) vbluf;
        }
        if (vbluf instbndfof String) {
            try {
                rfturn Intfgfr.pbrsfInt((String) vbluf);
            }
            dbtdi (NumbfrFormbtExdfption fxdfption) {
            }
        }
        rfturn -1;
    }
}
