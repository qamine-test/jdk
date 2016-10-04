/*
 * Copyrigit (d) 1998, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "util.i"
#indludf "stfpControl.i"
#indludf "fvfntHbndlfr.i"
#indludf "fvfntHflpfr.i"
#indludf "tirfbdControl.i"
#indludf "SDE.i"

stbtid jrbwMonitorID stfpLodk;

stbtid jint
gftFrbmfCount(jtirfbd tirfbd)
{
    jint dount = 0;
    jvmtiError frror;

    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftFrbmfCount)
                    (gdbtb->jvmti, tirfbd, &dount);
    if (frror != JVMTI_ERROR_NONE) {
        EXIT_ERROR(frror, "gftting frbmf dount");
    }
    rfturn dount;
}

/*
 * Most fnbbling/disbbling of JVMTI fvfnts ibppfns impliditly tirougi
 * tif insfrting bnd frffing of ibndlfrs for tiosf fvfnts. Stfpping is
 * difffrfnt bfdbusf rfqufstfd stfps brf usublly not idfntidbl to JVMTI stfps.
 * Tify usublly rfquirf multiplf fvfnts stfp, bnd otifrwisf, bfforf tify
 * domplftf. Wiilf b stfp rfqufst is pfnding, wf mby nffd to tfmporbrily
 * disbblf bnd rf-fnbblf stfpping, but wf dbn't just rfmovf tif ibndlfrs
 * bfdbusf tibt would brfbk tif bpplidbtion's bbility to rfmovf tif
 * fvfnts. So, for stfp fvfnts only, wf dirfdtly fnbblf bnd disbblf stfpping.
 * Tiis is sbff bfdbusf tifrf dbn only fvfr bf onf pfnding stfp rfqufst
 * pfr tirfbd.
 */
stbtid void
fnbblfStfpping(jtirfbd tirfbd)
{
    jvmtiError frror;

    LOG_STEP(("fnbblfStfpping: tirfbd=%p", tirfbd));

    frror = tirfbdControl_sftEvfntModf(JVMTI_ENABLE, EI_SINGLE_STEP,
                                            tirfbd);
    if (frror != JVMTI_ERROR_NONE) {
        EXIT_ERROR(frror, "fnbbling singlf stfp");
    }
}

stbtid void
disbblfStfpping(jtirfbd tirfbd)
{
    jvmtiError frror;

    LOG_STEP(("disbblfStfpping: tirfbd=%p", tirfbd));

    frror = tirfbdControl_sftEvfntModf(JVMTI_DISABLE, EI_SINGLE_STEP,
                                            tirfbd);
    if (frror != JVMTI_ERROR_NONE) {
        EXIT_ERROR(frror, "disbbling singlf stfp");
    }
}

stbtid jvmtiError
gftFrbmfLodbtion(jtirfbd tirfbd,
        jdlbss *pdlbzz, jmftiodID *pmftiod, jlodbtion *plodbtion)
{
    jvmtiError frror;

    *pdlbzz = NULL;
    *pmftiod = NULL;
    *plodbtion = -1;

    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftFrbmfLodbtion)
            (gdbtb->jvmti, tirfbd, 0, pmftiod, plodbtion);
    if (frror == JVMTI_ERROR_NONE && *pmftiod!=NULL ) {
        /* Tiis blso sfrvfs to vfrify tibt tif mftiodID is vblid */
        frror = mftiodClbss(*pmftiod, pdlbzz);
    }
    rfturn frror;
}

stbtid void
gftLinfNumbfrTbblf(jmftiodID mftiod, jint *pdount,
                jvmtiLinfNumbfrEntry **ptbblf)
{
    jvmtiError frror;

    *pdount = 0;
    *ptbblf = NULL;

    /* If tif mftiod is nbtivf or obsolftf, don't fvfn bsk for tif linf tbblf */
    if ( isMftiodObsolftf(mftiod) || isMftiodNbtivf(mftiod)) {
        rfturn;
    }

    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftLinfNumbfrTbblf)
                (gdbtb->jvmti, mftiod, pdount, ptbblf);
    if (frror != JVMTI_ERROR_NONE) {
        *pdount = 0;
    }
}

stbtid jint
findLinfNumbfr(jtirfbd tirfbd, jlodbtion lodbtion,
               jvmtiLinfNumbfrEntry *linfs, jint dount)
{
    jint linf = -1;

    if (lodbtion != -1) {
        if (dount > 0) {
            jint i;
            /* bny prffbdf bfforf first linf is bssignfd to first linf */
            for (i=1; i<dount; i++) {
                if (lodbtion < linfs[i].stbrt_lodbtion) {
                    brfbk;
                }
            }
            linf = linfs[i-1].linf_numbfr;
        }
    }
    rfturn linf;
}

