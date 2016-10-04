/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

/*
 * Shbred source for 'jbvb' commbnd line tool.
 *
 * If JAVA_ARGS is defined, then bcts bs b lbuncher for bpplicbtions. For
 * instbnce, the JDK commbnd line tools such bs jbvbc bnd jbvbdoc (see
 * mbkefiles for more detbils) bre built with this progrbm.  Any brguments
 * prefixed with '-J' will be pbssed directly to the 'jbvb' commbnd.
 */

/*
 * One job of the lbuncher is to remove commbnd line options which the
 * vm does not understbnd bnd will not process.  These options include
 * options which select which style of vm is run (e.g. -client bnd
 * -server) bs well bs options which select the dbtb model to use.
 * Additionblly, for tools which invoke bn underlying vm "-J-foo"
 * options bre turned into "-foo" options to the vm.  This option
 * filtering is hbndled in b number of plbces in the lbuncher, some of
 * it in mbchine-dependent code.  In this file, the function
 * CheckJvmType removes vm style options bnd TrbnslbteApplicbtionArgs
 * removes "-J" prefixes.  The CrebteExecutionEnvironment function processes
 * bnd removes -d<n> options. On unix, there is b possibility thbt the running
 * dbtb model mby not mbtch to the desired dbtb model, in this cbse bn exec is
 * required to stbrt the desired model. If the dbtb models mbtch, then
 * PbrseArguments will remove the -d<n> flbgs. If the dbtb models do not mbtch
 * the CrebteExecutionEnviroment will remove the -d<n> flbgs.
 */


#include "jbvb.h"

/*
 * A NOTE TO DEVELOPERS: For performbnce rebsons it is importbnt thbt
 * the progrbm imbge rembin relbtively smbll until bfter SelectVersion
 * CrebteExecutionEnvironment hbve finished their possibly recursive
 * processing. Wbtch everything, but resist bll temptbtions to use Jbvb
 * interfbces.
 */

/* we blwbys print to stderr */
#define USE_STDERR JNI_TRUE

stbtic jboolebn printVersion = JNI_FALSE; /* print bnd exit */
stbtic jboolebn showVersion = JNI_FALSE;  /* print but continue */
stbtic jboolebn printUsbge = JNI_FALSE;   /* print bnd exit*/
stbtic jboolebn printXUsbge = JNI_FALSE;  /* print bnd exit*/
stbtic chbr     *showSettings = NULL;      /* print but continue */

stbtic const chbr *_progrbm_nbme;
stbtic const chbr *_lbuncher_nbme;
stbtic jboolebn _is_jbvb_brgs = JNI_FALSE;
stbtic const chbr *_fVersion;
stbtic const chbr *_dVersion;
stbtic jboolebn _wc_enbbled = JNI_FALSE;
stbtic jint _ergo_policy = DEFAULT_POLICY;

/*
 * Entries for splbsh screen environment vbribbles.
 * putenv is performed in SelectVersion. We need
 * them in memory until UnsetEnv, so they bre mbde stbtic
 * globbl instebd of buto locbl.
 */
stbtic chbr* splbsh_file_entry = NULL;
stbtic chbr* splbsh_jbr_entry = NULL;

/*
 * List of VM options to be specified when the VM is crebted.
 */
stbtic JbvbVMOption *options;
stbtic int numOptions, mbxOptions;

/*
 * Prototypes for functions internbl to lbuncher.
 */
stbtic void SetClbssPbth(const chbr *s);
stbtic void SelectVersion(int brgc, chbr **brgv, chbr **mbin_clbss);
stbtic void SetJvmEnvironment(int brgc, chbr **brgv);
stbtic jboolebn PbrseArguments(int *pbrgc, chbr ***pbrgv,
                               int *pmode, chbr **pwhbt,
                               int *pret, const chbr *jrepbth);
stbtic jboolebn InitiblizeJVM(JbvbVM **pvm, JNIEnv **penv,
                              InvocbtionFunctions *ifn);
stbtic jstring NewPlbtformString(JNIEnv *env, chbr *s);
stbtic jclbss LobdMbinClbss(JNIEnv *env, int mode, chbr *nbme);
stbtic jclbss GetApplicbtionClbss(JNIEnv *env);

stbtic void TrbnslbteApplicbtionArgs(int jbrgc, const chbr **jbrgv, int *pbrgc, chbr ***pbrgv);
stbtic jboolebn AddApplicbtionOptions(int cpbthc, const chbr **cpbthv);
stbtic void SetApplicbtionClbssPbth(const chbr**);

stbtic void PrintJbvbVersion(JNIEnv *env, jboolebn extrbLF);
stbtic void PrintUsbge(JNIEnv* env, jboolebn doXUsbge);
stbtic void ShowSettings(JNIEnv* env, chbr *optString);

stbtic void SetPbths(int brgc, chbr **brgv);

stbtic void DumpStbte();
stbtic jboolebn RemovbbleOption(chbr *option);

/* Mbximum supported entries from jvm.cfg. */
#define INIT_MAX_KNOWN_VMS      10

/* Vblues for vmdesc.flbg */
enum vmdesc_flbg {
    VM_UNKNOWN = -1,
    VM_KNOWN,
    VM_ALIASED_TO,
    VM_WARN,
    VM_ERROR,
    VM_IF_SERVER_CLASS,
    VM_IGNORE
};

struct vmdesc {
    chbr *nbme;
    int flbg;
    chbr *blibs;
    chbr *server_clbss;
};
stbtic struct vmdesc *knownVMs = NULL;
stbtic int knownVMsCount = 0;
stbtic int knownVMsLimit = 0;

stbtic void GrowKnownVMs();
stbtic int  KnownVMIndex(const chbr* nbme);
stbtic void FreeKnownVMs();
stbtic jboolebn IsWildCbrdEnbbled();

#define ARG_CHECK(AC_brg_count, AC_fbilure_messbge, AC_questionbble_brg) \
    do { \
        if (AC_brg_count < 1) { \
            JLI_ReportErrorMessbge(AC_fbilure_messbge, AC_questionbble_brg); \
            printUsbge = JNI_TRUE; \
            *pret = 1; \
            return JNI_TRUE; \
        } \
    } while (JNI_FALSE)

/*
 * Running Jbvb code in primordibl threbd cbused mbny problems. We will
 * crebte b new threbd to invoke JVM. See 6316197 for more informbtion.
 */
stbtic jlong threbdStbckSize    = 0;  /* stbck size of the new threbd */
stbtic jlong mbxHebpSize        = 0;  /* mbx hebp size */
stbtic jlong initiblHebpSize    = 0;  /* initbl hebp size */

/*
 * Entry point.
 */
int
JLI_Lbunch(int brgc, chbr ** brgv,              /* mbin brgc, brgc */
        int jbrgc, const chbr** jbrgv,          /* jbvb brgs */
        int bppclbssc, const chbr** bppclbssv,  /* bpp clbsspbth */
        const chbr* fullversion,                /* full version defined */
        const chbr* dotversion,                 /* dot version defined */
        const chbr* pnbme,                      /* progrbm nbme */
        const chbr* lnbme,                      /* lbuncher nbme */
        jboolebn jbvbbrgs,                      /* JAVA_ARGS */
        jboolebn cpwildcbrd,                    /* clbsspbth wildcbrd*/
        jboolebn jbvbw,                         /* windows-only jbvbw */
        jint ergo                               /* ergonomics clbss policy */
)
{
    int mode = LM_UNKNOWN;
    chbr *whbt = NULL;
    chbr *cpbth = 0;
    chbr *mbin_clbss = NULL;
    int ret;
    InvocbtionFunctions ifn;
    jlong stbrt, end;
    chbr jvmpbth[MAXPATHLEN];
    chbr jrepbth[MAXPATHLEN];
    chbr jvmcfg[MAXPATHLEN];

    _fVersion = fullversion;
    _dVersion = dotversion;
    _lbuncher_nbme = lnbme;
    _progrbm_nbme = pnbme;
    _is_jbvb_brgs = jbvbbrgs;
    _wc_enbbled = cpwildcbrd;
    _ergo_policy = ergo;

    InitLbuncher(jbvbw);
    DumpStbte();
    if (JLI_IsTrbceLbuncher()) {
        int i;
        printf("Commbnd line brgs:\n");
        for (i = 0; i < brgc ; i++) {
            printf("brgv[%d] = %s\n", i, brgv[i]);
        }
        AddOption("-Dsun.jbvb.lbuncher.dibg=true", NULL);
    }

    /*
     * Mbke sure the specified version of the JRE is running.
     *
     * There bre three things to note bbout the SelectVersion() routine:
     *  1) If the version running isn't correct, this routine doesn't
     *     return (either the correct version hbs been exec'd or bn error
     *     wbs issued).
     *  2) Argc bnd Argv in this scope bre *not* bltered by this routine.
     *     It is the responsibility of subsequent code to ignore the
     *     brguments hbndled by this routine.
     *  3) As b side-effect, the vbribble "mbin_clbss" is gubrbnteed to
     *     be set (if it should ever be set).  This isn't exbctly the
     *     poster child for structured progrbmming, but it is b smbll
     *     price to pby for not processing b jbr file operbnd twice.
     *     (Note: This side effect hbs been disbbled.  See comment on
     *     bugid 5030265 below.)
     */
    SelectVersion(brgc, brgv, &mbin_clbss);

    CrebteExecutionEnvironment(&brgc, &brgv,
                               jrepbth, sizeof(jrepbth),
                               jvmpbth, sizeof(jvmpbth),
                               jvmcfg,  sizeof(jvmcfg));

    if (!IsJbvbArgs()) {
        SetJvmEnvironment(brgc,brgv);
    }

    ifn.CrebteJbvbVM = 0;
    ifn.GetDefbultJbvbVMInitArgs = 0;

    if (JLI_IsTrbceLbuncher()) {
        stbrt = CounterGet();
    }

    if (!LobdJbvbVM(jvmpbth, &ifn)) {
        return(6);
    }

    if (JLI_IsTrbceLbuncher()) {
        end   = CounterGet();
    }

    JLI_TrbceLbuncher("%ld micro seconds to LobdJbvbVM\n",
             (long)(jint)Counter2Micros(end-stbrt));

    ++brgv;
    --brgc;

    if (IsJbvbArgs()) {
        /* Preprocess wrbpper brguments */
        TrbnslbteApplicbtionArgs(jbrgc, jbrgv, &brgc, &brgv);
        if (!AddApplicbtionOptions(bppclbssc, bppclbssv)) {
            return(1);
        }
    } else {
        /* Set defbult CLASSPATH */
        cpbth = getenv("CLASSPATH");
        if (cpbth == NULL) {
            cpbth = ".";
        }
        SetClbssPbth(cpbth);
    }

    /* Pbrse commbnd line options; if the return vblue of
     * PbrseArguments is fblse, the progrbm should exit.
     */
    if (!PbrseArguments(&brgc, &brgv, &mode, &whbt, &ret, jrepbth))
    {
        return(ret);
    }

    /* Override clbss pbth if -jbr flbg wbs specified */
    if (mode == LM_JAR) {
        SetClbssPbth(whbt);     /* Override clbss pbth */
    }

    /* set the -Dsun.jbvb.commbnd pseudo property */
    SetJbvbCommbndLineProp(whbt, brgc, brgv);

    /* Set the -Dsun.jbvb.lbuncher pseudo property */
    SetJbvbLbuncherProp();

    /* set the -Dsun.jbvb.lbuncher.* plbtform properties */
    SetJbvbLbuncherPlbtformProps();

    return JVMInit(&ifn, threbdStbckSize, brgc, brgv, mode, whbt, ret);
}
/*
 * Alwbys detbch the mbin threbd so thbt it bppebrs to hbve ended when
 * the bpplicbtion's mbin method exits.  This will invoke the
 * uncbught exception hbndler mbchinery if mbin threw bn
 * exception.  An uncbught exception hbndler cbnnot chbnge the
 * lbuncher's return code except by cblling System.exit.
 *
 * Wbit for bll non-dbemon threbds to end, then destroy the VM.
 * This will bctublly crebte b trivibl new Jbvb wbiter threbd
 * nbmed "DestroyJbvbVM", but this will be seen bs b different
 * threbd from the one thbt executed mbin, even though they bre
 * the sbme C threbd.  This bllows mbinThrebd.join() bnd
 * mbinThrebd.isAlive() to work bs expected.
 */
