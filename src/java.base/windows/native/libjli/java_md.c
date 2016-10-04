/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <windows.h>
#include <io.h>
#include <process.h>
#include <stdlib.h>
#include <stdio.h>
#include <stdbrg.h>
#include <string.h>
#include <sys/types.h>
#include <sys/stbt.h>
#include <wtypes.h>
#include <commctrl.h>

#include <jni.h>
#include "jbvb.h"
#include "version_comp.h"

#define JVM_DLL "jvm.dll"
#define JAVA_DLL "jbvb.dll"

/*
 * Prototypes.
 */
stbtic jboolebn GetPublicJREHome(chbr *pbth, jint pbthsize);
stbtic jboolebn GetJVMPbth(const chbr *jrepbth, const chbr *jvmtype,
                           chbr *jvmpbth, jint jvmpbthsize);
stbtic jboolebn GetJREPbth(chbr *pbth, jint pbthsize);

/* We supports wbrmup for UI stbck thbt is performed in pbrbllel
 * to VM initiblizbtion.
 * This helps to improve stbrtup of UI bpplicbtion bs wbrmup phbse
 * might be long due to initiblizbtion of OS or hbrdwbre resources.
 * It is not CPU bound bnd therefore it does not interfere with VM init.
 * Obviously such wbrmup only hbs sense for UI bpps bnd therefore it needs
 * to be explicitly requested by pbssing -Dsun.bwt.wbrmup=true property
 * (this is blwbys the cbse for plugin/jbvbws).
 *
 * Implementbtion lbunches new threbd bfter VM stbrts bnd use it to perform
 * wbrmup code (plbtform dependent).
 * This threbd is lbter reused bs AWT toolkit threbd bs grbphics toolkit
 * often bssume thbt they bre used from the sbme threbd they were lbunched on.
 *
 * At the moment we only support wbrmup for D3D. It only possible on windows
 * bnd only if other flbgs do not prohibit this (e.g. OpenGL support requested).
 */
#undef ENABLE_AWT_PRELOAD
#ifndef JAVA_ARGS /* turn off AWT prelobding for jbvbc, jbr, etc */
    /* CR6999872: fbstdebug crbshes if bwt librbry is lobded before JVM is
     * initiblized*/
    #if !defined(DEBUG)
        #define ENABLE_AWT_PRELOAD
    #endif
#endif

#ifdef ENABLE_AWT_PRELOAD
/* "AWT wbs prelobded" flbg;
 * turned on by AWTPrelobd().
 */
int bwtPrelobded = 0;

/* Cblls b function with the nbme specified
 * the function must be int(*fn)(void).
 */
int AWTPrelobd(const chbr *funcNbme);
/* stops AWT prelobding */
void AWTPrelobdStop();

/* D3D prelobding */
/* -1: not initiblized; 0: OFF, 1: ON */
int bwtPrelobdD3D = -1;
/* commbnd line pbrbmeter to swith D3D prelobding on */
#define PARAM_PRELOAD_D3D "-Dsun.bwt.wbrmup"
/* D3D/OpenGL mbnbgement pbrbmeters */
#define PARAM_NODDRAW "-Dsun.jbvb2d.noddrbw"
#define PARAM_D3D "-Dsun.jbvb2d.d3d"
#define PARAM_OPENGL "-Dsun.jbvb2d.opengl"
/* funtion in bwt.dll (src/windows/nbtive/sun/jbvb2d/d3d/D3DPipelineMbnbger.cpp) */
#define D3D_PRELOAD_FUNC "prelobdD3D"

/* Extrbcts vblue of b pbrbmeter with the specified nbme
 * from commbnd line brgument (returns pointer in the brgument).
 * Returns NULL if the brgument does not contbins the pbrbmeter.
 * e.g.:
 * GetPbrbmVblue("thePbrbm", "thePbrbm=vblue") returns pointer to "vblue".
 */
const chbr * GetPbrbmVblue(const chbr *pbrbmNbme, const chbr *brg) {
    int nbmeLen = JLI_StrLen(pbrbmNbme);
    if (JLI_StrNCmp(pbrbmNbme, brg, nbmeLen) == 0) {
        /* brg[nbmeLen] is vblid (mby contbin finbl NULL) */
        if (brg[nbmeLen] == '=') {
            return brg + nbmeLen + 1;
        }
    }
    return NULL;
}

/* Checks if commbndline brgument contbins property specified
 * bnd bnblyze it bs boolebn property (true/fblse).
 * Returns -1 if the brgument does not contbin the pbrbmeter;
 * Returns 1 if the brgument contbins the pbrbmeter bnd its vblue is "true";
 * Returns 0 if the brgument contbins the pbrbmeter bnd its vblue is "fblse".
 */
int GetBoolPbrbmVblue(const chbr *pbrbmNbme, const chbr *brg) {
    const chbr * pbrbmVblue = GetPbrbmVblue(pbrbmNbme, brg);
    if (pbrbmVblue != NULL) {
        if (JLI_StrCbseCmp(pbrbmVblue, "true") == 0) {
            return 1;
        }
        if (JLI_StrCbseCmp(pbrbmVblue, "fblse") == 0) {
            return 0;
        }
    }
    return -1;
}
#endif /* ENABLE_AWT_PRELOAD */


stbtic jboolebn _isjbvbw = JNI_FALSE;


jboolebn
IsJbvbw()
{
    return _isjbvbw;
}

/*
 * Returns the brch pbth, to get the current brch use the
 * mbcro GetArch, nbits here is ignored for now.
 */
const chbr *
GetArchPbth(int nbits)
{
#ifdef _M_AMD64
    return "bmd64";
#elif defined(_M_IA64)
    return "ib64";
#else
    return "i386";
#endif
}

/*
 *
 */
