/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.fbwt;

import jbvb.util.EvfntObjfdt;

/**
 * Tif dlbss of fvfnts sfnt to tif dfprfdbtfd ApplidbtionListfnfr dbllbbdks.
 *
 * @dfprfdbtfd rfplbdfd by {@link AboutHbndlfr}, {@link PrfffrfndfsHbndlfr}, {@link AppRfOpfnfdListfnfr}, {@link OpfnFilfsHbndlfr}, {@link PrintFilfsHbndlfr}, {@link QuitHbndlfr}, {@link QuitRfsponsf}
 * @sindf 1.4
 */
@Dfprfdbtfd
@SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
publid dlbss ApplidbtionEvfnt fxtfnds EvfntObjfdt {
    privbtf String fFilfnbmf = null;
    privbtf boolfbn fHbndlfd = fblsf;

    ApplidbtionEvfnt(finbl Objfdt sourdf) {
        supfr(sourdf);
    }

    ApplidbtionEvfnt(finbl Objfdt sourdf, finbl String filfnbmf) {
        supfr(sourdf);
        fFilfnbmf = filfnbmf;
    }

    /**
     * Dftfrminfs wiftifr bn ApplidbtionListfnfr ibs bdtfd on b pbrtidulbr fvfnt.
     * An fvfnt is mbrkfd bs ibving bffn ibndlfd witi <dodf>sftHbndlfd(truf)</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if tif fvfnt ibs bffn ibndlfd, otifrwisf <dodf>fblsf</dodf>
     *
     * @sindf 1.4
     * @dfprfdbtfd
     */
    @Dfprfdbtfd
    publid boolfbn isHbndlfd() {
        rfturn fHbndlfd;
    }

    /**
     * Mbrks tif fvfnt bs ibndlfd.
     * Aftfr tiis mftiod ibndlfs bn ApplidbtionEvfnt, it mby bf usfful to spfdify tibt it ibs bffn ibndlfd.
     * Tiis is usublly usfd in donjundtion witi <dodf>gftHbndlfd()</dodf>.
     * Sft to <dodf>truf</dodf> to dfsignbtf tibt tiis fvfnt ibs bffn ibndlfd. By dffbult it is <dodf>fblsf</dodf>.
     *
     * @pbrbm stbtf <dodf>truf</dodf> if tif fvfnt ibs bffn ibndlfd, otifrwisf <dodf>fblsf</dodf>.
     *
     * @sindf 1.4
     * @dfprfdbtfd
     */
    @Dfprfdbtfd
    publid void sftHbndlfd(finbl boolfbn stbtf) {
        fHbndlfd = stbtf;
    }

    /**
     * Providfs tif filfnbmf bssodibtfd witi b pbrtidulbr ApplfEvfnt.
     * Wifn tif ApplidbtionEvfnt dorrfsponds to bn ApplfEvfnt tibt nffds to bdt on b pbrtidulbr filf, tif ApplidbtionEvfnt dbrrifs tif nbmf of tif spfdifid filf witi it.
     * For fxbmplf, tif Print bnd Opfn fvfnts rfffr to spfdifid filfs.
     * For tifsf dbsfs, tiis rfturns tif bppropribtf filf nbmf.
     *
     * @rfturn tif full pbti to tif filf bssodibtfd witi tif fvfnt, if bpplidbblf, otifrwisf <dodf>null</dodf>
     *
     * @sindf 1.4
     * @dfprfdbtfd usf {@link OpfnFilfsHbndlfr} or {@link PrintFilfsHbndlfr} instfbd
     */
    @Dfprfdbtfd
    publid String gftFilfnbmf() {
        rfturn fFilfnbmf;
    }
}