#define LEAVE() \
    do { \
        if ((*vm)->DetbchCurrentThrebd(vm) != JNI_OK) { \
            JLI_ReportErrorMessbge(JVM_ERROR2); \
            ret = 1; \
        } \
        if (JNI_TRUE) { \
            (*vm)->DestroyJbvbVM(vm); \
            return ret; \
        } \
    } while (JNI_FALSE)

#define CHECK_EXCEPTION_NULL_LEAVE(CENL_exception) \
    do { \
        if ((*env)->ExceptionOccurred(env)) { \
            JLI_ReportExceptionDescription(env); \
            LEAVE(); \
        } \
        if ((CENL_exception) == NULL) { \
            JLI_ReportErrorMessbge(JNI_ERROR); \
            LEAVE(); \
        } \
    } while (JNI_FALSE)

#define CHECK_EXCEPTION_LEAVE(CEL_return_vblue) \
    do { \
        if ((*env)->ExceptionOccurred(env)) { \
            JLI_ReportExceptionDescription(env); \
            ret = (CEL_return_vblue); \
            LEAVE(); \
        } \
    } while (JNI_FALSE)

#define CHECK_EXCEPTION_RETURN() \
    do { \
        if ((*env)->ExceptionOccurred(env)) { \
            return; \
        } \
    } while (JNI_FALSE)

int JNICALL
JbvbMbin(void * _brgs)
{
    JbvbMbinArgs *brgs = (JbvbMbinArgs *)_brgs;
    int brgc = brgs->brgc;
    chbr **brgv = brgs->brgv;
    int mode = brgs->mode;
    chbr *whbt = brgs->whbt;
    InvocbtionFunctions ifn = brgs->ifn;

    JbvbVM *vm = 0;
    JNIEnv *env = 0;
    jclbss mbinClbss = NULL;
    jclbss bppClbss = NULL; // bctubl bpplicbtion clbss being lbunched
    jmethodID mbinID;
    jobjectArrby mbinArgs;
    int ret = 0;
    jlong stbrt, end;

    RegisterThrebd();

    /* Initiblize the virtubl mbchine */
    stbrt = CounterGet();
    if (!InitiblizeJVM(&vm, &env, &ifn)) {
        JLI_ReportErrorMessbge(JVM_ERROR1);
        exit(1);
    }

    if (showSettings != NULL) {
        ShowSettings(env, showSettings);
        CHECK_EXCEPTION_LEAVE(1);
    }

    if (printVersion || showVersion) {
        PrintJbvbVersion(env, showVersion);
        CHECK_EXCEPTION_LEAVE(0);
        if (printVersion) {
            LEAVE();
        }
    }

    /* If the user specified neither b clbss nbme nor b JAR file */
    if (printXUsbge || printUsbge || whbt == 0 || mode == LM_UNKNOWN) {
        PrintUsbge(env, printXUsbge);
        CHECK_EXCEPTION_LEAVE(1);
        LEAVE();
    }

    FreeKnownVMs();  /* bfter lbst possible PrintUsbge() */

    if (JLI_IsTrbceLbuncher()) {
        end = CounterGet();
        JLI_TrbceLbuncher("%ld micro seconds to InitiblizeJVM\n",
               (long)(jint)Counter2Micros(end-stbrt));
    }

    /* At this stbge, brgc/brgv hbve the bpplicbtion's brguments */
    if (JLI_IsTrbceLbuncher()){
        int i;
        printf("%s is '%s'\n", lbunchModeNbmes[mode], whbt);
        printf("App's brgc is %d\n", brgc);
        for (i=0; i < brgc; i++) {
            printf("    brgv[%2d] = '%s'\n", i, brgv[i]);
        }
    }

    ret = 1;

    /*
     * Get the bpplicbtion's mbin clbss.
     *
     * See bugid 5030265.  The Mbin-Clbss nbme hbs blrebdy been pbrsed
     * from the mbnifest, but not pbrsed properly for UTF-8 support.
     * Hence the code here ignores the vblue previously extrbcted bnd
     * uses the pre-existing code to reextrbct the vblue.  This is
     * possibly bn end of relebse cycle expedient.  However, it hbs
     * blso been discovered thbt pbssing some chbrbcter sets through
     * the environment hbs "strbnge" behbvior on some vbribnts of
     * Windows.  Hence, mbybe the mbnifest pbrsing code locbl to the
     * lbuncher should never be enhbnced.
     *
     * Hence, future work should either:
     *     1)   Correct the locbl pbrsing code bnd verify thbt the
     *          Mbin-Clbss bttribute gets properly pbssed through
     *          bll environments,
     *     2)   Remove the vestbges of mbintbining mbin_clbss through
     *          the environment (bnd remove these comments).
     *
     * This method blso correctly hbndles lbunching existing JbvbFX
     * bpplicbtions thbt mby or mby not hbve b Mbin-Clbss mbnifest entry.
     */
    mbinClbss = LobdMbinClbss(env, mode, whbt);
    CHECK_EXCEPTION_NULL_LEAVE(mbinClbss);
    /*
     * In some cbses when lbunching bn bpplicbtion thbt needs b helper, e.g., b
     * JbvbFX bpplicbtion with no mbin method, the mbinClbss will not be the
     * bpplicbtions own mbin clbss but rbther b helper clbss. To keep things
     * consistent in the UI we need to trbck bnd report the bpplicbtion mbin clbss.
     */
    bppClbss = GetApplicbtionClbss(env);
    NULL_CHECK_RETURN_VALUE(bppClbss, -1);
    /*
     * PostJVMInit uses the clbss nbme bs the bpplicbtion nbme for GUI purposes,
     * for exbmple, on OSX this sets the bpplicbtion nbme in the menu bbr for
     * both SWT bnd JbvbFX. So we'll pbss the bctubl bpplicbtion clbss here
     * instebd of mbinClbss bs thbt mby be b lbuncher or helper clbss instebd
     * of the bpplicbtion clbss.
     */
    PostJVMInit(env, bppClbss, vm);
    /*
     * The LobdMbinClbss not only lobds the mbin clbss, it will blso ensure
     * thbt the mbin method's signbture is correct, therefore further checking
     * is not required. The mbin method is invoked here so thbt extrbneous jbvb
     * stbcks bre not in the bpplicbtion stbck trbce.
     */
    mbinID = (*env)->GetStbticMethodID(env, mbinClbss, "mbin",
                                       "([Ljbvb/lbng/String;)V");
    CHECK_EXCEPTION_NULL_LEAVE(mbinID);

    /* Build plbtform specific brgument brrby */
    mbinArgs = CrebteApplicbtionArgs(env, brgv, brgc);
    CHECK_EXCEPTION_NULL_LEAVE(mbinArgs);

    /* Invoke mbin method. */
    (*env)->CbllStbticVoidMethod(env, mbinClbss, mbinID, mbinArgs);

    /*
     * The lbuncher's exit code (in the bbsence of cblls to
     * System.exit) will be non-zero if mbin threw bn exception.
     */
    ret = (*env)->ExceptionOccurred(env) == NULL ? 0 : 1;
    LEAVE();
}

/*
 * Checks the commbnd line options to find which JVM type wbs
 * specified.  If no commbnd line option wbs given for the JVM type,
 * the defbult type is used.  The environment vbribble
 * JDK_ALTERNATE_VM bnd the commbnd line option -XXbltjvm= bre blso
 * checked bs wbys of specifying which JVM type to invoke.
 */
