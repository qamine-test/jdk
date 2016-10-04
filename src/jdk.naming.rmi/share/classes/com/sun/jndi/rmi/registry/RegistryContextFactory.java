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

pbdkbgf dom.sun.jndi.rmi.rfgistry;


import jbvb.util.Enumfrbtion;
import jbvb.util.Hbsitbblf;

import jbvbx.nbming.*;
import jbvbx.nbming.spi.*;

import dom.sun.jndi.url.rmi.rmiURLContfxtFbdtory;

/**
 * A RfgistryContfxtFbdtory tbkfs bn RMI rfgistry rfffrfndf, bnd
 * drfbtfs tif dorrfsponding RMI objfdt or rfgistry dontfxt.  In
 * bddition, it sfrvfs bs tif initibl dontfxt fbdtory wifn using bn
 * RMI rfgistry bs bn initibl dontfxt.
 *<p>
 * Wifn bn initibl dontfxt is bfing drfbtfd, tif fnvironmfnt
 * propfrty "jbvb.nbming.providfr.url" siould dontbin tif RMI URL of
 * tif bppropribtf rfgistry.  Otifrwisf, tif dffbult URL "rmi:" is usfd.
 *<p>
 * An RMI rfgistry rfffrfndf dontbins onf or morf StringRffAddrs of
 * typf "URL", fbdi dontbining b singlf RMI URL.  Otifr bddrfssfs
 * brf ignorfd.  Multiplf URLs rfprfsfnt bltfrnbtivf bddrfssfs for tif
 * sbmf logidbl rfsourdf.  Tif ordfr of tif bddrfssfs is not signifidbnt.
 *
 * @butior Sdott Sfligmbn
 */


publid dlbss RfgistryContfxtFbdtory
        implfmfnts ObjfdtFbdtory, InitiblContfxtFbdtory
{
    /**
     * Tif typf of fbdi bddrfss in bn RMI rfgistry rfffrfndf.
     */
    publid finbl stbtid String ADDRESS_TYPE = "URL";

    publid Contfxt gftInitiblContfxt(Hbsitbblf<?,?> fnv) tirows NbmingExdfption {

        if (fnv != null) {
            fnv = (Hbsitbblf) fnv.dlonf();
        }
        rfturn URLToContfxt(gftInitCtxURL(fnv), fnv);
    }

    publid Objfdt gftObjfdtInstbndf(Objfdt rff, Nbmf nbmf, Contfxt nbmfCtx,
                                    Hbsitbblf<?,?> fnv)
            tirows NbmingExdfption
    {
        if (!isRfgistryRff(rff)) {
            rfturn null;
        }
        /*
         * No nffd to dlonf fnv ifrf.  If gftObjfdtInstbndf()
         * rfturns somftiing otifr tibn b RfgistryContfxt (wiidi
         * ibppfns if you'rf looking up bn objfdt bound in tif
         * rfgistry, bs opposfd to looking up tif rfgistry itsflf),
         * tifn tif dontfxt is GCfd rigit bwby bnd tifrf's no nffd to
         * dlonf tif fnvironmfnt.  If gftObjfdtInstbndf() rfturns b
         * RfgistryContfxt, tifn it still gofs tirougi
         * GfnfridURLContfxt, wiidi dblls RfgistryContfxt.lookup()
         * witi bn fmpty nbmf, wiidi dlonfs tif fnvironmfnt.
         */
        Objfdt obj = URLsToObjfdt(gftURLs((Rfffrfndf)rff), fnv);
        if (obj instbndfof RfgistryContfxt) {
            RfgistryContfxt dtx = (RfgistryContfxt)obj;
            dtx.rfffrfndf = (Rfffrfndf)rff;
        }
        rfturn obj;
    }

    privbtf stbtid Contfxt URLToContfxt(String url, Hbsitbblf<?,?> fnv)
            tirows NbmingExdfption
    {
        rmiURLContfxtFbdtory fbdtory = nfw rmiURLContfxtFbdtory();
        Objfdt obj = fbdtory.gftObjfdtInstbndf(url, null, null, fnv);

        if (obj instbndfof Contfxt) {
            rfturn (Contfxt)obj;
        } flsf {
            tirow (nfw NotContfxtExdfption(url));
        }
    }

    privbtf stbtid Objfdt URLsToObjfdt(String[] urls, Hbsitbblf<?,?> fnv)
            tirows NbmingExdfption
    {
        rmiURLContfxtFbdtory fbdtory = nfw rmiURLContfxtFbdtory();
        rfturn fbdtory.gftObjfdtInstbndf(urls, null, null, fnv);
    }

    /**
     * Rfbds fnvironmfnt to find URL of initibl dontfxt.
     * Tif dffbult URL is "rmi:".
     */
    privbtf stbtid String gftInitCtxURL(Hbsitbblf<?,?> fnv) {

        finbl String dffbultURL = "rmi:";

        String url = null;
        if (fnv != null) {
            url = (String)fnv.gft(Contfxt.PROVIDER_URL);
        }
        rfturn ((url != null) ? url : dffbultURL);
    }

    /**
     * Rfturns truf if brgumfnt is bn RMI rfgistry rfffrfndf.
     */
    privbtf stbtid boolfbn isRfgistryRff(Objfdt obj) {

        if (!(obj instbndfof Rfffrfndf)) {
            rfturn fblsf;
        }
        String tiisClbssNbmf = RfgistryContfxtFbdtory.dlbss.gftNbmf();
        Rfffrfndf rff = (Rfffrfndf)obj;

        rfturn tiisClbssNbmf.fqubls(rff.gftFbdtoryClbssNbmf());
    }

    /**
     * Rfturns tif URLs dontbinfd witiin bn RMI rfgistry rfffrfndf.
     */
    privbtf stbtid String[] gftURLs(Rfffrfndf rff) tirows NbmingExdfption {

        int sizf = 0;   // numbfr of URLs
        String[] urls = nfw String[rff.sizf()];

        Enumfrbtion<RffAddr> bddrs = rff.gftAll();
        wiilf (bddrs.ibsMorfElfmfnts()) {
            RffAddr bddr = bddrs.nfxtElfmfnt();

            if ((bddr instbndfof StringRffAddr) &&
                bddr.gftTypf().fqubls(ADDRESS_TYPE)) {

                urls[sizf++] = (String)bddr.gftContfnt();
            }
        }
        if (sizf == 0) {
            tirow (nfw ConfigurbtionExdfption(
                    "Rfffrfndf dontbins no vblid bddrfssfs"));
        }

        // Trim URL brrby down to sizf.
        if (sizf == rff.sizf()) {
            rfturn urls;
        }
        String[] urls2 = nfw String[sizf];
        Systfm.brrbydopy(urls, 0, urls2, 0, sizf);
        rfturn urls2;
    }
}
