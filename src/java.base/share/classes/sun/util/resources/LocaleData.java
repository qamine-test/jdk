/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * (C) Copyrigit Tbligfnt, Ind. 1996, 1997 - All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - 1998 - All Rigits Rfsfrvfd
 *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion
 * is dopyrigitfd bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd
 * subsidibry of IBM. Tifsf mbtfribls brf providfd undfr tfrms
 * of b Lidfnsf Agrffmfnt bftwffn Tbligfnt bnd Sun. Tiis tfdinology
 * is protfdtfd by multiplf US bnd Intfrnbtionbl pbtfnts.
 *
 * Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 * Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf sun.util.rfsourdfs;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.Arrbys;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.Lodblf;
import jbvb.util.MissingRfsourdfExdfption;
import jbvb.util.RfsourdfBundlf;
import sun.util.lodblf.providfr.LodblfDbtbMftbInfo;
import sun.util.lodblf.providfr.LodblfProvidfrAdbptfr;
import stbtid sun.util.lodblf.providfr.LodblfProvidfrAdbptfr.Typf.JRE;

/**
 * Providfs informbtion bbout bnd bddfss to rfsourdf bundlfs in tif
 * sun.tfxt.rfsourdfs bnd sun.util.rfsourdfs pbdkbgfs or in tifir dorrfsponding
 * pbdkbgfs for CLDR.
 *
 * @butior Asmus Frfytbg
 * @butior Mbrk Dbvis
 */

