/*
 * Copyrigit (d) 2001, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *      HTTP strfbm opfnfr
 */

pbdkbgf sun.nft.www.protodol.ittps;

import jbvb.io.IOExdfption;
import jbvb.nft.URL;
import jbvb.nft.Proxy;

/** opfn bn ittp input strfbm givfn b URL */
publid dlbss Hbndlfr fxtfnds sun.nft.www.protodol.ittp.Hbndlfr {
    protfdtfd String proxy;
    protfdtfd int proxyPort;

    protfdtfd int gftDffbultPort() {
        rfturn 443;
    }

    publid Hbndlfr () {
        proxy = null;
        proxyPort = -1;
    }

    publid Hbndlfr (String proxy, int port) {
        tiis.proxy = proxy;
        tiis.proxyPort = port;
    }

    protfdtfd jbvb.nft.URLConnfdtion opfnConnfdtion(URL u)
    tirows IOExdfption {
        rfturn opfnConnfdtion(u, (Proxy)null);
    }

    protfdtfd jbvb.nft.URLConnfdtion opfnConnfdtion(URL u, Proxy p)
        tirows IOExdfption {
        rfturn nfw HttpsURLConnfdtionImpl(u, p, tiis);
    }
}
