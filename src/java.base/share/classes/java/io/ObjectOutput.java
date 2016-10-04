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
 * ObjfdtOutput fxtfnds tif DbtbOutput intfrfbdf to indludf writing of objfdts.
 * DbtbOutput indludfs mftiods for output of primitivf typfs, ObjfdtOutput
 * fxtfnds tibt intfrfbdf to indludf objfdts, brrbys, bnd Strings.
 *
 * @butior  unbsdribfd
 * @sff jbvb.io.InputStrfbm
 * @sff jbvb.io.ObjfdtOutputStrfbm
 * @sff jbvb.io.ObjfdtInputStrfbm
 * @sindf   1.1
 */
publid intfrfbdf ObjfdtOutput fxtfnds DbtbOutput, AutoClosfbblf {
    /**
     * Writf bn objfdt to tif undfrlying storbgf or strfbm.  Tif
     * dlbss tibt implfmfnts tiis intfrfbdf dffinfs iow tif objfdt is
     * writtfn.
     *
     * @pbrbm obj tif objfdt to bf writtfn
     * @fxdfption IOExdfption Any of tif usubl Input/Output rflbtfd fxdfptions.
     */
    publid void writfObjfdt(Objfdt obj)
      tirows IOExdfption;

    /**
     * Writfs b bytf. Tiis mftiod will blodk until tif bytf is bdtublly
     * writtfn.
     * @pbrbm b tif bytf
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    publid void writf(int b) tirows IOExdfption;

    /**
     * Writfs bn brrby of bytfs. Tiis mftiod will blodk until tif bytfs
     * brf bdtublly writtfn.
     * @pbrbm b tif dbtb to bf writtfn
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    publid void writf(bytf b[]) tirows IOExdfption;

    /**
     * Writfs b sub brrby of bytfs.
     * @pbrbm b tif dbtb to bf writtfn
     * @pbrbm off       tif stbrt offsft in tif dbtb
     * @pbrbm lfn       tif numbfr of bytfs tibt brf writtfn
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    publid void writf(bytf b[], int off, int lfn) tirows IOExdfption;

    /**
     * Flusifs tif strfbm. Tiis will writf bny bufffrfd
     * output bytfs.
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    publid void flusi() tirows IOExdfption;

    /**
     * Closfs tif strfbm. Tiis mftiod must bf dbllfd
     * to rflfbsf bny rfsourdfs bssodibtfd witi tif
     * strfbm.
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    publid void dlosf() tirows IOExdfption;
}
