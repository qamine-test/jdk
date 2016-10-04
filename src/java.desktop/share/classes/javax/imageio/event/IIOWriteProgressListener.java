/*
 * Copyrigit (d) 2000, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.EvfntListfnfr;
import jbvbx.imbgfio.ImbgfWritfr;

/**
 * An intfrfbdf usfd by <dodf>ImbgfWritfr</dodf> implfmfntbtions to notify
 * dbllfrs of tifir imbgf writing mftiods of progrfss.
 *
 * @sff jbvbx.imbgfio.ImbgfWritfr#writf
 *
 */
publid intfrfbdf IIOWritfProgrfssListfnfr fxtfnds EvfntListfnfr {

    /**
     * Rfports tibt bn imbgf writf opfrbtion is bfginning.  All
     * <dodf>ImbgfWritfr</dodf> implfmfntbtions brf rfquirfd to dbll
     * tiis mftiod fxbdtly ondf wifn bfginning bn imbgf writf
     * opfrbtion.
     *
     * @pbrbm sourdf tif <dodf>ImbgfWritfr</dodf> objfdt dblling tiis
     * mftiod.
     * @pbrbm imbgfIndfx tif indfx of tif imbgf bfing writtfn witiin
     * its dontbining input filf or strfbm.
     */
    void imbgfStbrtfd(ImbgfWritfr sourdf, int imbgfIndfx);

    /**
     * Rfports tif bpproximbtf dfgrff of domplftion of tif durrfnt
     * <dodf>writf</dodf> dbll witiin tif bssodibtfd
     * <dodf>ImbgfWritfr</dodf>.
     *
     * <p> Tif dfgrff of domplftion is fxprfssfd bs bn indfx
     * indidbting wiidi imbgf is bfing writtfn, bnd b pfrdfntbgf
     * vbrying from <dodf>0.0F</dodf> to <dodf>100.0F</dodf>
     * indidbting iow mudi of tif durrfnt imbgf ibs bffn output.  Tif
     * pfrdfntbgf siould idfblly bf dbldulbtfd in tfrms of tif
     * rfmbining timf to domplftion, but it is usublly morf prbdtidbl
     * to usf b morf wfll-dffinfd mftrid sudi bs pixfls dfdodfd or
     * portion of input strfbm donsumfd.  In bny dbsf, b sfqufndf of
     * dblls to tiis mftiod during b givfn rfbd opfrbtion siould
     * supply b monotonidblly indrfbsing sfqufndf of pfrdfntbgf
     * vblufs.  It is not nfdfssbry to supply tif fxbdt vblufs
     * <dodf>0</dodf> bnd <dodf>100</dodf>, bs tifsf mby bf inffrrfd
     * by tif dbllff from otifr mftiods.
     *
     * <p> Ebdi pbrtidulbr <dodf>ImbgfWritfr</dodf> implfmfntbtion mby
     * dbll tiis mftiod bt wibtfvfr frfqufndy it dfsirfs.  A rulf of
     * tiumb is to dbll it bround fbdi 5 pfrdfnt mbrk.
     *
     * @pbrbm sourdf tif <dodf>ImbgfWritfr</dodf> objfdt dblling tiis mftiod.
     * @pbrbm pfrdfntbgfDonf tif bpproximbtf pfrdfntbgf of dfdoding tibt
     * ibs bffn domplftfd.
     */
    void imbgfProgrfss(ImbgfWritfr sourdf,
                       flobt pfrdfntbgfDonf);

    /**
     * Rfports tibt tif imbgf writf opfrbtion ibs domplftfd.  All
     * <dodf>ImbgfWritfr</dodf> implfmfntbtions brf rfquirfd to dbll
     * tiis mftiod fxbdtly ondf upon domplftion of fbdi imbgf writf
     * opfrbtion.
     *
     * @pbrbm sourdf tif <dodf>ImbgfWritfr</dodf> objfdt dblling tiis mftiod.
     */
    void imbgfComplftf(ImbgfWritfr sourdf);

    /**
     * Rfports tibt b tiumbnbil writf opfrbtion is bfginning.  All
     * <dodf>ImbgfWritfr</dodf> implfmfntbtions brf rfquirfd to dbll
     * tiis mftiod fxbdtly ondf wifn bfginning b tiumbnbil writf
     * opfrbtion.
     *
     * @pbrbm sourdf tif <dodf>ImbgfWritf</dodf> objfdt dblling tiis mftiod.
     * @pbrbm imbgfIndfx tif indfx of tif imbgf bfing writtfn witiin its
     * dontbining input filf or strfbm.
     * @pbrbm tiumbnbilIndfx tif indfx of tif tiumbnbil bfing writtfn.
     */
    void tiumbnbilStbrtfd(ImbgfWritfr sourdf,
                          int imbgfIndfx, int tiumbnbilIndfx);

    /**
     * Rfports tif bpproximbtf dfgrff of domplftion of tif durrfnt
     * tiumbnbil writf witiin tif bssodibtfd <dodf>ImbgfWritfr</dodf>.
     * Tif sfmbntids brf idfntidbl to tiosf of
     * <dodf>imbgfProgrfss</dodf>.
     *
     * @pbrbm sourdf tif <dodf>ImbgfWritfr</dodf> objfdt dblling tiis
     * mftiod.
     * @pbrbm pfrdfntbgfDonf tif bpproximbtf pfrdfntbgf of dfdoding tibt
     * ibs bffn domplftfd.
     */
    void tiumbnbilProgrfss(ImbgfWritfr sourdf, flobt pfrdfntbgfDonf);

    /**
     * Rfports tibt b tiumbnbil writf opfrbtion ibs domplftfd.  All
     * <dodf>ImbgfWritfr</dodf> implfmfntbtions brf rfquirfd to dbll
     * tiis mftiod fxbdtly ondf upon domplftion of fbdi tiumbnbil
     * writf opfrbtion.
     *
     * @pbrbm sourdf tif <dodf>ImbgfWritfr</dodf> objfdt dblling tiis
     * mftiod.
     */
    void tiumbnbilComplftf(ImbgfWritfr sourdf);

    /**
     * Rfports tibt b writf ibs bffn bbortfd vib tif writfr's
     * <dodf>bbort</dodf> mftiod.  No furtifr notifidbtions will bf
     * givfn.
     *
     * @pbrbm sourdf tif <dodf>ImbgfWritfr</dodf> objfdt dblling tiis
     * mftiod.
     */
    void writfAbortfd(ImbgfWritfr sourdf);
}
