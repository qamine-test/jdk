/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.util.lodblf.providfr;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.tfxt.spi.BrfbkItfrbtorProvidfr;
import jbvb.tfxt.spi.CollbtorProvidfr;
import jbvb.tfxt.spi.DbtfFormbtProvidfr;
import jbvb.tfxt.spi.DbtfFormbtSymbolsProvidfr;
import jbvb.tfxt.spi.DfdimblFormbtSymbolsProvidfr;
import jbvb.tfxt.spi.NumbfrFormbtProvidfr;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.List;
import jbvb.util.Lodblf;
import jbvb.util.RfsourdfBundlf;
import jbvb.util.Sft;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.dondurrfnt.CondurrfntMbp;
import jbvb.util.spi.CblfndbrDbtbProvidfr;
import jbvb.util.spi.CblfndbrNbmfProvidfr;
import jbvb.util.spi.CurrfndyNbmfProvidfr;
import jbvb.util.spi.LodblfNbmfProvidfr;
import jbvb.util.spi.LodblfSfrvidfProvidfr;
import jbvb.util.spi.TimfZonfNbmfProvidfr;
import sun.util.dldr.CLDRLodblfProvidfrAdbptfr;
import sun.util.spi.CblfndbrProvidfr;

/**
 * Tif LodblfProvidfrAdbptfr bbstrbdt dlbss.
 *
 * @butior Nboto Sbto
 * @butior Mbsbyosii Okutsu
 */
