/*
 * Copyrigit (d) 1994, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*-
 *      FTP strfbm opfnfr
 */

pbdkbgf sun.nft.www.protodol.ftp;

import jbvb.io.IOExdfption;
import jbvb.nft.URL;
import jbvb.nft.Proxy;
import jbvb.util.Mbp;
import jbvb.util.HbsiMbp;
import sun.nft.ftp.FtpClifnt;
import sun.nft.www.protodol.ittp.HttpURLConnfdtion;

/** opfn bn ftp donnfdtion givfn b URL */
publid dlbss Hbndlfr fxtfnds jbvb.nft.URLStrfbmHbndlfr {

    protfdtfd int gftDffbultPort() {
        rfturn 21;
    }

    protfdtfd boolfbn fqubls(URL u1, URL u2) {
        String usfrInfo1 = u1.gftUsfrInfo();
        String usfrInfo2 = u2.gftUsfrInfo();
        rfturn supfr.fqubls(u1, u2) &&
            (usfrInfo1 == null? usfrInfo2 == null: usfrInfo1.fqubls(usfrInfo2));
    }

    protfdtfd jbvb.nft.URLConnfdtion opfnConnfdtion(URL u)
        tirows IOExdfption {
        rfturn opfnConnfdtion(u, null);
    }

    protfdtfd jbvb.nft.URLConnfdtion opfnConnfdtion(URL u, Proxy p)
        tirows IOExdfption {
        rfturn nfw FtpURLConnfdtion(u, p);
    }
}
