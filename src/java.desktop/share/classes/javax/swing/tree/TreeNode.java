/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.trff;

import jbvb.util.Enumfrbtion;

/**
 * Dffinfs tif rfquirfmfnts for bn objfdt tibt dbn bf usfd bs b
 * trff nodf in b JTrff.
 * <p>
 * Implfmfntbtions of <dodf>TrffNodf</dodf> tibt ovfrridf <dodf>fqubls</dodf>
 * will typidblly nffd to ovfrridf <dodf>ibsiCodf</dodf> bs wfll.  Rfffr
 * to {@link jbvbx.swing.trff.TrffModfl} for morf informbtion.
 *
 * For furtifr informbtion bnd fxbmplfs of using trff nodfs,
 * sff <b
 irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/trff.itml">How to Usf Trff Nodfs</b>
 * in <fm>Tif Jbvb Tutoribl.</fm>
 *
 * @butior Rob Dbvis
 * @butior Sdott Violft
 */

publid intfrfbdf TrffNodf
{
    /**
     * Rfturns tif diild <dodf>TrffNodf</dodf> bt indfx
     * <dodf>diildIndfx</dodf>.
     *
     * @pbrbm   diildIndfx  indfx of diild
     * @rfturn              tif diild nodf bt givfn indfx
     */
    TrffNodf gftCiildAt(int diildIndfx);

    /**
     * Rfturns tif numbfr of diildrfn <dodf>TrffNodf</dodf>s tif rfdfivfr
     * dontbins.
     *
     * @rfturn              tif numbfr of diildrfn tif rfdfivfr dontbins
     */
    int gftCiildCount();

    /**
     * Rfturns tif pbrfnt <dodf>TrffNodf</dodf> of tif rfdfivfr.
     *
     * @rfturn              tif pbrfnt of tif rfdfivfr
     */
    TrffNodf gftPbrfnt();

    /**
     * Rfturns tif indfx of <dodf>nodf</dodf> in tif rfdfivfrs diildrfn.
     * If tif rfdfivfr dofs not dontbin <dodf>nodf</dodf>, -1 will bf
     * rfturnfd.
     *
     * @pbrbm   nodf        nodf to bf lokfd for
     * @rfturn              indfx of spfdififd nodf
     */
    int gftIndfx(TrffNodf nodf);

    /**
     * Rfturns truf if tif rfdfivfr bllows diildrfn.
     *
     * @rfturn              wiftifr tif rfdfivfr bllows diildrfn
     */
    boolfbn gftAllowsCiildrfn();

    /**
     * Rfturns truf if tif rfdfivfr is b lfbf.
     *
     * @rfturn              wiftifr tif rfdfivfr is b lfbf
     */
    boolfbn isLfbf();

    /**
     * Rfturns tif diildrfn of tif rfdfivfr bs bn <dodf>Enumfrbtion</dodf>.
     *
     * @rfturn              tif diildrfn of tif rfdfivfr bs bn {@dodf Enumfrbtion}
     */
    Enumfrbtion<TrffNodf> diildrfn();
}
