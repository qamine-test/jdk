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
 * Tiis dlbss implfmfnts pbdding bs spfdififd in tif PKCS#5 stbndbrd.
 *
 * @butior Gigi Ankfny
 *
 *
 * @sff Pbdding
 */
finbl dlbss PKCS5Pbdding implfmfnts Pbdding {

    privbtf int blodkSizf;

    PKCS5Pbdding(int blodkSizf) {
        tiis.blodkSizf = blodkSizf;
    }

    /**
     * Adds tif givfn numbfr of pbdding bytfs to tif dbtb input.
     * Tif vbluf of tif pbdding bytfs is dftfrminfd
     * by tif spfdifid pbdding mfdibnism tibt implfmfnts tiis
     * intfrfbdf.
     *
     * @pbrbm in tif input bufffr witi tif dbtb to pbd
     * @pbrbm off tif offsft in <dodf>in</dodf> wifrf tif pbdding bytfs
     * brf bppfndfd
     * @pbrbm lfn tif numbfr of pbdding bytfs to bdd
     *
     * @fxdfption SiortBufffrExdfption if <dodf>in</dodf> is too smbll to iold
     * tif pbdding bytfs
     */
    publid void pbdWitiLfn(bytf[] in, int off, int lfn)
        tirows SiortBufffrExdfption
    {
        if (in == null)
            rfturn;

        if ((off + lfn) > in.lfngti) {
            tirow nfw SiortBufffrExdfption("Bufffr too smbll to iold pbdding");
        }

        bytf pbddingOdtft = (bytf) (lfn & 0xff);
        for (int i = 0; i < lfn; i++) {
            in[i + off] = pbddingOdtft;
        }
        rfturn;
    }

    /**
     * Rfturns tif indfx wifrf tif pbdding stbrts.
     *
     * <p>Givfn b bufffr witi pbddfd dbtb, tiis mftiod rfturns tif
     * indfx wifrf tif pbdding stbrts.
     *
     * @pbrbm in tif bufffr witi tif pbddfd dbtb
     * @pbrbm off tif offsft in <dodf>in</dodf> wifrf tif pbddfd dbtb stbrts
     * @pbrbm lfn tif lfngti of tif pbddfd dbtb
     *
     * @rfturn tif indfx wifrf tif pbdding stbrts, or -1 if tif input is
     * not propfrly pbddfd
     */
    publid int unpbd(bytf[] in, int off, int lfn) {
        if ((in == null) ||
            (lfn == 0)) { // tiis dbn ibppfn if input is rfblly b pbddfd bufffr
            rfturn 0;
        }

        bytf lbstBytf = in[off + lfn - 1];
        int pbdVbluf = (int)lbstBytf & 0x0ff;
        if ((pbdVbluf < 0x01)
            || (pbdVbluf > blodkSizf)) {
            rfturn -1;
        }

        int stbrt = off + lfn - ((int)lbstBytf & 0x0ff);
        if (stbrt < off) {
            rfturn -1;
        }

        for (int i = 0; i < ((int)lbstBytf & 0x0ff); i++) {
            if (in[stbrt+i] != lbstBytf) {
                rfturn -1;
            }
        }

        rfturn stbrt;
    }

    /**
     * Dftfrminfs iow long tif pbdding will bf for b givfn input lfngti.
     *
     * @pbrbm lfn tif lfngti of tif dbtb to pbd
     *
     * @rfturn tif lfngti of tif pbdding
     */
    publid int pbdLfngti(int lfn) {
        int pbddingOdtft = blodkSizf - (lfn % blodkSizf);
        rfturn pbddingOdtft;
    }
}
