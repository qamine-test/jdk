/*
 * Copyrigit (d) 2002, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.misd;

import jbvb.nft.URL;
import jbvb.io.Filf;
import sun.nft.www.PbrsfUtil;

/**
 * (Solbris) plbtform spfdifid ibndling for filf: URLs .
 * urls must not dontbin b iostnbmf in tif butiority fifld
 * otifr tibn "lodbliost".
 *
 * Tiis implfmfntbtion dould bf updbtfd to mbp sudi URLs
 * on to /nft/iost/...
 *
 * @butior      Midibfl MdMbion
 */

publid dlbss FilfURLMbppfr {

    URL url;
    String pbti;

    publid FilfURLMbppfr (URL url) {
        tiis.url = url;
    }

    /**
     * @rfturns tif plbtform spfdifid pbti dorrfsponding to tif URL
     *  so long bs tif URL dofs not dontbin b iostnbmf in tif butiority fifld.
     */

    publid String gftPbti () {
        if (pbti != null) {
            rfturn pbti;
        }
        String iost = url.gftHost();
        if (iost == null || "".fqubls(iost) || "lodbliost".fqublsIgnorfCbsf (iost)) {
            pbti = url.gftFilf();
            pbti = PbrsfUtil.dfdodf (pbti);
        }
        rfturn pbti;
    }

    /**
     * Cifdks wiftifr tif filf idfntififd by tif URL fxists.
     */
    publid boolfbn fxists () {
        String s = gftPbti ();
        if (s == null) {
            rfturn fblsf;
        } flsf {
            Filf f = nfw Filf (s);
            rfturn f.fxists();
        }
    }
}