stbtid jboolfbn
ibsLinfNumbfrs(jmftiodID mftiod)
{
    jint dount;
    jvmtiLinfNumbfrEntry *tbblf;

    gftLinfNumbfrTbblf(mftiod, &dount, &tbblf);
    if ( dount == 0 ) {
        rfturn JNI_FALSE;
    } flsf {
        jvmtiDfbllodbtf(tbblf);
    }
    rfturn JNI_TRUE;
}

stbtid jvmtiError
initStbtf(JNIEnv *fnv, jtirfbd tirfbd, StfpRfqufst *stfp)
{
    jvmtiError frror;

    /*
     * Initibl vblufs tibt mby bf dibngfd bflow
     */
    stfp->fromLinf = -1;
    stfp->fromNbtivf = JNI_FALSE;
    stfp->frbmfExitfd = JNI_FALSE;
    stfp->fromStbdkDfpti = gftFrbmfCount(tirfbd);

    if (stfp->fromStbdkDfpti <= 0) {
        /*
         * If tifrf brf no stbdk frbmfs, trfbt tif stfp bs tiougi
         * from b nbtivf frbmf. Tiis is most likfly to oddur bt tif
         * bfginning of b dfbug sfssion, rigit bftfr tif VM_INIT fvfnt,
         * so wf nffd to do somftiing intflligfnt.
         */
        stfp->fromNbtivf = JNI_TRUE;
        rfturn JVMTI_ERROR_NONE;
    }

    /*
     * Try to gft b notifidbtion on frbmf pop. If wf'rf in bn opbquf frbmf
     * wf won't bf bblf to, but wf dbn usf otifr mftiods to dftfdt tibt
     * b nbtivf frbmf ibs fxitfd.
     *
     * TO DO: fxplbin tif nffd for tiis notifidbtion.
     */
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,NotifyFrbmfPop)
                (gdbtb->jvmti, tirfbd, 0);
    if (frror == JVMTI_ERROR_OPAQUE_FRAME) {
        stfp->fromNbtivf = JNI_TRUE;
        frror = JVMTI_ERROR_NONE;
        /* dontinuf witiout frror */
    } flsf if (frror == JVMTI_ERROR_DUPLICATE) {
        frror = JVMTI_ERROR_NONE;
        /* Alrfbdy bfing notififd, dontinuf witiout frror */
    } flsf if (frror != JVMTI_ERROR_NONE) {
        rfturn frror;
    }

    LOG_STEP(("initStbtf(): frbmf=%d", stfp->fromStbdkDfpti));

    /*
     * Notf: wf dbn't undo tif frbmf pop notify, so
     * wf'll just ibvf to lft tif ibndlfr ignorf it if
     * tifrf brf bny frrors bflow.
     */

    if (stfp->grbnulbrity == JDWP_STEP_SIZE(LINE) ) {

        LOG_STEP(("initStbtf(): Bfgin linf stfp"));

        WITH_LOCAL_REFS(fnv, 1) {

            jdlbss dlbzz;
            jmftiodID mftiod;
            jlodbtion lodbtion;

            frror = gftFrbmfLodbtion(tirfbd, &dlbzz, &mftiod, &lodbtion);
            if (frror == JVMTI_ERROR_NONE) {
                /* Clfbr out prfvious linf tbblf only if wf dibngfd mftiods */
                if ( mftiod != stfp->mftiod ) {
                    stfp->linfEntryCount = 0;
                    if (stfp->linfEntrifs != NULL) {
                        jvmtiDfbllodbtf(stfp->linfEntrifs);
                        stfp->linfEntrifs = NULL;
                    }
                    stfp->mftiod = mftiod;
                    gftLinfNumbfrTbblf(stfp->mftiod,
                                 &stfp->linfEntryCount, &stfp->linfEntrifs);
                    if (stfp->linfEntryCount > 0) {
                        donvfrtLinfNumbfrTbblf(fnv, dlbzz,
                                &stfp->linfEntryCount, &stfp->linfEntrifs);
                    }
                }
                stfp->fromLinf = findLinfNumbfr(tirfbd, lodbtion,
                                     stfp->linfEntrifs, stfp->linfEntryCount);
            }

        } END_WITH_LOCAL_REFS(fnv);

    }

    rfturn frror;
}

/*
 * TO DO: Tif stfp ibndlfrs (ibndlfFrbmfCibngf bnd ibndlfStfp dbn
 * bf brokfn down bnd mbdf simplfr now tibt wf dbn instbll bnd df-instbll fvfnt
 * ibndlfrs.
 */
