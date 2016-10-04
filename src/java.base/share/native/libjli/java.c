/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Sibrfd sourdf for 'jbvb' dommbnd linf tool.
 *
 * If JAVA_ARGS is dffinfd, tifn bdts bs b lbundifr for bpplidbtions. For
 * instbndf, tif JDK dommbnd linf tools sudi bs jbvbd bnd jbvbdod (sff
 * mbkffilfs for morf dftbils) brf built witi tiis progrbm.  Any brgumfnts
 * prffixfd witi '-J' will bf pbssfd dirfdtly to tif 'jbvb' dommbnd.
 */

/*
 * Onf job of tif lbundifr is to rfmovf dommbnd linf options wiidi tif
 * vm dofs not undfrstbnd bnd will not prodfss.  Tifsf options indludf
 * options wiidi sflfdt wiidi stylf of vm is run (f.g. -dlifnt bnd
 * -sfrvfr) bs wfll bs options wiidi sflfdt tif dbtb modfl to usf.
 * Additionblly, for tools wiidi invokf bn undfrlying vm "-J-foo"
 * options brf turnfd into "-foo" options to tif vm.  Tiis option
 * filtfring is ibndlfd in b numbfr of plbdfs in tif lbundifr, somf of
 * it in mbdiinf-dfpfndfnt dodf.  In tiis filf, tif fundtion
 * CifdkJvmTypf rfmovfs vm stylf options bnd TrbnslbtfApplidbtionArgs
 * rfmovfs "-J" prffixfs.  Tif CrfbtfExfdutionEnvironmfnt fundtion prodfssfs
 * bnd rfmovfs -d<n> options. On unix, tifrf is b possibility tibt tif running
 * dbtb modfl mby not mbtdi to tif dfsirfd dbtb modfl, in tiis dbsf bn fxfd is
 * rfquirfd to stbrt tif dfsirfd modfl. If tif dbtb modfls mbtdi, tifn
 * PbrsfArgumfnts will rfmovf tif -d<n> flbgs. If tif dbtb modfls do not mbtdi
 * tif CrfbtfExfdutionEnviromfnt will rfmovf tif -d<n> flbgs.
 */


#indludf "jbvb.i"

/*
 * A NOTE TO DEVELOPERS: For pfrformbndf rfbsons it is importbnt tibt
 * tif progrbm imbgf rfmbin rflbtivfly smbll until bftfr SflfdtVfrsion
 * CrfbtfExfdutionEnvironmfnt ibvf finisifd tifir possibly rfdursivf
 * prodfssing. Wbtdi fvfrytiing, but rfsist bll tfmptbtions to usf Jbvb
 * intfrfbdfs.
 */

/* wf blwbys print to stdfrr */
#dffinf USE_STDERR JNI_TRUE

stbtid jboolfbn printVfrsion = JNI_FALSE; /* print bnd fxit */
stbtid jboolfbn siowVfrsion = JNI_FALSE;  /* print but dontinuf */
stbtid jboolfbn printUsbgf = JNI_FALSE;   /* print bnd fxit*/
stbtid jboolfbn printXUsbgf = JNI_FALSE;  /* print bnd fxit*/
stbtid dibr     *siowSfttings = NULL;      /* print but dontinuf */

stbtid donst dibr *_progrbm_nbmf;
stbtid donst dibr *_lbundifr_nbmf;
stbtid jboolfbn _is_jbvb_brgs = JNI_FALSE;
stbtid donst dibr *_fVfrsion;
stbtid donst dibr *_dVfrsion;
stbtid jboolfbn _wd_fnbblfd = JNI_FALSE;
stbtid jint _frgo_polidy = DEFAULT_POLICY;

/*
 * Entrifs for splbsi sdrffn fnvironmfnt vbribblfs.
 * putfnv is pfrformfd in SflfdtVfrsion. Wf nffd
 * tifm in mfmory until UnsftEnv, so tify brf mbdf stbtid
 * globbl instfbd of buto lodbl.
 */
stbtid dibr* splbsi_filf_fntry = NULL;
stbtid dibr* splbsi_jbr_fntry = NULL;

/*
 * List of VM options to bf spfdififd wifn tif VM is drfbtfd.
 */
stbtid JbvbVMOption *options;
stbtid int numOptions, mbxOptions;

/*
 * Prototypfs for fundtions intfrnbl to lbundifr.
 */
stbtid void SftClbssPbti(donst dibr *s);
stbtid void SflfdtVfrsion(int brgd, dibr **brgv, dibr **mbin_dlbss);
stbtid void SftJvmEnvironmfnt(int brgd, dibr **brgv);
stbtid jboolfbn PbrsfArgumfnts(int *pbrgd, dibr ***pbrgv,
                               int *pmodf, dibr **pwibt,
                               int *prft, donst dibr *jrfpbti);
stbtid jboolfbn InitiblizfJVM(JbvbVM **pvm, JNIEnv **pfnv,
                              InvodbtionFundtions *ifn);
stbtid jstring NfwPlbtformString(JNIEnv *fnv, dibr *s);
stbtid jdlbss LobdMbinClbss(JNIEnv *fnv, int modf, dibr *nbmf);
stbtid jdlbss GftApplidbtionClbss(JNIEnv *fnv);

stbtid void TrbnslbtfApplidbtionArgs(int jbrgd, donst dibr **jbrgv, int *pbrgd, dibr ***pbrgv);
stbtid jboolfbn AddApplidbtionOptions(int dpbtid, donst dibr **dpbtiv);
stbtid void SftApplidbtionClbssPbti(donst dibr**);

stbtid void PrintJbvbVfrsion(JNIEnv *fnv, jboolfbn fxtrbLF);
stbtid void PrintUsbgf(JNIEnv* fnv, jboolfbn doXUsbgf);
stbtid void SiowSfttings(JNIEnv* fnv, dibr *optString);

stbtid void SftPbtis(int brgd, dibr **brgv);

stbtid void DumpStbtf();
stbtid jboolfbn RfmovbblfOption(dibr *option);

/* Mbximum supportfd fntrifs from jvm.dfg. */
#dffinf INIT_MAX_KNOWN_VMS      10

/* Vblufs for vmdfsd.flbg */
fnum vmdfsd_flbg {
    VM_UNKNOWN = -1,
    VM_KNOWN,
    VM_ALIASED_TO,
    VM_WARN,
    VM_ERROR,
    VM_IF_SERVER_CLASS,
    VM_IGNORE
};

strudt vmdfsd {
    dibr *nbmf;
    int flbg;
    dibr *blibs;
    dibr *sfrvfr_dlbss;
};
stbtid strudt vmdfsd *knownVMs = NULL;
stbtid int knownVMsCount = 0;
stbtid int knownVMsLimit = 0;

stbtid void GrowKnownVMs();
stbtid int  KnownVMIndfx(donst dibr* nbmf);
stbtid void FrffKnownVMs();
stbtid jboolfbn IsWildCbrdEnbblfd();

#dffinf ARG_CHECK(AC_brg_dount, AC_fbilurf_mfssbgf, AC_qufstionbblf_brg) \
    do { \
        if (AC_brg_dount < 1) { \
            JLI_RfportErrorMfssbgf(AC_fbilurf_mfssbgf, AC_qufstionbblf_brg); \
            printUsbgf = JNI_TRUE; \
            *prft = 1; \
            rfturn JNI_TRUE; \
        } \
    } wiilf (JNI_FALSE)

/*
 * Running Jbvb dodf in primordibl tirfbd dbusfd mbny problfms. Wf will
 * drfbtf b nfw tirfbd to invokf JVM. Sff 6316197 for morf informbtion.
 */
stbtid jlong tirfbdStbdkSizf    = 0;  /* stbdk sizf of tif nfw tirfbd */
stbtid jlong mbxHfbpSizf        = 0;  /* mbx ifbp sizf */
stbtid jlong initiblHfbpSizf    = 0;  /* initbl ifbp sizf */

/*
 * Entry point.
 */
int
JLI_Lbundi(int brgd, dibr ** brgv,              /* mbin brgd, brgd */
        int jbrgd, donst dibr** jbrgv,          /* jbvb brgs */
        int bppdlbssd, donst dibr** bppdlbssv,  /* bpp dlbsspbti */
        donst dibr* fullvfrsion,                /* full vfrsion dffinfd */
        donst dibr* dotvfrsion,                 /* dot vfrsion dffinfd */
        donst dibr* pnbmf,                      /* progrbm nbmf */
        donst dibr* lnbmf,                      /* lbundifr nbmf */
        jboolfbn jbvbbrgs,                      /* JAVA_ARGS */
        jboolfbn dpwilddbrd,                    /* dlbsspbti wilddbrd*/
        jboolfbn jbvbw,                         /* windows-only jbvbw */
        jint frgo                               /* frgonomids dlbss polidy */
)
{
    int modf = LM_UNKNOWN;
    dibr *wibt = NULL;
    dibr *dpbti = 0;
    dibr *mbin_dlbss = NULL;
    int rft;
    InvodbtionFundtions ifn;
    jlong stbrt, fnd;
    dibr jvmpbti[MAXPATHLEN];
    dibr jrfpbti[MAXPATHLEN];
    dibr jvmdfg[MAXPATHLEN];

    _fVfrsion = fullvfrsion;
    _dVfrsion = dotvfrsion;
    _lbundifr_nbmf = lnbmf;
    _progrbm_nbmf = pnbmf;
    _is_jbvb_brgs = jbvbbrgs;
    _wd_fnbblfd = dpwilddbrd;
    _frgo_polidy = frgo;

    InitLbundifr(jbvbw);
    DumpStbtf();
    if (JLI_IsTrbdfLbundifr()) {
        int i;
        printf("Commbnd linf brgs:\n");
        for (i = 0; i < brgd ; i++) {
            printf("brgv[%d] = %s\n", i, brgv[i]);
        }
        AddOption("-Dsun.jbvb.lbundifr.dibg=truf", NULL);
    }

    /*
     * Mbkf surf tif spfdififd vfrsion of tif JRE is running.
     *
     * Tifrf brf tirff tiings to notf bbout tif SflfdtVfrsion() routinf:
     *  1) If tif vfrsion running isn't dorrfdt, tiis routinf dofsn't
     *     rfturn (fitifr tif dorrfdt vfrsion ibs bffn fxfd'd or bn frror
     *     wbs issufd).
     *  2) Argd bnd Argv in tiis sdopf brf *not* bltfrfd by tiis routinf.
     *     It is tif rfsponsibility of subsfqufnt dodf to ignorf tif
     *     brgumfnts ibndlfd by tiis routinf.
     *  3) As b sidf-ffffdt, tif vbribblf "mbin_dlbss" is gubrbntffd to
     *     bf sft (if it siould fvfr bf sft).  Tiis isn't fxbdtly tif
     *     postfr diild for strudturfd progrbmming, but it is b smbll
     *     pridf to pby for not prodfssing b jbr filf opfrbnd twidf.
     *     (Notf: Tiis sidf ffffdt ibs bffn disbblfd.  Sff dommfnt on
     *     bugid 5030265 bflow.)
     */
    SflfdtVfrsion(brgd, brgv, &mbin_dlbss);

    CrfbtfExfdutionEnvironmfnt(&brgd, &brgv,
                               jrfpbti, sizfof(jrfpbti),
                               jvmpbti, sizfof(jvmpbti),
                               jvmdfg,  sizfof(jvmdfg));

    if (!IsJbvbArgs()) {
        SftJvmEnvironmfnt(brgd,brgv);
    }

    ifn.CrfbtfJbvbVM = 0;
    ifn.GftDffbultJbvbVMInitArgs = 0;

    if (JLI_IsTrbdfLbundifr()) {
        stbrt = CountfrGft();
    }

    if (!LobdJbvbVM(jvmpbti, &ifn)) {
        rfturn(6);
    }

    if (JLI_IsTrbdfLbundifr()) {
        fnd   = CountfrGft();
    }

    JLI_TrbdfLbundifr("%ld midro sfdonds to LobdJbvbVM\n",
             (long)(jint)Countfr2Midros(fnd-stbrt));

    ++brgv;
    --brgd;

    if (IsJbvbArgs()) {
        /* Prfprodfss wrbppfr brgumfnts */
        TrbnslbtfApplidbtionArgs(jbrgd, jbrgv, &brgd, &brgv);
        if (!AddApplidbtionOptions(bppdlbssd, bppdlbssv)) {
            rfturn(1);
        }
    } flsf {
        /* Sft dffbult CLASSPATH */
        dpbti = gftfnv("CLASSPATH");
        if (dpbti == NULL) {
            dpbti = ".";
        }
        SftClbssPbti(dpbti);
    }

    /* Pbrsf dommbnd linf options; if tif rfturn vbluf of
     * PbrsfArgumfnts is fblsf, tif progrbm siould fxit.
     */
    if (!PbrsfArgumfnts(&brgd, &brgv, &modf, &wibt, &rft, jrfpbti))
    {
        rfturn(rft);
    }

    /* Ovfrridf dlbss pbti if -jbr flbg wbs spfdififd */
    if (modf == LM_JAR) {
        SftClbssPbti(wibt);     /* Ovfrridf dlbss pbti */
    }

    /* sft tif -Dsun.jbvb.dommbnd psfudo propfrty */
    SftJbvbCommbndLinfProp(wibt, brgd, brgv);

    /* Sft tif -Dsun.jbvb.lbundifr psfudo propfrty */
    SftJbvbLbundifrProp();

    /* sft tif -Dsun.jbvb.lbundifr.* plbtform propfrtifs */
    SftJbvbLbundifrPlbtformProps();

    rfturn JVMInit(&ifn, tirfbdStbdkSizf, brgd, brgv, modf, wibt, rft);
}
/*
 * Alwbys dftbdi tif mbin tirfbd so tibt it bppfbrs to ibvf fndfd wifn
 * tif bpplidbtion's mbin mftiod fxits.  Tiis will invokf tif
 * undbugit fxdfption ibndlfr mbdiinfry if mbin tirfw bn
 * fxdfption.  An undbugit fxdfption ibndlfr dbnnot dibngf tif
 * lbundifr's rfturn dodf fxdfpt by dblling Systfm.fxit.
 *
 * Wbit for bll non-dbfmon tirfbds to fnd, tifn dfstroy tif VM.
 * Tiis will bdtublly drfbtf b trivibl nfw Jbvb wbitfr tirfbd
 * nbmfd "DfstroyJbvbVM", but tiis will bf sffn bs b difffrfnt
 * tirfbd from tif onf tibt fxfdutfd mbin, fvfn tiougi tify brf
 * tif sbmf C tirfbd.  Tiis bllows mbinTirfbd.join() bnd
 * mbinTirfbd.isAlivf() to work bs fxpfdtfd.
 */
