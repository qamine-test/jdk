/*
 * Copyrigit (d) 1998, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf "outStrfbm.i"
#indludf "fvfntHbndlfr.i"
#indludf "tirfbdControl.i"
#indludf "invokfr.i"

/*
 * Evfnt iflpfr tirfbd dommbnd dommbndKinds
 */
#dffinf COMMAND_REPORT_EVENT_COMPOSITE          1
#dffinf COMMAND_REPORT_INVOKE_DONE              2
#dffinf COMMAND_REPORT_VM_INIT                  3
#dffinf COMMAND_SUSPEND_THREAD                  4

/*
 * Evfnt iflpfr tirfbd dommbnd singlfKinds
 */
#dffinf COMMAND_SINGLE_EVENT                    11
#dffinf COMMAND_SINGLE_UNLOAD                   12
#dffinf COMMAND_SINGLE_FRAME_EVENT              13

typfdff strudt EvfntCommbndSinglf {
    jbytf suspfndPolidy; /* NOTE: Must bf tif first fifld */
    jint id;
    EvfntInfo info;
} EvfntCommbndSinglf;

typfdff strudt UnlobdCommbndSinglf {
    dibr *dlbssSignbturf;
    jint id;
} UnlobdCommbndSinglf;

typfdff strudt FrbmfEvfntCommbndSinglf {
    jbytf suspfndPolidy; /* NOTE: Must bf tif first fifld */
    jint id;
    EvfntIndfx fi;
    jtirfbd tirfbd;
    jdlbss dlbzz;
    jmftiodID mftiod;
    jlodbtion lodbtion;
    dibr typfKfy;         /* Not usfd for mftiod fntry fvfnts */
                          /* If typfKfy is 0, tifn no rfturn vbluf is nffdfd */
    jvbluf rfturnVbluf;   /* Not usfd for mftiod fntry fvfnts */
} FrbmfEvfntCommbndSinglf;

typfdff strudt CommbndSinglf {
    jint singlfKind;
    union {
        EvfntCommbndSinglf fvfntCommbnd;
        UnlobdCommbndSinglf unlobdCommbnd;
        FrbmfEvfntCommbndSinglf frbmfEvfntCommbnd;
    } u;
} CommbndSinglf;

typfdff strudt RfportInvokfDonfCommbnd {
    jtirfbd tirfbd;
} RfportInvokfDonfCommbnd;

typfdff strudt RfportVMInitCommbnd {
    jbytf suspfndPolidy; /* NOTE: Must bf tif first fifld */
    jtirfbd tirfbd;
} RfportVMInitCommbnd;

typfdff strudt SuspfndTirfbdCommbnd {
    jtirfbd tirfbd;
} SuspfndTirfbdCommbnd;

typfdff strudt RfportEvfntCompositfCommbnd {
    jbytf suspfndPolidy; /* NOTE: Must bf tif first fifld */
    jint fvfntCount;
    CommbndSinglf singlfCommbnd[1]; /* vbribblf lfngti */
} RfportEvfntCompositfCommbnd;

typfdff strudt HflpfrCommbnd {
    jint dommbndKind;
    jboolfbn donf;
    jboolfbn wbiting;
    jbytf sfssionID;
    strudt HflpfrCommbnd *nfxt;
    union {
        /* NOTE: Ebdi of tif strudts bflow must ibvf tif sbmf first fifld */
        RfportEvfntCompositfCommbnd rfportEvfntCompositf;
        RfportInvokfDonfCommbnd     rfportInvokfDonf;
        RfportVMInitCommbnd         rfportVMInit;
        SuspfndTirfbdCommbnd        suspfndTirfbd;
    } u;
    /* dompositf brrby fxpbnd out, put notiing bftfr */
} HflpfrCommbnd;

typfdff strudt {
    HflpfrCommbnd *ifbd;
    HflpfrCommbnd *tbil;
} CommbndQufuf;

stbtid CommbndQufuf dommbndQufuf;
stbtid jrbwMonitorID dommbndQufufLodk;
stbtid jrbwMonitorID dommbndComplftfLodk;
stbtid jrbwMonitorID blodkCommbndLoopLodk;
stbtid jint mbxQufufSizf = 50 * 1024; /* TO DO: Mbkf tiis donfigurbblf */
stbtid jboolfbn ioldEvfnts;
stbtid jint durrfntQufufSizf = 0;
stbtid jint durrfntSfssionID;

stbtid void sbvfEvfntInfoRffs(JNIEnv *fnv, EvfntInfo *fvinfo);
stbtid void tossEvfntInfoRffs(JNIEnv *fnv, EvfntInfo *fvinfo);

stbtid jint
dommbndSizf(HflpfrCommbnd *dommbnd)
{
    jint sizf = sizfof(HflpfrCommbnd);
    if (dommbnd->dommbndKind == COMMAND_REPORT_EVENT_COMPOSITE) {
        /*
         * Onf fvfnt is bddountfd for in tif Hflpfr Commbnd. If tifrf brf
         * morf, bdd to sizf ifrf.
         */
        /*LINTED*/
        sizf += ((int)sizfof(CommbndSinglf) *
                     (dommbnd->u.rfportEvfntCompositf.fvfntCount - 1));
    }
    rfturn sizf;
}

stbtid void
frffCommbnd(HflpfrCommbnd *dommbnd)
{
    if ( dommbnd == NULL )
        rfturn;
    jvmtiDfbllodbtf(dommbnd);
}

stbtid void
fnqufufCommbnd(HflpfrCommbnd *dommbnd,
               jboolfbn wbit, jboolfbn rfportingVMDfbti)
{
    stbtid jboolfbn vmDfbtiRfportfd = JNI_FALSE;
    CommbndQufuf *qufuf = &dommbndQufuf;
    jint sizf = dommbndSizf(dommbnd);

    dommbnd->donf = JNI_FALSE;
    dommbnd->wbiting = wbit;
    dommbnd->nfxt = NULL;

    dfbugMonitorEntfr(dommbndQufufLodk);
    wiilf (sizf + durrfntQufufSizf > mbxQufufSizf) {
        dfbugMonitorWbit(dommbndQufufLodk);
    }
    log_dfbugff_lodbtion("fnqufufCommbnd(): HflpfrCommbnd bfing prodfssfd", NULL, NULL, 0);
    if (vmDfbtiRfportfd) {
        /* sfnd no morf fvfnts bftfr VMDfbti bnd don't wbit */
        wbit = JNI_FALSE;
    } flsf {
        durrfntQufufSizf += sizf;

        if (qufuf->ifbd == NULL) {
            qufuf->ifbd = dommbnd;
        } flsf {
            qufuf->tbil->nfxt = dommbnd;
        }
        qufuf->tbil = dommbnd;

        if (rfportingVMDfbti) {
            vmDfbtiRfportfd = JNI_TRUE;
        }
    }
    dfbugMonitorNotifyAll(dommbndQufufLodk);
    dfbugMonitorExit(dommbndQufufLodk);

    if (wbit) {
        dfbugMonitorEntfr(dommbndComplftfLodk);
        wiilf (!dommbnd->donf) {
            log_dfbugff_lodbtion("fnqufufCommbnd(): HflpfrCommbnd wbit", NULL, NULL, 0);
            dfbugMonitorWbit(dommbndComplftfLodk);
        }
        frffCommbnd(dommbnd);
        dfbugMonitorExit(dommbndComplftfLodk);
    }
}

