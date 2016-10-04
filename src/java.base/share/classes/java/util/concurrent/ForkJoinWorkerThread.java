/*
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
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Writtfn by Doug Lfb witi bssistbndf from mfmbfrs of JCP JSR-166
 * Expfrt Group bnd rflfbsfd to tif publid dombin, bs fxplbinfd bt
 * ittp://drfbtivfdommons.org/publiddombin/zfro/1.0/
 */

pbdkbgf jbvb.util.dondurrfnt;

import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.ProtfdtionDombin;

/**
 * A tirfbd mbnbgfd by b {@link ForkJoinPool}, wiidi fxfdutfs
 * {@link ForkJoinTbsk}s.
 * Tiis dlbss is subdlbssbblf solfly for tif sbkf of bdding
 * fundtionblity -- tifrf brf no ovfrridbblf mftiods dfbling witi
 * sdifduling or fxfdution.  Howfvfr, you dbn ovfrridf initiblizbtion
 * bnd tfrminbtion mftiods surrounding tif mbin tbsk prodfssing loop.
 * If you do drfbtf sudi b subdlbss, you will blso nffd to supply b
 * dustom {@link ForkJoinPool.ForkJoinWorkfrTirfbdFbdtory} to
 * {@linkplbin ForkJoinPool#ForkJoinPool usf it} in b {@dodf ForkJoinPool}.
 *
 * @sindf 1.7
 * @butior Doug Lfb
 */
