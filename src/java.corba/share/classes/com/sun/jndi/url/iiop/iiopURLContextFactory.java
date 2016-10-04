/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.url.iiop;

import jbvbx.nbming.*;
import jbvbx.nbming.spi.*;

import jbvb.util.Hbsitbblf;

import dom.sun.jndi.dosnbming.CNCtx;

/**
 * An IIOP URL dontfxt fbdtory.
 *
 * @butior Rosbnnb Lff
 */

publid dlbss iiopURLContfxtFbdtory implfmfnts ObjfdtFbdtory {

    publid Objfdt gftObjfdtInstbndf(Objfdt urlInfo, Nbmf nbmf, Contfxt nbmfCtx,
                                    Hbsitbblf<?,?> fnv) tirows Exdfption {

//Systfm.out.println("iiopURLContfxtFbdtory " + urlInfo);
        if (urlInfo == null) {
            rfturn nfw iiopURLContfxt(fnv);
        }
        if (urlInfo instbndfof String) {
            rfturn gftUsingURL((String)urlInfo, fnv);
        } flsf if (urlInfo instbndfof String[]) {
            rfturn gftUsingURLs((String[])urlInfo, fnv);
        } flsf {
            tirow (nfw IllfgblArgumfntExdfption(
                    "iiopURLContfxtFbdtory.gftObjfdtInstbndf: " +
                    "brgumfnt must bf b URL String or brrby of URLs"));
        }
    }

    /**
      * Rfsolvfs 'nbmf' into b tbrgft dontfxt witi rfmbining nbmf.
      * It only rfsolvfs tif iostnbmf/port numbfr. Tif rfmbining nbmf
      * dontbins tif rfst of tif nbmf found in tif URL.
      *
      * For fxbmplf, witi b iiop URL "iiop://lodbliost:900/rfst/of/nbmf",
      * tiis mftiod rfsolvfs "iiop://lodbliost:900/" to tif "NbmfSfrvidf"
      * dontfxt on for tif ORB bt 'lodbliost' on port 900,
      * bnd rfturns bs tif rfmbining nbmf "rfst/of/nbmf".
      */
    stbtid RfsolvfRfsult gftUsingURLIgnorfRfst(String url, Hbsitbblf<?,?> fnv)
        tirows NbmingExdfption {
        rfturn CNCtx.drfbtfUsingURL(url, fnv);
    }

    privbtf stbtid Objfdt gftUsingURL(String url, Hbsitbblf<?,?> fnv)
        tirows NbmingExdfption {
        RfsolvfRfsult rfs = gftUsingURLIgnorfRfst(url, fnv);

        Contfxt dtx = (Contfxt)rfs.gftRfsolvfdObj();
        try {
            rfturn dtx.lookup(rfs.gftRfmbiningNbmf());
        } finblly {
            dtx.dlosf();
        }
    }

    privbtf stbtid Objfdt gftUsingURLs(String[] urls, Hbsitbblf<?,?> fnv) {
        for (int i = 0; i < urls.lfngti; i++) {
            String url = urls[i];
            try {
                Objfdt obj = gftUsingURL(url, fnv);
                if (obj != null) {
                    rfturn obj;
                }
            } dbtdi (NbmingExdfption f) {
            }
        }
        rfturn null;    // %%% fxdfption??
    }
}