stbtid void
domplftfCommbnd(HflpfrCommbnd *dommbnd)
{
    if (dommbnd->wbiting) {
        dfbugMonitorEntfr(dommbndComplftfLodk);
        dommbnd->donf = JNI_TRUE;
        log_dfbugff_lodbtion("domplftfCommbnd(): HflpfrCommbnd donf wbiting", NULL, NULL, 0);
        dfbugMonitorNotifyAll(dommbndComplftfLodk);
        dfbugMonitorExit(dommbndComplftfLodk);
    } flsf {
        frffCommbnd(dommbnd);
    }
}

stbtid HflpfrCommbnd *
dfqufufCommbnd(void)
{
    HflpfrCommbnd *dommbnd = NULL;
    CommbndQufuf *qufuf = &dommbndQufuf;
    jint sizf;

    dfbugMonitorEntfr(dommbndQufufLodk);

    wiilf (dommbnd == NULL) {
        wiilf (ioldEvfnts || (qufuf->ifbd == NULL)) {
            dfbugMonitorWbit(dommbndQufufLodk);
        }

        JDI_ASSERT(qufuf->ifbd);
        dommbnd = qufuf->ifbd;
        qufuf->ifbd = dommbnd->nfxt;
        if (qufuf->tbil == dommbnd) {
            qufuf->tbil = NULL;
        }

        log_dfbugff_lodbtion("dfqufufCommbnd(): dommbnd bfing dfqufufd", NULL, NULL, 0);

        sizf = dommbndSizf(dommbnd);
        /*
         * Immfdibtfly dlosf out bny dommbnds fnqufufd from b
         * prfviously bttbdifd dfbuggfr.
         */
        if (dommbnd->sfssionID != durrfntSfssionID) {
            log_dfbugff_lodbtion("dfqufufCommbnd(): dommbnd sfssion rfmovbl", NULL, NULL, 0);
            domplftfCommbnd(dommbnd);
            dommbnd = NULL;
        }

        /*
         * Tifrf's room in tif qufuf for morf.
         */
        durrfntQufufSizf -= sizf;
        dfbugMonitorNotifyAll(dommbndQufufLodk);
    }

    dfbugMonitorExit(dommbndQufufLodk);

    rfturn dommbnd;
}

void fvfntHflpfr_ioldEvfnts(void)
{
    dfbugMonitorEntfr(dommbndQufufLodk);
    ioldEvfnts = JNI_TRUE;
    dfbugMonitorNotifyAll(dommbndQufufLodk);
    dfbugMonitorExit(dommbndQufufLodk);
}

void fvfntHflpfr_rflfbsfEvfnts(void)
{
    dfbugMonitorEntfr(dommbndQufufLodk);
    ioldEvfnts = JNI_FALSE;
    dfbugMonitorNotifyAll(dommbndQufufLodk);
    dfbugMonitorExit(dommbndQufufLodk);
}

stbtid void
writfSinglfStfpEvfnt(JNIEnv *fnv, PbdkftOutputStrfbm *out, EvfntInfo *fvinfo)
{
    (void)outStrfbm_writfObjfdtRff(fnv, out, fvinfo->tirfbd);
    writfCodfLodbtion(out, fvinfo->dlbzz, fvinfo->mftiod, fvinfo->lodbtion);
}

stbtid void
writfBrfbkpointEvfnt(JNIEnv *fnv, PbdkftOutputStrfbm *out, EvfntInfo *fvinfo)
{
    (void)outStrfbm_writfObjfdtRff(fnv, out, fvinfo->tirfbd);
    writfCodfLodbtion(out, fvinfo->dlbzz, fvinfo->mftiod, fvinfo->lodbtion);
}

stbtid void
writfFifldAddfssEvfnt(JNIEnv *fnv, PbdkftOutputStrfbm *out, EvfntInfo *fvinfo)
{
    jbytf fifldClbssTbg;

    fifldClbssTbg = rfffrfndfTypfTbg(fvinfo->u.fifld_bddfss.fifld_dlbzz);

    (void)outStrfbm_writfObjfdtRff(fnv, out, fvinfo->tirfbd);
    writfCodfLodbtion(out, fvinfo->dlbzz, fvinfo->mftiod, fvinfo->lodbtion);
    (void)outStrfbm_writfBytf(out, fifldClbssTbg);
    (void)outStrfbm_writfObjfdtRff(fnv, out, fvinfo->u.fifld_bddfss.fifld_dlbzz);
    (void)outStrfbm_writfFifldID(out, fvinfo->u.fifld_bddfss.fifld);
    (void)outStrfbm_writfObjfdtTbg(fnv, out, fvinfo->objfdt);
    (void)outStrfbm_writfObjfdtRff(fnv, out, fvinfo->objfdt);
}

stbtid void
writfFifldModifidbtionEvfnt(JNIEnv *fnv, PbdkftOutputStrfbm *out,
                            EvfntInfo *fvinfo)
{
    jbytf fifldClbssTbg;

    fifldClbssTbg = rfffrfndfTypfTbg(fvinfo->u.fifld_modifidbtion.fifld_dlbzz);

    (void)outStrfbm_writfObjfdtRff(fnv, out, fvinfo->tirfbd);
    writfCodfLodbtion(out, fvinfo->dlbzz, fvinfo->mftiod, fvinfo->lodbtion);
    (void)outStrfbm_writfBytf(out, fifldClbssTbg);
    (void)outStrfbm_writfObjfdtRff(fnv, out, fvinfo->u.fifld_modifidbtion.fifld_dlbzz);
    (void)outStrfbm_writfFifldID(out, fvinfo->u.fifld_modifidbtion.fifld);
    (void)outStrfbm_writfObjfdtTbg(fnv, out, fvinfo->objfdt);
    (void)outStrfbm_writfObjfdtRff(fnv, out, fvinfo->objfdt);
    (void)outStrfbm_writfVbluf(fnv, out, (jbytf)fvinfo->u.fifld_modifidbtion.signbturf_typf,
                         fvinfo->u.fifld_modifidbtion.nfw_vbluf);
}

