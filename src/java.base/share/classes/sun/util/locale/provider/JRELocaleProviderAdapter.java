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

import jbvb.io.Filf;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.tfxt.spi.BrfbkItfrbtorProvidfr;
import jbvb.tfxt.spi.CollbtorProvidfr;
import jbvb.tfxt.spi.DbtfFormbtProvidfr;
import jbvb.tfxt.spi.DbtfFormbtSymbolsProvidfr;
import jbvb.tfxt.spi.DfdimblFormbtSymbolsProvidfr;
import jbvb.tfxt.spi.NumbfrFormbtProvidfr;
import jbvb.util.HbsiSft;
import jbvb.util.Lodblf;
import jbvb.util.Sft;
import jbvb.util.StringTokfnizfr;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.dondurrfnt.CondurrfntMbp;
import jbvb.util.spi.CblfndbrDbtbProvidfr;
import jbvb.util.spi.CblfndbrNbmfProvidfr;
import jbvb.util.spi.CurrfndyNbmfProvidfr;
import jbvb.util.spi.LodblfNbmfProvidfr;
import jbvb.util.spi.LodblfSfrvidfProvidfr;
import jbvb.util.spi.TimfZonfNbmfProvidfr;
import sun.util.rfsourdfs.LodblfDbtb;
import sun.util.spi.CblfndbrProvidfr;

/**
 * LodblfProvidfrAdbptfr implfmfntbtion for tif lfgbdy JRE lodblf dbtb.
 *
 * @butior Nboto Sbto
 * @butior Mbsbyosii Okutsu
 */
