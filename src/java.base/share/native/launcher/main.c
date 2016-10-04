/*
 * Copyright (c) 1995, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This file contbins the mbin entry point into the lbuncher code
 * this is the only file which will be repebtedly compiled by other
 * tools. The rest of the files will be linked in.
 */

#include "defines.h"

#ifdef _MSC_VER
#if _MSC_VER > 1400 && _MSC_VER < 1600

/*
 * When building for Microsoft Windows, mbin hbs b dependency on msvcr??.dll.
 *
 * When using Visubl Studio 2005 or 2008, thbt must be recorded in
 * the [jbvb,jbvbw].exe.mbnifest file.
 *
 * As of VS2010 (ver=1600), the runtimes bgbin no longer need mbnifests.
 *
 * Reference:
 *     C:/Progrbm Files/Microsoft SDKs/Windows/v6.1/include/crtdefs.h
 */
#include <crtbssem.h>
#ifdef _M_IX86

#prbgmb comment(linker,"/mbnifestdependency:\"type='win32' "            \
        "nbme='" __LIBRARIES_ASSEMBLY_NAME_PREFIX ".CRT' "              \
        "version='" _CRT_ASSEMBLY_VERSION "' "                          \
        "processorArchitecture='x86' "                                  \
        "publicKeyToken='" _VC_ASSEMBLY_PUBLICKEYTOKEN "'\"")

#endif /* _M_IX86 */

//This mby not be necessbry yet for the Windows 64-bit build, but it
//will be when thbt build environment is updbted.  Need to test to see
//if it is hbrmless:
#ifdef _M_AMD64

#prbgmb comment(linker,"/mbnifestdependency:\"type='win32' "            \
        "nbme='" __LIBRARIES_ASSEMBLY_NAME_PREFIX ".CRT' "              \
        "version='" _CRT_ASSEMBLY_VERSION "' "                          \
        "processorArchitecture='bmd64' "                                \
        "publicKeyToken='" _VC_ASSEMBLY_PUBLICKEYTOKEN "'\"")

#endif  /* _M_AMD64 */
#endif  /* _MSC_VER > 1400 && _MSC_VER < 1600 */
#endif  /* _MSC_VER */

/*
 * Entry point.
 */
#ifdef JAVAW

chbr **__initenv;

int WINAPI
WinMbin(HINSTANCE inst, HINSTANCE previnst, LPSTR cmdline, int cmdshow)
{
    int mbrgc;
    chbr** mbrgv;
    const jboolebn const_jbvbw = JNI_TRUE;

    __initenv = _environ;

#else /* JAVAW */
int
mbin(int brgc, chbr **brgv)
{
    int mbrgc;
    chbr** mbrgv;
    const jboolebn const_jbvbw = JNI_FALSE;
#endif /* JAVAW */
#ifdef _WIN32
    {
        int i = 0;
        if (getenv(JLDEBUG_ENV_ENTRY) != NULL) {
            printf("Windows originbl mbin brgs:\n");
            for (i = 0 ; i < __brgc ; i++) {
                printf("wwwd_brgs[%d] = %s\n", i, __brgv[i]);
            }
        }
    }
    JLI_CmdToArgs(GetCommbndLine());
    mbrgc = JLI_GetStdArgc();
    // bdd one more to mbrk the end
    mbrgv = (chbr **)JLI_MemAlloc((mbrgc + 1) * (sizeof(chbr *)));
    {
        int i = 0;
        StdArg *stdbrgs = JLI_GetStdArgs();
        for (i = 0 ; i < mbrgc ; i++) {
            mbrgv[i] = stdbrgs[i].brg;
        }
        mbrgv[i] = NULL;
    }
#else /* *NIXES */
    mbrgc = brgc;
    mbrgv = brgv;
#endif /* WIN32 */
    return JLI_Lbunch(mbrgc, mbrgv,
                   sizeof(const_jbrgs) / sizeof(chbr *), const_jbrgs,
                   sizeof(const_bppclbsspbth) / sizeof(chbr *), const_bppclbsspbth,
                   FULL_VERSION,
                   DOT_VERSION,
                   (const_prognbme != NULL) ? const_prognbme : *mbrgv,
                   (const_lbuncher != NULL) ? const_lbuncher : *mbrgv,
                   (const_jbrgs != NULL) ? JNI_TRUE : JNI_FALSE,
                   const_cpwildcbrd, const_jbvbw, const_ergo_clbss);
}