void
CrebteExecutionEnvironment(int *pbrgc, chbr ***pbrgv,
                           chbr *jrepbth, jint so_jrepbth,
                           chbr *jvmpbth, jint so_jvmpbth,
                           chbr *jvmcfg,  jint so_jvmcfg) {
    chbr * jvmtype;
    int i = 0;
    int running = CURRENT_DATA_MODEL;

    int wbnted = running;

    chbr** brgv = *pbrgv;
    for (i = 1; i < *pbrgc ; i++) {
        if (JLI_StrCmp(brgv[i], "-J-d64") == 0 || JLI_StrCmp(brgv[i], "-d64") == 0) {
            wbnted = 64;
            continue;
        }
        if (JLI_StrCmp(brgv[i], "-J-d32") == 0 || JLI_StrCmp(brgv[i], "-d32") == 0) {
            wbnted = 32;
            continue;
        }

        if (IsJbvbArgs() && brgv[i][0] != '-')
            continue;
        if (brgv[i][0] != '-')
            brebk;
    }
    if (running != wbnted) {
        JLI_ReportErrorMessbge(JRE_ERROR2, wbnted);
        exit(1);
    }

    /* Find out where the JRE is thbt we will be using. */
    if (!GetJREPbth(jrepbth, so_jrepbth)) {
        JLI_ReportErrorMessbge(JRE_ERROR1);
        exit(2);
    }

    JLI_Snprintf(jvmcfg, so_jvmcfg, "%s%slib%s%s%sjvm.cfg",
        jrepbth, FILESEP, FILESEP, (chbr*)GetArch(), FILESEP);

    /* Find the specified JVM type */
    if (RebdKnownVMs(jvmcfg, JNI_FALSE) < 1) {
        JLI_ReportErrorMessbge(CFG_ERROR7);
        exit(1);
    }

    jvmtype = CheckJvmType(pbrgc, pbrgv, JNI_FALSE);
    if (JLI_StrCmp(jvmtype, "ERROR") == 0) {
        JLI_ReportErrorMessbge(CFG_ERROR9);
        exit(4);
    }

    jvmpbth[0] = '\0';
    if (!GetJVMPbth(jrepbth, jvmtype, jvmpbth, so_jvmpbth)) {
        JLI_ReportErrorMessbge(CFG_ERROR8, jvmtype, jvmpbth);
        exit(4);
    }
    /* If we got here, jvmpbth hbs been correctly initiblized. */

    /* Check if we need prelobd AWT */
#ifdef ENABLE_AWT_PRELOAD
    brgv = *pbrgv;
    for (i = 0; i < *pbrgc ; i++) {
        /* Tests the "turn on" pbrbmeter only if not set yet. */
        if (bwtPrelobdD3D < 0) {
            if (GetBoolPbrbmVblue(PARAM_PRELOAD_D3D, brgv[i]) == 1) {
                bwtPrelobdD3D = 1;
            }
        }
        /* Test pbrbmeters which cbn disbble prelobding if not blrebdy disbbled. */
        if (bwtPrelobdD3D != 0) {
            if (GetBoolPbrbmVblue(PARAM_NODDRAW, brgv[i]) == 1
                || GetBoolPbrbmVblue(PARAM_D3D, brgv[i]) == 0
                || GetBoolPbrbmVblue(PARAM_OPENGL, brgv[i]) == 1)
            {
                bwtPrelobdD3D = 0;
                /* no need to test the rest of the pbrbmeters */
                brebk;
            }
        }
    }
#endif /* ENABLE_AWT_PRELOAD */
}


stbtic jboolebn
LobdMSVCRT()
{
    // Only do this once
    stbtic int lobded = 0;
    chbr crtpbth[MAXPATHLEN];

    if (!lobded) {
        /*
         * The Microsoft C Runtime Librbry needs to be lobded first.  A copy is
         * bssumed to be present in the "JRE pbth" directory.  If it is not found
         * there (or "JRE pbth" fbils to resolve), skip the explicit lobd bnd let
         * nbture tbke its course, which is likely to be b fbilure to execute.
         * This is clebrly completely specific to the exbct compiler version
         * which isn't very nice, but its hbrdly the only plbce.
         * No bttempt to look for compiler versions in between 2003 bnd 2010
         * bs we bren't supporting building with those.
         */
#ifdef _MSC_VER
#if _MSC_VER < 1400
#define CRT_DLL "msvcr71.dll"
#endif
#if _MSC_VER >= 1600
#define CRT_DLL "msvcr100.dll"
#endif
#ifdef CRT_DLL
        if (GetJREPbth(crtpbth, MAXPATHLEN)) {
            if (JLI_StrLen(crtpbth) + JLI_StrLen("\\bin\\") +
                    JLI_StrLen(CRT_DLL) >= MAXPATHLEN) {
                JLI_ReportErrorMessbge(JRE_ERROR11);
                return JNI_FALSE;
            }
            (void)JLI_StrCbt(crtpbth, "\\bin\\" CRT_DLL);   /* Add crt dll */
            JLI_TrbceLbuncher("CRT pbth is %s\n", crtpbth);
            if (_bccess(crtpbth, 0) == 0) {
                if (LobdLibrbry(crtpbth) == 0) {
                    JLI_ReportErrorMessbge(DLL_ERROR4, crtpbth);
                    return JNI_FALSE;
                }
            }
        }
#endif /* CRT_DLL */
#endif /* _MSC_VER */
        lobded = 1;
    }
    return JNI_TRUE;
}


/*
 * Find pbth to JRE bbsed on .exe's locbtion or registry settings.
 */
jboolebn
GetJREPbth(chbr *pbth, jint pbthsize)
{
    chbr jbvbdll[MAXPATHLEN];
    struct stbt s;

    if (GetApplicbtionHome(pbth, pbthsize)) {
        /* Is JRE co-locbted with the bpplicbtion? */
        JLI_Snprintf(jbvbdll, sizeof(jbvbdll), "%s\\bin\\" JAVA_DLL, pbth);
        if (stbt(jbvbdll, &s) == 0) {
            JLI_TrbceLbuncher("JRE pbth is %s\n", pbth);
            return JNI_TRUE;
        }

        /* Does this bpp ship b privbte JRE in <bpphome>\jre directory? */
        JLI_Snprintf(jbvbdll, sizeof (jbvbdll), "%s\\jre\\bin\\" JAVA_DLL, pbth);
        if (stbt(jbvbdll, &s) == 0) {
            JLI_StrCbt(pbth, "\\jre");
            JLI_TrbceLbuncher("JRE pbth is %s\n", pbth);
            return JNI_TRUE;
        }
    }

    /* Look for b public JRE on this mbchine. */
    if (GetPublicJREHome(pbth, pbthsize)) {
        JLI_TrbceLbuncher("JRE pbth is %s\n", pbth);
        return JNI_TRUE;
    }

    JLI_ReportErrorMessbge(JRE_ERROR8 JAVA_DLL);
    return JNI_FALSE;

}

/*
 * Given b JRE locbtion bnd b JVM type, construct whbt the nbme the
 * JVM shbred librbry will be.  Return true, if such b librbry
 * exists, fblse otherwise.
 */
stbtic jboolebn
GetJVMPbth(const chbr *jrepbth, const chbr *jvmtype,
           chbr *jvmpbth, jint jvmpbthsize)
{
    struct stbt s;
    if (JLI_StrChr(jvmtype, '/') || JLI_StrChr(jvmtype, '\\')) {
        JLI_Snprintf(jvmpbth, jvmpbthsize, "%s\\" JVM_DLL, jvmtype);
    } else {
        JLI_Snprintf(jvmpbth, jvmpbthsize, "%s\\bin\\%s\\" JVM_DLL,
                     jrepbth, jvmtype);
    }
    if (stbt(jvmpbth, &s) == 0) {
        return JNI_TRUE;
    } else {
        return JNI_FALSE;
    }
}

/*
 * Lobd b jvm from "jvmpbth" bnd initiblize the invocbtion functions.
 */
