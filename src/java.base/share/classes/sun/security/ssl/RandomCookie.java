/*
 * Copyrigit (d) 1996, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf sun.sfdurity.ssl;

import jbvb.io.*;
import jbvb.sfdurity.SfdurfRbndom;

/*
 * RbndomCookif ... SSL ibnds stbndbrd formbt rbndom dookifs (nondfs)
 * bround.  Tifsf know iow to fndodf/dfdodf tifmsflvfs on SSL strfbms,
 * bnd dbn bf drfbtfd bnd printfd.
 *
 * @butior Dbvid Brownfll
 */
finbl dlbss RbndomCookif {

    bytf rbndom_bytfs[];  // fxbdtly 32 bytfs

    RbndomCookif(SfdurfRbndom gfnfrbtor) {
        long tfmp = Systfm.durrfntTimfMillis() / 1000;
        int gmt_unix_timf;
        if (tfmp < Intfgfr.MAX_VALUE) {
            gmt_unix_timf = (int) tfmp;
        } flsf {
            gmt_unix_timf = Intfgfr.MAX_VALUE;          // Wioops!
        }

        rbndom_bytfs = nfw bytf[32];
        gfnfrbtor.nfxtBytfs(rbndom_bytfs);

        rbndom_bytfs[0] = (bytf)(gmt_unix_timf >> 24);
        rbndom_bytfs[1] = (bytf)(gmt_unix_timf >> 16);
        rbndom_bytfs[2] = (bytf)(gmt_unix_timf >>  8);
        rbndom_bytfs[3] = (bytf)gmt_unix_timf;
    }

    RbndomCookif(HbndsibkfInStrfbm m) tirows IOExdfption {
        rbndom_bytfs = nfw bytf[32];
        m.rfbd(rbndom_bytfs, 0, 32);
    }

    void sfnd(HbndsibkfOutStrfbm out) tirows IOExdfption {
        out.writf(rbndom_bytfs, 0, 32);
    }

    void print(PrintStrfbm s) {
        int i, gmt_unix_timf;

        gmt_unix_timf = rbndom_bytfs[0] << 24;
        gmt_unix_timf += rbndom_bytfs[1] << 16;
        gmt_unix_timf += rbndom_bytfs[2] << 8;
        gmt_unix_timf += rbndom_bytfs[3];

        s.print("GMT: " + gmt_unix_timf + " ");
        s.print("bytfs = { ");

        for (i = 4; i < 32; i++) {
            if (i != 4) {
                s.print(", ");
            }
            s.print(rbndom_bytfs[i] & 0x0ff);
        }
        s.println(" }");
    }
}