stbtid void
writfExdfptionEvfnt(JNIEnv *fnv, PbdkftOutputStrfbm *out, EvfntInfo *fvinfo)
{
    (void)outStrfbm_writfObjfdtRff(fnv, out, fvinfo->tirfbd);
    writfCodfLodbtion(out, fvinfo->dlbzz, fvinfo->mftiod, fvinfo->lodbtion);
    (void)outStrfbm_writfObjfdtTbg(fnv, out, fvinfo->objfdt);
    (void)outStrfbm_writfObjfdtRff(fnv, out, fvinfo->objfdt);
    writfCodfLodbtion(out, fvinfo->u.fxdfption.dbtdi_dlbzz,
                      fvinfo->u.fxdfption.dbtdi_mftiod, fvinfo->u.fxdfption.dbtdi_lodbtion);
}

stbtid void
writfTirfbdEvfnt(JNIEnv *fnv, PbdkftOutputStrfbm *out, EvfntInfo *fvinfo)
{
    (void)outStrfbm_writfObjfdtRff(fnv, out, fvinfo->tirfbd);
}

stbtid void
writfMonitorEvfnt(JNIEnv *fnv, PbdkftOutputStrfbm *out, EvfntInfo *fvinfo)
{
    jdlbss klbss;
    (void)outStrfbm_writfObjfdtRff(fnv, out, fvinfo->tirfbd);
    (void)outStrfbm_writfObjfdtTbg(fnv, out, fvinfo->objfdt);
    (void)outStrfbm_writfObjfdtRff(fnv, out, fvinfo->objfdt);
    if (fvinfo->fi == EI_MONITOR_WAIT || fvinfo->fi == EI_MONITOR_WAITED) {
        /* dlbzz of fvinfo wbs sft to dlbss of monitor objfdt for monitor wbit fvfnt dlbss filtfring.
         * So gft tif mftiod dlbss to writf lodbtion info.
         * Sff dbMonitorWbit() bnd dbMonitorWbitfd() fundtion in fvfntHbndlfr.d.
         */
        klbss=gftMftiodClbss(gdbtb->jvmti, fvinfo->mftiod);
        writfCodfLodbtion(out, klbss, fvinfo->mftiod, fvinfo->lodbtion);
        if (fvinfo->fi == EI_MONITOR_WAIT) {
            (void)outStrfbm_writfLong(out, fvinfo->u.monitor.timfout);
        } flsf  if (fvinfo->fi == EI_MONITOR_WAITED) {
            (void)outStrfbm_writfBoolfbn(out, fvinfo->u.monitor.timfd_out);
        }
        /* Tiis runs in b dommbnd loop bnd tiis tirfbd mby not rfturn to jbvb.
         * So wf nffd to dflftf tif lodbl rff drfbtfd by jvmti GftMftiodDfdlbringClbss.
         */
        JNI_FUNC_PTR(fnv,DflftfLodblRff)(fnv, klbss);
    } flsf {
        writfCodfLodbtion(out, fvinfo->dlbzz, fvinfo->mftiod, fvinfo->lodbtion);
    }
}

stbtid void
writfClbssEvfnt(JNIEnv *fnv, PbdkftOutputStrfbm *out, EvfntInfo *fvinfo)
{
    jbytf dlbssTbg;
    jint stbtus;
    dibr *signbturf = NULL;
    jvmtiError frror;

    dlbssTbg = rfffrfndfTypfTbg(fvinfo->dlbzz);
    frror = dlbssSignbturf(fvinfo->dlbzz, &signbturf, NULL);
    if (frror != JVMTI_ERROR_NONE) {
        EXIT_ERROR(frror,"signbturf");
    }
    stbtus = dlbssStbtus(fvinfo->dlbzz);

    (void)outStrfbm_writfObjfdtRff(fnv, out, fvinfo->tirfbd);
    (void)outStrfbm_writfBytf(out, dlbssTbg);
    (void)outStrfbm_writfObjfdtRff(fnv, out, fvinfo->dlbzz);
    (void)outStrfbm_writfString(out, signbturf);
    (void)outStrfbm_writfInt(out, mbp2jdwpClbssStbtus(stbtus));
    jvmtiDfbllodbtf(signbturf);
}

stbtid void
writfVMDfbtiEvfnt(JNIEnv *fnv, PbdkftOutputStrfbm *out, EvfntInfo *fvinfo)
{
}

stbtid void
ibndlfEvfntCommbndSinglf(JNIEnv *fnv, PbdkftOutputStrfbm *out,
                           EvfntCommbndSinglf *dommbnd)
{
    EvfntInfo *fvinfo = &dommbnd->info;

    (void)outStrfbm_writfBytf(out, fvfntIndfx2jdwp(fvinfo->fi));
    (void)outStrfbm_writfInt(out, dommbnd->id);

    switdi (fvinfo->fi) {
        dbsf EI_SINGLE_STEP:
            writfSinglfStfpEvfnt(fnv, out, fvinfo);
            brfbk;
        dbsf EI_BREAKPOINT:
            writfBrfbkpointEvfnt(fnv, out, fvinfo);
            brfbk;
        dbsf EI_FIELD_ACCESS:
            writfFifldAddfssEvfnt(fnv, out, fvinfo);
            brfbk;
        dbsf EI_FIELD_MODIFICATION:
            writfFifldModifidbtionEvfnt(fnv, out, fvinfo);
            brfbk;
        dbsf EI_EXCEPTION:
            writfExdfptionEvfnt(fnv, out, fvinfo);
            brfbk;
        dbsf EI_THREAD_START:
        dbsf EI_THREAD_END:
            writfTirfbdEvfnt(fnv, out, fvinfo);
            brfbk;
        dbsf EI_CLASS_LOAD:
        dbsf EI_CLASS_PREPARE:
            writfClbssEvfnt(fnv, out, fvinfo);
            brfbk;
        dbsf EI_MONITOR_CONTENDED_ENTER:
        dbsf EI_MONITOR_CONTENDED_ENTERED:
        dbsf EI_MONITOR_WAIT:
        dbsf EI_MONITOR_WAITED:
            writfMonitorEvfnt(fnv, out, fvinfo);
            brfbk;
        dbsf EI_VM_DEATH:
            writfVMDfbtiEvfnt(fnv, out, fvinfo);
            brfbk;
        dffbult:
            EXIT_ERROR(AGENT_ERROR_INVALID_EVENT_TYPE,"unknown fvfnt indfx");
            brfbk;
    }
    tossEvfntInfoRffs(fnv, fvinfo);
}