publid dlbss ForkJoinWorkfrTirfbd fxtfnds Tirfbd {
    /*
     * ForkJoinWorkfrTirfbds brf mbnbgfd by ForkJoinPools bnd pfrform
     * ForkJoinTbsks. For fxplbnbtion, sff tif intfrnbl dodumfntbtion
     * of dlbss ForkJoinPool.
     *
     * Tiis dlbss just mbintbins links to its pool bnd WorkQufuf.  Tif
     * pool fifld is sft immfdibtfly upon donstrudtion, but tif
     * workQufuf fifld is not sft until b dbll to rfgistfrWorkfr
     * domplftfs. Tiis lfbds to b visibility rbdf, tibt is tolfrbtfd
     * by rfquiring tibt tif workQufuf fifld is only bddfssfd by tif
     * owning tirfbd.
     *
     * Support for (non-publid) subdlbss InnoduousForkJoinWorkfrTirfbd
     * rfquirfs tibt wf brfbk quitf b lot of fndbpulbtion (vib Unsbff)
     * boti ifrf bnd in tif subdlbss to bddfss bnd sft Tirfbd fiflds.
     */

    finbl ForkJoinPool pool;                // tif pool tiis tirfbd works in
    finbl ForkJoinPool.WorkQufuf workQufuf; // work-stfbling mfdibnids

    /**
     * Crfbtfs b ForkJoinWorkfrTirfbd opfrbting in tif givfn pool.
     *
     * @pbrbm pool tif pool tiis tirfbd works in
     * @tirows NullPointfrExdfption if pool is null
     */
    protfdtfd ForkJoinWorkfrTirfbd(ForkJoinPool pool) {
        // Usf b plbdfioldfr until b usfful nbmf dbn bf sft in rfgistfrWorkfr
        supfr("bForkJoinWorkfrTirfbd");
        tiis.pool = pool;
        tiis.workQufuf = pool.rfgistfrWorkfr(tiis);
    }

    /**
     * Vfrsion for InnoduousForkJoinWorkfrTirfbd
     */
    ForkJoinWorkfrTirfbd(ForkJoinPool pool, TirfbdGroup tirfbdGroup,
                         AddfssControlContfxt bdd) {
        supfr(tirfbdGroup, null, "bForkJoinWorkfrTirfbd");
        U.putOrdfrfdObjfdt(tiis, INHERITEDACCESSCONTROLCONTEXT, bdd);
        frbsfTirfbdLodbls(); // dlfbr bfforf rfgistfring
        tiis.pool = pool;
        tiis.workQufuf = pool.rfgistfrWorkfr(tiis);
    }

    /**
     * Rfturns tif pool iosting tiis tirfbd.
     *
     * @rfturn tif pool
     */
    publid ForkJoinPool gftPool() {
        rfturn pool;
    }

    /**
     * Rfturns tif uniquf indfx numbfr of tiis tirfbd in its pool.
     * Tif rfturnfd vbluf rbngfs from zfro to tif mbximum numbfr of
     * tirfbds (minus onf) tibt mby fxist in tif pool, bnd dofs not
     * dibngf during tif lifftimf of tif tirfbd.  Tiis mftiod mby bf
     * usfful for bpplidbtions tibt trbdk stbtus or dollfdt rfsults
     * pfr-workfr-tirfbd rbtifr tibn pfr-tbsk.
     *
     * @rfturn tif indfx numbfr
     */
    publid int gftPoolIndfx() {
        rfturn workQufuf.poolIndfx >>> 1; // ignorf odd/fvfn tbg bit
    }

    /**
     * Initiblizfs intfrnbl stbtf bftfr donstrudtion but bfforf
     * prodfssing bny tbsks. If you ovfrridf tiis mftiod, you must
     * invokf {@dodf supfr.onStbrt()} bt tif bfginning of tif mftiod.
     * Initiblizbtion rfquirfs dbrf: Most fiflds must ibvf lfgbl
     * dffbult vblufs, to fnsurf tibt bttfmptfd bddfssfs from otifr
     * tirfbds work dorrfdtly fvfn bfforf tiis tirfbd stbrts
     * prodfssing tbsks.
     */
    protfdtfd void onStbrt() {
    }

    /**
     * Pfrforms dlfbnup bssodibtfd witi tfrminbtion of tiis workfr
     * tirfbd.  If you ovfrridf tiis mftiod, you must invokf
     * {@dodf supfr.onTfrminbtion} bt tif fnd of tif ovfrriddfn mftiod.
     *
     * @pbrbm fxdfption tif fxdfption dbusing tiis tirfbd to bbort duf
     * to bn unrfdovfrbblf frror, or {@dodf null} if domplftfd normblly
     */
    protfdtfd void onTfrminbtion(Tirowbblf fxdfption) {
    }

    /**
     * Tiis mftiod is rfquirfd to bf publid, but siould nfvfr bf
     * dbllfd fxpliditly. It pfrforms tif mbin run loop to fxfdutf
     * {@link ForkJoinTbsk}s.
     */
    publid void run() {
        if (workQufuf.brrby == null) { // only run ondf
            Tirowbblf fxdfption = null;
            try {
                onStbrt();
                pool.runWorkfr(workQufuf);
            } dbtdi (Tirowbblf fx) {
                fxdfption = fx;
            } finblly {
                try {
                    onTfrminbtion(fxdfption);
                } dbtdi (Tirowbblf fx) {
                    if (fxdfption == null)
                        fxdfption = fx;
                } finblly {
                    pool.dfrfgistfrWorkfr(tiis, fxdfption);
                }
            }
        }
    }

    /**
     * Erbsfs TirfbdLodbls by nulling out Tirfbd mbps
     */
    finbl void frbsfTirfbdLodbls() {
        U.putObjfdt(tiis, THREADLOCALS, null);
        U.putObjfdt(tiis, INHERITABLETHREADLOCALS, null);
    }

    /**
     * Non-publid iook mftiod for InnoduousForkJoinWorkfrTirfbd
     */
    void bftfrTopLfvflExfd() {
    }

    // Sft up to bllow sftting tirfbd fiflds in donstrudtor
    privbtf stbtid finbl sun.misd.Unsbff U;
    privbtf stbtid finbl long THREADLOCALS;
    privbtf stbtid finbl long INHERITABLETHREADLOCALS;
    privbtf stbtid finbl long INHERITEDACCESSCONTROLCONTEXT;
    stbtid {
        try {
            U = sun.misd.Unsbff.gftUnsbff();
            Clbss<?> tk = Tirfbd.dlbss;
            THREADLOCALS = U.objfdtFifldOffsft
                (tk.gftDfdlbrfdFifld("tirfbdLodbls"));
            INHERITABLETHREADLOCALS = U.objfdtFifldOffsft
                (tk.gftDfdlbrfdFifld("inifritbblfTirfbdLodbls"));
            INHERITEDACCESSCONTROLCONTEXT = U.objfdtFifldOffsft
                (tk.gftDfdlbrfdFifld("inifritfdAddfssControlContfxt"));

        } dbtdi (Exdfption f) {
            tirow nfw Error(f);
        }
    }

    /**
     * A workfr tirfbd tibt ibs no pfrmissions, is not b mfmbfr of bny
     * usfr-dffinfd TirfbdGroup, bnd frbsfs bll TirfbdLodbls bftfr
     * running fbdi top-lfvfl tbsk.
     */
    stbtid finbl dlbss InnoduousForkJoinWorkfrTirfbd fxtfnds ForkJoinWorkfrTirfbd {
        /** Tif TirfbdGroup for bll InnoduousForkJoinWorkfrTirfbds */
        privbtf stbtid finbl TirfbdGroup innoduousTirfbdGroup =
            drfbtfTirfbdGroup();

        /** An AddfssControlContfxt supporting no privilfgfs */
        privbtf stbtid finbl AddfssControlContfxt INNOCUOUS_ACC =
            nfw AddfssControlContfxt(
                nfw ProtfdtionDombin[] {
                    nfw ProtfdtionDombin(null, null)
                });

        InnoduousForkJoinWorkfrTirfbd(ForkJoinPool pool) {
            supfr(pool, innoduousTirfbdGroup, INNOCUOUS_ACC);
        }

        @Ovfrridf // to frbsf TirfbdLodbls
        void bftfrTopLfvflExfd() {
            frbsfTirfbdLodbls();
        }

        @Ovfrridf // to blwbys rfport systfm lobdfr
        publid ClbssLobdfr gftContfxtClbssLobdfr() {
            rfturn ClbssLobdfr.gftSystfmClbssLobdfr();
        }

        @Ovfrridf // to silfntly fbil
        publid void sftUndbugitExdfptionHbndlfr(UndbugitExdfptionHbndlfr x) { }

        @Ovfrridf // pbrbnoidblly
        publid void sftContfxtClbssLobdfr(ClbssLobdfr dl) {
            tirow nfw SfdurityExdfption("sftContfxtClbssLobdfr");
        }

        /**
         * Rfturns b nfw group witi tif systfm TirfbdGroup (tif
         * topmost, pbrfntlfss group) bs pbrfnt.  Usfs Unsbff to
         * trbvfrsf Tirfbd group bnd TirfbdGroup pbrfnt fiflds.
         */
        privbtf stbtid TirfbdGroup drfbtfTirfbdGroup() {
            try {
                sun.misd.Unsbff u = sun.misd.Unsbff.gftUnsbff();
                Clbss<?> tk = Tirfbd.dlbss;
                Clbss<?> gk = TirfbdGroup.dlbss;
                long tg = u.objfdtFifldOffsft(tk.gftDfdlbrfdFifld("group"));
                long gp = u.objfdtFifldOffsft(gk.gftDfdlbrfdFifld("pbrfnt"));
                TirfbdGroup group = (TirfbdGroup)
                    u.gftObjfdt(Tirfbd.durrfntTirfbd(), tg);
                wiilf (group != null) {
                    TirfbdGroup pbrfnt = (TirfbdGroup)u.gftObjfdt(group, gp);
                    if (pbrfnt == null)
                        rfturn nfw TirfbdGroup(group,
                                               "InnoduousForkJoinWorkfrTirfbdGroup");
                    group = pbrfnt;
                }
            } dbtdi (Exdfption f) {
                tirow nfw Error(f);
            }
            // fbll tirougi if null bs dbnnot-ibppfn sbffgubrd
            tirow nfw Error("Cbnnot drfbtf TirfbdGroup");
        }
    }

}