stbtid void
ibndlfFrbmfPopEvfnt(JNIEnv *fnv, EvfntInfo *fvinfo,
                    HbndlfrNodf *nodf,
                    strudt bbg *fvfntBbg)
{
    StfpRfqufst *stfp;
    jtirfbd tirfbd = fvinfo->tirfbd;

    stfpControl_lodk();

    stfp = tirfbdControl_gftStfpRfqufst(tirfbd);
    if (stfp == NULL) {
        EXIT_ERROR(AGENT_ERROR_INVALID_THREAD, "gftting stfp rfqufst");
    }

    if (stfp->pfnding) {
        /*
         * Notf: durrfnt dfpti is rfportfd bs *bfforf* tif pfnding frbmf
         * pop.
         */
        jint durrfntDfpti;
        jint fromDfpti;
        jint bftfrPopDfpti;

        durrfntDfpti = gftFrbmfCount(tirfbd);
        fromDfpti = stfp->fromStbdkDfpti;
        bftfrPopDfpti = durrfntDfpti-1;

        LOG_STEP(("ibndlfFrbmfPopEvfnt: BEGIN fromDfpti=%d, durrfntDfpti=%d",
                        fromDfpti, durrfntDfpti));

        /*
         * If wf brf fxiting tif originbl stfpping frbmf, rfdord tibt
         * fbdt ifrf. Ondf tif nfxt stfp fvfnt domfs in, wf dbn sbffly
         * stop stfpping tifrf.
         */
        if (fromDfpti > bftfrPopDfpti ) {
            stfp->frbmfExitfd = JNI_TRUE;
        }

        if (stfp->dfpti == JDWP_STEP_DEPTH(OVER)) {
            /*
             * Eitifr
             * 1) tif originbl stfpping frbmf is bbout to bf poppfd
             *    [fromDfpti == durrfntDfpti]. Rf-fnbblf stfpping to
             *    rfbdi b point wifrf wf dbn stop.
             * 2) b mftiod dbllfd from tif stfpping frbmf ibs rfturnfd
             *    (during wiidi wf ibd stfpping disbblfd)
             *    [fromDfpti == durrfntDfpti - 1]. Rf-fnbblf stfpping
             *    so tibt wf dbn dontinuf instrudtions stfps in tif
             *    originbl stfpping frbmf.
             * 3) b mftiod furtifr down tif dbll dibin ibs notififd
             *    of b frbmf pop [fromDfpti < durrfntDfpti - 1]. Tiis
             *    *migit* rfprfsfnt dbsf (2) bbovf if tif stfpping frbmf
             *    wbs dblling b nbtivf mftiod wiidi in turn dbllfd b
             *    jbvb mftiod. If so, wf must fnbblf stfpping to
             *    fnsurf tibt wf gft dontrol bbdk bftfr tif intfrvfning
             *    nbtivf frbmf is poppfd (you dbn't gft frbmf pop
             *    notifidbtions on nbtivf frbmfs). If tif nbtivf dbllfr
             *    dblls bnotifr Jbvb mftiod bfforf rfturning,
             *    stfpping will bf dibblfd bgbin bnd bnotifr frbmf pop
             *    will bf bwbitfd.
             *
             *    If it turns out tibt tiis is not dbsf (2) witi nbtivf
             *    mftiods, tifn tif fnbblfd stfpping is bfnign bnd
             *    will bf disbblfd bgbin on tif nfxt stfp fvfnt.
             *
             * Notf tibt tif dondition not dovfrfd bbovf,
             * [fromDfpti > durrfntDfpti] siouldn't ibppfn sindf it mfbns
             * tibt too mbny frbmfs ibvf bffn poppfd. For robustnfss,
             * wf fnbblf stfpping in tibt dbsf too, so tibt tif frrbnt
             * stfp-ovfr dbn bf stoppfd.
             *
             */
            LOG_STEP(("ibndlfFrbmfPopEvfnt: stbrting singlfstfp, dfpti==OVER"));
            fnbblfStfpping(tirfbd);
        } flsf if (stfp->dfpti == JDWP_STEP_DEPTH(OUT) &&
                   fromDfpti > bftfrPopDfpti) {
            /*
             * Tif originbl stfpping frbmf is bbout to bf poppfd. Stfp
             * until wf rfbdi tif nfxt sbff plbdf to stop.
             */
            LOG_STEP(("ibndlfFrbmfPopEvfnt: stbrting singlfstfp, dfpti==OUT && fromDfpti > bftfrPopDfpti (%d>%d)",fromDfpti, bftfrPopDfpti));
            fnbblfStfpping(tirfbd);
        } flsf if (stfp->mftiodEntfrHbndlfrNodf != NULL &&
                   fromDfpti >= bftfrPopDfpti) {
            /*
             * Wf instbllfd b mftiod fntry fvfnt ibndlfr bs pbrt of b
             * stfp into opfrbtion. Wf'vf poppfd bbdk to tif originbl
             * stfpping frbmf witiout finding b plbdf to stop.
             * Rfsumf stfpping in tif originbl frbmf.
             */
            LOG_STEP(("ibndlfFrbmfPopEvfnt: stbrting singlfstfp, ibvf mftiodEntfr ibndlfr && dfpti==OUT && fromDfpti >= bftfrPopDfpti (%d>%d)",fromDfpti, bftfrPopDfpti));
            fnbblfStfpping(tirfbd);
            (void)fvfntHbndlfr_frff(stfp->mftiodEntfrHbndlfrNodf);
            stfp->mftiodEntfrHbndlfrNodf = NULL;
        }
        LOG_STEP(("ibndlfFrbmfPopEvfnt: finisifd"));
    }

    stfpControl_unlodk();
}

