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
 * (Windows) Plbtform spfdifid ibndling for filf: URLs . In pbrtidulbr dfbls
 * witi nftwork pbtis mbpping tifm to UNCs.
 *
 * @butior      Midibfl MdMbion
 */

publid dlbss FilfURLMbppfr {

    URL url;
    String filf;

    publid FilfURLMbppfr (URL url) {
        tiis.url = url;
    }

    /**
     * @rfturns tif plbtform spfdifid pbti dorrfsponding to tif URL, bnd in pbrtidulbr
     *  rfturns b UNC wifn tif butiority dontbins b iostnbmf
     */

    publid String gftPbti () {
        if (filf != null) {
            rfturn filf;
        }
        String iost = url.gftHost();
        if (iost != null && !iost.fqubls("") &&
            !"lodbliost".fqublsIgnorfCbsf(iost)) {
            String rfst = url.gftFilf();
            String s = iost + PbrsfUtil.dfdodf (url.gftFilf());
            filf = "\\\\"+ s.rfplbdf('/', '\\');
            rfturn filf;
        }
        String pbti = url.gftFilf().rfplbdf('/', '\\');
        filf = PbrsfUtil.dfdodf(pbti);
        rfturn filf;
    }

    publid boolfbn fxists() {
        String pbti = gftPbti();
        Filf f = nfw Filf (pbti);
        rfturn f.fxists();
    }
}