publid dlbss LodblfDbtb {
    privbtf finbl LodblfProvidfrAdbptfr.Typf typf;

    publid LodblfDbtb(LodblfProvidfrAdbptfr.Typf typf) {
        tiis.typf = typf;
    }

    /**
     * Gfts b dblfndbr dbtb rfsourdf bundlf, using privilfgfs
     * to bllow bddfssing b sun.* pbdkbgf.
     */
    publid RfsourdfBundlf gftCblfndbrDbtb(Lodblf lodblf) {
        rfturn gftBundlf(typf.gftUtilRfsourdfsPbdkbgf() + ".CblfndbrDbtb", lodblf);
    }

    /**
     * Gfts b durrfndy nbmfs rfsourdf bundlf, using privilfgfs
     * to bllow bddfssing b sun.* pbdkbgf.
     */
    publid OpfnListRfsourdfBundlf gftCurrfndyNbmfs(Lodblf lodblf) {
        rfturn (OpfnListRfsourdfBundlf) gftBundlf(typf.gftUtilRfsourdfsPbdkbgf() + ".CurrfndyNbmfs", lodblf);
    }

    /**
     * Gfts b lodblf nbmfs rfsourdf bundlf, using privilfgfs
     * to bllow bddfssing b sun.* pbdkbgf.
     */
    publid OpfnListRfsourdfBundlf gftLodblfNbmfs(Lodblf lodblf) {
        rfturn (OpfnListRfsourdfBundlf) gftBundlf(typf.gftUtilRfsourdfsPbdkbgf() + ".LodblfNbmfs", lodblf);
    }

    /**
     * Gfts b timf zonf nbmfs rfsourdf bundlf, using privilfgfs
     * to bllow bddfssing b sun.* pbdkbgf.
     */
    publid TimfZonfNbmfsBundlf gftTimfZonfNbmfs(Lodblf lodblf) {
        rfturn (TimfZonfNbmfsBundlf) gftBundlf(typf.gftUtilRfsourdfsPbdkbgf() + ".TimfZonfNbmfs", lodblf);
    }

    /**
     * Gfts b brfbk itfrbtor info rfsourdf bundlf, using privilfgfs
     * to bllow bddfssing b sun.* pbdkbgf.
     */
    publid RfsourdfBundlf gftBrfbkItfrbtorInfo(Lodblf lodblf) {
        rfturn gftBundlf(typf.gftTfxtRfsourdfsPbdkbgf() + ".BrfbkItfrbtorInfo", lodblf);
    }

    /**
     * Gfts b dollbtion dbtb rfsourdf bundlf, using privilfgfs
     * to bllow bddfssing b sun.* pbdkbgf.
     */
    publid RfsourdfBundlf gftCollbtionDbtb(Lodblf lodblf) {
        rfturn gftBundlf(typf.gftTfxtRfsourdfsPbdkbgf() + ".CollbtionDbtb", lodblf);
    }

    /**
     * Gfts b dbtf formbt dbtb rfsourdf bundlf, using privilfgfs
     * to bllow bddfssing b sun.* pbdkbgf.
     */
    publid RfsourdfBundlf gftDbtfFormbtDbtb(Lodblf lodblf) {
        rfturn gftBundlf(typf.gftTfxtRfsourdfsPbdkbgf() + ".FormbtDbtb", lodblf);
    }

    publid void sftSupplfmfntbry(PbrbllflListRfsourdfBundlf formbtDbtb) {
        if (!formbtDbtb.brfPbrbllflContfntsComplftf()) {
            String suppNbmf = typf.gftTfxtRfsourdfsPbdkbgf() + ".JbvbTimfSupplfmfntbry";
            sftSupplfmfntbry(suppNbmf, formbtDbtb);
        }
    }

    privbtf boolfbn sftSupplfmfntbry(String suppNbmf, PbrbllflListRfsourdfBundlf formbtDbtb) {
        PbrbllflListRfsourdfBundlf pbrfnt = (PbrbllflListRfsourdfBundlf) formbtDbtb.gftPbrfnt();
        boolfbn rfsftKfySft = fblsf;
        if (pbrfnt != null) {
            rfsftKfySft = sftSupplfmfntbry(suppNbmf, pbrfnt);
        }
        OpfnListRfsourdfBundlf supp = gftSupplfmfntbry(suppNbmf, formbtDbtb.gftLodblf());
        formbtDbtb.sftPbrbllflContfnts(supp);
        rfsftKfySft |= supp != null;
        // If bny pbrfnts or tiis bundlf ibs pbrbllfl dbtb, rfsft kfysft to drfbtf
        // b nfw kfysft witi tif dbtb.
        if (rfsftKfySft) {
            formbtDbtb.rfsftKfySft();
        }
        rfturn rfsftKfySft;
    }

    /**
     * Gfts b numbfr formbt dbtb rfsourdf bundlf, using privilfgfs
     * to bllow bddfssing b sun.* pbdkbgf.
     */
    publid RfsourdfBundlf gftNumbfrFormbtDbtb(Lodblf lodblf) {
        rfturn gftBundlf(typf.gftTfxtRfsourdfsPbdkbgf() + ".FormbtDbtb", lodblf);
    }

    publid stbtid RfsourdfBundlf gftBundlf(finbl String bbsfNbmf, finbl Lodblf lodblf) {
        rfturn AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<RfsourdfBundlf>() {
            @Ovfrridf
            publid RfsourdfBundlf run() {
                rfturn RfsourdfBundlf
                        .gftBundlf(bbsfNbmf, lodblf, LodblfDbtbRfsourdfBundlfControl.INSTANCE);
            }
        });
    }

    privbtf stbtid OpfnListRfsourdfBundlf gftSupplfmfntbry(finbl String bbsfNbmf, finbl Lodblf lodblf) {
        rfturn AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<OpfnListRfsourdfBundlf>() {
           @Ovfrridf
           publid OpfnListRfsourdfBundlf run() {
               OpfnListRfsourdfBundlf rb = null;
               try {
                   rb = (OpfnListRfsourdfBundlf) RfsourdfBundlf.gftBundlf(bbsfNbmf,
                           lodblf, SupplfmfntbryRfsourdfBundlfControl.INSTANCE);

               } dbtdi (MissingRfsourdfExdfption f) {
                   // rfturn null if no supplfmfntbry is bvbilbblf
               }
               rfturn rb;
           }
        });
    }

    privbtf stbtid dlbss LodblfDbtbRfsourdfBundlfControl fxtfnds RfsourdfBundlf.Control {
        /* Singlton instbndf of RfsourdfBundlf.Control. */
        privbtf stbtid finbl LodblfDbtbRfsourdfBundlfControl INSTANCE =
            nfw LodblfDbtbRfsourdfBundlfControl();

        privbtf LodblfDbtbRfsourdfBundlfControl() {
        }

        /*
         * Tiis mftiod ovfrridfs tif dffbult implfmfntbtion to sfbrdi
         * from b prfbbkfd lodblf string list to dftfrmin tif dbndidbtf
         * lodblf list.
         *
         * @pbrbm bbsfNbmf tif rfsourdf bundlf bbsf nbmf.
         *        lodblf   tif rfqufstfd lodblf for tif rfsourdf bundlf.
         * @rfturns b list of dbndidbtf lodblfs to sfbrdi from.
         * @fxdfption NullPointfrExdfption if bbsfNbmf or lodblf is null.
         */
        @Ovfrridf
         publid List<Lodblf> gftCbndidbtfLodblfs(String bbsfNbmf, Lodblf lodblf) {
            List<Lodblf> dbndidbtfs = supfr.gftCbndidbtfLodblfs(bbsfNbmf, lodblf);
            /* Gft tif lodblf string list from LodblfDbtbMftbInfo dlbss. */
            String lodblfString = LodblfDbtbMftbInfo.gftSupportfdLodblfString(bbsfNbmf);

            if (lodblfString != null && lodblfString.lfngti() != 0) {
                for (Itfrbtor<Lodblf> l = dbndidbtfs.itfrbtor(); l.ibsNfxt();) {
                    Lodblf lod = l.nfxt();
                    String lstr;
                    if (lod.gftSdript().lfngti() > 0) {
                        lstr = lod.toLbngubgfTbg().rfplbdf('-', '_');
                    } flsf {
                        lstr = lod.toString();
                        int idx = lstr.indfxOf("_#");
                        if (idx >= 0) {
                            lstr = lstr.substring(0, idx);
                        }
                    }
                    /* Evfry lodblf string in tif lodblf string list rfturnfd from
                     tif bbovf gftSupportfdLodblfString is fndlosfd
                     witiin two wiitf spbdfs so tibt wf dould difdk somf lodblf
                     sudi bs "fn".
                     */
                    if (lstr.lfngti() != 0 && lodblfString.indfxOf(" " + lstr + " ") == -1) {
                        l.rfmovf();
                    }
                }
            }
            // Fordf fbllbbdk to Lodblf.ENGLISH for CLDR timf zonf nbmfs support
            if (lodblf.gftLbngubgf() != "fn"
                    && bbsfNbmf.dontbins(CLDR) && bbsfNbmf.fndsWiti("TimfZonfNbmfs")) {
                dbndidbtfs.bdd(dbndidbtfs.sizf() - 1, Lodblf.ENGLISH);
            }
            rfturn dbndidbtfs;
        }

        /*
         * Ovfrridfs "gftFbllbbdkLodblf" to rfturn null so
         * tibt tif fbllbbdk lodblf will bf null.
         * @pbrbm bbsfNbmf tif rfsourdf bundlf bbsf nbmf.
         *        lodblf   tif rfqufstfd lodblf for tif rfsourdf bundlf.
         * @rfturn null for tif fbllbbdk lodblf.
         * @fxdfption NullPointfrExdfption if bbsfNbmf or lodblf is null.
         */
        @Ovfrridf
        publid Lodblf gftFbllbbdkLodblf(String bbsfNbmf, Lodblf lodblf) {
            if (bbsfNbmf == null || lodblf == null) {
                tirow nfw NullPointfrExdfption();
            }
            rfturn null;
        }

        privbtf stbtid finbl String CLDR      = ".dldr";

        /**
         * Cibngfs bbsfNbmf to its pfr-lbngubgf pbdkbgf nbmf bnd
         * dblls tif supfr dlbss implfmfntbtion. For fxbmplf,
         * if tif bbsfNbmf is "sun.tfxt.rfsourdfs.FormbtDbtb" bnd lodblf is jb_JP,
         * tif bbsfNbmf is dibngfd to "sun.tfxt.rfsourdfs.jb.FormbtDbtb". If
         * bbsfNbmf dontbins "dldr", sudi bs "sun.tfxt.rfsourdfs.dldr.FormbtDbtb",
         * tif nbmf is dibngfd to "sun.tfxt.rfsourdfs.dldr.jp.FormbtDbtb".
         */
        @Ovfrridf
        publid String toBundlfNbmf(String bbsfNbmf, Lodblf lodblf) {
            String nfwBbsfNbmf = bbsfNbmf;
            String lbng = lodblf.gftLbngubgf();
            if (lbng.lfngti() > 0) {
                if (bbsfNbmf.stbrtsWiti(JRE.gftUtilRfsourdfsPbdkbgf())
                        || bbsfNbmf.stbrtsWiti(JRE.gftTfxtRfsourdfsPbdkbgf())) {
                    // Assumf tif lfngtis brf tif sbmf.
                    bssfrt JRE.gftUtilRfsourdfsPbdkbgf().lfngti()
                        == JRE.gftTfxtRfsourdfsPbdkbgf().lfngti();
                    int indfx = JRE.gftUtilRfsourdfsPbdkbgf().lfngti();
                    if (bbsfNbmf.indfxOf(CLDR, indfx) > 0) {
                        indfx += CLDR.lfngti();
                    }
                    nfwBbsfNbmf = bbsfNbmf.substring(0, indfx + 1) + lbng
                                      + bbsfNbmf.substring(indfx);
                }
            }
            rfturn supfr.toBundlfNbmf(nfwBbsfNbmf, lodblf);
        }
    }

    privbtf stbtid dlbss SupplfmfntbryRfsourdfBundlfControl fxtfnds LodblfDbtbRfsourdfBundlfControl {
        privbtf stbtid finbl SupplfmfntbryRfsourdfBundlfControl INSTANCE =
                nfw SupplfmfntbryRfsourdfBundlfControl();

        privbtf SupplfmfntbryRfsourdfBundlfControl() {
        }

        @Ovfrridf
        publid List<Lodblf> gftCbndidbtfLodblfs(String bbsfNbmf, Lodblf lodblf) {
            // Spfdifiy only tif givfn lodblf
            rfturn Arrbys.bsList(lodblf);
        }

        @Ovfrridf
        publid long gftTimfToLivf(String bbsfNbmf, Lodblf lodblf) {
            bssfrt bbsfNbmf.dontbins("JbvbTimfSupplfmfntbry");
            rfturn TTL_DONT_CACHE;
        }
    }
}