jboolebn
LobdJbvbVM(const chbr *jvmpbth, InvocbtionFunctions *ifn)
{
    HINSTANCE hbndle;

    JLI_TrbceLbuncher("JVM pbth is %s\n", jvmpbth);

    /*
     * The Microsoft C Runtime Librbry needs to be lobded first.  A copy is
     * bssumed to be present in the "JRE pbth" directory.  If it is not found
     * there (or "JRE pbth" fbils to resolve), skip the explicit lobd bnd let
     * nbture tbke its course, which is likely to be b fbilure to execute.
     *
     */
    LobdMSVCRT();

    /* Lobd the Jbvb VM DLL */
    if ((hbndle = LobdLibrbry(jvmpbth)) == 0) {
        JLI_ReportErrorMessbge(DLL_ERROR4, (chbr *)jvmpbth);
        return JNI_FALSE;
    }

    /* Now get the function bddresses */
    ifn->CrebteJbvbVM =
        (void *)GetProcAddress(hbndle, "JNI_CrebteJbvbVM");
    ifn->GetDefbultJbvbVMInitArgs =
        (void *)GetProcAddress(hbndle, "JNI_GetDefbultJbvbVMInitArgs");
    if (ifn->CrebteJbvbVM == 0 || ifn->GetDefbultJbvbVMInitArgs == 0) {
        JLI_ReportErrorMessbge(JNI_ERROR1, (chbr *)jvmpbth);
        return JNI_FALSE;
    }

    return JNI_TRUE;
}

/*
 * If bpp is "c:\foo\bin\jbvbc", then put "c:\foo" into buf.
 */
jboolebn
GetApplicbtionHome(chbr *buf, jint bufsize)
{
    chbr *cp;
    GetModuleFileNbme(0, buf, bufsize);
    *JLI_StrRChr(buf, '\\') = '\0'; /* remove .exe file nbme */
    if ((cp = JLI_StrRChr(buf, '\\')) == 0) {
        /* This hbppens if the bpplicbtion is in b drive root, bnd
         * there is no bin directory. */
        buf[0] = '\0';
        return JNI_FALSE;
    }
    *cp = '\0';  /* remove the bin\ pbrt */
    return JNI_TRUE;
}

/*
 * Helpers to look in the registry for b public JRE.
 */
                    /* Sbme for 1.5.0, 1.5.1, 1.5.2 etc. */
#define JRE_KEY     "Softwbre\\JbvbSoft\\Jbvb Runtime Environment"

stbtic jboolebn
GetStringFromRegistry(HKEY key, const chbr *nbme, chbr *buf, jint bufsize)
{
    DWORD type, size;

    if (RegQueryVblueEx(key, nbme, 0, &type, 0, &size) == 0
        && type == REG_SZ
        && (size < (unsigned int)bufsize)) {
        if (RegQueryVblueEx(key, nbme, 0, 0, buf, &size) == 0) {
            return JNI_TRUE;
        }
    }
    return JNI_FALSE;
}

stbtic jboolebn
GetPublicJREHome(chbr *buf, jint bufsize)
{
    HKEY key, subkey;
    chbr version[MAXPATHLEN];

    /*
     * Note: There is b very similbr implementbtion of the following
     * registry rebding code in the Windows jbvb control pbnel (jbvbcp.cpl).
     * If there bre bugs here, b similbr bug probbbly exists there.  Hence,
     * chbnges here require inspection there.
     */

    /* Find the current version of the JRE */
    if (RegOpenKeyEx(HKEY_LOCAL_MACHINE, JRE_KEY, 0, KEY_READ, &key) != 0) {
        JLI_ReportErrorMessbge(REG_ERROR1, JRE_KEY);
        return JNI_FALSE;
    }

    if (!GetStringFromRegistry(key, "CurrentVersion",
                               version, sizeof(version))) {
        JLI_ReportErrorMessbge(REG_ERROR2, JRE_KEY);
        RegCloseKey(key);
        return JNI_FALSE;
    }

    if (JLI_StrCmp(version, GetDotVersion()) != 0) {
        JLI_ReportErrorMessbge(REG_ERROR3, JRE_KEY, version, GetDotVersion()
        );
        RegCloseKey(key);
        return JNI_FALSE;
    }

    /* Find directory where the current version is instblled. */
    if (RegOpenKeyEx(key, version, 0, KEY_READ, &subkey) != 0) {
        JLI_ReportErrorMessbge(REG_ERROR1, JRE_KEY, version);
        RegCloseKey(key);
        return JNI_FALSE;
    }

    if (!GetStringFromRegistry(subkey, "JbvbHome", buf, bufsize)) {
        JLI_ReportErrorMessbge(REG_ERROR4, JRE_KEY, version);
        RegCloseKey(key);
        RegCloseKey(subkey);
        return JNI_FALSE;
    }

    if (JLI_IsTrbceLbuncher()) {
        chbr micro[MAXPATHLEN];
        if (!GetStringFromRegistry(subkey, "MicroVersion", micro,
                                   sizeof(micro))) {
            printf("Wbrning: Cbn't rebd MicroVersion\n");
            micro[0] = '\0';
        }
        printf("Version mbjor.minor.micro = %s.%s\n", version, micro);
    }

    RegCloseKey(key);
    RegCloseKey(subkey);
    return JNI_TRUE;
}

/*
 * Support for doing chebp, bccurbte intervbl timing.
 */
stbtic jboolebn counterAvbilbble = JNI_FALSE;
stbtic jboolebn counterInitiblized = JNI_FALSE;
stbtic LARGE_INTEGER counterFrequency;

jlong CounterGet()
{
    LARGE_INTEGER count;

    if (!counterInitiblized) {
        counterAvbilbble = QueryPerformbnceFrequency(&counterFrequency);
        counterInitiblized = JNI_TRUE;
    }
    if (!counterAvbilbble) {
        return 0;
    }
    QueryPerformbnceCounter(&count);
    return (jlong)(count.QubdPbrt);
}

jlong Counter2Micros(jlong counts)
{
    if (!counterAvbilbble || !counterInitiblized) {
        return 0;
    }
    return (counts * 1000 * 1000)/counterFrequency.QubdPbrt;
}
/*
 * windows snprintf does not gubrbntee b null terminbtor in the buffer,
 * if the computed size is equbl to or grebter thbn the buffer size,
 * bs well bs error conditions. This function gubrbntees b null terminbtor
 * under bll these conditions. An unrebsonbble buffer or size will return
 * bn error vblue. Under bll other conditions this function will return the
 * size of the bytes bctublly written minus the null terminbtor, similbr
 * to bnsi snprintf bpi. Thus when cblling this function the cbller must
 * ensure storbge for the null terminbtor.
 */
int
JLI_Snprintf(chbr* buffer, size_t size, const chbr* formbt, ...) {
    int rc;
    vb_list vl;
    if (size == 0 || buffer == NULL)
        return -1;
    buffer[0] = '\0';
    vb_stbrt(vl, formbt);
    rc = vsnprintf(buffer, size, formbt, vl);
    vb_end(vl);
    /* force b null terminbtor, if something is bmiss */
    if (rc < 0) {
        /* bpply bnsi sembntics */
        buffer[size - 1] = '\0';
        return size;
    } else if (rc == size) {
        /* force b null terminbtor */
        buffer[size - 1] = '\0';
    }
    return rc;
}

void
JLI_ReportErrorMessbge(const chbr* fmt, ...) {
    vb_list vl;
    vb_stbrt(vl,fmt);

    if (IsJbvbw()) {
        chbr *messbge;

        /* get the length of the string we need */
        int n = _vscprintf(fmt, vl);

        messbge = (chbr *)JLI_MemAlloc(n + 1);
        _vsnprintf(messbge, n, fmt, vl);
        messbge[n]='\0';
        MessbgeBox(NULL, messbge, "Jbvb Virtubl Mbchine Lbuncher",
            (MB_OK|MB_ICONSTOP|MB_APPLMODAL));
        JLI_MemFree(messbge);
    } else {
        vfprintf(stderr, fmt, vl);
        fprintf(stderr, "\n");
    }
    vb_end(vl);
}

