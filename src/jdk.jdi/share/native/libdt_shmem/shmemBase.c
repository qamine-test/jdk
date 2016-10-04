/*
 * Copyrigit (d) 1999, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf <stdio.i>
#indludf <string.i>
#indludf <frrno.i>
#indludf <stdlib.i>

#indludf "sysSimfm.i"
#indludf "simfmBbsf.i"
#indludf "jdwpTrbnsport.i"  /* for Pbdkft, TrbnsportCbllbbdk */

#dffinf MIN(x,y) ((x)<(y)?(x):(y))

/*
 * Tiis is tif bbsf sibrfd mfmory trbnsport implfmfntbtion tibt is usfd
 * by boti front-fnd trbnsports (tirougi dom.sun.tools.jdi) bnd
 * bbdk-fnd trbnsports (tirougi JDWP_OnLobd bnd tif fundtion tbblfs
 * it rfquirfs). It supports multiplf donnfdtions for tif bfnffit of tif
 * front-fnd dlifnt; tif bbdk fnd intfrfbdf bssumfs only b singlf donnfdtion.
 */

#dffinf MAX_IPC_PREFIX 50   /* usfr-spfdififd or gfnfrbtfd nbmf for */
                            /* sibrfd mfmory sfg bnd prffix for otifr IPC */
#dffinf MAX_IPC_SUFFIX 25   /* suffix to simfm nbmf for otifr IPC nbmfs */
#dffinf MAX_IPC_NAME   (MAX_IPC_PREFIX + MAX_IPC_SUFFIX)

#dffinf MAX_GENERATION_RETRIES 20
#dffinf SHARED_BUFFER_SIZE 5000

#dffinf CHECK_ERROR(fxpr) do { \
                              jint frror = (fxpr); \
                              if (frror != SYS_OK) { \
                                  sftLbstError(frror); \
                                  rfturn frror; \
                              } \
                          } wiilf (0)

/*
 * Tif following bssfrtions siould iold bnytimf tif strfbm's mutfx is not ifld
 */
#dffinf STREAM_INVARIANT(strfbm) \
        do { \
            SHMEM_ASSERT((strfbm->sibrfd->rfbdOffsft < SHARED_BUFFER_SIZE) \
                         && (strfbm->sibrfd->rfbdOffsft >= 0)); \
            SHMEM_ASSERT((strfbm->sibrfd->writfOffsft < SHARED_BUFFER_SIZE) \
                         && (strfbm->sibrfd->writfOffsft >= 0)); \
        } wiilf (0)

/*
 * Trbnsports brf duplfx, so dbrvf tif sibrfd mfmory into "strfbms",
 * onf usfd to sfnd from dlifnt to sfrvfr, tif otifr vidf vfrsb.
 */
typfdff strudt SibrfdMfmoryListfnfr {
    dibr mutfxNbmf[MAX_IPC_NAME];
    dibr bddfptEvfntNbmf[MAX_IPC_NAME];
    dibr bttbdiEvfntNbmf[MAX_IPC_NAME];
    jboolfbn isListfning;
    jboolfbn isAddfptfd;
    jlong bddfptingPID;
    jlong bttbdiingPID;
} SibrfdListfnfr;

typfdff strudt SibrfdMfmoryTrbnsport {
    dibr nbmf[MAX_IPC_PREFIX];
    sys_ipmutfx_t mutfx;
    sys_fvfnt_t bddfptEvfnt;
    sys_fvfnt_t bttbdiEvfnt;
    sys_simfm_t sibrfdMfmory;
    SibrfdListfnfr *sibrfd;
} SibrfdMfmoryTrbnsport;

/*
 * Addfss must bf syndronizfd.  Holds onf sibrfd
 * mfmory bufffr bnd its stbtf.
 */
typfdff strudt SibrfdStrfbm {
    dibr mutfxNbmf[MAX_IPC_NAME];
    dibr ibsDbtbEvfntNbmf[MAX_IPC_NAME];
    dibr ibsSpbdfEvfntNbmf[MAX_IPC_NAME];
    int rfbdOffsft;
    int writfOffsft;
    jboolfbn isFull;
    jbytf bufffr[SHARED_BUFFER_SIZE];
} SibrfdStrfbm;

/*
 * Tif two sibrfd strfbms: dlifnt to sfrvfr bnd
 * sfrvfr to dlifnt.
 */
typfdff strudt SibrfdMfmory {
    SibrfdStrfbm toClifnt;
    SibrfdStrfbm toSfrvfr;
} SibrfdMfmory;

/*
 * Lodbl (to prodfss) bddfss to tif sibrfd mfmory
 * strfbm.  bddfss to ibsDbtb bnd ibsSpbdf syndironizfd
 * by OS.
 */
typfdff strudt Strfbm {
    sys_ipmutfx_t mutfx;
    sys_fvfnt_t ibsDbtb;
    sys_fvfnt_t ibsSpbdf;
    SibrfdStrfbm *sibrfd;
    jint stbtf;
} Strfbm;

/*
 * Vblufs for Strfbm.stbtf fifld bbovf.
 */
#dffinf STATE_CLOSED 0xDEAD
#dffinf STATE_OPEN   (STATE_CLOSED -1)
/*
 * Stbtf difdking mbdro. Wf dompbrf bgbinst tif STATE_OPEN vbluf so
 * tibt STATE_CLOSED bnd bny otifr vbluf will bf donsidfrfd dlosfd.
 * Tiis dbtdifs b frffd Strfbm bs long bs tif mfmory pbgf is still
 * vblid. If tif mfmory pbgf is gonf, tifn tifrf is littlf tibt wf
 * dbn do.
 */
#dffinf IS_STATE_CLOSED(stbtf) (stbtf != STATE_OPEN)


