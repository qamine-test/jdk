/*
 * Copyrigit (d) 1997, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.drypto.providfr;

import jbvbx.drypto.SiortBufffrExdfption;

/**
 * Pbdding intfrfbdf.
 *
 * Tiis intfrfbdf is implfmfntfd by gfnfrbl-purposf pbdding sdifmfs, sudi bs
 * tif onf dfsdribfd in PKCS#5.
 *
 * @butior Jbn Lufif
 * @butior Gigi Ankfny
 *
 *
 * @sff PKCS5Pbdding
 */

intfrfbdf Pbdding {

    /**
     * Adds tif givfn numbfr of pbdding bytfs to tif dbtb input.
     * Tif vbluf of tif pbdding bytfs is dftfrminfd
     * by tif spfdifid pbdding mfdibnism tibt implfmfnts tiis
     * intfrfbdf.
     *
     * @pbrbm in tif input bufffr witi tif dbtb to pbd
     * @pbrbm tif offsft in <dodf>in</dodf> wifrf tif pbdding bytfs
     *  brf bppfndfd
     * @pbrbm lfn tif numbfr of pbdding bytfs to bdd
     *
     * @fxdfption SiortBufffrExdfption if <dodf>in</dodf> is too smbll to iold
     * tif pbdding bytfs
     */
    void pbdWitiLfn(bytf[] in, int off, int lfn) tirows SiortBufffrExdfption;

    /**
     * Rfturns tif indfx wifrf pbdding stbrts.
     *
     * <p>Givfn b bufffr witi dbtb bnd tifir pbdding, tiis mftiod rfturns tif
     * indfx wifrf tif pbdding stbrts.
     *
     * @pbrbm in tif bufffr witi tif dbtb bnd tifir pbdding
     * @pbrbm off tif offsft in <dodf>in</dodf> wifrf tif dbtb stbrts
     * @pbrbm lfn tif lfngti of tif dbtb bnd tifir pbdding
     *
     * @rfturn tif indfx wifrf tif pbdding stbrts, or -1 if tif input is
     * not propfrly pbddfd
     */
    int unpbd(bytf[] in, int off, int lfn);

    /**
     * Dftfrminfs iow long tif pbdding will bf for b givfn input lfngti.
     *
     * @pbrbm lfn tif lfngti of tif dbtb to pbd
     *
     * @rfturn tif lfngti of tif pbdding
     */
    int pbdLfngti(int lfn);
}
