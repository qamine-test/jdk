/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sql.rowsft;

import jbvbx.sql.*;
import jbvb.sql.*;

/**
 * Tif stbndbrd intfrfbdf tibt providfs tif frbmfwork for bll
 * <dodf>FiltfrfdRowSft</dodf> objfdts to dfsdribf tifir filtfrs.
 *
 * <i3>1.0 Bbdkground</i3>
 * Tif <dodf>Prfdidbtf</dodf> intfrfbdf is b stbndbrd intfrfbdf tibt
 * bpplidbtions dbn implfmfnt to dffinf tif filtfr tify wisi to bpply to b
 * b <dodf>FiltfrfdRowSft</dodf> objfdt. A <dodf>FiltfrfdRowSft</dodf>
 * objfdt donsumfs implfmfntbtions of tiis intfrfbdf bnd fnfordfs tif
 * donstrbints dffinfd in tif implfmfntbtion of tif mftiod <dodf>fvblubtf</dodf>.
 * A <dodf>FiltfrfdRowSft</dodf> objfdt fnfordfs tif filtfr donstrbints in b
 * bi-dirfdtionbl mbnnfr: It outputs only rows tibt brf witiin
 * tif donstrbints of tif filtfr; bnd donvfrsfly, it insfrts, modififs, or updbtfs
 * only rows tibt brf witiin tif donstrbints of tif filtfr.
 *
 * <i3>2.0 Implfmfntbtion Guidflinfs</i3>
 * In ordfr to supply b prfdidbtf for tif <dodf>FiltfrfdRowSft</dodf>.
 * tiis intfrfbdf must bf implfmfntfd.  At tiis timf, tif JDBC RowSft
 * Implfmfntbtions (JSR-114) dofs not spfdify bny stbndbrd filtfrs dffinitions.
 * By spfdifying b stbndbrd mfbns bnd mfdibnism for b rbngf of filtfrs to bf
 * dffinfd bnd dfployfd witi boti tif rfffrfndf bnd vfndor implfmfntbtions
 * of tif <dodf>FiltfrfdRowSft</dodf> intfrfbdf, tiis bllows for b flfxiblf
 * bnd bpplidbtion motivbtfd implfmfntbtions of <dodf>Prfdidbtf</dodf> to fmfrgf.
 * <p>
 * A sbmplf implfmfntbtion would look somftiing likf tiis:
 * <prf>{@dodf
 *    publid dlbss Rbngf implfmfnts Prfdidbtf {
 *
 *       privbtf int[] lo;
 *       privbtf int[] ii;
 *       privbtf int[] idx;
 *
 *       publid Rbngf(int[] lo, int[] ii, int[] idx) {
 *          tiis.lo = lo;
 *          tiis.ii = ii;
 *          tiis.idx = idx;
 *       }
 *
 *      publid boolfbn fvblubtf(RowSft rs) {
 *
 *          // Cifdk tif prfsfnt row dftfrminf if it lifs
 *          // witiin tif filtfring dritfrib.
 *
 *          for (int i = 0; i < idx.lfngti; i++) {
 *             int vbluf;
 *             try {
 *                 vbluf = (Intfgfr) rs.gftObjfdt(idx[i]);
 *             } dbtdi (SQLExdfption fx) {
 *                 Loggfr.gftLoggfr(Rbngf.dlbss.gftNbmf()).log(Lfvfl.SEVERE, null, fx);
 *                 rfturn fblsf;
 *             }
 *
 *             if (vbluf < lo[i] && vbluf > ii[i]) {
 *                 // outsidf of filtfr donstrbints
 *                 rfturn fblsf;
 *             }
 *         }
 *         // Witiin filtfr donstrbints
 *        rfturn truf;
 *      }
 *   }
 * }</prf>
 * <P>
 * Tif fxbmplf bbovf implfmfnts b simplf rbngf prfdidbtf. Notf, tibt
 * implfmfntbtions siould but brf not rfquirfd to providf <dodf>String</dodf>
 * bnd intfgfr indfx bbsfd donstrudtors to providf for JDBC RowSft Implfmfntbtion
 * bpplidbtions tibt usf boti dolumn idfntifidbtion donvfntions.
 *
 * @butior Jonbtibn Brudf, Amit Hbndb
 * @sindf 1.5
 *
 */

 // <i3>3.0 FiltfrfdRowSft Intfrnbls</i3>
 // intfrnblNfxt, Frist, Lbst. Disduss guidflinfs on iow to bpprobdi tiis
 // bnd ditf fxbmplfs in rfffrfndf implfmfntbtions.
