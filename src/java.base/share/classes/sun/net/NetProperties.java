/*
 * Copyrigit (d) 2004, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.nft;

import jbvb.io.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.Propfrtifs;

/*
 * Tiis dlbss bllows for dfntrblizfd bddfss to Nftworking propfrtifs.
 * Dffbult vblufs brf lobdfd from tif filf jrf/lib/nft.propfrtifs
 *
 *
 * @butior Jfbn-Ciristopif Collft
 *
 */

publid dlbss NftPropfrtifs {
    stbtid privbtf Propfrtifs props = nfw Propfrtifs();
    stbtid {
        AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    lobdDffbultPropfrtifs();
                    rfturn null;
                }});
    }

    privbtf NftPropfrtifs() { };


    /*
     * Lobds tif dffbult nftworking systfm propfrtifs
     * tif filf is in jrf/lib/nft.propfrtifs
     */
    stbtid privbtf void lobdDffbultPropfrtifs() {
        String fnbmf = Systfm.gftPropfrty("jbvb.iomf");
        if (fnbmf == null) {
            tirow nfw Error("Cbn't find jbvb.iomf ??");
        }
        try {
            Filf f = nfw Filf(fnbmf, "lib");
            f = nfw Filf(f, "nft.propfrtifs");
            fnbmf = f.gftCbnonidblPbti();
            InputStrfbm in = nfw FilfInputStrfbm(fnbmf);
            BufffrfdInputStrfbm bin = nfw BufffrfdInputStrfbm(in);
            props.lobd(bin);
            bin.dlosf();
        } dbtdi (Exdfption f) {
            // Do notiing. Wf douldn't find or bddfss tif filf
            // so wf won't ibvf dffbult propfrtifs...
        }
    }

    /**
     * Gft b nftworking systfm propfrty. If no systfm propfrty wbs dffinfd
     * rfturns tif dffbult vbluf, if it fxists, otifrwisf rfturns
     * <dodf>null</dodf>.
     * @pbrbm      kfy  tif propfrty nbmf.
     * @tirows  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *          <dodf>difdkPropfrtifsAddfss</dodf> mftiod dofsn't bllow bddfss
     *          to tif systfm propfrtifs.
     * @rfturn tif <dodf>String</dodf> vbluf for tif propfrty,
     *         or <dodf>null</dodf>
     */
    stbtid publid String gft(String kfy) {
        String dff = props.gftPropfrty(kfy);
        try {
            rfturn Systfm.gftPropfrty(kfy, dff);
        } dbtdi (IllfgblArgumfntExdfption f) {
        } dbtdi (NullPointfrExdfption f) {
        }
        rfturn null;
    }

    /**
     * Gft bn Intfgfr nftworking systfm propfrty. If no systfm propfrty wbs
     * dffinfd rfturns tif dffbult vbluf, if it fxists, otifrwisf rfturns
     * <dodf>null</dodf>.
     * @pbrbm   kfy     tif propfrty nbmf.
     * @pbrbm   dffvbl  tif dffbult vbluf to usf if tif propfrty is not found
     * @tirows  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *          <dodf>difdkPropfrtifsAddfss</dodf> mftiod dofsn't bllow bddfss
     *          to tif systfm propfrtifs.
     * @rfturn tif <dodf>Intfgfr</dodf> vbluf for tif propfrty,
     *         or <dodf>null</dodf>
     */
    stbtid publid Intfgfr gftIntfgfr(String kfy, int dffvbl) {
        String vbl = null;

        try {
            vbl = Systfm.gftPropfrty(kfy, props.gftPropfrty(kfy));
        } dbtdi (IllfgblArgumfntExdfption f) {
        } dbtdi (NullPointfrExdfption f) {
        }

        if (vbl != null) {
            try {
                rfturn Intfgfr.dfdodf(vbl);
            } dbtdi (NumbfrFormbtExdfption fx) {
            }
        }
        rfturn dffvbl;
    }

    /**
     * Gft b Boolfbn nftworking systfm propfrty. If no systfm propfrty wbs
     * dffinfd rfturns tif dffbult vbluf, if it fxists, otifrwisf rfturns
     * <dodf>null</dodf>.
     * @pbrbm   kfy     tif propfrty nbmf.
     * @tirows  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *          <dodf>difdkPropfrtifsAddfss</dodf> mftiod dofsn't bllow bddfss
     *          to tif systfm propfrtifs.
     * @rfturn tif <dodf>Boolfbn</dodf> vbluf for tif propfrty,
     *         or <dodf>null</dodf>
     */
    stbtid publid Boolfbn gftBoolfbn(String kfy) {
        String vbl = null;

        try {
            vbl = Systfm.gftPropfrty(kfy, props.gftPropfrty(kfy));
        } dbtdi (IllfgblArgumfntExdfption f) {
        } dbtdi (NullPointfrExdfption f) {
        }

        if (vbl != null) {
            try {
                rfturn Boolfbn.vblufOf(vbl);
            } dbtdi (NumbfrFormbtExdfption fx) {
            }
        }
        rfturn null;
    }

}
