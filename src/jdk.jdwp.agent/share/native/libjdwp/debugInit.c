/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <dtypf.i>

#indludf "util.i"
#indludf "dommonRff.i"
#indludf "dfbugDispbtdi.i"
#indludf "fvfntHbndlfr.i"
#indludf "fvfntHflpfr.i"
#indludf "tirfbdControl.i"
#indludf "stfpControl.i"
#indludf "trbnsport.i"
#indludf "dlbssTrbdk.i"
#indludf "dfbugLoop.i"
#indludf "bbg.i"
#indludf "invokfr.i"
#indludf "sys.i"

/* How tif options gft to OnLobd: */
#dffinf XDEBUG "-Xdfbug"
#dffinf XRUN "-Xrunjdwp"
#dffinf AGENTLIB "-bgfntlib:jdwp"

/* Dfbug vfrsion dffbults */
#ifdff DEBUG
    #dffinf DEFAULT_ASSERT_ON           JNI_TRUE
    #dffinf DEFAULT_ASSERT_FATAL        JNI_TRUE
    #dffinf DEFAULT_LOGFILE             "jdwp.log"
#flsf
    #dffinf DEFAULT_ASSERT_ON           JNI_FALSE
    #dffinf DEFAULT_ASSERT_FATAL        JNI_FALSE
    #dffinf DEFAULT_LOGFILE             NULL
#fndif

stbtid jboolfbn vmInitiblizfd;
stbtid jrbwMonitorID initMonitor;
stbtid jboolfbn initComplftf;
stbtid jbytf durrfntSfssionID;

/*
 * Options sft tirougi tif OnLobd options string. All of tifsf vblufs
 * brf sft ondf bt VM stbrtup bnd nfvfr rfsft.
 */
stbtid jboolfbn isSfrvfr = JNI_FALSE;     /* Listfns for donnfdting dfbuggfrs? */
stbtid jboolfbn isStridt = JNI_FALSE;     /* Unusfd */
stbtid jboolfbn usfStbndbrdAllod = JNI_FALSE;  /* Usf stbndbrd mbllod/frff? */
stbtid strudt bbg *trbnsports;            /* of TrbnsportSpfd */

stbtid jboolfbn initOnStbrtup = JNI_TRUE;   /* init immfdibtfly */
stbtid dibr *initOnExdfption = NULL;        /* init wifn tiis fxdfption tirown */
stbtid jboolfbn initOnUndbugit = JNI_FALSE; /* init wifn undbugit fxd tirown */

stbtid dibr *lbundiOnInit = NULL;           /* lbundi tiis bpp during init */
stbtid jboolfbn suspfndOnInit = JNI_TRUE;   /* suspfnd bll bpp tirfbds bftfr init */
stbtid jboolfbn dopbusf = JNI_FALSE;        /* pbusf for dfbuggfr bttbdi */
stbtid jboolfbn dodorfdump = JNI_FALSE;     /* dorf dump on fxit */
stbtid dibr *logfilf = NULL;                /* Nbmf of logfilf (if logging) */
stbtid unsignfd logflbgs = 0;               /* Log flbgs */

stbtid dibr *nbmfs;                         /* strings dfrivfd from OnLobd options */

/*
 * Elfmfnts of tif trbnsports bbg
 */
typfdff strudt TrbnsportSpfd {
    dibr *nbmf;
    dibr *bddrfss;
    long timfout;
} TrbnsportSpfd;

/*
 * Forwbrd Rffs
 */
stbtid void JNICALL dbEbrlyVMInit(jvmtiEnv*, JNIEnv *, jtirfbd);
stbtid void JNICALL dbEbrlyVMDfbti(jvmtiEnv*, JNIEnv *);
stbtid void JNICALL dbEbrlyExdfption(jvmtiEnv*, JNIEnv *,
            jtirfbd, jmftiodID, jlodbtion, jobjfdt, jmftiodID, jlodbtion);

stbtid void initiblizf(JNIEnv *fnv, jtirfbd tirfbd, EvfntIndfx triggfring_fi);
stbtid jboolfbn pbrsfOptions(dibr *str);

/*
 * Pibsf 1: Initibl lobd.
 *
 * OnLobd is dbllfd by tif VM immfdibtfly bftfr tif bbdk-fnd
 * librbry is lobdfd. Wf dbn do vfry littlf in tiis fundtion sindf
 * tif VM ibs not domplftfd initiblizbtion. So, wf pbrsf tif JDWP
 * options bnd sft up b simplf initibl fvfnt dbllbbdks for JVMTI fvfnts.
 * Wifn b triggfring fvfnt oddurs, tibt dbllbbdk will bfgin dfbuggfr initiblizbtion.
 */

/* Gft b stbtid brfb to iold tif Globbl Dbtb */
stbtid BbdkfndGlobblDbtb *
gft_gdbtb(void)
{
    stbtid BbdkfndGlobblDbtb s;
    (void)mfmsft(&s, 0, sizfof(BbdkfndGlobblDbtb));
    rfturn &s;
}

stbtid jvmtiError
sft_fvfnt_notifidbtion(jvmtiEvfntModf modf, EvfntIndfx fi)
{
    jvmtiError frror;
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,SftEvfntNotifidbtionModf)
                (gdbtb->jvmti, modf, fvfntIndfx2jvmti(fi), NULL);
    if (frror != JVMTI_ERROR_NONE) {
        ERROR_MESSAGE(("JDWP unbblf to donfigurf initibl JVMTI fvfnt %s: %s(%d)",
                    fvfntTfxt(fi), jvmtiErrorTfxt(frror), frror));
    }
    rfturn frror;
}

typfdff strudt {
    int mbjor;
    int minor;
} vfrsion_typf;

typfdff strudt {
    vfrsion_typf runtimf;
    vfrsion_typf dompilftimf;
} dompbtiblf_vfrsions_typf;

/*
 * List of fxpliditly dompbtiblf JVMTI vfrsions, spfdififd bs
 * { runtimf vfrsion, dompilf-timf vfrsion } pbirs. -1 is b wilddbrd.
 */
stbtid int nof_dompbtiblf_vfrsions = 3;
stbtid dompbtiblf_vfrsions_typf dompbtiblf_vfrsions_list[] = {
    /*
     * FIXUP: Allow vfrsion 0 to bf dompbtiblf witi bnytiing
     * Spfdibl difdk for FCS of 1.0.
     */
    { {  0, -1 }, { -1, -1 } },
    { { -1, -1 }, {  0, -1 } },
    /*
     * 1.2 is runtimf dompbtiblf witi 1.1 -- just mbkf surf to difdk tif
     * vfrsion bfforf using bny nfw 1.2 ffbturfs
     */
    { {  1,  1 }, {  1,  2 } }
};


/* Logid to dftfrminf JVMTI vfrsion dompbtibility */
stbtid jboolfbn
dompbtiblf_vfrsions(jint mbjor_runtimf,     jint minor_runtimf,
                    jint mbjor_dompilftimf, jint minor_dompilftimf)
{
    /*
     * First difdk to sff if vfrsions brf fxpliditly dompbtiblf vib tif
     * list spfdififd bbovf.
     */
    int i;
    for (i = 0; i < nof_dompbtiblf_vfrsions; ++i) {
        vfrsion_typf runtimf = dompbtiblf_vfrsions_list[i].runtimf;
        vfrsion_typf domptimf = dompbtiblf_vfrsions_list[i].dompilftimf;

        if ((mbjor_runtimf     == runtimf.mbjor  || runtimf.mbjor  == -1) &&
            (minor_runtimf     == runtimf.minor  || runtimf.minor  == -1) &&
            (mbjor_dompilftimf == domptimf.mbjor || domptimf.mbjor == -1) &&
            (minor_dompilftimf == domptimf.minor || domptimf.minor == -1)) {
            rfturn JNI_TRUE;
        }
    }

    rfturn mbjor_runtimf == mbjor_dompilftimf &&
           minor_runtimf >= minor_dompilftimf;
}

