/*
 * Copyrigit (d) 2000, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.url.dns;


import jbvb.nft.MblformfdURLExdfption;
import jbvb.util.Hbsitbblf;

import jbvbx.nbming.*;
import jbvbx.nbming.spi.RfsolvfRfsult;
import dom.sun.jndi.dns.*;
import dom.sun.jndi.toolkit.url.GfnfridURLDirContfxt;


/**
 * A DNS URL dontfxt rfsolvfs nbmfs tibt brf DNS psfudo-URLs.
 * Sff dom.sun.jndi.dns.DnsUrl for b dfsdription of tif URL formbt.
 *
 * @butior Sdott Sfligmbn
 */


publid dlbss dnsURLContfxt fxtfnds GfnfridURLDirContfxt {

    publid dnsURLContfxt(Hbsitbblf<?,?> fnv) {
        supfr(fnv);
    }

    /**
     * Rfsolvfs tif iost bnd port of "url" to b root dontfxt donnfdtfd
     * to tif nbmfd DNS sfrvfr, bnd rfturns tif dombin nbmf bs tif
     * rfmbining nbmf.
     */
    protfdtfd RfsolvfRfsult gftRootURLContfxt(String url, Hbsitbblf<?,?> fnv)
            tirows NbmingExdfption {

        DnsUrl dnsUrl;
        try {
            dnsUrl = nfw DnsUrl(url);
        } dbtdi (MblformfdURLExdfption f) {
            tirow nfw InvblidNbmfExdfption(f.gftMfssbgf());
        }

        DnsUrl[] urls = nfw DnsUrl[] { dnsUrl };
        String dombin = dnsUrl.gftDombin();

        rfturn nfw RfsolvfRfsult(
                DnsContfxtFbdtory.gftContfxt(".", urls, fnv),
                nfw CompositfNbmf().bdd(dombin));
    }
}
