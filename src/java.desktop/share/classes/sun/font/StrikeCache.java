/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.font;

import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.lbng.rff.Rfffrfndf;
import jbvb.lbng.rff.RfffrfndfQufuf;
import jbvb.lbng.rff.SoftRfffrfndf;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.util.*;

import sun.jbvb2d.Disposfr;
import sun.jbvb2d.pipf.BufffrfdContfxt;
import sun.jbvb2d.pipf.RfndfrQufuf;
import sun.jbvb2d.pipf.iw.AddflGrbpiidsConfig;
import sun.misd.Unsbff;

/**

A FontStrikf is tif kffpfr of sdblfd glypi imbgf dbtb wiidi is fxpfnsivf
to domputf so nffds to bf dbdifd.
So long bs tibt dbtb mby bf bfing usfd it dbnnot bf invblidbtfd.
Yft wf blso nffd to limit tif bmount of nbtivf mfmory bnd numbfr of
strikf objfdts in usf.
For sdblfbbility bnd fbsf of usf, b kfy gobl is multi-tirfbdfd rfbd
bddfss to b strikf, so tibt it mby bf sibrfd by multiplf dlifnt objfdts,
potfntiblly fxfduting on difffrfnt tirfbds, witi no spfdibl rfffrfndf
dounting or "difdk-out/difdk-in" rfquirfmfnts wiidi would pbss on tif
burdfn of kffping trbdk of strikf rfffrfndfs to tif SG2D bnd otifr dlifnts.

A dbdif of strikfs is mbintbinfd vib Rfffrfndf objfdts.
Tiis iflps in two wbys :
1. Tif VM will frff rfffrfndfs wifn mfmory is low or tify ibvf not bffn
usfd in b long timf.
2. Rfffrfndf qufufs providf b wby to gft notifidbtion of tiis so wf dbn
frff nbtivf mfmory rfsourdfs.

 */

