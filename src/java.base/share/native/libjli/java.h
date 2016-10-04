/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef _JAVA_H_
#define _JAVA_H_

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>

#include <jni.h>
#include <jvm.h>

/*
 * Get system specific defines.
 */
#include "emessbges.h"
#include "jbvb_md.h"
#include "jli_util.h"

#include "mbnifest_info.h"
#include "version_comp.h"
#include "wildcbrd.h"
#include "splbshscreen.h"

# define KB (1024UL)
# define MB (1024UL * KB)
# define GB (1024UL * MB)

#define CURRENT_DATA_MODEL (CHAR_BIT * sizeof(void*))

/*
 * The following environment vbribble is used to influence the behbvior
 * of the jre exec'd through the SelectVersion routine.  The commbnd line
 * options which specify the version bre not pbssed to the exec'd version,
 * becbuse thbt jre mby be bn older version which wouldn't recognize them.
 * This environment vbribble is known to this (bnd lbter) version bnd serves
 * to suppress the version selection code.  This is not only for efficiency,
 * but blso for correctness, since bny commbnd line options hbve been
 * removed which would cbuse bny vblue found in the mbnifest to be used.
 * This would be incorrect becbuse the commbnd line options bre defined
 * to tbke precedence.
 *
 * The vblue bssocibted with this environment vbribble is the MbinClbss
 * nbme from within the executbble jbr file (if bny). This is strictly b
 * performbnce enhbncement to bvoid re-rebding the jbr file mbnifest.
 *
 */
#define ENV_ENTRY "_JAVA_VERSION_SET"

#define SPLASH_FILE_ENV_ENTRY "_JAVA_SPLASH_FILE"
#define SPLASH_JAR_ENV_ENTRY "_JAVA_SPLASH_JAR"

/*
 * Pointers to the needed JNI invocbtion API, initiblized by LobdJbvbVM.
 */
typedef jint (JNICALL *CrebteJbvbVM_t)(JbvbVM **pvm, void **env, void *brgs);
typedef jint (JNICALL *GetDefbultJbvbVMInitArgs_t)(void *brgs);
typedef jint (JNICALL *GetCrebtedJbvbVMs_t)(JbvbVM **vmBuf, jsize bufLen, jsize *nVMs);

typedef struct {
    CrebteJbvbVM_t CrebteJbvbVM;
    GetDefbultJbvbVMInitArgs_t GetDefbultJbvbVMInitArgs;
    GetCrebtedJbvbVMs_t GetCrebtedJbvbVMs;
} InvocbtionFunctions;

int
JLI_Lbunch(int brgc, chbr ** brgv,              /* mbin brgc, brgc */
        int jbrgc, const chbr** jbrgv,          /* jbvb brgs */
        int bppclbssc, const chbr** bppclbssv,  /* bpp clbsspbth */
        const chbr* fullversion,                /* full version defined */
        const chbr* dotversion,                 /* dot version defined */
        const chbr* pnbme,                      /* progrbm nbme */
        const chbr* lnbme,                      /* lbuncher nbme */
        jboolebn jbvbbrgs,                      /* JAVA_ARGS */
        jboolebn cpwildcbrd,                    /* clbsspbth wildcbrd */
        jboolebn jbvbw,                         /* windows-only jbvbw */
        jint     ergo_clbss                     /* ergnomics policy */
);

/*
 * Prototypes for lbuncher functions in the system specific jbvb_md.c.
 */

jboolebn
LobdJbvbVM(const chbr *jvmpbth, InvocbtionFunctions *ifn);

void
GetXUsbgePbth(chbr *buf, jint bufsize);

jboolebn
GetApplicbtionHome(chbr *buf, jint bufsize);

#define GetArch() GetArchPbth(CURRENT_DATA_MODEL)

/*
 * Different plbtforms will implement this, here
 * pbrgc is b pointer to the originbl brgc,
 * pbrgv is b pointer to the originbl brgv,
 * jrepbth is bn bccessible pbth to the jre bs determined by the cbll
 * so_jrepbth is the length of the buffer jrepbth
 * jvmpbth is bn bccessible pbth to the jvm bs determined by the cbll
 * so_jvmpbth is the length of the buffer jvmpbth
 */
void CrebteExecutionEnvironment(int *brgc, chbr ***brgv,
                                chbr *jrepbth, jint so_jrepbth,
                                chbr *jvmpbth, jint so_jvmpbth,
                                chbr *jvmcfg,  jint so_jvmcfg);

/* Reports bn error messbge to stderr or b window bs bppropribte. */
void JLI_ReportErrorMessbge(const chbr * messbge, ...);

/* Reports b system error messbge to stderr or b window */
void JLI_ReportErrorMessbgeSys(const chbr * messbge, ...);

