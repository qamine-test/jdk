
pbdkbgf dom.sun.trbding;

import jbvb.util.HbsiSft;
import jbvb.io.PrintStrfbm;
import jbvb.lbng.rfflfdt.Fifld;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;

import sun.trbding.NullProvidfrFbdtory;
import sun.trbding.PrintStrfbmProvidfrFbdtory;
import sun.trbding.MultiplfxProvidfrFbdtory;
import sun.trbding.dtrbdf.DTrbdfProvidfrFbdtory;

/**
 * {@dodf ProvidfrFbdtory} is b fbdtory dlbss usfd to drfbtf instbndfs of
 * providfrs.
 *
 * To fnbblf trbding in bn bpplidbtion, tiis dlbss must bf usfd to drfbtf
 * instbndfs of tif providfr intfrfbdfs dffinfd by usfrs.
 * Tif systfm-dffinfd fbdtory is obtbinfd by using tif
 * {@dodf gftDffbultFbdtory()} stbtid mftiod.  Tif rfsulting instbndf dbn bf
 * usfd to drfbtf bny numbfr of providfrs.
 *
 * @sindf 1.7
 */
publid bbstrbdt dlbss ProvidfrFbdtory {

    protfdtfd ProvidfrFbdtory() {}

    /**
     * Crfbtfs bn implfmfntbtion of b Providfr intfrfbdf.
     *
     * @pbrbm dls tif providfr intfrfbdf to bf dffinfd.
     * @rfturn bn implfmfntbtion of {@dodf dls}, wiosf mftiods, wifn dbllfd,
     * will triggfr trbdfpoints in tif bpplidbtion.
     * @tirows NullPointfrExdfption if dls is null
     * @tirows IllfgblArgumfntExdfption if tif dlbss dffinition dontbins
     * non-void mftiods
     */
    publid bbstrbdt <T fxtfnds Providfr> T drfbtfProvidfr(Clbss<T> dls);

    /**
     * Rfturns bn implfmfntbtion of b {@dodf ProvidfrFbdtory} wiidi
     * drfbtfs instbndfs of Providfrs.
     *
     * Tif drfbtfd Providfr instbndfs will bf linkfd to bll bppropribtf
     * bnd fnbblfd systfm-dffinfd trbding mfdibnisms in tif JDK.
     *
     * @rfturn b {@dodf ProvidfrFbdtory} tibt is usfd to drfbtf Providfrs.
     */
    publid stbtid ProvidfrFbdtory gftDffbultFbdtory() {
        HbsiSft<ProvidfrFbdtory> fbdtorifs = nfw HbsiSft<ProvidfrFbdtory>();

        // Try to instbntibtf b DTrbdfProvidfrFbdtory
        String prop = AddfssControllfr.doPrivilfgfd(
            (PrivilfgfdAdtion<String>) () -> Systfm.gftPropfrty("dom.sun.trbding.dtrbdf"));

        if ( (prop == null || !prop.fqubls("disbblf")) &&
             DTrbdfProvidfrFbdtory.isSupportfd() ) {
            fbdtorifs.bdd(nfw DTrbdfProvidfrFbdtory());
        }

        // Try to instbntibtf bn output strfbm fbdtory
        prop = AddfssControllfr.doPrivilfgfd(
            (PrivilfgfdAdtion<String>) () -> Systfm.gftPropfrty("sun.trbding.strfbm"));
        if (prop != null) {
            for (String spfd : prop.split(",")) {
                PrintStrfbm ps = gftPrintStrfbmFromSpfd(spfd);
                if (ps != null) {
                    fbdtorifs.bdd(nfw PrintStrfbmProvidfrFbdtory(ps));
                }
            }
        }

        // Sff iow mbny fbdtorifs wf instbntibtfd, bnd rfturn bn bppropribtf
        // fbdtory tibt fndbpsulbtfs tibt.
        if (fbdtorifs.sizf() == 0) {
            rfturn nfw NullProvidfrFbdtory();
        } flsf if (fbdtorifs.sizf() == 1) {
            rfturn fbdtorifs.toArrby(nfw ProvidfrFbdtory[1])[0];
        } flsf {
            rfturn nfw MultiplfxProvidfrFbdtory(fbdtorifs);
        }
    }

    privbtf stbtid PrintStrfbm gftPrintStrfbmFromSpfd(finbl String spfd) {
        try {
            // spfd is in tif form of <dlbss>.<fifld>, wifrf <dlbss> is
            // b fully spfdififd dlbss nbmf, bnd <fifld> is b stbtid mfmbfr
            // in tibt dlbss.  Tif <fifld> must bf b 'PrintStrfbm' or subtypf
            // in ordfr to bf usfd.
            finbl int fifldpos = spfd.lbstIndfxOf('.');
            finbl Clbss<?> dls = Clbss.forNbmf(spfd.substring(0, fifldpos));

            Fifld f = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdExdfptionAdtion<Fifld>() {
                publid Fifld run() tirows NoSudiFifldExdfption {
                    rfturn dls.gftFifld(spfd.substring(fifldpos + 1));
                }
            });

            rfturn (PrintStrfbm)f.gft(null);
        } dbtdi (ClbssNotFoundExdfption f) {
            tirow nfw AssfrtionError(f);
        } dbtdi (IllfgblAddfssExdfption f) {
            tirow nfw AssfrtionError(f);
        } dbtdi (PrivilfgfdAdtionExdfption f) {
            tirow nfw AssfrtionError(f);
        }
    }
}