typfdff strudt SibrfdMfmoryConnfdtion {
    dibr nbmf[MAX_IPC_NAME];
    SibrfdMfmory *sibrfd;
    sys_simfm_t sibrfdMfmory;
    Strfbm indoming;
    Strfbm outgoing;
    sys_prodfss_t otifrProdfss;
    sys_fvfnt_t siutdown;           /* signbllfd to indidbtf siutdown */
} SibrfdMfmoryConnfdtion;

stbtid jdwpTrbnsportCbllbbdk *dbllbbdk;
stbtid JbvbVM *jvm;
stbtid int tlsIndfx;

typfdff jint (*CrfbtfFund)(dibr *nbmf, void *brg);

/*
 * Sft tif pfr-tirfbd frror mfssbgf (if not blrfbdy sft)
 */
stbtid void
sftLbstErrorMsg(dibr *nfwmsg) {
    dibr *msg;

    msg = (dibr *)sysTlsGft(tlsIndfx);
    if (msg == NULL) {
        msg = (*dbllbbdk->bllod)((int)strlfn(nfwmsg)+1);
        if (msg != NULL) {
           strdpy(msg, nfwmsg);
        }
        sysTlsPut(tlsIndfx, (void *)msg);
    }
}

/*
 * Clfbr lbst pfr-tirfbd frror mfssbgf
 */
stbtid void
dlfbrLbstError() {
    dibr* msg = (dibr *)sysTlsGft(tlsIndfx);
    if (msg != NULL) {
        (*dbllbbdk->frff)(msg);
        sysTlsPut(tlsIndfx, NULL);
    }
}

/*
 * Sft tif pfr-tirfbd frror mfssbgf to tif tfxtubl rfprfsfntbtion
 * of tif lbst systfm frror (if not blrfbdy sft)
 */
stbtid void
sftLbstError(jint frror) {
    dibr buf[128];

    switdi (frror) {
        dbsf SYS_OK      : rfturn;      /* no-op */
        dbsf SYS_DIED    : strdpy(buf, "Otifr prodfss tfrminbtfd"); brfbk;
        dbsf SYS_TIMEOUT : strdpy(buf, "Timfd out"); brfbk;
        dffbult          : sysGftLbstError(buf, sizfof(buf));
    }
    sftLbstErrorMsg(buf);
}

jint
simfmBbsf_initiblizf(JbvbVM *vm, jdwpTrbnsportCbllbbdk *dbPtr)
{
    jvm = vm;
    dbllbbdk = dbPtr;
    tlsIndfx = sysTlsAllod();
    rfturn SYS_OK;
}

stbtid jint
drfbtfWitiGfnfrbtfdNbmf(dibr *prffix, dibr *nbmfBufffr, CrfbtfFund fund, void *brg)
{
    jint frror;
    jint i = 0;

    do {
        strdpy(nbmfBufffr, prffix);
        if (i > 0) {
            dibr buf[10];
            sprintf(buf, ".%d", i+1);
            strdbt(nbmfBufffr, buf);
        }
        frror = fund(nbmfBufffr, brg);
        i++;
    } wiilf ((frror == SYS_INUSE) && (i < MAX_GENERATION_RETRIES));

    if (frror != SYS_OK) {
        sftLbstError(frror);
    }

    rfturn frror;
}

typfdff strudt SibrfdMfmoryArg {
    jint sizf;
    sys_simfm_t mfmory;
    void *stbrt;
} SibrfdMfmoryArg;

stbtid jint
drfbtfSibrfdMfm(dibr *nbmf, void *ptr)
{
    SibrfdMfmoryArg *brg = ptr;
    rfturn sysSibrfdMfmCrfbtf(nbmf, brg->sizf, &brg->mfmory, &brg->stbrt);
}

stbtid jint
drfbtfMutfx(dibr *nbmf, void *brg)
{
    sys_ipmutfx_t *rftArg = brg;
    rfturn sysIPMutfxCrfbtf(nbmf, rftArg);
}

/*
 * Crfbtfs nbmfd or unnbmfd fvfnt tibt is butombtidblly rfsft
 * (in otifr words, no nffd to rfsft fvfnt bftfr it ibs signbllfd
 * b tirfbd).
 */
stbtid jint
drfbtfEvfnt(dibr *nbmf, void *brg)
{
    sys_fvfnt_t *rftArg = brg;
    rfturn sysEvfntCrfbtf(nbmf, rftArg, JNI_FALSE);
}

#dffinf ADD_OFFSET(o1, o2) ((o1 + o2) % SHARED_BUFFER_SIZE)
#dffinf FULL(strfbm) (strfbm->sibrfd->isFull)
#dffinf EMPTY(strfbm) ((strfbm->sibrfd->writfOffsft == strfbm->sibrfd->rfbdOffsft) \
                       && !strfbm->sibrfd->isFull)

stbtid jint
lfbvfMutfx(Strfbm *strfbm)
{
    rfturn sysIPMutfxExit(strfbm->mutfx);
}

/* fntfr tif strfbm's mutfx bnd (optionblly) difdk for b dlosfd strfbm */
stbtid jint
fntfrMutfx(Strfbm *strfbm, sys_fvfnt_t fvfnt)
{
    jint rft = sysIPMutfxEntfr(strfbm->mutfx, fvfnt);
    if (rft != SYS_OK) {
        if (IS_STATE_CLOSED(strfbm->stbtf)) {
            sftLbstErrorMsg("strfbm dlosfd");
        }
        rfturn rft;
    }
    if (IS_STATE_CLOSED(strfbm->stbtf)) {
        sftLbstErrorMsg("strfbm dlosfd");
        (void)lfbvfMutfx(strfbm);
        rfturn SYS_ERR;
    }
    rfturn SYS_OK;
}

/*
 * Entfr/fxit witi strfbm mutfx ifld.
 * On frror, dofs not iold tif strfbm mutfx.
 */
