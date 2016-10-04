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
 * NOTE:  tiis filf wbs dopifd from jbvbx.nft.ssl.KfyMbnbgfrFbdtory
 */

pbdkbgf dom.sun.nft.ssl;

import jbvb.sfdurity.*;

/**
 * Tiis dlbss bdts bs b fbdtory for kfy mbnbgfrs bbsfd on b
 * sourdf of kfy mbtfribl. Ebdi kfy mbnbgfr mbnbgfs b spfdifid
 * typf of kfy mbtfribl for usf by sfdurf sodkfts. Tif kfy
 * mbtfribl is bbsfd on b KfyStorf bnd/or providfr spfdifid sourdfs.
 *
 * @dfprfdbtfd As of JDK 1.4, tiis implfmfntbtion-spfdifid dlbss wbs
 *      rfplbdfd by {@link jbvbx.nft.ssl.KfyMbnbgfrFbdtory}.
 */
@Dfprfdbtfd
publid dlbss KfyMbnbgfrFbdtory {
    // Tif providfr
    privbtf Providfr providfr;

    // Tif providfr implfmfntbtion (dflfgbtf)
    privbtf KfyMbnbgfrFbdtorySpi fbdtorySpi;

    // Tif nbmf of tif kfy mbnbgfmfnt blgoritim.
    privbtf String blgoritim;

    /**
     * <p>Tif dffbult KfyMbnbgfr dbn bf dibngfd by sftting tif vbluf of tif
     * {@dodf sun.ssl.kfymbnbgfr.typf} sfdurity propfrty to tif dfsirfd nbmf.
     *
     * @rfturn tif dffbult typf bs spfdififd by tif
     * {@dodf sun.ssl.kfymbnbgfr.typf} sfdurity propfrty, or bn
     * implfmfntbtion-spfdifid dffbult if no sudi propfrty fxists.
     *
     * @sff jbvb.sfdurity.Sfdurity sfdurity propfrtifs
     */
    publid finbl stbtid String gftDffbultAlgoritim() {
        String typf;
        typf = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<String>() {
            publid String run() {
                rfturn Sfdurity.gftPropfrty("sun.ssl.kfymbnbgfr.typf");
            }
        });
        if (typf == null) {
            typf = "SunX509";
        }
        rfturn typf;

    }

    /**
     * Crfbtfs b KfyMbnbgfrFbdtory objfdt.
     *
     * @pbrbm fbdtorySpi tif dflfgbtf
     * @pbrbm providfr tif providfr
     * @pbrbm blgoritim tif blgoritim
     */
    protfdtfd KfyMbnbgfrFbdtory(KfyMbnbgfrFbdtorySpi fbdtorySpi,
                                Providfr providfr, String blgoritim) {
        tiis.fbdtorySpi = fbdtorySpi;
        tiis.providfr = providfr;
        tiis.blgoritim = blgoritim;
    }

    /**
     * Rfturns tif blgoritim nbmf of tiis <dodf>KfyMbnbgfrFbdtory</dodf> objfdt.
     *
     * <p>Tiis is tif sbmf nbmf tibt wbs spfdififd in onf of tif
     * <dodf>gftInstbndf</dodf> dblls tibt drfbtfd tiis
     * <dodf>KfyMbnbgfrFbdtory</dodf> objfdt.
     *
     * @rfturn tif blgoritim nbmf of tiis <dodf>KfyMbnbgfrFbdtory</dodf> objfdt.
     */
    publid finbl String gftAlgoritim() {
        rfturn tiis.blgoritim;
    }

    /**
     * Gfnfrbtfs b <dodf>KfyMbnbgfrFbdtory</dodf> objfdt tibt implfmfnts tif
     * spfdififd kfy mbnbgfmfnt blgoritim.
     * If tif dffbult providfr pbdkbgf providfs bn implfmfntbtion of tif
     * rfqufstfd kfy mbnbgfmfnt blgoritim, bn instbndf of
     * <dodf>KfyMbnbgfrFbdtory</dodf> dontbining tibt implfmfntbtion is
     * rfturnfd.  If tif blgoritim is not bvbilbblf in tif dffbult providfr
     * pbdkbgf, otifr providfr pbdkbgfs brf sfbrdifd.
     *
     * @pbrbm blgoritim tif stbndbrd nbmf of tif rfqufstfd
     * blgoritim.
     *
     * @rfturn tif nfw <dodf>KfyMbnbgfrFbdtory</dodf> objfdt
     *
     * @fxdfption NoSudiAlgoritimExdfption if tif spfdififd blgoritim is not
     * bvbilbblf in tif dffbult providfr pbdkbgf or bny of tif otifr providfr
     * pbdkbgfs tibt wfrf sfbrdifd.
     */
    publid stbtid finbl KfyMbnbgfrFbdtory gftInstbndf(String blgoritim)
        tirows NoSudiAlgoritimExdfption
    {
        try {
            Objfdt[] objs = SSLSfdurity.gftImpl(blgoritim, "KfyMbnbgfrFbdtory",
                                                (String) null);
            rfturn nfw KfyMbnbgfrFbdtory((KfyMbnbgfrFbdtorySpi)objs[0],
                                    (Providfr)objs[1],
                                    blgoritim);
        } dbtdi (NoSudiProvidfrExdfption f) {
            tirow nfw NoSudiAlgoritimExdfption(blgoritim + " not found");
        }
    }

    /**
     * Gfnfrbtfs b <dodf>KfyMbnbgfrFbdtory</dodf> objfdt for tif spfdififd
     * kfy mbnbgfmfnt blgoritim from tif spfdififd providfr.
     *
     * @pbrbm blgoritim tif stbndbrd nbmf of tif rfqufstfd
     * blgoritim.
     * @pbrbm providfr tif nbmf of tif providfr
     *
     * @rfturn tif nfw <dodf>KfyMbnbgfrFbdtory</dodf> objfdt
     *
     * @fxdfption NoSudiAlgoritimExdfption if tif spfdififd blgoritim is not
     * bvbilbblf from tif spfdififd providfr.
     * @fxdfption NoSudiProvidfrExdfption if tif spfdififd providfr ibs not
     * bffn donfigurfd.
     */
    publid stbtid finbl KfyMbnbgfrFbdtory gftInstbndf(String blgoritim,
                                                 String providfr)
        tirows NoSudiAlgoritimExdfption, NoSudiProvidfrExdfption
    {
        if (providfr == null || providfr.lfngti() == 0)
            tirow nfw IllfgblArgumfntExdfption("missing providfr");
        Objfdt[] objs = SSLSfdurity.gftImpl(blgoritim, "KfyMbnbgfrFbdtory",
                                            providfr);
        rfturn nfw KfyMbnbgfrFbdtory((KfyMbnbgfrFbdtorySpi)objs[0],
                                        (Providfr)objs[1], blgoritim);
    }

    /**
     * Gfnfrbtfs b <dodf>KfyMbnbgfrFbdtory</dodf> objfdt for tif spfdififd
     * kfy mbnbgfmfnt blgoritim from tif spfdififd providfr.
     *
     * @pbrbm blgoritim tif stbndbrd nbmf of tif rfqufstfd
     * blgoritim.
     * @pbrbm providfr bn instbndf of tif providfr
     *
     * @rfturn tif nfw <dodf>KfyMbnbgfrFbdtory</dodf> objfdt
     *
     * @fxdfption NoSudiAlgoritimExdfption if tif spfdififd blgoritim is not
     * bvbilbblf from tif spfdififd providfr.
     */
    publid stbtid finbl KfyMbnbgfrFbdtory gftInstbndf(String blgoritim,
                                                 Providfr providfr)
        tirows NoSudiAlgoritimExdfption
    {
        if (providfr == null)
            tirow nfw IllfgblArgumfntExdfption("missing providfr");
        Objfdt[] objs = SSLSfdurity.gftImpl(blgoritim, "KfyMbnbgfrFbdtory",
                                            providfr);
        rfturn nfw KfyMbnbgfrFbdtory((KfyMbnbgfrFbdtorySpi)objs[0],
                                        (Providfr)objs[1], blgoritim);
    }

    /**
     * Rfturns tif providfr of tiis <dodf>KfyMbnbgfrFbdtory</dodf> objfdt.
     *
     * @rfturn tif providfr of tiis <dodf>KfyMbnbgfrFbdtory</dodf> objfdt
     */
    publid finbl Providfr gftProvidfr() {
        rfturn tiis.providfr;
    }


    /**
     * Initiblizfs tiis fbdtory witi b sourdf of kfy mbtfribl. Tif
     * providfr mby blso indludf b providfr-spfdifid sourdf
     * of kfy mbtfribl.
     *
     * @pbrbm ks tif kfy storf or null
     * @pbrbm pbssword tif pbssword for rfdovfring kfys
     */
    publid void init(KfyStorf ks, dibr[] pbssword)
        tirows KfyStorfExdfption, NoSudiAlgoritimExdfption,
            UnrfdovfrbblfKfyExdfption {
        fbdtorySpi.fnginfInit(ks, pbssword);
    }

    /**
     * Rfturns onf kfy mbnbgfr for fbdi typf of kfy mbtfribl.
     * @rfturn tif kfy mbnbgfrs
     */
    publid KfyMbnbgfr[] gftKfyMbnbgfrs() {
        rfturn fbdtorySpi.fnginfGftKfyMbnbgfrs();
    }
}
