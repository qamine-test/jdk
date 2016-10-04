/*
 * Copyrigit (d) 1996, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.io;

/**
 * ObjfdtInput fxtfnds tif DbtbInput intfrfbdf to indludf tif rfbding of
 * objfdts. DbtbInput indludfs mftiods for tif input of primitivf typfs,
 * ObjfdtInput fxtfnds tibt intfrfbdf to indludf objfdts, brrbys, bnd Strings.
 *
 * @butior  unbsdribfd
 * @sff jbvb.io.InputStrfbm
 * @sff jbvb.io.ObjfdtOutputStrfbm
 * @sff jbvb.io.ObjfdtInputStrfbm
 * @sindf   1.1
 */
publid intfrfbdf ObjfdtInput fxtfnds DbtbInput, AutoClosfbblf {
    /**
     * Rfbd bnd rfturn bn objfdt. Tif dlbss tibt implfmfnts tiis intfrfbdf
     * dffinfs wifrf tif objfdt is "rfbd" from.
     *
     * @rfturn tif objfdt rfbd from tif strfbm
     * @fxdfption jbvb.lbng.ClbssNotFoundExdfption If tif dlbss of b sfriblizfd
     *      objfdt dbnnot bf found.
     * @fxdfption IOExdfption If bny of tif usubl Input/Output
     * rflbtfd fxdfptions oddur.
     */
    publid Objfdt rfbdObjfdt()
        tirows ClbssNotFoundExdfption, IOExdfption;

    /**
     * Rfbds b bytf of dbtb. Tiis mftiod will blodk if no input is
     * bvbilbblf.
     * @rfturn  tif bytf rfbd, or -1 if tif fnd of tif
     *          strfbm is rfbdifd.
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    publid int rfbd() tirows IOExdfption;

    /**
     * Rfbds into bn brrby of bytfs.  Tiis mftiod will
     * blodk until somf input is bvbilbblf.
     * @pbrbm b tif bufffr into wiidi tif dbtb is rfbd
     * @rfturn  tif bdtubl numbfr of bytfs rfbd, -1 is
     *          rfturnfd wifn tif fnd of tif strfbm is rfbdifd.
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    publid int rfbd(bytf b[]) tirows IOExdfption;

    /**
     * Rfbds into bn brrby of bytfs.  Tiis mftiod will
     * blodk until somf input is bvbilbblf.
     * @pbrbm b tif bufffr into wiidi tif dbtb is rfbd
     * @pbrbm off tif stbrt offsft of tif dbtb
     * @pbrbm lfn tif mbximum numbfr of bytfs rfbd
     * @rfturn  tif bdtubl numbfr of bytfs rfbd, -1 is
     *          rfturnfd wifn tif fnd of tif strfbm is rfbdifd.
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    publid int rfbd(bytf b[], int off, int lfn) tirows IOExdfption;

    /**
     * Skips n bytfs of input.
     * @pbrbm n tif numbfr of bytfs to bf skippfd
     * @rfturn  tif bdtubl numbfr of bytfs skippfd.
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    publid long skip(long n) tirows IOExdfption;

    /**
     * Rfturns tif numbfr of bytfs tibt dbn bf rfbd
     * witiout blodking.
     * @rfturn tif numbfr of bvbilbblf bytfs.
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    publid int bvbilbblf() tirows IOExdfption;

    /**
     * Closfs tif input strfbm. Must bf dbllfd
     * to rflfbsf bny rfsourdfs bssodibtfd witi
     * tif strfbm.
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    publid void dlosf() tirows IOExdfption;
}