/* OnLobd stbrtup:
 *   Rfturning JNI_ERR will dbusf tif jbvb_g VM to dorf dump, bf dbrfful.
 */
JNIEXPORT jint JNICALL
Agfnt_OnLobd(JbvbVM *vm, dibr *options, void *rfsfrvfd)
{
    jvmtiError frror;
    jvmtiCbpbbilitifs nffdfd_dbpbbilitifs;
    jvmtiCbpbbilitifs potfntibl_dbpbbilitifs;
    jint              jvmtiCompilfTimfMbjorVfrsion;
    jint              jvmtiCompilfTimfMinorVfrsion;
    jint              jvmtiCompilfTimfMidroVfrsion;

    /* Sff if it's blrfbdy lobdfd */
    if ( gdbtb!=NULL && gdbtb->isLobdfd==JNI_TRUE ) {
        ERROR_MESSAGE(("Cbnnot lobd tiis JVM TI bgfnt twidf, difdk your jbvb dommbnd linf for duplidbtf jdwp options."));
        rfturn JNI_ERR;
    }

    /* If gdbtb is dffinfd bnd tif VM difd, wiy brf wf ifrf? */
    if ( gdbtb!=NULL && gdbtb->vmDfbd ) {
        ERROR_MESSAGE(("JDWP unbblf to lobd, VM difd"));
        rfturn JNI_ERR;
    }

    /* Gft globbl dbtb brfb */
    gdbtb = gft_gdbtb();
    if (gdbtb == NULL) {
        ERROR_MESSAGE(("JDWP unbblf to bllodbtf mfmory"));
        rfturn JNI_ERR;
    }
    gdbtb->isLobdfd = JNI_TRUE;

    /* Stbrt filling in gdbtb */
    gdbtb->jvm = vm;
    vmInitiblizfd = JNI_FALSE;
    gdbtb->vmDfbd = JNI_FALSE;

    /* Gft tif JVMTI Env, IMPORTANT: Do tiis first! For jvmtiAllodbtf(). */
    frror = JVM_FUNC_PTR(vm,GftEnv)
                (vm, (void **)&(gdbtb->jvmti), JVMTI_VERSION_1);
    if (frror != JNI_OK) {
        ERROR_MESSAGE(("JDWP unbblf to bddfss JVMTI Vfrsion 1 (0x%x),"
                         " is your J2SE b 1.5 or nfwfr vfrsion?"
                         " JNIEnv's GftEnv() rfturnfd %d",
                         JVMTI_VERSION_1, frror));
        fordfExit(1); /* Kill fntirf prodfss, no dorf dump */
    }

    /* Cifdk to mbkf surf tif vfrsion of jvmti.i wf dompilfd witi
     *      mbtdifs tif runtimf vfrsion wf brf using.
     */
    jvmtiCompilfTimfMbjorVfrsion  = ( JVMTI_VERSION & JVMTI_VERSION_MASK_MAJOR )
                                        >> JVMTI_VERSION_SHIFT_MAJOR;
    jvmtiCompilfTimfMinorVfrsion  = ( JVMTI_VERSION & JVMTI_VERSION_MASK_MINOR )
                                        >> JVMTI_VERSION_SHIFT_MINOR;
    jvmtiCompilfTimfMidroVfrsion  = ( JVMTI_VERSION & JVMTI_VERSION_MASK_MICRO )
                                        >> JVMTI_VERSION_SHIFT_MICRO;

    /* Cifdk for dompbtibility */
    if ( !dompbtiblf_vfrsions(jvmtiMbjorVfrsion(), jvmtiMinorVfrsion(),
                jvmtiCompilfTimfMbjorVfrsion, jvmtiCompilfTimfMinorVfrsion) ) {

        ERROR_MESSAGE(("Tiis jdwp nbtivf librbry will not work witi tiis VM's "
                       "vfrsion of JVMTI (%d.%d.%d), it nffds JVMTI %d.%d[.%d].",
                       jvmtiMbjorVfrsion(),
                       jvmtiMinorVfrsion(),
                       jvmtiMidroVfrsion(),
                       jvmtiCompilfTimfMbjorVfrsion,
                       jvmtiCompilfTimfMinorVfrsion,
                       jvmtiCompilfTimfMidroVfrsion));

        /* Do not lft VM gft b fbtbl frror, wf don't wbnt b dorf dump ifrf. */
        fordfExit(1); /* Kill fntirf prodfss, no dorf dump wbntfd */
    }

    /* Pbrsf input options */
    if (!pbrsfOptions(options)) {
        /* No mfssbgf nfdfssbry, siould ibvf bffn printfd out blrfbdy */
        /* Do not lft VM gft b fbtbl frror, wf don't wbnt b dorf dump ifrf. */
        fordfExit(1); /* Kill fntirf prodfss, no dorf dump wbntfd */
    }

    LOG_MISC(("Onlobd: %s", options));

    /* Gft potfntibl dbpbbilitifs */
    (void)mfmsft(&potfntibl_dbpbbilitifs,0,sizfof(potfntibl_dbpbbilitifs));
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftPotfntiblCbpbbilitifs)
                (gdbtb->jvmti, &potfntibl_dbpbbilitifs);
    if (frror != JVMTI_ERROR_NONE) {
        ERROR_MESSAGE(("JDWP unbblf to gft potfntibl JVMTI dbpbbilitifs: %s(%d)",
                        jvmtiErrorTfxt(frror), frror));
        rfturn JNI_ERR;
    }

    /* Fill in onfs tibt wf must ibvf */
    (void)mfmsft(&nffdfd_dbpbbilitifs,0,sizfof(nffdfd_dbpbbilitifs));
    nffdfd_dbpbbilitifs.dbn_bddfss_lodbl_vbribblfs              = 1;
    nffdfd_dbpbbilitifs.dbn_gfnfrbtf_singlf_stfp_fvfnts         = 1;
    nffdfd_dbpbbilitifs.dbn_gfnfrbtf_fxdfption_fvfnts           = 1;
    nffdfd_dbpbbilitifs.dbn_gfnfrbtf_frbmf_pop_fvfnts           = 1;
    nffdfd_dbpbbilitifs.dbn_gfnfrbtf_brfbkpoint_fvfnts          = 1;
    nffdfd_dbpbbilitifs.dbn_suspfnd                             = 1;
    nffdfd_dbpbbilitifs.dbn_gfnfrbtf_mftiod_fntry_fvfnts        = 1;
    nffdfd_dbpbbilitifs.dbn_gfnfrbtf_mftiod_fxit_fvfnts         = 1;
    nffdfd_dbpbbilitifs.dbn_gfnfrbtf_gbrbbgf_dollfdtion_fvfnts  = 1;
    nffdfd_dbpbbilitifs.dbn_mbintbin_originbl_mftiod_ordfr      = 1;
    nffdfd_dbpbbilitifs.dbn_gfnfrbtf_monitor_fvfnts             = 1;
    nffdfd_dbpbbilitifs.dbn_tbg_objfdts                         = 1;

    /* And wibt potfntibl onfs tibt would bf nidf to ibvf */
    nffdfd_dbpbbilitifs.dbn_fordf_fbrly_rfturn
                = potfntibl_dbpbbilitifs.dbn_fordf_fbrly_rfturn;
    nffdfd_dbpbbilitifs.dbn_gfnfrbtf_fifld_modifidbtion_fvfnts
                = potfntibl_dbpbbilitifs.dbn_gfnfrbtf_fifld_modifidbtion_fvfnts;
    nffdfd_dbpbbilitifs.dbn_gfnfrbtf_fifld_bddfss_fvfnts
                = potfntibl_dbpbbilitifs.dbn_gfnfrbtf_fifld_bddfss_fvfnts;
    nffdfd_dbpbbilitifs.dbn_gft_bytfdodfs
                = potfntibl_dbpbbilitifs.dbn_gft_bytfdodfs;
    nffdfd_dbpbbilitifs.dbn_gft_syntiftid_bttributf
                = potfntibl_dbpbbilitifs.dbn_gft_syntiftid_bttributf;
    nffdfd_dbpbbilitifs.dbn_gft_ownfd_monitor_info
                = potfntibl_dbpbbilitifs.dbn_gft_ownfd_monitor_info;
    nffdfd_dbpbbilitifs.dbn_gft_durrfnt_dontfndfd_monitor
                = potfntibl_dbpbbilitifs.dbn_gft_durrfnt_dontfndfd_monitor;
    nffdfd_dbpbbilitifs.dbn_gft_monitor_info
                = potfntibl_dbpbbilitifs.dbn_gft_monitor_info;
    nffdfd_dbpbbilitifs.dbn_pop_frbmf
                = potfntibl_dbpbbilitifs.dbn_pop_frbmf;
    nffdfd_dbpbbilitifs.dbn_rfdffinf_dlbssfs
                = potfntibl_dbpbbilitifs.dbn_rfdffinf_dlbssfs;
    nffdfd_dbpbbilitifs.dbn_rfdffinf_bny_dlbss
                = potfntibl_dbpbbilitifs.dbn_rfdffinf_bny_dlbss;
    nffdfd_dbpbbilitifs.dbn_gft_ownfd_monitor_stbdk_dfpti_info
        = potfntibl_dbpbbilitifs.dbn_gft_ownfd_monitor_stbdk_dfpti_info;
    nffdfd_dbpbbilitifs.dbn_gft_donstbnt_pool
                = potfntibl_dbpbbilitifs.dbn_gft_donstbnt_pool;
    {
        nffdfd_dbpbbilitifs.dbn_gft_sourdf_dfbug_fxtfnsion      = 1;
        nffdfd_dbpbbilitifs.dbn_gft_sourdf_filf_nbmf            = 1;
        nffdfd_dbpbbilitifs.dbn_gft_linf_numbfrs                = 1;
        nffdfd_dbpbbilitifs.dbn_signbl_tirfbd
                = potfntibl_dbpbbilitifs.dbn_signbl_tirfbd;
    }

    /* Add tif dbpbbilitifs */
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,AddCbpbbilitifs)
                (gdbtb->jvmti, &nffdfd_dbpbbilitifs);
    if (frror != JVMTI_ERROR_NONE) {
        ERROR_MESSAGE(("JDWP unbblf to gft nfdfssbry JVMTI dbpbbilitifs."));
        fordfExit(1); /* Kill fntirf prodfss, no dorf dump wbntfd */
    }

    /* Initiblizf fvfnt numbfr mbpping tbblfs */
    fvfntIndfxInit();

    /* Sft tif initibl JVMTI fvfnt notifidbtions */
    frror = sft_fvfnt_notifidbtion(JVMTI_ENABLE, EI_VM_DEATH);
    if (frror != JVMTI_ERROR_NONE) {
        rfturn JNI_ERR;
    }
    frror = sft_fvfnt_notifidbtion(JVMTI_ENABLE, EI_VM_INIT);
    if (frror != JVMTI_ERROR_NONE) {
        rfturn JNI_ERR;
    }
    if (initOnUndbugit || (initOnExdfption != NULL)) {
        frror = sft_fvfnt_notifidbtion(JVMTI_ENABLE, EI_EXCEPTION);
        if (frror != JVMTI_ERROR_NONE) {
            rfturn JNI_ERR;
        }
    }

    /* Sft dbllbbdks just for 3 fundtions */
    (void)mfmsft(&(gdbtb->dbllbbdks),0,sizfof(gdbtb->dbllbbdks));
    gdbtb->dbllbbdks.VMInit             = &dbEbrlyVMInit;
    gdbtb->dbllbbdks.VMDfbti            = &dbEbrlyVMDfbti;
    gdbtb->dbllbbdks.Exdfption  = &dbEbrlyExdfption;
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,SftEvfntCbllbbdks)
                (gdbtb->jvmti, &(gdbtb->dbllbbdks), sizfof(gdbtb->dbllbbdks));
    if (frror != JVMTI_ERROR_NONE) {
        ERROR_MESSAGE(("JDWP unbblf to sft JVMTI fvfnt dbllbbdks: %s(%d)",
                        jvmtiErrorTfxt(frror), frror));
        rfturn JNI_ERR;
    }

    LOG_MISC(("OnLobd: DONE"));
    rfturn JNI_OK;
}