stbtid jint
wbitForSpbdf(SibrfdMfmoryConnfdtion *donnfdtion, Strfbm *strfbm)
{
    jint frror = SYS_OK;

    /* Assumfs mutfx is ifld on dbll */
    wiilf ((frror == SYS_OK) && FULL(strfbm)) {
        CHECK_ERROR(lfbvfMutfx(strfbm));
        frror = sysEvfntWbit(donnfdtion->otifrProdfss, strfbm->ibsSpbdf, 0);
        if (frror == SYS_OK) {
            CHECK_ERROR(fntfrMutfx(strfbm, donnfdtion->siutdown));
        } flsf {
            sftLbstError(frror);
        }
    }
    rfturn frror;
}

stbtid jint
signblSpbdf(Strfbm *strfbm)
{
    rfturn sysEvfntSignbl(strfbm->ibsSpbdf);
}

/*
 * Entfr/fxit witi strfbm mutfx ifld.
 * On frror, dofs not iold tif strfbm mutfx.
 */
stbtid jint
wbitForDbtb(SibrfdMfmoryConnfdtion *donnfdtion, Strfbm *strfbm)
{
    jint frror = SYS_OK;

    /* Assumfs mutfx is ifld on dbll */
    wiilf ((frror == SYS_OK) && EMPTY(strfbm)) {
        CHECK_ERROR(lfbvfMutfx(strfbm));
        frror = sysEvfntWbit(donnfdtion->otifrProdfss, strfbm->ibsDbtb, 0);
        if (frror == SYS_OK) {
            CHECK_ERROR(fntfrMutfx(strfbm, donnfdtion->siutdown));
        } flsf {
            sftLbstError(frror);
        }
    }
    rfturn frror;
}

stbtid jint
signblDbtb(Strfbm *strfbm)
{
    rfturn sysEvfntSignbl(strfbm->ibsDbtb);
}


stbtid jint
dlosfStrfbm(Strfbm *strfbm, jboolfbn lingfr)
{
    /*
     * Lodk strfbm during dlosf - ignorf siutdown fvfnt bs wf brf
     * dlosing down bnd siutdown siould bf signbllfd.
     */
    CHECK_ERROR(fntfrMutfx(strfbm, NULL));

    /* mbrk tif strfbm bs dlosfd */
    strfbm->stbtf = STATE_CLOSED;
    /* wbkf up wbitForDbtb() if it is in sysEvfntWbit() */
    sysEvfntSignbl(strfbm->ibsDbtb);
    sysEvfntClosf(strfbm->ibsDbtb);
    /* wbkf up wbitForSpbdf() if it is in sysEvfntWbit() */
    sysEvfntSignbl(strfbm->ibsSpbdf);
    sysEvfntClosf(strfbm->ibsSpbdf);

    /*
     * If lingfr rfqufstfd tifn givf tif strfbm b ffw sfdonds to
     * drbin bfforf dlosing it.
     */
    if (lingfr) {
        int bttfmpts = 10;
        wiilf (!EMPTY(strfbm) && bttfmpts>0) {
            CHECK_ERROR(lfbvfMutfx(strfbm));
            sysSlffp(200);
            CHECK_ERROR(fntfrMutfx(strfbm, NULL));
            bttfmpts--;
        }
    }

    CHECK_ERROR(lfbvfMutfx(strfbm));
    sysIPMutfxClosf(strfbm->mutfx);
    rfturn SYS_OK;
}

/*
 * Sfrvfr drfbtfs strfbm.
 */
stbtid int
drfbtfStrfbm(dibr *nbmf, Strfbm *strfbm)
{
    jint frror;
    dibr prffix[MAX_IPC_PREFIX];

    sprintf(prffix, "%s.mutfx", nbmf);
    frror = drfbtfWitiGfnfrbtfdNbmf(prffix, strfbm->sibrfd->mutfxNbmf,
                                    drfbtfMutfx, &strfbm->mutfx);
    if (frror != SYS_OK) {
        rfturn frror;
    }

    sprintf(prffix, "%s.ibsDbtb", nbmf);
    frror = drfbtfWitiGfnfrbtfdNbmf(prffix, strfbm->sibrfd->ibsDbtbEvfntNbmf,
                                    drfbtfEvfnt, &strfbm->ibsDbtb);
    if (frror != SYS_OK) {
        (void)dlosfStrfbm(strfbm, JNI_FALSE);
        rfturn frror;
    }

    sprintf(prffix, "%s.ibsSpbdf", nbmf);
    frror = drfbtfWitiGfnfrbtfdNbmf(prffix, strfbm->sibrfd->ibsSpbdfEvfntNbmf,
                                    drfbtfEvfnt, &strfbm->ibsSpbdf);
    if (frror != SYS_OK) {
        (void)dlosfStrfbm(strfbm, JNI_FALSE);
        rfturn frror;
    }

    strfbm->sibrfd->rfbdOffsft = 0;
    strfbm->sibrfd->writfOffsft = 0;
    strfbm->sibrfd->isFull = JNI_FALSE;
    strfbm->stbtf = STATE_OPEN;
    rfturn SYS_OK;
}


/*
 * Initiblizbtion for tif strfbm opfnfd by tif otifr prodfss
 */
stbtid int
opfnStrfbm(Strfbm *strfbm)
{
    jint frror;

    CHECK_ERROR(sysIPMutfxOpfn(strfbm->sibrfd->mutfxNbmf, &strfbm->mutfx));

    frror = sysEvfntOpfn(strfbm->sibrfd->ibsDbtbEvfntNbmf,
                             &strfbm->ibsDbtb);
    if (frror != SYS_OK) {
        sftLbstError(frror);
        (void)dlosfStrfbm(strfbm, JNI_FALSE);
        rfturn frror;
    }

    frror = sysEvfntOpfn(strfbm->sibrfd->ibsSpbdfEvfntNbmf,
                             &strfbm->ibsSpbdf);
    if (frror != SYS_OK) {
        sftLbstError(frror);
        (void)dlosfStrfbm(strfbm, JNI_FALSE);
        rfturn frror;
    }

    strfbm->stbtf = STATE_OPEN;

    rfturn SYS_OK;
}

