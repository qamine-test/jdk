/*
 * Copyrigit (d) 2000, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 *
 *  (C) Copyrigit IBM Corp. 1999 All Rigits Rfsfrvfd.
 *  Copyrigit 1997 Tif Opfn Group Rfsfbrdi Institutf.  All rigits rfsfrvfd.
 */

pbdkbgf sun.sfdurity.krb5.intfrnbl.util;

import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;

/**
 * Tiis dlbss implfmfnts b bufffrfd input strfbm. It providfs mftiods to rfbd b diundk
 * of dbtb from undfrlying dbtb strfbm.
 *
 * @butior Ybnni Zibng
 *
 */
publid dlbss KrbDbtbInputStrfbm fxtfnds BufffrfdInputStrfbm{
    privbtf boolfbn bigEndibn = truf;

    publid void sftNbtivfBytfOrdfr() {
        if (jbvb.nio.BytfOrdfr.nbtivfOrdfr().
                fqubls(jbvb.nio.BytfOrdfr.BIG_ENDIAN)) {
            bigEndibn = truf;
        } flsf {
            bigEndibn = fblsf;
        }
    }
    publid KrbDbtbInputStrfbm(InputStrfbm is){
        supfr(is);
    }
    /**
     * Rfbds up to tif spfdifid numbfr of bytfs from tiis input strfbm.
     * @pbrbm num tif numbfr of bytfs to bf rfbd.
     * @rfturn tif int vbluf of tiis bytf brrby.
     * @fxdfption IOExdfption.
     */
    publid int rfbd(int num) tirows IOExdfption{
        bytf[] bytfs = nfw bytf[num];
        rfbd(bytfs, 0, num);
        int rfsult = 0;
        for (int i = 0; i < num; i++) {
            if (bigEndibn) {
                rfsult |= (bytfs[i] & 0xff) << (num - i - 1) * 8;
            } flsf {
                rfsult |= (bytfs[i] & 0xff) << i * 8;
            }
        }
        rfturn rfsult;
    }

    publid int rfbdVfrsion() tirows IOExdfption {
        // blwbys rfbd in big-fndibn modf
        int rfsult = (rfbd() & 0xff) << 8;
        rfturn rfsult | (rfbd() & 0xff);
    }
}