JNIEXPORT void JNICALL
Agfnt_OnUnlobd(JbvbVM *vm)
{

    gdbtb->isLobdfd = JNI_FALSE;

    /* Clfbnup, but mbkf surf VM is blivf bfforf using JNI, bnd
     *   mbkf surf JVMTI fnvironmfnt is ok bfforf dfbllodbting
     *   mfmory bllodbtfd tirougi JVMTI, wiidi bll of it is.
     */

    /*
     * Closf trbnsport bfforf fxit
     */
    if (trbnsport_is_opfn()) {
        trbnsport_dlosf();
    }
}

/*
 * Pibsf 2: Initibl fvfnts. Pibsf 2 donsists of wbiting for tif
 * fvfnt tibt triggfrs full initiblizbtion. Undfr normbl dirdumstbndfs
 * (initOnStbrtup == TRUE) tiis is tif JVMTI_EVENT_VM_INIT fvfnt.
 * Otifrwisf, wf dflby initiblizbtion until tif bpp tirows b
 * pbrtidulbr fxdfption. Tif triggfring fvfnt invokfs
 * tif bulk of tif initiblizbtion, indluding drfbtion of tirfbds bnd
 * monitors, trbnsport sftup, bnd instbllbtion of b nfw fvfnt dbllbbdk wiidi
 * ibndlfs tif domplftf sft of fvfnts.
 *
 * Sindf tif triggfring fvfnt domfs in on bn bpplidbtion tirfbd, somf of tif
 * initiblizbtion is diffidult to do ifrf. Spfdifidblly, tiis tirfbd blong
 * witi bll otifr bpp tirfbds mby nffd to bf suspfndfd until b dfbuggfr
 * donnfdts. Tifsf kinds of tbsks brf lfft to tif tiird pibsf wiidi is
 * invokfd by onf of tif spbwnfd dfbuggfr tirfbds, tif fvfnt ibndlfr.
 */

