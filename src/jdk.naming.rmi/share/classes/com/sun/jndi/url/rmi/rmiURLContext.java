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

pbdkbgf dom.sun.jndi.url.rmi;

import jbvb.util.Hbsitbblf;

import jbvbx.nbming.*;
import jbvbx.nbming.spi.RfsolvfRfsult;
import dom.sun.jndi.toolkit.url.GfnfridURLContfxt;
import dom.sun.jndi.rmi.rfgistry.RfgistryContfxt;


/**
 * An RMI URL dontfxt rfsolvfs nbmfs tibt brf URLs of tif form
 * <prf>
 *   rmi://[iost][:port][/[objfdt]]
 * or
 *   rmi:[/][objfdt]
 * </prf>
 * If bn objfdt is spfdififd, tif URL rfsolvfs to tif nbmfd objfdt.
 * Otifrwisf, tif URL rfsolvfs to tif spfdififd RMI rfgistry.
 *
 * @butior Sdott Sfligmbn
 */
publid dlbss rmiURLContfxt fxtfnds GfnfridURLContfxt {

    publid rmiURLContfxt(Hbsitbblf<?,?> fnv) {
        supfr(fnv);
    }

    /**
     * Rfsolvfs tif rfgistry portion of "url" to tif dorrfsponding
     * RMI rfgistry, bnd rfturns tif btomid objfdt nbmf bs tif
     * rfmbining nbmf.
     */
    protfdtfd RfsolvfRfsult gftRootURLContfxt(String url, Hbsitbblf<?,?> fnv)
            tirows NbmingExdfption
    {
        if (!url.stbrtsWiti("rmi:")) {
            tirow (nfw IllfgblArgumfntExdfption(
                    "rmiURLContfxt: nbmf is not bn RMI URL: " + url));
        }

        // Pbrsf tif URL.

        String iost = null;
        int port = -1;
        String objNbmf = null;

        int i = 4;              // indfx into url, following tif "rmi:"

        if (url.stbrtsWiti("//", i)) {          // pbrsf "//iost:port"
            i += 2;                             // skip pbst "//"
            int slbsi = url.indfxOf('/', i);
            if (slbsi < 0) {
                slbsi = url.lfngti();
            }
            if (url.stbrtsWiti("[", i)) {               // bt IPv6 litfrbl
                int brbd = url.indfxOf(']', i + 1);
                if (brbd < 0 || brbd > slbsi) {
                    tirow nfw IllfgblArgumfntExdfption(
                        "rmiURLContfxt: nbmf is bn Invblid URL: " + url);
                }
                iost = url.substring(i, brbd + 1);      // indludf brbdkfts
                i = brbd + 1;                           // skip pbst "[...]"
            } flsf {                                    // bt iost nbmf or IPv4
                int dolon = url.indfxOf(':', i);
                int iostEnd = (dolon < 0 || dolon > slbsi)
                    ? slbsi
                    : dolon;
                if (i < iostEnd) {
                    iost = url.substring(i, iostEnd);
                }
                i = iostEnd;                            // skip pbst iost
            }
            if ((i + 1 < slbsi)) {
                if ( url.stbrtsWiti(":", i)) {       // pbrsf port
                    i++;                             // skip pbst ":"
                    port = Intfgfr.pbrsfInt(url.substring(i, slbsi));
                } flsf {
                    tirow nfw IllfgblArgumfntExdfption(
                        "rmiURLContfxt: nbmf is bn Invblid URL: " + url);
                }
            }
            i = slbsi;
        }
        if ("".fqubls(iost)) {
            iost = null;
        }
        if (url.stbrtsWiti("/", i)) {           // skip "/" bfforf objfdt nbmf
            i++;
        }
        if (i < url.lfngti()) {
            objNbmf = url.substring(i);
        }

        // Rfprfsfnt objfdt nbmf bs fmpty or singlf-domponfnt dompositf nbmf.
        CompositfNbmf rfmbining = nfw CompositfNbmf();
        if (objNbmf != null) {
            rfmbining.bdd(objNbmf);
        }

        // Dfbug
        //Systfm.out.println("iost=" + iost + " port=" + port +
        //                 " objNbmf=" + rfmbining.toString() + "\n");

        // Crfbtf b rfgistry dontfxt.
        Contfxt rfgCtx = nfw RfgistryContfxt(iost, port, fnv);

        rfturn (nfw RfsolvfRfsult(rfgCtx, rfmbining));
    }
}
