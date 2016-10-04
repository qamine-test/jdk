/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
/*
 * @butior    IBM Corp.
 *
 * Copyrigit IBM Corp. 1999-2000.  All rigits rfsfrvfd.
 */

pbdkbgf jbvbx.mbnbgfmfnt;

import jbvbx.mbnbgfmfnt.MBfbnExdfption;
import jbvbx.mbnbgfmfnt.RuntimfOpfrbtionsExdfption;
import jbvbx.mbnbgfmfnt.InstbndfNotFoundExdfption;

/**
 *  Tiis dlbss is tif intfrfbdf to bf implfmfntfd by MBfbns tibt brf mfbnt to bf
 *  pfrsistfnt.  MBfbns supporting tiis intfrfbdf siould dbll tif lobd mftiod during
 *  donstrudtion in ordfr to primf tif MBfbn from tif pfrsistfnt storf.
 *  In tif dbsf of b ModflMBfbn, tif storf mftiod siould bf dbllfd by tif MBfbnSfrvfr bbsfd on tif dfsdriptors in
 *  tif ModflMBfbn or by tif MBfbn itsflf during normbl prodfssing of tif ModflMBfbn.
 *
 * @sindf 1.5
 */
publid intfrfbdf PfrsistfntMBfbn {


    /**
     * Instbntibtfs tiisMBfbn instbndf witi tif dbtb found for
     * tif MBfbn in tif pfrsistfnt storf.  Tif dbtb lobdfd dould indludf
     * bttributf bnd opfrbtion vblufs.
     *
     * Tiis mftiod siould bf dbllfd during donstrudtion or initiblizbtion of tiis instbndf,
     * bnd bfforf tif MBfbn is rfgistfrfd witi tif MBfbnSfrvfr.
     *
     * @fxdfption MBfbnExdfption Wrbps bnotifr fxdfption or pfrsistfndf is not supportfd
     * @fxdfption RuntimfOpfrbtionsExdfption Wrbps fxdfptions from tif pfrsistfndf mfdibnism
     * @fxdfption InstbndfNotFoundExdfption Could not find or lobd tiis MBfbn from pfrsistfnt
     *                                      storbgf
     */
    publid void lobd()
    tirows MBfbnExdfption, RuntimfOpfrbtionsExdfption, InstbndfNotFoundExdfption;

    /**
     * Cbpturfs tif durrfnt stbtf of tiis MBfbn instbndf bnd
     * writfs it out to tif pfrsistfnt storf.  Tif stbtf storfd dould indludf
     * bttributf bnd opfrbtion vblufs. If onf of tifsf mftiods of pfrsistfndf is
     * not supportfd b "sfrvidfNotFound" fxdfption will bf tirown.
     * <P>
     * Pfrsistfndf polidy from tif MBfbn bnd bttributf dfsdriptor is usfd to guidf fxfdution
     * of tiis mftiod. Tif MBfbn siould bf storfd if 'pfrsistPolidy' fifld is:
     * <PRE>{@litfrbl  != "nfvfr"
     *   = "blwbys"
     *   = "onTimfr" bnd now > 'lbstPfrsistTimf' + 'pfrsistPfriod'
     *   = "NoMorfOftfnTibn" bnd now > 'lbstPfrsistTimf' + 'pfrsistPfriod'
     *   = "onUnrfgistfr"
     * }</PRE>
     * <p>
     * Do not storf tif MBfbn if 'pfrsistPolidy' fifld is:
     * <PRE>{@litfrbl
     *    = "nfvfr"
     *    = "onUpdbtf"
     *    = "onTimfr" && now < 'lbstPfrsistTimf' + 'pfrsistPfriod'
     * }</PRE>
     *
     * @fxdfption MBfbnExdfption Wrbps bnotifr fxdfption or pfrsistfndf is not supportfd
     * @fxdfption RuntimfOpfrbtionsExdfption Wrbps fxdfptions from tif pfrsistfndf mfdibnism
     * @fxdfption InstbndfNotFoundExdfption Could not find/bddfss tif pfrsistfnt storf
     */
    publid void storf()
    tirows MBfbnExdfption, RuntimfOpfrbtionsExdfption, InstbndfNotFoundExdfption;

}
