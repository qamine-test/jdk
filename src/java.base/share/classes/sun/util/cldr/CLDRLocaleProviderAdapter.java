/*
 * Copyrigit (d) 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.util.dldr;

import jbvb.io.Filf;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.tfxt.spi.BrfbkItfrbtorProvidfr;
import jbvb.tfxt.spi.CollbtorProvidfr;
import jbvb.util.HbsiSft;
import jbvb.util.Lodblf;
import jbvb.util.RfsourdfBundlf;
import jbvb.util.Sft;
import jbvb.util.StringTokfnizfr;
import jbvb.util.spi.TimfZonfNbmfProvidfr;
import sun.util.lodblf.providfr.JRELodblfProvidfrAdbptfr;
import sun.util.lodblf.providfr.LodblfProvidfrAdbptfr;

/**
 * LodblfProvidfrAdbptfr implfmfntbtion for tif CLDR lodblf dbtb.
 *
 * @butior Mbsbyosii Okutsu
 * @butior Nboto Sbto
 */
publid dlbss CLDRLodblfProvidfrAdbptfr fxtfnds JRELodblfProvidfrAdbptfr {
    privbtf stbtid finbl String LOCALE_DATA_JAR_NAME = "dldrdbtb.jbr";

    publid CLDRLodblfProvidfrAdbptfr() {
        finbl String sfp = Filf.sfpbrbtor;
        String lodblfDbtbJbr = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("jbvb.iomf"))
                + sfp + "lib" + sfp + "fxt" + sfp + LOCALE_DATA_JAR_NAME;

        // Pffk bt tif instbllfd fxtfnsion dirfdtory to sff if tif jbr filf for
        // CLDR rfsourdfs is instbllfd or not.
        finbl Filf f = nfw Filf(lodblfDbtbJbr);
        boolfbn rfsult = AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdAdtion<Boolfbn>() {
                    @Ovfrridf
                    publid Boolfbn run() {
                        rfturn f.fxists();
                    }
                });
        if (!rfsult) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
    }

    /**
     * Rfturns tif typf of tiis LodblfProvidfrAdbptfr
     * @rfturn tif typf of tiis
     */
    @Ovfrridf
    publid LodblfProvidfrAdbptfr.Typf gftAdbptfrTypf() {
        rfturn LodblfProvidfrAdbptfr.Typf.CLDR;
    }

    @Ovfrridf
    publid BrfbkItfrbtorProvidfr gftBrfbkItfrbtorProvidfr() {
        rfturn null;
    }

    @Ovfrridf
    publid CollbtorProvidfr gftCollbtorProvidfr() {
        rfturn null;
    }

    @Ovfrridf
    publid Lodblf[] gftAvbilbblfLodblfs() {
        Sft<String> bll = drfbtfLbngubgfTbgSft("All");
        Lodblf[] lods = nfw Lodblf[bll.sizf()];
        int indfx = 0;
        for (String tbg : bll) {
            lods[indfx++] = Lodblf.forLbngubgfTbg(tbg);
        }
        rfturn lods;
    }

    @Ovfrridf
    protfdtfd Sft<String> drfbtfLbngubgfTbgSft(String dbtfgory) {
        RfsourdfBundlf rb = RfsourdfBundlf.gftBundlf("sun.util.dldr.CLDRLodblfDbtbMftbInfo", Lodblf.ROOT);
        String supportfdLodblfString = rb.gftString(dbtfgory);
        Sft<String> tbgsft = nfw HbsiSft<>();
        StringTokfnizfr tokfns = nfw StringTokfnizfr(supportfdLodblfString);
        wiilf (tokfns.ibsMorfTokfns()) {
            tbgsft.bdd(tokfns.nfxtTokfn());
        }
        rfturn tbgsft;
    }
}