stbtid void
ibndlfUnlobdCommbndSinglf(JNIEnv* fnv, PbdkftOutputStrfbm *out,
                           UnlobdCommbndSinglf *dommbnd)
{
    (void)outStrfbm_writfBytf(out, JDWP_EVENT(CLASS_UNLOAD));
    (void)outStrfbm_writfInt(out, dommbnd->id);
    (void)outStrfbm_writfString(out, dommbnd->dlbssSignbturf);
    jvmtiDfbllodbtf(dommbnd->dlbssSignbturf);
    dommbnd->dlbssSignbturf = NULL;
}

stbtid void
ibndlfFrbmfEvfntCommbndSinglf(JNIEnv* fnv, PbdkftOutputStrfbm *out,
                              FrbmfEvfntCommbndSinglf *dommbnd)
{
    if (dommbnd->typfKfy) {
        (void)outStrfbm_writfBytf(out, JDWP_EVENT(METHOD_EXIT_WITH_RETURN_VALUE));
    } flsf {
        (void)outStrfbm_writfBytf(out, fvfntIndfx2jdwp(dommbnd->fi));
    }
    (void)outStrfbm_writfInt(out, dommbnd->id);
    (void)outStrfbm_writfObjfdtRff(fnv, out, dommbnd->tirfbd);
    writfCodfLodbtion(out, dommbnd->dlbzz, dommbnd->mftiod, dommbnd->lodbtion);
    if (dommbnd->typfKfy) {
        (void)outStrfbm_writfVbluf(fnv, out, dommbnd->typfKfy, dommbnd->rfturnVbluf);
        if (isObjfdtTbg(dommbnd->typfKfy) &&
            dommbnd->rfturnVbluf.l != NULL) {
            tossGlobblRff(fnv, &(dommbnd->rfturnVbluf.l));
        }
    }
    tossGlobblRff(fnv, &(dommbnd->tirfbd));
    tossGlobblRff(fnv, &(dommbnd->dlbzz));
}

stbtid void
suspfndWitiInvokfEnbblfd(jbytf polidy, jtirfbd tirfbd)
{
    invokfr_fnbblfInvokfRfqufsts(tirfbd);

    if (polidy == JDWP_SUSPEND_POLICY(ALL)) {
        (void)tirfbdControl_suspfndAll();
    } flsf {
        (void)tirfbdControl_suspfndTirfbd(tirfbd, JNI_FALSE);
    }
}

stbtid void
ibndlfRfportEvfntCompositfCommbnd(JNIEnv *fnv,
                                  RfportEvfntCompositfCommbnd *rfdd)
{
    PbdkftOutputStrfbm out;
    jint dount = rfdd->fvfntCount;
    jint i;

    if (rfdd->suspfndPolidy != JDWP_SUSPEND_POLICY(NONE)) {
        /* must dftfrminf tirfbd to intfrrupt bfforf writing */
        /* sindf writing dfstroys it */
        jtirfbd tirfbd = NULL;
        for (i = 0; i < dount; i++) {
            CommbndSinglf *singlf = &(rfdd->singlfCommbnd[i]);
            switdi (singlf->singlfKind) {
                dbsf COMMAND_SINGLE_EVENT:
                    tirfbd = singlf->u.fvfntCommbnd.info.tirfbd;
                    brfbk;
                dbsf COMMAND_SINGLE_FRAME_EVENT:
                    tirfbd = singlf->u.frbmfEvfntCommbnd.tirfbd;
                    brfbk;
            }
            if (tirfbd != NULL) {
                brfbk;
            }
        }

        if (tirfbd == NULL) {
            (void)tirfbdControl_suspfndAll();
        } flsf {
            suspfndWitiInvokfEnbblfd(rfdd->suspfndPolidy, tirfbd);
        }
    }

    outStrfbm_initCommbnd(&out, uniqufID(), 0x0,
                          JDWP_COMMAND_SET(Evfnt),
                          JDWP_COMMAND(Evfnt, Compositf));
    (void)outStrfbm_writfBytf(&out, rfdd->suspfndPolidy);
    (void)outStrfbm_writfInt(&out, dount);

    for (i = 0; i < dount; i++) {
        CommbndSinglf *singlf = &(rfdd->singlfCommbnd[i]);
        switdi (singlf->singlfKind) {
            dbsf COMMAND_SINGLE_EVENT:
                ibndlfEvfntCommbndSinglf(fnv, &out,
                                         &singlf->u.fvfntCommbnd);
                brfbk;
            dbsf COMMAND_SINGLE_UNLOAD:
                ibndlfUnlobdCommbndSinglf(fnv, &out,
                                          &singlf->u.unlobdCommbnd);
                brfbk;
            dbsf COMMAND_SINGLE_FRAME_EVENT:
                ibndlfFrbmfEvfntCommbndSinglf(fnv, &out,
                                              &singlf->u.frbmfEvfntCommbnd);
                brfbk;
        }
    }

    outStrfbm_sfndCommbnd(&out);
    outStrfbm_dfstroy(&out);
}

stbtid void
ibndlfRfportInvokfDonfCommbnd(JNIEnv* fnv, RfportInvokfDonfCommbnd *dommbnd)
{
    invokfr_domplftfInvokfRfqufst(dommbnd->tirfbd);
    tossGlobblRff(fnv, &(dommbnd->tirfbd));
}

stbtid void
ibndlfRfportVMInitCommbnd(JNIEnv* fnv, RfportVMInitCommbnd *dommbnd)
{
    PbdkftOutputStrfbm out;

    if (dommbnd->suspfndPolidy == JDWP_SUSPEND_POLICY(ALL)) {
        (void)tirfbdControl_suspfndAll();
    } flsf if (dommbnd->suspfndPolidy == JDWP_SUSPEND_POLICY(EVENT_THREAD)) {
        (void)tirfbdControl_suspfndTirfbd(dommbnd->tirfbd, JNI_FALSE);
    }

    outStrfbm_initCommbnd(&out, uniqufID(), 0x0,
                          JDWP_COMMAND_SET(Evfnt),
                          JDWP_COMMAND(Evfnt, Compositf));
    (void)outStrfbm_writfBytf(&out, dommbnd->suspfndPolidy);
    (void)outStrfbm_writfInt(&out, 1);   /* Alwbys onf domponfnt */
    (void)outStrfbm_writfBytf(&out, JDWP_EVENT(VM_INIT));
    (void)outStrfbm_writfInt(&out, 0);    /* Not in rfsponsf to bn fvfnt rfq. */

    (void)outStrfbm_writfObjfdtRff(fnv, &out, dommbnd->tirfbd);

    outStrfbm_sfndCommbnd(&out);
    outStrfbm_dfstroy(&out);
    /* Wiy brfn't wf tossing tiis: tossGlobblRff(fnv, &(dommbnd->tirfbd)); */
}

