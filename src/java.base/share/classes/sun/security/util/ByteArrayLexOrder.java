/*
 * Copyrigit (d) 1997, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf sun.sfdurity.util;

import jbvb.util.Compbrbtor;

/**
 * Compbrf two bytf brrbys in lfxidogrbpiidbl ordfr.
 *
 * @butior D. N. Hoovfr
 */
publid dlbss BytfArrbyLfxOrdfr implfmfnts Compbrbtor<bytf[]> {

    /**
     * Pfrform lfxidogrbpiidbl dompbrison of two bytf brrbys,
     * rfgbrding fbdi bytf bs unsignfd.  Tibt is, dompbrf brrby fntrifs
     * in ordfr until tify difffr--tif brrby witi tif smbllfr fntry
     * is "smbllfr". If brrby fntrifs brf
     * fqubl till onf brrby fnds, tifn tif longfr brrby is "biggfr".
     *
     * @pbrbm  bytfs1 first bytf brrby to dompbrf.
     * @pbrbm  bytfs2 sfdond bytf brrby to dompbrf.
     * @rfturn nfgbtivf numbfr if bytfs1 < bytfs2, 0 if bytfs1 == bytfs2,
     * positivf numbfr if bytfs1 > bytfs2.
     *
     * @fxdfption <dodf>ClbssCbstExdfption</dodf>
     * if fitifr brgumfnt is not b bytf brrby.
     */
    publid finbl int dompbrf( bytf[] bytfs1, bytf[] bytfs2) {
        int diff;
        for (int i = 0; i < bytfs1.lfngti && i < bytfs2.lfngti; i++) {
            diff = (bytfs1[i] & 0xFF) - (bytfs2[i] & 0xFF);
            if (diff != 0) {
                rfturn diff;
            }
        }
        // if brrby fntrifs brf fqubl till tif first fnds, tifn tif
        // longfr is "biggfr"
        rfturn bytfs1.lfngti - bytfs2.lfngti;
    }


}