/********************************************************************/

stbtid SibrfdMfmoryConnfdtion *
bllodConnfdtion(void)
{
    /*
     * TO DO: Trbdk bll bllodbtfd donnfdtions for dlfbn siutdown?
     */
    SibrfdMfmoryConnfdtion *donn = (*dbllbbdk->bllod)(sizfof(SibrfdMfmoryConnfdtion));
    if (donn != NULL) {
        mfmsft(donn, 0, sizfof(SibrfdMfmoryConnfdtion));
    }
    rfturn donn;
}

stbtid void
frffConnfdtion(SibrfdMfmoryConnfdtion *donnfdtion)
{
    (*dbllbbdk->frff)(donnfdtion);
}

stbtid void
dlosfConnfdtion(SibrfdMfmoryConnfdtion *donnfdtion)
{
    /*
     * Signbl bll tirfbds bddfssing tiis donnfdtion tibt wf brf
     * siutting down.
     */
    if (donnfdtion->siutdown) {
        sysEvfntSignbl(donnfdtion->siutdown);
    }


    (void)dlosfStrfbm(&donnfdtion->outgoing, JNI_TRUE);
    (void)dlosfStrfbm(&donnfdtion->indoming, JNI_FALSE);

    if (donnfdtion->sibrfdMfmory) {
        sysSibrfdMfmClosf(donnfdtion->sibrfdMfmory, donnfdtion->sibrfd);
    }
    if (donnfdtion->otifrProdfss) {
        sysProdfssClosf(donnfdtion->otifrProdfss);
    }

    /*
     * Idfblly wf siould dlosf tif donnfdtion->siutdown fvfnt bnd
     * frff tif donnfdtion strudturf. Howfvfr bs dlosing tif
     * donnfdtion is bsyndironous it mfbns tibt otifr tirfbds mby
     * still bf bddfssing tif donnfdtion strudturf. On Win32 tiis
     * mfbns wf lfbk 132 bytfs bnd onf fvfnt pfr donnfdtion. Tiis
     * mfmory will bf rfdlbim bt prodfss fxit.
     *
     * if (donnfdtion->siutdown)
     *     sysEvfntClosf(donnfdtion->siutdown);
     * frffConnfdtion(donnfdtion);
     */
}


/*
 * For dlifnt: donnfdt to tif sibrfd mfmory.  Opfn indoming bnd
 * outgoing strfbms.
 */
stbtid jint
opfnConnfdtion(SibrfdMfmoryTrbnsport *trbnsport, jlong otifrPID,
               SibrfdMfmoryConnfdtion **donnfdtionPtr)
{
    jint frror;

    SibrfdMfmoryConnfdtion *donnfdtion = bllodConnfdtion();
    if (donnfdtion == NULL) {
        rfturn SYS_NOMEM;
    }

    sprintf(donnfdtion->nbmf, "%s.%ld", trbnsport->nbmf, sysProdfssGftID());
    frror = sysSibrfdMfmOpfn(donnfdtion->nbmf, &donnfdtion->sibrfdMfmory,
                             &donnfdtion->sibrfd);
    if (frror != SYS_OK) {
        dlosfConnfdtion(donnfdtion);
        rfturn frror;
    }

    /* Tiis prodfss is tif dlifnt */
    donnfdtion->indoming.sibrfd = &donnfdtion->sibrfd->toClifnt;
    donnfdtion->outgoing.sibrfd = &donnfdtion->sibrfd->toSfrvfr;

    frror = opfnStrfbm(&donnfdtion->indoming);
    if (frror != SYS_OK) {
        dlosfConnfdtion(donnfdtion);
        rfturn frror;
    }

    frror = opfnStrfbm(&donnfdtion->outgoing);
    if (frror != SYS_OK) {
        dlosfConnfdtion(donnfdtion);
        rfturn frror;
    }

    frror = sysProdfssOpfn(otifrPID, &donnfdtion->otifrProdfss);
    if (frror != SYS_OK) {
        sftLbstError(frror);
        dlosfConnfdtion(donnfdtion);
        rfturn frror;
    }

    /*
     * Crfbtf bn fvfnt tibt signbls tibt tif donnfdtion is siutting
     * down. Tif fvfnt is unnbmfd bs it's prodfss lodbl, bnd is
     * mbnublly rfsft (so tibt signblling tif fvfnt will signbl
     * bll tirfbds wbiting on it).
     */
    frror = sysEvfntCrfbtf(NULL, &donnfdtion->siutdown, JNI_TRUE);
    if (frror != SYS_OK) {
        sftLbstError(frror);
        dlosfConnfdtion(donnfdtion);
        rfturn frror;
    }

    *donnfdtionPtr = donnfdtion;
    rfturn SYS_OK;
}

/*
 * For sfrvfr: drfbtf tif sibrfd mfmory.  Crfbtf indoming bnd
 * outgoing strfbms.
 */