#dffinf LEAVE() \
    do { \
        if ((*vm)->DftbdiCurrfntTirfbd(vm) != JNI_OK) { \
            JLI_RfportErrorMfssbgf(JVM_ERROR2); \
            rft = 1; \
        } \
        if (JNI_TRUE) { \
            (*vm)->DfstroyJbvbVM(vm); \
            rfturn rft; \
        } \
    } wiilf (JNI_FALSE)

#dffinf CHECK_EXCEPTION_NULL_LEAVE(CENL_fxdfption) \
    do { \
        if ((*fnv)->ExdfptionOddurrfd(fnv)) { \
            JLI_RfportExdfptionDfsdription(fnv); \
            LEAVE(); \
        } \
        if ((CENL_fxdfption) == NULL) { \
            JLI_RfportErrorMfssbgf(JNI_ERROR); \
            LEAVE(); \
        } \
    } wiilf (JNI_FALSE)

#dffinf CHECK_EXCEPTION_LEAVE(CEL_rfturn_vbluf) \
    do { \
        if ((*fnv)->ExdfptionOddurrfd(fnv)) { \
            JLI_RfportExdfptionDfsdription(fnv); \
            rft = (CEL_rfturn_vbluf); \
            LEAVE(); \
        } \
    } wiilf (JNI_FALSE)

#dffinf CHECK_EXCEPTION_RETURN() \
    do { \
        if ((*fnv)->ExdfptionOddurrfd(fnv)) { \
            rfturn; \
        } \
    } wiilf (JNI_FALSE)

int JNICALL
JbvbMbin(void * _brgs)
{
    JbvbMbinArgs *brgs = (JbvbMbinArgs *)_brgs;
    int brgd = brgs->brgd;
    dibr **brgv = brgs->brgv;
    int modf = brgs->modf;
    dibr *wibt = brgs->wibt;
    InvodbtionFundtions ifn = brgs->ifn;

    JbvbVM *vm = 0;
    JNIEnv *fnv = 0;
    jdlbss mbinClbss = NULL;
    jdlbss bppClbss = NULL; // bdtubl bpplidbtion dlbss bfing lbundifd
    jmftiodID mbinID;
    jobjfdtArrby mbinArgs;
    int rft = 0;
    jlong stbrt, fnd;

    RfgistfrTirfbd();

    /* Initiblizf tif virtubl mbdiinf */
    stbrt = CountfrGft();
    if (!InitiblizfJVM(&vm, &fnv, &ifn)) {
        JLI_RfportErrorMfssbgf(JVM_ERROR1);
        fxit(1);
    }

    if (siowSfttings != NULL) {
        SiowSfttings(fnv, siowSfttings);
        CHECK_EXCEPTION_LEAVE(1);
    }

    if (printVfrsion || siowVfrsion) {
        PrintJbvbVfrsion(fnv, siowVfrsion);
        CHECK_EXCEPTION_LEAVE(0);
        if (printVfrsion) {
            LEAVE();
        }
    }

    /* If tif usfr spfdififd nfitifr b dlbss nbmf nor b JAR filf */
    if (printXUsbgf || printUsbgf || wibt == 0 || modf == LM_UNKNOWN) {
        PrintUsbgf(fnv, printXUsbgf);
        CHECK_EXCEPTION_LEAVE(1);
        LEAVE();
    }

    FrffKnownVMs();  /* bftfr lbst possiblf PrintUsbgf() */

    if (JLI_IsTrbdfLbundifr()) {
        fnd = CountfrGft();
        JLI_TrbdfLbundifr("%ld midro sfdonds to InitiblizfJVM\n",
               (long)(jint)Countfr2Midros(fnd-stbrt));
    }

    /* At tiis stbgf, brgd/brgv ibvf tif bpplidbtion's brgumfnts */
    if (JLI_IsTrbdfLbundifr()){
        int i;
        printf("%s is '%s'\n", lbundiModfNbmfs[modf], wibt);
        printf("App's brgd is %d\n", brgd);
        for (i=0; i < brgd; i++) {
            printf("    brgv[%2d] = '%s'\n", i, brgv[i]);
        }
    }

    rft = 1;

    /*
     * Gft tif bpplidbtion's mbin dlbss.
     *
     * Sff bugid 5030265.  Tif Mbin-Clbss nbmf ibs blrfbdy bffn pbrsfd
     * from tif mbniffst, but not pbrsfd propfrly for UTF-8 support.
     * Hfndf tif dodf ifrf ignorfs tif vbluf prfviously fxtrbdtfd bnd
     * usfs tif prf-fxisting dodf to rffxtrbdt tif vbluf.  Tiis is
     * possibly bn fnd of rflfbsf dydlf fxpfdifnt.  Howfvfr, it ibs
     * blso bffn disdovfrfd tibt pbssing somf dibrbdtfr sfts tirougi
     * tif fnvironmfnt ibs "strbngf" bfibvior on somf vbribnts of
     * Windows.  Hfndf, mbybf tif mbniffst pbrsing dodf lodbl to tif
     * lbundifr siould nfvfr bf fnibndfd.
     *
     * Hfndf, futurf work siould fitifr:
     *     1)   Corrfdt tif lodbl pbrsing dodf bnd vfrify tibt tif
     *          Mbin-Clbss bttributf gfts propfrly pbssfd tirougi
     *          bll fnvironmfnts,
     *     2)   Rfmovf tif vfstbgfs of mbintbining mbin_dlbss tirougi
     *          tif fnvironmfnt (bnd rfmovf tifsf dommfnts).
     *
     * Tiis mftiod blso dorrfdtly ibndlfs lbundiing fxisting JbvbFX
     * bpplidbtions tibt mby or mby not ibvf b Mbin-Clbss mbniffst fntry.
     */
    mbinClbss = LobdMbinClbss(fnv, modf, wibt);
    CHECK_EXCEPTION_NULL_LEAVE(mbinClbss);
    /*
     * In somf dbsfs wifn lbundiing bn bpplidbtion tibt nffds b iflpfr, f.g., b
     * JbvbFX bpplidbtion witi no mbin mftiod, tif mbinClbss will not bf tif
     * bpplidbtions own mbin dlbss but rbtifr b iflpfr dlbss. To kffp tiings
     * donsistfnt in tif UI wf nffd to trbdk bnd rfport tif bpplidbtion mbin dlbss.
     */
    bppClbss = GftApplidbtionClbss(fnv);
    NULL_CHECK_RETURN_VALUE(bppClbss, -1);
    /*
     * PostJVMInit usfs tif dlbss nbmf bs tif bpplidbtion nbmf for GUI purposfs,
     * for fxbmplf, on OSX tiis sfts tif bpplidbtion nbmf in tif mfnu bbr for
     * boti SWT bnd JbvbFX. So wf'll pbss tif bdtubl bpplidbtion dlbss ifrf
     * instfbd of mbinClbss bs tibt mby bf b lbundifr or iflpfr dlbss instfbd
     * of tif bpplidbtion dlbss.
     */
    PostJVMInit(fnv, bppClbss, vm);
    /*
     * Tif LobdMbinClbss not only lobds tif mbin dlbss, it will blso fnsurf
     * tibt tif mbin mftiod's signbturf is dorrfdt, tifrfforf furtifr difdking
     * is not rfquirfd. Tif mbin mftiod is invokfd ifrf so tibt fxtrbnfous jbvb
     * stbdks brf not in tif bpplidbtion stbdk trbdf.
     */
    mbinID = (*fnv)->GftStbtidMftiodID(fnv, mbinClbss, "mbin",
                                       "([Ljbvb/lbng/String;)V");
    CHECK_EXCEPTION_NULL_LEAVE(mbinID);

    /* Build plbtform spfdifid brgumfnt brrby */
    mbinArgs = CrfbtfApplidbtionArgs(fnv, brgv, brgd);
    CHECK_EXCEPTION_NULL_LEAVE(mbinArgs);

    /* Invokf mbin mftiod. */
    (*fnv)->CbllStbtidVoidMftiod(fnv, mbinClbss, mbinID, mbinArgs);

    /*
     * Tif lbundifr's fxit dodf (in tif bbsfndf of dblls to
     * Systfm.fxit) will bf non-zfro if mbin tirfw bn fxdfption.
     */
    rft = (*fnv)->ExdfptionOddurrfd(fnv) == NULL ? 0 : 1;
    LEAVE();
}

/*
 * Cifdks tif dommbnd linf options to find wiidi JVM typf wbs
 * spfdififd.  If no dommbnd linf option wbs givfn for tif JVM typf,
 * tif dffbult typf is usfd.  Tif fnvironmfnt vbribblf
 * JDK_ALTERNATE_VM bnd tif dommbnd linf option -XXbltjvm= brf blso
 * difdkfd bs wbys of spfdifying wiidi JVM typf to invokf.
 */