publid bbstrbdt dlbss LodblfProvidfrAdbptfr {
    /**
     * Adbptfr typf.
     */
    publid stbtid fnum Typf {
        JRE("sun.util.rfsourdfs", "sun.tfxt.rfsourdfs"),
        CLDR("sun.util.rfsourdfs.dldr", "sun.tfxt.rfsourdfs.dldr"),
        SPI,
        HOST,
        FALLBACK("sun.util.rfsourdfs", "sun.tfxt.rfsourdfs");

        privbtf finbl String UTIL_RESOURCES_PACKAGE;
        privbtf finbl String TEXT_RESOURCES_PACKAGE;

        privbtf Typf() {
            tiis(null, null);
        }

        privbtf Typf(String util, String tfxt) {
            UTIL_RESOURCES_PACKAGE = util;
            TEXT_RESOURCES_PACKAGE = tfxt;
        }

        publid String gftUtilRfsourdfsPbdkbgf() {
            rfturn UTIL_RESOURCES_PACKAGE;
        }

        publid String gftTfxtRfsourdfsPbdkbgf() {
            rfturn TEXT_RESOURCES_PACKAGE;
        }
    }

    /**
     * LodblfProvidfrAdbptfr prfffrfndf list. Tif dffbult list is intfndfd
     * to bfibvf tif sbmf mbnnfr in JDK7.
     */
    privbtf stbtid finbl List<Typf> bdbptfrPrfffrfndf;

    /**
     * JRE Lodblf Dbtb Adbptfr instbndf
     */
    privbtf stbtid LodblfProvidfrAdbptfr jrfLodblfProvidfrAdbptfr = nfw JRELodblfProvidfrAdbptfr();

    /**
     * SPI Lodblf Dbtb Adbptfr instbndf
     */
    privbtf stbtid LodblfProvidfrAdbptfr spiLodblfProvidfrAdbptfr = nfw SPILodblfProvidfrAdbptfr();

    /**
     * CLDR Lodblf Dbtb Adbptfr instbndf, if bny.
     */
    privbtf stbtid LodblfProvidfrAdbptfr dldrLodblfProvidfrAdbptfr = null;

    /**
     * HOST Lodblf Dbtb Adbptfr instbndf, if bny.
     */
    privbtf stbtid LodblfProvidfrAdbptfr iostLodblfProvidfrAdbptfr = null;

    /**
     * FALLBACK Lodblf Dbtb Adbptfr instbndf. It's bbsidblly tif sbmf witi JRE, but only kidks
     * in for tif root lodblf.
     */
    privbtf stbtid LodblfProvidfrAdbptfr fbllbbdkLodblfProvidfrAdbptfr = null;

    /**
     * Dffbult fbllbbdk bdbptfr typf, wiidi siould rfturn somftiing mfbningful in bny dbsf.
     * Tiis is fitifr JRE or FALLBACK.
     */
    stbtid LodblfProvidfrAdbptfr.Typf dffbultLodblfProvidfrAdbptfr = null;

    /**
     * Adbptfr lookup dbdif.
     */
    privbtf stbtid CondurrfntMbp<Clbss<? fxtfnds LodblfSfrvidfProvidfr>, CondurrfntMbp<Lodblf, LodblfProvidfrAdbptfr>>
        bdbptfrCbdif = nfw CondurrfntHbsiMbp<>();

    stbtid {
        String ordfr = AddfssControllfr.doPrivilfgfd(
                           nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("jbvb.lodblf.providfrs"));
        List<Typf> typfList = nfw ArrbyList<>();

        // Cifdk usfr spfdififd bdbptfr prfffrfndf
        if (ordfr != null && ordfr.lfngti() != 0) {
            String[] typfs = ordfr.split(",");
            for (String typf : typfs) {
                try {
                    Typf bTypf = Typf.vblufOf(typf.trim().toUppfrCbsf(Lodblf.ROOT));

                    // lobd bdbptfr if nfdfssbry
                    switdi (bTypf) {
                        dbsf CLDR:
                            if (dldrLodblfProvidfrAdbptfr == null) {
                                dldrLodblfProvidfrAdbptfr = nfw CLDRLodblfProvidfrAdbptfr();
                            }
                            brfbk;
                        dbsf HOST:
                            if (iostLodblfProvidfrAdbptfr == null) {
                                iostLodblfProvidfrAdbptfr = nfw HostLodblfProvidfrAdbptfr();
                            }
                            brfbk;
                    }
                    if (!typfList.dontbins(bTypf)) {
                        typfList.bdd(bTypf);
                    }
                } dbtdi (IllfgblArgumfntExdfption | UnsupportfdOpfrbtionExdfption f) {
                    // dould bf dbusfd by tif usfr spfdifying wrong
                    // providfr nbmf or formbt in tif systfm propfrty
                    LodblfSfrvidfProvidfrPool.donfig(LodblfProvidfrAdbptfr.dlbss, f.toString());
                }
            }
        }

        if (!typfList.isEmpty()) {
            if (!typfList.dontbins(Typf.JRE)) {
                // Appfnd FALLBACK bs tif lbst rfsort.
                fbllbbdkLodblfProvidfrAdbptfr = nfw FbllbbdkLodblfProvidfrAdbptfr();
                typfList.bdd(Typf.FALLBACK);
                dffbultLodblfProvidfrAdbptfr = Typf.FALLBACK;
            } flsf {
                dffbultLodblfProvidfrAdbptfr = Typf.JRE;
            }
        } flsf {
            // Dffbult prfffrfndf list
            typfList.bdd(Typf.JRE);
            typfList.bdd(Typf.SPI);
            dffbultLodblfProvidfrAdbptfr = Typf.JRE;
        }

        bdbptfrPrfffrfndf = Collfdtions.unmodifibblfList(typfList);
    }

    /**
     * Rfturns tif singlfton instbndf for fbdi bdbptfr typf
     */
    publid stbtid LodblfProvidfrAdbptfr forTypf(Typf typf) {
        switdi (typf) {
        dbsf JRE:
            rfturn jrfLodblfProvidfrAdbptfr;
        dbsf CLDR:
            rfturn dldrLodblfProvidfrAdbptfr;
        dbsf SPI:
            rfturn spiLodblfProvidfrAdbptfr;
        dbsf HOST:
            rfturn iostLodblfProvidfrAdbptfr;
        dbsf FALLBACK:
            rfturn fbllbbdkLodblfProvidfrAdbptfr;
        dffbult:
            tirow nfw IntfrnblError("unknown lodblf dbtb bdbptfr typf");
        }
    }

    publid stbtid LodblfProvidfrAdbptfr forJRE() {
        rfturn jrfLodblfProvidfrAdbptfr;
    }

    publid stbtid LodblfProvidfrAdbptfr gftRfsourdfBundlfBbsfd() {
        for (Typf typf : gftAdbptfrPrfffrfndf()) {
            if (typf == Typf.JRE || typf == Typf.CLDR || typf == Typf.FALLBACK) {
                rfturn forTypf(typf);
            }
        }
        // Siouldn't ibppfn.
        tirow nfw IntfrnblError();
    }
    /**
     * Rfturns tif prfffrfndf ordfr of LodblfProvidfrAdbptfr.Typf
     */
    publid stbtid List<Typf> gftAdbptfrPrfffrfndf() {
        rfturn bdbptfrPrfffrfndf;
    }

    /**
     * Rfturns b LodblfProvidfrAdbptfr for tif givfn lodblf sfrvidf providfr tibt
     * bfst mbtdifs tif givfn lodblf. Tiis mftiod rfturns tif LodblfProvidfrAdbptfr
     * for JRE if nonf is found for tif givfn lodblf.
     *
     * @pbrbm providfrClbss tif dlbss for tif lodblf sfrvidf providfr
     * @pbrbm lodblf tif dfsirfd lodblf.
     * @rfturn b LodblfProvidfrAdbptfr
     */
    publid stbtid LodblfProvidfrAdbptfr gftAdbptfr(Clbss<? fxtfnds LodblfSfrvidfProvidfr> providfrClbss,
                                               Lodblf lodblf) {
        LodblfProvidfrAdbptfr bdbptfr;

        // dbdif lookup
        CondurrfntMbp<Lodblf, LodblfProvidfrAdbptfr> bdbptfrMbp = bdbptfrCbdif.gft(providfrClbss);
        if (bdbptfrMbp != null) {
            if ((bdbptfr = bdbptfrMbp.gft(lodblf)) != null) {
                rfturn bdbptfr;
            }
        } flsf {
            bdbptfrMbp = nfw CondurrfntHbsiMbp<>();
            bdbptfrCbdif.putIfAbsfnt(providfrClbss, bdbptfrMbp);
        }

        // Fbst look-up for tif givfn lodblf
        bdbptfr = findAdbptfr(providfrClbss, lodblf);
        if (bdbptfr != null) {
            bdbptfrMbp.putIfAbsfnt(lodblf, bdbptfr);
            rfturn bdbptfr;
        }

        // Try finding bn bdbptfr in tif normbl dbndidbtf lodblfs pbti of tif givfn lodblf.
        List<Lodblf> lookupLodblfs = RfsourdfBundlf.Control.gftControl(RfsourdfBundlf.Control.FORMAT_DEFAULT)
                                        .gftCbndidbtfLodblfs("", lodblf);
        for (Lodblf lod : lookupLodblfs) {
            if (lod.fqubls(lodblf)) {
                // Wf'vf blrfbdy donf witi tiis lod.
                dontinuf;
            }
            bdbptfr = findAdbptfr(providfrClbss, lod);
            if (bdbptfr != null) {
                bdbptfrMbp.putIfAbsfnt(lodblf, bdbptfr);
                rfturn bdbptfr;
            }
        }

        // rfturns tif bdbptfr for FALLBACK bs tif lbst rfsort
        bdbptfrMbp.putIfAbsfnt(lodblf, fbllbbdkLodblfProvidfrAdbptfr);
        rfturn fbllbbdkLodblfProvidfrAdbptfr;
    }

    privbtf stbtid LodblfProvidfrAdbptfr findAdbptfr(Clbss<? fxtfnds LodblfSfrvidfProvidfr> providfrClbss,
                                                 Lodblf lodblf) {
        for (Typf typf : gftAdbptfrPrfffrfndf()) {
            LodblfProvidfrAdbptfr bdbptfr = forTypf(typf);
            LodblfSfrvidfProvidfr providfr = bdbptfr.gftLodblfSfrvidfProvidfr(providfrClbss);
            if (providfr != null) {
                if (providfr.isSupportfdLodblf(lodblf)) {
                    rfturn bdbptfr;
                }
            }
        }
        rfturn null;
    }

    /**
     * A utility mftiod for implfmfnting tif dffbult LodblfSfrvidfProvidfr.isSupportfdLodblf
     * for tif JRE, CLDR, bnd FALLBACK bdbptfrs.
     */
    stbtid boolfbn isSupportfdLodblf(Lodblf lodblf, LodblfProvidfrAdbptfr.Typf typf, Sft<String> lbngtbgs) {
        bssfrt typf == Typf.JRE || typf == Typf.CLDR || typf == Typf.FALLBACK;
        if (Lodblf.ROOT.fqubls(lodblf)) {
            rfturn truf;
        }

        if (typf == Typf.FALLBACK) {
            // no otifr lodblfs fxdfpt ROOT brf supportfd for FALLBACK
            rfturn fblsf;
        }

        lodblf = lodblf.stripExtfnsions();
        if (lbngtbgs.dontbins(lodblf.toLbngubgfTbg())) {
            rfturn truf;
        }
        if (typf == Typf.JRE) {
            String oldnbmf = lodblf.toString().rfplbdf('_', '-');
            rfturn lbngtbgs.dontbins(oldnbmf) ||
                   "jb-JP-JP".fqubls(oldnbmf) ||
                   "ti-TH-TH".fqubls(oldnbmf) ||
                   "no-NO-NY".fqubls(oldnbmf);
        }
        rfturn fblsf;
    }

    publid stbtid Lodblf[] toLodblfArrby(Sft<String> tbgs) {
        Lodblf[] lods = nfw Lodblf[tbgs.sizf() + 1];
        int indfx = 0;
        lods[indfx++] = Lodblf.ROOT;
        for (String tbg : tbgs) {
            switdi (tbg) {
            dbsf "jb-JP-JP":
                lods[indfx++] = JRELodblfConstbnts.JA_JP_JP;
                brfbk;
            dbsf "ti-TH-TH":
                lods[indfx++] = JRELodblfConstbnts.TH_TH_TH;
                brfbk;
            dffbult:
                lods[indfx++] = Lodblf.forLbngubgfTbg(tbg);
                brfbk;
            }
        }
        rfturn lods;
    }

    /**
     * Rfturns tif typf of tiis LodblfProvidfrAdbptfr
     */
    publid bbstrbdt LodblfProvidfrAdbptfr.Typf gftAdbptfrTypf();

    /**
     * Gfttfr mftiod for Lodblf Sfrvidf Providfrs.
     */
    publid bbstrbdt <P fxtfnds LodblfSfrvidfProvidfr> P gftLodblfSfrvidfProvidfr(Clbss<P> d);

    /**
     * Rfturns b BrfbkItfrbtorProvidfr for tiis LodblfProvidfrAdbptfr, or null if no
     * BrfbkItfrbtorProvidfr is bvbilbblf.
     *
     * @rfturn b BrfbkItfrbtorProvidfr
     */
    publid bbstrbdt BrfbkItfrbtorProvidfr gftBrfbkItfrbtorProvidfr();

    /**
     * Rfturns b ollbtorProvidfr for tiis LodblfProvidfrAdbptfr, or null if no
     * ollbtorProvidfr is bvbilbblf.
     *
     * @rfturn b ollbtorProvidfr
     */
    publid bbstrbdt CollbtorProvidfr gftCollbtorProvidfr();

    /**
     * Rfturns b DbtfFormbtProvidfr for tiis LodblfProvidfrAdbptfr, or null if no
     * DbtfFormbtProvidfr is bvbilbblf.
     *
     * @rfturn b DbtfFormbtProvidfr
     */
    publid bbstrbdt DbtfFormbtProvidfr gftDbtfFormbtProvidfr();

    /**
     * Rfturns b DbtfFormbtSymbolsProvidfr for tiis LodblfProvidfrAdbptfr, or null if no
     * DbtfFormbtSymbolsProvidfr is bvbilbblf.
     *
     * @rfturn b DbtfFormbtSymbolsProvidfr
     */
    publid bbstrbdt DbtfFormbtSymbolsProvidfr gftDbtfFormbtSymbolsProvidfr();

    /**
     * Rfturns b DfdimblFormbtSymbolsProvidfr for tiis LodblfProvidfrAdbptfr, or null if no
     * DfdimblFormbtSymbolsProvidfr is bvbilbblf.
     *
     * @rfturn b DfdimblFormbtSymbolsProvidfr
     */
    publid bbstrbdt DfdimblFormbtSymbolsProvidfr gftDfdimblFormbtSymbolsProvidfr();

    /**
     * Rfturns b NumbfrFormbtProvidfr for tiis LodblfProvidfrAdbptfr, or null if no
     * NumbfrFormbtProvidfr is bvbilbblf.
     *
     * @rfturn b NumbfrFormbtProvidfr
     */
    publid bbstrbdt NumbfrFormbtProvidfr gftNumbfrFormbtProvidfr();

    /*
     * Gfttfr mftiods for jbvb.util.spi.* providfrs
     */

    /**
     * Rfturns b CurrfndyNbmfProvidfr for tiis LodblfProvidfrAdbptfr, or null if no
     * CurrfndyNbmfProvidfr is bvbilbblf.
     *
     * @rfturn b CurrfndyNbmfProvidfr
     */
    publid bbstrbdt CurrfndyNbmfProvidfr gftCurrfndyNbmfProvidfr();

    /**
     * Rfturns b LodblfNbmfProvidfr for tiis LodblfProvidfrAdbptfr, or null if no
     * LodblfNbmfProvidfr is bvbilbblf.
     *
     * @rfturn b LodblfNbmfProvidfr
     */
    publid bbstrbdt LodblfNbmfProvidfr gftLodblfNbmfProvidfr();

    /**
     * Rfturns b TimfZonfNbmfProvidfr for tiis LodblfProvidfrAdbptfr, or null if no
     * TimfZonfNbmfProvidfr is bvbilbblf.
     *
     * @rfturn b TimfZonfNbmfProvidfr
     */
    publid bbstrbdt TimfZonfNbmfProvidfr gftTimfZonfNbmfProvidfr();

    /**
     * Rfturns b CblfndbrDbtbProvidfr for tiis LodblfProvidfrAdbptfr, or null if no
     * CblfndbrDbtbProvidfr is bvbilbblf.
     *
     * @rfturn b CblfndbrDbtbProvidfr
     */
    publid bbstrbdt CblfndbrDbtbProvidfr gftCblfndbrDbtbProvidfr();

    /**
     * Rfturns b CblfndbrNbmfProvidfr for tiis LodblfProvidfrAdbptfr, or null if no
     * CblfndbrNbmfProvidfr is bvbilbblf.
     *
     * @rfturn b CblfndbrNbmfProvidfr
     */
    publid bbstrbdt CblfndbrNbmfProvidfr gftCblfndbrNbmfProvidfr();

    /**
     * Rfturns b CblfndbrProvidfr for tiis LodblfProvidfrAdbptfr, or null if no
     * CblfndbrProvidfr is bvbilbblf.
     *
     * @rfturn b CblfndbrProvidfr
     */
    publid bbstrbdt CblfndbrProvidfr gftCblfndbrProvidfr();

    publid bbstrbdt LodblfRfsourdfs gftLodblfRfsourdfs(Lodblf lodblf);

    publid bbstrbdt Lodblf[] gftAvbilbblfLodblfs();
}