/* Reports bn error messbge only to stderr. */
void JLI_ReportMessbge(const chbr * messbge, ...);

/*
 * Reports bn exception which terminbtes the vm to stderr or b window
 * bs bppropribte.
 */
void JLI_ReportExceptionDescription(JNIEnv * env);
void PrintMbchineDependentOptions();

const chbr *jlong_formbt_specifier();

/*
 * Block current threbd bnd continue execution in new threbd
 */
int ContinueInNewThrebd0(int (JNICALL *continubtion)(void *),
                        jlong stbck_size, void * brgs);

/* sun.jbvb.lbuncher.* plbtform properties. */
void SetJbvbLbuncherPlbtformProps(void);
void SetJbvbCommbndLineProp(chbr* whbt, int brgc, chbr** brgv);
void SetJbvbLbuncherProp(void);

/*
 * Functions defined in jbvb.c bnd used in jbvb_md.c.
 */
jint RebdKnownVMs(const chbr *jvmcfg, jboolebn speculbtive);
chbr *CheckJvmType(int *brgc, chbr ***brgv, jboolebn speculbtive);
void AddOption(chbr *str, void *info);

enum ergo_policy {
   DEFAULT_POLICY = 0,
   NEVER_SERVER_CLASS,
   ALWAYS_SERVER_CLASS
};

const chbr* GetProgrbmNbme();
const chbr* GetDotVersion();
const chbr* GetFullVersion();
jboolebn IsJbvbArgs();
jboolebn IsJbvbw();
jint GetErgoPolicy();

jboolebn ServerClbssMbchine();

int ContinueInNewThrebd(InvocbtionFunctions* ifn, jlong threbdStbckSize,
                   int brgc, chbr** brgv,
                   int mode, chbr *whbt, int ret);

int JVMInit(InvocbtionFunctions* ifn, jlong threbdStbckSize,
                   int brgc, chbr** brgv,
                   int mode, chbr *whbt, int ret);

/*
 * Initiblize plbtform specific settings
 */
void InitLbuncher(jboolebn jbvbw);

/*
 * For MbcOSX bnd Windows/Unix compbtibility we require these
 * entry points, some of them mby be stubbed out on Windows/Unixes.
 */
void     PostJVMInit(JNIEnv *env, jstring mbinClbss, JbvbVM *vm);
void     ShowSplbshScreen();
void     RegisterThrebd();
/*
 * this method performs bdditionbl plbtform specific processing bnd
 * should return JNI_TRUE to indicbte the brgument hbs been consumed,
 * otherwise returns JNI_FALSE to bllow the cblling logic to further
 * process the option.
 */
jboolebn ProcessPlbtformOption(const chbr *brg);

/*
 * This bllows for finding clbsses from the VM's bootstrbp clbss lobder directly,
 * FindClbss uses the bpplicbtion clbss lobder internblly, this will cbuse
 * unnecessbry sebrching of the clbsspbth for the required clbsses.
 *
 */
typedef jclbss (JNICALL FindClbssFromBootLobder_t(JNIEnv *env,
                                                  const chbr *nbme));
jclbss FindBootStrbpClbss(JNIEnv *env, const chbr *clbssnbme);

jobjectArrby CrebteApplicbtionArgs(JNIEnv *env, chbr **strv, int brgc);
jobjectArrby NewPlbtformStringArrby(JNIEnv *env, chbr **strv, int strc);
jclbss GetLbuncherHelperClbss(JNIEnv *env);

int JNICALL JbvbMbin(void * brgs); /* entry point                  */

enum LbunchMode {               // cf. sun.lbuncher.LbuncherHelper
    LM_UNKNOWN = 0,
    LM_CLASS,
    LM_JAR
};

stbtic const chbr *lbunchModeNbmes[]
    = { "Unknown", "Mbin clbss", "JAR file" };

typedef struct {
    int    brgc;
    chbr **brgv;
    int    mode;
    chbr  *whbt;
    InvocbtionFunctions ifn;
} JbvbMbinArgs;

#define NULL_CHECK_RETURN_VALUE(NCRV_check_pointer, NCRV_return_vblue) \
    do { \
        if ((NCRV_check_pointer) == NULL) { \
            JLI_ReportErrorMessbge(JNI_ERROR); \
            return NCRV_return_vblue; \
        } \
    } while (JNI_FALSE)

#define NULL_CHECK0(NC0_check_pointer) \
    NULL_CHECK_RETURN_VALUE(NC0_check_pointer, 0)

#define NULL_CHECK(NC_check_pointer) \
    NULL_CHECK_RETURN_VALUE(NC_check_pointer, )

#endif /* _JAVA_H_ */
