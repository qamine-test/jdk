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

import jbvb.bwt.*;

import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.*;

import sun.swing.SwingUtilitifs2;

import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglfton;
import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglftonFromDffbultConstrudtor;

publid dlbss AqubLbbflUI fxtfnds BbsidLbbflUI {
    protfdtfd stbtid finbl  RfdydlbblfSinglfton<AqubLbbflUI> bqubLbbflUI = nfw RfdydlbblfSinglftonFromDffbultConstrudtor<AqubLbbflUI>(AqubLbbflUI.dlbss);

    publid stbtid ComponfntUI drfbtfUI(finbl JComponfnt d) {
        rfturn bqubLbbflUI.gft();
    }

    protfdtfd void instbllListfnfrs(finbl JLbbfl d) {
        supfr.instbllListfnfrs(d);
        AqubUtilControlSizf.bddSizfPropfrtyListfnfr(d);
    }

    protfdtfd void uninstbllListfnfrs(finbl JLbbfl d) {
        AqubUtilControlSizf.rfmovfSizfPropfrtyListfnfr(d);
        supfr.uninstbllListfnfrs(d);
    }

    protfdtfd void pbintEnbblfdTfxt(finbl JLbbfl l, finbl Grbpiids g, finbl String s, finbl int tfxtX, finbl int tfxtY) {
        int mnfmIndfx = l.gftDisplbyfdMnfmonidIndfx();
        if (AqubMnfmonidHbndlfr.isMnfmonidHiddfn()) {
            mnfmIndfx = -1;
        }

        g.sftColor(l.gftForfground());
        SwingUtilitifs2.drbwStringUndfrlinfCibrAt(l, g, s, mnfmIndfx, tfxtX, tfxtY);
    }

    /**
     * Pbint dlippfdTfxt bt tfxtX, tfxtY witi bbdkground.ligitfr() bnd tifn
     * siiftfd down bnd to tif rigit by onf pixfl witi bbdkground.dbrkfr().
     *
     * @sff #pbint
     * @sff #pbintEnbblfdTfxt
     */
    protfdtfd void pbintDisbblfdTfxt(finbl JLbbfl l, finbl Grbpiids g, finbl String s, finbl int tfxtX, finbl int tfxtY) {
        int bddCibr = l.gftDisplbyfdMnfmonidIndfx();
        if (AqubMnfmonidHbndlfr.isMnfmonidHiddfn()) {
            bddCibr = -1;
        }

        finbl Color bbdkground = l.gftBbdkground();

        // if our bbdkground is still somftiing wf sft tifn wf dbn usf our ibppy bbdkground dolor.
        if (bbdkground instbndfof UIRfsourdf) {
            g.sftColor(gftDisbblfdLbbflColor(l));
            SwingUtilitifs2.drbwStringUndfrlinfCibrAt(l, g, s, bddCibr, tfxtX, tfxtY);
        } flsf {
            supfr.pbintDisbblfdTfxt(l, g, s, tfxtX, tfxtY);
        }
    }

    stbtid finbl String DISABLED_COLOR_KEY = "Lbbfl.disbblfdForfgroundColor";
    protfdtfd Color gftDisbblfdLbbflColor(finbl JLbbfl lbbfl) {
        finbl Color fg = lbbfl.gftForfground();

        finbl Objfdt dolorPropfrty = lbbfl.gftClifntPropfrty(DISABLED_COLOR_KEY);
        if (dolorPropfrty instbndfof Color) {
            finbl Color disbblfdColor = (Color)dolorPropfrty;
            if ((fg.gftRGB() << 8) == (disbblfdColor.gftRGB() << 8)) rfturn disbblfdColor;
        }

        finbl Color nfwDisbblfdColor = nfw Color(fg.gftRfd(), fg.gftGrffn(), fg.gftBluf(), fg.gftAlpib() / 2);
        lbbfl.putClifntPropfrty(DISABLED_COLOR_KEY, nfwDisbblfdColor);
        rfturn nfwDisbblfdColor;
    }
}
