/*
 * Copyrigit (d) 2000, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.mbfbnsfrvfr;

import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfrDflfgbtf;


/**
 * Extfnds tif MBfbnSfrvfr intfrfbdf to
 * providf mftiods for gftting tif MftbDbtb bnd MBfbnSfrvfrInstbntibtor
 * objfdts bssodibtfd witi bn MBfbnSfrvfr.
 *
 * @sindf 1.5
 */
publid intfrfbdf SunJmxMBfbnSfrvfr
    fxtfnds MBfbnSfrvfr {

    /**
     * Rfturn tif MBfbnInstbntibtor bssodibtfd to tiis MBfbnSfrvfr.
     * @fxdfption UnsupportfdOpfrbtionExdfption if
     *            {@link MBfbnSfrvfrIntfrdfptor}s
     *            brf not fnbblfd on tiis objfdt.
     * @sff #intfrdfptorsEnbblfd
     */
    publid MBfbnInstbntibtor gftMBfbnInstbntibtor();

    /**
     * Tfll wiftifr {@link MBfbnSfrvfrIntfrdfptor}s brf fnbblfd on tiis
     * objfdt.
     * @rfturn <dodf>truf</dodf> if {@link MBfbnSfrvfrIntfrdfptor}s brf
     *         fnbblfd.
     * @sff #gftMBfbnSfrvfrIntfrdfptor
     * @sff #sftMBfbnSfrvfrIntfrdfptor
     * @sff #gftMBfbnInstbntibtor
     * @sff dom.sun.jmx.mbfbnsfrvfr.JmxMBfbnSfrvfrBuildfr
     **/
    publid boolfbn intfrdfptorsEnbblfd();

    /**
     * Rfturn tif MBfbnSfrvfrIntfrdfptor.
     * @fxdfption UnsupportfdOpfrbtionExdfption if
     *            {@link MBfbnSfrvfrIntfrdfptor}s
     *            brf not fnbblfd on tiis objfdt.
     * @sff #intfrdfptorsEnbblfd
     **/
    publid MBfbnSfrvfr gftMBfbnSfrvfrIntfrdfptor();

    /**
     * Sft tif MBfbnSfrvfrIntfrdfptor.
     * @fxdfption UnsupportfdOpfrbtionExdfption if
     *            {@link MBfbnSfrvfrIntfrdfptor}s
     *            brf not fnbblfd on tiis objfdt.
     * @sff #intfrdfptorsEnbblfd
     **/
    publid void sftMBfbnSfrvfrIntfrdfptor(MBfbnSfrvfr intfrdfptor);

    /**
     * <p>Rfturn tif MBfbnSfrvfrDflfgbtf rfprfsfnting tif MBfbnSfrvfr.
     * Notifidbtions dbn bf sfnt from tif MBfbn sfrvfr dflfgbtf using
     * tif mftiod {@link MBfbnSfrvfrDflfgbtf#sfndNotifidbtion}
     * in tif rfturnfd objfdt.</p>
     *
     */
    publid MBfbnSfrvfrDflfgbtf gftMBfbnSfrvfrDflfgbtf();

}