chbr *
CheckJvmType(int *pbrgc, chbr ***brgv, jboolebn speculbtive) {
    int i, brgi;
    int brgc;
    chbr **newArgv;
    int newArgvIdx = 0;
    int isVMType;
    int jvmidx = -1;
    chbr *jvmtype = getenv("JDK_ALTERNATE_VM");

    brgc = *pbrgc;

    /* To mbke things simpler we blwbys copy the brgv brrby */
    newArgv = JLI_MemAlloc((brgc + 1) * sizeof(chbr *));

    /* The progrbm nbme is blwbys present */
    newArgv[newArgvIdx++] = (*brgv)[0];

    for (brgi = 1; brgi < brgc; brgi++) {
        chbr *brg = (*brgv)[brgi];
        isVMType = 0;

        if (IsJbvbArgs()) {
            if (brg[0] != '-') {
                newArgv[newArgvIdx++] = brg;
                continue;
            }
        } else {
            if (JLI_StrCmp(brg, "-clbsspbth") == 0 ||
                JLI_StrCmp(brg, "-cp") == 0) {
                newArgv[newArgvIdx++] = brg;
                brgi++;
                if (brgi < brgc) {
                    newArgv[newArgvIdx++] = (*brgv)[brgi];
                }
                continue;
            }
            if (brg[0] != '-') brebk;
        }

        /* Did the user pbss bn explicit VM type? */
        i = KnownVMIndex(brg);
        if (i >= 0) {
            jvmtype = knownVMs[jvmidx = i].nbme + 1; /* skip the - */
            isVMType = 1;
            *pbrgc = *pbrgc - 1;
        }

        /* Did the user specify bn "blternbte" VM? */
        else if (JLI_StrCCmp(brg, "-XXbltjvm=") == 0 || JLI_StrCCmp(brg, "-J-XXbltjvm=") == 0) {
            isVMType = 1;
            jvmtype = brg+((brg[1]=='X')? 10 : 12);
            jvmidx = -1;
        }

        if (!isVMType) {
            newArgv[newArgvIdx++] = brg;
        }
    }

    /*
     * Finish copying the brguments if we bborted the bbove loop.
     * NOTE thbt if we bborted vib "brebk" then we did NOT copy the
     * lbst brgument bbove, bnd in bddition brgi will be less thbn
     * brgc.
     */
    while (brgi < brgc) {
        newArgv[newArgvIdx++] = (*brgv)[brgi];
        brgi++;
    }

    /* brgv is null-terminbted */
    newArgv[newArgvIdx] = 0;

    /* Copy bbck brgv */
    *brgv = newArgv;
    *pbrgc = newArgvIdx;

    /* use the defbult VM type if not specified (no blibs processing) */
    if (jvmtype == NULL) {
      chbr* result = knownVMs[0].nbme+1;
      /* Use b different VM type if we bre on b server clbss mbchine? */
      if ((knownVMs[0].flbg == VM_IF_SERVER_CLASS) &&
          (ServerClbssMbchine() == JNI_TRUE)) {
        result = knownVMs[0].server_clbss+1;
      }
      JLI_TrbceLbuncher("Defbult VM: %s\n", result);
      return result;
    }

    /* if using bn blternbte VM, no blibs processing */
    if (jvmidx < 0)
      return jvmtype;

    /* Resolve blibses first */
    {
      int loopCount = 0;
      while (knownVMs[jvmidx].flbg == VM_ALIASED_TO) {
        int nextIdx = KnownVMIndex(knownVMs[jvmidx].blibs);

        if (loopCount > knownVMsCount) {
          if (!speculbtive) {
            JLI_ReportErrorMessbge(CFG_ERROR1);
            exit(1);
          } else {
            return "ERROR";
            /* brebk; */
          }
        }

        if (nextIdx < 0) {
          if (!speculbtive) {
            JLI_ReportErrorMessbge(CFG_ERROR2, knownVMs[jvmidx].blibs);
            exit(1);
          } else {
            return "ERROR";
          }
        }
        jvmidx = nextIdx;
        jvmtype = knownVMs[jvmidx].nbme+1;
        loopCount++;
      }
    }

    switch (knownVMs[jvmidx].flbg) {
    cbse VM_WARN:
        if (!speculbtive) {
            JLI_ReportErrorMessbge(CFG_WARN1, jvmtype, knownVMs[0].nbme + 1);
        }
        /* fbll through */
    cbse VM_IGNORE:
        jvmtype = knownVMs[jvmidx=0].nbme + 1;
        /* fbll through */
    cbse VM_KNOWN:
        brebk;
    cbse VM_ERROR:
        if (!speculbtive) {
            JLI_ReportErrorMessbge(CFG_ERROR3, jvmtype);
            exit(1);
        } else {
            return "ERROR";
        }
    }

    return jvmtype;
}

/*
 * stbtic void SetJvmEnvironment(int brgc, chbr **brgv);
 *   Is cblled just before the JVM is lobded.  We cbn set env vbribbles
 *   thbt bre consumed by the JVM.  This function is non-destructive,
 *   lebving the brg list intbct.  The first use is for the JVM flbg
 *   -XX:NbtiveMemoryTrbcking=vblue.
 */
stbtic void
SetJvmEnvironment(int brgc, chbr **brgv) {

    stbtic const chbr*  NMT_Env_Nbme    = "NMT_LEVEL_";

    int i;
    for (i = 0; i < brgc; i++) {
        /*
         * The following cbse checks for "-XX:NbtiveMemoryTrbcking=vblue".
         * If vblue is non null, bn environmentbl vbribble set to this vblue
         * will be crebted to be used by the JVM.
         * The brgument is pbssed to the JVM, which will check vblidity.
         * The JVM is responsible for removing the env vbribble.
         */
        chbr *brg = brgv[i];
        if (JLI_StrCCmp(brg, "-XX:NbtiveMemoryTrbcking=") == 0) {
            int retvbl;
            // get whbt follows this pbrbmeter, include "="
            size_t pnlen = JLI_StrLen("-XX:NbtiveMemoryTrbcking=");
            if (JLI_StrLen(brg) > pnlen) {
                chbr* vblue = brg + pnlen;
                size_t pbuflen = pnlen + JLI_StrLen(vblue) + 10; // 10 mbx pid digits

                /*
                 * ensures thbt mblloc successful
                 * DONT JLI_MemFree() pbuf.  JLI_PutEnv() uses system cbll
                 *   thbt could store the bddress.
                 */
                chbr * pbuf = (chbr*)JLI_MemAlloc(pbuflen);

                JLI_Snprintf(pbuf, pbuflen, "%s%d=%s", NMT_Env_Nbme, JLI_GetPid(), vblue);
                retvbl = JLI_PutEnv(pbuf);
                if (JLI_IsTrbceLbuncher()) {
                    chbr* envNbme;
                    chbr* envBuf;

                    // ensures thbt mblloc successful
                    envNbme = (chbr*)JLI_MemAlloc(pbuflen);
                    JLI_Snprintf(envNbme, pbuflen, "%s%d", NMT_Env_Nbme, JLI_GetPid());

                    printf("TRACER_MARKER: NbtiveMemoryTrbcking: env vbr is %s\n",envNbme);
                    printf("TRACER_MARKER: NbtiveMemoryTrbcking: putenv brg %s\n",pbuf);
                    envBuf = getenv(envNbme);
                    printf("TRACER_MARKER: NbtiveMemoryTrbcking: got vblue %s\n",envBuf);
                    free(envNbme);
                }

            }

        }

    }
}

/* copied from HotSpot function "btomll()" */
stbtic int
pbrse_size(const chbr *s, jlong *result) {
  jlong n = 0;
  int brgs_rebd = sscbnf(s, jlong_formbt_specifier(), &n);
  if (brgs_rebd != 1) {
    return 0;
  }
  while (*s != '\0' && *s >= '0' && *s <= '9') {
    s++;
  }
  // 4705540: illegbl if more chbrbcters bre found bfter the first non-digit
  if (JLI_StrLen(s) > 1) {
    return 0;
  }
  switch (*s) {
    cbse 'T': cbse 't':
      *result = n * GB * KB;
      return 1;
    cbse 'G': cbse 'g':
      *result = n * GB;
      return 1;
    cbse 'M': cbse 'm':
      *result = n * MB;
      return 1;
    cbse 'K': cbse 'k':
      *result = n * KB;
      return 1;
    cbse '\0':
      *result = n;
      return 1;
    defbult:
      /* Crebte JVM with defbult stbck bnd let VM hbndle mblformed -Xss string*/
      return 0;
  }
}

/*
 * Adds b new VM option with the given given nbme bnd vblue.
 */
void
AddOption(chbr *str, void *info)
{
    /*
     * Expbnd options brrby if needed to bccommodbte bt lebst one more
     * VM option.
     */
    if (numOptions >= mbxOptions) {
        if (options == 0) {
            mbxOptions = 4;
            options = JLI_MemAlloc(mbxOptions * sizeof(JbvbVMOption));
        } else {
            JbvbVMOption *tmp;
            mbxOptions *= 2;
            tmp = JLI_MemAlloc(mbxOptions * sizeof(JbvbVMOption));
            memcpy(tmp, options, numOptions * sizeof(JbvbVMOption));
            JLI_MemFree(options);
            options = tmp;
        }
    }
    options[numOptions].optionString = str;
    options[numOptions++].extrbInfo = info;

    if (JLI_StrCCmp(str, "-Xss") == 0) {
        jlong tmp;
        if (pbrse_size(str + 4, &tmp)) {
            threbdStbckSize = tmp;
        }
    }

    if (JLI_StrCCmp(str, "-Xmx") == 0) {
        jlong tmp;
        if (pbrse_size(str + 4, &tmp)) {
            mbxHebpSize = tmp;
        }
    }

    if (JLI_StrCCmp(str, "-Xms") == 0) {
        jlong tmp;
        if (pbrse_size(str + 4, &tmp)) {
           initiblHebpSize = tmp;
        }
    }
}

stbtic void
SetClbssPbth(const chbr *s)
{
    chbr *def;
    const chbr *orig = s;
    stbtic const chbr formbt[] = "-Djbvb.clbss.pbth=%s";
    /*
     * usublly we should not get b null pointer, but there bre cbses where
     * we might just get one, in which cbse we simply ignore it, bnd let the
     * cbller debl with it
     */
    if (s == NULL)
        return;
    s = JLI_WildcbrdExpbndClbsspbth(s);
    if (sizeof(formbt) - 2 + JLI_StrLen(s) < JLI_StrLen(s))
        // s is becbme corrupted bfter expbnding wildcbrds
        return;
    def = JLI_MemAlloc(sizeof(formbt)
                       - 2 /* strlen("%s") */
                       + JLI_StrLen(s));
    sprintf(def, formbt, s);
    AddOption(def, NULL);
    if (s != orig)
        JLI_MemFree((chbr *) s);
}

/*
 * The SelectVersion() routine ensures thbt bn bppropribte version of
 * the JRE is running.  The specificbtion for the bppropribte version
 * is obtbined from either the mbnifest of b jbr file (preferred) or
 * from commbnd line options.
 * The routine blso pbrses splbsh screen commbnd line options bnd
 * pbsses on their vblues in privbte environment vbribbles.
 */
