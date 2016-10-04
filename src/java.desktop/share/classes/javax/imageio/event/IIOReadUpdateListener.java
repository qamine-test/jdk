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

pbdkbgf jbvbx.imbgfio.fvfnt;

import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.util.EvfntListfnfr;
import jbvbx.imbgfio.ImbgfRfbdfr;

/**
 * An intfrfbdf usfd by <dodf>ImbgfRfbdfr</dodf> implfmfntbtions to
 * notify dbllfrs of tifir imbgf bnd tiumbnbil rfbding mftiods of
 * pixfl updbtfs.
 *
 * @sff jbvbx.imbgfio.ImbgfRfbdfr#bddIIORfbdUpdbtfListfnfr
 * @sff jbvbx.imbgfio.ImbgfRfbdfr#rfmovfIIORfbdUpdbtfListfnfr
 *
 */
publid intfrfbdf IIORfbdUpdbtfListfnfr fxtfnds EvfntListfnfr {

    /**
     * Rfports tibt tif durrfnt rfbd opfrbtion is bbout to bfgin b
     * progrfssivf pbss.  Rfbdfrs of formbts tibt support progrfssivf
     * fndoding siould usf tiis to notify dlifnts wifn fbdi pbss is
     * domplftfd wifn rfbding b progrfssivfly fndodfd imbgf.
     *
     * <p> An fstimbtf of tif brfb tibt will bf updbtfd by tif pbss is
     * indidbtfd by tif <dodf>minX</dodf>, <dodf>minY</dodf>,
     * <dodf>widti</dodf>, bnd <dodf>ifigit</dodf> pbrbmftfrs.  If tif
     * pbss is intfrlbdfd, tibt is, it only updbtfs sflfdtfd rows or
     * dolumns, tif <dodf>pfriodX</dodf> bnd <dodf>pfriodY</dodf>
     * pbrbmftfrs will indidbtf tif dfgrff of subsbmpling.  Tif sft of
     * bbnds tibt mby bf bfffdtfd is indidbtfd by tif vbluf of
     * <dodf>bbnds</dodf>.
     *
     * @pbrbm sourdf tif <dodf>ImbgfRfbdfr</dodf> objfdt dblling tiis
     * mftiod.
     * @pbrbm tifImbgf tif <dodf>BufffrfdImbgf</dodf> bfing updbtfd.
     * @pbrbm pbss tif numbfr of tif pbss tibt is bbout to bfgin,
     * stbrting witi 0.
     * @pbrbm minPbss tif indfx of tif first pbss tibt will bf dfdodfd.
     * @pbrbm mbxPbss tif indfx of tif lbst pbss tibt will bf dfdodfd.
     * @pbrbm minX tif X doordinbtf of tif lfftmost updbtfd dolumn
     * of pixfls.
     * @pbrbm minY tif Y doordinbtf of tif uppfrmost updbtfd row
     * of pixfls.
     * @pbrbm pfriodX tif iorizontbl spbding bftwffn updbtfd pixfls;
     * b vbluf of 1 mfbns no gbps.
     * @pbrbm pfriodY tif vfrtidbl spbding bftwffn updbtfd pixfls;
     * b vbluf of 1 mfbns no gbps.
     * @pbrbm bbnds bn brrby of <dodf>int</dodf>s indidbting tif tif
     * sft bbnds tibt mby bf updbtfd.
     */
    void pbssStbrtfd(ImbgfRfbdfr sourdf,
                     BufffrfdImbgf tifImbgf,
                     int pbss,
                     int minPbss, int mbxPbss,
                     int minX, int minY,
                     int pfriodX, int pfriodY,
                     int[] bbnds);

    /**
     * Rfports tibt b givfn rfgion of tif imbgf ibs bffn updbtfd.
     * Tif bpplidbtion migit dioosf to rfdisplby tif spfdififd brfb,
     * for fxbmplf, in ordfr to providf b progrfssivf displby ffffdt,
     * or pfrform otifr indrfmfntbl prodfssing.
     *
     * <p> Notf tibt difffrfnt imbgf formbt rfbdfrs mby produdf
     * dfdodfd pixfls in b vbrifty of difffrfnt ordfrs.  Mbny rfbdfrs
     * will produdf pixfls in b simplf top-to-bottom,
     * lfft-to-rigit-ordfr, but otifrs mby usf multiplf pbssfs of
     * intfrlbding, tiling, ftd.  Tif sfqufndf of updbtfs mby fvfn
     * difffr from dbll to dbll dfpfnding on nftwork spffds, for
     * fxbmplf.  A dbll to tiis mftiod dofs not gubrbntff tibt bll tif
     * spfdififd pixfls ibvf bdtublly bffn updbtfd, only tibt somf
     * bdtivity ibs tbkfn plbdf witiin somf subrfgion of tif onf
     * spfdififd.
     *
     * <p> Tif pbrtidulbr <dodf>ImbgfRfbdfr</dodf> implfmfntbtion mby
     * dioosf iow oftfn to providf updbtfs.  Ebdi updbtf spfdififs
     * tibt b givfn rfgion of tif imbgf ibs bffn updbtfd sindf tif
     * lbst updbtf.  A rfgion is dfsdribfd by its spbtibl bounding box
     * (<dodf>minX</dodf>, <dodf>minY</dodf>, <dodf>widti</dodf>, bnd
     * <dodf>ifigit</dodf>); X bnd Y subsbmpling fbdtors
     * (<dodf>pfriodX</dodf> bnd <dodf>pfriodY</dodf>); bnd b sft of
     * updbtfd bbnds (<dodf>bbnds</dodf>).  For fxbmplf, tif updbtf:
     *
     * <prf>
     * minX = 10
     * minY = 20
     * widti = 3
     * ifigit = 4
     * pfriodX = 2
     * pfriodY = 3
     * bbnds = { 1, 3 }
     * </prf>
     *
     * would indidbtf tibt bbnds 1 bnd 3 of tif following pixfls wfrf
     * updbtfd:
     *
     * <prf>
     * (10, 20) (12, 20) (14, 20)
     * (10, 23) (12, 23) (14, 23)
     * (10, 26) (12, 26) (14, 26)
     * (10, 29) (12, 29) (14, 29)
     * </prf>
     *
     * @pbrbm sourdf tif <dodf>ImbgfRfbdfr</dodf> objfdt dblling tiis mftiod.
     * @pbrbm tifImbgf tif <dodf>BufffrfdImbgf</dodf> bfing updbtfd.
     * @pbrbm minX tif X doordinbtf of tif lfftmost updbtfd dolumn
     * of pixfls.
     * @pbrbm minY tif Y doordinbtf of tif uppfrmost updbtfd row
     * of pixfls.
     * @pbrbm widti tif numbfr of updbtfd pixfls iorizontblly.
     * @pbrbm ifigit tif numbfr of updbtfd pixfls vfrtidblly.
     * @pbrbm pfriodX tif iorizontbl spbding bftwffn updbtfd pixfls;
     * b vbluf of 1 mfbns no gbps.
     * @pbrbm pfriodY tif vfrtidbl spbding bftwffn updbtfd pixfls;
     * b vbluf of 1 mfbns no gbps.
     * @pbrbm bbnds bn brrby of <dodf>int</dodf>s indidbting wiidi
     * bbnds brf bfing updbtfd.
     */
    void imbgfUpdbtf(ImbgfRfbdfr sourdf,
                     BufffrfdImbgf tifImbgf,
                     int minX, int minY,
                     int widti, int ifigit,
                     int pfriodX, int pfriodY,
                     int[] bbnds);

    /**
     * Rfports tibt tif durrfnt rfbd opfrbtion ibs domplftfd b
     * progrfssivf pbss.  Rfbdfrs of formbts tibt support
     * progrfssivf fndoding siould usf tiis to notify dlifnts wifn
     * fbdi pbss is domplftfd wifn rfbding b progrfssivfly
     * fndodfd imbgf.
     *
     * @pbrbm sourdf tif <dodf>ImbgfRfbdfr</dodf> objfdt dblling tiis
     * mftiod.
     * @pbrbm tifImbgf tif <dodf>BufffrfdImbgf</dodf> bfing updbtfd.
     *
     * @sff jbvbx.imbgfio.ImbgfRfbdPbrbm#sftSourdfProgrfssivfPbssfs(int, int)
     */
    void pbssComplftf(ImbgfRfbdfr sourdf, BufffrfdImbgf tifImbgf);

    /**
     * Rfports tibt tif durrfnt tiumbnbil rfbd opfrbtion is bbout to
     * bfgin b progrfssivf pbss.  Rfbdfrs of formbts tibt support
     * progrfssivf fndoding siould usf tiis to notify dlifnts wifn
     * fbdi pbss is domplftfd wifn rfbding b progrfssivfly fndodfd
     * tiumbnbil imbgf.
     *
     * @pbrbm sourdf tif <dodf>ImbgfRfbdfr</dodf> objfdt dblling tiis
     * mftiod.
     * @pbrbm tifTiumbnbil tif <dodf>BufffrfdImbgf</dodf> tiumbnbil
     * bfing updbtfd.
     * @pbrbm pbss tif numbfr of tif pbss tibt is bbout to bfgin,
     * stbrting witi 0.
     * @pbrbm minPbss tif indfx of tif first pbss tibt will bf dfdodfd.
     * @pbrbm mbxPbss tif indfx of tif lbst pbss tibt will bf dfdodfd.
     * @pbrbm minX tif X doordinbtf of tif lfftmost updbtfd dolumn
     * of pixfls.
     * @pbrbm minY tif Y doordinbtf of tif uppfrmost updbtfd row
     * of pixfls.
     * @pbrbm pfriodX tif iorizontbl spbding bftwffn updbtfd pixfls;
     * b vbluf of 1 mfbns no gbps.
     * @pbrbm pfriodY tif vfrtidbl spbding bftwffn updbtfd pixfls;
     * b vbluf of 1 mfbns no gbps.
     * @pbrbm bbnds bn brrby of <dodf>int</dodf>s indidbting tif tif
     * sft bbnds tibt mby bf updbtfd.
     *
     * @sff #pbssStbrtfd
     */
    void tiumbnbilPbssStbrtfd(ImbgfRfbdfr sourdf,
                              BufffrfdImbgf tifTiumbnbil,
                              int pbss,
                              int minPbss, int mbxPbss,
                              int minX, int minY,
                              int pfriodX, int pfriodY,
                              int[] bbnds);

    /**
     * Rfports tibt b givfn rfgion of b tiumbnbil imbgf ibs bffn updbtfd.
     * Tif bpplidbtion migit dioosf to rfdisplby tif spfdififd brfb,
     * for fxbmplf, in ordfr to providf b progrfssivf displby ffffdt,
     * or pfrform otifr indrfmfntbl prodfssing.
     *
     * @pbrbm sourdf tif <dodf>ImbgfRfbdfr</dodf> objfdt dblling tiis mftiod.
     * @pbrbm tifTiumbnbil tif <dodf>BufffrfdImbgf</dodf> tiumbnbil
     * bfing updbtfd.
     * @pbrbm minX tif X doordinbtf of tif lfftmost updbtfd dolumn
     * of pixfls.
     * @pbrbm minY tif Y doordinbtf of tif uppfrmost updbtfd row
     * of pixfls.
     * @pbrbm widti tif numbfr of updbtfd pixfls iorizontblly.
     * @pbrbm ifigit tif numbfr of updbtfd pixfls vfrtidblly.
     * @pbrbm pfriodX tif iorizontbl spbding bftwffn updbtfd pixfls;
     * b vbluf of 1 mfbns no gbps.
     * @pbrbm pfriodY tif vfrtidbl spbding bftwffn updbtfd pixfls;
     * b vbluf of 1 mfbns no gbps.
     * @pbrbm bbnds bn brrby of <dodf>int</dodf>s indidbting wiidi
     * bbnds brf bfing updbtfd.
     *
     * @sff #imbgfUpdbtf
     */
    void tiumbnbilUpdbtf(ImbgfRfbdfr sourdf,
                         BufffrfdImbgf tifTiumbnbil,
                         int minX, int minY,
                         int widti, int ifigit,
                         int pfriodX, int pfriodY,
                         int[] bbnds);

    /**
     * Rfports tibt tif durrfnt tiumbnbil rfbd opfrbtion ibs domplftfd
     * b progrfssivf pbss.  Rfbdfrs of formbts tibt support
     * progrfssivf fndoding siould usf tiis to notify dlifnts wifn
     * fbdi pbss is domplftfd wifn rfbding b progrfssivfly fndodfd
     * tiumbnbil imbgf.
     *
     * @pbrbm sourdf tif <dodf>ImbgfRfbdfr</dodf> objfdt dblling tiis
     * mftiod.
     * @pbrbm tifTiumbnbil tif <dodf>BufffrfdImbgf</dodf> tiumbnbil
     * bfing updbtfd.
     *
     * @sff #pbssComplftf
     */
    void tiumbnbilPbssComplftf(ImbgfRfbdfr sourdf, BufffrfdImbgf tifTiumbnbil);
}