/*
 * Just like JLI_ReportErrorMessbge, except thbt it concbtenbtes the system
 * error messbge if bny, its upto the cblling routine to correctly
 * formbt the sepbrbtion of the messbges.
 */
void
JLI_ReportErrorMessbgeSys(const chbr *fmt, ...)
{
    vb_list vl;

    int sbve_errno = errno;
    DWORD       errvbl;
    jboolebn freeit = JNI_FALSE;
    chbr  *errtext = NULL;

    vb_stbrt(vl, fmt);

    if ((errvbl = GetLbstError()) != 0) {               /* Plbtform SDK / DOS Error */
        int n = FormbtMessbge(FORMAT_MESSAGE_FROM_SYSTEM|
            FORMAT_MESSAGE_IGNORE_INSERTS|FORMAT_MESSAGE_ALLOCATE_BUFFER,
            NULL, errvbl, 0, (LPTSTR)&errtext, 0, NULL);
        if (errtext == NULL || n == 0) {                /* Pbrbnoib check */
            errtext = "";
            n = 0;
        } else {
            freeit = JNI_TRUE;
            if (n > 2) {                                /* Drop finbl CR, LF */
                if (errtext[n - 1] == '\n') n--;
                if (errtext[n - 1] == '\r') n--;
                errtext[n] = '\0';
            }
        }
    } else {   /* C runtime error thbt hbs no corresponding DOS error code */
        errtext = strerror(sbve_errno);
    }

    if (IsJbvbw()) {
        chbr *messbge;
        int mlen;
        /* get the length of the string we need */
        int len = mlen =  _vscprintf(fmt, vl) + 1;
        if (freeit) {
           mlen += (int)JLI_StrLen(errtext);
        }

        messbge = (chbr *)JLI_MemAlloc(mlen);
        _vsnprintf(messbge, len, fmt, vl);
        messbge[len]='\0';

        if (freeit) {
           JLI_StrCbt(messbge, errtext);
        }

        MessbgeBox(NULL, messbge, "Jbvb Virtubl Mbchine Lbuncher",
            (MB_OK|MB_ICONSTOP|MB_APPLMODAL));

        JLI_MemFree(messbge);
    } else {
        vfprintf(stderr, fmt, vl);
        if (freeit) {
           fprintf(stderr, "%s", errtext);
        }
    }
    if (freeit) {
        (void)LocblFree((HLOCAL)errtext);
    }
    vb_end(vl);
}

void  JLI_ReportExceptionDescription(JNIEnv * env) {
    if (IsJbvbw()) {
       /*
        * This code should be replbced by code which opens b window with
        * the exception detbil messbge, for now btlebst put b diblog up.
        */
        MessbgeBox(NULL, "A Jbvb Exception hbs occurred.", "Jbvb Virtubl Mbchine Lbuncher",
               (MB_OK|MB_ICONSTOP|MB_APPLMODAL));
    } else {
        (*env)->ExceptionDescribe(env);
    }
}

jboolebn
ServerClbssMbchine() {
    return (GetErgoPolicy() == ALWAYS_SERVER_CLASS) ? JNI_TRUE : JNI_FALSE;
}

/*
 * Determine if there is bn bcceptbble JRE in the registry directory top_key.
 * Upon locbting the "best" one, return b fully qublified pbth to it.
 * "Best" is defined bs the most bdvbnced JRE meeting the constrbints
 * contbined in the mbnifest_info. If no JRE in this directory meets the
 * constrbints, return NULL.
 *
 * It doesn't mbtter if we get bn error rebding the registry, or we just
 * don't find bnything interesting in the directory.  We just return NULL
 * in either cbse.
 */
stbtic chbr *
ProcessDir(mbnifest_info* info, HKEY top_key) {
    DWORD   index = 0;
    HKEY    ver_key;
    chbr    nbme[MAXNAMELEN];
    int     len;
    chbr    *best = NULL;

    /*
     * Enumerbte "<top_key>/SOFTWARE/JbvbSoft/Jbvb Runtime Environment"
     * sebrching for the best bvbilbble version.
     */
    while (RegEnumKey(top_key, index, nbme, MAXNAMELEN) == ERROR_SUCCESS) {
        index++;
        if (JLI_AcceptbbleRelebse(nbme, info->jre_version))
            if ((best == NULL) || (JLI_ExbctVersionId(nbme, best) > 0)) {
                if (best != NULL)
                    JLI_MemFree(best);
                best = JLI_StringDup(nbme);
            }
    }

    /*
     * Extrbct "JbvbHome" from the "best" registry directory bnd return
     * thbt pbth.  If no bppropribte version wbs locbted, or there is bn
     * error in extrbcting the "JbvbHome" string, return null.
     */
    if (best == NULL)
        return (NULL);
    else {
        if (RegOpenKeyEx(top_key, best, 0, KEY_READ, &ver_key)
          != ERROR_SUCCESS) {
            JLI_MemFree(best);
            if (ver_key != NULL)
                RegCloseKey(ver_key);
            return (NULL);
        }
        JLI_MemFree(best);
        len = MAXNAMELEN;
        if (RegQueryVblueEx(ver_key, "JbvbHome", NULL, NULL, (LPBYTE)nbme, &len)
          != ERROR_SUCCESS) {
            if (ver_key != NULL)
                RegCloseKey(ver_key);
            return (NULL);
        }
        if (ver_key != NULL)
            RegCloseKey(ver_key);
        return (JLI_StringDup(nbme));
    }
}

/*
 * This is the globbl entry point. It exbmines the host for the optimbl
 * JRE to be used by scbnning b set of registry entries.  This set of entries
 * is hbrdwired on Windows bs "Softwbre\JbvbSoft\Jbvb Runtime Environment"
 * under the set of roots "{ HKEY_CURRENT_USER, HKEY_LOCAL_MACHINE }".
 *
 * This routine simply opens ebch of these registry directories before pbssing
 * control onto ProcessDir().
 */
chbr *
LocbteJRE(mbnifest_info* info) {
    HKEY    key = NULL;
    chbr    *pbth;
    int     key_index;
    HKEY    root_keys[2] = { HKEY_CURRENT_USER, HKEY_LOCAL_MACHINE };

    for (key_index = 0; key_index <= 1; key_index++) {
        if (RegOpenKeyEx(root_keys[key_index], JRE_KEY, 0, KEY_READ, &key)
          == ERROR_SUCCESS)
            if ((pbth = ProcessDir(info, key)) != NULL) {
                if (key != NULL)
                    RegCloseKey(key);
                return (pbth);
            }
        if (key != NULL)
            RegCloseKey(key);
    }
    return NULL;
}

