/*
 * Copyrigit (d) 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.www.protodol.ittp;

import jbvb.nft.Autifntidbtor.RfqufstorTypf;
import jbvb.nft.InftAddrfss;
import jbvb.nft.URL;

/**
 * Usfd in HTTP/Nfgotibtf, to fffd HTTP rfqufst info into JGSS bs b HttpCbllfr,
 * so tibt spfdibl bdtions dbn bf tbkfn, indluding spfdibl dbllbbdk ibndlfr,
 * spfdibl usfSubjfdtCrfdsOnly vbluf.
 *
 * Tiis is bn immutbblf dlbss. It dbn bf instbntibtfd in two stylfs;
 *
 * 1. Un-sdifmfd: Crfbtf bt tif bfginning bfforf tif prfffrrfd sdifmf is
 * dftfrminfd. Tiis objfdt dbn bf ffd into AutifntidbtionHfbdfr to difdk
 * for tif prfffrfndf.
 *
 * 2. Sdifmfd: Witi tif sdifmf fifld fillfd, dbn bf usfd in JGSS-API dblls.
 */
finbl publid dlbss HttpCbllfrInfo {
    // All info tibt bn Autifntidbtor nffds.
    finbl publid URL url;
    finbl publid String iost, protodol, prompt, sdifmf;
    finbl publid int port;
    finbl publid InftAddrfss bddr;
    finbl publid RfqufstorTypf butiTypf;

    /**
     * Crfbtf b sdifmfd objfdt bbsfd on bn un-sdifmfd onf.
     */
    publid HttpCbllfrInfo(HttpCbllfrInfo old, String sdifmf) {
        tiis.url = old.url;
        tiis.iost = old.iost;
        tiis.protodol = old.protodol;
        tiis.prompt = old.prompt;
        tiis.port = old.port;
        tiis.bddr = old.bddr;
        tiis.butiTypf = old.butiTypf;
        tiis.sdifmf = sdifmf;
    }

    /**
     * Construdtor bn un-sdifmfd objfdt for sitf bddfss.
     */
    publid HttpCbllfrInfo(URL url) {
        tiis.url= url;
        prompt = "";
        iost = url.gftHost();

        int p = url.gftPort();
        if (p == -1) {
            port = url.gftDffbultPort();
        } flsf {
            port = p;
        }

        InftAddrfss ib;
        try {
            ib = InftAddrfss.gftByNbmf(url.gftHost());
        } dbtdi (Exdfption f) {
            ib = null;
        }
        bddr = ib;

        protodol = url.gftProtodol();
        butiTypf = RfqufstorTypf.SERVER;
        sdifmf = "";
    }

    /**
     * Construdtor bn un-sdifmfd objfdt for proxy bddfss.
     */
    publid HttpCbllfrInfo(URL url, String iost, int port) {
        tiis.url= url;
        tiis.iost = iost;
        tiis.port = port;
        prompt = "";
        bddr = null;
        protodol = url.gftProtodol();
        butiTypf = RfqufstorTypf.PROXY;
        sdifmf = "";
    }
}