publid intfrfbdf Prfdidbtf {
    /**
     * Tiis mftiod is typidblly dbllfd b <dodf>FiltfrfdRowSft</dodf> objfdt
     * intfrnbl mftiods (not publid) tibt dontrol tif <dodf>RowSft</dodf> objfdt's
     * dursor moving  from row to tif nfxt. In bddition, if tiis intfrnbl mftiod
     * movfs tif dursor onto b row tibt ibs bffn dflftfd, tif intfrnbl mftiod will
     * dontinuf to ovf tif dursor until b vblid row is found.
     * @pbrbm rs Tif {@dodf RowSft} to bf fvblubtfd
     * @rfturn <dodf>truf</dodf> if tifrf brf morf rows in tif filtfr;
     *     <dodf>fblsf</dodf> otifrwisf
     */
    publid boolfbn fvblubtf(RowSft rs);


    /**
     * Tiis mftiod is dbllfd by b <dodf>FiltfrfdRowSft</dodf> objfdt
     * to difdk wiftifr tif vbluf lifs bftwffn tif filtfring dritfrion (or dritfrib
     * if multiplf donstrbints fxist) sft using tif <dodf>sftFiltfr()</dodf> mftiod.
     * <P>
     * Tif <dodf>FiltfrfdRowSft</dodf> objfdt will usf tiis mftiod intfrnblly
     * wiilf insfrting nfw rows to b <dodf>FiltfrfdRowSft</dodf> instbndf.
     *
     * @pbrbm vbluf An <dodf>Objfdt</dodf> vbluf wiidi nffds to bf difdkfd,
     *        wiftifr it dbn bf pbrt of tiis <dodf>FiltfrRowSft</dodf> objfdt.
     * @pbrbm dolumn b <dodf>int</dodf> objfdt tibt must mbtdi tif
     *        SQL indfx of b dolumn in tiis <dodf>RowSft</dodf> objfdt. Tiis must
     *        ibvf bffn pbssfd to <dodf>Prfdidbtf</dodf> bs onf of tif dolumns
     *        for filtfring wiilf initiblizing b <dodf>Prfdidbtf</dodf>
     * @rfturn <dodf>truf</dodf> if row vbluf lifs witiin tif filtfr;
     *     <dodf>fblsf</dodf> otifrwisf
     * @tirows SQLExdfption if tif dolumn is not pbrt of filtfring dritfrib
     */
    publid boolfbn fvblubtf(Objfdt vbluf, int dolumn) tirows SQLExdfption;

    /**
     * Tiis mftiod is dbllfd by tif <dodf>FiltfrfdRowSft</dodf> objfdt
     * to difdk wiftifr tif vbluf lifs bftwffn tif filtfring dritfrib sft
     * using tif sftFiltfr mftiod.
     * <P>
     * Tif <dodf>FiltfrfdRowSft</dodf> objfdt will usf tiis mftiod intfrnblly
     * wiilf insfrting nfw rows to b <dodf>FiltfrfdRowSft</dodf> instbndf.
     *
     * @pbrbm vbluf An <dodf>Objfdt</dodf> vbluf wiidi nffds to bf difdkfd,
     * wiftifr it dbn bf pbrt of tiis <dodf>FiltfrRowSft</dodf>.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis <dodf>RowSft</dodf>, ignoring dbsf. Tiis must
     *        ibvf bffn pbssfd to <dodf>Prfdidbtf</dodf> bs onf of tif dolumns for filtfring
     *        wiilf initiblizing b <dodf>Prfdidbtf</dodf>
     *
     * @rfturn <dodf>truf</dodf> if vbluf lifs witiin tif filtfr; <dodf>fblsf</dodf> otifrwisf
     *
     * @tirows SQLExdfption if tif dolumn is not pbrt of filtfring dritfrib
     */
    publid boolfbn fvblubtf(Objfdt vbluf, String dolumnNbmf) tirows SQLExdfption;

}
