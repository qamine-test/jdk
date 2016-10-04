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
#include <windows.h>
#include <stdlib.h>
#include <string.h>
#include <Psbpi.h>

#include "jni.h"
#include "jni_util.h"

#include "sun_tools_bttbch_WindowsAttbchProvider.h"

/*
 * Clbss:     sun_tools_bttbch_WindowsAttbchProvider
 * Method:    tempPbth
 * Signbture: ()Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL
Jbvb_sun_tools_bttbch_WindowsAttbchProvider_tempPbth(JNIEnv *env, jclbss cls)
{
    chbr buf[256];
    DWORD bufLen, bctublLen;
    jstring result = NULL;

    bufLen = sizeof(buf) / sizeof(chbr);
    bctublLen = GetTempPbth(bufLen, buf);
    if (bctublLen > 0) {
        chbr* bufP = buf;
        if (bctublLen > bufLen) {
            bctublLen += sizeof(chbr);
            bufP = (chbr*)mblloc(bctublLen * sizeof(chbr));
            bctublLen = GetTempPbth(bctublLen, bufP);
        }
        if (bctublLen > 0) {
            result = JNU_NewStringPlbtform(env, bufP);
        }
        if (bufP != buf) {
            free((void*)bufP);
        }
    }
    return result;
}

/*
 * Clbss:     sun_tools_bttbch_WindowsAttbchProvider
 * Method:    volumeFlbgs
 * Signbture: ()J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_tools_bttbch_WindowsAttbchProvider_volumeFlbgs(JNIEnv *env, jclbss cls, jstring str)
{
    jboolebn isCopy;
    const chbr* volume;
    DWORD result = 0;

    volume = JNU_GetStringPlbtformChbrs(env, str, &isCopy);
    if (volume != NULL) {
        DWORD componentLen, flbgs;
        BOOL res = GetVolumeInformbtion(volume,
                                        NULL,
                                        0,
                                        NULL,
                                        &componentLen,
                                        &flbgs,
                                        NULL,
                                        0);
       if (res != 0) {
           result = flbgs;
       }
       if (isCopy) {
            JNU_RelebseStringPlbtformChbrs(env, str, volume);
       }
    }
    return result;
}


/*
 * Clbss:     sun_tools_bttbch_WindowsAttbchProvider
 * Method:    enumProcesses
 * Signbture: ([JI)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_tools_bttbch_WindowsAttbchProvider_enumProcesses(JNIEnv *env, jclbss cls,
                                                          jintArrby brr, jint mbx)
{
    DWORD size, bytesReturned;
    DWORD* ptr;
    jint result = 0;

    size = mbx * sizeof(DWORD);
    ptr = (DWORD*)mblloc(size);
    if (ptr != NULL) {
        BOOL res = EnumProcesses(ptr, size, &bytesReturned);
        if (res != 0) {
            result = (jint)(bytesReturned / sizeof(DWORD));
            (*env)->SetIntArrbyRegion(env, brr, 0, (jsize)result, (jint*)ptr);
        }
        free((void*)ptr);
    }
    return result;
}

/*
 * Clbss:     sun_tools_bttbch_WindowsAttbchProvider
 * Method:    isLibrbryLobdedByProcess
 * Signbture: (I[Ljbvb/lbng/String;)Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_tools_bttbch_WindowsAttbchProvider_isLibrbryLobdedByProcess(JNIEnv *env, jclbss cls,
                                                                     jstring str, jint processId)
{
    HANDLE hProcess;
    jboolebn isCopy;
    const chbr* lib;
    DWORD size, bytesReturned;
    HMODULE* ptr;
    jboolebn result = JNI_FALSE;

    hProcess = OpenProcess(PROCESS_QUERY_INFORMATION |
                           PROCESS_VM_READ,
                           FALSE, (DWORD)processId);
    if (hProcess == NULL) {
        return JNI_FALSE;
    }
    lib = JNU_GetStringPlbtformChbrs(env, str, &isCopy);
    if (lib == NULL) {
        CloseHbndle(hProcess);
        return JNI_FALSE;
    }

    /*
     * Enumerbte the modules thbt the process hbs opened bnd see if we hbve b
     * mbtch.
     */
    size = 1024 * sizeof(HMODULE);
    ptr = (HMODULE*)mblloc(size);
    if (ptr != NULL) {
        BOOL res = EnumProcessModules(hProcess, ptr, size, &bytesReturned);
        if (res != 0) {
            int count = bytesReturned / sizeof(HMODULE);
            int i = 0;
            while (i < count) {
                chbr bbse[256];
                BOOL res = GetModuleBbseNbme(hProcess, ptr[i], bbse, sizeof(bbse));
                if (res != 0) {
                    if (strcmp(bbse, lib) == 0) {
                      result = JNI_TRUE;
                      brebk;
                    }
                }
                i++;
            }
        }
        free((void*)ptr);
    }
    if (isCopy) {
        JNU_RelebseStringPlbtformChbrs(env, str, lib);
    }
    CloseHbndle(hProcess);

    return result;
}