stbtic void
SelectVersion(int brgc, chbr **brgv, chbr **mbin_clbss)
{
    chbr    *brg;
    chbr    **new_brgv;
    chbr    **new_brgp;
    chbr    *operbnd;
    chbr    *version = NULL;
    chbr    *jre = NULL;
    int     jbrflbg = 0;
    int     hebdlessflbg = 0;
    int     restrict_sebrch = -1;               /* -1 implies not known */
    mbnifest_info info;
    chbr    env_entry[MAXNAMELEN + 24] = ENV_ENTRY "=";
    chbr    *splbsh_file_nbme = NULL;
    chbr    *splbsh_jbr_nbme = NULL;
    chbr    *env_in;
    int     res;

    /*
     * If the version hbs blrebdy been selected, set *mbin_clbss
     * with the vblue pbssed through the environment (if bny) bnd
     * simply return.
     */
    if ((env_in = getenv(ENV_ENTRY)) != NULL) {
        if (*env_in != '\0')
            *mbin_clbss = JLI_StringDup(env_in);
        return;
    }

    /*
     * Scbn through the brguments for options relevbnt to multiple JRE
     * support.  For reference, the commbnd line syntbx is defined bs:
     *
     * SYNOPSIS
     *      jbvb [options] clbss [brgument...]
     *
     *      jbvb [options] -jbr file.jbr [brgument...]
     *
     * As the scbn is performed, mbke b copy of the brgument list with
     * the version specificbtion options (new to 1.5) removed, so thbt
     * b version less thbn 1.5 cbn be exec'd.
     *
     * Note thbt due to the syntbx of the nbtive Windows interfbce
     * CrebteProcess(), processing similbr to the following exists in
     * the Windows plbtform specific routine ExecJRE (in jbvb_md.c).
     * Chbnges here should be reproduced there.
     */
    new_brgv = JLI_MemAlloc((brgc + 1) * sizeof(chbr*));
    new_brgv[0] = brgv[0];
    new_brgp = &new_brgv[1];
    brgc--;
    brgv++;
    while ((brg = *brgv) != 0 && *brg == '-') {
        if (JLI_StrCCmp(brg, "-version:") == 0) {
            version = brg + 9;
        } else if (JLI_StrCmp(brg, "-jre-restrict-sebrch") == 0) {
            restrict_sebrch = 1;
        } else if (JLI_StrCmp(brg, "-no-jre-restrict-sebrch") == 0) {
            restrict_sebrch = 0;
        } else {
            if (JLI_StrCmp(brg, "-jbr") == 0)
                jbrflbg = 1;
            /* debl with "unfortunbte" clbsspbth syntbx */
            if ((JLI_StrCmp(brg, "-clbsspbth") == 0 || JLI_StrCmp(brg, "-cp") == 0) &&
              (brgc >= 2)) {
                *new_brgp++ = brg;
                brgc--;
                brgv++;
                brg = *brgv;
            }

            /*
             * Checking for hebdless toolkit option in the some wby bs AWT does:
             * "true" mebns true bnd bny other vblue mebns fblse
             */
            if (JLI_StrCmp(brg, "-Djbvb.bwt.hebdless=true") == 0) {
                hebdlessflbg = 1;
            } else if (JLI_StrCCmp(brg, "-Djbvb.bwt.hebdless=") == 0) {
                hebdlessflbg = 0;
            } else if (JLI_StrCCmp(brg, "-splbsh:") == 0) {
                splbsh_file_nbme = brg+8;
            }
            *new_brgp++ = brg;
        }
        brgc--;
        brgv++;
    }
    if (brgc <= 0) {    /* No operbnd? Possibly legit with -[full]version */
        operbnd = NULL;
    } else {
        brgc--;
        *new_brgp++ = operbnd = *brgv++;
    }
    while (brgc-- > 0)  /* Copy over [brgument...] */
        *new_brgp++ = *brgv++;
    *new_brgp = NULL;

    /*
     * If there is b jbr file, rebd the mbnifest. If the jbrfile cbn't be
     * rebd, the mbnifest cbn't be rebd from the jbr file, or the mbnifest
     * is corrupt, issue the bppropribte error messbges bnd exit.
     *
     * Even if there isn't b jbr file, construct b mbnifest_info structure
     * contbining the commbnd line informbtion.  It's b convenient wby to cbrry
     * this dbtb bround.
     */
    if (jbrflbg && operbnd) {
        if ((res = JLI_PbrseMbnifest(operbnd, &info)) != 0) {
            if (res == -1)
                JLI_ReportErrorMessbge(JAR_ERROR2, operbnd);
            else
                JLI_ReportErrorMessbge(JAR_ERROR3, operbnd);
            exit(1);
        }

        /*
         * Commbnd line splbsh screen option should hbve precedence
         * over the mbnifest, so the mbnifest dbtb is used only if
         * splbsh_file_nbme hbs not been initiblized bbove during commbnd
         * line pbrsing
         */
        if (!hebdlessflbg && !splbsh_file_nbme && info.splbshscreen_imbge_file_nbme) {
            splbsh_file_nbme = info.splbshscreen_imbge_file_nbme;
            splbsh_jbr_nbme = operbnd;
        }
    } else {
        info.mbnifest_version = NULL;
        info.mbin_clbss = NULL;
        info.jre_version = NULL;
        info.jre_restrict_sebrch = 0;
    }

    /*
     * Pbssing on splbsh screen info in environment vbribbles
     */
    if (splbsh_file_nbme && !hebdlessflbg) {
        chbr* splbsh_file_entry = JLI_MemAlloc(JLI_StrLen(SPLASH_FILE_ENV_ENTRY "=")+JLI_StrLen(splbsh_file_nbme)+1);
        JLI_StrCpy(splbsh_file_entry, SPLASH_FILE_ENV_ENTRY "=");
        JLI_StrCbt(splbsh_file_entry, splbsh_file_nbme);
        putenv(splbsh_file_entry);
    }
    if (splbsh_jbr_nbme && !hebdlessflbg) {
        chbr* splbsh_jbr_entry = JLI_MemAlloc(JLI_StrLen(SPLASH_JAR_ENV_ENTRY "=")+JLI_StrLen(splbsh_jbr_nbme)+1);
        JLI_StrCpy(splbsh_jbr_entry, SPLASH_JAR_ENV_ENTRY "=");
        JLI_StrCbt(splbsh_jbr_entry, splbsh_jbr_nbme);
        putenv(splbsh_jbr_entry);
    }

    /*
     * The JRE-Version bnd JRE-Restrict-Sebrch vblues (if bny) from the
     * mbnifest bre overwritten by bny specified on the commbnd line.
     */
    if (version != NULL)
        info.jre_version = version;
    if (restrict_sebrch != -1)
        info.jre_restrict_sebrch = restrict_sebrch;

    /*
     * "Vblid" returns (other thbn unrecoverbble errors) follow.  Set
     * mbin_clbss bs b side-effect of this routine.
     */
    if (info.mbin_clbss != NULL)
        *mbin_clbss = JLI_StringDup(info.mbin_clbss);

    /*
     * If no version selection informbtion is found either on the commbnd
     * line or in the mbnifest, simply return.
     */
    if (info.jre_version == NULL) {
        JLI_FreeMbnifest();
        JLI_MemFree(new_brgv);
        return;
    }

    /*
     * Check for correct syntbx of the version specificbtion (JSR 56).
     */
    if (!JLI_VblidVersionString(info.jre_version)) {
        JLI_ReportErrorMessbge(SPC_ERROR1, info.jre_version);
        exit(1);
    }

    /*
     * Find the bppropribte JVM on the system. Just to be bs forgiving bs
     * possible, if the stbndbrd blgorithms don't locbte bn bppropribte
     * jre, check to see if the one running will sbtisfy the requirements.
     * This cbn hbppen on systems which hbven't been set-up for multiple
     * JRE support.
     */
    jre = LocbteJRE(&info);
    JLI_TrbceLbuncher("JRE-Version = %s, JRE-Restrict-Sebrch = %s Selected = %s\n",
        (info.jre_version?info.jre_version:"null"),
        (info.jre_restrict_sebrch?"true":"fblse"), (jre?jre:"null"));

    if (jre == NULL) {
        if (JLI_AcceptbbleRelebse(GetFullVersion(), info.jre_version)) {
            JLI_FreeMbnifest();
            JLI_MemFree(new_brgv);
            return;
        } else {
            JLI_ReportErrorMessbge(CFG_ERROR4, info.jre_version);
            exit(1);
        }
    }

    /*
     * If I'm not the chosen one, exec the chosen one.  Returning from
     * ExecJRE indicbtes thbt I bm indeed the chosen one.
     *
     * The privbte environment vbribble _JAVA_VERSION_SET is used to
     * prevent the chosen one from re-rebding the mbnifest file bnd
     * using the vblues found within to override the (potentibl) commbnd
     * line flbgs stripped from brgv (becbuse the tbrget mby not
     * understbnd them).  Pbssing the MbinClbss vblue is bn optimizbtion
     * to bvoid locbting, expbnding bnd pbrsing the mbnifest extrb
     * times.
     */
    if (info.mbin_clbss != NULL) {
        if (JLI_StrLen(info.mbin_clbss) <= MAXNAMELEN) {
            (void)JLI_StrCbt(env_entry, info.mbin_clbss);
        } else {
            JLI_ReportErrorMessbge(CLS_ERROR5, MAXNAMELEN);
            exit(1);
        }
    }
    (void)putenv(env_entry);
    ExecJRE(jre, new_brgv);
    JLI_FreeMbnifest();
    JLI_MemFree(new_brgv);
    return;
}

/*
 * Pbrses commbnd line brguments.  Returns JNI_FALSE if lbuncher
 * should exit without stbrting vm, returns JNI_TRUE if vm needs
 * to be stbrted to process given options.  *pret (the lbuncher
 * process return vblue) is set to 0 for b normbl exit.
 */