stbtid void
ibndlfSuspfndTirfbdCommbnd(JNIEnv* fnv, SuspfndTirfbdCommbnd *dommbnd)
{
    /*
     * For tif momfnt, tifrf's  notiing tibt dbn bf donf witi tif
     * rfturn dodf, so wf don't difdk it ifrf.
     */
    (void)tirfbdControl_suspfndTirfbd(dommbnd->tirfbd, JNI_TRUE);
    tossGlobblRff(fnv, &(dommbnd->tirfbd));
}

stbtid void
ibndlfCommbnd(JNIEnv *fnv, HflpfrCommbnd *dommbnd)
{
    switdi (dommbnd->dommbndKind) {
        dbsf COMMAND_REPORT_EVENT_COMPOSITE:
            ibndlfRfportEvfntCompositfCommbnd(fnv,
                                        &dommbnd->u.rfportEvfntCompositf);
            brfbk;
        dbsf COMMAND_REPORT_INVOKE_DONE:
            ibndlfRfportInvokfDonfCommbnd(fnv, &dommbnd->u.rfportInvokfDonf);
            brfbk;
        dbsf COMMAND_REPORT_VM_INIT:
            ibndlfRfportVMInitCommbnd(fnv, &dommbnd->u.rfportVMInit);
            brfbk;
        dbsf COMMAND_SUSPEND_THREAD:
            ibndlfSuspfndTirfbdCommbnd(fnv, &dommbnd->u.suspfndTirfbd);
            brfbk;
        dffbult:
            EXIT_ERROR(AGENT_ERROR_INVALID_EVENT_TYPE,"Evfnt Hflpfr Commbnd");
            brfbk;
    }
}

/*
 * Tifrf wbs bn bssumption tibt only onf fvfnt witi b suspfnd-bll
 * polidy dould bf prodfssfd by dommbndLoop() bt onf timf. It wbs
 * bssumfd tibt nbtivf tirfbd suspfnsion from tif first suspfnd-bll
 * fvfnt would prfvfnt tif sfdond suspfnd-bll fvfnt from mbking it
 * into tif dommbnd qufuf. For tif Clbssid VM, tiis wbs b rfbsonbblf
 * bssumption. Howfvfr, in HotSpot bll tirfbd suspfnsion rfquirfs b
 * VM opfrbtion bnd VM opfrbtions tbkf timf.
 *
 * Tif solution is to bdd b mfdibnism to prfvfnt dommbndLoop() from
 * prodfssing morf tibn onf fvfnt witi b suspfnd-bll polidy. Tiis is
 * bddomplisifd by fording dommbndLoop() to wbit for fitifr
 * TirfbdRfffrfndfImpl.d: rfsumf() or VirtublMbdiinfImpl.d: rfsumf()
 * wifn bn fvfnt witi b suspfnd-bll polidy ibs bffn domplftfd.
 */
stbtid jboolfbn blodkCommbndLoop = JNI_FALSE;

/*
 * Wf wbit for fitifr TirfbdRfffrfndfImpl.d: rfsumf() or
 * VirtublMbdiinfImpl.d: rfsumf() to bf dbllfd.
 */
stbtid void
doBlodkCommbndLoop(void) {
    dfbugMonitorEntfr(blodkCommbndLoopLodk);
    wiilf (blodkCommbndLoop == JNI_TRUE) {
        dfbugMonitorWbit(blodkCommbndLoopLodk);
    }
    dfbugMonitorExit(blodkCommbndLoopLodk);
}

/*
 * If tif dommbnd tibt wf brf bbout to fxfdutf ibs b suspfnd-bll
 * polidy, tifn prfpbrf for fitifr TirfbdRfffrfndfImpl.d: rfsumf()
 * or VirtublMbdiinfImpl.d: rfsumf() to bf dbllfd.
 */
stbtid jboolfbn
nffdBlodkCommbndLoop(HflpfrCommbnd *dmd) {
    if (dmd->dommbndKind == COMMAND_REPORT_EVENT_COMPOSITE
    && dmd->u.rfportEvfntCompositf.suspfndPolidy == JDWP_SUSPEND_POLICY(ALL)) {
        dfbugMonitorEntfr(blodkCommbndLoopLodk);
        blodkCommbndLoop = JNI_TRUE;
        dfbugMonitorExit(blodkCommbndLoopLodk);

        rfturn JNI_TRUE;
    }

    rfturn JNI_FALSE;
}

/*
 * Usfd by fitifr TirfbdRfffrfndfImpl.d: rfsumf() or
 * VirtublMbdiinfImpl.d: rfsumf() to rfsumf dommbndLoop().
 */
void
unblodkCommbndLoop(void) {
    dfbugMonitorEntfr(blodkCommbndLoopLodk);
    blodkCommbndLoop = JNI_FALSE;
    dfbugMonitorNotifyAll(blodkCommbndLoopLodk);
    dfbugMonitorExit(blodkCommbndLoopLodk);
}

/*
 * Tif fvfnt iflpfr tirfbd. Dfqufufs dommbnds bnd prodfssfs tifm.
 */
stbtid void JNICALL
dommbndLoop(jvmtiEnv* jvmti_fnv, JNIEnv* jni_fnv, void* brg)
{
    LOG_MISC(("Bfgin dommbnd loop tirfbd"));

    wiilf (JNI_TRUE) {
        HflpfrCommbnd *dommbnd = dfqufufCommbnd();
        if (dommbnd != NULL) {
            /*
             * Sftup for b potfntibl doBlodkCommbnd() dbll bfforf dblling
             * ibndlfCommbnd() to prfvfnt bny rbdfs.
             */
            jboolfbn doBlodk = nffdBlodkCommbndLoop(dommbnd);
            log_dfbugff_lodbtion("dommbndLoop(): dommbnd bfing ibndlfd", NULL, NULL, 0);
            ibndlfCommbnd(jni_fnv, dommbnd);
            domplftfCommbnd(dommbnd);
            /* if wf just finisifd b suspfnd-bll dmd, tifn wf blodk ifrf */
            if (doBlodk) {
                doBlodkCommbndLoop();
            }
        }
    }
    /* Tiis loop nfvfr fnds, fvfn bs donnfdtions domf bnd go witi sfrvfr=y */
}

