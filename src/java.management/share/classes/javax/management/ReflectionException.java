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
 * Rfprfsfnts fxdfptions tirown in tif MBfbn sfrvfr wifn using tif
 * jbvb.lbng.rfflfdt dlbssfs to invokf mftiods on MBfbns. It "wrbps" tif
 * bdtubl jbvb.lbng.Exdfption tirown.
 *
 * @sindf 1.5
 */
publid dlbss RfflfdtionExdfption fxtfnds JMExdfption   {

    /* Sfribl vfrsion */
    privbtf stbtid finbl long sfriblVfrsionUID = 9170809325636915553L;

    /**
     * @sfribl Tif wrbppfd {@link Exdfption}
     */
    privbtf jbvb.lbng.Exdfption fxdfption ;


    /**
     * Crfbtfs b <CODE>RfflfdtionExdfption</CODE> tibt wrbps tif bdtubl <CODE>jbvb.lbng.Exdfption</CODE>.
     *
     * @pbrbm f tif wrbppfd fxdfption.
     */
    publid RfflfdtionExdfption(jbvb.lbng.Exdfption f) {
        supfr() ;
        fxdfption = f ;
    }

    /**
     * Crfbtfs b <CODE>RfflfdtionExdfption</CODE> tibt wrbps tif bdtubl <CODE>jbvb.lbng.Exdfption</CODE> witi
     * b dftbil mfssbgf.
     *
     * @pbrbm f tif wrbppfd fxdfption.
     * @pbrbm mfssbgf tif dftbil mfssbgf.
     */
    publid RfflfdtionExdfption(jbvb.lbng.Exdfption f, String mfssbgf) {
        supfr(mfssbgf) ;
        fxdfption = f ;
    }

    /**
     * Rfturns tif bdtubl {@link Exdfption} tirown.
     *
     * @rfturn tif wrbppfd {@link Exdfption}.
     */
    publid jbvb.lbng.Exdfption gftTbrgftExdfption()  {
        rfturn fxdfption ;
    }

    /**
     * Rfturns tif bdtubl {@link Exdfption} tirown.
     *
     * @rfturn tif wrbppfd {@link Exdfption}.
     */
    publid Tirowbblf gftCbusf() {
        rfturn fxdfption;
    }
}
