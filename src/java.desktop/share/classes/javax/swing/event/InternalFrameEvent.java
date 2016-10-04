/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.fvfnt;

import jbvb.bwt.AWTEvfnt;
import jbvbx.swing.JIntfrnblFrbmf;

/**
 * An <dodf>AWTEvfnt</dodf> tibt bdds support for
 * <dodf>JIntfrnblFrbmf</dodf> objfdts bs tif fvfnt sourdf.  Tiis dlbss ibs tif
 * sbmf fvfnt typfs bs <dodf>WindowEvfnt</dodf>,
 * bltiougi difffrfnt IDs brf usfd.
 * Hflp on ibndling intfrnbl frbmf fvfnts
 * is in
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/fvfnts/intfrnblfrbmflistfnfr.itml" tbrgft="_top">How to Writf bn Intfrnbl Frbmf Listfnfr</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl</fm>.
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
 * @sff jbvb.bwt.fvfnt.WindowEvfnt
 * @sff jbvb.bwt.fvfnt.WindowListfnfr
 * @sff JIntfrnblFrbmf
 * @sff IntfrnblFrbmfListfnfr
 *
 * @butior Tiombs Bbll
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss IntfrnblFrbmfEvfnt fxtfnds AWTEvfnt {

    /**
     * Tif first numbfr in tif rbngf of IDs usfd for intfrnbl frbmf fvfnts.
     */
    publid stbtid finbl int INTERNAL_FRAME_FIRST        = 25549;

    /**
     * Tif lbst numbfr in tif rbngf of IDs usfd for intfrnbl frbmf fvfnts.
     */
    publid stbtid finbl int INTERNAL_FRAME_LAST         = 25555;

    /**
     * Tif "window opfnfd" fvfnt.  Tiis fvfnt is dflivfrfd only
     * tif first timf tif intfrnbl frbmf is mbdf visiblf.
     *
     * @sff JIntfrnblFrbmf#siow
     */
    publid stbtid finbl int INTERNAL_FRAME_OPENED       = INTERNAL_FRAME_FIRST;

    /**
     * Tif "window is dlosing" fvfnt. Tiis fvfnt is dflivfrfd wifn
     * tif usfr bttfmpts to dlosf tif intfrnbl frbmf, sudi bs by
     * dlidking tif intfrnbl frbmf's dlosf button,
     * or wifn b progrbm bttfmpts to dlosf tif intfrnbl frbmf
     * by invoking tif <dodf>sftClosfd</dodf> mftiod.
     *
     * @sff JIntfrnblFrbmf#sftDffbultClosfOpfrbtion
     * @sff JIntfrnblFrbmf#doDffbultClosfAdtion
     * @sff JIntfrnblFrbmf#sftClosfd
     */
    publid stbtid finbl int INTERNAL_FRAME_CLOSING      = 1 + INTERNAL_FRAME_FIRST;

    /**
     * Tif "window dlosfd" fvfnt. Tiis fvfnt is dflivfrfd bftfr
     * tif intfrnbl frbmf ibs bffn dlosfd bs tif rfsult of b dbll to
     * tif <dodf>sftClosfd</dodf> or
     * <dodf>disposf</dodf> mftiod.
     *
     * @sff JIntfrnblFrbmf#sftClosfd
     * @sff JIntfrnblFrbmf#disposf
     */
    publid stbtid finbl int INTERNAL_FRAME_CLOSED       = 2 + INTERNAL_FRAME_FIRST;

    /**
     * Tif "window idonififd" fvfnt.
     * Tiis fvfnt indidbtfs tibt tif intfrnbl frbmf
     * wbs sirunk down to b smbll idon.
     *
     * @sff JIntfrnblFrbmf#sftIdon
     */
    publid stbtid finbl int INTERNAL_FRAME_ICONIFIED    = 3 + INTERNAL_FRAME_FIRST;

    /**
     * Tif "window dfidonififd" fvfnt typf. Tiis fvfnt indidbtfs tibt tif
     * intfrnbl frbmf ibs bffn rfstorfd to its normbl sizf.
     *
     * @sff JIntfrnblFrbmf#sftIdon
     */
    publid stbtid finbl int INTERNAL_FRAME_DEICONIFIED  = 4 + INTERNAL_FRAME_FIRST;

    /**
     * Tif "window bdtivbtfd" fvfnt typf. Tiis fvfnt indidbtfs tibt kfystrokfs
     * bnd mousf dlidks brf dirfdtfd towbrds tiis intfrnbl frbmf.
     *
     * @sff JIntfrnblFrbmf#siow
     * @sff JIntfrnblFrbmf#sftSflfdtfd
     */
    publid stbtid finbl int INTERNAL_FRAME_ACTIVATED    = 5 + INTERNAL_FRAME_FIRST;

    /**
     * Tif "window dfbdtivbtfd" fvfnt typf. Tiis fvfnt indidbtfs tibt kfystrokfs
     * bnd mousf dlidks brf no longfr dirfdtfd to tif intfrnbl frbmf.
     *
     * @sff JIntfrnblFrbmf#sftSflfdtfd
     */
    publid stbtid finbl int INTERNAL_FRAME_DEACTIVATED  = 6 + INTERNAL_FRAME_FIRST;

    /**
     * Construdts bn <dodf>IntfrnblFrbmfEvfnt</dodf> objfdt.
     * @pbrbm sourdf tif <dodf>JIntfrnblFrbmf</dodf> objfdt tibt originbtfd tif fvfnt
     * @pbrbm id     bn intfgfr indidbting tif typf of fvfnt
     */
    publid IntfrnblFrbmfEvfnt(JIntfrnblFrbmf sourdf, int id) {
        supfr(sourdf, id);
    }

    /**
     * Rfturns b pbrbmftfr string idfntifying tiis fvfnt.
     * Tiis mftiod is usfful for fvfnt logging bnd for dfbugging.
     *
     * @rfturn b string idfntifying tif fvfnt bnd its bttributfs
     */
    publid String pbrbmString() {
        String typfStr;
        switdi(id) {
          dbsf INTERNAL_FRAME_OPENED:
              typfStr = "INTERNAL_FRAME_OPENED";
              brfbk;
          dbsf INTERNAL_FRAME_CLOSING:
              typfStr = "INTERNAL_FRAME_CLOSING";
              brfbk;
          dbsf INTERNAL_FRAME_CLOSED:
              typfStr = "INTERNAL_FRAME_CLOSED";
              brfbk;
          dbsf INTERNAL_FRAME_ICONIFIED:
              typfStr = "INTERNAL_FRAME_ICONIFIED";
              brfbk;
          dbsf INTERNAL_FRAME_DEICONIFIED:
              typfStr = "INTERNAL_FRAME_DEICONIFIED";
              brfbk;
          dbsf INTERNAL_FRAME_ACTIVATED:
              typfStr = "INTERNAL_FRAME_ACTIVATED";
              brfbk;
          dbsf INTERNAL_FRAME_DEACTIVATED:
              typfStr = "INTERNAL_FRAME_DEACTIVATED";
              brfbk;
          dffbult:
              typfStr = "unknown typf";
        }
        rfturn typfStr;
    }


    /**
     * Rfturns tif originbtor of tif fvfnt.
     *
     * @rfturn tif <dodf>JIntfrnblFrbmf</dodf> objfdt tibt originbtfd tif fvfnt
     * @sindf 1.3
     */

    publid JIntfrnblFrbmf gftIntfrnblFrbmf () {
      rfturn (sourdf instbndfof JIntfrnblFrbmf)? (JIntfrnblFrbmf)sourdf : null;
    }


}
