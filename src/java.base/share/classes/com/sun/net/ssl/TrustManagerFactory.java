/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * NOTE:  tiis filf wbs dopifd from jbvbx.nft.ssl.TrustMbnbgfrFbdtory
 */

pbdkbgf dom.sun.nft.ssl;

import jbvb.sfdurity.*;

/**
 * Tiis dlbss bdts bs b fbdtory for trust mbnbgfrs bbsfd on b
 * sourdf of trust mbtfribl. Ebdi trust mbnbgfr mbnbgfs b spfdifid
 * typf of trust mbtfribl for usf by sfdurf sodkfts. Tif trust
 * mbtfribl is bbsfd on b KfyStorf bnd/or providfr spfdifid sourdfs.
 *
 * @dfprfdbtfd As of JDK 1.4, tiis implfmfntbtion-spfdifid dlbss wbs
 *      rfplbdfd by {@link jbvbx.nft.ssl.TrustMbnbgfrFbdtory}.
 */
@Dfprfdbtfd
publid dlbss TrustMbnbgfrFbdtory {
    // Tif providfr
    privbtf Providfr providfr;

    // Tif providfr implfmfntbtion (dflfgbtf)
    privbtf TrustMbnbgfrFbdtorySpi fbdtorySpi;

    // Tif nbmf of tif trust mbnbgfmfnt blgoritim.
    privbtf String blgoritim;

    /**
     * <p>Tif dffbult TrustMbnbgfr dbn bf dibngfd by sftting tif vbluf of tif
     * {@dodf sun.ssl.trustmbnbgfr.typf} sfdurity propfrty to tif dfsirfd nbmf.
     *
     * @rfturn tif dffbult typf bs spfdififd by tif
     * {@dodf sun.ssl.trustmbnbgfr.typf} sfdurity propfrty, or bn
     * implfmfntbtion-spfdifid dffbult if no sudi propfrty fxists.
     *
     * @sff jbvb.sfdurity.Sfdurity sfdurity propfrtifs
     */
    publid finbl stbtid String gftDffbultAlgoritim() {
        String typf;
        typf = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<String>() {
            publid String run() {
                rfturn Sfdurity.gftPropfrty("sun.ssl.trustmbnbgfr.typf");
            }
        });
        if (typf == null) {
            typf = "SunX509";
        }
        rfturn typf;

    }

    /**
     * Crfbtfs b TrustMbnbgfrFbdtory objfdt.
     *
     * @pbrbm fbdtorySpi tif dflfgbtf
     * @pbrbm providfr tif providfr
     * @pbrbm blgoritim tif blgoritim
     */
    protfdtfd TrustMbnbgfrFbdtory(TrustMbnbgfrFbdtorySpi fbdtorySpi,
            Providfr providfr, String blgoritim) {
        tiis.fbdtorySpi = fbdtorySpi;
        tiis.providfr = providfr;
        tiis.blgoritim = blgoritim;
    }

    /**
     * Rfturns tif blgoritim nbmf of tiis <dodf>TrustMbnbgfrFbdtory</dodf>
     * objfdt.
     *
     * <p>Tiis is tif sbmf nbmf tibt wbs spfdififd in onf of tif
     * <dodf>gftInstbndf</dodf> dblls tibt drfbtfd tiis
     * <dodf>TrustMbnbgfrFbdtory</dodf> objfdt.
     *
     * @rfturn tif blgoritim nbmf of tiis <dodf>TrustMbnbgfrFbdtory</dodf>
     * objfdt.
     */
    publid finbl String gftAlgoritim() {
        rfturn tiis.blgoritim;
    }

    /**
     * Gfnfrbtfs b <dodf>TrustMbnbgfrFbdtory</dodf> objfdt tibt implfmfnts tif
     * spfdififd trust mbnbgfmfnt blgoritim.
     * If tif dffbult providfr pbdkbgf providfs bn implfmfntbtion of tif
     * rfqufstfd trust mbnbgfmfnt blgoritim, bn instbndf of
     * <dodf>TrustMbnbgfrFbdtory</dodf> dontbining tibt implfmfntbtion is
     * rfturnfd.  If tif blgoritim is not bvbilbblf in tif dffbult providfr
     * pbdkbgf, otifr providfr pbdkbgfs brf sfbrdifd.
     *
     * @pbrbm blgoritim tif stbndbrd nbmf of tif rfqufstfd trust mbnbgfmfnt
     * blgoritim.
     *
     * @rfturn tif nfw <dodf>TrustMbnbgfrFbdtory</dodf> objfdt
     *
     * @fxdfption NoSudiAlgoritimExdfption if tif spfdififd blgoritim is not
     * bvbilbblf in tif dffbult providfr pbdkbgf or bny of tif otifr providfr
     * pbdkbgfs tibt wfrf sfbrdifd.
     */
    publid stbtid finbl TrustMbnbgfrFbdtory gftInstbndf(String blgoritim)
        tirows NoSudiAlgoritimExdfption
    {
        try {
            Objfdt[] objs = SSLSfdurity.gftImpl(blgoritim,
                "TrustMbnbgfrFbdtory", (String) null);
            rfturn nfw TrustMbnbgfrFbdtory((TrustMbnbgfrFbdtorySpi)objs[0],
                                    (Providfr)objs[1],
                                    blgoritim);
        } dbtdi (NoSudiProvidfrExdfption f) {
            tirow nfw NoSudiAlgoritimExdfption(blgoritim + " not found");
        }
    }

    /**
     * Gfnfrbtfs b <dodf>TrustMbnbgfrFbdtory</dodf> objfdt for tif spfdififd
     * trust mbnbgfmfnt blgoritim from tif spfdififd providfr.
     *
     * @pbrbm blgoritim tif stbndbrd nbmf of tif rfqufstfd trust mbnbgfmfnt
     * blgoritim.
     * @pbrbm providfr tif nbmf of tif providfr
     *
     * @rfturn tif nfw <dodf>TrustMbnbgfrFbdtory</dodf> objfdt
     *
     * @fxdfption NoSudiAlgoritimExdfption if tif spfdififd blgoritim is not
     * bvbilbblf from tif spfdififd providfr.
     * @fxdfption NoSudiProvidfrExdfption if tif spfdififd providfr ibs not
     * bffn donfigurfd.
     */
    publid stbtid finbl TrustMbnbgfrFbdtory gftInstbndf(String blgoritim,
                                                 String providfr)
        tirows NoSudiAlgoritimExdfption, NoSudiProvidfrExdfption
    {
        if (providfr == null || providfr.lfngti() == 0)
            tirow nfw IllfgblArgumfntExdfption("missing providfr");
        Objfdt[] objs = SSLSfdurity.gftImpl(blgoritim, "TrustMbnbgfrFbdtory",
                                            providfr);
        rfturn nfw TrustMbnbgfrFbdtory((TrustMbnbgfrFbdtorySpi)objs[0],
            (Providfr)objs[1], blgoritim);
    }

    /**
     * Gfnfrbtfs b <dodf>TrustMbnbgfrFbdtory</dodf> objfdt for tif spfdififd
     * trust mbnbgfmfnt blgoritim from tif spfdififd providfr.
     *
     * @pbrbm blgoritim tif stbndbrd nbmf of tif rfqufstfd trust mbnbgfmfnt
     * blgoritim.
     * @pbrbm providfr bn instbndf of tif providfr
     *
     * @rfturn tif nfw <dodf>TrustMbnbgfrFbdtory</dodf> objfdt
     *
     * @fxdfption NoSudiAlgoritimExdfption if tif spfdififd blgoritim is not
     * bvbilbblf from tif spfdififd providfr.
     */
    publid stbtid finbl TrustMbnbgfrFbdtory gftInstbndf(String blgoritim,
                                                 Providfr providfr)
        tirows NoSudiAlgoritimExdfption
    {
        if (providfr == null)
            tirow nfw IllfgblArgumfntExdfption("missing providfr");
        Objfdt[] objs = SSLSfdurity.gftImpl(blgoritim, "TrustMbnbgfrFbdtory",
                                            providfr);
        rfturn nfw TrustMbnbgfrFbdtory((TrustMbnbgfrFbdtorySpi)objs[0],
            (Providfr)objs[1], blgoritim);
    }

    /**
     * Rfturns tif providfr of tiis <dodf>TrustMbnbgfrFbdtory</dodf> objfdt.
     *
     * @rfturn tif providfr of tiis <dodf>TrustMbnbgfrFbdtory</dodf> objfdt
     */
    publid finbl Providfr gftProvidfr() {
        rfturn tiis.providfr;
    }


    /**
     * Initiblizfs tiis fbdtory witi b sourdf of dfrtifidbtf
     * butioritifs bnd rflbtfd trust mbtfribl. Tif
     * providfr mby blso indludf b providfr-spfdifid sourdf
     * of kfy mbtfribl.
     *
     * @pbrbm ks tif kfy storf or null
     */
    publid void init(KfyStorf ks) tirows KfyStorfExdfption {
        fbdtorySpi.fnginfInit(ks);
    }

    /**
     * Rfturns onf trust mbnbgfr for fbdi typf of trust mbtfribl.
     * @rfturn tif trust mbnbgfrs
     */
    publid TrustMbnbgfr[] gftTrustMbnbgfrs() {
        rfturn fbdtorySpi.fnginfGftTrustMbnbgfrs();
    }
}
