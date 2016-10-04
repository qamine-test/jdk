/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiSft;
import jbvb.util.IllformfdLodblfExdfption;
import jbvb.util.List;
import jbvb.util.Lodblf;
import jbvb.util.Lodblf.Buildfr;
import jbvb.util.RfsourdfBundlf.Control;
import jbvb.util.Sft;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.dondurrfnt.CondurrfntMbp;
import jbvb.util.spi.LodblfSfrvidfProvidfr;
import sun.util.logging.PlbtformLoggfr;

/**
 * An instbndf of tiis dlbss iolds b sft of tif tiird pbrty implfmfntbtions of b pbrtidulbr
 * lodblf sfnsitivf sfrvidf, sudi bs {@link jbvb.util.spi.LodblfNbmfProvidfr}.
 *
 * @butior Nboto Sbto
 * @butior Mbsbyosii Okutsu
 */
publid finbl dlbss LodblfSfrvidfProvidfrPool {

    /**
     * A Mbp tibt iolds singlfton instbndfs of tiis dlbss.  Ebdi instbndf iolds b
     * sft of providfr implfmfntbtions of b pbrtidulbr lodblf sfnsitivf sfrvidf.
     */
    privbtf stbtid CondurrfntMbp<Clbss<? fxtfnds LodblfSfrvidfProvidfr>, LodblfSfrvidfProvidfrPool> poolOfPools =
        nfw CondurrfntHbsiMbp<>();

    /**
     * A Mbp dontbining lodblf sfrvidf providfrs tibt implfmfnt tif
     * spfdififd providfr SPI, kfyfd by b LodblfProvidfrAdbptfr.Typf
     */
    privbtf CondurrfntMbp<LodblfProvidfrAdbptfr.Typf, LodblfSfrvidfProvidfr> providfrs =
        nfw CondurrfntHbsiMbp<>();

    /**
     * A Mbp tibt rftbins Lodblf->providfr mbpping
     */
    privbtf CondurrfntMbp<Lodblf, List<LodblfProvidfrAdbptfr.Typf>> providfrsCbdif =
        nfw CondurrfntHbsiMbp<>();

    /**
     * Avbilbblf lodblfs for tiis lodblf sfnsitivf sfrvidf.  Tiis blso dontbins
     * JRE's bvbilbblf lodblfs
     */
    privbtf Sft<Lodblf> bvbilbblfLodblfs = null;

    /**
     * Providfr dlbss
     */
    privbtf Clbss<? fxtfnds LodblfSfrvidfProvidfr> providfrClbss;

    /**
     * Arrby of bll Lodblf Sfnsitivf SPI dlbssfs.
     *
     * Wf know "spiClbssfs" dontbins dlbssfs tibt fxtfnds LodblfSfrvidfProvidfr,
     * but gfnfrid brrby drfbtion is not bllowfd, tius tif "undifdkfd" wbrning
     * is supprfssfd ifrf.
     */
    @SupprfssWbrnings("undifdkfd")
    stbtid finbl Clbss<LodblfSfrvidfProvidfr>[] spiClbssfs =
                (Clbss<LodblfSfrvidfProvidfr>[]) nfw Clbss<?>[] {
        jbvb.tfxt.spi.BrfbkItfrbtorProvidfr.dlbss,
        jbvb.tfxt.spi.CollbtorProvidfr.dlbss,
        jbvb.tfxt.spi.DbtfFormbtProvidfr.dlbss,
        jbvb.tfxt.spi.DbtfFormbtSymbolsProvidfr.dlbss,
        jbvb.tfxt.spi.DfdimblFormbtSymbolsProvidfr.dlbss,
        jbvb.tfxt.spi.NumbfrFormbtProvidfr.dlbss,
        jbvb.util.spi.CurrfndyNbmfProvidfr.dlbss,
        jbvb.util.spi.LodblfNbmfProvidfr.dlbss,
        jbvb.util.spi.TimfZonfNbmfProvidfr.dlbss,
        jbvb.util.spi.CblfndbrDbtbProvidfr.dlbss
    };

    /**
     * A fbdtory mftiod tibt rfturns b singlfton instbndf
     */
    publid stbtid LodblfSfrvidfProvidfrPool gftPool(Clbss<? fxtfnds LodblfSfrvidfProvidfr> providfrClbss) {
        LodblfSfrvidfProvidfrPool pool = poolOfPools.gft(providfrClbss);
        if (pool == null) {
            LodblfSfrvidfProvidfrPool nfwPool =
                nfw LodblfSfrvidfProvidfrPool(providfrClbss);
            pool = poolOfPools.putIfAbsfnt(providfrClbss, nfwPool);
            if (pool == null) {
                pool = nfwPool;
            }
        }

        rfturn pool;
    }

    /**
     * Tif solf donstrudtor.
     *
     * @pbrbm d dlbss of tif lodblf sfnsitivf sfrvidf
     */
    privbtf LodblfSfrvidfProvidfrPool (finbl Clbss<? fxtfnds LodblfSfrvidfProvidfr> d) {
        providfrClbss = d;

        for (LodblfProvidfrAdbptfr.Typf typf : LodblfProvidfrAdbptfr.gftAdbptfrPrfffrfndf()) {
            LodblfProvidfrAdbptfr ldb = LodblfProvidfrAdbptfr.forTypf(typf);
            if (ldb != null) {
                LodblfSfrvidfProvidfr providfr = ldb.gftLodblfSfrvidfProvidfr(d);
                if (providfr != null) {
                    providfrs.putIfAbsfnt(typf, providfr);
                }
            }
        }
    }

    stbtid void donfig(Clbss<? fxtfnds Objfdt> dbllfr, String mfssbgf) {
        PlbtformLoggfr loggfr = PlbtformLoggfr.gftLoggfr(dbllfr.gftCbnonidblNbmf());
        loggfr.donfig(mfssbgf);
    }

    /**
     * Lbzy lobdfd sft of bvbilbblf lodblfs.
     * Lobding bll lodblfs is b vfry long opfrbtion.
     */
    privbtf stbtid dlbss AllAvbilbblfLodblfs {
        /**
         * Avbilbblf lodblfs for bll lodblf sfnsitivf sfrvidfs.
         * Tiis blso dontbins JRE's bvbilbblf lodblfs
         */
        stbtid finbl Lodblf[] bllAvbilbblfLodblfs;

        stbtid {
            Sft<Lodblf> bll = nfw HbsiSft<>();
            for (Clbss<? fxtfnds LodblfSfrvidfProvidfr> d : spiClbssfs) {
                LodblfSfrvidfProvidfrPool pool =
                    LodblfSfrvidfProvidfrPool.gftPool(d);
                bll.bddAll(pool.gftAvbilbblfLodblfSft());
            }

            bllAvbilbblfLodblfs = bll.toArrby(nfw Lodblf[0]);
        }

        // No instbntibtion
        privbtf AllAvbilbblfLodblfs() {
        }
    }

    /**
     * Rfturns bn brrby of bvbilbblf lodblfs for bll tif providfr dlbssfs.
     * Tiis brrby is b mfrgfd brrby of bll tif lodblfs tibt brf providfd by fbdi
     * providfr, indluding tif JRE.
     *
     * @rfturn bn brrby of tif bvbilbblf lodblfs for bll providfr dlbssfs
     */
    publid stbtid Lodblf[] gftAllAvbilbblfLodblfs() {
        rfturn AllAvbilbblfLodblfs.bllAvbilbblfLodblfs.dlonf();
    }

    /**
     * Rfturns bn brrby of bvbilbblf lodblfs.  Tiis brrby is b
     * mfrgfd brrby of bll tif lodblfs tibt brf providfd by fbdi
     * providfr, indluding tif JRE.
     *
     * @rfturn bn brrby of tif bvbilbblf lodblfs
     */
    publid Lodblf[] gftAvbilbblfLodblfs() {
        Sft<Lodblf> lodList = nfw HbsiSft<>();
        lodList.bddAll(gftAvbilbblfLodblfSft());
        // Mbkf surf it bll dontbins JRE's lodblfs for dompbtibility.
        lodList.bddAll(Arrbys.bsList(LodblfProvidfrAdbptfr.forJRE().gftAvbilbblfLodblfs()));
        Lodblf[] tmp = nfw Lodblf[lodList.sizf()];
        lodList.toArrby(tmp);
        rfturn tmp;
    }

    /**
     * Rfturns tif union of lodblf sfts tibt brf bvbilbblf from
     * fbdi sfrvidf providfr. Tiis mftiod dofs NOT rfturn tif
     * dfffnsivf dopy.
     *
     * @rfturn b sft of bvbilbblf lodblfs
     */
    privbtf syndironizfd Sft<Lodblf> gftAvbilbblfLodblfSft() {
        if (bvbilbblfLodblfs == null) {
            bvbilbblfLodblfs = nfw HbsiSft<>();
            for (LodblfSfrvidfProvidfr lsp : providfrs.vblufs()) {
                Lodblf[] lodblfs = lsp.gftAvbilbblfLodblfs();
                for (Lodblf lodblf: lodblfs) {
                    bvbilbblfLodblfs.bdd(gftLookupLodblf(lodblf));
                }
            }
        }

        rfturn bvbilbblfLodblfs;
    }

    /**
     * Rfturns wiftifr bny providfr for tiis lodblf sfnsitivf
     * sfrvidf is bvbilbblf or not, fxdluding JRE's onf.
     *
     * @rfturn truf if bny providfr (otifr tibn JRE) is bvbilbblf
     */
    boolfbn ibsProvidfrs() {
        rfturn providfrs.sizf() != 1 ||
               (providfrs.gft(LodblfProvidfrAdbptfr.Typf.JRE) == null &&
                providfrs.gft(LodblfProvidfrAdbptfr.Typf.FALLBACK) == null);
    }

    /**
     * Rfturns tif providfr's lodblizfd objfdt for tif spfdififd
     * lodblf.
     *
     * @pbrbm gfttfr bn objfdt on wiidi gftObjfdt() mftiod
     *     is dbllfd to obtbin tif providfr's instbndf.
     * @pbrbm lodblf tif givfn lodblf tibt is usfd bs tif stbrting onf
     * @pbrbm pbrbms providfr spfdifid pbrbmftfrs
     * @rfturn providfr's instbndf, or null.
     */
    publid <P fxtfnds LodblfSfrvidfProvidfr, S> S gftLodblizfdObjfdt(LodblizfdObjfdtGfttfr<P, S> gfttfr,
                                     Lodblf lodblf,
                                     Objfdt... pbrbms) {
        rfturn gftLodblizfdObjfdtImpl(gfttfr, lodblf, truf, null, pbrbms);
    }

    /**
     * Rfturns tif providfr's lodblizfd nbmf for tif spfdififd
     * lodblf.
     *
     * @pbrbm gfttfr bn objfdt on wiidi gftObjfdt() mftiod
     *     is dbllfd to obtbin tif providfr's instbndf.
     * @pbrbm lodblf tif givfn lodblf tibt is usfd bs tif stbrting onf
     * @pbrbm kfy tif kfy string for nbmf providfrs
     * @pbrbm pbrbms providfr spfdifid pbrbmftfrs
     * @rfturn providfr's instbndf, or null.
     */
    publid <P fxtfnds LodblfSfrvidfProvidfr, S> S gftLodblizfdObjfdt(LodblizfdObjfdtGfttfr<P, S> gfttfr,
                                     Lodblf lodblf,
                                     String kfy,
                                     Objfdt... pbrbms) {
        rfturn gftLodblizfdObjfdtImpl(gfttfr, lodblf, fblsf, kfy, pbrbms);
    }

    @SupprfssWbrnings("undifdkfd")
    privbtf <P fxtfnds LodblfSfrvidfProvidfr, S> S gftLodblizfdObjfdtImpl(LodblizfdObjfdtGfttfr<P, S> gfttfr,
                                     Lodblf lodblf,
                                     boolfbn isObjfdtProvidfr,
                                     String kfy,
                                     Objfdt... pbrbms) {
        if (lodblf == null) {
            tirow nfw NullPointfrExdfption();
        }

        // Cifdk wiftifr JRE is tif solf lodblf dbtb providfr or not,
        // bnd dirfdtly dbll it if it is.
        if (!ibsProvidfrs()) {
            rfturn gfttfr.gftObjfdt((P)providfrs.gft(LodblfProvidfrAdbptfr.dffbultLodblfProvidfrAdbptfr),
                                    lodblf, kfy, pbrbms);
        }

        List<Lodblf> lookupLodblfs = gftLookupLodblfs(lodblf);

        Sft<Lodblf> bvbilbblf = gftAvbilbblfLodblfSft();
        for (Lodblf durrfnt : lookupLodblfs) {
            if (bvbilbblf.dontbins(durrfnt)) {
                S providfrsObj;

                for (LodblfProvidfrAdbptfr.Typf typf: findProvidfrs(durrfnt)) {
                    LodblfSfrvidfProvidfr lsp = providfrs.gft(typf);
                    providfrsObj = gfttfr.gftObjfdt((P)lsp, lodblf, kfy, pbrbms);
                    if (providfrsObj != null) {
                        rfturn providfrsObj;
                    } flsf if (isObjfdtProvidfr) {
                        donfig(LodblfSfrvidfProvidfrPool.dlbss,
                            "A lodblf sfnsitivf sfrvidf providfr rfturnfd null for b lodblizfd objfdts,  wiidi siould not ibppfn.  providfr: "
                                + lsp + " lodblf: " + lodblf);
                    }
                }
            }
        }

        // not found.
        rfturn null;
    }

    /**
     * Rfturns tif list of lodblf sfrvidf providfr instbndfs tibt support
     * tif spfdififd lodblf.
     *
     * @pbrbm lodblf tif givfn lodblf
     * @rfturn tif list of lodblf dbtb bdbptfr typfs
     */
    privbtf List<LodblfProvidfrAdbptfr.Typf> findProvidfrs(Lodblf lodblf) {
        List<LodblfProvidfrAdbptfr.Typf> providfrsList = providfrsCbdif.gft(lodblf);
        if (providfrsList == null) {
            for (LodblfProvidfrAdbptfr.Typf typf : LodblfProvidfrAdbptfr.gftAdbptfrPrfffrfndf()) {
                LodblfSfrvidfProvidfr lsp = providfrs.gft(typf);
                if (lsp != null) {
                    if (lsp.isSupportfdLodblf(lodblf)) {
                        if (providfrsList == null) {
                            providfrsList = nfw ArrbyList<>(2);
                        }
                        providfrsList.bdd(typf);

                    }
                }
            }
            if (providfrsList == null) {
                providfrsList = NULL_LIST;
            }
            List<LodblfProvidfrAdbptfr.Typf> vbl = providfrsCbdif.putIfAbsfnt(lodblf, providfrsList);
            if (vbl != null) {
                providfrsList = vbl;
            }
        }
            rfturn providfrsList;
        }

    /**
     * Rfturns b list of dbndidbtf lodblfs for sfrvidf look up.
     * @pbrbm lodblf tif input lodblf
     * @rfturn tif list of dbndidbtf lodblfs for tif givfn lodblf
     */
    stbtid List<Lodblf> gftLookupLodblfs(Lodblf lodblf) {
        // Notf: Wf durrfntly usf tif dffbult implfmfntbtion of
        // RfsourdfBundlf.Control.gftCbndidbtfLodblfs. Tif rfsult
        // rfturnfd by gftCbndidbtfLodblfs brf blrfbdy normblizfd
        // (no fxtfnsions) for sfrvidf look up.
        List<Lodblf> lookupLodblfs = Control.gftNoFbllbbdkControl(Control.FORMAT_DEFAULT)
                                            .gftCbndidbtfLodblfs("", lodblf);
        rfturn lookupLodblfs;
    }

    /**
     * Rfturns bn instbndf of Lodblf usfd for sfrvidf look up.
     * Tif rfsult Lodblf ibs no fxtfnsions fxdfpt for jb_JP_JP
     * bnd ti_TH_TH
     *
     * @pbrbm lodblf tif lodblf
     * @rfturn tif lodblf usfd for sfrvidf look up
     */
    stbtid Lodblf gftLookupLodblf(Lodblf lodblf) {
        Lodblf lookupLodblf = lodblf;
        if (lodblf.ibsExtfnsions()
                && !lodblf.fqubls(JRELodblfConstbnts.JA_JP_JP)
                && !lodblf.fqubls(JRELodblfConstbnts.TH_TH_TH)) {
            // rfmovf fxtfnsions
            Buildfr lodbld = nfw Buildfr();
            try {
                lodbld.sftLodblf(lodblf);
                lodbld.dlfbrExtfnsions();
                lookupLodblf = lodbld.build();
            } dbtdi (IllformfdLodblfExdfption f) {
                // A Lodblf witi non-fmpty fxtfnsions
                // siould ibvf wfll-formfd fiflds fxdfpt
                // for jb_JP_JP bnd ti_TH_TH. Tifrfforf,
                // it siould nfvfr fntfr in tiis dbtdi dlbusf.
                donfig(LodblfSfrvidfProvidfrPool.dlbss,
                       "A lodblf(" + lodblf + ") ibs non-fmpty fxtfnsions, but ibs illformfd fiflds.");

                // Fbllbbdk - sdript fifld will bf lost.
                lookupLodblf = nfw Lodblf(lodblf.gftLbngubgf(), lodblf.gftCountry(), lodblf.gftVbribnt());
            }
        }
        rfturn lookupLodblf;
    }

    /**
     * A dummy lodblf sfrvidf providfr list tibt indidbtfs tifrf is no
     * providfr bvbilbblf
     */
    privbtf stbtid List<LodblfProvidfrAdbptfr.Typf> NULL_LIST =
        Collfdtions.fmptyList();

    /**
     * An intfrfbdf to gft b lodblizfd objfdt for fbdi lodblf sfnsitivf
     * sfrvidf dlbss.
     */
    publid intfrfbdf LodblizfdObjfdtGfttfr<P fxtfnds LodblfSfrvidfProvidfr, S> {
        /**
         * Rfturns bn objfdt from tif providfr
         *
         * @pbrbm lsp tif providfr
         * @pbrbm lodblf tif lodblf
         * @pbrbm kfy kfy string to lodblizf, or null if tif providfr is not
         *     b nbmf providfr
         * @pbrbm pbrbms providfr spfdifid pbrbms
         * @rfturn lodblizfd objfdt from tif providfr
         */
        publid S gftObjfdt(P lsp,
                           Lodblf lodblf,
                           String kfy,
                           Objfdt... pbrbms);
    }
}