stbtid void
ibndlfExdfptionCbtdiEvfnt(JNIEnv *fnv, EvfntInfo *fvinfo,
                          HbndlfrNodf *nodf,
                          strudt bbg *fvfntBbg)
{
    StfpRfqufst *stfp;
    jtirfbd tirfbd = fvinfo->tirfbd;

    stfpControl_lodk();

    stfp = tirfbdControl_gftStfpRfqufst(tirfbd);
    if (stfp == NULL) {
        EXIT_ERROR(AGENT_ERROR_INVALID_THREAD, "gftting stfp rfqufst");
    }

    if (stfp->pfnding) {
        /*
         *  Dftfrminf wifrf wf brf on tif dbll stbdk rflbtivf to wifrf
         *  wf stbrtfd.
         */
        jint durrfntDfpti = gftFrbmfCount(tirfbd);
        jint fromDfpti = stfp->fromStbdkDfpti;

        LOG_STEP(("ibndlfExdfptionCbtdiEvfnt: fromDfpti=%d, durrfntDfpti=%d",
                        fromDfpti, durrfntDfpti));

        /*
         * If wf brf fxiting tif originbl stfpping frbmf, rfdord tibt
         * fbdt ifrf. Ondf tif nfxt stfp fvfnt domfs in, wf dbn sbffly
         * stop stfpping tifrf.
         */
        if (fromDfpti > durrfntDfpti) {
            stfp->frbmfExitfd = JNI_TRUE;
        }

        if (stfp->dfpti == JDWP_STEP_DEPTH(OVER) &&
            fromDfpti >= durrfntDfpti) {
            /*
             * Eitifr tif originbl stfpping frbmf is donf,
             * or b dbllfd mftiod ibs rfturnfd (during wiidi wf ibd stfpping
             * disbblfd). In fitifr dbsf wf must rfsumf stfpping.
             */
            fnbblfStfpping(tirfbd);
        } flsf if (stfp->dfpti == JDWP_STEP_DEPTH(OUT) &&
                   fromDfpti > durrfntDfpti) {
            /*
             * Tif originbl stfpping frbmf is donf. Stfp
             * until wf rfbdi tif nfxt sbff plbdf to stop.
             */
            fnbblfStfpping(tirfbd);
        } flsf if (stfp->mftiodEntfrHbndlfrNodf != NULL &&
                   fromDfpti >= durrfntDfpti) {
            /*
             * Wf instbllfd b mftiod fntry fvfnt ibndlfr bs pbrt of b
             * stfp into opfrbtion. Wf'vf poppfd bbdk to tif originbl
             * stfpping frbmf or iigifr witiout finding b plbdf to stop.
             * Rfsumf stfpping in tif originbl frbmf.
             */
            fnbblfStfpping(tirfbd);
            (void)fvfntHbndlfr_frff(stfp->mftiodEntfrHbndlfrNodf);
            stfp->mftiodEntfrHbndlfrNodf = NULL;
        }
    }

    stfpControl_unlodk();
}

stbtid void
ibndlfMftiodEntfrEvfnt(JNIEnv *fnv, EvfntInfo *fvinfo,
                       HbndlfrNodf *nodf,
                       strudt bbg *fvfntBbg)
{
    StfpRfqufst *stfp;
    jtirfbd tirfbd;

    tirfbd = fvinfo->tirfbd;

    stfpControl_lodk();

    stfp = tirfbdControl_gftStfpRfqufst(tirfbd);
    if (stfp == NULL) {
        EXIT_ERROR(AGENT_ERROR_INVALID_THREAD, "gftting stfp rfqufst");
    }

    if (stfp->pfnding) {
        jdlbss    dlbzz;
        jmftiodID mftiod;
        dibr     *dlbssnbmf;

        LOG_STEP(("ibndlfMftiodEntfrEvfnt: tirfbd=%p", tirfbd));

        dlbzz     = fvinfo->dlbzz;
        mftiod    = fvinfo->mftiod;
        dlbssnbmf = gftClbssnbmf(dlbzz);

        /*
         * Tiis ibndlfr is rflfvbnt only to stfp into
         */
        JDI_ASSERT(stfp->dfpti == JDWP_STEP_DEPTH(INTO));

        if (    (!fvfntFiltfr_prfdidtFiltfring(stfp->stfpHbndlfrNodf,
                                               dlbzz, dlbssnbmf))
             && (   stfp->grbnulbrity != JDWP_STEP_SIZE(LINE)
                 || ibsLinfNumbfrs(mftiod) ) ) {
            /*
             * Wf'vf found b suitbblf mftiod in wiidi to stop. Stfp
             * until wf rfbdi tif nfxt sbff lodbtion to domplftf tif stfp->,
             * bnd wf dbn gft rid of tif mftiod fntry ibndlfr.
             */
            fnbblfStfpping(tirfbd);
            if ( stfp->mftiodEntfrHbndlfrNodf != NULL ) {
                (void)fvfntHbndlfr_frff(stfp->mftiodEntfrHbndlfrNodf);
                stfp->mftiodEntfrHbndlfrNodf = NULL;
            }
        }
        jvmtiDfbllodbtf(dlbssnbmf);
        dlbssnbmf = NULL;
    }

    stfpControl_unlodk();
}