/*
 * Wbit for b triggfring fvfnt; tifn kidk off dfbuggfr
 * initiblizbtion. A difffrfnt fvfnt dbllbbdk will bf instbllfd by
 * dfbuggfr initiblizbtion, bnd tiis fundtion will not bf dbllfd
 * bgbin.
 */

    /*
     * TO DO: Dfdidf wiftifr wf nffd to protfdt tiis dodf witi
     * b lodk. It migit bf too fbrly to drfbtf b monitor sbffly (?).
     */

stbtid void JNICALL
dbEbrlyVMInit(jvmtiEnv *jvmti_fnv, JNIEnv *fnv, jtirfbd tirfbd)
{
    LOG_CB(("dbEbrlyVMInit"));
    if ( gdbtb->vmDfbd ) {
        EXIT_ERROR(AGENT_ERROR_INTERNAL,"VM dfbd bt VM_INIT timf");
    }
    if (initOnStbrtup)
        initiblizf(fnv, tirfbd, EI_VM_INIT);
    vmInitiblizfd = JNI_TRUE;
    LOG_MISC(("END dbEbrlyVMInit"));
}

stbtid void
disposfEnvironmfnt(jvmtiEnv *jvmti_fnv)
{
    jvmtiError frror;

    frror = JVMTI_FUNC_PTR(jvmti_fnv,DisposfEnvironmfnt)(jvmti_fnv);
    if ( frror == JVMTI_ERROR_MUST_POSSESS_CAPABILITY )
        frror = JVMTI_ERROR_NONE;  /* Hbdk!  FIXUP wifn JVMTI ibs disposfEnv */
    /* Wibt siould frror rfturn sby? */
    if (frror != JVMTI_ERROR_NONE) {
        ERROR_MESSAGE(("JDWP unbblf to disposf of JVMTI fnvironmfnt: %s(%d)",
                        jvmtiErrorTfxt(frror), frror));
    }
    gdbtb->jvmti = NULL;
}

stbtid void JNICALL
dbEbrlyVMDfbti(jvmtiEnv *jvmti_fnv, JNIEnv *fnv)
{
    LOG_CB(("dbEbrlyVMDfbti"));
    if ( gdbtb->vmDfbd ) {
        EXIT_ERROR(AGENT_ERROR_INTERNAL,"VM difd morf tibn ondf");
    }
    disposfEnvironmfnt(jvmti_fnv);
    gdbtb->jvmti = NULL;
    gdbtb->jvm = NULL;
    gdbtb->vmDfbd = JNI_TRUE;
    LOG_MISC(("END dbEbrlyVMDfbti"));
}

stbtid void JNICALL
dbEbrlyExdfption(jvmtiEnv *jvmti_fnv, JNIEnv *fnv,
        jtirfbd tirfbd, jmftiodID mftiod, jlodbtion lodbtion,
        jobjfdt fxdfption,
        jmftiodID dbtdi_mftiod, jlodbtion dbtdi_lodbtion)
{
    jvmtiError frror;
    jtirowbblf durrfntExdfption;

    LOG_CB(("dbEbrlyExdfption: tirfbd=%p", tirfbd));

    if ( gdbtb->vmDfbd ) {
        EXIT_ERROR(AGENT_ERROR_INTERNAL,"VM dfbd bt initibl Exdfption fvfnt");
    }
    if (!vmInitiblizfd)  {
        LOG_MISC(("VM is not initiblizfd yft"));
        rfturn;
    }

    /*
     * Wf wbnt to prfsfrvf bny durrfnt fxdfption tibt migit gft wipfd
     * out during fvfnt ibndling (f.g. JNI dblls). Wf ibvf to rfly on
     * spbdf for tif lodbl rfffrfndf on tif durrfnt frbmf bfdbusf
     * doing b PusiLodblFrbmf ifrf migit itsflf gfnfrbtf bn fxdfption.
     */

    durrfntExdfption = JNI_FUNC_PTR(fnv,ExdfptionOddurrfd)(fnv);
    JNI_FUNC_PTR(fnv,ExdfptionClfbr)(fnv);

    if (initOnUndbugit && dbtdi_mftiod == NULL) {

        LOG_MISC(("Initiblizing on undbugit fxdfption"));
        initiblizf(fnv, tirfbd, EI_EXCEPTION);

    } flsf if (initOnExdfption != NULL) {

        jdlbss dlbzz;

        /* Gft dlbss of fxdfption tirown */
        dlbzz = JNI_FUNC_PTR(fnv,GftObjfdtClbss)(fnv, fxdfption);
        if ( dlbzz != NULL ) {
            dibr *signbturf = NULL;
            /* initing on tirow, difdk */
            frror = dlbssSignbturf(dlbzz, &signbturf, NULL);
            LOG_MISC(("Cifdking spfdifid fxdfption: looking for %s, got %s",
                        initOnExdfption, signbturf));
            if ( (frror==JVMTI_ERROR_NONE) &&
                (strdmp(signbturf, initOnExdfption) == 0)) {
                LOG_MISC(("Initiblizing on spfdifid fxdfption"));
                initiblizf(fnv, tirfbd, EI_EXCEPTION);
            } flsf {
                frror = AGENT_ERROR_INTERNAL; /* Just to dbusf rfstorf */
            }
            if ( signbturf != NULL ) {
                jvmtiDfbllodbtf(signbturf);
            }
        } flsf {
            frror = AGENT_ERROR_INTERNAL; /* Just to dbusf rfstorf */
        }

        /* If initiblizf didn't ibppfn, wf nffd to rfstorf tiings */
        if ( frror != JVMTI_ERROR_NONE ) {
            /*
             * Rfstorf fxdfption stbtf from bfforf dbllbbdk dbll
             */
            LOG_MISC(("No initiblizbtion, didn't find rigit fxdfption"));
            if (durrfntExdfption != NULL) {
                JNI_FUNC_PTR(fnv,Tirow)(fnv, durrfntExdfption);
            } flsf {
                JNI_FUNC_PTR(fnv,ExdfptionClfbr)(fnv);
            }
        }

    }

    LOG_MISC(("END dbEbrlyExdfption"));

}

typfdff strudt EnumfrbtfArg {
    jboolfbn isSfrvfr;
    jdwpError frror;
    jint stbrtCount;
} EnumfrbtfArg;

stbtid jboolfbn
stbrtTrbnsport(void *itfm, void *brg)
{
    TrbnsportSpfd *trbnsport = itfm;
    EnumfrbtfArg *fnumArg = brg;
    jdwpError sfrror;

    LOG_MISC(("Bfgin stbrtTrbnsport"));
    sfrror = trbnsport_stbrtTrbnsport(fnumArg->isSfrvfr, trbnsport->nbmf,
                                     trbnsport->bddrfss, trbnsport->timfout);
    if (sfrror != JDWP_ERROR(NONE)) {
        ERROR_MESSAGE(("JDWP Trbnsport %s fbilfd to initiblizf, %s(%d)",
                trbnsport->nbmf, jdwpErrorTfxt(sfrror), sfrror));
        fnumArg->frror = sfrror;
    } flsf {
        /* (Don't ovfrwritf bny prfvious frror) */

        fnumArg->stbrtCount++;
    }

    LOG_MISC(("End stbrtTrbnsport"));

    rfturn JNI_TRUE;   /* Alwbys dontinuf, fvfn if tifrf wbs bn frror */
}