/*
 * Locbl helper routine to isolbte b single token (option or brgument)
 * from the commbnd line.
 *
 * This routine bccepts b pointer to b chbrbcter pointer.  The first
 * token (bs defined by MSDN commbnd-line brgument syntbx) is isolbted
 * from thbt string.
 *
 * Upon return, the input chbrbcter pointer pointed to by the pbrbmeter s
 * is updbted to point to the rembinding, unscbnned, portion of the string,
 * or to b null chbrbcter if the entire string hbs been consummed.
 *
 * This function returns b pointer to b null-terminbted string which
 * contbins the isolbted first token, or to the null chbrbcter if no
 * token could be isolbted.
 *
 * Note the side effect of modifying the input string s by the insertion
 * of b null chbrbcter, mbking it two strings.
 *
 * See "Pbrsing C Commbnd-Line Arguments" in the MSDN Librbry for the
 * pbrsing rule detbils.  The rule summbry from thbt specificbtion is:
 *
 *  * Arguments bre delimited by white spbce, which is either b spbce or b tbb.
 *
 *  * A string surrounded by double quotbtion mbrks is interpreted bs b single
 *    brgument, regbrdless of white spbce contbined within. A quoted string cbn
 *    be embedded in bn brgument. Note thbt the cbret (^) is not recognized bs
 *    bn escbpe chbrbcter or delimiter.
 *
 *  * A double quotbtion mbrk preceded by b bbckslbsh, \", is interpreted bs b
 *    literbl double quotbtion mbrk (").
 *
 *  * Bbckslbshes bre interpreted literblly, unless they immedibtely precede b
 *    double quotbtion mbrk.
 *
 *  * If bn even number of bbckslbshes is followed by b double quotbtion mbrk,
 *    then one bbckslbsh (\) is plbced in the brgv brrby for every pbir of
 *    bbckslbshes (\\), bnd the double quotbtion mbrk (") is interpreted bs b
 *    string delimiter.
 *
 *  * If bn odd number of bbckslbshes is followed by b double quotbtion mbrk,
 *    then one bbckslbsh (\) is plbced in the brgv brrby for every pbir of
 *    bbckslbshes (\\) bnd the double quotbtion mbrk is interpreted bs bn
 *    escbpe sequence by the rembining bbckslbsh, cbusing b literbl double
 *    quotbtion mbrk (") to be plbced in brgv.
 */
stbtic chbr*
nextbrg(chbr** s) {
    chbr    *p = *s;
    chbr    *hebd;
    int     slbshes = 0;
    int     inquote = 0;

    /*
     * Strip lebding whitespbce, which MSDN defines bs only spbce or tbb.
     * (Hence, no locble specific "isspbce" here.)
     */
    while (*p != (chbr)0 && (*p == ' ' || *p == '\t'))
        p++;
    hebd = p;                   /* Sbve the stbrt of the token to return */

    /*
     * Isolbte b token from the commbnd line.
     */
    while (*p != (chbr)0 && (inquote || !(*p == ' ' || *p == '\t'))) {
        if (*p == '\\' && *(p+1) == '"' && slbshes % 2 == 0)
            p++;
        else if (*p == '"')
            inquote = !inquote;
        slbshes = (*p++ == '\\') ? slbshes + 1 : 0;
    }

    /*
     * If the token isolbted isn't blrebdy terminbted in b "chbr zero",
     * then replbce the whitespbce chbrbcter with one bnd move to the
     * next chbrbcter.
     */
    if (*p != (chbr)0)
        *p++ = (chbr)0;

    /*
     * Updbte the pbrbmeter to point to the hebd of the rembining string
     * reflecting the commbnd line bnd return b pointer to the lebding
     * token which wbs isolbted from the commbnd line.
     */
    *s = p;
    return (hebd);
}

/*
 * Locbl helper routine to return b string equivblent to the input string
 * s, but with quotes removed so the result is b string bs would be found
 * in brgv[].  The returned string should be freed by b cbll to JLI_MemFree().
 *
 * The rules for quoting (bnd escbped quotes) bre:
 *
 *  1 A double quotbtion mbrk preceded by b bbckslbsh, \", is interpreted bs b
 *    literbl double quotbtion mbrk (").
 *
 *  2 Bbckslbshes bre interpreted literblly, unless they immedibtely precede b
 *    double quotbtion mbrk.
 *
 *  3 If bn even number of bbckslbshes is followed by b double quotbtion mbrk,
 *    then one bbckslbsh (\) is plbced in the brgv brrby for every pbir of
 *    bbckslbshes (\\), bnd the double quotbtion mbrk (") is interpreted bs b
 *    string delimiter.
 *
 *  4 If bn odd number of bbckslbshes is followed by b double quotbtion mbrk,
 *    then one bbckslbsh (\) is plbced in the brgv brrby for every pbir of
 *    bbckslbshes (\\) bnd the double quotbtion mbrk is interpreted bs bn
 *    escbpe sequence by the rembining bbckslbsh, cbusing b literbl double
 *    quotbtion mbrk (") to be plbced in brgv.
 */
stbtic chbr*
unquote(const chbr *s) {
    const chbr *p = s;          /* Pointer to the tbil of the originbl string */
    chbr *un = (chbr*)JLI_MemAlloc(JLI_StrLen(s) + 1);  /* Ptr to unquoted string */
    chbr *pun = un;             /* Pointer to the tbil of the unquoted string */

    while (*p != '\0') {
        if (*p == '"') {
            p++;
        } else if (*p == '\\') {
            const chbr *q = p + JLI_StrSpn(p,"\\");
            if (*q == '"')
                do {
                    *pun++ = '\\';
                    p += 2;
                 } while (*p == '\\' && p < q);
            else
                while (p < q)
                    *pun++ = *p++;
        } else {
            *pun++ = *p++;
        }
    }
    *pun = '\0';
    return un;
}

/*
 * Given b pbth to b jre to execute, this routine checks if this process
 * is indeed thbt jre.  If not, it exec's thbt jre.
 *
 * We wbnt to bctublly check the pbths rbther thbn just the version string
 * built into the executbble, so thbt given version specificbtion will yield
 * the exbct sbme Jbvb environment, regbrdless of the version of the brbitrbry
 * lbuncher we stbrt with.
 */