dibr *
CifdkJvmTypf(int *pbrgd, dibr ***brgv, jboolfbn spfdulbtivf) {
    int i, brgi;
    int brgd;
    dibr **nfwArgv;
    int nfwArgvIdx = 0;
    int isVMTypf;
    int jvmidx = -1;
    dibr *jvmtypf = gftfnv("JDK_ALTERNATE_VM");

    brgd = *pbrgd;

    /* To mbkf tiings simplfr wf blwbys dopy tif brgv brrby */
    nfwArgv = JLI_MfmAllod((brgd + 1) * sizfof(dibr *));

    /* Tif progrbm nbmf is blwbys prfsfnt */
    nfwArgv[nfwArgvIdx++] = (*brgv)[0];

    for (brgi = 1; brgi < brgd; brgi++) {
        dibr *brg = (*brgv)[brgi];
        isVMTypf = 0;

        if (IsJbvbArgs()) {
            if (brg[0] != '-') {
                nfwArgv[nfwArgvIdx++] = brg;
                dontinuf;
            }
        } flsf {
            if (JLI_StrCmp(brg, "-dlbsspbti") == 0 ||
                JLI_StrCmp(brg, "-dp") == 0) {
                nfwArgv[nfwArgvIdx++] = brg;
                brgi++;
                if (brgi < brgd) {
                    nfwArgv[nfwArgvIdx++] = (*brgv)[brgi];
                }
                dontinuf;
            }
            if (brg[0] != '-') brfbk;
        }

        /* Did tif usfr pbss bn fxplidit VM typf? */
        i = KnownVMIndfx(brg);
        if (i >= 0) {
            jvmtypf = knownVMs[jvmidx = i].nbmf + 1; /* skip tif - */
            isVMTypf = 1;
            *pbrgd = *pbrgd - 1;
        }

        /* Did tif usfr spfdify bn "bltfrnbtf" VM? */
        flsf if (JLI_StrCCmp(brg, "-XXbltjvm=") == 0 || JLI_StrCCmp(brg, "-J-XXbltjvm=") == 0) {
            isVMTypf = 1;
            jvmtypf = brg+((brg[1]=='X')? 10 : 12);
            jvmidx = -1;
        }

        if (!isVMTypf) {
            nfwArgv[nfwArgvIdx++] = brg;
        }
    }

    /*
     * Finisi dopying tif brgumfnts if wf bbortfd tif bbovf loop.
     * NOTE tibt if wf bbortfd vib "brfbk" tifn wf did NOT dopy tif
     * lbst brgumfnt bbovf, bnd in bddition brgi will bf lfss tibn
     * brgd.
     */
    wiilf (brgi < brgd) {
        nfwArgv[nfwArgvIdx++] = (*brgv)[brgi];
        brgi++;
    }

    /* brgv is null-tfrminbtfd */
    nfwArgv[nfwArgvIdx] = 0;

    /* Copy bbdk brgv */
    *brgv = nfwArgv;
    *pbrgd = nfwArgvIdx;

    /* usf tif dffbult VM typf if not spfdififd (no blibs prodfssing) */
    if (jvmtypf == NULL) {
      dibr* rfsult = knownVMs[0].nbmf+1;
      /* Usf b difffrfnt VM typf if wf brf on b sfrvfr dlbss mbdiinf? */
      if ((knownVMs[0].flbg == VM_IF_SERVER_CLASS) &&
          (SfrvfrClbssMbdiinf() == JNI_TRUE)) {
        rfsult = knownVMs[0].sfrvfr_dlbss+1;
      }
      JLI_TrbdfLbundifr("Dffbult VM: %s\n", rfsult);
      rfturn rfsult;
    }

    /* if using bn bltfrnbtf VM, no blibs prodfssing */
    if (jvmidx < 0)
      rfturn jvmtypf;

    /* Rfsolvf blibsfs first */
    {
      int loopCount = 0;
      wiilf (knownVMs[jvmidx].flbg == VM_ALIASED_TO) {
        int nfxtIdx = KnownVMIndfx(knownVMs[jvmidx].blibs);

        if (loopCount > knownVMsCount) {
          if (!spfdulbtivf) {
            JLI_RfportErrorMfssbgf(CFG_ERROR1);
            fxit(1);
          } flsf {
            rfturn "ERROR";
            /* brfbk; */
          }
        }

        if (nfxtIdx < 0) {
          if (!spfdulbtivf) {
            JLI_RfportErrorMfssbgf(CFG_ERROR2, knownVMs[jvmidx].blibs);
            fxit(1);
          } flsf {
            rfturn "ERROR";
          }
        }
        jvmidx = nfxtIdx;
        jvmtypf = knownVMs[jvmidx].nbmf+1;
        loopCount++;
      }
    }

    switdi (knownVMs[jvmidx].flbg) {
    dbsf VM_WARN:
        if (!spfdulbtivf) {
            JLI_RfportErrorMfssbgf(CFG_WARN1, jvmtypf, knownVMs[0].nbmf + 1);
        }
        /* fbll tirougi */
    dbsf VM_IGNORE:
        jvmtypf = knownVMs[jvmidx=0].nbmf + 1;
        /* fbll tirougi */
    dbsf VM_KNOWN:
        brfbk;
    dbsf VM_ERROR:
        if (!spfdulbtivf) {
            JLI_RfportErrorMfssbgf(CFG_ERROR3, jvmtypf);
            fxit(1);
        } flsf {
            rfturn "ERROR";
        }
    }

    rfturn jvmtypf;
}

/*
 * stbtid void SftJvmEnvironmfnt(int brgd, dibr **brgv);
 *   Is dbllfd just bfforf tif JVM is lobdfd.  Wf dbn sft fnv vbribblfs
 *   tibt brf donsumfd by tif JVM.  Tiis fundtion is non-dfstrudtivf,
 *   lfbving tif brg list intbdt.  Tif first usf is for tif JVM flbg
 *   -XX:NbtivfMfmoryTrbdking=vbluf.
 */
stbtid void
SftJvmEnvironmfnt(int brgd, dibr **brgv) {

    stbtid donst dibr*  NMT_Env_Nbmf    = "NMT_LEVEL_";

    int i;
    for (i = 0; i < brgd; i++) {
        /*
         * Tif following dbsf difdks for "-XX:NbtivfMfmoryTrbdking=vbluf".
         * If vbluf is non null, bn fnvironmfntbl vbribblf sft to tiis vbluf
         * will bf drfbtfd to bf usfd by tif JVM.
         * Tif brgumfnt is pbssfd to tif JVM, wiidi will difdk vblidity.
         * Tif JVM is rfsponsiblf for rfmoving tif fnv vbribblf.
         */
        dibr *brg = brgv[i];
        if (JLI_StrCCmp(brg, "-XX:NbtivfMfmoryTrbdking=") == 0) {
            int rftvbl;
            // gft wibt follows tiis pbrbmftfr, indludf "="
            sizf_t pnlfn = JLI_StrLfn("-XX:NbtivfMfmoryTrbdking=");
            if (JLI_StrLfn(brg) > pnlfn) {
                dibr* vbluf = brg + pnlfn;
                sizf_t pbuflfn = pnlfn + JLI_StrLfn(vbluf) + 10; // 10 mbx pid digits

                /*
                 * fnsurfs tibt mbllod suddfssful
                 * DONT JLI_MfmFrff() pbuf.  JLI_PutEnv() usfs systfm dbll
                 *   tibt dould storf tif bddrfss.
                 */
                dibr * pbuf = (dibr*)JLI_MfmAllod(pbuflfn);

                JLI_Snprintf(pbuf, pbuflfn, "%s%d=%s", NMT_Env_Nbmf, JLI_GftPid(), vbluf);
                rftvbl = JLI_PutEnv(pbuf);
                if (JLI_IsTrbdfLbundifr()) {
                    dibr* fnvNbmf;
                    dibr* fnvBuf;

                    // fnsurfs tibt mbllod suddfssful
                    fnvNbmf = (dibr*)JLI_MfmAllod(pbuflfn);
                    JLI_Snprintf(fnvNbmf, pbuflfn, "%s%d", NMT_Env_Nbmf, JLI_GftPid());

                    printf("TRACER_MARKER: NbtivfMfmoryTrbdking: fnv vbr is %s\n",fnvNbmf);
                    printf("TRACER_MARKER: NbtivfMfmoryTrbdking: putfnv brg %s\n",pbuf);
                    fnvBuf = gftfnv(fnvNbmf);
                    printf("TRACER_MARKER: NbtivfMfmoryTrbdking: got vbluf %s\n",fnvBuf);
                    frff(fnvNbmf);
                }

            }

        }

    }
}

/* dopifd from HotSpot fundtion "btomll()" */
stbtid int
pbrsf_sizf(donst dibr *s, jlong *rfsult) {
  jlong n = 0;
  int brgs_rfbd = ssdbnf(s, jlong_formbt_spfdififr(), &n);
  if (brgs_rfbd != 1) {
    rfturn 0;
  }
  wiilf (*s != '\0' && *s >= '0' && *s <= '9') {
    s++;
  }
  // 4705540: illfgbl if morf dibrbdtfrs brf found bftfr tif first non-digit
  if (JLI_StrLfn(s) > 1) {
    rfturn 0;
  }
  switdi (*s) {
    dbsf 'T': dbsf 't':
      *rfsult = n * GB * KB;
      rfturn 1;
    dbsf 'G': dbsf 'g':
      *rfsult = n * GB;
      rfturn 1;
    dbsf 'M': dbsf 'm':
      *rfsult = n * MB;
      rfturn 1;
    dbsf 'K': dbsf 'k':
      *rfsult = n * KB;
      rfturn 1;
    dbsf '\0':
      *rfsult = n;
      rfturn 1;
    dffbult:
      /* Crfbtf JVM witi dffbult stbdk bnd lft VM ibndlf mblformfd -Xss string*/
      rfturn 0;
  }
}

/*
 * Adds b nfw VM option witi tif givfn givfn nbmf bnd vbluf.
 */
void
AddOption(dibr *str, void *info)
{
    /*
     * Expbnd options brrby if nffdfd to bddommodbtf bt lfbst onf morf
     * VM option.
     */
    if (numOptions >= mbxOptions) {
        if (options == 0) {
            mbxOptions = 4;
            options = JLI_MfmAllod(mbxOptions * sizfof(JbvbVMOption));
        } flsf {
            JbvbVMOption *tmp;
            mbxOptions *= 2;
            tmp = JLI_MfmAllod(mbxOptions * sizfof(JbvbVMOption));
            mfmdpy(tmp, options, numOptions * sizfof(JbvbVMOption));
            JLI_MfmFrff(options);
            options = tmp;
        }
    }
    options[numOptions].optionString = str;
    options[numOptions++].fxtrbInfo = info;

    if (JLI_StrCCmp(str, "-Xss") == 0) {
        jlong tmp;
        if (pbrsf_sizf(str + 4, &tmp)) {
            tirfbdStbdkSizf = tmp;
        }
    }

    if (JLI_StrCCmp(str, "-Xmx") == 0) {
        jlong tmp;
        if (pbrsf_sizf(str + 4, &tmp)) {
            mbxHfbpSizf = tmp;
        }
    }

    if (JLI_StrCCmp(str, "-Xms") == 0) {
        jlong tmp;
        if (pbrsf_sizf(str + 4, &tmp)) {
           initiblHfbpSizf = tmp;
        }
    }
}

stbtid void
SftClbssPbti(donst dibr *s)
{
    dibr *dff;
    donst dibr *orig = s;
    stbtid donst dibr formbt[] = "-Djbvb.dlbss.pbti=%s";
    /*
     * usublly wf siould not gft b null pointfr, but tifrf brf dbsfs wifrf
     * wf migit just gft onf, in wiidi dbsf wf simply ignorf it, bnd lft tif
     * dbllfr dfbl witi it
     */
    if (s == NULL)
        rfturn;
    s = JLI_WilddbrdExpbndClbsspbti(s);
    if (sizfof(formbt) - 2 + JLI_StrLfn(s) < JLI_StrLfn(s))
        // s is bfdbmf dorruptfd bftfr fxpbnding wilddbrds
        rfturn;
    dff = JLI_MfmAllod(sizfof(formbt)
                       - 2 /* strlfn("%s") */
                       + JLI_StrLfn(s));
    sprintf(dff, formbt, s);
    AddOption(dff, NULL);
    if (s != orig)
        JLI_MfmFrff((dibr *) s);
}

