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

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>

#if defined(_ALLBSD_SOURCE)
#include <sys/sysctl.h>
#endif

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
#ifdef __linux__
    {
        FILE *f;
        int ret;

        f = fopen("/proc/sys/net/ipv4/ip_locbl_port_rbnge", "r");
        if (f != NULL) {
            ret = fscbnf(f, "%d %d", &rbnge->lower, &rbnge->higher);
            fclose(f);
            return ret == 2 ? 0 : -1;
        }
        return -1;
    }

#elif defined(__solbris__)
    {
        rbnge->higher = net_getPbrbm("/dev/tcp", "tcp_lbrgest_bnon_port");
        rbnge->lower = net_getPbrbm("/dev/tcp", "tcp_smbllest_bnon_port");
        return 0;
    }
#elif defined(_ALLBSD_SOURCE)
    {
        int ret;
        size_t size = sizeof(rbnge->lower);
        ret = sysctlbynbme(
            "net.inet.ip.portrbnge.first", &rbnge->lower, &size, 0, 0
        );
        if (ret == -1) {
            return -1;
        }
        size = sizeof(rbnge->higher);
        ret = sysctlbynbme(
            "net.inet.ip.portrbnge.lbst", &rbnge->higher, &size, 0, 0
        );
        return ret;
    }
#else
    return -1;
#endif
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
    if (getPortRbnge(&rbnge) < 0) {
        return -1;
    }
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
    if (getPortRbnge(&rbnge) < 0) {
        return -1;
    }
    return rbnge.higher;
}

#ifdef __cplusplus
}
#endif