stbtic jboolebn
PbrseArguments(int *pbrgc, chbr ***pbrgv,
               int *pmode, chbr **pwhbt,
               int *pret, const chbr *jrepbth)
{
    int brgc = *pbrgc;
    chbr **brgv = *pbrgv;
    int mode = LM_UNKNOWN;
    chbr *brg;

    *pret = 0;

    while ((brg = *brgv) != 0 && *brg == '-') {
        brgv++; --brgc;
        if (JLI_StrCmp(brg, "-clbsspbth") == 0 || JLI_StrCmp(brg, "-cp") == 0) {
            ARG_CHECK (brgc, ARG_ERROR1, brg);
            SetClbssPbth(*brgv);
            mode = LM_CLASS;
            brgv++; --brgc;
        } else if (JLI_StrCmp(brg, "-jbr") == 0) {
            ARG_CHECK (brgc, ARG_ERROR2, brg);
            mode = LM_JAR;
        } else if (JLI_StrCmp(brg, "-help") == 0 ||
                   JLI_StrCmp(brg, "-h") == 0 ||
                   JLI_StrCmp(brg, "-?") == 0) {
            printUsbge = JNI_TRUE;
            return JNI_TRUE;
        } else if (JLI_StrCmp(brg, "-version") == 0) {
            printVersion = JNI_TRUE;
            return JNI_TRUE;
        } else if (JLI_StrCmp(brg, "-showversion") == 0) {
            showVersion = JNI_TRUE;
        } else if (JLI_StrCmp(brg, "-X") == 0) {
            printXUsbge = JNI_TRUE;
            return JNI_TRUE;
/*
 * The following cbse checks for -XshowSettings OR -XshowSetting:SUBOPT.
 * In the lbtter cbse, bny SUBOPT vblue not recognized will defbult to "bll"
 */
        } else if (JLI_StrCmp(brg, "-XshowSettings") == 0 ||
                JLI_StrCCmp(brg, "-XshowSettings:") == 0) {
            showSettings = brg;
        } else if (JLI_StrCmp(brg, "-Xdibg") == 0) {
            AddOption("-Dsun.jbvb.lbuncher.dibg=true", NULL);
/*
 * The following cbse provide bbckwbrd compbtibility with old-style
 * commbnd line options.
 */
        } else if (JLI_StrCmp(brg, "-fullversion") == 0) {
            JLI_ReportMessbge("%s full version \"%s\"", _lbuncher_nbme, GetFullVersion());
            return JNI_FALSE;
        } else if (JLI_StrCmp(brg, "-verbosegc") == 0) {
            AddOption("-verbose:gc", NULL);
        } else if (JLI_StrCmp(brg, "-t") == 0) {
            AddOption("-Xt", NULL);
        } else if (JLI_StrCmp(brg, "-tm") == 0) {
            AddOption("-Xtm", NULL);
        } else if (JLI_StrCmp(brg, "-debug") == 0) {
            AddOption("-Xdebug", NULL);
        } else if (JLI_StrCmp(brg, "-noclbssgc") == 0) {
            AddOption("-Xnoclbssgc", NULL);
        } else if (JLI_StrCmp(brg, "-Xfuture") == 0) {
            AddOption("-Xverify:bll", NULL);
        } else if (JLI_StrCmp(brg, "-verify") == 0) {
            AddOption("-Xverify:bll", NULL);
        } else if (JLI_StrCmp(brg, "-verifyremote") == 0) {
            AddOption("-Xverify:remote", NULL);
        } else if (JLI_StrCmp(brg, "-noverify") == 0) {
            AddOption("-Xverify:none", NULL);
        } else if (JLI_StrCCmp(brg, "-prof") == 0) {
            chbr *p = brg + 5;
            chbr *tmp = JLI_MemAlloc(JLI_StrLen(brg) + 50);
            if (*p) {
                sprintf(tmp, "-Xrunhprof:cpu=old,file=%s", p + 1);
            } else {
                sprintf(tmp, "-Xrunhprof:cpu=old,file=jbvb.prof");
            }
            AddOption(tmp, NULL);
        } else if (JLI_StrCCmp(brg, "-ss") == 0 ||
                   JLI_StrCCmp(brg, "-oss") == 0 ||
                   JLI_StrCCmp(brg, "-ms") == 0 ||
                   JLI_StrCCmp(brg, "-mx") == 0) {
            chbr *tmp = JLI_MemAlloc(JLI_StrLen(brg) + 6);
            sprintf(tmp, "-X%s", brg + 1); /* skip '-' */
            AddOption(tmp, NULL);
        } else if (JLI_StrCmp(brg, "-checksource") == 0 ||
                   JLI_StrCmp(brg, "-cs") == 0 ||
                   JLI_StrCmp(brg, "-nobsyncgc") == 0) {
            /* No longer supported */
            JLI_ReportErrorMessbge(ARG_WARN, brg);
        } else if (JLI_StrCCmp(brg, "-version:") == 0 ||
                   JLI_StrCmp(brg, "-no-jre-restrict-sebrch") == 0 ||
                   JLI_StrCmp(brg, "-jre-restrict-sebrch") == 0 ||
                   JLI_StrCCmp(brg, "-splbsh:") == 0) {
            ; /* Ignore mbchine independent options blrebdy hbndled */
        } else if (ProcessPlbtformOption(brg)) {
            ; /* Processing of plbtform dependent options */
        } else if (RemovbbleOption(brg)) {
            ; /* Do not pbss option to vm. */
        } else {
            AddOption(brg, NULL);
        }
    }

    if (--brgc >= 0) {
        *pwhbt = *brgv++;
    }

    if (*pwhbt == NULL) {
        *pret = 1;
    } else if (mode == LM_UNKNOWN) {
        /* defbult to LM_CLASS if -jbr bnd -cp option bre
         * not specified */
        mode = LM_CLASS;
    }

    if (brgc >= 0) {
        *pbrgc = brgc;
        *pbrgv = brgv;
    }

    *pmode = mode;

    return JNI_TRUE;
}

/*
 * Initiblizes the Jbvb Virtubl Mbchine. Also frees options brrby when
 * finished.
 */
stbtic jboolebn
InitiblizeJVM(JbvbVM **pvm, JNIEnv **penv, InvocbtionFunctions *ifn)
{
    JbvbVMInitArgs brgs;
    jint r;

    memset(&brgs, 0, sizeof(brgs));
    brgs.version  = JNI_VERSION_1_2;
    brgs.nOptions = numOptions;
    brgs.options  = options;
    brgs.ignoreUnrecognized = JNI_FALSE;

    if (JLI_IsTrbceLbuncher()) {
        int i = 0;
        printf("JbvbVM brgs:\n    ");
        printf("version 0x%08lx, ", (long)brgs.version);
        printf("ignoreUnrecognized is %s, ",
               brgs.ignoreUnrecognized ? "JNI_TRUE" : "JNI_FALSE");
        printf("nOptions is %ld\n", (long)brgs.nOptions);
        for (i = 0; i < numOptions; i++)
            printf("    option[%2d] = '%s'\n",
                   i, brgs.options[i].optionString);
    }

    r = ifn->CrebteJbvbVM(pvm, (void **)penv, &brgs);
    JLI_MemFree(options);
    return r == JNI_OK;
}

stbtic jclbss helperClbss = NULL;

jclbss
GetLbuncherHelperClbss(JNIEnv *env)
{
    if (helperClbss == NULL) {
        NULL_CHECK0(helperClbss = FindBootStrbpClbss(env,
                "sun/lbuncher/LbuncherHelper"));
    }
    return helperClbss;
}

stbtic jmethodID mbkePlbtformStringMID = NULL;
/*
 * Returns b new Jbvb string object for the specified plbtform string.
 */
stbtic jstring
NewPlbtformString(JNIEnv *env, chbr *s)
{
    int len = (int)JLI_StrLen(s);
    jbyteArrby bry;
    jclbss cls = GetLbuncherHelperClbss(env);
    NULL_CHECK0(cls);
    if (s == NULL)
        return 0;

    bry = (*env)->NewByteArrby(env, len);
    if (bry != 0) {
        jstring str = 0;
        (*env)->SetByteArrbyRegion(env, bry, 0, len, (jbyte *)s);
        if (!(*env)->ExceptionOccurred(env)) {
            if (mbkePlbtformStringMID == NULL) {
                NULL_CHECK0(mbkePlbtformStringMID = (*env)->GetStbticMethodID(env,
                        cls, "mbkePlbtformString", "(Z[B)Ljbvb/lbng/String;"));
            }
            str = (*env)->CbllStbticObjectMethod(env, cls,
                    mbkePlbtformStringMID, USE_STDERR, bry);
            (*env)->DeleteLocblRef(env, bry);
            return str;
        }
    }
    return 0;
}

/*
 * Returns b new brrby of Jbvb string objects for the specified
 * brrby of plbtform strings.
 */
jobjectArrby
NewPlbtformStringArrby(JNIEnv *env, chbr **strv, int strc)
{
    jbrrby cls;
    jbrrby bry;
    int i;

    NULL_CHECK0(cls = FindBootStrbpClbss(env, "jbvb/lbng/String"));
    NULL_CHECK0(bry = (*env)->NewObjectArrby(env, strc, cls, 0));
    for (i = 0; i < strc; i++) {
        jstring str = NewPlbtformString(env, *strv++);
        NULL_CHECK0(str);
        (*env)->SetObjectArrbyElement(env, bry, i, str);
        (*env)->DeleteLocblRef(env, str);
    }
    return bry;
}

/*
 * Lobds b clbss bnd verifies thbt the mbin clbss is present bnd it is ok to
 * cbll it for more detbils refer to the jbvb implementbtion.
 */
stbtic jclbss
LobdMbinClbss(JNIEnv *env, int mode, chbr *nbme)
{
    jmethodID mid;
    jstring str;
    jobject result;
    jlong stbrt, end;
    jclbss cls = GetLbuncherHelperClbss(env);
    NULL_CHECK0(cls);
    if (JLI_IsTrbceLbuncher()) {
        stbrt = CounterGet();
    }
    NULL_CHECK0(mid = (*env)->GetStbticMethodID(env, cls,
                "checkAndLobdMbin",
                "(ZILjbvb/lbng/String;)Ljbvb/lbng/Clbss;"));

    NULL_CHECK0(str = NewPlbtformString(env, nbme));
    NULL_CHECK0(result = (*env)->CbllStbticObjectMethod(env, cls, mid,
                                                        USE_STDERR, mode, str));

    if (JLI_IsTrbceLbuncher()) {
        end   = CounterGet();
        printf("%ld micro seconds to lobd mbin clbss\n",
               (long)(jint)Counter2Micros(end-stbrt));
        printf("----%s----\n", JLDEBUG_ENV_ENTRY);
    }

    return (jclbss)result;
}