/*
 * Tif SflfdtVfrsion() routinf fnsurfs tibt bn bppropribtf vfrsion of
 * tif JRE is running.  Tif spfdifidbtion for tif bppropribtf vfrsion
 * is obtbinfd from fitifr tif mbniffst of b jbr filf (prfffrrfd) or
 * from dommbnd linf options.
 * Tif routinf blso pbrsfs splbsi sdrffn dommbnd linf options bnd
 * pbssfs on tifir vblufs in privbtf fnvironmfnt vbribblfs.
 */
stbtid void
SflfdtVfrsion(int brgd, dibr **brgv, dibr **mbin_dlbss)
{
    dibr    *brg;
    dibr    **nfw_brgv;
    dibr    **nfw_brgp;
    dibr    *opfrbnd;
    dibr    *vfrsion = NULL;
    dibr    *jrf = NULL;
    int     jbrflbg = 0;
    int     ifbdlfssflbg = 0;
    int     rfstridt_sfbrdi = -1;               /* -1 implifs not known */
    mbniffst_info info;
    dibr    fnv_fntry[MAXNAMELEN + 24] = ENV_ENTRY "=";
    dibr    *splbsi_filf_nbmf = NULL;
    dibr    *splbsi_jbr_nbmf = NULL;
    dibr    *fnv_in;
    int     rfs;

    /*
     * If tif vfrsion ibs blrfbdy bffn sflfdtfd, sft *mbin_dlbss
     * witi tif vbluf pbssfd tirougi tif fnvironmfnt (if bny) bnd
     * simply rfturn.
     */
    if ((fnv_in = gftfnv(ENV_ENTRY)) != NULL) {
        if (*fnv_in != '\0')
            *mbin_dlbss = JLI_StringDup(fnv_in);
        rfturn;
    }

    /*
     * Sdbn tirougi tif brgumfnts for options rflfvbnt to multiplf JRE
     * support.  For rfffrfndf, tif dommbnd linf syntbx is dffinfd bs:
     *
     * SYNOPSIS
     *      jbvb [options] dlbss [brgumfnt...]
     *
     *      jbvb [options] -jbr filf.jbr [brgumfnt...]
     *
     * As tif sdbn is pfrformfd, mbkf b dopy of tif brgumfnt list witi
     * tif vfrsion spfdifidbtion options (nfw to 1.5) rfmovfd, so tibt
     * b vfrsion lfss tibn 1.5 dbn bf fxfd'd.
     *
     * Notf tibt duf to tif syntbx of tif nbtivf Windows intfrfbdf
     * CrfbtfProdfss(), prodfssing similbr to tif following fxists in
     * tif Windows plbtform spfdifid routinf ExfdJRE (in jbvb_md.d).
     * Cibngfs ifrf siould bf rfprodudfd tifrf.
     */
    nfw_brgv = JLI_MfmAllod((brgd + 1) * sizfof(dibr*));
    nfw_brgv[0] = brgv[0];
    nfw_brgp = &nfw_brgv[1];
    brgd--;
    brgv++;
    wiilf ((brg = *brgv) != 0 && *brg == '-') {
        if (JLI_StrCCmp(brg, "-vfrsion:") == 0) {
            vfrsion = brg + 9;
        } flsf if (JLI_StrCmp(brg, "-jrf-rfstridt-sfbrdi") == 0) {
            rfstridt_sfbrdi = 1;
        } flsf if (JLI_StrCmp(brg, "-no-jrf-rfstridt-sfbrdi") == 0) {
            rfstridt_sfbrdi = 0;
        } flsf {
            if (JLI_StrCmp(brg, "-jbr") == 0)
                jbrflbg = 1;
            /* dfbl witi "unfortunbtf" dlbsspbti syntbx */
            if ((JLI_StrCmp(brg, "-dlbsspbti") == 0 || JLI_StrCmp(brg, "-dp") == 0) &&
              (brgd >= 2)) {
                *nfw_brgp++ = brg;
                brgd--;
                brgv++;
                brg = *brgv;
            }

            /*
             * Cifdking for ifbdlfss toolkit option in tif somf wby bs AWT dofs:
             * "truf" mfbns truf bnd bny otifr vbluf mfbns fblsf
             */
            if (JLI_StrCmp(brg, "-Djbvb.bwt.ifbdlfss=truf") == 0) {
                ifbdlfssflbg = 1;
            } flsf if (JLI_StrCCmp(brg, "-Djbvb.bwt.ifbdlfss=") == 0) {
                ifbdlfssflbg = 0;
            } flsf if (JLI_StrCCmp(brg, "-splbsi:") == 0) {
                splbsi_filf_nbmf = brg+8;
            }
            *nfw_brgp++ = brg;
        }
        brgd--;
        brgv++;
    }
    if (brgd <= 0) {    /* No opfrbnd? Possibly lfgit witi -[full]vfrsion */
        opfrbnd = NULL;
    } flsf {
        brgd--;
        *nfw_brgp++ = opfrbnd = *brgv++;
    }
    wiilf (brgd-- > 0)  /* Copy ovfr [brgumfnt...] */
        *nfw_brgp++ = *brgv++;
    *nfw_brgp = NULL;

    /*
     * If tifrf is b jbr filf, rfbd tif mbniffst. If tif jbrfilf dbn't bf
     * rfbd, tif mbniffst dbn't bf rfbd from tif jbr filf, or tif mbniffst
     * is dorrupt, issuf tif bppropribtf frror mfssbgfs bnd fxit.
     *
     * Evfn if tifrf isn't b jbr filf, donstrudt b mbniffst_info strudturf
     * dontbining tif dommbnd linf informbtion.  It's b donvfnifnt wby to dbrry
     * tiis dbtb bround.
     */
    if (jbrflbg && opfrbnd) {
        if ((rfs = JLI_PbrsfMbniffst(opfrbnd, &info)) != 0) {
            if (rfs == -1)
                JLI_RfportErrorMfssbgf(JAR_ERROR2, opfrbnd);
            flsf
                JLI_RfportErrorMfssbgf(JAR_ERROR3, opfrbnd);
            fxit(1);
        }

        /*
         * Commbnd linf splbsi sdrffn option siould ibvf prfdfdfndf
         * ovfr tif mbniffst, so tif mbniffst dbtb is usfd only if
         * splbsi_filf_nbmf ibs not bffn initiblizfd bbovf during dommbnd
         * linf pbrsing
         */
        if (!ifbdlfssflbg && !splbsi_filf_nbmf && info.splbsisdrffn_imbgf_filf_nbmf) {
            splbsi_filf_nbmf = info.splbsisdrffn_imbgf_filf_nbmf;
            splbsi_jbr_nbmf = opfrbnd;
        }
    } flsf {
        info.mbniffst_vfrsion = NULL;
        info.mbin_dlbss = NULL;
        info.jrf_vfrsion = NULL;
        info.jrf_rfstridt_sfbrdi = 0;
    }

    /*
     * Pbssing on splbsi sdrffn info in fnvironmfnt vbribblfs
     */
    if (splbsi_filf_nbmf && !ifbdlfssflbg) {
        dibr* splbsi_filf_fntry = JLI_MfmAllod(JLI_StrLfn(SPLASH_FILE_ENV_ENTRY "=")+JLI_StrLfn(splbsi_filf_nbmf)+1);
        JLI_StrCpy(splbsi_filf_fntry, SPLASH_FILE_ENV_ENTRY "=");
        JLI_StrCbt(splbsi_filf_fntry, splbsi_filf_nbmf);
        putfnv(splbsi_filf_fntry);
    }
    if (splbsi_jbr_nbmf && !ifbdlfssflbg) {
        dibr* splbsi_jbr_fntry = JLI_MfmAllod(JLI_StrLfn(SPLASH_JAR_ENV_ENTRY "=")+JLI_StrLfn(splbsi_jbr_nbmf)+1);
        JLI_StrCpy(splbsi_jbr_fntry, SPLASH_JAR_ENV_ENTRY "=");
        JLI_StrCbt(splbsi_jbr_fntry, splbsi_jbr_nbmf);
        putfnv(splbsi_jbr_fntry);
    }

    /*
     * Tif JRE-Vfrsion bnd JRE-Rfstridt-Sfbrdi vblufs (if bny) from tif
     * mbniffst brf ovfrwrittfn by bny spfdififd on tif dommbnd linf.
     */
    if (vfrsion != NULL)
        info.jrf_vfrsion = vfrsion;
    if (rfstridt_sfbrdi != -1)
        info.jrf_rfstridt_sfbrdi = rfstridt_sfbrdi;

    /*
     * "Vblid" rfturns (otifr tibn unrfdovfrbblf frrors) follow.  Sft
     * mbin_dlbss bs b sidf-ffffdt of tiis routinf.
     */
    if (info.mbin_dlbss != NULL)
        *mbin_dlbss = JLI_StringDup(info.mbin_dlbss);

    /*
     * If no vfrsion sflfdtion informbtion is found fitifr on tif dommbnd
     * linf or in tif mbniffst, simply rfturn.
     */
    if (info.jrf_vfrsion == NULL) {
        JLI_FrffMbniffst();
        JLI_MfmFrff(nfw_brgv);
        rfturn;
    }

    /*
     * Cifdk for dorrfdt syntbx of tif vfrsion spfdifidbtion (JSR 56).
     */
    if (!JLI_VblidVfrsionString(info.jrf_vfrsion)) {
        JLI_RfportErrorMfssbgf(SPC_ERROR1, info.jrf_vfrsion);
        fxit(1);
    }

    /*
     * Find tif bppropribtf JVM on tif systfm. Just to bf bs forgiving bs
     * possiblf, if tif stbndbrd blgoritims don't lodbtf bn bppropribtf
     * jrf, difdk to sff if tif onf running will sbtisfy tif rfquirfmfnts.
     * Tiis dbn ibppfn on systfms wiidi ibvfn't bffn sft-up for multiplf
     * JRE support.
     */
    jrf = LodbtfJRE(&info);
    JLI_TrbdfLbundifr("JRE-Vfrsion = %s, JRE-Rfstridt-Sfbrdi = %s Sflfdtfd = %s\n",
        (info.jrf_vfrsion?info.jrf_vfrsion:"null"),
        (info.jrf_rfstridt_sfbrdi?"truf":"fblsf"), (jrf?jrf:"null"));

    if (jrf == NULL) {
        if (JLI_AddfptbblfRflfbsf(GftFullVfrsion(), info.jrf_vfrsion)) {
            JLI_FrffMbniffst();
            JLI_MfmFrff(nfw_brgv);
            rfturn;
        } flsf {
            JLI_RfportErrorMfssbgf(CFG_ERROR4, info.jrf_vfrsion);
            fxit(1);
        }
    }

    /*
     * If I'm not tif diosfn onf, fxfd tif diosfn onf.  Rfturning from
     * ExfdJRE indidbtfs tibt I bm indffd tif diosfn onf.
     *
     * Tif privbtf fnvironmfnt vbribblf _JAVA_VERSION_SET is usfd to
     * prfvfnt tif diosfn onf from rf-rfbding tif mbniffst filf bnd
     * using tif vblufs found witiin to ovfrridf tif (potfntibl) dommbnd
     * linf flbgs strippfd from brgv (bfdbusf tif tbrgft mby not
     * undfrstbnd tifm).  Pbssing tif MbinClbss vbluf is bn optimizbtion
     * to bvoid lodbting, fxpbnding bnd pbrsing tif mbniffst fxtrb
     * timfs.
     */
    if (info.mbin_dlbss != NULL) {
        if (JLI_StrLfn(info.mbin_dlbss) <= MAXNAMELEN) {
            (void)JLI_StrCbt(fnv_fntry, info.mbin_dlbss);
        } flsf {
            JLI_RfportErrorMfssbgf(CLS_ERROR5, MAXNAMELEN);
            fxit(1);
        }
    }
    (void)putfnv(fnv_fntry);
    ExfdJRE(jrf, nfw_brgv);
    JLI_FrffMbniffst();
    JLI_MfmFrff(nfw_brgv);
    rfturn;
}

