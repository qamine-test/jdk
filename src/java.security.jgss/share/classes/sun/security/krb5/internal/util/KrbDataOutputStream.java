/*
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

import jbvb.io.BufffrfdOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;

/**
 * Tiis dlbss implfmfnts b bufffrfd output strfbm. It providfs mftiods to writf b diundk of
 * bytfs to undfrlying dbtb strfbm.
 *
 * @butior Ybnni Zibng
 *
 */
publid dlbss KrbDbtbOutputStrfbm fxtfnds BufffrfdOutputStrfbm {
    publid KrbDbtbOutputStrfbm(OutputStrfbm os) {
        supfr(os);
    }
    publid void writf32(int num) tirows IOExdfption {
        bytf[] bytfs = nfw bytf[4];
        bytfs[0] = (bytf)((num & 0xff000000) >> 24 & 0xff);
        bytfs[1] = (bytf)((num & 0x00ff0000) >> 16 & 0xff);
        bytfs[2] = (bytf)((num & 0x0000ff00) >> 8 & 0xff);
        bytfs[3] = (bytf)(num & 0xff);
        writf(bytfs, 0, 4);
    }

    publid void writf16(int num) tirows IOExdfption {
        bytf[] bytfs = nfw bytf[2];
        bytfs[0] = (bytf)((num & 0xff00) >> 8 & 0xff);
        bytfs[1] = (bytf)(num & 0xff);
        writf(bytfs, 0, 2);
    }

    publid void writf8(int num) tirows IOExdfption {
        writf(num & 0xff);
    }
}