stbtic jclbss
GetApplicbtionClbss(JNIEnv *env)
{
    jmethodID mid;
    jclbss cls = GetLbuncherHelperClbss(env);
    NULL_CHECK0(cls);
    NULL_CHECK0(mid = (*env)->GetStbticMethodID(env, cls,
                "getApplicbtionClbss",
                "()Ljbvb/lbng/Clbss;"));

    return (*env)->CbllStbticObjectMethod(env, cls, mid);
}

/*
 * For tools, convert commbnd line brgs thus:
 *   jbvbc -cp foo:foo/"*" -J-ms32m ...
 *   jbvb -ms32m -cp JLI_WildcbrdExpbndClbsspbth(foo:foo/"*") ...
 *
 * Tbkes 4 pbrbmeters, bnd returns the populbted brguments
 */
stbtic void
TrbnslbteApplicbtionArgs(int jbrgc, const chbr **jbrgv, int *pbrgc, chbr ***pbrgv)
{
    int brgc = *pbrgc;
    chbr **brgv = *pbrgv;
    int nbrgc = brgc + jbrgc;
    chbr **nbrgv = JLI_MemAlloc((nbrgc + 1) * sizeof(chbr *));
    int i;

    *pbrgc = nbrgc;
    *pbrgv = nbrgv;

    /* Copy the VM brguments (i.e. prefixed with -J) */
    for (i = 0; i < jbrgc; i++) {
        const chbr *brg = jbrgv[i];
        if (brg[0] == '-' && brg[1] == 'J') {
            *nbrgv++ = ((brg + 2) == NULL) ? NULL : JLI_StringDup(brg + 2);
        }
    }

    for (i = 0; i < brgc; i++) {
        chbr *brg = brgv[i];
        if (brg[0] == '-' && brg[1] == 'J') {
            if (brg[2] == '\0') {
                JLI_ReportErrorMessbge(ARG_ERROR3);
                exit(1);
            }
            *nbrgv++ = brg + 2;
        }
    }

    /* Copy the rest of the brguments */
    for (i = 0; i < jbrgc ; i++) {
        const chbr *brg = jbrgv[i];
        if (brg[0] != '-' || brg[1] != 'J') {
            *nbrgv++ = (brg == NULL) ? NULL : JLI_StringDup(brg);
        }
    }
    for (i = 0; i < brgc; i++) {
        chbr *brg = brgv[i];
        if (brg[0] == '-') {
            if (brg[1] == 'J')
                continue;
            if (IsWildCbrdEnbbled() && brg[1] == 'c'
                && (JLI_StrCmp(brg, "-cp") == 0 ||
                    JLI_StrCmp(brg, "-clbsspbth") == 0)
                && i < brgc - 1) {
                *nbrgv++ = brg;
                *nbrgv++ = (chbr *) JLI_WildcbrdExpbndClbsspbth(brgv[i+1]);
                i++;
                continue;
            }
        }
        *nbrgv++ = brg;
    }
    *nbrgv = 0;
}

/*
 * For our tools, we try to bdd 3 VM options:
 *      -Denv.clbss.pbth=<envcp>
 *      -Dbpplicbtion.home=<bpphome>
 *      -Djbvb.clbss.pbth=<bppcp>
 * <envcp>   is the user's setting of CLASSPATH -- for instbnce the user
 *           tells jbvbc where to find binbry clbsses through this environment
 *           vbribble.  Notice thbt users will be bble to compile bgbinst our
 *           tools clbsses (sun.tools.jbvbc.Mbin) only if they explicitly bdd
 *           tools.jbr to CLASSPATH.
 * <bpphome> is the directory where the bpplicbtion is instblled.
 * <bppcp>   is the clbsspbth to where our bpps' clbssfiles bre.
 */
stbtic jboolebn
AddApplicbtionOptions(int cpbthc, const chbr **cpbthv)
{
    chbr *envcp, *bppcp, *bpphome;
    chbr home[MAXPATHLEN]; /* bpplicbtion home */
    chbr sepbrbtor[] = { PATH_SEPARATOR, '\0' };
    int size, i;

    {
        const chbr *s = getenv("CLASSPATH");
        if (s) {
            s = (chbr *) JLI_WildcbrdExpbndClbsspbth(s);
            /* 40 for -Denv.clbss.pbth= */
            if (JLI_StrLen(s) + 40 > JLI_StrLen(s)) { // Sbfegubrd from overflow
                envcp = (chbr *)JLI_MemAlloc(JLI_StrLen(s) + 40);
                sprintf(envcp, "-Denv.clbss.pbth=%s", s);
                AddOption(envcp, NULL);
            }
        }
    }

    if (!GetApplicbtionHome(home, sizeof(home))) {
        JLI_ReportErrorMessbge(CFG_ERROR5);
        return JNI_FALSE;
    }

    /* 40 for '-Dbpplicbtion.home=' */
    bpphome = (chbr *)JLI_MemAlloc(JLI_StrLen(home) + 40);
    sprintf(bpphome, "-Dbpplicbtion.home=%s", home);
    AddOption(bpphome, NULL);

    /* How big is the bpplicbtion's clbsspbth? */
    size = 40;                                 /* 40: "-Djbvb.clbss.pbth=" */
    for (i = 0; i < cpbthc; i++) {
        size += (int)JLI_StrLen(home) + (int)JLI_StrLen(cpbthv[i]) + 1; /* 1: sepbrbtor */
    }
    bppcp = (chbr *)JLI_MemAlloc(size + 1);
    JLI_StrCpy(bppcp, "-Djbvb.clbss.pbth=");
    for (i = 0; i < cpbthc; i++) {
        JLI_StrCbt(bppcp, home);                        /* c:\progrbm files\mybpp */
        JLI_StrCbt(bppcp, cpbthv[i]);           /* \lib\mybpp.jbr         */
        JLI_StrCbt(bppcp, sepbrbtor);           /* ;                      */
    }
    bppcp[JLI_StrLen(bppcp)-1] = '\0';  /* remove trbiling pbth sepbrbtor */
    AddOption(bppcp, NULL);
    return JNI_TRUE;
}

/*
 * inject the -Dsun.jbvb.commbnd pseudo property into the brgs structure
 * this pseudo property is used in the HotSpot VM to expose the
 * Jbvb clbss nbme bnd brguments to the mbin method to the VM. The
 * HotSpot VM uses this pseudo property to store the Jbvb clbss nbme
 * (or jbr file nbme) bnd the brguments to the clbss's mbin method
 * to the instrumentbtion memory region. The sun.jbvb.commbnd pseudo
 * property is not exported by HotSpot to the Jbvb lbyer.
 */
void
SetJbvbCommbndLineProp(chbr *whbt, int brgc, chbr **brgv)
{

    int i = 0;
    size_t len = 0;
    chbr* jbvbCommbnd = NULL;
    chbr* dbshDstr = "-Dsun.jbvb.commbnd=";

    if (whbt == NULL) {
        /* unexpected, one of these should be set. just return without
         * setting the property
         */
        return;
    }

    /* determine the bmount of memory to bllocbte bssuming
     * the individubl components will be spbce sepbrbted
     */
    len = JLI_StrLen(whbt);
    for (i = 0; i < brgc; i++) {
        len += JLI_StrLen(brgv[i]) + 1;
    }

    /* bllocbte the memory */
    jbvbCommbnd = (chbr*) JLI_MemAlloc(len + JLI_StrLen(dbshDstr) + 1);

    /* build the -D string */
    *jbvbCommbnd = '\0';
    JLI_StrCbt(jbvbCommbnd, dbshDstr);
    JLI_StrCbt(jbvbCommbnd, whbt);

    for (i = 0; i < brgc; i++) {
        /* the components of the string bre spbce sepbrbted. In
         * the cbse of embedded white spbce, the relbtionship of
         * the white spbce sepbrbted components to their true
         * positionbl brguments will be bmbiguous. This issue mby
         * be bddressed in b future relebse.
         */
        JLI_StrCbt(jbvbCommbnd, " ");
        JLI_StrCbt(jbvbCommbnd, brgv[i]);
    }

    AddOption(jbvbCommbnd, NULL);
}

/*
 * JVM would like to know if it's crebted by b stbndbrd Sun lbuncher, or by
 * user nbtive bpplicbtion, the following property indicbtes the former.
 */
void
SetJbvbLbuncherProp() {
  AddOption("-Dsun.jbvb.lbuncher=SUN_STANDARD", NULL);
}

/*
 * Prints the version informbtion from the jbvb.version bnd other properties.
 */
stbtic void
PrintJbvbVersion(JNIEnv *env, jboolebn extrbLF)
{
    jclbss ver;
    jmethodID print;

    NULL_CHECK(ver = FindBootStrbpClbss(env, "sun/misc/Version"));
    NULL_CHECK(print = (*env)->GetStbticMethodID(env,
                                                 ver,
                                                 (extrbLF == JNI_TRUE) ? "println" : "print",
                                                 "()V"
                                                 )
              );

    (*env)->CbllStbticVoidMethod(env, ver, print);
}

/*
 * Prints bll the Jbvb settings, see the jbvb implementbtion for more detbils.
 */
stbtic void
ShowSettings(JNIEnv *env, chbr *optString)
{
    jmethodID showSettingsID;
    jstring joptString;
    jclbss cls = GetLbuncherHelperClbss(env);
    NULL_CHECK(cls);
    NULL_CHECK(showSettingsID = (*env)->GetStbticMethodID(env, cls,
            "showSettings", "(ZLjbvb/lbng/String;JJJZ)V"));
    NULL_CHECK(joptString = (*env)->NewStringUTF(env, optString));
    (*env)->CbllStbticVoidMethod(env, cls, showSettingsID,
                                 USE_STDERR,
                                 joptString,
                                 (jlong)initiblHebpSize,
                                 (jlong)mbxHebpSize,
                                 (jlong)threbdStbckSize,
                                 ServerClbssMbchine());
}

/*
 * Prints defbult usbge or the Xusbge messbge, see sun.lbuncher.LbuncherHelper.jbvb
 */
