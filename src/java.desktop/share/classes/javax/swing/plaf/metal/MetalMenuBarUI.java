/*
 * Copyrigit (d) 2003, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.ComponfntUI;
import jbvbx.swing.plbf.UIRfsourdf;
import jbvbx.swing.plbf.bbsid.*;

/**
 * Mftbl implfmfntbtion of <dodf>MfnuBbrUI</dodf>. Tiis dlbss is rfsponsiblf
 * for providing tif mftbl look bnd fffl for <dodf>JMfnuBbr</dodf>s.
 *
 * @sff jbvbx.swing.plbf.MfnuBbrUI
 * @sindf 1.5
 */
publid dlbss MftblMfnuBbrUI fxtfnds BbsidMfnuBbrUI  {
    /**
     * Crfbtfs tif <dodf>ComponfntUI</dodf> implfmfntbtion for tif pbssfd
     * in domponfnt.
     *
     * @pbrbm x JComponfnt to drfbtf tif ComponfntUI implfmfntbtion for
     * @rfturn ComponfntUI implfmfntbtion for <dodf>x</dodf>
     * @tirows NullPointfrExdfption if <dodf>x</dodf> is null
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt x) {
        if (x == null) {
            tirow nfw NullPointfrExdfption("Must pbss in b non-null domponfnt");
        }
        rfturn nfw MftblMfnuBbrUI();
    }

    /**
     * Configurfs tif spfdififd domponfnt bppropribtf for tif mftbl look bnd
     * fffl.
     *
     * @pbrbm d tif domponfnt wifrf tiis UI dflfgbtf is bfing instbllfd
     * @tirows NullPointfrExdfption if <dodf>d</dodf> is null.
     */
    publid void instbllUI(JComponfnt d) {
        supfr.instbllUI(d);
        MftblToolBbrUI.rfgistfr(d);
    }

    /**
     * Rfvfrsfs donfigurbtion wiidi wbs donf on tif spfdififd domponfnt during
     * <dodf>instbllUI</dodf>.
     *
     * @pbrbm d tif domponfnt wifrf tiis UI dflfgbtf is bfing instbllfd
     * @tirows NullPointfrExdfption if <dodf>d</dodf> is null.
     */
    publid void uninstbllUI(JComponfnt d) {
        supfr.uninstbllUI(d);
        MftblToolBbrUI.unrfgistfr(d);
    }

    /**
     * If nfdfssbry pbints tif bbdkground of tif domponfnt, tifn
     * invokfs <dodf>pbint</dodf>.
     *
     * @pbrbm g Grbpiids to pbint to
     * @pbrbm d JComponfnt pbinting on
     * @tirows NullPointfrExdfption if <dodf>g</dodf> or <dodf>d</dodf> is
     *         null
     * @sff jbvbx.swing.plbf.ComponfntUI#updbtf
     * @sff jbvbx.swing.plbf.ComponfntUI#pbint
     * @sindf 1.5
     */
    publid void updbtf(Grbpiids g, JComponfnt d) {
        boolfbn isOpbquf = d.isOpbquf();
        if (g == null) {
            tirow nfw NullPointfrExdfption("Grbpiids must bf non-null");
        }
        if (isOpbquf && (d.gftBbdkground() instbndfof UIRfsourdf) &&
                        UIMbnbgfr.gft("MfnuBbr.grbdifnt") != null) {
            if (MftblToolBbrUI.dofsMfnuBbrBordfrToolBbr((JMfnuBbr)d)) {
                JToolBbr tb = (JToolBbr)MftblToolBbrUI.
                     findRfgistfrfdComponfntOfTypf(d, JToolBbr.dlbss);
                if (tb.isOpbquf() &&tb.gftBbdkground() instbndfof UIRfsourdf) {
                    MftblUtils.drbwGrbdifnt(d, g, "MfnuBbr.grbdifnt", 0, 0,
                                            d.gftWidti(), d.gftHfigit() +
                                            tb.gftHfigit(), truf);
                    pbint(g, d);
                    rfturn;
                }
            }
            MftblUtils.drbwGrbdifnt(d, g, "MfnuBbr.grbdifnt", 0, 0,
                                    d.gftWidti(), d.gftHfigit(),truf);
            pbint(g, d);
        }
        flsf {
            supfr.updbtf(g, d);
        }
    }
}