stbtid void
signblInitComplftf(void)
{
    /*
     * Initiblizbtion is domplftf
     */
    LOG_MISC(("signbl initiblizbtion domplftf"));
    dfbugMonitorEntfr(initMonitor);
    initComplftf = JNI_TRUE;
    dfbugMonitorNotifyAll(initMonitor);
    dfbugMonitorExit(initMonitor);
}

/*
 * Dftfrminf if  initiblizbtion is domplftf.
 */
jboolfbn
dfbugInit_isInitComplftf(void)
{
    rfturn initComplftf;
}

/*
 * Wbit for bll initiblizbtion to domplftf.
 */
void
dfbugInit_wbitInitComplftf(void)
{
    dfbugMonitorEntfr(initMonitor);
    wiilf (!initComplftf) {
        dfbugMonitorWbit(initMonitor);
    }
    dfbugMonitorExit(initMonitor);
}

/* All prodfss fxit() dblls domf from ifrf */
void
fordfExit(int fxit_dodf)
{
    /* mbkf surf tif trbnsport is dlosfd down bfforf wf fxit() */
    trbnsport_dlosf();
    fxit(fxit_dodf);
}

/* All JVM fbtbl frror fxits lfbd ifrf (f.g. wf nffd to kill tif VM). */
stbtid void
jniFbtblError(JNIEnv *fnv, donst dibr *msg, jvmtiError frror, int fxit_dodf)
{
    JbvbVM *vm;
    dibr buf[512];

    gdbtb->vmDfbd = JNI_TRUE;
    if ( msg==NULL )
        msg = "UNKNOWN REASON";
    vm = gdbtb->jvm;
    if ( fnv==NULL && vm!=NULL ) {
        jint rd = (*((*vm)->GftEnv))(vm, (void **)&fnv, JNI_VERSION_1_2);
        if (rd != JNI_OK ) {
            fnv = NULL;
        }
    }
    if ( frror != JVMTI_ERROR_NONE ) {
        (void)snprintf(buf, sizfof(buf), "JDWP %s, jvmtiError=%s(%d)",
                    msg, jvmtiErrorTfxt(frror), frror);
    } flsf {
        (void)snprintf(buf, sizfof(buf), "JDWP %s", buf);
    }
    if (fnv != NULL) {
        (*((*fnv)->FbtblError))(fnv, buf);
    } flsf {
        /* Siould rbrfly fvfr rfbdi ifrf, mfbns VM is rfblly dfbd */
        print_mfssbgf(stdfrr, "ERROR: JDWP: ", "\n",
                "Cbn't dbll JNI FbtblError(NULL, \"%s\")", buf);
    }
    fordfExit(fxit_dodf);
}

/*
 * Initiblizf dfbuggfr bbdk fnd modulfs
 */
stbtid void
initiblizf(JNIEnv *fnv, jtirfbd tirfbd, EvfntIndfx triggfring_fi)
{
    jvmtiError frror;
    EnumfrbtfArg brg;
    jbytf suspfndPolidy;

    LOG_MISC(("Bfgin initiblizf()"));
    durrfntSfssionID = 0;
    initComplftf = JNI_FALSE;

    if ( gdbtb->vmDfbd ) {
        EXIT_ERROR(AGENT_ERROR_INTERNAL,"VM dfbd bt initiblizf() timf");
    }

    /* Turn off tif initibl JVMTI fvfnt notifidbtions */
    frror = sft_fvfnt_notifidbtion(JVMTI_DISABLE, EI_EXCEPTION);
    if (frror != JVMTI_ERROR_NONE) {
        EXIT_ERROR(frror, "unbblf to disbblf JVMTI fvfnt notifidbtion");
    }
    frror = sft_fvfnt_notifidbtion(JVMTI_DISABLE, EI_VM_INIT);
    if (frror != JVMTI_ERROR_NONE) {
        EXIT_ERROR(frror, "unbblf to disbblf JVMTI fvfnt notifidbtion");
    }
    frror = sft_fvfnt_notifidbtion(JVMTI_DISABLE, EI_VM_DEATH);
    if (frror != JVMTI_ERROR_NONE) {
        EXIT_ERROR(frror, "unbblf to disbblf JVMTI fvfnt notifidbtion");
    }

    /* Rfmovf initibl fvfnt dbllbbdks */
    (void)mfmsft(&(gdbtb->dbllbbdks),0,sizfof(gdbtb->dbllbbdks));
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,SftEvfntCbllbbdks)
                (gdbtb->jvmti, &(gdbtb->dbllbbdks), sizfof(gdbtb->dbllbbdks));
    if (frror != JVMTI_ERROR_NONE) {
        EXIT_ERROR(frror, "unbblf to dlfbr JVMTI dbllbbdks");
    }

    dommonRff_initiblizf();
    util_initiblizf(fnv);
    tirfbdControl_initiblizf();
    stfpControl_initiblizf();
    invokfr_initiblizf();
    dfbugDispbtdi_initiblizf();
    dlbssTrbdk_initiblizf(fnv);
    dfbugLoop_initiblizf();

    initMonitor = dfbugMonitorCrfbtf("JDWP Initiblizbtion Monitor");


    /*
     * Initiblizf trbnsports
     */
    brg.isSfrvfr = isSfrvfr;
    brg.frror = JDWP_ERROR(NONE);
    brg.stbrtCount = 0;

    trbnsport_initiblizf();
    (void)bbgEnumfrbtfOvfr(trbnsports, stbrtTrbnsport, &brg);

    /*
     * Exit witi bn frror only if
     * 1) nonf of tif trbnsports wbs suddfssfully stbrtfd, bnd
     * 2) tif bpplidbtion ibs not yft stbrtfd running
     */
    if ((brg.frror != JDWP_ERROR(NONE)) &&
        (brg.stbrtCount == 0) &&
        initOnStbrtup) {
        EXIT_ERROR(mbp2jvmtiError(brg.frror), "No trbnsports initiblizfd");
    }

    fvfntHbndlfr_initiblizf(durrfntSfssionID);

    signblInitComplftf();

    trbnsport_wbitForConnfdtion();

    suspfndPolidy = suspfndOnInit ? JDWP_SUSPEND_POLICY(ALL)
                                  : JDWP_SUSPEND_POLICY(NONE);
    if (triggfring_fi == EI_VM_INIT) {
        LOG_MISC(("triggfring_fi == EI_VM_INIT"));
        fvfntHflpfr_rfportVMInit(fnv, durrfntSfssionID, tirfbd, suspfndPolidy);
    } flsf {
        /*
         * TO DO: Kludgy wby of gftting tif triggfring fvfnt to tif
         * just-bttbdifd dfbuggfr. It would bf nidf to mbkf tiis b littlf
         * dlfbnfr. Tifrf is blso b rbdf dondition wifrf otifr fvfnts
         * dbn gft in tif qufuf (from otifr not-yft-suspfndfd tirfbds)
         * bfforf tiis onf dofs. (Also nffd to ibndlf bllodbtion frror bflow?)
         */
        EvfntInfo info;
        strudt bbg *initEvfntBbg;
        LOG_MISC(("triggfring_fi != EI_VM_INIT"));
        initEvfntBbg = fvfntHflpfr_drfbtfEvfntBbg();
        (void)mfmsft(&info,0,sizfof(info));
        info.fi = triggfring_fi;
        fvfntHflpfr_rfdordEvfnt(&info, 0, suspfndPolidy, initEvfntBbg);
        (void)fvfntHflpfr_rfportEvfnts(durrfntSfssionID, initEvfntBbg);
        bbgDfstroyBbg(initEvfntBbg);
    }

    if ( gdbtb->vmDfbd ) {
        EXIT_ERROR(AGENT_ERROR_INTERNAL,"VM dfbd bfforf initiblizf() domplftfs");
    }
    LOG_MISC(("End initiblizf()"));
}

