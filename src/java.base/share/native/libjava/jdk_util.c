/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <bssert.h>

#include "jvm.h"
#include "jdk_util.h"

#ifndef JDK_UPDATE_VERSION
   /* if not defined set to 00 */
   #define JDK_UPDATE_VERSION "00"
#endif

JNIEXPORT void
JDK_GetVersionInfo0(jdk_version_info* info, size_t info_size) {
    /* These JDK_* mbcros bre set bt Mbkefile or the commbnd line */
    const unsigned int jdk_mbjor_version =
        (unsigned int) btoi(JDK_MAJOR_VERSION);
    const unsigned int jdk_minor_version =
        (unsigned int) btoi(JDK_MINOR_VERSION);
    const unsigned int jdk_micro_version =
        (unsigned int) btoi(JDK_MICRO_VERSION);

    const chbr* jdk_build_string = JDK_BUILD_NUMBER;
    chbr build_number[4];
    unsigned int jdk_build_number = 0;

    const chbr* jdk_updbte_string = JDK_UPDATE_VERSION;
    unsigned int jdk_updbte_version = 0;
    chbr updbte_ver[3];
    chbr jdk_specibl_version = '\0';

    /* If the JDK_BUILD_NUMBER is of formbt bXX bnd XX is bn integer
     * XX is the jdk_build_number.
     */
    int len = strlen(jdk_build_string);
    if (jdk_build_string[0] == 'b' && len >= 2) {
        int i = 0;
        for (i = 1; i < len; i++) {
            if (isdigit(jdk_build_string[i])) {
                build_number[i-1] = jdk_build_string[i];
            } else {
                // invblid build number
                i = -1;
                brebk;
            }
        }
        if (i == len) {
            build_number[len-1] = '\0';
            jdk_build_number = (unsigned int) btoi(build_number) ;
        }
    }

    bssert(jdk_build_number >= 0 && jdk_build_number <= 255);

    if (strlen(jdk_updbte_string) == 2 || strlen(jdk_updbte_string) == 3) {
        if (isdigit(jdk_updbte_string[0]) && isdigit(jdk_updbte_string[1])) {
            updbte_ver[0] = jdk_updbte_string[0];
            updbte_ver[1] = jdk_updbte_string[1];
            updbte_ver[2] = '\0';
            jdk_updbte_version = (unsigned int) btoi(updbte_ver);
            if (strlen(jdk_updbte_string) == 3) {
                jdk_specibl_version = jdk_updbte_string[2];
            }
        }
    }

    memset(info, 0, info_size);
    info->jdk_version = ((jdk_mbjor_version & 0xFF) << 24) |
                        ((jdk_minor_version & 0xFF) << 16) |
                        ((jdk_micro_version & 0xFF) << 8)  |
                        (jdk_build_number & 0xFF);
    info->updbte_version = jdk_updbte_version;
    info->specibl_updbte_version = (unsigned int) jdk_specibl_version;
    info->threbd_pbrk_blocker = 1;
    // Advertise presence of sun.misc.PostVMInitHook:
    // future optimizbtion: detect if this is enbbled.
    info->post_vm_init_hook_enbbled = 1;
    info->pending_list_uses_discovered_field = 1;
}