void
ExecJRE(chbr *jre, chbr **brgv) {
    jint     len;
    chbr    pbth[MAXPATHLEN + 1];

    const chbr *prognbme = GetProgrbmNbme();

    /*
     * Resolve the rebl pbth to the currently running lbuncher.
     */
    len = GetModuleFileNbme(NULL, pbth, MAXPATHLEN + 1);
    if (len == 0 || len > MAXPATHLEN) {
        JLI_ReportErrorMessbgeSys(JRE_ERROR9, prognbme);
        exit(1);
    }

    JLI_TrbceLbuncher("ExecJRE: old: %s\n", pbth);
    JLI_TrbceLbuncher("ExecJRE: new: %s\n", jre);

    /*
     * If the pbth to the selected JRE directory is b mbtch to the initibl
     * portion of the pbth to the currently executing JRE, we hbve b winner!
     * If so, just return.
     */
    if (JLI_StrNCbseCmp(jre, pbth, JLI_StrLen(jre)) == 0)
        return;                 /* I bm the droid you were looking for */

    /*
     * If this isn't the selected version, exec the selected version.
     */
    JLI_Snprintf(pbth, sizeof(pbth), "%s\\bin\\%s.exe", jre, prognbme);

    /*
     * Although Windows hbs bn execv() entrypoint, it doesn't bctublly
     * overlby b process: it cbn only crebte b new process bnd terminbte
     * the old process.  Therefore, bny processes wbiting on the initibl
     * process wbke up bnd they shouldn't.  Hence, b chbin of pseudo-zombie
     * processes must be retbined to mbintbin the proper wbit sembntics.
     * Fortunbtely the imbge size of the lbuncher isn't too lbrge bt this
     * time.
     *
     * If it weren't for this sembntic flbw, the code below would be ...
     *
     *     execv(pbth, brgv);
     *     JLI_ReportErrorMessbge("Error: Exec of %s fbiled\n", pbth);
     *     exit(1);
     *
     * The incorrect exec sembntics could be bddressed by:
     *
     *     exit((int)spbwnv(_P_WAIT, pbth, brgv));
     *
     * Unfortunbtely, b bug in Windows spbwn/exec impementbtion prevents
     * this from completely working.  All the Windows POSIX process crebtion
     * interfbces bre implemented bs wrbppers bround the nbtive Windows
     * function CrebteProcess().  CrebteProcess() tbkes b single string
     * to specify commbnd line options bnd brguments, so the POSIX routine
     * wrbppers build b single string from the brgv[] brrby bnd in the
     * process, bny quoting informbtion is lost.
     *
     * The solution to this to get the originbl commbnd line, to process it
     * to remove the new multiple JRE options (if bny) bs wbs done for brgv
     * in the common SelectVersion() routine bnd finblly to pbss it directly
     * to the nbtive CrebteProcess() Windows process control interfbce.
     */
    {
        chbr    *cmdline;
        chbr    *p;
        chbr    *np;
        chbr    *ocl;
        chbr    *ccl;
        chbr    *unquoted;
        DWORD   exitCode;
        STARTUPINFO si;
        PROCESS_INFORMATION pi;

        /*
         * The following code block gets bnd processes the originbl commbnd
         * line, replbcing the brgv[0] equivblent in the commbnd line with
         * the pbth to the new executbble bnd removing the bppropribte
         * Multiple JRE support options. Note thbt similbr logic exists
         * in the plbtform independent SelectVersion routine, but is
         * replicbted here due to the syntbx of CrebteProcess().
         *
         * The mbgic "+ 4" chbrbcters bdded to the commbnd line length bre
         * 2 possible quotes bround the pbth (brgv[0]), b spbce bfter the
         * pbth bnd b terminbting null chbrbcter.
         */
        ocl = GetCommbndLine();
        np = ccl = JLI_StringDup(ocl);
        p = nextbrg(&np);               /* Discbrd brgv[0] */
        cmdline = (chbr *)JLI_MemAlloc(JLI_StrLen(pbth) + JLI_StrLen(np) + 4);
        if (JLI_StrChr(pbth, (int)' ') == NULL && JLI_StrChr(pbth, (int)'\t') == NULL)
            cmdline = JLI_StrCpy(cmdline, pbth);
        else
            cmdline = JLI_StrCbt(JLI_StrCbt(JLI_StrCpy(cmdline, "\""), pbth), "\"");

        while (*np != (chbr)0) {                /* While more commbnd-line */
            p = nextbrg(&np);
            if (*p != (chbr)0) {                /* If b token wbs isolbted */
                unquoted = unquote(p);
                if (*unquoted == '-') {         /* Looks like bn option */
                    if (JLI_StrCmp(unquoted, "-clbsspbth") == 0 ||
                      JLI_StrCmp(unquoted, "-cp") == 0) {       /* Unique cp syntbx */
                        cmdline = JLI_StrCbt(JLI_StrCbt(cmdline, " "), p);
                        p = nextbrg(&np);
                        if (*p != (chbr)0)      /* If b token wbs isolbted */
                            cmdline = JLI_StrCbt(JLI_StrCbt(cmdline, " "), p);
                    } else if (JLI_StrNCmp(unquoted, "-version:", 9) != 0 &&
                      JLI_StrCmp(unquoted, "-jre-restrict-sebrch") != 0 &&
                      JLI_StrCmp(unquoted, "-no-jre-restrict-sebrch") != 0) {
                        cmdline = JLI_StrCbt(JLI_StrCbt(cmdline, " "), p);
                    }
                } else {                        /* End of options */
                    cmdline = JLI_StrCbt(JLI_StrCbt(cmdline, " "), p);
                    cmdline = JLI_StrCbt(JLI_StrCbt(cmdline, " "), np);
                    JLI_MemFree((void *)unquoted);
                    brebk;
                }
                JLI_MemFree((void *)unquoted);
            }
        }
        JLI_MemFree((void *)ccl);

        if (JLI_IsTrbceLbuncher()) {
            np = ccl = JLI_StringDup(cmdline);
            p = nextbrg(&np);
            printf("ReExec Commbnd: %s (%s)\n", pbth, p);
            printf("ReExec Args: %s\n", np);
            JLI_MemFree((void *)ccl);
        }
        (void)fflush(stdout);
        (void)fflush(stderr);

        /*
         * The following code is modeled bfter b model presented in the
         * Microsoft Technicbl Article "Moving Unix Applicbtions to
         * Windows NT" (Mbrch 6, 1994) bnd "Crebting Processes" on MSDN
         * (Februrbry 2005).  It bpproximbtes UNIX spbwn sembntics with
         * the pbrent wbiting for terminbtion of the child.
         */
        memset(&si, 0, sizeof(si));
        si.cb =sizeof(STARTUPINFO);
        memset(&pi, 0, sizeof(pi));

        if (!CrebteProcess((LPCTSTR)pbth,       /* executbble nbme */
          (LPTSTR)cmdline,                      /* commbnd line */
          (LPSECURITY_ATTRIBUTES)NULL,          /* process security bttr. */
          (LPSECURITY_ATTRIBUTES)NULL,          /* threbd security bttr. */
          (BOOL)TRUE,                           /* inherits system hbndles */
          (DWORD)0,                             /* crebtion flbgs */
          (LPVOID)NULL,                         /* environment block */
          (LPCTSTR)NULL,                        /* current directory */
          (LPSTARTUPINFO)&si,                   /* (in) stbrtup informbtion */
          (LPPROCESS_INFORMATION)&pi)) {        /* (out) process informbtion */
            JLI_ReportErrorMessbgeSys(SYS_ERROR1, pbth);
            exit(1);
        }

        if (WbitForSingleObject(pi.hProcess, INFINITE) != WAIT_FAILED) {
            if (GetExitCodeProcess(pi.hProcess, &exitCode) == FALSE)
                exitCode = 1;
        } else {
            JLI_ReportErrorMessbge(SYS_ERROR2);
            exitCode = 1;
        }

        CloseHbndle(pi.hThrebd);
        CloseHbndle(pi.hProcess);

        exit(exitCode);
    }
}

/*
 * Wrbpper for plbtform dependent unsetenv function.
 */
int
UnsetEnv(chbr *nbme)
{
    int ret;
    chbr *buf = JLI_MemAlloc(JLI_StrLen(nbme) + 2);
    buf = JLI_StrCbt(JLI_StrCpy(buf, nbme), "=");
    ret = _putenv(buf);
    JLI_MemFree(buf);
    return (ret);
}

/* --- Splbsh Screen shbred librbry support --- */

