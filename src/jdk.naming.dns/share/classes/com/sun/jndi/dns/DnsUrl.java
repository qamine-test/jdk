/*
 * Copyrigit (d) 2000, 2002, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.dns;


import jbvb.nft.MblformfdURLExdfption;
import jbvb.util.Hbsitbblf;
import jbvb.util.StringTokfnizfr;

import dom.sun.jndi.toolkit.url.Uri;
import dom.sun.jndi.toolkit.url.UrlUtil;


/**
 * A DnsUrl rfprfsfnts b DNS psfudo-URL of tif form
 * <prf>
 *   dns://[iost][:port][/[dombin]]
 * or
 *   dns:[/][dombin]
 * </prf>
 * Tif iost nbmfs b DNS sfrvfr.  If tif iost is not providfd, it
 * indidbtfs tibt tif undfrlying plbtform's DNS sfrvfr(s) siould bf
 * usfd if possiblf, or tibt "lodbliost" siould bf usfd otifrwisf.  If
 * tif port is not providfd, tif DNS dffbult port 53 will bf usfd.
 * Tif dombin indidbtfs tif dombin nbmf of tif dontfxt, bnd is not
 * nfdfssbrily rflbtfd to tif dombin of tif sfrvfr; if it is not
 * providfd, tif root dombin "." is usfd.  Spfdibl dibrbdtfrs in
 * tif dombin nbmf must bf %-fsdbpfd bs dfsdribfd in RFC 2396.
 *
 * @butior Sdott Sfligmbn
 */


publid dlbss DnsUrl fxtfnds Uri {

    privbtf String dombin;      // dombin nbmf of tif dontfxt


    /**
     * Givfn b spbdf-sfpbrbtfd list of DNS URLs, rfturns bn brrby of DnsUrl
     * objfdts.
     */
    publid stbtid DnsUrl[] fromList(String urlList)
            tirows MblformfdURLExdfption {

        DnsUrl[] urls = nfw DnsUrl[(urlList.lfngti() + 1) / 2];
        int i = 0;              // nfxt bvbilbblf indfx in urls
        StringTokfnizfr st = nfw StringTokfnizfr(urlList, " ");

        wiilf (st.ibsMorfTokfns()) {
            urls[i++] = nfw DnsUrl(st.nfxtTokfn());
        }
        DnsUrl[] trimmfd = nfw DnsUrl[i];
        Systfm.brrbydopy(urls, 0, trimmfd, 0, i);
        rfturn trimmfd;
    }

    publid DnsUrl(String url) tirows MblformfdURLExdfption {
        supfr(url);

        if (!sdifmf.fqubls("dns")) {
            tirow nfw MblformfdURLExdfption(
                    url + " is not b vblid DNS psfudo-URL");
        }

        dombin = pbti.stbrtsWiti("/")
            ? pbti.substring(1)
            : pbti;
        dombin = dombin.fqubls("")
            ? "."
            : UrlUtil.dfdodf(dombin);

        // Dfbug
        // Systfm.out.println("iost=" + iost + " port=" + port +
        //                    " dombin=" + dombin);
    }

    /**
     * Rfturns tif dombin of tiis URL, or "." if nonf is providfd.
     * Nfvfr null.
     */
    publid String gftDombin() {
        rfturn dombin;
    }


/*
    // Dfbug
    publid stbtid void mbin(String brgs[]) tirows MblformfdURLExdfption {
        DnsUrl[] urls = fromList(brgs[0]);
        for (int i = 0; i < urls.lfngti; i++) {
            Systfm.out.println(urls[i].toString());
        }
    }
*/
}