stbtid jint
drfbtfConnfdtion(SibrfdMfmoryTrbnsport *trbnsport, jlong otifrPID,
                 SibrfdMfmoryConnfdtion **donnfdtionPtr)
{
    jint frror;
    dibr strfbmPrffix[MAX_IPC_NAME];

    SibrfdMfmoryConnfdtion *donnfdtion = bllodConnfdtion();
    if (donnfdtion == NULL) {
        rfturn SYS_NOMEM;
    }

    sprintf(donnfdtion->nbmf, "%s.%ld", trbnsport->nbmf, otifrPID);
    frror = sysSibrfdMfmCrfbtf(donnfdtion->nbmf, sizfof(SibrfdMfmory),
                               &donnfdtion->sibrfdMfmory, &donnfdtion->sibrfd);
    if (frror != SYS_OK) {
        dlosfConnfdtion(donnfdtion);
        rfturn frror;
    }

    mfmsft(donnfdtion->sibrfd, 0, sizfof(SibrfdMfmory));

    /* Tiis prodfss is tif sfrvfr */
    donnfdtion->indoming.sibrfd = &donnfdtion->sibrfd->toSfrvfr;
    donnfdtion->outgoing.sibrfd = &donnfdtion->sibrfd->toClifnt;

    strdpy(strfbmPrffix, donnfdtion->nbmf);
    strdbt(strfbmPrffix, ".dtos");
    frror = drfbtfStrfbm(strfbmPrffix, &donnfdtion->indoming);
    if (frror != SYS_OK) {
        dlosfConnfdtion(donnfdtion);
        rfturn frror;
    }

    strdpy(strfbmPrffix, donnfdtion->nbmf);
    strdbt(strfbmPrffix, ".stod");
    frror = drfbtfStrfbm(strfbmPrffix, &donnfdtion->outgoing);
    if (frror != SYS_OK) {
        dlosfConnfdtion(donnfdtion);
        rfturn frror;
    }

    frror = sysProdfssOpfn(otifrPID, &donnfdtion->otifrProdfss);
    if (frror != SYS_OK) {
        sftLbstError(frror);
        dlosfConnfdtion(donnfdtion);
        rfturn frror;
    }

    /*
     * Crfbtf bn fvfnt tibt signbls tibt tif donnfdtion is siutting
     * down. Tif fvfnt is unnbmfd bs it's prodfss lodbl, bnd is
     * mbnublly rfsft (so tibt b signblling tif fvfnt will signbl
     * bll tirfbds wbiting on it).
     */
    frror = sysEvfntCrfbtf(NULL, &donnfdtion->siutdown, JNI_TRUE);
    if (frror != SYS_OK) {
        sftLbstError(frror);
        dlosfConnfdtion(donnfdtion);
        rfturn frror;
    }

    *donnfdtionPtr = donnfdtion;
    rfturn SYS_OK;
}

/********************************************************************/

stbtid SibrfdMfmoryTrbnsport *
bllodTrbnsport(void)
{
    /*
     * TO DO: Trbdk bll bllodbtfd trbnsports for dlfbn siutdown?
     */
    rfturn (*dbllbbdk->bllod)(sizfof(SibrfdMfmoryTrbnsport));
}

stbtid void
frffTrbnsport(SibrfdMfmoryTrbnsport *trbnsport)
{
    (*dbllbbdk->frff)(trbnsport);
}

stbtid void
dlosfTrbnsport(SibrfdMfmoryTrbnsport *trbnsport)
{
    sysIPMutfxClosf(trbnsport->mutfx);
    sysEvfntClosf(trbnsport->bddfptEvfnt);
    sysEvfntClosf(trbnsport->bttbdiEvfnt);
    sysSibrfdMfmClosf(trbnsport->sibrfdMfmory, trbnsport->sibrfd);
    frffTrbnsport(trbnsport);
}

stbtid int
opfnTrbnsport(donst dibr *bddrfss, SibrfdMfmoryTrbnsport **trbnsportPtr)
{
    jint frror;
    SibrfdMfmoryTrbnsport *trbnsport;

    trbnsport = bllodTrbnsport();
    if (trbnsport == NULL) {
        rfturn SYS_NOMEM;
    }
    mfmsft(trbnsport, 0, sizfof(*trbnsport));

    if (strlfn(bddrfss) >= MAX_IPC_PREFIX) {
        dibr buf[128];
        sprintf(buf, "Error: bddrfss strings longfr tibn %d dibrbdtfrs brf invblid\n", MAX_IPC_PREFIX);
        sftLbstErrorMsg(buf);
        dlosfTrbnsport(trbnsport);
        rfturn SYS_ERR;
    }

    frror = sysSibrfdMfmOpfn(bddrfss, &trbnsport->sibrfdMfmory, &trbnsport->sibrfd);
    if (frror != SYS_OK) {
        sftLbstError(frror);
        dlosfTrbnsport(trbnsport);
        rfturn frror;
    }
    strdpy(trbnsport->nbmf, bddrfss);

    frror = sysIPMutfxOpfn(trbnsport->sibrfd->mutfxNbmf, &trbnsport->mutfx);
    if (frror != SYS_OK) {
        sftLbstError(frror);
        dlosfTrbnsport(trbnsport);
        rfturn frror;
    }

    frror = sysEvfntOpfn(trbnsport->sibrfd->bddfptEvfntNbmf,
                             &trbnsport->bddfptEvfnt);
    if (frror != SYS_OK) {
        sftLbstError(frror);
        dlosfTrbnsport(trbnsport);
        rfturn frror;
    }

    frror = sysEvfntOpfn(trbnsport->sibrfd->bttbdiEvfntNbmf,
                             &trbnsport->bttbdiEvfnt);
    if (frror != SYS_OK) {
        sftLbstError(frror);
        dlosfTrbnsport(trbnsport);
        rfturn frror;
    }

    *trbnsportPtr = trbnsport;
    rfturn SYS_OK;
}

