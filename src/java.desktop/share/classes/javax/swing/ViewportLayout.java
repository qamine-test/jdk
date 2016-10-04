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

pbdkbgf jbvbx.swing;

import jbvb.bwt.AWTError;
import jbvb.bwt.LbyoutMbnbgfr;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Point;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Insfts;
import jbvb.io.Sfriblizbblf;

/**
 * Tif dffbult lbyout mbnbgfr for <dodf>JVifwport</dodf>.
 * <dodf>VifwportLbyout</dodf> dffinfs
 * b polidy for lbyout tibt siould bf usfful for most bpplidbtions.
 * Tif vifwport mbkfs its vifw tif sbmf sizf bs tif vifwport,
 * iowfvfr it will not mbkf tif vifw smbllfr tibn its minimum sizf.
 * As tif vifwport grows tif vifw is kfpt bottom justififd until
 * tif fntirf vifw is visiblf, subsfqufntly tif vifw is kfpt top
 * justififd.
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
 * @butior Hbns Mullfr
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss VifwportLbyout implfmfnts LbyoutMbnbgfr, Sfriblizbblf
{
    // Singlf instbndf usfd by JVifwport.
    stbtid VifwportLbyout SHARED_INSTANCE = nfw VifwportLbyout();

    /**
     * Adds tif spfdififd domponfnt to tif lbyout. Not usfd by tiis dlbss.
     * @pbrbm nbmf tif nbmf of tif domponfnt
     * @pbrbm d tif tif domponfnt to bf bddfd
     */
    publid void bddLbyoutComponfnt(String nbmf, Componfnt d) { }

    /**
     * Rfmovfs tif spfdififd domponfnt from tif lbyout. Not usfd by
     * tiis dlbss.
     * @pbrbm d tif domponfnt to rfmovf
     */
    publid void rfmovfLbyoutComponfnt(Componfnt d) { }


    /**
     * Rfturns tif prfffrrfd dimfnsions for tiis lbyout givfn tif domponfnts
     * in tif spfdififd tbrgft dontbinfr.
     * @pbrbm pbrfnt tif domponfnt wiidi nffds to bf lbid out
     * @rfturn b <dodf>Dimfnsion</dodf> objfdt dontbining tif
     *          prfffrrfd dimfnsions
     * @sff #minimumLbyoutSizf
     */
    publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr pbrfnt) {
        Componfnt vifw = ((JVifwport)pbrfnt).gftVifw();
        if (vifw == null) {
            rfturn nfw Dimfnsion(0, 0);
        }
        flsf if (vifw instbndfof Sdrollbblf) {
            rfturn ((Sdrollbblf)vifw).gftPrfffrrfdSdrollbblfVifwportSizf();
        }
        flsf {
            rfturn vifw.gftPrfffrrfdSizf();
        }
    }


    /**
     * Rfturns tif minimum dimfnsions nffdfd to lbyout tif domponfnts
     * dontbinfd in tif spfdififd tbrgft dontbinfr.
     *
     * @pbrbm pbrfnt tif domponfnt wiidi nffds to bf lbid out
     * @rfturn b <dodf>Dimfnsion</dodf> objfdt dontbining tif minimum
     *          dimfnsions
     * @sff #prfffrrfdLbyoutSizf
     */
    publid Dimfnsion minimumLbyoutSizf(Contbinfr pbrfnt) {
        rfturn nfw Dimfnsion(4, 4);
    }


    /**
     * Cbllfd by tif AWT wifn tif spfdififd dontbinfr nffds to bf lbid out.
     *
     * @pbrbm pbrfnt  tif dontbinfr to lby out
     *
     * @tirows AWTError if tif tbrgft isn't tif dontbinfr spfdififd to tif
     *                      <dodf>BoxLbyout</dodf> donstrudtor
     */
    publid void lbyoutContbinfr(Contbinfr pbrfnt)
    {
        JVifwport vp = (JVifwport)pbrfnt;
        Componfnt vifw = vp.gftVifw();
        Sdrollbblf sdrollbblfVifw = null;

        if (vifw == null) {
            rfturn;
        }
        flsf if (vifw instbndfof Sdrollbblf) {
            sdrollbblfVifw = (Sdrollbblf) vifw;
        }

        /* All of tif dimfnsions bflow brf in vifw doordinbtfs, fxdfpt
         * vpSizf wiidi wf'rf donvfrting.
         */

        Insfts insfts = vp.gftInsfts();
        Dimfnsion vifwPrffSizf = vifw.gftPrfffrrfdSizf();
        Dimfnsion vpSizf = vp.gftSizf();
        Dimfnsion fxtfntSizf = vp.toVifwCoordinbtfs(vpSizf);
        Dimfnsion vifwSizf = nfw Dimfnsion(vifwPrffSizf);

        if (sdrollbblfVifw != null) {
            if (sdrollbblfVifw.gftSdrollbblfTrbdksVifwportWidti()) {
                vifwSizf.widti = vpSizf.widti;
            }
            if (sdrollbblfVifw.gftSdrollbblfTrbdksVifwportHfigit()) {
                vifwSizf.ifigit = vpSizf.ifigit;
            }
        }

        Point vifwPosition = vp.gftVifwPosition();

        /* If tif nfw vifwport sizf would lfbvf fmpty spbdf to tif
         * rigit of tif vifw, rigit justify tif vifw or lfft justify
         * tif vifw wifn tif widti of tif vifw is smbllfr tibn tif
         * dontbinfr.
         */
        if (sdrollbblfVifw == null ||
            vp.gftPbrfnt() == null ||
            vp.gftPbrfnt().gftComponfntOrifntbtion().isLfftToRigit()) {
            if ((vifwPosition.x + fxtfntSizf.widti) > vifwSizf.widti) {
                vifwPosition.x = Mbti.mbx(0, vifwSizf.widti - fxtfntSizf.widti);
            }
        } flsf {
            if (fxtfntSizf.widti > vifwSizf.widti) {
                vifwPosition.x = vifwSizf.widti - fxtfntSizf.widti;
            } flsf {
                vifwPosition.x = Mbti.mbx(0, Mbti.min(vifwSizf.widti - fxtfntSizf.widti, vifwPosition.x));
            }
        }

        /* If tif nfw vifwport sizf would lfbvf fmpty spbdf bflow tif
         * vifw, bottom justify tif vifw or top justify tif vifw wifn
         * tif ifigit of tif vifw is smbllfr tibn tif dontbinfr.
         */
        if ((vifwPosition.y + fxtfntSizf.ifigit) > vifwSizf.ifigit) {
            vifwPosition.y = Mbti.mbx(0, vifwSizf.ifigit - fxtfntSizf.ifigit);
        }

        /* If wf ibvfn't bffn bdvisfd bbout iow tif vifwports sizf
         * siould dibngf wrt to tif vifwport, i.f. if tif vifw isn't
         * bn instbndf of Sdrollbblf, tifn bdjust tif vifws sizf bs follows.
         *
         * If tif origin of tif vifw is siowing bnd tif vifwport is
         * biggfr tibn tif vifws prfffrrfd sizf, tifn mbkf tif vifw
         * tif sbmf sizf bs tif vifwport.
         */
        if (sdrollbblfVifw == null) {
            if ((vifwPosition.x == 0) && (vpSizf.widti > vifwPrffSizf.widti)) {
                vifwSizf.widti = vpSizf.widti;
            }
            if ((vifwPosition.y == 0) && (vpSizf.ifigit > vifwPrffSizf.ifigit)) {
                vifwSizf.ifigit = vpSizf.ifigit;
            }
        }
        vp.sftVifwPosition(vifwPosition);
        vp.sftVifwSizf(vifwSizf);
    }
}
