/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.instrumfnt;

/*
 * Copyrigit 2003 Wily Tfdinology, Ind.
 */

/**
 * Tiis dlbss sfrvfs bs b pbrbmftfr blodk to tif <dodf>Instrumfntbtion.rfdffinfClbssfs</dodf> mftiod.
 * Sfrvfs to bind tif <dodf>Clbss</dodf> tibt nffds rfdffining togftifr witi tif nfw dlbss filf bytfs.
 *
 * @sff     jbvb.lbng.instrumfnt.Instrumfntbtion#rfdffinfClbssfs
 * @sindf   1.5
 */
publid finbl dlbss ClbssDffinition {
    /**
     *  Tif dlbss to rfdffinf
     */
    privbtf finbl Clbss<?> mClbss;

    /**
     *  Tif rfplbdfmfnt dlbss filf bytfs
     */
    privbtf finbl bytf[]   mClbssFilf;

    /**
     *  Crfbtfs b nfw <dodf>ClbssDffinition</dodf> binding using tif supplifd
     *  dlbss bnd dlbss filf bytfs. Dofs not dopy tif supplifd bufffr, just dbpturfs b rfffrfndf to it.
     *
     * @pbrbm tifClbss tif <dodf>Clbss</dodf> tibt nffds rfdffining
     * @pbrbm tifClbssFilf tif nfw dlbss filf bytfs
     *
     * @tirows jbvb.lbng.NullPointfrExdfption if tif supplifd dlbss or brrby is <dodf>null</dodf>.
     */
    publid
    ClbssDffinition(    Clbss<?> tifClbss,
                        bytf[]  tifClbssFilf) {
        if (tifClbss == null || tifClbssFilf == null) {
            tirow nfw NullPointfrExdfption();
        }
        mClbss      = tifClbss;
        mClbssFilf  = tifClbssFilf;
    }

    /**
     * Rfturns tif dlbss.
     *
     * @rfturn    tif <dodf>Clbss</dodf> objfdt rfffrrfd to.
     */
    publid Clbss<?>
    gftDffinitionClbss() {
        rfturn mClbss;
    }

    /**
     * Rfturns tif brrby of bytfs tibt dontbins tif nfw dlbss filf.
     *
     * @rfturn    tif dlbss filf bytfs.
     */
    publid bytf[]
    gftDffinitionClbssFilf() {
        rfturn mClbssFilf;
    }
}