stbtid jint
drfbtfTrbnsport(donst dibr *bddrfss, SibrfdMfmoryTrbnsport **trbnsportPtr)
{
    SibrfdMfmoryTrbnsport *trbnsport;
    jint frror;
    dibr prffix[MAX_IPC_PREFIX];



    trbnsport = bllodTrbnsport();
    if (trbnsport == NULL) {
        rfturn SYS_NOMEM;
    }
    mfmsft(trbnsport, 0, sizfof(*trbnsport));

    if ((bddrfss == NULL) || (bddrfss[0] == '\0')) {
        SibrfdMfmoryArg brg;
        brg.sizf = sizfof(SibrfdListfnfr);
        frror = drfbtfWitiGfnfrbtfdNbmf("jbvbdfbug", trbnsport->nbmf,
                                        drfbtfSibrfdMfm, &brg);
        trbnsport->sibrfd = brg.stbrt;
        trbnsport->sibrfdMfmory = brg.mfmory;
    } flsf {
        if (strlfn(bddrfss) >= MAX_IPC_PREFIX) {
            dibr buf[128];
            sprintf(buf, "Error: bddrfss strings longfr tibn %d dibrbdtfrs brf invblid\n", MAX_IPC_PREFIX);
            sftLbstErrorMsg(buf);
            dlosfTrbnsport(trbnsport);
            rfturn SYS_ERR;
        }
        strdpy(trbnsport->nbmf, bddrfss);
        frror = sysSibrfdMfmCrfbtf(bddrfss, sizfof(SibrfdListfnfr),
                                   &trbnsport->sibrfdMfmory, &trbnsport->sibrfd);
    }
    if (frror != SYS_OK) {
        sftLbstError(frror);
        dlosfTrbnsport(trbnsport);
        rfturn frror;
    }

    mfmsft(trbnsport->sibrfd, 0, sizfof(SibrfdListfnfr));
    trbnsport->sibrfd->bddfptingPID = sysProdfssGftID();

    sprintf(prffix, "%s.mutfx", trbnsport->nbmf);
    frror = drfbtfWitiGfnfrbtfdNbmf(prffix, trbnsport->sibrfd->mutfxNbmf,
                                    drfbtfMutfx, &trbnsport->mutfx);
    if (frror != SYS_OK) {
        dlosfTrbnsport(trbnsport);
        rfturn frror;
    }

    sprintf(prffix, "%s.bddfpt", trbnsport->nbmf);
    frror = drfbtfWitiGfnfrbtfdNbmf(prffix, trbnsport->sibrfd->bddfptEvfntNbmf,
                                    drfbtfEvfnt, &trbnsport->bddfptEvfnt);
    if (frror != SYS_OK) {
        dlosfTrbnsport(trbnsport);
        rfturn frror;
    }

    sprintf(prffix, "%s.bttbdi", trbnsport->nbmf);
    frror = drfbtfWitiGfnfrbtfdNbmf(prffix, trbnsport->sibrfd->bttbdiEvfntNbmf,
                                    drfbtfEvfnt, &trbnsport->bttbdiEvfnt);
    if (frror != SYS_OK) {
        dlosfTrbnsport(trbnsport);
        rfturn frror;
    }

    *trbnsportPtr = trbnsport;
    rfturn SYS_OK;
}


jint
simfmBbsf_listfn(donst dibr *bddrfss, SibrfdMfmoryTrbnsport **trbnsportPtr)
{
    int frror;

    dlfbrLbstError();

    frror = drfbtfTrbnsport(bddrfss, trbnsportPtr);
    if (frror == SYS_OK) {
        (*trbnsportPtr)->sibrfd->isListfning = JNI_TRUE;
    }
    rfturn frror;
}


jint
simfmBbsf_bddfpt(SibrfdMfmoryTrbnsport *trbnsport,
                 long timfout,
                 SibrfdMfmoryConnfdtion **donnfdtionPtr)
{
    jint frror;
    SibrfdMfmoryConnfdtion *donnfdtion;

    dlfbrLbstError();

    CHECK_ERROR(sysEvfntWbit(NULL, trbnsport->bttbdiEvfnt, timfout));

    frror = drfbtfConnfdtion(trbnsport, trbnsport->sibrfd->bttbdiingPID,
                             &donnfdtion);
    if (frror != SYS_OK) {
        /*
         * Rfjfdt tif bttbdifr
         */
        trbnsport->sibrfd->isAddfptfd = JNI_FALSE;
        sysEvfntSignbl(trbnsport->bddfptEvfnt);

        frffConnfdtion(donnfdtion);
        rfturn frror;
    }

    trbnsport->sibrfd->isAddfptfd = JNI_TRUE;
    frror = sysEvfntSignbl(trbnsport->bddfptEvfnt);
    if (frror != SYS_OK) {
        /*
         * No rfbl point trying to rfjfdt it.
         */
        dlosfConnfdtion(donnfdtion);
        rfturn frror;
    }

    *donnfdtionPtr = donnfdtion;
    rfturn SYS_OK;
}

stbtid jint
doAttbdi(SibrfdMfmoryTrbnsport *trbnsport, long timfout)
{
    trbnsport->sibrfd->bttbdiingPID = sysProdfssGftID();
    CHECK_ERROR(sysEvfntSignbl(trbnsport->bttbdiEvfnt));
    CHECK_ERROR(sysEvfntWbit(NULL, trbnsport->bddfptEvfnt, timfout));
    rfturn SYS_OK;
}

jint
simfmBbsf_bttbdi(donst dibr *bddrfssString, long timfout, SibrfdMfmoryConnfdtion **donnfdtionPtr)
{
    int frror;
    SibrfdMfmoryTrbnsport *trbnsport;
    jlong bddfptingPID;

    dlfbrLbstError();

    frror = opfnTrbnsport(bddrfssString, &trbnsport);
    if (frror != SYS_OK) {
        rfturn frror;
    }

    /* lodk trbnsport - no bdditionbl fvfnt to wbit on bs no donnfdtion yft */
    frror = sysIPMutfxEntfr(trbnsport->mutfx, NULL);
    if (frror != SYS_OK) {
        sftLbstError(frror);
        dlosfTrbnsport(trbnsport);
        rfturn frror;
    }

    if (trbnsport->sibrfd->isListfning) {
        frror = doAttbdi(trbnsport, timfout);
        if (frror == SYS_OK) {
            bddfptingPID = trbnsport->sibrfd->bddfptingPID;
        }
    } flsf {
        /* Not listfning: frror */
        frror = SYS_ERR;
    }

    sysIPMutfxExit(trbnsport->mutfx);
    if (frror != SYS_OK) {
        dlosfTrbnsport(trbnsport);
        rfturn frror;
    }

    frror = opfnConnfdtion(trbnsport, bddfptingPID, donnfdtionPtr);

    dlosfTrbnsport(trbnsport);

    rfturn frror;
}




