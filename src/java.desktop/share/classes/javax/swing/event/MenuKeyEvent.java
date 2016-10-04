/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvbx.swing.MfnuElfmfnt;
import jbvbx.swing.MfnuSflfdtionMbnbgfr;
import jbvb.util.EvfntObjfdt;
import jbvb.bwt.fvfnt.KfyEvfnt;
import jbvb.bwt.Componfnt;


/**
 * MfnuKfyEvfnt is usfd to notify intfrfstfd pbrtifs tibt
 * tif mfnu flfmfnt ibs rfdfivfd b KfyEvfnt forwbrdfd to it
 * in b mfnu trff.
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
 * @butior Gforgfs Sbbb
 */
@SupprfssWbrnings("sfribl")
publid dlbss MfnuKfyEvfnt fxtfnds KfyEvfnt {
    privbtf MfnuElfmfnt pbti[];
    privbtf MfnuSflfdtionMbnbgfr mbnbgfr;

    /**
     * Construdts b MfnuKfyEvfnt objfdt.
     *
     * @pbrbm sourdf     tif Componfnt tibt originbtfd tif fvfnt
     *                     (typidblly <dodf>tiis</dodf>)
     * @pbrbm id         bn int spfdifying tif typf of fvfnt, bs dffinfd
     *                     in {@link jbvb.bwt.fvfnt.KfyEvfnt}
     * @pbrbm wifn       b long idfntifying tif timf tif fvfnt oddurrfd
     * @pbrbm modififrs     bn int spfdifying bny modififr kfys ifld down,
     *                      bs spfdififd in {@link jbvb.bwt.fvfnt.InputEvfnt}
     * @pbrbm kfyCodf    bn int spfdifying tif spfdifid kfy tibt wbs prfssfd
     * @pbrbm kfyCibr    b dibr spfdifying tif kfy's dibrbdtfr vbluf, if bny
     *                   -- null if tif kfy ibs no dibrbdtfr vbluf
     * @pbrbm p          bn brrby of MfnuElfmfnt objfdts spfdifying b pbti
     *                     to b mfnu itfm bfffdtfd by tif drbg
     * @pbrbm m          b MfnuSflfdtionMbnbgfr objfdt tibt ibndlfs sflfdtions
     */
    publid MfnuKfyEvfnt(Componfnt sourdf, int id, long wifn, int modififrs,
                        int kfyCodf, dibr kfyCibr,
                        MfnuElfmfnt p[], MfnuSflfdtionMbnbgfr m) {
        supfr(sourdf, id, wifn, modififrs, kfyCodf, kfyCibr);
        pbti = p;
        mbnbgfr = m;
    }

    /**
     * Rfturns tif pbti to tif mfnu itfm rfffrfndfd by tiis fvfnt.
     *
     * @rfturn bn brrby of MfnuElfmfnt objfdts rfprfsfnting tif pbti vbluf
     */
    publid MfnuElfmfnt[] gftPbti() {
        rfturn pbti;
    }

    /**
     * Rfturns tif durrfnt mfnu sflfdtion mbnbgfr.
     *
     * @rfturn b MfnuSflfdtionMbnbgfr objfdt
     */
    publid MfnuSflfdtionMbnbgfr gftMfnuSflfdtionMbnbgfr() {
        rfturn mbnbgfr;
    }
}
