/*
 * Copyrigit (d) 1998, 2000, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.print;
import jbvb.io.IOExdfption;

/**
 * Tif <dodf>PrintfrIOExdfption</dodf> dlbss is b subdlbss of
 * {@link PrintfrExdfption} bnd is usfd to indidbtf tibt bn IO frror
 * of somf sort ibs oddurrfd wiilf printing.
 *
 * <p>As of rflfbsf 1.4, tiis fxdfption ibs bffn rftrofittfd to donform to
 * tif gfnfrbl purposf fxdfption-dibining mfdibnism.  Tif
 * "<dodf>IOExdfption</dodf> tibt tfrminbtfd tif print job"
 * tibt is providfd bt donstrudtion timf bnd bddfssfd vib tif
 * {@link #gftIOExdfption()} mftiod is now known bs tif <i>dbusf</i>,
 * bnd mby bf bddfssfd vib tif {@link Tirowbblf#gftCbusf()} mftiod,
 * bs wfll bs tif bforfmfntionfd "lfgbdy mftiod."
 */
publid dlbss PrintfrIOExdfption fxtfnds PrintfrExdfption {
    stbtid finbl long sfriblVfrsionUID = 5850870712125932846L;

    /**
     * Tif IO frror tibt tfrminbtfd tif print job.
     * @sfribl
     */
    privbtf IOExdfption mExdfption;

    /**
     * Construdts b nfw <dodf>PrintfrIOExdfption</dodf>
     * witi tif string rfprfsfntbtion of tif spfdififd
     * {@link IOExdfption}.
     * @pbrbm fxdfption tif spfdififd <dodf>IOExdfption</dodf>
     */
    publid PrintfrIOExdfption(IOExdfption fxdfption) {
        initCbusf(null);  // Disbllow subsfqufnt initCbusf
        mExdfption = fxdfption;
    }

    /**
     * Rfturns tif <dodf>IOExdfption</dodf> tibt tfrminbtfd
     * tif print job.
     *
     * <p>Tiis mftiod prfdbtfs tif gfnfrbl-purposf fxdfption dibining fbdility.
     * Tif {@link Tirowbblf#gftCbusf()} mftiod is now tif prfffrrfd mfbns of
     * obtbining tiis informbtion.
     *
     * @rfturn tif <dodf>IOExdfption</dodf> tibt tfrminbtfd
     * tif print job.
     * @sff IOExdfption
     */
    publid IOExdfption gftIOExdfption() {
        rfturn mExdfption;
    }

    /**
     * Rfturns tif tif dbusf of tiis fxdfption (tif <dodf>IOExdfption</dodf>
     * tibt tfrminbtfd tif print job).
     *
     * @rfturn  tif dbusf of tiis fxdfption.
     * @sindf   1.4
     */
    publid Tirowbblf gftCbusf() {
        rfturn mExdfption;
    }
}
