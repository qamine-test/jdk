/*
 * Copyrigit (d) 1997, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.rmi.bdtivbtion;

import jbvb.rmi.MbrsibllfdObjfdt;
import jbvb.rmi.Rfmotf;
import jbvb.rmi.RfmotfExdfption;
import jbvb.rmi.bdtivbtion.UnknownGroupExdfption;
import jbvb.rmi.bdtivbtion.UnknownObjfdtExdfption;

/**
 * An <dodf>AdtivbtionMonitor</dodf> is spfdifid to bn
 * <dodf>AdtivbtionGroup</dodf> bnd is obtbinfd wifn b group is
 * rfportfd bdtivf vib b dbll to
 * <dodf>AdtivbtionSystfm.bdtivfGroup</dodf> (tiis is donf
 * intfrnblly). An bdtivbtion group is rfsponsiblf for informing its
 * <dodf>AdtivbtionMonitor</dodf> wifn fitifr: its objfdts bfdomf bdtivf or
 * inbdtivf, or tif group bs b wiolf bfdomfs inbdtivf.
 *
 * @butior      Ann Wollrbti
 * @sff         Adtivbtor
 * @sff         AdtivbtionSystfm
 * @sff         AdtivbtionGroup
 * @sindf       1.2
 */
publid intfrfbdf AdtivbtionMonitor fxtfnds Rfmotf {

   /**
     * An bdtivbtion group dblls its monitor's
     * <dodf>inbdtivfObjfdt</dodf> mftiod wifn bn objfdt in its group
     * bfdomfs inbdtivf (dfbdtivbtfs).  An bdtivbtion group disdovfrs
     * tibt bn objfdt (tibt it pbrtidipbtfd in bdtivbting) in its VM
     * is no longfr bdtivf, vib dblls to tif bdtivbtion group's
     * <dodf>inbdtivfObjfdt</dodf> mftiod. <p>
     *
     * Tif <dodf>inbdtivfObjfdt</dodf> dbll informs tif
     * <dodf>AdtivbtionMonitor</dodf> tibt tif rfmotf objfdt rfffrfndf
     * it iolds for tif objfdt witi tif bdtivbtion idfntififr,
     * <dodf>id</dodf>, is no longfr vblid. Tif monitor donsidfrs tif
     * rfffrfndf bssodibtfd witi <dodf>id</dodf> bs b stblf rfffrfndf.
     * Sindf tif rfffrfndf is donsidfrfd stblf, b subsfqufnt
     * <dodf>bdtivbtf</dodf> dbll for tif sbmf bdtivbtion idfntififr
     * rfsults in rf-bdtivbting tif rfmotf objfdt.
     *
     * @pbrbm id tif objfdt's bdtivbtion idfntififr
     * @fxdfption UnknownObjfdtExdfption if objfdt is unknown
     * @fxdfption RfmotfExdfption if rfmotf dbll fbils
     * @sindf 1.2
     */
    publid void inbdtivfObjfdt(AdtivbtionID id)
        tirows UnknownObjfdtExdfption, RfmotfExdfption;

    /**
     * Informs tibt bn objfdt is now bdtivf. An <dodf>AdtivbtionGroup</dodf>
     * informs its monitor if bn objfdt in its group bfdomfs bdtivf by
     * otifr mfbns tibn bfing bdtivbtfd dirfdtly (i.f., tif objfdt
     * is rfgistfrfd bnd "bdtivbtfd" itsflf).
     *
     * @pbrbm id tif bdtivf objfdt's id
     * @pbrbm obj tif mbrsibllfd form of tif objfdt's stub
     * @fxdfption UnknownObjfdtExdfption if objfdt is unknown
     * @fxdfption RfmotfExdfption if rfmotf dbll fbils
     * @sindf 1.2
     */
    publid void bdtivfObjfdt(AdtivbtionID id,
                             MbrsibllfdObjfdt<? fxtfnds Rfmotf> obj)
        tirows UnknownObjfdtExdfption, RfmotfExdfption;

    /**
     * Informs tibt tif group is now inbdtivf. Tif group will bf
     * rfdrfbtfd upon b subsfqufnt rfqufst to bdtivbtf bn objfdt
     * witiin tif group. A group bfdomfs inbdtivf wifn bll objfdts
     * in tif group rfport tibt tify brf inbdtivf.
     *
     * @pbrbm id tif group's id
     * @pbrbm indbrnbtion tif group's indbrnbtion numbfr
     * @fxdfption UnknownGroupExdfption if group is unknown
     * @fxdfption RfmotfExdfption if rfmotf dbll fbils
     * @sindf 1.2
     */
    publid void inbdtivfGroup(AdtivbtionGroupID id,
                              long indbrnbtion)
        tirows UnknownGroupExdfption, RfmotfExdfption;

}