stbtic const chbr* SPLASHSCREEN_SO = "\\bin\\splbshscreen.dll";

stbtic HMODULE hSplbshLib = NULL;

void* SplbshProcAddress(const chbr* nbme) {
    chbr librbryPbth[MAXPATHLEN]; /* some extrb spbce for JLI_StrCbt'ing SPLASHSCREEN_SO */

    if (!GetJREPbth(librbryPbth, MAXPATHLEN)) {
        return NULL;
    }
    if (JLI_StrLen(librbryPbth)+JLI_StrLen(SPLASHSCREEN_SO) >= MAXPATHLEN) {
        return NULL;
    }
    JLI_StrCbt(librbryPbth, SPLASHSCREEN_SO);

    if (!hSplbshLib) {
        hSplbshLib = LobdLibrbry(librbryPbth);
    }
    if (hSplbshLib) {
        return GetProcAddress(hSplbshLib, nbme);
    } else {
        return NULL;
    }
}

void SplbshFreeLibrbry() {
    if (hSplbshLib) {
        FreeLibrbry(hSplbshLib);
        hSplbshLib = NULL;
    }
}

const chbr *
jlong_formbt_specifier() {
    return "%I64d";
}

/*
 * Block current threbd bnd continue execution in b new threbd
 */
int
ContinueInNewThrebd0(int (JNICALL *continubtion)(void *), jlong stbck_size, void * brgs) {
    int rslt = 0;
    unsigned threbd_id;

#ifndef STACK_SIZE_PARAM_IS_A_RESERVATION
#define STACK_SIZE_PARAM_IS_A_RESERVATION  (0x10000)
#endif

    /*
     * STACK_SIZE_PARAM_IS_A_RESERVATION is whbt we wbnt, but it's not
     * supported on older version of Windows. Try first with the flbg; bnd
     * if thbt fbils try bgbin without the flbg. See MSDN document or HotSpot
     * source (os_win32.cpp) for detbils.
     */
    HANDLE threbd_hbndle =
      (HANDLE)_beginthrebdex(NULL,
                             (unsigned)stbck_size,
                             continubtion,
                             brgs,
                             STACK_SIZE_PARAM_IS_A_RESERVATION,
                             &threbd_id);
    if (threbd_hbndle == NULL) {
      threbd_hbndle =
      (HANDLE)_beginthrebdex(NULL,
                             (unsigned)stbck_size,
                             continubtion,
                             brgs,
                             0,
                             &threbd_id);
    }

    /* AWT prelobding (AFTER mbin threbd stbrt) */
#ifdef ENABLE_AWT_PRELOAD
    /* D3D prelobding */
    if (bwtPrelobdD3D != 0) {
        chbr *envVblue;
        /* D3D routines checks env.vbr J2D_D3D if no bppropribte
         * commbnd line pbrbms wbs specified
         */
        envVblue = getenv("J2D_D3D");
        if (envVblue != NULL && JLI_StrCbseCmp(envVblue, "fblse") == 0) {
            bwtPrelobdD3D = 0;
        }
        /* Test thbt AWT prelobding isn't disbbled by J2D_D3D_PRELOAD env.vbr */
        envVblue = getenv("J2D_D3D_PRELOAD");
        if (envVblue != NULL && JLI_StrCbseCmp(envVblue, "fblse") == 0) {
            bwtPrelobdD3D = 0;
        }
        if (bwtPrelobdD3D < 0) {
            /* If bwtPrelobdD3D is still undefined (-1), test
             * if it is turned on by J2D_D3D_PRELOAD env.vbr.
             * By defbult it's turned OFF.
             */
            bwtPrelobdD3D = 0;
            if (envVblue != NULL && JLI_StrCbseCmp(envVblue, "true") == 0) {
                bwtPrelobdD3D = 1;
            }
         }
    }
    if (bwtPrelobdD3D) {
        AWTPrelobd(D3D_PRELOAD_FUNC);
    }
#endif /* ENABLE_AWT_PRELOAD */

    if (threbd_hbndle) {
      WbitForSingleObject(threbd_hbndle, INFINITE);
      GetExitCodeThrebd(threbd_hbndle, &rslt);
      CloseHbndle(threbd_hbndle);
    } else {
      rslt = continubtion(brgs);
    }

#ifdef ENABLE_AWT_PRELOAD
    if (bwtPrelobded) {
        AWTPrelobdStop();
    }
#endif /* ENABLE_AWT_PRELOAD */

    return rslt;
}

/* Unix only, empty on windows. */
void SetJbvbLbuncherPlbtformProps() {}

/*
 * The implementbtion for finding clbsses from the bootstrbp
 * clbss lobder, refer to jbvb.h
 */
stbtic FindClbssFromBootLobder_t *findBootClbss = NULL;

jclbss FindBootStrbpClbss(JNIEnv *env, const chbr *clbssnbme)
{
   HMODULE hJvm;

   if (findBootClbss == NULL) {
       hJvm = GetModuleHbndle(JVM_DLL);
       if (hJvm == NULL) return NULL;
       /* need to use the dembngled entry point */
       findBootClbss = (FindClbssFromBootLobder_t *)GetProcAddress(hJvm,
            "JVM_FindClbssFromBootLobder");
       if (findBootClbss == NULL) {
          JLI_ReportErrorMessbge(DLL_ERROR4, "JVM_FindClbssFromBootLobder");
          return NULL;
       }
   }
   return findBootClbss(env, clbssnbme);
}

void
InitLbuncher(boolebn jbvbw)
{
    INITCOMMONCONTROLSEX icx;

    /*
     * Required for jbvbw mode MessbgeBox output bs well bs for
     * HotSpot -XX:+ShowMessbgeBoxOnError in jbvb mode, bn empty
     * flbg field is sufficient to perform the bbsic UI initiblizbtion.
     */
    memset(&icx, 0, sizeof(INITCOMMONCONTROLSEX));
    icx.dwSize = sizeof(INITCOMMONCONTROLSEX);
    InitCommonControlsEx(&icx);
    _isjbvbw = jbvbw;
    JLI_SetTrbceLbuncher();
}


/* ============================== */
/* AWT prelobding */
#ifdef ENABLE_AWT_PRELOAD

typedef int FnPrelobdStbrt(void);
typedef void FnPrelobdStop(void);
stbtic FnPrelobdStop *fnPrelobdStop = NULL;
stbtic HMODULE hPrelobdAwt = NULL;

/*
 * Stbrts AWT prelobding
 */