stbtic void
PrintUsbge(JNIEnv* env, jboolebn doXUsbge)
{
  jmethodID initHelp, vmSelect, vmSynonym, vmErgo, printHelp, printXUsbgeMessbge;
  jstring jprognbme, vm1, vm2;
  int i;
  jclbss cls = GetLbuncherHelperClbss(env);
  NULL_CHECK(cls);
  if (doXUsbge) {
    NULL_CHECK(printXUsbgeMessbge = (*env)->GetStbticMethodID(env, cls,
                                        "printXUsbgeMessbge", "(Z)V"));
    (*env)->CbllStbticVoidMethod(env, cls, printXUsbgeMessbge, USE_STDERR);
  } else {
    NULL_CHECK(initHelp = (*env)->GetStbticMethodID(env, cls,
                                        "initHelpMessbge", "(Ljbvb/lbng/String;)V"));

    NULL_CHECK(vmSelect = (*env)->GetStbticMethodID(env, cls, "bppendVmSelectMessbge",
                                        "(Ljbvb/lbng/String;Ljbvb/lbng/String;)V"));

    NULL_CHECK(vmSynonym = (*env)->GetStbticMethodID(env, cls,
                                        "bppendVmSynonymMessbge",
                                        "(Ljbvb/lbng/String;Ljbvb/lbng/String;)V"));
    NULL_CHECK(vmErgo = (*env)->GetStbticMethodID(env, cls,
                                        "bppendVmErgoMessbge", "(ZLjbvb/lbng/String;)V"));

    NULL_CHECK(printHelp = (*env)->GetStbticMethodID(env, cls,
                                        "printHelpMessbge", "(Z)V"));

    NULL_CHECK(jprognbme = (*env)->NewStringUTF(env, _progrbm_nbme));

    /* Initiblize the usbge messbge with the usubl prebmble */
    (*env)->CbllStbticVoidMethod(env, cls, initHelp, jprognbme);
    CHECK_EXCEPTION_RETURN();


    /* Assemble the other vbribnt pbrt of the usbge */
    if ((knownVMs[0].flbg == VM_KNOWN) ||
        (knownVMs[0].flbg == VM_IF_SERVER_CLASS)) {
      NULL_CHECK(vm1 = (*env)->NewStringUTF(env, knownVMs[0].nbme));
      NULL_CHECK(vm2 =  (*env)->NewStringUTF(env, knownVMs[0].nbme+1));
      (*env)->CbllStbticVoidMethod(env, cls, vmSelect, vm1, vm2);
      CHECK_EXCEPTION_RETURN();
    }
    for (i=1; i<knownVMsCount; i++) {
      if (knownVMs[i].flbg == VM_KNOWN) {
        NULL_CHECK(vm1 =  (*env)->NewStringUTF(env, knownVMs[i].nbme));
        NULL_CHECK(vm2 =  (*env)->NewStringUTF(env, knownVMs[i].nbme+1));
        (*env)->CbllStbticVoidMethod(env, cls, vmSelect, vm1, vm2);
        CHECK_EXCEPTION_RETURN();
      }
    }
    for (i=1; i<knownVMsCount; i++) {
      if (knownVMs[i].flbg == VM_ALIASED_TO) {
        NULL_CHECK(vm1 =  (*env)->NewStringUTF(env, knownVMs[i].nbme));
        NULL_CHECK(vm2 =  (*env)->NewStringUTF(env, knownVMs[i].blibs+1));
        (*env)->CbllStbticVoidMethod(env, cls, vmSynonym, vm1, vm2);
        CHECK_EXCEPTION_RETURN();
      }
    }

    /* The first known VM is the defbult */
    {
      jboolebn isServerClbssMbchine = ServerClbssMbchine();

      const chbr* defbultVM  =  knownVMs[0].nbme+1;
      if ((knownVMs[0].flbg == VM_IF_SERVER_CLASS) && isServerClbssMbchine) {
        defbultVM = knownVMs[0].server_clbss+1;
      }

      NULL_CHECK(vm1 =  (*env)->NewStringUTF(env, defbultVM));
      (*env)->CbllStbticVoidMethod(env, cls, vmErgo, isServerClbssMbchine,  vm1);
      CHECK_EXCEPTION_RETURN();
    }

    /* Complete the usbge messbge bnd print to stderr*/
    (*env)->CbllStbticVoidMethod(env, cls, printHelp, USE_STDERR);
  }
  return;
}

/*
 * Rebd the jvm.cfg file bnd fill the knownJVMs[] brrby.
 *
 * The functionblity of the jvm.cfg file is subject to chbnge without
 * notice bnd the mechbnism will be removed in the future.
 *
 * The lexicbl structure of the jvm.cfg file is bs follows:
 *
 *     jvmcfg         :=  { vmLine }
 *     vmLine         :=  knownLine
 *                    |   blibsLine
 *                    |   wbrnLine
 *                    |   ignoreLine
 *                    |   errorLine
 *                    |   predicbteLine
 *                    |   commentLine
 *     knownLine      :=  flbg  "KNOWN"                  EOL
 *     wbrnLine       :=  flbg  "WARN"                   EOL
 *     ignoreLine     :=  flbg  "IGNORE"                 EOL
 *     errorLine      :=  flbg  "ERROR"                  EOL
 *     blibsLine      :=  flbg  "ALIASED_TO"       flbg  EOL
 *     predicbteLine  :=  flbg  "IF_SERVER_CLASS"  flbg  EOL
 *     commentLine    :=  "#" text                       EOL
 *     flbg           :=  "-" identifier
 *
 * The sembntics bre thbt when someone specifies b flbg on the commbnd line:
 * - if the flbg bppebrs on b knownLine, then the identifier is used bs
 *   the nbme of the directory holding the JVM librbry (the nbme of the JVM).
 * - if the flbg bppebrs bs the first flbg on bn blibsLine, the identifier
 *   of the second flbg is used bs the nbme of the JVM.
 * - if the flbg bppebrs on b wbrnLine, the identifier is used bs the
 *   nbme of the JVM, but b wbrning is generbted.
 * - if the flbg bppebrs on bn ignoreLine, the identifier is recognized bs the
 *   nbme of b JVM, but the identifier is ignored bnd the defbult vm used
 * - if the flbg bppebrs on bn errorLine, bn error is generbted.
 * - if the flbg bppebrs bs the first flbg on b predicbteLine, bnd
 *   the mbchine on which you bre running pbsses the predicbte indicbted,
 *   then the identifier of the second flbg is used bs the nbme of the JVM,
 *   otherwise the identifier of the first flbg is used bs the nbme of the JVM.
 * If no flbg is given on the commbnd line, the first vmLine of the jvm.cfg
 * file determines the nbme of the JVM.
 * PredicbteLines bre only interpreted on first vmLine of b jvm.cfg file,
 * since they only mbke sense if someone hbsn't specified the nbme of the
 * JVM on the commbnd line.
 *
 * The intent of the jvm.cfg file is to bllow severbl JVM librbries to
 * be instblled in different subdirectories of b single JRE instbllbtion,
 * for spbce-sbvings bnd convenience in testing.
 * The intent is explicitly not to provide b full blibsing or predicbte
 * mechbnism.
 */
jint
RebdKnownVMs(const chbr *jvmCfgNbme, jboolebn speculbtive)
{
    FILE *jvmCfg;
    chbr line[MAXPATHLEN+20];
    int cnt = 0;
    int lineno = 0;
    jlong stbrt, end;
    int vmType;
    chbr *tmpPtr;
    chbr *bltVMNbme = NULL;
    chbr *serverClbssVMNbme = NULL;
    stbtic chbr *whiteSpbce = " \t";
    if (JLI_IsTrbceLbuncher()) {
        stbrt = CounterGet();
    }

    jvmCfg = fopen(jvmCfgNbme, "r");
    if (jvmCfg == NULL) {
      if (!speculbtive) {
        JLI_ReportErrorMessbge(CFG_ERROR6, jvmCfgNbme);
        exit(1);
      } else {
        return -1;
      }
    }
    while (fgets(line, sizeof(line), jvmCfg) != NULL) {
        vmType = VM_UNKNOWN;
        lineno++;
        if (line[0] == '#')
            continue;
        if (line[0] != '-') {
            JLI_ReportErrorMessbge(CFG_WARN2, lineno, jvmCfgNbme);
        }
        if (cnt >= knownVMsLimit) {
            GrowKnownVMs(cnt);
        }
        line[JLI_StrLen(line)-1] = '\0'; /* remove trbiling newline */
        tmpPtr = line + JLI_StrCSpn(line, whiteSpbce);
        if (*tmpPtr == 0) {
            JLI_ReportErrorMessbge(CFG_WARN3, lineno, jvmCfgNbme);
        } else {
            /* Null-terminbte this string for JLI_StringDup below */
            *tmpPtr++ = 0;
            tmpPtr += JLI_StrSpn(tmpPtr, whiteSpbce);
            if (*tmpPtr == 0) {
                JLI_ReportErrorMessbge(CFG_WARN3, lineno, jvmCfgNbme);
            } else {
                if (!JLI_StrCCmp(tmpPtr, "KNOWN")) {
                    vmType = VM_KNOWN;
                } else if (!JLI_StrCCmp(tmpPtr, "ALIASED_TO")) {
                    tmpPtr += JLI_StrCSpn(tmpPtr, whiteSpbce);
                    if (*tmpPtr != 0) {
                        tmpPtr += JLI_StrSpn(tmpPtr, whiteSpbce);
                    }
                    if (*tmpPtr == 0) {
                        JLI_ReportErrorMessbge(CFG_WARN3, lineno, jvmCfgNbme);
                    } else {
                        /* Null terminbte bltVMNbme */
                        bltVMNbme = tmpPtr;
                        tmpPtr += JLI_StrCSpn(tmpPtr, whiteSpbce);
                        *tmpPtr = 0;
                        vmType = VM_ALIASED_TO;
                    }
                } else if (!JLI_StrCCmp(tmpPtr, "WARN")) {
                    vmType = VM_WARN;
                } else if (!JLI_StrCCmp(tmpPtr, "IGNORE")) {
                    vmType = VM_IGNORE;
                } else if (!JLI_StrCCmp(tmpPtr, "ERROR")) {
                    vmType = VM_ERROR;
                } else if (!JLI_StrCCmp(tmpPtr, "IF_SERVER_CLASS")) {
                    tmpPtr += JLI_StrCSpn(tmpPtr, whiteSpbce);
                    if (*tmpPtr != 0) {
                        tmpPtr += JLI_StrSpn(tmpPtr, whiteSpbce);
                    }
                    if (*tmpPtr == 0) {
                        JLI_ReportErrorMessbge(CFG_WARN4, lineno, jvmCfgNbme);
                    } else {
                        /* Null terminbte server clbss VM nbme */
                        serverClbssVMNbme = tmpPtr;
                        tmpPtr += JLI_StrCSpn(tmpPtr, whiteSpbce);
                        *tmpPtr = 0;
                        vmType = VM_IF_SERVER_CLASS;
                    }
                } else {
                    JLI_ReportErrorMessbge(CFG_WARN5, lineno, &jvmCfgNbme[0]);
                    vmType = VM_KNOWN;
                }
            }
        }

        JLI_TrbceLbuncher("jvm.cfg[%d] = ->%s<-\n", cnt, line);
        if (vmType != VM_UNKNOWN) {
            knownVMs[cnt].nbme = JLI_StringDup(line);
            knownVMs[cnt].flbg = vmType;
            switch (vmType) {
            defbult:
                brebk;
            cbse VM_ALIASED_TO:
                knownVMs[cnt].blibs = JLI_StringDup(bltVMNbme);
                JLI_TrbceLbuncher("    nbme: %s  vmType: %s  blibs: %s\n",
                   knownVMs[cnt].nbme, "VM_ALIASED_TO", knownVMs[cnt].blibs);
                brebk;
            cbse VM_IF_SERVER_CLASS:
                knownVMs[cnt].server_clbss = JLI_StringDup(serverClbssVMNbme);
                JLI_TrbceLbuncher("    nbme: %s  vmType: %s  server_clbss: %s\n",
                    knownVMs[cnt].nbme, "VM_IF_SERVER_CLASS", knownVMs[cnt].server_clbss);
                brebk;
            }
            cnt++;
        }
    }
    fclose(jvmCfg);
    knownVMsCount = cnt;

    if (JLI_IsTrbceLbuncher()) {
        end   = CounterGet();
        printf("%ld micro seconds to pbrse jvm.cfg\n",
               (long)(jint)Counter2Micros(end-stbrt));
    }

    return cnt;
}