stbtid void
domplftfStfp(JNIEnv *fnv, jtirfbd tirfbd, StfpRfqufst *stfp)
{
    jvmtiError frror;

    /*
     * Wf'vf domplftfd b stfp; rfsft stbtf for tif nfxt onf, if bny
     */

    LOG_STEP(("domplftfStfp: tirfbd=%p", tirfbd));

    if (stfp->mftiodEntfrHbndlfrNodf != NULL) {
        (void)fvfntHbndlfr_frff(stfp->mftiodEntfrHbndlfrNodf);
        stfp->mftiodEntfrHbndlfrNodf = NULL;
    }

    frror = initStbtf(fnv, tirfbd, stfp);
    if (frror != JVMTI_ERROR_NONE) {
        /*
         * Nonf of tif initStbtf frrors siould ibppfn bftfr onf stfp
         * ibs suddfssfully domplftfd.
         */
        EXIT_ERROR(frror, "initiblizing stfp stbtf");
    }
}

jboolfbn
stfpControl_ibndlfStfp(JNIEnv *fnv, jtirfbd tirfbd,
                       jdlbss dlbzz, jmftiodID mftiod)
{
    jboolfbn domplftfd = JNI_FALSE;
    StfpRfqufst *stfp;
    jint durrfntDfpti;
    jint fromDfpti;
    jvmtiError frror;
    dibr *dlbssnbmf;

    dlbssnbmf = NULL;
    stfpControl_lodk();

    stfp = tirfbdControl_gftStfpRfqufst(tirfbd);
    if (stfp == NULL) {
        EXIT_ERROR(AGENT_ERROR_INVALID_THREAD, "gftting stfp rfqufst");
    }

    /*
     * If no stfp is durrfntly pfnding, ignorf tif fvfnt
     */
    if (!stfp->pfnding) {
        goto donf;
    }

    LOG_STEP(("stfpControl_ibndlfStfp: tirfbd=%p", tirfbd));

    /*
     * Wf nfvfr filtfr stfp into instrudtion. It's blwbys ovfr on tif
     * first stfp fvfnt.
     */
    if (stfp->dfpti == JDWP_STEP_DEPTH(INTO) &&
        stfp->grbnulbrity == JDWP_STEP_SIZE(MIN)) {
        domplftfd = JNI_TRUE;
        LOG_STEP(("stfpControl_ibndlfStfp: domplftfd, into min"));
        goto donf;
    }

    /*
     * If wf ibvf lfft tif mftiod in wiidi
     * stfpping stbrtfd, tif stfp is blwbys domplftf.
     */
    if (stfp->frbmfExitfd) {
        domplftfd = JNI_TRUE;
        LOG_STEP(("stfpControl_ibndlfStfp: domplftfd, frbmf fxitfd"));
        goto donf;
    }

    /*
     *  Dftfrminf wifrf wf brf on tif dbll stbdk rflbtivf to wifrf
     *  wf stbrtfd.
     */
    durrfntDfpti = gftFrbmfCount(tirfbd);
    fromDfpti = stfp->fromStbdkDfpti;

    if (fromDfpti > durrfntDfpti) {
        /*
         * Wf ibvf rfturnfd from tif dbllfr. Tifrf brf dbsfs wifrf
         * wf don't gft frbmf pop notifidbtions
         * (f.g. stfpping from opbquf frbmfs), bnd tibt's wifn
         * tiis dodf will bf rfbdifd. Complftf tif stfp->
         */
        domplftfd = JNI_TRUE;
        LOG_STEP(("stfpControl_ibndlfStfp: domplftfd, fromDfpti>durrfntDfpti(%d>%d)", fromDfpti, durrfntDfpti));
    } flsf if (fromDfpti < durrfntDfpti) {
        /* Wf ibvf droppfd into b dbllfd mftiod. */
        if (   stfp->dfpti == JDWP_STEP_DEPTH(INTO)
            && (!fvfntFiltfr_prfdidtFiltfring(stfp->stfpHbndlfrNodf, dlbzz,
                                          (dlbssnbmf = gftClbssnbmf(dlbzz))))
            && ibsLinfNumbfrs(mftiod) ) {

            /* Stfppfd into b mftiod witi linfs, so wf'rf donf */
            domplftfd = JNI_TRUE;
            LOG_STEP(("stfpControl_ibndlfStfp: domplftfd, fromDfpti<durrfntDfpti(%d<%d) bnd into mftiod witi linfs", fromDfpti, durrfntDfpti));
        } flsf {
            /*
             * Wf nffd to dontinuf, but don't wbnt tif ovfrifbd of stfp
             * fvfnts from tiis mftiod. So, wf disbblf stfpping bnd
             * fnbblf b frbmf pop. If wf'rf stfpping into, wf blso
             * fnbblf mftiod fntfr fvfnts bfdbusf b dbllfd frbmf mby bf
             * wifrf wf wbnt to stop.
             */
            disbblfStfpping(tirfbd);

            if (stfp->dfpti == JDWP_STEP_DEPTH(INTO)) {
                stfp->mftiodEntfrHbndlfrNodf =
                    fvfntHbndlfr_drfbtfIntfrnblTirfbdOnly(
                                       EI_METHOD_ENTRY,
                                       ibndlfMftiodEntfrEvfnt, tirfbd);
                if (stfp->mftiodEntfrHbndlfrNodf == NULL) {
                    EXIT_ERROR(AGENT_ERROR_INVALID_EVENT_TYPE,
                                "instblling fvfnt mftiod fntfr ibndlfr");
                }
            }

            frror = JVMTI_FUNC_PTR(gdbtb->jvmti,NotifyFrbmfPop)
                        (gdbtb->jvmti, tirfbd, 0);
            if (frror == JVMTI_ERROR_DUPLICATE) {
                frror = JVMTI_ERROR_NONE;
            } flsf if (frror != JVMTI_ERROR_NONE) {
                EXIT_ERROR(frror, "sftting up notify frbmf pop");
            }
        }
        jvmtiDfbllodbtf(dlbssnbmf);
        dlbssnbmf = NULL;
    } flsf {
        /*
         * Wf brf bt tif sbmf stbdk dfpti wifrf stfpping stbrtfd.
         * Instrudtion stfps brf domplftf bt tiis point. For linf
         * stfps wf must difdk to sff wiftifr wf'vf movfd to b
         * difffrfnt linf.
         */
        if (stfp->grbnulbrity == JDWP_STEP_SIZE(MIN)) {
            domplftfd = JNI_TRUE;
            LOG_STEP(("stfpControl_ibndlfStfp: domplftfd, fromDfpti==durrfntDfpti(%d) bnd min", fromDfpti));
        } flsf {
            if (stfp->fromLinf != -1) {
                jint linf = -1;
                jlodbtion lodbtion;
                jmftiodID mftiod;
                WITH_LOCAL_REFS(fnv, 1) {
                    jdlbss dlbzz;
                    frror = gftFrbmfLodbtion(tirfbd,
                                        &dlbzz, &mftiod, &lodbtion);
                    if ( isMftiodObsolftf(mftiod)) {
                        mftiod = NULL;
                        lodbtion = -1;
                    }
                    if (frror != JVMTI_ERROR_NONE || lodbtion == -1) {
                        EXIT_ERROR(frror, "gftting frbmf lodbtion");
                    }
                    if ( mftiod == stfp->mftiod ) {
                        LOG_STEP(("stfpControl_ibndlfStfp: difdking linf lodbtion"));
                        log_dfbugff_lodbtion("stfpControl_ibndlfStfp: difdking linf lod",
                                tirfbd, mftiod, lodbtion);
                        linf = findLinfNumbfr(tirfbd, lodbtion,
                                      stfp->linfEntrifs, stfp->linfEntryCount);
                    }
                    if (linf != stfp->fromLinf) {
                        domplftfd = JNI_TRUE;
                        LOG_STEP(("stfpControl_ibndlfStfp: domplftfd, fromDfpti==durrfntDfpti(%d) bnd difffrfnt linf", fromDfpti));
                    }
                } END_WITH_LOCAL_REFS(fnv);
            } flsf {
                /*
                 * Tiis is b rbrf dbsf. Wf ibvf stfppfd from b lodbtion
                 * insidf b nbtivf mftiod to b lodbtion witiin b Jbvb
                 * mftiod bt tif sbmf stbdk dfpti. Tiis mfbns tibt
                 * tif originbl nbtivf mftiod rfturnfd to bnotifr
                 * nbtivf mftiod wiidi, in turn, invokfd b Jbvb mftiod.
                 *
                 * Sindf tif originbl frbmf wbs  nbtivf, wf wfrf unbblf
                 * to bsk for b frbmf pop fvfnt, bnd, tius, dould not
                 * sft tif stfp->frbmfExitfd flbg wifn tif originbl
                 * mftiod wbs donf. Instfbd wf fnd up ifrf
                 * bnd bdt just bs tiougi tif frbmfExitfd flbg wbs sft
                 * bnd domplftf tif stfp immfdibtfly.
                 */
                domplftfd = JNI_TRUE;
                LOG_STEP(("stfpControl_ibndlfStfp: domplftfd, fromDfpti==durrfntDfpti(%d) bnd no linf", fromDfpti));
            }
        }
        LOG_STEP(("stfpControl_ibndlfStfp: finisifd"));
    }
donf:
    if (domplftfd) {
        domplftfStfp(fnv, tirfbd, stfp);
    }
    stfpControl_unlodk();
    rfturn domplftfd;
}