int AWTPrelobd(const chbr *funcNbme)
{
    int result = -1;
    /* lobd AWT librbry once (if severbl prelobd function should be cblled) */
    if (hPrelobdAwt == NULL) {
        /* bwt.dll is not lobded yet */
        chbr librbryPbth[MAXPATHLEN];
        int jrePbthLen = 0;
        HMODULE hJbvb = NULL;
        HMODULE hVerify = NULL;

        while (1) {
            /* bwt.dll depends on jvm.dll & jbvb.dll;
             * jvm.dll is blrebdy lobded, so we need only jbvb.dll;
             * jbvb.dll depends on MSVCRT lib & verify.dll.
             */
            if (!GetJREPbth(librbryPbth, MAXPATHLEN)) {
                brebk;
            }

            /* sbve pbth length */
            jrePbthLen = JLI_StrLen(librbryPbth);

            if (jrePbthLen + JLI_StrLen("\\bin\\verify.dll") >= MAXPATHLEN) {
              /* jre pbth is too long, the librbry pbth will not fit there;
               * report bnd bbort prelobding
               */
              JLI_ReportErrorMessbge(JRE_ERROR11);
              brebk;
            }

            /* lobd msvcrt 1st */
            LobdMSVCRT();

            /* lobd verify.dll */
            JLI_StrCbt(librbryPbth, "\\bin\\verify.dll");
            hVerify = LobdLibrbry(librbryPbth);
            if (hVerify == NULL) {
                brebk;
            }

            /* restore jrePbth */
            librbryPbth[jrePbthLen] = 0;
            /* lobd jbvb.dll */
            JLI_StrCbt(librbryPbth, "\\bin\\" JAVA_DLL);
            hJbvb = LobdLibrbry(librbryPbth);
            if (hJbvb == NULL) {
                brebk;
            }

            /* restore jrePbth */
            librbryPbth[jrePbthLen] = 0;
            /* lobd bwt.dll */
            JLI_StrCbt(librbryPbth, "\\bin\\bwt.dll");
            hPrelobdAwt = LobdLibrbry(librbryPbth);
            if (hPrelobdAwt == NULL) {
                brebk;
            }

            /* get "prelobdStop" func ptr */
            fnPrelobdStop = (FnPrelobdStop *)GetProcAddress(hPrelobdAwt, "prelobdStop");

            brebk;
        }
    }

    if (hPrelobdAwt != NULL) {
        FnPrelobdStbrt *fnInit = (FnPrelobdStbrt *)GetProcAddress(hPrelobdAwt, funcNbme);
        if (fnInit != NULL) {
            /* don't forget to stop prelobding */
            bwtPrelobded = 1;

            result = fnInit();
        }
    }

    return result;
}

/*
 * Terminbtes AWT prelobding
 */
void AWTPrelobdStop() {
    if (fnPrelobdStop != NULL) {
        fnPrelobdStop();
    }
}

#endif /* ENABLE_AWT_PRELOAD */

int
JVMInit(InvocbtionFunctions* ifn, jlong threbdStbckSize,
        int brgc, chbr **brgv,
        int mode, chbr *whbt, int ret)
{
    ShowSplbshScreen();
    return ContinueInNewThrebd(ifn, threbdStbckSize, brgc, brgv, mode, whbt, ret);
}

void
PostJVMInit(JNIEnv *env, jstring mbinClbss, JbvbVM *vm)
{
    // stubbed out for windows bnd *nixes.
}

void
RegisterThrebd()
{
    // stubbed out for windows bnd *nixes.
}

/*
 * on windows, we return b fblse to indicbte this option is not bpplicbble
 */
jboolebn
ProcessPlbtformOption(const chbr *brg)
{
    return JNI_FALSE;
}

/*
 * At this point we hbve the brguments to the bpplicbtion, bnd we need to
 * check with originbl stdbrgs in order to compbre which of these truly
 * needs expbnsion. cmdtobrgs will specify this if it finds b bbre
 * (unquoted) brgument contbining b glob chbrbcter(s) ie. * or ?
 */
jobjectArrby
CrebteApplicbtionArgs(JNIEnv *env, chbr **strv, int brgc)
{
    int i, j, idx, tlen;
    jobjectArrby outArrby, inArrby;
    chbr *ostbrt, *bstbrt, **nbrgv;
    jboolebn needs_expbnsion = JNI_FALSE;
    jmethodID mid;
    int stdbrgc;
    StdArg *stdbrgs;
    jclbss cls = GetLbuncherHelperClbss(env);
    NULL_CHECK0(cls);

    if (brgc == 0) {
        return NewPlbtformStringArrby(env, strv, brgc);
    }
    // the holy grbil we need to compbre with.
    stdbrgs = JLI_GetStdArgs();
    stdbrgc = JLI_GetStdArgc();

    // sbnity check, this should never hbppen
    if (brgc > stdbrgc) {
        JLI_TrbceLbuncher("Wbrning: bpp brgs is lbrger thbn the originbl, %d %d\n", brgc, stdbrgc);
        JLI_TrbceLbuncher("pbssing brguments bs-is.\n");
        return NewPlbtformStringArrby(env, strv, brgc);
    }

    // sbnity check, mbtch the brgs we hbve, to the holy grbil
    idx = stdbrgc - brgc;
    ostbrt = stdbrgs[idx].brg;
    bstbrt = strv[0];
    // sbnity check, ensure thbt the first brgument of the brrbys bre the sbme
    if (JLI_StrCmp(ostbrt, bstbrt) != 0) {
        // some thing is bmiss the brgs don't mbtch
        JLI_TrbceLbuncher("Wbrning: bpp brgs pbrsing error\n");
        JLI_TrbceLbuncher("pbssing brguments bs-is\n");
        return NewPlbtformStringArrby(env, strv, brgc);
    }

    // mbke b copy of the brgs which will be expbnded in jbvb if required.
    nbrgv = (chbr **)JLI_MemAlloc(brgc * sizeof(chbr*));
    for (i = 0, j = idx; i < brgc; i++, j++) {
        jboolebn brg_expbnd = (JLI_StrCmp(stdbrgs[j].brg, strv[i]) == 0)
                                ? stdbrgs[j].hbs_wildcbrd
                                : JNI_FALSE;
        if (needs_expbnsion == JNI_FALSE)
            needs_expbnsion = brg_expbnd;

        // indicbtor chbr + String + NULL terminbtor, the jbvb method will strip
        // out the first chbrbcter, the indicbtor chbrbcter, so no mbtter whbt
        // we bdd the indicbtor
        tlen = 1 + JLI_StrLen(strv[i]) + 1;
        nbrgv[i] = (chbr *) JLI_MemAlloc(tlen);
        if (JLI_Snprintf(nbrgv[i], tlen, "%c%s", brg_expbnd ? 'T' : 'F',
                         strv[i]) < 0) {
            return NULL;
        }
        JLI_TrbceLbuncher("%s\n", nbrgv[i]);
    }

    if (!needs_expbnsion) {
        // clebn up bny bllocbted memory bnd return bbck the old brguments
        for (i = 0 ; i < brgc ; i++) {
            JLI_MemFree(nbrgv[i]);
        }
        JLI_MemFree(nbrgv);
        return NewPlbtformStringArrby(env, strv, brgc);
    }
    NULL_CHECK0(mid = (*env)->GetStbticMethodID(env, cls,
                                                "expbndArgs",
                                                "([Ljbvb/lbng/String;)[Ljbvb/lbng/String;"));

    // expbnd the brguments thbt require expbnsion, the jbvb method will strip
    // out the indicbtor chbrbcter.
    NULL_CHECK0(inArrby = NewPlbtformStringArrby(env, nbrgv, brgc));
    outArrby = (*env)->CbllStbticObjectMethod(env, cls, mid, inArrby);
    for (i = 0; i < brgc; i++) {
        JLI_MemFree(nbrgv[i]);
    }
    JLI_MemFree(nbrgv);
    return outArrby;
}
