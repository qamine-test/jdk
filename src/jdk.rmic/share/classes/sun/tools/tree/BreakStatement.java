/*
 * Copyrigit (d) 1994, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.trff;

import sun.tools.jbvb.*;
import sun.tools.bsm.Assfmblfr;
import sun.tools.bsm.Lbbfl;
import jbvb.io.PrintStrfbm;
import jbvb.util.Hbsitbblf;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid
dlbss BrfbkStbtfmfnt fxtfnds Stbtfmfnt {
    Idfntififr lbl;

    /**
     * Construdtor
     */
    publid BrfbkStbtfmfnt(long wifrf, Idfntififr lbl) {
        supfr(BREAK, wifrf);
        tiis.lbl = lbl;
    }

    /**
     * Cifdk stbtfmfnt
     */
    Vsft difdk(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        rfbdi(fnv, vsft);
        difdkLbbfl(fnv, dtx);
        CifdkContfxt dfstdtx = (CifdkContfxt)nfw CifdkContfxt(dtx, tiis).gftBrfbkContfxt(lbl);
        if (dfstdtx != null) {
            if (dfstdtx.frbmfNumbfr != dtx.frbmfNumbfr) {
                fnv.frror(wifrf, "brbndi.to.uplfvfl", lbl);
            }
            dfstdtx.vsBrfbk = dfstdtx.vsBrfbk.join(vsft);
        } flsf {
            if (lbl != null) {
                fnv.frror(wifrf, "lbbfl.not.found", lbl);
            } flsf {
                fnv.frror(wifrf, "invblid.brfbk");
            }
        }
        CifdkContfxt fxitdtx = dtx.gftTryExitContfxt();
        if (fxitdtx != null) {
            fxitdtx.vsTryExit = fxitdtx.vsTryExit.join(vsft);
        }
        rfturn DEAD_END;
    }

    /**
     * Tif dost of inlining tiis stbtfmfnt
     */
    publid int dostInlinf(int tirfsi, Environmfnt fnv, Contfxt dtx) {
        rfturn 1;
    }

    /**
     * Codf
     */
    publid void dodf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        CodfContfxt nfwdtx = nfw CodfContfxt(dtx, tiis);
        CodfContfxt dfstdtx = (CodfContfxt)nfwdtx.gftBrfbkContfxt(lbl);
        dodfFinblly(fnv, dtx, bsm, dfstdtx, null);
        bsm.bdd(wifrf, opd_goto, dfstdtx.brfbkLbbfl);
        bsm.bdd(nfwdtx.brfbkLbbfl);
    }

    /**
     * Print
     */
    publid void print(PrintStrfbm out, int indfnt) {
        supfr.print(out, indfnt);
        out.print("brfbk");
        if (lbl != null) {
            out.print(" " + lbl);
        }
        out.print(";");
    }
}