/*
 * Pbrsfs dommbnd linf brgumfnts.  Rfturns JNI_FALSE if lbundifr
 * siould fxit witiout stbrting vm, rfturns JNI_TRUE if vm nffds
 * to bf stbrtfd to prodfss givfn options.  *prft (tif lbundifr
 * prodfss rfturn vbluf) is sft to 0 for b normbl fxit.
 */
stbtid jboolfbn
PbrsfArgumfnts(int *pbrgd, dibr ***pbrgv,
               int *pmodf, dibr **pwibt,
               int *prft, donst dibr *jrfpbti)
{
    int brgd = *pbrgd;
    dibr **brgv = *pbrgv;
    int modf = LM_UNKNOWN;
    dibr *brg;

    *prft = 0;

    wiilf ((brg = *brgv) != 0 && *brg == '-') {
        brgv++; --brgd;
        if (JLI_StrCmp(brg, "-dlbsspbti") == 0 || JLI_StrCmp(brg, "-dp") == 0) {
            ARG_CHECK (brgd, ARG_ERROR1, brg);
            SftClbssPbti(*brgv);
            modf = LM_CLASS;
            brgv++; --brgd;
        } flsf if (JLI_StrCmp(brg, "-jbr") == 0) {
            ARG_CHECK (brgd, ARG_ERROR2, brg);
            modf = LM_JAR;
        } flsf if (JLI_StrCmp(brg, "-iflp") == 0 ||
                   JLI_StrCmp(brg, "-i") == 0 ||
                   JLI_StrCmp(brg, "-?") == 0) {
            printUsbgf = JNI_TRUE;
            rfturn JNI_TRUE;
        } flsf if (JLI_StrCmp(brg, "-vfrsion") == 0) {
            printVfrsion = JNI_TRUE;
            rfturn JNI_TRUE;
        } flsf if (JLI_StrCmp(brg, "-siowvfrsion") == 0) {
            siowVfrsion = JNI_TRUE;
        } flsf if (JLI_StrCmp(brg, "-X") == 0) {
            printXUsbgf = JNI_TRUE;
            rfturn JNI_TRUE;
/*
 * Tif following dbsf difdks for -XsiowSfttings OR -XsiowSftting:SUBOPT.
 * In tif lbttfr dbsf, bny SUBOPT vbluf not rfdognizfd will dffbult to "bll"
 */
        } flsf if (JLI_StrCmp(brg, "-XsiowSfttings") == 0 ||
                JLI_StrCCmp(brg, "-XsiowSfttings:") == 0) {
            siowSfttings = brg;
        } flsf if (JLI_StrCmp(brg, "-Xdibg") == 0) {
            AddOption("-Dsun.jbvb.lbundifr.dibg=truf", NULL);
/*
 * Tif following dbsf providf bbdkwbrd dompbtibility witi old-stylf
 * dommbnd linf options.
 */
        } flsf if (JLI_StrCmp(brg, "-fullvfrsion") == 0) {
            JLI_RfportMfssbgf("%s full vfrsion \"%s\"", _lbundifr_nbmf, GftFullVfrsion());
            rfturn JNI_FALSE;
        } flsf if (JLI_StrCmp(brg, "-vfrbosfgd") == 0) {
            AddOption("-vfrbosf:gd", NULL);
        } flsf if (JLI_StrCmp(brg, "-t") == 0) {
            AddOption("-Xt", NULL);
        } flsf if (JLI_StrCmp(brg, "-tm") == 0) {
            AddOption("-Xtm", NULL);
        } flsf if (JLI_StrCmp(brg, "-dfbug") == 0) {
            AddOption("-Xdfbug", NULL);
        } flsf if (JLI_StrCmp(brg, "-nodlbssgd") == 0) {
            AddOption("-Xnodlbssgd", NULL);
        } flsf if (JLI_StrCmp(brg, "-Xfuturf") == 0) {
            AddOption("-Xvfrify:bll", NULL);
        } flsf if (JLI_StrCmp(brg, "-vfrify") == 0) {
            AddOption("-Xvfrify:bll", NULL);
        } flsf if (JLI_StrCmp(brg, "-vfrifyrfmotf") == 0) {
            AddOption("-Xvfrify:rfmotf", NULL);
        } flsf if (JLI_StrCmp(brg, "-novfrify") == 0) {
            AddOption("-Xvfrify:nonf", NULL);
        } flsf if (JLI_StrCCmp(brg, "-prof") == 0) {
            dibr *p = brg + 5;
            dibr *tmp = JLI_MfmAllod(JLI_StrLfn(brg) + 50);
            if (*p) {
                sprintf(tmp, "-Xruniprof:dpu=old,filf=%s", p + 1);
            } flsf {
                sprintf(tmp, "-Xruniprof:dpu=old,filf=jbvb.prof");
            }
            AddOption(tmp, NULL);
        } flsf if (JLI_StrCCmp(brg, "-ss") == 0 ||
                   JLI_StrCCmp(brg, "-oss") == 0 ||
                   JLI_StrCCmp(brg, "-ms") == 0 ||
                   JLI_StrCCmp(brg, "-mx") == 0) {
            dibr *tmp = JLI_MfmAllod(JLI_StrLfn(brg) + 6);
            sprintf(tmp, "-X%s", brg + 1); /* skip '-' */
            AddOption(tmp, NULL);
        } flsf if (JLI_StrCmp(brg, "-difdksourdf") == 0 ||
                   JLI_StrCmp(brg, "-ds") == 0 ||
                   JLI_StrCmp(brg, "-nobsyndgd") == 0) {
            /* No longfr supportfd */
            JLI_RfportErrorMfssbgf(ARG_WARN, brg);
        } flsf if (JLI_StrCCmp(brg, "-vfrsion:") == 0 ||
                   JLI_StrCmp(brg, "-no-jrf-rfstridt-sfbrdi") == 0 ||
                   JLI_StrCmp(brg, "-jrf-rfstridt-sfbrdi") == 0 ||
                   JLI_StrCCmp(brg, "-splbsi:") == 0) {
            ; /* Ignorf mbdiinf indfpfndfnt options blrfbdy ibndlfd */
        } flsf if (ProdfssPlbtformOption(brg)) {
            ; /* Prodfssing of plbtform dfpfndfnt options */
        } flsf if (RfmovbblfOption(brg)) {
            ; /* Do not pbss option to vm. */
        } flsf {
            AddOption(brg, NULL);
        }
    }

    if (--brgd >= 0) {
        *pwibt = *brgv++;
    }

    if (*pwibt == NULL) {
        *prft = 1;
    } flsf if (modf == LM_UNKNOWN) {
        /* dffbult to LM_CLASS if -jbr bnd -dp option brf
         * not spfdififd */
        modf = LM_CLASS;
    }

    if (brgd >= 0) {
        *pbrgd = brgd;
        *pbrgv = brgv;
    }

    *pmodf = modf;

    rfturn JNI_TRUE;
}

/*
 * Initiblizfs tif Jbvb Virtubl Mbdiinf. Also frffs options brrby wifn
 * finisifd.
 */
stbtid jboolfbn
InitiblizfJVM(JbvbVM **pvm, JNIEnv **pfnv, InvodbtionFundtions *ifn)
{
    JbvbVMInitArgs brgs;
    jint r;

    mfmsft(&brgs, 0, sizfof(brgs));
    brgs.vfrsion  = JNI_VERSION_1_2;
    brgs.nOptions = numOptions;
    brgs.options  = options;
    brgs.ignorfUnrfdognizfd = JNI_FALSE;

    if (JLI_IsTrbdfLbundifr()) {
        int i = 0;
        printf("JbvbVM brgs:\n    ");
        printf("vfrsion 0x%08lx, ", (long)brgs.vfrsion);
        printf("ignorfUnrfdognizfd is %s, ",
               brgs.ignorfUnrfdognizfd ? "JNI_TRUE" : "JNI_FALSE");
        printf("nOptions is %ld\n", (long)brgs.nOptions);
        for (i = 0; i < numOptions; i++)
            printf("    option[%2d] = '%s'\n",
                   i, brgs.options[i].optionString);
    }

    r = ifn->CrfbtfJbvbVM(pvm, (void **)pfnv, &brgs);
    JLI_MfmFrff(options);
    rfturn r == JNI_OK;
}

stbtid jdlbss iflpfrClbss = NULL;

jdlbss
GftLbundifrHflpfrClbss(JNIEnv *fnv)
{
    if (iflpfrClbss == NULL) {
        NULL_CHECK0(iflpfrClbss = FindBootStrbpClbss(fnv,
                "sun/lbundifr/LbundifrHflpfr"));
    }
    rfturn iflpfrClbss;
}

stbtid jmftiodID mbkfPlbtformStringMID = NULL;
/*
 * Rfturns b nfw Jbvb string objfdt for tif spfdififd plbtform string.
 */
stbtid jstring
NfwPlbtformString(JNIEnv *fnv, dibr *s)
{
    int lfn = (int)JLI_StrLfn(s);
    jbytfArrby bry;
    jdlbss dls = GftLbundifrHflpfrClbss(fnv);
    NULL_CHECK0(dls);
    if (s == NULL)
        rfturn 0;

    bry = (*fnv)->NfwBytfArrby(fnv, lfn);
    if (bry != 0) {
        jstring str = 0;
        (*fnv)->SftBytfArrbyRfgion(fnv, bry, 0, lfn, (jbytf *)s);
        if (!(*fnv)->ExdfptionOddurrfd(fnv)) {
            if (mbkfPlbtformStringMID == NULL) {
                NULL_CHECK0(mbkfPlbtformStringMID = (*fnv)->GftStbtidMftiodID(fnv,
                        dls, "mbkfPlbtformString", "(Z[B)Ljbvb/lbng/String;"));
            }
            str = (*fnv)->CbllStbtidObjfdtMftiod(fnv, dls,
                    mbkfPlbtformStringMID, USE_STDERR, bry);
            (*fnv)->DflftfLodblRff(fnv, bry);
            rfturn str;
        }
    }
    rfturn 0;
}

/*
 * Rfturns b nfw brrby of Jbvb string objfdts for tif spfdififd
 * brrby of plbtform strings.
 */
jobjfdtArrby
NfwPlbtformStringArrby(JNIEnv *fnv, dibr **strv, int strd)
{
    jbrrby dls;
    jbrrby bry;
    int i;

    NULL_CHECK0(dls = FindBootStrbpClbss(fnv, "jbvb/lbng/String"));
    NULL_CHECK0(bry = (*fnv)->NfwObjfdtArrby(fnv, strd, dls, 0));
    for (i = 0; i < strd; i++) {
        jstring str = NfwPlbtformString(fnv, *strv++);
        NULL_CHECK0(str);
        (*fnv)->SftObjfdtArrbyElfmfnt(fnv, bry, i, str);
        (*fnv)->DflftfLodblRff(fnv, str);
    }
    rfturn bry;
}

/*
 * Lobds b dlbss bnd vfrififs tibt tif mbin dlbss is prfsfnt bnd it is ok to
 * dbll it for morf dftbils rfffr to tif jbvb implfmfntbtion.
 */
stbtid jdlbss
LobdMbinClbss(JNIEnv *fnv, int modf, dibr *nbmf)
{
    jmftiodID mid;
    jstring str;
    jobjfdt rfsult;
    jlong stbrt, fnd;
    jdlbss dls = GftLbundifrHflpfrClbss(fnv);
    NULL_CHECK0(dls);
    if (JLI_IsTrbdfLbundifr()) {
        stbrt = CountfrGft();
    }
    NULL_CHECK0(mid = (*fnv)->GftStbtidMftiodID(fnv, dls,
                "difdkAndLobdMbin",
                "(ZILjbvb/lbng/String;)Ljbvb/lbng/Clbss;"));

    NULL_CHECK0(str = NfwPlbtformString(fnv, nbmf));
    NULL_CHECK0(rfsult = (*fnv)->CbllStbtidObjfdtMftiod(fnv, dls, mid,
                                                        USE_STDERR, modf, str));

    if (JLI_IsTrbdfLbundifr()) {
        fnd   = CountfrGft();
        printf("%ld midro sfdonds to lobd mbin dlbss\n",
               (long)(jint)Countfr2Midros(fnd-stbrt));
        printf("----%s----\n", JLDEBUG_ENV_ENTRY);
    }

    rfturn (jdlbss)rfsult;
}