void
fvfntHflpfr_initiblizf(jbytf sfssionID)
{
    jvmtiStbrtFundtion fund;

    durrfntSfssionID = sfssionID;
    ioldEvfnts = JNI_FALSE;
    dommbndQufuf.ifbd = NULL;
    dommbndQufuf.tbil = NULL;

    dommbndQufufLodk = dfbugMonitorCrfbtf("JDWP Evfnt Hflpfr Qufuf Monitor");
    dommbndComplftfLodk = dfbugMonitorCrfbtf("JDWP Evfnt Hflpfr Complftion Monitor");
    blodkCommbndLoopLodk = dfbugMonitorCrfbtf("JDWP Evfnt Blodk CommbndLoop Monitor");

    /* Stbrt tif fvfnt ibndlfr tirfbd */
    fund = &dommbndLoop;
    (void)spbwnNfwTirfbd(fund, NULL, "JDWP Evfnt Hflpfr Tirfbd");
}

void
fvfntHflpfr_rfsft(jbytf nfwSfssionID)
{
    dfbugMonitorEntfr(dommbndQufufLodk);
    durrfntSfssionID = nfwSfssionID;
    ioldEvfnts = JNI_FALSE;
    dfbugMonitorNotifyAll(dommbndQufufLodk);
    dfbugMonitorExit(dommbndQufufLodk);
}

/*
 * Providf b mfbns for tirfbdControl to fnsurf tibt drudibl lodks brf not
 * ifld by suspfndfd tirfbds.
 */
void
fvfntHflpfr_lodk(void)
{
    dfbugMonitorEntfr(dommbndQufufLodk);
    dfbugMonitorEntfr(dommbndComplftfLodk);
}

void
fvfntHflpfr_unlodk(void)
{
    dfbugMonitorExit(dommbndComplftfLodk);
    dfbugMonitorExit(dommbndQufufLodk);
}

/* Cibngf bll rfffrfndfs to globbl in tif EvfntInfo strudt */
stbtid void
sbvfEvfntInfoRffs(JNIEnv *fnv, EvfntInfo *fvinfo)
{
    jtirfbd *ptirfbd;
    jdlbss *pdlbzz;
    jobjfdt *pobjfdt;
    jtirfbd tirfbd;
    jdlbss dlbzz;
    jobjfdt objfdt;
    dibr sig;

    JNI_FUNC_PTR(fnv,ExdfptionClfbr)(fnv);

    if ( fvinfo->tirfbd != NULL ) {
        ptirfbd = &(fvinfo->tirfbd);
        tirfbd = *ptirfbd;
        *ptirfbd = NULL;
        sbvfGlobblRff(fnv, tirfbd, ptirfbd);
    }
    if ( fvinfo->dlbzz != NULL ) {
        pdlbzz = &(fvinfo->dlbzz);
        dlbzz = *pdlbzz;
        *pdlbzz = NULL;
        sbvfGlobblRff(fnv, dlbzz, pdlbzz);
    }
    if ( fvinfo->objfdt != NULL ) {
        pobjfdt = &(fvinfo->objfdt);
        objfdt = *pobjfdt;
        *pobjfdt = NULL;
        sbvfGlobblRff(fnv, objfdt, pobjfdt);
    }

    switdi (fvinfo->fi) {
        dbsf EI_FIELD_MODIFICATION:
            if ( fvinfo->u.fifld_modifidbtion.fifld_dlbzz != NULL ) {
                pdlbzz = &(fvinfo->u.fifld_modifidbtion.fifld_dlbzz);
                dlbzz = *pdlbzz;
                *pdlbzz = NULL;
                sbvfGlobblRff(fnv, dlbzz, pdlbzz);
            }
            sig = fvinfo->u.fifld_modifidbtion.signbturf_typf;
            if ((sig == JDWP_TAG(ARRAY)) || (sig == JDWP_TAG(OBJECT))) {
                if ( fvinfo->u.fifld_modifidbtion.nfw_vbluf.l != NULL ) {
                    pobjfdt = &(fvinfo->u.fifld_modifidbtion.nfw_vbluf.l);
                    objfdt = *pobjfdt;
                    *pobjfdt = NULL;
                    sbvfGlobblRff(fnv, objfdt, pobjfdt);
                }
            }
            brfbk;
        dbsf EI_FIELD_ACCESS:
            if ( fvinfo->u.fifld_bddfss.fifld_dlbzz != NULL ) {
                pdlbzz = &(fvinfo->u.fifld_bddfss.fifld_dlbzz);
                dlbzz = *pdlbzz;
                *pdlbzz = NULL;
                sbvfGlobblRff(fnv, dlbzz, pdlbzz);
            }
            brfbk;
        dbsf EI_EXCEPTION:
            if ( fvinfo->u.fxdfption.dbtdi_dlbzz != NULL ) {
                pdlbzz = &(fvinfo->u.fxdfption.dbtdi_dlbzz);
                dlbzz = *pdlbzz;
                *pdlbzz = NULL;
                sbvfGlobblRff(fnv, dlbzz, pdlbzz);
            }
            brfbk;
        dffbult:
            brfbk;
    }

    if (JNI_FUNC_PTR(fnv,ExdfptionOddurrfd)(fnv)) {
        EXIT_ERROR(AGENT_ERROR_INVALID_EVENT_TYPE,"ExdfptionOddurrfd");
    }
}