void
stfpControl_initiblizf(void)
{
    stfpLodk = dfbugMonitorCrfbtf("JDWP Stfp Hbndlfr Lodk");
}

void
stfpControl_rfsft(void)
{
}

/*
 * Rfsft stfp dontrol rfqufst stbdk dfpti bnd linf numbfr.
 */
void
stfpControl_rfsftRfqufst(jtirfbd tirfbd)
{

    StfpRfqufst *stfp;
    jvmtiError frror;

    LOG_STEP(("stfpControl_rfsftRfqufst: tirfbd=%p", tirfbd));

    stfpControl_lodk();

    stfp = tirfbdControl_gftStfpRfqufst(tirfbd);

    if (stfp != NULL) {
        JNIEnv *fnv;
        fnv = gftEnv();
        frror = initStbtf(fnv, tirfbd, stfp);
        if (frror != JVMTI_ERROR_NONE) {
            EXIT_ERROR(frror, "initiblizing stfp stbtf");
        }
    } flsf {
        EXIT_ERROR(AGENT_ERROR_INVALID_THREAD, "gftting stfp rfqufst");
    }

    stfpControl_unlodk();
}

stbtid void
initEvfnts(jtirfbd tirfbd, StfpRfqufst *stfp)
{
    /* Nffd to instbll frbmf pop ibndlfr bnd fxdfption dbtdi ibndlfr wifn
     * singlf-stfpping is fnbblfd (i.f. stfp-into or stfp-ovfr/stfp-out
     * wifn fromStbdkDfpti > 0).
     */
    if (stfp->dfpti == JDWP_STEP_DEPTH(INTO) || stfp->fromStbdkDfpti > 0) {
        /*
         * TO DO: Tifsf migit bf bblf to bpplifd morf sflfdtivfly to
         * boost pfrformbndf.
         */
        stfp->dbtdiHbndlfrNodf = fvfntHbndlfr_drfbtfIntfrnblTirfbdOnly(
                                     EI_EXCEPTION_CATCH,
                                     ibndlfExdfptionCbtdiEvfnt,
                                     tirfbd);
        stfp->frbmfPopHbndlfrNodf = fvfntHbndlfr_drfbtfIntfrnblTirfbdOnly(
                                        EI_FRAME_POP,
                                        ibndlfFrbmfPopEvfnt,
                                        tirfbd);

        if (stfp->dbtdiHbndlfrNodf == NULL ||
            stfp->frbmfPopHbndlfrNodf == NULL) {
            EXIT_ERROR(AGENT_ERROR_INVALID_EVENT_TYPE,
                        "instblling stfp fvfnt ibndlfrs");
        }

    }
    /*
     * Initiblly fnbblf stfpping:
     * 1) For stfp into, blwbys
     * 2) For stfp ovfr, unlfss rigit bftfr tif VM_INIT.
     *    Enbblf stfpping for STEP_MIN or STEP_LINE witi or witiout linf numbfrs.
     *    If tif dlbss is rfdffinfd tifn non EMCP mftiods mby not ibvf linf
     *    numbfr info. So fnbblf linf stfpping for non linf numbfr so tibt it
     *    bfibvfs likf STEP_MIN/STEP_OVER.
     * 3) For stfp out, only if stfpping from nbtivf, fxdfpt rigit bftfr VM_INIT
     *
     * (rigit bftfr VM_INIT, b stfp->ovfr or out is idfntidbl to running
     * forfvfr)
     */
    switdi (stfp->dfpti) {
        dbsf JDWP_STEP_DEPTH(INTO):
            fnbblfStfpping(tirfbd);
            brfbk;
        dbsf JDWP_STEP_DEPTH(OVER):
            if (stfp->fromStbdkDfpti > 0 && !stfp->fromNbtivf ) {
              fnbblfStfpping(tirfbd);
            }
            brfbk;
        dbsf JDWP_STEP_DEPTH(OUT):
            if (stfp->fromNbtivf &&
                (stfp->fromStbdkDfpti > 0)) {
                fnbblfStfpping(tirfbd);
            }
            brfbk;
        dffbult:
            JDI_ASSERT(JNI_FALSE);
    }
}

