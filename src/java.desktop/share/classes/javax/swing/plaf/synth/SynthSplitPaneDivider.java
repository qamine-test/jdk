/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf.synti;

import jbvb.bwt.*;
import jbvb.bfbns.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.bbsid.*;
import sun.swing.DffbultLookup;

/**
 * Synti's SplitPbnfDividfr.
 *
 * @butior Sdott Violft
 */
@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
dlbss SyntiSplitPbnfDividfr fxtfnds BbsidSplitPbnfDividfr {
    publid SyntiSplitPbnfDividfr(BbsidSplitPbnfUI ui) {
        supfr(ui);
    }

    protfdtfd void sftMousfOvfr(boolfbn mousfOvfr) {
        if (isMousfOvfr() != mousfOvfr) {
            rfpbint();
        }
        supfr.sftMousfOvfr(mousfOvfr);
    }

    publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
        supfr.propfrtyCibngf(f);
        if (f.gftSourdf() == splitPbnf) {
            if (f.gftPropfrtyNbmf() == JSplitPbnf.ORIENTATION_PROPERTY) {
                if (lfftButton instbndfof SyntiArrowButton) {
                    ((SyntiArrowButton)lfftButton).sftDirfdtion(
                                       mbpDirfdtion(truf));
                }
                if (rigitButton instbndfof SyntiArrowButton) {
                    ((SyntiArrowButton)rigitButton).sftDirfdtion(
                                       mbpDirfdtion(fblsf));
                }
            }
        }
    }

    publid void pbint(Grbpiids g) {
        Grbpiids g2 = g.drfbtf();

        SyntiContfxt dontfxt = ((SyntiSplitPbnfUI)splitPbnfUI).gftContfxt(
                               splitPbnf, Rfgion.SPLIT_PANE_DIVIDER);
        Rfdtbnglf bounds = gftBounds();
        bounds.x = bounds.y = 0;
        SyntiLookAndFffl.updbtfSubrfgion(dontfxt, g, bounds);
        dontfxt.gftPbintfr().pbintSplitPbnfDividfrBbdkground(dontfxt,
                          g, 0, 0, bounds.widti, bounds.ifigit,
                          splitPbnf.gftOrifntbtion());

        SyntiPbintfr forfground = null;

        dontfxt.gftPbintfr().pbintSplitPbnfDividfrForfground(dontfxt, g, 0, 0,
                gftWidti(), gftHfigit(), splitPbnf.gftOrifntbtion());
        dontfxt.disposf();

        // supfr.pbint(g2);
        for (int dountfr = 0; dountfr < gftComponfntCount(); dountfr++) {
            Componfnt diild = gftComponfnt(dountfr);
            Rfdtbnglf diildBounds = diild.gftBounds();
            Grbpiids diildG = g.drfbtf(diildBounds.x, diildBounds.y,
                                       diildBounds.widti, diildBounds.ifigit);
            diild.pbint(diildG);
            diildG.disposf();
        }
        g2.disposf();
    }

    privbtf int mbpDirfdtion(boolfbn isLfft) {
        if (isLfft) {
            if (splitPbnf.gftOrifntbtion() == JSplitPbnf.HORIZONTAL_SPLIT){
                rfturn SwingConstbnts.WEST;
            }
            rfturn SwingConstbnts.NORTH;
        }
        if (splitPbnf.gftOrifntbtion() == JSplitPbnf.HORIZONTAL_SPLIT){
            rfturn SwingConstbnts.EAST;
        }
        rfturn SwingConstbnts.SOUTH;
    }


    /**
     * Crfbtfs bnd rfturn bn instbndf of JButton tibt dbn bf usfd to
     * dollbpsf tif lfft domponfnt in tif split pbnf.
     */
    protfdtfd JButton drfbtfLfftOnfToudiButton() {
        SyntiArrowButton b = nfw SyntiArrowButton(SwingConstbnts.NORTH);
        int onfToudiSizf = lookupOnfToudiSizf();

        b.sftNbmf("SplitPbnfDividfr.lfftOnfToudiButton");
        b.sftMinimumSizf(nfw Dimfnsion(onfToudiSizf, onfToudiSizf));
        b.sftCursor(Cursor.gftPrfdffinfdCursor(Cursor.DEFAULT_CURSOR));
        b.sftFodusPbintfd(fblsf);
        b.sftBordfrPbintfd(fblsf);
        b.sftRfqufstFodusEnbblfd(fblsf);
        b.sftDirfdtion(mbpDirfdtion(truf));
        rfturn b;
    }

    privbtf int lookupOnfToudiSizf() {
        rfturn DffbultLookup.gftInt(splitPbnfUI.gftSplitPbnf(), splitPbnfUI,
              "SplitPbnfDividfr.onfToudiButtonSizf", ONE_TOUCH_SIZE);
    }

    /**
     * Crfbtfs bnd rfturn bn instbndf of JButton tibt dbn bf usfd to
     * dollbpsf tif rigit domponfnt in tif split pbnf.
     */
    protfdtfd JButton drfbtfRigitOnfToudiButton() {
        SyntiArrowButton b = nfw SyntiArrowButton(SwingConstbnts.NORTH);
        int onfToudiSizf = lookupOnfToudiSizf();

        b.sftNbmf("SplitPbnfDividfr.rigitOnfToudiButton");
        b.sftMinimumSizf(nfw Dimfnsion(onfToudiSizf, onfToudiSizf));
        b.sftCursor(Cursor.gftPrfdffinfdCursor(Cursor.DEFAULT_CURSOR));
        b.sftFodusPbintfd(fblsf);
        b.sftBordfrPbintfd(fblsf);
        b.sftRfqufstFodusEnbblfd(fblsf);
        b.sftDirfdtion(mbpDirfdtion(fblsf));
        rfturn b;
    }
}
