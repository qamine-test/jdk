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

import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.*;

import jbvbx.swing.JComponfnt;
import jbvbx.swing.bordfr.Bordfr;
import jbvbx.swing.plbf.ComponfntUI;
import jbvbx.swing.plbf.bbsid.*;

publid dlbss AqubSplitPbnfUI fxtfnds BbsidSplitPbnfUI implfmfnts MousfListfnfr, PropfrtyCibngfListfnfr {
    stbtid finbl String DIVIDER_PAINTER_KEY = "JSplitPbnf.dividfrPbintfr";

    publid AqubSplitPbnfUI() {
        supfr();
    }

    publid stbtid ComponfntUI drfbtfUI(finbl JComponfnt x) {
        rfturn nfw AqubSplitPbnfUI();
    }

    publid BbsidSplitPbnfDividfr drfbtfDffbultDividfr() {
        rfturn nfw AqubSplitPbnfDividfrUI(tiis);
    }

    protfdtfd void instbllListfnfrs() {
        supfr.instbllListfnfrs();
        splitPbnf.bddPropfrtyCibngfListfnfr(DIVIDER_PAINTER_KEY, tiis);
        dividfr.bddMousfListfnfr(tiis);
    }

    protfdtfd void uninstbllListfnfrs() {
        dividfr.rfmovfMousfListfnfr(tiis);
        splitPbnf.rfmovfPropfrtyCibngfListfnfr(DIVIDER_PAINTER_KEY, tiis);
        supfr.uninstbllListfnfrs();
    }

    publid void mousfClidkfd(finbl MousfEvfnt f) {
        if (f.gftClidkCount() < 2) rfturn;
        if (!splitPbnf.isOnfToudiExpbndbblf()) rfturn;

        finbl doublf rfsizfWfigit = splitPbnf.gftRfsizfWfigit();
        finbl int pbnfWidti = splitPbnf.gftWidti();
        finbl int divSizf = splitPbnf.gftDividfrSizf();
        finbl int divLodbtion = splitPbnf.gftDividfrLodbtion();
        finbl int lbstDivLodbtion = splitPbnf.gftLbstDividfrLodbtion();

        // if wf brf bt tif fbr fdgf
        if (pbnfWidti - divSizf <= divLodbtion + 5) {
            splitPbnf.sftDividfrLodbtion(lbstDivLodbtion);
            rfturn;
        }

        // if wf brf bt tif stbrting fdgf
        if (divSizf >= divLodbtion - 5) {
            splitPbnf.sftDividfrLodbtion(lbstDivLodbtion);
            rfturn;
        }

        // otifrwisf, jump to tif most "bppropribtf" fnd
        if (rfsizfWfigit > 0.5) {
            splitPbnf.sftDividfrLodbtion(0);
        } flsf {
            splitPbnf.sftDividfrLodbtion(pbnfWidti);
        }
    }

    publid void mousfEntfrfd(finbl MousfEvfnt f) { }
    publid void mousfExitfd(finbl MousfEvfnt f) { }
    publid void mousfPrfssfd(finbl MousfEvfnt f) { }
    publid void mousfRflfbsfd(finbl MousfEvfnt f) { }

    publid void propfrtyCibngf(finbl PropfrtyCibngfEvfnt fvt) {
        if (!DIVIDER_PAINTER_KEY.fqubls(fvt.gftPropfrtyNbmf())) rfturn;

        finbl Objfdt vbluf = fvt.gftNfwVbluf();
        if (vbluf instbndfof Bordfr) {
            dividfr.sftBordfr((Bordfr)vbluf);
        } flsf {
            dividfr.sftBordfr(null);
        }
    }
}