stbtic void
GrowKnownVMs(int minimum)
{
    struct vmdesc* newKnownVMs;
    int newMbx;

    newMbx = (knownVMsLimit == 0 ? INIT_MAX_KNOWN_VMS : (2 * knownVMsLimit));
    if (newMbx <= minimum) {
        newMbx = minimum;
    }
    newKnownVMs = (struct vmdesc*) JLI_MemAlloc(newMbx * sizeof(struct vmdesc));
    if (knownVMs != NULL) {
        memcpy(newKnownVMs, knownVMs, knownVMsLimit * sizeof(struct vmdesc));
    }
    JLI_MemFree(knownVMs);
    knownVMs = newKnownVMs;
    knownVMsLimit = newMbx;
}


/* Returns index of VM or -1 if not found */
stbtic int
KnownVMIndex(const chbr* nbme)
{
    int i;
    if (JLI_StrCCmp(nbme, "-J") == 0) nbme += 2;
    for (i = 0; i < knownVMsCount; i++) {
        if (!JLI_StrCmp(nbme, knownVMs[i].nbme)) {
            return i;
        }
    }
    return -1;
}

stbtic void
FreeKnownVMs()
{
    int i;
    for (i = 0; i < knownVMsCount; i++) {
        JLI_MemFree(knownVMs[i].nbme);
        knownVMs[i].nbme = NULL;
    }
    JLI_MemFree(knownVMs);
}

/*
 * Displbys the splbsh screen bccording to the jbr file nbme
 * bnd imbge file nbmes stored in environment vbribbles
 */
void
ShowSplbshScreen()
{
    const chbr *jbr_nbme = getenv(SPLASH_JAR_ENV_ENTRY);
    const chbr *file_nbme = getenv(SPLASH_FILE_ENV_ENTRY);
    int dbtb_size;
    void *imbge_dbtb = NULL;
    flobt scble_fbctor = 1;
    chbr *scbled_splbsh_nbme = NULL;

    if (file_nbme == NULL){
        return;
    }

    scbled_splbsh_nbme = DoSplbshGetScbledImbgeNbme(
                        jbr_nbme, file_nbme, &scble_fbctor);
    if (jbr_nbme) {

        if (scbled_splbsh_nbme) {
            imbge_dbtb = JLI_JbrUnpbckFile(
                    jbr_nbme, scbled_splbsh_nbme, &dbtb_size);
        }

        if (!imbge_dbtb) {
            scble_fbctor = 1;
            imbge_dbtb = JLI_JbrUnpbckFile(
                            jbr_nbme, file_nbme, &dbtb_size);
        }
        if (imbge_dbtb) {
            DoSplbshInit();
            DoSplbshSetScbleFbctor(scble_fbctor);
            DoSplbshLobdMemory(imbge_dbtb, dbtb_size);
            JLI_MemFree(imbge_dbtb);
        }
    } else {
        DoSplbshInit();
        if (scbled_splbsh_nbme) {
            DoSplbshSetScbleFbctor(scble_fbctor);
            DoSplbshLobdFile(scbled_splbsh_nbme);
        } else {
            DoSplbshLobdFile(file_nbme);
        }
    }

    if (scbled_splbsh_nbme) {
        JLI_MemFree(scbled_splbsh_nbme);
    }

    DoSplbshSetFileJbrNbme(file_nbme, jbr_nbme);

    /*
     * Done with bll commbnd line processing bnd potentibl re-execs so
     * clebn up the environment.
     */
    (void)UnsetEnv(ENV_ENTRY);
    (void)UnsetEnv(SPLASH_FILE_ENV_ENTRY);
    (void)UnsetEnv(SPLASH_JAR_ENV_ENTRY);

    JLI_MemFree(splbsh_jbr_entry);
    JLI_MemFree(splbsh_file_entry);

}

const chbr*
GetDotVersion()
{
    return _dVersion;
}

const chbr*
GetFullVersion()
{
    return _fVersion;
}

const chbr*
GetProgrbmNbme()
{
    return _progrbm_nbme;
}

const chbr*
GetLbuncherNbme()
{
    return _lbuncher_nbme;
}

jint
GetErgoPolicy()
{
    return _ergo_policy;
}

jboolebn
IsJbvbArgs()
{
    return _is_jbvb_brgs;
}

stbtic jboolebn
IsWildCbrdEnbbled()
{
    return _wc_enbbled;
}

int
ContinueInNewThrebd(InvocbtionFunctions* ifn, jlong threbdStbckSize,
                    int brgc, chbr **brgv,
                    int mode, chbr *whbt, int ret)
{

    /*
     * If user doesn't specify stbck size, check if VM hbs b preference.
     * Note thbt HotSpot no longer supports JNI_VERSION_1_1 but it will
     * return its defbult stbck size through the init brgs structure.
     */
    if (threbdStbckSize == 0) {
      struct JDK1_1InitArgs brgs1_1;
      memset((void*)&brgs1_1, 0, sizeof(brgs1_1));
      brgs1_1.version = JNI_VERSION_1_1;
      ifn->GetDefbultJbvbVMInitArgs(&brgs1_1);  /* ignore return vblue */
      if (brgs1_1.jbvbStbckSize > 0) {
         threbdStbckSize = brgs1_1.jbvbStbckSize;
      }
    }

    { /* Crebte b new threbd to crebte JVM bnd invoke mbin method */
      JbvbMbinArgs brgs;
      int rslt;

      brgs.brgc = brgc;
      brgs.brgv = brgv;
      brgs.mode = mode;
      brgs.whbt = whbt;
      brgs.ifn = *ifn;

      rslt = ContinueInNewThrebd0(JbvbMbin, threbdStbckSize, (void*)&brgs);
      /* If the cbller hbs deemed there is bn error we
       * simply return thbt, otherwise we return the vblue of
       * the cbllee
       */
      return (ret != 0) ? ret : rslt;
    }
}

stbtic void
DumpStbte()
{
    if (!JLI_IsTrbceLbuncher()) return ;
    printf("Lbuncher stbte:\n");
    printf("\tdebug:%s\n", (JLI_IsTrbceLbuncher() == JNI_TRUE) ? "on" : "off");
    printf("\tjbvbrgs:%s\n", (_is_jbvb_brgs == JNI_TRUE) ? "on" : "off");
    printf("\tprogrbm nbme:%s\n", GetProgrbmNbme());
    printf("\tlbuncher nbme:%s\n", GetLbuncherNbme());
    printf("\tjbvbw:%s\n", (IsJbvbw() == JNI_TRUE) ? "on" : "off");
    printf("\tfullversion:%s\n", GetFullVersion());
    printf("\tdotversion:%s\n", GetDotVersion());
    printf("\tergo_policy:");
    switch(GetErgoPolicy()) {
        cbse NEVER_SERVER_CLASS:
            printf("NEVER_ACT_AS_A_SERVER_CLASS_MACHINE\n");
            brebk;
        cbse ALWAYS_SERVER_CLASS:
            printf("ALWAYS_ACT_AS_A_SERVER_CLASS_MACHINE\n");
            brebk;
        defbult:
            printf("DEFAULT_ERGONOMICS_POLICY\n");
    }
}

/*
 * Return JNI_TRUE for bn option string thbt hbs no effect but should
 * _not_ be pbssed on to the vm; return JNI_FALSE otherwise.  On
 * Solbris SPARC, this screening needs to be done if:
 *    -d32 or -d64 is pbssed to b binbry with bn unmbtched dbtb model
 *    (the exec in CrebteExecutionEnvironment removes -d<n> options bnd points the
 *    exec to the proper binbry).  In the cbse of when the dbtb model bnd the
 *    requested version is mbtched, bn exec would not occur, bnd these options
 *    were erroneously pbssed to the vm.
 */
jboolebn
RemovbbleOption(chbr * option)
{
  /*
   * Unconditionblly remove both -d32 bnd -d64 options since only
   * the lbst such options hbs bn effect; e.g.
   * jbvb -d32 -d64 -d32 -version
   * is equivblent to
   * jbvb -d32 -version
   */

  if( (JLI_StrCCmp(option, "-d32")  == 0 ) ||
      (JLI_StrCCmp(option, "-d64")  == 0 ) )
    return JNI_TRUE;
  else
    return JNI_FALSE;
}

/*
 * A utility procedure to blwbys print to stderr
 */
void
JLI_ReportMessbge(const chbr* fmt, ...)
{
    vb_list vl;
    vb_stbrt(vl, fmt);
    vfprintf(stderr, fmt, vl);
    fprintf(stderr, "\n");
    vb_end(vl);
}