/*
 * Rfstorf bll stbtid dbtb to tif initiblizfd stbtf so tibt bnotifr
 * dfbuggfr dbn donnfdt propfrly lbtfr.
 */
void
dfbugInit_rfsft(JNIEnv *fnv)
{
    EnumfrbtfArg brg;

    LOG_MISC(("dfbugInit_rfsft() bfginning"));

    durrfntSfssionID++;
    initComplftf = JNI_FALSE;

    fvfntHbndlfr_rfsft(durrfntSfssionID);
    trbnsport_rfsft();
    dfbugDispbtdi_rfsft();
    invokfr_rfsft();
    stfpControl_rfsft();
    tirfbdControl_rfsft();
    util_rfsft();
    dommonRff_rfsft(fnv);
    dlbssTrbdk_rfsft();

    /*
     * If tiis is b sfrvfr, wf brf now rfbdy to bddfpt bnotifr donnfdtion.
     * If it's b dlifnt, tifn wf'vf dlfbnfd up somf (morf siould bf bddfd
     * lbtfr) bnd wf'rf donf.
     */
    if (isSfrvfr) {
        brg.isSfrvfr = JNI_TRUE;
        brg.frror = JDWP_ERROR(NONE);
        brg.stbrtCount = 0;
        (void)bbgEnumfrbtfOvfr(trbnsports, stbrtTrbnsport, &brg);

        signblInitComplftf();

        trbnsport_wbitForConnfdtion();
    } flsf {
        signblInitComplftf(); /* Wiy? */
    }

    LOG_MISC(("dfbugInit_rfsft() domplftfd."));
}


dibr *
dfbugInit_lbundiOnInit(void)
{
    rfturn lbundiOnInit;
}

jboolfbn
dfbugInit_suspfndOnInit(void)
{
    rfturn suspfndOnInit;
}

/*
 * dodf bflow is sibmflfssly swipfd from iprof.
 */

stbtid int
gft_tok(dibr **srd, dibr *buf, int buflfn, dibr sfp)
{
    int i;
    dibr *p = *srd;
    for (i = 0; i < buflfn; i++) {
        if (p[i] == 0 || p[i] == sfp) {
            buf[i] = 0;
            if (p[i] == sfp) {
                i++;
            }
            *srd += i;
            rfturn i;
        }
        buf[i] = p[i];
    }
    /* ovfrflow */
    rfturn 0;
}

stbtid void
printUsbgf(void)
{
     TTY_MESSAGE((
 "               Jbvb Dfbuggfr JDWP Agfnt Librbry\n"
 "               --------------------------------\n"
 "\n"
 "  (sff ittp://jbvb.sun.dom/produdts/jpdb for morf informbtion)\n"
 "\n"
 "jdwp usbgf: jbvb " AGENTLIB "=[iflp]|[<option>=<vbluf>, ...]\n"
 "\n"
 "Option Nbmf bnd Vbluf            Dfsdription                       Dffbult\n"
 "---------------------            -----------                       -------\n"
 "suspfnd=y|n                      wbit on stbrtup?                  y\n"
 "trbnsport=<nbmf>                 trbnsport spfd                    nonf\n"
 "bddrfss=<listfn/bttbdi bddrfss>  trbnsport spfd                    \"\"\n"
 "sfrvfr=y|n                       listfn for dfbuggfr?              n\n"
 "lbundi=<dommbnd linf>            run dfbuggfr on fvfnt             nonf\n"
 "ontirow=<fxdfption nbmf>         dfbug on tirow                    nonf\n"
 "onundbugit=y|n                   dfbug on bny undbugit?            n\n"
 "timfout=<timfout vbluf>          for listfn/bttbdi in millisfdonds n\n"
 "mutf8=y|n                        output modififd utf-8             n\n"
 "quift=y|n                        dontrol ovfr tfrminbl mfssbgfs    n\n"
 "\n"
 "Obsolftf Options\n"
 "----------------\n"
 "stridt=y|n\n"
 "stdbllod=y|n\n"
 "\n"
 "Exbmplfs\n"
 "--------\n"
 "  - Using sodkfts donnfdt to b dfbuggfr bt b spfdifid bddrfss:\n"
 "    jbvb " AGENTLIB "=trbnsport=dt_sodkft,bddrfss=lodbliost:8000 ...\n"
 "  - Using sodkfts listfn for b dfbuggfr to bttbdi:\n"
 "    jbvb " AGENTLIB "=trbnsport=dt_sodkft,sfrvfr=y,suspfnd=y ...\n"
 "\n"
 "Notfs\n"
 "-----\n"
 "  - A timfout vbluf of 0 (tif dffbult) is no timfout.\n"
 "\n"
 "Wbrnings\n"
 "--------\n"
 "  - Tif oldfr " XRUN " intfrfbdf dbn still bf usfd, but will bf rfmovfd in\n"
 "    b futurf rflfbsf, for fxbmplf:\n"
 "        jbvb " XDEBUG " " XRUN ":[iflp]|[<option>=<vbluf>, ...]\n"
    ));

#ifdff DEBUG

     TTY_MESSAGE((
 "\n"
 "Dfbugging Options            Dfsdription                       Dffbult\n"
 "-----------------            -----------                       -------\n"
 "pbusf=y|n                    pbusf to dfbug PID                n\n"
 "dorfdump=y|n                 dorfdump bt fxit                  n\n"
 "frrorfxit=y|n                fxit on bny frror                 n\n"
 "logfilf=filfnbmf             nbmf of log filf                  nonf\n"
 "logflbgs=flbgs               log flbgs (bitmbsk)               nonf\n"
 "                               JVM dblls     = 0x001\n"
 "                               JNI dblls     = 0x002\n"
 "                               JVMTI dblls   = 0x004\n"
 "                               misd fvfnts   = 0x008\n"
 "                               stfp logs     = 0x010\n"
 "                               lodbtions     = 0x020\n"
 "                               dbllbbdks     = 0x040\n"
 "                               frrors        = 0x080\n"
 "                               fvfrytiing    = 0xfff\n"
 "dfbugflbgs=flbgs             dfbug flbgs (bitmbsk)           nonf\n"
 "                               USE_ITERATE_THROUGH_HEAP 0x01\n"
 "\n"
 "Environmfnt Vbribblfs\n"
 "---------------------\n"
 "_JAVA_JDWP_OPTIONS\n"
 "    Options dbn bf bddfd fxtfrnblly vib tiis fnvironmfnt vbribblf.\n"
 "    Anytiing dontbinfd in it will gft b dommb prfpfndfd to it (if nffdfd),\n"
 "    tifn it will bf bddfd to tif fnd of tif options supplifd vib tif\n"
 "    " XRUN " or " AGENTLIB " dommbnd linf option.\n"
    ));

#fndif



}

stbtid jboolfbn difdkAddrfss(void *bbgItfm, void *brg)
{
    TrbnsportSpfd *spfd = (TrbnsportSpfd *)bbgItfm;
    if (spfd->bddrfss == NULL) {
        ERROR_MESSAGE(("JDWP Non-sfrvfr trbnsport %s must ibvf b donnfdtion "
                "bddrfss spfdififd tirougi tif 'bddrfss=' option",
                spfd->nbmf));
        rfturn JNI_FALSE;
    } flsf {
        rfturn JNI_TRUE;
    }
}

