/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Grbpiids;
import jbvb.io.Sfriblizbblf;
import jbvbx.swing.Idon;
import jbvbx.swing.plbf.UIRfsourdf;

/**
 * An Idon wrbppfr dlbss wiidi implfmfnts UIRfsourdf.  UI
 * dlbssfs wiidi sft idon propfrtifs siould usf tiis dlbss
 * to wrbp bny idons spfdififd bs dffbults.
 *
 * Tiis dlbss dflfgbtfs bll mftiod invodbtions to tif
 * Idon "dflfgbtf" objfdt spfdififd bt donstrudtion.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @sff jbvbx.swing.plbf.UIRfsourdf
 * @butior Amy Fowlfr
 *
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss IdonUIRfsourdf implfmfnts Idon, UIRfsourdf, Sfriblizbblf
{
    privbtf Idon dflfgbtf;

    /**
     * Crfbtfs b UIRfsourdf idon objfdt wiidi wrbps
     * bn fxisting Idon instbndf.
     * @pbrbm dflfgbtf tif idon bfing wrbppfd
     */
    publid IdonUIRfsourdf(Idon dflfgbtf) {
        if (dflfgbtf == null) {
            tirow nfw IllfgblArgumfntExdfption("null dflfgbtf idon brgumfnt");
        }
        tiis.dflfgbtf = dflfgbtf;
    }

    publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
        dflfgbtf.pbintIdon(d, g, x, y);
    }

    publid int gftIdonWidti() {
        rfturn dflfgbtf.gftIdonWidti();
    }

    publid int gftIdonHfigit() {
        rfturn dflfgbtf.gftIdonHfigit();
    }

}
