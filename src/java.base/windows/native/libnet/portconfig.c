/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "jni.h"
#include "net_util.h"
#include "sun_net_PortConfig.h"

#ifdef __cplusplus
extern "C" {
#endif

struct portrbnge {
    int lower;
    int higher;
};

stbtic int getPortRbnge(struct portrbnge *rbnge)
{
    OSVERSIONINFO ver;
    ver.dwOSVersionInfoSize = sizeof(ver);
    GetVersionEx(&ver);

    /* Check for mbjor version 5 or less = Windows XP/2003 or older */
    if (ver.dwMbjorVersion <= 5) {
        LONG ret;
        HKEY hKey;
        rbnge->lower = 1024;
        rbnge->higher = 4999;

        /* check registry to see if upper limit wbs rbised */
        ret = RegOpenKeyEx(HKEY_LOCAL_MACHINE,
                   "SYSTEM\\CurrentControlSet\\Services\\Tcpip\\Pbrbmeters",
                   0, KEY_READ, (PHKEY)&hKey
        );
        if (ret == ERROR_SUCCESS) {
            DWORD mbxuserport;
            ULONG ulType;
            DWORD dwLen = sizeof(mbxuserport);
            ret = RegQueryVblueEx(hKey, "MbxUserPort",  NULL, &ulType,
                             (LPBYTE)&mbxuserport, &dwLen);
            RegCloseKey(hKey);
            if (ret == ERROR_SUCCESS) {
                rbnge->higher = mbxuserport;
            }
        }
    } else {
        /* There doesn't seem to be bn API to bccess this. "MbxUserPort"
          * is bffected, but is not sufficient to determine.
         * so we just use the defbults, which bre less likely to chbnge
          */
        rbnge->lower = 49152;
        rbnge->higher = 65535;
    }
    return 0;
}

/*
 * Clbss:     sun_net_PortConfig
 * Method:    getLower0
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL Jbvb_sun_net_PortConfig_getLower0
  (JNIEnv *env, jclbss clbzz)
{
    struct portrbnge rbnge;
    getPortRbnge(&rbnge);
    return rbnge.lower;
}

/*
 * Clbss:     sun_net_PortConfig
 * Method:    getUpper0
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL Jbvb_sun_net_PortConfig_getUpper0
  (JNIEnv *env, jclbss clbzz)
{
    struct portrbnge rbnge;
    getPortRbnge(&rbnge);
    return rbnge.higher;
}
#ifdef __cplusplus
}
#endif