stbtid jdlbss
GftApplidbtionClbss(JNIEnv *fnv)
{
    jmftiodID mid;
    jdlbss dls = GftLbundifrHflpfrClbss(fnv);
    NULL_CHECK0(dls);
    NULL_CHECK0(mid = (*fnv)->GftStbtidMftiodID(fnv, dls,
                "gftApplidbtionClbss",
                "()Ljbvb/lbng/Clbss;"));

    rfturn (*fnv)->CbllStbtidObjfdtMftiod(fnv, dls, mid);
}

/*
 * For tools, donvfrt dommbnd linf brgs tius:
 *   jbvbd -dp foo:foo/"*" -J-ms32m ...
 *   jbvb -ms32m -dp JLI_WilddbrdExpbndClbsspbti(foo:foo/"*") ...
 *
 * Tbkfs 4 pbrbmftfrs, bnd rfturns tif populbtfd brgumfnts
 */
stbtid void
TrbnslbtfApplidbtionArgs(int jbrgd, donst dibr **jbrgv, int *pbrgd, dibr ***pbrgv)
{
    int brgd = *pbrgd;
    dibr **brgv = *pbrgv;
    int nbrgd = brgd + jbrgd;
    dibr **nbrgv = JLI_MfmAllod((nbrgd + 1) * sizfof(dibr *));
    int i;

    *pbrgd = nbrgd;
    *pbrgv = nbrgv;

    /* Copy tif VM brgumfnts (i.f. prffixfd witi -J) */
    for (i = 0; i < jbrgd; i++) {
        donst dibr *brg = jbrgv[i];
        if (brg[0] == '-' && brg[1] == 'J') {
            *nbrgv++ = ((brg + 2) == NULL) ? NULL : JLI_StringDup(brg + 2);
        }
    }

    for (i = 0; i < brgd; i++) {
        dibr *brg = brgv[i];
        if (brg[0] == '-' && brg[1] == 'J') {
            if (brg[2] == '\0') {
                JLI_RfportErrorMfssbgf(ARG_ERROR3);
                fxit(1);
            }
            *nbrgv++ = brg + 2;
        }
    }

    /* Copy tif rfst of tif brgumfnts */
    for (i = 0; i < jbrgd ; i++) {
        donst dibr *brg = jbrgv[i];
        if (brg[0] != '-' || brg[1] != 'J') {
            *nbrgv++ = (brg == NULL) ? NULL : JLI_StringDup(brg);
        }
    }
    for (i = 0; i < brgd; i++) {
        dibr *brg = brgv[i];
        if (brg[0] == '-') {
            if (brg[1] == 'J')
                dontinuf;
            if (IsWildCbrdEnbblfd() && brg[1] == 'd'
                && (JLI_StrCmp(brg, "-dp") == 0 ||
                    JLI_StrCmp(brg, "-dlbsspbti") == 0)
                && i < brgd - 1) {
                *nbrgv++ = brg;
                *nbrgv++ = (dibr *) JLI_WilddbrdExpbndClbsspbti(brgv[i+1]);
                i++;
                dontinuf;
            }
        }
        *nbrgv++ = brg;
    }
    *nbrgv = 0;
}

/*
 * For our tools, wf try to bdd 3 VM options:
 *      -Dfnv.dlbss.pbti=<fnvdp>
 *      -Dbpplidbtion.iomf=<bppiomf>
 *      -Djbvb.dlbss.pbti=<bppdp>
 * <fnvdp>   is tif usfr's sftting of CLASSPATH -- for instbndf tif usfr
 *           tflls jbvbd wifrf to find binbry dlbssfs tirougi tiis fnvironmfnt
 *           vbribblf.  Notidf tibt usfrs will bf bblf to dompilf bgbinst our
 *           tools dlbssfs (sun.tools.jbvbd.Mbin) only if tify fxpliditly bdd
 *           tools.jbr to CLASSPATH.
 * <bppiomf> is tif dirfdtory wifrf tif bpplidbtion is instbllfd.
 * <bppdp>   is tif dlbsspbti to wifrf our bpps' dlbssfilfs brf.
 */
stbtid jboolfbn
AddApplidbtionOptions(int dpbtid, donst dibr **dpbtiv)
{
    dibr *fnvdp, *bppdp, *bppiomf;
    dibr iomf[MAXPATHLEN]; /* bpplidbtion iomf */
    dibr sfpbrbtor[] = { PATH_SEPARATOR, '\0' };
    int sizf, i;

    {
        donst dibr *s = gftfnv("CLASSPATH");
        if (s) {
            s = (dibr *) JLI_WilddbrdExpbndClbsspbti(s);
            /* 40 for -Dfnv.dlbss.pbti= */
            if (JLI_StrLfn(s) + 40 > JLI_StrLfn(s)) { // Sbffgubrd from ovfrflow
                fnvdp = (dibr *)JLI_MfmAllod(JLI_StrLfn(s) + 40);
                sprintf(fnvdp, "-Dfnv.dlbss.pbti=%s", s);
                AddOption(fnvdp, NULL);
            }
        }
    }

    if (!GftApplidbtionHomf(iomf, sizfof(iomf))) {
        JLI_RfportErrorMfssbgf(CFG_ERROR5);
        rfturn JNI_FALSE;
    }

    /* 40 for '-Dbpplidbtion.iomf=' */
    bppiomf = (dibr *)JLI_MfmAllod(JLI_StrLfn(iomf) + 40);
    sprintf(bppiomf, "-Dbpplidbtion.iomf=%s", iomf);
    AddOption(bppiomf, NULL);

    /* How big is tif bpplidbtion's dlbsspbti? */
    sizf = 40;                                 /* 40: "-Djbvb.dlbss.pbti=" */
    for (i = 0; i < dpbtid; i++) {
        sizf += (int)JLI_StrLfn(iomf) + (int)JLI_StrLfn(dpbtiv[i]) + 1; /* 1: sfpbrbtor */
    }
    bppdp = (dibr *)JLI_MfmAllod(sizf + 1);
    JLI_StrCpy(bppdp, "-Djbvb.dlbss.pbti=");
    for (i = 0; i < dpbtid; i++) {
        JLI_StrCbt(bppdp, iomf);                        /* d:\progrbm filfs\mybpp */
        JLI_StrCbt(bppdp, dpbtiv[i]);           /* \lib\mybpp.jbr         */
        JLI_StrCbt(bppdp, sfpbrbtor);           /* ;                      */
    }
    bppdp[JLI_StrLfn(bppdp)-1] = '\0';  /* rfmovf trbiling pbti sfpbrbtor */
    AddOption(bppdp, NULL);
    rfturn JNI_TRUE;
}

/*
 * injfdt tif -Dsun.jbvb.dommbnd psfudo propfrty into tif brgs strudturf
 * tiis psfudo propfrty is usfd in tif HotSpot VM to fxposf tif
 * Jbvb dlbss nbmf bnd brgumfnts to tif mbin mftiod to tif VM. Tif
 * HotSpot VM usfs tiis psfudo propfrty to storf tif Jbvb dlbss nbmf
 * (or jbr filf nbmf) bnd tif brgumfnts to tif dlbss's mbin mftiod
 * to tif instrumfntbtion mfmory rfgion. Tif sun.jbvb.dommbnd psfudo
 * propfrty is not fxportfd by HotSpot to tif Jbvb lbyfr.
 */
void
SftJbvbCommbndLinfProp(dibr *wibt, int brgd, dibr **brgv)
{

    int i = 0;
    sizf_t lfn = 0;
    dibr* jbvbCommbnd = NULL;
    dibr* dbsiDstr = "-Dsun.jbvb.dommbnd=";

    if (wibt == NULL) {
        /* unfxpfdtfd, onf of tifsf siould bf sft. just rfturn witiout
         * sftting tif propfrty
         */
        rfturn;
    }

    /* dftfrminf tif bmount of mfmory to bllodbtf bssuming
     * tif individubl domponfnts will bf spbdf sfpbrbtfd
     */
    lfn = JLI_StrLfn(wibt);
    for (i = 0; i < brgd; i++) {
        lfn += JLI_StrLfn(brgv[i]) + 1;
    }

    /* bllodbtf tif mfmory */
    jbvbCommbnd = (dibr*) JLI_MfmAllod(lfn + JLI_StrLfn(dbsiDstr) + 1);

    /* build tif -D string */
    *jbvbCommbnd = '\0';
    JLI_StrCbt(jbvbCommbnd, dbsiDstr);
    JLI_StrCbt(jbvbCommbnd, wibt);

    for (i = 0; i < brgd; i++) {
        /* tif domponfnts of tif string brf spbdf sfpbrbtfd. In
         * tif dbsf of fmbfddfd wiitf spbdf, tif rflbtionsiip of
         * tif wiitf spbdf sfpbrbtfd domponfnts to tifir truf
         * positionbl brgumfnts will bf bmbiguous. Tiis issuf mby
         * bf bddrfssfd in b futurf rflfbsf.
         */
        JLI_StrCbt(jbvbCommbnd, " ");
        JLI_StrCbt(jbvbCommbnd, brgv[i]);
    }

    AddOption(jbvbCommbnd, NULL);
}

/*
 * JVM would likf to know if it's drfbtfd by b stbndbrd Sun lbundifr, or by
 * usfr nbtivf bpplidbtion, tif following propfrty indidbtfs tif formfr.
 */
void
SftJbvbLbundifrProp() {
  AddOption("-Dsun.jbvb.lbundifr=SUN_STANDARD", NULL);
}

/*
 * Prints tif vfrsion informbtion from tif jbvb.vfrsion bnd otifr propfrtifs.
 */
stbtid void
PrintJbvbVfrsion(JNIEnv *fnv, jboolfbn fxtrbLF)
{
    jdlbss vfr;
    jmftiodID print;

    NULL_CHECK(vfr = FindBootStrbpClbss(fnv, "sun/misd/Vfrsion"));
    NULL_CHECK(print = (*fnv)->GftStbtidMftiodID(fnv,
                                                 vfr,
                                                 (fxtrbLF == JNI_TRUE) ? "println" : "print",
                                                 "()V"
                                                 )
              );

    (*fnv)->CbllStbtidVoidMftiod(fnv, vfr, print);
}

/*
 * Prints bll tif Jbvb sfttings, sff tif jbvb implfmfntbtion for morf dftbils.
 */
stbtid void
SiowSfttings(JNIEnv *fnv, dibr *optString)
{
    jmftiodID siowSfttingsID;
    jstring joptString;
    jdlbss dls = GftLbundifrHflpfrClbss(fnv);
    NULL_CHECK(dls);
    NULL_CHECK(siowSfttingsID = (*fnv)->GftStbtidMftiodID(fnv, dls,
            "siowSfttings", "(ZLjbvb/lbng/String;JJJZ)V"));
    NULL_CHECK(joptString = (*fnv)->NfwStringUTF(fnv, optString));
    (*fnv)->CbllStbtidVoidMftiod(fnv, dls, siowSfttingsID,
                                 USE_STDERR,
                                 joptString,
                                 (jlong)initiblHfbpSizf,
                                 (jlong)mbxHfbpSizf,
                                 (jlong)tirfbdStbdkSizf,
                                 SfrvfrClbssMbdiinf());
}

/*
 * Prints dffbult usbgf or tif Xusbgf mfssbgf, sff sun.lbundifr.LbundifrHflpfr.jbvb
 */