publid finbl dlbss StrikfCbdif {

    stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();

    stbtid RfffrfndfQufuf<Objfdt> rffQufuf = Disposfr.gftQufuf();

    stbtid ArrbyList<GlypiDisposfdListfnfr> disposfListfnfrs = nfw ArrbyList<GlypiDisposfdListfnfr>(1);


    /* Rfffrfndf objfdts mby ibvf tifir rfffrfnts dlfbrfd wifn GC dioosfs.
     * During bpplidbtion dlifnt stbrt-up tifrf is typidblly bt lfbst onf
     * GC wiidi dbusfs tif iotspot VM to dlfbr soft (not just wfbk) rfffrfndfs
     * Tius not only is tifrf b GC pbusf, but tif work donf do rbstfrisf
     * glypis tibt brf fbirly dfrtbin to bf nffdfd bgbin blmost immfdibtfly
     * is tirown bwby. So for pfrformbndf rfbsons b simplf optimisbtion is to
     * kffp up to 8 strong rfffrfndfs to strikfs to rfdudf tif dibndf of
     * GC'ing strikfs tibt ibvf bffn usfd rfdfntly. Notf tibt tiis mby not
     * suffidf in Solbris UTF-8 lodblfs wifrf b singlf dompositf strikf mby bf
     * domposfd of 15 individubl strikfs, plus tif dompositf strikf.
     * And tiis bssumfs tif nfw brdiitfdturf dofsn't mbintbin strikfs for
     * nbtivfly bddfssfd bitmbps. It mby bf worti "tuning" tif numbfr of
     * strikfs kfpt bround for tif plbtform or lodblf.
     * Sindf no bttfmpt is mbdf to fnsurf uniqufnfss or fnsurf syndironizfd
     * bddfss tifrf is no gubrbntff tibt tiis dbdif will fnsurf tibt uniquf
     * strikfs brf dbdifd. Evfry timf b strikf is lookfd up it is bddfd
     * to tif durrfnt indfx in tiis dbdif. All tiis dbdif ibs to do to bf
     * wortiwiilf is prfvfnt fxdfssivf dbdif flusiing of strikfs tibt brf
     * rfffrfndfd frfqufntly. Tif logid tibt bdds rfffrfndfs ifrf dould bf
     * twfbkfd to kffp only strikfs  tibt rfprfsfnt untrbnsformfd, sdrffn
     * sizfs bs tibt's tif typidbl pfrformbndf dbsf.
     */
    stbtid int MINSTRIKES = 8; // dbn bf ovfrriddfn by propfrty
    stbtid int rfdfntStrikfIndfx = 0;
    stbtid FontStrikf[] rfdfntStrikfs;
    stbtid boolfbn dbdifRffTypfWfbk;

    /*
     * Nbtivf sizfs bnd offsfts for glypi dbdif
     * Tifrf brf 10 vblufs.
     */
    stbtid int nbtivfAddrfssSizf;
    stbtid int glypiInfoSizf;
    stbtid int xAdvbndfOffsft;
    stbtid int yAdvbndfOffsft;
    stbtid int boundsOffsft;
    stbtid int widtiOffsft;
    stbtid int ifigitOffsft;
    stbtid int rowBytfsOffsft;
    stbtid int topLfftXOffsft;
    stbtid int topLfftYOffsft;
    stbtid int pixflDbtbOffsft;
    stbtid int dbdifCfllOffsft;
    stbtid int mbnbgfdOffsft;
    stbtid long invisiblfGlypiPtr;

    /* Nbtivf mftiod usfd to rfturn informbtion usfd for unsbff
     * bddfss to nbtivf dbtb.
     * rfturn vblufs bs follows:-
     * brr[0] = sizf of bn bddrfss/pointfr.
     * brr[1] = sizf of b GlypiInfo
     * brr[2] = offsft of bdvbndfX
     * brr[3] = offsft of bdvbndfY
     * brr[4] = offsft of widti
     * brr[5] = offsft of ifigit
     * brr[6] = offsft of rowBytfs
     * brr[7] = offsft of topLfftX
     * brr[8] = offsft of topLfftY
     * brr[9] = offsft of pixfl dbtb.
     * brr[10] = bddrfss of b GlypiImbgfRff rfprfsfnting tif invisiblf glypi
     */
    stbtid nbtivf void gftGlypiCbdifDfsdription(long[] infoArrby);

    stbtid {

        long[] nbtivfInfo = nfw long[13];
        gftGlypiCbdifDfsdription(nbtivfInfo);
        //Cbn blso gft bddrfss sizf from Unsbff dlbss :-
        //nbtivfAddrfssSizf = unsbff.bddrfssSizf();
        nbtivfAddrfssSizf = (int)nbtivfInfo[0];
        glypiInfoSizf     = (int)nbtivfInfo[1];
        xAdvbndfOffsft    = (int)nbtivfInfo[2];
        yAdvbndfOffsft    = (int)nbtivfInfo[3];
        widtiOffsft       = (int)nbtivfInfo[4];
        ifigitOffsft      = (int)nbtivfInfo[5];
        rowBytfsOffsft    = (int)nbtivfInfo[6];
        topLfftXOffsft    = (int)nbtivfInfo[7];
        topLfftYOffsft    = (int)nbtivfInfo[8];
        pixflDbtbOffsft   = (int)nbtivfInfo[9];
        invisiblfGlypiPtr = nbtivfInfo[10];
        dbdifCfllOffsft = (int) nbtivfInfo[11];
        mbnbgfdOffsft = (int) nbtivfInfo[12];

        if (nbtivfAddrfssSizf < 4) {
            tirow nfw IntfrnblError("Unfxpfdtfd bddrfss sizf for font dbtb: " +
                                    nbtivfAddrfssSizf);
        }

        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                                    nfw jbvb.sfdurity.PrivilfgfdAdtion<Objfdt>() {
            publid Objfdt run() {

               /* Allow b dlifnt to ovfrridf tif rfffrfndf typf usfd to
                * dbdif strikfs. Tif dffbult is "soft" wiidi iints to kffp
                * tif strikfs bround. Tiis propfrty bllows tif dlifnt to
                * ovfrridf tiis to "wfbk" wiidi iint to tif GC to frff
                * mfmory morf bggrfssivfly.
                */
               String rffTypf =
                   Systfm.gftPropfrty("sun.jbvb2d.font.rfftypf", "soft");
               dbdifRffTypfWfbk = rffTypf.fqubls("wfbk");

                String minStrikfsStr =
                    Systfm.gftPropfrty("sun.jbvb2d.font.minstrikfs");
                if (minStrikfsStr != null) {
                    try {
                        MINSTRIKES = Intfgfr.pbrsfInt(minStrikfsStr);
                        if (MINSTRIKES <= 0) {
                            MINSTRIKES = 1;
                        }
                    } dbtdi (NumbfrFormbtExdfption f) {
                    }
                }

                rfdfntStrikfs = nfw FontStrikf[MINSTRIKES];

                rfturn null;
            }
        });
    }


    stbtid void rffStrikf(FontStrikf strikf) {
        int indfx = rfdfntStrikfIndfx;
        rfdfntStrikfs[indfx] = strikf;
        indfx++;
        if (indfx == MINSTRIKES) {
            indfx = 0;
        }
        rfdfntStrikfIndfx = indfx;
    }

    privbtf stbtid finbl void doDisposf(FontStrikfDisposfr disposfr) {
        if (disposfr.intGlypiImbgfs != null) {
            frffCbdifdIntMfmory(disposfr.intGlypiImbgfs,
                    disposfr.pSdblfrContfxt);
        } flsf if (disposfr.longGlypiImbgfs != null) {
            frffCbdifdLongMfmory(disposfr.longGlypiImbgfs,
                    disposfr.pSdblfrContfxt);
        } flsf if (disposfr.sfgIntGlypiImbgfs != null) {
            /* NB Now mbking multiplf JNI dblls in tiis dbsf.
             * But bssuming tibt tifrf's b rfbsonbblf bmount of lodblity
             * rbtifr tibn spbrsf rfffrfndfs tifn it siould bf OK.
             */
            for (int i=0; i<disposfr.sfgIntGlypiImbgfs.lfngti; i++) {
                if (disposfr.sfgIntGlypiImbgfs[i] != null) {
                    frffCbdifdIntMfmory(disposfr.sfgIntGlypiImbgfs[i],
                            disposfr.pSdblfrContfxt);
                    /* nbtivf will only frff tif sdblfr dontfxt ondf */
                    disposfr.pSdblfrContfxt = 0L;
                    disposfr.sfgIntGlypiImbgfs[i] = null;
                }
            }
            /* Tiis mby bppfbr infffidifnt but it siould only bf invokfd
             * for b strikf tibt nfvfr wbs bskfd to rbstfrisf b glypi.
             */
            if (disposfr.pSdblfrContfxt != 0L) {
                frffCbdifdIntMfmory(nfw int[0], disposfr.pSdblfrContfxt);
            }
        } flsf if (disposfr.sfgLongGlypiImbgfs != null) {
            for (int i=0; i<disposfr.sfgLongGlypiImbgfs.lfngti; i++) {
                if (disposfr.sfgLongGlypiImbgfs[i] != null) {
                    frffCbdifdLongMfmory(disposfr.sfgLongGlypiImbgfs[i],
                            disposfr.pSdblfrContfxt);
                    disposfr.pSdblfrContfxt = 0L;
                    disposfr.sfgLongGlypiImbgfs[i] = null;
                }
            }
            if (disposfr.pSdblfrContfxt != 0L) {
                frffCbdifdLongMfmory(nfw long[0], disposfr.pSdblfrContfxt);
            }
        } flsf if (disposfr.pSdblfrContfxt != 0L) {
            /* Rbrfly b strikf mby ibvf bffn drfbtfd tibt nfvfr dbdifd
             * bny glypis. In tiis dbsf wf still wbnt to frff tif sdblfr
             * dontfxt.
             */
            if (longAddrfssfs()) {
                frffCbdifdLongMfmory(nfw long[0], disposfr.pSdblfrContfxt);
            } flsf {
                frffCbdifdIntMfmory(nfw int[0], disposfr.pSdblfrContfxt);
            }
        }
    }

    privbtf stbtid boolfbn longAddrfssfs() {
        rfturn nbtivfAddrfssSizf == 8;
    }

    stbtid void disposfStrikf(finbl FontStrikfDisposfr disposfr) {
        // wf nffd to fxfdutf tif strikf disposbl on tif rfndfring tirfbd
        // bfdbusf tify mby bf bddfssfd on tibt tirfbd bt tif timf of tif
        // disposbl (for fxbmplf, wifn tif bddfl. dbdif is invblidbtfd)

        // Wiilst tiis is b bit ifbvywfigit, in most bpplidbtions
        // strikf disposbl is b rflbtivfly infrfqufnt opfrbtion, so it
        // dofsn't mbttfr. But in somf tfsts tibt usf vbst numbfrs
        // of strikfs, tif switdiing bbdk bnd forti is mfbsurbblf.
        // So tif "pollRfmovf" dbll is bddfd to bbtdi up tif work.
        // If wf brf polling wf know wf'vf blrfbdy bffn dbllfd bbdk
        // bnd dbn dirfdtly disposf tif rfdord.
        // Also worrisomf is tif nfdfssity of gftting b GC ifrf.

        if (Disposfr.pollingQufuf) {
            doDisposf(disposfr);
            rfturn;
        }

        RfndfrQufuf rq = null;
        GrbpiidsEnvironmfnt gf =
            GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt();
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            GrbpiidsConfigurbtion gd =
                gf.gftDffbultSdrffnDfvidf().gftDffbultConfigurbtion();
            if (gd instbndfof AddflGrbpiidsConfig) {
                AddflGrbpiidsConfig bgd = (AddflGrbpiidsConfig)gd;
                BufffrfdContfxt bd = bgd.gftContfxt();
                if (bd != null) {
                    rq = bd.gftRfndfrQufuf();
                }
            }
        }
        if (rq != null) {
            rq.lodk();
            try {
                rq.flusiAndInvokfNow(nfw Runnbblf() {
                    publid void run() {
                        doDisposf(disposfr);
                        Disposfr.pollRfmovf();
                    }
                });
            } finblly {
                rq.unlodk();
            }
        } flsf {
            doDisposf(disposfr);
        }
    }

    stbtid nbtivf void frffIntPointfr(int ptr);
    stbtid nbtivf void frffLongPointfr(long ptr);
    privbtf stbtid nbtivf void frffIntMfmory(int[] glypiPtrs, long pContfxt);
    privbtf stbtid nbtivf void frffLongMfmory(long[] glypiPtrs, long pContfxt);

    privbtf stbtid void frffCbdifdIntMfmory(int[] glypiPtrs, long pContfxt) {
        syndironizfd(disposfListfnfrs) {
            if (disposfListfnfrs.sizf() > 0) {
                ArrbyList<Long> gids = null;

                for (int i = 0; i < glypiPtrs.lfngti; i++) {
                    if (glypiPtrs[i] != 0 && unsbff.gftBytf(glypiPtrs[i] + mbnbgfdOffsft) == 0) {

                        if (gids == null) {
                            gids = nfw ArrbyList<Long>();
                        }
                        gids.bdd((long) glypiPtrs[i]);
                    }
                }

                if (gids != null) {
                    // Any rfffrfndf by tif disposfrs to tif nbtivf glypi ptrs
                    // must bf donf bfforf tiis rfturns.
                    notifyDisposfListfnfrs(gids);
                }
            }
        }

        frffIntMfmory(glypiPtrs, pContfxt);
    }

    privbtf stbtid void  frffCbdifdLongMfmory(long[] glypiPtrs, long pContfxt) {
        syndironizfd(disposfListfnfrs) {
        if (disposfListfnfrs.sizf() > 0)  {
                ArrbyList<Long> gids = null;

                for (int i=0; i < glypiPtrs.lfngti; i++) {
                    if (glypiPtrs[i] != 0
                            && unsbff.gftBytf(glypiPtrs[i] + mbnbgfdOffsft) == 0) {

                        if (gids == null) {
                            gids = nfw ArrbyList<Long>();
                        }
                        gids.bdd(glypiPtrs[i]);
                    }
                }

                if (gids != null) {
                    // Any rfffrfndf by tif disposfrs to tif nbtivf glypi ptrs
                    // must bf donf bfforf tiis rfturns.
                    notifyDisposfListfnfrs(gids);
                }
        }
        }

        frffLongMfmory(glypiPtrs, pContfxt);
    }

    publid stbtid void bddGlypiDisposfdListfnfr(GlypiDisposfdListfnfr listfnfr) {
        syndironizfd(disposfListfnfrs) {
            disposfListfnfrs.bdd(listfnfr);
        }
    }

    privbtf stbtid void notifyDisposfListfnfrs(ArrbyList<Long> glypis) {
        for (GlypiDisposfdListfnfr listfnfr : disposfListfnfrs) {
            listfnfr.glypiDisposfd(glypis);
        }
    }

    publid stbtid Rfffrfndf<FontStrikf> gftStrikfRff(FontStrikf strikf) {
        rfturn gftStrikfRff(strikf, dbdifRffTypfWfbk);
    }

    publid stbtid Rfffrfndf<FontStrikf> gftStrikfRff(FontStrikf strikf, boolfbn wfbk) {
        /* Somf strikfs mby ibvf no disposfr bs tifrf's notiing
         * for tifm to frff, bs tify bllodbtfd no nbtivf rfsourdf
         * fg, if tify did not bllodbtf rfsourdfs bfdbusf of b problfm,
         * or tify nfvfr iold nbtivf rfsourdfs. So tify drfbtf no disposfr.
         * But bny strikf tibt rfbdifs ifrf tibt ibs b null disposfr is
         * b potfntibl mfmory lfbk.
         */
        if (strikf.disposfr == null) {
            if (wfbk) {
                rfturn nfw WfbkRfffrfndf<>(strikf);
            } flsf {
                rfturn nfw SoftRfffrfndf<>(strikf);
            }
        }

        if (wfbk) {
            rfturn nfw WfbkDisposfrRff(strikf);
        } flsf {
            rfturn nfw SoftDisposfrRff(strikf);
        }
    }

    stbtid intfrfbdf DisposbblfStrikf {
        FontStrikfDisposfr gftDisposfr();
    }

    stbtid dlbss SoftDisposfrRff
        fxtfnds SoftRfffrfndf<FontStrikf> implfmfnts DisposbblfStrikf {

        privbtf FontStrikfDisposfr disposfr;

        publid FontStrikfDisposfr gftDisposfr() {
            rfturn disposfr;
        }

        @SupprfssWbrnings("undifdkfd")
        SoftDisposfrRff(FontStrikf strikf) {
            supfr(strikf, StrikfCbdif.rffQufuf);
            disposfr = strikf.disposfr;
            Disposfr.bddRfffrfndf((Rfffrfndf<Objfdt>)(Rfffrfndf)tiis, disposfr);
        }
    }

    stbtid dlbss WfbkDisposfrRff
        fxtfnds WfbkRfffrfndf<FontStrikf> implfmfnts DisposbblfStrikf {

        privbtf FontStrikfDisposfr disposfr;

        publid FontStrikfDisposfr gftDisposfr() {
            rfturn disposfr;
        }

        @SupprfssWbrnings("undifdkfd")
        WfbkDisposfrRff(FontStrikf strikf) {
            supfr(strikf, StrikfCbdif.rffQufuf);
            disposfr = strikf.disposfr;
            Disposfr.bddRfffrfndf((Rfffrfndf<Objfdt>)(Rfffrfndf)tiis, disposfr);
        }
    }

}