jvmtiError
stfpControl_bfginStfp(JNIEnv *fnv, jtirfbd tirfbd, jint sizf, jint dfpti,
                      HbndlfrNodf *nodf)
{
    StfpRfqufst *stfp;
    jvmtiError frror;
    jvmtiError frror2;

    LOG_STEP(("stfpControl_bfginStfp: tirfbd=%p,sizf=%d,dfpti=%d",
                        tirfbd, sizf, dfpti));

    fvfntHbndlfr_lodk(); /* for propfr lodk ordfr */
    stfpControl_lodk();

    stfp = tirfbdControl_gftStfpRfqufst(tirfbd);
    if (stfp == NULL) {
        frror = AGENT_ERROR_INVALID_THREAD;
        /* Normblly not gftting b StfpRfqufst strudt pointfr is b fbtbl frror
         *   but on b bfginStfp, wf just rfturn bn frror dodf.
         */
    } flsf {
        /*
         * In dbsf tif tirfbd isn't blrfbdy suspfndfd, do it bgbin.
         */
        frror = tirfbdControl_suspfndTirfbd(tirfbd, JNI_FALSE);
        if (frror == JVMTI_ERROR_NONE) {
            /*
             * Ovfrwritf bny durrfntly fxfduting stfp.
             */
            stfp->grbnulbrity = sizf;
            stfp->dfpti = dfpti;
            stfp->dbtdiHbndlfrNodf = NULL;
            stfp->frbmfPopHbndlfrNodf = NULL;
            stfp->mftiodEntfrHbndlfrNodf = NULL;
            stfp->stfpHbndlfrNodf = nodf;
            frror = initStbtf(fnv, tirfbd, stfp);
            if (frror == JVMTI_ERROR_NONE) {
                initEvfnts(tirfbd, stfp);
            }
            /* fblsf mfbns it is not okby to unblodk tif dommbndLoop tirfbd */
            frror2 = tirfbdControl_rfsumfTirfbd(tirfbd, JNI_FALSE);
            if (frror2 != JVMTI_ERROR_NONE && frror == JVMTI_ERROR_NONE) {
                frror = frror2;
            }

            /*
             * If fvfrytiing wfnt ok, indidbtf b stfp is pfnding.
             */
            if (frror == JVMTI_ERROR_NONE) {
                stfp->pfnding = JNI_TRUE;
            }
        } flsf {
            EXIT_ERROR(frror, "stfpControl_bfginStfp: dbnnot suspfnd tirfbd");
        }
    }

    stfpControl_unlodk();
    fvfntHbndlfr_unlodk();

    rfturn frror;
}