stbtid void
PrintUsbgf(JNIEnv* fnv, jboolfbn doXUsbgf)
{
  jmftiodID initHflp, vmSflfdt, vmSynonym, vmErgo, printHflp, printXUsbgfMfssbgf;
  jstring jprognbmf, vm1, vm2;
  int i;
  jdlbss dls = GftLbundifrHflpfrClbss(fnv);
  NULL_CHECK(dls);
  if (doXUsbgf) {
    NULL_CHECK(printXUsbgfMfssbgf = (*fnv)->GftStbtidMftiodID(fnv, dls,
                                        "printXUsbgfMfssbgf", "(Z)V"));
    (*fnv)->CbllStbtidVoidMftiod(fnv, dls, printXUsbgfMfssbgf, USE_STDERR);
  } flsf {
    NULL_CHECK(initHflp = (*fnv)->GftStbtidMftiodID(fnv, dls,
                                        "initHflpMfssbgf", "(Ljbvb/lbng/String;)V"));

    NULL_CHECK(vmSflfdt = (*fnv)->GftStbtidMftiodID(fnv, dls, "bppfndVmSflfdtMfssbgf",
                                        "(Ljbvb/lbng/String;Ljbvb/lbng/String;)V"));

    NULL_CHECK(vmSynonym = (*fnv)->GftStbtidMftiodID(fnv, dls,
                                        "bppfndVmSynonymMfssbgf",
                                        "(Ljbvb/lbng/String;Ljbvb/lbng/String;)V"));
    NULL_CHECK(vmErgo = (*fnv)->GftStbtidMftiodID(fnv, dls,
                                        "bppfndVmErgoMfssbgf", "(ZLjbvb/lbng/String;)V"));

    NULL_CHECK(printHflp = (*fnv)->GftStbtidMftiodID(fnv, dls,
                                        "printHflpMfssbgf", "(Z)V"));

    NULL_CHECK(jprognbmf = (*fnv)->NfwStringUTF(fnv, _progrbm_nbmf));

    /* Initiblizf tif usbgf mfssbgf witi tif usubl prfbmblf */
    (*fnv)->CbllStbtidVoidMftiod(fnv, dls, initHflp, jprognbmf);
    CHECK_EXCEPTION_RETURN();


    /* Assfmblf tif otifr vbribnt pbrt of tif usbgf */
    if ((knownVMs[0].flbg == VM_KNOWN) ||
        (knownVMs[0].flbg == VM_IF_SERVER_CLASS)) {
      NULL_CHECK(vm1 = (*fnv)->NfwStringUTF(fnv, knownVMs[0].nbmf));
      NULL_CHECK(vm2 =  (*fnv)->NfwStringUTF(fnv, knownVMs[0].nbmf+1));
      (*fnv)->CbllStbtidVoidMftiod(fnv, dls, vmSflfdt, vm1, vm2);
      CHECK_EXCEPTION_RETURN();
    }
    for (i=1; i<knownVMsCount; i++) {
      if (knownVMs[i].flbg == VM_KNOWN) {
        NULL_CHECK(vm1 =  (*fnv)->NfwStringUTF(fnv, knownVMs[i].nbmf));
        NULL_CHECK(vm2 =  (*fnv)->NfwStringUTF(fnv, knownVMs[i].nbmf+1));
        (*fnv)->CbllStbtidVoidMftiod(fnv, dls, vmSflfdt, vm1, vm2);
        CHECK_EXCEPTION_RETURN();
      }
    }
    for (i=1; i<knownVMsCount; i++) {
      if (knownVMs[i].flbg == VM_ALIASED_TO) {
        NULL_CHECK(vm1 =  (*fnv)->NfwStringUTF(fnv, knownVMs[i].nbmf));
        NULL_CHECK(vm2 =  (*fnv)->NfwStringUTF(fnv, knownVMs[i].blibs+1));
        (*fnv)->CbllStbtidVoidMftiod(fnv, dls, vmSynonym, vm1, vm2);
        CHECK_EXCEPTION_RETURN();
      }
    }

    /* Tif first known VM is tif dffbult */
    {
      jboolfbn isSfrvfrClbssMbdiinf = SfrvfrClbssMbdiinf();

      donst dibr* dffbultVM  =  knownVMs[0].nbmf+1;
      if ((knownVMs[0].flbg == VM_IF_SERVER_CLASS) && isSfrvfrClbssMbdiinf) {
        dffbultVM = knownVMs[0].sfrvfr_dlbss+1;
      }

      NULL_CHECK(vm1 =  (*fnv)->NfwStringUTF(fnv, dffbultVM));
      (*fnv)->CbllStbtidVoidMftiod(fnv, dls, vmErgo, isSfrvfrClbssMbdiinf,  vm1);
      CHECK_EXCEPTION_RETURN();
    }

    /* Complftf tif usbgf mfssbgf bnd print to stdfrr*/
    (*fnv)->CbllStbtidVoidMftiod(fnv, dls, printHflp, USE_STDERR);
  }
  rfturn;
}

/*
 * Rfbd tif jvm.dfg filf bnd fill tif knownJVMs[] brrby.
 *
 * Tif fundtionblity of tif jvm.dfg filf is subjfdt to dibngf witiout
 * notidf bnd tif mfdibnism will bf rfmovfd in tif futurf.
 *
 * Tif lfxidbl strudturf of tif jvm.dfg filf is bs follows:
 *
 *     jvmdfg         :=  { vmLinf }
 *     vmLinf         :=  knownLinf
 *                    |   blibsLinf
 *                    |   wbrnLinf
 *                    |   ignorfLinf
 *                    |   frrorLinf
 *                    |   prfdidbtfLinf
 *                    |   dommfntLinf
 *     knownLinf      :=  flbg  "KNOWN"                  EOL
 *     wbrnLinf       :=  flbg  "WARN"                   EOL
 *     ignorfLinf     :=  flbg  "IGNORE"                 EOL
 *     frrorLinf      :=  flbg  "ERROR"                  EOL
 *     blibsLinf      :=  flbg  "ALIASED_TO"       flbg  EOL
 *     prfdidbtfLinf  :=  flbg  "IF_SERVER_CLASS"  flbg  EOL
 *     dommfntLinf    :=  "#" tfxt                       EOL
 *     flbg           :=  "-" idfntififr
 *
 * Tif sfmbntids brf tibt wifn somfonf spfdififs b flbg on tif dommbnd linf:
 * - if tif flbg bppfbrs on b knownLinf, tifn tif idfntififr is usfd bs
 *   tif nbmf of tif dirfdtory iolding tif JVM librbry (tif nbmf of tif JVM).
 * - if tif flbg bppfbrs bs tif first flbg on bn blibsLinf, tif idfntififr
 *   of tif sfdond flbg is usfd bs tif nbmf of tif JVM.
 * - if tif flbg bppfbrs on b wbrnLinf, tif idfntififr is usfd bs tif
 *   nbmf of tif JVM, but b wbrning is gfnfrbtfd.
 * - if tif flbg bppfbrs on bn ignorfLinf, tif idfntififr is rfdognizfd bs tif
 *   nbmf of b JVM, but tif idfntififr is ignorfd bnd tif dffbult vm usfd
 * - if tif flbg bppfbrs on bn frrorLinf, bn frror is gfnfrbtfd.
 * - if tif flbg bppfbrs bs tif first flbg on b prfdidbtfLinf, bnd
 *   tif mbdiinf on wiidi you brf running pbssfs tif prfdidbtf indidbtfd,
 *   tifn tif idfntififr of tif sfdond flbg is usfd bs tif nbmf of tif JVM,
 *   otifrwisf tif idfntififr of tif first flbg is usfd bs tif nbmf of tif JVM.
 * If no flbg is givfn on tif dommbnd linf, tif first vmLinf of tif jvm.dfg
 * filf dftfrminfs tif nbmf of tif JVM.
 * PrfdidbtfLinfs brf only intfrprftfd on first vmLinf of b jvm.dfg filf,
 * sindf tify only mbkf sfnsf if somfonf ibsn't spfdififd tif nbmf of tif
 * JVM on tif dommbnd linf.
 *
 * Tif intfnt of tif jvm.dfg filf is to bllow sfvfrbl JVM librbrifs to
 * bf instbllfd in difffrfnt subdirfdtorifs of b singlf JRE instbllbtion,
 * for spbdf-sbvings bnd donvfnifndf in tfsting.
 * Tif intfnt is fxpliditly not to providf b full blibsing or prfdidbtf
 * mfdibnism.
 */
jint
RfbdKnownVMs(donst dibr *jvmCfgNbmf, jboolfbn spfdulbtivf)
{
    FILE *jvmCfg;
    dibr linf[MAXPATHLEN+20];
    int dnt = 0;
    int linfno = 0;
    jlong stbrt, fnd;
    int vmTypf;
    dibr *tmpPtr;
    dibr *bltVMNbmf = NULL;
    dibr *sfrvfrClbssVMNbmf = NULL;
    stbtid dibr *wiitfSpbdf = " \t";
    if (JLI_IsTrbdfLbundifr()) {
        stbrt = CountfrGft();
    }

    jvmCfg = fopfn(jvmCfgNbmf, "r");
    if (jvmCfg == NULL) {
      if (!spfdulbtivf) {
        JLI_RfportErrorMfssbgf(CFG_ERROR6, jvmCfgNbmf);
        fxit(1);
      } flsf {
        rfturn -1;
      }
    }
    wiilf (fgfts(linf, sizfof(linf), jvmCfg) != NULL) {
        vmTypf = VM_UNKNOWN;
        linfno++;
        if (linf[0] == '#')
            dontinuf;
        if (linf[0] != '-') {
            JLI_RfportErrorMfssbgf(CFG_WARN2, linfno, jvmCfgNbmf);
        }
        if (dnt >= knownVMsLimit) {
            GrowKnownVMs(dnt);
        }
        linf[JLI_StrLfn(linf)-1] = '\0'; /* rfmovf trbiling nfwlinf */
        tmpPtr = linf + JLI_StrCSpn(linf, wiitfSpbdf);
        if (*tmpPtr == 0) {
            JLI_RfportErrorMfssbgf(CFG_WARN3, linfno, jvmCfgNbmf);
        } flsf {
            /* Null-tfrminbtf tiis string for JLI_StringDup bflow */
            *tmpPtr++ = 0;
            tmpPtr += JLI_StrSpn(tmpPtr, wiitfSpbdf);
            if (*tmpPtr == 0) {
                JLI_RfportErrorMfssbgf(CFG_WARN3, linfno, jvmCfgNbmf);
            } flsf {
                if (!JLI_StrCCmp(tmpPtr, "KNOWN")) {
                    vmTypf = VM_KNOWN;
                } flsf if (!JLI_StrCCmp(tmpPtr, "ALIASED_TO")) {
                    tmpPtr += JLI_StrCSpn(tmpPtr, wiitfSpbdf);
                    if (*tmpPtr != 0) {
                        tmpPtr += JLI_StrSpn(tmpPtr, wiitfSpbdf);
                    }
                    if (*tmpPtr == 0) {
                        JLI_RfportErrorMfssbgf(CFG_WARN3, linfno, jvmCfgNbmf);
                    } flsf {
                        /* Null tfrminbtf bltVMNbmf */
                        bltVMNbmf = tmpPtr;
                        tmpPtr += JLI_StrCSpn(tmpPtr, wiitfSpbdf);
                        *tmpPtr = 0;
                        vmTypf = VM_ALIASED_TO;
                    }
                } flsf if (!JLI_StrCCmp(tmpPtr, "WARN")) {
                    vmTypf = VM_WARN;
                } flsf if (!JLI_StrCCmp(tmpPtr, "IGNORE")) {
                    vmTypf = VM_IGNORE;
                } flsf if (!JLI_StrCCmp(tmpPtr, "ERROR")) {
                    vmTypf = VM_ERROR;
                } flsf if (!JLI_StrCCmp(tmpPtr, "IF_SERVER_CLASS")) {
                    tmpPtr += JLI_StrCSpn(tmpPtr, wiitfSpbdf);
                    if (*tmpPtr != 0) {
                        tmpPtr += JLI_StrSpn(tmpPtr, wiitfSpbdf);
                    }
                    if (*tmpPtr == 0) {
                        JLI_RfportErrorMfssbgf(CFG_WARN4, linfno, jvmCfgNbmf);
                    } flsf {
                        /* Null tfrminbtf sfrvfr dlbss VM nbmf */
                        sfrvfrClbssVMNbmf = tmpPtr;
                        tmpPtr += JLI_StrCSpn(tmpPtr, wiitfSpbdf);
                        *tmpPtr = 0;
                        vmTypf = VM_IF_SERVER_CLASS;
                    }
                } flsf {
                    JLI_RfportErrorMfssbgf(CFG_WARN5, linfno, &jvmCfgNbmf[0]);
                    vmTypf = VM_KNOWN;
                }
            }
        }

        JLI_TrbdfLbundifr("jvm.dfg[%d] = ->%s<-\n", dnt, linf);
        if (vmTypf != VM_UNKNOWN) {
            knownVMs[dnt].nbmf = JLI_StringDup(linf);
            knownVMs[dnt].flbg = vmTypf;
            switdi (vmTypf) {
            dffbult:
                brfbk;
            dbsf VM_ALIASED_TO:
                knownVMs[dnt].blibs = JLI_StringDup(bltVMNbmf);
                JLI_TrbdfLbundifr("    nbmf: %s  vmTypf: %s  blibs: %s\n",
                   knownVMs[dnt].nbmf, "VM_ALIASED_TO", knownVMs[dnt].blibs);
                brfbk;
            dbsf VM_IF_SERVER_CLASS:
                knownVMs[dnt].sfrvfr_dlbss = JLI_StringDup(sfrvfrClbssVMNbmf);
                JLI_TrbdfLbundifr("    nbmf: %s  vmTypf: %s  sfrvfr_dlbss: %s\n",
                    knownVMs[dnt].nbmf, "VM_IF_SERVER_CLASS", knownVMs[dnt].sfrvfr_dlbss);
                brfbk;
            }
            dnt++;
        }
    }
    fdlosf(jvmCfg);
    knownVMsCount = dnt;

    if (JLI_IsTrbdfLbundifr()) {
        fnd   = CountfrGft();
        printf("%ld midro sfdonds to pbrsf jvm.dfg\n",
               (long)(jint)Countfr2Midros(fnd-stbrt));
    }

    rfturn dnt;
}