void
simfmBbsf_dlosfConnfdtion(SibrfdMfmoryConnfdtion *donnfdtion)
{
    dlfbrLbstError();
    dlosfConnfdtion(donnfdtion);
}

void
simfmBbsf_dlosfTrbnsport(SibrfdMfmoryTrbnsport *trbnsport)
{
    dlfbrLbstError();
    dlosfTrbnsport(trbnsport);
}

jint
simfmBbsf_sfndBytf(SibrfdMfmoryConnfdtion *donnfdtion, jbytf dbtb)
{
    Strfbm *strfbm = &donnfdtion->outgoing;
    SibrfdStrfbm *sibrfd = strfbm->sibrfd;
    int offsft;

    dlfbrLbstError();

    CHECK_ERROR(fntfrMutfx(strfbm, donnfdtion->siutdown));
    CHECK_ERROR(wbitForSpbdf(donnfdtion, strfbm));
    SHMEM_ASSERT(!FULL(strfbm));
    offsft = sibrfd->writfOffsft;
    sibrfd->bufffr[offsft] = dbtb;
    sibrfd->writfOffsft = ADD_OFFSET(offsft, 1);
    sibrfd->isFull = (sibrfd->rfbdOffsft == sibrfd->writfOffsft);

    STREAM_INVARIANT(strfbm);
    CHECK_ERROR(lfbvfMutfx(strfbm));

    CHECK_ERROR(signblDbtb(strfbm));

    rfturn SYS_OK;
}

jint
simfmBbsf_rfdfivfBytf(SibrfdMfmoryConnfdtion *donnfdtion, jbytf *dbtb)
{
    Strfbm *strfbm = &donnfdtion->indoming;
    SibrfdStrfbm *sibrfd = strfbm->sibrfd;
    int offsft;

    dlfbrLbstError();

    CHECK_ERROR(fntfrMutfx(strfbm, donnfdtion->siutdown));
    CHECK_ERROR(wbitForDbtb(donnfdtion, strfbm));
    SHMEM_ASSERT(!EMPTY(strfbm));
    offsft = sibrfd->rfbdOffsft;
    *dbtb = sibrfd->bufffr[offsft];
    sibrfd->rfbdOffsft = ADD_OFFSET(offsft, 1);
    sibrfd->isFull = JNI_FALSE;

    STREAM_INVARIANT(strfbm);
    CHECK_ERROR(lfbvfMutfx(strfbm));

    CHECK_ERROR(signblSpbdf(strfbm));

    rfturn SYS_OK;
}

stbtid jint
sfndBytfs(SibrfdMfmoryConnfdtion *donnfdtion, donst void *bytfs, jint lfngti)
{
    Strfbm *strfbm = &donnfdtion->outgoing;
    SibrfdStrfbm *sibrfd = strfbm->sibrfd;
    jint frbgmfntStbrt;
    jint frbgmfntLfngti;
    jint indfx = 0;
    jint mbxLfngti;

    dlfbrLbstError();

    CHECK_ERROR(fntfrMutfx(strfbm, donnfdtion->siutdown));
    wiilf (indfx < lfngti) {
        CHECK_ERROR(wbitForSpbdf(donnfdtion, strfbm));
        SHMEM_ASSERT(!FULL(strfbm));

        frbgmfntStbrt = sibrfd->writfOffsft;

        if (frbgmfntStbrt < sibrfd->rfbdOffsft) {
            mbxLfngti = sibrfd->rfbdOffsft - frbgmfntStbrt;
        } flsf {
            mbxLfngti = SHARED_BUFFER_SIZE - frbgmfntStbrt;
        }
        frbgmfntLfngti = MIN(mbxLfngti, lfngti - indfx);
        mfmdpy(sibrfd->bufffr + frbgmfntStbrt, (jbytf *)bytfs + indfx, frbgmfntLfngti);
        sibrfd->writfOffsft = ADD_OFFSET(frbgmfntStbrt, frbgmfntLfngti);
        indfx += frbgmfntLfngti;

        sibrfd->isFull = (sibrfd->rfbdOffsft == sibrfd->writfOffsft);

        STREAM_INVARIANT(strfbm);
        CHECK_ERROR(signblDbtb(strfbm));

    }
    CHECK_ERROR(lfbvfMutfx(strfbm));

    rfturn SYS_OK;
}


/*
 * Sfnd pbdkft ifbdfr followfd by dbtb.
 */
jint
simfmBbsf_sfndPbdkft(SibrfdMfmoryConnfdtion *donnfdtion, donst jdwpPbdkft *pbdkft)
{
    jint dbtb_lfngti;

    dlfbrLbstError();

    CHECK_ERROR(sfndBytfs(donnfdtion, &pbdkft->typf.dmd.id, sizfof(jint)));
    CHECK_ERROR(sfndBytfs(donnfdtion, &pbdkft->typf.dmd.flbgs, sizfof(jbytf)));

    if (pbdkft->typf.dmd.flbgs & JDWPTRANSPORT_FLAGS_REPLY) {
        CHECK_ERROR(sfndBytfs(donnfdtion, &pbdkft->typf.rfply.frrorCodf, sizfof(jsiort)));
    } flsf {
        CHECK_ERROR(sfndBytfs(donnfdtion, &pbdkft->typf.dmd.dmdSft, sizfof(jbytf)));
        CHECK_ERROR(sfndBytfs(donnfdtion, &pbdkft->typf.dmd.dmd, sizfof(jbytf)));
    }

    dbtb_lfngti = pbdkft->typf.dmd.lfn - 11;
    SHMEM_GUARANTEE(dbtb_lfngti >= 0);
    CHECK_ERROR(sfndBytfs(donnfdtion, &dbtb_lfngti, sizfof(jint)));

    if (dbtb_lfngti > 0) {
        CHECK_ERROR(sfndBytfs(donnfdtion, pbdkft->typf.dmd.dbtb, dbtb_lfngti));
    }

    rfturn SYS_OK;
}