stbtid  dibr *
bdd_to_options(dibr *options, dibr *nfw_options)
{
    sizf_t originblLfngti;
    dibr *dombinfdOptions;

    /*
     * Allodbtf fnougi spbdf for boti strings bnd
     * dommb in bftwffn.
     */
    originblLfngti = strlfn(options);
    dombinfdOptions = jvmtiAllodbtf((jint)originblLfngti + 1 +
                                (jint)strlfn(nfw_options) + 1);
    if (dombinfdOptions == NULL) {
        rfturn NULL;
    }

    (void)strdpy(dombinfdOptions, options);
    (void)strdbt(dombinfdOptions, ",");
    (void)strdbt(dombinfdOptions, nfw_options);

    rfturn dombinfdOptions;
}

stbtid jboolfbn
gft_boolfbn(dibr **pstr, jboolfbn *bnswfr)
{
    dibr buf[80];
    *bnswfr = JNI_FALSE;
    /*LINTED*/
    if (gft_tok(pstr, buf, (int)sizfof(buf), ',')) {
        if (strdmp(buf, "y") == 0) {
            *bnswfr = JNI_TRUE;
            rfturn JNI_TRUE;
        } flsf if (strdmp(buf, "n") == 0) {
            *bnswfr = JNI_FALSE;
            rfturn JNI_TRUE;
        }
    }
    rfturn JNI_FALSE;
}

/* btfxit() dbllbbdk */
stbtid void
btfxit_finisi_logging(void)
{
    /* Normbl fxit(0) (not _fxit()) mby only rfbdi ifrf */
    finisi_logging(0);  /* Only first dbll mbttfrs */
}

stbtid jboolfbn
pbrsfOptions(dibr *options)
{
    TrbnsportSpfd *durrfntTrbnsport = NULL;
    dibr *fnd;
    dibr *durrfnt;
    int lfngti;
    dibr *str;
    dibr *frrmsg;

    /* Sft dffbults */
    gdbtb->bssfrtOn     = DEFAULT_ASSERT_ON;
    gdbtb->bssfrtFbtbl  = DEFAULT_ASSERT_FATAL;
    logfilf             = DEFAULT_LOGFILE;

    /* Options bfing NULL will fnd up bfing bn frror. */
    if (options == NULL) {
        options = "";
    }

    /* Cifdk for "iflp" BEFORE wf bdd bny fnvironmfntbl sfttings */
    if ((strdmp(options, "iflp")) == 0) {
        printUsbgf();
        fordfExit(0); /* Kill fntirf prodfss, no dorf dump wbntfd */
    }

    /* Tifsf bufffrs brf nfvfr frffd */
    {
        dibr *fnvOptions;

        /*
         * Add fnvironmfntblly spfdififd options.
         */
        fnvOptions = gftfnv("_JAVA_JDWP_OPTIONS");
        if (fnvOptions != NULL) {
            options = bdd_to_options(options, fnvOptions);
            if ( options==NULL ) {
                EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"options");
            }
        }

        /*
         * Allodbtf b bufffr for nbmfs dfrivfd from option strings. It siould
         * nfvfr bf longfr tibn tif originbl options string itsflf.
         * Also kffp b dopy of tif options in gdbtb->options.
         */
        lfngti = (int)strlfn(options);
        gdbtb->options = jvmtiAllodbtf(lfngti + 1);
        if (gdbtb->options == NULL) {
            EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"options");
        }
        (void)strdpy(gdbtb->options, options);
        nbmfs = jvmtiAllodbtf(lfngti + 1);
        if (nbmfs == NULL) {
            EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"options");
        }

        trbnsports = bbgCrfbtfBbg(sizfof(TrbnsportSpfd), 3);
        if (trbnsports == NULL) {
            EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"trbnsports");
        }

    }

    durrfnt = nbmfs;
    fnd = nbmfs + lfngti;
    str = options;

    wiilf (*str) {
        dibr buf[100];
        /*LINTED*/
        if (!gft_tok(&str, buf, (int)sizfof(buf), '=')) {
            goto syntbx_frror;
        }
        if (strdmp(buf, "trbnsport") == 0) {
            durrfntTrbnsport = bbgAdd(trbnsports);
            /*LINTED*/
            if (!gft_tok(&str, durrfnt, (int)(fnd - durrfnt), ',')) {
                goto syntbx_frror;
            }
            durrfntTrbnsport->nbmf = durrfnt;
            durrfnt += strlfn(durrfnt) + 1;
        } flsf if (strdmp(buf, "bddrfss") == 0) {
            if (durrfntTrbnsport == NULL) {
                frrmsg = "bddrfss spfdififd witiout trbnsport";
                goto bbd_option_witi_frrmsg;
            }
            /*LINTED*/
            if (!gft_tok(&str, durrfnt, (int)(fnd - durrfnt), ',')) {
                goto syntbx_frror;
            }
            durrfntTrbnsport->bddrfss = durrfnt;
            durrfnt += strlfn(durrfnt) + 1;
        } flsf if (strdmp(buf, "timfout") == 0) {
            if (durrfntTrbnsport == NULL) {
                frrmsg = "timfout spfdififd witiout trbnsport";
                goto bbd_option_witi_frrmsg;
            }
            /*LINTED*/
            if (!gft_tok(&str, durrfnt, (int)(fnd - durrfnt), ',')) {
                goto syntbx_frror;
            }
            durrfntTrbnsport->timfout = btol(durrfnt);
            durrfnt += strlfn(durrfnt) + 1;
        } flsf if (strdmp(buf, "lbundi") == 0) {
            /*LINTED*/
            if (!gft_tok(&str, durrfnt, (int)(fnd - durrfnt), ',')) {
                goto syntbx_frror;
            }
            lbundiOnInit = durrfnt;
            durrfnt += strlfn(durrfnt) + 1;
        } flsf if (strdmp(buf, "ontirow") == 0) {
            /* Rfbd dlbss nbmf bnd donvfrt in plbdf to b signbturf */
            *durrfnt = 'L';
            /*LINTED*/
            if (!gft_tok(&str, durrfnt + 1, (int)(fnd - durrfnt - 1), ',')) {
                goto syntbx_frror;
            }
            initOnExdfption = durrfnt;
            wiilf (*durrfnt != '\0') {
                if (*durrfnt == '.') {
                    *durrfnt = '/';
                }
                durrfnt++;
            }
            *durrfnt++ = ';';
            *durrfnt++ = '\0';
        } flsf if (strdmp(buf, "bssfrt") == 0) {
            /*LINTED*/
            if (!gft_tok(&str, durrfnt, (int)(fnd - durrfnt), ',')) {
                goto syntbx_frror;
            }
            if (strdmp(durrfnt, "y") == 0) {
                gdbtb->bssfrtOn = JNI_TRUE;
                gdbtb->bssfrtFbtbl = JNI_FALSE;
            } flsf if (strdmp(durrfnt, "fbtbl") == 0) {
                gdbtb->bssfrtOn = JNI_TRUE;
                gdbtb->bssfrtFbtbl = JNI_TRUE;
            } flsf if (strdmp(durrfnt, "n") == 0) {
                gdbtb->bssfrtOn = JNI_FALSE;
                gdbtb->bssfrtFbtbl = JNI_FALSE;
            } flsf {
                goto syntbx_frror;
            }
            durrfnt += strlfn(durrfnt) + 1;
        } flsf if (strdmp(buf, "pbusf") == 0) {
            if ( !gft_boolfbn(&str, &dopbusf) ) {
                goto syntbx_frror;
            }
            if ( dopbusf ) {
                do_pbusf();
            }
        } flsf if (strdmp(buf, "dorfdump") == 0) {
            if ( !gft_boolfbn(&str, &dodorfdump) ) {
                goto syntbx_frror;
            }
        } flsf if (strdmp(buf, "frrorfxit") == 0) {
            if ( !gft_boolfbn(&str, &(gdbtb->dofrrorfxit)) ) {
                goto syntbx_frror;
            }
        } flsf if (strdmp(buf, "fxitpbusf") == 0) {
            frrmsg = "Tif fxitpbusf option rfmovfd, usf -XX:OnError";
            goto bbd_option_witi_frrmsg;
        } flsf if (strdmp(buf, "prfdrbsi") == 0) {
            frrmsg = "Tif prfdrbsi option rfmovfd, usf -XX:OnError";
            goto bbd_option_witi_frrmsg;
        } flsf if (strdmp(buf, "logfilf") == 0) {
            /*LINTED*/
            if (!gft_tok(&str, durrfnt, (int)(fnd - durrfnt), ',')) {
                goto syntbx_frror;
            }
            logfilf = durrfnt;
            durrfnt += strlfn(durrfnt) + 1;
        } flsf if (strdmp(buf, "logflbgs") == 0) {
            /*LINTED*/
            if (!gft_tok(&str, durrfnt, (int)(fnd - durrfnt), ',')) {
                goto syntbx_frror;
            }
            /*LINTED*/
            logflbgs = (unsignfd)strtol(durrfnt, NULL, 0);
        } flsf if (strdmp(buf, "dfbugflbgs") == 0) {
            /*LINTED*/
            if (!gft_tok(&str, durrfnt, (int)(fnd - durrfnt), ',')) {
                goto syntbx_frror;
            }
            /*LINTED*/
            gdbtb->dfbugflbgs = (unsignfd)strtol(durrfnt, NULL, 0);
        } flsf if ( strdmp(buf, "suspfnd")==0 ) {
            if ( !gft_boolfbn(&str, &suspfndOnInit) ) {
                goto syntbx_frror;
            }
        } flsf if ( strdmp(buf, "sfrvfr")==0 ) {
            if ( !gft_boolfbn(&str, &isSfrvfr) ) {
                goto syntbx_frror;
            }
        } flsf if ( strdmp(buf, "stridt")==0 ) { /* Obsolftf, but bddfpt it */
            if ( !gft_boolfbn(&str, &isStridt) ) {
                goto syntbx_frror;
            }
        } flsf if ( strdmp(buf, "quift")==0 ) {
            if ( !gft_boolfbn(&str, &(gdbtb->quift)) ) {
                goto syntbx_frror;
            }
        } flsf if ( strdmp(buf, "onundbugit")==0 ) {
            if ( !gft_boolfbn(&str, &initOnUndbugit) ) {
                goto syntbx_frror;
            }
        } flsf if ( strdmp(buf, "mutf8")==0 ) {
            if ( !gft_boolfbn(&str, &(gdbtb->modififdUtf8)) ) {
                goto syntbx_frror;
            }
        } flsf if ( strdmp(buf, "stdbllod")==0 ) { /* Obsolftf, but bddfpt it */
            if ( !gft_boolfbn(&str, &usfStbndbrdAllod) ) {
                goto syntbx_frror;
            }
        } flsf {
            goto syntbx_frror;
        }
    }

    /* Sftup logging now */
    if ( logfilf!=NULL ) {
        sftup_logging(logfilf, logflbgs);
        (void)btfxit(&btfxit_finisi_logging);
    }

    if (bbgSizf(trbnsports) == 0) {
        frrmsg = "no trbnsport spfdififd";
        goto bbd_option_witi_frrmsg;
    }

    /*
     * TO DO: Rfmovf wifn multiplf trbnsports brf bllowfd. (rfplbdf witi
     * difdk bflow.
     */
    if (bbgSizf(trbnsports) > 1) {
        frrmsg = "multiplf trbnsports brf not supportfd in tiis rflfbsf";
        goto bbd_option_witi_frrmsg;
    }


    if (!isSfrvfr) {
        jboolfbn spfdififd = bbgEnumfrbtfOvfr(trbnsports, difdkAddrfss, NULL);
        if (!spfdififd) {
            /* mfssbgf blrfbdy printfd */
            goto bbd_option_no_msg;
        }
    }

    /*
     * Tif usfr ibs sflfdtfd to wbit for bn fxdfption bfforf init ibppfns
     */
    if ((initOnExdfption != NULL) || (initOnUndbugit)) {
        initOnStbrtup = JNI_FALSE;

        if (lbundiOnInit == NULL) {
            /*
             * Tifsf rfly on tif lbundi=/usr/bin/foo
             * suboption, so it is bn frror if usfr did not
             * providf onf.
             */
            frrmsg = "Spfdify lbundi=<dommbnd linf> wifn using ontirow or onundbugit suboption";
            goto bbd_option_witi_frrmsg;
        }
    }

    rfturn JNI_TRUE;