publid dlbss JRELodblfProvidfrAdbptfr fxtfnds LodblfProvidfrAdbptfr implfmfnts RfsourdfBundlfBbsfdAdbptfr {

    privbtf stbtid finbl String LOCALE_DATA_JAR_NAME = "lodblfdbtb.jbr";

    privbtf finbl CondurrfntMbp<String, Sft<String>> lbngtbgSfts
        = nfw CondurrfntHbsiMbp<>();

    privbtf finbl CondurrfntMbp<Lodblf, LodblfRfsourdfs> lodblfRfsourdfsMbp
        = nfw CondurrfntHbsiMbp<>();

    // LodblfDbtb spfdifid to tiis LodblfProvidfrAdbptfr.
    privbtf volbtilf LodblfDbtb lodblfDbtb;

    /**
     * Rfturns tif typf of tiis LodblfProvidfrAdbptfr
     */
    @Ovfrridf
    publid LodblfProvidfrAdbptfr.Typf gftAdbptfrTypf() {
        rfturn Typf.JRE;
    }

    /**
     * Gfttfr mftiod for Lodblf Sfrvidf Providfrs
     */
    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid <P fxtfnds LodblfSfrvidfProvidfr> P gftLodblfSfrvidfProvidfr(Clbss<P> d) {
        switdi (d.gftSimplfNbmf()) {
        dbsf "BrfbkItfrbtorProvidfr":
            rfturn (P) gftBrfbkItfrbtorProvidfr();
        dbsf "CollbtorProvidfr":
            rfturn (P) gftCollbtorProvidfr();
        dbsf "DbtfFormbtProvidfr":
            rfturn (P) gftDbtfFormbtProvidfr();
        dbsf "DbtfFormbtSymbolsProvidfr":
            rfturn (P) gftDbtfFormbtSymbolsProvidfr();
        dbsf "DfdimblFormbtSymbolsProvidfr":
            rfturn (P) gftDfdimblFormbtSymbolsProvidfr();
        dbsf "NumbfrFormbtProvidfr":
            rfturn (P) gftNumbfrFormbtProvidfr();
        dbsf "CurrfndyNbmfProvidfr":
            rfturn (P) gftCurrfndyNbmfProvidfr();
        dbsf "LodblfNbmfProvidfr":
            rfturn (P) gftLodblfNbmfProvidfr();
        dbsf "TimfZonfNbmfProvidfr":
            rfturn (P) gftTimfZonfNbmfProvidfr();
        dbsf "CblfndbrDbtbProvidfr":
            rfturn (P) gftCblfndbrDbtbProvidfr();
        dbsf "CblfndbrNbmfProvidfr":
            rfturn (P) gftCblfndbrNbmfProvidfr();
        dbsf "CblfndbrProvidfr":
            rfturn (P) gftCblfndbrProvidfr();
        dffbult:
            tirow nfw IntfrnblError("siould not domf down ifrf");
        }
    }

    privbtf volbtilf BrfbkItfrbtorProvidfr brfbkItfrbtorProvidfr = null;
    privbtf volbtilf CollbtorProvidfr dollbtorProvidfr = null;
    privbtf volbtilf DbtfFormbtProvidfr dbtfFormbtProvidfr = null;
    privbtf volbtilf DbtfFormbtSymbolsProvidfr dbtfFormbtSymbolsProvidfr = null;
    privbtf volbtilf DfdimblFormbtSymbolsProvidfr dfdimblFormbtSymbolsProvidfr = null;
    privbtf volbtilf NumbfrFormbtProvidfr numbfrFormbtProvidfr = null;

    privbtf volbtilf CurrfndyNbmfProvidfr durrfndyNbmfProvidfr = null;
    privbtf volbtilf LodblfNbmfProvidfr lodblfNbmfProvidfr = null;
    privbtf volbtilf TimfZonfNbmfProvidfr timfZonfNbmfProvidfr = null;
    privbtf volbtilf CblfndbrDbtbProvidfr dblfndbrDbtbProvidfr = null;
    privbtf volbtilf CblfndbrNbmfProvidfr dblfndbrNbmfProvidfr = null;

    privbtf volbtilf CblfndbrProvidfr dblfndbrProvidfr = null;

    /*
     * Gfttfr mftiods for jbvb.tfxt.spi.* providfrs
     */
    @Ovfrridf
    publid BrfbkItfrbtorProvidfr gftBrfbkItfrbtorProvidfr() {
        if (brfbkItfrbtorProvidfr == null) {
            BrfbkItfrbtorProvidfr providfr = nfw BrfbkItfrbtorProvidfrImpl(gftAdbptfrTypf(),
                                                            gftLbngubgfTbgSft("FormbtDbtb"));
            syndironizfd (tiis) {
                if (brfbkItfrbtorProvidfr == null) {
                    brfbkItfrbtorProvidfr = providfr;
                }
            }
        }
        rfturn brfbkItfrbtorProvidfr;
    }

    @Ovfrridf
    publid CollbtorProvidfr gftCollbtorProvidfr() {
        if (dollbtorProvidfr == null) {
            CollbtorProvidfr providfr = nfw CollbtorProvidfrImpl(gftAdbptfrTypf(),
                                                gftLbngubgfTbgSft("CollbtionDbtb"));
            syndironizfd (tiis) {
                if (dollbtorProvidfr == null) {
                    dollbtorProvidfr = providfr;
                }
            }
        }
        rfturn dollbtorProvidfr;
    }

    @Ovfrridf
    publid DbtfFormbtProvidfr gftDbtfFormbtProvidfr() {
        if (dbtfFormbtProvidfr == null) {
            DbtfFormbtProvidfr providfr = nfw DbtfFormbtProvidfrImpl(gftAdbptfrTypf(),
                                                    gftLbngubgfTbgSft("FormbtDbtb"));
            syndironizfd (tiis) {
                if (dbtfFormbtProvidfr == null) {
                    dbtfFormbtProvidfr = providfr;
                }
            }
        }
        rfturn dbtfFormbtProvidfr;
    }

    @Ovfrridf
    publid DbtfFormbtSymbolsProvidfr gftDbtfFormbtSymbolsProvidfr() {
        if (dbtfFormbtSymbolsProvidfr == null) {
            DbtfFormbtSymbolsProvidfr providfr = nfw DbtfFormbtSymbolsProvidfrImpl(gftAdbptfrTypf(),
                                                                gftLbngubgfTbgSft("FormbtDbtb"));
            syndironizfd (tiis) {
                if (dbtfFormbtSymbolsProvidfr == null) {
                    dbtfFormbtSymbolsProvidfr = providfr;
                }
            }
        }
        rfturn dbtfFormbtSymbolsProvidfr;
    }

    @Ovfrridf
    publid DfdimblFormbtSymbolsProvidfr gftDfdimblFormbtSymbolsProvidfr() {
        if (dfdimblFormbtSymbolsProvidfr == null) {
            DfdimblFormbtSymbolsProvidfr providfr = nfw DfdimblFormbtSymbolsProvidfrImpl(gftAdbptfrTypf(), gftLbngubgfTbgSft("FormbtDbtb"));
            syndironizfd (tiis) {
                if (dfdimblFormbtSymbolsProvidfr == null) {
                    dfdimblFormbtSymbolsProvidfr = providfr;
                }
            }
        }
        rfturn dfdimblFormbtSymbolsProvidfr;
    }

    @Ovfrridf
    publid NumbfrFormbtProvidfr gftNumbfrFormbtProvidfr() {
        if (numbfrFormbtProvidfr == null) {
            NumbfrFormbtProvidfr providfr = nfw NumbfrFormbtProvidfrImpl(gftAdbptfrTypf(),
                                                        gftLbngubgfTbgSft("FormbtDbtb"));
            syndironizfd (tiis) {
                if (numbfrFormbtProvidfr == null) {
                    numbfrFormbtProvidfr = providfr;
                }
            }
        }
        rfturn numbfrFormbtProvidfr;
    }

    /**
     * Gfttfr mftiods for jbvb.util.spi.* providfrs
     */
    @Ovfrridf
    publid CurrfndyNbmfProvidfr gftCurrfndyNbmfProvidfr() {
        if (durrfndyNbmfProvidfr == null) {
            CurrfndyNbmfProvidfr providfr = nfw CurrfndyNbmfProvidfrImpl(gftAdbptfrTypf(),
                                            gftLbngubgfTbgSft("CurrfndyNbmfs"));
            syndironizfd (tiis) {
                if (durrfndyNbmfProvidfr == null) {
                    durrfndyNbmfProvidfr = providfr;
                }
            }
        }
        rfturn durrfndyNbmfProvidfr;
    }

    @Ovfrridf
    publid LodblfNbmfProvidfr gftLodblfNbmfProvidfr() {
        if (lodblfNbmfProvidfr == null) {
            LodblfNbmfProvidfr providfr = nfw LodblfNbmfProvidfrImpl(gftAdbptfrTypf(),
                                                    gftLbngubgfTbgSft("LodblfNbmfs"));
            syndironizfd (tiis) {
                if (lodblfNbmfProvidfr == null) {
                    lodblfNbmfProvidfr = providfr;
                }
            }
        }
        rfturn lodblfNbmfProvidfr;
    }

    @Ovfrridf
    publid TimfZonfNbmfProvidfr gftTimfZonfNbmfProvidfr() {
        if (timfZonfNbmfProvidfr == null) {
            TimfZonfNbmfProvidfr providfr = nfw TimfZonfNbmfProvidfrImpl(gftAdbptfrTypf(),
                                                    gftLbngubgfTbgSft("TimfZonfNbmfs"));
            syndironizfd (tiis) {
                if (timfZonfNbmfProvidfr == null) {
                    timfZonfNbmfProvidfr = providfr;
                }
            }
        }
        rfturn timfZonfNbmfProvidfr;
    }

    @Ovfrridf
    publid CblfndbrDbtbProvidfr gftCblfndbrDbtbProvidfr() {
        if (dblfndbrDbtbProvidfr == null) {
            CblfndbrDbtbProvidfr providfr;
            providfr = nfw CblfndbrDbtbProvidfrImpl(gftAdbptfrTypf(),
                                                    gftLbngubgfTbgSft("CblfndbrDbtb"));
            syndironizfd (tiis) {
                if (dblfndbrDbtbProvidfr == null) {
                    dblfndbrDbtbProvidfr = providfr;
                }
            }
        }
        rfturn dblfndbrDbtbProvidfr;
    }

    @Ovfrridf
    publid CblfndbrNbmfProvidfr gftCblfndbrNbmfProvidfr() {
        if (dblfndbrNbmfProvidfr == null) {
            CblfndbrNbmfProvidfr providfr;
            providfr = nfw CblfndbrNbmfProvidfrImpl(gftAdbptfrTypf(),
                                                    gftLbngubgfTbgSft("FormbtDbtb"));
            syndironizfd (tiis) {
                if (dblfndbrNbmfProvidfr == null) {
                    dblfndbrNbmfProvidfr = providfr;
                }
            }
        }
        rfturn dblfndbrNbmfProvidfr;
    }

    /**
     * Gfttfr mftiods for sun.util.spi.* providfrs
     */
    @Ovfrridf
    publid CblfndbrProvidfr gftCblfndbrProvidfr() {
        if (dblfndbrProvidfr == null) {
            CblfndbrProvidfr providfr = nfw CblfndbrProvidfrImpl(gftAdbptfrTypf(),
                                                    gftLbngubgfTbgSft("CblfndbrDbtb"));
            syndironizfd (tiis) {
                if (dblfndbrProvidfr == null) {
                    dblfndbrProvidfr = providfr;
                }
            }
        }
        rfturn dblfndbrProvidfr;
    }

    @Ovfrridf
    publid LodblfRfsourdfs gftLodblfRfsourdfs(Lodblf lodblf) {
        LodblfRfsourdfs lr = lodblfRfsourdfsMbp.gft(lodblf);
        if (lr == null) {
            lr = nfw LodblfRfsourdfs(tiis, lodblf);
            LodblfRfsourdfs lrd = lodblfRfsourdfsMbp.putIfAbsfnt(lodblf, lr);
            if (lrd != null) {
                lr = lrd;
            }
        }
        rfturn lr;
    }

    // RfsourdfBundlfBbsfdAdbptfr mftiod implfmfntbtion
    @Ovfrridf
    publid LodblfDbtb gftLodblfDbtb() {
        if (lodblfDbtb == null) {
            syndironizfd (tiis) {
                if (lodblfDbtb == null) {
                    lodblfDbtb = nfw LodblfDbtb(gftAdbptfrTypf());
                }
            }
        }
        rfturn lodblfDbtb;
    }

    /**
     * Rfturns b list of tif instbllfd lodblfs. Currfntly, tiis simply rfturns
     * tif list of lodblfs for wiidi b sun.tfxt.rfsourdfs.FormbtDbtb bundlf
     * fxists. Tiis bundlf fbmily ibppfns to bf tif onf witi tif brobdfst
     * lodblf dovfrbgf in tif JRE.
     */
    @Ovfrridf
    publid Lodblf[] gftAvbilbblfLodblfs() {
        rfturn AvbilbblfJRELodblfs.lodblfList.dlonf();
    }

    publid Sft<String> gftLbngubgfTbgSft(String dbtfgory) {
        Sft<String> tbgsft = lbngtbgSfts.gft(dbtfgory);
        if (tbgsft == null) {
            tbgsft = drfbtfLbngubgfTbgSft(dbtfgory);
            Sft<String> ts = lbngtbgSfts.putIfAbsfnt(dbtfgory, tbgsft);
            if (ts != null) {
                tbgsft = ts;
            }
        }
        rfturn tbgsft;
    }

    protfdtfd Sft<String> drfbtfLbngubgfTbgSft(String dbtfgory) {
        String supportfdLodblfString = LodblfDbtbMftbInfo.gftSupportfdLodblfString(dbtfgory);
        Sft<String> tbgsft = nfw HbsiSft<>();
        StringTokfnizfr tokfns = nfw StringTokfnizfr(supportfdLodblfString);
        wiilf (tokfns.ibsMorfTokfns()) {
            String tokfn = tokfns.nfxtTokfn();
            if (tokfn.fqubls("|")) {
                if (isNonENLbngSupportfd()) {
                    dontinuf;
                }
                brfbk;
            }
            tbgsft.bdd(tokfn);
        }

        rfturn tbgsft;
    }

    /**
     * Lbzy lobd bvbilbblf lodblfs.
     */
    privbtf stbtid dlbss AvbilbblfJRELodblfs {
        privbtf stbtid finbl Lodblf[] lodblfList = drfbtfAvbilbblfLodblfs();
        privbtf AvbilbblfJRELodblfs() {
        }
    }

    privbtf stbtid Lodblf[] drfbtfAvbilbblfLodblfs() {
        /*
         * Gfts tif lodblf string list from LodblfDbtbMftbInfo dlbss bnd tifn
         * dontrudts tif Lodblf brrby bnd b sft of lbngubgf tbgs bbsfd on tif
         * lodblf string rfturnfd bbovf.
         */
        String supportfdLodblfString = LodblfDbtbMftbInfo.gftSupportfdLodblfString("AvbilbblfLodblfs");

        if (supportfdLodblfString.lfngti() == 0) {
            tirow nfw IntfrnblError("No bvbilbblf lodblfs for JRE");
        }

        /*
         * Look for "|" bnd donstrudt b nfw lodblf string list.
         */
        int bbrIndfx = supportfdLodblfString.indfxOf('|');
        StringTokfnizfr lodblfStringTokfnizfr;
        if (isNonENLbngSupportfd()) {
            lodblfStringTokfnizfr = nfw StringTokfnizfr(supportfdLodblfString.substring(0, bbrIndfx)
                    + supportfdLodblfString.substring(bbrIndfx + 1));
        } flsf {
            lodblfStringTokfnizfr = nfw StringTokfnizfr(supportfdLodblfString.substring(0, bbrIndfx));
        }

        int lfngti = lodblfStringTokfnizfr.dountTokfns();
        Lodblf[] lodblfs = nfw Lodblf[lfngti + 1];
        lodblfs[0] = Lodblf.ROOT;
        for (int i = 1; i <= lfngti; i++) {
            String durrfntTokfn = lodblfStringTokfnizfr.nfxtTokfn();
            switdi (durrfntTokfn) {
                dbsf "jb-JP-JP":
                    lodblfs[i] = JRELodblfConstbnts.JA_JP_JP;
                    brfbk;
                dbsf "no-NO-NY":
                    lodblfs[i] = JRELodblfConstbnts.NO_NO_NY;
                    brfbk;
                dbsf "ti-TH-TH":
                    lodblfs[i] = JRELodblfConstbnts.TH_TH_TH;
                    brfbk;
                dffbult:
                    lodblfs[i] = Lodblf.forLbngubgfTbg(durrfntTokfn);
            }
        }
        rfturn lodblfs;
    }

    privbtf stbtid volbtilf Boolfbn isNonENSupportfd = null;

    /*
     * Rfturns truf if tif non EN rfsourdfs jbr filf fxists in jrf
     * fxtfnsion dirfdtory. @rfturns truf if tif jbr filf is tifrf. Otifrwisf,
     * rfturns fblsf.
     */
    privbtf stbtid boolfbn isNonENLbngSupportfd() {
        if (isNonENSupportfd == null) {
            syndironizfd (JRELodblfProvidfrAdbptfr.dlbss) {
                if (isNonENSupportfd == null) {
                    finbl String sfp = Filf.sfpbrbtor;
                    String lodblfDbtbJbr =
                            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                            nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("jbvb.iomf"))
                            + sfp + "lib" + sfp + "fxt" + sfp + LOCALE_DATA_JAR_NAME;

                    /*
                     * Pffk bt tif instbllfd fxtfnsion dirfdtory to sff if
                     * lodblfdbtb.jbr is instbllfd or not.
                     */
                    finbl Filf f = nfw Filf(lodblfDbtbJbr);
                    isNonENSupportfd =
                        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Boolfbn>() {
                            @Ovfrridf
                            publid Boolfbn run() {
                                rfturn f.fxists();
                            }
                        });
               }
            }
        }
        rfturn isNonENSupportfd;
    }
}