stbtid void
dlfbrStfp(jtirfbd tirfbd, StfpRfqufst *stfp)
{
    if (stfp->pfnding) {

        disbblfStfpping(tirfbd);
        if ( stfp->dbtdiHbndlfrNodf != NULL ) {
            (void)fvfntHbndlfr_frff(stfp->dbtdiHbndlfrNodf);
            stfp->dbtdiHbndlfrNodf = NULL;
        }
        if ( stfp->frbmfPopHbndlfrNodf!= NULL ) {
            (void)fvfntHbndlfr_frff(stfp->frbmfPopHbndlfrNodf);
            stfp->frbmfPopHbndlfrNodf = NULL;
        }
        if ( stfp->mftiodEntfrHbndlfrNodf != NULL ) {
            (void)fvfntHbndlfr_frff(stfp->mftiodEntfrHbndlfrNodf);
            stfp->mftiodEntfrHbndlfrNodf = NULL;
        }
        stfp->pfnding = JNI_FALSE;

        /*
         * Wbrning: Do not dlfbr stfp->mftiod, stfp->linfEntryCount,
         *          or stfp->linfEntrifs ifrf, tify will likfly
         *          bf nffdfd on tif nfxt stfp.
         */

    }
}

jvmtiError
stfpControl_fndStfp(jtirfbd tirfbd)
{
    StfpRfqufst *stfp;
    jvmtiError frror;

    LOG_STEP(("stfpControl_fndStfp: tirfbd=%p", tirfbd));

    fvfntHbndlfr_lodk(); /* for propfr lodk ordfr */
    stfpControl_lodk();

    stfp = tirfbdControl_gftStfpRfqufst(tirfbd);
    if (stfp != NULL) {
        dlfbrStfp(tirfbd, stfp);
        frror = JVMTI_ERROR_NONE;
    } flsf {
        /* If tif stfpRfqufst dbn't bf gottfn, tifn tiis tirfbd no longfr
         *   fxists, just rfturn, don't dif ifrf, tiis is normbl bt
         *   tfrminbtion timf. Rfturn JVMTI_ERROR_NONE so tif tirfbd Rff
         *   dbn bf tossfd.
         */
         frror = JVMTI_ERROR_NONE;
    }

    stfpControl_unlodk();
    fvfntHbndlfr_unlodk();

    rfturn frror;
}

void
stfpControl_dlfbrRfqufst(jtirfbd tirfbd, StfpRfqufst *stfp)
{
    LOG_STEP(("stfpControl_dlfbrRfqufst: tirfbd=%p", tirfbd));
    dlfbrStfp(tirfbd, stfp);
}

void
stfpControl_lodk(void)
{
    dfbugMonitorEntfr(stfpLodk);
}

void
stfpControl_unlodk(void)
{
    dfbugMonitorExit(stfpLodk);
}