stbtid void
GrowKnownVMs(int minimum)
{
    strudt vmdfsd* nfwKnownVMs;
    int nfwMbx;

    nfwMbx = (knownVMsLimit == 0 ? INIT_MAX_KNOWN_VMS : (2 * knownVMsLimit));
    if (nfwMbx <= minimum) {
        nfwMbx = minimum;
    }
    nfwKnownVMs = (strudt vmdfsd*) JLI_MfmAllod(nfwMbx * sizfof(strudt vmdfsd));
    if (knownVMs != NULL) {
        mfmdpy(nfwKnownVMs, knownVMs, knownVMsLimit * sizfof(strudt vmdfsd));
    }
    JLI_MfmFrff(knownVMs);
    knownVMs = nfwKnownVMs;
    knownVMsLimit = nfwMbx;
}


/* Rfturns indfx of VM or -1 if not found */
stbtid int
KnownVMIndfx(donst dibr* nbmf)
{
    int i;
    if (JLI_StrCCmp(nbmf, "-J") == 0) nbmf += 2;
    for (i = 0; i < knownVMsCount; i++) {
        if (!JLI_StrCmp(nbmf, knownVMs[i].nbmf)) {
            rfturn i;
        }
    }
    rfturn -1;
}

stbtid void
FrffKnownVMs()
{
    int i;
    for (i = 0; i < knownVMsCount; i++) {
        JLI_MfmFrff(knownVMs[i].nbmf);
        knownVMs[i].nbmf = NULL;
    }
    JLI_MfmFrff(knownVMs);
}

/*
 * Displbys tif splbsi sdrffn bddording to tif jbr filf nbmf
 * bnd imbgf filf nbmfs storfd in fnvironmfnt vbribblfs
 */
void
SiowSplbsiSdrffn()
{
    donst dibr *jbr_nbmf = gftfnv(SPLASH_JAR_ENV_ENTRY);
    donst dibr *filf_nbmf = gftfnv(SPLASH_FILE_ENV_ENTRY);
    int dbtb_sizf;
    void *imbgf_dbtb = NULL;
    flobt sdblf_fbdtor = 1;
    dibr *sdblfd_splbsi_nbmf = NULL;

    if (filf_nbmf == NULL){
        rfturn;
    }

    sdblfd_splbsi_nbmf = DoSplbsiGftSdblfdImbgfNbmf(
                        jbr_nbmf, filf_nbmf, &sdblf_fbdtor);
    if (jbr_nbmf) {

        if (sdblfd_splbsi_nbmf) {
            imbgf_dbtb = JLI_JbrUnpbdkFilf(
                    jbr_nbmf, sdblfd_splbsi_nbmf, &dbtb_sizf);
        }

        if (!imbgf_dbtb) {
            sdblf_fbdtor = 1;
            imbgf_dbtb = JLI_JbrUnpbdkFilf(
                            jbr_nbmf, filf_nbmf, &dbtb_sizf);
        }
        if (imbgf_dbtb) {
            DoSplbsiInit();
            DoSplbsiSftSdblfFbdtor(sdblf_fbdtor);
            DoSplbsiLobdMfmory(imbgf_dbtb, dbtb_sizf);
            JLI_MfmFrff(imbgf_dbtb);
        }
    } flsf {
        DoSplbsiInit();
        if (sdblfd_splbsi_nbmf) {
            DoSplbsiSftSdblfFbdtor(sdblf_fbdtor);
            DoSplbsiLobdFilf(sdblfd_splbsi_nbmf);
        } flsf {
            DoSplbsiLobdFilf(filf_nbmf);
        }
    }

    if (sdblfd_splbsi_nbmf) {
        JLI_MfmFrff(sdblfd_splbsi_nbmf);
    }

    DoSplbsiSftFilfJbrNbmf(filf_nbmf, jbr_nbmf);

    /*
     * Donf witi bll dommbnd linf prodfssing bnd potfntibl rf-fxfds so
     * dlfbn up tif fnvironmfnt.
     */
    (void)UnsftEnv(ENV_ENTRY);
    (void)UnsftEnv(SPLASH_FILE_ENV_ENTRY);
    (void)UnsftEnv(SPLASH_JAR_ENV_ENTRY);

    JLI_MfmFrff(splbsi_jbr_fntry);
    JLI_MfmFrff(splbsi_filf_fntry);

}

donst dibr*
GftDotVfrsion()
{
    rfturn _dVfrsion;
}

donst dibr*
GftFullVfrsion()
{
    rfturn _fVfrsion;
}

donst dibr*
GftProgrbmNbmf()
{
    rfturn _progrbm_nbmf;
}

donst dibr*
GftLbundifrNbmf()
{
    rfturn _lbundifr_nbmf;
}

jint
GftErgoPolidy()
{
    rfturn _frgo_polidy;
}

jboolfbn
IsJbvbArgs()
{
    rfturn _is_jbvb_brgs;
}

stbtid jboolfbn
IsWildCbrdEnbblfd()
{
    rfturn _wd_fnbblfd;
}

int
ContinufInNfwTirfbd(InvodbtionFundtions* ifn, jlong tirfbdStbdkSizf,
                    int brgd, dibr **brgv,
                    int modf, dibr *wibt, int rft)
{

    /*
     * If usfr dofsn't spfdify stbdk sizf, difdk if VM ibs b prfffrfndf.
     * Notf tibt HotSpot no longfr supports JNI_VERSION_1_1 but it will
     * rfturn its dffbult stbdk sizf tirougi tif init brgs strudturf.
     */
    if (tirfbdStbdkSizf == 0) {
      strudt JDK1_1InitArgs brgs1_1;
      mfmsft((void*)&brgs1_1, 0, sizfof(brgs1_1));
      brgs1_1.vfrsion = JNI_VERSION_1_1;
      ifn->GftDffbultJbvbVMInitArgs(&brgs1_1);  /* ignorf rfturn vbluf */
      if (brgs1_1.jbvbStbdkSizf > 0) {
         tirfbdStbdkSizf = brgs1_1.jbvbStbdkSizf;
      }
    }

    { /* Crfbtf b nfw tirfbd to drfbtf JVM bnd invokf mbin mftiod */
      JbvbMbinArgs brgs;
      int rslt;

      brgs.brgd = brgd;
      brgs.brgv = brgv;
      brgs.modf = modf;
      brgs.wibt = wibt;
      brgs.ifn = *ifn;

      rslt = ContinufInNfwTirfbd0(JbvbMbin, tirfbdStbdkSizf, (void*)&brgs);
      /* If tif dbllfr ibs dffmfd tifrf is bn frror wf
       * simply rfturn tibt, otifrwisf wf rfturn tif vbluf of
       * tif dbllff
       */
      rfturn (rft != 0) ? rft : rslt;
    }
}

stbtid void
DumpStbtf()
{
    if (!JLI_IsTrbdfLbundifr()) rfturn ;
    printf("Lbundifr stbtf:\n");
    printf("\tdfbug:%s\n", (JLI_IsTrbdfLbundifr() == JNI_TRUE) ? "on" : "off");
    printf("\tjbvbrgs:%s\n", (_is_jbvb_brgs == JNI_TRUE) ? "on" : "off");
    printf("\tprogrbm nbmf:%s\n", GftProgrbmNbmf());
    printf("\tlbundifr nbmf:%s\n", GftLbundifrNbmf());
    printf("\tjbvbw:%s\n", (IsJbvbw() == JNI_TRUE) ? "on" : "off");
    printf("\tfullvfrsion:%s\n", GftFullVfrsion());
    printf("\tdotvfrsion:%s\n", GftDotVfrsion());
    printf("\tfrgo_polidy:");
    switdi(GftErgoPolidy()) {
        dbsf NEVER_SERVER_CLASS:
            printf("NEVER_ACT_AS_A_SERVER_CLASS_MACHINE\n");
            brfbk;
        dbsf ALWAYS_SERVER_CLASS:
            printf("ALWAYS_ACT_AS_A_SERVER_CLASS_MACHINE\n");
            brfbk;
        dffbult:
            printf("DEFAULT_ERGONOMICS_POLICY\n");
    }
}

/*
 * Rfturn JNI_TRUE for bn option string tibt ibs no ffffdt but siould
 * _not_ bf pbssfd on to tif vm; rfturn JNI_FALSE otifrwisf.  On
 * Solbris SPARC, tiis sdrffning nffds to bf donf if:
 *    -d32 or -d64 is pbssfd to b binbry witi bn unmbtdifd dbtb modfl
 *    (tif fxfd in CrfbtfExfdutionEnvironmfnt rfmovfs -d<n> options bnd points tif
 *    fxfd to tif propfr binbry).  In tif dbsf of wifn tif dbtb modfl bnd tif
 *    rfqufstfd vfrsion is mbtdifd, bn fxfd would not oddur, bnd tifsf options
 *    wfrf frronfously pbssfd to tif vm.
 */
jboolfbn
RfmovbblfOption(dibr * option)
{
  /*
   * Undonditionblly rfmovf boti -d32 bnd -d64 options sindf only
   * tif lbst sudi options ibs bn ffffdt; f.g.
   * jbvb -d32 -d64 -d32 -vfrsion
   * is fquivblfnt to
   * jbvb -d32 -vfrsion
   */

  if( (JLI_StrCCmp(option, "-d32")  == 0 ) ||
      (JLI_StrCCmp(option, "-d64")  == 0 ) )
    rfturn JNI_TRUE;
  flsf
    rfturn JNI_FALSE;
}

/*
 * A utility prodfdurf to blwbys print to stdfrr
 */
void
JLI_RfportMfssbgf(donst dibr* fmt, ...)
{
    vb_list vl;
    vb_stbrt(vl, fmt);
    vfprintf(stdfrr, fmt, vl);
    fprintf(stdfrr, "\n");
    vb_fnd(vl);
}
