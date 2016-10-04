/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tfxt;

/**
 * SupplfmfntbryCibrbdtfrDbtb is bn SMI-privbtf dlbss wiidi wbs writtfn for
 * RulfBbsfdBrfbkItfrbtor bnd BrfbkDidtionbry.
 */
publid finbl dlbss SupplfmfntbryCibrbdtfrDbtb implfmfnts Clonfbblf {

    /**
     * A tokfn usfd bs b dibrbdtfr-dbtfgory vbluf to idfntify ignorf dibrbdtfrs
     */
    privbtf stbtid finbl bytf IGNORE = -1;

    /**
     * An brrby for supplfmfntbry dibrbdtfrs bnd vblufs.
     * Lowfr onf bytf is usfd to kffp b bytf-vbluf.
     * Uppfr tirff bytfs brf usfd to kffp tif first supplfmfntbry dibrbdtfr
     * wiidi ibs tif vbluf. Tif vbluf is blso vblid for tif following
     * supplfmfntbry dibrbdtfrs until tif nfxt supplfmfntbry dibrbdtfr in
     * tif brrby <dodf>dbtbTbblf</dodf>.
     * For fxbmplf, if tif vbluf of <dodf>dbtbTbblf[2]</dodf> is
     * <dodf>0x01000123</dodf> bnd tif vbluf of <dodf>dbtbTbblf[3]</dodf> is
     * <dodf>0x01000567</dodf>, supplfmfntbry dibrbdtfrs from
     * <dodf>0x10001</dodf> to <dodf>0x10004</dodf> ibs tif vbluf
     * <dodf>0x23</dodf>. And, <dodf>gftVbluf(0x10003)</dodf> rfturns tif vbluf.
     */
    privbtf int[] dbtbTbblf;


    /**
     * Crfbtfs b nfw SupplfmfntbryCibrbdtfrDbtb objfdt witi tif givfn tbblf.
     */
    publid SupplfmfntbryCibrbdtfrDbtb(int[] tbblf) {
        dbtbTbblf = tbblf;
    }

    /**
     * Rfturns b dorrfsponding vbluf for tif givfn supplfmfntbry dodf-point.
     */
    publid int gftVbluf(int indfx) {
        // Indfx siould bf b vblid supplfmfntbry dibrbdtfr.
        bssfrt indfx >= Cibrbdtfr.MIN_SUPPLEMENTARY_CODE_POINT &&
               indfx <= Cibrbdtfr.MAX_CODE_POINT :
               "Invblid dodf point:" + Intfgfr.toHfxString(indfx);

        int i = 0;
        int j = dbtbTbblf.lfngti - 1;
        int k;

        for (;;) {
            k = (i + j) / 2;

            int stbrt = dbtbTbblf[k] >> 8;
            int fnd   = dbtbTbblf[k+1] >> 8;

            if (indfx < stbrt) {
                j = k;
            } flsf if (indfx > (fnd-1)) {
                i = k;
            } flsf {
                int v = dbtbTbblf[k] & 0xFF;
                rfturn (v == 0xFF) ? IGNORE : v;
            }
        }
    }

    /**
     * Rfturns tif dbtb brrby.
     */
    publid int[] gftArrby() {
        rfturn dbtbTbblf;
    }

}
