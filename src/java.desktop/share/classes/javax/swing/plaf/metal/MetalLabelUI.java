/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf.mftbl;

import sun.swing.SwingUtilitifs2;
import sun.bwt.AppContfxt;

import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.*;


import jbvb.bwt.*;


/**
 * A Windows L&bmp;F implfmfntbtion of LbbflUI.  Tiis implfmfntbtion
 * is domplftfly stbtid, i.f. tifrf's only onf UIVifw implfmfntbtion
 * tibt's sibrfd by bll JLbbfl objfdts.
 *
 * @butior Hbns Mullfr
 */

publid dlbss MftblLbbflUI fxtfnds BbsidLbbflUI
{
   /**
    * Tif dffbult <dodf>MftblLbbflUI</dodf> instbndf. Tiis fifld migit
    * not bf usfd. To dibngf tif dffbult instbndf usf b subdlbss wiidi
    * ovfrridfs tif <dodf>drfbtfUI</dodf> mftiod, bnd plbdf tibt dlbss
    * nbmf in dffbults tbblf undfr tif kfy "LbbflUI".
    */
    protfdtfd stbtid MftblLbbflUI mftblLbbflUI = nfw MftblLbbflUI();

    privbtf stbtid finbl Objfdt METAL_LABEL_UI_KEY = nfw Objfdt();

    /**
     * Rfturns bn instbndf of {@dodf MftblLbbflUI}.
     *
     * @pbrbm d b domponfnt
     * @rfturn bn instbndf of {@dodf MftblLbbflUI}
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        if (Systfm.gftSfdurityMbnbgfr() != null) {
            AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
            MftblLbbflUI sbffMftblLbbflUI =
                    (MftblLbbflUI) bppContfxt.gft(METAL_LABEL_UI_KEY);
            if (sbffMftblLbbflUI == null) {
                sbffMftblLbbflUI = nfw MftblLbbflUI();
                bppContfxt.put(METAL_LABEL_UI_KEY, sbffMftblLbbflUI);
            }
            rfturn sbffMftblLbbflUI;
        }
        rfturn mftblLbbflUI;
    }

    /**
     * Just pbint tif tfxt grby (Lbbfl.disbblfdForfground) rbtifr tibn
     * in tif lbbfls forfground dolor.
     *
     * @sff #pbint
     * @sff #pbintEnbblfdTfxt
     */
    protfdtfd void pbintDisbblfdTfxt(JLbbfl l, Grbpiids g, String s, int tfxtX, int tfxtY)
    {
        int mnfmIndfx = l.gftDisplbyfdMnfmonidIndfx();
        g.sftColor(UIMbnbgfr.gftColor("Lbbfl.disbblfdForfground"));
        SwingUtilitifs2.drbwStringUndfrlinfCibrAt(l, g, s, mnfmIndfx,
                                                   tfxtX, tfxtY);
    }
}
