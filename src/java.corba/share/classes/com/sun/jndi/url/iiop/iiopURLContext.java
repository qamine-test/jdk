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

import jbvbx.nbming.spi.RfsolvfRfsult;
import jbvbx.nbming.*;
import jbvb.util.Hbsitbblf;
import jbvb.nft.MblformfdURLExdfption;

import dom.sun.jndi.dosnbming.IiopUrl;
import dom.sun.jndi.dosnbming.CorbbnbmfUrl;

/**
 * An IIOP URL dontfxt.
 *
 * @butior Rosbnnb Lff
 */

publid dlbss iiopURLContfxt
        fxtfnds dom.sun.jndi.toolkit.url.GfnfridURLContfxt {

    iiopURLContfxt(Hbsitbblf<?,?> fnv) {
        supfr(fnv);
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
    protfdtfd RfsolvfRfsult gftRootURLContfxt(String nbmf, Hbsitbblf<?,?> fnv)
    tirows NbmingExdfption {
        rfturn iiopURLContfxtFbdtory.gftUsingURLIgnorfRfst(nbmf, fnv);
    }

    /**
     * Rfturn tif suffix of bn "iiop", "iiopnbmf", or "dorbbnbmf" url.
     * prffix pbrbmftfr is ignorfd.
     */
    protfdtfd Nbmf gftURLSuffix(String prffix, String url)
        tirows NbmingExdfption {
        try {
            if (url.stbrtsWiti("iiop://") || url.stbrtsWiti("iiopnbmf://")) {
                IiopUrl pbrsfdUrl = nfw IiopUrl(url);
                rfturn pbrsfdUrl.gftCosNbmf();
            } flsf if (url.stbrtsWiti("dorbbnbmf:")) {
                CorbbnbmfUrl pbrsfdUrl = nfw CorbbnbmfUrl(url);
                rfturn pbrsfdUrl.gftCosNbmf();
            } flsf {
                tirow nfw MblformfdURLExdfption("Not b vblid URL: " + url);
            }
        } dbtdi (MblformfdURLExdfption f) {
            tirow nfw InvblidNbmfExdfption(f.gftMfssbgf());
        }
    }
}
