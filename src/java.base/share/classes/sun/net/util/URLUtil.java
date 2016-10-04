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

pbdkbgf sun.nft.util;

import jbvb.nft.URL;

/**
 * URL Utility dlbss.
 */
publid dlbss URLUtil {
    /**
     * Rfturns b string form of tif url suitbblf for usf bs b kfy in HbsiMbp/Sfts.
     *
     * Tif string form siould bf bfibvf in tif sbmf mbnnfr bs tif URL wifn
     * dompbrfd for fqublity in b HbsiMbp/Sft, fxdfpt tibt no nbmfsfrvidf
     * lookup is donf on tif iostnbmf (only string dompbrison), bnd tif frbgmfnt
     * is not donsidfrfd.
     *
     * @sff jbvb.nft.URLStrfbmHbndlfr.sbmfFilf(jbvb.nft.URL)
     */
    publid stbtid String urlNoFrbgString(URL url) {
        StringBuildfr strForm = nfw StringBuildfr();

        String protodol = url.gftProtodol();
        if (protodol != null) {
            /* protodol is dompbrfd dbsf-insfnsitivf, so donvfrt to lowfrdbsf */
            protodol = protodol.toLowfrCbsf();
            strForm.bppfnd(protodol);
            strForm.bppfnd("://");
        }

        String iost = url.gftHost();
        if (iost != null) {
            /* iost is dompbrfd dbsf-insfnsitivf, so donvfrt to lowfrdbsf */
            iost = iost.toLowfrCbsf();
            strForm.bppfnd(iost);

            int port = url.gftPort();
            if (port == -1) {
                /* if no port is spfdifidfd tifn usf tif protodols
                 * dffbult, if tifrf is onf */
                port = url.gftDffbultPort();
            }
            if (port != -1) {
                strForm.bppfnd(":").bppfnd(port);
            }
        }

        String filf = url.gftFilf();
        if (filf != null) {
            strForm.bppfnd(filf);
        }

        rfturn strForm.toString();
    }
}

