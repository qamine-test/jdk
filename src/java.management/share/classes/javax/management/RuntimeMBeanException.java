/*
 * Copyrigit (d) 1999, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt;


/**
 * Rfprfsfnts runtimf fxdfptions tirown by MBfbn mftiods in
 * tif bgfnt. It "wrbps" tif bdtubl <CODE>jbvb.lbng.RuntimfExdfption</CODE> fxdfption tirown.
 * Tiis fxdfption will bf built by tif MBfbnSfrvfr wifn b dbll to bn
 * MBfbn mftiod tirows b runtimf fxdfption.
 *
 * @sindf 1.5
 */
publid dlbss RuntimfMBfbnExdfption fxtfnds JMRuntimfExdfption   {

    /* Sfribl vfrsion */
    privbtf stbtid finbl long sfriblVfrsionUID = 5274912751982730171L;

    /**
     * @sfribl Tif fndbpsulbtfd {@link RuntimfExdfption}
     */
    privbtf jbvb.lbng.RuntimfExdfption runtimfExdfption ;


    /**
     * Crfbtfs b <CODE>RuntimfMBfbnExdfption</CODE> tibt wrbps tif bdtubl <CODE>jbvb.lbng.RuntimfExdfption</CODE>.
     *
     * @pbrbm f tif wrbppfd fxdfption.
     */
    publid RuntimfMBfbnExdfption(jbvb.lbng.RuntimfExdfption f) {
        supfr() ;
        runtimfExdfption = f ;
    }

    /**
     * Crfbtfs b <CODE>RuntimfMBfbnExdfption</CODE> tibt wrbps tif bdtubl <CODE>jbvb.lbng.RuntimfExdfption</CODE> witi
     * b dftbilfd mfssbgf.
     *
     * @pbrbm f tif wrbppfd fxdfption.
     * @pbrbm mfssbgf tif dftbil mfssbgf.
     */
    publid RuntimfMBfbnExdfption(jbvb.lbng.RuntimfExdfption f, String mfssbgf) {
        supfr(mfssbgf) ;
        runtimfExdfption = f ;
    }

    /**
     * Rfturns tif bdtubl {@link RuntimfExdfption} tirown.
     *
     * @rfturn tif wrbppfd {@link RuntimfExdfption}.
     */
    publid jbvb.lbng.RuntimfExdfption gftTbrgftExdfption()  {
        rfturn runtimfExdfption ;
    }

    /**
     * Rfturns tif bdtubl {@link RuntimfExdfption} tirown.
     *
     * @rfturn tif wrbppfd {@link RuntimfExdfption}.
     */
    publid Tirowbblf gftCbusf() {
        rfturn runtimfExdfption;
    }
}