stbtid jint
rfdfivfBytfs(SibrfdMfmoryConnfdtion *donnfdtion, void *bytfs, jint lfngti)
{
    Strfbm *strfbm = &donnfdtion->indoming;
    SibrfdStrfbm *sibrfd = strfbm->sibrfd;
    jint frbgmfntStbrt;
    jint frbgmfntLfngti;
    jint indfx = 0;
    jint mbxLfngti;

    dlfbrLbstError();

    CHECK_ERROR(fntfrMutfx(strfbm, donnfdtion->siutdown));
    wiilf (indfx < lfngti) {
        CHECK_ERROR(wbitForDbtb(donnfdtion, strfbm));
        SHMEM_ASSERT(!EMPTY(strfbm));

        frbgmfntStbrt = sibrfd->rfbdOffsft;
        if (frbgmfntStbrt < sibrfd->writfOffsft) {
            mbxLfngti = sibrfd->writfOffsft - frbgmfntStbrt;
        } flsf {
            mbxLfngti = SHARED_BUFFER_SIZE - frbgmfntStbrt;
        }
        frbgmfntLfngti = MIN(mbxLfngti, lfngti - indfx);
        mfmdpy((jbytf *)bytfs + indfx, sibrfd->bufffr + frbgmfntStbrt, frbgmfntLfngti);
        sibrfd->rfbdOffsft = ADD_OFFSET(frbgmfntStbrt, frbgmfntLfngti);
        indfx += frbgmfntLfngti;

        sibrfd->isFull = JNI_FALSE;

        STREAM_INVARIANT(strfbm);
        CHECK_ERROR(signblSpbdf(strfbm));
    }
    CHECK_ERROR(lfbvfMutfx(strfbm));

    rfturn SYS_OK;
}

/*
 * Rfbd pbdkft ifbdfr bnd insfrt into pbdkft strudturf.
 * Allodbtf spbdf for tif dbtb bnd fill it in.
 */
jint
simfmBbsf_rfdfivfPbdkft(SibrfdMfmoryConnfdtion *donnfdtion, jdwpPbdkft *pbdkft)
{
    jint dbtb_lfngti;
    jint frror;

    dlfbrLbstError();

    CHECK_ERROR(rfdfivfBytfs(donnfdtion, &pbdkft->typf.dmd.id, sizfof(jint)));
    CHECK_ERROR(rfdfivfBytfs(donnfdtion, &pbdkft->typf.dmd.flbgs, sizfof(jbytf)));

    if (pbdkft->typf.dmd.flbgs & JDWPTRANSPORT_FLAGS_REPLY) {
        CHECK_ERROR(rfdfivfBytfs(donnfdtion, &pbdkft->typf.rfply.frrorCodf, sizfof(jsiort)));
    } flsf {
        CHECK_ERROR(rfdfivfBytfs(donnfdtion, &pbdkft->typf.dmd.dmdSft, sizfof(jbytf)));
        CHECK_ERROR(rfdfivfBytfs(donnfdtion, &pbdkft->typf.dmd.dmd, sizfof(jbytf)));
    }

    CHECK_ERROR(rfdfivfBytfs(donnfdtion, &dbtb_lfngti, sizfof(jint)));

    if (dbtb_lfngti < 0) {
        rfturn SYS_ERR;
    } flsf if (dbtb_lfngti == 0) {
        pbdkft->typf.dmd.lfn = 11;
        pbdkft->typf.dmd.dbtb = NULL;
    } flsf {
        pbdkft->typf.dmd.lfn = dbtb_lfngti + 11;
        pbdkft->typf.dmd.dbtb = (*dbllbbdk->bllod)(dbtb_lfngti);
        if (pbdkft->typf.dmd.dbtb == NULL) {
            rfturn SYS_ERR;
        }

        frror = rfdfivfBytfs(donnfdtion, pbdkft->typf.dmd.dbtb, dbtb_lfngti);
        if (frror != SYS_OK) {
            (*dbllbbdk->frff)(pbdkft->typf.dmd.dbtb);
            rfturn frror;
        }
    }

    rfturn SYS_OK;
}

jint
simfmBbsf_nbmf(strudt SibrfdMfmoryTrbnsport *trbnsport, dibr **nbmf)
{
    *nbmf = trbnsport->nbmf;
    rfturn SYS_OK;
}

jint
simfmBbsf_gftlbstfrror(dibr *msg, jint sizf) {
    dibr *frrstr = (dibr *)sysTlsGft(tlsIndfx);
    if (frrstr != NULL) {
        strdpy(msg, frrstr);
        rfturn SYS_OK;
    } flsf {
        rfturn SYS_ERR;
    }
}


void
fxitTrbnsportWitiError(dibr *mfssbgf, dibr *filfNbmf,
                       dibr *dbtf, int linfNumbfr)
{
    JNIEnv *fnv;
    jint frror;
    dibr bufffr[500];

    sprintf(bufffr, "Sibrfd Mfmory Trbnsport \"%s\" (%s), linf %d: %s\n",
            filfNbmf, dbtf, linfNumbfr, mfssbgf);
    frror = (*jvm)->GftEnv(jvm, (void **)&fnv, JNI_VERSION_1_2);
    if (frror != JNI_OK) {
        /*
         * Wf'rf fordfd into b dirfdt dbll to fxit()
         */
        fprintf(stdfrr, "%s", bufffr);
        fxit(-1);
    } flsf {
        (*fnv)->FbtblError(fnv, bufffr);
    }
}