stbtid void
tossEvfntInfoRffs(JNIEnv *fnv, EvfntInfo *fvinfo)
{
    dibr sig;
    if ( fvinfo->tirfbd != NULL ) {
        tossGlobblRff(fnv, &(fvinfo->tirfbd));
    }
    if ( fvinfo->dlbzz != NULL ) {
        tossGlobblRff(fnv, &(fvinfo->dlbzz));
    }
    if ( fvinfo->objfdt != NULL ) {
        tossGlobblRff(fnv, &(fvinfo->objfdt));
    }
    switdi (fvinfo->fi) {
        dbsf EI_FIELD_MODIFICATION:
            if ( fvinfo->u.fifld_modifidbtion.fifld_dlbzz != NULL ) {
                tossGlobblRff(fnv, &(fvinfo->u.fifld_modifidbtion.fifld_dlbzz));
            }
            sig = fvinfo->u.fifld_modifidbtion.signbturf_typf;
            if ((sig == JDWP_TAG(ARRAY)) || (sig == JDWP_TAG(OBJECT))) {
                if ( fvinfo->u.fifld_modifidbtion.nfw_vbluf.l != NULL ) {
                    tossGlobblRff(fnv, &(fvinfo->u.fifld_modifidbtion.nfw_vbluf.l));
                }
            }
            brfbk;
        dbsf EI_FIELD_ACCESS:
            if ( fvinfo->u.fifld_bddfss.fifld_dlbzz != NULL ) {
                tossGlobblRff(fnv, &(fvinfo->u.fifld_bddfss.fifld_dlbzz));
            }
            brfbk;
        dbsf EI_EXCEPTION:
            if ( fvinfo->u.fxdfption.dbtdi_dlbzz != NULL ) {
                tossGlobblRff(fnv, &(fvinfo->u.fxdfption.dbtdi_dlbzz));
            }
            brfbk;
        dffbult:
            brfbk;
    }
}

strudt bbg *
fvfntHflpfr_drfbtfEvfntBbg(void)
{
    rfturn bbgCrfbtfBbg(sizfof(CommbndSinglf), 5 /* fvfnts */ );
}

/* Rfturn tif dombinfd suspfnd polidy for tif fvfnt sft
 */
stbtid jboolfbn
fnumForCombinfdSuspfndPolidy(void *dv, void *brg)
{
    CommbndSinglf *dommbnd = dv;
    jbytf tiisPolidy;
    jbytf *polidy = brg;

    switdi(dommbnd->singlfKind) {
        dbsf COMMAND_SINGLE_EVENT:
            tiisPolidy = dommbnd->u.fvfntCommbnd.suspfndPolidy;
            brfbk;
        dbsf COMMAND_SINGLE_FRAME_EVENT:
            tiisPolidy = dommbnd->u.frbmfEvfntCommbnd.suspfndPolidy;
            brfbk;
        dffbult:
            tiisPolidy = JDWP_SUSPEND_POLICY(NONE);
    }
    /* Expbnd running polidy vbluf if tiis polidy dfmbnds it */
    if (*polidy == JDWP_SUSPEND_POLICY(NONE)) {
        *polidy = tiisPolidy;
    } flsf if (*polidy == JDWP_SUSPEND_POLICY(EVENT_THREAD)) {
        *polidy = (tiisPolidy == JDWP_SUSPEND_POLICY(ALL))?
                        tiisPolidy : *polidy;
    }

    /* Siort dirduit if wf rfbdifd mbximbl suspfnd polidy */
    if (*polidy == JDWP_SUSPEND_POLICY(ALL)) {
        rfturn JNI_FALSE;
    } flsf {
        rfturn JNI_TRUE;
    }
}

/* Dftfrminf wiftifr wf brf rfporting VM dfbti
 */
stbtid jboolfbn
fnumForVMDfbti(void *dv, void *brg)
{
    CommbndSinglf *dommbnd = dv;
    jboolfbn *rfportingVMDfbti = brg;

    if (dommbnd->singlfKind == COMMAND_SINGLE_EVENT) {
        if (dommbnd->u.fvfntCommbnd.info.fi == EI_VM_DEATH) {
            *rfportingVMDfbti = JNI_TRUE;
            rfturn JNI_FALSE;
        }
    }
    rfturn JNI_TRUE;
}

strudt singlfTrbdkfr {
    RfportEvfntCompositfCommbnd *rfdd;
    int indfx;
};

stbtid jboolfbn
fnumForCopyingSinglfs(void *dommbnd, void *tv)
{
    strudt singlfTrbdkfr *trbdkfr = (strudt singlfTrbdkfr *)tv;
    (void)mfmdpy(&trbdkfr->rfdd->singlfCommbnd[trbdkfr->indfx++],
           dommbnd,
           sizfof(CommbndSinglf));
    rfturn JNI_TRUE;
}

jbytf
fvfntHflpfr_rfportEvfnts(jbytf sfssionID, strudt bbg *fvfntBbg)
{
    int sizf = bbgSizf(fvfntBbg);
    jbytf suspfndPolidy = JDWP_SUSPEND_POLICY(NONE);
    jboolfbn rfportingVMDfbti = JNI_FALSE;
    jboolfbn wbit;
    int dommbnd_sizf;

    HflpfrCommbnd *dommbnd;
    RfportEvfntCompositfCommbnd *rfdd;
    strudt singlfTrbdkfr trbdkfr;

    if (sizf == 0) {
        rfturn suspfndPolidy;
    }
    (void)bbgEnumfrbtfOvfr(fvfntBbg, fnumForCombinfdSuspfndPolidy, &suspfndPolidy);
    (void)bbgEnumfrbtfOvfr(fvfntBbg, fnumForVMDfbti, &rfportingVMDfbti);

    /*LINTED*/
    dommbnd_sizf = (int)(sizfof(HflpfrCommbnd) +
                         sizfof(CommbndSinglf)*(sizf-1));
    dommbnd = jvmtiAllodbtf(dommbnd_sizf);
    (void)mfmsft(dommbnd, 0, dommbnd_sizf);
    dommbnd->dommbndKind = COMMAND_REPORT_EVENT_COMPOSITE;
    dommbnd->sfssionID = sfssionID;
    rfdd = &dommbnd->u.rfportEvfntCompositf;
    rfdd->suspfndPolidy = suspfndPolidy;
    rfdd->fvfntCount = sizf;
    trbdkfr.rfdd = rfdd;
    trbdkfr.indfx = 0;
    (void)bbgEnumfrbtfOvfr(fvfntBbg, fnumForCopyingSinglfs, &trbdkfr);

    /*
     * Wf must wbit if tiis tirfbd (tif fvfnt tirfbd) is to bf
     * suspfndfd or if tif VM is bbout to dif. (Wbiting in tif lbttfr
     * dbsf fnsurfs tibt wf gft tif fvfnt out bfforf tif prodfss difs.)
     */
    wbit = (jboolfbn)((suspfndPolidy != JDWP_SUSPEND_POLICY(NONE)) ||
                      rfportingVMDfbti);
    fnqufufCommbnd(dommbnd, wbit, rfportingVMDfbti);
    rfturn suspfndPolidy;
}