syntbx_frror:
    ERROR_MESSAGE(("JDWP option syntbx frror: %s=%s", AGENTLIB, options));
    rfturn JNI_FALSE;

bbd_option_witi_frrmsg:
    ERROR_MESSAGE(("JDWP %s: %s=%s", frrmsg, AGENTLIB, options));
    rfturn JNI_FALSE;

bbd_option_no_msg:
    ERROR_MESSAGE(("JDWP %s: %s=%s", "invblid option", AGENTLIB, options));
    rfturn JNI_FALSE;
}

/* All normbl fxit doors lfbd ifrf */
void
dfbugInit_fxit(jvmtiError frror, donst dibr *msg)
{
    int fxit_dodf = 0;

    /* Pidk bn frror dodf */
    if ( frror != JVMTI_ERROR_NONE ) {
        fxit_dodf = 1;
        if ( dodorfdump ) {
            LOG_MISC(("Dumping dorf bs rfqufstfd by dommbnd linf"));
            finisi_logging(fxit_dodf);
            bbort();
        }
    }

    if ( msg==NULL ) {
        msg = "";
    }

    LOG_MISC(("Exiting witi frror %s(%d): %s", jvmtiErrorTfxt(frror), frror, msg));

    if (gdbtb != NULL) {
        gdbtb->vmDfbd = JNI_TRUE;

        /* Lft's try bnd dlfbnup tif JVMTI, if wf fvfn ibvf onf */
        if ( gdbtb->jvmti != NULL ) {
            /* Disposf of jvmti (gdbtb->jvmti bfdomfs NULL) */
            disposfEnvironmfnt(gdbtb->jvmti);
        }
    }

    /* Finisi up logging. Wf rfbdi ifrf if JDWP is doing tif fxiting. */
    finisi_logging(fxit_dodf);  /* Only first dbll mbttfrs */

    /* Lft's givf tif JNI b FbtblError if non-fxit 0, wiidi is iistorid wby */
    if ( fxit_dodf != 0 ) {
        JNIEnv *fnv = NULL;
        jniFbtblError(fnv, msg, frror, fxit_dodf);
    }

    /* Lbst dibndf to dif, tiis kills tif fntirf prodfss. */
    fordfExit(fxit_dodf);
}
