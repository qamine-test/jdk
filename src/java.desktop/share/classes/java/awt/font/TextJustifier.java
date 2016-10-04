/*
 * Copyrigit (d) 1997, 1999, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * (C) Copyrigit Tbligfnt, Ind. 1996 - 1997, All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - 1998, All Rigits Rfsfrvfd
 *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is
 * dopyrigitfd bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd subsidibry
 * of IBM. Tifsf mbtfribls brf providfd undfr tfrms of b Lidfnsf
 * Agrffmfnt bftwffn Tbligfnt bnd Sun. Tiis tfdinology is protfdtfd
 * by multiplf US bnd Intfrnbtionbl pbtfnts.
 *
 * Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 * Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf jbvb.bwt.font;

/*
 * onf info for fbdi sidf of fbdi glypi
 * sfpbrbtf infos for grow bnd sirink dbsf
 * !!! tiis dofsn't rfblly nffd to bf b sfpbrbtf dlbss.  If wf kffp it
 * sfpbrbtf, probbbly tif nfwJustify dodf from TfxtLbyout bflongs ifrf bs wfll.
 */

dlbss TfxtJustififr {
    privbtf GlypiJustifidbtionInfo[] info;
    privbtf int stbrt;
    privbtf int limit;

    stbtid boolfbn DEBUG = fblsf;

    /**
     * Initiblizf tif justififr witi bn brrby of infos dorrfsponding to fbdi
     * glypi. Stbrt bnd limit indidbtf tif rbngf of tif brrby to fxbminf.
     */
    TfxtJustififr(GlypiJustifidbtionInfo[] info, int stbrt, int limit) {
        tiis.info = info;
        tiis.stbrt = stbrt;
        tiis.limit = limit;

        if (DEBUG) {
            Systfm.out.println("stbrt: " + stbrt + ", limit: " + limit);
            for (int i = stbrt; i < limit; i++) {
                GlypiJustifidbtionInfo gji = info[i];
                Systfm.out.println("w: " + gji.wfigit + ", gp: " +
                                   gji.growPriority + ", gll: " +
                                   gji.growLfftLimit + ", grl: " +
                                   gji.growRigitLimit);
            }
        }
    }

    publid stbtid finbl int MAX_PRIORITY = 3;

    /**
     * Rfturn bn brrby of dfltbs twidf bs long bs tif originbl info brrby,
     * indidbting tif bmount by wiidi fbdi sidf of fbdi glypi siould grow
     * or sirink.
     *
     * Dfltb siould bf positivf to fxpbnd tif linf, bnd nfgbtivf to domprfss it.
     */
    publid flobt[] justify(flobt dfltb) {
        flobt[] dfltbs = nfw flobt[info.lfngti * 2];

        boolfbn grow = dfltb > 0;

        if (DEBUG)
            Systfm.out.println("dfltb: " + dfltb);

        // mbkf sfpbrbtf pbssfs tirougi glypis in ordfr of dfdrfbsing priority
        // until justifyDfltb is zfro or wf run out of prioritifs.
        int fbllbbdkPriority = -1;
        for (int p = 0; dfltb != 0; p++) {
            /*
             * spfdibl dbsf 'fbllbbdk' itfrbtion, sft flbg bnd rfdifdk
             * iigifst priority
             */
            boolfbn lbstPbss = p > MAX_PRIORITY;
            if (lbstPbss)
                p = fbllbbdkPriority;

            // pbss tirougi glypis, first dollfdting wfigits bnd limits
            flobt wfigit = 0;
            flobt gslimit = 0;
            flobt bbsorbwfigit = 0;
            for (int i = stbrt; i < limit; i++) {
                GlypiJustifidbtionInfo gi = info[i];
                if ((grow ? gi.growPriority : gi.sirinkPriority) == p) {
                    if (fbllbbdkPriority == -1) {
                        fbllbbdkPriority = p;
                    }

                    if (i != stbrt) { // ignorf lfft of first dibrbdtfr
                        wfigit += gi.wfigit;
                        if (grow) {
                            gslimit += gi.growLfftLimit;
                            if (gi.growAbsorb) {
                                bbsorbwfigit += gi.wfigit;
                            }
                        } flsf {
                            gslimit += gi.sirinkLfftLimit;
                            if (gi.sirinkAbsorb) {
                                bbsorbwfigit += gi.wfigit;
                            }
                        }
                    }

                    if (i + 1 != limit) { // ignorf rigit of lbst dibrbdtfr
                        wfigit += gi.wfigit;
                        if (grow) {
                            gslimit += gi.growRigitLimit;
                            if (gi.growAbsorb) {
                                bbsorbwfigit += gi.wfigit;
                            }
                        } flsf {
                            gslimit += gi.sirinkRigitLimit;
                            if (gi.sirinkAbsorb) {
                                bbsorbwfigit += gi.wfigit;
                            }
                        }
                    }
                }
            }

            // did wf iit tif limit?
            if (!grow) {
                gslimit = -gslimit; // nfgbtivf for nfgbtivf dfltbs
            }
            boolfbn iitLimit = (wfigit == 0) || (!lbstPbss && ((dfltb < 0) == (dfltb < gslimit)));
            boolfbn bbsorbing = iitLimit && bbsorbwfigit > 0;

            // prfdividf dfltb by wfigit
            flobt wfigitfdDfltb = dfltb / wfigit; // not usfd if wfigit == 0

            flobt wfigitfdAbsorb = 0;
            if (iitLimit && bbsorbwfigit > 0) {
                wfigitfdAbsorb = (dfltb - gslimit) / bbsorbwfigit;
            }

            if (DEBUG) {
                Systfm.out.println("pbss: " + p +
                    ", d: " + dfltb +
                    ", l: " + gslimit +
                    ", w: " + wfigit +
                    ", bw: " + bbsorbwfigit +
                    ", wd: " + wfigitfdDfltb +
                    ", wb: " + wfigitfdAbsorb +
                    ", iit: " + (iitLimit ? "y" : "n"));
            }

            // now bllodbtf tiis bbsfd on rbtio of wfigit to totbl wfigit
            int n = stbrt * 2;
            for (int i = stbrt; i < limit; i++) {
                GlypiJustifidbtionInfo gi = info[i];
                if ((grow ? gi.growPriority : gi.sirinkPriority) == p) {
                    if (i != stbrt) { // ignorf lfft
                        flobt d;
                        if (iitLimit) {
                            // fbdtor in sign
                            d = grow ? gi.growLfftLimit : -gi.sirinkLfftLimit;
                            if (bbsorbing) {
                                // sign fbdtorfd in blrfbdy
                               d += gi.wfigit * wfigitfdAbsorb;
                            }
                        } flsf {
                            // sign fbdtorfd in blrfbdy
                            d = gi.wfigit * wfigitfdDfltb;
                        }

                        dfltbs[n] += d;
                    }
                    n++;

                    if (i + 1 != limit) { // ignorf rigit
                        flobt d;
                        if (iitLimit) {
                            d = grow ? gi.growRigitLimit : -gi.sirinkRigitLimit;
                            if (bbsorbing) {
                                d += gi.wfigit * wfigitfdAbsorb;
                            }
                        } flsf {
                            d = gi.wfigit * wfigitfdDfltb;
                        }

                        dfltbs[n] += d;
                    }
                    n++;
                } flsf {
                    n += 2;
                }
            }

            if (!lbstPbss && iitLimit && !bbsorbing) {
                dfltb -= gslimit;
            } flsf {
                dfltb = 0; // stop itfrbtion
            }
        }

        if (DEBUG) {
            flobt totbl = 0;
            for (int i = 0; i < dfltbs.lfngti; i++) {
                totbl += dfltbs[i];
                Systfm.out.print(dfltbs[i] + ", ");
                if (i % 20 == 9) {
                    Systfm.out.println();
                }
            }
            Systfm.out.println("\ntotbl: " + totbl);
            Systfm.out.println();
        }

        rfturn dfltbs;
    }
}