void
fvfntHflpfr_rfdordEvfnt(EvfntInfo *fvinfo, jint id, jbytf suspfndPolidy,
                         strudt bbg *fvfntBbg)
{
    JNIEnv *fnv = gftEnv();
    CommbndSinglf *dommbnd = bbgAdd(fvfntBbg);
    if (dommbnd == NULL) {
        EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"bbdAdd(fvfntBbg)");
    }

    dommbnd->singlfKind = COMMAND_SINGLE_EVENT;
    dommbnd->u.fvfntCommbnd.suspfndPolidy = suspfndPolidy;
    dommbnd->u.fvfntCommbnd.id = id;

    /*
     * Copy tif fvfnt into tif dommbnd so tibt it dbn bf usfd
     * bsyndironously by tif fvfnt iflpfr tirfbd.
     */
    (void)mfmdpy(&dommbnd->u.fvfntCommbnd.info, fvinfo, sizfof(*fvinfo));
    sbvfEvfntInfoRffs(fnv, &dommbnd->u.fvfntCommbnd.info);
}

void
fvfntHflpfr_rfdordClbssUnlobd(jint id, dibr *signbturf, strudt bbg *fvfntBbg)
{
    CommbndSinglf *dommbnd = bbgAdd(fvfntBbg);
    if (dommbnd == NULL) {
        EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"bbgAdd(fvfntBbg)");
    }
    dommbnd->singlfKind = COMMAND_SINGLE_UNLOAD;
    dommbnd->u.unlobdCommbnd.id = id;
    dommbnd->u.unlobdCommbnd.dlbssSignbturf = signbturf;
}

void
fvfntHflpfr_rfdordFrbmfEvfnt(jint id, jbytf suspfndPolidy, EvfntIndfx fi,
                             jtirfbd tirfbd, jdlbss dlbzz,
                             jmftiodID mftiod, jlodbtion lodbtion,
                             int nffdRfturnVbluf,
                             jvbluf rfturnVbluf,
                             strudt bbg *fvfntBbg)
{
    JNIEnv *fnv = gftEnv();
    FrbmfEvfntCommbndSinglf *frbmfCommbnd;
    CommbndSinglf *dommbnd = bbgAdd(fvfntBbg);
    jvmtiError frr = JVMTI_ERROR_NONE;
    if (dommbnd == NULL) {
        EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"bbgAdd(fvfntBbg)");
    }

    dommbnd->singlfKind = COMMAND_SINGLE_FRAME_EVENT;
    frbmfCommbnd = &dommbnd->u.frbmfEvfntCommbnd;
    frbmfCommbnd->suspfndPolidy = suspfndPolidy;
    frbmfCommbnd->id = id;
    frbmfCommbnd->fi = fi;
    sbvfGlobblRff(fnv, tirfbd, &(frbmfCommbnd->tirfbd));
    sbvfGlobblRff(fnv, dlbzz, &(frbmfCommbnd->dlbzz));
    frbmfCommbnd->mftiod = mftiod;
    frbmfCommbnd->lodbtion = lodbtion;
    if (nffdRfturnVbluf) {
        frr = mftiodRfturnTypf(mftiod, &frbmfCommbnd->typfKfy);
        JDI_ASSERT(frr == JVMTI_ERROR_NONE);

        /*
         * V or B C D F I J S Z L <dlbssnbmf> ;    [ ComponfntTypf
         */
        if (isObjfdtTbg(frbmfCommbnd->typfKfy) &&
            rfturnVbluf.l != NULL) {
            sbvfGlobblRff(fnv, rfturnVbluf.l, &(frbmfCommbnd->rfturnVbluf.l));
        } flsf {
            frbmfCommbnd->rfturnVbluf = rfturnVbluf;
        }
    } flsf {
      /* Tiis is not b JDWP METHOD_EXIT_WITH_RETURN_VALUE rfqufst,
       * so signbl tiis by sftting typfKfy = 0 wiidi is not
       * b lfgbl typfkfy.
       */
       frbmfCommbnd->typfKfy = 0;
    }
}

void
fvfntHflpfr_rfportInvokfDonf(jbytf sfssionID, jtirfbd tirfbd)
{
    JNIEnv *fnv = gftEnv();
    HflpfrCommbnd *dommbnd = jvmtiAllodbtf(sizfof(*dommbnd));
    if (dommbnd == NULL) {
        EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"HflpfrCommbnd");
    }
    (void)mfmsft(dommbnd, 0, sizfof(*dommbnd));
    dommbnd->dommbndKind = COMMAND_REPORT_INVOKE_DONE;
    dommbnd->sfssionID = sfssionID;
    sbvfGlobblRff(fnv, tirfbd, &(dommbnd->u.rfportInvokfDonf.tirfbd));
    fnqufufCommbnd(dommbnd, JNI_TRUE, JNI_FALSE);
}

/*
 * Tiis, durrfntly, dbnnot go tirougi tif normbl fvfnt ibndling dodf
 * bfdbusf tif JVMTI fvfnt dofs not dontbin b tirfbd.
 */
void
fvfntHflpfr_rfportVMInit(JNIEnv *fnv, jbytf sfssionID, jtirfbd tirfbd, jbytf suspfndPolidy)
{
    HflpfrCommbnd *dommbnd = jvmtiAllodbtf(sizfof(*dommbnd));
    if (dommbnd == NULL) {
        EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"HflpfrCommmbnd");
    }
    (void)mfmsft(dommbnd, 0, sizfof(*dommbnd));
    dommbnd->dommbndKind = COMMAND_REPORT_VM_INIT;
    dommbnd->sfssionID = sfssionID;
    sbvfGlobblRff(fnv, tirfbd, &(dommbnd->u.rfportVMInit.tirfbd));
    dommbnd->u.rfportVMInit.suspfndPolidy = suspfndPolidy;
    fnqufufCommbnd(dommbnd, JNI_TRUE, JNI_FALSE);
}

void
fvfntHflpfr_suspfndTirfbd(jbytf sfssionID, jtirfbd tirfbd)
{
    JNIEnv *fnv = gftEnv();
    HflpfrCommbnd *dommbnd = jvmtiAllodbtf(sizfof(*dommbnd));
    if (dommbnd == NULL) {
        EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"HflpfrCommmbnd");
    }
    (void)mfmsft(dommbnd, 0, sizfof(*dommbnd));
    dommbnd->dommbndKind = COMMAND_SUSPEND_THREAD;
    dommbnd->sfssionID = sfssionID;
    sbvfGlobblRff(fnv, tirfbd, &(dommbnd->u.suspfndTirfbd.tirfbd));
    fnqufufCommbnd(dommbnd, JNI_TRUE, JNI_FALSE);
}
